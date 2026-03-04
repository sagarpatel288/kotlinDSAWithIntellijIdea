package courses.uc.course02dataStructures.module04hashTables

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
 * ## Grader Output
 *
 * * It passed once, and then it started failing again!
 *
 * ```
 * Good job! (Max time used: 2.27/5.40, max memory used: 201785344/536870912.)
 * ```
 *
 */
class LongestCommonSubstring03(private val string1: String, private val string2: String) {

    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_263L
    private val base1 = 31L
    private val base2 = 263L
    private val hashesA1 = LongArray(string1.length + 1)
    private val hashesA2 = LongArray(string1.length + 1)
    private val hashesB1 = LongArray(string2.length + 1)
    private val hashesB2 = LongArray(string2.length + 1)
    private val basePowersA1 = LongArray(string1.length + 1)
    private val basePowersA2 = LongArray(string1.length + 1)
    private val basePowersB1 = LongArray(string2.length + 1)
    private val basePowersB2 = LongArray(string2.length + 1)

    init {
        hashesA1[0] = 0L
        hashesA2[0] = 0L
        hashesB1[0] = 0L
        hashesB2[0] = 0L
        basePowersA1[0] = 1L
        basePowersA2[0] = 1L
        basePowersB1[0] = 1L
        basePowersB2[0] = 1L

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        // Uses the Horner's method to calculate the hash codes.
        for (i in 0 until string1.length) {
            // Finding the hash of the first "i" characters.
            // To find the hash of the current length, we use the hash of the previous length.
            hashesA1[i + 1] = (hashesA1[i] * base1) % prime1
            hashesA1[i + 1] = ((hashesA1[i + 1]) + string1[i].code.toLong()) % prime1

            hashesA2[i + 1] = (hashesA2[i] * base2) % prime2
            hashesA2[i + 1] = (hashesA2[i + 1] + string1[i].code.toLong()) % prime2

            basePowersA1[i + 1] = (basePowersA1[i] * base1) % prime1
            basePowersA2[i + 1] = (basePowersA2[i] * base2) % prime2
        }

        // Calculate the hash codes of each string, starting from length 1 up to the entire length.
        // Uses the Horner's method to calculate the hash codes.
        for (i in 0 until string2.length) {
            // Finding the hash of the first "i" characters.
            // To find the hash of the current length, we use the hash of the previous length.
            hashesB1[i + 1] = (hashesB1[i] * base1) % prime1
            hashesB1[i + 1] = ((hashesB1[i + 1]) + string2[i].code.toLong()) % prime1

            hashesB2[i + 1] = (hashesB2[i] * base2) % prime2
            hashesB2[i + 1] = (hashesB2[i + 1] + string2[i].code.toLong()) % prime2

            basePowersB1[i + 1] = (basePowersB1[i] * base1) % prime1
            basePowersB2[i + 1] = (basePowersB2[i] * base2) % prime2
        }
    }

    fun checkForLength(length: Int): Triple<Int, Int, Int>? {
        if (length > string1.length || length > string2.length) return null
        if (length == 0) return Triple(0, 0, 0)
        val hashes1 = mutableMapOf<Long, Int>()
        // Uses the prefix hashes to find the hash of the substring of any length.
        // The length of the substring is fixed, but the starting index of the substring is variable.
        // So, we find all the substrings of the given length and store their hash codes.
        // We start from the index 0 and perform the sliding window technique (rolling hash).
        // But instead of calculating the hash using the Rabin-Karp algorithm, we use the prefix hashes.
        // We can also use the Rabin-Karp algorithm, and avoid using the prefix hashes.
        for (i in 0..string1.length - length) {
            val long1 = hashesA1[i + length]
            val short1 = hashesA1[i]
            val base1 = basePowersA1[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashesA2[i + length]
            val short2 = hashesA2[i]
            val base2 = basePowersA2[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            // Packing two 31-bit hashes into one 64-bit Long.
            val combined = (hash1 shl 32) or hash2
            // It is impossible to have the same key for the two different strings that have two different lengths.
            hashes1[combined] = i
        }

        for (i in 0..string2.length - length) {
            val long1 = hashesB1[i + length]
            val short1 = hashesB1[i]
            val base1 = basePowersB1[length]
            val sub1 = (short1 * base1) % prime1
            var hash1 = (long1 - sub1) % prime1
            hash1 = (hash1 % prime1 + prime1) % prime1

            val long2 = hashesB2[i + length]
            val short2 = hashesB2[i]
            val base2 = basePowersB2[length]
            val sub2 = (short2 * base2) % prime2
            var hash2 = (long2 - sub2) % prime2
            hash2 = (hash2 % prime2 + prime2) % prime2

            // Packing two 31-bit hashes into one 64-bit Long
            val combined = (hash1 shl 32) or hash2

            if (hashes1.containsKey(combined)) {
                val startIndex1 = hashes1[combined]!!
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