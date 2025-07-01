# Queue Data Structure

A **queue** is a linear data structure that follows the **First-In-First-Out (FIFO)** principle. Elements are added at the rear (end) and removed from the front (beginning).

---

## Table of Contents

- [Complexities of Queue Operations](#complexities-of-queue-operations)
- [How to Perform Various Operations](#how-to-perform-various-operations)
- [Use Cases and Edge Cases](#use-cases-and-edge-cases)
- [Queue Using Linked List](#queue-using-linked-list)
- [Queue Using Circular Array](#queue-using-circular-array)

---

<details>
<summary><strong>Complexities of Queue Operations</strong></summary>

| Operation     | Plain Array (shift) | Circular Array | Linked List |
|---------------|:-------------------:|:--------------:|:-----------:|
| Enqueue       |        O(1)*        | O(1)           | O(1)        |
| Dequeue       |        O(n)*        | O(1)           | O(1)        |
| Front/Peek    |        O(1)         | O(1)           | O(1)        |
| IsEmpty       |        O(1)         | O(1)           | O(1)        |
| IsFull        |        O(1)         | O(1)           | N/A         |

> \*Array with shifting: Enqueue is O(1) if space is available; Dequeue is O(n) due to shifting elements.

#### Complexity Analysis

- **Worst Case:** O(n) for the plain array with shifting (after many dequeues), O(1) for circular array/linked list.
- **Average/Best Case:** O(1) for all except for the plain array with shifting.
- **When do we get O(n)?** When using a plain array, we need to shift after dequeue.

</details>

---

<details>
<summary><strong>How to Perform Various Operations</strong></summary>

### Enqueue (Add Element)
- **Linked List:** Add at tail. So, `tail = newNode`
- **Circular Array:** Add at `rear/write` index, then advance it circularly.
- **Plain Array:** Add at next available index.

### Dequeue (Remove Element)
- **Linked List:** Remove from the head. So, `head = head.next`
- **Circular Array:** Remove from the `front/read` index, then advance it circularly.
- **Plain Array:** Remove from front, then shift all elements left (inefficient).

### Peek/Front
- Return element at `front/read` (array) or `head` (linked list).

### IsEmpty
- **Array:** `front(read index) == rear(write index)` (circular), or `size == 0`.
- **Linked List:** `head == null`.

### IsFull (for fixed-size array)
- **Circular Array:** `(rear + 1) % capacity == front`. (Where `rear` is the `write` index, and `front` is the `read` index.)

</details>

---

<details>
<summary><strong>Use Cases and Edge Cases</strong></summary>

### Use Cases

- **Task Scheduling:** Print queue, CPU scheduling.
- **BFS Traversal:** Graph/tree level order traversal.
- **Stream Buffers:** Data streaming.
- **Order Processing:** Customer service, ticketing.

### Edge Cases

- **Empty Queue:** Dequeue or peek returns error/null.
- **Full Queue (Array):** Enqueue fails or resizes.
- **Wrap Around (Circular Array):** Rear/front indices wrap to 0.
- **Single Element:** After one dequeue, queue becomes empty.

</details>

---

<details>
<summary><strong>Queue Using Linked List</strong></summary>

### Why Use a Tail Pointer?

- **Without Tail:** Enqueue is O(n) (traverse to end).
- **With Tail:** Enqueue is O(1) (directly attach to tail).

### Operations

- **Enqueue: O(1)** 
1. Create a new node.  
2. tail.next = newNode
3. tail = newNode

- **Dequeue: O(1)**  
head = head.next  


- **isEmpty: O(1)**  
head == null 

</details>