package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * []()
 *
 * # Count islands
 *
 * * This problem is similar to:
 * * []()
 *
 */
class ComponentsInMaze(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        if (a !in 0 until vertices || b !in 0 until vertices) return
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

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