package courses.uc.course02dataStructures.module04hashTables

import java.io.BufferedReader
import java.io.InputStreamReader

private class LongestCommonSubstringRabinKarp {

    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_263L
    private val base1 = 31L
    private val base2 = 263L

    fun checkForLength(string1: String, string2: String, length: Int): Triple<Int, Int, Int>? {
        if (length == 0) return Triple(0, 0, 0)
        if (length > string1.length || length > string2.length) return null
        val hashes = mutableMapOf<Long, Int>()

        var hashA1 = 0L
        var hashA2 = 0L
        var hashB1 = 0L
        var hashB2 = 0L

        // Compute rolling hashes for all substrings of the given length, storing all start indices per hash
        for (i in 0 until length) {
            hashA1 = ((hashA1 * base1) + string1[i].code.toLong()) % prime1
            hashA2 = ((hashA2 * base2) + string1[i].code.toLong()) % prime2
            hashB1 = ((hashB1 * base1) + string2[i].code.toLong()) % prime1
            hashB2 = ((hashB2 * base2) + string2[i].code.toLong()) % prime2
        }

        hashA1 = (hashA1 % prime1 + prime1) % prime1
        hashA2 = (hashA2 % prime2 + prime2) % prime2
        hashB1 = (hashB1 % prime1 + prime1) % prime1
        hashB2 = (hashB2 % prime2 + prime2) % prime2

        if (hashA1 == hashB1 && hashA2 == hashB2) {
            return Triple(0, 0, length)
        }

        val combined = (hashA1 shl 32) or hashA2
        hashes[combined] = 0

        // Calculate the highest power of the base for the given length
        // Precompute base^(length-1) mod primes
        var baseWithHighestPower1 = 1L
        var baseWithHighestPower2 = 1L
        for (i in 1 until length) {
            baseWithHighestPower1 = (baseWithHighestPower1 * base1) % prime1
            baseWithHighestPower2 = (baseWithHighestPower2 * base2) % prime2
        }

        fun rollingHash(string: String, startingIndex: Int, previousHash: Pair<Long, Long>): Pair<Long, Long> {
            var (hash1, hash2) = previousHash
            val sub1 = (string[startingIndex - 1].code.toLong() * baseWithHighestPower1) % prime1
            hash1 = (hash1 - sub1) % prime1
            hash1 = (hash1 * base1) % prime1
            val add1 = string[startingIndex + length].code.toLong() % prime1
            hash1 = (hash1 + add1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val sub2 = (string[startingIndex - 1].code.toLong() * baseWithHighestPower2) % prime2
            hash2 = (hash2 - sub2) % prime2
            hash2 = (hash2 * base2) % prime2
            val add2 = string[startingIndex + length].code.toLong() % prime2
            hash2 = (hash2 + add2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            return hash1 to hash2
        }

        // Rolling hash for the subsequent windows of the string1
        for (i in 1 until string1.length - length) {
            val (hash1, hash2) = rollingHash(string1, i, hashA1 to hashA2)
            hashA1 = hash1
            hashA2 = hash2
            val combined = (hashA1 shl 32) or hashA2
            hashes[combined] = i
        }

        // Rolling hash for the subsequent windows of the string2
        for (i in 1 until string2.length - length) {
            val (hash1, hash2) = rollingHash(string2, i, hashB1 to hashB2)
            hashB1 = hash1
            hashB2 = hash2
            val combined = (hashB1 shl 32) or hashB2
            if (hashes.containsKey(combined)) {
                val startingIndex = hashes[combined]!!
                return Triple(startingIndex, i, length)
            }
        }

        return null
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var line = reader.readLine()
    val solver = LongestCommonSubstringRabinKarp()
    val output = StringBuilder()
    while (line != null && line.isNotBlank()) {
        var bestAnswer = Triple(0, 0, 0)
        val (string1, string2) = line.split(" ")
        var start = 0
        var end = minOf(string1.length, string2.length)
        while (start <= end) {
            val mid = start + (end - start) / 2
            val result = solver.checkForLength(string1, string2, mid)
            if (result != null) {
                bestAnswer = result
                start = mid + 1  // try longer
            } else {
                end = mid - 1   // try shorter
            }
        }
        output.append("${bestAnswer.first} ${bestAnswer.second} ${bestAnswer.third}\n")
        line = reader.readLine()
    }
    println(output)
}
