package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

class Graph(val size: Int) {

    val adjacencyList = List(size) { mutableListOf<Int>() }

    fun addEdges(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }

    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        for ((index, neighbors) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex is $index: Neighbors are: ")
            neighbors.forEach {
                stringBuilder.append(" $it, ")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }
}

fun main() {
    val graph = Graph(5)
    graph.addEdges(0, 1)
    graph.addEdges(1, 2)
    graph.addEdges(1, 3)
    graph.addEdges(2, 3)
    graph.addEdges(2, 4)
    graph.printAdjacencyList()
}