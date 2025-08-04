# Complete Binary Trees

## References / Resources

* [The CS Underdog](https://youtu.be/tUSF7I9cr_k?si=I5QyojPLAGVgmm6J)
* 

## Definition

![01definitionCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/01definitionCompleteBinaryTree.png)

* A complete binary tree is a binary tree where all the levels are completely filled, except the last level.
* The last level can be empty, partially filled, or completely filled.
* The last level must follow the left-to-right filling order.
* It means that if we have a node on the right side, there must be a node on the left side as well. 
* We cannot have a node on the right side without a node on the left side. 
* The right portion cannot be filled without or before the left portion.
* In other words, all the nodes of the last level must be aligned to the left side.

## Examples

### Example 01

![02exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/02exampleCompleteBinaryTree.png)

### Example 02

![03exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/03exampleCompleteBinaryTree.png)

### Example 03

![04exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/04exampleCompleteBinaryTree.png)

### Example 04

![05exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/05exampleCompleteBinaryTree.png)

### Example 05

![06exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/06exampleCompleteBinaryTree.png)

* It is fine for the last level to be empty, partially filled, or completely filled.
* However, it must follow the left-to-right filling order.
* It means that we cannot have a right node without or before filling a left node.

### Example 06

![07exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/07exampleCompleteBinaryTree.png)

### Example 07

![08exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/08exampleCompleteBinaryTree.png)

### Example 08

![09exampleCompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/09exampleCompleteBinaryTree.png)

## Benefits Of A Complete Binary Tree

### Height is at most O(log n) 

![10benefitOfACompleteBinaryTree.png](../../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/10benefitOfACompleteBinaryTree.png)

* The height of a complete binary tree is shallow.
* So, we get the minimum height for a given number of nodes when we use a complete binary tree to arrange the nodes.
* If we have `n` nodes, and we want to create a binary tree that has minimum height, we can follow the complete binary tree structure.
* It makes the tree traversal fast.

#### Proof

$2^l - 1$

