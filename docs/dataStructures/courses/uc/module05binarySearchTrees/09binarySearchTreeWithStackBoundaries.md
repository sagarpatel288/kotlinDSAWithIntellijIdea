# Check if the given binary tree is a valid binary search tree

<!-- TOC -->
* [Check if the given binary tree is a valid binary search tree](#check-if-the-given-binary-tree-is-a-valid-binary-search-tree)
* [Problem](#problem)
  * [Is it a binary search tree? (Modified rules).](#is-it-a-binary-search-tree-modified-rules)
  * [Problem Introduction](#problem-introduction)
  * [Problem Description](#problem-description)
  * [Task](#task)
  * [Input Format](#input-format)
  * [Constraints](#constraints)
  * [Output Format](#output-format)
    * [Time Limit](#time-limit)
    * [Memory Limit](#memory-limit)
    * [Solution: Thought Process](#solution-thought-process)
    * [Time Complexity](#time-complexity)
    * [Space Complexity](#space-complexity)
    * [Code](#code)
<!-- TOC -->

> left subtree < parent <= right subtree 

# Problem

## Is it a binary search tree? (Modified rules).

## Problem Introduction

* In this problem you are going to solve the same problem as the previous one, but for a more general case, when binary search tree may contain equal keys.

## Problem Description

## Task

* You are given a binary tree with integers as its keys.
* You need to test whether it is a correct binary search tree.
* Note that there can be duplicate integers in the tree, and this is allowed.
* The definition of the binary search tree in such case is the following:
* For any node of the tree, if its key is 𝑥, then for any node in its left subtree its key must be strictly less than 𝑥, and for any node in its right subtree its key must be greater than or equal to 𝑥.
* In other words, smaller elements are to the left, bigger elements are to the right, and duplicates are always to the right.
* You need to check whether the given binary tree structure satisfies this condition.
* You are guaranteed that the input contains a valid binary tree.
* That is, it is a tree, and each node has at most two children.

## Input Format

* The first line contains the number of vertices 𝑛.
* The vertices of the tree are numbered from 0 to 𝑛 − 1.
* Vertex 0 is the root.
* The next 𝑛 lines contain information about vertices 0, 1, ..., 𝑛−1 in order.
* Each of these lines contains three integers 𝑘𝑒𝑦𝑖, 𝑙𝑒𝑓𝑡𝑖 and 𝑟𝑖𝑔ℎ𝑡𝑖.
* 𝑘𝑒𝑦𝑖 is the key of the 𝑖-th vertex, 𝑙𝑒𝑓𝑡𝑖 is the index of the left child of the 𝑖-th vertex, and 𝑟𝑖𝑔ℎ𝑡𝑖 is the index of the right child of the 𝑖-th vertex.
* If 𝑖 doesn’t have left or right child (or both), the corresponding 𝑙𝑒𝑓𝑡𝑖 or 𝑟𝑖𝑔ℎ𝑡𝑖 (or both) will be equal to −1.

## Constraints

* $0 ≤ 𝑛 ≤ 10^5$;
* $−2^{31} ≤ 𝑘𝑒𝑦𝑖 ≤ 2^{31} − 1$;
* $−1 ≤ 𝑙𝑒𝑓𝑡𝑖, 𝑟𝑖𝑔ℎ𝑡𝑖 ≤ 𝑛 − 1$.
* It is guaranteed that the input represents a valid binary tree.
* In particular, if 𝑙𝑒𝑓𝑡𝑖 != −1 and 𝑟𝑖𝑔ℎ𝑡𝑖 != −1, then 𝑙𝑒𝑓𝑡𝑖 != 𝑟𝑖𝑔ℎ𝑡𝑖.
* Also, a vertex cannot be a child of two different vertices.
* Also, each vertex is a descendant of the root vertex.
* Note that the minimum and the maximum possible values of the 32-bit integer type are allowed to be keys in the tree — beware of integer overflow!

## Output Format

* If the given binary tree is a correct binary search tree (see the definition in the problem description), output
* one word “CORRECT” (without quotes).
* Otherwise, output one word “INCORRECT” (without quotes).

### Time Limit

```markdown
| language   	| C 	| C++ 	| Java 	| Python 	| C# 	| Haskell 	| JavaScript 	| Ruby 	| Scala 	|
|------------	|---	|-----	|------	|--------	|----	|---------	|------------	|------	|-------	|
| time (sec) 	| 2 	| 2   	| 3    	| 10     	| 3  	| 4       	| 10         	| 10   	| 6     	|
```

### Memory Limit

* 512 MB

### Solution: Thought Process

* We cannot use the previous solution: [Validate BinarySearchTree](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/015validBstBinarySearchTree.kt).
  * Because how do we compare and validate the case where a deep node from the right subtree can be equal to the anscestor, where the ancestor doesn't have to be an immediate parent.
  * The ancestor can be a grand-parent or grand-grand-parent, and so on.
  * In the previous solution, we used a stack, and followed the `in-order` traversal.
  * Every time we pop a node, we compare it with the previous key and check that the recently popped key must be greater than the previous key.
  * And then, we update the previous key.
  * When we pop a node, we cannot know whether it is a left child or a right child or a deep right child to some really old ancestor.
  * All we get is a sorted list in ascending order.
  * So, we only know about the previous key, but not the relation.
  * In this new problem, we need to compare the value of a key with its parent to ensure that the value is within the given boundaries. 
* Here, we use the given rule to create boundaries for each node.
* We push a node along with its boundaries.
* When we pop, we compare the node key with its boundaries.
* We can follow any traversal order to cover the entire tree.
* Here, we will follow the `Pre-Order` traversal.
* For example, suppose we have the below tree to validate.

![09bstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/09bstBinarySearchTree.png)

* We start with the root node.

![810validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/810validateBstBinarySearchTree.png)

> Push 50 (key = 50, min = Long.MIN_VALUE, max = Long.MAX_VALUE)
* Initially, `min = Long.MIN_VALUE` and `max = Long.MAX_VALUE`.
* So, the root node value can be anything between these values.
* We push the root node with these boundaries.
* After pushing the root node, we immediately perform the pop operation.
---
> **Pop 50 (key = 50, min = Long.MIN_VALUE, max = Long.MAX_VALUE)**
* The pop operation gives us `key = 50, min = Long.MIN_VALUE, max = Long.MAX_VALUE`.
* We check if the `key = 50` is within its attached boundaries.
* `min = Long.MIN_VALUE`, and `50 >= min`.
* `max = Long.MAX_VALUE`, and `50 < max`.
* The condition is met. 
* So, the node (50) is a valid node and we can proceed further.
* Now, we want to follow the `Pre-Order` traversal to cover the tree.
* So, we first push the right child, and then the left child.
* Now, when we push the right child, we attach the associative boundaries with it.

![820validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/820validateBstBinarySearchTree.png)

* For example, the current boundaries we have from `50` is:
> `min = Long.MIN_VALUE`, and `max = Long.MAX_VALUE`.
---
> Push `50.rightChild` = 60 (key = 60, min = 50, max = Long.MAX_VALUE)
* Now, the right child of `50` must be greater than or equal to `50`.
* The right child of `50` must be at least (minimum) `50`.
* The lower bound of `50.rightChild` is `50`.
* So, we update the `min` when we push the right child of `50`.
* When we push `50.rightChild = 60`, we attach the following boundaries with it:
* `min = 50`, `max = Long.MAX_VALUE`.
* Now, whenever we pop `50.rightChild = 60`, we will compare its value against these boundaries.
* It must satisfy: `min <= 50.rightChild(=60) < Long.MAX_VALUE`.
---
> Push `50.leftChild` = 40 (key = 40, min = Long.MIN_VALUE, max = 50)
* After the right child, we push the left child.
* So, the next node we are going to push is `50.leftChild = 40`.
* It is the left child of `50`.
* So, it must be smaller than `50`.
* It means that we know the upper bound (max) of `50.leftChild = 40`.
* So, we update the `max` when we push the left child of `50`.
* When we push `50.leftChild`, we attach the following boundaries with it:
* `min = Long.MIN_VALUE`, `max = 50`.
---
* After pushing the right child followed by the left child, we perform the pop operation.

![830validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/830validateBstBinarySearchTree.png)

---
> **Pop 40 (key = 40, min = Long.MIN_VALUE, max = 50)**
* When we pop, we get `40` along with its attached boundaries.
* We use the attached boundaries to validate `40`.
* `min = Long.MIN_VALUE`, and `40 >= min`.
* `max = 50`, and `40 < max`.
* So, the condition `min <= key < max` is met.
* Hence, the node `40` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `40.rightChild` = 45 (key = 45, min = 40, max = 50)
* First, we push the right child of the popped node, `40`.
* The right child of `40` must be greater than or equal to `40`.
* The right child of `40` must be at least (min) `40`.
* The lower bound of `40.rightChild` is `40`.
* So, we update the `min` boundary to `40`.
* When we push `40.rightChild`, we attach the following boundaries with it.
* `min = 40, max = 50`
* After pushing the right child, we push the left child of the popped node.
---
> Push `40.leftChild` = 30 (key = 30, min = Long.MIN_VALUE, max = 40)
* We push the left child of `40`.
* The left child of `40` must be smaller than `40`.
* The upper bound (max) of `40.leftChild` is `40`.
* So, we update the `max` boundary to `40`.
* When we push `40.leftChild`, we attach the following boundaries with it.
* `min = Long.MIN_VALUE, max = 40`
* After pushing the left child, we perform the pop operation.
---
> **Pop 30 (key = 30, min = Long.MIN_VALUE, max = 40)**

![840validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/840validateBstBinarySearchTree.png)

* When we pop, we get `30`.
* We check it against its attached boundaries.
* `min = Long.MIN_VALUE`, and `30 >= min`.
* `max = 40`, and `30 < max`.
* The condition met.
* Hence, the node `30` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `30.rightChild` = 35 (key = 35, min = 30, max = 40)
* First, we push the right child of the popped node, `30`.
* The right child of `30` must be greater than or equal to `30`.
* The right child of `30` must be at least (min) `30`.
* The lower bound (min) of `30.rightChild` is `30`.
* So, we update the `min` to `30`.
* When we push `30.rightChild`, we attach the following boundaries with it:
* `min = 30, max = 40`
* After pushing the right child, we push the left child of the popped node.
---
> Push `30.leftChild` = 25 (key = 25, min = Long.MIN_VALUE, max = 30)
* We push the left child of `30`.
* The left child of `30` must be smaller than `30`.
* The upper bound (max) of `30.leftChild` is `30`.
* So, we update the `max` boundary to `30`.
* When we push `30.leftChild`, we attach the following boundaries with it:
* `min = Long.MIN_VALUE, max = 30`
* After pushing the left child, we perform the pop operation.
---
> **Pop 25 (key = 25, min = Long.MIN_VALUE, max = 30)**

![850validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/850validateBstBinarySearchTree.png)

* When we pop, we get `25`.
* We check it against its attached boundaries.
* `min = Long.MIN_VALUE`, and `25 >= min`.
* `max = 30`, and `25 < max`.
* The condition met.
* Hence, the node `25` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `25.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `25.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 35 (key = 35, min = 30, max = 40)**

![860validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/860validateBstBinarySearchTree.png)

* When we pop, we get `35`.
* We check it against its attached boundaries.
* `min = 30`, and `35 >= min`.
* `max = 40`, and `35 < max`.
* The condition met.
* Hence, the node `35` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `35.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `35.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 45 (key = 45, min = 40, max = 50)**

![870validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/870validateBstBinarySearchTree.png)

* When we pop, we get `45`.
* We check it against its attached boundaries.
* `min = 40`, and `45 >= min`.
* `max = 50`, and `45 < max`.
* The condition met.
* Hence, the node `45` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `45.rightChild` = 47 (key = 47, min = 45, max = 50)
* First, we push the right child of the popped node, `45`.
* The right child of `45` must be greater than or equal to `45`.
* The right child of `45` must be at least (min) `45`.
* The lower bound (min) of `45.rightChild` is `45`.
* So, we update the `min` to `45`.
* When we push `45.rightChild`, we attach the following boundaries with it:
* `min = 45, max = 50`.
* After pushing the right child, we push the left child of the popped node.
---
> Push `45.leftChild` = 43 (key = 43, min = 40, max = 45)
* We push the left child of `45`.
* The left child of `45` must be smaller than `45`.
* The upper bound (max) of `45.leftChild` is `45`.
* So, we update the `max` boundary to `45`.
* When we push `45.leftChild`, we attach the following boundaries with it:
* `min = 40, max = 45`
* After pushing the left child, we perform the pop operation.
---
> **Pop 43 (key = 43, min = 40, max = 45)**

![880validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/880validateBstBinarySearchTree.png)

* When we pop, we get `43`.
* We check it against its attached boundaries.
* `min = 40`, and `43 >= min`.
* `max = 45`, and `43 < max`.
* The condition met.
* Hence, the node `43` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `43.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `43.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 47 (key = 47, min = 45, max = 50)**

![890validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/890validateBstBinarySearchTree.png)

* When we pop, we get `47`.
* We check it against its attached boundaries.
* `min = 45`, and `47 >= min`.
* `max = 50`, and `47 < max`.
* The condition met.
* Hence, the node `47` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `47.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `47.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 60 (key = 60, min = 50, max = Long.MAX_VALUE)**

![900validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/900validateBstBinarySearchTree.png)

* When we pop, we get `60`.
* We check it against its attached boundaries.
* `min = 50`, and `60 >= 50`.
* `max = Long.MAX_VALUE`, and `60 < max`.
* The condition met.
* Hence, the node `60` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `60.rightChild` = 70 (key = 70, min = 60, max = Long.MAX_VALUE)
* First, we push the right child of the popped node, `60`.
* The right child must be greater than or equal to the popped node, `60`.
* The right child must be at least (min) `60`.
* The lower bound (min) of the `60.rightChild` is `60`.
* So, we update the `min` to `60`.
* When we push `60.rightChild`, we attach the following boundaries with it:
* `min = 60, max = Long.MAX_VALUE`.
* After pushing the right child, we push the left child of the popped node.
---
> Push `60.leftChild` = 55 (key = 55, min = 50, max = 60)
* We push the left child of `60`.
* The left child of `60` must be smaller than `60`.
* The upper bound (max) of `60.leftChild` is `60`.
* So, we update the `max` to `60`.
* When we push `60.leftChild`, we attach the following boundaries with it:
* `min = 50, max = 60`.
* After pushing the left child, we perform the pop operation.
---
> **Pop 55 (key = 55, min = 50, max = 60)**

![910validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/910validateBstBinarySearchTree.png)

* When we pop, we get `55`.
* We check it against its attached boundaries.
* `min = 50`, and `55 >= min`.
* `max = 60`, and `55 < max`.
* The condition met.
* Hence, the node `55` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `55.rightChild` = 57 (key = 57, min = 55, max = 60)
* First, we push the right child of the popped node, `55`.
* The right child must be greater than or equal to the popped node, `55`.
* The right child must be at least (min) `55`.
* The lower bound (min) of `55.rightChild` is `55`.
* So, we update the `min` to `55`.
* When we push `55.rightChild`, we attach the following boundaries with it:
* `min = 55, max = 60`.
* After pushing the right child, we push the left child of the popped node.
---
> Push `55.leftChild` = 53 (key = 53, min = 50, max = 55)
* We push the left child of the popped node, `55`.
* The left child must be smaller than `55`.
* The upper bound (max) of the `55.leftChild` is `55`.
* So, we update the `max` to `55`.
* When we push `55.leftChild`, we attach the following boundaries with it:
* `min = 50, max = 55`.
* After pushing the left child, we perform the pop operation.
---
> **Pop 53 (key = 53, min = 50, max = 55)**

![920validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/920validateBstBinarySearchTree.png)

* When we pop, we get `53`.
* We check it against its attached boundaries.
* `min = 50`, and `53 >= min`.
* `max = 55`, and `53 < max`.
* The condition met.
* Hence, the popped node `53` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `53.rightChild` = No Right Child To Push!
* After pushing the right child of the popped node, we push the left child of the popped node.
> Push `53.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 57 (key = 57, min = 55, max = 60)**

![930validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/930validateBstBinarySearchTree.png)

* When we pop, we get `57`.
* We check it against its attached boundaries.
* `min = 55`, and `57 >= min`.
* `max = 60`, and `57 < max`.
* The condition met.
* Hence, the popped node, `57` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `57.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `57.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 70 (key = 70, min = 60, max = Long.MAX_VALUE)**

![940validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/940validateBstBinarySearchTree.png)

* When we pop, we get `70`.
* We check it against its attached boundaries.
* `min = 60`, and `70 >= min`.
* `max = Long.MAX_VALUE`, and `70 < max`.
* The condition met.
* Hence, the popped node, `70` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `70.rightChild` = 80 (key = 80, min = 70, max = Long.MAX_VALUE)
* First, we push the right child of the popped node, `70`.
* The right child must be greater than or equal to the popped node, `70`.
* The right child must be at least (min) `70`.
* The lower bound (min) of `70.rightChild` is `70`.
* So, we update the `min` to `70`.
* When we push `70.rightChild`, we attach the following boundaries with it:
* `min = 70, max = Long.MAX_VALUE`
* After pushing the right child of the popped node, we push the left child of the popped node.
---
> Push `70.leftChild` = 65 (key = 65, min = 60, max = 70)
* We push the left child of the popped node, `70`.
* The left child must be smaller than the popped node, `70`.
* The upper bound (max) of the `70.leftChild` is `70`.
* So, we update the `max` to `70`.
* When we push the `70.leftChild`, we attach the following boundaries with it:
* `min = 60, max = 70`.
* After pushing the left child, we perform the pop operation.
---
> **Pop 65 (key = 65, min = 60, max = 70)** 

![950validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/950validateBstBinarySearchTree.png)

* When we pop, we get `65`.
* We check it against its attached boundaries.
* `min = 60`, and `65 >= min`.
* `max = 70`, and `65 < max`.
* The condition met.
* Hence, the popped node, `65` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `65.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `65.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation.
---
> **Pop 80 (key = 80, min = 70, max = Long.MAX_VALUE)**

![960validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/960validateBstBinarySearchTree.png)

* When we pop, we get `80`.
* We check it against its attached boundaries.
* `min = 70`, and `80 >= min`.
* `max = Long.MAX_VALUE`, and `80 < max`.
* The condition met.
* Hence, the popped node, `80` is a valid node.
* After the pop operation, we perform the push operations.
---
> Push `80.rightChild` = No Right Child To Push!
* After pushing the right child, we push the left child of the popped node.
> Push `80.leftChild` = No Left Child To Push!
* After pushing the left child, we perform the pop operation. 
---
> Pop: The Stack Is Empty!

![970validateBstBinarySearchTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/970validateBstBinarySearchTree.png)

* We have covered (traveled) the entire tree.
* And we found that each node is a valid node.

### Time Complexity

* We visit each node once.
* So, the time complexity is `O(n)`.

### Space Complexity

* We use a stack and the maximum size of the stack would be `O(h)`, where `h = Tree Height`.
* So, in the worst case (A skewed binary tree), `O(h) = O(n)`.
* And in the best case (A perfectly balanced binary tree), `O(h) = O(log n)`.

### Code