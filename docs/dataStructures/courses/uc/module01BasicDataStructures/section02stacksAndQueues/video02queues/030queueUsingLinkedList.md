# Queue implementation using a linked list

<!-- TOC -->
* [Queue implementation using a linked list](#queue-implementation-using-a-linked-list)
  * [How can we create a queue data structure using a linked list?](#how-can-we-create-a-queue-data-structure-using-a-linked-list)
  * [Why a singly linked list with a tail? What happens if we don't use the tail?](#why-a-singly-linked-list-with-a-tail-what-happens-if-we-dont-use-the-tail)
  * [What will be the gate between head and tail to perform the enqueue operation?](#what-will-be-the-gate-between-head-and-tail-to-perform-the-enqueue-operation)
  * [What will be the gate between head and tail to perform the dequeue operation?](#what-will-be-the-gate-between-head-and-tail-to-perform-the-dequeue-operation)
  * [What will happen if we interchange (reverse) the gates of enqueue and dequeue operations?](#what-will-happen-if-we-interchange-reverse-the-gates-of-enqueue-and-dequeue-operations)
  * [Time Complexity](#time-complexity)
  * [Space Complexity](#space-complexity)
  * [Class](#class)
  * [Pseudocode of the `Enqueue` function](#pseudocode-of-the-enqueue-function)
    * [Time complexity of the `Enqueue` function](#time-complexity-of-the-enqueue-function)
    * [Space complexity of the `Enqueue` function](#space-complexity-of-the-enqueue-function)
  * [Pseudocode of the `Front` function](#pseudocode-of-the-front-function)
    * [Time complexity of the `Front` function](#time-complexity-of-the-front-function)
    * [Space complexity of the `Front` function](#space-complexity-of-the-front-function)
  * [Pseudocode of the `Dequeue` function](#pseudocode-of-the-dequeue-function)
    * [Time complexity of the `Dequeue` function](#time-complexity-of-the-dequeue-function)
    * [Space complexity of the `Dequeue` function](#space-complexity-of-the-dequeue-function)
  * [Pseudocode of the `toString` function](#pseudocode-of-the-tostring-function)
    * [Time complexity of the `toString` function](#time-complexity-of-the-tostring-function)
    * [Space complexity of the `toString` function](#space-complexity-of-the-tostring-function)
<!-- TOC -->

## How can we create a queue data structure using a linked list?

* We use a linked list.
* In a queue, we insert the element from the back (right) side and remove the element from the front (left) side.
* So, the `pushBack` will become `enqueue` and `popFront` will become `dequeue`.
* Hence, as we remove the front element, we would change the `head.`
* And as we insert the new element, we would change the `tail`.
* In this way, the time and space complexity of each function (enqueue and dequeue) will be O(1).

## Why a singly linked list with a tail? What happens if we don't use the tail?

* If we don't use the `tail,` we will have to travel through the entire linked list for each `enqueue` operation.
* So, each enqueue operation will have O(n) time complexity.

## What will be the gate between head and tail to perform the enqueue operation?

* The `enqueue` operation of the queue will be the `pushBack` operation on the linked list.

## What will be the gate between head and tail to perform the dequeue operation?

* The `dequeue` operation of the queue will be the `popFront` operation on the linked list.

## What will happen if we interchange (reverse) the gates of enqueue and dequeue operations?

* If we perform `dequeue` from the `tail`, we will have to travel through the entire linked list until we find the second last node to make it the new tail.
* That would be O(n) time complexity for each `dequeue` operation.
* We can still get it done in O(1) time complexity if we use a doubly linked list in which case we can move backward to the second-last element from the old-last element to make it a new tail.
* But that will introduce the additional maintenance of the `previous pointers` for all the nodes.

## Time Complexity

* The overall time complexity is O(1).

## Space Complexity

* The overall space complexity is O(n) due to the number of items (nodes) we add (enqueue).

## Class

```kotlin

class QueueUsingLinkedList<T> {

    private data class Node<T>(var value: T, var next: Node<T>? = null)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    /**
     * Instead of relying on [head == null] check, it is good to maintain a dedicated [size] variable.
     * Because, if the queue is accepting the nullable values and if the first item is null, but not the subsequent
     * items, we still get [head == null], but the queue is not empty.
     */
    var size: Int = 0
        private set

    val isEmpty: Boolean
        get() = size == 0

    // ... //
}
```

## Pseudocode of the `Enqueue` function

```kotlin

fun enqueue(value: T) {
    val node = Node(value, null)
    if (isEmpty) { 
        head = node
        tail = node
    } else { 
        tail?.next = node
        tail = node
    }
    size++
}
```

### Time complexity of the `Enqueue` function

* There is no iteration and the logic does not depend on or grow with the input size.
* Hence, the time complexity is O(1). 

### Space complexity of the `Enqueue` function

* There is no additional memory we use that depends on or grow with the input size.
* Hence, the space complexity is O(1). 

## Pseudocode of the `Front` function

```kotlin

fun front(): T? {
    return head?.value
}

```

### Time complexity of the `Front` function

* There is no iteration and the logic does not depend on or grow with the input size.
* Hence, the time complexity is O(1).

### Space complexity of the `Front` function
  
* There is no additional memory we use that depends on or grow with the input size.
* Hence, the space complexity is O(1).

## Pseudocode of the `Dequeue` function

```kotlin

fun dequeue(): T? {
    if (isEmpty) return null
    val topped = front()
    head = head?.next
    size--
    if (isEmpty) {
        // This is critical when we remove the only available element.
        tail = null 
    }
    return topped 
}
```

### Time complexity of the `Dequeue` function

* There is no iteration and the logic does not depend on or grow with the input size.
* Hence, the time complexity is O(1).

### Space complexity of the `Dequeue` function

* There is no additional memory we use that depends on or grow with the input size.
* Hence, the space complexity is O(1).

## Pseudocode of the `toString` function

```kotlin

override fun toString() = buildString {
    var curr = head
    while (curr != null) {
        append(" ${curr.value} --> ")
        curr = curr.next
    }
    append(" -- End Of The Queue -- ")
    }
```

### Time complexity of the `toString` function

* There is an iteration and the logic depends on and grow with the input size.
* Hence, the time complexity is O(n).

### Space complexity of the `toString` function

* Each string uses the value of an item.
* Hence, the space complexity is O(n).