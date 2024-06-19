package level20Week2AlgorithmicWarmUp

fun main() {

    var iteration = 0L

    /**
     * There are a total of [numberOfRebels] rebels.
     * And after every [killingFactorInterval] interval, the rebel on that position dies.
     * We want to find the original position of the survivor - the only rebel that survives and everyone else dies.
     * We get initial [numberOfRebels] and we eliminate one rebel according to the [killingFactorInterval].
     * We repeat this process until there is only one rebel left.
     * For example, let us assume that there are a total of 5 rebels and the killing factor interval is 3.
     * The position always starts with 0.
     * So, we have A at 0, B at 1, C at 2, D at 3, and E at 4 rebels.
     * According to the given killing factor interval, the rebel C on the position 2 dies.
     * Then, we again count from D. The rebel D becomes a new 0th position.
     *
     * | Before             	| A 	| B 	| C 	| D 	| E 	|   	|   	|   	|   	|   	|
     * |--------------------	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|
     * | Original Positions 	| 0 	| 1 	| 2 	| 3 	| 4 	|   	|   	|   	|   	|   	|
     * | Kill-1               	|   	|   	| x 	|   	|   	|   	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	| D 	| E 	| A 	| B 	|   	|   	|   	|
     * | New Positions-1      	|   	|   	|   	| 0 	| 1 	| 2 	| 3 	|   	|   	|   	|
     *
     * So, it becomes: D, E, A, B. The counting starts from D. So, D becomes 0.
     * Notice that the original position 3(track D) became 0, 4(track E) became 1, 0(track A) became 2,
     * and 1(track B) became 3.
     * Now, The rebel A dies.
     *
     * | Before             	| A 	| B 	| C 	| D 	| E 	|   	|   	|   	|   	|   	|
     * |--------------------	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|
     * | Original Positions 	| 0 	| 1 	| 2 	| 3 	| 4 	|   	|   	|   	|   	|   	|
     * | Kill-1             	|   	|   	| x 	|   	|   	|   	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	| D 	| E 	| A 	| B 	|   	|   	|   	|
     * | New Positions-1    	|   	|   	|   	| 0 	| 1 	| 2 	| 3 	|   	|   	|   	|
     * | Kill-2             	|   	|   	|   	|   	|   	| x 	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	| B 	| D 	| E 	|   	|
     * | New Positions-2    	|   	|   	|   	|   	|   	|   	| 0 	| 1 	| 2 	|   	|
     *
     * So, it becomes B, D, E. The counting starts from B. So, B becomes 0, D becomes 1, and E becomes 2.
     * E dies.
     *
     * | Before             	| A 	| B 	| C 	| D 	| E 	|   	|   	|   	|   	|   	|
     * |--------------------	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|
     * | Original Positions 	| 0 	| 1 	| 2 	| 3 	| 4 	|   	|   	|   	|   	|   	|
     * | Kill-1             	|   	|   	| x 	|   	|   	|   	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	| D 	| E 	| A 	| B 	|   	|   	|   	|
     * | New Positions-1    	|   	|   	|   	| 0 	| 1 	| 2 	| 3 	|   	|   	|   	|
     * | Kill-2             	|   	|   	|   	|   	|   	| x 	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	| B 	| D 	| E 	|   	|
     * | New Positions-2    	|   	|   	|   	|   	|   	|   	| 0 	| 1 	| 2 	|   	|
     * | Kill-3             	|   	|   	|   	|   	|   	|   	|   	|   	| x 	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	| B 	| D 	|   	|   	|
     * | New Positions-3    	|   	|   	|   	|   	|   	|   	| 0 	| 1 	|   	|   	|
     *
     * It becomes B and D. The counting starts from B. So, B becomes 0, and D becomes 1.
     * The rebel B dies.
     *
     * | Before             	| A 	| B 	| C 	| D 	| E 	|   	|   	|   	|   	|   	|
     * |--------------------	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|
     * | Original Positions 	| 0 	| 1 	| 2 	| 3 	| 4 	|   	|   	|   	|   	|   	|
     * | Kill-1             	|   	|   	| x 	|   	|   	|   	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	| D 	| E 	| A 	| B 	|   	|   	|   	|
     * | New Positions-1    	|   	|   	|   	| 0 	| 1 	| 2 	| 3 	|   	|   	|   	|
     * | Kill-2             	|   	|   	|   	|   	|   	| x 	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	| B 	| D 	| E 	|   	|
     * | New Positions-2    	|   	|   	|   	|   	|   	|   	| 0 	| 1 	| 2 	|   	|
     * | Kill-3             	|   	|   	|   	|   	|   	|   	|   	|   	| x 	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	| B 	| D 	|   	|   	|
     * | New Positions-3    	|   	|   	|   	|   	|   	|   	| 0 	| 1 	|   	|   	|
     * | Kill-4             	|   	|   	|   	|   	|   	|   	| x 	|   	|   	|   	|
     * | After              	|   	|   	|   	|   	|   	|   	|   	| D 	|   	|   	|
     * | New Positions-4    	|   	|   	|   	|   	|   	|   	|   	| 0 	|   	|   	|
     *
     * The rebel D survives. When the rebel D survives, its new position is obviously 0 as there is only one rebel.
     * And for the rebel D, the original position (that we need to return as an answer) was 3.
     * Now, if we map the new position with the original position, we can say that for N = 5 and K = 3,
     * the original position 3 survives.
     * And if we look at the process, we keep reducing (eliminating) one rebel (position) until only one rebel remains.
     * So, to solve a large N, we reduce it to 1.
     * Also, if we look at the relationship between before and after eliminating a rebel, we always get the
     * original position if we add K to the new position and then perform the modular n on the result.
     * For example, if we want to find the original position of each rebel after we eliminate the first rebel,
     *
     * | Before             	| A 	| B 	| C 	| D 	| E 	|   	|   	|   	|   	|   	|
     * |--------------------	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|
     * | Original Positions 	| 0 	| 1 	| 2 	| 3 	| 4 	|   	|   	|   	|   	|   	|
     * | Kill-1             	|   	|   	| x 	|   	|   	|   	|   	|   	|   	|   	|
     * | After              	|   	|   	|   	| D 	| E 	| A 	| B 	|   	|   	|   	|
     * | New Positions-1    	|   	|   	|   	| 0 	| 1 	| 2 	| 3 	|   	|   	|   	|
     *
     * We can see that,
     * New position 0 + add k( = 3) = 3 % n = 3 % 5 = 3 (the original position).
     * Similarly,
     * New position 1 + 3 = 4 % n = 4 % 5 = 4 (the original position).
     * New position 2 + 3 = 5 % n = 5 % 5 = 0 (the original position).
     * New position 3 + 3 = 6 % n = 6 % 5 = 1 (the original position).
     * So, the original position for any given f(n, k) becomes (f(n-1, k) + k) % n after one rebel dies.
     * So, we can come up with a formula as:
     * f(n, k) = (f(n-1, k) + k ) % n
     * How? f(n, k) becomes f(n-1, k) after one rebel dies.
     * If we add k to the new position and then perform % n to the result, we get the original position.
     *
     */
    fun getSurvivorRecursively(numberOfRebels: Long, killingFactorInterval: Long): Long {
        iteration++
        if (numberOfRebels == 1L) return 0L
        return (getSurvivorRecursively(numberOfRebels -1L, killingFactorInterval) + killingFactorInterval) % numberOfRebels
    }

    /**
     * There are a total of [numberOfRebels] rebels.
     * And after every [killingFactorInterval] interval, the rebel on that position dies.
     * We want to find the original position of the survivor - the only rebel that survives and everyone else dies.
     * We get initial [numberOfRebels] and we eliminate one rebel according to the [killingFactorInterval].
     * We repeat this process until there is only one rebel left.
     * We have seen one perspective to look at it in the above [getSurvivorRecursively] function.
     * There is one more perspective to look at it, and it is more efficient than the recursive approach.
     * Recursive approach can introduce stack overflow when [numberOfRebels] is too large,
     * because each function call creates an object.
     * The iterative approach is comparatively safe.
     * The iterative approach is based on the pre-computed fact that:
     * `newSurvivorPosition = (previousSurvivorPosition + killingFactorInterval) % i`
     * where i = `for (i in 1..numberOfRebels)`.
     *
     * For example:
     * When n = 1, the survivor position is 0.
     * When n = 2, the survivor position (LHS) is 1 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 2 = 3 % 2 = 1.
     * When n = 3, the survivor position (LHS) is 1 = RHS => (previousSurvivorPosition + k) % i = (1 + 3) % 3 = 4 % 3 = 1.
     * When n = 4, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (1 + 3) % 4 = 4 % 4 = 0.
     * When n = 5, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 5 = 3 % 5 = 3.
     * When n = 6, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (3 + 3) % 6 = 6 % 6 = 0.
     * When n = 7, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 7 = 3 % 7 = 3.
     * When n = 8, the survivor position (LHS) is 6 = RHS => (previousSurvivorPosition + k) % i = (3 + 3) % 8 = 6 % 8 = 6
     * When n = 9, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (6 + 3) % 9 = 9 % 9 = 0.
     * When n = 10, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 10 = 3 % 10 = 3.
     *
     * We can see that LHS = RHS. The formula is correct.
     */
    fun getSurvivorIteratively(numberOfRebels: Long, killingFactorInterval: Long): Long {
        // If there is only one rebel, the survivor position is 0.
        var survivorPosition = 0L
        // We already know the case when there is only one rebel.
        // Hence, we start with 2 and go up to the given number of rebels.
        for (i in 2..numberOfRebels) {
            survivorPosition = (survivorPosition + killingFactorInterval) % i
        }
        return survivorPosition
    }

    fun start() {
        try {
            println("Press 1 to solve with the iterative solution, 2 for the recursive solution, 3 for both, or any other key to exit.")
            val input = readln().toInt()
            val isIterative = input == 1
            val isRecursive = input == 2
            val isIterativeAndRecursiveComparison = input == 3

            println("Please enter the number of rebels")
            val numberOfRebels = readln().toLong()

            println("Please enter the killing interval factor")
            val killingFactorInterval = readln().toLong()

            if (isIterative) {
                println(getSurvivorIteratively(numberOfRebels, killingFactorInterval))
            } else if (isRecursive) {
                println(getSurvivorRecursively(numberOfRebels, killingFactorInterval))
            } else if (isIterativeAndRecursiveComparison) {
                println(getSurvivorIteratively(numberOfRebels, killingFactorInterval))
                println(getSurvivorRecursively(numberOfRebels, killingFactorInterval))
            }
            start()
        } catch (e: Exception) {
            println(": :Terminating with exception: $e")
        }
    }

    start()
}