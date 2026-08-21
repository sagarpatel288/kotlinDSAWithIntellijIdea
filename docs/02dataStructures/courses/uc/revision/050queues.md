# Queues

<!-- TOC -->
* [Queues](#queues)
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

* [Arrays](010arrays.md)
* [Dynamic Arrays.md](020dynamicArrays.md)
* [Linked Lists](030linkedLists.md)
* [Singly Linked List Without Tail](035singlyLinkedListWithoutTail.md)
* [Doubly Linked List With Tail.md](045doublyLinkedListWithTail.md)

## References / Resources

* 

## What

* "Everything in moderation!"
* "Too much of anything can make us sick!"
* Having too many functionalities than what we need increases the chances of bugs.
* Earlier, we have seen arrays, dynamic arrays, and linked lists.
* What if all we do is `pushBack` and `popFront`?
* It doesn't create a cycle, and we don't travel to find anything between `top` and `end`.
* We don't even perform `popBack`!
* We always `insert` an item to the `back` and get the item from the `front`.
* So, we want to mimic the real-world `Queue` behavior.
* It follows `FIFO - First In, First Out`.
* Like: First come, first served.
* For example: The request (e.g., API call) we get the first, is the first we serve. 
* So, we came up with a new data structure: `Queue`.
* Where we want to respect the order of arrival.
* The item that we get from the front, is the item that has the longest waiting period in the queue compared to any other item of the queue.

## Solves

* We get only what is needed: `FIFO - First In, First Out`.
* Take the item from the front in `O(1)`.
* Insert the item to the back in `O(1)`.
* We don't get anything unnecessary.
* It reduces the chances of bugs.

## How

* 

## Problem/s

* 

## Next

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