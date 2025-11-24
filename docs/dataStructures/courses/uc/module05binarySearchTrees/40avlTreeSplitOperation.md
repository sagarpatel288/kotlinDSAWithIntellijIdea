# Split An AvlTree into Two AvlTrees

<!-- TOC -->
* [Split An AvlTree into Two AvlTrees](#split-an-avltree-into-two-avltrees)
  * [Prerequisites](#prerequisites)
  * [The Problem Statement](#the-problem-statement)
  * [Thought Process: Brick by brick, Step by step:](#thought-process-brick-by-brick-step-by-step)
    * [Understanding the requirements and output](#understanding-the-requirements-and-output-)
    * [Return type](#return-type)
    * [Approach, idea](#approach-idea)
    * [Building the function](#building-the-function)
    * [Understanding the `split` function and its returned values](#understanding-the-split-function-and-its-returned-values)
    * [Break and Merge (Break and build)](#break-and-merge-break-and-build)
    * [The meaning of changing the path](#the-meaning-of-changing-the-path)
    * [Returning the result](#returning-the-result)
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

## Thought Process: Brick by brick, Step by step:

### Understanding the requirements and output 

* We want to split the given `AvlTree` into two `AvlTrees`.
* So, our final output will be two `AvlTrees`.
* Now, we denote one `AvlTree` as $T_1$ and another as $T_2$.
* $T_1$ should be $T_1 <= x$.
* It means that all the nodes (keys) of $T_1$ must be at most `x`.
* We can call $T_1$ the small (light, left) tree. 
* When we say a small tree, the focus is on the key value of any node, and not on the overall height of the tree.
* Similarly, $T_2$ should be $T_2 > x$.
* It means that all the nodes (keys) must be greater than `x`.
* We can call $T_2$ the big (heavy, right) tree.
* When we say a big tree, the focus is on the key value of any node, and not on the overall height of the tree.

### Return type

* Two `AvlTrees` so that $T_1 <= x$, and $T_2 > x$.
* So, we can return a pair of `AvlTrees` or return an encapsulated `data class`.

### Approach, idea

* Our approach is to build the tree from the bottom.
* So, we start the traversal from the root of the given `AvlTree`.
* And to build a tree, we need 3 data: The parent node, left subtree (a.k.a. left tree or left child), and the right subtree (a.k.a. right tree or right child).
* Now, when we reach the leaf node, we need to have the references of the parent node and the right node, so that we can merge them.
* And this merged tree will be a subtree for a certain parent node.
* And this process keeps going on.
* So that will be the reverse journey.
* First, we travel towards the bottom, and then we travel towards the top.
* As we travel towards the top, we merge and build the tree.
* Every time we build a tree, we need to know whether the tree is a part of $T_1$ or $T_2$.
* The condition to decide whether a particular node is a part of $T_1$ or $T_2$ is simple.
* `if (node.key <= target)`, then it is the part of $T_1$.
* Else, it is part of $T_2$.

### Building the function

![450avlTreeSplit.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/450avlTreeSplit.svg)

* We want to split the given `AvlTree` into two `AvlTrees`.
* So, let us call the function: `split`.
* And we want to split it from a particular node.
* Let us call this node `x`, `target`, or `limit` node.
* So, the `split` function expects this `target` node.
* And we want to return two `AvlTrees`.
* Let us encapsulate these two `AvlTrees` into a `data class` and call it `SplitResult`.
* This `SplitResult` data class might look like below:

```kotlin

data class SplitResult(val t1LeftTree: AvlTree, val t2RightTree: AvlTree)

```

* The property name `t1LeftTree` conveys that all the nodes in this tree have values at most `target`.
* Similarly, the property name `t2RighTree` conveys that all the nodes of this tree have values greater than the `target` value.
* Back to our function `Split`.
* So, the function returns `SplitResult`.

```kotlin

fun split(target: AvlNode): SplitResult {
    
}

```

* To find all the nodes for $T_1$ and $T_2$, we need to travel the tree. 
* The traversal follows the typical binary search tree.
* The traversal starts from the root node.
* So, it looks like:

```kotlin

fun split(target: AvlNode): SplitResult {
    var curr = root
    if (curr == null) {
        return SplitResult(null, null)
    }
    while (curr != null) {
        if (curr.key <= target) {
            //...
        } else {
            //...
        }
    }
}
```

![450avlTreeSplit.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/450avlTreeSplit.svg)

* When we are at a particular node and we find that $node.key <= x$, we are sure that this `node` belongs to the $T_1$.
  * We also know that the left children of this `node` will also be at most `x`. 
  * So, we know that $node.left <= x$.
  * But, we don't know about the right node and its children.
  * For example, as shown in the given image, we want to split from `60`.
  * Let us call `60` our `x`, `target`, or `limit` node.
  * We start from `50`, which is the `root` node.
  * $50 <= 60$. At this point, we are sure that all the left children of the node `50` must be smaller than `60`, because that is the `AvlTree` (or Binary Search Tree) property.
  * So, the entire left subtree of the node `50` is `<= 60`.
  * However, the right child of the node `50` is `60`.
  * And it doesn't mean that all the children, the entire subtree of `60` is greater than `60`.
  * For example, the right subtree of `50` is `60` and it includes many nodes, such as the entire `55` subtree, that is `<= 60`.
  * The point is, when the `node.key <= x`, we are sure that the entire `node.left` subtree must be `<= x`.
  * But, we are not sure about the `node.right` subtree.
  * So, when `node.key <= x`, we want to explore the `node.right` path.
  * So, it might look like:

```kotlin

if (curr.key <= x) {
    // We will learn more about this `explore` method soon.
    explore(node.right)
}
```

* **Now, what are the expectations from this `explore` function?**
* **What do we say when we pass `node.right` to the `explore` function?**
* We are saying that:
* "Hey, for a particular `target` and a particular `node`, if `node.key <= x`, we already know that going further down towards the left side of this node will get us all the nodes that will belong to $T_1$. Now, we want to do the same exercise when the root node, the starting point of our traversal, is `node.right`.
* So, the `input` is changed, but the process remains the same.
* So, it looks like a recursion.
* Hence, we will adjust our `split` function as follows:

```kotlin
// When we call the `split` function for the first time, from the outside, we pass `root`, because that's the node from where we start our binary search type traversal. 
split(root, target)
fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return SplitResult(null, null)
    if (node.key <= target) {
        // We will learn about these returned values soon. 
        val (t1LeftTree, t2RightTree) = split(node.right, target)
    } else {
        
    }
}
```

* Now, similar to the `node.key <= x` case, when we reach a particular node where `node.key > x`, we are sure that `node.right > x`.
  * So, when `node.key > x`, we want to explore the `node.left` path.
  * So, the function might look like this:

```kotlin

fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return SplitResult(null, null)
    if (node.key <= target) {
        // We will learn about these returned values soon.
        val (t1LeftTree, t2RightTree) = split(node.right, target) 
    } else {
        // We will learn about these returned values soon.
        val (t1LeftTree, t2RightTree) = split(node.left, target)
    }
}

```

### Understanding the `split` function and its returned values

* The `split` function takes two arguments: 
* `node` is the starting point of the traversal.
* `target` is the split point.
* We call the `split` function two times:

`if (node.key) <= target`

* Under this condition, we pass `node = node.right` and `target = target` to the `split` function.
* The `split` function returns `SplitResult`.
* `SplitResult` is a `data class` and it has two properties: `t1LeftTree` and `t2RightTree`.
* So, we store the returned values of the `split` function under this `if` condition as: `val (t1LeftTree, t2RightTree)`.
* `t1LeftTree` represents all the nodes whose key values are at most to the `target` we pass to the `split` function.
* And we start our traversal from the `node` that we pass to the `split` function.
* Passing `node = node.right` and `target = target` as arguments to the `split` function under this `if` condition conveys the following:

![450avlTreeSplit.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/450avlTreeSplit.svg)


* "We are calling this `split` function under this `if(node.key <= target` condition. So, we know that the left subtree of this `node` has all the nodes whose key values are at most the `target`. That is the certain part. But, the left subtree of `node.right` might have (the uncertainty) nodes whose key values are also at most the `target` value, but we are not sure about it. We need to travel through it to find such nodes. So, we resume the traversal from the `node.right` and try to find the nodes whose key values are at most the `target` value."
* Now, the `split` function returns `SplitResult`.
* This `SplitResult` contains two properties: `t1LeftTree`, and `t2RightTree`.
* Now, at this point, the function `split` doesn't have any process to build the proper trees.
* It only returns `null` trees.
* In the following steps, we will learn about how to build and return proper and balanced `AvlTrees`.

### Break and Merge (Break and build)

![450avlTreeSplit.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/450avlTreeSplit.svg)

 * Now, to create two `AvlTrees` from the one `AvlTree`, we need to break it first.
 * We break and group (segregate, assort) it in such a way that we get two `AvlTres` as $T_1 <= x$ and $T_2 > x$.
 * Now, when we say `break` the `AvlTree`, we are talking about breaking the connections of the nodes.
 * **So, how do we break the connections of a node?**

```kotlin
node.left = null
node.right = null
node.height = 1
```

* So, the `split` function might look like this:

```kotlin

fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return SplitResult(null, null)
    // Before breaking the node, we need to take references to its children as we use them later
    val leftChild = node.left
    val rightChild = node.right
    node.left = null
    node.right = null
    node.height = 1
    if (node.key <= target) {
        val (t1LeftTree, t2RightTree) = split(rightChild, target)
    } else {
        val (t1LeftTree, t2RightTree) = split(leftChild, target)
    }
}

```

* Now, after breaking the tree, we also need to `merge` the segregated, assorted nodes.
* **So, how do we merge two `AvlTrees`?**
* Prerequisites/Reference: The `merge` process is already implemented below:

* [avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)

* Where do we call this `mergeTwoAvlTrees` function?
* The `SplitResult` returns two trees.
* So, it can be as given below:

```kotlin
fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return SplitResult(null, null)
    // Before breaking the node, we need to take references to its children as we use them later
    val leftChild = node.left
    val rightChild = node.right
    node.left = null
    node.right = null
    node.height = 1
    if (node.key <= target) {
        val (t1LeftTree, t2RightTree) = split(rightChild, target)
        val mergedTree = mergeTwoAvlTrees(whatDoWePassHere, whatDoWePassHere, whatDoWePassHere)
    } else {
        val (t1LeftTree, t2RightTree) = split(leftChild, target)
        val mergedTree = mergeTwoAvlTrees(whatDoWePassHere, whatDoWePassHere, whatDoWePassHere)
    }
}
```

* The `mergeTwoAvlTrees` function takes three arguments:
* The root node of tree one, the root node of tree two, and the pivot node that connects these two trees.
* So, what do we pass to this `mergeTwoAvlTrees` function?
* **What is the job, role, responsibility, and purpose of this `mergeTwoAvlTrees` function?**
* We have two `mergeTwoAvlTrees` functions inside the `split` function.  
* 
`if (node.key <= x)`:

* The `leftChild` that we read and stored earlier belongs to the $T_1$ tree.
* We know that the `node.key <= x` belongs to the $T_1$ tree.
* Inside that condition, `t1LeftTree` also belongs to the $T_1$ tree.
* //ToDo: It will be interesting and helpful to know and understand why and how the `t1LeftTree` belongs to the $T_1$ tree. Maybe it has something to do with the fact that we process the `uncertain` part when we recursively call the `split` function. Maybe this behavior (action, process) holds `t1LeftTree`. Maybe this is how we accumulate, segregate, assort, and collect the nodes that belong to the $T_1$ tree. Consider explaining.
* And the `node` itself belongs to the $T_1$ tree, because its `key <= x`.
* So, inside `node.key <= x`, we have `leftChild`, `t1LeftTree`, and `node` as the pivot.  

```kotlin
fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return SplitResult(null, null)
    // Before breaking the node, we need to take references to its children as we use them later
    val leftChild = node.left
    val rightChild = node.right
    node.left = null
    node.right = null
    node.height = 1
    if (node.key <= target) {
        val (t1LeftTree, t2RightTree) = split(rightChild, target)
        val mergedTree = mergeTwoAvlTrees(leftChild, t1LeftTree, node)
    } else {
        val (t1LeftTree, t2RightTree) = split(leftChild, target)
        val mergedTree = mergeTwoAvlTrees(whatDoWePassHere, whatDoWePassHere, whatDoWePassHere)
    }
}
```

`else` (which means when `node.key > x`):
* The `rightChild` that we read and stored earlier belongs to the $T_2$ tree.
* The `node` belongs to the $T_2$ because its `key > x`.
* And the `t2RightTree` belongs to the $T_2$. 
* //ToDo: It will be interesting and helpful to know and understand why and how the `t2RightTree` belongs to the $T_2$ tree. Maybe it has something to do with the fact that we process the `uncertain` part when we recursively call the `split` function. Maybe this behavior (action, process) holds `t2RightTree`. Maybe this is how we accumulate, segregate, assort, and collect the nodes that belong to the $T_2$ tree. Consider explaining.
* So, inside the `else` part, we have the `rightChild`, `t2RightTree`, and the `node` as the pivot.

```kotlin

fun split(node: AvlNode, target: AvlNode): SplitResult {
    if (node == null) return null
    val leftChild = node.left
    val rightChild = node.right
    node.left = null
    node.right = null
    node.height = 1
    if (node.key <= target) {
        val (t1LeftTree, t2RightTree) = split(rightChild, target)
        val mergedTree = mergeTwoAvlTrees(leftChild, t1LeftTree, node)
    } else {
        val (t1LeftTree, t2RightTree) = split(leftChild, target)
        val mergedTree = mergeTwoAvlTrees(rightChild, t2RightTree, node)
    }
}
```

### The meaning of changing the path

### Returning the result

## Next