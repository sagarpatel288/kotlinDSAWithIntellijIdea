package courses.uc.course02dataStructures.module01.section02stacksAndQueues.video01Stack

import java.util.EmptyStackException

/**
 * Reference:
 * [Min Stack](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d1c622ecf1dc67a213254da25d74d60851c66c4e/docs/dataStructures/courses/uc/module01BasicDataStructures/section02stacksAndQueues/video01stacks/050minStack.md)
 *
 * Implements a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * This implementation uses an [ArrayDeque] as the underlying data structure and a clever
 * encoding/decoding scheme to track the minimum value with O(1) auxiliary space complexity.
 *
 * When we use [ArrayDeque] instead of [Array], we get several benefits.
 *
 * * Using [ArrayDeque] eliminates the need to manually track the stack size or manage resizing. We can simply use
 * [ArrayDeque.size], and [ArrayDeque.isEmpty].
 *
 * * [ArrayDeque] gives out-of-the-box [java.util.Stack] like operations such as [ArrayDeque.addFirst],
 * [ArrayDeque.first], [ArrayDeque.removeFirst], etc.
 *
 * * [ArrayDeque] is more efficient for [java.util.Stack], [java.util.Queue], [java.util.Deque] operations than [Array].
 *
 * * We don't need random access like [Array] when we implement a stack.
 * So, [ArrayDeque] is sufficient to implement a stack data structure.
 *
 * ## The Core Idea
 *
 * The algorithm avoids using a second stack by encoding information about the previous minimum
 * directly into the main stack whenever a new, smaller minimum is pushed.
 *
 * - **Pushing `x`**:
 *   - If `x` is not the new minimum, it's pushed directly.
 *   - If `x` is the new minimum, we push a calculated value `2L * x - oldMin` instead.
 *     This encoded value is guaranteed to be less than the new minimum `x`.
 *
 * - **Popping/Topping**:
 *   - If the value at the top of the stack is greater than or equal to the current minimum, it's a normal value.
 *   - If it's less than the current minimum, it must be an encoded value. This tells us two things:
 *     1. The *actual* value being popped/topped is the current minimum.
 *     2. We must decode the *previous* minimum using the formula: `previousMin = 2L * currentMin - encodedValue`.
 *
 * ## Time Complexity
 * - `push`: O(1)
 * - `pop`: O(1)
 * - `top`: O(1)
 * - `getMin`: O(1)
 *
 * ## Space Complexity
 * - `O(n)`, where `n` is the number of elements in the stack.
 *
 * Reference:
 * src/courses/uc/course02dataStructures/module01/video01Stack/03MinStackUsingArray.kt
 */
class MinStackUsingArrayDeque() {

    // Caution! Possible point of mistake!
    // We use `Long` instead of `Int`.
    // Because our encryption and decryption can cause `IntegerOverflow`.
    // Always use `Long` (or `Double` for fractions/decimal) whenever we have a mathematical formula in an algorithm.˛
    private val arrayDeque = ArrayDeque<Long>()

    val size: Int
        get() = arrayDeque.size

    val isEmpty: Boolean
        get() = arrayDeque.isEmpty()

    // Caution! Possible point of mistake!
    // 1. Initially, we have a default placeholder value for the `min`.
    // And we set the default `min` value as `Long.MAX_VALUE`,
    // to ensure that whenever we push the very first item to the stack,
    // it will be less than this default `min`.
    // So that the new (and real) item can become actual new `min`.
    // 2. And yes, it will be a `Long` type instead of an `Int` type.
    // Because our encryption and decryption can cause `IntegerOverflow`.
    // So, ensure to take (use) `Long` instead of `Int`.
    // 3. Also, we don't want external world to accidentally retrieve this default `min` as the actual `min`.
    // So, we allow the external world to access this `min` only through the helper function.
    private var minValue: Long = Long.MAX_VALUE

    // Caution! Possible point of mistake!
    // The external world access the `min` value through this helper function only.
    // Without this helper function, external world can accidentally get a default (dummy, placeholder) value of `min`.
    fun getMin() = if (isEmpty) throw EmptyStackException() else minValue.toInt()

