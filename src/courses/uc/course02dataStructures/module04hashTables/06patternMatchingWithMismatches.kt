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
 * ### Intuition
 *
 * **How do we naively compare two strings?**
 * **Pseudocode (core part) of naive implementation**
 *
 * * We compare two strings character by character.
 * * So, it looks like below:
 *
 * ```
 * // This outer loop increments the pointer of the text string
 * for (t in 0 until text.length) {
 *     // This inner loop increments the pointer of the pattern string
 *     for (p in 0 until pattern.length) {
 *         // Comparing characters
 *         if (text[t] != pattern[p]) {
 *             // With each mismatch, we increase the "mismatch" counter
 *             mismatch++
 *         } else {
 *             // If it is a match, we increment the "matchLen" counter
 *             matchLen++
 *         }
 *         if (mismatch > k) {
 *             // We crossed the maximum allowed mismatches
 *             matchLen = 0
 *             break
 *         }
 *     }
 *     // Result based on the mismatch counter
 *     if (mismatch <= k) {
 *         result.add(t) // The text index from where the pattern matching with allowed mismatches starts
 *     }
 * }
 * ```
 *
 * **String comparison using binary search**
 *
 * * Now, in the `longest common substring` problem, we have learned that the `binary search` helps string comparison.
 * * The idea is, instead of comparing character by character, we check characters in bulk.
 * * And `Characters in bulk` means substrings.
 * * And when we want to compare substrings, we use hash codes.
 * * So, the idea is:
 * * We use precomputed prefix hashing for the text string and the pattern string.
 * * The binary search gives us a length.
 * * We use the following formula to compare the substrings:
 *
 * ```
 * H(a, l) = [ H(a + l) - { H(a) * x^l } ] % prime
 * ```
 *
 * * And to reduce the collisions, we can use double hashing.
 * * Let us add a pinch of binary search and precomputed prefix hashing mixed with double hashing.
 *
 * **Precomputed prefix double hashing**
 * ```
 * val prime1 = 1_000_000_007L
 * val prime2 = 1_000_000_079L
 * val xBase = 263L
 * val xTextPowers1 = LongArray(text.length + 1)
 * val xTextPowers2 = LongArray(text.length + 1)
 * val xPatternPowers1 = LongArray(pattern.length + 1)
 * val xPatternPowers2 = LongArray(pattern.length + 1)
 *
 * textHashes1[0] = 0L
 * textHashes2[0] = 0L
 * patternHashes1[0] = 0L
 * pattenHashes2[0] = 0L
 * xTextPowers1[0] = 1L
 * xTextPowers2[0] = 1L
 * xPatternPowers1[0] = 1L
 * xPatternPowers2[0] = 1L
 *
 * for (i in 1 until text.length) {
 *     xTextPowers1[i] = (xTextPowers1[i - 1] * xBase) % prime1
 *     xTextPowers2[i] = (xTextPowers2[i - 1] * xBase) % prime2
 * }
 *
 * for (i in 1 until pattern.length) {
 *     xPatternPowers1[i] = (xPatternPowers1[i - 1] * xBase) % prime1
 *     xPatternPowers2[i] = (xPatternPowers2[i - 1] * xBase) % prime2
 * }
 *
 * // Precomputed prefix double hashing of the text string (Given that pattern.length <= text.length)
 * for (i in 0..text.length - pattern.length) {
 *     textHashes1[i + 1] = ( ( textHashes1[i] * xBase ) + text[i].code.toLong() ) % prime1
 *     textHashes2[i + 1] = ( ( textHashes2[i] * xBase ) + text[i].code.toLong() ) % prime2
 * }
 *
 * // precomputed prefix double hashing of the patten string
 * for (i in 0 until pattern.length) {
 *     patternHashes1[i + 1] = ( (patternHashes1[i] * xBase) + pattern[i].code.toLong() ) % prime1
 *     patternHashes2[i + 1] = ( (patternHashes2[i] * xBase) + pattern[i].code.toLong() ) % prime2
 * }
 *
 * fun textHashes(startIndex: Int, length: Int): Pair<Long, Long> {
 *     val longHash1 = textHashes1[startIndex + length]
 *     val shortHash1 = textHashes1[startIndex]
 *     val sub1 = (shortHash1 * xTextPowers1[length]) % prime1
 *     val hash1 = (longHash1 - sub1) % prime1
 *
 *     val longHash2 = textHashes2[startIndex + length]
 *     val shortHash2 = textHashes2[startIndex]
 *     val sub2 = (shortHash2 * xTextPowers2[length]) % prime2
 *     val hash2 = (longHash2 - sub2) % prime2
 *
 *     return hash1 to hash2
 * }
 *
 * fun pattenHashes(startIndex: Int, length: Int): Pair<Long, Long> {
 *     val longHash1 = patternHashes1[startIndex + length]
 *     val shortHash1 = patternHashes1[startIndex]
 *     val sub1 = (shortHash1 * xPatternPowers1[length]) % prime1
 *     val hash1 = (longHash1 - sub1) % prime1
 *
 *     val longHash2 = patternHashes2[startIndex + length]
 *     val shortHash2 = patternHashes2[startIndex]
 *     val sub2 = (shortHash2 * xPatternPowers2[length]) % prime2
 *     val hash2 = (longHash2 - sub2) % prime2
 *
 *     return hash1 to hash2
 * }
 * ```
 *
 * **Binary Search**
 *
 * ```
 * val mid = start + (end - start) / 2
 * (hash1a, hash2a) = textHashes(t, mid)
 * (hash1b, hash2b) = patternHashes(p, mid)
 * if (hash1a == hash1b && hash2a == hash2b) {
 *     // See if the longer length matches
 *     start = mid + 1
 * } else {
 *     end = mid - 1
 * }
 * ```
 *
 * * A couple of reflective questions and observation here.
 *
 * **We have to stop the binary search at some point. How do we stop? What will be the condition?**
 *
 * * The condition will be: `while (start <= end) binarySearch`.
 * * Let us put this condition.
 *
 * ```
 * while (start <= end) {
 *     val mid = start + (end - start) / 2
 *     val (hash1a, hash2a) = textHashes(t, mid)
 *     val (hash1b, hash2b) = patternHashes(p, mid)
 *     if (hash1a == hash1b && hash2a == hash2b) {
 *         // See if the longer length matches
 *         start = mid + 1
 *     } else {
 *         // See if the shorter length matches
 *         end = mid - 1
 *     }
 * }
 * ```
 *
 * **And what about those two starting indices: t and p? How do we get them?**
 *
 * ```
 * // Did you understand the purpose of this outer for loop?
 * for (t in 0 until text.length) {
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < p.length) {
 *         // binary search
 *         var start = p
 *         var end = p.length - p // Did you understand this?
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * * Now, we need to add a couple of more things here.
 * * matchLen counter
 * * mismatches counter
 * * Starting index of the pattern matching with mismatches
 *
 * **Did you understand why we need to add them?**
 * **Starting index of the pattern matching (with allowed mismatches) in the text string**
 *
 * * We have been asked to provide the starting index of the pattern matching with mismatches.
 * * For example, if the text is `abcdef` and the pattern is `abx` with `k = 1`, then we need to output: `1 0`.
 * * In the output, `1` represents the total matched patterns.
 * * `0` represents the starting index in the text that matches the pattern (can use allowed mismatches).
 *
 * **matchLen counter = jump**
 *
 * *
 *
 * **mismatch counter**
 *
 * * We need to keep track of our wild card - the number of allowed mismatches.
 * * For example, in our previous example, where `text = abcdef` and `pattern = abx`, and `k = 1`.
 * * We have used `k = 1` to consider `c = x` in `abc = abx`.
 * * If `k` was `0`, and we find that `mismatches > k`, we can't consider `abc = abx`.
 * * In that case where `k = 0`, the pattern `abx` does not fit in the text `abcdef`.
 * * With every mismatch, we can use the allowed `mismatches` wildcard.
 *
 * **When, where, and how do we use the mismatch counter?**
 *
 * * How does the binary search work here?
 * * If we find a match, we increase the length to see if we can find a longer substring match.
 * * If we do not find a match, we decrease the length to see if we can find a shorter substring match.
 * * See, at some point, the binary search finishes.
 * * What does the end of the binary search indicate?
 * > It covered all the possible lengths (from 0 to pattern.length).
 *
 * * Now, it is possible that we don't find any match, and the binary search finishes.
 * * For example, the binary search would start with `mid (length) = 3`, then `2`, then `1`.
 * * What does that mean? It means that we need to move on.
 * * We tried binary search for a particular position `t` of the text string and `p` of the pattern string.
 * * Now, we need to try the next `t` position = `t++`, and the next `p` position = `p++`.
 * * However, if there was a match of any length, we can `jump` over that part.
 * * For example, let us assume that the text string is `abcdef` and the pattern is `abxy`.
 * * Now, the binary search can tell us that it could find the maximum `matchLen = 2`.
 * * So, we can perform `t = t + 2` and `p = p + 2`.
 * * Also, the `matchLen` value indicates that past this `matchLen`, there is a `mismatch.`
 *
 *
 * ### TL;DR
 *
 * * Precomputed prefix hashing
 * * Binary search for length
 * * Counting mismatches
 * * If matched lengths + counted mismatches = pattern length --> Maybe, we have found the answer and can exit.
 * * Otherwise, slide the window until we reach the end of the text string.
 */
fun main() {

}
