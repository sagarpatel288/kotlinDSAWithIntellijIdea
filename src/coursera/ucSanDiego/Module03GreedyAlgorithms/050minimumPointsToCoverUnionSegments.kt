package coursera.ucSanDiego.Module03GreedyAlgorithms

fun main() {

    /**
     * Visual references:
     *
     * res/level30Module3AlgorithmExercise/050_1minimumPointsToCoverUnionSegments.jpeg
     *
     * res/level30Module3AlgorithmExercise/050_2minimumPointsToCoverUnionSegments.jpeg
     *
     * [Interactive Visual](https://discrete-math-puzzles.github.io/puzzles/touch-all-segments/index.html)
     *
     * Covering Segments by Points Problem.
     * Find the minimum number of points needed to cover all given segments on a line.
     *
     * Input: A sequence of n segments [l1, r1],...,[ln, rn] on a line.
     * Output: A set of points of minimum size such that each segment [li, ri] contains a point,
     * i.e., there exists a point x from this set such that li ≤ x ≤ ri.
     *
     * You are responsible for collecting signatures from all tenants in a building.
     * For each tenant, you know a period of time when he or she is at home.
     * You would like to collect all signatures by visiting the building as few times as possible.
     * For simplicity, we assume that when you enter the building,
     * you instantly collect the signatures of all tenants that are in the building at that time.
     *
     * Input format.
     *
     * The first line of the input contains the number n of segments.
     * Each of the following n lines contains two integers li and ri (separated by a space)
     * defining the coordinates of endpoints of the i-th segment.
     *
     * Output format.
     *
     * The minimum number k of points on the first line and the integer coordinates of k points
     * (separated by spaces) on the second line. You can output the points in any order.
     * If there are multiple such sets of points, you can output any of them.
     *
     * Constraints. 1 ≤ n ≤100; 0 ≤ li ≤ ri ≤ 10^9 for all i.
     *
     * The horizontal lines are segments.
     * All the segments are on vertical lines.
     * A point is a specific commonplace on both the horizontal line (segment) and the vertical line.
     * l1 Stands for the left side (a.k.a. Starting point) of the first segment and
     * r1 stands for the right side (a.k.a. Ending point) of the first segment.
     * So, ln represents the left side or starting point of the nth segment and
     * rn represents the right side or ending point of the nth segment.
     * When we say “minimum points needed to cover all the segments”,
     * we are asking to find minimum numbers of the points (vertical lines) we need to select in such a way that
     * the selection touches all the segments.
     *
     * [Image1](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/aca23b779d4b2eccdc9cbba3d3191808ebfa5ad9/res/level30Module3AlgorithmExercise/050_1minimumPointsToCoverUnionSegments.jpeg)
     *
     * For example, in the given image,
     * we have selected and highlighted a vertical line that all the 3 segments touch.
     * It means that the selected point on the selected line touches all the 3 segments.
     * So, we can say that for the given image, only one point is enough to cover (touch) all the 3 segments.
     * But it does not have to be only one point in all the cases. For example:
     *
     * [Image2](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/aca23b779d4b2eccdc9cbba3d3191808ebfa5ad9/res/level30Module3AlgorithmExercise/050_2minimumPointsToCoverUnionSegments.jpeg)
     *
     * For the above image (example), we must select 5 points to cover all the segments.
     *
     * The key observation here is that by always choosing the rightmost endpoint of the segments that are still uncovered,
     * we can ensure that we minimise the number of points used.
     *
     * Let us take the first sample.
     *
     * Sample 1.
     * Input:
     *
     * 3
     *
     * 1 3
     *
     * 2 5
     *
     * 3 6
     *
     * Here, the first segment is [1, 3].
     * If we take point 3, it covers (touches, overlaps) the second segment [2, 5] and the third segment [3, 6].
     * Hence, we only need one point at coordinate 3 to touch all three segments.
     *
     * If we notice one thing here,
     * the left side (starting point) and the right side (ending point) of each segment are sorted in ascending order
     * (lower to higher).
     *
     * However, it might be impossible to sort both the left side (starting point) and the right side (ending point)
     * of each segment in all the cases. For example,
     *
     * Sample 2.
     * Input:
     *
     * 4
     *
     * 4 7
     *
     * 1 3
     *
     * 2 5
     *
     * 5 6
     *
     * Here, either we can sort the left side (starting point) of each segment or the right side (ending point).
     *
     * Let us sort by the left side (starting point) first.
     *
     * 1 3
     *
     * 2 5
     *
     * 4 7
     *
     * 5 6
     *
     * We can see that if we take coordinate 3, it covers the first segment [1, 3] and the second segment [2, 5].
     * Similarly, if we take coordinate 5 or 6, it covers the third segment [4, 7] and the fourth segment [5, 6].
     * Hence, we need at least 2 points, at coordinates 3 and 5 or 3 and 6, to cover all the segments.
     *
     * Now, let us sort by the right side (ending point).
     *
     * 1 3
     *
     * 2 5
     *
     * 5 6
     *
     * 4 7
     *
     * Again, we can see that if we take coordinate 3, it covers the first segment [1, 3] and the second segment [2, 5].
     * Similarly, if we take coordinate 5 or 6, it covers the third segment [5, 6] and the fourth segment [4, 7].
     * Hence, we need at least 2 points, at coordinates 3 and 5 or 3 and 6, to cover all the segments.
     *
     * Visual representation:
     *
     * | Step 	| Current Point 	| Segment (li, ri) 	| Current<br>End Point (CEP) 	| Is Covered?<br>(CEP >= start)? 	| Action          	| Current<br>End Point (CEP) 	| Points 	|
     * |------	|---------------	|------------------	|----------------------------	|-------------------------------	|-----------------	|----------------------------	|--------	|
     * | 1    	| -1            	| (1, 3)           	| -1                         	| No                            	| Add 3 to Points 	| 3                          	| [3]    	|
     * | 2    	| 3             	| (2, 5)           	| 3                          	| Yes                           	| No Action       	| 3                          	| [3]    	|
     * | 3    	| 3             	| (5, 6)           	| 3                          	| No                            	| Add 6 to Points 	| 6                          	| [3, 6] 	|
     * | 4    	| 6             	| (4, 7)           	| 6                          	| Yes                           	| No Action       	| 6                          	| [3, 6] 	|
     *
     */
    fun minimumPointsToTouchAllTheSegments(listOfSegments: List<Pair<Int, Int>>): List<Int> {
        // Regular checks
        if (listOfSegments.isEmpty()) {
            return emptyList()
        }
        if (listOfSegments.size == 1) {
            return listOf(listOfSegments[0].second)
        }
        // Create a bucket to store points that we need to touch.
        val mutableListOfCommonPoints = mutableListOf<Int>()
        // Sort the segments either by the starting point or the ending point in ascending order.
        val sortedSegmentsByRightEnd = listOfSegments.sortedBy {
            it.second
        }
        var currentEnd = -1
        for (i in sortedSegmentsByRightEnd.indices) {
            // Let us assume that the first (previous, known, saved, checked) segment is A and the segment that we
            // are checking is B. So, val segmentB = sortedSegmentsByRightEnd[i].
            val segment = sortedSegmentsByRightEnd[i]
            // Now, the end point of segment A cannot be greater than the end point of the segment B.
            // Why? How? Because, we have already sorted it in ascending order.
            // So, the end point of segment A cannot be greater than the end of the segment B.
            // However, the end point of segment A can be less than, equal to, or greater than the starting point of the
            // segment B.
            // If the end point of segment A is equal to the starting point of segment B, then we have already covered
            // both the segments.
            // If the end point of segment A is greater than the starting point of segment B, it means segment B covers
            // segment A. So again, in this case also, we cover both the segments.
            // However, if the end point of segment A is less than the starting point of segment B, it means that
            // segment B is out of range than segment A. They do not overlap each other.
            // They have completely different timeline. And in that case, we have to add the end point of segment B
            // to cover the segment B.
            // Check the visuals and tables provided in the description of the function to understand the same.
            if (currentEnd < segment.first ) {
                mutableListOfCommonPoints.add(segment.second)
                currentEnd = segment.second
            }
        }
        return mutableListOfCommonPoints
    }

    // Enter total segments. Read total segments.
    val totalSegments = readln().toInt()
    // A bucket to store starting point and ending point of each segment.
    val listOfSegments = mutableListOf<Pair<Int, Int>>()
    // Read each segment, equal to the total segments entered earlier.
    for (i in 1..totalSegments) {
        // Enter each segment with starting point, space, and ending point.
        // Read each segment. Store the starting point and the ending point in a bucket.
        val segment = readln().split(" ").map {
            it.toInt()
        }
        // Add each segment to the segment bucket.
        listOfSegments.add(Pair(segment[0], segment[1]))
    }
    val minimumPointsToTouchAllTheSegments = minimumPointsToTouchAllTheSegments(listOfSegments)
    println(minimumPointsToTouchAllTheSegments.size)
    minimumPointsToTouchAllTheSegments.forEach {
        print("$it ")
    }
}