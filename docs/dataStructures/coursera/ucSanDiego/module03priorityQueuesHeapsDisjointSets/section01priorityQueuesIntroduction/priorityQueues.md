# Priority Queues

<!-- TOC -->
* [Priority Queues](#priority-queues)
  * [Resources / References](#resources--references)
  * [Learning Objectives](#learning-objectives)
  * [Definition](#definition)
  * [How is it different from a queue or a stack?](#how-is-it-different-from-a-queue-or-a-stack)
  * [Common Operations](#common-operations)
  * [When to use?](#when-to-use)
  * [Examples](#examples)
  * [Does a priority queue have to have the Comparable rule?](#does-a-priority-queue-have-to-have-the-comparable-rule-)
  * [What is the difference between the comparable interface and the comparator?](#what-is-the-difference-between-the-comparable-interface-and-the-comparator)
  * [What is the problem if we implement a priority queue using an array?](#what-is-the-problem-if-we-implement-a-priority-queue-using-an-array)
    * [Using an unsorted array](#using-an-unsorted-array)
    * [Using a sorted array](#using-a-sorted-array)
      * [Linear insertion](#linear-insertion)
      * [Binary Search](#binary-search)
      * [Conclusion for the Sorted Array](#conclusion-for-the-sorted-array)
    * [Conclusion for the Array](#conclusion-for-the-array)
  * [What is the problem if we implement a priority queue using a linked list?](#what-is-the-problem-if-we-implement-a-priority-queue-using-a-linked-list)
    * [Using an Unsorted Linked List](#using-an-unsorted-linked-list)
    * [Using a Sorted Linked List](#using-a-sorted-linked-list)
    * [Conclusion](#conclusion)
  * [Can't we use binary search to find the right position to insert a new element in a sorted linked list?](#cant-we-use-binary-search-to-find-the-right-position-to-insert-a-new-element-in-a-sorted-linked-list)
  * [Which data structure is most commonly used to implement a priority queue?](#which-data-structure-is-most-commonly-used-to-implement-a-priority-queue)
  * [Explain a binary heap tree.](#explain-a-binary-heap-tree)
  * [Summary: Priority Queue](#summary-priority-queue)
  * [Next](#next)
  * [Application Summary: Purpose And Reason](#application-summary-purpose-and-reason)
<!-- TOC -->

## Resources / References

* [ADT - Abstract Data Type](../../module01BasicDataStructures/section01arraysAndLinkedLists/01abstractDataType.md)
* [William Fiset](https://youtu.be/wptevk0bshY?si=Erkcz-IKL_O2JsQa)
* [Coursera: UC San Diego: Data Structures](https://www.coursera.org/learn/data-structures)

## Learning Objectives

* To understand what a priority queue is.
* How to implement a priority queue.
* To understand what is going on in a built-in priority queue.
* To be aware of the priority queue applications (usages).
  * To be aware of the famous algorithms that use priority queues.
  * To be aware of problems where we should use priority queues.

## Definition

```markdown

         Container                         
                                           
┌────────────────────────┐                 
│                        │     peek() = 7  
│   5                7   │                 
│                        │     remove() = 7
│                        │                 
│                        │     remove() = 5
│           1            │                 
│                        │     remove() = 4
│                        │                 
│                        │     remove() = 3
│           3        4   │                 
│                        │     remove() = 1
└────────────────────────┘                 

```

* A `Priority Queue` is an abstract data type where each element has a priority.
* We use this `priority` to perform various operations.
* For example, when we call `remove`, the element with the highest priority is
  removed first.
* However, we can change the priority of the element.
* Also, if the priorities of two elements are the same, then we follow the
  `FIFO - First-In-First-Out` order.

## How is it different from a queue or a stack?

* A queue strictly follows `FIFO - First-In-First-Out` order.
* A stack strictly follows `LIFO - Last-In-First-Out` order.
* However, a priority queue follows a `priority` order.
* For example, when we call `remove`, the element with the highest priority is
  removed first.
* Only if the priorities of two elements are the same, the priority queue
  follows the `FIFO - First-In-First-Out` order.

## Common Operations

* Add, peek, poll (or remove), isEmpty, max (or findMax, getMax),
  changePriority, etc.

## When to use?

**When we want to perform:**

* Operations based on priority, importance, urgency, value, weight, etc., rather
  than based on the arrival order.
* Process the order based on priority.
* Operations to frequently find the minimum and maximum elements.
* Operations to find the shortest and the longest paths.
* Operations to find the minimum or the maximum cost of the network.

## Examples

* Dijkstra's Algorithm uses a priority queue to find the shortest path from
  point `a` to point `b` in a map or graph.
* Prim's Algorithm uses a priority queue to find an optimum spanning tree in a
  graph.
  * For example, suppose we have a network of computers, and we want to
      connect them with cables.
  * The prim's algorithm (and hence, the priority queue) helps us find the
      optimal length (and hence, minimum cost) of cables.
* Huffman's algorithm to find the optimal prefix-free encoding of a string or
  file.
  * For example, MP3 audio format encoding.
* The heap sort algorithm to sort `n` objects.

## Does a priority queue have to have the Comparable rule? 

* Either the comparable interface or the external comparator.
* So, either the underlying data must implement the comparable interface, or
  we must provide the external comparator to the priority queue.
* Because the priority queue needs to understand how to compare two objects,
  and how to find (or distinguish) the priorities of two objects.

## What is the difference between the comparable interface and the comparator?

* It is natural for a class to implement the Comparable interface for the natural order.
* For example, `Int`, `String`, `Double`, `Long`, etc.
* So that numbers can be ordered or compared numerically, and strings can be ordered or compared alphabetically.
* However, we can also provide a custom rule externally.
* For example, for a custom data class, we can provide a custom, external comparator to the priority queue.
* We can also provide this custom, external comparator for the classes that implement the Comparable interface.
* In that case, the custom, external comparator takes priority as it conveys that we want to overwrite the default,
implemented Comparable interface.

## What is the problem if we implement a priority queue using an array?

* There are two ways to implement a priority queue using an array.
  * Using an unsorted array.
  * Using a sorted array.
  
### Using an unsorted array

* Inserting an element takes `O(1)`.

```markdown
┌─────┌─────┌─────┌─────┌─────┌─────┐
│  3  │  9  │  16 │  10 │  2  │  7  │
└─────└─────└─────└─────└─────└─────┘
```

* But, finding the maximum takes `O(n)`, because we have to scan through the entire array.
* And we cannot even use the binary search here, because the array is not sorted.
* So, finding the element with maximum priority takes `O(n)` time. 

### Using a sorted array

```markdown

Indices       0     1     2     3     4

           ┌─────┐─────┌─────┌─────┐─────┐            
           │  2  │  3  │  9  │  10 │  16 │            
           └─────┘─────└─────└─────┘─────┘            
                          \      \     \              
                           \      \     \             
                            \      \     \            
                             \      \     \           
                              \      \     \          
                               ▼      ▼     ▼         

Indices       0     1     2     3     4     5

           ┌─────┐─────┌─────┐─────┌─────┐─────┐      
           │  2  │  3  │  7  │  9  │  10 │  16 │      
           └─────┘─────└─────┘─────└─────┘─────┘      
                                                      
              \      \     \    \     \      \        
               \      \     \    \     \      \       
                \      \     \    \     \      \      
                 \      \     \    \     \      \     
                  \      \     \    \     \      \    
                   ▼      ▼     ▼    ▼     ▼      ▼   

Indices       0     1     2     3     4     5     6

           ┌─────┐─────┐─────┌─────┐─────┌─────┐─────┐
           │  1  │  2  │  3  │  7  │  9  │  10 │  16 │
           └─────┘─────┘─────└─────┘─────└─────┘─────┘

```

* If we use a sorted array, then we can get the maximum element, the element with the highest priority, in `O(1)`.
* However, to insert an element, we have two options:

#### Linear insertion

* We scan through the array and find the right position to insert the new element.
* However, it takes `O(n)`.

#### Binary Search

* This is a better option than the linear search and insertion. 
* It takes `log n` time to find the right position to insert the new element.
* However, just because this is an array, if the new element that we want to insert or remove is the smallest one, 
  all the other elements will have to shift their positions.
* And this shifting takes `O(n)` time in the worst-case.

#### Conclusion for the Sorted Array

* So, in one way or another, we get `O(n)` time complexity for the insertion.

### Conclusion for the Array

* If we use an unsorted array, we get `O(n)` time for finding the element with maximum priority.
* If we use a sorted array, we get `O(n)` time for inserting an element due to the position shifting process of the 
  array.

## What is the problem if we implement a priority queue using a linked list?

* Similar to an array implementation, we have two ways to use a linked list for a priority queue.

### Using an Unsorted Linked List

* If we use an unsorted linked list, inserting an element is `O(1)`. 
* However, to find the element having maximum priority takes `O(n)` in the worst case, because we have to scan through the entire list.

### Using a Sorted Linked List

* Finding the element that has the maximum priority takes `O(1)`.
* However, to insert an element at the right position takes `O(n)`, because we have to scan through the entire list 
  to find the right position.

### Conclusion

* If we use an unsorted linked list, then finding the element with maximum priority takes `O(n)`.
* If we use a sorted linked list, then inserting an element at the right position takes `O(n)`.

## Can't we use binary search to find the right position to insert a new element in a sorted linked list?

[Basic Data Structures](../../module01BasicDataStructures/questionsOnBasicDataStructures.md)

* We cannot use binary search for a linked list, because a linked list does not give us random access in `O(1)`.
* A linked list does not give us random access in `O(1)`, because it is not a contiguous data structure.
* It means that the previous and the next elements are not neighbours in the memory.
* The previous and next elements are scattered in the memory.
* It means that we cannot use `index` to find any element in `O(1)`.
* In a binary search, we use `indices` to get any element in `O(1)` time, and adjust our boundaries accordingly.
* For example, we might have the `start` index and the `end` index. 
* So, we might get the element at the `middle` index, and compare it with the element that we want to insert.
* If the new element is higher than the `middle` element, the `middle` becomes the `start` index.
* If the new element is lower than the `middle` element, the `middle` becomes the `end` index.
* And so on...
* But in a linked list, we can't have `indices`. 
* So, we can't use the binary search in a linked list.

## Which data structure is most commonly used to implement a priority queue?

* A binary heap tree.

## Explain a binary heap tree.

* [binaryHeapTrees.md](../section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)

## Summary: Priority Queue

* Core:
  * A priority queue is an Abstract Data Type where each element is associated with a priority.
* Operations:
  * It mainly performs `insert`, `peek`, `remove`, and `changePriority` operations.
* Operation specific:
  * The elements are removed based on their priorities instead of their arrival orders.
* Implementation:
  * We use a [Binary Heap](../section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md) for the implementation.
* Time Complexity:
* Space Complexity:
* Applications:

## Next

* [Binary Heap Theory](../section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
* [Complete Binary Tree Theory](../section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
* [Binary Max Heap Implementation](../../../../../../src/coursera/ucSanDiego/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/01binaryMaxHeap.kt)
* [Heap Sort Theory](../section03HeapSort/heapSort.md)
* [Heap Sort Implementation](../../../../../../src/coursera/ucSanDiego/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/02heapSort.kt)

## Application Summary: Purpose And Reason

* Once we understand the [entire priority queue material](#next), the purpose and reasoning of the priority queue becomes clearer.
* We use the priority queue to perform:
  * If we get unorganized data, we can `build a heap` in `O(n)` time. And then:
    * `insert` in `O(log n)` time.
    * `peekMax,` or `peekMin` in `O(1)` time.
    * `extractMax,` or `extractMin` in `O(log n)` time.
    * `changePriority` in `O(log n)` time.
    * `sort` the data in `O(n log n)` time.
  