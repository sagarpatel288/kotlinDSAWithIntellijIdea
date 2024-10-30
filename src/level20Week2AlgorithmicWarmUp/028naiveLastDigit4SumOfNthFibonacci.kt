package level20Week2AlgorithmicWarmUp

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
 */
fun main() {

    val nthFibonacci = readln().toInt()
    val sumOfNthFibonacci = sumOfNthFibonacci(nthFibonacci, 10)
    val sumOfNthFibonacciByModularAddition = sumOfNthFibonacciByModularAddition(nthFibonacci, 10)
    val sumOfReducedNthFibonacci = sumOfReducedNthFibonacci(nthFibonacci, 10)
    println(": :main: sumOfNthFibonacci: $sumOfNthFibonacci")
    println(": :main: sumOfNthFibonacciByModularAddition $sumOfNthFibonacciByModularAddition")
    println(": :main: sumOfReducedNthFibonacci $sumOfReducedNthFibonacci")
}

/**
 * We want to find: `S(n) % m`
 * However, Sum of S(n) = `F(n+2) - 1`
 * So, `S(n) % m` = `(F(n+2) - 1) % m`
 */
fun sumOfNthFibonacci(nthFibonacci: Int, modulo: Int): Long {
    // F(0)
    var previous = 0L
    // F(1)
    var current = 1L
    val fibonacciElements = mutableListOf(previous, current)
    // S(n) = F(n+2) - 1. So, we iterate up to F(n+2) instead of n.
    for (i in 2..(nthFibonacci + 2)) {
        val newCurrent = previous + current
        fibonacciElements.add(newCurrent)
        previous = current
        current = newCurrent
    }
    println("sumOfNthFibonacci: fibonacciElements: $fibonacciElements")
    println(": :sumOfNthFibonacci: current: $current")
    // S(n) = F(n+2) - 1. So, we subtract 1.
    return (current - 1) % modulo
}

/**
 * We want to find sum of Nth Fibonacci % modulo
 *  = `S(n) % m`
 *  Apply Sum of Nth Fibonacci rule/theory:
 *  = `(F(n+2) - 1) % m`
 *  Simplify F(n+2) as F(n')
 *  = `(F(n') - 1) % m`
 *  Apply Modular arithmetic theory:
 *  [Modular Subtraction](https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction)
 *  = `(F(n') % m - 1 % m) % m`
 *  Each Fibonacci number is the sum of the two preceding ones.
 *  = `((F(n'1) + F(n'2)) % m - 1 % m) % m`
 *  Apply Modular arithmetic theory:
 *  [Modular Addition](https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction)
 *  = `((F(n'1) % m + F(n'2) % m) % m - 1 % m) % m`
 */
fun sumOfNthFibonacciByModularAddition(nthFibonacci: Int, modulo: Int): Long {
    // F(0) % modulo
    var previousRemainder = 0L
    // F(1) % modulo
    var nextRemainder = 1L
    val fibonacciRemainderElements = mutableListOf(previousRemainder, nextRemainder)
    for (i in 2..(nthFibonacci + 2)) {
        // Each Fibonacci number is the sum of the two preceding ones.
        // (A + B) mod C = (A mod C + B mod C) mod C
        val newRemainder = (previousRemainder + nextRemainder) % modulo
        fibonacciRemainderElements.add(newRemainder)
        previousRemainder = nextRemainder
        nextRemainder = newRemainder
    }
    println(": :sumOfNthFibonacciByModularAddition: fibonacciRemainderElements: $fibonacciRemainderElements")
    println(": :sumOfNthFibonacciByModularAddition: (n+2) remainder: $nextRemainder")
    // S(n) = F(n+2) - 1. So, we subtract 1.
    // Key-point:
    // We add 10 because if the remainder is 0, subtracting 1 will give a negative number - that we want to avoid.
    // In the end, % 10 to get the last digit.
    return (nextRemainder - 1 + 10) % 10
}

/**
 * We want to find sum of Nth Fibonacci % modulo
 *  = `S(n) % m`
 *  Apply Sum of Nth Fibonacci rule/theory:
 *  = `(F(n+2) - 1) % m`
 *  Simplify F(n+2) as F(n')
 *  = `(F(n') - 1) % m`
 *  When we have a Fibonacci number and a modulo to apply, we use the Pisano period.
 *  According to the Pisano period theory, find the much smaller congruence number.
 *  Because the modulo on the original large Fibonacci number and the modulo on the much smaller congruence number,
 *  is the same.
 *  = `(F(p) - 1) % m`
 *  Apply Modular arithmetic theory:
 *  [Modular Subtraction](https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction)
 *  = `(F(p) % m - 1 % m) % m`
 *  Each Fibonacci number is the sum of the two preceding ones.
 *  = `((F(p1) + F(p2)) % m - 1 % m) % m`
 *  Apply Modular arithmetic theory:
 *  [Modular Addition](https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-addition-and-subtraction)
 *  = `((F(p1) % m + F(p2) % m) % m - 1 % m) % m`
 */
fun sumOfReducedNthFibonacci(nthFibonacci: Int, modulo: Int): Long {
    // When we have a Fibonacci number and a modulo to apply, we use the Pisano period.
    // According to the Pisano period theory, find the much smaller congruence number.
    // Because the modulo on the original large Fibonacci number and the modulo on the much smaller congruence number,
    // are the same.
    val pisanoPeriod = findPisanoPeriod(modulo)
    val reducedN = (nthFibonacci + 2) % pisanoPeriod
    println(": :sumOfReducedNthFibonacci: pisanoPeriod: $pisanoPeriod reducedN: $reducedN")
    // F(0) % modulo
    var previousRemainder = 0L
    // F(1) % modulo
    var nextRemainder = 1L
    val reducedRemainderElements = mutableListOf(previousRemainder, nextRemainder)
    for (i in 2..reducedN) {
        val newRemainder = (previousRemainder + nextRemainder) % modulo
        reducedRemainderElements.add(newRemainder)
        previousRemainder = nextRemainder
        nextRemainder = newRemainder
    }
    println(": :sumOfReducedNthFibonacci: reducedRemainderElements: $reducedRemainderElements")
    println(": :sumOfReducedNthFibonacci: (n+2) remainder: $nextRemainder")
    // S(n) = F(n+2) - 1. So, we subtract 1.
    // We add 10 because if the remainder is 0, subtracting 1 will give a negative number - that we want to avoid.
    // In the end, % 10 to get the last digit.
    return (nextRemainder - 1 + 10) % 10
}

fun findPisanoPeriod(modulo: Int): Int {
    // We are interested in tracking the pattern of remainders so that we can know when it repeats.
    // F(0) % modulo
    var previousRemainder = 0
    // F(1) % modulo
    var nextRemainder = 1
    // This is to track at which point the cycle (sequence or pattern) of the remainders repeats.
    var cycleLength = 0
    // The Pisano period is always less than or equal to modulo squared.
    for (i in 0..(modulo * modulo)) {
        val newRemainder = (previousRemainder + nextRemainder) % modulo
        previousRemainder = nextRemainder
        nextRemainder = newRemainder
        cycleLength++

        // When we find that the cycle (sequence or pattern) of the remainders repeats itself,
        // that is the length (point) of the Pisano period.
        if (previousRemainder == 0 && nextRemainder == 1) {
            return cycleLength
        }
    }
    return cycleLength
}