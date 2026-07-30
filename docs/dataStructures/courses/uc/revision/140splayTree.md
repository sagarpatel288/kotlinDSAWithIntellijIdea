# Splay Tree

<!-- TOC -->
* [Splay Tree](#splay-tree)
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

## References / Resources

*

## What

* The main objective of a splay tree is to optimize the find (search) operation of the last accessed, recently accessed, frequently accessed, and neighbor nodes.
* It gives better caching.
* It makes the last accessed node the root node.
* As a consequence, all the recent, frequent, and neighbor nodes remain close to the root.  
* It uses various rotations to achieve the same.
* Zig-Rotations: Right or left rotation
  * Also: 
  * Zig-rotation is the right rotation and zag rotation is the left rotation.
  * We perform it when we don't have a grandparent of the target.
  * Because the parent of the target is the root.
* Zig-Zig Rotation: Right-right rotation or Left-left rotation
  * Also:
  * Zig-Zig Rotation is the Right-Right Rotation, And
  * Zag-Zag Rotation is the Left-Left Rotation
  * We perform it when both the parent and the target are on the same side of the grandparent.
* Zig-Zag Rotation: Right-left or Left-right rotation
  * Also:
  * Zig-Zag Rotation is the Right-left rotation, And
  * Zag-Zig Rotation is the Left-right rotation
  * We perform it when both the parent and the target are on the opposite (different) sides of the grandparent.
* Insert, delete, find - all these operations are followed by the splay operation where we perform the rotations to make the last accessed node the root node.
* 

## Solves

*

## How

*

## Problem/s

*

## Next

* [Trie](145trie.md)
* [Graph](200graph.md)
* [Overview](300overview.md)
* [Comparison](comparison.md)
* [Data Structure Questions](dataStructureQuestions.md)