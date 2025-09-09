package courses.ucSanD.course01algorithmicToolbox.module02AlgorithmicWarmUp

import kotlin.system.exitProcess

/**
 * Problem Statement:
 *
 * Find the position of the survivor in the vicious series of killings described by Flavius Josephus,
 * the first-century historian and head of Jewish forces in Galilee.
 *
 * Input:
 *
 * Natural numbers n and k.
 * They represent n rebels standing in a circle.
 * Rebels are being eliminated in such a way that every k-th still alive rebel around the circle is killed
 * until only one rebel left. Rebels are killed clockwise starting from rebel 0, i.e., rebel k − 1 is killed first.
 *
 * Output:
 *
 * The position of the survivor, denoted Josephus(n,k).
 *
 * ----------------------- Explanation -----------------------
 *
 * Let us understand the problem first.
 * 1. Input N represents the number of rebels.
 * 2. The variable K represents interval.
 * 3. All the rebels are killed until only 1 rebel is left alive.
 * 4. We need to find the position of this rebel, who survives.
 * 5. The killing starts from the index 0, and we count up to the interval k to kill a rebel.
 * 6. So, for example, if we have n = 7 rebels, the array looks like below:
 * At index 0, rebel r0. At index 1, rebel r1. At index 2, rebel r2.... up to At index 6, rebel r6.
 * Rebels = [0, 1, 2, 3, 4, 5, 6] => [r0, r1, r2, r3, r4, r5, r6].
 * 7. Now, if the interval k = 3, and the killing starts from the index 0, then:
 * count 1 = index 0, count 2 = index 1, cunt 3 = index 2.
 * 8. So, the rebel at index 2, that is k - 1 = 3 - 1 = 2, is killed first.
 * 9. Hence, the new array becomes:
 * Rebels = [0, 1, 2, 3, 4, 5] => [r0, r1, r3, r4, r5, r6] because the rebel r2 has been killed.
 * 10. Then, we again count the interval k = 3.
 * 11. From where? From rebel r3 itself.
 * Why? Because we counted from r0 itself earlier. We count from where we start.
 * Earlier, we started from the index 0, rebel r0. So, we counted count 1 = index 0 = r0.
 * Similarly, this time we start from rebel r3. So, we count from r3.
 * 12. And if we start counting from r3,
 * count 1 = r3, count 2 = r4, count 3 = r5.
 * 13. Hence, the rebel r5 will be killed.
 * 14. So, the array becomes: [0, 1, 2, 3, 4] => [r0, r1, r3, r4, r6] because the rebel r5 has been killed.
 * 15. Next, we start from r6, and this game of death continues until only 1 rebel is left.
 * 16. We need to find the original position of this rebel who survives.
 * For example, it will not be r2 = index 2 or r5 = index 5, because they have been killed.
 *
 */
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
     * # How to remember? (Refer the above tables side-by-side to understand).
     *
     * 1. We have n rebels. When one rebel dies, we are left with n - 1.
     * 2. The next person after the rebel who has died recently, will be the survivor. Right?
     * 3. The next counting starts from the survivor.
     * 4. Hence, position shifts from 0 to + k when it is n - 1.
     * 5. So, it becomes: f(n, k) = f(n - 1, k) + k
     * 6. However, we want to make sure that + k does not exceed the total.
     * 7. Hence, it becomes: f(n, k) = ( f(n - 1, k) + k ) % n
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
     *
     * Recursive approach can introduce stack overflow when [numberOfRebels] is too large,
     * because each function call creates an object and until the function call is finished, the function occupies
     * (reserves) stack memory.
     *
     * The iterative approach is comparatively safe. It does not have any recursive call.
     * It uses constant memory inside the loop.
     * The iterative approach is based on the pre-computed fact that:
     * `newSurvivorPosition = (previousSurvivorPosition + killingFactorInterval) % i`
     * where i = `for (i in 2..numberOfRebels)`.
     *
     * For example:
     *
     * | Iteration (i) 	| i <br>= Number of rebels 	| Previous Survivor<br>Position 	| newSurvivorPos <br>= (prevSurvivorPos + k) % i 	| New Survivor<br>Position 	|
     * |---------------	|--------------------------	|-------------------------------	|------------------------------------------------	|--------------------------	|
     * | 1             	| 1                        	| 0                             	| (0 + 3) % 1 = 0                                	| 0                        	|
     * | 2             	| 2                        	| 0                             	| (0 + 3) % 2 = 1                                	| 1                        	|
     * | 3             	| 3                        	| 1                             	| (1 + 3) % 3 = 1                                	| 1                        	|
     * | 4             	| 4                        	| 1                             	| (1 + 3) % 4 = 0                                	| 0                        	|
     * | 5             	| 5                        	| 0                             	| (0 + 3) % 5 = 3                                	| 3                        	|
     * | 6             	| 6                        	| 3                             	| (3 + 3) % 6 = 0                                	| 0                        	|
     * | 7             	| 7                        	| 0                             	| (0 + 3) % 7 = 3                                	| 3                        	|
     * | 8             	| 8                        	| 3                             	| (3 + 3) % 8 = 6                                	| 6                        	|
     * | 9             	| 9                        	| 6                             	| (6 + 3) % 9 = 0                                	| 0                        	|
     * | 10            	| 10                       	| 0                             	| (0 + 3) % 10 = 3                               	| 3                        	|
     *
     * When n = 1, the survivor position is 0.
     *
     * When n = 2, the survivor position (LHS) is 1 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 2 = 3 % 2 = 1.
     *
     * When n = 3, the survivor position (LHS) is 1 = RHS => (previousSurvivorPosition + k) % i = (1 + 3) % 3 = 4 % 3 = 1.
     *
     * When n = 4, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (1 + 3) % 4 = 4 % 4 = 0.
     *
     * When n = 5, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 5 = 3 % 5 = 3.
     *
     * When n = 6, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (3 + 3) % 6 = 6 % 6 = 0.
     *
     * When n = 7, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 7 = 3 % 7 = 3.
     *
     * When n = 8, the survivor position (LHS) is 6 = RHS => (previousSurvivorPosition + k) % i = (3 + 3) % 8 = 6 % 8 = 6
     *
     * When n = 9, the survivor position (LHS) is 0 = RHS => (previousSurvivorPosition + k) % i = (6 + 3) % 9 = 9 % 9 = 0.
     *
     * When n = 10, the survivor position (LHS) is 3 = RHS => (previousSurvivorPosition + k) % i = (0 + 3) % 10 = 3 % 10 = 3.
     *
     * We can see that LHS = RHS. The formula is correct.
     *
     * # How to remember? (Please refer to the above examples and tables side-by-side as a reference).
     *
     * 1. For the recursion approach, we started from `n` and we kept decreasing `n -1` until we found the answer from
     * the base case. That was the top-to-bottom approach.
     * 2. Here, for the iteration approach, we start from 1.
     * 3. If there is only 1 person, the person survives and the survivor position will be 0.
     * 4. If we add 1 more person, the survivor position shifts by the interval factor.
     * 5. So, it becomes: survivor = survivor + intervalFactor
     * 6. However, we want to make sure that `+ intervalFactor` does not exceed the available people.
     * 7. Hence, it becomes: survivor = ( survivor + intervalFactor ) % i, where `i` is keep changing from 2 to n.
     */
    fun getSurvivorIteratively(numberOfRebels: Long, killingFactorInterval: Long): Long {
        // If there is only one rebel, the survivor position is 0.
        var survivorPosition = 0L
        // We already know the case when there is only one rebel.
        // Hence, we start with 2 and go up to the given number of rebels.
        for (i in 2..numberOfRebels) {
            // This is the key-lemma (key-point) to solve the josephus problem.
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

            if (input !in intArrayOf(1,2,3)) {
                exitProcess(200)
            }

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