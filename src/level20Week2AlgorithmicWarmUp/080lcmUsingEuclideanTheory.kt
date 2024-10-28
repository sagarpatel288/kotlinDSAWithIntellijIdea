package level20Week2AlgorithmicWarmUp

import java.util.*

/**
 * Find LCM (The Least Common Multiple) of the given pair.
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