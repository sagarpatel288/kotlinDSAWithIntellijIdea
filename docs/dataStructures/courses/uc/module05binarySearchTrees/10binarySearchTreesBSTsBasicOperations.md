# Binary Search Trees (BSTs): Basic Operations

<!-- TOC -->
* [Binary Search Trees (BSTs): Basic Operations](#binary-search-trees-bsts-basic-operations)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Find (Search)](#find-search)
  * [Next (Adjacent Element, Next Largest)](#next-adjacent-element-next-largest)
  * [Range Search](#range-search)
  * [Insert](#insert)
  * [Delete](#delete)
  * [Summary](#summary)
    * [Find(key, node = root)](#findkey-node--root)
    * [NextLarger(key: Int)](#nextlargerkey-int)
    * [RangeSearch(x, y)](#rangesearchx-y)
    * [Insert(key, node = root)](#insertkey-node--root)
    * [Delete(key)](#deletekey)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References


* [Trees](../module01BasicDataStructures/section03trees/010trees.md)
* [Basic Data Structure Questions](../module01BasicDataStructures/questionsOnBasicDataStructures.md)
* [Priority Queues](../module03priorityQueuesHeapsDisjointSets/section01priorityQueuesIntroduction/priorityQueues.md)
* [Binary Heap Trees](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
* [Complete Binary Tree](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
* [Heap Sort](../module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
* [Binary Search Trees](05binarySearchTrees.md)

## Find (Search)

* Find and return the node that has the given key value.
* 

![10bstFindIntro.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/12bstFindIntro.png)

* If the key we want to find is equal to the root key, we return the root.
* Otherwise, we start with the root node. 
* So, `currentNode = rootNode`.
* And then we will start our searching journey using iteration.

**Code Translation**

```kotlin
if (rootNode.key == key) return rootNode
var currentNode = rootNode
```

* We will stop our searching: 
  * As soon as we find a node that has the given key.
  * Or when we fall off the tree! (We finish the tree, but can't find the key!)
  
**Code Translation** 

```kotlin

// Keep searching as long as we haven't fallen off the tree
// That is to say: Keep searching as long as the "currentNode != null"
while (currentNode != null) {
    
}
```

**Inside the `while` loop: Looking for the key**

* If `key == currentNode.key`, we return the `currentNode`.
* Otherwise, if the `key > currentNode.key`, we need to go to the right side (direction) of the current node.
  * But only if the `currentNode` has a right child! 
* If the `key < currentNode.key`, we need to go left side of the current node.
  * But only if the `currentNode` has a left child!
* If we can't find the given key, the last station (node) is the closest one where the key would come.

**Code Translation**

```kotlin

when {
    key == currentNode.key -> return currentNode
    key > currentNode.key -> currentNode = currentNode.right
    key < currentNode.key -> currentNode = currentNode.left
}
```

**What if we reach the end of the tree?**

* When the `currentNode.right` or `currentNode.left` gives a `null` value, we finished (fallen off) the tree. 
* So, the `while` condition `currentNode != null` becomes false, and we exit the loop.
* Hence, we get a `null` value for the `currentNode` after the `while` loop.  

**Pseudocode**

```kotlin

fun find(key: Int, rootNode: Node?): Node? {
    if (rootNode == null) return null
    if (rootNode.key == key) {
        return rootNode
    }
    var currentNode = rootNode
    while (currentNode != null) {
        when {
            key == currentNode.key -> return currentNode
            key < currentNode.key -> currentNode = currentNode.left
            key > currentNode.key -> currentNode = currentNode.right
        }
    }
    // The `while` loop finished. We fallen off the tree. The `currentNode` is `null`.
    return currentNode
}

```

## Next (Adjacent Element, Next Largest)

* We want to find the **next largest** key than the given node.
* Now, there are two possibilities:
  * Either the subject node has a right subtree.
  * The subject does not have a right subtree.
* If the subject has a right subtree:
  * We go to the right side of the subject once.
  * And then we keep going to the left side until we hit the end.

![35bstNextLargerAdjacent01.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/35bstNextLargerAdjacent01.png)

![40bstNextLargerAdjacent02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/40bstNextLargerAdjacent02.png)

**We go to the right side once and then to the left side. But what if there is no left subtree?**

* In that case, the right side node is the **next largest** node.

![40bstNextLargerAdjacent02b.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/40bstNextLargerAdjacent02b.png)

**Code Translation**

```kotlin

var currentNode = subjectNode
if (subjectNode.right != null) {
    // Go to the right direction once
    currentNode = subjectNode.right
    // Then go to the left most node
    while (currentNode?.left != null) {
        currentNode = currentNode.left
    }
    // This left most node is the next largest node
    return currentNode
}

```


* If the subject does not have a right subtree:
  * We travel upwards via parents until we find the next larger node.

![45bstNextLargerAdjacent03.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/45bstNextLargerAdjacent03.png)

**When will we find the next larger parent?**

* As long as the current node is not null and it is a right child of the parent, the parent cannot be the next larger node.
* Only once we find that the current node is a left child of the parent, the `parent` is the next larger node.

```kotlin
var currentNode = subjectNode
if (subjectNode.right == null) {
    var parentNode = currentNode.parent
    // Keep climbing upside
    // As long as we are (`currentNode`) is at the right side of the parent, the parent is smaller than us. 
    // Such a parent cannot be the `next larger` node.
    // So, we keep looking until we find ourselves as the left child of the parent.
    while (parentNode != null && currentNode == parentNode.right) {
        currentNode = parentNode
        parentNode = parentNode.parent
    }
    // When the `while` loop exits:
    // Either the `parentNode` is `null`. So, we couldn't find the next larger node.
    // Or `currentNode == parentNode.left` that makes the `parentNode` the next larger node.
    return parentNode
}
```

* Now, when we travel upwards via parents, we may or may not find the next larger node.

![50bstNextLargerAdjacent04.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/50bstNextLargerAdjacent04.png)

* Suppose we want to find `nextLarger(N)`.
* If we reach the root and do not find any larger key than `N`, then:
  * Return the best output: Error, null, the subject node `N` itself, the last node we reached (might be a misleading, wrong, confusing choice), etc.
  * Here, we return `null` that indicates there is no next larger node.
  * It means that the subject node itself is the largest node!

```kotlin

fun nextLarger(node: Node?): Node? {
    if (node == null) return null
    return if (node.right != null) {
        nextLargerLeftDescendant(node.right)
    } else {
        nextLargerParent(node)
    }
}

fun nextLargerLeftDescendant(node: Node?): Node? {
    if (node == null) return null
    var currentNode = node
    while (currentNode.left != null) {
        currentNode = currentNode.left
    }
    return currentNode
}

fun nextLargerParent(node: Node?): Node? {
    if (node == null) return null
    var currentNode = node
    var parentNode = currentNode.parent
    while (parentNode != null && currentNode == parentNode.right) {
        currentNode = parentNode
        parentNode = parentNode.parent
    }
    return parentNode
}

```

## Range Search

![60bstRangeSearch.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/60bstRangeSearch.png)

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TD
    A(("7")) --> n1(("4")) & n2(("13"))
    n1 --> n3(("1")) & n4(("6"))
    n2 --> n5(("10"))
    n2 --- n6(("30"))
    n3 ~~~ n9(("<br>"))
    n5 --> n10(("8")) & n11(("11"))
    n6 --> n12(("20")) & n13(("40"))
    n12 --> n14(("17")) & n15(("25"))
    n14 ~~~ n16(("<br>"))
    n14 --> n17(("18"))
    n13 --> n18(("35")) & n19(("50"))
    style n9 stroke:none,fill:transparent
    style n16 stroke:none,fill:transparent

```
* Suppose the tree is:
```kotlin

val tree = [1, 4, 6, 7, 8, 10, 11, 13, 17, 18, 20, 25, 30, 35, 40, 50]
```
* Suppose, we have got `rangeSearch(5, 19)`.
* Now, if we denote the ranges as: `5 = x` and `19 = y`.
* We want to add nodes whose key values are between `x` and `y` inclusive.
* Then, we can keep looking for the next larger node until the node value becomes equal to or greater than `y`.
* The `nextLarger` approach must start with one `find` call to stand on the `x` node.
* And from there, we can keep looking for the `nextLarger` node.
* The `find` operation takes `O(Tree Height)` = $O(\;log (n)\;)$ time.
* The problem with this `nextLarger` approach is that we may re-visit (re-travel) nodes and paths along the way.
* For example, in the given image, the `nextLarger` travels `7 --> 13 --> 10 --> 8` to find `nextLarger(7)`.
* Then, from `8`, the same person travels and touches the nodes `8 --> 10`. 
* So, `10` is re-touched, (re-visited, re-travelled).
* From `10`, the person travels to `11`.
* And from `11`, the person travels as: `11 --> 10 --> 13`.
* See, all these two nodes, `10` and `13` are re-touched (re-visited, re-travelled) again.
* We can do better than that.
* The idea is: We do `Pruned In-Order Traversal`.
* `Prune` means: To trim (cut) away dead or overgrown branches from trees to improve their growth or shape.
* So, while following `L-P-R` traversal, if at any point, the node is out of range, we discard the subtree.
* In case of the `nextLarger` approach, we had a single normal human being.
* In case of the `Pruned In-Order Traversal,` we hire a mutant.
* The mutant has a super-power of creating multiple self-cloning (recursion).
* But the mutant is also lazy!
* The mutant does not visit more than 1 node!
* Instead of visiting each node by himself, the mutant creates clones and follows `In-Order: L-P-R`.
* Now, the mutant is so lazy (or smart) that before creating clones, he checks: Do I need to?
* Because if the `mutant.key < x`, then entire left-subtree will be even smaller, making it out of range.
* So, to continue on the left-subtree, `mutant.key > x`.
* For example, the mutant at node `4` does not create any `left-side clone` as he finds everything is small down there.
* Similarly, if the `mutant.key > y`, the entire right-subtree will be larger, making it out of range.
* So, to continue on the right side, `mutant.key < y`.
* For example, the mutant at node `30` does not create any `right-side clone` as he finds everything is large down there.
* The mutant that creates a clone, becomes the boss mutant.
* If there is a left-side, the boss mutant creates a left-side clone and sends them to the left-side.
* Once the left-side clone finishes the travelling, they call the boss mutant (returns the result) and disappears.
* Yes, once the mutant calls the boss (returns the result), he disappears (Garbage collected). 
* Then, the boss mutant processes the occupied node (does his job of comparison for the `rangeSearch`).
* And then the boss mutant creates a right-side clone.
* Once the right-side clone finishes the job, they call the boss mutant (returns the result) and disappears.
* And then the boss mutant calls his boss mutant, and disappears.
* Now, the mutant doesn't even need to `find` the lower-end boundary of the range.
* The mutant starts with the root, `7`. 
* And for our example, after `7`, it will be the `right-side clone` who will visit `13`.
* `13` creates a `left-side clone` (recursion) that visits `10`.
* `10` creates a `left-side clone` (recursion) that visits `8`.
* `8` does not create any clone, finishes the job, and calls (returns the result) to the boss mutant `10`.
* `10` processes the occupied node and creates a `right-side clone` (recursion) that visits `11`.
* `11` does not create any clone, finishes the job, and calls (returns the result) to `10`.
* `10` calls (returns the result) to `13`.
* Each mutant only visits a single node and sends clones for other nodes.
* Clones send results to the boss mutant.
* So, we don't revisit (re-travel, re-touch) a single node twice!
* But yes, we pay the price of: Clones (recursion stack).

**Code Translation**

```kotlin

fun rangeSearch(xLeftLimit: Int, yRightLimit: Int): List<Int> {
    val results = mutableListOf<Int>()
    fun rangeSearchHelper(node: Node?, xLeftLimit: Int, yRightLimit: Int) {
        if (node == null) return
        // The `Left` part of `Left-Parent-Right`.
        // Don't worry. We follow `L-P-R`.
        // So, even though we know that `node.key > xLeftLimit`, we will process it after finishing the `L` part.
        // Before proceeding the left part, check if the left subtree is within the range.
        if (node.key > xLeftLimit) {
            rangeSearchHelper(node.left, xLeftLimit, yRightLimit)
        }
        // The `P` part of `L-Parent-R`.
        if (node.key >= xLeftLimit && node.key <= yRightLimit) {
            results.add(node.key)
        }
        // Before proceeding the right subtree, check if the right subtree is within the range.
        if (node.key < yRightLimit) {
            rangeSearchHelper(node.right, xLeftLimit, yRightLimit)
        }
    }
    // Handle edge cases
    if (xLeftLimit > yRightLimit) {
        return results
    }
    rangeSearchHelper(rootNode, xLeftLimit, yRightLimit)
    return results
}
```

## Insert

![70bstInsert.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/70bstInsert.png)

* Suppose, we want to insert a node with key `4`.
* We want to insert a new node.
* So, we need to create a new node.

**Code Translation**

```kotlin
val newNode = Node(key = 4)
```

**Initial base conditions**

* If the `rootNode` is `null`, the `newNode` becomes the `rootNode` and we are done.

**Code Translation**

```kotlin

if (rootNode == null) {
    rootNode = newNode
    return
}
```

**What if the `rootNode` is not `null`?**

* In that case, we need to find the correct `parent` under which the `newNode` should fit.

**What happens to the existing `child` of the `parent` under which the `newNode` would fit?**

* This is the magic of the binary search tree!
* The correct spot (position, location) will always be `null` for the `newNode` in a valid binary search tree.
* The `newNode` that we insert always ends up being a `leaf` node.

**How do we insert the `newNode`?**

* We follow the [find](#find-search) approach to travel the tree.
* When the `currentNode` becomes `null` (when we exit the `while` loop), we fit the `newNode` right there.
* But we cannot fit (float!) the `newNode` in the air! 
* We need a `parentNode` under which we can attach the `newNode`.
* And the `parentNode` is the node right before the `currentNode` became `null`. 
* It means that we need to keep track of the `parentNode` as well along with the `currentNode`.
* Because `currentNode` tells the spot where we fit the `newNode` and the `parentNode` holds the `newNode`.
* Now, as we can see in the [find](#find-search), inside the `while` loop, the `currentNode` is not `null`.
* So, we can assign the `parentNode` inside the `while` loop.

**Code Translation**

```kotlin
val newNode = Node(key = 4)
var currentNode = rootNode
var parentNode = currentNode
while (currentNode != null) {
    parentNode = currentNode
    when {
        newNode.key == currentNode.key -> return
        newNode.key > currentNode.key -> currentNode = currentNode.right
        newNode.key < currentNode.key -> currentNode = currentNode.left
    }
}
newNode.parent = parentNode
if (newNode.key > parentNode.key) {
    parentNode.right = newNode
} else {
    parentNode.left = newNode
}
```

* So, the full pseudocode is as below:

```kotlin

fun insert(key: Int, rootNode: Node?) {
    val newNode = Node(key)
    if (rootNode == null) {
        rootNode = newNode
        return
    }
    var currentNode = rootNode
    var parentNode = currentNode
    while (currentNode != null) {
        parentNode = currentNode
        when {
            key == currentNode.key -> return
            key < currentNode.key -> currentNode = currentNode.left
            key > currentNode.key -> currentNode = currentNode.right
        }
    }
    newNode.parent = parentNode
    if (key < parentNode.key) {
        parentNode.left = newNode
    } else {
        parentNode.right = newNode
    }
}
```

## Delete

* While performing the `delete` operation, we need to ensure and keep the tree a valid BST.
* We can keep the tree a valid binary search tree by taking care of (by handling):
  * Parent
  * Left Subtree
  * Right Subtree
* Suppose the node we want to delete is `nodeToDelete`.
* Now, when we delete a node, what gets affected?
  * The parent of the `nodeToDelete`
  * The left subtree of the `nodeToDelete`
  * And the right subtree of the `nodeToDelete`
* So, we need to handle those `3` pointers, along with the edge cases.
* What are the edge cases?
  * Maybe, the `nodeToDelete` is `null`. 
  * Maybe, the `nodeToDelete` is the `rootNode`!

**How do we manage the pointers?**

```kotlin

// The `nodeToDelete` is `null`.
if (nodeToDelete == null) {
    return
}

// The `nodeToDelete` is the `rootNode`.
if (nodeToDelete.parent == null) {
    rootNode = null
}

```

**Case: 01: `nodeToDelete` does not have any children** 

![80bstDelete01.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete01.png)

* In this case, we just disconnect `nodeToDelete` and its parent.
* So, it becomes:

```kotlin

// If `nodeToDelete` does not have any children
if (nodeToDelete.left == null && nodeToDelete.right == null) {
    // If `nodeToDelete` is a left child 
    if (nodeToDelete == nodeToDelete.parent.left) {
        nodeToDelete.parent.left = null
    } else {
        nodeToDelete.parent.right = null
    }
    nodeToDelete = null
}

```

**Case: 02: `nodeToDelete` has a single child**

![80bstDelete02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete02.png)

* In this case, the child of `nodeToDelete` takes the place of `nodeToDelete`.
* Now, either the `nodeToDelete` is a left-child or a right-child of the parent.
* Also, either the `nodeToDelete` has a left-child or a right-child.
* So, the pseudocode becomes:

```kotlin

// If `nodeToDelete` is a left child of the parent
if (nodeToDelete == nodeToDelete.parent.left) {
    // If `nodeToDelete` has only a right child
    if (nodeToDelete.left == null && nodeToDelete.right != null) {
        // The parent of `nodeToDelete` points to the child of `nodeToDelete`
        nodeToDelete.parent.left = nodeToDelete.right
        // Needs to change the parent of `nodeToDelete.right`
        nodeToDelete.right.parent = nodeToDelete.parent 
        // If `nodeToDelete` has only a left child
    } else if (nodeToDelete.right == null && nodeToDelete.left != null) {
        // The parent of `nodeToDelete` points to the child of `nodeToDelete`
        nodeToDelete.parent.left = nodeToDelete.left
        // Needs to change the parent of the `nodeToDelete.left`
        nodeToDelete.left.parent = nodeToDelete.parent
    }
    // If `nodeToDelete` is a right child of the parent
} else if (nodeToDelete == nodeToDelete.parent.right) {
    // If `nodeToDelete` has only a right child
    if (nodeToDelete.left == null && nodeToDelete.right != null) {
        // The parent of `nodeToDelete` points to the child of `nodeToDelete`
        nodeToDelete.parent.right = nodeToDelete.right
        // Needs to change the parent of the `nodeToDelete.right`
        nodeToDelete.right = nodeToDelete.parent
        // If `nodeToDelete` has only a left child
    } else if (nodeToDelete.right == null && nodeToDelete.left != null) {
        // The parent of `nodeToDelete` points to the child of `nodeToDelete`
        nodeToDelete.parent.right = nodeToDelete.left
        // Needs to change the parent of the `nodeToDelete.left`
        nodeToDelete.left.parent = nodeToDelete.parent
    }
}
```
* In two sentences, when `nodeToDelete` has only one child, the `parent` of `nodeToDelete` points to the `child` of the `nodeToDelete`.
* And the child of `nodeToDelete` points to the `nodeToDelete.parent`.
* And in one sentence, the `nodeToDelete` is replaced by its child.
* So, let us assume the replacement code where we get two parameters: `nodeToDelete`, and `childOfNodeToDelete`.
* So, the pseudocode might look like follow:

```kotlin

fun replaceNode(nodeToDelete: Node?, childOfNodeToDelete: Node?) {
    if (nodeToDelete == null) return
    if (nodeToDelete == rootNode) {
        rootNode = childOfNodeToDelete
        childOfNodeToDelete?.parent = null
        return
    }
    val parent = nodeToDelete?.parent
    if (nodeToDelete == parent?.left) {
        parent?.left = childOfNodeToDelete
    } else {
        parent?.right = childOfNodeToDelete
    }
    childOfNodeToDelete?.parent = parent
    nodeToDelete = null
}
```

**Case: 03: `nodeToDelete` has two children**

![80bstDelete03.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete03.png)

* Suppose, we want to delete `13`.
* Then, we would first replace `13` with its `nextLarger` node. (`nodeToDelete = nextLargerNode`)
* And if the next larger node has any right child, then it will take the vacant place of its parent - as its parent has moved to take place of `13`.
* So, the pseudocode might look like follow:

```kotlin

fun delete(key: Int) {
    val deletingNode = find(key, rootNode)
    if (deletingNode != null) {
        val nextLargerNode = nextLarger(node)
        val rightChild = nextLargerNode.right
        deletingNode = nextLargerNode
        replace(nextLargerNode, nextLargerNode.right)
    }
}
```

**The complete delete pseudocode**

```kotlin

fun delete(nodeToDelete: Node?) {
    if (nodeToDelete == null) return
    fun replace(nodeToDelete: Node?, childOfDeletingNode: Node?) {
        if (nodeToDelete == null) return
        // `nodeToDelete` is a `rootNode`
        if (nodeToDelete?.parent == null) {
            rootNode = childOfDeletingNode
            nodeToDelete = null
            return
        }
        val parent = nodeToDelete?.parent
        if (nodeToDelete == parent?.left) {
            parent?.left = childOfDeletingNode
        } else {
            parent?.right = childOfDeletingNode
        }
        childOfDeletingNode?.parent = parent
        nodeToDelete = null
    }
    
    val deletingNode = find(nodeToDelete)
    
    // `deletingNode` might have only right child
    if (deletingNode?.left == null) {
        replace(deletingNode, deletingNode.right)
    } else if (deletingNode?.right == null) {
        // `deletingNode` might have only left child
        replace(deletingNode, deletingNode?.left)
    } else {
        // `deletingNode` has two children (left and right)
        val nextLarger = nextLarger(deletingNode)
        if (nextLarger != null) {
            deletingNode.key = nextLarger.key
            // By the definition of `nextLarger,` it cannot have the `left child`.
            replace(nextLarger, nextLarger.right)
        }
    }
}

```

## Summary

### Find(key, node = root)

* Takes `key` and `node` with default value `root` if not given already.
* Start with the root.
* Use binary search.
* when:
* key == node.key -> return node
* key > node.key -> node = node.right
* key < node.key -> node = node.left

### NextLarger(key: Int)

* Start with root
* Two cases: (1) Has a right child (2) No right child
* (1) Has a right child: 
* Go to the right side once and then keep going to the left-side
* `while(current?.left != null) { current = current.left }`
* (2) No right child:
* Go through the parents (Travel upwards through the parents)
* `while(parent != null && current == parent.right)`
* Return the `parent` for which `current == parent.left` or `parent == null`.

### RangeSearch(x, y)

* Uses the pruned binary search with the help of `recursion`
* Start with the root
* Follow `L-P-R-In-Order-Traversal`
* If `node.key < x`, then the entire left-subtree is smaller than `x`.
* So, `node.key` needs to be larger than `x` to continue the left side. 
* `if (node.key > x) { rangeSearch(x, y, node.left) }`
* `if (node.key >= x && node.key <= y) { results.add(node.key) }`
* If `node.key > y`, then the entire right-subtree will be larger than `y`.
* So, to continue on the right-subtree, `node.key` must be smaller than `y`.
* `if (node.key < y) { rangeSearch(x, y, node.right) }`

### Insert(key, node = root)

* Uses binary search
* Start with the root node
* Need to keep track of the parent node also, because the last node will be null
* Otherwise, it is almost similar to the [find](#find-search)
* `while(current != null) { current = parent, parent = parent.node }`
* `current` becomes `null` and that is the location where the new node will be inserted
* Use `parent` to connect `parent.left = newNode` or `parent.right = newNode` and `newNode.parent = parent`.

### Delete(key)

* 3 cases: 2 methods
* case 1: 0 child (Simply, disconnect)
* case 2: 1 child (replace: The child takes the place of the `nodeToDelete`)
* case 3: 2 children (2 steps)
* (1) `nextLarger`: `nodeToDelete.key = nextLarger.key`
* (2) Follow the case 2 for the `nextLarger`.

## Next

* [Binary Tree Implementation](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)
* [BST: Self-Balancing = AVLTree](15binarySearchTreesBSTBalance.md)
* [AVLTree: Insert Operation](20avlTreeInsertOperation.md)
* [AVLTree: Delete Operation](25avlTreeDeleteOperation.md)
* [AVLTree: Implementation](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/020avlTreeImplementation.kt)
* [AVLTree: Merge Operation](30avlTreeMergeOperation.md)
* [AVLTree: Split Operation](40avlTreeSplitOperation.md)
* [AVLTree: Kth Small Key](50avlTreeFindKthSmallKey.md)
* [Flip (Replace) Using An AvlTree](60flipReplaceWithAvlTree.md)
* [Splay Trees](70splayTrees.md)
* [Red-Black Trees](80redBlackTrees.md)
* 