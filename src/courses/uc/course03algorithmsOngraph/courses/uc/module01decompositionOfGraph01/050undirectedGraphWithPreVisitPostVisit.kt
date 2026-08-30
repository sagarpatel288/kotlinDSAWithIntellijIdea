package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class UndirectedGraph(val vertices: Int = 0, val grid: Array<CharArray>? = null) {
    init {
        if (grid == null && vertices <= 0) throw IllegalArgumentException("Total vertices must be greater than 0!")
    }
    val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdges(a: Int, b: Int) {
        if (a !in 0..<vertices || b !in 0..<vertices) {
            throw IllegalArgumentException("Total vertices: $vertices Given: a: $a and b: $b")
        }
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        for ((vertex, neighbors) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex is: $vertex: Neighbors are: ")
            neighbors.forEach {
                stringBuilder.append("$it, ")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }

    fun dfs() {
        val visited = BooleanArray(vertices) { false }
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                dfsTraversal(vertex, visited)
            }
        }
    }

    private fun dfsTraversal(vertex: Int, visited: BooleanArray) {
        println(vertex)
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                dfsTraversal(it, visited)
            }
        }
    }

    fun bfs() {
        val visited = BooleanArray(vertices) { false }
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                bfsTraversal(vertex, visited)
            }
        }
    }

    private fun bfsTraversal(vertex: Int, visited: BooleanArray) {
        val queue = ArrayDeque<Int>()
        queue.addLast(vertex)
        visited[vertex] = true
        while (queue.isNotEmpty()) {
            val pop = queue.removeFirst()
            println(pop)
            val neighbors = adjacencyList[pop]
            neighbors.forEach {
                if (!visited[it]) {
                    queue.addLast(it)
                    visited[it] = true
                }
            }
        }
    }

    fun hasCycleUsingDfs(): Boolean {
        val visited = BooleanArray(vertices) { false }
        var hasCycle = false
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                hasCycle = hasCycleUsingDfsTraversal(vertex, -1, visited)
                if (hasCycle) return true
            }
        }
        println("Has cycle: $hasCycle. Used DFS.")
        return hasCycle
    }

    private fun hasCycleUsingDfsTraversal(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
        println(vertex)
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                if (hasCycleUsingDfsTraversal(it, vertex, visited)) return true
            } else if (it != parent) {
                println(it) // Optional
                return true
            }
        }
        return false
    }

    fun hasCycleUsingBfs(): Boolean {
        val visited = BooleanArray(vertices) { false }
        var hasCycle = false
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                hasCycle = hasCycleUsingBfsTraversal(vertex, -1, visited)
                if (hasCycle) return true
            }
        }
        println("Has Cycle: $hasCycle. Used BFS.")
        return hasCycle
    }

    private fun hasCycleUsingBfsTraversal(vertex: Int, parent_: Int, visited: BooleanArray): Boolean {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(vertex to parent_)
        while (queue.isNotEmpty()) {
            val pair = queue.removeFirst()
            val vertex = pair.first
            val parent = pair.second
            println(vertex)
            val neighbors = adjacencyList[vertex]
            neighbors.forEach {
                if (!visited[it]) {
                    queue.addLast(it to vertex)
                    visited[it] = true
                } else if (it != parent) {
                    println(it)
                    return true
                }
            }
        }
        return false
    }

    fun countIslandsUsingDfs(): Int {
        var islands = 0
        if (grid == null || grid.isEmpty()) return 0
        val rows = grid.size
        val columns = grid[0].size
        val visited = Array(rows) { BooleanArray(columns) { false } }
        if (rows <= 0) return 0
        for (i in 0..<rows) {
            for (j in 0..<columns) {
                if (grid[i].isEmpty()) continue
                if (!visited[i][j] && grid[i][j] == '1') {
                    countIslandsUsingDfsTraversal(i, j, grid, visited)
                    islands++
                }
            }
        }
        return islands
    }

    private fun countIslandsUsingDfsTraversal(row: Int, column: Int, grid: Array<CharArray>, visited: Array<BooleanArray>) {
        if (row !in 0..<grid.size || column !in 0..<grid[0].size || visited[row][column] || grid[row][column] == '0') {
            return
        }
        println(grid[row][column])
        visited[row][column] = true
        countIslandsUsingDfsTraversal(row - 1, column, grid, visited)
        countIslandsUsingDfsTraversal(row, column + 1, grid, visited)
        countIslandsUsingDfsTraversal(row + 1, column, grid, visited)
        countIslandsUsingDfsTraversal(row, column - 1, grid, visited)
        return
    }

    /**
     * A normal class instead of a `data class` because it has mutable properties
     */
    class Timestamps(val vertex: Int, var preVisit: Int = -1, var postVisit: Int = -1)

    fun previsitPostvisitTimestamps() {
        val visited = BooleanArray(vertices) { false }
        var time = 0
        val timestamps = Array(vertices) { Timestamps(-1, -1, -1) }
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                time = preVisitPostVisitTimestampsUsingDfs(vertex, time, visited, timestamps)
            }
        }
        timestamps.sortByDescending { it.postVisit }
        timestamps.forEach {
            println("Vertex: ${it.vertex} PreVisit: ${it.preVisit} PostVisit: ${it.postVisit}")
        }
    }

    private fun preVisitPostVisitTimestampsUsingDfs(vertex: Int, time_: Int, visited: BooleanArray, timestamps: Array<Timestamps>): Int {
        var time = time_
        val timestamp = Timestamps(vertex, time++)
        timestamps[vertex] = timestamp
        println(vertex)
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                time = preVisitPostVisitTimestampsUsingDfs(it, time, visited, timestamps)
            }
        }
        timestamp.postVisit = time++
        timestamps[vertex] = timestamp
        return time
    }
}

fun main() {
    val row1 = charArrayOf('1', '1', '0', '0', '0')
    val row2 = charArrayOf('1', '1', '0', '0', '0')
    val row3 = charArrayOf('0', '0', '1', '0', '0')
    val row4 = charArrayOf('0', '0', '0', '1', '1')
    val grid = arrayOf(row1, row2, row3, row4)
    val undirectedGraph = UndirectedGraph(10, grid)
    undirectedGraph.addEdges(0, 1)
    undirectedGraph.addEdges(1, 2)
    undirectedGraph.addEdges(1, 3)
    undirectedGraph.addEdges(2, 4)
    undirectedGraph.addEdges(5, 6)
    undirectedGraph.addEdges(6, 7)
    undirectedGraph.addEdges(6, 8)
    undirectedGraph.addEdges(7, 9)
    undirectedGraph.printAdjacencyList()
    println("BFS Traversal: ${undirectedGraph.bfs()}")
    println("DFS Traversal: ${undirectedGraph.dfs()}")
    if (undirectedGraph.hasCycleUsingDfs()) println("Has cycle! Used Dfs.")
    if (undirectedGraph.hasCycleUsingBfs()) println("Has cycle! Used Bfs.")
    val islands = undirectedGraph.countIslandsUsingDfs()
    println("Islands: $islands : Used Dfs.")
    println("Timestamps: ${undirectedGraph.previsitPostvisitTimestamps()}")
}