package courses.uc.course02dataStructures.module04hashTables

class LongestCommonSubstring03(private val string1: String, private val string2: String) {

    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_263L
    private val base1 = 31L
    private val base2 = 263L
    private val hashesA1 = LongArray(string1.length + 1)
    private val hashesA2 = LongArray(string1.length + 1)
    private val hashesB1 = LongArray(string2.length + 1)
    private val hashesB2 = LongArray(string2.length + 1)
    private val xPowerA1 = LongArray(string1.length + 1)
    private val xPowerA2 = LongArray(string1.length + 1)
    private val xPowerB1 = LongArray(string2.length + 1)
    private val xPowerB2 = LongArray(string2.length + 1)

    init {
        hashesA1[0] = 0L
        hashesA2[0] = 0L
        hashesB1[0] = 0L
        hashesB2[0] = 0L
        xPowerA1[0] = 1L
        xPowerA2[0] = 1L
        xPowerB1[0] = 1L
        xPowerB2[0] = 1L

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        for (i in 0 until string1.length) {
            hashesA1[i + 1] = (hashesA1[i] * base1) % prime1
            hashesA1[i + 1] = ((hashesA1[i + 1]) + string1[i].code.toLong()) % prime1

            hashesA2[i + 1] = (hashesA2[i] * base2) % prime2
            hashesA2[i + 1] = (hashesA2[i + 1] + string1[i].code.toLong()) % prime2

            xPowerA1[i + 1] = (xPowerA1[i] * base1) % prime1
            xPowerA2[i + 1] = (xPowerA2[i] * base2) % prime2
        }

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        for (i in 0 until string2.length) {
            hashesB1[i + 1] = (hashesB1[i] * base1) % prime1
            hashesB1[i + 1] = ((hashesB1[i + 1]) + string2[i].code.toLong()) % prime1

            hashesB2[i + 1] = (hashesB2[i] * base2) % prime2
            hashesB2[i + 1] = (hashesB2[i + 1] + string2[i].code.toLong()) % prime2

            xPowerB1[i + 1] = (xPowerB1[i] * base1) % prime1
            xPowerB2[i + 1] = (xPowerB2[i] * base2) % prime2
        }
    }

    fun checkForLength(length: Int): Triple<Int, Int, Int>? {
        if (length > string1.length || length > string2.length) return null
        val hashes1 = mutableMapOf<Long, Int>()
        for (i in 0..string1.length - length) {
            val long1 = hashesA1[i + length]
            val short1 = hashesA1[i]
            val base1 = xPowerA1[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashesA2[i + length]
            val short2 = hashesA2[i]
            val base2 = xPowerA2[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            // Packing two 31-bit hashes into one 64-bit Long
            val combined = (hash1 shl 32) or hash2
            hashes1[combined] = i
        }

        for (i in 0..string2.length - length) {
            val long1 = hashesB1[i + length]
            val short1 = hashesB1[i]
            val base1 = xPowerB1[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashesB2[i + length]
            val short2 = hashesB2[i]
            val base2 = xPowerB2[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            // Packing two 31-bit hashes into one 64-bit Long
            val combined = (hash1 shl 32) or hash2

            if (hashes1.contains(combined)) {
                val startIndex1 = hashes1[combined]!!
                return Triple(startIndex1, i, length)
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