# AVL-Tree: Merge Operation

<!-- TOC -->
* [AVL-Tree: Merge Operation](#avl-tree-merge-operation)
  * [Prerequisites/References:](#prerequisitesreferences)
  * [Having almost the same height](#having-almost-the-same-height)
  * [One tree has more height than the other](#one-tree-has-more-height-than-the-other)
  * [Process](#process)
    * [Prerequisites/Previously/References](#prerequisitespreviouslyreferences)
    * [Back to the present](#back-to-the-present)
  * [Process: TL;DR](#process-tldr)
  * [Pseudocode](#pseudocode)
  * [ToDo](#todo)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References:

## Having almost the same height

![410avlTreeMerge.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/410avlTreeMerge.svg)

## One tree has more height than the other

![420avlTreeMergeCase02.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/420avlTreeMergeCase02.svg)

![430avlTreeMergeCase03.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/430avlTreeMergeCase03.svg)

## Process

* We need a function that takes two trees that we want to merge.
* And we don't pass the entire trees.
* We can pass the roots of the trees.
* We can travel and play with the trees using their roots.

```kotlin

private fun merge(lightTreeRoot: AvlNode?, heavyTreeRoot: AvlNode): AvlTree {
    // The `merge` function needs to return a merged `AvlTree`.
    // So, we create a new `AvlTree` and call it `mergedAvlTree`.
    val mergedAvlTree = AvlTree()
    // Base cases. Edge cases.
    if (lightTreeRoot == null) {
        // The lightTreeRoot is null.
        // It means that there is no lightTree.
        // But we can't simply directly return the `heavyTreeRoot`, because it is an `AvlNode`.
        // We need to return a merged `AvlTree`.
        // So, we create a new AvlTree.
        // We call it a `mergedTree`.
        // And we make the given `heavyTreeRoot` the root node of the `mergedTree`.
        mergedAvlTree.root = heavyTreeRoot
        // And then we return the `mergedTree`.
        return mergedAvlTree
    }
    if (heavyTreeRoot == null) {
        mergedAvlTree.root = lightTreeRoot
        return mergedAvlTree
    }
}

```

* Now, what if none of the given nodes are null?
* So, both the given nodes are non-null.
* In that case, we need to find the pivot on which we can hang two trees.
* The `pivot` is always the rightmost node of the light tree.
* The `rightmost` node is the `max` node of the light tree.
* So, we can call it a `findMax` operation.

```kotlin

private fun findMax(node: AvlNode?): AvlNode? {
    if (node == null) return null
    var curr = node
    while (curr?.right != null) {
        curr = curr.right
    }
    // This is the rightmost child.
    // If we call `findMax` and pass the root node of a light tree, then `curr` will be the `pivot`.
    return curr
}

```

* To make the `rightmost` node a `pivot`, we need first to delete it from its original place.
* So, we can call it a `deleteMax` operation.
* Now, how do we find the rightmost node of the tree? 
* And how do we take care of its left child, if it has any?

### Prerequisites/Previously/References

* Recall the `delete` operation we have seen earlier.
* The `delete` operation where the node has 1 child.

![80bstDelete02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete02.png)

* The image shows a `delete` operation in a binary search tree.
* And we have a balanced binary search tree: AVLTree.
* Also, we have seen the `delete` operation in `AVLTreeImplementation`.

[010avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)

### Back to the present

* So, the `deleteMax` function might look like:

```kotlin

private fun deleteMax(node: AvlNode?): AvlNode? {
    if (node == null) return null
    if (node.right != null) {
        node.right = delete(node.right)
    } else {
        // There is no more `node.right` now.
        // It means that this is the `rightmost` node that we want to delete and make it a pivot.
        // It's left child will be attached in the `if` block as:
        // node.right = node.left
        // This way, we took care of the left child of the `rightmost` node.
        return node.left
    }
}

```

* We found the max of the light tree.
* We deleted it.
* Now, we make it a pivot.

```kotlin
// ...continue in the `merge` function
val rightMost = findMax(lightTreeRoot)
// Using the `rightMost` node's key, we create a new node `pivot`.
// A new node because we cannot reuse the left, right, and height properties of the `rightMost`.
// Creating a new node will set the correct properties for our pivot.
// For the pivot, left and right will be null, and height will be 1. 
val pivot = AvlNode(rightMost.key) 
val lightTree = deleteMax(lightTreeRoot)
```


* Now, when it comes to `merge,` check the heights of both the trees.
* If the height of both the trees is the same, then:

```kotlin
pivot.left = lightTree
pivot.right = rightTree
```

* So, it is clear that our function needs: The light tree, the right tree, and the pivot.
* Now, we don't need to pass the entire tree. 
* We can pass the root of the tree instead of the entire tree.
* When we have a reference to the root, we can travel and play with the entire tree.
* So, the function might look like:

```kotlin

private fun mergeTwoAvlTrees(lightTree: AvlNode?, heavyTree: AvlNode?, pivot: AvlNode?): AvlNode? {
    
    if (abs(height(lightTree) - height(heavyTree)) <= 1) {
        pivot.left = lightTree
        pivot.right = heavyTree
        // We just attached two trees to our pivot.
        // So, we need to update the height of our pivot.
        updateHeight(pivot)
        // We don't need to rebalance the pivot.
        // Because the `if` condition says that the height difference is already controlled-balanced.
        // So, the pivot cannot be an unbalanced node.
        return pivot
    }
}

```

* If the light tree is taller, then travel right to find the subtree whose height is almost equivalent ($\pm1$) to the height of the other tree.
  * How to remember? Light = Right.
  * So, we pass `lightTree.right` to our `mergeAvlTrees` function.
  * This recursive process continues until we get the height difference in controlled-balanced.

```kotlin

private fun mergeTwoAvlTrees(lightTree: AvlNode?, heavyTree: AvlNode?, pivot: AvlNode?): AvlNode? {
    
    if (abs.(height(lightTree) - height(heavyTree)) <= 1) {
        pivot.left = lightTree
        pivot.right = heavyTree
        updateHeight(pivot)
        return pivot
    } else if (height(lightTree) > height(heavyTree)) {
        // The light tree is taller.
        // Light = Right.
        // We keep going to the right side of the light tree until we find a subtree whose height equals `height(heavyTree) + 1`.
        // When we reach that case, we fall into the first `if` condition.
        // The first `if` condition returns the `pivot`.
        // The `lightTree.right` assignment `=` attaches the `pivot` returned by the above `if` condition to the right side of the `light tree`.
        // The `pivot.left` has a light subtree and the `pivot.right` has the heavy tree.
        // It means that after attaching the `pivot`, the parent of the `pivot` must be updated and balanced.
        // And the parent of the `pivot` is the `lightTree` node.
        // So, we rebalance the `lightTree`.
        lightTree.right = mergeTwoAvlTrees(lightTree.right, heavyTree, pivot)
        return rebalance(lightTree)
    }
}

```

* If the heavy tree is taller, then travel left to find the subtree whose height is almost equivalent ($\pm1$) to the height of the other tree.
  * How to remember? Heavy lifting = Left.
  * So, we keep going towards the left side of the heavy tree until the height of the heavy subtree is `height(lightTree) + 1`.
  * Once we get that situation, we merge the left heavy subtree and the light tree.
* Merge these two trees whose height difference is controlled and balanced.
* So:

```kotlin

private fun mergeTwoAvlTrees(lightTree: AvlNode?, heavyTree: AvlNode?, pivot: AvlNode): AvlNode {
    
    if (abs(height(lightTree) - height(heavytree)) <= 1) {
        pivot.left = lightTree
        pivot.right = heavyTree
        updateHeight(pivot)
        return pivot
    } else if (height(lightTree) > height(heavyTree)) {
        lightTree.right = mergeTwoAvlTrees(lightTree.right, heavyTree, pivot)
        return rebalance(lightTree)
    } else {
        heavyTree.left = mergeTwoAvlTrees(lightTree, heavyTree.left, pivot)
        return rebalance(heavyTree)
    }
}

```

* Now, if we remember, we passed the roots of the light tree and the heavy tree to this `mergeTwoAvlTrees` function.
* It returns a balanced `AvlNode`.
* And the rebalancing process might have performed some rotations.
* And the rotation can change the original root.
* So, the node returned by the `mergeTwoAvlTrees` function is the final root.

```kotlin
//...continue in the `merge` function
val newRoot = mergeTwoAvlTrees(lightTree, heavyTree, pivot)
val mergedTree = AvlTree()
mergedTree.root = newRoot
return mergedTree
```


## Process: TL;DR

* The `merge` function takes two roots: `lightTreeRoot` and `heavyTreeRoot`.
* The `merge` function returns a merged `AvlTree`.
* To return a merged `AvlTree`, we need to create one.
* This `AvlTree` has to have a `root` node.
* If one of the two given roots of the `AvlTrees` is `null`:
```kotlin
mergedAvlTree.root = nonNullRoot // The root node that is non-null
```
* Otherwise, we find a `pivot`.
* The `pivot` node is always the rightmost node of the light tree.
* To find a `pivot`, we use `findMax` and `deleteMax` on the light tree.
* Once we find the `pivot`, we pass it as an argument to the `mergeTwoAvlTrees` along with the given two root nodes.
* The `mergeTwoAvlTrees` function is a recursive function.
* We keep passing either `lightTree.right` or `heavyTree.left` to the `mergeTwoAvlTrees`, depending upon which tree is taller, until we get the controlled and balanced height difference.
* We initially passed the root nodes to the `mergeTwoAvlTrees`.
* So, it returns a balanced root node.
* We assign this balanced root node to our `mergedAvlTree` as a root node.
```kotlin
mergedAvlTree.root = mergeTwoAvlTrees(lightTree, heavyTree, pivot)
```

## Pseudocode

## ToDo

* Show why naive ways do not work - why they break the AVL Properties.
* Also add the case where `Tree2` is larger than the `Tree1`.

## Next