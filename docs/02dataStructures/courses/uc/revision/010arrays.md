# Arrays

<!-- TOC -->
* [Arrays](#arrays)
  * [Includes](#includes)
  * [Prerequisites](#prerequisites)
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

## Prerequisites

* It is required to study each data structure in detail before jumping onto this revision section.

## References / Resources

* [Google Sheet](https://docs.google.com/spreadsheets/d/12aumAgS5zvI7XryTnDCltWPE4PlpDWswkie7VvgtBwE/edit?usp=sharing)
* 

## What

![Array Definition.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/025arrayDefinition.png)

![Array Structure.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/030arrayStructure.png)

![2DArrays Finding An Element.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/050arraysFindingAnElement.png)

![Arrays2d RowMajor.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/060arrays2dRowMajor.png)

![Arrays2d ColumnMajor.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/070arrays2dColumnMajor.png)

![Arrays2d RowsVsColumns.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/080arrays2dRowsVsColumns.png)

* In a 2D-Array, the first index represents the row, and the second index represents the column.
* In a row-major 2D-Arrays, the column index changes rapidly.
* In a column-major 2D-Arrays, the row index changes rapidly.

![150arraysCommonOperationTimeComplexity.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/150arraysCommonOperationTimeComplexity.png)

## Solves

* 

## How

* 

## Problem/s

* It is a fixed-sized static array.
* So, once we declare it with certain size, we cannot resize it.
* If we get more elements than what we were supposed to get, we can't add these additional elements, and we might lose the data.
* If we get fewer elements than what we were supposed to get, we waste too much memory - allocated but unused memory.
* To solve this, we use [Dynamic Arrays](#dynamic-arrays).

## Next

* [Dynamic Arrays.md](020dynamicArrays.md)
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