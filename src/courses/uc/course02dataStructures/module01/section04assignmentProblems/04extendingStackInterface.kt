package courses.uc.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem Description:
 *
 * ## Problem Introduction:
 *
 * * Stack is an abstract data type supporting the operations `Push()` and `Pop()`.
 * * It is not difficult to implement it in a way that both these operations work in constant time.
 * * In this problem, your goal will be to implement a stack that also supports finding the maximum value and to
 * ensure that all operations still work in constant time.
 *
 * ## Problem Description:
 *
 * * **Task.**
 *
 * * Implement a stack supporting the operations Push(), Pop(), and Max().
 *
 * * **Input Format:**
 *
 * * The first line of the input contains the number `𝑞` of queries.
 * * Each of the following `𝑞` lines specifies a query of one of the following formats: `push v`, `pop`, or `max`.
 *
 * * **Constraints:**
 * ```
 * 1 ≤ 𝑞 ≤ 400 000;
 * 0 ≤ 𝑣 ≤ 10^5;
 * ```
 *
 * * **Output Format:**
 *
 * * For each `max` query, output (on a separate line) the maximum value of the stack.
 *
 * * **Sample 1:**
 *
 * * _Input:_
 * ```
 * 5
 * push 2
 * push 1
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 2
 * 2
 * ```
 *
 * * _Explanation:_
 *
 * * After the first two push queries, the stack contains elements 1 and 2.
 * * After the pop query, the element 1 is removed.
 *
 * * **Sample 2:**
 *
 * * _Input:_
 * ```
 * 5
 * push 1
 * push 2
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 2
 * 1
 * ```
 *
 * * **Sample 3:**
 *
 * * _Input:_
 * ```
 * 10
 * push 2
 * push 3
 * push 9
 * push 7
 * push 2
 * max
 * max
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 9
 * 9
 * 9
 * 9
 * ```
 *
 * * **Sample 4:**
 *
 * * _Input:_
 * ```
 * 3
 * push 1
 * push 7
 * pop
 * ```
 *
 * * _Output:_
 * ```
 * ```
 *
 * * _Explanation:_
 *
 * * The output is empty since there are no max queries.
 *
 * * **Sample 5:**
 *
 * * _Input:_
 * ```
 * 6
 * push 7
 * push 1
 * push 7
 * max
 * pop
 * max
 * ```
 * * _Output:_
 * ```
 * 7
 * 7
 * ```
 *
 * ## Thought Process And Code Translation
 *
 * ### Prerequisites
 *
 * [MinStack](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/64c6c2f62fbf53d9a5ab34d7e2a4af7b50062530/src/coursera/ucSanDiego/course02dataStructures/module01/section02stacksAndQueues/video01Stack/03MinStackUsingArray.kt)
 *
 * * Similar to the `minStack` problem, we can use the `encoding` technique to find `maxStack` in `O(1)` time.
 *
 * ### Just a small recapitulation:
 *
 * * Suppose, we use a global variable, and update it every time we get a `new max` value.
 * ```
 * push 1 -> latestMax = 1
 * push 2 -> latestMax = 2
 * push 3 -> latestMax = 3
 * ```
 * * Now, each new input value is a `new max` value.
 * * When we pop the `latest max` value from the stack, how do we get the `previous max` value?
 * ```
 * pop 3
 * -> How do we get the previous max that we had before `3`, because `3` is no more in the stack.
 * -> It can't be the `latestMax`.
 * -> Then what will be the `latestMax`? How do we find it?
 * ```
 * * If we iterate and go through each element, it will `O(n)` time.
 * * So, we have two known options:
 * ```
 * 1. Maintain a separate stack. Maybe, we can call it, `maxStack`.
 * ```
 * * Yes, we can get the `maxStack` value in `O(1)` by simply calling `topMax`.
 * * But if every new value is a `new max` value, the `maxStack` will be of size `O(n)`.
 * ```
 * 2. Using the clever `encoding` technique.
 * ```
 *
 * ### What is the `clever encoding technique`?
 *
 * * We use a single global variable, called `latestMax`.
 * * Every time we get a new max value, we store the `encoded` value into the stack in such a way that it includes
 * two values. 1. The original incoming value. 2. The previous (old) max value.
 * * Now, whenever we pop a value, and if it is an encoded value, we need to decode it to restore the old max value.
 *
 * ### How do we `encode`? How do we know if the `popped` value is an `encoded` value? How do we restore the `old max`?
 *
 * * The encoding formula is: `encoded = 2 * new - old`.
 * ```
 * // Note that the condition is `>` only, and not `>=`. Can you explain why?
 * // The purpose of encoding is to remember the current max (becoming the old max) when we change its value.
 * // Well, if we use `>=`, then:
 * // It means that we would replace the `latestMax` value with the same value
 * // + we get unnecessary overhead of `encoding-decoding`.
 * // So, we change the `latestMax` and `encode` the value,
 * // only if the incoming value is greater than the current max, and not when it is equal to the current max.
 * if (incomingValue > latestMax) {
 *     // The incomingValue is greater than the current latestMax.
 *     // Hence, the incomingValue is going to be the new latestMax.
 *     // So, the incomingValue is the `new` value.
 *     // And the existing `latestMax` becomes the `old` value now.
 *     val encoded = 2 * incomingValue - latestMax
 *     // The original incoming value becomes the new latestMax.
 *     // The latestMax is the original incomingValue.
 *     latestMax = incomingValue
 *     // We store the encoded version of the original value (latestMax) to the stack.
 *     stack.push(encoded)
 * }
 * ```
 * * The encoding formula guarantees that the encoded value will always be greater than the new latest max value.
 * * So, if we pop a value, and it is greater than the current latest max value, the popped value is an encoded value.
 * ```
 * val popped = stack.pop()
 * if (popped > latestMax) {
 *     // If the popped value is greater than the current latestMax, it is an encoded value.
 *     // So, what will be the original value?
 *     // Well, the current latestMax should be the original value that has been stored as an encoded value into the stack.
 *     val original = latestMax
 *     val oldMax = 2 * latestMax - popped
 *     latestMax = oldMax
 *     return original
 * }
 * ```
 * * So, the decoding formula to restore the oldMax value is:
 * ```
 * val oldMax = 2 * new - encoded
 * ```
 *
 * ## Time Complexity
 *
 * * The time complexity of getting the `maxStack` value is `O(1)`.
 * * We simply read and return the current `latestMax`.
 *
 * ## Space Complexity
 *
 * * We use only a single variable to hold the `latestMax` value.
 * * If we don't count the input size, then space complexity is `O(1)` for the `maxStack` functionality.
 *
 * ## Grader Output
 *
 * ```
 * Good job! (Max time used: 0.63/2.00, max memory used: 100147200/536870912.)
 * ```
 *
 */
