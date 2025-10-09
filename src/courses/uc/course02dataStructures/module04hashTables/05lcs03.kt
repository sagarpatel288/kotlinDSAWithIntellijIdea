package courses.uc.course02dataStructures.module04hashTables

import java.io.BufferedReader
import java.io.InputStreamReader

private data class LCSubstring(val startIndex1: Int, val startIndex2: Int, val length: Int)

private fun checkForLength(string1: String, string2: String, length: Int): LCSubstring? {
    if (length == 0) return LCSubstring(0, 0, 0)
    if (length > string1.length || length > string2.length) return null

    // Compute rolling hashes for all substrings of the given length, storing all start indices per hash
    fun getAllHashes(string: String, length: Int): Map<Pair<Long, Long>, MutableList<Int>> {
        val prime1 = 1_000_000_007L
        val prime2 = 1_000_000_009L
        val xBase = 263L
        val hashes = mutableMapOf<Pair<Long, Long>, MutableList<Int>>()
        var hash1 = 0L
        var hash2 = 0L

        // First window
        for (i in 0 until length) {
            hash1 = (hash1 * xBase + string[i].code.toLong()) % prime1
            hash2 = (hash2 * xBase + string[i].code.toLong()) % prime2
        }
        hash1 = (hash1 % prime1 + prime1) % prime1
        hash2 = (hash2 % prime2 + prime2) % prime2
        hashes.getOrPut(hash1 to hash2) { mutableListOf() }.add(0)

        // Precompute base^(length-1) mod primes
        var xPower1 = 1L
        var xPower2 = 1L
        for (i in 1 until length) {
            xPower1 = (xPower1 * xBase) % prime1
            xPower2 = (xPower2 * xBase) % prime2
        }

        // Rolling hash for subsequent windows
        for (i in 1 .. string.length - length) {
            val sub1 = (string[i - 1].code.toLong() * xPower1) % prime1
            val add1 = string[i + length - 1].code.toLong()
            hash1 = (hash1 - sub1 + prime1) % prime1
            hash1 = (hash1 * xBase + add1) % prime1
            hash1 = (hash1 + prime1) % prime1

            val sub2 = (string[i - 1].code.toLong() * xPower2) % prime2
            val add2 = string[i + length - 1].code.toLong()
            hash2 = (hash2 - sub2 + prime2) % prime2
            hash2 = (hash2 * xBase + add2) % prime2
            hash2 = (hash2 + prime2) % prime2

            hashes.getOrPut(hash1 to hash2) { mutableListOf() }.add(i)
        }
        return hashes
    }

    val hashes1 = getAllHashes(string1, length)
    val hashes2 = getAllHashes(string2, length)

    // Check all matching hash pairs and verify actual substrings
    for ((hashPair, list1) in hashes1) {
        if (hashes2.containsKey(hashPair)) {
            for (start1 in list1) {
                for (start2 in hashes2[hashPair]!!) {
                    if (string1.substring(start1, start1 + length) ==
                        string2.substring(start2, start2 + length)) {
                        return LCSubstring(start1, start2, length)
                    }
                }
            }
        }
    }
    return null
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var line = reader.readLine()
    val output = StringBuilder()
    while (line != null && line.isNotBlank()) {
        val (string1, string2) = line.split(" ")
        var start = 0
        var end = minOf(string1.length, string2.length)
        var bestAnswer = LCSubstring(0, 0, 0)

        while (start <= end) {
            val mid = start + (end - start) / 2
            val result = checkForLength(string1, string2, mid)
            if (result != null) {
                bestAnswer = result
                start = mid + 1  // try longer
            } else {
                end = mid - 1   // try shorter
            }
        }
        output.append("${bestAnswer.startIndex1} ${bestAnswer.startIndex2} ${bestAnswer.length}\n")
        line = reader.readLine()
    }
    println(output)
}
