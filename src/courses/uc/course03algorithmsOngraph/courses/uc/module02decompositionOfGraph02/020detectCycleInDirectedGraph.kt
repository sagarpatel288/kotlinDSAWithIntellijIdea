package courses.uc.course03algorithmsOngraph.courses.uc.module02decompositionOfGraph02

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * [Detect cycle in a directed graph](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/fdf4ad833ddfae26ef6f89fd580d6cb1b4e97b44/docs/03graph/courses/uc/module02decompositionOfGraph02/020assignment/010detectCycleInDirectedGraph.md)
 */
class DetectCycle(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
    }

    fun detectCycle(): Boolean {
        val visited = BooleanArray(vertices)
        val visitedPath = BooleanArray(vertices)
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                if (dfs(vertex, visited, visitedPath)) return true
            }
        }
        return false
    }

    private fun dfs(vertex: Int, visited: BooleanArray, visitedPath: BooleanArray): Boolean {
        visited[vertex] = true
        visitedPath[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                if (dfs(it, visited, visitedPath)) return true
            } else if (visitedPath[it]) {
                return true
            }
        }
        visitedPath[vertex] = false
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
        cycleDetection.addEdge(a - 1, b - 1)
    }
    println(if (cycleDetection.detectCycle()) "1" else "0")
}