package coursera.ucSanDiego.course01algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 */
fun main() {

    /**
     * We want to print the `f(n) % [modulo]` where f is a fibonacci series and n is [reducedN].
     */
    fun getFibonacciModulo(reducedN: Long, modulo: Long): Long {
        if (reducedN <= 1L) return reducedN
        // F(0) % modulo
        var previous = 0L
        // F(1) % modulo
        var current = 1L
        // We already have F(0) and F(1). Hence, we start from 2.
        // For example, let us assume that the reducedN is 5.
        // The reducedN is our upper limit and it is 5.
        // We are looking for the value of F(5).
        // If we start from 0, it will go up to and including 7.
        // Because, then 0 for F(2), 1 for F(3), 2 for F(4), 3 for F(5), 4 for F(6), 5 for F(7) which is wrong.
        // If we really want to start from 0, then we should subtract 2 from the reducedN. I.e., reducedN - 2,
        // acknowledging the fact that we already have two values. So, the upper limit will be reducedN - 2 = 5 - 2 = 3.
        // In that case, 0 for F(2), 1 for F(3), 2 for F(4), 3 for F(5), which is fine.
        for (i in 2..reducedN) {
            // When we use `% modulo`, we talk about remainders.
            // We want to find the remainder of reducedN fibonacci number for a given modulo.
            // Essentially, we track remainders.
            // The next fibonacci number is an addition of the last two fibonacci numbers.
            // But, instead of the true value of the next fibonacci number, we are interested in its remainder.
            // Hence, we use modulo. I.e. (previous + next) % modulo.
            // Let us assume that the reducedN (nth fibonacci) is f(21) and the modulo is 17.
            // Naive algorithm: result = previous + current, previous = current, current = result
            // In the end, result % modulo
            // A collection of the Naive fibonacci numbers
            // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946]
            // So, what did we do? 0 (previous) + 1 (current) = 1 (result), 1 + 1 = 2, 2 + 1 = 3, 3 + 2 = 5, 5 + 3 = 8..
            // So, basically: a + b and in the end, what did we get? F(21) = 10946, which is a result of a + b,
            // where a = 4181 and b = 6765.
            // F(21) = 10946 % 17 = 15 <----------------------------------------------------------------------------
            // Modular arithmetic: result = (previous + current) % modulo, previous = current, current = result
            // In the end, result is our answer.
            // A collection of the Modular Addition for each fibonacci number
            // [0, 1, 1, 2, 3, 5, 8, 13, 4, 0, 4, 4, 8, 12, 3, 15, 1, 16, 0, 16, 16, 15]
            // F(21) = 15 (Magic?) <--------------------------------------------------------------------------------
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
            // To learn more about Modular Arithmetic:
            // https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction
            // So, if we are doing modulo on a sum of two large numbers,
            // it is better to first derive modulo of each large number, sum up the result of each,
            // and then perform the modulo on it. It will give the same answer, but will save us from the too large computation.
            // The purpose of doing % modulo is to keep the result within the modulo range.
            // The modulo operation ensures that the result stays within the range of 0 to m-1, where m is modulo.
            val newCurrent = (previous + current) % modulo
            previous = current
            current = newCurrent
        }
        return current
    }

    /**
     * We want to find at which point the nth fibonacci value % [modulo] repeats itself.
     * Essentially, we track remainders, and we check at which point the sequence of remainders repeats.
     * E.g., We may find that, when we do nth fibonacci number `f(n) % 10`,
     * the sequence repeats itself after 60 in such a way that
     * `f(4) % 10` and `f(64) % 10` give the same result: 3.
     * I.e., The remainder (in our example, the last digit - that's what we get when we do `% 10`)
     * of the nth fibonacci value repeats after every 60 numbers.
     * The point at which it (the sequence or pattern of remainders) starts repeating itself, is our Pisano period.
     * So, in our example, for modulo 10, the Pisano period is 60.
     * I.e. After 60, the sequence (result) of `f(n) % 10` starts repeating itself.
     * Hence, if we are finding `f(64) % 10`, we can get the same answer by taking the much smaller number.
     * I.e. 64 % our Pisano period = 64 % 60 = 4 is our much smaller equivalent number.
     * Then, we can do 4 % our modulo = f(4) % 10 = 3.
     * Mathematical Insight:
     * For a given modulo m, the nth fibonacci number a, f(a) % m = f(b) % m where b = a % p and p = The Pisano period.
     */
    fun getPisanoPeriodOfFibonacci(modulo: Long): Long {
        // Notice that the nth fibonacci remainder sequence starts with 0 and 1.
        // Because the constraint is: 2 <= modulo <= 10 to the power 3rd.
        var previous = 0L
        var next = 1L
        var length = 0L
        // It is a precomputed fact that the Pisano period does not exceed modulo squared.
        // It is based on the number theory property.
        for (i in 0..(modulo * modulo)) {
            // We want to check at which point `f(n) % modulo` repeats.
            // When we use `% modulo`, we talk about remainders.
            // Essentially, we track remainders, and we check at which point the sequence or pattern of remainders repeats.
            // The next fibonacci number is an addition of the last two fibonacci numbers.
            // But, instead of the true value of the next fibonacci number, we are interested in its remainder.
            // Hence, we use modulo. I.e. (previous + next) % modulo.
            // And the purpose of doing % modulo is to keep the result within the modulo range.
            // The modulo operation ensures that the result stays within the range of 0 to m-1, where m is modulo.
            // Imagine we have a clock with m hours. The current time is 1.
            // If we add hours and exceed m, we wrap around the clock.
            // E.g. If m is 12, adding 14 hours would wrap around 2 hours (14 % 12 = 2)
            // and 2 is within the range of 0 to m-1 = 0 to 12-1 = 0 to 11.
            // And if we look at the answer, whether we add 14 hours or 2 hours, the answer remains the same: 3
            val remainder = (previous + next) % modulo
            previous = next
            next = remainder
            length += 1

            if (previous == 0L && next == 1L) {
                // Stop when the sequence repeats
                return length
            }
        }
        return length
    }

    /**
     * Gives the fibonacci value for the [nthFibonacci].
     */
    fun getEquivalentSmallerFibonacciNumber(nthFibonacci: Long, modulo: Long): Long {

        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1L) return nthFibonacci

        // It is inefficient if we do this for each and every large fibonacci number.
        // There is a number theory property to optimise the approach.
        // For a particular modulo m, the sequence of result `f(n) % m` repeats itself.
        // This point is known as Pisano period.
        // If we find it, we can take the equivalent smaller number of a large nth fibonacci number,
        // because the result of `f(n) % m` would be same for both of them.
        val pisanoPeriod = getPisanoPeriodOfFibonacci(modulo)

        // We reduce the input (nth fibonacci number) modulo pisanoPeriod to get a much smaller equivalent of the input!
        // E.g., 4 mod 10 and 64 mod 10 will give the same answer: 3
        val reducedN = (nthFibonacci % pisanoPeriod)

        return reducedN
    }

    // Take the input
    val scanner = Scanner(System.`in`)

    // Read the input
    val nthFibonacci = scanner.nextLong()
    val modulo = scanner.nextLong()

    // Get the equivalent but much smaller (reduced) fibonacci number
    // for which the last digit will be the same when using the same modulo
    val equivalentReducedFibonacciNumber = getEquivalentSmallerFibonacciNumber(nthFibonacci, modulo)
    // Let us find the last digit of the much smaller equivalent fibonacci number.
    println(getFibonacciModulo(equivalentReducedFibonacciNumber, modulo))
}