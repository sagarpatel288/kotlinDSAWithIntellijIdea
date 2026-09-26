package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

/**
 * # Exchange money optimally
 *
 * * Reference: [Optimal money exchange](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/bb266a523aa78c3488d20f5e76b1a5d113dd2320/docs/03graph/courses/uc/module04pathsInGraph02/020assignment/030optimalMoneyExchange.md)
 */
class OptimalMoneyExchange(val vertices: Int) {
    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    fun shortestPath(source: Int): Array<String> {
        val dist = LongArray(vertices) { Long.MAX_VALUE }
        dist[source] = 0
        // Find the finite distance
        repeat(vertices - 1) {
            var changed = false
            for ((vertex, edges) in adjList.withIndex()) {
                for ((from, to, weight) in edges) {
                    if (dist[from] >= Long.MAX_VALUE) {
                        continue
                    }
                    if (dist[to] > dist[from] + weight) {
                        val newDistance = dist[from] + weight
                        dist[to] = newDistance
                        changed = true
                    }
                }
            }
            if (!changed) {
                return@repeat
            }
        }
        val affected = BooleanArray(vertices)
        for ((vertex, edges) in adjList.withIndex()) {
            for ((from, to, weight) in edges) {
                if (dist[from] >= Long.MAX_VALUE) {
                    continue
                }
                if (dist[to] > dist[from] + weight) {
                    affected[to] = true
                }
            }
        }
        markAffected(affected)
        return markValues(dist, affected)
    }

    private fun markValues(dist: LongArray, affected: BooleanArray): Array<String> {
        return Array(vertices) { vertex ->
            when {
                affected[vertex] -> {
                    "-"
                }
                dist[vertex] == Long.MAX_VALUE -> {
                    "*"
                }
                else -> {
                    dist[vertex].toString()
                }
            }
        }
    }

    private fun markAffected(affected: BooleanArray) {
        val queue = ArrayDeque<Int>()
        for (vertex in 0 until vertices) {
            if (!affected[vertex]) {
                continue
            }
            queue.addLast(vertex)
        }
        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            val edges = adjList[vertex]
            for ((from, to, weight) in edges) {
                if (!affected[to]) {
                    affected[to] = true
                    queue.addLast(to)
                }
            }
        }
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val optimalMoneyExchange = OptimalMoneyExchange(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        optimalMoneyExchange.addEdge(from - 1, to - 1, weight)
    }
    val source = readln().toInt()
    println(optimalMoneyExchange.shortestPath(source - 1).joinToString("\n"))
}