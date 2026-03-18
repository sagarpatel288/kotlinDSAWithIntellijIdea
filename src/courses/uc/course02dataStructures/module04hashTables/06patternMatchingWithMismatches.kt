package courses.uc.course02dataStructures.module04hashTables

/**
 * # Pattern matching with mismatches
 *
 * ## Problem Introduction
 *
 * * A natural generalization of the pattern matching problem is the following:
 * * Find all text locations where the distance from the pattern is sufficiently small.
 * * This problem has applications in text searching (where mismatches correspond to typos)
 * and bioinformatics (where mismatches correspond to mutations).
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * For an integer parameter `k` and two strings `t = t0t1 ···tm-1` and `p = p0p1 ··· pn-1`,
 * we say that `p` occurs in `t` at position `i` with at most `k` mismatches if the strings
 * `p` and `t[i : i + p) = titi+1 ···ti+n-1` differ in at most k positions.
 *
 * ### Input Format
 *
 * * Every line of the input contains an integer `k` and two strings `t` and `p` consisting of lower case Latin letters.
 *
 * ### Constraints
 * ```
 * 0 <= k <= 5, 1 <= |t| <= 200 000, 1 <= |p| <= min{|t|, 100 000}
 * ```
 * * The total length of all t’s does not exceed 200 000, the total length of all p’s does not exceed 100 000.
 *
 * ### Output Format
 *
 * * For each triple `(k, t, p)`, find all positions 0 < i1 < i2 < ··· < il < |t| where p occurs in t
 * with at most k mismatches. Output l and i1, i2,...,il.
 *
 * ### Time Limits
 *
 * C: 2 sec, C++: 2 sec, Java: 5 sec, Python: 40 sec. C#: 3 sec, Haskell: 4 sec, JavaScript: 10 sec, Ruby: 10 sec,
 * Scala: 10 sec.
 *
 * ### Memory Limit
 * 512MB.
 *
 * ### Sample 1
 *
 * #### Input:
 * ```
 * 0 ababab baaa
 * 1 ababab baaa
 * 1 xabcabc ccc
 * 2 xabcabc ccc
 * 3 aaa xxx
 * ```
 *
 * #### Output:
 * ```
 * 0
 * 1 1
 * 0
 * 4 1 2 3 4
 * 1 0
 * ```
 *
 * #### Explanation:
 *
 * * For the first triple, there are no exact matches.
 * * For the second triple, `baaa` has distance one from the pattern.
 * * For the third triple, there are no occurrences with at most one mismatch.
 * * For the fourth triple, any (length three) substring of `p` containing at least one `c` has distance at most two
 * from `t`.
 * * For the fifth triple, `t` and `p` differ in three positions.
 *
 * ## Solution
 *
 * * [Local: Pattern Matching With Mismatches](docs/dataStructures/courses/uc/module04hashTables/53patternMatchingWithMismatches.md)
 *
 * * [GitHub: Pattern Matching With Mismatches](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/c6b76cd2b64002e0997ac9e95f25af3546f9e03b/docs/dataStructures/courses/uc/module04hashTables/53patternMatchingWithMismatches.md)
 *
 * ## Time Complexity
 *
 * * Precomputed prefix hashes for the text and the pattern: |T| + |P|
 * * Binary search: log(|P|)
 * * The binary search runs for: |T| Times (But exits as long as mismatches > k. So, runs for `k + 1` times.)
 * * Binary search total time: |T| * k * log(|P|) (Took `k` from `k + 1`, ignoring the constant `+ 1`).
 * * Hence, the total time becomes:
 * ```
 * = Precomputation cost + Main loop cost
 * = (|T| + |P|) + ( |T| * k * log(|P|) )
 * ```
 * * Now, compared to the precomputation cost, the main loop cost is higher due to the multiplicative nature.
 * ```
 * |T| * k * log(|P|) >> |T|
 * ```
 * * Similarly:
 * ```
 * |T| * k * log(|P|) >> |P|, given that |T| >= |P|
 * ```
 * * Hence, the dominant term is:
 * ```
 * O( |T| * k * log(|P|) )
 * ```
 * * Now:
 * * As per the problem statement, `k <= 5`, which makes `k` a small constant.
 * * In that case, the effective total time becomes:
 * ```
 * O(|T| * log(|P|))
 * ```
 *
 * ## Space Complexity
 *
 * * We use multiple arrays of size: |T| and |P| for the precomputation.
 * * Hence, the auxiliary space is: O(|T| + |P|).
 *
 * ## Grader output
 * ```
 * Good job! (Max time used: 2.50/5.00, max memory used: 147873792/536870912.)
 * ```
 */
