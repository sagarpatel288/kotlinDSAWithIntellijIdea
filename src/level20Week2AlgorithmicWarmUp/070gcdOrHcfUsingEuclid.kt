package level20Week2AlgorithmicWarmUp

fun main() {

    val firstInput = readln().toLong()
    val secondInput = readln().toLong()
    if (firstInput >= secondInput) {
        println(getGcd(firstInput, secondInput))
    } else {
        println(getGcd(secondInput, firstInput))
    }
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
fun getGcd(larger: Long, smaller: Long): Long {
    if (smaller == 0L) return larger
    return getGcd(smaller, larger % smaller)
}
