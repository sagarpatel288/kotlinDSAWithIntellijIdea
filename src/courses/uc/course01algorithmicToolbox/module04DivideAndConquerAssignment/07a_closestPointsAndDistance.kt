package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

import kotlin.math.min
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.abs

/**
 *
 * Reference:
 *
 * [Closest Pair Of Points](https://youtu.be/6u_hWxbOc7E?si=lDCLKzRNdHHZ1qf9)
 * [Playlist](https://youtube.com/playlist?list=PLL-JBmgAwwRX6cZ092RqOuKNY2fTWxYyy&si=y1MMzfrfvknYMmr6)
 *
 * ----------------------- Problem Statement -----------------------
 *
 * Closest Points Problem:
 *
 * Find the closest pair of points in a set of points on a plane.
 *
 * Input:
 *
 * A list of n points on a plane.
 *
 * Output:
 *
 * The minimum distance between a pair of these points.
 *
 * Input format:
 *
 * The first line contains the number of points n. Each of the following n lines defines a point (xi,yi).
 *
 * Output format:
 *
 * The minimum distance. Recall that the distance between points (x1,y1) and (x2,y2) is equal to
 * square root of (x1−x2)^2 + (y1−y2)^2.
 *
 * Thus, while the Input contains only integers, the Output is not necessarily integer, and you have to pay attention
 * to precision when you report it. The absolute value of the difference between the answer of your
 * program and the optimal value should be at most 10^−3.
 *
 * To ensure this, output your answer with at least four digits after the decimal point
 * (otherwise even correctly computed answer may fail to pass our grader because of the rounding errors).
 *
 * Constraints:
 *
 * 2 ≤ n ≤ 10^5;
 * −10^9 ≤ xi,yi ≤ 10^9 are integers.
 *
 * Sample 1:
 *
 * Input:
 *
 * 2
 *
 * 0 0
 *
 * 3 4
 *
 * Output:
 *
 * 5.0
 *
 * There are only two points at distance 5.
 *
 * Sample 2:
 *
 * Input:
 *
 * 11
 *
 * 4 4
 *
 * -2 -2
 *
 * -3 -4
 *
 * -1 3
 *
 * 2 3
 *
 * -4 0
 *
 * 1 1
 *
 * -1 -1
 *
 * 3 -1
 *
 * -4 2
 *
 * -2 4
 *
 * Output:
 *
 * 1.414213
 *
 * ----------------------- References -----------------------
 *
 * res/courses/uc/module04DivideAndConquerAssignment/07a_closestPointsDistance.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/b313879762c83e25cdbdbbf0543ba89f7280d42a/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07a_closestPointsDistance.png)
 *
 * ----------------------- Story Time -----------------------
 *
 * Imagine you're an air traffic controller at a busy airport.
 * Your job is to ensure that planes don't get too close to each other to avoid collisions.
 * You have a radar screen with dots representing planes, and you need to find the two closest planes quickly
 * as new planes constantly appear.
 * This is similar to the "Closest Pair of Points" problem where we need to efficiently find the pair of points (planes)
 * that are closest to each other on a 2D plane.
 *
 * ----------------------- Prerequisites -----------------------
 *
 * 1. Merge-sort (Reduce the problem-size by half. Solve for a smaller-problem. Merge (conquer) for the bigger problem.).
 * 2. Euclidean Distance (Distance between two points).
 * 3. Geometric Insight (Strip).
 * 4. Geometric packing arguments (i + 7).
 *
 *
 */
