package coursera.ucSanDiego.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ## ----------------------- Problem Statement -----------------------
 *
 * Longest Common Subsequence of Three Sequences Problem:
 *
 * Compute the maximum length of a common subsequence of three sequences.
 *
 * Input:
 *
 * Three sequences.
 *
 * Output:
 *
 * The maximum length of a common subsequence.
 *
 * Note: The underscore symbol (`_`) indicates a subscript.
 *
 * Given three sequences `A= (a_1,a_2,...,a_n), B= (b_1,b_2,...,b_m), and C=(c_1,c_2,...,c_l)`,
 * find the length of their longest common subsequence,
 * i.e., the largest non-negative integer `p` such that there exist indices:
 *
 * ```
 * 1 ≤ i_1 < i_2 <···< i_p ≤ n,
 * 1 ≤ j_1 < j_2 <···< j_p ≤ m,
 * 1 ≤ k_1 < k_2 <···< k_p ≤l
 * ```
 * such that:
 *
 * ```
 * a_i1 = b_j1 = c_k1 ,
 * a_i2 = b_j2 = c_k2 ,
 * a_i3 = b_j3 = c_k3 ,
 * ...
 * a_ip = b_jp = c_kp
 * ```
 *
 * Input format.
 *
 * ```
 * First line: n.
 *
 * Second line: a_1,a_2,...,a_n.
 *
 * Third line: m.
 *
 * Fourth line: b_1,b_2,...,b_m.
 *
 * Fifth line: l.
 *
 * Sixth line: c_1,c_2,...,c_l.
 * ```
 *
 * Output format: `p`.
 *
 * Constraints:
 *
 * ```
 * 1 ≤ n,m,l ≤ 100;
 * −10^9 ≤ a_i, b_i, c_i ≤ 10^9.
 * ```
 *
 * `Sample 1`:
 *
 * Input:
 *
 * ```
 * 3
 * 1 2 3
 * 3
 * 2 1 3
 * 3
 * 1 3 5
 * ```
 *
 * `Output:`
 *
 * ```
 * 2
 * ```
 *
 * * A common subsequence of length 2 is (1,3).
 *
 * `Sample 2`:
 *
 * Input:
 *
 * ```
 * 5
 * 8 3 2 1 7
 * 7
 * 8 2 1 3 8 10 7
 * 6
 * 6 8 3 1 4 7
 * ```
 *
 * Output:
 *
 * ```
 * 3
 * ```
 *
 * * One common subsequence of length 3 in this case is (8,3,7). Another one is (8,1,7).
 *
 * ## ----------------------- Reference / Prerequisite -----------------------
 *
 * We have already seen the longest common subsequences of the two sequences.
 * [LCS of Two sequences](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/512c53d284b73b51f009da7e5a1438de732fa5d8/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/090longestCommonSubsequenceOfTwoSequencesStrings.kt)
 *
 * The current problem, LCS of Three sequences is almost the same.
 *
 * ## ----------------------- Complexity Analysis -----------------------
 *
 * * Time Complexity:
 * We iterate through 3 nested for-loops of size l, m, and n,
 * where `l` is the size of the first sequence,
 * `m` is the size of the second sequence,
 * and `n` is the size of the third sequence.
 * Hence, the time complexity is `O(l * m * n)`.
 *
 * * Space Complexity:
 * We create a table of size `(l + 1)(m + 1)(n + 1)`.
 * We do not count constants during the complexity analysis.
 * Hence, the space complexity is `O(l * m * n)`.
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 *
 * Good job! (Max time used: 0.19/2.00, max memory used: 49692672/536870912.)
 */
fun main() {

    fun lcs(sequenceListOne: List<Int>, sequenceListTwo: List<Int>, sequenceListThree: List<Int>): Int {
        val table = Array(sequenceListOne.size + 1) {
            Array(sequenceListTwo.size + 1) {
                Array(sequenceListThree.size + 1) {
                    0
                }
            }
        }
        for (i in 1..sequenceListOne.size) {
            for (j in 1..sequenceListTwo.size) {
                for (k in 1..sequenceListThree.size) {
                    if (sequenceListOne[i - 1] == sequenceListTwo[j - 1]
                        && sequenceListTwo[j - 1] == sequenceListThree[k - 1]
                    ) {
                        table[i][j][k] = 1 + table[i - 1][j - 1][k - 1]
                    } else {
                        val optionOne = table[i - 1][j][k]
                        val optionTwo = table[i][j - 1][k]
                        val optionThree = table[i][j][k - 1]
                        table[i][j][k] = maxOf(optionOne, optionTwo, optionThree)
                    }
                }
            }
        }
        return table[sequenceListOne.size][sequenceListTwo.size][sequenceListThree.size]
    }

    val totalFirstSequences = readln().toInt()
    val firstSequenceList = readln().split(" ").map { it.toInt() }
    val totalSecondSequences = readln().toInt()
    val secondSequenceList = readln().split(" ").map { it.toInt() }
    val totalThirdSequences = readln().toInt()
    val thirdSequenceList = readln().split(" ").map { it.toInt() }
    println(lcs(firstSequenceList, secondSequenceList, thirdSequenceList))
}