import java.io.BufferedReader
import java.io.InputStreamReader

data class LongestCommonStrings(val startIndex1: Int, val startIndex2: Int, val length: Int)

fun getAllHashes(string: String, length: Int): Map<Pair<Long, Long>, MutableList<Int>> {
    if (length == 0 || length > string.length) return emptyMap()
    
    val prime1 = 1_000_000_007L
    val prime2 = 1_000_000_009L  // Changed to different prime
    val xBase1 = 263L
    val xBase2 = 31L
    val hashes = mutableMapOf<Pair<Long, Long>, MutableList<Int>>()
    var hash1 = 0L
    var hash2 = 0L

    // First window - build hash from left to right
    for (i in 0 until length) {
        hash1 = (hash1 * xBase1 + string[i].code.toLong()) % prime1
        hash2 = (hash2 * xBase2 + string[i].code.toLong()) % prime2
    }

    hash1 = (hash1 % prime1 + prime1) % prime1
    hash2 = (hash2 % prime2 + prime2) % prime2

    hashes.computeIfAbsent(hash1 to hash2) { mutableListOf() }.add(0)

    // Precompute x^length for rolling hash
    var xPower1 = 1L
    var xPower2 = 1L
    for (i in 0 until length) {
        xPower1 = (xPower1 * xBase1) % prime1
        xPower2 = (xPower2 * xBase2) % prime2
    }

    // Rolling Hash
    for (i in 1..string.length - length) {
        // Remove leftmost character
        hash1 = (hash1 - (string[i - 1].code.toLong() * xPower1) % prime1 + prime1) % prime1
        // Shift and add rightmost character
        hash1 = (hash1 * xBase1 + string[i + length - 1].code.toLong()) % prime1
        hash1 = (hash1 % prime1 + prime1) % prime1

        hash2 = (hash2 - (string[i - 1].code.toLong() * xPower2) % prime2 + prime2) % prime2
        hash2 = (hash2 * xBase2 + string[i + length - 1].code.toLong()) % prime2
        hash2 = (hash2 % prime2 + prime2) % prime2

        hashes.computeIfAbsent(hash1 to hash2) { mutableListOf() }.add(i)
    }

    return hashes
}

fun checkForLength(string1: String, string2: String, length: Int): LongestCommonStrings? {
    // Edge Cases
    if (length == 0) return LongestCommonStrings(0, 0, 0)
    if (length > string1.length || length > string2.length) return null

    val hashes1 = getAllHashes(string1, length)
    val hashes2 = getAllHashes(string2, length)

    // Check for common hashes
    for ((hashCodePair, indices1) in hashes1) {
        val indices2 = hashes2[hashCodePair]
        if (indices2 != null) {
            // Verify actual match to handle hash collisions
            for (startIndex1 in indices1) {
                val sub1 = string1.substring(startIndex1, startIndex1 + length)
                for (startIndex2 in indices2) {
                    val sub2 = string2.substring(startIndex2, startIndex2 + length)
                    if (sub1 == sub2) {
                        return LongestCommonStrings(startIndex1, startIndex2, length)
                    }
                }
            }
        }
    }

    return null
}

fun findLongestCommonSubstring(string1: String, string2: String): LongestCommonStrings {
    if (string1.isEmpty() || string2.isEmpty()) {
        return LongestCommonStrings(0, 0, 0)
    }
    
    var bestAnswer = LongestCommonStrings(0, 0, 0)
    var start = 0
    var end = minOf(string1.length, string2.length)
    
    while (start <= end) {
        val mid = start + (end - start) / 2
        
        val result = checkForLength(string1, string2, mid)
        if (result != null) {
            bestAnswer = result
            // Found common substring of length mid, try longer
            start = mid + 1
        } else {
            // No common substring of length mid, try shorter
            end = mid - 1
        }
    }
    
    return bestAnswer
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val output = StringBuilder()
    
    while (true) {
        val line = reader.readLine() ?: break
        if (line.isBlank()) continue
        
        val parts = line.trim().split("\\s+".toRegex())
        if (parts.size < 2) continue
        
        val string1 = parts[0]
        val string2 = parts[1]
        
        val result = findLongestCommonSubstring(string1, string2)
        output.append("${result.startIndex1} ${result.startIndex2} ${result.length}\n")
    }
    
    print(output.toString())
}
