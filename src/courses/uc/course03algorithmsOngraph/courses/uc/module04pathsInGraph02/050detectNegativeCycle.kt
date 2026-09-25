package courses.uc.course03algorithmsOngraph.courses.uc.module04pathsInGraph02

class DetectNegCycle(val vertices: Int) {
    data class Edge(val from: Int, val to: Int, val weight: Int)

    private val adjList = List(vertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(from, to, weight))
    }

    fun hasNegativeCycle(): Boolean {
        // Imagine a super-source to which each vertex is connected with weight 0.
        // Without this, all the values will remain Long.MAX_VALUE
        // And we will be doing math on infinite.
        // It would give a wrong result.
        // For the solve purpose of detecting a negative cycle, we need some base value, a finite default value.
        // The solve purpose is to detect whether we can keep reducing the value infinitely or not.
        // If we can, there is a negative cycle.
        // Otherwise, there is no negative cycle.
        val dist = LongArray(vertices) { 0L }
        repeat(vertices - 1) {
            for ((_, edges) in adjList.withIndex()) {
                for ((from, to, weight) in edges) {
                    if (dist[to] > dist[from] + weight) {
                        dist[to] = dist[from] + weight
                    }
                }
            }
        }
        var hasNegCycle = false
        for ((_, edges) in adjList.withIndex()) {
            for ((from, to, weight) in edges) {
                if (dist[to] > dist[from] + weight) {
                    hasNegCycle = true
                    break
                }
            }
            if (hasNegCycle) {
                break
            }
        }
        return hasNegCycle
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val detectNegCycle = DetectNegCycle(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        // 1-based index
        detectNegCycle.addEdge(from - 1, to - 1, weight)
    }
    println(if (detectNegCycle.hasNegativeCycle()) "1" else "0")
}