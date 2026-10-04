package courses.uc.course03algorithmsOngraph.courses.uc.module05minimumSpanningTrees.part02assignments

import kotlin.math.pow
import kotlin.math.sqrt

class Dsu(val total: Int) {
    private val parents = IntArray(total) { it }
    private val ranks = IntArray(total)

    fun find(a: Int): Int {
        if (a == parents[a]) return a
        parents[a] = find(parents[a])
        return parents[a]
    }

    fun union(a: Int, b: Int): Boolean {
        val aRoot = find(a)
        val bRoot = find(b)
        if (aRoot == bRoot) return false
        val aRank = ranks[aRoot]
        val bRank = ranks[bRoot]
        if (aRank > bRank) {
            parents[bRoot] = aRoot
        } else if (bRank > aRank) {
            parents[aRoot] = bRoot
        } else {
            parents[bRoot] = aRoot
            ranks[aRoot]++
        }
        return true
    }
}

data class Edge(val from: Int, val to: Int, val weight: Double)

class Grouping(val totalVertices: Int) {

    data class Point(val x: Int, val y: Int)

    private fun euclideanDistance(a: Point, b: Point): Double {
        return sqrt(
            (b.x - a.x).toDouble().pow(2)
                    + (b.y - a.y).toDouble().pow(2)
        )
    }

    fun findMinDistance(points: List<Point>, kGroups: Int): Double {
        val edges = mutableListOf<Edge>()
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val dist = euclideanDistance(points[i], points[j])
                edges.add(Edge(i, j, dist))
            }
        }
        val sortedEdges = edges.sortedBy { it.weight }
        var selectedEdges = 0
        val dsu = Dsu(totalVertices)
        for ((from, to, weight) in sortedEdges) {
            val merged = dsu.union(from, to)
            if (!merged) continue
            if (selectedEdges == totalVertices - kGroups) return weight
            selectedEdges++
        }
        throw RuntimeException("Could not group!")
    }
}

fun main() {
    val totalPoints = readln().toInt()
    val points = mutableListOf<Grouping.Point>()
    repeat(totalPoints) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        points.add(Grouping.Point(x, y))
    }
    val kGroups = readln().toInt()
    val grouping = Grouping(totalPoints)
    println("%.9f".format(grouping.findMinDistance(points, kGroups)))
}