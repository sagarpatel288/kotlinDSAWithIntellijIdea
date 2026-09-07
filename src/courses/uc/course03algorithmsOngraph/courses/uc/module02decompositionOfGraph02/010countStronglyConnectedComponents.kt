package courses.uc.course03algorithmsOngraph.courses.uc.module02decompositionOfGraph02

/**
 * # Reference
 *
 * * [Counting strongly connected components](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob
 * /4a055308d936986a21bd751c9120b10bf8d5281c/docs/03graph/courses/uc/module02decompositionOfGraph02/010lectures/040countingStronglyConnectedComponents.md)
 *
 * # Count strongly connected components of a directed graph
 */
class CountStronglyConnectedComponents(val vertices: Int) {
    private val adjacencyList = List(vertices) { mutableListOf<Int>() }

    fun addEdge(a: Int, b: Int) {
        adjacencyList[a].add(b)
    }

    private fun topoSort(): ArrayDeque<Int> {
        val stack = ArrayDeque<Int>()
        val visited = BooleanArray(vertices)
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                dfs(vertex, adjacencyList, visited, stack)
            }
        }
        return stack
    }

    private fun dfs(vertex: Int, adjacencyList: List<MutableList<Int>>, visited: BooleanArray, stack:
    ArrayDeque<Int>? = null) {
        visited[vertex] = true
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (!visited[it]) {
                dfs(it, adjacencyList, visited, stack)
            }
        }
        stack?.addFirst(vertex)
    }

    private fun transposedGraph(): List<MutableList<Int>> {
        val transposedAdjacency = List(vertices) { mutableListOf<Int>()}
        val visited = BooleanArray(vertices)
        for (vertex in adjacencyList.indices) {
            if (!visited[vertex]) {
                val neighbors = adjacencyList[vertex]
                neighbors.forEach {
                    transposedAdjacency[it].add(vertex)
                }
            }
        }
        return transposedAdjacency
    }

    fun countStronglyConnectedComponents(): Int {
        val stack = topoSort()
        val transposedAdjacency = transposedGraph()
        val visited = BooleanArray(vertices)
        var count = 0
        while (stack.isNotEmpty()) {
            val pop = stack.removeFirst()
            if (!visited[pop]) {
                dfs(pop, transposedAdjacency, visited)
                count++
            }
        }
        return count
    }
}

fun main() {
    val countSccs = CountStronglyConnectedComponents(5)
    countSccs.addEdge(0, 1)
    countSccs.addEdge(1, 2)
    countSccs.addEdge(2, 0)
    countSccs.addEdge(0, 3)
    countSccs.addEdge(3, 4)
    println(countSccs.countStronglyConnectedComponents())
}