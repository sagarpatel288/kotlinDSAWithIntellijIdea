package courses.uc.course02dataStructures.module05binarySearchTrees

import courses.uc.course02dataStructures.module05binarySearchTrees.ValidateBstUsingPostOrderTraversal.Node
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

/**
 * ## Grader output
 * ```
 * Good job! (Max time used: 0.30/3.00, max memory used: 74555392/2147483648.)
 * ```
 */
class ValidateBstUsingPostOrderTraversal {

    data class Node(val key: Long, val left: Int, val right: Int)

    private data class NodeBoundaries(val nodeIndex: Int, val min: Long, val max: Long)

    fun isValidBst(arr: Array<Node>): Boolean {
        if (arr.isEmpty()) return true
        val stack = ArrayDeque<NodeBoundaries>()
        val curr = NodeBoundaries(0, Long.MIN_VALUE, Long.MAX_VALUE)
        stack.push(curr)
        while (stack.isNotEmpty()) {
            val pop = stack.pop()
            val node = arr[pop.nodeIndex]
            if (node.key < pop.min || node.key >= pop.max) {
                return false
            }
            if (node.left != -1) {
                stack.push(NodeBoundaries(node.left, pop.min, node.key))
            }
            if (node.right != -1) {
                stack.push(NodeBoundaries(node.right, node.key, pop.max))
            }
        }
        return true
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val total = reader.readLine()?.toInt() ?: 0
    if (total < 0) return
    if (total == 0) {
        println("CORRECT")
        return
    }
    val arr = Array(total) { Node(0, -1, -1) }
    repeat(total) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val key = token.nextToken()?.toLong() ?: 0L
        val left = token.nextToken()?.toInt() ?: -1
        val right = token.nextToken()?.toInt() ?: -1
        arr[it] = Node(key, left, right)
    }
    val solver = ValidateBstUsingPostOrderTraversal()
    val result = solver.isValidBst(arr)
    println(if (result) "CORRECT" else "INCORRECT")
}