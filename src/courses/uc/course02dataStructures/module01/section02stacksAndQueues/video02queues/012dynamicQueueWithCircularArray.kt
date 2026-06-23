package courses.uc.course02dataStructures.module01.section02stacksAndQueues.video02queues

/**
 * // Caution! Possible point of mistake!
 * * Ensure that the [capacity] is private!
 * * We don't want to let outside world change it!
 */
@Suppress("UNCHECKED_CAST")
class QueueWithDynamicCircularArray<T>(private var capacity: Int = 10) {

    init {
        // Caution! Possible point of mistake!
        // Without this safety, we can get several problems like:
        // Trying to `resize` the array whose initial capacity is `0`!
        // newCap = cap * 2 = 0 * 2
        // It will always remain `0`!
        // And when we try `offer`, `writeIndex = (writeIndex + 1) % capacity` can cause exception!
        require(capacity > 0) {
            "Capacity must be greater than 0!"
        }
    }

    var size = 0
        private set

    // Caution! Possible point of mistake!
    // With a dynamic array, the queue never becomes `full` as per the outer world's definition.
    // Maybe, it becomes `full` temporarily, but it is internal and we immediately `resize` it.
    // So, technically, the outer world never gets `isFull` as the `true`!
    // So, we keep it private to use it during the `resize` operation.
    private val isFull: Boolean
        get() = size == capacity

    val isEmpty: Boolean
        get() = size == 0

    private var array = arrayOfNulls<Any>(capacity)
    private var readIndex = 0
    private var writeIndex = 0

    // Caution! Possible point of mistake!
    // Ensure that the `resize` function is private!
    // We don't want to let the outside world call this function!
    private fun resize() {
        val newCap = capacity * 2
        val reArray = arrayOfNulls<Any>(newCap)
        // Caution! Possible point of mistake!
        // We must use the `size` variable as the end-boundary instead of using `array.indices`.
        // Because `array.indices` is a physical size, whereas the `size` represents the logical size!
        // And we want to follow the logical size.
        // Suppose, we get a task to `resize` before the `queue` is full!
        // Or maybe, we get the `shrink` feature, where we reduce the physical capacity of the array!
        // For example, suppose that the array's capacity is `10`, and the queue size is `5`.
        // If we use `array.indices`, it will go from `0 to 9`.
        // If we use `<size`, it will go from `0 to 4`.
        // Using the `size` variable as the end-boundary is technically and logically more correct.
        // Because, the `size` represents the valid queue (valid items).
        // Whereas the `array` represents `queue` plus empty slots (maybe default values!).
        // When we rebuild the queue, we want to copy the valid items, and nothing else, no extra work.
        // The `queue` contains the `size` elements, not the `capacity` elements!
        for (i in 0..<size) {
            val originalIndex = (readIndex + i) % capacity
            reArray[i] = array[originalIndex]
        }
        array = reArray
        readIndex = 0
        writeIndex = size
        capacity = newCap
    }

    fun offer(item: T) {
        if (isFull) {
            resize()
        }
        array[writeIndex] = item
        writeIndex = (writeIndex + 1) % capacity
        size++
    }

    fun peek(): T? {
        if (isEmpty) return null
        return array[readIndex] as T
    }

    fun poll(): T? {
        if (isEmpty) return null
        val front = array[readIndex]
        array[readIndex] = null
        readIndex = (readIndex + 1) % capacity
        size--
        return front as T
    }
}