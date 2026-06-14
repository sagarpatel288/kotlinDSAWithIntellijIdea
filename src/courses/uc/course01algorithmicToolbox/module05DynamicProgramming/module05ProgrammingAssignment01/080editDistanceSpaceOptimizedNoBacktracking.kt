package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * [Edit Distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/be74523bc7fb51c51d6418039bdc885dcadc55eb/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 * [Edit Distance with backtracking](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/be74523bc7fb51c51d6418039bdc885dcadc55eb/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt)
 * [Edit distance space optimized without backtracking image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/038340fcade8a36e29cdb2ea60e0d35faf054f69/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003editDistanceSpaceOptimized.png)
 * [Edit Distance Space Optimized Without Backtracking Image 02](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7d7bfba61c5f9cfd24bfd36e2421532f151a0aef/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003editDistanceSpaceOptimizedExample.png)
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
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f970241ced958d1d1c70b7b57a442b5bffee578b/assets/images/algorithmToolbox/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003a_editDistance2DVs1DAlgoTransformation.webp)
 *
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7d7bfba61c5f9cfd24bfd36e2421532f151a0aef/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchStringSpaceOptimized/003editDistanceSpaceOptimizedExample.png)
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
 * # ----------------------- Grader Output -----------------------
 *
 * Good job! (Max time used: 0.08/2.00, max memory used: 42364928/536870912.)
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

        // When one of the strings is empty.
        // First, we fill the first row, the row that represents that one of the two strings is empty.
        // Visualize: We are filling the first row.
        // The columns keeps changing (incrementally and horizontal) to fill each cell of the first row.
        // This is the base case where we are filling the very first entire row, where the longer string is empty.
        // This is the 0th row.
        // | 0 | 0 | S | U | N |
        // |---|---|---|---|---|
        // | 0 | 0 | 1 | 2 | 3 | <-------- This is what we are filling at the moment from left to right.
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
        for (j in 0..shorter.length) {
            prev[j] = j
        }

        // Outer `for` loop => Longer string.
        // The outer for loop represents the longer string (rows). It moves vertically, Row by row.
        // To understand:
        // Visualize:
        // Put your left index finger on the row (outer for loop) and move your right index finger horizontally to fill
        // each cell of the row.
        // Once we fill the entire row, the inner for loop gets finished, and the outer for loop changes the row.
        // Hence, the outer for loop moves vertical and downwards to change the row.
        // `i` is the pointer that changes the row.
        for (i in 1..longer.length) {
            // Each row starts from here, where the column value is `0` (meaning, empty string).
            // curr[0] represents the value of the "0th" column (the very first, base-case, empty-string column).
            // As soon as we start the iteration for the vertical rows,
            // the first index of the current array is always equal to the length up to the
            // current vertical row character.
            // Because, the first column considers the case where the string that represents the columns, is empty.
            // It means that the number of operation we need when one string is empty but the other string is non-empty,
            // is equal to the number of characters of the non-empty string.
            // To understand:
            // Visualize: We are filling each cell of the first column.
            // The row keeps changing (incrementally and vertically) to fill each cell of the first column.
            // curr[0] = (r_i, 0)
            // curr[0] fills the 0th column for each row, only one cell at a time, followed by the inner loop.
            //       |
            //       |
            //       |
            //       |
            //       V
            // | 0 | 0 | S | U | N |
            // |---|---|---|---|---|
            // | 0 | 0 | 1 | 2 | 3 |
            // | S | 1 | <---------- Later, the inner loop fills the remaining columns of the row from left to right.
            // | H |   | <----------
            // | I |   | <----------
            // | N |   | <----------
            // | E |   | <----------
            curr[0] = i

            // Inner `for` loop => Shorter string.
            // The inner for loop represents the shorter string (columns). It moves horizontally. Column by column.
            // The inner for loop fills each cell of the row.
            // The inner for loop moves horizontal and increments the column index.
            // `j` is the pointer that changes the columns.
            //
            // | 0 | 0 | S | U | N |
            // |---|---|---|---|---|
            // | 0 | 0 | 1 | 2 | 3 | <-------- We have already filled this via the independent for loop:  prev[i] = i
            //
            // The inner loop fills these columns from left to right for each row.
            //       |   |   |   |
            //       |   |   |   |
            //       |   |   |   |
            //       |   |   |   |
            //       V   V   V   V
            // | S | 1 | 0 | 1 | 2 | <-- Finishing the inner loop = Finishing all the columns of the row.
            // | H |   |   |   |   | <-- Then, the outer loop sets the new row and the inner loop changes the columns.
            // | I |   |   |   |   | <-- It continues until we finish the last row (all the columns of the last row).
            // | N |   |   |   |   |
            // | E |   |   |   |   |
            //
            for (j in 1..shorter.length) {
                // Compare the character of the longer string with each character of the shorter string.
                // `i` takes the value from the row, `j` takes the value from the column.
                // `j` is the cell index where we want to put the value.
                // Because we are changing columns (`j`) more frequently than the row.
                // It is like:
                // (r_i, c_j) = (0, 0), (0, 1), (0, 2)...(1, 0), (1, 1), (1, 2)...(2, 0), (2, 1), (2, 2)... and so on...
                //
                // Think of `prev` and `curr` as two rows (from left to right).
                // `prev` and `curr` are our `1-D-Arrays`.
                // We need and track only two columns: `j - 1` and `j` for each row (1-D array).
                // Two 1-D-Arrays, and previous and current index for each is all we need to calculate the cost.
                //
                // | prev | j - 1 | j |
                // |------|-------|---|
                // | curr | j - 1 | j |
                //
                // Using which we can calculate the cost as:
                //
                // | prev | j - 1  (replace)    | j (delete) |
                // |      |                \    |      |     |
                // |      |                 \   |      |     |
                // |      |                  ⊿  |      ▿     |
                // |------|---------------------|------------|
                // | curr | j - 1 (insert) --▷  | j          |
                //
                //
                if (longer[i - 1] == shorter[j - 1]) {
                    curr[j] = prev[j - 1]
                } else {
                    val deleteCost = prev[j] + 1
                    val insertCost = curr[j - 1] + 1
                    val substituteCost = prev[j - 1] + 1
                    curr[j] = minOf(deleteCost, insertCost, substituteCost)
                }
            }
            // Once we fill the entire array (covering each column), and before we change the row,
            // the previous becomes the current.
            // Or in other words, we assign the current array to the previous array.
            // In this way, the variable `prev` now represents (points to) the `curr` array.
            // Then, what about the `old curr` array?
            // We temporarily give it the old value of the `prev`, the old value of `prev` before we did `prev = curr`.
            // We assign the old `prev` to the `curr` to reduce the garbage.
            val temp = prev
            prev = curr
            curr = temp
        }

        // The last value of the curr (`prev` points to the `curr`. So, `prev` is the `curr`!).
        return prev[shorter.length]
    }

    val target = readln().trim()
    val reference = readln().trim()
    println(minEditDistanceWithoutBacktracking(target, reference))
}