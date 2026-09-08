package courses.uc.course03algorithmsOngraph.courses.uc.module02decompositionOfGraph02

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * [Topological sort in a DAG](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /bc51cab1df11839ba126e828064dfb6370f9a5bc/docs/03graph/courses/uc/module02decompositionOfGraph02/020assignment/020topologicalSortInDirectedGraph.md)
 */
class TopoSortOfDag(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
    }

    fun topoSort(): List<Int> {
        val visited = BooleanArray(vertices)
        val stack = ArrayDeque<Int>()
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                dfs(vertex, visited, stack)
            }
        }
        return stack.map { it + 1 }.toList()
    }

    private fun dfs(vertex: Int, visited: BooleanArray, stack: ArrayDeque<Int>) {
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                dfs(it, visited, stack)
            }
        }
        stack.addFirst(vertex)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine()
    val token = StringTokenizer(line)
    val vertices = token.nextToken().toInt()
    val edges = token.nextToken().toInt()
    val topoSort = TopoSortOfDag(vertices)
    repeat(edges) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val a = token.nextToken().toInt()
        val b = token.nextToken().toInt()
        topoSort.addEdge(a - 1, b - 1)
    }
    println(topoSort.topoSort())
}


