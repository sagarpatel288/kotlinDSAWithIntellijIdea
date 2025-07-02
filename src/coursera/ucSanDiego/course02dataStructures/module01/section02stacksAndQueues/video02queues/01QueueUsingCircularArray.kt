package coursera.ucSanDiego.course02dataStructures.module01.section02stacksAndQueues.video02queues

/**
 * [java.util.Queue] implementation using a circular array.
 *
 * # Why a circular array instead of a plain array?
 *
 * * Because a plain array would take `O(n)` time complexity for each [dequeue] operation due to shifting.
 *
 * # How does a circular array improve (optimize) the time complexity?
 *
 * * We use two variables: [readIndex], and [writeIndex] to track reading (dequeue) and writing (enqueue) respectively.
 * * For each [enqueue] operation, the [writeIndex] moves one step forward.
 * * Similarly, for each [dequeue] operation, the [readIndex] moves one step forward.
 *
 * # How do we prevent `IndexOutOfBoundsException` if we keep increasing the [readIndex] and [writeIndex]?
 *
 * * We wrap them back around by using modulus capacity.
 * * For example:
 * ```
 * writeIndex = (writeIndex + 1) % capacity
 * ```
 *
 * # Is there any problem we might get when we use [readIndex] and [writeIndex] to optimize the dequeue operation?
 *
 * * Yes. We might get "One empty slot - unused allocated memory."
 * * To solve that, we can take one extra variable, [size] to track the queue size.
 *
 * # What is the "One empty slot - unused allocated memory" problem?
 *
 * * Please check the [size] property for the details.
 *
 * # Time Complexity:
 *
 * * The overall time complexity is O(1) without the [toString] function.
 * * The [toString] function uses O(n) to print each value.
 *
 * # Space Complexity:
 *
 * * The underlying [array] of size [capacity].
 * * So, if [capacity] is `n,` then the space complexity is O(n).
 * * Other variables that we take do not depend on or grow with the input size [capacity].
 */
class QueueUsingCircularArray<T>(val capacity: Int) {

    init {
        require(capacity > 0) { "Capacity must be greater than 0" }
    }

    var readIndex = 0
        private set

    var writeIndex = 0
        private set

    private val array = arrayOfNulls<Any?>(capacity)

    /**
     * # Why the [size] property?
     *
     * * To decide whether the `Queue` is full or empty.
     * * If we don't use the [size] variable, we will have to leave one slot empty to distinguish between
     * the empty state Vs. the full state.
     *
     * # How do we decide whether the queue is empty or full without the size variable?
     *
     * * In that case, we have to leave one slot empty.
     * * For example:
     * ```
     * val isFull: Boolean
     *     get() = (writeIndex + 1) % capacity == readIndex
     *
     * val isEmpty: Boolean
     *     get() = writeIndex == readIndex
     * ```
     * * Here, we have two variables: [readIndex] and [writeIndex].
     * * Initially, when the underlying array is empty, both the indices [readIndex] and [writeIndex] will be at the
     * same index.
     *
     * ```
     *
     *   readIndex == writeIndex
     *
     *              |
     *              |
     *              |
     *              v
     *           +-----+-----+-----+-----+-----+
     *           |     |     |     |     |     |
     *           |     |     |     |     |     |
     *           +-----+-----+-----+-----+-----+
     * Indices =    0     1     2     3     4
     *
     * ```
     *
     * * So, `writeIndex == readIndex` will indicate an empty queue.
     *
     * ```
     *           ---+-----+-----+-----+-----+---
     *              |     |     |     |     |
     *    Dequeue   |  a  |  b  |  c  |  d  |    Enqueue
     * <----------- |     |     |     |     | <-----------
     *           ---+-----+-----+-----+-----+---
     * ```
     * * Now, we know that a queue follows the FIFO principle.
     * * And an array adds (enqueue) an item to the end.
     * * So, we must pull out the item from the front to respect and follow the FIFO system.
     * * Hence, we take these two variables [readIndex], and [writeIndex] to track removal index (dequeue index) and
     * the insertion index (enqueue index) respectively.
     * * So, every time we remove an item, the [readIndex] moves one step forward.
     * * And similarly, every time we insert an item, the [writeIndex] moves one step forward.
     *
     * ```
     *      readIndex    writeIndex
     *
     *             |      |
     *             |      |
     *             |      |
     *             v      v
     *           +-----+-----+-----+-----+-----+
     *           |     |     |     |     |     |
     *           |  a  |     |     |     |     |
     *           +-----+-----+-----+-----+-----+
     * Indices =    0     1     2     3     4
     *
     * // After inserting an item, the writeIndex moved one step forward to index 1.
     * // We have not removed any item yet. So, the readIndex is still at index 0.
     *
     * ```
     * * Now, let us assume that we kept adding the items to the queue as below:
     *
     * ```
     *      readIndex                      writeIndex
     *
     *             |                        |
     *             |                        |
     *             |                        |
     *             v                        v
     *           +-----+-----+-----+-----+-----+
     *           |     |     |     |     |     |
     *           |  a  |  b  |  c  |  d  |     |
     *           +-----+-----+-----+-----+-----+
     * Indices =    0     1     2     3     4
     * ```
     * * Now, adding one more item at index 4, will make the `writeIndex` equal to the `readIndex.`
     *
     * ```
     *      readIndex == writeIndex
     *
     *             |
     *             |
     *             |
     *             v
     *           +-----+-----+-----+-----+-----+
     *           |     |     |     |     |     |
     *           |  a  |  b  |  c  |  d  |  e  |
     *           +-----+-----+-----+-----+-----+
     * Indices =    0     1     2     3     4
     * ```
     * * Now, the condition `readIndex == writeIndex` represents a full queue.
     * * But initially, the same condition `readIndex == writeIndex` used to represent an empty queue.
     * * So, we got conflicts, confusions, and ambiguity.
     * * So, we have a problem. How do we decide the empty state Vs. the full state of the queue?
     * * Because we cannot use the same condition, the same formula to decide both states.
     * * So, the solution is to prevent the situation where `readIndex == writeIndex` shows the full queue.
     *
     * * How do we prevent that?
     *
     * * Before we `enqueue` an item, we check if `(writeIndex + 1) % capacity == readIndex`, which translates to:
     * ```
     * Adding an item will move the `writeIndex` one step forward. Will it result in `writeIndex == readIndex`?
     * ```
     * * If it is true, then we cannot enqueue (add) the item.
     * * For example: Let us assume that we have the following state:
     * ```
     *      readIndex                      writeIndex
     *
     *             |                        |
     *             |                        |
     *             |                        |
     *             v                        v
     *           +-----+-----+-----+-----+-----+
     *           |     |     |     |     |     |
     *           |  a  |  b  |  c  |  d  |     |
     *           +-----+-----+-----+-----+-----+
     * Indices =    0     1     2     3     4
     * ```
     * * Now, we want to add `e`.
     * * But, before we add `e`, we check if `(writeIndex + 1) % capacity == readIndex`.
     * * Which is `(4 + 1) % 5 = 5 % 5 = 0` and at `0`, we already have the `readIndex.`
     * * So, adding `e` will make the `writeIndex` equal to the `readIndex.`
     * * So, we don't do it.
     * * We don't add `e` even though we have the space to enable us distinguish and decide the empty state Vs. the full
     * state properly.
     * * And this is how, we end up with "One empty slot, unused allocated memory" if we don't use the [size] variable.
     */
    var size: Int = 0
        private set

