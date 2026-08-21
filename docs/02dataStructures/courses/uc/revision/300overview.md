# Data Structures

* We wanted to store data.
* We came up with fixed-size `Array`.
* We got random access in `O(1)` due to contiguous structure.
* But we got a problem of fixed-size.
* We came up with `Dynamic Array` and maintained the amortized cost to `O(1)`.
* But we still have the shifting cost of `O(n)`.
* So, we came up with the `Singly Linked List`.
* But removing the last item takes `O(n)`.
* So, we came up with `Singly Linked List With Tail`.
* But adding an item before a node takes `O(n)`.
* So, we came up with `Doubly Linked List Without Tail`.
* But then adding the last item still takes `O(n)`.
* So, we came up with `Doubly Linked List With Tail`.
* But then we wanted to have some restrictions and special requirements.
* For `Last-In-First-Out (LIFO)`, we came up with `Stack`.
* We can implement `stack` using array or linked list.
* For `First-In-First-Out (FIFO)`, we came up with `Queue`.
* We can implement `queue` using a circular array or linked list.
* But then we wanted to have hierarchical data structure.
* For example, a data structure where we can establish relationship between data such as parents, children, siblings, etc.
* So, we came up with `Trees`.