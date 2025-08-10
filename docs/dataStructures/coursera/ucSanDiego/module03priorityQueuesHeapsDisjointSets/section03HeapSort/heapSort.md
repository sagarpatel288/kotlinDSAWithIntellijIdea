# Heap Sort

## References / Resources

* [Michael Sambol](https://youtu.be/2DmK_H7IdTo?si=K6g9BPvsCyOY9_eC)

## Prerequisites

* We have learned about:
  * [Priority Queues](../section01priorityQueuesIntroduction/priorityQueues.md)
  * [A Complete Binary Trees](../section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
  * [Binary Heaps](../section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
* And now, we are going to see one of the applications of a binary heap.
  * Heap Sort

## Reflection

* We know that in a max heap tree, the maximum value is always at the root.
* It means that if we follow the process below: 
  * We maintain the heap structure, 
  * Call `extractMax`, and 
  * Add the result of `extractMax` to the end of the new array (we fill the array from end to start), 
  * Repeat this process until there are no more elements left to perform the `extractMax` function, 
  * The resultant array, where we store the result of each `extractMax` call, becomes a sorted array (ascending).
* However, in that case, we take the additional array, which increases the space complexity.
* What if we can do this process as an `in-place` sorting?
* To make it an `in-place` sorting process, we don't take the new array to store the results.
* Instead, we follow the process below:

## `In-Place` Sorting

* First of all, we call the `buildHeap` function to ensure that we have a valid max heap structure.
* The `buildHeap` function would use the `siftDown` function.
* The `siftDown` function ensures that the parent node is always greater than or equal to the child nodes.
* How does it ensure that? It compares the parent node with child nodes.
* If any child node is greater than the parent node, it swaps the position with the max child index.
* The `siftDown` function needs an index to compare parents and children.
* So, from where do we start?
* Well, we start from $\frac{n}{2}$ instead of from the last node.
* Why?
* Because $\frac{n}{2}$ is the last parent node.

![01heapSortLastParentPosition.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic10heapSort/01heapSortLastParentPosition.png)

* All the other nodes will be at the last level without any children!
* So, this is the advantage of this algorithm. We reduce (cut, ignore) many nodes at once in this process.
* We store the `extractMax` result at the last unsorted index of the existing array.
* The existing last element will become the first element. So, we swap the positions.
* And once we place the `extractMax` result at the last unsorted index, we get one less element to sort.
* The result of the `extractMax` call gets its true, final, sorted position in the array.
* Once we place the result of the `extractMax` at the end, we narrow down the ending boundary.
* Now, we have `n - 1` elements to sort instead of the `n` elements.
* But, note that we have performed the `swap` operation to fit the `extractMax` result to the last unsorted index.
* That might have violated the max heap structure.
* So, we repeat the process.
* We call `buildHeap` and so on... 

## Worst-Case Analysis

* We perform the `siftDown` operation for at least $\frac{n}{2}$ elements.
* We know that the height of a complete binary tree that we use for the heap tree is `log n`.

![13heightOfACompleteBinaryTree.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic04CompleteBinaryTree/13heightOfACompleteBinaryTree.png)

* So, we know that each `siftDown` call gets `log n` time complexity.
* And for $\frac{n}{2}$ elements, it becomes $\frac{n}{2} * \log n$.
* Which is, $n \log n$ time, because we drop the constant $\frac{1}{2}$.

## Realistic Analysis

* However, if we notice, only when a node travels top-to-bottom or bottom-to-top do we travel `log n` distance.

![05buildHeapAnalysis.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02PriorityQueuesHeaps/topic10heapSort/05buildHeapAnalysis.png)

* In all the other cases, it is always less than `log n`.
* How about calculating the maximum travel each node has to make during the `buildHeap` process?

### Mathematical Proof

* 



