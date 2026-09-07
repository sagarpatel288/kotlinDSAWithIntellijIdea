package courses.uc.course03algorithmsOngraph.courses.uc.module02decompositionOfGraph02

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * []()
 */
class DetectCycle(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
    }

    fun detectCycle(): Boolean {
        val visited = BooleanArray(vertices)
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                if (dfs(vertex, -1, visited)) return true
            }
        }
        return false
    }

    private fun dfs(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
        println("DetectCycle: :dfs: incoming: vertex $vertex and parent is: $parent")
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                if (dfs(it, vertex, visited)) return true
            } else if (it != parent) {
                println("DetectCycle: :dfs: visited vertex is: $it and parent is $parent")
                return true
            }
        }
        return false
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine()
    val token = StringTokenizer(line)
    val vertices = token.nextToken().toInt()
    val edges = token.nextToken().toInt()
    val cycleDetection = DetectCycle(vertices)
    repeat(edges) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val a = token.nextToken().toInt()
        val b = token.nextToken().toInt()
        // If vertex are 1-based, subtract by 1
        cycleDetection.addEdge(a, b)
    }
    println(if (cycleDetection.detectCycle()) "1" else "0")
}