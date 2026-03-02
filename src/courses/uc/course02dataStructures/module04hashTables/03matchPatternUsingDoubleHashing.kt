package courses.uc.course02dataStructures.module04hashTables

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * # Problem:
 *
 * * This is the same problem as:
 *
 * * [Local: Find Substring](src/courses/uc/course02dataStructures/module04hashTables/03findSubstring.kt)
 *
 * * [GitHub: Find Substring](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/29e897950fe432d27bdf9d541c9adb8f13110fe7/src/courses/uc/course02dataStructures/module04hashTables/03findSubstring.kt)
 *
 * * Here, we solve the same problem using the "Double Hashing" technique instead of manual (character-by-character)
 * double-check.
 *
 * ## Grader Output:
 * ```
 * Good job! (Max time used: 0.48/3.00, max memory used: 97570816/536870912.)
 * ```
 */
class MatchPatternUsingDoubleHashing {

    private val p1 = 1_000_000_007L
    private val p2 = 1_000_000_263L
    private val b1 = 31L
    private val b2 = 263L
    private var b1HighestPower = 1L
    private var b2HighestPower = 1L

    fun matchPattern(pattern: String, text: String): List<Int> {
        if (pattern.length > text.length) return emptyList()

        var hashP1 = 0L
        var hashP2 = 0L
        var hashT1 = 0L
        var hashT2 = 0L

        // Hash value of the pattern and the first window
        for (i in 0 until pattern.length) {
            hashP1 = ((hashP1 * b1) + pattern[i].code.toLong()) % p1
            hashP2 = ((hashP2 * b2) + pattern[i].code.toLong()) % p2
            hashT1 = ((hashT1 * b1) + text[i].code.toLong()) % p1
            hashT2 = ((hashT2 * b2) + text[i].code.toLong()) % p2
        }

        hashP1 = (hashP1 % p1 + p1) % p1
        hashP2 = (hashP2 % p2 + p2) % p2
        hashT1 = (hashT1 % p1 + p1) % p1
        hashT2 = (hashT2 % p2 + p2) % p2

        // Finding the highest power of the base for the rolling hashing
        for (i in 1 until pattern.length) {
            // Mistake: Don't forget to modulo the multiplication product. It matters.
            b1HighestPower = (b1HighestPower * b1) % p1
            b2HighestPower = (b2HighestPower * b2) % p2
        }

        val result = mutableListOf<Int>()

        // Rolling hashing
        for (i in 0 .. (text.length - pattern.length)) {
            if (hashP1 == hashT1 && hashP2 == hashT2) {
                result.add(i)
            }

            if (i < (text.length - pattern.length)) {
                val sub1 = (text[i].code.toLong() * b1HighestPower) % p1
                hashT1 = (hashT1 - sub1) % p1
                hashT1 = (hashT1 * b1) % p1
                hashT1 = (hashT1 + text[i + pattern.length].code.toLong()) % p1

                val sub2 = (text[i].code.toLong() * b2HighestPower) % p2
                hashT2 = (hashT2 - sub2) % p2
                hashT2 = (hashT2 * b2) % p2
                hashT2 = (hashT2 + text[i + pattern.length].code.toLong()) % p2

                hashT1 = (hashT1 % p1 + p1) % p1
                hashT2 = (hashT2 % p2 + p2) % p2
            }
        }

        return result
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val pattern = reader.readLine()
    val text = reader.readLine()
    val solver = MatchPatternUsingDoubleHashing()
    println(solver.matchPattern(pattern, text).joinToString(" "))
}