package courses.uc.course01algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Find GCD (The Greatest Common Divisor, a.k.a. HCF - Highest Common Factor)
 * of the given pair.
 *
 * GCD (Greatest Common Divisor) and HCF (Highest Common Factor) refer to the same concept in mathematics.
 * Both terms describe the largest positive integer that divides two or more integers without leaving a remainder.
 * Depending on the region or educational context, one term may be preferred over the other,
 * but they are interchangeable in meaning.
 */
fun main() {

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

    val input = Scanner(System.`in`).nextLine()?.split(" ")
    input?.let {
        println(getGcd(it[0].toLong(), it[1].toLong()))
    }
}

