package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * This is another solution of:
 * [Longest common subsequences of the two sequences with backtracking](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/4f64b4c49b327db93e3744c025c39c285c02a52f/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/100longestCommonSequencesOfTwoSequencesWithBacktracking.kt)
 *
 * However, in this solution, we cannot backtrack (reconstruct).
 * But, we improve the space complexity.
 * So, it is a trade-off between the space complexity and backtracking.
 *
 * In this particular version (type of the solution),
 * we trade off the backtracking for space complexity.
 *
 * The space optimization technique we use here, is based on:
 * [Edit distance space optimized without backtracking](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/0f74bf7c00a85fe749b4288d84c82ef0fe3763e7/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/080editDistanceSpaceOptimizedNoBacktracking.kt)
 *
 * So, instead of using a table (a 2D-array), we use two 1D-arrays.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * * Time Complexity:
 *
 * We still iterate over an array using two nested for loops,
 * where the size of the outer for loop is `n`,
 * and the size of the inner for loop is `m`.
 * Hence, the time complexity is `O(n * m)`.
 *
 * * Space Complexity:
 *
 * Instead of taking a table (a 2D-array) of size `(n + 1)(m + 1)`,
 * we take two 1D-arrays of size `n` or `m` (whichever is minimum).
 * Hence, we effectively reduce the space complexity.
 * The space complexity is `O(minOf(n, m))` which is either `O(n)` or `O(m)`, whichever is minimum.
 *
 * # ----------------------- Coursera's Grader Output -----------------------
 *
 * Good job! (Max time used: 0.10/2.00, max memory used: 46112768/536870912.).
 */
fun main() {

    fun longestCommonSubsequencesOfTheTwoSequences(refList: List<Int>, targetList: List<Int>): Int {
        val (shorterList, longerList) = if (refList.size > targetList.size) {
            targetList to refList
        } else {
            refList to targetList
        }
        var prevRow = IntArray(shorterList.size + 1)
        var currRow = IntArray(shorterList.size + 1)
        for (i in 0..prevRow.lastIndex) {
            prevRow[i] = 0
        }
        for (i in 1..longerList.size) {
            currRow[0] = 0
            for (j in 1..shorterList.size) {
                if (longerList[i - 1] == shorterList[j - 1]) {
                    currRow[j] = 1 + prevRow[j - 1]
                } else {
                    val prevRowEle = prevRow[j]
                    val prevEle = currRow[j - 1]
                    currRow[j] = maxOf(prevRowEle, prevEle)
                }
            }
            val temp = prevRow
            prevRow = currRow
            currRow = temp
        }
        return prevRow.last()
    }

    val refSize = readln().toInt()
    val refList = readln().split(" ").map { it.toInt() }
    val targetSize = readln().toInt()
    val targetList = readln().split(" ").map { it.toInt() }
    println(longestCommonSubsequencesOfTheTwoSequences(refList, targetList))
}