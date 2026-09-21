package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

import java.util.PriorityQueue

class DijkstraShortestPath(val vertices: Int) {

    data class Edge(val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        require(from in 0..<vertices && to in 0..<vertices) {
            "from: $from, to: $to, but the total vertices are: $vertices"
        }
        require(weight > 0) {
            "Dijkstra's Algorithm to find the shortest path requires non-negative weight!"
        }
        adjList[from].add(Edge(to, weight))
    }

    fun shortestPath(source: Int, destination: Int) {
        val dist = IntArray(vertices) { Int.MAX_VALUE }
        val prev = IntArray(vertices) { -1 }
        dist[source] = 0
        val minHeap = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        // The shortest known distance is from the source node to the source node, which is 0
        // Notice what we add and in which sequence we add it to the min-heap: (distance, vertex)
        minHeap.add(0 to source)
        while (minHeap.isNotEmpty()) {
            val (distance, vertex) = minHeap.poll()
            if (dist[vertex] < distance) continue
            val edges = adjList[vertex]
            edges.forEach {
                val (to, weight) = it
                if (dist[to] > dist[vertex] + weight) {
                    val newDistance = dist[vertex] + weight
                    dist[to] = newDistance
                    minHeap.add(newDistance to to)
                    prev[to] = vertex
                }
            }
        }
        println(dist.joinToString(" "))
        if (dist[destination] == Int.MAX_VALUE) return
        val shortestPath = mutableListOf<Int>()
        var cur = destination
        while (cur != source) {
            shortestPath.add(cur)
            cur = prev[cur]
        }
        shortestPath.add(source)
        println(shortestPath.reversed().joinToString(" "))
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val dijkstraShortestPath = DijkstraShortestPath(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        dijkstraShortestPath.addEdge(from, to, weight)
    }
    val (source, destination) = readln().split(" ").map { it.toInt() }
    dijkstraShortestPath.shortestPath(source, destination)
}