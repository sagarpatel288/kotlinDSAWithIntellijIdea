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
 *
 * ### Thought Process
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
 */
fun main() {

}