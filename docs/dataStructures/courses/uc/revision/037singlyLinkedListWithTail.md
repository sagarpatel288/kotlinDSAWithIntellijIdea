# Singly Linked List With Tail

<!-- TOC -->
* [Singly Linked List With Tail](#singly-linked-list-with-tail)
  * [Includes](#includes)
  * [Prerequisites / Previously](#prerequisites--previously)
  * [References / Resources](#references--resources)
  * [What](#what)
  * [Solves](#solves)
  * [How](#how)
    * [Adding a new node to the last](#adding-a-new-node-to-the-last)
    * [Reading the last node](#reading-the-last-node)
  * [Problem/s](#problems)
    * [Add a node before an arbitrary node](#add-a-node-before-an-arbitrary-node)
    * [Find an arbitrary node](#find-an-arbitrary-node)
    * [Remove an arbitrary node](#remove-an-arbitrary-node)
    * [Removing the last node](#removing-the-last-node)
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

* [Revision: Singly Linked List Without Tail.md](035singlyLinkedListWithoutTail.md)

## References / Resources

* 

## What

* Similar to the head that represents the first element, we hold and manage a reference of the last element, too.
* We call it the "Tail".

## Solves

![010singlyLinkedListTimeComplexity.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/030linkedList/010singlyLinkedListTimeComplexity.png)

* To get the last element, we don't have to travel from the head down to the last element.
* We already have the "tail", that represents the last element.
* So, adding the last element, and reading the last element now take $O(1)$ instead of $O(n)$.
* Note that adding a node before an arbitrary node, finding an arbitrary node, removing an arbitrary node, and removing the last node still take $O(n)$ as explained in short in the [Problems](#problems) section.

## How

### Adding a new node to the last

**Without Tail**

* We start from the head and travel all the way down to the current last element.
* Then, we do: `current.next = NewNode`.
* That takes $O(n)$ time.

**With Tail**

* We do: `tail.next = NewNode` and then `tail = NewNode`.
* That is $O(1)$ time operation.

### Reading the last node

**Without Tail**

* We start from the head and travel all the way down to the last element.
* That takes $O(n)$ time.

**With Tail**

* The "Tail" itself represents the last element!
* So, `lastNode = tail`.
* That is $O(1)$ time operation.

## Problem/s

### Add a node before an arbitrary node

* For example, suppose that there are four nodes: A, B, C, D.
* Now, we want to add a node before D.
* Even when we have the reference of the tail, which is D, it doesn't help much here.
* Because we have to travel till C and change its "next" pointer to point to the "New Node".
* That takes $O(n)$ time.

### Find an arbitrary node

* This is not a contiguous (index based) structure.
* So, we can't access the element using the index in $O(1)$ time.
* In fact, there is no ordered and sequential index system here.
* To find an arbitrary node, we have to conduct the linear search.
* We have to check each node, one by one.
* That takes $O(n)$ time.

### Remove an arbitrary node

* To remove an arbitrary node, we need to know where the node is.
* And finding an arbitrary node is $O(n)$ time operation.
* So, removing an arbitrary node is also $O(n)$ time operation.

### Removing the last node

**With or Without Tail**

* We start from the head and keep traveling as long as `current.next.next != null`.

```kotlin

while (curr?.next?.next != null) {
    curr = curr.next
}

// At this point, we know that `curr?.next?.next is null`.
// It means that, `curr.next` is the current (old) last node. 
val oldLastNode = curr.next
val newLastNode = curr
curr.next = null //or newLastNode.next = null
// If we are using the "tail", then:
tail = curr //or tail = newLastNode
```

* That takes $O(n)$ time.

## Next

* Doubly Linked List Without Tail
* Doubly Linked List With Tail