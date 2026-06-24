package courses.uc.course02dataStructures.module01.section02stacksAndQueues.video01Stack

class MaxStackUsingArrayDeque {
    private val stack = ArrayDeque<Long>()
    private var max = Long.MIN_VALUE
    val size: Int
        get() = stack.size
    val isEmpty: Boolean
        get() = stack.isEmpty()

    fun max() = if (isEmpty) null else max.toInt()

    fun push(data: Int) {
        val item = data.toLong()
        if (isEmpty) {
            max = item
            stack.addFirst(item)
        } else if (item > max) {
            val encode = 2 * item - max
            max = item
            stack.addFirst(encode)
        } else {
            stack.addFirst(item)
        }
    }

    fun pop(): Int? {
        if (isEmpty) return null
        val top = stack.removeFirst()
        val result = if (top > max) {
            val answer = max
            val decode = 2 * max - top
            max = decode
            answer.toInt()
        } else {
            top.toInt()
        }
        // Caution! Possible point of mistake!
        // If we don't reset the `max` value, it will hold the stale (leftover) value.
        if (isEmpty) {
            max = Long.MIN_VALUE
        }
        return result
    }

    fun peek(): Int? {
        if (isEmpty) return null
        val top = stack.removeFirst()
        return if (top > max) {
            max.toInt()
        } else {
            top.toInt()
        }
    }
}