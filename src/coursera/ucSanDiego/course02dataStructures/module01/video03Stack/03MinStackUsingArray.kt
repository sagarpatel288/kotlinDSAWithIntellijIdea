package coursera.ucSanDiego.course02dataStructures.module01.video03Stack

import java.util.EmptyStackException

/**
 * The `MinStack` problem:
 *
 * # ----------------------- Problem Statement -----------------------
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * * Implement the MinStack class:
 *
 * * MinStack() initializes the stack object.
 * * void push(int val) pushes the element val onto the stack.
 * * void pop() removes the element on the top of the stack.
 * * int top() gets the top element of the stack.
 * * int getMin() retrieves the minimum element in the stack.
 * * You must implement a solution with O(1) time complexity for each function.
 *
 * ## ----------------------- Constraints -----------------------
 *
 * * -2^31 <= val <= 2^31 - 1
 * * Methods pop, top, and getMin operations will always be called on non-empty stacks.
 * * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 *
 * ## ----------------------- Heads Up! -----------------------
 *
 * Prefer [ArrayDeque] over [Array] as the underlying data structure.
 * The reason we use [Array] here is to understand the concept.
 * Once we understand the concept, we can check the file below that uses the [ArrayDeque]:
 *
 * src/coursera/ucSanDiego/course02dataStructures/module01/video03Stack/04minStackUsingArrayDeque.kt
 *
 * ## ----------------------- Solution Overview -----------------------
 *
 * Implements a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * This implementation uses a single array and a clever encoding/decoding scheme to track the minimum value
 * with O(1) auxiliary space complexity.
 *
 * ## The Core Idea
 *
 * When a new value `x` is pushed:
 * - If `x` is greater than or equal to the current minimum, it's pushed directly onto the stack.
 * - If `x` is the new minimum, we need to store the *previous* minimum. We do this by encoding it.
 *   Instead of pushing `x`, we push `2L * x - currentMin`. This encoded value is guaranteed to be
 *   less than `x` (the new minimum).
 *
 * When a value is popped or topped:
 * - If the value on the stack is greater than or equal to the current minimum, it's a normal value.
 * - If it's less than the current minimum, it's an encoded value. The actual value is the current minimum,
 *   and the previous minimum can be decoded using `previousMin = 2L * currentMin - encodedValue`.
 *
 * Using a `LongArray` prevents integer overflow during the encoding calculation (`2L * x`).
 *
 * ### ----------------------- Time Complexity -----------------------
 *
 * It uses O(1) for `push,` `top,` `pop,` and `getMin` functions (operations).
 * It uses O(n) to print itself.
 *
 * ### ----------------------- Space Complexity -----------------------
 *
 * Overall, this data structure uses `O(n)` space due to the container that we use to store the stack elements.
 * The benefit of the encoding approach in this solution is that it does not require an extra container to store and
 * manage the `min` elements.
 *
 * If we had used an extra container (like [Array], [ArrayDeque], etc.), we would have got `O(2n)` space in the
 * worst case where each new push is a new `min.`
 *
 * References:
 * [Shraddha K]((https://youtu.be/wHDm-N2m2XY?si=ZGfPBFLljG46UqDw))
 * [LeetCode Problem 155](https://leetcode.com/problems/min-stack/description/)
 */
class MinStackUsingArray(private val capacity: Int) {

    init {
        require(capacity > 0) { "The stack must have capacity greater than 0" }
    }

    /**
     * Why [LongArray] instead of [IntArray]?
     * Because, we will be doing encoding for the incoming values as and when required, and we want to ensure that we
     * handle it if it exceeds the integer limit (overflow) during the encoding process.
     *
     * For more information about the `encoding` stuff, check [push].
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * We are not using Java's built-in `Stack.`
     * We use [array] of size [capacity].
     * So, if [capacity] is `n`, then this [array] occupies `O(n)` space.
     */
    private val array = LongArray(capacity)

    /**
     * We don't want to allow setting or changing [stackSize] from the outside.
     */
    var stackSize = 0
        private set

    val isEmpty: Boolean
        get() = stackSize == 0

    val isFull: Boolean
        get() = stackSize == capacity

    private var minValue = Long.MAX_VALUE

