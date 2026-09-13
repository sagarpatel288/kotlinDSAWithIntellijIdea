package courses.uc.course03algorithmsOngraph.courses.uc.module03pathsInGraph01

/**
 * # Reference
 *
 * * [Is it a bipartite graph?](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/0fcd5902387043322503e4bdd9285d2f1eb4a4a7/docs/03graph/courses/uc/module03pathsInGraph01/020assignment/020isItBipartiteGraph.md)
 *
 */
class CheckBipartite(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun isBipartite(): Boolean {
        val colors = IntArray(vertices) { -1 }
        for (vertex in adjacencyList.indices) {
            if (colors[vertex] == -1) {
                if (isBipartiteDfs(vertex, colors, 1) == false) {
                    return false
                }
            }
        }
        return true
    }

    private fun isBipartiteDfs(vertex: Int, colors: IntArray, color: Int): Boolean {
        colors[vertex] = color
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (colors[it] == -1) {
                val neighborColor = 1 - color
                if (isBipartiteDfs(it, colors, neighborColor) == false) {
                    return false
                }
            } else if (colors[it] == color) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val (vertices, edges) = readln().split(" ").map { it.toInt() }
    val checkBipartite = CheckBipartite(vertices)
    repeat(edges) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        checkBipartite.addEdge(a - 1, b - 1)
    }
    println(if (checkBipartite.isBipartite()) "1" else "0")
}