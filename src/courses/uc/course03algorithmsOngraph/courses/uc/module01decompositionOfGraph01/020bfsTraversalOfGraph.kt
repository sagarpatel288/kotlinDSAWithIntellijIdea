package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class BfsTraversalInGraph(val size: Int) {
    val adjacencyList = List(size) { mutableListOf<Int>() }

    fun addEdges(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        for ((index, neighborList) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex: $index : Neighbors: ")
            for (neighbor in neighborList) {
                stringBuilder.append(" $neighbor")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }

    fun bfsTraversal() {
        val stringBuilder = StringBuilder()
        val visited = BooleanArray(size) { false }
        val queue = ArrayDeque<Int>()
        queue.addLast(adjacencyList[0][0])
        visited[adjacencyList[0][0]] = true
        while (queue.isNotEmpty()) {
            val pop = queue.removeFirst()
            stringBuilder.append("$pop ")
            val neighborList = adjacencyList[pop]
            for (neighbor in neighborList) {
                if (!visited[neighbor]) {
                    queue.addLast(neighbor)
                    visited[neighbor] = true
                }
            }
        }
        println(stringBuilder)
    }
}

fun main() {
    val graph = BfsTraversalInGraph(5)
    graph.addEdges(0, 1)
    graph.addEdges(1, 2)
    graph.addEdges(1, 3)
    graph.addEdges(2, 3)
    graph.addEdges(2, 4)
    graph.printAdjacencyList()
    graph.bfsTraversal()
}