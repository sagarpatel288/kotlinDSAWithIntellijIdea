package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import courses.uc.course02dataStructures.module05binarySearchTrees.BuildAndTravelBst.Node
import java.util.ArrayDeque

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
 * ### Time Limit
 *
 * | language   	| C 	| C++ 	| Java 	| Python 	| C# 	| Haskell 	| JavaScript 	| Ruby 	| Scala 	|
 * |------------	|---	|-----	|------	|--------	|----	|---------	|------------	|------	|-------	|
 * | time (sec) 	| 2 	| 2   	| 3    	| 10     	| 3  	| 4       	| 10         	| 10   	| 6     	|
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
 * ## Does this solution work only for `BST(Binary Search Tree)`, or any `Binary Tree`?
 *
 * * It works for any binary tree. It doesn't have to strictly be a `BST`.
 *
 * ## Why didn't we use recursion?
 *
 * * It can cause overflow.
 *
 * ## Why recursion cause overflow, but not the manual stack?
 *
 * >---
 * * The call stack is handled by JVM/OS and it has a limited size.
 * * It can handle around 10,000 stacks.
 * * It has to store many more things like call(return) addresses, function metadata, etc.
 * * And the most important point is, it resides (lives, stays) on the stack memory.
 * * The stack memory is small and fixed.
 * * So, it gets filled up very quickly for a deep recursion and we get the `stackOverflow` error.
 * >---
 * * The manual stack resides (lives, stays) on the heap memory.
 * * The heap memory is large and flexible.
 * * We decide and control the size of our manual stack.
 * * We don't store additional data like function metadata, and call(return) addresses.
 *
 * ## Why didn't we use a queue instead of a deque (stack)?
 *
 * * A queue follows FIFO.
 * * No matter what, when, and how we deque an item, we always end up getting the oldest item in the output.
 * * In this way, we cannot mimic any BST-Traversal orders using the queue alone.
 * * ToDO: Improve and Finish.
 *
 * ## Why don't we compare only the immediate left child, the node, and the immediate right child?
 *
 * * The definition of a binary search tree (BST) says that:
 * > The node (root, parent) must be greater than the entire left subtree, and smaller than the entire right subtree.
 * * Clearly, it is not limited to a single immediate child.
 * * We need to compare the entire subtrees.
 *
 * ## What will be the difference in having `nodes` as a class constructor once Vs. each method (function) parameter?
 *
 * * When we have one static `nodes`, and we perform all the operations on it, we make it a class constructor.
 * * In this case, it is a stateful design, where we have a specific `nodes` state (one specific data).
 * * And we perform various operations on this specific `nodes`.
 * * For a different `nodes`, we must create a different object on which we can call various functions.
 * * If different operations use different `nodes`, we may use it as a function parameter.
 * * In this case, it is a stateless design, where each function might use a different `nodes` object.
 * * A single object of this class is enough for different `nodes`, because each function will have its own `nodes`.
 * * We use it as a helper or utility.
 *
 * ## Edge Cases:
 *
 * * Empty nodes (input).
 * * Only one node.
 * * Left skewed binary tree.
 * * Right skewed binary tree.
 *
 * ## Time Complexity
 *
 * * `O(n)` because we visit each node once for each operation.
 * * The stack operations (push and pop) we use take constant time.
 * * So, Printing:
 * * In-Order: `O(n)`
 * * Pre-Order: `O(n)`
 * * Post-Order: `O(n)`
 *
 * ## Space Complexity
 *
 * * `O(h)` where `h` is the height of the tree.
 * * So, the maximum size of the stack we use, will be `O(h)`.
 * * In the worst case, the height can be `n` (Skewed binary tree).
 * * In the best case, the height can be `log n` (Balanced binary tree).
 *
 * ## Coursera's Grader Output
 * ```
 * Good job! (Max time used: 0.68/1.50, max memory used: 129232896/2147483648.)
 * ```
 */
class BuildAndTravelBst {

    data class Node(val key: Int, val leftChildIndex: Int, val rightChildIndex: Int)