    /**
     * The key-lemma (formula) is
     * ```
     * if the incoming value is less than the current minValue:
     * Store the encoded value to the stack as: 2 * incoming - minValue
     * ```
     * ## What is going on?
     *
     * We want to implement the `findMin` function (operation, action) in the `Stack` that gives us the minimum element
     * (of type `int`) stored in our `stack` in the O(1) space complexity.
     *
     * Now, if we use a simple global variable, then it is challenging to find the second-last (previous) minimum value.
     * For example, suppose we do the following operations in the given order:
     * ```
     * push -2. So, min = -2.
     * push 0. Still, min = -2.
     * push -3. Now, min = -3.
     * findMin => should give us -3. All good.
     * pop => We delete -3. How do we re-assign min and evaluate (set) it to -2? Who will manage the information of -2?
     * top => should give us 0.
     * findMin => should give us -2. How?
     * ```
     * If we take another list that stores all the minimum values, it can easily exceed the space complexity O(1).
     * In fact, in the worst case where each `push` is a new `min`, the space complexity will be O(n).
     *
     * So, what is the solution?
     * To get the minimum value in O(1) space and time complexity, the value must be ready.
     * Also, we need information of the last `min.`
     * So that if we `pop`, the next `top` element should give us the previous `min` value than what the popped item
     * had held.
     * Read the above statement again.
     * Because it (the explanation, interpretation, logic) holds (hints, shares, conveys) an important message:
     * ```
     * Each element represents the actual value + the min value up to the time the element was pushed.
     * It means that, each element represents an encoded value that can give the actual value of the element as well as
     * the min value up to the time the element was pushed.
     * It means that, we need to encode the value before storing it to the stack.
     * ```
     *
     * ### How do we encode? When do we encode?
     *
     * So, there is a formula (key-lemma) for that.
     * ```
     * if the incoming value is less than the current minValue:
     * Store the encoded value to the stack as: 2 * incoming - minValue
     * ```
     * Why to encode only if the incoming value is less than the current value?
     * What will happen when we top? Do we always decode? How do we decode?
     *
     * Let us address these questions one by one.
     *
     * ### Why to encode only if the incoming value is less than the current value?
     *
     * To reduce (minimize) the work! If the incoming value is already greater than the current min value,
     * we are not going to change the current min value. So, why encode the incoming value in this case?
     *
     * On the other hand, if the incoming value is less than the current min value, and we don't encode it
     * before storing, we lose the opportunity to remember the old (current) min value. Because the incoming value is
     * already less than the current min value, and hence, we are going to replace the current min value with the
     * incoming value. So, before we assign a new min value, we need to remember the current min value. Hence, we encode
     * the incoming value if it is less than the current min value.
     *
     * ### What will happen when we top? Do we always decode?
     *
     * The way we do encoding (only when the incoming value is less than the current min value),
     * we need to decode only if the top element is less than the current min value.
     *
     * For example, suppose we do the following operations in order:
     * ```
     * push -2. So, min = -2.
     * push 0. And 0 > -2. So, no change in min. Still, min = -2.
     * push -3. And -3 < -2. So, val encode = 2 * incoming - min = 2 * (-3) - (-2) = -6 + 2 = -4. And min = -3.
     * findMin => Gives us -3. All good.
     * pop => Top is -4 < min. So, val old min = 2 * curr min - encoded = 2 * -3 - (-4) = -6 + 4 = -2
     * top => Gives us 0.
     * findMin => Gives us -2. Because, we have already changed it. All good.
     * ```
     * So, the answer is:
     * * When we top, if the top element is less than the current min, we need to decode the top element.
     * * We decode the top element only if the top element is less than the current min.
     *
     * The next question is:
     *
     * ### How do we decode?
     *
     * So, the formula is similar:
     * ```
     * val oldMin = 2 * currentMin - encodedValue
     * ```
     * ## Explanation of the encoding formula:
     * ```
     * val toStore = 2 * incomingValue - currentMinValue
     * ```
     * * We use the above formula to encode the incoming value and the current min value at the time.
     * * We encode the incoming value only when we find that the incoming value is less than the current min value.
     * * We want to encode only those incoming values that are less than the current min value.
     * * Every time we encode the incoming value, it inherently proves that the incoming value is less than the current
     * min value. So, the incoming value becomes the new current minimum value.
     * * Every time we `top,` (read) or `pop` (remove) an element, we need to identify and distinguish the normal values
     * from the encoded values.
     * * Once we identify the encoded value, we should be able to get the old min value before the encoded value using
     * the encoded value itself. That's why it is an encoded value that contains both the incoming value i.e., the true
     * value, and the current min value at (or before) the arrival time of the incoming value.
     *
     * ### How do we identify and distinguish the encoded values from the normal values?
     * * The key-lemma is:
     * ```
     * We need to ensure that the encoded value is smaller than the current (new) minimum value.
     * ```
     * * Now, let us prove that the encoding formula ensures that the encoded value is always less than the current min.
     * ```
     * encoded = 2 * incoming - currentMin
     * encoded = incoming + incoming - currentMin
     * encoded - incoming = incoming - currentMin --------------------------------------------------------- (1)
     * ```
     * * Now, we know that we encode the incoming value only if it is less than the current min value, right?
     * * So, when we encode, the incoming value is less than the current min value.
     * * It means: `incoming - currentMin` gives a negative result.
     * * So, we can say:
     * ```
     * encoded - incoming < 0
     * encoded < incoming --------------------------------------------------------- (2)
     * ```
     * * The expression (2) indicates that the resultant encoded value will be less than the incoming value.
     * * We also know that we encode only when the incoming value is less than the current min value.
     * * And we also know that the incoming value will become the new current min value.
     * * It means that the encoded value will always be smaller than the new current min value.
     * * And that is how we identify and distinguish the encoded values from the normal values.
     *
     * ---------------------------------------------------------
     *
     * * We can think (walk) it the other way around also.
     * * We know that we want to keep the encoded value less than the (new) current min value.
     * ```
     * encoded < newMin
     * ```
     * * And we know that this `newMin` is actually the `incoming` value itself.
     * * We make it `newMin` because it is less than the old min value.
     * * So, it translates into the following expression:
     * ```
     * encoded < incoming
     * encoded - incoming < 0 --------------------------------------------------------- (1)
     * ```
     * * Now, we also know that the incoming value is less than the current (old) min value when we encode.
     * * So, it translates into the following expression:
     * ```
     * incoming < current (old) min
     * incoming - oldMin < 0 --------------------------------------------------------- (2)
     * ```
     * * For both the above expressions (1) and (2), we can write:
     * ```
     * encoded - incoming = incoming - oldMin
     * encoded = incoming + incoming - oldMin
     * encoded = 2 * incoming - oldMin --------------------------------------------------------- (3)
     * ```
     * * The expression (3) is our encoding formula.
     *
     * We can understand it in another way, also.
     *
     * * We want to prove that:
     * ```
     * The encoded value will always be less than the latest min value.
     * => encoded < latestMin
     * ```
     * * Now, we know that when we encode, the incoming value is less than the latest min value.
     * ```
     * => incoming < latestMin
     * => incoming - latestMin < 0
     * => incoming + incoming - latestMin < incoming //Added `incoming` at both ends.
     * => (2 * incoming) - latestMin < incoming
     * => We can represent (replace) the expression "(2 * incoming) - latestMin" with "encoded."
     * So, it becomes:
     * => encoded < incoming
     * => Now, we encode when the incoming value is less than the latest min.
     * => And then the incoming value becomes the new latest min.
     * So, it becomes:
     * => encoded < latestMin
     * Hence, we just explained how the encoded value will always be less than the latest min value!
     * ```
     *
     * ## Explanation of the decoding formula (Or connecting the encoding and the decoding formulas):
     *
     * * The encoding formula is:
     *
     * ```
     * val encodedToStore = 2 * incoming - minValue
     *                             ^          ^
     *                             |          |
     *                             |          |
     *                             |        1. It will become the old minValue. --------------------------(3)
     *                             |
     *                         1. It will become the new minValue. ---------------------------------------(1)
     *                         2. Note that it is the actual (without encoding) incoming value. ----------(2)
     * ```
     * * Now, about the decoding formula:
     * * We want to get the original incoming value and the old min value.
     * * The current (new, latest) min value is the original incoming value (Note 1 and 2).
     * * And to get the old min value, we can re-arrange the formula as below:
     *
     * ```
     * val encodedToStore = 2 * incoming (it will be the latest current min) - minValue (this is the old min)
     * val minValue (the old min) = 2 * incoming (the latest current min) - encodedToStore (the top value)
     * So:
     * val oldMin = 2 * currentMin - encoded
     * ```
     *
     * ## ----------------------- Complexity Analysis -----------------------
     *
     * ### ----------------------- Time Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * There is no loop.
     * It is a constant-time operation.
     * It takes O(1) time.
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * It does not take any additional memory.
     * It takes constant memory.
     * It is O(1) space.
     *
     * ### ----------------------- References -----------------------
     *
     * [Shraddha K: Apna Collage](https://youtu.be/wHDm-N2m2XY?si=ZGfPBFLljG46UqDw)
     * [LeetCode Problem 155](https://leetcode.com/problems/min-stack/description/)
     */
    fun push(value: Int) {
        // If the stack is full, we cannot push the new item.
        // Throwing an exception is the standard library practice for stack overflow and underflow cases.
        // StackOverflowError is reserved for JVM function calls (e.g., recursion).
        if (isFull) throw IllegalStateException("The stack is already full! Capacity is $capacity, numberOfElements are $stackSize")
        val longValue = value.toLong()
        // If the stack is empty, the incoming value becomes the new latest min value.
        if (isEmpty) {
            array[stackSize++] = longValue
            minValue = longValue
        } else if (longValue >= minValue) {
            // If the incoming value is greater than or equal to the current min value,
            // it doesn't change the current min value. So, we don't need any encoding. We store it in the stack.
            array[stackSize++] = longValue
        } else {
            //Only if the incoming value is less than the current latest min value,
            // it changes and replaces the current min value. So, we store the encoded value in the stack.
            // Use `2L` instead of `2` to prevent integer overflow!
            // E.g., if the `value` is `Int.MAX_VALUE,` multiplying it by `2` can easily cause integer overflow!
            val encodedValue = 2L * value - minValue
            minValue = longValue
            array[stackSize++] = encodedValue
        }
    }

