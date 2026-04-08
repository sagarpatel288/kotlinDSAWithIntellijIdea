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

*

## References / Resources

*

## What



* Here, we can resize the array.
* Initially, we declare the array of certain size similar to the fixed-sized static array.
* But once it is full, we increase its size.
* Similarly, if it is too empty, we shrink it.
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

* When the array is full, we create a new array twice the size of the old array.
* We copy the elements from the old array, and add it to the new array.
* We don't have to do this frequently.
* The amortized cost of resize operation is $O(1)$.

## Problem/s

* The shifting problem.

## Next

* 
