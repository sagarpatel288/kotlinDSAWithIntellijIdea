package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

/**
 * # Reference
 * * []()
 */
class CycleDetectionUndirectedGraph(val size: Int) {
    val adjacencyList = List(size) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun hasCycle(): Boolean {
        val visited = BooleanArray(size) { false }
        var hasCycle = false
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                hasCycle = hasCycleUsingDfs(vertex, -1, visited)
                if (hasCycle) return true
            }
        }
        return hasCycle
    }

    private fun hasCycleUsingDfs(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
        println(vertex) // Optional
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                if (hasCycleUsingDfs(it, vertex, visited)) return true
            } else if (it != parent) {
                // The neighbor is already visited, but it is not the parent.
                // That's the back edge.
                // That's the cycle.
                println(it) // Optional
                return true
            }
        }
        return false
    }
}

fun main() {
    val graph = CycleDetectionUndirectedGraph(5)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 3)
    graph.addEdge(1, 2)
    graph.addEdge(3, 4)
    println(if (graph.hasCycle()) "Has cycle" else "No cycle")
}