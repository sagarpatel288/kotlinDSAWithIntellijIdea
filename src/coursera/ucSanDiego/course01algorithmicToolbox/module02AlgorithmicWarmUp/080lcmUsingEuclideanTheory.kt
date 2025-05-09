package coursera.ucSanDiego.course01algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.*

/**
 * Find LCM (The Least Common Multiple) of the given pair.
 *
 * Note that:
 * LCM (The Least Common Multiple) and LCF (The Least Common Factor) are not the same.
 *
 * - **LCM (The Least Common Multiple)**:
 * This is the smallest multiple that is exactly divisible by two or more numbers.
 * For example, the LCM of 4 and 5 is 20.
 *
 * - **LCF (The Least Common Factor)**:
 * This typically refers to the smallest factor that two or more numbers share,
 * which is often 1 for any set of numbers since 1 is a factor of all integers.
 *
 * So, while LCM deals with multiples, LCF deals with factors, and they are used in different contexts in mathematics.
 */
fun main() {

    /**
     * Finding:
     * GCD: Greatest Common Divisor or
     * HCF: Highest Common Factor
     *
     * This is based on the Euclidean theory.
     * According to the theory,
     * gcd(a, b)
     *
     * = a if b is 0. Otherwise,
     *
     * = gcd(b, a % b) until the second argument becomes 0.
     */
    fun getGcd(first: Long, second: Long): Long {
        if (second == 0L) return first
        return getGcd(second, first % second)
    }

    val input = Scanner(System.`in`).nextLine().split(" ")
    val first = input[0].toLong()
    val second = input[1].toLong()
    val gcd = getGcd(first, second)
    val lcm = (first * second) / gcd
    println(lcm)
}