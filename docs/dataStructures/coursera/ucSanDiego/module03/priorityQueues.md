# Priority Queues

## Resources / References

* [William Fiset](https://youtu.be/wptevk0bshY?si=Erkcz-IKL_O2JsQa)
*

## Learning Objectives

* To understand what is a priority queue.
* How to implement a priority queue.
* To understand what is going on in a built-in priority queue.
* To be aware of the priority queue applications (usages).
  * To be aware of the famous algorithms that use priority queues.
  * To be aware of problems where we should use priority queues.

## Definition

* A `Priority Queue` is an abstract data type where each element has a priority.
* We use this `priority` to perform various operations.
* For example, when we call `remove`, the element with the highest priority is removed first.
* However, we can change the priority of the element.
* Also, if the priority of two elements are the same, then we follow the `FIFO - First-In-First-Out` order.

## How is it different from a queue or a stack?

* A queue strictly follows `FIFO - First-In-First-Out` order.
* A stack strictly follows `LIFO - Last-In-First-Out` order.
* However, a priority queue follows a `priority` order. 
* For example, when we call `remove`, the element with the highest priority is removed first.
* Only if the priority of two elements are the same, the priority queue follows the `FIFO - First-In-First-Out` order.