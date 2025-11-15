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
* Make it a root of the light tree.
* We do not allow duplicate keys.
* So, delete the original `pivot` from the rightmost place in the light tree.
* Now, when it comes to `merge,` check the heights of both the trees.
* If the height of both the trees is the same, then:

```kotlin
pivot.left = lightTree
pivot.right = rightTree
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