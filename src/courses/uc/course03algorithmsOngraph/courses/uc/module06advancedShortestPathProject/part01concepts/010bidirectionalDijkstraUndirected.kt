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

    data class Edge(val to: Int, val weight: Long)

    data class Result(val distance: Long, val path: List<Int>)

    private val adjList = List(totalVertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Long) {
        adjList[from].add(Edge(to, weight))
        adjList[to].add(Edge(from, weight))
    }

    fun shortestPath(source: Int, target: Int): Result {
        if (source == target) {
            return Result(
                distance = 0L,
                path = listOf(source)
            )
        }

        val forwardDist = LongArray(totalVertices) { Long.MAX_VALUE }
        forwardDist[source] = 0
        val parentsInForward = IntArray(totalVertices) { -1 }
        parentsInForward[source] = source // or -1?

        val backwardDist = LongArray(totalVertices) { Long.MAX_VALUE }
        backwardDist[target] = 0
        val parentsInBackward = IntArray(totalVertices) { -1 }
        parentsInBackward[target] = target // or -1?

        val forwardQueue = PriorityQueue(
            compareBy<Pair<Long, Int>> { it.first }.thenBy { it.second }
        )
        forwardQueue.add(0L to source)

        val backwardQueue = PriorityQueue(
            compareBy<Pair<Long, Int>> { it.first }.thenBy { it.second }
        )
        backwardQueue.add(0L to target)

        var min = Long.MAX_VALUE
        var bestFrom = -1
        var bestTo = -1

        while (forwardQueue.isNotEmpty() && backwardQueue.isNotEmpty()) {

            removeStaleEntries(forwardQueue, forwardDist)
            removeStaleEntries(backwardQueue, backwardDist)

            if (forwardQueue.isEmpty() || backwardQueue.isEmpty()) {
                break
            }

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
                    forwardDist[to] = distance
                    parentsInForward[to] = forwardVertex
                    forwardQueue.add(distance to to)
                }
                if (backwardDist[to] != Long.MAX_VALUE) {
                    val completePathDistance = forwardDistance + weight + backwardDist[to]
                    if (completePathDistance < min) {
                        min = completePathDistance
                        bestFrom = forwardVertex
                        bestTo = to
                    }
                }
            }

            val (backwardDistance, backwardVertex) = backwardQueue.poll()
            if (backwardDistance > backwardDist[backwardVertex]) continue
            val backwardEdges = adjList[backwardVertex]
            for ((to, weight) in backwardEdges) {
                if (backwardDist[to] > backwardDistance + weight) {
                    val distance = backwardDistance + weight
                    backwardDist[to] = distance
                    backwardQueue.add(distance to to)
                    parentsInBackward[to] = backwardVertex
                }
                if (forwardDist[to] != Long.MAX_VALUE) {
                    val completePathDistance = backwardDistance + weight + forwardDist[to]
                    if (completePathDistance < min) {
                        min = completePathDistance
                        bestFrom = to
                        bestTo = backwardVertex
                    }
                }
            }
        }
        if (min == Long.MAX_VALUE) {
            return Result(distance = -1L, path = emptyList())
        }
        val path = reconstructPath(source, target, bestFrom, bestTo, parentsInForward, parentsInBackward)
        return Result(min, path)
    }

    private fun removeStaleEntries(queue: PriorityQueue<Pair<Long, Int>>, dist: LongArray) {
        while (queue.isNotEmpty()) {
            val (distance, vertex) = queue.peek()
            if (distance > dist[vertex]) {
                queue.poll()
            } else {
                return
            }
        }
    }

    private fun reconstructPath(source: Int, target: Int, bestFrom: Int, bestTo: Int, parentsInForward: IntArray,
                                parentsInBackward: IntArray): List<Int> {
        val forwardPath = mutableListOf<Int>()
        var curForward = bestFrom
        while (curForward != source) {
            forwardPath.add(curForward)
            curForward = parentsInForward[curForward]
        }
        forwardPath.add(source)
        forwardPath.reverse()

        val backwardPath = mutableListOf<Int>()
        var curBackward = bestTo
        while (curBackward != target) {
            backwardPath.add(curBackward)
            curBackward = parentsInBackward[curBackward]
        }
        backwardPath.add(target)
        return (forwardPath + backwardPath)
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val shortestPath = ShortestPathBidirectionalDijkstra(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        shortestPath.addEdge(from - 1, to - 1, weight.toLong())
    }
    val (source, target) = readln().split(" ").map { it.toInt() }
    val result = shortestPath.shortestPath(source - 1, target - 1)
    println(result.distance)
    println(result.path.joinToString(" "))
}