    /**
     * **Pre-Order = Parent(Root)-Left-Right**
     * * Start with the root node.
     * * Initially, push the root node to the stack.
     * 1. Pop and add it to the result.
     * 2. If the popped node has a right child, push it to the stack.
     * 3. If the popped node has a left child, push it to the stack.
     * 4. Repeat steps 1 to 3 until the stack is empty.
     */
    fun getPreOrder(nodes: Array<Node>): List<Int> {
        if (nodes.isEmpty()) return emptyList()
        // Can you explain why did we take the type `Int` instead of `Node`?
        // To keep it light.
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
     * **In-Order = Left-Parent(Root)-Right**
     * * Start with the root node.
     * * Set the current node to the root node.
     * 1. If the node is valid, push it to the stack.
     * 2. After push, set the current node to the left child of the pushed node.
     * 3. If the node is invalid (null or -1, etc.), pop and add it to the result.
     * 4. After pop, set the current node to the right child of the popped node.
     * 5. Repeat steps 1 to 4 as long as the current node is valid or the stack is not empty.
     */
    fun getInOrder(nodes: Array<Node>): List<Int> {
        if (nodes.isEmpty()) return emptyList()
        var currentNodeIndex = 0
        val stack = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        // Did you understand these two conditions?
        // We can't just have `stack.isNotEmpty()`, because initially, the stack is empty only!
        // Also, imagine a BST with only two nodes: Root and the right child.
        // After we pop the root node from the stack, the current node index points to the right child.
        // But the stack is empty.
        // So, if we had only one condition: `stack.isNotEmpty()`, we could not have processed the right sub-tree.
        while (currentNodeIndex != -1 || stack.isNotEmpty()) {
            while (currentNodeIndex != -1) {
                stack.addLast(currentNodeIndex)
                val currentNode = nodes[currentNodeIndex]
                currentNodeIndex = currentNode.leftChildIndex
            }
            val poppedNodeIndex = stack.removeLast()
            val poppedNode = nodes[poppedNodeIndex]
            result.add(poppedNode.key)
            currentNodeIndex = poppedNode.rightChildIndex
        }
        return result
    }

    /**
     * **Post-Order-Traversal: Left-Right-Parent(root)**
     * ---
     * > The Trick:
     * > Pre-Order: Parent(Root)-Left-Right
     * > Modified Pre-Order: Parent(Root)-Right-Left
     * > Return the reversed result of the modified pre-order
     * ---
     * * Start with the root node.
     * * Initially, push the root node to the stack.
     * 1. Pop and add it to the result.
     * 2. If the popped node has a left child, push it to the stack.
     * 3. If the popped node has a right child, push it to the stack.
     * 4. Repeat steps 1 to 3 until the stack is empty.
     * 5. Return the reversed result.
     */
    fun getPostOrder(nodes: Array<Node>): List<Int> {
        if (nodes.isEmpty()) return emptyList()
        // Can you explain why did we take the type `Int` instead of `Node`?
        // To keep it light.
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<Int>()
        stack.addLast(0)
        while (stack.isNotEmpty()) {
            val poppedNodeIndex = stack.removeLast()
            val poppedNode = nodes[poppedNodeIndex]
            result.add(poppedNode.key)
            if (poppedNode.leftChildIndex != -1) {
                stack.addLast(poppedNode.leftChildIndex)
            }
            if (poppedNode.rightChildIndex != -1) {
                stack.addLast(poppedNode.rightChildIndex)
            }
        }
        return result.reversed()
    }

    fun onEmptyOrNullInput() {
        repeat(3) {
            println()
        }
    }

    fun printList(list: List<Int>) {
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
        // Notice that we read line every time and then generate the token.
        // Can you explain why?
        val line = reader.readLine()
        if (line == null) {
            traversal.onEmptyOrNullInput()
            return
        }
        val token = StringTokenizer(line)
        val key = token.nextToken().toInt()
        val leftChildIndex = token.nextToken().toInt()
        val rightChildIndex = token.nextToken().toInt()
        nodes[it] = Node(key, leftChildIndex, rightChildIndex)
    }
    traversal.printList(traversal.getInOrder(nodes))
    traversal.printList(traversal.getPreOrder(nodes))
    traversal.printList(traversal.getPostOrder(nodes))
}