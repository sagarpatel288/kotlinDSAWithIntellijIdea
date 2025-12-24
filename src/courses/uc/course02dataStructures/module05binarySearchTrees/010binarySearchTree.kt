package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import courses.uc.course02dataStructures.module05binarySearchTrees.BuildAndTravelBst.Node

/**
 * # Prerequisites
 *
 * [Local: binarySearchTrees.md](docs/dataStructures/courses/uc/module05binarySearchTrees/05binarySearchTrees.md)
 * [GitHub: binarySearchTrees.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f08de016ccea24454b55ed81b429634c55e180ca/docs/dataStructures/courses/uc/module05binarySearchTrees/05binarySearchTrees.md)
 *
 * [Local: bstOperations.md](docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
 * [GitHub: bstOperations.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f08de016ccea24454b55ed81b429634c55e180ca/docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
 *
 * [Tushar Roy: Binary Search Tree-BST: Pre-Order Traversal Using A Stack](https://youtu.be/elQcrJrfObg?si=0mJhLS3Z-k05a6O0)
 * [Tushar Roy: Binary Search Tree-BST: In-Order Traversal Using A Stack](https://youtu.be/nzmtCFNae9k?si=6imsFFpH3wHfZzbx)
 * [Tushar Roy: Binary Search Tree-BST: Post-Order Traversal Using A Stack](https://youtu.be/xLQKdq0Ffjg?si=3GOfPN1gVtUu7Daz)
 *
 * # Problem Statement
 *
 * [Local image](assets/images/dataStructures/uc/module05binarySearchTreesBST/05buildBst.png)
 * [GitHub image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a4f1ca3d080af04712c68ac061a80fdaab78789e/assets/images/dataStructures/uc/module05binarySearchTreesBST/1010buildBst.png)
 *
 * ## Binary tree traversals
 *
 * ## Problem Introduction
 *
 * * In this problem you will implement in-order, pre-order and post-order traversals of a binary tree.
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * You are given a rooted binary tree.
 * * Build and output its in-order, pre-order and post-order traversals.
 *
 * ### Input Format
 *
 * * The first line contains the number of vertices 𝑛.
 * * The vertices of the tree are numbered from 0 to 𝑛 − 1. Vertex 0 is the root.
 * * The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛−1 in order.
 * * Each of these lines contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔ℎ𝑡𝑖
 * is the index of the right child of the 𝑖-th vertex.
 * * If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔ℎ𝑡𝑖 (or both) will be equal to −1.
 *
 * ### Constraints
 *
 * * 1 ≤ 𝑛 ≤ 10^5;
 * * 0 ≤ 𝑘𝑒𝑦𝑖 ≤ 109;
 * * −1 ≤ 𝑙𝑒𝑓𝑡𝑖, 𝑟𝑖𝑔ℎ𝑡𝑖 ≤ 𝑛 − 1.
 * * It is guaranteed that the input represents a valid binary tree.
 * * In particular, if 𝑙𝑒𝑓𝑡𝑖 != −1 and 𝑟𝑖𝑔ℎ𝑡𝑖 != −1, then 𝑙𝑒𝑓𝑡𝑖 != 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * Also, a vertex cannot be a child of two different vertices.
 * * Also, each vertex is a descendant of the root vertex.
 *
 * ### Output Format
 *
 * * Print three lines.
 * * The first line should contain the keys of the vertices in the in-order traversal of the tree.
 * * The second line should contain the keys of the vertices in the pre-order traversal of the tree.
 * * The third line should contain the keys of the vertices in the post-order traversal of the tree.
 *
 * ### Memory Limit
 *
 * * 512MB
 *
 * ### Sample 1
 *
 * **Input**
 *
 * ```
 * 5
 * 4 1 2
 * 2 3 4
 * 5 -1 -1
 * 1 -1 -1
 * 3 -1 -1
 * ```
 *
 * **Output**
 *
 * ```
 * 1 2 3 4 5
 * 4 2 1 3 5
 * 1 3 2 5 4
 * ```
 *
 * ### Sample 2
 *
 * **Input**
 *
 * ```
 * 10
 * 0 7 2
 * 10 -1 -1
 * 20 -1 6
 * 30 8 9
 * 40 3 -1
 * 50 -1 -1
 * 60 1 -1
 * 70 5 4
 * 80 -1 -1
 * 90 -1 -1
 * ```
 *
 * **Output**
 *
 * ```
 * 50 70 80 30 90 40 0 20 10 60
 * 0 70 50 40 30 80 90 20 60 10
 * 50 80 90 30 40 70 10 60 20 0
 * ```
 *
 */
class BuildAndTravelBst {

    data class Node(val key: Int, val leftChildIndex: Int, val rightChildIndex: Int)

    /**
     * Pre-Order = Parent(Root)-Left-Right
     */
    fun getPreOrder(nodes: Array<Node>): List<Int> {
        if (nodes.isEmpty()) return emptyList()
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<Int>()
        stack.addLast(0)
        while (stack.isNotEmpty()) {
            val currNodeIndex = stack.removeLast()
            val currNode = nodes[currNodeIndex]
            result.add(currNode.key)
            if (currNode.rightChildIndex != -1) {
                stack.addLast(currNode.rightChildIndex)
            }
            if (currNode.leftChildIndex != -1) {
                stack.addLast(currNode.leftChildIndex)
            }
        }
        return result
    }

    /**
     * In-Order = Left-Parent(Root)-Right
     */
    fun getInOrder(nodes: Array<Node>): List<Int> {
        if (nodes.isEmpty()) return emptyList()
        var currentNodeIndex = 0
        val stack = ArrayDeque<Node>()
        val result = mutableListOf<Int>()
        while (stack.isNotEmpty()) {
            while (currentNodeIndex != -1) {
                val currentNode = nodes[currentNodeIndex]
                stack.addLast(currentNode)
                currentNodeIndex = currentNode.leftChildIndex
            }
            val poppedNode = stack.removeLast()
            result.add(poppedNode.key)
            currentNodeIndex = poppedNode.rightChildIndex
        }
        return result
    }

    fun onEmptyOrNullInput() {
        repeat(3) {
            println()
        }
    }

    fun printList(list: List<Int>) {
        if (list.isEmpty()) println("The list is empty!")
        println(list.joinToString(" "))
    }

}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val firstLine = reader.readLine()
    val traversal = BuildAndTravelBst()
    if (firstLine == null) {
        traversal.onEmptyOrNullInput()
        return
    }
    val total = firstLine.toInt()
    if (total == 0) {
        traversal.onEmptyOrNullInput()
        return
    }
    val nodes = Array<Node>(total) { Node(0, -1, -1) }
    repeat(total) {
        val line = reader.readLine()
        if (line == null) {
            traversal.onEmptyOrNullInput()
        }
        val token = StringTokenizer(line)
        val key = token.nextToken().toInt()
        val leftChildIndex = token.nextToken().toInt()
        val rightChildIndex = token.nextToken().toInt()
        nodes[it] = Node(key, leftChildIndex, rightChildIndex)
    }
    traversal.printList(traversal.getPreOrder(nodes))
}