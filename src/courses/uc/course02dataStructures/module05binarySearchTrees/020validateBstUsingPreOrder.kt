package courses.uc.course02dataStructures.module05binarySearchTrees

import courses.uc.course02dataStructures.module05binarySearchTrees.ValidateBinarySearchTreeHavingDuplicateKeys.Node
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

/**
 * # Is it a binary search tree? Hard version.
 *
 * # Problem Introduction
 *
 * * In this problem you are going to solve the same problem as the previous one, but for a more general case,
 * when binary search tree may contain equal keys.
 *
 * # Problem Description
 *
 * ## Task
 *
 * * You are given a binary tree with integers as its keys.
 * * You need to test whether it is a correct binary search tree.
 * * Note that there can be duplicate integers in the tree, and this is allowed.
 * * The definition of the binary search tree in such case is the following:
 * * for any node of the tree, if its key is 𝑥, then for any node in its left subtree its key must be strictly less
 * than 𝑥, and for any node in its right subtree its key must be greater than or equal to 𝑥.
 * * In other words, smaller elements are to the left, bigger elements are to the right, and duplicates are always to
 * the right.
 * * You need to check whether the given binary tree structure satisfies this condition.
 * * You are guaranteed that the input contains a valid binary tree.
 * * That is, it is a tree, and each node has at most two children.
 *
 * ## Input Format
 *
 * * The first line contains the number of vertices 𝑛.
 * * The vertices of the tree are numbered from 0 to 𝑛 − 1.
 * * Vertex 0 is the root.
 * * The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛−1 in order.
 * * Each of these lines contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔ℎ𝑡𝑖 is the
 * index of the right child of the 𝑖-th vertex.
 * If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔ℎ𝑡𝑖 (or both) will be equal to −1.
 *
 * ## Constraints
 *
 * * 0 ≤ 𝑛 ≤ 10^5;
 * * −2^31 ≤ 𝑘𝑒𝑦𝑖 ≤ 2^31 − 1;
 * * −1 ≤ 𝑙𝑒𝑓𝑡𝑖, 𝑟𝑖𝑔ℎ𝑡𝑖 ≤ 𝑛 − 1.
 * * It is guaranteed that the input represents a valid binary tree.
 * * In particular, if 𝑙𝑒𝑓𝑡𝑖 != −1 and 𝑟𝑖𝑔ℎ𝑡𝑖 != −1, then 𝑙𝑒𝑓𝑡𝑖 != 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * Also, a vertex cannot be a child of two different vertices.
 * * Also, each vertex is a descendant of the root vertex.
 * * Note that the minimum and the maximum possible values of the 32-bit integer type are allowed to be keys in the
 * tree — beware of integer overflow!
 *
 * ## Output Format
 *
 * * If the given binary tree is a correct binary search tree (see the definition in the problem description), output
 * one word “CORRECT” (without quotes).
 * * Otherwise, output one word “INCORRECT” (without quotes).
 *
 * ### Time Limit
 *
 * | language   	| C 	| C++ 	| Java 	| Python 	| C# 	| Haskell 	| JavaScript 	| Ruby 	| Scala 	|
 * |------------	|---	|-----	|------	|--------	|----	|---------	|------------	|------	|-------	|
 * | time (sec) 	| 2 	| 2   	| 3    	| 10     	| 3  	| 4       	| 10         	| 10   	| 6     	|
 *
 * ### Memory Limit
 *
 * * 512 MB
 *
 * ### Grader Output
 *
 * ```
 * Good job! (Max time used: 0.31/3.00, max memory used: 74686464/2147483648.)
 * ```
 *
 */
class ValidateBinarySearchTreeHavingDuplicateKeys {

    /**
     * A public data class because we use it while reading, and transforming the input.
     */
    data class Node(val key: Long, val leftChildIndex: Int, val rightChildIndex: Int)

    /**
     * Using the [nodeIndex], we retrieve the [Node] from the input array in the [isValidBst] function.
     * Why a different data class?
     * Because the input gives us [Node] data only.
     * It is our approach to assign the boundaries for each [Node].
     */
    private data class NodeWithBoundaries(val nodeIndex: Int, val min: Long, val max: Long)

    /**
     * Why didn't we use a nullable [nodes] array?
     * Because we are getting this [nodes] array directly from the input.
     * If it was a nullable array, we would not have called this function.
     * If the array is null, it means that we have received either `0` as the total vertices (nodes), or less than `0`.
     * And in both cases, we would have returned `true` or `false`, before even calling this function.
     */
    fun isValidBst(nodes: Array<Node>): Boolean {
        if (nodes.isEmpty()) return true
        val stack = ArrayDeque<NodeWithBoundaries>()
        stack.push(NodeWithBoundaries(0, Long.MIN_VALUE, Long.MAX_VALUE))
        while (stack.isNotEmpty()) {
            val state = stack.pop()
            val node = nodes[state.nodeIndex]
            if (node.key < state.min || node.key >= state.max) return false
            if (node.rightChildIndex != -1) {
                stack.push(
                    NodeWithBoundaries(node.rightChildIndex, node.key, state.max)
                )
            }
            if (node.leftChildIndex != -1) {
                stack.push(
                    NodeWithBoundaries(node.leftChildIndex, state.min, node.key)
                )
            }
        }
        return true
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val firstLine = reader.readLine()
    if (firstLine == null) {
        println("INCORRECT")
        return
    }
    val total = firstLine.toInt()
    if (total < 0) {
        println("INCORRECT")
        return
    }
    if (total == 0) {
        println("CORRECT")
        return
    }
    val nodes = Array<Node>(total) { Node(0L, -1, -1) }
    repeat(total) { index ->
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val key = token.nextToken().toLong()
        val leftChildIndex = token.nextToken().toInt()
        val rightChildIndex = token.nextToken().toInt()
        nodes[index] = Node(key, leftChildIndex, rightChildIndex)
    }
    val solver = ValidateBinarySearchTreeHavingDuplicateKeys()
    if (solver.isValidBst(nodes)) {
        println("CORRECT")
    } else {
        println("INCORRECT")
    }
}