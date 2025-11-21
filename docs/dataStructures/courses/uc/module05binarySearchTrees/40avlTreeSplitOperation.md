# Split An AvlTree into Two AvlTrees

<!-- TOC -->
* [Split An AvlTree into Two AvlTrees](#split-an-avltree-into-two-avltrees)
  * [Prerequisites](#prerequisites)
  * [The Problem Statement](#the-problem-statement)
  * [Thought Process](#thought-process)
    * [Understanding the requirements and output](#understanding-the-requirements-and-output-)
    * [Process](#process)
  * [Next](#next)
<!-- TOC -->

## Prerequisites

* [Trees](../module01BasicDataStructures/section03trees/trees.md)
* [Basic Data Structure Questions](../module01BasicDataStructures/questionsOnBasicDataStructures.md)
* [Priority Queues](../module03priorityQueuesHeapsDisjointSets/section01priorityQueuesIntroduction/priorityQueues.md)
* [Binary Heap Trees](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
* [Complete Binary Tree](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
* [Heap Sort](../module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
* [Binary Search Trees](05binarySearchTrees.md)
* [Binary Search Trees: Basic Operations](10binarySearchTreesBSTsBasicOperations.md)
* [AVL Visualization](https://www.cs.usfca.edu/~galles/visualization/AVLtree.html)
* [Abdul Bari Sir: AVL Tree](https://youtu.be/jDM6_TnYIqE?si=ozgBIYnV79pJw8Nc)
* [AVL Insert](20avlTreeInsertOperation.md)
* [AVL Delete](25avlTreeDeleteOperation.md)
* [avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)

## The Problem Statement

* Split the given `AvlTree` from the node of key `x` in such a way that we get two `AVLTrees` where one `AvlTree` gets all the nodes that are less than or equal to the `x`, and the other `AvlTree` gets all the nodes that are greater than `x`.

$$T_1 <= x$$
$$T_2 > x$$

## Thought Process

### Understanding the requirements and output 

* We want to split the given `AvlTree` into two `AvlTrees`.
* So, our final output will be two `AvlTrees`.
* Now, we denote one `AvlTree` as $T_1$ and another as $T_2$.
* $T_1$ should be $T_1 <= x$.
* It means that all the nodes (keys) must be at most `x`.
* We can call $T_1$ the small (light, left) tree. 
* When we say a small tree, the focus is on the key value of any node, and not on the overall height of the tree.
* Similarly, $T_2$ should be $T_2 > x$.
* It means that all the nodes (keys) must be greater than `x`.
* We can call $T_2$ the big (heavy, right) tree.
* When we say a big tree, the focus is on the key value of any node, and not on the overall height of the tree.

### Process

![450avlTreeSplit.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/450avlTreeSplit.svg)

* To group (i.e., segregate, assort, collect, combine, merge, join, connect) all the nodes with their relevant group (i.e. $T_1$ or $T_2$), we start our traversal from the root. 
* When we are at a particular node and we find that $node.key <= x$, we are sure that this `node` belongs to the $T_1$.
  * We also know that the left children of this `node` will also be at most `x`. 
  * So, we know that $node.left <= x$.
  * But, we don't know about the right node and its children.
  * For example, as shown in the given image, we want to split from `60`.
  * We start from `50`.
  * $50 <= 60$. At this point, we are sure that all the left children of the node `50` must be smaller than `60`, because that is the `AvlTree` (or Binary Search Tree) property.
  * So, the entire left subtree of the node `50` is `<= 60`.
  * However, the right child of the node `50` is `60`.
  * And it doesn't mean that all the children, the entire subtree of `60` is greater than `60`.
  * For example, the right subtree of `50` is `60` and it includes many nodes, such as the entire `55` subtree, that is `<= 60`.
  * The point is, when the `node.key <= x`, we are sure that the entire `node.left` subtree must be `<= x`.
  * But, when the `node.key <= x`, we cannot say that the entire `node.right` subtree must be `> x`.

## Next