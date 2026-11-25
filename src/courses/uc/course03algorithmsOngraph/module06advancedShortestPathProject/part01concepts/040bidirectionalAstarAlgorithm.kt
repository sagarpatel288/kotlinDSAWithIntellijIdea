package courses.uc.course03algorithmsOngraph.module06advancedShortestPathProject.part01concepts

import java.util.PriorityQueue

/**
 * # Bidirectional A-Star Algorithm
 *
 * # Reference
 * * [Bidirectional A-Star Algorithm](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/e9fd432cad8227923d15dac1b6071e2282a1d322/docs
 * /03graph/courses/uc/module06advancedShortestPathProject/010lectures/030bidirectionalAstar.md)
 *
 * # Note:
 *
 * * It is guaranteed that all the values are within `int` range.
 * * However, their arithmetic operations can cause overflow.
 */
class BidirectionalAStar(private val totalVertices: Int) {

    init {
        require(totalVertices > 0) {
            "Total vertices must be greater than 0!"
        }
    }

    private data class Edge(val to: Int, val weight: Long)

    private val adjList = List(totalVertices) { mutableListOf<Edge>() }

    fun addEdge(from: Int, to: Int, weight: Long) {
        require(from >= 0) {
            "From cannot be a negative value!"
        }
        require(from in 0..<totalVertices) {
            "From must be between 0 to ${totalVertices - 1}"
        }
        require(to >= 0) {
            "To cannot be a negative value!"
        }
        require(to in 0..<totalVertices) {
            "To must be between 0 to  ${totalVertices - 1}"
        }
        require(weight >= 0) {
            "Edge weight must be non-negative!"
        }
        adjList[from].add(Edge(to, weight))
        adjList[to].add(Edge(from, weight))
    }

    data class Result(val distance: Long, val path: List<Int>)

    private data class QueueItem(val priority: Long, val actualDistance: Long, val vertex: Int)

    fun shortestPath(source: Int, target: Int, heuristicToTarget: List<Int>, heuristicToSource: List<Int>): Result {
        require(source >= 0) {
            "Source cannot be a negative value! Source is: $source"
        }
        require(source in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but the source is: $source"
        }
        require(target >= 0) {
            "Target cannot be a negative value! Target is: $target"
        }
        require(target in 0..<totalVertices) {
            "Total vertices are: $totalVertices, but the target is: $target"
        }
        require(heuristicToTarget.size == totalVertices) {
            "heuristicToTarget size is: ${heuristicToTarget.size}, but total vertices are: $totalVertices"
        }
        require(heuristicToTarget[target] == 0) {
            "For the target, heuristic to target must be 0!"
        }
        require(heuristicToSource.size == totalVertices) {
            "heuristicToSource size is: ${heuristicToSource.size}, but total vertices are: $totalVertices"
        }
        require(heuristicToSource[source] == 0) {
            "For the source, heuristic to source must be 0!"
        }
        if (source == target) {
            return Result(0L, listOf(source))
        }
        val inf = Long.MAX_VALUE
        val forwardDist = LongArray(totalVertices) { inf }
        val backwardDist = LongArray(totalVertices) { inf }

        forwardDist[source] = 0L
        backwardDist[target] = 0L

        val forwardParents = IntArray(totalVertices) { -1 }
        val backwardParents = IntArray(totalVertices) { -1 }

        forwardParents[source] = source
        backwardParents[target] = target

        var shortestPath = inf
        var sourceEnd = -1
        var targetStart = -1

        val forwardQueue = PriorityQueue(
            compareBy<QueueItem> { it.priority }.thenBy { it.actualDistance }.thenBy { it.vertex }
        )
        val backwardQueue = PriorityQueue(
            compareBy<QueueItem> { it.priority }.thenBy { it.actualDistance }.thenBy { it.vertex }
        )

        val sourcePotential = potential(source, heuristicToTarget, heuristicToSource)

        forwardQueue.add(QueueItem(
            priority = sourcePotential,
            actualDistance = 0L,
            source
        ))

        val targetPotential = potential(target, heuristicToTarget, heuristicToSource)

        backwardQueue.add(QueueItem(
            priority = -targetPotential,
            actualDistance = 0L,
            target
        ))

        while (true) {
            removeStaleEntries(forwardQueue, forwardDist)
            removeStaleEntries(backwardQueue, backwardDist)
            if (forwardQueue.isEmpty() || backwardQueue.isEmpty()) {
                val shortestPathResult = if (shortestPath == inf) -1 else shortestPath
                val pathResult = if (shortestPath == inf) emptyList() else path(source, target, sourceEnd, targetStart, forwardParents, backwardParents)
                return Result(shortestPathResult, pathResult)
            }
            val fPeek = forwardQueue.peek()
            val bPeek = backwardQueue.peek()
            if (fPeek.priority + bPeek.priority >= (2 * shortestPath)) {
                return Result(shortestPath, path(source, target, sourceEnd, targetStart, forwardParents, backwardParents))
            }
            if (fPeek.priority <= bPeek.priority) {
                val fPolled = forwardQueue.poll()
                val fEdges = adjList[fPolled.vertex]
                for ((to, weight) in fEdges) {
                    val shorterDist = forwardDist[fPolled.vertex] + weight
                    if (forwardDist[to] > shorterDist) {
                        forwardDist[to] = shorterDist
                        forwardParents[to] = fPolled.vertex
                        val potential = potential(to, heuristicToTarget, heuristicToSource)
                        val priority = (2L * shorterDist) + potential
                        forwardQueue.add(QueueItem(priority, shorterDist, to))
                    }
                    if (backwardDist[to] != inf) {
                        val compPath = forwardDist[fPolled.vertex] + weight + backwardDist[to]
                        if (compPath < shortestPath) {
                            shortestPath = compPath
                            sourceEnd = fPolled.vertex
                            targetStart = to
                        }
                    }
                }
            } else {
                val bPolled = backwardQueue.poll()
                val bEdges = adjList[bPolled.vertex]
                for ((to, weight) in bEdges) {
                    val dist = backwardDist[bPolled.vertex] + weight
                    if (backwardDist[to] > dist) {
                        backwardDist[to] = dist
                        backwardParents[to] = bPolled.vertex
                        val potential = potential(to, heuristicToTarget, heuristicToSource)
                        val priority = (2L * dist) - potential
                        backwardQueue.add(QueueItem(priority, dist, to))
                    }
                    if (forwardDist[to] != inf) {
                        val compPath = forwardDist[to] + weight + backwardDist[bPolled.vertex]
                        if (compPath < shortestPath) {
                            shortestPath = compPath
                            sourceEnd = to
                            targetStart = bPolled.vertex
                        }
                    }
                }
            }
        }
    }

    private fun potential(vertex: Int, heuristicToTarget: List<Int>, heuristicToSource: List<Int>): Long {
        return (heuristicToTarget[vertex].toLong() - heuristicToSource[vertex].toLong())
    }

    private fun removeStaleEntries(queue: PriorityQueue<QueueItem>, dist: LongArray) {
        while (queue.isNotEmpty()) {
            val peek = queue.peek()
            if (peek.actualDistance > dist[peek.vertex]) {
                queue.poll()
            } else {
                return
            }
        }
    }

    private fun path(source: Int, target: Int, sourceEnd: Int, targetStart: Int, fParents: IntArray, bParents: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var fStart = sourceEnd
        while (fStart != source) {
            result.add(fStart)
            fStart = fParents[fStart]
        }
        result.add(source)
        result.reverse()
        var bStart = targetStart
        while (bStart != target) {
            result.add(bStart)
            bStart = bParents[bStart]
        }
        return result
    }
}