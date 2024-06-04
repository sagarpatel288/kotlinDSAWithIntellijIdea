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
            // We want to find the remainder of nth fibonacci number for a given modulo.
            // The next fibonacci number is an addition of the last two fibonacci numbers.
            // Naive fibonacci:
            // Let us take the known fibonacci numbers for a while and understand how it works.
            // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
            // Let us assume our modulo is 7. 21 + 34 = 55 and 55 mod 7 = 6. So, the answer is 6. <-----------------
            // Modular arithmetic theory: Modular Addition
            // Imagine we have a clock with m hours. The current time is 1.
            // If we add hours and exceed m, we wrap around the clock.
            // E.g. If m is 12, adding 14 hours would wrap around 2 hours (14 % 12 = 2)
            // and 2 is within the range of 0 to m-1 = 0 to 12-1 = 0 to 11.
            // And if we look at the answer, whether we add 14 hours or 2 hours, the answer remains the same: 3
            // The mathematical equation for the modular addition:
            // (A + B) mod C = (A mod C + B mod C) mod C
            // Modular Addition: When we use the modular addition:
            // (21 mod 7 + 34 mod 7) mod 7 = (0 + 6) mod 7 = 6. So, the answer is 6. <------------------------------
            // Let us assume that the reducedN (nth fibonacci) is f(21) and the modulo is 17.
            // Naive algorithm: result = previous + current, previous = current, current = result
            // In the end, result % modulo
            // A collection of the Naive fibonacci numbers
            // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946]
            // F(21) = 10946 % 17 = 15 <----------------------------------------------------------------------------
            // Modular arithmetic: result = (previous + current) % modulo, previous = current, current = result
            // In the end, result is our answer.
            // A collection of the Modular Addition for each fibonacci number
            // [0, 1, 1, 2, 3, 5, 8, 13, 4, 0, 4, 4, 8, 12, 3, 15, 1, 16, 0, 16, 16, 15]
            // F(21) = 15 <-----------------------------------------------------------------------------------------
            // To learn more about Modular Arithmetic:
            // https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction
            // So, if we are doing modulo on a sum of two large numbers,
            // it is better to first derive modulo of each large number, sum up the result of each,
            // and then perform the modulo on it. It will give the same answer, but will save us from the too large computation.
            // The purpose of doing % modulo is to keep the result within the modulo range.
            // The modulo operation ensures that the result stays within the range of 0 to m-1, where m is modulo.
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