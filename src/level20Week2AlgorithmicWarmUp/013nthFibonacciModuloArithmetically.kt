package level20Week2AlgorithmicWarmUp

import java.util.Scanner

/**
 * The fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 */
fun main() {

    fun printNthFibonacciModulo(nthFibonacci: Int, modulo: Int): Long {
        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1) return nthFibonacci.toLong()
        var previous = 0L
        var current = 1L
        var result = 0L
        val history = mutableListOf(previous, current)
        for (i in 2..nthFibonacci) {
            result = (previous + current)
            history.add(result)
            previous = current
            current = result
        }
        println("naive $history")
        return result % modulo
    }

    fun printNthFibonacciModuloArithmetically(nthFibonacci: Int, modulo: Int): Long {
        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1) return nthFibonacci.toLong()
        var previous = 0L
        var current = 1L
        var result = 0L
        val history = mutableListOf(previous, current)
        for (i in 2..nthFibonacci) {
            result = (previous + current) % modulo
            history.add(result)
            previous = current
            current = result
        }
        println("modular addition: $history")
        return result
    }

    // Let us take the input from the console
    val scanner = Scanner(System.`in`)
    // We expect an integer input
    val nthFibonacci = scanner.nextInt()
    val modulo = scanner.nextInt()
    println("naive: ${printNthFibonacciModulo(nthFibonacci, modulo)}")
    println("modular addition: ${printNthFibonacciModuloArithmetically(nthFibonacci, modulo)}")
}