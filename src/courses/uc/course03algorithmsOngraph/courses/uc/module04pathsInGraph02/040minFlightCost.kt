package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

/**
 *
 * # Minimum cost for the flight between the source and the destination
 *
 * * [Minimum cost from the source to destination](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f16b5170abd6069b88ac5065df9ab3a43a6c53c9/docs/03graph/courses/uc/module04pathsInGraph02/020assignment/010computingMinFlightCost.md)
 *
 */
class MinFlightCost(val vertices: Int) {

    // Edges are given (input format is) as: from, to, weight
    data class Edge(val from: Int, val to: Int, val weight: Int)

    // Each index = Each vertex; Each vertex has a list of edges
    private val adjList = List(vertices) { mutableListOf<Edge>() }

    /**
     * * Creates a graph
     * * Add a list of edges for the vertex "from"
     * * The edge is outgoing for `from` and is incoming for `to`
     * * The edge goes outside of `from` and goes inside `to`
     * * The edge leaves `from` and enters into `to`
     * * So, the edge is going as `from → to`
     *
     *
     * @param from: Outgoing edges of the vertex "from"
     * @param to: Target vertex from (The vertex is "from") → to (The vertex is "to")
     * @param weight: The weight of the edge that goes as: from → to
     */
    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    /**
     * Using the Bellman-Ford Algorithm
     */
    fun findMinCost(source: Int, destination: Int): Long {
        val dist = LongArray(vertices) { Long.MAX_VALUE }
        dist[source] = 0L
        repeat(vertices - 1) {
            for ((_, edges) in adjList.withIndex()) {
                for ((from, to, weight) in edges) {
                    // Caution! Possible point of mistake!
                    // We don't know yet whether dist[from] is reachable or not.
                    // In the Dijkstra's Algorithm, we go through the connected vertices only.
                    // Here, we pick up a vertex from the adjacency list.
                    // So, we don't know whether it is reachable from the source or not.
                    // If we don't check this, then we end-up with a wrong result.
                    // Because in that case, we would store the distance for a non-reachable vertex.
                    // So, this check is critical.
                    // For example, if this is B → C, but B is not reachable from the source A,
                    // then storing the distance of C would mean that:
                    // dist[c] = The infinite distance of B + weight(B, C)
                    // And it does not give us the correct result.
                    // Because if B is not reachable from A,
                    // then how can we say that there is a path from A to C via B?
                    // So, the `dist` array also indicates whether a vertex is reachable or not from the source.
                    // Instead of storing the path that goes from the infinite distance of B,
                    // we should explore and try another shorter, finite path that might connect A and C.
                    if (dist[from] >= Long.MAX_VALUE) continue
                    if (dist[to] > dist[from] + weight) {
                        dist[to] = dist[from] + weight
                    }
                }
            }
        }
        return if (dist[destination] >= Long.MAX_VALUE) -1 else dist[destination]
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val minCost = MinFlightCost(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        // 1-based index
        minCost.addEdge(from - 1, to - 1, weight)
    }
    val (source, destination) = readln().split(" ").map { it.toInt() }
    println(minCost.findMinCost(source - 1, destination - 1))
}