    /**
     * ### ----------------------- Time Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * There is no loop.
     * It is a constant-time operation.
     * It is O(1) time.
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * The variable we create inside the function does not depend on (or grow with) the input size.
     * It is a constant memory operation.
     * It is O(1) space.
     */
    fun top(): Int {
        // Throwing an exception is the standard library practice for stack overflow and underflow cases.
        if (isEmpty) throw EmptyStackException()
        val topped = array[stackSize - 1]
        // If the `topped` value is less than the current (latest) min value, it means the `topped` value is encoded.
        // It is the value that has replaced the old min value with the new min value.
        // And the new (current, latest) min value is the original incoming value.
        return if (topped < minValue) {
            minValue.toInt()
        } else {
            topped.toInt()
        }
    }

    /**
     * ### ----------------------- Time Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * There is no loop.
     * It takes constant time.
     * It is O(1) time.
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * The function (operations and actions) does not depend on (or grow with) the input size.
     * We create variables inside the function, but it does not depend on (or grow with) the input size.
     * It takes constant memory.
     * It is O(1) space.
     */
    fun pop(): Int {
        if (isEmpty) throw EmptyStackException()
        val popped = array[stackSize - 1]
        stackSize--
        return if (popped < minValue) {
            val original = minValue
            val oldMin = (2L * minValue) - popped
            minValue = oldMin
            original.toInt()
        } else {
            popped.toInt()
        }
    }

