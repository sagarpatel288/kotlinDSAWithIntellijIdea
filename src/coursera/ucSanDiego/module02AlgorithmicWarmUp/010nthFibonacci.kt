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

    fun printNthFibonacci(nthFibonacci: Int): Long {
        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1) return nthFibonacci.toLong()
        var a = 0L
        var b = 1L
        var result = 0L
        for (i in 2..nthFibonacci) {
            result = a + b
            a = b
            b = result
        }
        return result
    }

    // Let us take the input from the console
    val scanner = Scanner(System.`in`)
    // We expect an integer input
    val nthFibonacci = scanner.nextInt()
    println(printNthFibonacci(nthFibonacci))
}