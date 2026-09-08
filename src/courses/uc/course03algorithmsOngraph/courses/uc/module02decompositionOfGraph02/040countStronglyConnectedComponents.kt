package courses.uc.course03algorithmsOngraph.courses.uc.module02decompositionOfGraph02

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * [Count strongly connected components](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /4fe49a5f332702866cabe39f97e53c980c42dff8/docs/03graph/courses/uc/module02decompositionOfGraph02/020assignment/030countStronglyConnectedComponents.md)
 */
class CountSccs(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }
    
    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
    }
    
    fun countSccs(): Int {
        val stack = topoSort()
        val transposedAdjacencyList = transposedGraph()
        val visited = BooleanArray(vertices)
        var count = 0
        while (stack.isNotEmpty()) {
            val vertex = stack.removeFirst()
            if (!visited[vertex]) {
                dfsToCountSccs(vertex, transposedAdjacencyList, visited)
                count++
            }
        }
        return count
    }
    
    private fun topoSort(): ArrayDeque<Int> {
        val visited = BooleanArray(vertices)
        val stack = ArrayDeque<Int>()
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                dfsForTopoSort(vertex, visited, stack)
            }
        }
        return stack
    }
    
    private fun transposedGraph(): List<MutableList<Int>> {
        val transposedAdjacencyList = List(vertices) { mutableListOf<Int>() }
        for ((vertex, neighbors) in adjacencyList.withIndex()) {
            neighbors.forEach {
                transposedAdjacencyList[it].add(vertex)
            }
        }
        return transposedAdjacencyList
    }
    
    private fun dfsForTopoSort(vertex: Int, visited: BooleanArray, stack: ArrayDeque<Int>) {
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                dfsForTopoSort(it, visited, stack)
            }
        }
        stack.addFirst(vertex)
    }
    
    private fun dfsToCountSccs(vertex: Int, transposedAdjacencyList: List<MutableList<Int>>, visited: BooleanArray) {
        visited[vertex] = true
        val neighbors = transposedAdjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                dfsToCountSccs(it, transposedAdjacencyList, visited)
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
    val countSccs = CountSccs(vertices)
    repeat(edges) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val a = token.nextToken().toInt()
        val b = token.nextToken().toInt()
        countSccs.addEdge(a - 1, b - 1)
    }
    println(countSccs.countSccs())
}