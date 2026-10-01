package courses.uc.course03algorithmsOngraph.courses.uc.module05minimumSpanningTrees

/**
 * # Kruskal's Algorithm to find the minimum spanning tree cost
 *
 * * Reference
 * * [Kruskal's Algorithm](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/92fc44853d417f261061cd1ea15255170c5bffd8/docs/03graph/courses/uc/module05minimumSpanningTrees/010lectures/020kruskalsAlgorithmToFindMst.md)
 *
 */
class Dsu(val total: Int) {
    private val parent = IntArray(total) { it }
    private val rank = IntArray(total) { 0 }

    fun find(a: Int): Int {
        require(a in 0..<total) {
            "Size is $total, but argument is $a"
        }
        if (parent[a] == a) return a
        parent[a] = find(parent[a])
        return parent[a]
    }

    fun union(a: Int, b: Int): Boolean {
        require(a in 0..<total && b in 0..<total) {
            "Size is $total, but arguments are: $a and $b"
        }
        val aRoot = find(a)
        val bRoot = find(b)
        if (aRoot == bRoot) return false
        val aRank = rank[aRoot]
        val bRank = rank[bRoot]
        if (aRank > bRank) {
            parent[bRoot] = aRoot
        } else if (bRank > aRank) {
            parent[aRoot] = bRoot
        } else {
            parent[bRoot] = aRoot
            rank[aRoot]++
        }
        return true
    }
}

data class Edge(val from: Int, val to: Int, val weight: Int)

class KruskalAlgorithm(val totalVertices: Int) {

    private val edges = mutableListOf<Edge>()

    fun addEdge(from: Int, to: Int, weight: Int) {
        edges.add(Edge(from, to, weight))
    }

    fun minSpanTree(): Long {
        val dsu = Dsu(totalVertices)
        val sortedEdges = edges.sortedBy { it.weight }
        var cost = 0L
        var selectedEdges = 0
        for ((from, to, weight) in sortedEdges) {
            val merged = dsu.union(from, to)
            if (merged) {
                cost += weight
                selectedEdges++
            }
            if (selectedEdges == (totalVertices - 1)) {
                break
            }
        }
        // Consider a disconnected graph
        return if (selectedEdges == (totalVertices - 1)) {
            cost
        } else {
            -1
        }
    }
}

fun main() {
    val (totalVertices, totalEdges) = readln().split(" ").map { it.toInt() }
    val minSpanTree = KruskalAlgorithm(totalVertices)
    repeat(totalEdges) {
        val (from, to, weight) = readln().split(" ").map { it.toInt() }
        minSpanTree.addEdge(from, to, weight)
    }
    println(minSpanTree.minSpanTree())
}

/*
* Test input
4 5
0 1 10
0 2 6
0 3 5
1 3 15
2 3 4
* Output
19
* */