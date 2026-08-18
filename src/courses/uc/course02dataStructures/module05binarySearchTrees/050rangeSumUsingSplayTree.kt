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
 *
 * ## Caution
 *
 * * Take care of when to use the global [root] and when to not use it!
 *
 * ## Time Complexity
 *
 * * Amortized: `O(log n)` per operation
 *
 * ## Space Complexity
 *
 * * The tree contains `O(n)` nodes
 *
 * ## Grader Output
 *
 * ```
 * Good job! (Max time used: 0.71/1.50, max memory used: 85323776/2147483648.)
 * ```
 *
 *
 */
class RangeSumUsingSplayTree {

    /**
     * The "class Node" instead of a "data class Node" is a right choice here.
     * Can you explain why did we use a "class" instead of a "data class" for the "Node"?
     * When should we use a "data class" over a "class" and vice versa?
     * Also explain why the "class Node" is a "private" class.
     * ---
     * * We use a "data class" for an immutable object, such as data holders like DTO, API response, etc.
     * * If we use a "data class" here with all the "mutable variables", then it is a "normal class" with hidden costs.
     * * The hidden cost is circular dependency.
     * * For example, the `toString` function would create a circular dependency for "child -> parent -> child".
     * * It can cause "StackOverflow" error.
     * * The "data class" can also create the "identify Vs. equality" issue here.
     * * Two nodes having the same "key" or "subtreeSum" are not equal.
     * * The "data class" compares values for the equality, whereas a normal class compares the "memory reference".
     * ---
     * * The "Node" class is a "private class" because public APIs like [add], [delete], [find], [rangeSum], etc. do not
     * need to access and accidentally modify the internal implementation of this [Node] class.
     * * The public APIs are like customers.
     * * They are interested in the service, the end result.
     * * How we provide the end result, the internal system and process, the engine is not the business of consumers.
     * ---
     * **Do you understand why do we treat [key] as a class property instead of a class constructor parameter?**
     * * Hint: [add]
     * ---
     */
    private class Node(val key: Long) {
        var parent: Node? = null
        var leftChild: Node? = null
        var rightChild: Node? = null

        // Do you understand why don't we apply the "update" formula here?
        // Because that can create a false impression that the "subtreeSum" will always give us the correct value.
        // However, the truth is, we need to update it manually, every time we change the pointers of the node.
        // Having initial value without formula implies that we need to manually "update" it when required.
        // Also, a single, isolated `node` is a `subtree` itself.
        // Hence, the initial value of the `subtreeSum` is equal to the `key`.
        var subtreeSum: Long = key
    }

    private var root: Node? = null

    private data class SplitResult(val left: Node?, val right: Node?)

    /**
     * * The [rotate] function depends on the definition of the [target] that we pass.
     * * We can pass the target node that we want to promote (move up),
     * or we can pass the parent or grandparent node that we want to rotate.
     * * The logic will change accordingly, depending upon the definition of the argument.
     * * Currently, this [rotate] function expects the node that we want to promote (move up).
     * * Normally, we prefer this "promote" mental model for the splay tree.
     */
    private fun rotate(target: Node?) {
        if (target == null) return
        // If the parent is null, it means that the target is already the root.
        // In that case, we don't need to perform any rotation/s.
        // So, when the parent is null, we safely abort and return.
        val parent = target.parent ?: return
        val grandParent = parent.parent
        if (parent.leftChild == target) {
            // If the target is the left child, then we rotate the parent to the right side.
            // Rotate right
            // Rotating the right side changes the right child of the target.
            // It becomes a left child of the parent.
            // How to remember?
            // "Rotate right" means right child of the target becomes the left child of the parent.
            // And then we update the parent of that child.
            // And then parent takes that empty space (place, replaces) as a child for the target.
            parent.leftChild = target.rightChild
            // Then we also change its parent.
            // We also change the parent pointer of the target's child, because it has got a new parent.
            target.rightChild?.parent = parent
            // And ultimately, the old parent becomes the child of the target node that we want to promote.
            // In other words, the parent replaces the child of the target.
            target.rightChild = parent
        } else {
            // If the target is a right child, we rotate the parent to the left side.
            // Rotate left
            // Rotating the parent to the left side takes the left child of the target and attaches it as a right child.
            // How to remember?
            // "Rotate left" means left child of the target becomes the right child of the parent.
            // And then we update the parent of that child.
            // And then parent takes that empty space (place, replaces) as a child for the target.
            parent.rightChild = target.leftChild
            target.leftChild?.parent = parent
            // And ultimately, the old parent becomes the left child of the target.
            // In other words, the parent replaces the child of the target.
            target.leftChild = parent
        }
        // In any case, the target becomes the parent of its old parent
        parent.parent = target
        // And the original grandparent becomes the parent of the target
        target.parent = grandParent
        // But if the grandparent was null, target is the root
        if (grandParent == null) root = target
        // Otherwise, the target replaces the old parent
        if (grandParent != null) {
            if (grandParent.leftChild == parent) {
                grandParent.leftChild = target
            } else {
                grandParent.rightChild = target
            }
        }
        // First, update the child (the old parent is now a child of the target)
        update(parent)
        // Then, update the target (the target is now a parent)
        update(target)
    }

