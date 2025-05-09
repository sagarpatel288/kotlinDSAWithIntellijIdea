package coursera.ucSanDiego.course01algorithmicToolbox.module02AlgorithmicWarmUp

/**
 * Problem Statement:
 *
 * Last Digit of the Sum of Fibonacci Numbers Problem:
 *
 * Compute the last digit of F0 + F1 +···+ Fn.
 *
 * Input: An integer n.
 *
 * Output: The last digit of F0 + F1 + ···+ Fn.
 *
 *
 * Input format. An integer n.
 *
 * Output format. (F0+F1+···+Fn) mod 10.
 *
 * Constraints. 0 ≤ n ≤10^14.
 *
 * Sample 1.
 *
 * Input:
 *
 * 3
 *
 * Output:
 *
 * 4
 *
 * F0+F1+F2+F3 =0+1+1+2=4.
 * 4 % 10 = 4.
 *
 * Sample 2.
 *
 * Input:
 *
 * 100
 *
 * Output:
 *
 * 5
 *
 * F0+···+F100 =927372692193078999175 % 10 = 5.
 *
 *
 * How to remember? (This is a trick to remember something using the association principle, not a proof of the theory.):
 *
 * We remember 3 formulas in a sequence. We start with the sum.
 *
 * Sum of nth Fibonacci: S(n):
 *
 * Sum = a + b. a and b are two elements. n + 2. But, we were asked f(sum of nth). So, we do - 1.
 * I.e. (n + 2) - 1. So, it is: f(n + 2) - 1.
 *
 *  Partial sum of n and m fibonacci: S(m, n):
 *
 *  Keep `(n + 2) - ` from the above equation as it is.
 *  Now, The m is the remaining one part. m + 1.
 *  So, f(n + 2) - f(m + 1).
 *
 *  Sum of square of nth Fibonacci:
 *
 *  After normal sum of fibonacci, we have this sum of square of fibonacci.
 *  Keep f(m + 1) as it is from the above equation.
 *  Square means, m * m, right? So, one m is the remaining part in the equation.
 *  So, it is: f(m + 1) f(m).
 *  Now, you take m or n, it doesn't matter.
 *  So, it is: f(n + 1) f(n).
 *  Also, the order doesn't matter in multiplication.
 *  So, it is: f(n) * f(n + 1).
 */
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
    // We add 10 because we want to avoid a negative number when the remainder is 0. (Key-point).
    // For example, the last digit so far we have calculated can be 0 at this moment.
    // According to the formula, we would do, 0 - 1 = -1.
    // We want to avoid the negative number. So, we add 10.
    // Hence, it becomes: 0 - 1 + 10 = 9 % 10 = 9.
    // Why do we do % 10 here at this stage also?
    // Because, this is a common code. So, it applies even when we get 4 as a last digit after the above for loop.
    // Now, when we do 4 - 1 + 10 = we get 13, and we do not want to return 13.
    // Hence, the % 10 will make it: 13 % 10 = 3, the last digit we want.
    // So, in the end, % 10 ensures that we get the last digit.
    // We perform % 10 to get the last digit.
    // E.g., 3 % 10 and (3 + 10) = 13 % 10, both give 3 only. (Modular arithmetic).
    // Reference: https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/what-is-modular-arithmetic
    // In short:
    // + 10 to avoid negative number, but `+ 10` can also be resulted into more than 1 digit answer.
    // So, to limit the answer after adding `+10`, we also perform `% 10` and get the last digit (single digit) answer.
    return (nextRemainder - 1 + 10) % 10
}

fun reduceNthFibonacci(nthFibonacci: Long, modulo: Long): Long {
    val pisanoPeriod = getPisanoPeriodOfFibonacci(modulo)
    return nthFibonacci % pisanoPeriod
}

fun getPisanoPeriodOfFibonacci(modulo: Long): Long {
    // F(0) % modulo = a % m
    var previousRemainder = 0L
    // F(1) % modulo = b % m
    var nextRemainder = 1L
    // The point at which we notice that the sequence pattern of the remainders repeats itself
    // The Pisano length always starts with 0.
    var pisanoLength = 0L
    // The Pisano period is always less than or equal to modulo squared
    // The Pisano iteration always starts with 0.
    for (i in 0..(modulo * modulo)) {
        // Modular arithmetic = (a + b) % m = {(a % m) + (b % m)} % m = {(previous) + (next)} % m
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

