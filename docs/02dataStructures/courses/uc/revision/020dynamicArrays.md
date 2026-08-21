# Dynamic Arrays

<!-- TOC -->
* [Dynamic Arrays](#dynamic-arrays)
  * [Includes](#includes)
  * [Prerequisites / Previously](#prerequisites--previously)
  * [References / Resources](#references--resources)
  * [What](#what)
  * [Solves](#solves)
  * [How](#how)
  * [Problem/s](#problems)
  * [Next](#next)
<!-- TOC -->

## Includes

* Which need led us this transition from one data structure to another
* What changes along the way:
    * The underlying data structure
    * Supported operations
    * Time and space complexity of each supported operation
    * Miscellaneous
* Progressive comparison
    * Access, find, insert, update, delete, etc.
    * Best case, average case, worst-case with notes
    * Pros and cons
    * The drawback that the next data structure solves
* Miscellaneous

## Prerequisites / Previously

* [Arrays.md](010arrays.md)

## References / Resources

* [Dynamic Arrays Detailed.md](../module02dynamicArraysAndAmortizedAnalysis/010dynamicArrays.md)
* [Banking Amortized Analysis.md](../module02dynamicArraysAndAmortizedAnalysis/020bankingAmortizedAnalysis.md)
* [Physicist Potential Amortized Analysis.md](../module02dynamicArraysAndAmortizedAnalysis/030physicistPotentialAmortizedAnalysis.md)
* [Other Factors For Resize.md](../module02dynamicArraysAndAmortizedAnalysis/040otherFactorsForResize.md)
* [Mcqs.md](../module02dynamicArraysAndAmortizedAnalysis/050mcqs.md)
* [Dynamic Arrays Questions.md](../module02dynamicArraysAndAmortizedAnalysis/060dynamicArraysQuestions.md)

## What

* Here, we can resize the array.
* Initially, we declare the array of certain size similar to the fixed-sized static array.
* But once it is full, we increase its size.
* Similarly, if it is too empty after removing the elements, we shrink it.
* We don't resize (increase or decrease the size) arbitrary.
* There is an optimal way of doing it.
* When it is full, we double the size.
* The worst-case cost is $O(n)$, but the amortized cost is $O(1)$.

## Solves

* Now, we don't need to know the total number of elements we are going to store in advance.
* Dynamic elements, dynamic memory allocation.
* We don't waste too much memory.
* We maintain the random access in $O(1)$.

## How

* When the array is full (hits the maximum capacity), we create a new array twice the size of the old array.
* We copy the elements from the old array, and add it to the new array.
* Similarly, when the number of elements are less than half the size, we shrink (reduce) the array size.
* We don't have to resize frequently.
* The aggregated method, the banker's method, and the physicist potential method - all of them proves that the amortized cost is $O(1)$.
* Also, any constant factor for resize forms a geometric series $C \over {C - 1}$.
* Here, "2" is the optimal factor by which we should resize the array.
* It gives the best balance between the frequency of resize operations and unused allocated memory.

## Problem/s

* The shifting problem.
* Every time we insert a new element anywhere except in the end (last), we have to shift all the other (right side) elements to make room for the new element.
* We have to do this shifting because it is a contagious data structure.
* So, they must maintain a specific order (sequence) in the memory.
* Otherwise, the math that makes random access in $O(1)$ would fail.
* Similarly, every time we remove an element from anywhere except from the end (last), we have to shift all the other (right side) elements to fill the gap. 
* This shifting cost is $O(n)$.
* 

## Next

* [Linked Lists](030linkedLists.md)
* [Singly Linked List With Tail](037singlyLinkedListWithTail.md)
* [Doubly Linked List With Tail](045doublyLinkedListWithTail.md)
* [Queues](050queues.md)
* [Stacks](060stacks.md)
* [Trees](070trees.md)
* [Priority Queues](080priorityQueues.md)
* [Disjoint Sets](090disjointSets.md)
* [Hash Tables](100hashTables.md)
* [Hash Map](105hashMap.md)
* [Hash Set](110hashSet.md)
* [Binary Search Trees](120binarySearchTrees.md)
* [Self Balancing Binary SearchTrees](130selfBalancingBinarySearchTrees.md)
* [AvlTree](135avlTree.md)
* [SplayTree](140splayTree.md)
* [Trie](145trie.md)
* [Graph](200graph.md)
* [Overview](300overview.md)
* [Comparison](comparison.md)
* [Data Structure Questions](dataStructureQuestions.md)