package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

/**
 * # Exchange money optimally
 *
 * * Reference: [Optimal money exchange](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/bb266a523aa78c3488d20f5e76b1a5d113dd2320/docs/03graph/courses/uc/module04pathsInGraph02/020assignment/030optimalMoneyExchange.md)
 */
class ClassifyVertex(val vertices: Int) {
    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }
    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    fun classify(source: Int): Array<String> {
        val dist = LongArray(vertices) { Long.MAX_VALUE }
        dist[source] = 0
        // Phase-1: Finite values: Time: (VE)
        for (i in 0 until vertices) {
            var changed = false // For early exit. Reset before we start!
            for ((vertex, edges) in adjList.withIndex()) {
                for ((from, to, weight) in edges) {
                    if (dist[from] >= Long.MAX_VALUE) continue
                    if (dist[to] > dist[from] + weight) {
                        dist[to] = dist[from] + weight
                        changed = true
                    }
                }
            }
            // Check before we start the next round.
            // If the last inspection did not change any `dist` value, no future inspection will change it ever.
            // Early exit.
            if (!changed) {
                break
            }
        }
        // Phase-2: Detecting a negative cycle + collecting infected seeds:
        val infected = BooleanArray(vertices)
        val queue = ArrayDeque<Int>()
        for ((vertex, edges) in adjList.withIndex()) {
            for ((from, to, weight) in edges) {
                if (dist[from] >= Long.MAX_VALUE) continue
                if (dist[to] > dist[from] + weight) {
                    if (!infected[to]) {
                        infected[to] = true
                        queue.addLast(to)
                    }
                }
            }
        }
        // Phase-3: Finding all the infected vertices via seeds
        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            val edges = adjList[vertex]
            for ((_, to, _) in edges) {
                if (!infected[to]) {
                    infected[to] = true
                    queue.addLast(to)
                }
            }
        }
        // Phase-4: Building the result
        return Array(vertices) { index ->
            when {
                infected[index] -> "-"
                dist[index] >= Long.MAX_VALUE -> "*"
                else -> dist[index].toString()
            }
        }
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val classifyVertex = ClassifyVertex(totalVertices)
    repeat(totalEdges) {
        // 1- based index
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        classifyVertex.addEdge(from - 1, to - 1, weight)
    }
    val source = readln().toInt()
    println(classifyVertex.classify(source - 1).joinToString("\n"))
}