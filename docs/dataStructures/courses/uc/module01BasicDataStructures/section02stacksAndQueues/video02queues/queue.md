# Queue Data Structure (FIFO)
          
```             
          ---+-----+-----+-----+-----+---            
             |     |     |     |     |             
   Dequeue   |  a  |  b  |  c  |  d  |    Enqueue           
<----------- |     |     |     |     | <-----------
          ---+-----+-----+-----+-----+---          
```

A **queue** is a linear data structure that follows the **First-In-First-Out (FIFO)** principle. 
Elements are added at the rear (end) and removed from the front (beginning).

<!-- TOC -->
* [Queue Data Structure (FIFO)](#queue-data-structure-fifo)
  * [Table of Contents](#table-of-contents)
    * [Enqueue (Add Element)](#enqueue-add-element)
    * [Dequeue (Remove Element)](#dequeue-remove-element)
    * [Peek/Front](#peekfront)
    * [IsEmpty](#isempty)
    * [IsFull (for fixed-size array)](#isfull-for-fixed-size-array)
    * [Use Cases](#use-cases)
    * [Edge Cases](#edge-cases)
    * [Why Use a Tail Pointer?](#why-use-a-tail-pointer)
    * [Operations](#operations)
    * [What if we `dequeue` from the tail and `enqueue` to the head?](#what-if-we-dequeue-from-the-tail-and-enqueue-to-the-head)
    * [Why Use a Circular Array instead of a Plain Array?](#why-use-a-circular-array-instead-of-a-plain-array)
    * [Operations](#operations-1)
  * [Questions](#questions)
    * [What problem does a `Queue` solve?](#what-problem-does-a-queue-solve)
    * [What are the pros and cons of a `Queue`?](#what-are-the-pros-and-cons-of-a-queue)
    * [How does a `Queue` work?](#how-does-a-queue-work)
<!-- TOC -->

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

Only the dequeue operation with a plain array causes O(n) time complexity. Otherwise, everything else is in O(1).

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

- **Enqueue as `pushBack` (to the tail): O(1)** 
1. Create a new node.  
2. tail.next = newNode
3. tail = newNode

- **Dequeue as `topFront` + `popFront` (from the head): O(1)**  
head = head.next  


- **isEmpty: O(1)**  
head == null 


### What if we `dequeue` from the tail and `enqueue` to the head?

1. `Dequeue` from the tail means we want to change the `tail` every time we perform the `dequeue` operation. 
2. And what does it mean by changing the `tail`? 
3. Well, `Dequeue` means removing an item. So, we remove the current `tail` that points to the last item.
4. But then, we also have to ensure that once we remove the current `tail`, the old second-last item (which is now the new last item) becomes the new `tail`.
5. So, how do we ensure that the `tail` points to the old second-last item (now the new last item)? 
6. We have to travel until the next pointer of a node points to null, indicating the item whose next pointer points to null is the new last item that we can assign to `tail` now.
7. We have to do this because when we remove the last old item, we cannot move backward to the old second-last item directly without having the `prev` pointer.
8. Without `prev` pointer, it would take `O(n)` time complexity for each `dequeue` operation.
9. This is the reason `enqueue` is `pushBack` and `dequeue` is `topFront` + `popFront`.
10. Otherwise, we have to use a `Doubly Linked List` to maintain the `dequeue` operation in `O(1)` time.

</details>

---

<details>
<summary><strong>Queue Using Circular Array</strong></summary>

### Why Use a Circular Array instead of a Plain Array?

- The plain array causes a time complexity of each `dequeue` operation O(n) due to shifting (to fill the gap).
- The circular array does that in O(1) time complexity.

### Operations

- **Enqueue: O(1)**
1. If it is not full, the simple `add` operation on the circular array at the `write` index.
2. If it is full, throw the exception `IllegalStateException("The Queue is full!")`  

- **How do we check if the `Queue` is full?**  
```kotlin 
        isFull = (rear + 1) % capacity == front
``` 

- **Dequeue: O(1)**  
1. If it is not empty, return the element from the `read` index and advance the `read` index one step.
2. If it is empty, throw `EmptyStackException()` or a custom and dedicated exception.  

- **How do we check if the `Queue` is empty?**
```kotlin
         isEmpty = rear == front
```

- **isEmpty: O(1)**  
```kotlin 
         isEmpty = rear == front
```

</details>

## Questions

### What problem does a `Queue` solve?
* When do we use a `Queue`?

### What are the pros and cons of a `Queue`?


### How does a `Queue` work?

* How do we implement a `Queue`?
* What underlying data structure do we use to implement a `Queue`?
* How do we perform various operations on a `Queue`?
* What is the time complexity of various operations on a `Queue`?
* What is the space complexity of various operations on a `Queue`?