# Binary Search Trees (BSTs): Basic Operations

<!-- TOC -->
* [Binary Search Trees (BSTs): Basic Operations](#binary-search-trees-bsts-basic-operations)
  * [Find (Search)](#find-search)
  * [Next (Adjacent Element, Next Largest)](#next-adjacent-element-next-largest)
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

## Next (Adjacent Element, Next Largest)

![35bstNextLargerAdjacent01.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/35bstNextLargerAdjacent01.png)

![40bstNextLargerAdjacent02.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/40bstNextLargerAdjacent02.png)

![45bstNextLargerAdjacent03.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/45bstNextLargerAdjacent03.png)

* Suppose we want to find `nextLarger(N)`.
* If we have a right child of `N`, then:
  * Go to the right side once.
  * And then keep going to the left side until we hit the end.
* If `N` does not have a right child, then:
  * Keep going upwards through parents until we find the larger key than `N`.

## Range Search

## Insert

## Delete

