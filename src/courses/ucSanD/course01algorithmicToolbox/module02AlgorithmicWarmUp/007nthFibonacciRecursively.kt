package courses.ucSanD.course01algorithmicToolbox.module02AlgorithmicWarmUp

fun main() {

    /**
     * Write a program to print the nth Fibonacci number, where n is the input.
     * Use recursion.
     *
     * The fibonacci series example:
     * 0, 1, 1, 2, 3, 5, 8, 13...
     *
     * So, if n = 3, then print the 3rd fibonacci number, which is 2.
     *
     * ----------------------- Explanation -----------------------
     *
     * The first two fibonacci numbers (actually, their values) are known. F(0) = 0 and F(1) = 1.
     * After that, F(n) = F(n-1) + F(n-2).
     * For example,
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
     *
     *
     * Notice that there are some repetitions.
     * For example, while trying to find F(5), we calculated F(3) twice, and F(2) thrice.
     * This repetition causes the time complexity of this recursive solution to be O(2^n) which is exponential.
     * The function starts from F(n) and goes down to F(0) to solve the problem using recursion.
     * Hence, this recursion stack causes the space-complexity to be O(n).
     */
    fun nthFibonacciRecursively(nthFibonacci: Int): Int {
        if (nthFibonacci <= 1) return nthFibonacci
        return (nthFibonacciRecursively(nthFibonacci - 1) + nthFibonacciRecursively(nthFibonacci - 2))
    }

    val nthFibonacci = readln().toInt()
    println(nthFibonacciRecursively(nthFibonacci))
}