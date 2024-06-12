package level20Week2AlgorithmicWarmUp

import java.util.Scanner

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

