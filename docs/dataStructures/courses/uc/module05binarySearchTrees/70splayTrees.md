# Splay Trees

<!-- TOC -->
* [Splay Trees](#splay-trees)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Purpose](#purpose)
  * [Rotations And Terminologies](#rotations-and-terminologies)
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
  * [Splay](#splay)
    * [Pseudocode Of Splay](#pseudocode-of-splay)
  * [Insert](#insert)
    * [Steps](#steps)
    * [Pseudocode Of Insert](#pseudocode-of-insert)
  * [Search (Find)](#search-find)
    * [Steps](#steps-1)
    * [Pseudocode Of Search](#pseudocode-of-search)
  * [Delete](#delete)
    * [Bottom-Up Delete](#bottom-up-delete)
    * [Top-Down-Join Delete](#top-down-join-delete)
      * [Found The Subject?](#found-the-subject)
      * [No Subject?](#no-subject)
    * [Pseudocode Of Delete (Top-Down-Join)](#pseudocode-of-delete-top-down-join)
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

## Rotations And Terminologies

### Zig Rotations (Terminal Step)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

#### Zig Rotation (Zig-Right)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

![605splayTreeZigRightWithChildren.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/605splayTreeZigRightWithChildren02.png)

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

/**
 * The reason we call it [parent] is to help us remember.
 * The node that we pass to the `rotate` functions is the `parent` of the subject node.
 * The subject node is the one that we want to make the root.
 * If the param name [parent] seems confusing, feel free to replace it with a simple `node` or `current`.
 * Just remember that it should be the `parent` of the subject node.
 */
fun rotateRight(parent: Node<T>) {
    // 1. Find the target node.
    // In a right-side rotation, the target node is the left child.
    val target = parent?.left ?: return

    // 2. The right child of the target node becomes the left child of the current parent node.
    parent.left = target.right
    target.right?.parent = parent

    // 3. The target node replaces its parent.
    // The parent of the current parent node becomes the parent of the target node.
    target.parent = parent.parent
    // It might make the target node the root, left, or right child.
    if (target.parent == null) root = target
    else if (parent.isLeftChild()) parent.parent?.left = target
    else parent.parent?.right = target

    // 4. The current parent node becomes the right child of the target node
    target.right = parent
    parent.parent = target
}
```

#### Zag Rotation (Zig-Left)

![600splayTreesZigRightAndZagLeft.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/600splayTreesZigRightAndZagLeft.svg)

![610splayTreesZagLeftWithChildren02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/610splayTreesZagLeftWithChildren02.png)

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

/**
 * The reason we call it [parent] is to help us remember.
 * The node that we pass to the `rotate` functions is the `parent` of the subject node.
 * The subject node is the one that we want to make the root.
 * If the param name [parent] seems confusing, feel free to replace it with a simple `node` or `current`.
 * Just remember that it should be the `parent` of the subject node.
 */
fun rotateLeft(parent: Node<T>) {
    // 1. Find the target node.
    // For the left rotation, the target node is the right child of the current parent node.
    val target = parent.right ?: return

    // 2. The left child of the target node becomes the right child of the current parent node.
    parent.right = target.left
    target.left?.parent = parent

    // 3. The target node replaces the parent node.
    // The parent of the parent node becomes the parent of the target node.
    // It might make the target node the root, left, or right child.
    target.parent = parent.parent
    if (target.parent == null) root = target
    else if (parent.isRightChild()) parent.parent?.right = target
    else parent.parent?.left = target

    // 4. Finally, the current parent node becomes the left child of the target node.
    // The target node becomes the parent of the current node.
    target.left = parent
    parent.parent = target
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

* [AVLTrees](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)
  are strictly balanced binary search trees.
* Splay trees are roughly balanced binary search trees.
* //ToDo: Elaborate
* In a splay tree, the recently accessed node becomes the root.
* The search or insert operation follows the splay operation.
* It means that after every search or insert operation, we have to rearrange the tree.
* The resultant tree may not be perfectly (strictly) balanced.
* But it still maintains the amortized cost as `O(log n)`.
* Because we reduce the access time of the recent node to `O(1)`.
* That's the reason we use `Splay Trees` for `Caches`.

## Splay

* After `search(find)`, `insert`, or `delete` operation, we perform the `splay` operation on the node.
* The purpose of the `splay` operation is to make the recently accessed node the root node.
* Now, depending upon the position of the recently accessed node, we might perform various (one or multiple and
  different) rotations on the node to make it the root node.
* We have already seen various [rotations](#rotations-and-terminologies).
* If we find that the node has no grandparent, then we perform one of the `zig rotations`.
* Otherwise, we perform either one of the `zig-zig` or one of the `zig-zag` rotations.
* We keep performing these rotations until the node becomes the root.

### Pseudocode Of Splay

```kotlin

fun splay(node: Node<T>) {
    // Keep performing the rotations until the node becomes the root.
    // It means that keep performing the rotations until the node becomes `parentless`.
    while (node.parent != null) {
        // To decide the rotation, we use the parent and the grandparent.
        val parent = node.parent
        val grandParent = parent?.parent

        if (grandParent == null) {
            // If there is no grandparent, it is one of the `zig rotations`.
            // If the node is a left child, we rotate the parent to the right side.
            // Otherwise, we rotate the parent to the left side.
            // In both cases, the rotation brings the parent down and the child up.
            // Note that we pass the `parent` to the `rotate` functions.
            // Because we have designed the `rotate` functions in that way. 
            // It expects the parent, because that's the node on which we perform the rotation.
            if (node.isLeftChild()) rotateRight(parent)
            else rotateLeft(parent)
        } else {
            // If there is a grandparent, we have 4 possible rotations.
            // Zig-Zig-Right, Zag-Zag-Left, Zig-Zag-Right-Left, and Zag-Zig-Left-Right.
            // We check the node and its parent to determine the rotations.
            // If both the node and parent are on the left side (left aligned), it is Zig-Zig-Right.
            if (node.isLeftChild() && parent.isLeftChild()) {
                // First, we rotate the grandparent.
                rotateRight(grandParent)
                // Then, we rotate the parent.
                rotateRight(parent)
            } else if (node.isRightChild() && parent.isRightChild()) {
                // First, rotate the grandparent.
                rotateLeft(grandParent)
                // Then, rotate the parent.
                rotateLeft(parent)
            } else if (node.isLeftChild() && parent.isRightChild()) {
                // Rotate the parent first.
                rotateRight(parent)
                // Then, rotate the grandparent.
                rotateLeft(grandParent)
            } else {
                // Node is a right child, and parent is a left child.
                // First, rotate the parent.
                rotateLeft(parent)
                // Then, rotate the grandparent.
                rotateRight(grandParent)
            }
        }
    }
}

```

## Insert

![700splayTreeInsertOperation.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/700splayTreeInsertOperation.svg)

### Steps

* This is a typical binary tree insert operation followed by the splay operation.
* If the root is null, this new node becomes the root.
* Otherwise, we perform the standard binary search traversal.
* We select the left direction if the value is less than the current node.
* We select the right direction if the value is greater than the current node.
* If the value is equal to the current node, we return.
* That is to say, we don't allow duplicate values.
* Once we reach the leaf node, we attach this new node as a child.
* We update the respective (relevant) pointers for this child and the parent node.
* And finally, we perform the splay operation on this newly inserted node.

### Pseudocode Of Insert

```kotlin

fun insert(key: T) {
    val node = Node(key) // `parent`, `left`, and `right` are null at the moment.
    if (root == null) {
        root = node
        return
    }
    var curr = root
    var parent = curr
    while (curr != null) {
        parent = curr
        if (key < curr.key) {
            curr = curr.left
        } else if (key > curr.key) {
            curr = curr.right
        } else {
            // The node already exists.
            // Next: splay
            splay(curr)
            return
        }
    }
    if (key < parent.key) {
        parent.left = node
    } else {
        parent.right = node
    }
    node.parent = parent
    splay(node)
}

```

## Search (Find)

### Steps

* Perform the typical binary search to find the subject node.
* Perform the splay operation on the subject node.
* If we don't find the subject node, perform the splay operation on the last accessed node. (Don't forget this!)

### Pseudocode Of Search

```kotlin

fun search(key: T): Node? {
    if (root == null) return null
    var curr = root
    var lastAccessed = curr
    while (curr != null) {
        lastAccessed = curr
        if (key < curr.key) {
            curr = curr.left
        } else if (key > curr.key) {
            curr = curr.right
        } else {
            splay(curr)
            return curr
        }
    }
    // We did not find the node.
    // We still perform the splay operation on the `lastAccessed` node.
    splay(lastAccessed)
    // We return `null` to indicate that we could not find the subject node.
    return null
}

```

## Delete

### Bottom-Up Delete

![720splayTreeDeleteOperation.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/720splayTreeDeleteOperation.svg)

* Delete the target node and perform the splay operation on the parent node.
* If we don't find the target node, we still perform the splay operation on the parent node for which the target node
  could have been a child.
* In that case, the last node we reach in a standard binary search traversal is the parent of this target ghost node.

### Top-Down-Join Delete

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

* If we don't find the target node, we still perform the splay operation on the parent node for which the target node
  could have been a child.
* In that case, the last node we reach in a standard binary search traversal is the parent of this target ghost node.
* So, even if we don't find the subject node, we still perform the splay operation.

### Pseudocode Of Delete (Top-Down-Join)

```kotlin

fun delete(key: T) {
    // The search operation internally performs the splay operation, too. 
    val target = search(key)
    if (target == null || target.key != key) {
        return
    }
    val leftRoot = target.left
    val rightRoot = target.right
    
    // Disconnect to delete the target
    leftRoot?.parent = null
    rightRoot?.parent = null
    target.left = null
    target.right = null
  
    // If the `leftRoot` is null
    if (leftRoot == null) {
        root = rightRoot
        return
    }
  
    // Find max of the leftRoot
    var max = leftRoot
    while (max.right != null) {
        max = max.right
    }
    splay(max)
    
    // Now, the `max` is the `root` of the `leftRoot`
    max.right = rightRoot
    rightRoot?.parent = max
  
    // Update the root (Don't forget this!)
    root = max
}

```

## Questions-Answers

### What is the difference between the bottom-up and the top-down approaches of the delete operation in a splay tree?

## ToDos

* Translation of each step, comparison, check, decision, operation, etc., into pseudocode.
* Complete implementation in Kotlin.
* Pseudocode for:
    * Splay
    * Search
    * Insert
    * Delete

## Next

* [Red-Black Trees](80redBlackTrees.md)
* 