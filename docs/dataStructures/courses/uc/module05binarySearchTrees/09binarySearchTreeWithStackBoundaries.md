# Check if the given binary tree is a valid binary search tree

<!-- TOC -->
* [Check if the given binary tree is a valid binary search tree](#check-if-the-given-binary-tree-is-a-valid-binary-search-tree)
  * [Problem](#problem)
    * [Solution: Thought Process](#solution-thought-process)
    * [Illustration](#illustration)
    * [Time Complexity](#time-complexity)
    * [Space Complexity](#space-complexity)
    * [Code](#code)
<!-- TOC -->

> left subtree < parent <= right subtree 

## Problem

### Solution: Thought Process

* We use the given rule to create boundaries for each node.
* We push a node along with its boundaries.
* When we pop, we compare the node key with its boundaries.
* We can follow any traversal order to cover the entire tree.
* Here, we will follow the `Pre-Order` traversal.

### Illustration

![810validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/810validateBstBinarySearchTree.png)

![820validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/820validateBstBinarySearchTree.png)

![830validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/830validateBstBinarySearchTree.png)

![840validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/840validateBstBinarySearchTree.png)

![850validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/850validateBstBinarySearchTree.png)

![860validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/860validateBstBinarySearchTree.png)

![870validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/870validateBstBinarySearchTree.png)

![880validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/880validateBstBinarySearchTree.png)

![890validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/890validateBstBinarySearchTree.png)

![900validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/900validateBstBinarySearchTree.png)

![910validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/910validateBstBinarySearchTree.png)

![920validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/920validateBstBinarySearchTree.png)

![930validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/930validateBstBinarySearchTree.png)

![940validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/940validateBstBinarySearchTree.png)

![950validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/950validateBstBinarySearchTree.png)

![960validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/960validateBstBinarySearchTree.png)

![970validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/970validateBstBinarySearchTree.png)

### Time Complexity

### Space Complexity

### Code