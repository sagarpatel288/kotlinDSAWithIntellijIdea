package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

import courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment.ClosestPairOfPoints.Point
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class ClosestPairOfPoints {

    data class Point(val x: Int, val y: Int)

    private fun euclideanDistance(a: Point, b: Point): Double {
        return sqrt(
            abs(
                (b.x - a.x).toDouble().pow(2)
                +
                        (b.y - a.y).toDouble().pow(2)
            )
        )
    }

    private fun findClosestPairOfPointsBrutely(sortedByX: List<Point>, start: Int, end: Int): Double {
        var min = Double.MAX_VALUE
        for (i in start..end) {
            for (j in i + 1..end) {
                min = minOf(min, euclideanDistance(sortedByX[i], sortedByX[j]))
            }
        }
        return min
    }

    private fun findClosestPairOfPointsInStrip(sortedByY: List<Point>, min: Double, midX: Int): Double {
        // Create a vertical strip
        val strip = sortedByY.filter { abs(it.x - midX) <= min }
        var minInStrip = min
        for (i in strip.indices) {
            for (j in i + 1..<minOf(i + 7, strip.size)) {
                minInStrip = minOf(min, euclideanDistance(strip[i], strip[j]))
            }
        }
        return minInStrip
    }

    private fun findClosestPairOfPoints(sortedByX: List<Point>, sortedByY: List<Point>, start: Int, end: Int): Double {
        if (end - start <= 2) {
            return findClosestPairOfPointsBrutely(sortedByX, start, end)
        }
        val mid = start + (end - start) / 2
        val midX = sortedByX[mid].x
        val leftY = sortedByY.filter { it.x <= midX }
        val rightY = sortedByY.filter { it.x > midX }
        var min = findClosestPairOfPoints(sortedByX, leftY, start, mid)
        min = minOf(min, findClosestPairOfPoints(sortedByX, rightY, mid + 1, end))
        min = minOf(min, findClosestPairOfPointsInStrip(sortedByY, min, midX))
        return min
    }

    fun findClosestPairOfPoints(input: List<Point>): Double {
        if (input.size <= 1) return 0.0
        // Sort by x to define the upcoming vertical strip
        val sortedByX = input.sortedBy { it.x }
        // Sort by y to scan through the upcoming vertical strip
        val sortedByY = input.sortedBy { it.y }
        return findClosestPairOfPoints(sortedByX, sortedByY, 0, sortedByX.lastIndex)
    }
}

fun main() {
    val total = readln().toInt()
    val points = mutableListOf<Point>()
    repeat(total) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        points.add(Point(x, y))
    }
    val solver = ClosestPairOfPoints()
    val answer = solver.findClosestPairOfPoints(points)
    println("%.4f".format(answer))
}