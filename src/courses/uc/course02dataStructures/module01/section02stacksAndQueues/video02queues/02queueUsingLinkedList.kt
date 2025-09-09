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

    private data class Node<T>(var value: T, var next: Node<T>? = null)

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
    fun front(): T? {
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
    fun dequeue(): T? {
        if (isEmpty) return null
        val topped = front()
        head = head?.next
        size--
        if (isEmpty) {
            tail = null // This is critical when we remove the only available element.
        }
        return topped
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
    println("Dequeue: ${queue.dequeue()}")
    println("enqueue 1: ${queue.enqueue(1)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("enqueue 2: ${queue.enqueue(2)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("enqueue 3: ${queue.enqueue(3)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("enqueue 4: ${queue.enqueue(4)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("enqueue 5: ${queue.enqueue(5)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("enqueue 6: ${queue.enqueue(6)}")
    println("is empty: ${queue.isEmpty}")
    println("Print: $queue")
    println("Front: ${queue.front()}")
    println("Dequeue: ${queue.dequeue()}")
    println("Front: ${queue.front()}")
    println("Dequeue: ${queue.dequeue()}")
    println("Front: ${queue.front()}")
    println("Dequeue: ${queue.dequeue()}")
    println("Front: ${queue.front()}")
    println("Dequeue: ${queue.dequeue()}")
    println("Front: ${queue.front()}")
    println("Dequeue: ${queue.dequeue()}")
    println("is empty: ${queue.isEmpty}")
}