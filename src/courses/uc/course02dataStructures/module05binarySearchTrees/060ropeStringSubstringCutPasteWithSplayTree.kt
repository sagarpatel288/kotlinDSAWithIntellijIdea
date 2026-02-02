package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer


/**
 * ## References:
 * [Local 77ropeStringSubstringCutPaste.md]()
 * [GitHub 77ropeStringSubstringCutPaste.md]()
 *
 * ## Grader Output
 * ```
 * Good job! (Max time used: 0.77/4.50, max memory used: 90300416/2147483648.)
 * ```
 */
class RopeStringSubstringCutPaste {

    class Node(val key: Char) {
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = null

        // The default size of a node (one individual node) is 1.
        var size: Long = 1L
    }

    private data class SplitResult(val left: Node?, val right: Node?)

    private fun update(node: Node?) {
        if (node == null) return
        node.size = 1 + (node.left?.size ?: 0) + (node.right?.size ?: 0)
    }

    fun buildBst(input: String?, start: Int, end: Int): Node? {
        if (input == null) return null
        if (start > end) return null
        val mid = start + (end - start) / 2
        val node = Node(input[mid])
        node.left = buildBst(input, start, mid - 1)
        node.left?.parent = node
        node.right = buildBst(input, mid + 1, end)
        node.right?.parent = node
        update(node)
        return node
    }

    private fun rotate(target: Node?) {
        if (target == null) return
        val parent = target.parent ?: return
        val grandParent = parent.parent
        if (parent.left == target) {
            // Right rotation
            val rightChild = target.right
            parent.left = rightChild
            rightChild?.parent = parent
            target.right = parent
        } else {
            // Left rotation
            val leftChild = target.left
            parent.right = leftChild
            leftChild?.parent = parent
            target.left = parent
        }
        parent.parent = target
        target.parent = grandParent
        if (grandParent != null) {
            if (grandParent.left == parent) {
                grandParent.left = target
            } else {
                grandParent.right = target
            }
        }
        // Update the current child (old parent) node first before the current parent (target) node
        update(parent)
        update(target)
    }

    private fun splay(target: Node?): Node? {
        // If the node that we want to splay and make the new root is null, then the existing root stays as it is.
        // So, we return the existing root, because the caller expects the "root" node.
        while (target?.parent != null) {
            val parent = target.parent
            val grandParent = parent?.parent
            if (grandParent == null) {
                rotate(target)
            } else if ((grandParent.left == parent) == (parent.left == target)) {
                rotate(parent)
                rotate(target)
            } else {
                rotate(target)
                rotate(target)
            }
        }
        update(target)
        return target
    }

    private fun findByIndex(root: Node?, kIndex: Int): Node? {
        // If there is no root, there is no other node. The tree is empty or null.
        if (root == null) return null
        var curr = root
        var k = kIndex.toLong()
        whileLoop@ while (curr != null) {
            val leftSize = curr.left?.size ?: 0
            when {
                // Do you understand this? This is the heart formula of the problem.
                k == leftSize -> {
                    return curr
                }
                k < leftSize -> {
                    curr = curr.left
                }
                else -> {
                    // k > leftSize
                    curr = curr.right
                    // Do you understand this?
                    k -= (leftSize + 1)
                }
            }
        }
        return curr
    }

    private fun split(root: Node?, splitKeyAsCount: Int): SplitResult {
        if (root == null) return SplitResult(null, null)
        if (splitKeyAsCount == 0) return SplitResult(null, root)
        if (splitKeyAsCount.toLong() == root.size) return SplitResult(root, null)
        val target = findByIndex(root,splitKeyAsCount)
        val newRoot = splay(target)
        val left = newRoot?.left
        left?.parent = null
        newRoot?.left = null
        // Update because the "newRoot" has just lost the left subtree!
        update(newRoot)
        return SplitResult(left, newRoot)
    }

    private fun findMax(root: Node?): Node? {
        if (root == null) return null
        var curr = root
        while (curr?.right != null) {
            curr = curr.right
        }
        return curr
    }

    private fun merge(left: Node?, right: Node?): Node? {
        if (left == null) return right
        if (right == null) return left
        val leftMax = findMax(left)
        val root = splay(leftMax)
        root?.right = right
        right.parent = root
        // Update "root" because the "root" has got a new subtree!
        update(root)
        return root
    }

    fun cutAndPaste(root: Node?, start: Int, end: Int, kCount: Int): Node? {
        if (root == null) return null
        val (a, bc) = split(root,start)
        val (b, c) = split(bc, end - start + 1)
        val ac = merge(a, c)
        val (before, after) = split(ac, kCount)
        val ab = merge(before, b)
        val abc = merge(ab, after)
        return abc
    }

    fun inorderTraversal(root: Node?): String {
        if (root == null) return ""
        val stringBuilder = StringBuilder()
        val stack = ArrayDeque<Node>()
        var curr = root
        // Do you understand these conditions?
        while (curr != null || stack.isNotEmpty()) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            val pop = stack.pop()
            stringBuilder.append(pop.key)
            curr = pop.right
        }
        return stringBuilder.toString()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val input = reader.readLine()
    if (input.isEmpty()) return
    val totalQueries = reader.readLine()?.toInt() ?: 0
    if (totalQueries <= 0) return
    val solver = RopeStringSubstringCutPaste()
    var root = solver.buildBst(input, 0, input.length - 1)
    repeat(totalQueries) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val i = token.nextToken().toInt()
        val j = token.nextToken().toInt()
        val k = token.nextToken().toInt()
        root = solver.cutAndPaste(root,i, j, k)
        
    }
    val result = solver.inorderTraversal(root)
    println(result)
}