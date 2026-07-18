# AVLTree Foundation

<!-- TOC -->
* [AVLTree Foundation](#avltree-foundation)
  * [Node](#node)
  * [Height (A naive recursive approach)](#height-a-naive-recursive-approach)
  * [Size (A naive recursive approach)](#size-a-naive-recursive-approach)
  * [Height (A null-safe helper function to read the height)](#height-a-null-safe-helper-function-to-read-the-height)
  * [Size (A null-safe helper function to read the size)](#size-a-null-safe-helper-function-to-read-the-size)
  * [UpdateHeightAndSize (The function that updates the height and size)](#updateheightandsize-the-function-that-updates-the-height-and-size)
  * [BalanceFactor](#balancefactor)
  * [Rotations](#rotations)
    * [Critical](#critical)
    * [How to remember?](#how-to-remember)
  * [Other Functions](#other-functions)
<!-- TOC -->

## Node

* We use and deal with the below properties of a node:
* key (value), left, right, height, size, and the balance factor.
* We (as an external world) only provide the key (value).
* We (as an insider team) connect and manage the neighbors: Left and right.
* Height, size, and balance factor are something we calculate.
* So, we might think of them as regular computed properties as below:

```kotlin

class Node(var key: Int, var left: Node? = null, var right: Node? = null) {
    val height: Int
        get() = 1 + maxOf(height(left), height(right))
    
    val size: Int
        get() = 1 + size(left) + size(right)
    
    val balanceFactor: Int
        get() = height(left) - height(right)
}

```

## Height (A naive recursive approach)

```kotlin

private fun height(node: Node?): Int {
    return node?.let {
        1 + maxOf(height(it.left), height(it.right))
    } ?: 0
}

```

## Size (A naive recursive approach)

```kotlin

private fun size(node: Node?): Int {
    return node?.let {
        1 + size(it.left) + size(it.right)
    } ?: 0
}

```

* But then every single time we try to read any of those "computed properties", we go through a massive calculation!
* The better and efficient way is to separate the reading and calculation behavior.
* So, we get:

## Height (A null-safe helper function to read the height)

```kotlin

private fun height(node: Node?): Int {
    return node?.height ?: 0
}

```

## Size (A null-safe helper function to read the size)

```kotlin

private fun size(node: Node?): Int {
    return node?.size ?: 0
}

```

## UpdateHeightAndSize (The function that updates the height and size)

```kotlin

private fun updateHeightAndSize(node: Node?) {
    node?.height = 1 + maxOf(height(node?.left), height(node?.right))
    node?.size = 1 + size(node?.left) + size(node?.right)
}

```

* Notice the massive time we save when we use [updateHeightAndSize](#updateheightandsize-the-function-that-updates-the-height-and-size) compared to the computed properties and recursive functions like [height](#height-a-naive-recursive-approach) and [size](#size-a-naive-recursive-approach).

## BalanceFactor

* Now, we can use it as a computed property – thanks to the separate [updateHeightAndSize](#updateheightandsize-the-function-that-updates-the-height-and-size), [height](#height-a-null-safe-helper-function-to-read-the-height), and [size](#size-a-null-safe-helper-function-to-read-the-size) functions.

```kotlin

val balanceFactor: Int
    get() = height(left) - height(right)

```

## Rotations

**What are the things that we need to take care of when we rotate a node?**

* Mainly two things: The child and the grandchild.
* One of the children of the unbalanced node becomes the parent of the unbalanced node.
  * The unbalanced node becomes the child of its one of the children.
* Importantly, the process can move the grandchild of the unbalanced node.
* For example:
* ![Left Rotation](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/300denseAvlTreeLeftRotationPulleyAnalogy.png)
* We can see that the unbalanced node is `10`, and its immediate right child is `20`.
* The node `20` is a right child of the unbalanced node `10`.
* The node `20` has a left child `15` and a right child `30`.
* When we rotate `10` to the left side, it also affects the grandchild `15`, too.
* The grandchild `15` leaves the parent `20` and goes with the grandparent `10`.
* The grandchild takes care of the grandparent.
* And the old parent of the `15` takes care of both!
---
* When we rotate a node on the left side, the left grandchild becomes their right child.
* And their old right child becomes their parent.
---
* Similarly:
* ![Right Rotation](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/280denseAvlTreeRightRotationPulleyAnalogy.png)
* The grandparent is `30`, left child is `20`, left grandchild is `10`, and right grandchild is `25`.
* When we rotate the grandparent `30` to the right side, the right grandchild `25` goes with the grandparent.
* And the old parent of `25` takes care of both!
---
* When we rotate a node on the right side, the right grandchild becomes their left child.
* And their old left child becomes their parent.

### Critical

* It is not only about the rotations.
* We perform one or more rotations to rebalance one or more nodes.
* Every time we insert or delete a node, it can cause one or multiple rotations.
* Each rotation can change the root, connections (like `left` or `right`), height, and size of one or multiple nodes.
* So, it is critical that we update the root, connection, height, and size of one or multiple nodes after every rotation.
* That's why every rotation returns a `node`.
* We can use this returned node to update the root or to attach it as a new child to its parent.
* The process has the potential to propagate these changes upto the root.
* Hence, when applicable, we also ensure to update the root.

### How to remember?

> Recall this, imagine this, replay this again and again to remember

* The child goes into the opposite direction.
  * Take the child from the opposite direction.
* The grandchild aligns!
  * Take the grandchild from the same direction (with the grandparent).
* The grandchild goes with the grandparent and becomes their new child.
  * Opposite side.
* The old child becomes the parent.
  * Same side.

```kotlin

// We are rotating the unbalanced node on the right side.
// We remember two things: 
// The rotation side: Right
// And the sequence: Opposite + Same (Two times)
private fun rotateRight(unbalanced: Node) {
    // We are rotating on the right side.
    // Take the child from the opposite side.
    // Opposite side: Left
    val child = unbalanced.left
    // Take the grandchild from the same rotation side with the grandparent.
    // Aligned. Same side: Right
    val grandChild = child.right
    // We ended with the grandchild.
    // We start with the grandchild.
    // The grandchild goes with the grandparent and becomes their new child.
    // Opposite side: Left
    unbalanced.left = grandChild
    // The old child becomes the parent.
    // Aligned. Same side: Right
    child.right = unbalanced
}

```

* Similarly:

```kotlin

// Rotation side is: Left
// Sequence: Opposite + Same (Two times)
private fun rotateLeft(unbalanced: Node) {
    // Take the child from the opposite side
    val child = unbalanced.right
    // Take the grandchild from the same side
    val grandChild = child.left
    // Follow the same sequence: Opposite + Same
    // We ended with the grandchild.
    // We start with the grandchild.
    // Grandchild becomes the child
    unbalanced.right = grandChild
    // Child becomes the parent
    child.left = unbalanced
}

```



## Other Functions

* Insert
* Delete
* NextLarge
* RangeSearch
* Split
* Merge
* Find Kth Smallest
* Find
* Replace
