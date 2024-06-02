package level20Week2AlgorithmicWarmUp

import java.util.Scanner

/**
 * The fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 */
fun main() {

    fun printNthFibonacci(nthFibonacci: Int): Long {
        if (nthFibonacci == 0) return 0L
        if (nthFibonacci == 1) return 1L
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