    /**
     * * This [rotateLeft] function is to show how the expected definition changes the rotation logic.
     * * Here, we expect that the [node] is the parent node that we want to downgrade.
     * * Ultimately, downgrading the parent upgrades (pulls/moves up) the child node.
     * * However, normally we prefer the promote mental model for the splay tree rotations, like [rotate].
     */
    private fun rotateLeft(node: Node) {
        val parent = node.parent
        // This is the safety net.
        // If the caller accidentally pass the "child" instead of the "parent,"
        // we want to ensure that we don't assign a null value to the root.
        // For example, suppose `node.parent = null`.
        // It means that `node.parent` is the root.
        // Now, suppose that `node` does not have any child.
        // So, `node.rightChild` will be null.
        // Now, eventually, the code performs: `node.parent = child`.
        // Given that, `node.parent` is `root` and `child` is null, we end up with:
        // `root = null`, something that we must avoid.
        // Otherwise, it would destroy the tree!
        val child = requireNotNull(node.rightChild) {
            "Demoting the $node via rotating it to the left side requires a right child!"
        }
        val gc = child.leftChild
        node.rightChild = gc
        child.leftChild = node
        gc?.parent = node
        node.parent = child
        if (parent == null) {
            root = child
        } else if (parent.leftChild == node) {
            parent.leftChild = child
        } else if (parent.rightChild == node) {
            parent.rightChild = child
        }
        child.parent = parent
        update(node)
        update(child)
    }

    /**
     * * Same as [rotateLeft], the expected definition of the [node] is `parent` here.
     * * We downgrade the parent to upgrade the target child.
     * * However, normally we prefer the promote mental model for the splay tree rotations, like [rotate].
     */
    private fun rotateRight(node: Node) {
        val parent = node.parent
        val child = requireNotNull(node.leftChild) {
            "Demoting the $node via rotating it to the right side requires a left child!"
        }
        val gc = child.rightChild
        node.leftChild = gc
        child.rightChild = node
        gc?.parent = node
        node.parent = child
        if (parent == null) {
            root = child
        } else if (parent.leftChild == node) {
            parent.leftChild = child
        } else if (parent.rightChild == node) {
            parent.rightChild = child
        }
        child.parent = parent
        update(node)
        update(child)
    }

    /**
     * Should [update] be a responsibility of this [splay] function to avoid manual calls?
     * On one hand, it seems like an "error-proof" design where we get rid of the case where a developer might forget
     * to call the [update] function manually after every a particular operation.
     * On the other hand, it seems like breaking the single responsibility principle.
     * So, what should be the better decision and why?
     * ---
     * * The [update] function is the integrated part of the [splay] and [rotate] functions.
     * * Because, the process of the [splay] and [rotate] functions should maintain the invariant (rules) and metadata.
     * * For example, the binary search tree invariant and the metadata like [subtreeSum], and various pointers.
     * * [splay] and [rotate] operations change the metadata.
     * * So, it is also their responsibility to maintain the same.
     * ---
     * **Why do we return `Node`?**
     * ---
     * * We return the `Node` that was `splayed`.
     * ---
     */
    private fun splay(node: Node?): Node? {
        if (node == null) return null
        while (node.parent != null) {
            val parent = node.parent
            val grandParent = parent?.parent
            if (grandParent == null) {
                // Promote the target node
                rotate(node)
                // Are these two relationship the same?
                // A = node is a left child of the parent.
                // B = parent is a left child of the grandparent.
                // And then, we have A == B.
                // Now, if A is true and B is also true, then A == B.
                // If A is false and B is also false, then also A == B.
                // So, we cover two conditions in one conditional statement!
                // If both the parent and the child are on the same side,
                // we first promote the parent followed by the node.
                // The remaining condition is covered by the last conditional statement!
            } else if ((parent.leftChild == node) == (grandParent.leftChild == parent)) {
                // First, promote the parent
                // And then, promote the target node
                rotate(parent)
                rotate(node)
            } else {
                // If the parent and node are on the opposite side, we promote the node two times.
                // Promote the target node two times consecutively/subsequently.
                rotate(node)
                rotate(node)
            }
        }
        root = node
        update(root)
        return node
    }

