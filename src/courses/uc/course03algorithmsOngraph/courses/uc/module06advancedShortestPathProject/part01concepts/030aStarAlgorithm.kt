package courses.uc.course03algorithmsOngraph.courses.uc.module06advancedShortestPathProject.part01concepts

import java.util.PriorityQueue

/**
 * # A* Algorithm to find the shortest path from the source to the target
 *
 * * Reference:
 * [A-Star Algorithm](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /8baa281c0713deb392a27d1b50959d804ec8540d/docs/03graph/courses/uc/module06advancedShortestPathProject/010lectures/020aStarAlgorithm.md)
 */
class ShortestPathUsingAStar(private val totalVertices: Int) {

    init {
        require(totalVertices > 0) {
            "Total vertices must be greater than 0"
        }
    }

    private data class Edge(val to: Int, val weight: Long)

    private val adjList = List(totalVertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Long) {
        require(from in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but from is: $from"
        }
        require(to in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but to is: $to"
        }
        require(weight >= 0) {
            "Weight must be non-negative for the A-Star Algorithm."
        }
        adjList[from].add(Edge(to, weight))
        adjList[to].add(Edge(from, weight))
    }

    data class Result(val distance: Long, val path: List<Int>)

    private data class QueueItem(val fScore: Long, val gScore: Long, val vertex: Int)

    fun findShortestPath(source: Int, target: Int, heuristics: List<Long>): Result {
        require(source in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but source is: $source"
        }
        require(target in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but target is: $target"
        }
        require(heuristics.size == totalVertices) {
            "Total vertices are: $totalVertices, but total heuristics are: ${heuristics.size}"
        }
        require(heuristics.all { it >= 0 }) {
            "Heuristic must be non-negative"
        }
        require(heuristics[target] == 0L) {
            "Target estimate must be 0"
        }
        if (source == target) {
            return Result(0L, listOf(source))
        }

        val inf = Long.MAX_VALUE
        val gScore = LongArray(totalVertices) { inf }
        gScore[source] = 0L

        val priorityQueue = PriorityQueue(
            compareBy<QueueItem> { it.fScore }.thenBy { it.gScore }.thenBy { it.vertex }
        )

        val parents = IntArray(totalVertices) { -1 }
        parents[source] = source

        val fScore = gScore[source] + heuristics[source]
        priorityQueue.add(QueueItem(fScore, gScore[source], source))

        while (priorityQueue.isNotEmpty()) {
            val polled = priorityQueue.poll()
            if (polled.gScore > gScore[polled.vertex]) {
                continue
            }
            if (polled.vertex == target) {
                return Result(polled.gScore, reconstructPath(source, target, parents))
            }
            val edges = adjList[polled.vertex]
            for ((to, weight) in edges) {
                val candidateDistance = gScore[polled.vertex] + weight
                if (gScore[to] > candidateDistance) {
                    gScore[to] = candidateDistance
                    parents[to] = polled.vertex
                    val fScore = candidateDistance + heuristics[to]
                    priorityQueue.add(QueueItem(fScore, candidateDistance, to))
                }
            }
        }

        return Result(-1L, emptyList())
    }

    private fun reconstructPath(source: Int, target: Int, parents: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var cur = target
        while (cur != source) {
            result.add(cur)
            cur = parents[cur]
        }
        result.add(source)
        return result.reversed()
    }
}

/**
 * It is guaranteed that all the values will be within `Int` limit.
 * This is for a bidirectional (undirected) graph.
 * This is to demonstrate a simple A* algorithm, and not a bidirectional-a* algorithm.
 */
fun main() {
    val (totalVertices, totalEdges) = readln().split(Regex("\\s+")).map { it.toInt() }
    val aStar = ShortestPathUsingAStar(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(Regex("\\s+")).map { it.toInt() }
        aStar.addEdge(from - 1, to - 1, weight.toLong())
    }
    val heuristics = mutableListOf<Long>()
    repeat(totalVertices) {
        val estimate = readln().toLong()
        heuristics.add(estimate)
    }
    val (source, target) = readln().split(Regex("\\s+")).map { it.toInt() }
    val result = aStar.findShortestPath(source - 1, target - 1, heuristics)
    println(result.distance)
    println(result.path.map { it + 1 }.joinToString(" "))
}

/**
 * Sample input:
 *
```
4 4
1 2 1
4 1 2
2 3 2
1 3 5
0
0
0
0
1 3
 ```
 *
 *
 * Expected output
 *
 * 3
 * 1 2 3
 *
 */

