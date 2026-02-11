package courses.uc.course02dataStructures.module05binarySearchTrees

import courses.uc.course02dataStructures.module05binarySearchTrees.ValidateBstUsingBoundaries.Node
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

/**
 * # Reference:
 *
 * [Local: Validate BST using Boundaries](docs/dataStructures/courses/uc/module05binarySearchTrees/09binarySearchTreeWithStackBoundaries.md)
 *
 * [GitHub: Validate BST using Boundaries](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/49f8fa69ae39c6acd3bd5c0c7bf2a3dcbbda044f/docs/dataStructures/courses/uc/module05binarySearchTrees/09binarySearchTreeWithStackBoundaries.md)
 *
 * # Prerequisites:
 *
 * * [Local: Print BinarySearchTree DFS Traversals](src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)
 * * [GitHub: Print BinarySearchTree DFS Traversal](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f150828e7a09cfea8949def8c0955ca04cb8a4a3/src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)
 *
 * * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/05binarySearchTrees.md)
 * * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/2d175b2a51c2afa9d21f57a15e5400561d270962/docs/dataStructures/courses/uc/module05binarySearchTrees/05binarySearchTrees.md)
 *
 * * [Local: BinarySearchTreeAndStack](docs/dataStructures/courses/uc/module05binarySearchTrees/07binarySearchTreeAndStack.md)
 * * [GitHub: BinarySearchTreeAndStack](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/fb5a7ac7d88eb8973de93b05330de3a3111c2bd0/docs/dataStructures/courses/uc/module05binarySearchTrees/07binarySearchTreeAndStack.md)
 *
 * * [Local: BinarySearchTreeOperations](docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
 * * [GitHub: BinarySearchTreeOperations](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/2d175b2a51c2afa9d21f57a15e5400561d270962/docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
 *
 * [Local: Validate BST using Boundaries](docs/dataStructures/courses/uc/module05binarySearchTrees/09binarySearchTreeWithStackBoundaries.md)
 *
 * [GitHub: Validate BST using Boundaries](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/49f8fa69ae39c6acd3bd5c0c7bf2a3dcbbda044f/docs/dataStructures/courses/uc/module05binarySearchTrees/09binarySearchTreeWithStackBoundaries.md)
 *
 * # Problem: Is it a binary search tree?
 *
 * ## Problem Introduction
 *
 * * In this problem you are going to test whether a binary search tree data structure from some programming
 * language library was implemented correctly.
 * * There is already a program that plays with this data structure by inserting, removing, searching integers in the
 * data structure and outputs the state of the internal binary tree after each operation.
 * * Now you need to test whether the given binary tree is indeed a correct binary search tree.
 * * In other words, you want to ensure that you can search for integers in this binary tree using
 * binary search through the tree, and you will always get correct result: if the integer is in the tree, you will
 * find it, otherwise you will not.
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * You are given a binary tree with integers as its keys.
 * * You need to test whether it is a correct binary search tree.
 * * The definition of the binary search tree is the following:
 *
 * > for any node of the tree, if its key is 𝑥, then for any node in its left subtree its key must be strictly less
 * than 𝑥, and for any node in its right subtree its key must be strictly greater than 𝑥.
 * > In other words, smaller elements are to the left, and bigger elements are to the right.
 * > You need to check whether the given binary tree structure satisfies this condition.
 * > You are guaranteed that the input contains a valid binary tree.
 * > That is, it is a tree, and each node has at most two children.
 *
 * ### Input Format
 *
 * * The first line contains the number of vertices 𝑛.
 * * The vertices of the tree are numbered from 0 to 𝑛 − 1.
 * * Vertex 0 is the root.
 * * The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛−1 in order.
 * * Each of these lines contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔ℎ𝑡𝑖 is the
 * index of the right child of the 𝑖-th vertex.
 * * If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔ℎ𝑡𝑖 (or both) will be equal to −1.
 *
 * ### Constraints
 *
 * * 0 ≤ 𝑛 ≤ 10^5;
 * * −2^31 < 𝑘𝑒𝑦𝑖 < 2^31 − 1;
 * * −1 ≤ 𝑙𝑒𝑓𝑡𝑖, 𝑟𝑖𝑔ℎ𝑡𝑖 ≤ 𝑛 − 1.
 * * It is guaranteed that the input represents a valid binary tree.
 * * In particular, if 𝑙𝑒𝑓𝑡𝑖 != −1 and 𝑟𝑖𝑔ℎ𝑡𝑖 != −1, then 𝑙𝑒𝑓𝑡𝑖 != 𝑟𝑖𝑔ℎ𝑡𝑖.
 * * Also, a vertex cannot be a child of two different vertices.
 * * Also, each vertex is a descendant of the root vertex.
 * * All keys in the input will be different.
 *
 * ### Output Format
 *
 * * If the given binary tree is a correct binary search tree (see the definition in the problem
 * description), output one word “CORRECT” (without quotes).
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
 * * 512MB
 *
 * ## Time Complexity
 *
 * * `O(n)` as we visit each node once.
 *
 * ## Space Complexity
 *
 * * `O(h)` due to the stack we use, where `h` is the height of the tree.
 * * In the worst case, `h = n` for a skewed tree (left side or right side binary search tree).
 * * For the average case, `h = log n`.
 *
 * ## Grader output
 * ```
 * Good job! (Max time used: 0.28/3.00, max memory used: 74067968/2147483648.)
 * ```
 *
 */
class ValidateBstUsingBoundaries {

    data class Node(val key: Long, val left: Int, val right: Int)

    private data class NodeBoundaries(val nodeIndex: Int, val min: Long, val max: Long)

    fun isValidBst(arr: Array<Node>): Boolean {
        if (arr.isEmpty()) return true
        val stack = ArrayDeque<NodeBoundaries>()
        // Initial boundaries for the root node.
        val curr = NodeBoundaries(0, Long.MIN_VALUE, Long.MAX_VALUE)
        stack.push(curr)
        while (stack.isNotEmpty()) {
            // "pop" represents the associated boundaries for the relevant node.
            val pop = stack.pop()
            val node = arr[pop.nodeIndex]
            // The ideal condition for a valid BST is: min < key < max
            // The term "min < key" indicates that the "node.key" belongs to the right child,
            // and it must be strictly greater than the parent (the "min" represents the parent here).
            // Similarly, the term "key < max" indicates that the "node.key" belongs to the left child,
            // and it must be strictly less than the parent (the "max" represents the parent here).
            // It means that if "min >= key" or "key >= max", then it is an invalid BST.
            if (node.key <= pop.min || node.key >= pop.max) {
                return false
            }
            // Pushing the right child.
            // For a right child, the "parent.key" becomes the "min", and the child inherits the "max" from the parent.
            // For "node.right," the "node" is the parent.
            if (node.right != -1) {
                stack.push(NodeBoundaries(node.right, node.key, pop.max))
            }
            // Pushing the left child.
            // For a left child, the "parent.key" becomes the "max," and the child inherits the "min" from the parent.
            // For "node.left," the "node" is the parent.
            if (node.left != -1) {
                stack.push(NodeBoundaries(node.left, pop.min, node.key))
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
    val arr = Array<Node>(total) { Node(0, -1, -1) }
    repeat(total) {
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val key = token.nextToken()?.toLong() ?: 0L
        val left = token.nextToken()?.toInt() ?: -1
        val right = token.nextToken()?.toInt() ?: -1
        arr[it] = Node(key, left, right)
    }
    val solver = ValidateBstUsingBoundaries()
    val result = solver.isValidBst(arr)
    println(if (result) "CORRECT" else "INCORRECT")
}

