# Balancing A Binary Search Tree (BST)

<!-- TOC -->
* [Balancing A Binary Search Tree (BST)](#balancing-a-binary-search-tree-bst)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Objectives](#objectives)
  * [Understanding the basic runtime of a binary search tree operation](#understanding-the-basic-runtime-of-a-binary-search-tree-operation)
  * [The depth problem](#the-depth-problem)
  * [How does the re-balancing solve the depth problem?](#how-does-the-re-balancing-solve-the-depth-problem)
  * [What can create an unbalanced tree?](#what-can-create-an-unbalanced-tree)
  * [How do we keep the binary search tree balanced?](#how-do-we-keep-the-binary-search-tree-balanced)
  * [Rotation of a binary search tree](#rotation-of-a-binary-search-tree)
  * [Balancing Thought Process](#balancing-thought-process)
  * [Height of a node](#height-of-a-node)
  * [A balanced binary search tree](#a-balanced-binary-search-tree)
  * [A node structure for balance](#a-node-structure-for-balance)
  * [AVL Claim: AVL Properties](#avl-claim-avl-properties)
  * [AVL Introduction Summary: TL;DR](#avl-introduction-summary-tldr)
  * [How do we find (calculate) which side is more weighted?](#how-do-we-find-calculate-which-side-is-more-weighted)
  * [AVL-Tree Basic Left Rotation Idea](#avl-tree-basic-left-rotation-idea)
    * [Code Consideration: Thought Process](#code-consideration-thought-process)
    * [Pseudocode: Basic Left Rotation (Cause: RR)](#pseudocode-basic-left-rotation-cause-rr)
  * [AVL-Tree Basic Right Rotation Idea: (LL-Cause)](#avl-tree-basic-right-rotation-idea-ll-cause)
  * [AVL-Tree Basic Left-Right Rotation Idea](#avl-tree-basic-left-right-rotation-idea)
    * [Pseudocode of Left-Right Rotation](#pseudocode-of-left-right-rotation)
  * [AVL-Tree Basic Right-Left Rotation Idea](#avl-tree-basic-right-left-rotation-idea)
  * [On which node do we perform the rotation when multiple nodes are imbalanced?](#on-which-node-do-we-perform-the-rotation-when-multiple-nodes-are-imbalanced)
    * [Insert example](#insert-example)
  * [How to distinguish between the right rotation and the LR-Rotation?](#how-to-distinguish-between-the-right-rotation-and-the-lr-rotation)
  * [Final pseudocode for the right rotation](#final-pseudocode-for-the-right-rotation)
  * [Final pseudocode for the LR-Rotation](#final-pseudocode-for-the-lr-rotation)
  * [How to distinguish between the left rotation and the RL-Rotation?](#how-to-distinguish-between-the-left-rotation-and-the-rl-rotation-)
  * [Final pseudocode for the left rotation](#final-pseudocode-for-the-left-rotation)
  * [Final pseudocode for the RL-Rotation](#final-pseudocode-for-the-rl-rotation)
  * [Rotation summary](#rotation-summary)
  * [Conclusion](#conclusion)
  * [What is the difference between a binary heap tree and a binary search tree?](#what-is-the-difference-between-a-binary-heap-tree-and-a-binary-search-tree)
  * [ToDo](#todo)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References

* [Trees](../module01BasicDataStructures/section03trees/trees.md)
* [Basic Data Structure Questions](../module01BasicDataStructures/questionsOnBasicDataStructures.md)
* [Priority Queues](../module03priorityQueuesHeapsDisjointSets/section01priorityQueuesIntroduction/priorityQueues.md)
* [Binary Heap Trees](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
* [Complete Binary Tree](../module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
* [Heap Sort](../module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
* [Binary Search Trees](05binarySearchTrees.md)
* [Binary Search Trees: Basic Operations](10binarySearchTreesBSTsBasicOperations.md)
* [Binary Tree Implementation](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)
* [AVL Visualization](https://www.cs.usfca.edu/~galles/visualization/AVLtree.html)
* [Abdul Bari Sir: AVL Tree](https://youtu.be/jDM6_TnYIqE?si=ozgBIYnV79pJw8Nc)

## Objectives

* Understand the basic runtime of a binary search tree operation.
* Understand the motivation behind the binary search tree balancing.
* Implement rotation.

## Understanding the basic runtime of a binary search tree operation

![90bstFindIntroRuntime.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/90bstFindIntroRuntime.png)

* The `find` operation depends on the `depth` of the tree.
* If a node has a higher depth, then we take more time.
* For example, while trying to find `6`.
* Similarly, if the node has a lower depth, we take less time.
* For example, `3`.
* If you remember, we have associated the well analogy with `depth` in [Trees](../module01BasicDataStructures/section03trees/trees.md).
* So, it is like a node that we want to find is in a well.
* It is looking upside waiting for someone to descend into the well.

## The depth problem

![100bstDepthProblemExample.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/100bstDepthProblemExample.png)

![120bstDepthProblemExample3.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/120bstDepthProblemExample3.png)

* If a binary search tree is not balanced, we might take `O(n)` time for the `find` operation. 

## How does the re-balancing solve the depth problem?

![130bstBalancingExample.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/130bstBalancingExample.png)

* Re-balancing reduces the depth and keeps the `in-order (LPR)` sorted.
* Hence, the `find` operation becomes faster (efficient).
* The key observation here is that the efficiency depends on the height of the tree.
* And the height of a tree depends on the height of subtrees.
* So, if we maintain the height of subtrees, we can maintain the height of the tree.
* If the distribution (structure) of the subtrees is unbalanced, then the tree becomes taller, unbalanced and inefficient.
* So, if we control and maintain the distribution (structure) of the subtree, we can control the efficiency of the tree.
* To control and maintain the distribution (structure) of a subtree, we need a way to define, and measure it.
* We already know that "height" is the defining property.
* And the measurement scale here is in terms of "balance."
* "Balance" is associated with the "height" only.
* So, "maintaining the balance" means "maintaining the height".
* And, a tree can be highly "unbalanced," "balanced," or somewhere in the middle.
* It means that the tree can be "taller," "shorter," or somewhere in the middle.
* Also, we need to ensure that the efforts of maintaining the balance should not defeat the core purpose.
* The core purpose is to make various operations, such as `find(search)` (that depends on the tree height) more efficient.

## What can create an unbalanced tree?

![140bstInsertDeleteProblem.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/140bstInsertDeleteProblem.png)

* Insert and delete operations.

## How do we keep the binary search tree balanced?

* Using the rotation technique.

## Rotation of a binary search tree

* It is possible to change the structure of a binary search tree without violating the rules that define the binary search tree.
* For example, as shown in the image below: 

![150bstRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/150bstRotation.png)

## Balancing Thought Process

* To maintain the balance of a binary search tree, we need to define, measure, and keep track of the "balance".
* We use the term "height" to define, measure, and keep track of the "balance."

## Height of a node

![160nodeHeight.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/160nodeHeight.png)

![170nodeHeight2.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/170nodeHeight2.png)

* The longest path from the node to the leaf

```kotlin

val heightOfNode = 1 + maxOf(Node.left.height, Node.right.height) 

```

## A balanced binary search tree

![190balancedBst.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/190balancedBst.png)

* If the height of a left sub-tree is equal to the height of a right sub-tree, we call it a perfectly balanced tree.
* It means that, we need to add another field, "height" to the node structure.
* So that we can measure and keep track of the "balance".
* And when a tree is balanced:


$$
N.left.height == N.right.height
$$
$$
N.left.height - N.right.height = 0
$$

* However, maintaining a perfectly balanced binary search tree after every `insert` and `delete` operation might take `O(n)` time.
* The objective of maintaining the "balance" is to maintain the "height".
* If we maintain the "height" at most `O(log n)`, then we can perform various operations, such as `find(search)` efficiently.
* But if maintaining the perfect balance itself takes `O(n)` time, then it is not helpful.
* So, we take some flexibility here.
* In an AVL tree, we maintain the below property: 

$$
| N.left.height - N.right.height | <= 1
$$

* It is known as the "controlled balance."
* And the analysis of such an AVL-Tree, satisfies the below formula:

$$
N(h) = 1 + N(h - 1) + N(h - 2)
$$

* Here, `N(h)` means "minimum number of nodes for an AVL-Tree of height `h`."
* The formula connects (associates) the "minimum number of nodes" with the "height".
* And we will see that using the "rotation" technique, we can maintain this "flexible balance" (also known as: "controlled balance," "enough balance," "perfectly imperfect balance," etc.) in just `O(1)` time!
* It means that we don't spend more time in maintaining the balance, and we can still maintain the tree height at most `O(log n)`.
* Now, to maintain the "balance," we need to maintain the "height".
* And to maintain the "height," we need to track it.
* To track "height," we introduce a new field (property) to the "node".

## A node structure for balance

![180bstAvlNodeStructure.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/180bstAvlNodeStructure.png)

## AVL Claim: AVL Properties

* We claim that the height of a balanced binary tree (AVL) is at most almost $O(log\ n)$.
* Now, height is made up with nodes.
* So, it is possible to get an idea about the total number of nodes based upon the height of the tree.
* And we can add (consider) the "balance" property into this relationship.
* So, we are trying to figure out the relationship between the height and the total number of nodes in a tree when the tree is balanced.
* So, we start with the minimum nodes.
* We want to figure out the minimum nodes for a binary tree of height `h`.
* As per the AVL property, the difference between the height of two children should be less than or equal to 1.
* We can have such an AVL tree as shown in the below image:

![200heightAndNodesRelation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/200heightAndNodesRelation.png)

* Now, the subtree of height `h - 1` must have at least `N(h - 1)` nodes under the AVL-Tree constraint (properties).
* Let us denote it as `N(h - 1)` that says the minimum nodes in an AVL-Tree of height `h - 1`.
* And the subtree of height `h - 2` must have at least `N(h - 2)` nodes.
* So, `N(h - 2)` says the minimum nodes in an AVL-Tree of height `h - 2`.
* Now, if we add `+1(the root node)` to `N(h - 1) + N(h - 2)`, we get the minimum nodes in an AVL-Tree of height `h` (Refer the above [image](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/200heightAndNodesRelation.png) to get the idea).
* So, it is:

$$
N(h) = 1 + N(h - 1) + N(h - 2)
$$

* It indicates a recursive formula!
* And it is similar to a Fibonacci formula!
* For a Fibonacci number $F_h$, we can say:

$$
F_h = F_{h - 1} + F_{h - 2}
$$

* The value of a Fibonacci number, $F_h$ is a summation of the previous (last) Fibonacci number, $F_{h - 1}$ and the second previous (the second last) Fibonacci number, $F_{h - 2}$.
* And since $N(h)$ represents "minimum number of nodes for an AVL-Tree of height `h`," we can say:

$$
N(h) >= F_h
$$

* If we represent this "minimum number of nodes" as `n`, then it is:

$$
n >= F_h
$$

* Also:

$$
F_h >= 2^{\frac{h}{2}}
$$

* So, it becomes:

$$
n >= 2^{\frac{h}{2}}
$$

* Taking $log_2$ both the sides:

$$
log_2(n) >= log_2(2^{\frac{h}{2}})
$$

$$
log_2(n) >= \frac{h}{2}
$$

* Multiplying both the sides by 2:

$$
2\;log_2(n) >= h
$$

$$
h <= 2\;log_2(n)
$$

* We just proved that the maximum height of an AVL-Tree is $2\;log_2(n)$.
* $log_2(n)$ indicates a perfect balanced binary tree, but we take some flexibility in an AVL-Tree.
* Due to this flexibility, it can become twice as tall as a perfectly balanced binary tree.
* `2` is the price we pay to rebalance the tree in `O(1)`.
* It means that, we don't spend too much time in rebalancing, and `find(search)` operation is still efficient.
* Asymptotically, it is `O(log n)`.

## AVL Introduction Summary: TL;DR

* A binary search tree (BST) becomes inefficient as we perform the insert or delete operations.
* Insert or delete operations can skew the BST and make it taller and thin. 
* As a result, various operations such as `find(search)` become inefficient.
* The efficiency depends on the tree height.
* Tree height depends on the height of the subtrees.
* If we can control the height of the subtrees, we can maintain the height and efficiency of the tree.
* However, the efforts of controlling the height of the subtrees should not defeat the core purpose.
* The core purpose is to perform various operations such as `find(search)` efficiently.
* It turned out that, if we can control $| N.left.height - N.right.height | <= 1$, we get a nice enough balance (controlled balance).
* It is known as the "Adelson-Velsky and Landis (AVL)" property. 
* The minimum number of nodes for an AVL-Tree of height, `h` is:

$$
N(h) = 1 + N(h - 1) + N(h - 2)
$$

* We can relate it with a Fibonacci number, $F_h$.

$$
F_h >= 2^{\frac{h}{2}}
$$

$$
n >= 2^{\frac{h}{2}}
$$

$$
2\;log_2(n) >= h
$$

$$
h <= 2\;log_2(n)
$$

* It means that the maximum height of an AVL-Tree is $2\;log_2(n)$. 
* Next, we will see how to maintain the AVL-Tree property through the "rotation" technique.

## How do we find (calculate) which side is more weighted?

* We use the "height" property of a node to measure and fix the balance.
* The formula (AVL-Tree Property) is:

$$
\text{Balance Factor} = \text{Height Of A Left-Subtree} - \text{Height Of A Right-Subtree}
$$

* And we want to ensure that:

$$
| \text{Balance Factor} | <= 1
$$

## AVL-Tree Basic Left Rotation Idea

* When we have an excessive right-subtree, we perform the left-rotation.
* It is also known as "RR-Rotation (Right of right rotation)".
* Because it happens when the right of right node causes the imbalance.
* So, the name "RRR" is based upon the root cause of the rotation.
* And to fix that, we perform the "Left Rotation."

![210avlBasicLeftRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/210avlBasicLeftRotation.png)

![215avlBasicLeftRotationWithBf.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/215avlBasicLeftRotationWithBf.png)

### Code Consideration: Thought Process

* Identify the cause and decide the rotation. 
  * For example, if (bf < -1), then it is right-sided tree.
  * It means we need to perform either left rotation or RL-rotation.
  * Similarly, if (bf > 1), then it is left-sided tree.
  * So, we need to perform either right rotation or LR-rotation.
  * LL-Cause leads right rotation, RR-Cause leads left rotation, LR-Cause leads LR-Rotation, and RL-Cause leads RL-Rotation. 
  * Decide the rotation based on the cause and effect.
* Visualize the image, recall the rotation, and affected nodes.
* Start with the unbalanced node and the upcoming (new, future) parent.
  * For example, in the left rotation, the right child of the unbalanced node becomes the new parent.
  * And if the new parent has a left child, we may need to store it in a temporary variable as the unbalanced node will become its new parent.
  * It will become the right child of the unbalanced node.
  * Think about this for the rotation that we perform.
* Think about the affected nodes.
* Consider 4 properties: Parent, left, right, and height (including the relevant edge cases).
  * Consider the edge cases. 
  * For example, the upcoming (new, future) parent might not have all the children.
* Take required references before the rotation. 
  * For example, children of the upcoming (new, future) parent.
* Apply the changes to perform the rotation. 
  * For example, shifting the unbalanced node by changing the properties (pointers).
* Update the properties of the affected nodes after the rotation.
* Update the height of all the affected nodes at the end - after we are done with all the position shifting.

### Pseudocode: Basic Left Rotation (Cause: RR)

* This is not a final code that covers all the cases.
* This is just a piece of code based on the recent explanation about the basic left rotation.
* We will slowly and gradually cover the final code with all the cases, including the edge cases.
* Think about these properties: Parent, left child, right child, and height of a node.
* Whenever we rotate the tree, we need to take care of these properties of the affected node.
* And we need to understand which nodes get affected by a particular rotation.

```kotlin
val bf = balanceFactor(unbalancedNode)
// This is the condition for the left-rotation (and also for the RL-Rotation. We will improve it.)
if (bf < -1) {
    // Start with the unbalanced node.
    // Unbalanced node: Parent, left, right, and height.
    // Left child of the unbalanced node remains as it is.
    // Right child of the unbalanced node becomes the new parent.
    val newParent = unbalancedNode.right
    // Update the parent of the unbalanced node to point to the new parent.
    // We will handle the case of a dense AVL-Tree shortly.
    unbalancedNode.parent = newParent
    // The unbalanced node becomes the left child of the new parent
    newParent.left = unbalancedNode
    // Update the height of affected nodes from children to parent order
    updateHeight(unbalancedNode)
    updateHeight(newParent)
}
```

* We can understand this with an analogy of a pulley.

![230avlTreeLeftRotaionAnalogyPulley.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/230avlTreeLeftRotaionAnalogyPulley.png)

* When the right-side is more weighted, we pull the left-side to maintain the balance.

![235avlTreeLeftRotationPulleyAnalogy.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/235avlTreeLeftRotationPulleyAnalogy.png)

* Now, we can take an example of a dense tree.

![290denseAvlTreeLeftRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/290denseAvlTreeLeftRotation.png)

* We can see that no matter how dense the tree is, we need to change the parents of only few nodes (3 to 4) to balance the entire tree.

```kotlin

val bf = balanceFactor(unbalancedNode)
// This is the condition for the left rotation (and also for the RL-Rotation. We will improve it.)
if (bf < -1) {
    // Start with the unbalanced node.
    // Left child of the unbalanced node remains as it is.
    // Right child of the unbalanced node becomes the new parent
    val newParent = unbalancedNode.right
    // Update the parent of the unbalanced node - The new parent node becomes the parent
    unbalancedNode.parent = newParent
    // Take the reference of the left child of the new parent as we will have to update its parent
    val oldLeftOfNewParent = newParent.left
    // Right child of the new parent remains as it is.
    // Update the left side of the new parent - The unbalanced node becomes the left child
    newParent.left = unbalancedNode
    // Update the right child of the unbalanced node - The old left child of the new parent
    unbalancedNode.right = oldLeftOfNewParent
    oldLeftOfNewParent.parent = unbalancedNode
    // Update the height from children to parent order
    updateHeight(oldLeftOfNewParent)
    updateHeight(unbalancedNode)
    updateHeight(newParent)
}

```

* We can understand this with the pulley example also.

![300denseAvlTreeLeftRotationPulleyAnalogy.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/300denseAvlTreeLeftRotationPulleyAnalogy.png)

## AVL-Tree Basic Right Rotation Idea: (LL-Cause)

* When we have an excessive left-subtree, we perform the right-rotation.
* We also call it "LL-Rotation."
* Because it happens when the left of left node causes the imbalance.
* So, remember that the name "LLR" is based upon the root cause of the imbalance.
* And to fix it, we perform "Right Rotation."

![220avlBasicRightRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/220avlBasicRightRotation.png)

![225avlBasicRightRotationWithBf.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/225avlBasicRightRotationWithBf.png)

```kotlin
val bf = balanceFactor(unbalancedNode)
// This is the condition for the right rotation (and also for LR-Rotation. We will improve.)
if (bf > 1) {
    // The left child of the unbalanced node becomes the new parent
    val newParent = unbalancedNode.left
    // The unbalanced node goes to the right side of the new parent
    newParent.right = unbalancedNode
    // The "newParent" becomes the parent node of the unbalanced node 
    unbalancedNode.parent = newParent
    // Update the height from children to parent order 
    updateHeight(unbalancedNode)
    updateHeight(newParent)
    // We will shortly see an example of a dense tree, where the new parent might have children.
}
```

* We can understand this with an analogy of a pulley.

![240avlTreeRightRotaionAnalogyPulley.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/240avlTreeRightRotaionAnalogyPulley.png)

* When the left-side is more weighted, we pull the right-side to maintain the balance.

![245avlTreeRightRotaionAnalogyPulley.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/245avlTreeRightRotaionAnalogyPulley.png)

* Now, we can take an example of a dense tree.

![270avlTreeRightRotationDenseExample.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/270avlTreeRightRotationDenseExample.png)

```kotlin

val bf = balanceFactor(unbalancedNode)
// This is the condition for the right rotation (and also for the LR-Rotation). We will improve it.
if (bf > 1) {
    // The left child of the unbalanced node becomes the new parent.
    val newParent = unbalancedNode.left
    // The right child of the new parent node gets affected - if any.
    val oldRightOfNewParent = newParent.right
    // The unbalanced node becomes the right child of the new parent.
    newParent.right = unbalancedNode
    // Update the parent of the unbalanced node.
    unbalancedNode.parent = newParent
    // The old right child of the new parent (if any) becomes the left child of the unbalanced node.
    unbalancedNode.left = oldRightOfNewParent
    oldRightOfNewParent.parent = unbalancedNode
    // Update the height from children to parent order
    updateHeight(oldRightOfNewParent)
    updateHeight(unbalancedNode)
    updateHeight(newParent)
}

```

* Again, we can visualize and understand it better with the pulley analogy. 

![280denseAvlTreeRightRotationPulleyAnalogy.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/280denseAvlTreeRightRotationPulleyAnalogy.png)

## AVL-Tree Basic Left-Right Rotation Idea

* Sometimes, we need to perform multiple rotations to balance the tree.
* The below image shows a left-rotation followed by the right-rotation. 

![250avlTreeLeftThenRightRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/250avlTreeLeftThenRightRotation.png)

* First, we perform the left rotation on the "**unbalancedNode.left**".
* It establishes a different "left" child to the "**unbalancedNode**".
* And then, we perform the right rotation on the "**unbalancedNode**".
* We can do the Left-Right Rotation in two steps also, as shown in the image below.

![310avlTreeLeftRightRotationTwoSteps.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/310avlTreeLeftRightRotationTwoSteps.png)

* Now, we can take an example of a dense tree, as shown in the image below.

![320denseAvlTreeLeftRightRotationTwoSteps.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/320denseAvlTreeLeftRightRotationTwoSteps.png)

![325denseAvlTreeLeftRightRotationProcess1.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/325denseAvlTreeLeftRightRotationProcess1.png)

### Pseudocode of Left-Right Rotation

* Left rotation on the `unbalancedNode.left`.
  * The left rotation should also update the height of the affected nodes.
* And the right rotation on the `unbalancedNode` itself.
  * The right rotation would also update the height of the affected nodes.

```kotlin

val bf = balanceFactor(unbalancedNode)
// We will see how to distinguish b/w right-rotation and LR-rotation soon.
if (bf > 1) {
    // Start with the unbalancedNode.left and perform the left rotation on it
    val leftOfUnbalanced = unbalancedNode.left
    // The rotate operation should also update the height of the relevant (affected) nodes.
    rotateLeft(leftOfUnbalanced)
    rotateRight(unbalancedNode)
}
```

## AVL-Tree Basic Right-Left Rotation Idea

* Similarly, the below image shows a right-rotation followed by the left-rotation.

![260avlTreeRightThenLeftRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/260avlTreeRightThenLeftRotation.png)

* First, we perform the right rotation on the "**unbalancedNode.right**".
* It establishes a different right child to the "**unbalancedNode**".
* Then, we perform the left rotation on the "**unbalancedNode**".
* Right-Left Rotation in two steps, as shown in the image below:

![330avlTreeRightLeftRotationInTwoSteps.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/330avlTreeRightLeftRotationInTwoSteps.png)

* An example of a dense tree, as shown in the image below:

![340denseAvlTreeRightLeftRotationTwoSteps.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/340denseAvlTreeRightLeftRotationTwoSteps.png)

![345denseAvlTreeRightLeftRotationProcess.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/345denseAvlTreeRightLeftRotationProcess.png)

```kotlin

val bf = balanceFactor(unbalancedNode)
if (bf < -1) {
    rotateRight(unbalancedNode.right)
    rotateLeft(unbalancedNode)
}

```

## On which node do we perform the rotation when multiple nodes are imbalanced?

**When, how, and on which node do we perform a rotation?**

* The first unbalanced ancestor node becomes the subject.
* And the type of rotation depends on the relative position between the node that causes the imbalance and the subject node (the first unbalanced ancestor node).
* For example:

### Insert example

![349avlInsertionProcessCodeExample.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/349avlInsertionProcessCodeExample.png)

![350avlImbalancedInsertionFixExample.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/350avlImbalancedInsertionFixExample.png)

* Now, if we insert 22:

![360avlImbalancedInsertionFixExample2.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/360avlImbalancedInsertionFixExample2.png)

* Inserting `22` made the node `20` unbalanced.
* `20` is the first and only unbalanced ancestor node.
* The direction of first two nodes from the first unbalanced ancestor node, `20` towards the node that has caused imbalance is `RL`.
* Yes, we take only the first two nodes and it doesn't have to reach the node that has caused the imbalance.
* So, we perform the `RL-Rotation.`
* The root-cause node, `22` becomes the parent, and the old-parent, `20` goes to the left side.
* The process changes the parent of `4` nodes.

---

**Why do we take only the first two nodes to detect and decide the rotation?**

* Because, the direction of the first two nodes from the first unbalanced ancestor to the root-cause node is enough to decide the rotation and balance the tree.
* For example, if we had inserted `27` instead of `22`:

![370avlImbalancedInsertionFixExample3.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/370avlImbalancedInsertionFixExample3.png)

---

* Now, insert `50` (after inserting `22`):

![380avlImbalancedInsertionFixExample4.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/380avlImbalancedInsertionFixExample4.png)

## How to distinguish between the right rotation and the LR-Rotation?

**When do we perform the right rotation?**

* When we have a left-sided tree.
* So, when `balanceFactor(unbalancedNode) > 1` for the unbalanced node.
* So, the balance factor of the unbalanced node belongs to the `positive` family.
* For example:

![225avlBasicRightRotationWithBf.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/225avlBasicRightRotationWithBf.png)

* For the left-sided tree, we also check the left child of the unbalanced node.
* If it is a pure (straight) left-sided tree, then the left child of the unbalanced node must have `balanceFactor(unbalancedNode.left) >= 0`.
* So, the left child of the unbalanced node also belongs to the `positive` family, or at least the neutral (zero) family.
* However, if `balanceFactor(unbalancedNode.left) < 0`, then it is zigzag and it is LR-Rotation.
* So, when the balance factor of the unbalanced node belongs the positive family, but the balance factor of the left child of the unbalanced node belongs to the negative family, it is LR-Rotation.  
* For example:

![250avlTreeLeftThenRightRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/250avlTreeLeftThenRightRotation.png)

**How to remember?**

* Be it the right rotation or the LR-rotation, it always starts with:

```kotlin
// The common condition for the right rotation and LR-Rotation
if (bf > 1) {
    
}
```

* Now, if it is a pure, straight left-sided tree, then the left child of the unbalanced node cannot be right-sided.
* So, in a pure unbalanced left-sided AVL-tree, `balanceFactor(unbalancedNode.left) >= 0`.
* In a pure, straight left-sided unbalanced AVL-Tree, both the `unbalancedNode` and `unbalancedNode.left` belong to the same `positive` family.
* But if `balanceFactor(unbalancedNode.left) < 0`, it means that the unbalanced node itself is left-sided, but the left child of the unbalanced node is right-sided.
* So, in the `RL-Cause`, the balance factor of the `unbalancedNode` belongs to the `positive` family, but the balance factor of its left child belongs to the `negative` family!
* So, the condition for the pure and straight unbalanced left-sided AVL-tree is:

```kotlin

if (balanceFactor(unbalancedNode) > 1 && balanceFactor(unbalancedNode.left) >= 0) {
    // This is a pure, straight unbalanced left-sided AVL-tree
}
```

* And the condition for the LR-Cause is:

```kotlin

if (balanceFactor(unbalancedNode) > 1 && balanceFactor(unbalancedNode.left) < 0) {
    // This is LR-Caused imbalance
}
```

## Final pseudocode for the right rotation

```kotlin

val bf = balanceFactor(node)
if (bf > 1 && balanceFactor(node.left) >= 0) {
    val oldLeftOfUnbalancedNode = node.left
    val rightChildIfAnyOfNewParent = oldLeftOfUnbalancedNode.right
    node.parent = oldLeftOfUnbalancedNode
    oldLeftOfUnbalancedNode.right = node
    node.left = rightChildIfAnyOfNewParent
    rightChildIfAnyOfNewParent.parent = node
    // Update the height from children to parent order
    updateHeight(rightChildIfAnyOfNewParent)
    updateHeight(node)
    updateHeight(oldLeftOfUnbalancedNode)
}

```

## Final pseudocode for the LR-Rotation

```kotlin

val bf = balanceFactor(node)
if (bf > 1 && balanceFactor(node.left) < 0) {
    rotateLeft(node.left)
    rotateRight(node)
}
```

## How to distinguish between the left rotation and the RL-Rotation? 

**When do we perform the left rotation?**

* When the BST is right-sided.
* It means when `balanceFactor(unbalancedNode) < -1`.
* We can remember it in this way: The balance factor of the unbalanced node belongs to the negative family for the **left rotation**.
* For the right-sided tree, we also check the right child of the unbalanced node.
* If the balance factor of the right child of the unbalanced node also belongs to the negative family or to the neutral family (zero), it is only the **single left rotation**.
* For example:

![215avlBasicLeftRotationWithBf.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/215avlBasicLeftRotationWithBf.png)

* However, when the unbalanced node belongs to the negative family, but the right child of the unbalanced node belongs to the positive family, it is the case of the double rotation: RL-Rotation.
* For example:

![260avlTreeRightThenLeftRotation.png](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/260avlTreeRightThenLeftRotation.png)

## Final pseudocode for the left rotation

```kotlin

val bf = balanceFactor(node)
if (bf < -1 && balanceFactor(node.right) <= 0) {
    // This is the case of a single left rotation
    val rightOfUnbalancedNode = node.right
    val leftOfNewParentIfAny = rightOfUnbalancedNode.left
    node.parent = rightOfUnbalancedNode
    rightOfUnbalancedNode.left = node
    node.right = leftOfNewParentIfAny
    leftOfNewParentIfAny.parent = node
    // Update the height from children to parent order.
    updateHeight(rightOfUnbalancedNode)
    updateHeight(node)
    updateHeight(leftOfNewParentIfAny)
}

```

## Final pseudocode for the RL-Rotation

```kotlin

val bf = balanceFactor(node)
if (bf < -1 && balanceFactor(node.right > 0)) {
    // This is the case of the double rotation: RL-Rotation
    rotateRight(node.right)
    rotateLeft(node)
}

```

## Rotation summary

* if `bf > 1`, it means the tree is left-sided. 
* So, we perform either right rotation or the LR-rotation.
  * How to remember? At the end, we do the right rotation only in `LR`.
  * For the left-sided tree, we check the left child.
  * If both the unbalanced node and the left child belong to the same family, it is the case of the single rotation: Right rotation.
  * Otherwise, it is the case of the double rotation: LR-Rotation.
* If `bf < -1`, it means that the tree is right-sided.
* So, we perform either the left rotation or the RL-rotation.
  * How to remember? At the end, we do the left rotation only in `RL`.
  * For the right-sided tree, we check the right child.
  * If both the unbalanced node and the right child belong to the same family, it is the case of the single rotation: Left rotation.
  * Otherwise, it is the case of the double rotation: RL-Rotation.
* `LR-Rotation`: (1) Left rotation on the `unbalancedNode.left` and then (2) Right rotation on the `unbalancedNode`.
* `RL-Rotation`: (1) Right rotation on the `unbalancedNode.right` and then (2) Left rotation on the `unbalancedNode`.

## Conclusion

* The first unbalanced ancestor node becomes the anchor of the rotation.
* To find the first unbalanced ancestor node, we travel upwards via parents of the root-cause node that has caused the imbalance.
* To decide the rotation, we check only the first two nodes from the first unbalanced ancestor to the node that has caused the imbalance. 
* The rotation changes maximum 4 nodes.
* The area of changes includes: Child-Parent relation and the balance factor (height).
* The rebalance must happen immediately after the moment of imbalance.
* We must not accumulate the imbalance weight (score). Otherwise, it becomes chaos.

## What is the difference between a binary heap tree and a binary search tree?

* The binary heap tree is not a sorted tree.
* The binary search tree is sorted for `In-Order(Left-Parent-Right)` traversal.
* For almost every other operations, we need to consider two variations of a binary search tree: (1) A balanced binary search tree (2) A skewed binary search tree.
* `Insert` is `O(log n)` in a heap.
* In a heap, we perform the `swiftUp` operation to maintain the properties.
* It takes `O(log n)` time (Tree height).
* In a binary search tree, we start from the root and find the right place.
* If it is a balanced binary search tree, it takes `O(log n)`.
* Otherwise, a skewed `BST` can take `O(n)` time.
* `Search` is tricky. 
* `Search` for a balanced binary search tree (and not a skewed `BST`) is `O(log n)`. 
* Otherwise, it can be `O(n)` for a skewed `BST`.
* `searchMax` (`findMax`) is `O(1)` for a `maxHeap`.
* `searchMin` (`findMin`) is `O(1)` for a `minHeap`.
* `Search`, it can be `O(n)` for a heap. Because, we know that the `parent` is `greater` than children in the `maxHeap`, and `smaller` than children in the `minHeap`, but we don't know in which direction to go after the parent. 
* `Delete` is also tricky.
* `Delete`: If it is a balanced binary search tree (and not a skewed `BST`), then it is `O(log n)`. Otherwise, it can be `O(n)` for a skewed `BST` as we have to travel through each node.
* `Delete` can make the `BST` unbalanced. 
* We need to perform a rotation operation to rebalance the tree, which takes `O(1)` time.
* For a heap, it can be `O(n)` as we can't know in which direction to go after the parent. Hence, we might have to visit each node.
* In a heap, we may take `O(n)` time to find the node that we want to delete. 
* Then we increase the priority, which follows the `swiftUp` process and it takes `O(log n)` time. 
* And then, we extract it by swapping it with the leaf node, which follows the `swiftDown` process and it takes `O(log n)` time.
* So overall, for the `delete` operation, a `heap` takes `O(n) + O(log n) + O(log n)` time, where the dominant term is `O(n)`.
* `ExtractMax` is `O(1)` in the `maxHeap` tree.
* For a balanced binary search tree, it is `O(log n)`. Otherwise, it can be `O(n)` for a skewed binary search tree. 
* Similarly, `extractMin` is `O(1)` in the `minHeap` tree, `O(log n)` for a balanced binary search tree, and `O(n)` for a skewed binary search tree.

## ToDo

* Under each rotation theory -> Pseudocode
  * Add step-by-step progress along with those 4 properties: Parent, left, right, and height.
* When and how do we recalculate the balance factor? Do we have to recalculate the balance factor of each node after each insert or delete operation? How does that work? 
* Why does the height update order matter?
* Maybe we can have a separate file for the insert and delete operations as this file has become too long.
  * I am unsure if it is better to have relevant (associated) concepts at one place. 
  * For example: Both the `insert` and `delete` operations also use `updateHeight`, `balanceFactor`, and `rotations`.
  * But this file focuses mainly on the `rotation` part.
  * If we use a separate file for the `insert` and `delete` operations, it will be step-by-step (the next step) progress.
  * I am convinced to have a separate file for the `insert` and `delete` operations.
* Actual implementation
* Relevant problems
  //ToDo: Follow The Standard Improvement Process.

## Next

* [AVLTree: Insert Operation](20avlTreeInsertOperation.md)
* [AVLTree: Delete Operation](25avlTreeDeleteOperation.md)
* [AVLTree: Implementation](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/020avlTreeImplementation.kt)
* [AVLTree: Merge Operation](30avlTreeMergeOperation.md)
* [AVLTree: Split Operation](40avlTreeSplitOperation.md)
* [AVLTree: Kth Small Key](50avlTreeFindKthSmallKey.md)
* [Flip (Replace) Using An AvlTree](60flipReplaceWithAvlTree.md)
* [Splay Trees](70splayTrees.md)
* [Red-Black Trees](80redBlackTrees.md)