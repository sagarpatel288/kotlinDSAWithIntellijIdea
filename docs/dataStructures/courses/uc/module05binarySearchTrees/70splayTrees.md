# Splay Trees

<!-- TOC -->
* [Splay Trees](#splay-trees)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Purpose](#purpose)
  * [Terminologies](#terminologies)
    * [Zig Rotations (Terminal Step)](#zig-rotations-terminal-step)
      * [Zig Rotation (Zig-Right)](#zig-rotation-zig-right)
      * [Zag Rotation (Zig-Left)](#zag-rotation-zig-left)
    * [Zig-Zig Rotations (Homogeneous Rotations)](#zig-zig-rotations-homogeneous-rotations)
      * [Zig-Zig Rotation (Zig-Zig Right)](#zig-zig-rotation-zig-zig-right)
      * [Zag-Zag Rotation (Zig-Zig Left)](#zag-zag-rotation-zig-zig-left)
    * [Zig-Zag Rotations (First-Parent-Then-Grandparent): Heterogeneous Rotations](#zig-zag-rotations-first-parent-then-grandparent-heterogeneous-rotations)
      * [Zig-Zag Rotation](#zig-zag-rotation)
      * [Zag-Zig Rotation](#zag-zig-rotation)
  * [Introduction](#introduction)
  * [Insert](#insert)
  * [Questions-Answers](#questions-answers)
  * [ToDos](#todos)
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

### Zig Rotations (Terminal Step)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

#### Zig Rotation (Zig-Right)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

* Right Rotation.
* It is also known as **Zig-Right Rotation**.
* The node on which we perform the rotation does not have any grandparent.
* It means that the parent of the node is the root.
* So, we perform a single rotation on the node.
* When we rotate a node in the right-side direction (clockwise).

#### Zag Rotation (Zig-Left)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

* Left Rotation.
* It is also known as **Zig-Left Rotation**.
* The node on which we perform the rotation does not have any grandparent.
* So, we perform a single rotation on the node.
* When we rotate a node in the left-side direction (anti-clockwise).

### Zig-Zig Rotations (Homogeneous Rotations)

#### Zig-Zig Rotation (Zig-Zig Right)

![620splayTreesZigZigRightTwice.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/620splayTreesZigZigRightTwice.svg)

* Right-Right Rotation.
* It is also known as **Zig-Zig Right Rotations**.
* The node on which we perform the rotation(s), has grandparent.
* So, we perform double rotations.
* When we perform two rotations.
* Both the parent and the grandparent are on the left side.
* Both rotations are in the right direction (clockwise).
* First, we pull the grandparent in the right direction.
* And then we pull the parent in the right direction.

#### Zag-Zag Rotation (Zig-Zig Left)

![640splayTreesZagZagLeftTwice.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/640splayTreesZagZagLeftTwice.svg)

* Left-Left Rotation.
* It is also known as **Zig-Zig Left Rotations**.
* The node on which we perform the rotation(s), has grandparent.
* So, we perform double rotations.
* When we perform two rotations.
* Both the parent and the grandparent are on the right side.
* Both the rotations are in the left direction (anti-clockwise).
* First, we pull the grandparent in the left direction.
* And then we pull the parent in the left direction.

### Zig-Zag Rotations (First-Parent-Then-Grandparent): Heterogeneous Rotations

#### Zig-Zag Rotation

![660splayTreesZigZagRightLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/660splayTreesZigZagRightLeft.svg)

* Right-Left Rotation.
* The node on which we perform the rotation(s), has grandparent.
* So, we perform double rotations.
* When we perform two rotations.
* First, we perform the rotation on the parent. 
* The first rotation is in the right direction (clockwise).
* It means that the parent is having the node in the left direction. 
* So, we rotate the parent in the right direction.
* Then, we perform the rotation on the grandparent.
* And the second rotation is in the left direction (anti-clockwise).
* It means that the parent node is in the right direction of the grandparent node.
* So, we rotate the grandparent in the left direction.

#### Zag-Zig Rotation

![680splayTreesZagZigLeftRight.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/680splayTreesZagZigLeftRight.svg)

* Left-Right Rotation.
* The node on which we perform the rotation(s), has grandparent.
* So, we perform double rotations.
* When we perform two rotations.
* First, we perform the rotation on the parent.
* The first rotation is in the left direction (anti-clockwise).
* It means that the parent is having the node in the right direction.
* So, we rotate the parent in the left direction.
* Then, we perform the rotation on the grandparent.
* And the second rotation is in the right direction (clockwise).
* It means that the parent node is to the left of the grandparent node. 
* So, we rotate the grandparent in the right direction.

## Introduction

* [AVLTrees](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt) are strictly balanced binary search trees.
* Splay trees are roughly balanced binary search trees.
* //ToDo: Elaborate
* In a splay tree, the recent node (via search or insert) becomes the root or moves closer to the root.
* The search or insert operation follows the splay operation.
* It means that after every search or insert operation, we have to rearrange the tree.
* The resultant tree may not be perfectly (strictly) balanced.
* But it still maintains the amortized cost as `O(log n)`.
* Because we reduce the access time (search or insert) of the recent node.
* That's the reason we use `Splay Trees` for `Caches`. 

## Insert

![700splayTreeInsertOperation.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/700splayTreeInsertOperation.svg)

## Questions-Answers



## ToDos

* Example of all the rotations (Before and After).

## Next

* [Red-Black Trees](80redBlackTrees.md)