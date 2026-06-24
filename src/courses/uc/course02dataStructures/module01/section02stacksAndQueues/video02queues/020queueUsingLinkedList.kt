package courses.uc.course02dataStructures.module01.section02stacksAndQueues.video02queues

/**
 * # How can we create a queue data structure using a linked list?
 *
 * * We use a linked list.
 * * In a queue, we insert the element from the back (right) side and remove the element from the front (left) side.
 * * So, the `pushBack` will become `enqueue` and `popFront` will become `dequeue`.
 * * Hence, as we remove the front element, we would change the `head.`
 * * And as we insert the new element, we would change the `tail`.
 * * In this way, the time and space complexity of each function (enqueue and dequeue) will be O(1).
 *
 * # Why a singly linked list with a tail? What happens if we don't use the tail?
 *
 * * If we don't use the `tail,` we will have to travel through the entire linked list for each `enqueue` operation.
 * * So, each enqueue operation will have O(n) time complexity.
 *
 * # What will be the gate between head and tail to perform the enqueue operation?
 *
 * * The `enqueue` operation of the queue will be the `pushBack` operation on the linked list.
 *
 * # What will be the gate between head and tail to perform the dequeue operation?
 *
 * * The `dequeue` operation of the queue will be the `popFront` operation on the linked list.
 *
 * # What will happen if we interchange (reverse) the gates of enqueue and dequeue operations?
 *
 * * If we perform `dequeue` from the `tail`, we will have to travel through the entire linked list until we find the
 * node whose next pointer is null, to make it the new tail.
 * * That would be O(n) time complexity for each `dequeue` operation.
 * * We can still get it done in O(1) time complexity if we use a doubly linked list in which case we can move backward
 * to the second-last element from the old-last element to make it a new tail.
 * * But if we use a doubly-linked list, we get an extra overhead to maintain the previous pointer of each node.
 *
 * # Time Complexity
 *
 * * The overall time complexity is O(1).
 *
 * # Space Complexity
 *
 * * The overall space complexity is O(n) due to the number of items (nodes) we add.
 */
class QueueUsingLinkedList<T> {

    /**
     * * If the properties [value] and [next] are mutable, we should not use a `data class`.
     * * A data class should not hold mutable properties.
     * * Use a normal class here.
     */
    private class Node<T>(var value: T, var next: Node<T>? = null)

    // Caution! Possible point of mistake!
    // Ensure that these `head` and `tail` properties are private!
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    /**
     * * Instead of relying on [head == null] check, it is good to maintain a dedicated [size] variable.
     * * Because, if the queue is accepting the nullable values and if the first item is null, but not the subsequent
     * items, we still get [head == null], but the queue is not empty.
     */
    var size: Int = 0
        private set

    val isEmpty: Boolean
        get() = size == 0

    /**
     * # Time Complexity:
     *
     * * There is no iteration and the logic does not depend on or grow with the input size.
     * * Hence, the time complexity is O(1).
     *
     * # Space Complexity:
     *
     * * There is no additional memory we use that depends on or grow with the input size.
     * * Hence, the space complexity is O(1).
     */
    fun offer(value: T) {
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

    /**
     * # Time Complexity:
     *
     * * There is no iteration and the logic does not depend on or grow with the input size.
     * * Hence, the time complexity is O(1).
     *
     * # Space Complexity:
     *
     * * There is no additional memory we use that depends on or grow with the input size.
     * * Hence, the space complexity is O(1).
     */
    fun peek(): T? {
        return head?.value
    }

    /**
     * # Time Complexity:
     *
     * * There is no iteration and the logic does not depend on or grow with the input size.
     * * Hence, the time complexity is O(1).
     *
     * # Space Complexity:
     *
     * * There is no additional memory we use that depends on or grow with the input size.
     * * Hence, the space complexity is O(1).
     */
    fun poll(): T? {
        if (isEmpty) return null
        val front = peek()
        head = head?.next
        size--
        // Caution! Possible point of mistake!
        // Check the empty state after removing an item.
        // Update tail if required after removing an item.
        if (isEmpty) {
            tail = null // This is critical when we remove the only available element.
        }
        return front
    }

    /**
     * # Time Complexity:
     *
     * * There is an iteration and the logic depends on and grow with the input size.
     * * Hence, the time complexity is O(n).
     *
     * # Space Complexity:
     *
     * * Each string uses the value of an item.
     * * Hence, the space complexity is O(n).
     */
    override fun toString() = buildString {
        var curr = head
        while (curr != null) {
            append(" ${curr.value} --> ")
            curr = curr.next
        }
        append(" -- End Of The Queue -- ")
    }
}

fun main() {
    val queue = QueueUsingLinkedList<Int>()
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Dequeue: ${queue.poll()}")
    println("enqueue 1: ${queue.offer(1)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("enqueue 2: ${queue.offer(2)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("enqueue 3: ${queue.offer(3)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("enqueue 4: ${queue.offer(4)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("enqueue 5: ${queue.offer(5)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("enqueue 6: ${queue.offer(6)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.peek()}")
    println("Dequeue: ${queue.poll()}")
    println("Front: ${queue.peek()}")
    println("Dequeue: ${queue.poll()}")
    println("Front: ${queue.peek()}")
    println("Dequeue: ${queue.poll()}")
    println("Front: ${queue.peek()}")
    println("Dequeue: ${queue.poll()}")
    println("Front: ${queue.peek()}")
    println("Dequeue: ${queue.poll()}")
    println("is empty: ${queue.isEmpty}")
}