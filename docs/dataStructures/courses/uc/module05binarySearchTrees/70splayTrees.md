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
  * [Delete](#delete)
    * [Bottom-Up Delete](#bottom-up-delete)
    * [Top-Down Delete](#top-down-delete)
      * [Found The Subject?](#found-the-subject)
      * [No Subject?](#no-subject)
  * [Questions-Answers](#questions-answers)
    * [What is the difference between the bottom-up and the top-down approaches of the delete operation in a splay tree?](#what-is-the-difference-between-the-bottom-up-and-the-top-down-approaches-of-the-delete-operation-in-a-splay-tree)
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
* We make the recently accessed node the root.
* Next time, it takes `O(1)` only.
* That is the reason we use it for `caches`.

## Terminologies

### Zig Rotations (Terminal Step)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

#### Zig Rotation (Zig-Right)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

![605splayTreeZigRight.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/605splayTreeZigRight.png)

**General Overview: Understanding The Right Rotation**
* It is also known as **Zig-Right Rotation**.
* The node on which we perform the rotation does not have any grandparent.
* It means that the parent of the node is the root.
* So, we perform a single rotation on the node.
* We rotate the parent node in the right-side direction (clockwise).
* It pulls the subject node upward.

**Main Points: Right Rotation**

* 3 nodes change their child-parent pointers.
* The order of these three nodes is: 
  1. Right child of the target node.
  2. The target node.
  3. Current node (Current parent node of the target).

**Pseudocode: Right Rotation**

```kotlin

fun rotateRight(current: Node<T>) {
    // Find the target node.
    // In a right-side rotation, the target node is the left child.
    val target = current?.left ?: return

    // The right child of the target node becomes the left child of the current node.
    current.left = target.right
    target.right?.parent = current

    // Target node takes the place of the current node.
    // The parent of the current node becomes the parent of the target node.
    target.parent = current.parent
    // It might make the target node the root, left, or right child.
    if (target.parent == null) root = target
    else if (current.isLeftChild()) current.parent?.left = target
    else current.parent?.right = target

    // The current node becomes the right child of the target node
    target.right = current
    current.parent = target
}
```

#### Zag Rotation (Zig-Left)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

![610splayTreeZagLeftWithChildren.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/610splayTreeZagLeftWithChildren.png)

**General Overview: Understanding The Left Rotation**

* It is also known as **Zig-Left Rotation**.
* The node on which we perform the rotation does not have any grandparent.
* So, we perform a single rotation on the node.
* When we rotate a node in the left-side direction (anti-clockwise).

**Main Points: Left Rotation**

* 3 Nodes change their child-parent pointers.
* The order of these three nodes is:
  1. Left child of the target node.
  2. The target node.
  3. Current node (Current parent of the target node).

**Pseudocode: Left Rotation**

```kotlin

fun rotateLeft(current: Node<T>) {
    // Find the target node.
    // For the left rotation, the target node is the right child of the current node.
    val target = current.right ?: return
    
    // The left child of the target node becomes the right child of the current node.
    current.right = target.left
    target.left?.parent = current
    
    // The target node takes the place of the current node.
    // The parent of the current node becomes the parent of the target node.
    target.parent = current.parent
    if (target.parent == null) root = target
    else if (current.isRightChild()) current.parent?.right = target
    else current.parent?.left = target
    
    // Finally, the current node becomes the left child of the target node.
    // The target node becomes the parent of the current node.
    target.left = current
    current.parent = target
}

```

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
* In a splay tree, the recently accessed node becomes the root.
* The search or insert operation follows the splay operation.
* It means that after every search or insert operation, we have to rearrange the tree.
* The resultant tree may not be perfectly (strictly) balanced.
* But it still maintains the amortized cost as `O(log n)`.
* Because we reduce the access time of the recent node to `O(1)`.
* That's the reason we use `Splay Trees` for `Caches`. 

## Insert

![700splayTreeInsertOperation.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/700splayTreeInsertOperation.svg)

## Delete

### Bottom-Up Delete

![720splayTreeDeleteOperation.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/720splayTreeDeleteOperation.svg)

* Delete the target node and perform the splay operation on the parent node.
* If we don't find the target node, we still perform the splay operation on the parent node for which the target node could have been a child.
* In that case, the last node we reach in a standard binary search traversal is the parent of this target ghost node.

### Top-Down Delete

![740splayTreeDeleteUsingJoin.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/740splayTreeDeleteUsingJoin.svg)

#### Found The Subject?

* Splay the target node (Make the target node the root).
* Delete the target node (Now, the root).

**Two Children: Left and Right Subtrees**

* We might get two orphan children: The left subtree and the right subtree.
* Perform the splay operation on the maximum key of the left subtree.
* The key with the maximum value becomes the root node in the left subtree.
* Make the right subtree the right child of this new root.
* Done. This is our final tree.

**Only The Right Child: Only The Right Subtree. No Left Subtree.**

* Suppose that after deleting the target node, we only have a right-side child of it.
* In this case, the right subtree is the final tree. 

**Only The Left Child: Only The Left Subtree. No Right Subtree.**

* Suppose that after deleting the target node, we only have a left-side child of it.
* In this case, we still perform the splay operation on the maximum key of the left subtree.
* So, the node with the maximum value becomes the root of the left subtree.
* And that will be the final tree.

#### No Subject?

* If we don't find the target node, we still perform the splay operation on the parent node for which the target node could have been a child.
* In that case, the last node we reach in a standard binary search traversal is the parent of this target ghost node.
* So, even if we don't find the subject node, we still perform the splay operation. 

## Questions-Answers

### What is the difference between the bottom-up and the top-down approaches of the delete operation in a splay tree?

## ToDos

* Example of all the rotations (Before and After).
* Translation of each step, comparison, check, decision, operation, etc., into pseudocode.
* Complete implementation in Kotlin.


## Next

* [Red-Black Trees](80redBlackTrees.md)