    /**
     * ### ----------------------- Time Complexity -----------------------
     *
     * It is a computed (computational) val property that reads and gives the value using another mutable property,
     * [minValue].
     * It just reads the value. The operation and action do not depend on (or grow with) the input size.
     * Hence, the time complexity of this function (operations and actions) is O(1).
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * We use only 1 computational immutable property regardless of the input size.
     * We don't use anything else, and it does not depend on (or grow with) the input size.
     * Hence, the space complexity is `O(1)`.
     *
     * On the other hand, if we had used a separate container (an [Array], [ArrayDeque], or anything else) to manage
     * the `min` item/s, it could have been `O(n)` space complexity in the worst case, where each new push is a new min.
     */
    val minStackItem: Int
        get() = if (isEmpty) throw EmptyStackException() else minValue.toInt()

    /**
     * The size [capacity] of the [array] can be far more (oversized, too much unallocated memory).
     * Hence, we print only the filled items - items that have been pushed to the stack.
     *
     * ### ----------------------- Time Complexity -----------------------
     *
     * We iterate through the [array] to print the value of each element.
     * Clearly, the iteration depends on the size of the [array].
     * The iteration limit is constrained by [stackSize].
     * If `n` is the [stackSize], then the time-complexity of this function is O(n).
     *
     * ### ----------------------- Space Complexity -----------------------
     *
     * We iterate through the [array] up to [stackSize] to represent each element's value.
     * Each string represents an individual element value.
     * So, if `n` is the [stackSize], then the space complexity of this function is O(n).
     */
    override fun toString() = buildString {
        for (i in 0..<stackSize) {
            append("${array[i]} --> ")
        }
        append(" -- End Of The Stack! -- ")
    }
}

fun main() {
    val minStack = MinStackUsingArray(5)
    println("isEmpty: ${minStack.isEmpty}")
    println("printStack: $minStack")
    try {
        println("top: ${minStack.top()}")
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
    println("min: ${minStack.minStackItem}")
    println("push -2: ${minStack.push(-2)}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("push 2: ${minStack.push(2)}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("push -3: ${minStack.push(-3)}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("push -3: ${minStack.push(-3)}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
    println("top: ${minStack.top()}")
    println("pop: ${minStack.pop()}")
    println("print stack: $minStack")
    println("min: ${minStack.minStackItem}")
}