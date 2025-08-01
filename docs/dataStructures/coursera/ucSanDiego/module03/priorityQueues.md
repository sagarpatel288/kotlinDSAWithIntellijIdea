# Priority Queues

## Resources / References

* [William Fiset](https://youtu.be/wptevk0bshY?si=Erkcz-IKL_O2JsQa)
*

## Learning Objectives

* To understand what a priority queue is.
* How to implement a priority queue.
* To understand what is going on in a built-in priority queue.
* To be aware of the priority queue applications (usages).
  * To be aware of the famous algorithms that use priority queues.
  * To be aware of problems where we should use priority queues.

## Definition

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

**When we want to:**

* Operations based on priority, importance, urgency, value, weight, etc., rather
  than based on the arrival order.
* Process the order based on priority.
* Frequently find the minimum and maximum elements.
* Shortest and longest paths.
* Minimum or maximum cost of the network.

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
