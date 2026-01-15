package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * # Prerequisites/References
 *
 * * [Local: rangeSumSet.md](docs/dataStructures/courses/uc/module05binarySearchTrees/73rangeSumSet.md)
 *
 * * [GitHub: rangeSumSet.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/b6594eeccd88271f5898b9e3eecd1bcb26a6bcc0/docs/dataStructures/courses/uc/module05binarySearchTrees/73rangeSumSet.md)
 *
 * ## Problem
 *
 * ### Problem Introduction
 *
 * * In this problem, your goal is to implement a data structure to store a set of integers and quickly compute range sums.
 *
 * ### Problem Description
 *
 * #### Task
 *
 * * Implement a data structure that stores a set `𝑆` of integers with the following allowed operations:
 * * `add(𝑖)` — add integer `𝑖` into the set `𝑆` (if it was there already, the set doesn’t change).
 * * `del(𝑖)` — remove integer `𝑖` from the set `𝑆` (if there was no such element, nothing happens).
 * * `find(𝑖)` — check whether `𝑖` is in the set `𝑆` or not.
 * * `sum(𝑙, 𝑟)` — output the sum of all elements `𝑣` in `𝑆` such that `𝑙 ≤ 𝑣 ≤ 𝑟`.
 *
 * #### Input Format
 *
 * * Initially, the set `𝑆` is empty.
 * * The `first line` contains `𝑛` — the number of operations.
 * * The `next 𝑛 lines` contain operations.
 * * Each operation is one of the following:
 * * “`+ i`" — which means add some integer (**not 𝑖**, see below) to `𝑆`.
 * * “`- i`" — which means del some integer (**not 𝑖**, see below) from `𝑆`.
 * * “`? i`" — which means find some integer (**not 𝑖**, see below) in `𝑆`.
 * * “`s l r`" — which means compute the sum of all elements of `𝑆` within some range of values (**not from 𝑙 to 𝑟**, see below).
 * * However, to make sure that your solution can work in an online fashion, each request will actually depend on the result of the last sum request.
 * * Denote `𝑀 = 1 000 000 001`.
 * * At any moment, let `𝑥` be the result of the last sum operation, or just `0` if there were no sum operations before.
 * * Then:
 * * “`+ i`" means `add((𝑖 + 𝑥) mod 𝑀)`,
 * * “`- i`" means `del((𝑖 + 𝑥) mod 𝑀)`,
 * * “`? i`" means `find((𝑖 + 𝑥) mod 𝑀)`,
 * * “`s l r`" means `sum((𝑙 + 𝑥) mod 𝑀,(𝑟 + 𝑥) mod 𝑀)`.
 *
 * #### Constraints
 *
 * $1 ≤ 𝑛 ≤ 100 000$
 *
 * $0 ≤ 𝑖 ≤ 10^9$
 *
 * #### Output Format
 *
 * * For each find request, just output “**Found**" or “**Not found**" (without quotes; note that the first letter is capital) depending on whether `(𝑖 + 𝑥) mod 𝑀` is in `𝑆` or not.
 * * For each sum query, output the sum of all the values `𝑣` in `𝑆` such that `((𝑙 + 𝑥) mod 𝑀) ≤ 𝑣 ≤ ((𝑟 + 𝑥) mod 𝑀)` (it is guaranteed that in all the tests `((𝑙 + 𝑥) mod 𝑀) ≤ ((𝑟 + 𝑥) mod 𝑀))`, where `𝑥` is the result of the last sum operation or `0` if there was no previous sum operation.
 *
 * #### Time Limit
 *
 * | Language   	 | C 	 | C++ 	 | Java 	 | Python 	 | C# 	 | Haskell 	 | JavaScript 	 | Ruby 	 | Scala 	 |
 * |--------------|-----|-------|--------|----------|------|-----------|--------------|--------|---------|
 * | Time (sec) 	 | 2 	 | 2   	 | 3    	 | 10     	 | 3  	 | 4       	 | 10         	 | 10   	 | 6     	 |
 *
 * #### Memory Limit
 *
 * 512 MB
 *
 * #### Sample 1
 *
 * ##### Input
 *
 * ```
 * 15
 * ? 1
 * + 1
 * ? 1
 * + 2
 * s 1 2
 * + 1000000000
 * ? 1000000000
 * - 1000000000
 * ? 1000000000
 * s 999999999 1000000000
 * - 2
 * ? 2
 * - 0
 * + 9
 * s 0 9
 * ```
 *
 * ##### Output
 *
 * ```
 * Not found
 * Found
 * 3
 * Found
 * Not found
 * 1
 * Not found
 * 10
 * ```
 *
 * ##### Explanation
 *
 * * For the first 5 queries, `𝑥 = 0`.
 * * For the next 5 queries, `𝑥 = 3`.
 * * For the next 5 queries, `𝑥 = 1`.
 * * The actual list of operations is:
 *
 * ```
 *   find(1)
 *   add(1)
 *   find(1)
 *   add(2)
 *   sum(1, 2) → 3
 *   add(2)
 *   find(2) → Found
 *   del(2)
 *   find(2) → Not found
 *   sum(1, 2) → 1
 *   del(3)
 *   find(3) → Not found
 *   del(1)
 *   add(10)
 *   sum(1, 10) → 10
 * ```
 *
 * * Adding the same element twice doesn’t change the set.
 * * Attempts to remove an element which is not in the set are ignored.
 *
 * #### Sample 2
 *
 * ##### Input
 *
 * ```
 * 5
 * ? 0
 * + 0
 * ? 0
 * - 0
 * ? 0
 * ```
 *
 * ##### Output
 *
 * ```
 * Not found
 * Found
 * Not found
 * ```
 *
 * * First, 0 is not in the set.
 * * Then it is added to the set.
 * * Then it is removed from the set.
 *
 * #### Sample 3
 *
 * ##### Input
 *
 * ```
 * 5
 * + 491572259
 * ? 491572259
 * ? 899375874
 * s 310971296 877523306
 * + 352411209
 * ```
 *
 * ##### Output
 *
 * ```
 * Found
 * Not found
 * 491572259
 * ```
 */
class RangeSumUsingSplayTree {

    /**
     * The "class Node" instead of a "data class Node" is a right choice here.
     * Can you explain why did we use a "class" instead of a "data class" for the "Node"?
     * When should we use a "data class" over a "class" and vice versa?
     * Also explain why the "class Node" is a "private" class.
     */
    private class Node {
        var key: Long = 0L
        var parent: Node? = null
        var leftChild: Node? = null
        var rightChild: Node? = null
        var subtreeSum: Long = key + (leftChild?.subtreeSum ?: 0L) + (rightChild?.subtreeSum ?: 0L)
    }

    private var root: Node? = null

    private data class SplitResult(val left: Node?, val right: Node?)

    private fun rotate(target: Node?) {
        if (target == null) return
        val parent = target.parent ?: return
        val grandParent = parent.parent
        if (parent.leftChild == target) {
            // Rotate right
            parent.leftChild = target.rightChild
            target.rightChild?.parent = parent
            target.rightChild = parent
        } else {
            // Rotate left
            parent.rightChild = target.leftChild
            target.leftChild?.parent = parent
            target.leftChild = parent
        }
        parent.parent = target
        target.parent = grandParent
        if (grandParent == null) root = target
        if (grandParent != null) {
            if (grandParent.leftChild == parent) {
                grandParent.leftChild = target
            } else {
                grandParent.rightChild = target
            }
        }
        update(parent)
        update(target)
    }

    private fun splay(node: Node?): Node? {
        if (node == null || node.parent == null) return null
        while (node.parent != null) {
            val parent = node.parent
            val grandParent = parent?.parent
            if (grandParent == null) {
                rotate(node)
            } else if ((parent.leftChild == node) == (grandParent.leftChild == parent)) {
                rotate(parent)
                rotate(node)
            } else {
                rotate(node)
                rotate(node)
            }
        }
        root = node
        update(node)
        return node
    }

    private fun findAndSplay(key: Long): Node? {
        if (root == null) return null
        var curr = root
        var last = curr
        while (curr != null) {
            last = curr
            curr = when {
                curr.key < key -> {
                    curr.rightChild
                }

                curr.key > key -> {
                    curr.leftChild
                }

                else -> {
                    break
                }
            }
        }
        splay(last)
        root = last
        return last
    }

    /**
     * Do you understand why do we pass [root]?
     * Hint: [add] operation
     * We
     */
    private fun split(root: Node?, splitKey: Long): SplitResult {
        if (root == null) return SplitResult(null, null)
        findAndSplay(splitKey)
        if (root.key < splitKey) {
            val right = root.rightChild
            right?.parent = null
            root.rightChild = null
            update(root)
            return SplitResult(root, right)
        } else {
            val left = root.leftChild
            left?.parent = null
            root.leftChild = null
            update(root)
            return SplitResult(left, root)
        }
    }

    fun add(key: Long) {
        val (left, right) = split(root, key)
        if (right?.key == key) return
        val newNode = Node().apply {
            this.key = key
        }
        newNode.leftChild = left
        left?.parent = newNode
        newNode.rightChild = right
        right?.parent = newNode
        root = newNode
        update(root)
    }

    private fun findMax(root: Node?): Node? {
        if (root == null) return null
        var curr = root
        while (curr?.rightChild != null) {
            curr = curr.rightChild
        }
        return curr
    }

    private fun merge(left: Node?, right: Node?): Node? {
        if (left == null) return right
        if (right == null) return left
        val maxOfLeft = findMax(left)
        val root = splay(maxOfLeft)
        root?.rightChild = right
        right.parent = root
        update(root)
        return root
    }

    fun delete(key: Long): Boolean {
        if (root == null) return false
        val root = findAndSplay(key)
        if (root?.key != key) return false
        val left = root.leftChild
        val right = root.rightChild
        left?.parent = null
        right?.parent = null
        root.leftChild = null
        root.rightChild = null
        merge(left, right)
        return true
    }

    fun rangeSum(start: Long, end: Long): Long {
        if (root == null) return 0L
        val (left, right) = split(root, start)
        val (rightLeft, rightRight) = split(right, end + 1)
        val rangeSum = rightLeft?.subtreeSum ?: 0L
        root = merge(left, merge(rightLeft, rightRight))
        return rangeSum
    }

    private fun update(node: Node?) {
        if (node == null) return
        node.subtreeSum = node.key + (node.leftChild?.subtreeSum ?: 0L) + (node.rightChild?.subtreeSum ?: 0L)
    }

}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val firstLine = reader.readLine()
    if (firstLine == null) return
    val total = firstLine.toInt()
    var rangeSum = 0L

    repeat(total) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val char = token.nextToken()
        when (char) {
            "+" -> {

            }

            "-" -> {

            }

            "?" -> {

            }
        }
    }
}