package coursera.ucSanDiego.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
 *
 * Reference:
 * 1. res/coursera/ucSanDiego/module06DynamicProgramming02/02equalSumPartition/02equalSumPartition.png
 * 2. res/coursera/ucSanDiego/module06DynamicProgramming02/02equalSumPartition/03equalSumPartitionMathSimplification.png
 *
 * 3-Partition Problem:
 *
 * Partition a set of integers into three subsets with equal sums.
 *
 * Input:
 *
 * A sequence of integers v_1,v_2,...,v_n.
 *
 * Output:
 *
 * Check whether it is possible to partition them into three subsets with equal sums, i.e.,
 * check whether there exist three disjoint sets S1,S2,S3 ⊆ {1,2,...,n}
 * such that S1 ∪ S2 ∪ S3 = {1,2,...,n}
 * and
 *
 * ```
 *  __                        __                        __
 * \                  V   =  \                  V   =  \                  V
 * /__ i epsilon  S    i     /__ i epsilon  S    j     /__ i epsilon  S    k
 *                 1                         2                         3
 * ```
 *
 * How did I use mathematical expressions, and greek symbols?
 * 1. Use [An online latex editor](https://www.sciweavers.org/free-online-latex-equation-editor#).
 *
 *      1.1 We can select an image, adjust the symbols, syntax, notation, values, etc.
 *      1.2 Generate the image.
 *      1.3 We also get a code-comment!
 *
 * 2. Use OCR, paste the text here, adjust the format, space, indents, etc., cover it inside the code format, etc.
 *
 * ```
 *
 *   ∑      V_i     =       ∑      V_j      =       ∑      V_k
 * i ∈ S_1                i ∈ S_2                 i ∈ S_3
 *
 * ```
 *
 * Three pirates are splitting their loot consisting of n items of varying value.
 * Can you help them to evenly split the loot?
 *
 * Input format:
 *
 * The first line contains an integer n. The second line contains integers v1,v2,...,vn separated by spaces.
 *
 * Output format:
 *
 * Output 1, if it is possible to partition v1,v2,...,vn into three subsets with equal sums, and 0 otherwise.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 20, 1 ≤ vi ≤ 30 for all i.
 *
 * Sample 1:
 *
 * Input:
 *
 * 4
 *
 * 3 3 3 3
 *
 * Output:
 *
 * 0
 *
 * Sample 2:
 *
 * Input:
 *
 * 1
 *
 * 30
 *
 * Output:
 *
 * 0
 *
 * Sample 3:
 *
 * Input:
 *
 * 13
 *
 * 1 2 3 4 5 5 7 7 8 10 12 19 25
 *
 * Output:
 *
 * 1
 *
 * Because: 1 + 3 + 7 + 25 = 2 + 4 + 5 + 7 + 8 + 10 = 5 + 12 + 19.
 *
 *
 *
 *
 */
fun main() {

}