package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class GraphTraversal(val size: Int) {
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
     * ---
     * * BFS Traversal from the given [start] vertex indicates and includes the connected components of [start].
     * * In other words, it prints all the vertices that are connected with [start], including [start].
     * * Sometimes, we also say that it prints all the reachable vertices for [start].
     * * And [start] is always reachable from itself, which is the reason we include it when we print.
     * * So, we use BFS or DFS Traversal whenever we want to explore the entire graph.
     * * And we use this specific logic whenever we want to find all the connections (vertices), that are connected
     * to and reachable from [start] using the BFS Traversal.
     * * We also use BFS and DFS Traversal to find a particular vertex.
     * ---
     * # TL;DR
     * * Enqueue and mark it as visited.
     * * As long as the queue is not empty, repeat:
     * * pop and print.
     * * Get the neighbor list.
     * * And enqueue each unvisited neighbor.
     *
     * # Time Complexity
     *
     * * We check each vertex and all the neighbors of the vertex.
     * * To check all the neighbors of the vertex, we use edges.
     * * We use all the edges to cover the entire graph.
     * * So, it is `O(V + E)`.
     * ---
     * * Another way to look at it is that we visite each vertex exactly once.
     * * We visit and explore the vertex only if we find that it is not visited.
     * * Finding the visit value is a constant time operation.
     * * So, we confirm that we visit each vertex exactly once.
     * * That's `V`.
     * * For every visited vertex, we scan the corresponding adjacency list.
     * * Total adjacency list entries for an undirected (bidirectional) graph is `2E`.
     * * And for a unidirectional graph, it is `E`.
     * * Since we drop the constant, it becomes `E`.
     * * So, the total becomes: `O(V + E)`.
     * ---
     * * In the worst case, we touch and visit each vertex.
     * * In the worst case, we process each edge from the `adjacencyList`.
     * * So, the time complexity is: `O(V + E)`.
     *
     * # Space Complexity
     *
     * * We use the [adjacencyList] and the `visited boolean array`.
     * * The [adjacencyList] is a part of the structure.
     * * It stores vertices and edges.
     * * So, it is `O(V + E)`.
     * * For the undirected (bidirectional) graph, an edge is stored twice.
     * * For example, if there is a connection (edge) between A and B, then the adjacency list will have:
     * ```
     * A : B
     * B : A
     * ```
     * * So, it takes roughly `2E` space.
     * * Because when we add an edge (a, b), it populates the neighbor list of each.
     * * However, the extra memory that we use solely for the [bfsTraversal], is counted for the space complexity.
     * * The auxialary space is coming from the `visited boolean array` and the `queue`.
     * * Their size is [size], which is the total number of vertices.
     * * Hence, the space complexity is `O(V)`.
     */
    fun bfsTraversal(start: Int, visited_: BooleanArray? = null) {
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
        var visited = visited_
        // We have to take a visited boolean array, because a graph can have a cycle.
        // And we don't want to print a vertex more than once.
        // And we don't want to keep running the program infinitely.
        // That's why, we take this boolean array, and we mark the vertex as visited once we enqueue it to the queue.
        if (visited == null) {
            visited = BooleanArray(size) { false }
        }
        if (!visited[start]) {
            // We use a queue for the BFS Traversal.
            val queue = ArrayDeque<Int>()
            // We eagerly add (enqueue, ) one starting vertex to the queue, and mark it as visited.
            queue.addLast(start)
            // Once we add (enqueue, ) the vertex to the queue, we mark the vertex as visited.
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
                        // Once we add (enqueue, ) the vertex to the queue, we mark the vertex as visited.
                        visited[neighbor] = true
                    }
                }
            }
        }
        println(stringBuilder)
    }

    /**
     * * This is helpful when we have a disconnected graph.
     * * We reuse the [bfsTraversal] logic.
     * * We reuse it for each unvisited vertex of the [adjacencyList].
     * * We pass only unvisited vertex.
     * * So, we define the `visited boolean array` here, and pass it to the [bfsTraversal].
     * ---
     * * We use this approach whenever we want to explore the entire graph (whether connected or disconnected).
     * * This works for both the connected graph and the disconnected graph.
     * * For the connected graph, the for-loop will run only once.
     * * Because a connected graph has only one component.
     * * So, it doesn't matter from where we start.
     * * It will cover the entire component - the only component of the graph - and hence, the entire graph.
     * * For the disconnected graph, the number of times the for-loop run is based on the number of components.
     * * For example, if we have 3 components, then the for-loop will run 3 times.
     * * In any case, it doesn't change the time complexity.
     * * Because the time complexity is based on the vertices and edges, and not on the components.
     * ---
     * # TL;DR
     *
     * * Define the `visited boolean array`.
     * * Iterate through the adjacency list (indices).
     * * An index of the adjacency list represents a vertex (direct addressing).
     * * Pass each unvisited vertex to the [bfsTraversal]
     *
     * # Time Complexity
     *
     * * We visit each vertex exactly once.
     * * And we use edges to visit the vertex.
     * * In an undirected (bidirectional) graph, there are a total of `2E` edges.
     * * So, it is `O(V + E)`.
     * * We might think that we are doing it inside a for-loop, for each vertex.
     * * But the point is we are not visiting every other vertex and every edge for each vertex.
     * * We are not scanning all the edges for each vertex.
     * * We are using the `visited boolean array`.
     * * That's the reason we take at most `O(V + E)` time.
     * ---
     * * In the worst case, we touch and visit each vertex.
     * * In the worst case, we check and process each edge from the `adjacencyList`.
     * * So, the time complexity is: `O(V + E)`.
     *
     * # Space Complexity
     *
     * * We visit each vertex exactly once.
     * * To visit each vertex, we use edges.
     * * For an undirected (bidirectional) graph, there are `2E` edges.
     * * If that's already given in the form of [adjacencyList], we are left with the auxialary space.
     * * We use the `visited boolean array` and the `queue`.
     * * Their size is [size], which is equal to the number of total vertices.
     * * So, the space complexity is `O(V)`.
     */
    fun bfsAll() {
        val visited = BooleanArray(size)
        // We check all the vertices of the adjacency list one by one.
        // And we are using the direct addressing method.
        // So, vertex is an index in the adjacencyList.
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                bfsTraversal(vertex, visited)
            }
        }
    }

    /**
     * * For the [dfsTraversal], we use the recursion approach.
     * * It has two parameters: [start] and [visited].
     * * We check whether [start] is visited or not.
     * * If [start] is visited, we don't simply return.
     * * We need to check the neighbor list of the [start].
     * * We need to check each neighbor from the neighbor list of the [start].
     * * If [start] is not visited, we print it, and mark it as visited.
     * * Then, we get the neighbor list of the [start].
     * * And for each neighbor, if it is not visited, we pass it to this [dfsTraversalHelper] recursively.
     * * We don't need to check `is visited` two times, though.
     * * Either we check in the beginning, or we check within the neighbor list loop.
     * * If we keep it only in the neighbor list loop, the caller of this function needs to take care that it passes only unvisited [start].
     * * Otherwise, if the caller of this function passes the visited [start], and we don't check it using the `if` condition before we print it, then we get false output.
     * * Here, it is the responsibility of the caller function to pass only unvisited vertices.
     * * So, the parameter [start] must represent the unvisited vertex to make the function work correctly.
     * ---
     * # TL;DR
     * * Eagery print the incoming vertex.
     * * Mark it as visited.
     * * Get neighbors.
     * * Recursively call the function itself for each unvisited neighbor.
     *
     * # Time Complexity
     *
     * ---
     * * We check each vertex.
     * * To check each vertex, we travel through the edges.
     * * To cover the entire graph, we cover all the edges.
     * ---
     * * In the worst case, we touch and visit each vertex.
     * * In the worst case, we read and process all the edges from the `adjacencyList`.
     * * So, the time complexity is `O(V + E)`.
     *
     * # Space Complexity
     *
     * * We use the [adjacencyList] and a `visited boolean array`.
     * * The [adjacencyList] is a part of the structure.
     * * It takes roughly `2E` space for the undirected (bidirectional) graph.
     * * But it is the part of the structure.
     * * However, the `visited boolean array` and the `call stack` are the auxialary space.
     * * The size of the `visited boolean array` is [size], which is the total number of vertices.
     * * And the call stack is also at most `O(V)`, even for the degenerated (linear) graph.
     * * Because a call stack represents visiting/exploring a vertex.
     * * The call stack represents the current depth, current DFS path.
     * * So, the call stack depends on the DFS path.
     * * In the worst case, we can have only one path for all the vertices.
     * * In other words, all the vertices are connected through exactly one path in a linear line.
     * * The single path that contains all the vertices.
     * * So, if the total vertices are `V`, we get a total of `V` call stack.
     * * And that would make it `O(V)`.
     * * So, the call stack is at most `O(V)`.
     * * So, the space complexity is `O(V)`, and it also considers the call stack.
     */
    private fun dfsTraversalHelper(start: Int, visited: BooleanArray) {
        println(start)
        visited[start] = true
        val neighbors = adjacencyList[start]
        neighbors.forEach {
            if (!visited[it]) {
                dfsTraversalHelper(it, visited)
            }
        }
    }


    /**
     * # TL;DR
     *
     * * Define the `visited boolean array`.
     * * Iterate through the adjacency list (indices).
     * * Each index represents a vertex (Direct addressing).
     * * For each unvisited vertex, call [dfsTraversalHelper].
     *
     * # Time Complexity
     *
     * * To cover the graph, we visit each vertex and each edge.
     * * In the worst case, we touch and visit each vertex.
     * * In the worst case, we read and process each edge from the `adjacencyList`.
     * * Hence, it is `O(V + E)`.
     *
     * # Space Complexity
     *
     * * We use [adjacencyList] and a `visited boolean array`.
     * * The [adjacencyList] is a part of the structure.
     * * The `visited boolean array` is the auxilary space we use solely for the [dfsTraversal].
     * * The size of the `visited boolean array` is [size], which is equal to the total vertices.
     * * So, the space complexity is `O(V)`.
     */
    fun dfsTraversal() {
        val visited = BooleanArray(size) { false }
        // We check all the vertices.
        // We are using the direct addressing method.
        // So, vertices are stored as indices.
        // We get the vertices from the `adjacencyList`.
        // This is useful for the disconnected graph.
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                dfsTraversalHelper(vertex, visited)
            }
        }
    }
}

fun main() {
    // Using the `size` and the `addEdges` function, we can create and test various connected and disconnected graphs.
    val graph = GraphTraversal(5)
    graph.addEdges(0, 1)
    graph.addEdges(1, 2)
    graph.addEdges(1, 3)
    graph.addEdges(2, 4)
    graph.printAdjacencyList()
    graph.bfsTraversal(0)
    graph.bfsAll()
    graph.dfsTraversal()
}