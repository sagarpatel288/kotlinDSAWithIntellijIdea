package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- History -----------------------
 *
 * Based on the `Bellman`'s DP theory, James Hunt and Douglas Mcilroy gave LCS-like approach for their `diff` program.
 *
 * ## ----------------------- How to remember? -----------------------
 *
 * 1. Bellman gave DP theory.
 * 2. Using that DP theory, James Hunt and Douglas Mcilroy developed the `diff` program.
 * 3. Using that `diff` program, we got the `LCS` topic.
 *
 * Bellman --> DP --> James Hunt & Douglas Mcilroy --> Diff --> LCS
 *
 * ### ----------------------- Story -----------------------
 *
 * 1. Imagine a `Bellman` who has got a `bell`.
 * 2. The name of the `bell` is `DP`.
 * 3. Whenever the Bellman rings the `DP` bell, James Hunt starts `hunting` for the `Doug` the `diff` boy.
 * 4. James Hunt and Doug Mcilroy together gave the hidden `Diff` treasure.
 * 5. Using the `Diff` treasure, we got the `LCS` machine (inspiration, idea).
 *
 * # ----------------------- Formulas -----------------------
 *
 * According to the `Diff` program, suppose we have two strings.
 * Similar to the last [Edit Distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d54335c0d51925e1ef9183c4ba7107f3d40b66d9/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 * problem, each character of one string represents rows, and each character of another string represents columns.
 *
 * So, it is a 2D-table.
 * At this moment, if the length of one string is `n`, and the length of the other string is `m`,
 * then the length of the table is `n * m`.
 *
 * However, we start with the case where both the strings are empty.
 * So, we add one more row, and one more column in the beginning.
 * Hence, the size of the table becomes `(n + 1)(m + 1)`.
 *
 * Which signifies the below table:
 *
 * |   i / j   	| 0 (j = 0) 	| s (j = 1) 	| u (j = 2) 	| n (j = 3) 	|
 * |:---------:	|:---------:	|:---------:	|:---------:	|:---------:	|
 * | 0 (i = 0) 	|     0     	|     0     	|     0     	|     0     	|
 * | s (i = 1) 	|     0     	|   (i, j)  	|           	|           	|
 * | h (i = 2) 	|     0     	|           	|           	|           	|
 * | i (i = 3) 	|     0     	|           	|           	|           	|
 * | n (i = 4) 	|     0     	|           	|           	|           	|
 * | e (i = 5) 	|     0     	|           	|           	|           	|
 *
 * The `0th row` signifies what will be the match (LCS) if the `rowString` is empty.
 * If the `rowString` is empty, there will be no match. Hence, each cell of the `0th row` has value `0`.
 *
 * This signifies the below code:
 *
 * ```
 * // When the `rowString` is empty.
 * for (i in 0..columnString) {
 *     cell[0][j] = 0
 * }
 * ```
 *
 * Similarly, the 0th column signifies what will be the match (LCS) if the columnString is empty.
 * If the `columnString` is empty, there will be no match. Hence, each cell of the `0th column` has value `0`.
 *
 * This signifies the below code:
 *
 * ```
 * // When the `columnString` is empty.
 * for (j in 0..rowString) {
 *     cell[i][0] = 0
 * }
 * ```
 *
 * Now, based on the `Diff` program of `James Hunt and Douglas Mcilroy`,
 * if the index pointer of a row is `i`, and the index pointer of a column is `j`,
 * then the value of the `cell (i, j)` is:
 *
 * ```
 * If the characters rowString[i - 1] == columnString[j - 1] match,
 * then the value of the cell[i][j] = 1 + cell[i - 1][j - 1].
 * Otherwise,
 * cell[i][j] = maxOf( cell[i - 1][j], cell[i][j - 1] )
 * ```
 *
 * Which signifies the below code:
 *
 * ```
 * if (rowString[i - 1] == columnString[j - 1]) {
 *    cell[i][j] = 1 + cell[i - 1][j - 1]
 * } else {
 *    cell[i][j] = maxOf( cell[i - 1][j], cell[i][j - 1] )
 * }
 * ```
 *
 * We compare each character of the `rowString` with each character of the `columnString`.
 * We start with the first row.
 * We put the index finger of the left-hand on the first row,
 * and put the index finger of the right-hand on the first column.
 * We move forward, move the index finger of the right-hand right side, column by column, and fill the entire first row.
 *
 * We do the same with each row.
 *
 * We start with the second row (i = 1) and the second column (j = 1),
 * because we have already covered the first row (i = 0), and the first column (j = 0).
 *
 * This process signifies two nested for loops where the outer loop represents the `rowString`,
 * and the inner loop represents the `columnString` as below:
 *
 * ```
 * for (i in 1..rowString) {
 *     for (j in 1..columnString) {
 *         // Comparison code
 *         ...
 *     }
 * }
 * ```
 *
 * The last cell of the table gives the length of the LCS - Longest common subsequence of two sequences (strings).
 * The size of the table is `(n + 1)(m + 1)`.
 * Hence, the last cell is: `cell[n][m]`.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * * Time Complexity:
 *
 * We use two nested for loop to iterate a table of size `(n + 1)(m + 1)`.
 * We ignore the constants in the complexity analysis.
 * So, the time complexity is `O(n * m)`.
 *
 * * Space Complexity:
 *
 * We use a 2D-array (a table) of size `(n + 1)(m + 1)`.
 * We ignore the constants in the complexity analysis.
 * Hence, the space complexity is `O(n * m)`.
 */
fun main() {

}