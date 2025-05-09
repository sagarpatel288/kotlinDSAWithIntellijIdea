package coursera.ucSanDiego.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ## ----------------------- Problem Statement -----------------------
 *
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5b4d35fc27f4c235a749fef2c89f05280607d0ec/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchString/003editDistanceMatchStrings.png)
 *
 * Edit Distance Problem:
 *
 * Compute the edit distance (the number of operations required to match strings) between two strings.
 *
 * Input:
 *
 * Two strings.
 *
 * Output:
 *
 * The minimum number of single-symbol insertions, deletions, and substitutions (replacements) to transform one string
 * into the other one.
 *
 * (In other words, the minimum number of operations required to transform one (target) string into other (reference)
 * string.)
 *
 * The Edit Distance Problem has many applications in computational biology, natural language processing,
 * spell checking, and many other areas.
 *
 * For example, biologists often compute edit distances when they search for disease-causing mutations.
 * The edit distance between two strings is defined as the minimum number of single-symbol insertions, deletions,
 * and substitutions to transform one string into the other one.
 *
 * Input format:
 *
 * Two strings consisting of lower case Latin letters, each on a separate line.
 *
 * Output format:
 *
 * The edit distance between them.
 *
 * Constraints:
 *
 * The length of both strings is at least 1 and at most 100.
 *
 * Sample 1:
 *
 * Input:
 *
 * short
 *
 * ports
 *
 * Output:
 *
 * 3
 *
 * The second string can be obtained from the first one by deleting s, substituting h for p, and inserting s.
 *
 * Sample 2:
 *
 * Input:
 *
 * editing
 *
 * distance
 *
 * Output:
 *
 * 5
 *
 * Delete e, insert s after i, substitute i for `a`, substitute g for c, insert e to the end.
 *
 * Sample 3:
 *
 * Input:
 *
 * ab
 *
 * ab
 *
 * Output:
 *
 * 0
 *
 * ## ----------------------- Which data type to use? -----------------------
 *
 * We have two strings to compare. This is not a single object (or subject, resource).
 * We are not comparing elements of a single resource as we did in the sorting techniques.
 * Unlike the resources of the sorting techniques, we have two different resources here.
 *
 * Now, imagine a column with a header `target`, a column with a header `reference`, and a result column with a header
 * `number of operations to match`.
 *
 * | Target (i) 	| Reference (j) 	| Operations               	|
 * |------------	|---------------	|--------------------------	|
 * | s          	| p             	| 1 (Replace `s` with `p`) 	|
 * | h          	| o             	|                          	|
 * | o          	| r             	|                          	|
 * | r          	| t             	|                          	|
 * | t          	| s             	|                          	|
 *
 * Each cell of the `target` column contains a single character of the target string.
 * Similarly, each cell of the `reference` column contains a single character (expected character) of the
 * reference string.
 * And each cell of the result column contains the number of operations it takes to match the character of the target
 * string with the character of the reference string.
 *
 * We can consider the two columns as a 2D array, and the result column as a value for each cell (index).
 * We need to fill each cell of the result column (the `operations` column) with the number of operations required
 * to match the character of the target string with the character of the reference string.
 *
 * The length of the target string and the reference string are not fixed, and can be different.
 * Let us assume that the length of the target string is `n`, and the length of the reference string is `m`.
 *
 * We need to compare and cover each and every character of both the strings.
 *
 * ```
 * val operations = Array(n) { IntArray(m) }
 * ```
 *
 * The `Array(n)` represents an outer loop and the `IntArray(m)` represents an inner loop.
 * In other words, the outer loop `Array(n)` are rows, and the inner loop `IntArray(m)` are columns.
 * There are `n` rows, and `m` columns.
 * Each row is of length `m`.
 * This understanding is important when we iterate through rows and columns to fill-out each cell of the table.
 *
 * | rows / columns 	|        1        	| 2 	|        3        	| .. 	| .. 	|        m        	|
 * |:--------------:	|:---------------:	|:-:	|:---------------:	|:--:	|:--:	|:---------------:	|
 * |        1       	| (r, c) = (1, 1) 	|   	|                 	|    	|    	|                 	|
 * |        2       	|                 	|   	| (r, c) = (2, 3) 	|    	|    	|                 	|
 * |        3       	|                 	|   	|                 	|    	|    	|                 	|
 * |       ..       	|                 	|   	|                 	|    	|    	|                 	|
 * |       ..       	|                 	|   	|                 	|    	|    	|                 	|
 * |        n       	|                 	|   	|                 	|    	|    	| (r, c) = (n, m) 	|
 *
 * Now, the length measurement (count) starts with 1.
 * For example, 1 character = length is 1. For 2 characters, length is 2. And so on...
 * But, what about the case when length is 0? Either the target string, or the reference string, or both the strings
 * can have 0 characters. It is possible.
 *
 * Image reference:
 *
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/658890438ec5952058e296152c3d44fd66e8b2eb/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchString/003editDistance2dCalculation.png)
 *
 * To cover that case, we need to add one more cell for each string.
 *
 * ```
 * val operations = Array(n + 1) { IntArray(m + 1) }
 * ```
 *
 * Here, `+ 1` of `n + 1` covers the case when the target string is empty, and `+ 1` of `m + 1` covers the case when
 * the reference string is empty.
 *
 * So, the size of the 2D array `operations` is one count more than the size (length) of the target string (row),
 * and one count more than the size (length) of the reference string (column).
 *
 * | i\j     	| 0 (”” ) 	| 1 (“P”) 	| 2 (“O”) 	| 3 (“R”) 	| 4 (“T”) 	| 5 (“S”) 	|
 * |---------	|---------	|---------	|---------	|---------	|---------	|---------	|
 * | 0 (”” ) 	| dp[0,0] 	| dp[0,1] 	| dp[0,2] 	| dp[0,3] 	| dp[0,4] 	| dp[0,5] 	|
 * | 1 (“S”) 	| dp[1,0] 	| dp[1,1] 	| dp[1,2] 	| dp[1,3] 	| dp[1,4] 	| dp[1,5] 	|
 * | 2 (“H”) 	| dp[2,0] 	| dp[2,1] 	| dp[2,2] 	| dp[2,3] 	| dp[2,4] 	| dp[2,5] 	|
 * | 3 (“O”) 	| dp[3,0] 	| dp[3,1] 	| dp[3,2] 	| dp[3,3] 	| dp[3,4] 	| dp[3,5] 	|
 * | 4 (“R”) 	| dp[4,0] 	| dp[4,1] 	| dp[4,2] 	| dp[4,3] 	| dp[4,4] 	| dp[4,5] 	|
 * | 5 (“T”) 	| dp[5,0] 	| dp[5,1] 	| dp[5,2] 	| dp[5,3] 	| dp[5,4] 	| dp[5,5] 	|
 *
 * Here, `dp[0,0]` indicates a case when both the target and reference strings are empty.
 * `dp[i, 0]` indicates a case when only the reference string is empty.
 * And `dp[0, j]` indicates a case when only the target string is empty.
 *
 * We start with one character at a time, compare it, select the operation, store the value, and move on.
 *
 * We start from the first character of both the strings.
 * Once we are done with the first character of both the strings, we are left with the remaining parts of each string.
 *
 * The process starts with the first character, and covers all the characters of each string.
 * Hence, it is a bottom-up approach of the dynamic programming.
 *
 *
 * ## ----------------------- Explanation: Thought Process -----------------------
 *
 * Creating and using formulas:
 *
 * We have two strings: The target string and the reference string.
 * Suppose, the target string is `short`, and the reference string is `ports`.
 *
 * The length of the target string is `n`, and the length of the reference string is `m`.
 *
 * ```
 * val n = target.length
 * val m = reference.length
 * ```
 *
 * To compare, we need two pointers. Let us assume that we have a pointer `i` to cover the target string, and
 * the pointer `j` to cover the reference string.
 *
 * The pointer `i` represents the `ith` character of the target string.
 * The pointer `j` represents the `jth` character of the reference string.
 *
 * We compare the two strings character by character.
 * We perform one operation at a time on the target string.
 * We don't perform any operation on the reference string.
 *
 * ### ----------------------- Base Case -----------------------
 *
 * If the reference string is empty, we need to delete each character of the target string.
 * For example, suppose the target string is `short` and the reference string is ` `.
 * We go through each character of the target string, compare it with the `0th` character of the reference string,
 * (because there is no `1st` character in an empty string!) realize that the reference string does not have
 * that character. In fact, the reference string is empty.
 * So, to match the `ith` character of the target string with the `0th` character of the reference string,
 * we delete the `ith` character of the target string.
 * We do the same while going through each character of the target string.
 * When the reference string is empty, and the target string is not empty (has certain characters), we need to delete
 * each character of the target string to match the reference string.
 *
 * So, the number of operations it takes to match the target string with the reference string when the reference string
 * is empty, depends upon the length of the target string (`n`).
 * In this case, the number of operations it takes is equal to the number of characters in the target string.
 *
 * In our example, the target string is `short` and the reference string is empty.
 * So, it takes `s = 1, h = 2, o = 3, r = 4, t = 5` operations.
 * If the target string had 1 character, it would have taken 1 operation.
 * If the target string had 2 characters, it would have taken 2 operations.
 * If the target string had 3 characters, it would have taken 3 operations.
 * And so on...
 *
 *
 * ```
 * /**
 *  * If `n` is the length of the target string, and `m` is the length of the reference string,
 *  * and the 2D-array is `table(row size)(column size)` = `operations(n + 1)(m + 1)`,
 *  * and we want to cover the case when the `reference string` is empty,
 *  * we iterate from index `0` up to `n` to cover each row, because the size of the row is `n + 1`.
 *  * And we hard-code the value for each cell of the 0th column to `0` to indicate that there is no reference character.
 *  * There is no further column to increase or proceed after `0`.
 *  * Hence, the iteration becomes:
 *  */
 * for (i in 0..n) {
 *     // `n` is the length of the target string.
 *     // The pointer `i` represents the target string, whereas the pointer `j` represents the reference string.
 *     // We compare each character of the target string with each character of the reference string.
 *     // The reference string is empty.
 *     // So, it is like: Compare `ith` character of the target string with the `0th` character of the reference string.
 *     // The value represents the number of operations required to match the target string with the reference string.
 *     // If the reference string is empty, we need to delete each character of the target string.
 *     // In this case, the number of operations it takes is equal to the number of characters in the target string.
 *     operations[i][0] = i // Fills 0th column.
 * }
 * ```
 *
 * (If you are wondering why we iterate up to `n` instead of `n - 1`, we will cover it shortly).
 *
 * Similarly, if the target string is empty, we need to insert each character of the reference string.
 * For example, suppose the reference string is `ports` and the target string is ` `.
 * We go through each character of the reference string, and insert the character to the target string.
 *
 * In this case, the number of operations it takes to match the target string with the reference string,
 * depends upon the length of the reference string (`m`).
 * And the number of operations it takes to match the target string with the reference string
 * is equal to the number of characters in the reference string.
 *
 * ```
 * /**
 *  * `m` is the length of the `reference string`.
 *  * The size of the 2D-array is `table(row size)(column size) = operations(n + 1)(m + 1)`,
 *  * where, the size of the rows is `r = n + 1 = 0` when the `target string` is empty.
 *  * However, we still have a non-empty size of columns, which is `m + 1`.
 *  * So, we cover each column, from index `0` up to `m`.
 *  * During the iteration, we do not increase the index of the row, because the target string is empty.
 *  * Hence, the iteration becomes:
 *  */
 * for (j in 0..m) {
 *     operations[0][j] = j // Fills 0th row.
 * }
 * ```
 *
 * Now, you might wonder that unlike normal iterations where we iterate from `0` and go up to `length - 1`,
 * the above code snippets show a `for loop` that starts from `0` and goes up to `length`.
 * Well, because we use the same index pointer `i`, and `j` to `store` values to 2D array `operations`,
 * and we use the same index pointer `i`, and `j` to `access` (`read`) the elements of the
 * target string and the reference string.
 *
 * And we can understand it.
 * The index starts from 0 for the `operations` 2D array, target string, and the reference string.
 * Each character of the target string represents a row of the `operations` array.
 * Each character of the reference string represents a column of the `operations` array.
 *
 * | i\j     	| 0 (”” ) 	| 1 (“P”) 	| 2 (“O”) 	| 3 (“R”) 	| 4 (“T”) 	| 5 (“S”) 	|
 * |---------	|---------	|---------	|---------	|---------	|---------	|---------	|
 * | 0 (”” ) 	| dp[0,0] 	| dp[0,1] 	| dp[0,2] 	| dp[0,3] 	| dp[0,4] 	| dp[0,5] 	|
 * | 1 (“S”) 	| dp[1,0] 	| dp[1,1] 	| dp[1,2] 	| dp[1,3] 	| dp[1,4] 	| dp[1,5] 	|
 * | 2 (“H”) 	| dp[2,0] 	| dp[2,1] 	| dp[2,2] 	| dp[2,3] 	| dp[2,4] 	| dp[2,5] 	|
 * | 3 (“O”) 	| dp[3,0] 	| dp[3,1] 	| dp[3,2] 	| dp[3,3] 	| dp[3,4] 	| dp[3,5] 	|
 * | 4 (“R”) 	| dp[4,0] 	| dp[4,1] 	| dp[4,2] 	| dp[4,3] 	| dp[4,4] 	| dp[4,5] 	|
 * | 5 (“T”) 	| dp[5,0] 	| dp[5,1] 	| dp[5,2] 	| dp[5,3] 	| dp[5,4] 	| dp[5,5] 	|
 *
 * The rows of the `operations` 2D array are: 1 + target characters.
 * So, the length (size) of the rows of the 2D array `operations` is `+1` than the length (size) of the target string.
 * This additional `+1` row is the first row, before the `target` characters start. So, the 0th row.
 *
 * Similarly, the columns of the `operations` 2D array are: 1 + reference characters.
 * So, the length (size) of the columns of the 2D array `operations` is `+1` than the length of the reference string.
 * The additional `+1` column is the first column, before the `reference` characters tart. The 0th column.
 *
 * Now, when we look at the 2D array `operations`, it obviously starts with the index (0, 0).
 * But, within the `operations` array, the `target` characters start from the index (1, 0) of the `operations` array,
 * and the `reference` characters start from the index (0, 1) of the `operations` array.
 *
 * It means, the index pointer of the `operations` array is `+1` index ahead than the index pointer of the
 * target string array, and the index pointer of the reference string array.
 *
 * For example, we read the first character of any string as `string[0]` where `0` is the index pointer.
 * In other words, the first character of any string is at the `0th` index pointer.
 *
 * However, the index pointer of the first characters of the target string and the reference string are not at the
 * (0, 0) index in the 2D array `operations`.
 * The first character of the target string starts when the index pointer of the 2D array `operations` is `i = 1`,
 * and the first character of the reference string starts when the index pointer of the 2D array `operations` is `j = 1`.
 *
 * So, to read the first character of the target string, we perform `target[i - 1]`,
 * and to read the first character of the reference string, we perform `reference[j - 1]`.
 *
 * Similarly, when `i` = `targetLength`, it becomes the last row of the 2D array `operations`,
 * and for the `target` string, it becomes `target[targetLength - 1]`,
 * which gives the last character of the `target` string.
 *
 * Likewise, when `j` = `referenceLength`, it becomes the last column of the 2D array `operations`,
 * and for the `reference` string, it becomes `reference[referenceLength - 1]`,
 * which gives the last character of the `reference` string.
 *
 *
 * ### ----------------------- Bottom-up Process -----------------------
 *
 * Now, as we have covered the base-cases where either one of the two strings has `0` character,
 * or both the strings are empty, we can move-on to consider that none of the two strings are empty.
 *
 * We compare each character of the target string with each character of the reference string.
 *
 * ```
 * for (i in 1..n) {
 *     for (j in 1..m) {
 *     ...
 *     }
 * }
 * ```
 *
 * How do we compare one character of a string with another character of a different string?
 *
 * 1. We access and read the character from each string.
 * 2. We compare the characters.
 *
 * How do we access and read a character from a string?
 *
 * We use:
 *
 * ```
 * `string[index pointer]`
 * ```
 *
 * For example, when we use `target[0]`, we get the character at index 0 (= first character) of the `target` string.
 * If we use `target[1]`, we get the character at index 1 (= second character) of the `target` string.
 * And so on.
 *
 * How do we compare the characters?
 *
 * Using the comparator operator:
 *
 * ```
 * target[index pointer] == reference[index pointer]
 * ```
 *
 * What will be the value of each `index pointer` during the comparison?
 *
 * ```
 * The outer loop uses the outer index pointer.
 * The inner loop uses the inner index pointer.
 * ```
 *
 * For example,
 *
 * ```
 * for (i in 1..n) corresponds to the `target` string as `n` = `target.length`.
 * for (j in 1..m) corresponds to the `reference` string as `m = `reference.length`.
 * ```
 *
 * The outer loop represents the iteration over the `target` string,
 * and it denotes the index pointer as `i`.
 * Hence, the target string uses the index pointer `i` to access and read the characters of the target string.
 *
 * Similarly, the inner loop represents the iteration over the `reference` string,
 * and it denotes the index pointer as `j`.
 * Hence, the reference string uses the index pointer `j` to access and read the characters of the reference string.
 *
 * So, it looks like:
 *
 * ```
 * for (i in 1..n) {
 *     for (j in 1..m) {
 *         if (target[i - 1] == reference[j - 1]) {
 *         ...
 *         } else {
 *         ...
 *         }
 *     }
 * }
 * ```
 *
 * To visualize the process and progress,
 *
 * | i\j     	| 0 (”” ) 	| 1 (“P”) 	| 2 (“O”) 	| 3 (“R”) 	| 4 (“T”) 	| 5 (“S”) 	|
 * |---------	|---------	|---------	|---------	|---------	|---------	|---------	|
 * | 0 (”” ) 	| dp[0,0] 	| dp[0,1] 	| dp[0,2] 	| dp[0,3] 	| dp[0,4] 	| dp[0,5] 	|
 * | 1 (“S”) 	| dp[1,0] 	| dp[1,1] 	| dp[1,2] 	| dp[1,3] 	| dp[1,4] 	| dp[1,5] 	|
 * | 2 (“H”) 	| dp[2,0] 	| dp[2,1] 	| dp[2,2] 	| dp[2,3] 	| dp[2,4] 	| dp[2,5] 	|
 * | 3 (“O”) 	| dp[3,0] 	| dp[3,1] 	| dp[3,2] 	| dp[3,3] 	| dp[3,4] 	| dp[3,5] 	|
 * | 4 (“R”) 	| dp[4,0] 	| dp[4,1] 	| dp[4,2] 	| dp[4,3] 	| dp[4,4] 	| dp[4,5] 	|
 * | 5 (“T”) 	| dp[5,0] 	| dp[5,1] 	| dp[5,2] 	| dp[5,3] 	| dp[5,4] 	| dp[5,5] 	|
 *
 * 1. Imagine the above table in front of you.
 * 2. Now, the outer loop corresponds to the left hand.
 * 3. And the inner loop corresponds to the right hand.
 * 4. We put our index finger of the left hand on the row number (index) that the outer loop (`i`) represents.
 * 5. And we keep moving the index finger of the right hand on each cell, from left to right, column by column,
 * along with, and according to the index (`j`) of the inner loop.
 * 6. This way, we compare the character of the outer loop (i) with each character of the inner loop (j).
 * 7. Once the inner loop finishes the iteration, the index of the outer loop (i) changes.
 * 8. So, we change the position of our index finger of the left hand vertically, moving downwards, one step at a time,
 * row by row, along with, and according to the index `i` of the outer loop. This is indeed the step 4.
 * 9. Repeat step 5, 6, 7, and 8 until we finish the last index (`i`) of the outer loop,
 * and the last index (`j`) of the inner loop.
 *
 * Now, during the comparison, we have two possibilities: Either they match, or they don't.
 *
 * Suppose, the character at `i` and the character at `j` matches with each-other.
 *
 * In this case, we move both the pointers `i` and `j` to indicate that we do not need to perform any operation.
 * It means, the current operation does not cost us anything extra.
 * It does not add any extra cost to the existing cost.
 *
 * When the character at `i` matches with the character at `j`, the pointer `i` says that
 * `I am done with the current character. Now, I have `i - 1` characters to cover.`, and the pointer `j` says that
 * `I am done with the current character. Now, I have `j - 1` characters to cover.`
 *
 * The more accurate (close to the actual theory) way to look at it is:
 *
 * ```
 * If the current state `operations[i][j]` do not require any operation (because they match), it does not add any cost
 * (it does not add any extra count to the number of operations required to match two strings).
 * In other words, it costs the same as the previous cost: `operations[i - 1][j - 1]`
 * ```
 *
 * We can represent it with the below code:
 *
 * ```
 * // The number of operations required to match the characters remains the same as the previously calculated value.
 * val costIfMatch = operations[i - 1][j - 1]
 * ```
 *
 * Suppose, they don't match.
 *
 * To match a character, we can perform the following operations (one at a time) on the target string:
 *
 * 1. Insert.
 * 2. Delete.
 * 3. Replace.
 *
 * Each operation adds `+1` to the number of operations required to match the target string with the reference string.
 * The `+1` is the cost of each operation. It indicates that the operation costs us `+1`.
 *
 * If we perform the insert operation, it means that, after the insert operation, the character at `j`, and the newly
 * inserted character will match. So, we can move `j`, but the pointer `i` stays at the same position.
 *
 * For example,
 *
 * Let us assume that we are comparing these two strings: `short` and `ports`.
 * `short` is a target string, and `ports` is a reference string.
 *
 * Let us assume that we start the comparison from the end (length - 1) of both the strings.
 * So, the pointer `i` is at `t` of the `short`, and the pointer `j` is at `s` of the `ports`.
 *
 * The characters do not match, and we select the `insert` operation to make them match.
 * So, we insert a character `s` at the end of the `short`.
 *
 * Now, the character `s` matches with the character `s` of the pointer `j` of the string `ports`.
 * So, we can move the pointer `j`.
 *
 * Because, we performed the insert operation to match the character at pointer `j`.
 * So, after the insert operation, we should move the pointer `j`.
 *
 * However, the pointer `i` stays at the same position,
 * at character `t` of the target string `short`, which is now `shorts`.
 *
 * ```
 * So, after the insert operation, the pointer `i` stays at the same position `t`,
 * but the pointer `j` moves to the `j - 1` position, at the character `t` of the reference string `ports`.
 * ```
 *
 * We can represent that with the below code:
 *
 * ```
 * // The `+1` indicates the cost of the insert operation we just have performed.
 * val costOfInsertion = operations[i][j - 1] + 1
 * ```
 *
 * Similarly, if we perform the delete operation, it means that, after the delete operation, the pointer `i` moves
 * (the position of the pointer `i` changes), but the position of the pointer `j` remains the same.
 *
 * Let us continue our example, and the progress we have made so far.
 * The pointer `i` is at the character `t` of the target string `short` (which is now `shorts` because we have inserted
 * the character `s` in the previous step), and the pointer `j` is at the character `t` of the reference `ports`.
 *
 * The character at pointer `i`, and the character at pointer `j` matches. So, we don't perform any operation, and we
 * move on.
 *
 * Now, the pointer `i` is at the character `r` of the target `short` (currently, `shorts`),
 * and the pointer `j` is at the character `r` of the reference `ports`. They match. So, we move on.
 *
 * The pointer `i` is at the character `o` of the target `short` (currently, `shorts`),
 * and the pointer `j` is at the character `o` of the reference `ports`. They match. So, we move on.
 *
 * The pointer `i` is at the character `h` of the target `short` (currently, `shorts`),
 * and the pointer `j` is at the character `p` of the reference `ports`. They do not match.
 *
 * Let us assume that we select the `delete` operation on the current character of the pointer `i`.
 * Once we delete the character `h`, the pointer `i` moves to the character `s` of the target `short`
 * (currently, `sorts`).
 *
 * So, after the delete operation, the new position of the pointer `i` becomes `i - 1`.
 * However, the pointer `j` remains at the same position, on the character `p`.
 *
 * ```
 * So, when we perform the delete operation, the pointer `i` moves to `i - 1`,
 * but the pointer `j` remains at the same position.
 * ```
 *
 * We can represent it with the below code:
 *
 * ```
 * // The `+1` indicates the cost of the delete operation we just have performed.
 * val costOfDelete = operations[i - 1][j] + 1
 * ```
 *
 * Similarly, if we perform the replace operation, both the pointers `i`, and `j` move on.
 *
 * Let us continue the example and progress.
 * The pointer `i` is at the first character `s` of the target `short` (currently, `sorts`).
 * The pointer `j` is at the character `p` of the target `ports`.
 *
 * We select the replace operation. So, we replace the character `s` of the target `short` with the character `p`.
 * It means that after the replace operation, both the characters, the characters at the pointer `i` and `j` will match.
 *
 * So, we can move both the pointers.
 *
 * ```
 * Hence, after the replace operation, the new position of the pointer `i` will be `i - 1`,
 * and the new position of the pointer `j` will be `j - 1`.
 * ```
 *
 * We can represent it with the below code:
 *
 * ```
 * // The `+1` indicates the cost of the replacement operation we just have performed.
 * val costOfReplacement = operations[i - 1][j - 1] + 1
 * ```
 *
 * However, during the process we don't (can't) know beforehand which operation is cheaper.
 * Hence, we use `minOf`.
 *
 * ```
 * val operations[i][j] = minOf(costOfInsertion, costOfDelete, costOfReplacement)
 * ```
 *
 * ### ----------------------- Visual References (Technical Explanation) -----------------------
 *
 * [History](https://youtu.be/d-Eq6x1yssU?si=QA9xUzBsNwIWI_ge)
 *
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/658890438ec5952058e296152c3d44fd66e8b2eb/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchString/003editDistance2dCalculation.png)
 *
 * #### ----------------------- Data: Simplified 2D Table -----------------------
 *
 * | Target \ Reference 	| 0 	| P 	| O 	| R 	| T 	| S 	|
 * |--------------------	|---	|---	|---	|---	|---	|---	|
 * | 0                  	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	|
 * | S                  	| 1 	| 1 	| 1 	| 2 	| 3 	| 4 	|
 * | H                  	| 2 	| 2 	| 2 	| 2 	| 3 	| 5 	|
 * | O                  	| 3 	| 3 	| 3 	| 2 	| 3 	| 4 	|
 * | R                  	| 4 	| 4 	| 4 	| 3 	| 2 	| 3 	|
 * | T                  	| 5 	| 5 	| 5 	| 4 	| 3 	| 3 	|
 *
 * #### ----------------------- Data: Detailed 2D Table -----------------------
 *
 * | i\j     	| 0 (”” )     	| 1 (“P”)     	| 2 (“O”)     	| 3 (“R”)     	| 4 (“T”)     	| 5 (“S”)     	|
 * |---------	|-------------	|-------------	|-------------	|-------------	|-------------	|-------------	|
 * | 0 (”” ) 	| dp[0,0] = 0 	| dp[0,1] = 1 	| dp[0,2] = 2 	| dp[0,3] = 3 	| dp[0,4] = 4 	| dp[0,5] = 5 	|
 * | 1 (“S”) 	| dp[1,0] = 1 	| dp[1,1] = 1 	| dp[1,2] = 2 	| dp[1,3] = 3 	| dp[1,4] = 4 	| dp[1,5] = 4 	|
 * | 2 (“H”) 	| dp[2,0] = 2 	| dp[2,1] = 2 	| dp[2,2] = 2 	| dp[2,3] = 3 	| dp[2,4] = 4 	| dp[2,5] = 5 	|
 * | 3 (“O”) 	| dp[3,0] = 3 	| dp[3,1] = 3 	| dp[3,2] = 2 	| dp[3,3] = 3 	| dp[3,4] = 4 	| dp[3,5] = 5 	|
 * | 4 (“R”) 	| dp[4,0] = 4 	| dp[4,1] = 4 	| dp[4,2] = 3 	| dp[4,3] = 2 	| dp[4,4] = 3 	| dp[4,5] = 4 	|
 * | 5 (“T”) 	| dp[5,0] = 5 	| dp[5,1] = 5 	| dp[5,2] = 4 	| dp[5,3] = 3 	| dp[5,4] = 2 	| dp[5,5] = 3 	|
 *
 * `dp[0][j]` represents the cases when the target string is empty.
 * Similarly, `dp[i][0]` represents the cases when the reference string is empty.
 *
 * #### ----------------------- When `target[i - 1] == reference[j - 1]`  -----------------------
 *
 * | Condition              	| Operation    	| DP Relationship           	| Arrow Color (Sample) 	|
 * |------------------------	|--------------	|---------------------------	|----------------------	|
 * | `array[i] == array[j]` 	| Copy / No Op 	| `dp[i][j] = dp[i-1][j-1]` 	| Teal/Green arrow     	|
 *
 *
 * #### ----------------------- When `target[i - 1] # reference[j - 1]`  -----------------------
 *
 * | Operation 	| From Which DP Cell?       	| Cost/Formula       	| Arrow Color (Sample) 	|
 * |-----------	|---------------------------	|--------------------	|----------------------	|
 * | Replace   	| `dp[i-1][j-1]` (diagonal) 	| `dp[i-1][j-1] + 1` 	| Blue arrow           	|
 * | Delete    	| `dp[i-1][j]` (up)         	| `dp[i-1][j] + 1`   	| Red arrow            	|
 * | Insert    	| `dp[i][j-1]` (left)       	| `dp[i][j-1] + 1`   	| Pink arrow           	|
 *
 *
 * ## ----------------------- Complexity Analysis -----------------------
 *
 * ### ----------------------- Time Complexity -----------------------
 *
 * Two nested for loops. We compare each character of the target string with each character of the reference string.
 * Evaluate all the 3 possible operations and select the minimum cost.
 *
 * It is O(n * m).
 *
 * ### ----------------------- Space Complexity -----------------------
 *
 * We use a 2D array to store the number of operations required for each character of both the strings.
 * We store the result for each cell.
 * The size of the 2D array is (n + 1)(m + 1).
 * Hence, the space complexity is O(n * m).
 * This is useful only if we want to find which operations we took.
 *
 * If we don't need to backtrack, we can store only the previous row or column instead of storing each cell.
 * In that case, the space complexity can be reduced to min(n * m).
 *
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 *
 * (Max time used: 0.09/2.00, max memory used: 42418176/536870912.)
 *
 * ## ----------------------- How to remember? -----------------------
 *
 * 1. Comparison means iteration (two nested for loops)
 * 2. An iteration (loop) requires the end-limit. (What will be the end-point?)
 * 3. We need to store the values. (What will be the data type of the container?)
 * 4. We use Wagner-Fischer theory. (We use a 2D array, with the length of `(n + 1)(m + 1)`).
 * 5. Formulas. (When characters match, no operation. When they don't, min cost of delete, insert, or substitute).
 * 6. Backtracking starts with the last cell of the table. [Backtracking edit distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/dcb3a367bc47b095d37018006f07a6626eabce6c/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt)
 * 7. Iteration uses an index pointer, and the last index of the 2D array is `length`.
 * 8. And it is a 2D array. So, we use two index pointers.
 * 9. If the characters match, no operation.
 * 10. If they don't, compare the cost.
 * 11. Don't forget to reduce `pointers`, and add `>0` checks to avoid `index out of bound` exception.
 */
fun main() {

    fun minEditDistance(target: String, reference: String): Int {
        // The 2D array that we use to store the values will be slightly larger,
        // with an additional size of `+1` compared to the given strings,
        // to cover the `empty` string cases.
        val targetLength = target.length
        val referenceLength = reference.length
        // Define a 2D-array = Array(n) { m } (or say, Array(i) { j }, however we can remember!)
        val operations = Array(targetLength + 1) { IntArray(referenceLength + 1) }
        // Normally, we iterate from `0` to `length - 1` when we want to cover each character of a string.
        // However, this is not just about covering each character of a single string. There's more.
        // Here, we have created a 2D array of size (n + 1)(m + 1),
        // where n is the length of the target string, and m is the length of the reference string.
        // The `+1` of the `n + 1` indicates an additional (first) row for the target string,
        // to cover the case when the target string has `0` character.
        // Similarly, the `+1` of the `m + 1` indicates an additional (first) column for the reference string,
        // to cover the case when the reference string has `0` character.
        // Because of these additional (first) row/column, we can cover the cases
        // where the target string has `0` character, but the reference string has a few characters,
        // or where the reference string has `0` character, but the target string has a few characters,
        // or where both the target and the reference strings are empty.
        // These cases are the base cases.
        // These cases are also known as initialization of the edit distance dynamic programming,
        // which is mandatory for the correctness of the upcoming logic.
        // In this sense, it is the base of the logic, and it is kind of a pre-computed key-lemma.
        // In fact, This is a part of the wagner-fischer process/formula who gave this logic.
        for (i in 0..targetLength) {
            // When `j` is 0, it indicates `0th` character, that is, an empty reference string.
            // Fill `0th` column.
            operations[i][0] = i
        }
        for (j in 0..referenceLength) {
            // When `i` is 0, it indicates `0th` character, that is, an empty target string.
            // Fill `0th` row.
            operations[0][j] = j
        }

        // The number of rows are equal to `targetLength + 1`,
        // and the number of columns are equal to `referenceLength + 1`.
        // We have already covered the row at index 0, and the column at index 0 of the `operations` 2D array.
        // It covered the cases of empty strings,
        // where one of the two strings or both the strings are empty.
        // Now, we start the iteration to fill the rows and columns from index (1,1),
        // which signifies the row at index 1 and the column at index 1.
        // That's why the pointers `i` and `j` start from `1`.
        // We use the same pointers (`i` and `j`) to access the characters from the given strings.
        // It means, to access the first character (or for that matter, any character) from the target string,
        // we need to perform `target[i - 1]` = `target[1 - 1]` = `target[0]`.
        // Similarly, to access the first character (or for that matter, any character) from the reference string,
        // we need to perform `reference[j - 1]` = `reference[1 - 1]` = `reference[0]`.
        // We iterate up to `targetLength` index to cover the last row, and `referenceLength` index to cover the last
        // column of the `operations` 2D array.
        // So, when we try to access the last character of each string, it becomes: `string[length - 1]` which is valid.
        // For example, when the pointer `i` is `targetLength`, it becomes:
        // `target[i - 1]` = `target[targetLength - 1]`, which is valid.
        // Similarly, when the pointer `j` is `referenceLength`, it becomes:
        // `reference[j - 1]` = `reference[referenceLength - 1]`, which is valid.
        for (i in 1..targetLength) {
            for (j in 1..referenceLength) {
                // `i` and `j` start from 1, because we have already calculated the value of the 0th index (row/column),
                // where the 0th index of the `operations` array represents an empty string (an empty character).
                // If we again start from 0, it would overwrite the old and correct values.
                // So, we start from 1.
                // However, the target and reference strings are indexed from 0 to length - 1.
                // If we compare target[1] == reference[1], then it would skip the first character of index 0.
                // So, we use target[i - 1] == reference[j - 1], and it also prevents index out of bound exception,
                // when the iteration reaches to the `targetLength` and `referenceLength`.
                // So, when `i` and `j` are 1, the comparison looks like: target[0] == reference[0],
                // which compares the first character of each string, and
                // when `i` is `targetLength` and `j` is `referenceLength`,
                // the comparison looks like: target[targetLength - 1] == reference[referenceLength - 1],
                // which compares the last character of each string.
                // So, the `i`th index of the `operations` array is `i - 1` index in the `target` string,
                // and the `j`th index of the `operations` array is at `j - 1` index in the `reference` string.
                // This also satisfies the value assignment.
                // For example, if the first character of both the strings matches with each other,
                // we use previously calculated value.
                // If we start the iteration from `0` instead of `1`, we get `index out of bound` exception while
                // trying to assign the value as `operations[i - 1][j - 1]`, because it would result into
                // `operations[0][0] = operations[-1][-1]`.
                if (target[i - 1] == reference[j - 1]) {
                    operations[i][j] = operations[i - 1][j - 1]
                } else {
                    // Variables to increase the readability and simplicity.
                    val costOfInsertion = operations[i][j - 1] + 1
                    val costOfDelete = operations[i - 1][j] + 1
                    val costOfReplacement = operations[i - 1][j - 1] + 1
                    operations[i][j] = minOf(costOfInsertion, costOfDelete, costOfReplacement)
                }
            }
        }
        // The bottom-last cell value is the result (minimum edit distance of the two strings).
        return operations[targetLength][referenceLength]
    }

    val target = readln().trim()
    val reference = readln().trim()
    println(minEditDistance(target, reference))
}