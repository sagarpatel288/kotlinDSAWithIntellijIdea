package coursera.ucSanDiego.algorithmicToolbox.module02AlgorithmicWarmUp

/**
 * Problem Statement:
 *
 * Last Digit of the Sum of Squares of Fibonacci Numbers Problem
 * Compute the last digit of F0^2 +F1^2 +···+Fn^2.
 *
 * Input: An integer n.
 * Output: The last digit of F0^2 +F1^2 +···+Fn^2.
 *
 * Hint:
 * Since the brute force search algorithm for this problem is too slow (n may be as large as 1014),
 * we need to come up with a simple formula for F0^2 +F1^2 +···+Fn^2.
 *
 * ----------------------- Explanation -----------------------
 *
 * Last digit of sum of square of nth Fibonacci
 * = (Fn0 squared + Fn1 squared + .. + Fn squared) % m where m = 10 to take the last digit.
 *
 * Fn % m
 *
 * = {Fn (Fn+1)} % m (This is the key-lemma, a formula that gives the sum of square of nth Fibonacci number.)
 *
 * = {(Fn % m) (Fn+1) % m} % m (This is the simplification.)
 *
 * = {(Fnp % m) (Fnp' % m)} % m
 *
 * Where Fnp = Fn % p and Fnp' = Fn+1 % p
 * Where p = The Pisano Period (The Pisano length)
 * and p <= (m * m)
 *
 * = {(np * np')} % m
 * Where np is the result of the (Fnp % m)
 * and np' is the result of the (Fnp' % m)
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

    val nthFibonacci = readln().toLong()
    val pisanoLength = getPisanoLengthOfFibonacci(10L)
    val reducedN = nthFibonacci % pisanoLength
    val reducedNplusOne = (nthFibonacci + 1) % pisanoLength
    val reducedNmodulo = getNthFibonacciModulo(reducedN, 10L)
    val reducedNplusOneModulo = getNthFibonacciModulo(reducedNplusOne, 10L)
    val result = (reducedNmodulo * reducedNplusOneModulo) % 10L
    println(result)
}

fun getNthFibonacciModulo(reducedN: Long, modulo: Long): Long {
    if (reducedN <= 1) return reducedN
    // F(0) % m = 0
    var previous = 0L
    // F(1) % m = 1
    var current = 1L
    // We have our first two elements, and we want to iterate up to the given `reducedN`
    // Hence, we start from 2 instead of 0.
    // If we start from 0, it becomes reducedN + 2, and it will give the modulo of the wrong n.
    for(i in 2..reducedN) {
        val newCurrent = (previous + current) % modulo
        previous = current
        current = newCurrent
    }
    return current
}

fun getPisanoLengthOfFibonacci(modulo: Long): Long {
    // F(0) % m = 0 The first element of the remainders
    var previous = 0L
    // F(1) % m = 1 The second element of the remainders
    var next = 1L
    // The point from which the sequence pattern of the remainders repeat itself
    var pisanoLength = 0L
    // We are finding the length.
    // Our initial pair is (0, 1) The first two elements.
    // Now, we start our counting from 0 and go up to the point where we find that the sequence pattern of
    // the remainders repeat itself. So basically that's why we start our iteration from 0 instead of 2.
    // I.e. (0, 1) 1(length 0), 2(l 1), 0(l 2), 3(l 3), 0(l 4), 1(l 5). So, we conclude that the Pisano length is 5.
    // Which means, after every 5 n % m, the sequence pattern of the remainders repeat.
    for (i in 0..(modulo * modulo)) {
        val newNext = (previous + next) % modulo
        previous = next
        next = newNext
        pisanoLength++

        // Stop when we find that the sequence pattern of the remainders repeat itself
        if (previous == 0L && next == 1L) {
            return pisanoLength
        }
    }
    return pisanoLength
}
