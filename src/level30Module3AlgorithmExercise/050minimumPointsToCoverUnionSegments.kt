package level30Module3AlgorithmExercise

fun main() {

    /**
     * Covering Segments by Points Problem
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
     * The key observation here is that by always choosing the rightmost endpoint of the segments that are still uncovered, we can ensure that we minimise the number of points used.
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
     * Here, the first segment is [1, 3]. If we take point 3, it covers (touches, overlaps) the second segment [2, 5] and the third segment [3, 6]. Hence, we only need one point at coordinate 3 to touch all three segments.
     *
     * If we notice one thing here, the left side (starting point) and the right side (ending point) of each segment are sorted in ascending order (lower to higher).
     *
     * However, it might be impossible to sort both the left side (starting point) and the right side (ending point) of each segment in all the cases. For example,
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
     * Similarly, if we take coordinate 5 or 6, it covers the third segment [4, 7] and the fourth segment [5, 6]. Hence, we need at least 2 points, at coordinates 3 and 5 or 3 and 6, to cover all the segments.
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
     * Again, we can see that if we take coordinate 3, it covers the first segment [1, 3] and the second segment [2, 5]. Similarly, if we take coordinate 5 or 6, it covers the third segment [5, 6] and the fourth segment [4, 7]. Hence, we need at least 2 points, at coordinates 3 and 5 or 3 and 6, to cover all the segments.
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
        val mutableListOfCommonPoints = mutableListOf<Int>()
        val sortedSegmentsByRightEnd = listOfSegments.sortedBy {
            it.second
        }
        var currentEnd = -1
        for (i in sortedSegmentsByRightEnd.indices) {
            val segment = sortedSegmentsByRightEnd[i]
            if (currentEnd < segment.first ) {
                mutableListOfCommonPoints.add(segment.second)
                currentEnd = segment.second
            }
        }
        return mutableListOfCommonPoints
    }

    val totalSegments = readln().toInt()
    val listOfSegments = mutableListOf<Pair<Int, Int>>()
    for (i in 1..totalSegments) {
        val segment = readln().split(" ").map {
            it.toInt()
        }
        listOfSegments.add(Pair(segment[0], segment[1]))
    }
    val minimumPointsToTouchAllTheSegments = minimumPointsToTouchAllTheSegments(listOfSegments)
    println(minimumPointsToTouchAllTheSegments.size)
    minimumPointsToTouchAllTheSegments.forEach {
        print("$it ")
    }
}