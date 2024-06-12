package level20Week2AlgorithmicWarmUp

import java.util.Scanner

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
