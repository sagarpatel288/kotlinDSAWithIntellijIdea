package coursera.ucSanDiego.algorithmicToolbox.module05DynamicProgramming

/**
 * Earlier, We have already seen a few fibonacci examples:
 *
 * Print a fibonacci series up to the given number:
 *
 * src/coursera/ucSanDiego/module02AlgorithmicWarmUp/005naivePrintFibonacciUpTo.kt
 * [005printFibonacciUsingNaiveSolution](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c945e89b5becd905bf3d4b4000358992fe654ee/src/coursera/ucSanDiego/module02AlgorithmicWarmUp/005naivePrintFibonacciUpTo.kt)
 *
 * Print the value of nth Fibonacci number:
 *
 * src/coursera/ucSanDiego/module02AlgorithmicWarmUp/007nthFibonacciRecursively.kt
 * [007nthFibonacciUsingRecursion](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c945e89b5becd905bf3d4b4000358992fe654ee/src/coursera/ucSanDiego/module02AlgorithmicWarmUp/007nthFibonacciRecursively.kt)
 *
 * A quick re-cap:
 *
 * The recursive solution looks like:
 *
 *                                                 F(5)
 *                                                  |
 *                 -----------------------------------------------------------------
 *                 |                                                                 |
 *               F(4)                                                             F(3)
 *               |                                                                 |
 *       -------------------------                                   -------------------------
 *       |                       |                                   |                       |
 *     F(3)                    F(2)                               F(2)                    F(1)
 *     |                       |                                   |
 *     ------------         ------------                         ------------
 *     |          |         |          |                         |          |
 *     F(2)       F(1)      F(1)       F(0)                     F(1)       F(0)
 *     |
 *     ------------
 *     |          |
 *     F(1)      F(0)
 *
 *
 * To avoid (remove, eliminate) the duplicate calculation, we came up with the following solution:
 *
 * src/coursera/ucSanDiego/module02AlgorithmicWarmUp/010nthFibonacci.kt
 * [010nthFibonacciUsingBottomUp](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c945e89b5becd905bf3d4b4000358992fe654ee/src/coursera/ucSanDiego/module02AlgorithmicWarmUp/010nthFibonacci.kt)
 *
 * For more information on the solution mentioned above, please visit the solution file.
 *
 * The above examples represent the recursion, the recursive solution, and the bottom-up approach (a.k.a tabulation)
 * of the dynamic programming.
 *
 * In this example, we will see the top-down approach (a.k.a. memoization or caching) of dynamic programming.
 * Note that a few people do not consider memoization (caching) as a form of dynamic programming.
 * A few people consider only the bottom-up (tabulation) approach as dynamic programming.
 *
 * The idea is to use a container to store intermediate calculations and avoid repetitive computation.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * ## ----------------------- Time Complexity -----------------------
 *
 * Due to memoization, each nth Fibonacci number runs only once.
 * We cover each nth Fibonacci number down to 0.
 * Hence, if `n` is the `nth` Fibonacci number, then the time complexity is O(n).
 *
 * ## ----------------------- Space Complexity -----------------------
 *
 * The maximum memory that we use here is the container of size `n + 1` for the memoization (caching).
 * The recursion stack depth is also O(n). (From `n` to 0).
 * Hence, the space complexity is O(n).
 *
 */
fun main() {

    fun nthFibonacciTopDown(nthFibonacci: Int, memoization: IntArray): Int {
        // The very first thing we do is check the cache. Do we have the answer in the cache?
        // If yes, then we don't need to recalculate anything.
        // If not, we calculate the answer, save it to the cache, and then return the answer from the cache itself.
        // This approach helps us avoid recalculating the same problem.
        // If we have calculated a particular problem, it must be in the cache.
        if (memoization[nthFibonacci] != -1) return memoization[nthFibonacci]
        // This is our base-case.
        if (nthFibonacci <= 1) return nthFibonacci
        // If we didn't have the answer in the cache, we calculate the answer and save it in the cache before
        // returning the answer.
        memoization[nthFibonacci] =
            nthFibonacciTopDown(nthFibonacci - 1, memoization) +
                    nthFibonacciTopDown(nthFibonacci - 2, memoization)
        // Once we calculate and save the answer to the cache, we return the answer from the cache itself only.
        return memoization[nthFibonacci]
    }

    val nthFibonacci = readln().toInt()
    // We declare, define, initialize a container of size n + 1.
    // Why n + 1?
    // For example, if we want to calculate F(1), we save the answers of F(0) and F(1).
    val memoization = IntArray(nthFibonacci + 1) {-1}
    println(nthFibonacciTopDown(nthFibonacci, memoization))
}