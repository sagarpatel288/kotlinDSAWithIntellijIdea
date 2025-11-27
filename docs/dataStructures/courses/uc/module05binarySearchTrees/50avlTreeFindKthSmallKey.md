# AVLTree: Find $K^{th}$ small key

<!-- TOC -->
* [AVLTree: Find $K^{th}$ small key](#avltree-find-kth-small-key)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Thought Process](#thought-process)
    * [Dry run when k = 7](#dry-run-when-k--7)
    * [Dry run when k = 9](#dry-run-when-k--9)
    * [Dry run when k = 15](#dry-run-when-k--15)
  * [Questions-Answers](#questions-answers)
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
* [AVL Visualization](https://www.cs.usfca.edu/~galles/visualization/AVLtree.html)
* [Abdul Bari Sir: AVL Tree](https://youtu.be/jDM6_TnYIqE?si=ozgBIYnV79pJw8Nc)
* [AVL Insert](20avlTreeInsertOperation.md)
* [AVL Delete](25avlTreeDeleteOperation.md)
* [AVL Split](40avlTreeSplitOperation.md)
* [avlTreeImplementation.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010avlTreeImplementation.kt)

## Thought Process

![500avlTreeFindKthSmallKey.svg](../../../../../assets/images/dataStructures/uc/module05binarySearchTreesBST/500avlTreeFindKthSmallKey.svg)

* If we can add a new field, called `size`, to the `AvlNode` data class, we can find the $k^{th}$ smallest key quickly.
* So, in the given image above of an `AvlTree`, each node has this `size` field.
* Now, we have the following pseudocode: 

```kotlin

fun findKthSmallestKey(node: AvlNode, k: Int): AvlNode {
    val sizeOfLeft = node.left.size
    when {
        k == sizeOfLeft + 1 -> {
            return node
        }
        k < sizeOfLeft + 1 -> {
            return findKthSmallestKey(node.left, k)
        }
        k > sizeOfLeft + 1 -> {
            return findKthSmallestKey(node.right, k - sizeOfLeft - 1)
        }
    }
}

```

### Dry run when k = 7



### Dry run when k = 9



### Dry run when k = 15



## Questions-Answers

## Next