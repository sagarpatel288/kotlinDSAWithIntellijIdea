# Which data structure should we use for the following requirements?
* Up to 1,00,000 operations
* Add
* Delete
* Find
* Range Sum Query (Based on values, not indices): `l <= val <= r`.

<!-- TOC -->
* [Which data structure should we use for the following requirements?](#which-data-structure-should-we-use-for-the-following-requirements)
  * [Pre-requisites/References](#pre-requisitesreferences)
  * [Thought Process](#thought-process)
  * [The `Node` class based on the requirements and responsibilities](#the-node-class-based-on-the-requirements-and-responsibilities)
  * [Operations](#operations)
  * [Using `Split` and `Merge` for `Add` and `Delete` Operations](#using-split-and-merge-for-add-and-delete-operations)
  * [The `Update` function](#the-update-function)
  * [The `Rotate` functions](#the-rotate-functions)
  * [The `Split` function](#the-split-function)
  * [The `Merge` function](#the-merge-function)
  * [The `Splay` function](#the-splay-function)
  * [The `Add` function](#the-add-function)
  * [The `Delete` function](#the-delete-function)
  * [The `FindAndSplay` function](#the-findandsplay-function)
    * [Pseudocode for the `FindAndSplay` function](#pseudocode-for-the-findandsplay-function)
<!-- TOC -->

## Pre-requisites/References

* [Doc: Splay Tree](70splayTrees.md)
* [Implementation: Splay Tree](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/040splayTreeImplementation.kt)

## Thought Process

* Suppose, we have 50,000 elements, and 50,000 range sum queries.
* The range sum query is based on values, not indices.
* If we do not have an ordered data structure based on values, then we have to scan and check each element that lies in the range.
* It means, each range sum query will have to scan 50,000 elements.
* So, 50,000 range sum queries will take 50,000 * 50,000 = TLE!
* It means, any data structure that takes `O(n)` doesn't work. 
* We need a faster way to find elements whose values lie in the range.
* Only an ordered data structure can do range sum query fast enough.
* But a BST can be skewed.
* What are the other options?
* Self-balancing trees like AVL or Splay trees.
* An AVL tree is a strictly self-balancing tree.
* It means that it can take more rotations.
* A Splay tree is a roughly self-balancing tree.
* It means that it can take fewer rotations.
* In a Splay tree, we can split the tree into `< l`, `> r`, and `[l, r]`.
* Also, each node can store the sum of its left and right subtrees.
* So, `node.sum = node.key + node.left.sum + node.right.sum`.
* This way, the root of the `[l, r]` part quickly gives us the sum of the range.
* And then, we can merge them back together.
* Reference: [Splay Trees](70splayTrees.md).

## The `Node` class based on the requirements and responsibilities

* A `Node` contains a `key`.
* A `Node` must know its left child and right child. 
  * In other words, it must know its left subtree and right subtree.
* A `Node` must know its parent. For example, we use it to decide the rotation.
* A `Node` must know the sum of its left and right subtrees.
  * So that we don't have to traverse the whole tree to find the sum of the subtree.
  * It helps us find the sum of the subtrees in `O(1)`.

## Operations

* After understanding why we need to use a Splay tree, and designing the `Node` class, we can understand the operations.
* We know the public APIs: `add`, `delete`, `find`, and `rangeSum`.
* We understood that `rangeSum` uses `split` and `merge`.
* Similarly, `add` also uses `split` and `merge`.
* In the `merge` operation, we call `findMax` on the left tree. 
* Except `split` and `merge`, each operation uses `splay` in the end to make the recently (last) accessed node the root node.
* The `splay` operation brings the node to the root of the tree by performing rotations.
* We use `rotateRight` and `rotateLeft` to perform various rotations like `zig`, `zig-zig`, and `zig-zag`.
* After every rotation, various properties of a few nodes may change.
* For example, the parent, left child, right child, height, and sum.
* So, we need the `update` function.
* We would call `update` after every rotation.
* And we call `update` after every `split` and `merge` operations, too.

## Using `Split` and `Merge` for `Add` and `Delete` Operations

* Traditionally, `add` would do a standard BST insertion followed by a `splay` operation.
* And `delete` would `find` the node to delete, `splay` it to the root, `delete` (disconnect) it, `findMax` on the left subtree, and `splay` it to the root, and finally, connect it with the right subtree.
* However, we can use `split` and `merge` to do `add` and `delete` and follow the `DRY` principle.
* For example, suppose that we have a set as: `S: {1, 3, 6, 8, 10}`.
* Now, we want to `add` `7` to the set.
* So, we `split` `S` into `S1` and `S2` such that `S1 < 7` and  `S2 >= 7`.
* So, `S1` becomes `S1: {1, 3, 6}`, and `S2` becomes `S2: {8, 10)`.
* Then, we `merge` `S1` and `7` to get `S3: {1, 3, 6, 7}`.
* And then, we `merge` `S3` and `S2` to get `S4: {1, 3, 6, 7, 8, 10}`.
* If `7` was already in the set, we would have received `S1` as `S1: {1, 3, 6}`, and `S2` as `S2: {7, 8, 10}`.
* In that case, we would `merge` `S1`, and `S2`.
* Similarly, for `delete`, suppose that we have a set as: `S: {1, 3, 6, 7, 8, 10}`, and we want to `delete` `7`.
* So, we `split` `S` into `S1` and `S2` such that `S1 < 7` and `S2 >= 7`.
* So, `S1` becomes `S1: {1, 3, 6}`, and `S2` becomes `S2: {7, 8, 10}`.
* Then, we further `split` `S2` into `S3` and `S4` such that `S3 = {7}`, and `S4 = {8, 10}`.
* Finally, we `merge` `S1`, and `S4` to get `S5: {1, 3, 6, 8, 10}`.

## The `Update` function

> What do we do in the `update` function?

* We update the `sum` property of the node.

> How do we update the `sum` property of the node? What is the formula for the `sum` property of the node?

* `node.sum = key + node.left.sum + node.right.sum`

> When do we call the `update` function?

* Every time we change the structure (shape) of the tree.

> When do we change the structure (shape) of the tree? What operations change the structure of the tree?

* `find`, `add`, and `delete` uses `splay` and changes the structure of the tree.
* `splay` uses the various `rotate` functions, which changes the structure of the tree.
* `split` and `merge` also changes the structure of the tree.
* So, after every `rotate`, `split`, and `merge`, we call the `update` function.

### Pseudocode for `update` function

```kotlin

fun update(node: Node) {
    node.sum = node.key + (node.left?.sum ?: 0) + (node.right?.sum ?: 0)
}

```

## The `Rotate` functions

> What do the `rotate` functions do?

* They are the helper functions for `splay`.
* They move the target node upwards in the tree.
* The `splay` function keeps calling the `rotate` functions until the target node reaches the root of the tree.

> How does the `rotate` function work? 

**Depending upon the type of rotation:**
1. First, we change the child pointer of the target's current parent.
   1. Consequently, we change the parent pointer of the target's child.
2. Then, we change the child pointer of the target.
   1. Consequently, we change the parent pointer of the target's current parent.
3. Then, we change the parent pointer of the target.
   1. Consequently, we change the child pointer of the target's new parent.
4. Finally, we `update` the old parent of the target (now child of the target), and the target.

### Pseudocode for `rotate` function

```kotlin

fun rotate(target: Node) {
    val parent = target.parent ?: return
    val grandParent = parent.parent
    if (parent.left == target) {
        // Right rotation
        // Update the parent pointer of the target's child
        parent.left = target.right
        target.right?.parent = parent
      
        // Update the child pointer of the target
        target.right = parent
    } else if (parent.right == target) {
        // Left rotation
        // Update the parent pointer of the target's child
        parent.right = target.left
        target.left?.parent = parent
        // Update the child pointer of the target
        target.left = parent
    }
    parent.parent = target
    target.parent = grandParent
    if (grandParent == null) root = target
    if (grandParent != null) {
        if (grandParent.left == parent) grandParent.left = target
        else if (grandParent.right == parent) grandParent.right = target
    }
    update(parent)
    update(target)
}

```

## The `Split` function

> How does the `split` function work?

* We call the `find` function on the `split key`.
* It will make the `split key` the root of the tree.
* Then, we cut the tree into two parts.
* The `split key` (now the root) becomes the part of either the left or the right tree.

> When do we call (use) the `split` function?

* We call(use) the `split` function when we want to `add` or `delete` an element or find elements in a range.

## The `Merge` function

> How does the `merge` function work?

* We call the `findMax` on the left subtree and make it the root of the left subtree.
* Then, we make the right subtree the right child of that root.

> When do we call (use) the `merge` function?

* After we `split` the tree, we `merge` the left and the right subtrees.
* We `split` the tree when we want to `add` or `delete` an element or find elements in a range.

## The `Splay` function

> What does the `splay` function do? How does it work?

* The `splay` function makes the last accessed node the root of the tree.
* Depending upon the current structure of the tree, it keeps calling the `rotate` function until the last accessed node becomes the root of the tree.
* The `rotate` function performs various rotations on the various nodes (the target node, the parent of the target node, and the grandparent of the target node) to move the target node upwards in the tree.

## The `Add` function

* We treat the key that we want to add as a `split key`.
* Then, we `split` the tree for this `split key`.
* Then, the `split key` becomes the root, the left subtree becomes the left child, and the right subtree becomes the right child.

## The `Delete` function

* We treat the key that we want to delete as a `split key`.
* Then, we `split` the tree for this `split key`.
* Then, we discard (disconnect) this `split key`.
* And we `merge` the remaining subtrees.

## The `FindAndSplay` function

* Perform the typical binary search on the tree.
* Perform the `splay` operation on the last accessed node.

### Pseudocode for the `FindAndSplay` function

```kotlin

fun findAndSplay(root: Node, key: Long): Node {
    var current = root
    var lastAccessedNode = current
    while (current != null) {
        lastAccessedNode = current
        when {
            current.key < key -> {
                current = current.right
            }
            current.key > key -> {
                current = current.left
            }
            else -> break // found the key
        }
    }
    // splay calls rotate and rotate calls update 
    splay(lastAccessed)  
    return lastAccessed
}

```
