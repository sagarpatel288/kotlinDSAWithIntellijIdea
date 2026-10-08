package courses.uc.course03algorithmsOngraph.courses.uc.module06advancedShortestPathProject.part01concepts

import java.util.PriorityQueue

/**
 * # Reference
 *
 * * [Bidirectional Dijkstra](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /c071ba68b94e74d6bc510a64179576ab968f5970/docs/03graph/courses/uc/module06advancedShortestPathProject/010lectures/010bidirectionalDijkstra.md)
 *
 */
class ShortestPathBidirectionalDijkstra(val totalVertices: Int) {

    data class Edge(val to: Int, val weight: Int)

    private val adjList = List(totalVertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(to, weight))
        adjList[to].add(Edge(from, weight))
    }

    fun shortestPath(source: Int, target: Int): Long {
        val forwardDist = LongArray(totalVertices) { Long.MAX_VALUE }
        forwardDist[source] = 0
        val parentsInForward = IntArray(totalVertices) { -1 }
        parentsInForward[source] = source // or -1?

        val backwardDist = LongArray(totalVertices) { Long.MAX_VALUE }
        backwardDist[target] = 0
        val parentsInBackward = IntArray(totalVertices) { -1 }
        parentsInBackward[target] = target // or -1?

        val forwardQueue = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        forwardQueue.add(0 to source)

        val backwardQueue = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )
        backwardQueue.add(0 to target)

        var min = Long.MAX_VALUE

        while (forwardQueue.isNotEmpty() && backwardQueue.isNotEmpty()) {

            val forwardMin = forwardQueue.peek().first
            val backwardMin = backwardQueue.peek().first

            val bestDist = forwardMin + backwardMin

            if (bestDist >= min) {
                break
            }

            val (forwardDistance, forwardVertex) = forwardQueue.poll()
            if (forwardDistance > forwardDist[forwardVertex]) continue
            val forwardEdges = adjList[forwardVertex]
            for ((to, weight) in forwardEdges) {
                if (forwardDist[to] > forwardDistance + weight) {
                    val distance = forwardDistance + weight
                    forwardDist[to] = distance.toLong()
                    parentsInForward[to] = forwardVertex
                    forwardQueue.add(distance to to)
                }
                if (backwardDist[to] != Long.MAX_VALUE) {
                    val completePathDistance = forwardDistance + weight + backwardDist[to]
                    if (completePathDistance < min) {
                        min = completePathDistance
                    }
                }
            }

            val (backwardDistance, backwardVertex) = backwardQueue.poll()
            if (backwardDistance > backwardDist[backwardVertex]) continue
            val backwardEdges = adjList[backwardVertex]
            for ((to, weight) in backwardEdges) {
                if (backwardDist[to] > backwardDistance + weight) {
                    val distance = backwardDistance + weight
                    backwardDist[to] = distance.toLong()
                    backwardQueue.add(distance to to)
                    parentsInBackward[to] = backwardVertex
                }
                if (forwardDist[to] != Long.MAX_VALUE) {
                    val completePathDistance = backwardDistance + weight + forwardDist[to]
                    if (completePathDistance < min) {
                        min = completePathDistance
                    }
                }
            }
        }

        return if (min == Long.MAX_VALUE) -1 else min
    }
}