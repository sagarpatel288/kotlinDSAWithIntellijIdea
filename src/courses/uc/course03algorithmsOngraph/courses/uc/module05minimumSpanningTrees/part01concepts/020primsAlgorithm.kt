package courses.uc.course03algorithmsOngraph.courses.uc.module05minimumSpanningTrees.part01concepts

import java.util.PriorityQueue

/**
 *
 * # Prim's Algorithm to find the Minium Spanning Tree (MST)
 *
 * * Reference:
 * * [Prim's Algorithm to find MST](https://github
 * .com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/19e03c47cea695b58ed9ae35b5288e2c685aa059/docs/03graph/courses/uc/module05minimumSpanningTrees/010lectures/030primsAlgorithmToFindMst.md)
 *
 */
class PrimsAlgorithm(val totalVertices: Int) {

    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(totalVertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
        adjList[to].add(Edge(to, from, weight))
    }

    fun findMinSpanTreeCost(start: Int): Long {
        require (start in 0 until totalVertices) {
            "Size is: $totalVertices, but start argument is: $start"
        }
        val selected = BooleanArray(totalVertices)
        var selectedEdges = 0
        var cost = 0L
        val minHeap = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        val edges = adjList[start]
        // Initially, add all the outgoing edges of the starting vertex
        for ((from, to, weight) in edges) {
            // `weight` represents the cost of adding the `to` vertex
            minHeap.add(weight to to)
        }
        while (minHeap.isNotEmpty()) {
            val (weight, vertex) = minHeap.poll()
            if (selected[vertex]) {
                continue
            }
            selected[vertex] = true
            cost += weight
            selectedEdges++
            if (selectedEdges == (totalVertices - 1)) {
                break
            }
            val edges = adjList[vertex]
            for ((from, to, weight) in edges) {
                minHeap.add(weight to to)
            }
        }
        return if (selectedEdges == (totalVertices - 1)) {
            cost
        } else {
            -1
        }
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val prim = PrimsAlgorithm(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        prim.addEdge(from, to, weight)
    }
    println(prim.findMinSpanTreeCost(0))
}

/*
* Sample input
*
* 4 5
0 1 10
0 3 30
0 2 15
1 3 40
2 3 50
*
* Expected output
55
*
* */