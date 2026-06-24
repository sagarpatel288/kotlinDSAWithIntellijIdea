package courses.uc.course02dataStructures.module01.section02stacksAndQueues.video01Stack

/**
 * When we implement a stack using an array, we need to define the fixed size.
 *
 * @param capacity We use the term `capacity` instead of `size` because `size` might be interpreted as the
 *                 `current size.` However, when we implement `stack,` using an `array,` we have to specify the `size.`
 *                 But, the stack does not have to be filled up to the `size.` It is just a `capacity` up to which we
 *                 can fill the `stack.`
 *                 The term `capacity` naturally expresses that the `stack` has certain `capacity`.
 *                 It implicitly says that this is not the current size of the stack.
 *                 The current size of the stack might be full, empty, half, or somewhere in between of its `capacity.`
 */
class StackUsingArray<T>(val capacity: Int) {

    init {
        require(capacity > 0) { "Stack capacity must be greater than 0." }
    }

    /**
     * ---------------------------------------------------------
     * In Kotlin (and Java), generics are implemented using type erasure.
     * This means that the type parameter `T` is not retained at runtime; it's only available at compile time.
     * `Arrays`, however, are reified—they require their element type to be known at runtime for type safety.
     * Since the JVM doesn't know what `T` is at runtime, it cannot allocate an array of T.
     * Attempting to do so, results in a runtime error.
     *
     * To work around this, we use `Array<Any?>`, which can hold any type,
     * and then cast elements back to `T` when retrieving them.
     *
     * This is a well-known workaround for implementing generic collections with arrays on the JVM.
     * ---------------------------------------------------------
     * We cannot have ```Array<T>``` because of type erasure.
     * Type erasure means that the type "T" information is not available at runtime,
     * because the generic type parameters are erased at runtime.
     *
     * So, at compile time (while we write the code), the IDE (compiler) may not give any error.
     * But, an `Array` needs a reified type that it can recognize at runtime.
     * So, when we run the code, we might get some error.
     * ```
     * Kotlin: Cannot use 'T' as reified type parameter. Use a class instead.
     * ```
     * It means that at runtime when `Array` tries to use the generic type parameter `T` (reified),
     * there is no information. Because, the generic type parameters are erased at runtime.
     * And an `Array` needs to know its element type `T` at runtime because of type safety.
     *
     * So, we have to use "Any," which is the super type of all the types in Kotlin.
     * (Similar to `Object` in Java.).
     */
    private val array: Array<Any?> = arrayOfNulls(capacity)

    /**
     * Caution! Possible point of mistake!
     * We are not using the `array.size`!
     * Because `array.size` represents the total capacity of the array.
     * Here, [size] represents the current size of the array that can be anything between 0 to capacity (inclusive).
     */
    var size = 0
        private set

    /**
     * [isEmpty] is a state, not an action.
     * Also, we don't want it to be set externally.
     * And it uses the internal state [size] to decide its own state.
     * [isEmpty] depends on the [size], which clearly suggests that [isEmpty] is a computed property.
     * All these characteristics suggest an immutable "val" property, and not the "var" property.
     * Hence, [isEmpty] is a "val" property.
     *
     * Also, if we use `var` with `private set,` we will have to remember to update it for each relevant operation,
     * such as [push], and [pop], which is a maintenance burden and an error-prone approach.
     * Also, It does not align with Kotlin idiomatic and Kotlin standard library conventions.
     * Hence, [isEmpty] is a "val" property.
     */
    val isEmpty: Boolean
        // Caution! Possible point of mistake!
        // We are not using the `array.size`.
        // Because `array.size` represents the final and full capacity of the array.
        // Here, the `size` property represents the current size of the array.
        // Current size of the array can be anything between 0 to capacity (inclusive).
        get() = size == 0 // We can also use array.isEmpty()

    /**
     * [isFull] is a state, not an action.
     * [isFull] depends on the internal state [size].
     * Hence, [isFull] is a computed property.
     * We don't want to set [isFull] from the outside.
     * All these characteristics suggest that [isFull] should be an immutable and computed "val" property.
     */
    val isFull: Boolean
        // Caution! Possible point of mistake!
        // We are not using the `array.size`!
        // Because `array.size` represents the full and final capacity of the array.
        // Here, the `size` property represents the current size of the array.
        // The current size of the array can be anything between 0 and capacity (inclusive).
        get() = size == capacity

    /**
     * Time complexity is O(1), constant time.
     * Space complexity is also O(1), no additional memory.
     */
    fun push(data: T) {
        if (isFull) throw IllegalStateException("Stack is full. Capacity is $capacity, and number of elements are $size")
        array[size++] = data
    }

    /**
     * Time complexity is O(1), constant time.
     * Space complexity is also O(1), no additional memory.
     */
    fun peek(): T {
        if (isEmpty) throw NoSuchElementException("The stack is empty!")
        @Suppress("Unchecked_Cast")
        return array[size - 1] as T
    }

    /**
     * Time complexity is O(1), constant time.
     * Space complexity is also O(1).
     */
    fun pop(): T {
        // top() already handles the "isEmpty" case.
        val top = peek()
        // Caution! Possible point of mistake!
        // It is important to nullify the place that we just removed.
        array[size - 1] = null
        size--
        return top
    }

    /**
     * Instead of using a for loop,
     * the usage of [Array.take] and [joinToString] demonstrates good knowledge of Kotlin idiomatic.
     *
     * Time Complexity, O(n), where `n` is the `numberOfElements.`
     * Space Complexity is also O(n), because the resulting string is a representation of every elements.
     */
    fun asString(): String =
        buildString {
            for (i in 0..<size) {
                append("Index is $i and value is ${array[i]} --> ")
            }
            append(" -- End Of The Stack -- ")
        }

    /**
     * Time Complexity is O(n), because [asString] takes O(n).
     * Space complexity is also O(n), because [asString] takes O(n).
     */
    fun printStack() {
        println(asString())
    }
}

fun main() {
    val stack = StackUsingArray<Int>(3)
    println("Stack capacity is: ${stack.capacity}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Stack push 10: ${stack.push(10)}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Stack push 20: ${stack.push(20)}")
    println("Print stack: ${stack.printStack()}")
    println("Stack push 30: ${stack.push(30)}")
    println("Print stack: ${stack.printStack()}")
    println("Stack top: ${stack.peek()}")
    println("Stack pop: ${stack.pop()}")
    println("Print stack: ${stack.printStack()}")
    println("Stack pop: ${stack.pop()}")
    println("Print stack: ${stack.printStack()}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Stack push 40: ${stack.push(40)}")
    println("Print stack: ${stack.printStack()}")
    println("Stack pop: ${stack.pop()}")
    println("Print stack: ${stack.printStack()}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Stack pop: ${stack.pop()}")
    println("Print stack: ${stack.printStack()}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Stack size: ${stack.size}")
    println("Stack isEmpty: ${stack.isEmpty}")
    println("Top on empty stack: ${stack.peek()}")
    println("Pop on empty stack: ${stack.pop()}")
    println("Push 10: ${stack.push(10)}")
    println("Push 20: ${stack.push(20)}")
    println("Push 30: ${stack.push(30)}")
    println("Print stack: ${stack.printStack()}")
    println("Push 40: StackOverflowError: ${stack.push(40)}")
}