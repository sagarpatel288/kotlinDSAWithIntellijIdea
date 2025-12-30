package courses.uc.course02dataStructures.module05binarySearchTrees

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer
import courses.uc.course02dataStructures.module05binarySearchTrees.ValidateBinarySearchTree.Node

/**
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
 * ## Coursera's Grader Output
 * ```
 * Good job! (Max time used: 0.26/3.00, max memory used: 71426048/2147483648.)
 * ```
 */
class ValidateBinarySearchTree {

    data class Node(val key: Int, val leftChildIndex: Int, val rightChildIndex: Int)

    fun isValidBst(nodes: Array<Node>): Boolean {
        if (nodes.isEmpty()) return true
        var prevKey = Int.MIN_VALUE
        val stack = ArrayDeque<Int>()
        var currentIndex = 0
        while (currentIndex != -1 || stack.isNotEmpty()) {
            while (currentIndex != - 1) {
                stack.push(currentIndex)
                val node = nodes[currentIndex]
                currentIndex = node.leftChildIndex
            }
            val poppedIndex = stack.pop()
            val poppedNode = nodes[poppedIndex]
            // We are doing the `in-order` pop.
            // So, the recently popped node's key must be greater than the previous key.
            if (poppedNode.key <= prevKey) {
                return false
            }
            prevKey = poppedNode.key
            currentIndex = poppedNode.rightChildIndex
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
    if (total == 0) {
        println("CORRECT")
        return
    }
    val nodes = Array<Node>(total) { Node(0, -1, -1) }
    repeat(total) { index ->
        val line = reader.readLine()
        val token = StringTokenizer(line)
        val key = token.nextToken().toInt()
        val leftChildIndex = token.nextToken().toInt()
        val rightChildIndex = token.nextToken().toInt()
        nodes[index] = Node(key, leftChildIndex, rightChildIndex)
    }
    val validater = ValidateBinarySearchTree()
    val result = validater.isValidBst(nodes)
    if (result) {
        println("CORRECT")
    } else {
        println("INCORRECT")
    }
}