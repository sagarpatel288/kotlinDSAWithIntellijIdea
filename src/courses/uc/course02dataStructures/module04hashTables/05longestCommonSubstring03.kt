package courses.uc.course02dataStructures.module04hashTables

import java.io.BufferedReader
import java.io.InputStreamReader

class LongestCommonSubstring03(private val string1: String, private val string2: String) {

    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_061L
    private val xBase = 31L
    private val hashes1a = LongArray(string1.length + 1)
    private val hashes2a = LongArray(string1.length + 1)
    private val hashes1b = LongArray(string2.length + 1)
    private val hashes2b = LongArray(string2.length + 1)
    private val xPower1a = LongArray(string1.length + 1)
    private val xPower2a = LongArray(string1.length + 1)
    private val xPower1b = LongArray(string2.length + 1)
    private val xPower2b = LongArray(string2.length + 1)

    init {
        hashes1a[0] = 0L
        hashes2a[0] = 0L
        hashes1b[0] = 0L
        hashes2b[0] = 0L
        xPower1a[0] = 1L
        xPower2a[0] = 1L
        xPower1b[0] = 1L
        xPower2b[0] = 1L

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        for (i in 0 until string1.length) {
            hashes1a[i + 1] = (hashes1a[i] * xBase) % prime1
            hashes1a[i + 1] = ((hashes1a[i + 1]) + string1[i].code.toLong()) % prime1
            hashes2a[i + 1] = (hashes2a[i] * xBase) % prime2
            hashes2a[i + 1] = (hashes2a[i + 1] + string1[i].code.toLong()) % prime2
        }

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        for (i in 0 until string2.length) {
            hashes1b[i + 1] = (hashes1b[i] * xBase) % prime1
            hashes1b[i + 1] = ((hashes1b[i + 1]) + string2[i].code.toLong()) % prime1
            hashes2b[i + 1] = (hashes2b[i] * xBase) % prime2
            hashes2b[i + 1] = (hashes2b[i + 1] + string2[i].code.toLong()) % prime2
        }

        // Calculate the power (degree) of the base
        for (i in 1 until string1.length) {
            xPower1a[i] = (xPower1a[i - 1] * xBase) % prime1
            xPower2a[i] = (xPower2a[i - 1] * xBase) % prime2
        }

        // Calculate the power (degree) of the base
        for (i in 1 until string2.length) {
            xPower1b[i] = (xPower1b[i - 1] * xBase) % prime1
            xPower2b[i] = (xPower2b[i - 1] * xBase) % prime2
        }
    }

    fun checkForLength(length: Int): Triple<Int, Int, Int>? {
        if (length > string1.length || length > string2.length) return null
        val hashes1 = mutableMapOf<Pair<Long, Long>, Int>()
        for (i in 0..string1.length - length) {
            val long1 = hashes1a[i + length]
            val short1 = hashes1a[i]
            val base1 = xPower1a[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashes2a[i + length]
            val short2 = hashes2a[i]
            val base2 = xPower2a[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            hashes1[hash1 to hash2] = i
        }

        for (i in 0..string2.length - length) {
            val long1 = hashes1b[i + length]
            val short1 = hashes1b[i]
            val base1 = xPower1b[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashes2b[i + length]
            val short2 = hashes2b[i]
            val base2 = xPower2b[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            if (hashes1.contains(hash1 to hash2)) {
                val startIndex1 = hashes1[hash1 to hash2]!!
                if (string1.substring(startIndex1, startIndex1 + length) == string2.substring(i, i + length)) {
                    return Triple(startIndex1, i, length)
                }
            }
        }
        return null
    }

}

fun main() {
    val lines = generateSequence { readLine()?.takeIf { it.isNotBlank() } }.toList()
    val output = StringBuilder()
    for (line in lines) {
        val (string1, string2) = line.split(" ")
        val lcs = LongestCommonSubstring03(string1, string2)
        var bestAnswer = Triple(0, 0, 0)
        // Shortest possible length of a substring match
        var start = 0
        // Longest possible length of a substring match
        var end = minOf(string1.length, string2.length)
        while (start <= end) {
            val mid = start + (end - start) / 2
            val answer = lcs.checkForLength(mid)
            if (answer != null) {
                bestAnswer = answer
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        output.append("${bestAnswer.first} ${bestAnswer.second} ${bestAnswer.third}\n")
    }
    println(output.toString())
}