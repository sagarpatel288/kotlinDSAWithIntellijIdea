# Need-led (Purpose) Transitions Of Data Structures

<!-- TOC -->
* [Need-led (Purpose) Transitions Of Data Structures](#need-led-purpose-transitions-of-data-structures)
  * [Includes](#includes)
  * [Prerequisites](#prerequisites)
  * [References / Resources](#references--resources)
  * [Arrays](#arrays)
    * [References / Resources](#references--resources-1)
    * [What](#what)
    * [Solves](#solves)
    * [How](#how)
    * [Problem/s](#problems)
  * [Dynamic Arrays](#dynamic-arrays)
    * [References / Resources](#references--resources-2)
    * [What](#what-1)
    * [Solves](#solves-1)
    * [How](#how-1)
    * [Problem/s](#problems-1)
  * [Linked Lists](#linked-lists)
    * [References / Resources](#references--resources-3)
    * [What](#what-2)
    * [Singly Linked List](#singly-linked-list)
      * [References / Resources](#references--resources-4)
      * [What](#what-3)
      * [Solves](#solves-2)
      * [How](#how-2)
      * [Problem/s](#problems-2)
    * [Singly Linked List With Tail](#singly-linked-list-with-tail)
      * [References / Resources](#references--resources-5)
      * [What](#what-4)
      * [Solves](#solves-3)
      * [How](#how-3)
      * [Problem/s](#problems-3)
    * [Doubly Linked List](#doubly-linked-list)
      * [References / Resources](#references--resources-6)
      * [What](#what-5)
      * [Solves](#solves-4)
      * [How](#how-)
      * [Problem/s](#problems-4)
    * [Double Linked List With Tail](#double-linked-list-with-tail)
      * [References / Resources](#references--resources-7)
      * [What](#what-6)
      * [Solves](#solves-5)
      * [How](#how-4)
      * [Problem/s](#problems-5)
  * [Queues](#queues)
    * [References / Resources](#references--resources-8)
    * [What](#what-7)
    * [Solves](#solves-6)
    * [How](#how-5)
    * [Problem/s](#problems-6)
  * [Stacks](#stacks)
    * [References / Resources](#references--resources-9)
    * [What](#what-8)
    * [Solves](#solves-7)
    * [How](#how-6)
    * [Problem/s](#problems-7)
  * [Trees](#trees)
    * [References / Resources](#references--resources-10)
    * [What](#what-9)
    * [Solves](#solves-8)
    * [How](#how-7)
    * [Problem/s](#problems-8)
  * [Priority Queues (Heaps)](#priority-queues-heaps)
    * [References / Resources](#references--resources-11)
    * [What](#what-10)
    * [Solves](#solves-9)
    * [How](#how-8)
    * [Problem/s](#problems-9)
  * [Disjoint Sets](#disjoint-sets)
    * [References / Resources](#references--resources-)
    * [What](#what-11)
    * [Solves](#solves-10)
    * [How](#how-9)
    * [Problem/s](#problems-10)
  * [Hash Tables](#hash-tables)
    * [References / Resources](#references--resources--1)
    * [What](#what-12)
    * [Solves](#solves-11)
    * [How](#how-10)
    * [Problem/s](#problems-11)
  * [Binary Search Trees (BSTs)](#binary-search-trees-bsts)
    * [References / Resources](#references--resources-12)
    * [What](#what-13)
    * [Solves](#solves-12)
    * [How](#how-11)
    * [Problem/s](#problems-12)
    * [Self-balancing binary search trees](#self-balancing-binary-search-trees)
      * [References / Resources](#references--resources-13)
      * [What](#what-14)
      * [AVL-Tree](#avl-tree)
        * [References / Resources](#references--resources-14)
        * [What](#what-15)
        * [Solves](#solves-13)
        * [How](#how-12)
        * [Problem/s](#problems-13)
      * [Splay-Tree](#splay-tree)
        * [References / Resources](#references--resources-15)
        * [What](#what-16)
        * [Solves](#solves-14)
        * [How](#how-13)
        * [Problem/s](#problems-14)
      * [Trie](#trie)
        * [References / Resources](#references--resources-16)
        * [What](#what-17)
        * [Solves](#solves-15)
        * [How](#how-14)
        * [Problem/s](#problems-15)
  * [Graph](#graph)
    * [References / Resources](#references--resources--2)
    * [What](#what-18)
    * [Solves](#solves-16)
    * [How](#how-15)
    * [Problem/s](#problems-16)
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

## Arrays

### References / Resources

* 

### What

![Array Definition.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/025arrayDefinition.png)

![Array Structure.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/030arrayStructure.png)

![2DArrays Finding An Element.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/050arraysFindingAnElement.png)

![Arrays2d RowMajor.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/060arrays2dRowMajor.png)

![Arrays2d ColumnMajor.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/070arrays2dColumnMajor.png)

![Arrays2d RowsVsColumns.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/080arrays2dRowsVsColumns.png)

* In a 2D-Array, the first index represents the row, and the second index represents the column.
* In a row-major 2D-Arrays, the column index changes rapidly.
* In a column-major 2D-Arrays, the row index changes rapidly.

![150arraysCommonOperationTimeComplexity.png](../../../../../../assets/images/dataStructures/uc/module01basicDataStructures/010arrays/150arraysCommonOperationTimeComplexity.png)

### Solves

* 

### How

* 

### Problem/s

* It is a fixed-sized static array.
* So, once we declare it with certain size, we cannot resize it.
* To solve this, we use [Dynamic Arrays](#dynamic-arrays).

## Dynamic Arrays

### References / Resources

* 

### What

* Here, we can resize the array.
* Initially, we declare the array of certain size similar to the fixed-sized static array.
* But once it is full, we double its size.
* Similarly, if it is too empty, we shrink it.
* We don't resize (increase or decrease the size) arbitrary.
* There is an optimal way of doing it. 

### Solves

* 

### How

* 

### Problem/s

* 

## Linked Lists

### References / Resources

*

### What

* 

### Singly Linked List

#### References / Resources

* 

#### What

* 

#### Solves

* 

#### How

* 

#### Problem/s

* 

### Singly Linked List With Tail

#### References / Resources

* 

#### What

* 

#### Solves

* 

#### How

* 

#### Problem/s

* 

### Doubly Linked List

#### References / Resources

* 

#### What

* 

#### Solves

* 

#### How 

* 

#### Problem/s

* 

### Double Linked List With Tail

#### References / Resources

* 

#### What

* 

#### Solves

* 

#### How

* 

#### Problem/s

* 

## Queues

### References / Resources

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Stacks

### References / Resources

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Trees

### References / Resources

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Priority Queues (Heaps)

### References / Resources

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Disjoint Sets

### References / Resources 

*

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Hash Tables

### References / Resources 

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

## Binary Search Trees (BSTs)

### References / Resources

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s

* 

### Self-balancing binary search trees

#### References / Resources

*

#### What

* 

#### AVL-Tree

##### References / Resources

*

##### What

* 

##### Solves

* 

##### How

* 

##### Problem/s

* 

#### Splay-Tree

##### References / Resources

*

##### What

* 

##### Solves

* 

##### How

* 

##### Problem/s

* 

#### Trie

##### References / Resources

* 

##### What

* 

##### Solves

* 

##### How

* 

##### Problem/s

* 

## Graph

### References / Resources 

* 

### What

* 

### Solves

* 

### How

* 

### Problem/s