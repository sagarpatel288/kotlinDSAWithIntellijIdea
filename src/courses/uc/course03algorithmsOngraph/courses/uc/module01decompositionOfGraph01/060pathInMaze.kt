package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class PathInMaze(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        if (a !in 0 until vertices || b !in 0 until vertices) return
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun hasPath(a: Int, b: Int): Boolean {
        if (a !in 0 until vertices || b !in 0 until vertices) return false
        val visited = BooleanArray(vertices)
        return hasPathUsingDfs(a, b, visited)
    }

    private fun hasPathUsingDfs(a: Int, b: Int, visited: BooleanArray): Boolean {
        if (a == b) return true
        if (visited[a]) return false
        visited[a] = true
        val neighbors = adjacencyList[a]
        neighbors.forEach {
            if (!visited[it]) {
                if (hasPathUsingDfs(it, b, visited)) {
                    return true
                }
            }
        }
        return false
    }
}

fun main() {
    val (vertices, edges) = readln().split(" ").map { it.toInt() }
    val graph = PathInMaze(vertices)
    repeat(edges) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph.addEdge(a - 1, b - 1)
    }
    val (a, b) = readln().split(" ").map { it.toInt() }
    println(if (graph.hasPath(a - 1, b - 1)) "1" else "0")
}