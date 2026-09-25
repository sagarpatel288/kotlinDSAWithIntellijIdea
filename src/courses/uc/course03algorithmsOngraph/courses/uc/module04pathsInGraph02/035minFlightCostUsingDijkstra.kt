package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

import java.util.PriorityQueue

/**
 * # Find the minimum cost of a flight from the source to the destination
 *
 * * [Minimum cost of a flight](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f16b5170abd6069b88ac5065df9ab3a43a6c53c9/docs/03graph/courses/uc/module04pathsInGraph02/020assignment/010computingMinFlightCost.md)
 *
 *
 */
class MinCost(val vertices: Int) {
    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    /**
     * We can use Dijkstra's Algorithm because it is given that there is no negative weight.
     */
    fun minCost(source: Int, destination: Int): Int {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val minHeap = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        dist[source] = 0
        minHeap.add(0 to source)
        while (minHeap.isNotEmpty()) {
            val (distance, vertex) = minHeap.poll()
            val edges = adjList[vertex]
            edges.forEach {
                val (from, to, weight) = it
                if (dist[to] > dist[from] + weight) {
                    val newDistance = dist[from] + weight
                    dist[to] = newDistance
                    minHeap.add(newDistance to to)
                }
            }
        }
        return if (dist[destination] >= Int.MAX_VALUE) -1 else dist[destination]
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val minCost = MinCost(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        minCost.addEdge(from - 1, to - 1, weight)
    }
    val (source, destination) = readln().split(" ").map { it.toInt() }
    println(minCost.minCost(source - 1, destination - 1))
}