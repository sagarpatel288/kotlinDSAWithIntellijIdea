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

    /**
     * Why is [root] a global property?
     * Because the purpose of this data structure [RopeStringSubstringCutPaste] is to stimulate rope (string)
     * cut(remove, delete)-and-paste(insert) a substring and provide the final (new, resultant) string.
     * So, it is not the responsibility of the caller to manage the [root] property.
     * The caller is interested in only calling the [buildBst], [cutAndPaste], [inorderTraversal] functions.
     * The caller does not need to manage the [root] property.
     */
    private var root: Node? = null

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
        node.right = buildBst(input, mid + 1, end)
        update(node)
        return node
    }

    private fun rotate(target: Node?): Node? {
        if (target == null) return null
        val parent = target.parent ?: return target
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
        return target
    }

    private fun splay(target: Node?): Node? {
        // If the node that we want to splay and make the new root is null, then the existing root stays as it is.
        // So, we return the existing root, because the caller expects the "root" node.
        if (target == null) return null
        while (target.parent != null) {
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
        println("findByIndex: ${root?.key} size: ${root?.size} :kIndex: ${kIndex}")
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
        println("findByIndex: curr: ${curr?.key}")
        return curr
    }

    private fun split(root: Node?, splitKeyAsCount: Int): SplitResult {
        println("split: root: ${root?.key} size: ${root?.size} splitKeyAsCount: $splitKeyAsCount")
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
        println("split: left: ${left?.key} :right: ${newRoot?.key}")
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
        println("merge: left: ${left?.key} :right: ${right?.key}")
        if (left == null) return right
        if (right == null) return left
        val leftMax = findMax(left)
        val root = splay(leftMax)
        root?.right = right
        right.parent = root
        // Update "root" because the "root" has got a new subtree!
        update(root)
        println("RopeStringSubstringCutPaste: :merge: ${inorderTraversal(root)}")
        return root
    }

    fun cutAndPaste(root: Node?, start: Int, end: Int, kCount: Int): Node? {
        println("root: ${root?.key}")
        if (root == null) return null
        val (a, bc) = split(root,start)
        println("RopeStringSubstringCutPaste: :cutAndPaste: split: a: ${a?.key} ${a?.size} bc: ${bc?.key} ${bc?.size}")
        val (b, c) = split(bc, end - start + 1)
        println("RopeStringSubstringCutPaste: :cutAndPaste: split: b: ${b?.key} ${b?.size} c: ${c?.key} ${c?.size}")
        val ac = merge(a, c)
        println("RopeStringSubstringCutPaste: :cutAndPaste: merge: ac: ${ac?.key} ${ac?.size}")
        val (before, after) = split(ac, kCount)
        println("RopeStringSubstringCutPaste: :cutAndPaste: split: before: ${before?.key} ${before?.size} after: " +
                "${after?.key} ${after?.size}")
        val ab = merge(before, b)
        println("RopeStringSubstringCutPaste: :cutAndPaste: merge: ab: ${ab?.key} ${ab?.size}")
        val abc = merge(ab, after)
        println("RopeStringSubstringCutPaste: :cutAndPaste: merge: abc: ${abc?.key} ${abc?.size}")
        println("cutAndPaste: " + inorderTraversal(abc))
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
        println("RopeStringSubstringCutPaste: :inorderTraversal: ${stringBuilder}")
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
        println("query: $it" + solver.inorderTraversal(root))
    }
    val result = solver.inorderTraversal(root)
    println("result: $result")
}