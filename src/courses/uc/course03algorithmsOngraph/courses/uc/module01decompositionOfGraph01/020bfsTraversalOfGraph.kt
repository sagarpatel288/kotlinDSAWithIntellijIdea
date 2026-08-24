package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class BfsTraversalInGraph(val size: Int) {
    val adjacencyList = List(size) { mutableListOf<Int>() }

    fun addEdges(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        for ((index, neighborList) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex: $index : Neighbors: ")
            for (neighbor in neighborList) {
                stringBuilder.append(" $neighbor")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }

    /**
     * * In a BST, we always start the BFS traversal from the root.
     * * But in a graph, we can start the BFS Traversal from any vertex.
     * * That's the reason, we have [start] as an argument/parameter.
     * * The thumb rule is that we first cover the immediate neighbor, no vertex should be repeated, and that no vertex should be left unattended.
     * * The BFS Traversal uses the [adjacencyList].
     * * So, ensure to have the [adjacencyList] before we can perform the BFS Traversal.
     */
    fun bfsTraversal(start: Int) {
        // We want to start from a particular vertex.
        // That vertex must be a part of the graph.
        // In other words, it must exist in the graph.
        // How do we check whether the vertex exist or not.
        // Before we can determine whether the vertex exist, we need to understand how we store the vertex.
        // We use the direct addressing method.
        // So, the vertx value becomes the index.
        // It means, if the given vertex value is not within the range of the available indices, it doesn't exist.
        if (start !in adjacencyList.indices) return
        val stringBuilder = StringBuilder()
        // We have to take a visited boolean array, because a graph can have a cycle.
        // And we don't want to print a vertex more than once.
        // And we don't want to keep running the program infinitely.
        // That's why, we take this boolean array, and we mark the vertex as visited once we push it to the queue.
        val visited = BooleanArray(size) { false }
        // We check all the vertices of the adjacency list one by one.
        // This is helpful when we have a disconnected graph.
        for ((vertex, neighbors) in adjacencyList.withIndex()) {
            if (!visited[vertex]) {
                // We use a queue for the BFS Traversal.
                val queue = ArrayDeque<Int>()
                // We eagerly add (enqueue, push) one starting vertex to the queue, and mark it as visited.
                queue.addLast(start)
                // Once we add (enqueue, push) the vertex to the queue, we mark the vertex as visited.
                // Add --> mark. (EdMark)
                visited[start] = true
                while (queue.isNotEmpty()) {
                    val pop = queue.removeFirst()
                    // After we pop, we print. pop --> print.
                    stringBuilder.append("$pop ")
                    // Add (enqueue) the neighbor list of the popped vertex.
                    // pop --> print --> neighbor list from the adjacency list.
                    val neighborList = adjacencyList[pop]
                    for (neighbor in neighborList) {
                        // Add (enqueue) only the unvisited vertices.
                        // Add only if the element is not visited.
                        if (!visited[neighbor]) {
                            queue.addLast(neighbor)
                            // Once we add (enqueue, push) the vertex to the queue, we mark the vertex as visited.
                            visited[neighbor] = true
                        }
                    }
                }
            }
        }
        println(stringBuilder)
    }
}

fun main() {
    val graph = BfsTraversalInGraph(5)
    graph.addEdges(0, 1)
    graph.addEdges(1, 2)
    graph.addEdges(1, 3)
    graph.addEdges(2, 3)
    graph.addEdges(2, 4)
    graph.printAdjacencyList()
    graph.bfsTraversal(0)
}