    fun push(value: Int) {
        val longValue = value.toLong()
        // We perform encoding when we find a `new min` to store the `previous` and the `current` min in one value.
        // Because the `new` min is going to replace the `previous` min and becomes the `current` min.
        // And when we remove the `current` min, we need to retrieve and restore the `previous` min.
        // But, if the stack is empty, we don't have any previous minimum to restore when we pop this new min.
        // So, we don't need to encode the very first item.
        // What happens if we encode it? We have to go through unnecessary computational overhead.
        // Because our current and default value is Long.MAX_VALUE.
        // When we encode, it becomes: 2L * incoming - Long.MAX_VALUE, which will be a massive negative number.
        // Then, we will push the encoded value to the stack.
        // Now, when we pop that value, we want to return the actual min value and restore the previous min.
        // But there is no previous min, because this is the first and only min value.
        // But we still have to return the actual value.
        // So, we would again go through the decoding computation.
        // In the end, we would return the actual value.
        // But we could have done these all without these heavy encoding-decoding computation, for the first item.
        // And we would still maintain the correctness of the algorithm, with higher efficiency.
        // Hence, avoid encryption-decryption when we push the first and only item to the stack.
        if (isEmpty) {
            minValue = longValue
            arrayDeque.addFirst(longValue)
        } else if (longValue >= minValue) {
            // Caution! Possible point of mistake!
            // We don't encode the item that is equivalent to the current min.
            // Because we are interested in one `min` value, and not multiple or in any specific order.
            // And if we try to encode the value that is equivalent to the current min, it does not change the value.
            // For example, if the new incoming value is `i` and the current min value is `c`.
            // Now, we are saying that both the values are equal.
            // So, `i == c`.
            // Now, the encoding formula is: 2 * i - c
            // But we can replace `i` with `c` or vice versa.
            // So, it becomes: `2i - i = i`.
            // See, we got the same original incoming value even after going through the encoding computation!
            // So, why to waste resources on the computation operation that ultimately does nothing?
            // Also, when we `pop`, it decodes only `if (popped < currentMin)`.
            // And the decoding formula is: `2 * currentMin - poppedEncoded`.
            // And we have just seen that encoding does not change the item value that is equivalent to the current min.
            // It means that: `poppedEncoded = currentMin`.
            // So, we get: `2 * currentMin - currentMin = currentMin`.
            // Ultimately, we are getting `currentMin` only!
            // So, for the items that are equivalent to the current min, there will be no decoding for that!
            // So, why to encode-decode an item whose resultant value remains the same even after encoding-decoding?
            // Why to encode-decode an item that gives `currentMin` even without going through encoding-decoding?
            // Hence, we don't encode-decode the items that are equivalent to the current min.
            // We only encode-decode items that are less than the current min.
            arrayDeque.addFirst(longValue)
        } else {
            val encoded = 2L * value - minValue
            minValue = longValue
            arrayDeque.addFirst(encoded)
        }
    }

    fun peek(): Int {
        if (isEmpty) throw EmptyStackException()
        val topped = arrayDeque.first()
        // Caution! Possible point of mistake!
        // We cannot return the topped (peeked) value directly!
        // It can be an encoded value!
        // So, first we check.
        // If it is less than the current min, then it is an encoded value.
        // In that case, we return the current min.
        // Otherwise, if the peeked value is greater than or equal to the current min,
        // we return it as it is.
        return if (topped < minValue) {
            minValue.toInt()
        } else {
            topped.toInt()
        }
    }

    fun pop(): Int {
        if (isEmpty) throw EmptyStackException()
        val popped = arrayDeque.removeFirst()
        val answer = if (popped < minValue) {
            val actualValue = minValue
            val oldMin = (2L * minValue) - popped
            minValue = oldMin
            actualValue.toInt()
        } else {
            popped.toInt()
        }
        // Caution! Possible point of mistake!
        // This is very critical.
        // Even though we ensure the correctness through our wrapper function `fun min()` and `isEmpty` gatekeepers,
        // we must reset the `min` value.
        // Yes, it is right that `fun min()` will check `isEmpty` and we will get the correct answer only.
        // Yes, it is right that `isEmpty` ensures that when we `push` the first item, we would update the `min`.
        // However, without this reset, those are `masks` that hide the actual bug.
        // To ensure the entire technical hygiene, we must reset the `min` when the stack is empty.
        if (isEmpty) {
            minValue = Long.MAX_VALUE
        }
        return answer
    }

    override fun toString() = buildString {
        for ((index, value) in arrayDeque.withIndex()) {
            append("index is $index, and value is $value --> ")
        }
        append("-- End Of The Stack!-- ")
    }
}

fun main() {
    val minStack = MinStackUsingArrayDeque()
    println("isEmpty: ${minStack.isEmpty}")
    println("printStack: $minStack")
    try {
        println("top: ${minStack.peek()}")
    } catch (e: Exception) {
        println("Exception: ${e.message}")
    }
    try {
        println("pop: ${minStack.pop()}")
    } catch(e: Exception) {
        println("Exception: ${e.message}")
    }
    println("push 0: ${minStack.push(0)}")
    println("isEmpty: ${minStack.isEmpty}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("push -2: ${minStack.push(-2)}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("push 2: ${minStack.push(2)}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("push -3: ${minStack.push(-3)}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("push -3: ${minStack.push(-3)}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
    println("top: ${minStack.peek()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.getMin()}")
}