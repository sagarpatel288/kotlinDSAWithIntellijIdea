package level20Week2AlgorithmicWarmUp

fun main() {

    // Take the input
    val nthFibonacci = readln().toLong()
    // S(n) = F(n+2) - 1. Hence, we use n + 2.
    // The modulo is 10, because we want to find the last digit of the sum of the nth Fibonacci number
    val reducedN = reduceNthFibonacci(nthFibonacci + 2, 10L)
    val lastDigitOfSumOfNthFibonacci = lastDigitOfSumOfNthFibonacci(reducedN, 10L)
    println(lastDigitOfSumOfNthFibonacci)
}

/**
 * Each Fibonacci number is an addition of the two preceding numbers.
 * So, F(n) = F(n1) + F(n2)
 * Also, we are interested in the last digit.
 * Hence, we use a [modulo]
 * So, F(n) % m = (F(n1) + F(n2)) % m
 * However, according to the modular arithmetic theory:
 * [Modular Arithmetic](https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction)
 * (F(n1) + F(n2)) % m = (F(n1) % m + F(n2) % m) % m
 * It means, instead of doing addition of actual Fibonacci number's values,
 * we can perform addition of (each Fibonacci number % modulo).
 */
fun lastDigitOfSumOfNthFibonacci(reducedN: Long, modulo: Long): Long {
    //F(0) % modulo
    var previousRemainder = 0L
    //F(1) % modulo
    var nextRemainder = 1L
    // We already have the values of F(0) and F(1).
    // Now, we want to calculate the value of F(2).
    // Hence, we start from 2 instead of 0.
    // For example, if the reducedN is 5, the iteration should go up to and including F(5).
    // Not below, not above. Exact up to and including F(5).
    // However, if we start the iteration from 0, it will go up to F(7) - which is wrong.
    // Only if we start from 2, it will correctly assign the value to the correct F(n).
    for (i in 2..reducedN) {
        val newRemainder = (previousRemainder + nextRemainder) % modulo
        previousRemainder = nextRemainder
        nextRemainder = newRemainder
    }
    // We subtract 1 because S(n) = F(n+2) - 1.
    // We add 10 because we want to avoid a negative number when the remainder is 0.
    // We perform % 10 to get the last digit.
    // E.g., 3 % 10 and (3 + 10) = 13 % 10, both give 3 only. (Modular arithmetic)
    // Reference: https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/what-is-modular-arithmetic
    return (nextRemainder - 1 + 10) % 10
}

fun reduceNthFibonacci(nthFibonacci: Long, modulo: Long): Long {
    val pisanoPeriod = getPisanoPeriod(modulo)
    return nthFibonacci % pisanoPeriod
}

fun getPisanoPeriod(modulo: Long): Long {
    // F(0) % modulo
    var previousRemainder = 0L
    // F(1) % modulo
    var nextRemainder = 1L
    // The point at which we notice that the sequence pattern of the remainders repeats itself
    // The Pisano length always starts with 0.
    var pisanoLength = 0L
    // The Pisano period is always less than or equal to modulo squared
    // The Pisano iteration always starts with 0.
    for (i in 0..(modulo * modulo)) {
        val newRemainder = (previousRemainder + nextRemainder) % modulo
        previousRemainder = nextRemainder
        nextRemainder = newRemainder
        pisanoLength++

        // When we find that the sequence pattern of the remainders repeats itself,
        // it is the length of the Pisano period.
        if (previousRemainder == 0L && nextRemainder == 1L) {
            return pisanoLength
        }
    }
    return pisanoLength
}

