package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

/**
 * # Reference
 *
 * * [Cycle Detection In An Undirected Graph Using DFS](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/154e06d75822919c502c4069c4e439cdf111c52f/docs/03graph/courses/uc/module01decompositionOfGraph01/028cycleDetectionInGraph.md)
 *
 *
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

    fun hasCycleWithBfs(): Boolean {
        val visited = BooleanArray(size) { false }
        var hasCycle = false
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                hasCycle = hasCycleUsingBfs(vertex, -1, visited)
                if (hasCycle) return true
            }
        }
        return hasCycle
    }

    private fun hasCycleUsingBfs(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(Pair(vertex, parent))
        visited[vertex] = true
        while (queue.isNotEmpty()) {
            val pop = queue.removeFirst()
            val source = pop.first
            println(source) // Optional
            val parent = pop.second
            val neighbors = adjacencyList[source]
            neighbors.forEach {
                if (!visited[it]) {
                    queue.addLast(Pair(it, source))
                    visited[it] = true
                } else if (it != parent) {
                    println(it) // Optional
                    return true
                }
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
    println(if (graph.hasCycle()) "Has cycle: Used DFS" else "No cycle: Used DFS")
    println(if (graph.hasCycleWithBfs()) "Has cycle: Used BFS" else "No cycle: Used BFS")
}