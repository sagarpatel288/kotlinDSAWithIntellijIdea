# AVL-Tree: Merge Operation

<!-- TOC -->
* [AVL-Tree: Merge Operation](#avl-tree-merge-operation)
  * [Prerequisites/References:](#prerequisitesreferences)
  * [Having almost the same height](#having-almost-the-same-height)
  * [One tree has more height than the other](#one-tree-has-more-height-than-the-other)
  * [Process](#process)
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

* Find the pivot.
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

private fun mergeAvlTrees(lightTree: AvlNode?, heavyTree: AvlNode?, pivot: AvlNode?): AvlNode? {
    
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
* If the heavy tree is taller, then travel left to find the subtree whose height is almost equivalent ($\pm1$) to the height of the other tree.
  * How to remember? Heavy lifting = Left.
* Merge these two trees of the same height.
* So:

```kotlin
pivot.left = lightTree
pivot.right = rightTree
```

* Now, we need to attach this `pivot` to the taller tree at its original place.
* So, if the light tree was taller, then we attach this `pivot` to the right side.
* Otherwise, we attach it to the left side.
* And then we need to rebalance the parent of this `pivot`.

## Pseudocode

### deleteMax

```kotlin

private fun deleteMax(node: AvlNode?): AvlNode? {
    if (node == null) return null
    if (node.right != null) {
        node.right = deleteMax(node.right)
    } else {
        return node.left
    }
    return rebalance(node)
}

```

## ToDo

* Show why naive ways do not work - why they break the AVL Properties.
* Also add the case where `Tree2` is larger than the `Tree1`.

## Next