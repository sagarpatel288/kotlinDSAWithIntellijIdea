# The `delete` operation in an AVL-Tree

<!-- TOC -->
* [The `delete` operation in an AVL-Tree](#the-delete-operation-in-an-avl-tree)
  * [Prerequisites/References:](#prerequisitesreferences)
  * [Delete Example](#delete-example)
    * [No Child](#no-child)
    * [1 Child](#1-child)
    * [2 Children](#2-children)
  * [Next:](#next)
<!-- TOC -->

## Prerequisites/References:

## Delete Example

### No Child

![80bstDelete01.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete01.png)

### 1 Child

![80bstDelete02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/80bstDelete02.png)

### 2 Children

![390avlTreeDelete.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/390avlTreeDelete.svg)

* When the node that we want to delete has 2 children (left and right), we replace "**nodeToDelete.key**" with "**nextLarger.key**".
* At this moment, we have two duplicate keys (`nodeToDelete.key == nextLarger.key`).
* So, we delete the `nextLarger` node and its right child takes its vacant place.
* This process might change the height and balance factor of `nodeToDelete.right`.
* In that case, we update the height of the ancestor nodes, establish the right connection, and also perform the relevant rotations as and when required.
* For example:
* `nodeToDelete.right = delete(nodeToDelete.right, nextLarger.key)`.
* The `delete` operation should handle updating the height and ensuring the balance in the AVL-Tree.

## Next:

* [010avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/020avlTreeImplementation.kt)
* [AVLTree: Merge Operation](30avlTreeMergeOperation.md)
* [AVLTree: Split Operation](40avlTreeSplitOperation.md)
* [AVLTree: Kth Small Key](50avlTreeFindKthSmallKey.md)
* [Flip (Replace) Using An AvlTree](60flipReplaceWithAvlTree.md)
* [Splay Trees](70splayTrees.md)
* [Red-Black Trees](80redBlackTrees.md)