class PatternMatchingWithMismatches(private val text: String, private val pattern: String) {
    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_079L
    private val xBase = 263L
    private val textHashes1 = LongArray(text.length + 1)
    private val textHashes2 = LongArray(text.length + 1)
    private val patternHashes1 = LongArray(pattern.length + 1)
    private val patternHashes2 = LongArray(pattern.length + 1)
    private val xPowersText1 = LongArray(text.length + 1)
    // We don't need an extra power table for the text or pattern
    // One power table for the text, and one power table for the pattern is enough
    private val xPowersText2 = LongArray(text.length + 1)
    private val xPowersPattern1 = LongArray(pattern.length + 1)
    // We don't need an extra power table for the text or pattern
    // One power table for the text, and one power table for the pattern is enough
    private val xPowersPattern2 = LongArray(pattern.length + 1)

    init {
        textHashes1[0] = 0L
        textHashes2[0] = 0L
        patternHashes1[0] = 0L
        patternHashes2[0] = 0L
        xPowersText1[0] = 1L
        xPowersText2[0] = 1L
        xPowersPattern1[0] = 1L
        xPowersPattern2[0] = 1L

        for (i in 0 until text.length) {
            textHashes1[i + 1] = ((textHashes1[i] * xBase) + text[i].code.toLong()) % prime1
            textHashes2[i + 1] = ((textHashes2[i] * xBase) + text[i].code.toLong()) % prime2
        }

        for (i in 0 until pattern.length) {
            patternHashes1[i + 1] = ((patternHashes1[i] * xBase) + pattern[i].code.toLong()) % prime1
            patternHashes2[i + 1] = ((patternHashes2[i] * xBase) + pattern[i].code.toLong()) % prime2
        }

        for (i in 1 until text.length) {
            xPowersText1[i] = (xPowersText1[i - 1] * xBase) % prime1
            xPowersText2[i] = (xPowersText2[i - 1] * xBase) % prime2
        }

        for (i in 1 until pattern.length) {
            xPowersPattern1[i] = (xPowersPattern1[i - 1] * xBase) % prime1
            xPowersPattern2[i] = (xPowersPattern2[i - 1] * xBase) % prime2
        }
    }

    private fun textHashes(startingIndex: Int, length: Int): Pair<Long, Long> {
        if (length <= 0) return 0L to 0L
        val long1 = textHashes1[startingIndex + length]
        val short1 = textHashes1[startingIndex]
        val sub1 = (short1 * xPowersText1[length]) % prime1
        var hash1 = (long1 - sub1) % prime1
        hash1 = (hash1 % prime1 + prime1) % prime1

        val long2 = textHashes2[startingIndex + length]
        val short2 = textHashes2[startingIndex]
        val sub2 = (short2 * xPowersText2[length]) % prime2
        var hash2 = (long2 - sub2) % prime2
        hash2 = (hash2 % prime2 + prime2) % prime2

        return hash1 to hash2
    }

