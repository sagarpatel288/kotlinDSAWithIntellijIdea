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
