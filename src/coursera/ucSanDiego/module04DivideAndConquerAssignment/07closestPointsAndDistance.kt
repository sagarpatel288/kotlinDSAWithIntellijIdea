package coursera.ucSanDiego.module04DivideAndConquerAssignment

import kotlin.math.min
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.abs

/**
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
 * res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07closestPointsDistance.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/b313879762c83e25cdbdbbf0543ba89f7280d42a/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/07closestPointsDistance.png)
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

    fun euclideanDistanceOfPoints(pointOne: Point, pointTwo: Point): Double {
        return sqrt(
            (
                    (pointOne.xAxis - pointTwo.xAxis).toDouble().pow(2)
                            +
                            (pointOne.yAxis - pointTwo.yAxis).toDouble().pow(2)
                    )
        )
    }

    fun closestPointsByBruteForce(sortedPoints: List<Point>, start: Int, end: Int): Double {
        var minDistance = Double.MAX_VALUE
        for (i in start..end) {
            // If we use `..<` instead of `until`, we may get the below error:
            // error: this declaration needs opt-in. Its usage must be marked with '@kotlin.ExperimentalStdlibApi' or '@OptIn(kotlin.ExperimentalStdlibApi::class)'
            // So, either update the language version or use until.
            for (j in i + 1 .. end) {
                minDistance = min(minDistance, euclideanDistanceOfPoints(sortedPoints[i], sortedPoints[j]))
            }
        }
        return minDistance
    }

    fun closestPointsInStrip(sortedPoints: List<Point>, minDistance: Double): Double {
        var minDistanceStrip = minDistance
        val sortedByYAxis = sortedPoints.sortedBy { it.yAxis }
        for (i in sortedByYAxis.indices) {
            // If we use `..<` instead of `until`, we may get the below error:
            // error: this declaration needs opt-in. Its usage must be marked with '@kotlin.ExperimentalStdlibApi' or '@OptIn(kotlin.ExperimentalStdlibApi::class)'
            // So, either update the language version or use until.
            for (j in i + 1 until sortedByYAxis.size) {
                if ((sortedByYAxis[j].yAxis - sortedByYAxis[i].yAxis) >= minDistanceStrip) break
                minDistanceStrip = min(minDistanceStrip, euclideanDistanceOfPoints(sortedByYAxis[i], sortedByYAxis[j]))
            }
        }
        return minDistanceStrip
    }

    fun closestPointsRecursively(sortedPoints: List<Point>, start: Int, end: Int): Double {
        if (end - start <= 2) {
            return closestPointsByBruteForce(sortedPoints, start, end)
        }

        val mid = start + (end - start) / 2
        val midX = sortedPoints[mid].xAxis

        val leftMinDistance = closestPointsRecursively(sortedPoints, start, mid)
        val rightMinDistance = closestPointsRecursively(sortedPoints, mid + 1, end)

        var minDistance = min(leftMinDistance, rightMinDistance)

        val strip = sortedPoints
            .filter { abs(it.xAxis - midX) < minDistance }
            .sortedBy { it.yAxis }

        minDistance = min(minDistance, closestPointsInStrip(strip, minDistance))

        return minDistance
    }

    fun closestPoints(points: List<Point>): Double {
        val sortedPoints = points.sortedBy { it.xAxis }
        return closestPointsRecursively(sortedPoints, 0, sortedPoints.lastIndex)
    }

    val totalPairs = readln().toInt()
    val points = mutableListOf<Point>()
    for (i in 1..totalPairs) {
        val point = readln().split(" ").map { it.toInt() }
        points.add(Point(point[0], point[1]))
    }

    val result = closestPoints(points)
    println("%.4f".format(result))

}