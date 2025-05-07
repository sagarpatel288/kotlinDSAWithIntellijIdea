package coursera.ucSanDiego.algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Print n % m where n is the nth fibonacci number and m is the given modulo.
 *
 * The fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 *
 * So, if n = 4 and m = 2,
 * then print nth fibonacci number % m
 * = 4th fibonacci number % 2
 * = 3 % 2
 * = 1.
 * Hence, the expected answer of the given example is 1.
 */
fun main() {

    /**
     * If the input [nthFibonacci] is too large, it can overflow!
     */
    fun printNthFibonacciModulo(nthFibonacci: Int, modulo: Int): Long {
        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1) return nthFibonacci.toLong()
        var previous = 0L
        var current = 1L
        var result = 0L
        val fibonacciNumbers = mutableListOf(previous, current)
        for (i in 2..nthFibonacci) {
            result = previous + current
            previous = current
            current = result
        }
        println("List of fibonacci numbers: $fibonacciNumbers")
        return result % modulo
    }

    // Let us take the input from the console
    val scanner = Scanner(System.`in`)
    // We expect an integer input
    val nthFibonacci = scanner.nextInt()
    val modulo = scanner.nextInt()

    // If the input is too large, it can overflow!
    println("nth Fibonacci % Modulo: ${printNthFibonacciModulo(nthFibonacci, modulo)}")
}