    private fun patternHashes(startingIndex: Int, length: Int): Pair<Long, Long> {
        if (length <= 0) return 0L to 0L
        val long1 = patternHashes1[startingIndex + length]
        val short1 = patternHashes1[startingIndex]
        val sub1 = (short1 * xPowersPattern1[length]) % prime1
        var hash1 = (long1 - sub1) % prime1
        hash1 = (hash1 % prime1 + prime1) % prime1

        val long2 = patternHashes2[startingIndex + length]
        val short2 = patternHashes2[startingIndex]
        val sub2 = (short2 * xPowersPattern2[length]) % prime2
        var hash2 = (long2 - sub2) % prime2
        hash2 = (hash2 % prime2 + prime2) % prime2

        return hash1 to hash2
    }

    fun findPatternMatchingWithMismatches(kAllowedMismatches: Int): Pair<Int, List<Int>> {
        val startingIndices = mutableListOf<Int>()
        // Sliding Window
        // The starting index of the last window cannot go beyond: (text.length - pattern.length)
        for (i in 0..(text.length - pattern.length)) {
            // Text pointer (index)
            var t = i
            // Pattern pointer (index)
            var p = 0
            // Number of mismatches found
            var mismatches = 0
            while (p < pattern.length) {
                // Matching length for this window
                var matchLen = 0
                // We can take `minLength = 0` or `minLength = 1`, both works as long as `matchLen` is `0` before binary search
                var minLength = 1
                var maxLength = pattern.length - p
                while (minLength <= maxLength) {
                    println("i: $i, t: $t, p: $p")
                    val mid = minLength + (maxLength - minLength) / 2
                    println("before: start: $minLength, end: $maxLength, mid: $mid")
                    // Use the given sample inputs to understand the dry run
                    // Understand how it compares (the pattern) and how it moves ahead (proceeds)
                    println("text substring: ${text.substring(t, t + mid)} pattern: ${pattern.substring(p, p + mid)}")
                    val (textHash1, textHash2) = textHashes(t, mid)
                    val (patternHash1, patternHash2) = patternHashes(p, mid)
                    if (textHash1 == patternHash1 && textHash2 == patternHash2) {
                        // Update matched length
                        matchLen = mid
                        // Try a longer length
                        minLength = mid + 1
                    } else {
                        // Try a shorter length
                        maxLength = mid - 1
                    }
                    // Also print "start" and "end" to understand how it goes and when it stops
                    println("next: start: $minLength, end: $maxLength")
                }
                // End of the binary search
                // We know the longest common substring `matchLen`
                // Jump over the `matchLen`
                t += matchLen
                p += matchLen
                println("After matchLen: $matchLen t: $t and p: $p")
                if (mismatches <= kAllowedMismatches && p < pattern.length) {
                    // It is critical to check the position of `p` before we increase the `mismatches`
                    // Because if we increase `mismatches` when `p == pattern.length`, we get an extra mismatch.
                    mismatches++
                    println("After: mismatches: $mismatches")
                    if (mismatches > kAllowedMismatches) {
                        break
                    }
                    // Move past the mismatched character
                    t++
                    p++
                    println("After mismatches: $mismatches t: $t and p: $p")
                } else {
                    break
                }
            }
            // End of while (p < pattern.length)
            // We finished iterating over the pattern
            // Result of this text window that starts from the `i` index
            if (mismatches <= kAllowedMismatches) {
                startingIndices.add(i)
            }
        }
        return startingIndices.size to startingIndices
    }
}

fun main() {
    val lines = generateSequence { readLine()?.takeIf { it.isNotBlank() } }.toList()
    val results = mutableListOf<Pair<Int, List<Int>>>()
    for (line in lines) {
        val (kAllowedMismatches, text, pattern) = line.split(" ")
        val solver = PatternMatchingWithMismatches(text, pattern)
        val result = solver.findPatternMatchingWithMismatches(kAllowedMismatches.toInt())
        results.add(result)
    }
    val output = StringBuilder()
    for (result in results) {
        output.append(result.first).append(" ").append(result.second.joinToString(" ")).append("\n")
    }
    println(output.toString())
}

