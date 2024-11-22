package coursera.ucSanDiego.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Write a program to print the nth Fibonacci number, where n is the input.
 *
 * The fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 *
 * So, if n = 3, then print the 3rd fibonacci number, which is 2.
 */
fun main() {

    /**
     * The time-complexity is O(n) because we iterate through the input once.
     * The space-complexity is O(1) because the variables we declare, define, and use do not depend on the input size.
     */
    fun printNthFibonacci(nthFibonacci: Int): Long {
        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1) return nthFibonacci.toLong()
        // The variables we use do not depend on the input size.
        // Hence, the space-complexity is O(1).
        var a = 0L
        var b = 1L
        var result = 0L
        println(": :printNthFibonacci: initially: a = $a, b = $b, and result = $result")
        // We iterate through the input once. So, it takes O(n) time-complexity.
        // F(0) and F(1) are known values. We use it to build F(2) and other subsequent F(n) up to the target F(n).
        // So basically, we know the base-case, and we start with the base-case and gradually build the target.
        // That is to say, we start from the bottom, and go upside.
        // Hence, this approach is known as bottom-up approach, which is a type of the dynamic programming.
        for (i in 2..nthFibonacci) {
            // The operation a + b and the subsequent assignment operations are not dependent on the input size.
            // Hence, it takes O(1) time complexity.
            result = a + b
            a = b
            b = result
            println(": :printNthFibonacci: result = a + b = $result. New a = $a and b = $b")
        }
        return result
    }

    // Let us take the input from the console
    val scanner = Scanner(System.`in`)
    // We expect an integer input
    val nthFibonacci = scanner.nextInt()
    println(printNthFibonacci(nthFibonacci))
}