fun main() {

    data class Point(val xAxis: Int, val yAxis: Int)

    /**
     * What do we do here?
     *
     * We calculate the distance between the two points on a 2D (x-axis, y-axis) plane using the
     * Euclidean distance formula. Each point has an x-axis and a y-axis value.
     */
    fun euclideanDistanceOfPoints(pointOne: Point, pointTwo: Point): Double {
        // Calculate the Euclidean distance between two points using the standard formula.
        return sqrt(
            (
                    (pointOne.xAxis - pointTwo.xAxis).toDouble().pow(2)
                            +
                            (pointOne.yAxis - pointTwo.yAxis).toDouble().pow(2)
                    )
        )
    }

    /**
     * What do we do here?
     *
     * In a brute force function, we compare every single point with every other point.
     * This requires an outer for loop (i) and an inner for loop `(i + 1)..end`.
     *
     * We will use this brute force in two scenarios:
     * 1. The input itself has less than or equals to 2 or 3 total points.
     * 2. During the vertical strip: [closestPointsInStrip]
     * Because in the strip, we compare each point with the next 6 to 7 points.
     */
    fun closestPointsByBruteForce(sortedPoints: List<Point>, start: Int, end: Int): Double {
        // Compare every pair of points in the given range to find the minimum distance.
        var minDistance = Double.MAX_VALUE
        for (i in start..end) {
            // If we use `..<` instead of `until`, we may get the below error:
            // error: this declaration needs opt-in. Its usage must be marked with '@kotlin.ExperimentalStdlibApi' or '@OptIn(kotlin.ExperimentalStdlibApi::class)'
            // So, either update the language version or use until.
            // Caution! Possible point of mistake!
            // It is `i + 1` and not the `start + 1`!
            // If we use `start + 1`, `i` and `j` will be equal at some point.
            // And the distance between the same (single) point (itself) is always 0!
            // So, again: The inner loop starts from `i + 1`.
            // It is like the outer loop picks up one point at `i`,
            // and then the inner loop picks up all the other, surrounding points, starting from `i + 1`.
            for (j in i + 1..end) {
                minDistance = min(minDistance, euclideanDistanceOfPoints(sortedPoints[i], sortedPoints[j]))
            }
        }
        return minDistance
    }

    /**
     * Reference images:
     *
     * res/courses/uc/module04DivideAndConquerAssignment/07b_closestPointsStripConcept01.png
     * [image01](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/6abcdbafac0bf968f456f7cd41673266c065e303/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07b_closestPointsStripConcept01.png)
     *
     * res/courses/uc/module04DivideAndConquerAssignment/07b_closestPointsStripConcept02.png
     * [image02](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/6abcdbafac0bf968f456f7cd41673266c065e303/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07b_closestPointsStripConcept02.png)
     *
     * res/courses/uc/module04DivideAndConquerAssignment/07b_closestPointsStripConcept03.png
     * [image03](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/6abcdbafac0bf968f456f7cd41673266c065e303/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07b_closestPointsStripConcept03.png)
     *
     *
     * The closest pair problem uses the divide and conquer approach.
     *
     * Divide: Split points into two halves based on x-coordinates.
     *
     * Conquer: Solve the problem for each half recursively.
     *
     * Combine: Find the closest pair across the dividing line.
     *
     * The strip plays a crucial role in the combine step, where we handle the pairs that might span across
     * the two halves.
     *
     * If two points are in different halves, their x-coordinates are close to the dividing line.
     * The distance between them can only be relevant if it is smaller than the minimum distance d found in
     * the left and right halves.
     *
     * We only need to compare points within this vertical strip around the dividing line.
     *
     * The strip is a subset of points filtered from the original set.
     * It includes only those points whose x-coordinates are within d distance of the dividing line.
     *
     * Limit Comparisons to 7 Points:
     *
     * For a point p, we only need to compare it with the next 7 points in the sorted strip.
     * Why? Because in a `d * d` square (centered on p ), a maximum of 7 points can exist due to
     * geometric packing arguments (explained below).
     *
     * Calculate Distances:
     *
     * For each point p, compare its distance with the next 7 points and update the minimum distance
     * if a smaller one is found.
     *
     * Why Only i + 7 Points?
     *
     * Geometric Packing Argument:
     *
     * The bound of 7 comparisons comes from a mathematical observation about point placement in a  d * d  square:
     *
     * 	1.	The minimum distance between two points is  d .
     * 	2.	Within a strip, consider a square of side  d  around a point  p .
     * 	3.	At most 7 points can fit in this square without violating the  d  minimum distance constraint.
     *
     * The main geometric insight here is that, for any given point in the strip, it is sufficient to compare it
     * with only the next min(7, strip.size) points in the sorted order.
     *
     * Why? Because points that are farther apart vertically than `minDistance` cannot possibly form a closer pair
     * due to the constraints of Euclidean distance.
     *
     * Iteration:
     *
     * 	For each point i in the strip:
     *
     * 	1. Compare it with the next j points (up to 7 or the end of the list).
     * 	2. Break early if the difference in the y-coordinates exceeds the current minDistance.
     * 	3. Keep track of the minimum distance found during these comparisons.
     *
     * 	We scan all the points of the strip, similar to brute force, but we do not compare each point with every single
     * 	other point. Instead, we compare each point with the next (noting that it is sorted) `minOf(i + 7, strip.size)`
     * 	points only because we leverage geometric packing arguments.
     *
     * 	To understand this, imagine that we draw a circle with a radius of `minDistance` from a specific point in the
     * 	strip area. Note that the strip area is sorted by the y-axis. Therefore, we search for another point solely in
     * 	one direction: downward. According to the geometric packing argument, there can be at most 7 points that can fit
     * 	within this area, allowing the point under inspection to pair with them and achieve a smaller distance than the
     * 	last known `minDistance`.
     *
     * If we attempt to fit more than 7 points, one of the pairs would result in a smaller distance than the last known
     * `minDistance` without crossing the divider (vertical line) where both points would lie on the same side.
     * This situation would have already been identified while searching for minDistance on the left and right sides
     * separately.
     *
     * 	Outer loop: Iterate over each point in the strip.
     *
     * 	Inner loop: Compare only the next j points `minOf(i + 7, strip.size)` until the y-axis difference exceeds
     * 	`minDistance`.
     *
     */
    fun closestPointsInStrip(sortedByY: List<Point>, midX: Int, minDistance: Double): Double {
        // Create the vertical strip.
        // Filter points that are within minDistance of the vertical dividing line.
        val strip = sortedByY.filter { abs(it.xAxis - midX) < minDistance }
        var minDistanceStrip = minDistance
        // Iterate over each point in the strip.
        for (i in strip.indices) {
            // If we use `..<` instead of `until`, we may get the below error:
            // error: this declaration needs opt-in. Its usage must be marked with '@kotlin.ExperimentalStdlibApi' or '@OptIn(kotlin.ExperimentalStdlibApi::class)'
            // So, either update the language version or use until.
            // Compare each point with the next at most 7 points.
            // A point in the strip can only have at most 7 neighbors closer than minDistance.
            // (Based on the geometric packing arguments).
            // So, compare only the next j points, minOf(i + 7, strip.size)
            // until the y-axis difference exceeds minDistance.
            for (j in i + 1 until minOf(i + 7, strip.size)) {
                // Caution! Possible point of mistake!
                // Do not miss this optimization!
                // This is already `sorted by Y` collection.
                // So, if we ever find that the distance between the subject point and neighbor point is greater than
                // the `minDistanceStrip`, then the distance between the subject point and remaining
                // neighbor points will also be more than the `minDistanceStrip`.
                // So, we don't need to check those remaining neighbors.
                // If the y-distance exceeds minDistance, break early.
                if ((strip[j].yAxis - strip[i].yAxis) >= minDistanceStrip) break
                // Otherwise, calculate and update the minimum distance.
                minDistanceStrip = min(minDistanceStrip, euclideanDistanceOfPoints(strip[i], strip[j]))
            }
        }
        return minDistanceStrip
    }

    /**
     * What do we do here and why?
     *
     * We recursively divide the original larger problem into two smaller problems: left and right.
     * We continue this process until the size of each problem is 3 or less.
     * This makes it easier to find the distance between the points.
     *
     * We compare the minimum distance found in the left and right sections
     * and take the smaller of the two.
     *
     * To account for the possible closest pair of points where one point is on the left side
     * and another point is on the right side, we create a strip and find the minimum distance between the points
     * within the strip.
     *
     * In the end, we compare the previously determined minimum distance (obtained from comparing the minimum distances
     * of the left and right sections) with the minimum distance found within the strip.
     *
     * We take the minimum of the two.
     */
    fun closestPointsRecursively(sortedByX: List<Point>, sortedByY: List<Point>, start: Int, end: Int): Double {
        // Base case: Use brute force for small subsets.
        if (end - start <= 2) {
            return closestPointsByBruteForce(sortedByX, start, end)
        }

        // Divide into two halves.
        // Notice that we divide based on the total points, not based on their x-distance.
        // Because, the initial ultimate goal is to get the smallest possible subset of two points out of many points.
        // So, we are dividing based on the number of points.
        val mid = start + (end - start) / 2
        val midX = sortedByX[mid].xAxis

        // Filter left and right subsets of points based on x-axis midpoint.
        // These filters on the `sortedByY` help the `closestPointsInStrip` function.
        // Due to these filters, the `closestPointsInStrip` gets the required `sortedByY` strip.
        // The benefit is, `sortedByY` which is required in the `strip` is already sorted.
        // We are not sorting it inside this recursion.
        // If we sort it inside this recursion or inside the `strip` function, that we call from this recursion,
        // each `sortByY` call will take another `O(n log n)` time.
        // It means that for each `merge sort`, we will spend extra `O(n log n)` time on `sortByY` for the strip.
        // That will make the total time `O(n log^2 n)`.
        // So, we better `sortByY` once, and pass it along the way.
        // We use `sortedByX` to find the `mid`, and then we apply that `mid` on the `sortedByY`.
        // The partition spans the area from top to bottom. We began at the bottom and will now cover the top area.
        // Imagine that we have multiple row houses.
        // If we want to divide the row houses into two parts, we don’t simply divide the floors (x-axis).
        // We calculate and mark the midpoint on the floor, then draw a vertical line extending from the floor (bottom)
        // to the ceiling (y-axis) as well.
        // Imagine you’re an urban planner tasked with dividing a neighborhood of row houses into two regions,
        // left and right, for better management. To do this:
        //	1.	Start with the Floor Division: First, you calculate the midpoint along the row of houses,
        //	dividing the area into a left side and a right side based on the floor (x-axis).
        //	2.	Extend the Line Vertically: But wait, a simple horizontal floor split isn’t enough.
        //	Houses might have balconies, rooftops, or even taller extensions.
        //	To ensure no house is left out based on its vertical presence, you extend the division line upwards
        //	along the ceiling (y-axis), effectively dividing the region into two fully enclosed sections.
        //	3.	Assign Houses: Now, every house on the left of the line (both by its position on the floor and vertically)
        //	goes into the left region. Similarly, all the houses on the right of the line belong to the right region.
        val leftY = sortedByY.filter { it.xAxis <= midX }
        val rightY = sortedByY.filter { it.xAxis > midX }

        // Recursive calls for the left and the right halves.
        val leftMinDistance = closestPointsRecursively(sortedByX, leftY, start, mid)
        val rightMinDistance = closestPointsRecursively(sortedByX, rightY, mid + 1, end)

        // Current minimum distance so far.
        var minDistance = min(leftMinDistance, rightMinDistance)

        // Check for closer points in the strip. Handle the strip case.
        minDistance = min(minDistance, closestPointsInStrip(sortedByY, midX, minDistance))

        return minDistance
    }

    /**
     * What do we do here and why?
     *
     * Sort points by x-coordinate and y-coordinate initially.
     * Imagine that we have a box. The box contains many pairs.
     * Each pair has two points, and each point represents a coordinate.
     * Therefore, each pair represents x and y coordinates.
     * Now, we open the box, pick up each pair one by one, and place it in the space according to their coordinates.
     * In the end, we have all the pairs in the space sorted by their coordinates.
     * This will help us calculate the proper distance between each pair.
     */
    fun closestPoints(points: List<Point>): Double {
        // Prepare sorted lists of points by X and Y coordinates to optimize the recursive search.
        // Sorting by x-axis creates geometric partition.
        // It helps (powers) the creation, existence, definition of the upcoming strip concept.
        // Without sorting by x-axis, there can be no strip.
        // Sorting by x-axis is like arranging all the pairs in rows based on their x-coordinates.
        // Imagine marking positions on a horizontal line and placing each pair where its x-coordinate matches.
        // Now the pairs are aligned horizontally, making it easier to compute distances between nearby pairs along the x-axis.
        val sortedByX = points.sortedBy { it.xAxis }
        // Sorting by y-axis is akin to arranging the same pairs vertically, focusing on their y-coordinates.
        // Sorting by y-axis helps scan the strip.
        // It also helps follow the "6 to 7 neighbors at most" concept within the strip.
        // This setup helps efficiently calculate distances for subsets of points constrained by y-coordinates,
        // such as the “strip” in the closest pair problem.
        val sortedByY = points.sortedBy { it.yAxis }
        // Start the recursive divide-and-conquer process.
        // Notice that we divide based on the total points, not based on their x-distance.
        // The range, `start = 0` to `end = sortedByX.lastIndex` represents number of points within this range.
        // It does not represent `x = 0` to `x = maximumXDistance`.
        // However, the `sortedByX` is already `sorted by x`.
        // It means that it is natural that the last element will have the maximum x-distance.
        // But dividing based on the total number Vs. based on the x-distance will give a different time complexity.
        // If we divide based on the x-distance, there can be a situation of unbalanced partition.
        // But if we divide based on the total number of points, there will be always the balanced partition.
        // Both the parts will get almost equal points.
        // Because, the initial ultimate goal is to get the smallest possible subset of two points out of many points.
        // So, we are dividing based on the number of points.
        return closestPointsRecursively(sortedByX, sortedByY, 0, sortedByX.lastIndex)
    }

    // Read the number of points and their coordinates from standard input.
    val totalPairs = readln().toInt()
    val points = mutableListOf<Point>()
    for (i in 1..totalPairs) {
        val point = readln().split(" ").map { it.toInt() }
        points.add(Point(point[0], point[1]))
    }

    // Calculate the result and print it formatted to four decimal places.
    val result = closestPoints(points)
    // Ensure 4 decimal places.
    println("%.4f".format(result))

}