package courses.uc.course02dataStructures.module05binarySearchTrees

import courses.uc.course02dataStructures.module05binarySearchTrees.ValidateBstUsingInOrder.Node
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

/**
 * ## Grader output
 * ```
 * Good job! (Max time used: 0.29/3.00, max memory used: 74924032/2147483648.)
 * ```
 */
class ValidateBstUsingInOrder {

    data class Node(val key: Long, val left: Int, val right: Int)

    private data class NodeBoundaries(val nodeIndex: Int, val min: Long, val max: Long)

    fun isValidBstUsingInOrder(arr: Array<Node>): Boolean {
        if (arr.isEmpty()) return true
        val stack = ArrayDeque<NodeBoundaries>()
        var curr: NodeBoundaries? = NodeBoundaries(0, Long.MIN_VALUE, Long.MAX_VALUE)
        while (curr != null || stack.isNotEmpty()) {
            while (curr != null) {
                stack.push(curr)
                val node = arr[curr.nodeIndex]
                curr = if (node.left != -1) {
                    NodeBoundaries(node.left, curr.min, node.key)
                } else {
                    // This is the signal that we can't go left anymore, and it helps exit the loop.
                    null
                }
            }
            val boundaries = stack.pop()
            val node = arr[boundaries.nodeIndex]
            if (node.key < boundaries.min || node.key >= boundaries.max) {
                return false
            }
            curr = if (node.right != -1) {
                NodeBoundaries(node.right, node.key, boundaries.max)
            } else {
                // This is the signal that there is no right child, and it helps pop the next element from the stack.
                null
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
    val array = Array(total) { Node(0, -1, -1) }
    repeat(total) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val key = token.nextToken()?.toLong() ?: 0L
        val left = token.nextToken()?.toInt() ?: -1
        val right = token.nextToken()?.toInt() ?: -1
        array[it] = Node(key, left, right)
    }
    val solver = ValidateBstUsingInOrder()
    val result = solver.isValidBstUsingInOrder(array)
    println(if (result) "CORRECT" else "INCORRECT")
}