fun maxStack(queryList: List<String>): String {
    val stack = ArrayDeque<Long>()
    val stringBuilder = StringBuilder()
    var latestMax = Long.MIN_VALUE
    queryList.forEach {
        val query = it.split(" ")
        when (query[0].lowercase()) {
            "push" -> {
                val incoming = query[1].toLong()
                if (stack.isEmpty()) {
                    latestMax = incoming
                    stack.addLast(incoming)
                } else if (incoming > latestMax) {
                    val encoded = 2 * incoming - latestMax
                    stack.addLast(encoded)
                    latestMax = incoming
                } else {
                    stack.addLast(incoming)
                }
            }
            "pop" -> {
                val popped = stack.removeLast() // Empty case is handled by the `ArrayDeque` itself (throws exception).
                if (popped > latestMax) {
                    val oldMax = 2 * latestMax - popped
                    latestMax = oldMax
                }
            }
            "max" -> {
                if (stack.isEmpty()) {
                    stringBuilder.append("\n")
                } else {
                    stringBuilder.append("$latestMax\n")
                }
            }
        }
    }
    return stringBuilder.toString()
}

fun main() {
    val totalQueries = readln().toInt()
    val queryList = mutableListOf<String>()
    repeat(totalQueries) {
        queryList.add(readln())
    }
    println(maxStack(queryList))
}