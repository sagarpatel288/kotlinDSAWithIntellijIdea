package courses.uc.course03algorithmsOngraph.courses.uc.module03pathsInGraph01

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Reference
 *
 * * [Minimum number of flight segments](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /f100a2fd7560f9c58f355d86ec553fd0276f9f32/docs/03graph/courses/uc/module03pathsInGraph01/020assignment/010computingTheMinimumNumberOfFlightSegments.md)
 *
 */
class ShortestPath(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun shortestPath(start: Int, destination: Int) {
        val dist = IntArray(vertices) { -1 }
        val prev = IntArray(vertices) { -1 }
        val queue = ArrayDeque<Int>()
        dist[start] = 0
        prev[start] = -1
        queue.addLast(start)
        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            // +1 because input is 1-based index
            // This is the standard BFS printing
            // It covers all the vertices, the entire connected component (unless we use early exit)
            // This represents which vertices we have explored in which order
            // It can include the paths that are not the shortest path between the start and the destination
            // This does not represent the shortest path between the start and the destination
            if (vertex == destination) break
            val neighbors = adjacencyList[vertex]
            neighbors.forEach {
                if (dist[it] == -1) {
                    queue.addLast(it)
                    dist[it] = dist[vertex] + 1
                    prev[it] = vertex
                }
            }
        }
        println(dist[destination])
        val result = mutableListOf<Int>()
        var cur = destination
        while (cur != start) {
            result.add(cur + 1)
            cur = prev[cur]
        }
        result.add(start + 1)
        println(result.reversed())
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine()
    val token = StringTokenizer(line)
    val vertices = token.nextToken().toInt()
    val edges = token.nextToken().toInt()
    val shortestPath = ShortestPath(vertices)
    repeat(edges) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val a = token.nextToken().toInt()
        val b = token.nextToken().toInt()
        shortestPath.addEdge(a - 1, b - 1)
    }
    val pathLine = reader.readLine()
    val pathToken = StringTokenizer(pathLine)
    val start = pathToken.nextToken().toInt()
    val destination = pathToken.nextToken().toInt()
    shortestPath.shortestPath(start - 1, destination - 1)
}