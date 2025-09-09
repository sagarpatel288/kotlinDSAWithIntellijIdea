package courses.uc.course01algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Find GCD (The greatest common divisor, a.k.a. the highest common factor)
 * of the given pair.
 *
 * GCD (Greatest Common Divisor) and HCF (Highest Common Factor) refer to the same concept in mathematics.
 * Both terms describe the largest positive integer that divides two or more integers without leaving a remainder.
 * Depending on the region or educational context, one term may be preferred over the other,
 * but they are interchangeable in meaning.
 */
fun main() {

    val firstInput = Scanner(System.`in`).nextLong()
    val secondInput = Scanner(System.`in`).nextLong()
    println(getGcd(firstInput, secondInput))
}

/**
 * The Euclid's theory says:
 *
 * gcd(a,b)
 *
 * = a if b is 0
 *
 * otherwise,
 *
 * keep doing gcd(b, a % b) until the remainder becomes 0.
 */
fun getGcd(first: Long, second: Long): Long {
    if (second == 0L) return first
    return getGcd(second, first % second)
}
