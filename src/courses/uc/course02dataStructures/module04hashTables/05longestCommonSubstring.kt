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
 * * The longest common substring of the first pair of strings is `ool`, it starts at the first position in toolbox
 * and at the first position in cool.
 * * The strings from the second line do not share any non-empty common substrings
 * (in this case, 𝑙 = 0 and one may output any indices 𝑖 and 𝑗).
 * * Finally, the last two strings share a substring `aab` that has length `3` and starts at position `0` in the first
 * string and at position `4` in the second one.
 * * Note that for this pair of strings, one may output `2 3 3` as well.
 *
 *
 *
 */
fun main() {

}