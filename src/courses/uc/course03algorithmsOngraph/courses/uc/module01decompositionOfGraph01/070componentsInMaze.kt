package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * [Components in maze](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /e3dc5c22165eeab6b7a0c861643e4c93b300e647/docs/03graph/courses/uc/module01decompositionOfGraph01/020assignment/020componentsInMaze.md)
 *
 * # Count islands
 *
 * * This problem is similar to:
 * * [Count islands](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /6c6fc328efb9cc83507fab5ad7b071dbbe19d26e/docs/03graph/courses/uc/module01decompositionOfGraph01/010lectures/032numberOfIslands.md)
 *
 */
class ComponentsInMaze(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        if (a !in 0 until vertices || b !in 0 until vertices) return
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    /**
     * # Time Complexity
     *
     * * In the worst case, we touch, visit, and process each vertex.
     * * In the worst case, we process each edge from the `adjacencyList`.
     * * So, the total time complexity is: `O(V + E)`
     *
     * # Space Complexity
     *
     * * If we consider that `adjacencyList` is given, and it is a part of the problem memory,
     * then the auxiliary memory (space) that we use to solve the problem comes from the `visited boolean array`.
     * * The size of the `visited boolean array` is as per the given value of total number of `vertices`.
     * * And the maximum stack depth cannot be more than `vertices`.
     * * So, the total space complexity is: `O(V)`.
     */
    fun ensureExit(): Int {
        val visited = BooleanArray(vertices)
        var islands = 0
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                ensureExitUsingDfs(vertex, visited)
                islands++
            }
        }
        return islands
    }

    private fun ensureExitUsingDfs(vertex: Int, visited: BooleanArray) {
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                ensureExitUsingDfs(it, visited)
            }
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine()
    val token = StringTokenizer(line)
    val vertices = token.nextToken().toInt()
    val edges = token.nextToken().toInt()
    val exitInMazeAsGraph = ComponentsInMaze(vertices)
    repeat(edges) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val a = token.nextToken().toInt()
        val b = token.nextToken().toInt()
        exitInMazeAsGraph.addEdge(a, b)
    }
    println(exitInMazeAsGraph.ensureExit())
}