    val isEmpty: Boolean
        get() = size == 0

    val isFull: Boolean
        get() = size == capacity

    /**
     * [java.util.Stack] overflow throws an exception.
     * But, [java.util.Queue.offer] overflow returns false. Why?
     *
     * Because the purpose of a [java.util.Queue.offer] is more like a buffer where the waiting period is normal.
     * If there is no more room, the request can wait. It is OK.
     * However, the purpose of a [java.util.Queue.add] is more like a bounded restriction where an overflow can throw
     * an exception.
     * So, we have both the functions (methods) in [java.util.Queue].
     * For [java.util.Queue.add], overflow is an exceptional situation. Hence, it throws an exception.
     * For [java.util.Queue.offer], overflow is normal. Hence, it simply returns false.
     *
     * On the other hand, the purpose of a [java.util.Stack] is more like a tracker to track function calls and data.
     * If there is no more room, we cannot track it.
     * In fact, the built-in [java.util.Stack] uses [java.util.Vector]. So, it grows dynamically.
     *
     * # Key-point:
     *
     * * We cannot simply increase the [writeIndex] by one and finish the job. It can go beyond the [capacity] of the
     * [array].
     * * We wrap around the [writeIndex]. That is why we call it a circular array.
     *
     * # Time Complexity:
     *
     * * It is O(1) as there is no iteration and the logic does not depend on or grow with the input size [capacity].
     *
     * # Space Complexity:
     *
     * * It is O(1) as we are not taking any additional variable other than the global [array] which uses O(n).
     */
    fun enqueue(value: T): Boolean {
        if (isFull) return false
        array[writeIndex] = value
        writeIndex = (writeIndex + 1) % capacity
        size++
        return true
    }

    /**
     * Again, [java.util.Queue] offers two methods.
     * [java.util.Queue.poll] returns null in the case of underflow.
     * Whereas, [java.util.Queue.remove] throws [java.util.NoSuchElementException] for the underflow.
     *
     * # Key-point:
     *
     * * We cannot simply increase the [readIndex] by one and finish the job. It can go beyond the [capacity] of the
     * [array].
     * * We wrap around the [readIndex]. That is why we call it a circular array.
     *
     * # Time Complexity:
     *
     * * We are not iterating and the logic does not depend on or grow with the input size [capacity].
     * * Hence, it is O(1).
     *
     * # Space Complexity:
     *
     * * It is O(1) as we are not taking any other variable than using the global [array] which uses O(n) space.
     */
    fun dequeue(): T? {
        return front()?.let {
            array[readIndex] = null // To prevent memory loitering, we nullify the slot.
            readIndex = (readIndex + 1) % capacity
            size--
            it
        }
    }

    /**
     * When we want to read the top (front) item.
     *
     * # Time Complexity:
     *
     * * It is O(1) as there is no iteration and the logic does not depend on or grow with the input size [capacity].
     *
     * # Space Complexity:
     *
     * * It is O(1) as there is no additional memory we use here other than the global [array] which uses O(n).
     */
    fun front(): T? {
        if (isEmpty) return null
        @Suppress("Unchecked_Cast")
        return array[readIndex] as T
    }

    /**
     * # Time Complexity:
     *
     * * We iterate through the entire queue to print each value.
     * * Hence, it is O(n).
     *
     * # Space Complexity:
     *
     * * Each string represents a value of each item.
     * * Hence, it is O(n).
     */
    override fun toString() = buildString {
        for (i in 0..<size) {
            append("index = $i, value = ${array[i]}, ")
        }
        append(" -- End Of The Queue -- ")
    }
}

fun main() {
    val queue = QueueUsingCircularArray<Int>(5)
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
    println("isFull: ${queue.isFull}")
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
    println("isFull: ${queue.isFull}")
    println("is empty: ${queue.isEmpty}")
}