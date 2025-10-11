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
 * * Precomputed prefix hashing
 * * Binary search for length
 * * Counting mismatches
 * * If matched lengths + counted mismatches = pattern length --> Maybe, we have found the answer and can exit.
 */
