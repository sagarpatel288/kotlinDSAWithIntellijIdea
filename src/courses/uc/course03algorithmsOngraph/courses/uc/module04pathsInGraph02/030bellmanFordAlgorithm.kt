package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

/**
 * # Bellman-Ford Algorithm to find the shortest distance where an edge can have a negative weight
 *
 * * Reference:
 * * [Bellman-Ford Algorithm.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f19d71d7a9e09f9f50475a13b7cc82a10fd15dad/docs/03graph/courses/uc/module04pathsInGraph02/010lectures/040bellmanFordAlgorithm.md)
 *
 */
class BellmanFordAlgorithm(val vertices: Int) {

    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    fun findShortestDistance(source: Int, destination: Int) {
        val dist = LongArray(vertices) { Long.MAX_VALUE }
        val prev = IntArray(vertices) { -1 }
        dist[source] = 0L
        repeat(vertices - 1) {
            for ((vertex, edges) in adjList.withIndex()) {
                for (edge in edges) {
                    if (dist[edge.to] > dist[edge.from] + edge.weight) {
                        dist[edge.to] = dist[edge.from] + edge.weight
                        prev[edge.to] = edge.from
                    }
                }
            }
        }
        // One more time
        var hasNegativeCycle = false
        for ((vertex, edges) in adjList.withIndex()) {
            for (edge in edges) {
                if (dist[edge.to] > dist[edge.from] + edge.weight) {
                    dist[edge.to] = dist[edge.from] + edge.weight
                    hasNegativeCycle = true
                    break
                }
            }
            if (hasNegativeCycle) break
        }
        if (hasNegativeCycle) {
            println("The source has a reachable negative cycle!")
            return
        } else {
            println(dist.joinToString(" "))
        }
        if (dist[destination] == Long.MAX_VALUE) {
            println("Destination is not reachable!")
            return
        }
        val result = mutableListOf<Int>()
        var cur = destination
        while (cur != source) {
            result.add(cur)
            cur = prev[cur]
        }
        result.add(source)
        println(result.reversed().joinToString(" "))
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val bellman = BellmanFordAlgorithm(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        bellman.addEdge(from, to, weight)
    }
    val (source, destination) = readln().split(" ").map { it.toInt() }
    bellman.findShortestDistance(source, destination)
}

/*
* Test input:
* 5 6
0 1 2
0 2 4
1 4 -1
1 2 -4
2 3 2
3 4 1
0 3
* Expected output:
* 0 2 -2 0 1
0 1 2 3
* */