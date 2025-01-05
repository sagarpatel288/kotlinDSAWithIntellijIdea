package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * [Edit Distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/be74523bc7fb51c51d6418039bdc885dcadc55eb/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 * [Edit Distance with backtracking](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/be74523bc7fb51c51d6418039bdc885dcadc55eb/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt)
 * [Edit distance space optimized without backtracking image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/038340fcade8a36e29cdb2ea60e0d35faf054f69/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003editDistanceSpaceOptimized.png)
 *
 * # ----------------------- Explanation: -----------------------
 *
 * When we want to backtrack, we fill the entire table (the 2D array `operations`), and keep the record (save data) of
 * each cell.
 *
 * ```
 * operations[i][j] = some value
 * ```
 *
 * We store the value of each cell to the 2D array `operations` to reconstruct (backtrack) the solution.
 * We reconstruct (backtrack) the solution to explain which operations we took to get the answer (result).
 *
 * However, if we don't want to reconstruct (backtrack) the solution, we can optimize the space (memory) complexity.
 *
 * For example, if we look at the below image:
 * [Edit distance space optimized without backtracking image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/038340fcade8a36e29cdb2ea60e0d35faf054f69/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003editDistanceSpaceOptimized.png),
 * we can understand that a cell requires only 3 previous cells (1 immediate top neighbour, 1 immediate left neighbour,
 * and 1 immediate left-diagonal neighbour) to get a value for itself.
 *
 * |                        	| Previous Column  	| Current Column 	|
 * |------------------------	|------------------	|----------------	|
 * | Previous Row `(i - 1)` 	| `(i - 1, j - 1)` 	| `(i - 1)`      	|
 * | Current Row `(i)`      	| `(i, j - 1)`     	| `(i, j)`       	|
 *
 * In other words, a cell depends on only 3 immediate and previous cells.
 * The cell gets the value based on the immediate top neighbour, the immediate left neighbour,
 * or the immediate left-diagonal neighbour.
 *
 * So, for example, if we are at `(i, j)` cell, where `i` represents a row index, and `j` represents a column index,
 * then, the value of the `(i, j)` is based on:
 *
 * ```
 * 1. The immediate top (above, previous) neighbour => `(i - 1, j)` => Previous row, same column.
 * 2. The immediate left (previous) neighbour => `(i, j - 1)` => Same row, previous column.
 * 3. The immediate left-diagonal neighbour => `(i - 1, j - 1)` => Previous row, previous column.
 * ```
 *
 * So, to get the value of any cell, we need only three neighbour cells.
 * We don't need to store data for all the rows and columns, if we don't want to reconstruct (backtrack).
 *
 * In other words (and this is an interesting perspective), if we take a closer look at the table,
 * the `current cell (i)`, depends on the `(i) of the previous` row for the delete operation,
 * `i - 1 of the current` row for the insert operation, and `i - 1 of the previous` row for the substitute operation.
 *
 * | Previous Row 	| prev ( i - 1 ) 	| prev ( i ) 	|
 * |:------------:	|:--------------:	|:----------:	|
 * |  Current Row 	| curr ( i - 1 ) 	| curr ( i ) 	|
 *
 * If we try to map (convert) it into a coding (math) language, we might get something similar to the below block:
 *
 * ```
 * curr[i] == prev[i] OR curr[i - 1] OR prev[i - 1]
 * ```
 *
 * In this case, the previous and current are two independent 1D array. This is where we save the space complexity.
 *
 * Because, if we don't want to reconstruct (backtrack), and we don't need to store the value of each cell,
 * then we also don't need to take a huge 2D array `operations` of size `(n + 1)(m + 1)`, and this is where we save
 * (reduce) the space complexity.
 *
 * Instead of taking a 2D array `operations`, we can take two 1D arrays, called `prev`, and `current`.
 *
 * What will be the size of these 1D-arrays? The length of the shorter input.
 *
 * Why and how does it help? We save a few space (memory) when we take the shorter string as the `1D-array`.
 *
 * Let us understand this with an example.
 *
 * Suppose, we have two strings: `Sun` and `Shine`.
 *
 * We will see the difference of taking the longer string Vs. the shorter string as the 1D-array base.
 *
 * Let us first take the longer string `Shine` as our `1D-array`.
 *
 * The length of the longer string `shine` is `5`.
 *
 * So, the length of the `1D-array` will be `length + 1` = `5 + 1` = `6`.
 *
 * |  1-D Arrays  	| i / j 	| 0 	| s 	| h 	| i 	| n 	| e 	|
 * |:------------:	|:-----:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|
 * | Previous Row 	|   0   	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	|
 * |  Current Row 	|   s   	| 1 	| 0 	| 1 	| 2 	| 3 	| 4 	|
 * |              	|   u   	|   	|   	|   	|   	|   	|   	|
 * |              	|   n   	|   	|   	|   	|   	|   	|   	|
 *
 * Once we fill both the rows (both the 1-D arrays), the `current row` becomes the `previous row` as below:
 *
 * |  1-D Arrays  	| i / j 	| 0 	| s 	| h 	| i 	| n 	| e 	|
 * |:------------:	|:-----:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|
 * |              	|   0   	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	|
 * | Previous Row 	|   s   	| 1 	| 0 	| 1 	| 2 	| 3 	| 4 	|
 * |  Current Row 	|   u   	|   	|   	|   	|   	|   	|   	|
 * |              	|   n   	|   	|   	|   	|   	|   	|   	|
 *
 * We can see that, with each iteration, each row (array) fills `6 cells` (which is the length of the 1D array).
 *
 * Now, let us see the difference if we take the shorter string as the `1D array` base.
 *
 * The length of the shorter string `sun` is `3`.
 *
 * So, the length of the `1D-array` will be `length + 1` = `3 + 1` = `4`.
 *
 * |  1-D Arrays  	| i / j 	| 0 	| s 	| u 	| n 	|
 * |:------------:	|:-----:	|:-:	|:-:	|:-:	|:-:	|
 * | Previous Row 	|   0   	| 0 	| 1 	| 2 	| 3 	|
 * |  Current Row 	|   s   	| 1 	| 0 	| 1 	| 2 	|
 * |              	|   h   	|   	|   	|   	|   	|
 * |              	|   i   	|   	|   	|   	|   	|
 * |              	|   n   	|   	|   	|   	|   	|
 * |              	|   e   	|   	|   	|   	|   	|
 *
 * Once we fill both the rows (both the 1D-arrays), the `current row` becomes the `previous row` as below:
 *
 * |  1-D Arrays  	| i / j 	| 0 	| s 	| u 	| n 	|
 * |:------------:	|:-----:	|:-:	|:-:	|:-:	|:-:	|
 * |              	|   0   	| 0 	| 1 	| 2 	| 3 	|
 * | Previous Row 	|   s   	| 1 	| 0 	| 1 	| 2 	|
 * |  Current Row 	|   h   	|   	|   	|   	|   	|
 * |              	|   i   	|   	|   	|   	|   	|
 * |              	|   n   	|   	|   	|   	|   	|
 * |              	|   e   	|   	|   	|   	|   	|
 *
 * We can see that, with each iteration, each row (array) fills `4 cells` instead of `6 cells`.
 * So, this is how we reduce the space complexity.
 *
 * To take two 1D-arrays (to store the number of operations):
 *
 * ```
 * val prev = IntArray (shorterLength + 1)
 * val curr = IntArray (shorterLength + 1)
 * ```
 *
 * When the reference string is empty, the number of operations is equal to the length of the target.
 *
 * ```
 * for (i in 0..targetLength) {
 *     // The first row at 0th index.
 *     // When one string is empty, the number of operations is equal to the length of the other string.
 *     prev[i] = i
 * }
 * ```
 *
 * Now, we fill the `curr` row (array). And, it depends on the `prev` row (array).
 * For each row, we iterate up to `shorter string length + 1`. (Move horizontally. Cover each column. Column by column.)
 * The number of rows are equal to the `longer string length + 1`. (Move Vertically. Cover each row. Row by row.)
 *
 * We have two iterations. Rows and columns. Horizontal and vertical.
 *
 * The outer loop is the longer one, covering the longer string.
 * And the inner loop is the shorter one that covers the shorter string.
 *
 * ```
 * for (i in 1..longerStringLength) {
 *     // The first column at 0th index.
 *     // When one string is empty, the number of operations is equal to the length of the other string.
 *     curr[0] = i
 *     for (j in 1..shorterStringLength) {
 *         // The index pointer `j` corresponds to the `shorter` string, and
 *         // the index pointer `i` corresponds to the `longer` string.
 *         if (shorter[j - 1] == longer[i - 1]) {
 *            curr[j] = prev[j - 1]
 *         } else {
 *            val deleteCost = prev[ j ]
 *            val insertCost = curr[ j - 1 ]
 *            val substituteCost = prev[ j - 1 ]
 *         }
 *     }
 * }
 * ```
 *
 * Once we finish the iteration, the `curr` array becomes the `prev`,
 * (Or in other words, the `prev` array becomes the `curr`).
 * And the new array becomes the `curr` array.
 *
 * ```
 * // Taking a temporary variable to store the `prev` array.
 * val temp = prev
 * // The old `prev` array becomes the `curr` array.
 * prev = curr
 * // Reusing the old `prev` array as a new(?) `curr` array.
 * curr = temp // This way, we are not creating a brand new array, and reduce the memory overhead!
 * ```
 *
 * In the end, the latest `curr` is actually, the `prev` as we assign `prev = curr` after each iteration.
 * The last cell of the `prev` (which becomes the latest `curr` in the end) array is our answer.
 * The last cell of the `prev` is `shorterStringLength` instead of `length - 1` because the `prev` array
 * (actually, both the arrays) are of size `length + 1`
 *
 * Remember that the `prev` array is not the target or the reference string array,
 * and that the size of the `prev` array is `shorterStringLength + 1`.
 * Notice this `+1`. So, the index `shorterStringLength` gives the last index of the `prev` array,
 * and it does not give the `index out of bound` exception.
 *
 * To access and read the last element of the `prev` array (which is the latest `curr` array in the end),
 * we can safely use the `shorterStringLength` index pointer.
 *
 * ```
 * val answer = prev[shorterStringLength]
 * ```
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * * Time Complexity:
 *
 * We still iterate over `longerStringLength * shorterStringLength`.
 * Hence, the time complexity remains the same as `O(n * m)`.
 *
 * * Space Complexity:
 *
 * Unlike the [Edit distance with backtracking](),
 * where we take a `2D-array`, here we take two `1D-array` of size `shorterStringLength + 1`.
 *
 * So, unlike the [Edit distance with backtracking](),
 * where the space complexity was `O(n * m)`, here we get the space complexity as `O(n)`.
 *
 */
fun main() {

    fun minEditDistanceWithoutBacktracking(target: String, reference: String): Int {
        // Destructuring declaration with a Pair.
        // We can use it with any data type that provides the `componentN()` function/s.
        val (shorter, longer) = if (target.length < reference.length) {
            // This is a pair.
            // The variable `shorter` represents the first part of the pair,
            // and the variable `longer` represents the second part of the pair.
            // So, in one-line, we check the shorter length, create a pair, and define two individual variables!
            target to reference
        } else {
            reference to target
        }

        var prev = IntArray(shorter.length + 1)
        var curr = IntArray(shorter.length + 1)

        for (i in 0..shorter.length) {
            prev[i] = i
        }

        for (i in 1..longer.length) {
            curr[0] = i
            for (j in 1..shorter.length) {
                // Compare the character of the longer string with each character of the shorter string.
                if (longer[i - 1] == shorter[j - 1]) {
                    curr[j] = prev[j - 1]
                } else {
                    val deleteCost = prev[j] + 1
                    val insertCost = curr[j - 1] + 1
                    val substituteCost = prev[j - 1] + 1
                    curr[j] = minOf(deleteCost, insertCost, substituteCost)
                }
            }
            val temp = prev
            prev = curr
            curr = temp
        }

        return prev[shorter.length]
    }

    val target = readln().trim()
    val reference = readln().trim()
    println(minEditDistanceWithoutBacktracking(target, reference))
}