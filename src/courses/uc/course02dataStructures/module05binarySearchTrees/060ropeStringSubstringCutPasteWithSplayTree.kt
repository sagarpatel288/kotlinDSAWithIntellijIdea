package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer


/**
 * # References:
 * [Local 77ropeStringSubstringCutPaste.md](docs/dataStructures/courses/uc/module05binarySearchTrees/77ropeStringSubstringCutPaste.md)
 *
 * [GitHub 77ropeStringSubstringCutPaste.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7d7068b771370be9b44c6a135ce1ce6257b9c25f/docs/dataStructures/courses/uc/module05binarySearchTrees/77ropeStringSubstringCutPaste.md)
 *
 * ## Problem Introduction
 *
 * * In this problem you will implement Rope — data structure that can store a string and efficiently cut a part
 * (a substring) of this string and insert it in a different position.
 * * This data structure can be enhanced to become persistent — that is, to allow access to the previous versions
 * of the string.
 * * These properties make it a suitable choice for storing the text in text editors.
 * * This is a very advanced problem, harder than all the previous advanced problems in this course.
 * * Don’t be upset if it doesn’t crack.
 * * Congratulations to all the learners who are able to successfully pass this problem!
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * You are given a string `𝑆` and you have to process `𝑛` queries.
 * * Each query is described by three integers `𝑖, 𝑗, 𝑘` and means to cut substring `𝑆[𝑖 ..𝑗]` (`𝑖` and `𝑗` are `0-based`)
 * from the string and then insert it after the `𝑘-th` symbol of the remaining string
 * (**if the symbols are numbered from 1**).
 * * If `𝑘 = 0`, `𝑆[𝑖 ..𝑗]` is inserted in the beginning.
 * * See the examples for further clarification.
 *
 * ### Input Format
 *
 * * The first line contains the initial string `𝑆`.
 * * The second line contains the number of queries `𝑞`.
 * * Next `𝑞` lines contain triples of integers `𝑖, 𝑗, 𝑘`.
 *
 * ### Constraints
 *
 * * `𝑆` contains only lowercase English letters.
 * * 1 ≤ |𝑆| ≤ 300000;
 * * 1 ≤ 𝑞 ≤ 100000;
 * * 0 ≤ 𝑖 ≤ 𝑗 ≤ 𝑛 − 1;
 * * 0 ≤ 𝑘 ≤ 𝑛 − (𝑗 − 𝑖 + 1).
 *
 * ### Output Format
 *
 * * Output the string after all `𝑞` queries.
 *
 * ### Time Limits
 *
 * | Language   | C | C++ | Java | Python | C#  | Haskell | JavaScript | Ruby | Scala |
 * |------------|---|-----|------|--------|-----|---------|------------|------|-------|
 * | Time (Sec) | 3 | 3   | 6    | 120    | 4.5 | 6       | 120        | 120  | 12    |
 *
 *
 * ### Memory Limit
 *
 * * 512MB
 *
 * ### Sample 1.
 *
 * #### Input
 *
 * ```
 * hlelowrold
 * 2
 * 1 1 2
 * 6 6 7
 * ```
 *
 * #### Output
 *
 * ```
 * helloworld
 * ```
 *
 * #### Explanation
 *
 * ```
 * ℎ𝑙𝑒𝑙𝑜𝑤𝑟𝑜𝑙𝑑 → ℎ𝑒𝑙𝑙𝑜𝑤𝑟𝑜𝑙𝑑 → ℎ𝑒𝑙𝑙𝑜𝑤𝑜𝑟𝑙𝑑
 * ```
 *
 * * When `𝑖 = 𝑗 = 1, 𝑆[𝑖 ..𝑗] = 𝑙`, and it is inserted **after the** `2-nd symbol` of the **remaining string** `ℎ𝑒𝑙𝑜𝑤𝑟𝑜𝑙𝑑`,
 * which gives `ℎ𝑒𝑙𝑙𝑜𝑤𝑟𝑜𝑙𝑑`.
 * * Then, `𝑖 = 𝑗 = 6`, so `𝑆[𝑖 ..𝑗] = 𝑟`, and it is inserted **after** the `7-th symbol` of the remaining string `ℎ𝑒𝑙𝑙𝑜𝑤𝑜𝑙𝑑`,
 * which gives `ℎ𝑒𝑙𝑙𝑜𝑤𝑜𝑟𝑙𝑑`.
 *
 * ### Sample 2.
 *
 * #### Input
 *
 * ```
 * abcdef
 * 2
 * 0 1 1
 * 4 5 0
 * ```
 *
 * #### Output:
 *
 * ```
 * efcabd
 * ```
 *
 * #### Explanation
 *
 * ```
 * 𝑎𝑏𝑐𝑑𝑒𝑓 → 𝑐𝑎𝑏𝑑𝑒𝑓 → 𝑒𝑓 𝑐𝑎𝑏d
 * ```
 *
 * ## TL;DR
 *
 * * A string `S` of length `n` is given.
 * * There are `q` queries.
 * * Each query has three integers: `i`, `j`, and `k`.
 * * `i` and `j` represent the start and end indices of a substring of `S` respectively.
 * * Cut the substring.
 * * Paste the substring at the `k-th` position.
 * * `1 <= |S| <= 300000`
 * * `1 <= q <= 100000`
 * * `0 ≤ 𝑖 ≤ 𝑗 ≤ 𝑛 − 1`
 * * `0 ≤ 𝑘 ≤ 𝑛 − (𝑗 − 𝑖 + 1)`
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
        // Common mistake: Don't forget to assign the result as: "node.left"
        node.left = buildBst(input, start, mid - 1)
        // Common mistake: Don't forget to update the "parent" pointer of the "left child."
        node.left?.parent = node
        node.right = buildBst(input, mid + 1, end)
        // Common mistake: Don't forget to update the "parent" pointer of the "right child."
        node.right?.parent = node
        // Common mistake: Don't forget to "update" the "node" as it has got two children (left and right).
        update(node)
        return node
    }

    private fun rotate(target: Node?) {
        if (target == null) return
        val parent = target.parent ?: return
        val grandParent = parent.parent
        // Visualize the right and left rotations.
        // Refer: https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7d7068b771370be9b44c6a135ce1ce6257b9c25f/docs/dataStructures/courses/uc/module05binarySearchTrees/77ropeStringSubstringCutPaste.md
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
        // Remember the order: Parent (Now a child of the target) --> Target (Now the parent of its old parent!).
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
                // Remember: When grandparent is null, rotate target once.
                rotate(target)
            } else if ((grandParent.left == parent) == (parent.left == target)) {
                // Remember: When both parent and the target are in the same side: Rotate parent, and then the target.
                rotate(parent)
                rotate(target)
            } else {
                // Remember: Otherwise (when both parent and target are on the different sides):
                // Rotate the target two times.
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
            // 1. Remember this formula (relationship).
            // 2. Common mistake: Using "root" instead of "curr"!
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
                    // Remember this formula (relationship, equation, step).
                    k -= (leftSize + 1)
                }
            }
        }
        return curr
    }

    /**
     * # Do you understand what does the [splitKeyAsCount] convey here?
     * ---
     * * It represents the number of nodes on the left side (left subtree).
     */
    private fun split(root: Node?, splitKeyAsCount: Int): SplitResult {
        if (root == null) return SplitResult(null, null)
        // Do you understand this?
        // If the `splitKeyAsCount` is `0`, it means we want `0` node on the left side (left subtree).
        // Having `0` node on the left side (left subtree) means that the left subtree does not have any node.
        // So, the left subtree is `null`.
        if (splitKeyAsCount == 0) return SplitResult(null, root)
        // If the `splitKeyAsCount` is `root.size`, it means we want all the nodes on the left side (left subtree).
        // It means there is no node on the right side (right subtree).
        // It means that the right subtree is empty.
        // It means that the right subtree does not have any node.
        // It means that the right subtree is `null`.
        if (splitKeyAsCount.toLong() == root.size) return SplitResult(root, null)
        // Common mistake: Don't forget to first "findByIndex" and "splay" before we "split".
        val target = findByIndex(root, splitKeyAsCount)
        val newRoot = splay(target)
        val left = newRoot?.left
        left?.parent = null
        newRoot?.left = null
        // Common mistake: Don't forget to update the "newRoot," because the "newRoot" has just lost the left subtree!
        update(newRoot)
        return SplitResult(left, newRoot)
    }

    private fun findMax(root: Node?): Node? {
        if (root == null) return null
        var curr = root
        // Common mistake: It is "curr?.right != null," and not "curr != null"
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
        // Something to understand and visualize:
        // Refer: https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7d7068b771370be9b44c6a135ce1ce6257b9c25f/docs/dataStructures/courses/uc/module05binarySearchTrees/77ropeStringSubstringCutPaste.md
        // Do you understand this sequence of multiple "split" and "merge" operations?
        val (a, bc) = split(root, start)
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
        // Common mistake: Remember: There are two conditions!
        // Remember: Do you understand these conditions?
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
        // Common mistake: We read the line and take the token inside the "repeat" loop.
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val i = token.nextToken().toInt()
        val j = token.nextToken().toInt()
        val k = token.nextToken().toInt()
        root = solver.cutAndPaste(root, i, j, k)

    }
    val result = solver.inorderTraversal(root)
    println(result)
}