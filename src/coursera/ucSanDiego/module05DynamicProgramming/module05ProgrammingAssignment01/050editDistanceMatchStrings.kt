package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

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
 * This can be compactly visualized by the following alignment.
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
 * And each cell of the result column contains the number of operation it takes to match the character of the target
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
 * for (i in 0..n) {
 *     // `n` is the length of the target string.
 *     // The pointer `i` represents the target string, whereas the pointer `j` represents the reference string.
 *     // We compare each character of the target string with each character of the reference string.
 *     // The reference string is empty.
 *     // So, it is like: Compare `ith` character of the target string with the `0th` character of the reference string.
 *     // The value represents the number of operations required to match the target string with the reference string.
 *     // If the reference string is empty, we need to delete each character of the target string.
 *     // In this case, the number of operations it takes is equal to the number of characters in the target string.
 *     operations[i][0] = i
 * }
 * ```
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
 * for (j in 0..m) {
 *     operations[0][j] = j
 * }
 * ```
 *
 * ### ----------------------- Bottom-up Process -----------------------
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
 * During the comparison, we have two possibilities: Either they match, or they don't.
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
 * The pointer `i` is at the character `s` of the target `short` (currently, `sorts`).
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
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/658890438ec5952058e296152c3d44fd66e8b2eb/res/coursera/ucSanDiego/module05DynamicProgramming/03editDistanceMatchString/003editDistance2dCalculation.png)
 *
 * #### ----------------------- Given Data -----------------------
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
 */
fun main() {

    fun minEditDistance(target: String, reference: String): Int {
        val targetLength = target.length
        val referenceLength = reference.length
        val operations = Array(targetLength + 1) { IntArray(referenceLength + 1) }
        for (i in 0..targetLength) {
            operations[i][0] = i
        }
        for (j in 0..referenceLength) {
            operations[0][j] = j
        }
        for (i in 1..targetLength) {
            for (j in 1..referenceLength) {
                // `i` and `j` start from 1, because we have already calculated the value of the 0th index,
                // where the 0th index represents an empty string (an empty character).
                // If we again start from 0, it would overwrite the old and correct values.
                // So, we start from 1.
                // However, the target and reference strings are indexed from 0 to length - 1.
                // If we compare target[1] == reference[1], then it would skip the first character of index 0.
                // So, we use target[i - 1] == reference[j - 1], and it also prevents index out of bound exception,
                // when the iteration reaches to the `targetLength` and `referenceLength`.
                // So, when `i` and `j` are 1, the comparison looks like: target[0] == reference[0], and
                // when `i` is `targetLength` and `j` is `referenceLength`,
                // the comparison looks like: target[targetLength - 1] == reference[referenceLength - 1].
                // So, the `i`th character (or cell) of the `operations` array is at `i - 1` index in the `target`
                // string, and the `j`th character (or cell) of the `operations` array is at `j - 1` index in the
                // `reference` string.
                // This also satisfies the value assignment.
                // For example, if the first character of both the strings matches with each other,
                // we use previously calculated value.
                // If we start the iteration from `0` instead of `1`, we get `index out of bound` exception while
                // trying to assign the value as `operations[i - 1][j - 1]`, because it would result into
                // `operations[0][0] = operations[-1][-1]`.
                if (target[i - 1] == reference[j - 1]) {
                    operations[i][j] = operations[i - 1][j - 1]
                } else {
                    val costOfInsertion = operations[i][j - 1] + 1
                    val costOfDelete = operations[i - 1][j] + 1
                    val costOfReplacement = operations[i - 1][j - 1] + 1
                    operations[i][j] = minOf(costOfInsertion, costOfDelete, costOfReplacement)
                }
            }
        }
        return operations[targetLength][referenceLength]
    }

    val target = readln().trim()
    val reference = readln().trim()
    println(minEditDistance(target, reference))
}