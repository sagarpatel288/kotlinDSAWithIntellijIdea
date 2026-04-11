# Doubly Linked List With Tail

<!-- TOC -->
* [Doubly Linked List With Tail](#doubly-linked-list-with-tail)
  * [Includes](#includes)
  * [Prerequisites / Previously](#prerequisites--previously)
  * [References / Resources](#references--resources)
  * [What](#what)
  * [Solves](#solves)
    * [popBack in $O(1)$](#popback-in-o1)
      * [How](#how)
    * [addBefore in $O(1)$](#addbefore-in-o1)
      * [How](#how-1)
  * [Implementation](#implementation)
  * [Problem/s](#problems)
    * [Finding an arbitrary node is $O(n)$](#finding-an-arbitrary-node-is-on)
    * [Removing an arbitrary node is $O(n)$](#removing-an-arbitrary-node-is-on)
  * [Questions](#questions)
    * [Can't we optimize the find operation in a linked list by making it a sorted list and using a binary search?](#cant-we-optimize-the-find-operation-in-a-linked-list-by-making-it-a-sorted-list-and-using-a-binary-search)
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

* [Revision: Singly Linked List With Tail.md](037singlyLinkedListWithTail.md)

## References / Resources

*

## What

* Instead of having the reference of only the next node, we keep the reference of the previous node, too.
* So, a node will have the reference of both the next and the previous node.

## Solves

### popBack in $O(1)$

* Removing the last node takes $O(1)$ now.

#### How

* The current tail has the reference of the previous node.
* And that previous node is going to become the new tail.
* So, it will be like:

```kotlin

val currentTail = tail
val newTail = currentTail.previous
newTail.next = null
tail = newTail

```

### addBefore in $O(1)$

* Similarly, adding before a particular node means changing a few pointers.

#### How

* For example:

```kotlin

fun addBefore(node: Node, value: T) {
    val newNode = Node(T, next = node, previous = node.previous)
    val oldPrevious = node.previous
    oldPrevious.next = newNode
    // newNode.previous = oldPrevious // We did this while declaring the new node
    // newNode.next = node // We did this while declaring the new node
    node.previous = newNode
}

```

## Implementation

* [Doubly Linked List With Tail.kt](../../../../../src/courses/uc/course02dataStructures/module01/section01arraysAndLinkedLists/video02LinkedLists/07DoublyLinkedListWithTail.kt)

## Problem/s

* We still have a couple of problems:

### Finding an arbitrary node is $O(n)$

* A linked list is a non-contagious data structure.
* It means that nodes are scattered in random orders at random places (addresses) in memory.
* It means that we cannot use the index system to find an arbitrary node.
* We have to check each node one by one using their pointers.
* So, it is a linear scan and it takes $O(n)$.

### Removing an arbitrary node is $O(n)$

* To remove an arbitrary node, we first need to find the node.
* And finding an arbitrary node takes $O(n)$ time.
* As a result, removing an arbitrary node also takes $O(n)$ time. 

## Questions

### Can't we optimize the find operation in a linked list by making it a sorted list and using a binary search?

* Even if we make it a sorted list, there is no index system to get random access in $O(1)$ time.
* There is no index system because a linked list is a non-contagious data structure.
* Without the index system, we cannot have the binary search.
* So, it is not possible to apply the binary search to a linked list.

## Next

* 