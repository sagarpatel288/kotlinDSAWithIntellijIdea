package courses.uc.course02dataStructures.module04hashTables

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * # Longest Common Substring
 *
 * ## Problem Introduction
 *
 * * In the longest common substring problem, one is given two strings `𝑠` and `𝑡` and the goal is to find a string `𝑤`
 * of maximal length that is a substring of both `𝑠` and `𝑡`.
 * * This is a natural measure of similarity between two strings.
 * * The problem has applications in text comparison and compression as well as in bioinformatics.
 * * The problem can be seen as a special case of the edit distance problem (where only insertions and
 * deletions are allowed).
 * * Hence, it can be solved in time `𝑂(|𝑠| · |𝑡|)` using dynamic programming.
 * * Later in this specialization, we will learn highly non-trivial data structures for solving this problem in linear
 * time `𝑂(|𝑠| + |𝑡|)`.
 * * In this problem, your goal is to use hashing to solve it in almost linear time.
 *
 * ## Problem Description
 *
 * ### Input Format
 *
 * * Every line of the input contains two strings `𝑠` and `𝑡` consisting of lower-case Latin letters.
 *
 * ### Constraints
 *
 * * The total length of all 𝑠’s as well as the total length of all 𝑡’s does not exceed 100_000.
 *
 * ### Output Format
 *
 * * For each pair of strings `𝑠` and `𝑡𝑖`, find its longest common substring and specify it by outputting three integers:
 * * Its starting position in `𝑠`, its starting position in `𝑡` (both 0-based), and its length.
 * * More formally, output integers `0 ≤ 𝑖 < |𝑠|, 0 ≤ 𝑗 < |𝑡|, and 𝑙 ≥ 0` such that:
 * `𝑠𝑖𝑠𝑖+1 · · · 𝑠𝑖+𝑙−1 = 𝑡𝑗 𝑡𝑗+1 · · ·𝑡𝑗+𝑙−1` and `𝑙` is maximal.
 * * (As usual, if there are many such triples with maximal 𝑙, output any of them.)
 *
 * ### Time Limits
 *
 * C: 2 sec, C++: 2 sec, Java: 5 sec, Python: 15 sec. C#: 3 sec, Haskell: 4 sec, JavaScript: 10 sec, Ruby: 10 sec,
 * Scala: 10 sec.
 *
 * ### Memory Limit
 * 512MB.
 *
 * ### Sample 1
 *
 * #### Input
 * ```
 * cool toolbox
 * aaa bb
 * aabaa babbaab
 * ```
 *
 * #### Output
 * ```
 * 1 1 3
 * 0 1 0
 * 0 4 3
 * ```
 *
 * #### Explanation
 *
 * * The longest common substring of the first pair of strings is `ool`, it starts at the first position in `toolbox`
 * and at the first position in `cool`.
 * * The strings from the second line do not share any non-empty common substrings
 * (in this case, 𝑙 = 0 and one may output any indices 𝑖 and 𝑗).
 * * Finally, the last two strings share a substring `aab` that has length `3` and starts at position `0` in the first
 * string and at position `4` in the second one.
 * * Note that for this pair of strings, one may output `2 3 3` as well.
 *
 * ## Solution
 *
 * ### Prerequisites
 *
 * * Binary Search
 * * HashTables (Set, Map)
 * * String Hashing
 * * Rolling Hashing
 * * Double Hashing
 *
 * ### Thought Process
 *
 * #### Binary Search For Length
 *
 * * Suppose the length of `s` is `sl` and the length of `t` is `tl`.
 * * Now, the maximum length of the common substring cannot be more than `minOf(sl, tl)`.
 * * So, any substring of length between `0 to minOf(sl, tl)` can be a longest common substring.
 * * But we don't need to process all the substrings of length from `0 to minOf(sl, tl)`.
 * * Because, if a substring of length `k` is common, then there must exist a common substring of length <= `k`.
 * * So, we don't need to check for any substrings whose length is less than or equal to `k`.
 * * Because we are interested in the "Longest common substring."
 * * For example, if `s = abcdefgh` and `t = cdefgh`.
 * * Now, we know that the longest common substring length can be between `0 to minOf(sl, tl) = 0 to 6`.
 * * Here, the lower-end is `0`, and the highest-end is `6`.
 * * Let us assume that we start checking with the middle point `3`. The upper bound `6` remains the same.
 * * We find that there exists a common substring of length `3`.
 * * `cde`, `def`, `efg`, and `fgh`.
 * * Now, we can clearly see that there are common substrings of length <= 3.
 * * `cd`, `de`, `ef`, `fg`, and `gh`.
 * * But we want to find the "Longest Common Substring".
 * * When we find that there exists a common substring of length `3`, we increase the bar.
 * * We ask: Can we have a common substring of length higher than 3?
 * * Can we have a common substring of length 4?
 * * We increase the lower end of the range.
 * * We get a new range.
 * * The new range becomes `4 to 6`.
 * * Here, the lower-end is `4` and the higher-end is `6`.
 * * Any substring of length between `k to minOf(sl, tl) = 4 to 6` can be a longest common substring.
 * * Similarly, if there was no common substring of length `3`, we would decrease the `higher-end` of the range.
 * * Because if there is no common substring of length `3`, then there cannot be a common substring of length > 3.
 * * In that case, the higher-end would decrease to `mid-1 = 3 - 1 = 2` and the lower bound `0` remains the same.
 * * So, in that case, the range would have become `0 to 2`.
 *
 * #### Hash Code
 *
 * * The binary search gives a specific length to the function that calculates a hash code.
 * * The hash function calculates the hash code of each substring of the given length of a specific string.
 * * The hash function uses double hashing and rolling hashing.
 * * The hash function gives two different hash codes and the starting index for each substring.
 * * So, the return type would be a collection that can hold a pair of hash codes as a key and the starting index of the substring as a value.
 * * We use this hash function for both strings and get the result for each string.
 * * We iterate through the pair of hash codes of one string and check if the result of another string also contains the same pair of hash codes.
 * * When both strings contain the same pair of hash codes, it means that we found a common substring.
 * * And the hash function also returns the starting index.
 * * And we also have the length that we give through the binary search.
 *
 */
