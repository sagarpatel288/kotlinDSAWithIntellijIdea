package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

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
 * # ----------------------- Grader Output -----------------------
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

        // We can replace this first `for loop` with default initialization to `0` for the `prevRow`.
        // When one of the strings is empty.
        // Visualize: We are filling the first row.
        // The columns keeps changing (incrementally and horizontal) to fill each cell of the first row.
        // This is the base case where we are filling the very first entire row, where the longer string is empty.
        // This is the 0th row.
        //
        // | 0 | 0 | S | U | N |
        // |---|---|---|---|---|
        // | 0 | 0 | 0 | 0 | 0 | <-------- This is what we are filling at the moment from left to right.
        // | S |   |   |   |   |
        // | H |   |   |   |   |
        // | I |   |   |   |   |
        // | N |   |   |   |   |
        // | E |   |   |   |   |
        //
        // In this implementation, `j` represents the columns. (Check the inner loop to understand.)
        // The pointer `j` moves from left-to-right to fill the columns for each row.
        // So, to align with it, we can take `j` instead of `i` to keep it easy to understand and relevant.
        //
        for (j in 0..prevRow.lastIndex) {
            prevRow[j] = 0
        }

        for (i in 1..longerList.size) {
            // We don't have to assign the value `0` if we initialize the `currRow` with the default value `0`.
            // curr[0] = (r_i, 0)
            // Here, one of the two strings is empty.
            // 0th column indicates that the string is empty.
            // Hence, there will be no matching!
            // That's why:
            // curr[0] = (r_i, 0)
            // curr[0] fills the 0th column for each row, only one cell at a time, followed by the inner loop.
            //       |
            //       |
            //       |
            //       |
            //       V
            // | 0 | 0 | S | U | N |
            // |---|---|---|---|---|
            // | 0 | 0 | 0 | 0 | 0 |
            // | S | 0 | <---------- Later, the inner loop fills the remaining columns of the row from left to right.
            // | H |   | <----------
            // | I |   | <----------
            // | N |   | <----------
            // | E |   | <----------
            //
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