    /**
     * Can you explain why did we have to use a label to break the outer-loop (the while loop) from the inner `when`?
     */
    private fun findAndSplay(root: Node?, key: Long): Node? {
        if (root == null) return null
        var curr = root
        var last = curr
        outerLoop@ while (curr != null) {
            last = curr
            curr = when {
                curr.key < key -> {
                    curr.rightChild
                }

                curr.key > key -> {
                    curr.leftChild
                }

                else -> {
                    break@outerLoop
                }
            }
        }
        val target = curr ?: last
        splay(target)
        return target
    }

    fun find(key: Long): Boolean {
        if (root == null) return false
        root = findAndSplay(root, key) // "splay" calls "rotate", and "rotate" calls "update".
        return root?.key == key
    }

    /**
     * Do you understand why do we pass [root]?
     * Hint: [add] and [delete] operations
     *
     */
    private fun split(root: Node?, splitKey: Long): SplitResult {
        if (root == null) return SplitResult(null, null)
        val partition = findAndSplay(root, splitKey) ?: return SplitResult(null, null)
        if (partition.key < splitKey) {
            // The root key is less than the split key.
            // So, we make it a part of the left subtree.
            // So, we detach only the right subtree.
            // How to remember?
            // We always ensure that the split point remains on the right side.
            val right = partition.rightChild
            right?.parent = null
            partition.rightChild = null
            // We just detached the right subtree.
            // Hence, we need to update the sum/height/size of the root.
            update(partition)
            return SplitResult(partition, right)
        } else {
            // The root key is equal to or greater than the split key.
            // So, we detach the left part.
            // How to remember?
            // We always ensure that the split point remains on the right side.
            val left = partition.leftChild
            left?.parent = null
            partition.leftChild = null
            // After detaching the left subtree, we need to update the sum/height/size of the root.
            update(partition)
            return SplitResult(left, partition)
        }
    }

    fun add(key: Long) {
        val (left, right) = split(root, key)
        // Caution! Possible point of mistake!
        // Don't forget this case: The key already exists!
        // Do you understand why are we sure that if the "key" exist, it must be the root of the "right" subtree?
        if (right?.key == key) {
            // The key already exists in the tree.
            merge(left, right)
            return
        }
        val newNode = Node(key)
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
        val leftRoot = splay(maxOfLeft)
        leftRoot?.rightChild = right
        right.parent = leftRoot
        update(leftRoot)
        return leftRoot
    }

    fun delete(key: Long): Boolean {
        if (root == null) return false
        val target = findAndSplay(root, key)
        if (target?.key != key) return false
        val left = target.leftChild
        val right = target.rightChild
        // Caution! Possible point of mistake!
        // Don't forget to nullify the parent pointers!
        left?.parent = null
        right?.parent = null
        // Caution! Possible point of mistake!
        // Don't forget to nullify the children pointers as well!
        target.leftChild = null
        target.rightChild = null
        root = merge(left, right)
        return true
    }

    fun rangeSum(start: Long, end: Long): Long {
        if (root == null) return 0L
        val (left, right) = split(root, start)
        val (rightLeft, rightRight) = split(right, end + 1)
        val rangeSum = rightLeft?.subtreeSum ?: 0L
        root = merge(left, merge(rightLeft, rightRight))
        update(root)
        return rangeSum
    }

    /**
     * * Why did we use the [update] function instead of using the computational property?
     * * Because if we use the computational property, it will always calculate the sum of the many subtrees.
     * * But if we use the [update] function, we only update the sum of the subject (target) node.
     * * The [update] function reads (and does not calculate) the properties of the left and right subtrees.
     * * But if we use the computational property, it will calculate the properties of the left and right subtrees.
     * * The computational property will increase the cost.
     */
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
    val mod = 1000000001L
    val solver = RangeSumUsingSplayTree()

    repeat(total) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val char = token.nextToken()
        when (char) {
            "+" -> {
                val key = token.nextToken().toLong()
                solver.add((key + rangeSum) % mod)
            }

            "-" -> {
                val key = token.nextToken()?.toLong()
                key?.let {
                    solver.delete((it + rangeSum) % mod)
                }
            }

            "?" -> {
                val key = token.nextToken()?.toLong()
                key?.let {
                    val found = solver.find((key + rangeSum) % mod)
                    println(if (found) "Found" else "Not found")
                }
            }

            "s" -> {
                val start = token.nextToken()?.toLong()
                val end = token.nextToken()?.toLong()
                if (start != null && end != null) {
                    rangeSum = solver.rangeSum((start + rangeSum) % mod, (end + rangeSum) % mod)
                    println(rangeSum)
                }
            }
        }
    }
}