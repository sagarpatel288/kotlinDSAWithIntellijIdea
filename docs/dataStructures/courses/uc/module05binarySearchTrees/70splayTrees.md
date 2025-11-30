# Splay Trees

<!-- TOC -->
* [Splay Trees](#splay-trees)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Purpose](#purpose)
  * [Terminologies](#terminologies)
    * [Zig rotations](#zig-rotations-)
      * [Zig rotation](#zig-rotation)
      * [Zag rotation](#zag-rotation)
    * [Zig-Zig rotations](#zig-zig-rotations)
      * [Zig-Zig rotations](#zig-zig-rotations-1)
      * [Zag-Zag rotations](#zag-zag-rotations)
    * [Zig-Zag rotations](#zig-zag-rotations)
      * [Zig-Zag rotation](#zig-zag-rotation)
      * [Zag-Zig rotations](#zag-zig-rotations)
  * [Introduction](#introduction)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References

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
* [Flip the values using an AVLTree](60flipReplaceWithAvlTree.md)
* [avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)
* [Splay Tree Introduction By Jenny's Lectures](https://youtu.be/qMmqOHr75b8?si=o84h4uQAOPIwNALb)
* [Splay Tree: Insert Operation By Jenny's Lectures](https://youtu.be/1HeIZNP3w4A?si=s0xuQMVg8OBzpmP8)
* 

## Purpose

* To reduce the time of the search operation.
* We bring the most frequently accessed node near the root.
* So, it gradually takes less time.
* That is the reason we use it for `caches`.

## Terminologies

### Zig rotations 

#### Zig rotation

#### Zag rotation

### Zig-Zig rotations

#### Zig-Zig rotations

#### Zag-Zag rotations

### Zig-Zag rotations

#### Zig-Zag rotation

#### Zag-Zig rotations

## Introduction

* [AVLTrees](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt) are strictly balanced binary search trees.
* Splay trees are roughly balanced binary search trees.
* //ToDo: Elaborate
* In a splay tree, the node on which we perform a search or an insert operation becomes the root of the tree!
* The search or insert operation follows the splay operation.
* It means that after every search or insert operation, we have to rearrange the tree.
* 

## Next

* [Red-Black Trees](80redBlackTrees.md)