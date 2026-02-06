# Which data structure should we use for the following requirements?
* Up to 1,00,000 operations
* Add
* Delete
* Find
* Range Sum Query (Based on values, not indices): `l <= val <= r`.

<!-- TOC -->
* [Which data structure should we use for the following requirements?](#which-data-structure-should-we-use-for-the-following-requirements)
  * [Pre-requisites/References](#pre-requisitesreferences)
  * [Problem](#problem)
    * [Problem Introduction](#problem-introduction)
    * [Problem Description](#problem-description)
      * [Task](#task-)
      * [Input Format](#input-format-)
      * [Constraints](#constraints-)
      * [Output Format](#output-format-)
      * [Time Limit](#time-limit)
      * [Memory Limit](#memory-limit-)
      * [Sample 1](#sample-1)
        * [Input](#input)
        * [Output](#output)
        * [Explanation](#explanation)
      * [Sample 2](#sample-2)
        * [Input](#input-1)
        * [Output](#output-1)
      * [Sample 3](#sample-3)
        * [Input](#input-2)
        * [Output](#output-2)
        * [Explanation](#explanation-1)
  * [Thought Process](#thought-process)
  * [The `Node` class based on the requirements and responsibilities](#the-node-class-based-on-the-requirements-and-responsibilities)
  * [Operations](#operations)
  * [Using `Split` and `Merge` for `Add` and `Delete` Operations](#using-split-and-merge-for-add-and-delete-operations)
  * [The `Update` function](#the-update-function)
    * [Pseudocode for `update` function](#pseudocode-for-update-function)
  * [The `Rotate` functions](#the-rotate-functions)
    * [Pseudocode for `rotate` function](#pseudocode-for-rotate-function)
  * [The `Split` function](#the-split-function)
      * [Pseudocode for `split` function](#pseudocode-for-split-function)
  * [The `Merge` function](#the-merge-function)
      * [Pseudocode for `merge` function](#pseudocode-for-merge-function)
  * [The `findMax` function](#the-findmax-function)
    * [Pseudocode for `findMax` function](#pseudocode-for-findmax-function)
  * [The `Splay` function](#the-splay-function)
    * [Pseudocode for the `splay` function](#pseudocode-for-the-splay-function)
  * [The `Add` function](#the-add-function)
    * [Pseudocode for the `Add` function](#pseudocode-for-the-add-function)
  * [The `Delete` function](#the-delete-function)
    * [Pseudocode for the `Delete` function](#pseudocode-for-the-delete-function)
    * [Pseudocode for the `Delete` function (improved)](#pseudocode-for-the-delete-function-improved)
  * [The `FindAndSplay` function](#the-findandsplay-function)
    * [Pseudocode for the `FindAndSplay` function](#pseudocode-for-the-findandsplay-function)
  * [The `RangeSum` function](#the-rangesum-function)
  * [Questions](#questions)
    * [Why did we use a `SplayTree`?](#why-did-we-use-a-splaytree)
    * [Why did we use `Long` even though the input is within the `Integer` range?](#why-did-we-use-long-even-though-the-input-is-within-the-integer-range)
    * [Why do we `Splay` before we `Split`?](#why-do-we-splay-before-we-split)
    * [Why do we perform rotations?](#why-do-we-perform-rotations)
    * [Why `merge` calls `splay` on the `max` of the `left`?](#why-merge-calls-splay-on-the-max-of-the-left)
    * [Why does this work?](#why-does-this-work)
    * [What happens if we forget to `update`?](#what-happens-if-we-forget-to-update)
    * [Why do we return the non-null subtree immediately as it is when one of the subtrees is `null` during `merge`?](#why-do-we-return-the-non-null-subtree-immediately-as-it-is-when-one-of-the-subtrees-is-null-during-merge)
      * [Then, why do we `splay` even when the `find key` is missing?](#then-why-do-we-splay-even-when-the-find-key-is-missing)
    * [Why do we have this strange `(+ x) % Mod` condition?](#why-do-we-have-this-strange--x--mod-condition)
    * [What will be the time complexity?](#what-will-be-the-time-complexity)
    * [Can a particular operation take `O(n)` time?](#can-a-particular-operation-take-on-time)
  * [Relevant DSA Variants](#relevant-dsa-variants)
<!-- TOC -->

## Pre-requisites/References

* [Doc: Splay Tree](70splayTrees.md)
* [Implementation: Splay Tree](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/040splayTreeImplementation.kt)
* [Range sum using a splay tree](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/050rangeSumUsingSplayTree.kt)

## Problem

### Problem Introduction

* In this problem, your goal is to implement a data structure to store a set of integers and quickly compute range sums.

### Problem Description

#### Task 

* Implement a data structure that stores a set `𝑆` of integers with the following allowed operations:
* `add(𝑖)` — add integer `𝑖` into the set `𝑆` (if it was there already, the set doesn’t change).
* `del(𝑖)` — remove integer `𝑖` from the set `𝑆` (if there was no such element, nothing happens).
* `find(𝑖)` — check whether `𝑖` is in the set `𝑆` or not.
* `sum(𝑙, 𝑟)` — output the sum of all elements `𝑣` in `𝑆` such that `𝑙 ≤ 𝑣 ≤ 𝑟`.

#### Input Format 

* Initially, the set `𝑆` is empty. 
* The `first line` contains `𝑛` — the number of operations. 
* The `next 𝑛 lines` contain operations. 
* Each operation is one of the following:  
* “`+ i`" — which means add some integer (**not 𝑖**, see below) to `𝑆`.    
* “`- i`" — which means del some integer (**not 𝑖**, see below) from `𝑆`.   
* “`? i`" — which means find some integer (**not 𝑖**, see below) in `𝑆`.   
* “`s l r`" — which means compute the sum of all elements of `𝑆` within some range of values (**not from 𝑙 to 𝑟**, see below).  
* However, to make sure that your solution can work in an online fashion, each request will actually depend on the result of the last sum request. 
* Denote `𝑀 = 1 000 000 001`. 
* At any moment, let `𝑥` be the result of the last sum operation, or just `0` if there were no sum operations before. 
* Then:  
* “`+ i`" means `add((𝑖 + 𝑥) mod 𝑀)`,  
* “`- i`" means `del((𝑖 + 𝑥) mod 𝑀)`,  
* “`? i`" means `find((𝑖 + 𝑥) mod 𝑀)`,  
* “`s l r`" means `sum((𝑙 + 𝑥) mod 𝑀,(𝑟 + 𝑥) mod 𝑀)`.  

#### Constraints 

$1 ≤ 𝑛 ≤ 100 000$  

$0 ≤ 𝑖 ≤ 10^9$

#### Output Format 

* For each find request, just output “**Found**" or “**Not found**" (without quotes; note that the first letter is capital) depending on whether `(𝑖 + 𝑥) mod 𝑀` is in `𝑆` or not. 
* For each sum query, output the sum of all the values `𝑣` in `𝑆` such that `((𝑙 + 𝑥) mod 𝑀) ≤ 𝑣 ≤ ((𝑟 + 𝑥) mod 𝑀)` (it is guaranteed that in all the tests `((𝑙 + 𝑥) mod 𝑀) ≤ ((𝑟 + 𝑥) mod 𝑀))`, where `𝑥` is the result of the last sum operation or `0` if there was no previous sum operation.

#### Time Limit

| Language   	 | C 	 | C++ 	 | Java 	 | Python 	 | C# 	 | Haskell 	 | JavaScript 	 | Ruby 	 | Scala 	 |
|--------------|-----|-------|--------|----------|------|-----------|--------------|--------|---------|
| Time (sec) 	 | 2 	 | 2   	 | 3    	 | 10     	 | 3  	 | 4       	 | 10         	 | 10   	 | 6     	 |

#### Memory Limit 

512 MB

#### Sample 1

##### Input

```
15
? 1
+ 1
? 1
+ 2
s 1 2
+ 1000000000
? 1000000000
- 1000000000
? 1000000000
s 999999999 1000000000
- 2
? 2
- 0
+ 9
s 0 9
```

##### Output

```
Not found
Found
3
Found
Not found
1
Not found
10
```

##### Explanation

* For the first 5 queries, `𝑥 = 0`. 
* For the next 5 queries, `𝑥 = 3`. 
* For the next 5 queries, `𝑥 = 1`. 
* The actual list of operations is:

```
  find(1)
  add(1)
  find(1)
  add(2)
  sum(1, 2) → 3
  add(2)
  find(2) → Found
  del(2)
  find(2) → Not found
  sum(1, 2) → 1
  del(3)
  find(3) → Not found
  del(1)
  add(10)
  sum(1, 10) → 10
```
  
* Adding the same element twice doesn’t change the set.   
* Attempts to remove an element which is not in the set are ignored.  
  
#### Sample 2

##### Input

```
5
? 0
+ 0
? 0
- 0
? 0
```

##### Output

``` 
Not found
Found
Not found
```

* First, 0 is not in the set.  
* Then it is added to the set.  
* Then it is removed from the set.

#### Sample 3

##### Input

```
5
+ 491572259
? 491572259
? 899375874
s 310971296 877523306
+ 352411209
```

##### Output

```  
Found
Not found
491572259
```

##### Explanation

* First, `491572259` is added to the set, then it is found there. 
* Number `899375874` is not in the set. 
* The only number in the set is now `491572259`, and it is in the range between `310971296` and `877523306`.  
* So, the sum of all numbers in this range is equal to `491572259`.

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

#### Pseudocode for `split` function

```kotlin

fun split(key: Long): SplitResult {
    root = find(key)
    // Decide which parts will be the left and right subtrees and where each will go.
    if (root.key >= key) {
        val left = root.left
        left?.parent = null
        root.left = null
        // The root has lost its left subtree, so we need to update the root.
        update(root)
        return SplitResult(left, root)
    } else {
        val right = root.right
        right?.parent = null
        root.right = null
      // The root has lost its right subtree, so we need to update the root.
        update(root)
        return SplitResult(root, right)
    }
}

```

## The `Merge` function

> How does the `merge` function work?

* We call the `findMax` on the left subtree and make it the root of the left subtree.
* Then, we make the right subtree the right child of that root.

> When do we call (use) the `merge` function?

* After we `split` the tree, we `merge` the left and the right subtrees.
* We `split` the tree when we want to `add` or `delete` an element or find elements in a range.

#### Pseudocode for `merge` function

```kotlin

fun merge(left: Node?, right: Node?): Node? {
    if (left == null) return right
    if (right == null) return left
    val maxLeft = findMax(left)
    splay(maxLeft)
    maxLeft.right = right
    right?.parent = maxLeft
    root = maxLeft
    update(maxLeft)
    return maxLeft
}

```

* Notice that in the `merge` function, when the right subtree is null, we return the left subtree as it is.
* Before we understand why we don't splay the maximum node in the left subtree when the right subtree is null, let us first understand why we do that when the right subtree is not null.
* We splay the maximum node in the left subtree and make it the root so that it can create a dock (a space, a place) to park the right subtree.
* If there is no right subtree, then we don't need to create the dock!
* So, when the right subtree is null, we don't need to splay the maximum node in the left subtree and make it the root.
* When the right subtree is null, we just return the left subtree as it is.
* The important thing is that it still maintains (holds) the binary search tree invariants (`in-order` is ascending order).
* The purpose of the `merge` function is to merge the left and the right subtrees, and give us the merged tree that maintains (holds) the binary search tree invariants (the `in-order` traversal of the merged tree is ascending order).
* Also, we use the `merge` function for `delete` operation, too.
* The `merge` function is not an access operation here.
* In the standard splay tree merge, we would still perform the `findMax` and `splay` functions on the left subtree.
* Because, the standard splay tree is optimized for access operations.
* Whereas, this problem is optimized for `rangeSumQuery`.
* We want to minimize the operations.
* So, we avoid unnecessary `findMax`, `splay`, `rotate`, `update`, etc. whenever we can.
* Notice that we still maintain the binary search tree invariants (structural correctness).

## The `findMax` function

* We call `findMax` function from the `merge` function to `find` and `splay` the maximum element in the left subtree.
* By doing that, we make the maximum element in the left subtree the root of the left subtree.

### Pseudocode for `findMax` function

```kotlin

fun findMax(root: Node): Node {
    var current = root
    while (current.right != null) {
        current = current.right
    }
    return current
}

```

* By the definition of a binary search tree, the maximum element is the rightmost element in the tree.

## The `Splay` function

> What does the `splay` function do? How does it work?

* The `splay` function makes the last accessed node the root of the tree.
* Depending upon the current structure of the tree, it keeps calling the `rotate` function until the last accessed node becomes the root of the tree.
* The `rotate` function performs various rotations on the various nodes (the target node, the parent of the target node, and the grandparent of the target node) to move the target node upwards in the tree.

### Pseudocode for the `splay` function

```kotlin

fun splay(target: Node): Node {
    while (target.parent != null) {
        var parent = target.parent
        var grandparent = parent?.parent
        if (grandparent == null) {
            // Whether to rotate right or left is decided by and within the `rotate` function
            rotate(target)
        } else if ((parent.left == target) == (grandparent.left == parent)) {
            // Again, whether to rotate right or left is decided by and within the `rotate` function
            rotate(parent)
            rotate(target)
        } else {
            rotate(target)
            rotate(target)
        }
    }
    root = target
    update(target)
    return target
}

```

* If (grandparent == null) {rotate(target)}
* If ((parent.left == target) == (grandparent.left == parent)) { rotate(parent), rotate(target)}
  * Both parent and target are in the same direction (left or right).
  * Parent pulls the target upwards.
* Else (zigZag, zagZig) {rotate(target), rotate(target)}
  * Rotate the target two times.

## The `Add` function

* We treat the key that we want to add as a `split key`.
* Then, we `split` the tree for this `split key`.
* The `split key` becomes the root, the left subtree becomes the left child, and the right subtree becomes the right child.

### Pseudocode for the `Add` function

```kotlin

fun add(key: Long) {
    // We treat the key that we want to add as a `split key`.
    // "left" is the left subtree and "right" is the right subtree.
    // `left < key` and `right >= key`
    val (left, right) = split(root, key)
    if (right == null || right.key != key) {
        val newNode = Node(key)
        newNode.left = left
        left.parent = newNode
        newNode.right = right
        right?.parent = newNode
        root = newNode
        update(root)
    } else {
        // The key already exists
        root = merge(left, right)
    }
}

```

## The `Delete` function

* We treat the key that we want to delete as a `split key`.
* Then, we `split` the tree for this `split key`.
* Then, we discard (disconnect) this `split key`.
* And we `merge` the remaining subtrees.

### Pseudocode for the `Delete` function

```kotlin

fun delete(key: Long) {
    // We treat the key that we want to delete as a `split key`.
    val (left, rightWithDeleteKey) = split(root, key)
    val (delete, rightWithoutDeleteKey) = split(rightWithDeleteKey, key + 1)
    root = merge(left, rightWithoutDeleteKey)
    update(root)
}
```

* Notice how the current implementation of the `delete` function works.
* What happens if the key that we want to delete does not exist?
* It will still perform the two `split` operations and one `merge` operation for nothing!
* We can improve this by the "fail fast" (early exit, short circuit) principle.
* We `findAndSplay` the key that we want to delete.
* If the key does not exist, then we can return early.

### Pseudocode for the `Delete` function (improved)

```kotlin

fun delete(key: Long) {
    val root = findAndSplay(key)
    if (root.key != key) return // key does not exist, return early
    val left = root.left
    val right = root.right
    left?.parent = null
    right?.parent = null
    root.left = null
    root.right = null
    root = merge(left, right)
}

```

* Find and splay the key that we want to delete.
* If the root key is not equal to the key that we want to delete, then return early.
* Otherwise, disconnect the left and right subtrees from the root.
* And merge the left and right subtrees.

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

## The `RangeSum` function

```kotlin

fun rangeSum(startInclusive: Long, endInclusive: Long): Long {
  // left < startInclusive, and right >= startInclusive
  // The "right" subtree contains all the keys that are greater than or equal to `startInclusive`.
  val (left, right) = split(root, startInclusive)
  // right(startInclusive) <= withinRange < endInclusive + 1, and greaterThanRange >= endInclusive + 1
  // For each key of the "withinRange" subtree, `startInclusive <= key <= endInclusive`.
  val (withinRange, greaterThanRange) = split(right, endInclusive + 1)
  val rangeSum = withinRange?.sum ?: 0L
  // merge the subtrees back together
  root = merge(left, merge(withinRange, greaterThanRange))
  return rangeSum
}

```

* Suppose that we want to find the sum of all the keys that are in the range `[l, r]` inclusive.
* So, first we use the start of the range as the `split key` to split the tree into two subtrees.
* `val (left, right) = split(root, l)`.
* The `left` subtree contains all the keys that are less than `l`.
* We can think of it as the extra (unnecessary) weight.
* Or maybe, imagine that a school has sent many students to a competition.
* The selection process for the competition is based on the age and weight.
* The minimum age or weight is `l` and the maximum age or weight is `r`.
* All the students are in a line (queue) and they are sorted by their age and weight.
* The left most student is the youngest and the lightest student.
* The right most student is the oldest and the heaviest student.
* The `left` subtree represents the students who are younger or lighter than `l`. 
* So, we drop (ignore) it.
* The `right` subtree contains all the keys that are `>= l`.
* It means that the `right` subtree contains the keys that are in the range `[l, r]` inclusive.
* But, the `right` subtree also contains the keys that are greater than `r`.
* So, we need to split the `right` subtree into two subtrees again.
* `val (withinRange, greaterThanRange) = split(right, r + 1)`.
* The `withinRange` subtree is a `rightLeft` subtree, the `left` subtree (part) of the `right` subtree.
* The `greaterThanRange` subtree is a `rightRight` subtree, the `right` subtree (part) of the `right` subtree.
* The `greaterThanRange` subtree contains all the keys that are `>= r + 1`.
* It means that the `greaterThanRange` part contains all the students who are older or heavier than `r`.
* So, we have the `left` subtree, that is `< l`, and the `greaterThanRange` subtree, that is `> r`.
* Hence, the middle part is the `withinRange` subtree, that is `>= l` and `<= r`.
* The two subtrees, `withinRange`, and `greaterThanRange`, are the subtrees of the `right` subtree.
* Out of these two subtrees, the `withinRange` subtree contains all the keys that are less than `r + 1`.
* And remember that the `right` subtree contains all the keys that are greater than or equal to `l`.
* So, the `withinRange` subtree contains all the keys that are `>= l`, but `< r + 1`.
* In other words, the `withinRange` subtree contains all the keys that are in the range `[l, r]` inclusive.
* The `withinRange` represents a root node of the subtree.
* So, `withinRange.sum` is the sum of all the keys that are in the range `[l, r]` inclusive.
* That is the answer that we are looking for.
* But, we need to merge the subtrees back together.
* Think of it as the need to send all the students back to the school.
* `root = merge(left, merge(withinRange, greaterThanRange))`.

## Questions

### Why did we use a `SplayTree`?

* The `range` query works efficiently with a value-based order structure than the index based data structure.
* Binary tree is a value based ordered data structure.
* It is difficult in a simple binary tree to find a particular node, as it can be either left or the right side.
* A binary search tree can be skewed if all the "add" operations are strictly ascending order.
* AVL-Tree and Red-Black trees are strictly self-balanced binary search trees.
* It means that it might perform more rotations than a splay tree.
* Also, the "split", and the "merge" operations are complex in an AVL-Tree and for a Red-Black-Tree than in a Splay-Tree.
* Hence, we used a "Splay Tree".

### Why did we use `Long` even though the input is within the `Integer` range?

* Because the `update` can easily cause `IntegerOverflow`.
* To avoid that, we use `Long` instead of `Integer`.

### Why do we `Splay` before we `Split`?

* For the partition.
* When we `splay` the `splitKey`, we make the `splitKey` or the closest `key` the root node.
* And then depending upon the root value, we `split` the tree into two subtrees: Left and right. 

### Why do we perform rotations?

* To maintain the amortized cost to `O(log n)` by making the recently accessed node the root node.
* Each `splay` operation re-balances the tree to maintain the `O(log n)` amortized cost.

### Why `merge` calls `splay` on the `max` of the `left`?

* To make a room (vacancy) for the `right` child.
* When we `splay` the `max` of the `left`, the `right` child-position becomes empty, waiting to get the `right` child.
* So, we attach the `right` subtree there.
* And this is how we perform the `merge` operation.

### Why does this work?

* Although the `Splay Tree` is a flexible self-balanced binary search tree, we maintain the invariants.
* We maintain the `in-order` structure throughout all the operations.

### What happens if we forget to `update`?

* We lose the BST-invariants.
* It can break the `BST` structure.
* It can lead to higher amortized cost than the expected `O(log n)`.
* We can get unexpected results.

### Why do we return the non-null subtree immediately as it is when one of the subtrees is `null` during `merge`?

* Because each subtree follows the BST-invariants.
* We don't need to do anything else on an existing BST-invariant structure.
* Also, we are not treating the `merge` operation as an `access` operation here.
* The existing setup is to efficiently perform higher number of operations.
* So, we avoid unnecessary `splay` and consequent `rotate` operations when one of the subtrees is null.

#### Then, why do we `splay` even when the `find key` is missing?

* We use `splay` for the `split` operation.
* For the proper partition, we perform `splay` even when the `find key` is missing.

### Why do we have this strange `(+ x) % Mod` condition?

* It prevents precomputed offline solutions such as the usage of a segment tree with offline sorting.
* It forces an online, on-demand, dynamic solution.

### What will be the time complexity?

* The amortized cost is `O(log n)`.
* A single operation can take `O(n)`, but `M` operations would take `O(M log n)`.
* Because each costly operation re-balances the tree.
* As a result, the overall cost across the `M` operations on an average, is `O(log n)` per operation.

### Can a particular operation take `O(n)` time?

* Yes, an individual operation can take `O(n)` time.
* However, each costly operation re-balances the tree.
* Hence, the overall amortized cost is `O(log n)` per operation.

### What if the range was based on the indices instead of the values?
**Will we solve it using a `prefixed sum` array?**

* Even if the range was based on the indices, we will still solve it using a splay tree.
* Because an array takes `O(n)` time per insert/delete operation due to shifting of elements.
* Whereas a splay tree maintains the `O(log n)` time complexity.
* To understand how we manage and process "**index and count based queries**", please refer to the following file:
  * [implicitIndicesWithSplayTree.md](75implicitIndicesWithSplayTree.md)
  * [ropeStringSubstringCutPaste.md](77ropeStringSubstringCutPaste.md)

## Relevant DSA Variants

* Find K-th smallest/largest element.
  * Hint: Add `size` field to `Node` and use it to navigate left/right in `find`.
* Support `min` and `max` queries.
* Count nodes for the range `[l, r]`.
* Add `x` to all the elements within range `[l, r]`.
  * Hint: Use `Lazy Propagation` just like `Segment Trees`. 
  * Store `lazeAdd` value to the `Node` and push it down during rotations.
* Predecessor/Successor: Implement `next(key)` that returns the smallest node, strictly greater than `key`.
  * Hint: `findAndSplay`, and check the `root`.
* Support duplicate keys (multiset).
* How can we replace this `splay tree` implementation with a `treap`?
* Why a segment tree fails here without offline preprocessing?
* Compare splay Vs. AVL-Tree implementation for this problem.
* Compare splay Vs. red-black tree implementation for this problem.
* 