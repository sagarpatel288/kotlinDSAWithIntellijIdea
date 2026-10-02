package courses.uc.course03algorithmsOngraph.courses.uc.module05minimumSpanningTrees.part02assignments

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * # Building roads, connecting co-ordinates as MST
 *
 * * [Connect co-ordinates](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7dabd45ccde5caf600062fb2c4a895abce77d5a8/docs/03graph/courses/uc/module05minimumSpanningTrees/020assignment/010buildingRoads.md)
 */
data class Point(val x: Int, val y: Int)

class ConnectObjects(val total: Int) {

    private fun distance(a: Point, b: Point): Double {
        return sqrt(
            (
                    (b.x - a.x).toDouble().pow(2) +
                            (b.y - a.y).toDouble().pow(2)
                    )
        )
    }

    fun findMinCost(points: List<Point>): Double {
        if (total <= 1) return 0.0
        val minDistance = DoubleArray(total) { Double.MAX_VALUE }
        val selected = BooleanArray(total)
        minDistance[0] = 0.0
        var cost = 0.0
        repeat(total) {
            // Find the next vertex (point) to bring in (connect) to the tree
            var next = -1
            for (i in 0 until total) {
                if (!selected[i] && (next == -1 || minDistance[i] < minDistance[next])) {
                    next = i
                }
            }
            selected[next] = true
            cost += minDistance[next]
            for (i in 0 until total) {
                if (!selected[i]) {
                    val distance = distance(points[next], points[i])
                    if (distance < minDistance[i]) {
                        minDistance[i] = distance
                    }
                }
            }
        }
        return cost
    }
}

fun main() {
    val total = readln().toInt()
    val connectObjects = ConnectObjects(total)
    val points = mutableListOf<Point>()
    repeat(total) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        points.add(Point(x, y))
    }
    println("%.9f".format(connectObjects.findMinCost(points)))
}

/*
* Sample input:
*
* 4
0 0
0 1
1 0
1 1
*
* Output:
*
* 3.0000000
*
* Input:
*
* 5
0 0
0 2
1 1
3 0
3 2
*
* Output:
*
* 7.064495102
*
* */