private data class LongestCommonStrings(val startIndex1: Int, val startIndex2: Int, val length: Int)

private fun checkForLength(string1: String, string2: String, length: Int): LongestCommonSubStrings? {
    // Edge Cases
    if (length == 0) return LongestCommonSubStrings(0, 0, 0)
    if (length > string1.length || length > string2.length) return null

    fun getAllHashes(string: String, length: Int): Map<Pair<Long, Long>, Int> {
        val prime1 = 1_000_000_007L
        val prime2 = 1_000_000_061L
        val xBase1 = 31L
        val xBase2 = 263L
        val hashes = mutableMapOf<Pair<Long, Long>, Int>()
        var hash1 = 0L
        var hash2 = 0L
        var xPower1 = 1L
        var xPower2 = 1L

        // First window
        for (i in 0 until length) {
            hash1 = (hash1 * xBase1) % prime1
            hash1 = (hash1 + string[i].code.toLong()) % prime1
            hash2 = (hash2 * xBase2) % prime2
            hash2 = (hash2 + string[i].code.toLong()) % prime2
        }

        hash1 = (hash1 % prime1 + prime1) % prime1
        hash2 = (hash2 % prime2 + prime2) % prime2

        hashes[hash1 to hash2] = 0

        // Maximum degree (power) to use in the rolling hash
        for (i in 1 until length) {
            xPower1 = (xPower1 * xBase1) % prime1
            xPower2 = (xPower2 * xBase2) % prime2
        }

        // Rolling Hash
        for (i in 1..string.length - length) {
            val sub1 = (string[i - 1].code.toLong() * xPower1) % prime1
            val add1 = string[i + length - 1].code.toLong()
            hash1 = (hash1 - sub1 + prime1) % prime1
            hash1 = (hash1 * xBase1) % prime1
            hash1 = (hash1 + add1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val sub2 = (string[i - 1].code.toLong() * xPower2) % prime2
            val add2 = string[i + length - 1].code.toLong()
            hash2 = (hash2 - sub2 + prime2) % prime2
            hash2 = (hash2 * xBase2) % prime2
            hash2 = (hash2 + add2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            if (hashes.containsKey(hash1 to hash2)) {
                val startIndex = hashes[hash1 to hash2]!!
                if (string.substring(startIndex, startIndex + length) != string.substring(i, i + length)) {
                    hashes[hash1 to hash2] = i
                }
            } else {
                hashes[hash1 to hash2] = i
            }
        }

        return hashes
    }

    val hashes1 = getAllHashes(string1, length)
    val hashes2 = getAllHashes(string2, length)

    for ((hashCodePair, startIndex) in hashes1) {
        if (hashes2.containsKey(hashCodePair)) {
            val startIndex2 = hashes2[hashCodePair]!!
            if (string1.substring(startIndex, startIndex + length) == string2.substring(startIndex2, startIndex2 + length)) {
                return LongestCommonSubStrings(startIndex, startIndex2, length)
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
        var bestAnswer = LongestCommonSubStrings(0, 0, 0)
        if (string1.isNotEmpty() && string2.isNotEmpty()) {
            var start = 0
            var end = minOf(string1.length, string2.length)
            while (start <= end) {
                val mid = start + (end - start) / 2
                if (mid == 0) {
                    start = 1
                }
                val result = checkForLength(string1, string2, mid)
                if (result != null) {
                    bestAnswer = result
                    // Check for a longer length
                    start = mid + 1
                } else {
                    // Check for a shorter length
                    end = mid - 1
                }
            }
        }
        output.append("${bestAnswer.startIndex1} ${bestAnswer.startIndex2} ${bestAnswer.length}\n")
        line = reader.readLine()
    }
    println(output)
}