package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

import courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02.DijkstrasAlgoToFindShortestPath.Edge
import java.util.PriorityQueue

/**
 * # Reference
 *
 * * [Dijkstra's Algorithm To Find The Shortest Path](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /13073440aee8a7296a0ef3a75728862478395b8d
 * /docs/03graph/courses/uc/module04pathsInGraph02/010lectures/030dijkstrasAlgorithm.md)
 *
 * ToDo
 * * Add short time and space complexity analysis note/conclusion
 */
class DijkstrasAlgoToFindShortestPath(val vertices: Int) {

    data class Edge(val start: Int, val end: Int, val weight: Int)

    fun findShortestPath(source: Int, destination: Int, edges: List<Edge>) {
        if (source !in 0..<vertices || destination !in 0..<vertices) {
            throw IllegalArgumentException("Source: $source, Destination: $destination, Total vertices: $vertices")
        }
        val adjList = List(vertices) { mutableListOf<Edge>() }
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val prev = IntArray(vertices) { -1 }
        dist[source] = 0
        for (edge in edges) {
            adjList[edge.start].add(edge)
        }
        val minHeap = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        // From the source to the source, the distance is `0`
        minHeap.add(Pair(0, source))
        while (minHeap.isNotEmpty()) {
            val (distance, vertex) = minHeap.poll()
            if (dist[vertex] < distance) continue
            val edges = adjList[vertex]
            for ((_, end, weight) in edges) {
                if (dist[end] > distance + weight) {
                    val newDistance = dist[vertex] + weight
                    dist[end] = newDistance
                    prev[end] = vertex
                    minHeap.add(newDistance to end)
                }
            }
        }
        dist.forEach {
            println(it)
        }
        if (dist[destination] == Int.MAX_VALUE) {
            println("No path exists from the source to the destination!")
            return
        }
        val result = mutableListOf<Int>()
        var end = destination
        while (end != source) {
            result.add(end)
            end = prev[end]
        }
        result.add(source)
        println(result.reversed())
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val dijkstraShortestPath = DijkstrasAlgoToFindShortestPath(totalVertices)
    val edges = mutableListOf<Edge>()
    repeat(totalEdges) {
        val (start, end, weight) = readln().split(" ").map { it.toInt() }
        require(weight <= 0) {
            "Dijkstra's Algorithm to find the shortest path requires non-negative weight!"
        }
        edges.add(Edge(start, end, weight))
    }
    val (source, destination) = readln().split(" ").map { it.toInt() }
    dijkstraShortestPath.findShortestPath(source, destination, edges)
}
