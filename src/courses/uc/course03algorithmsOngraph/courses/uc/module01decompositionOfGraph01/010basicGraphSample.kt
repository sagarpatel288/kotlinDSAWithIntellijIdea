package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

/**
 * * It assumes that (or this is when) we have a fixed number of vertices.
 * * This is just a warmup to get basic idea about the graph.
 */
class Graph(val size: Int) {

    // It is a list of list.
    // Each element has a list of neighbors.
    val adjacencyList = List(size) { mutableListOf<Int>() }

    /**
     * * We use direct addressing.
     * * We treat the incoming arguments [a] and [b] as indices of the [adjacencyList].
     * * It implies that we are adding (populating) the neighbor list of [a] and [b] in the [adjacencyList].
     * * This approach is the reason we take the fixed size of [adjacencyList], which is equal to the [size].
     * * The [size] property indicates the total number of vertices.
     * * The vertex value becomes the vertex index.
     * * To populate the neighbor list of a particular vertex, we need to find the vertex location.
     * * To find the vertex location, we treat the vertex as an index.
     * * That index is the vertex location where we can populate its neighbor list.
     * * Another thing to notice is that we consider both the vertices neighbor of each other.
     * * In other words, we add each vertex to the neighbor list of the other incoming vertex.
     * * And the reason is that we are modeling an undirected graph, which is also known as the bidirectional graph.
     * * If it was a unidirectional (directed) graph, we would only add [b] as the neighbor of [a], not the other way around.
     * * So, for a unidirectional graph, we would only populate the neighbor list of [a], not for the [b].
     */
    fun addEdges(a: Int, b: Int) {
        if (a !in adjacencyList.indices || b !in adjacencyList.indices) return
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        // An adjacency list is a list of list.
        // Each element has a neighbor list.
        for ((index, neighbors) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex is $index: Neighbors are: ")
            neighbors.forEach {
                stringBuilder.append(" $it, ")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }
}

fun main() {
    val graph = Graph(5)
    graph.addEdges(0, 1)
    graph.addEdges(1, 2)
    graph.addEdges(1, 3)
    graph.addEdges(2, 3)
    graph.addEdges(2, 4)
    graph.printAdjacencyList()
}