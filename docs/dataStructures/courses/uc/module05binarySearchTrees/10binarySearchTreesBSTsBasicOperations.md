# Binary Search Trees (BSTs): Basic Operations

<!-- TOC -->
* [Binary Search Trees (BSTs): Basic Operations](#binary-search-trees-bsts-basic-operations)
  * [Find (Search)](#find-search)
  * [Next (Adjacent Element)](#next-adjacent-element)
  * [Range Search](#range-search)
  * [Insert](#insert)
  * [Delete](#delete)
<!-- TOC -->

## Find (Search)

![10bstFindIntro.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/12bstFindIntro.png)

* We start with the root node. 
* So, `currentNode = rootNode`.
* If the key we want to find is equal to the root key, we return the root.
* Otherwise, if the `key > currentNode.key`, we need to go to the right side (direction) of the current node.
  * But only if the `currentNode` has a right child! 
* If the `key < currentNode.key`, we need to go left side of the current node.
  * But only if the `currentNode` has a left child!
* If we can't find the given key, the last station (node) is the closest one where the key would come.

```kotlin

fun find(key: Int, rootNode: Node): Node {
    if (rootNode != null && rootNode.key == key) {
        return rootNode
    }
    var currentNode = rootNode
    if (key > currentNode.key) {
        return if (currentNode.right != null) {
            find(key, currentNode)
        } else {
            currentNode
        }
    } else if (key < currentNode.key) {
        return if (currentNode.left != null) {
            find(key, currentNode.left)
        } else {
            currentNode
        }
    }
}

```

## Next (Adjacent Element)

## Range Search

## Insert

## Delete

