# Flip (replace) using an AVLTree

<!-- TOC -->
* [Flip (replace) using an AVLTree](#flip-replace-using-an-avltree)
  * [Prerequisites / References](#prerequisites--references)
  * [Problem Statement](#problem-statement)
  * [Naive approach](#naive-approach)
  * [Using Two AVL Trees](#using-two-avl-trees)
  * [Next](#next)
<!-- TOC -->

## Prerequisites / References


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
* [AVL Split](40avlTreeSplitOperation.md)
* [Find Kth Smallest Using An AVLTree](50avlTreeFindKthSmallKey.md)
* [avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/020avlTreeImplementation.kt)

## Problem Statement

![550flipReplaceWithAvlTree.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/550flipReplaceWithAvlTree.svg)

* For a given list, flip (replace) the value (in this case, color) from the given index (till the end of the list).

## Naive approach

* Suppose we use an array.
* We travel and check if we are at the start-flip index.
* Once we reach the start-flip index, we would check the current color and replace it with the other color.
* This approach takes `O(n)` linear time.
* Now, we will see how we can improve this.

## Using Two AVL Trees

* The idea is that we can represent a list using an `AVL Tree` as below:

![560arrayAsAnAvlTree.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/560arrayAsAnAvlTree.svg)

* Now, we take another flipped version of the original `AVL Tree` as below:

![570flipUsingTwoAvlTrees.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/570flipUsingTwoAvlTrees.svg)

* Then, we split both the trees from the given start-flip index.
* So, $T_1$ becomes $L_1$ and $R_1$.
* And $T_2$ becomes $L_2$ and $R_2$.
* And then, we merge $L_1$ and $R_2$.
* And then, we merge $L_2$ and $R_1$.
* So, it becomes:

![580flipUsingTwoAvlTreesSplitMerge.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/580flipUsingTwoAvlTreesSplitMerge.svg)

## Next

* [Splay Trees](70splayTrees.md)
* [Red-Black Trees](80redBlackTrees.md)