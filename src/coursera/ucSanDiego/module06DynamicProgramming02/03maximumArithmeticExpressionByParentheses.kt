package coursera.ucSanDiego.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
 *
 * References:
 * 1. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/03maximumArithmeticExpression.png
 * 2. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/05singleElementsInDiagonalCells.png
 * 3. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
 * 4. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/07twoElements.png
 * 5. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/09minimumMaximum.png
 * 6. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/11allThreeElements.png
 * 7. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/13fourElements.png
 * 8. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 * 9. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/17fourElementsTable.png
 * 10. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/21theCompleteTablesBothMinAndMaxForAllElements.png
 *
 * Maximum Value of an Arithmetic Expression Problem:
 *
 * Parenthesize an arithmetic expression to maximize its value:
 *
 * Input:
 *
 * An arithmetic expression consisting of digits as well as plus, minus, and multiplication signs.
 *
 * Output:
 *
 * Add parentheses to the expression to maximize its value.
 *
 * For example, for an expression `(3 + 2 ×4)`, there are two ways of parenthesizing it:
 * `(3 + (2 ×4)) = 11` and `((3 + 2) ×4) = 20`.
 *
 * Input format:
 *
 * The only line of the input contains a `string s` of `length 2n+ 1` for `some n, with symbols s0,s1,...,s2n`.
 * Each symbol at an `even position` of s is a `digit` (that is, an integer from 0 to 9),
 * while each symbol at an `odd position` is one of three operations from `{+, -, *}`.
 *
 * Output format:
 *
 * The maximum value of the given arithmetic expression among all possible orders of applying arithmetic operations.
 *
 * Constraints:
 *
 * 0 ≤ n ≤ 14 (hence the string contains at most 29 symbols).
 *
 * Sample:
 *
 * Input:
 *
 * 5-8+7*4-8+9
 *
 * Output:
 *
 * 200
 *
 * Why and How 200? Because 200 = (5−((8 + 7) ×(4−(8 + 9))))
 *
 * # ----------------------- Thought Process & Translating it into the code -----------------------
 *
 * ## ----------------------- Objective: Understanding the pattern: -----------------------
 *
 * Suppose that we have the following input:
 *
 * ```
 * 5 - 8 + 7 x 4 - 8 + 9
 * ```
 *
 * ## ----------------------- **_Key-point: 1_** -----------------------
 *
 * The parentheses define an order in the Arithmetic operation.
 *
 * For example, for the following Arithmetic expression:
 *
 * ```
 * (a + b) - (c + d)
 * = First, there will be an addition between a and b or c and d.
 * = Then, in the end, there will be a subtraction between the result of (a + b) and (c + d).
 * = Hence, the subtraction is the last operation for the given arithmetic expression.
 * ```
 *
 * Similarly, let us assume that the last operation for our example is `multiplication`.
 * So, it can be:
 *
 * ```
 * (5 - 8 + 7) * (4 - 8 + 9)
 * ```
 *
 * The important observation here is:
 *
 * ```
 * For the given range (i, j), where i < j, we can split the expression into two subproblems as (i, k) and (k + 1, j).
 * ```
 *
 * ## ----------------------- _**Key-point: 2**_ -----------------------
 *
 * Now, to maximize the arithmetic expression value, these two subsets must be an optimal solution.
 * What we want to convey here is that, to be the maximum arithmetic expression (the original problem),
 * the subproblems must also be the maximum arithmetic expression for the reduced size.
 *
 * For example, let us assume that we have the following arithmetic expression:
 *
 * ```
 * a - b + c * d - e + f
 * ```
 *
 * And if we assume the multiplication as the last operation,
 * and divide the original problem into two subproblems as below:
 *
 * ```
 * (a - b + c) * (d - e + f)
 * ```
 *
 * Now, only if these two subproblems give the maximum value can the original problem produce the maximum value.
 * In other words, for the original problem to produce the maximum value,
 * both of these subproblems must also yield the maximum value.
 *
 * ## ----------------------- _**Key-point: 3**_ -----------------------
 *
 * However, knowing or considering only the maximum value is not enough. Why?
 * Because, if the last operation was subtraction, then we would want the subexpression one (minuend)
 * to be as large as possible, but the subexpression two (subtrahend) should be as small as possible to produce
 * the maximum value of the arithmetic operation. Right?
 *
 * ## ----------------------- _**Key-point: 4**_ -----------------------
 *
 * That means we need to consider at least four combinations for two sub-expressions as below:
 *
 * ```
 * 1. max1 operation max2
 * 2. max1 operation min2
 * 3. min1 operation min2
 * 4. min1 operation max2
 * ```
 *
 * Out of these four combinations, we take the one that gives us the maximum value.
 *
 * ## ----------------------- _**Key-point: 5: An important observation. An interesting pattern.**_ ------------------
 *
 * What happens when we place all the elements into a 2D-Matrix Table?
 *
 * Reference:
 * 1. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/05singleElementsInDiagonalCells.png
 * 2. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
 *
 * Table:
 *
 * | i / j 	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	|
 * |:-----:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|
 * |   0   	| 5 	|   	|   	|   	|   	|   	|
 * |   1   	|   	| 8 	|   	|   	|   	|   	|
 * |   2   	|   	|   	| 7 	|   	|   	|   	|
 * |   3   	|   	|   	|   	| 4 	|   	|   	|
 * |   4   	|   	|   	|   	|   	| 8 	|   	|
 * |   5   	|   	|   	|   	|   	|   	| 9 	|
 *
 * We can see that when the values of i and j are the same, it represents only a single element.
 * There is no operation for a single element.
 * Hence, we don't need to place parentheses around the element. The result is the element itself.
 *
 * In other words, when the difference between `i` and `j` is 0, we get a single element.
 *
 * Similarly, when the difference between `i` and `j` is 1, we get two elements.
 * For example, when `i = 0` and `j = 1`, it indicates the arithmetic operation between the elements `5` and `8`.
 * Similarly, when `i = 1` and `j = 2`, it indicates the arithmetic operation between the elements `8` and `7`.
 *
 * Again, when the difference between `i` and `j` is 2, we get three elements.
 * For example, when `i = 0` and `j = 2`, it indicates the arithmetic operation between the elements `5`, `8`, and `7`.
 * Similarly, when `i = 1` and `j = 3`, it indicates the arithmetic operation between the elements `8`, `7`, and `4`.
 *
 * And so on...
 *
 * ```
 * We can say that if `i` and `j` are 0-based indices, and `i <= j`, we get `j - i = result + 1` elements.
 * ```
 *
 * Also, we can associate the given terms `5 - 8 + 7 * 4 - 8 + 9` with the indices (columns) as below:
 *
 * ```
 * 5 = index 0
 * 8 = index 1
 * 7 = index 2
 * 4 = index 3
 * 8 = index 4
 * 9 = index 5
 * ```
 *
 * What about rows? Any observation/significance of the rows? What do the rows convey?
 *
 * References:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
 *
 * | i / j 	| 0 	|   1   	|     2     	|       3       	|         4         	|           5           	|
 * |:-----:	|:-:	|:-----:	|:---------:	|:-------------:	|:-----------------:	|:---------------------:	|
 * |   0   	| 5 	| 5 - 8 	| 5 - 8 + 7 	| 5 - 8 + 7 * 4 	| 5 - 8 + 7 * 4 - 8 	| 5 - 8 + 7 * 4 - 8 + 9 	|
 * |   1   	|   	|   8   	|   8 + 7   	|   8 + 7 * 4   	|   8 + 7 * 4 - 8   	|   8 + 7 * 4 - 8 + 9   	|
 * |   2   	|   	|       	|     7     	|     7 * 4     	|     7 * 4 - 8     	|     7 * 4 - 8 + 9     	|
 * |   3   	|   	|       	|           	|       4       	|       4 - 8       	|       4 - 8 + 9       	|
 * |   4   	|   	|       	|           	|               	|         8         	|         8 + 9         	|
 * |   5   	|   	|       	|           	|               	|                   	|           9           	|
 *
 * ```
 * If we observe the journey of row 0 from column 0 to column 5, it is a classical dynamic programming pattern,
 * where we start from the bottom and increase the size of the problem as we move to the right side.
 * ```
 *
 * For the given expression `5 - 8 + 7 * 4 - 8 + 9`, each row signifies the below meanings:
 *
 * ```
 * 0th row indicates the operation/s between the element/s at 0th index, `5`. So, no operation.
 * 1st row indicates the operation/s between the elements at 0 and 1st indices, `5` and `8`.
 * So, only one operation is available and possible between them: `(5 - 8)`.
 *
 * 2nd row indicates the operation/s between the elements at index 0, 1, and 2 => `5`, `8`, and `7`.
 * In this case, we have multiple combinations.
 * It can be `(5 - 8) + 7` or `5 - (8 + 7)`.
 * Out of these two possibilities, the combination `(5 - 8) + 7` gives the maximum result(4),
 * whereas the combination `5 - (8 + 7)` gives the minimum result (-10).
 *
 * Similarly, 3rd row indicates the operations between the elements at index 0, 1, 2, and 3 => 5, 8, 7, and 4.
 * 4th row indicates the operations between the elements at index 0, 1, 2, 3, and 4 => 5, 8, 7, 4, and 8.
 * And 5th row indicates the operations between the elements at index 0, 1, 2, 3, 4, and 5 => 5, 8, 7, 4, 8, and 9.
 * ```
 *
 * ## ----------------------- _**Key-point: 6: Two Tables**_ -----------------------
 *
 * Now, if you remember, we have concluded that we need both the maximum and minimum values.
 * Hence, we will use two tables. One table for the minimum values, and the other for the maximum values.
 *
 * But how do we fill the tables?
 *
 * ## ---------- _**Key-point: 7: The Process: Filling Up The Two Tables = Placing The Parentheses**_ ----------
 *
 * Let us do this gradually. We have the following input:
 *
 * ```
 * 5 - 8 + 7 x 4 - 8 + 9
 * ```
 *
 * We have already seen above that a single element does not require any parentheses.
 * As we make progress from one element to multiple elements (in other words, as the multiple elements are involved),
 * We need to consider multiple possibilities of placing the parentheses around them.
 *
 * Let us understand this.
 *
 * ```
 * 1. The single element 5 does not require any parentheses.
 * 2. Two elements 5 and 8 have only one operation between them. So, (5 - 8) = -3.
 * 3. Three elements 5, 8, and 7 have two possibilities.
 * Either, we can form (5 - 8) + 7 = -3 + 7 = 4 or we can form 5 - (8 + 7) = 5 - 15 = -10.
 * So, we can put the value `-10` in the minimum value table, and `4` in the maximum value table.
 * ```
 *
 * But in which cell? Well, we have learned that in the last few key points:
 *
 * ```
 * if i and j are 0-based indices, and i <= j, then `j - i = result + 1 elements are involved.`
 * ```
 *
 * And if we use the corresponding index of each term, we can say that:
 *
 * ```
 * 1. (5 - 8) = 2 elements => 1 - 0 = 1 + 1. So, i = 0 and j = 1 => (0, 1).
 * 2. Similarly, (8 + 7) = 2 elements => j = 2, and i = 1 => (1, 2).
 * ```
 *
 * We can represent it in the table form as below:
 *
 * References:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/07twoElements.png
 *
 * Expression: `5 - 8 + 7 * 4 - 8 + 9`
 *
 * | i / j 	| 0 	|  1 	|  2 	| 3 	| 4 	| 5 	|
 * |:-----:	|:-:	|:--:	|:--:	|:-:	|:-:	|:-:	|
 * |   0   	| 5 	| -3 	|    	|   	|   	|   	|
 * |   1   	|   	|  8 	| 15 	|   	|   	|   	|
 * |   2   	|   	|    	|  7 	|   	|   	|   	|
 * |   3   	|   	|    	|    	| 4 	|   	|   	|
 * |   4   	|   	|    	|    	|   	| 8 	|   	|
 * |   5   	|   	|    	|    	|   	|   	| 9 	|
 *
 *
 * After filling in all the pairs of two elements, the table looks as shown below:
 *
 * | i / j 	| 0 	|  1 	|  2 	|  3 	|  4 	|  5 	|
 * |:-----:	|:-:	|:--:	|:--:	|:--:	|:--:	|:--:	|
 * |   0   	| 5 	| -3 	|    	|    	|    	|    	|
 * |   1   	|   	|  8 	| 15 	|    	|    	|    	|
 * |   2   	|   	|    	|  7 	| 28 	|    	|    	|
 * |   3   	|   	|    	|    	|  4 	| -4 	|    	|
 * |   4   	|   	|    	|    	|    	|  8 	| 17 	|
 * |   5   	|   	|    	|    	|    	|    	|  9 	|
 *
 * 1. The operation `5 - 8` goes to the cell `(i, j) = (0, 1) = -3`.
 * 2. The operation `8 + 7` goes to the cell `(i, j) = (1, 2) = 15`.
 * 3. The operation `7 * 4` goes to the cell `(i, j) = (2, 3) = 28`.
 * 4. The operation `4 - 8` goes to the cell `(i, j) = (3, 4) = -4`.
 * 5. The operation `8 + 9` goes to the cell `(i, j) = (4, 5) = 17`.
 *
 * What about the case when there are more than two elements?
 *
 * ## ----------------------- _**Key-Point: 8: Minimum And Maximum Table**_ -----------------------
 *
 * When there are more than two elements in an arithmetic expression, we can have multiple combinations.
 * Out of these combinations, there must be one combination that gives us the minimum result,
 * and another combination that gives us the maximum result.
 *
 * For example, we have already seen the following case:
 *
 * ```
 * (5 - 8) + 7 = 4 would give us the maximum result, whereas
 * 5 - (8 + 7) = -10 is the minium result.
 * ```
 *
 * So, how do we store these minimum and maximum values in two separate tables?
 *
 * As below:
 *
 * References:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/09minimumMaximum.png
 *
 * Minimum:
 *
 * | i / j 	| 0 	|  1 	|  2  	| 3 	| 4 	| 5 	|
 * |:-----:	|:-:	|:--:	|:---:	|:-:	|:-:	|:-:	|
 * |   0   	| 5 	| -3 	| -10 	|   	|   	|   	|
 * |   1   	|   	|  8 	|  15 	|   	|   	|   	|
 * |   2   	|   	|    	|  7  	|   	|   	|   	|
 * |   3   	|   	|    	|     	| 4 	|   	|   	|
 * |   4   	|   	|    	|     	|   	| 8 	|   	|
 * |   5   	|   	|    	|     	|   	|   	| 9 	|
 *
 * In the minimum table, the cell (i, j) = (0, 2) stores the minimum value of the expression `5 - 8 + 7`, which is
 * `5 - (8 + 7) = -10`.
 *
 * Maximum:
 *
 * | i / j 	| 0 	|  1 	|  2 	| 3 	| 4 	| 5 	|
 * |:-----:	|:-:	|:--:	|:--:	|:-:	|:-:	|:-:	|
 * |   0   	| 5 	| -3 	|  4 	|   	|   	|   	|
 * |   1   	|   	|  8 	| 15 	|   	|   	|   	|
 * |   2   	|   	|    	|  7 	|   	|   	|   	|
 * |   3   	|   	|    	|    	| 4 	|   	|   	|
 * |   4   	|   	|    	|    	|   	| 8 	|   	|
 * |   5   	|   	|    	|    	|   	|   	| 9 	|
 *
 * In the maximum table, the cell (i, j) = (0, 2) stores the maximum value of the expression `5 - 8 + 7`, which is
 * `(5 - 8) + 7 = 4`.
 *
 * Similarly, we can prepare the entire minimum and the maximum table for all the pairs of three elements as below:
 *
 * References:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/11allThreeElements.png
 *
 * Minimum:
 *
 * | i / j 	| 0 	|  1 	|  2  	|  3 	|  4  	|  5  	|
 * |:-----:	|:-:	|:--:	|:---:	|:--:	|:---:	|:---:	|
 * |   0   	| 5 	| -3 	| -10 	|    	|     	|     	|
 * |   1   	|   	|  8 	|  15 	| 36 	|     	|     	|
 * |   2   	|   	|    	|  7  	| 28 	| -28 	|     	|
 * |   3   	|   	|    	|     	|  4 	|  -4 	| -13 	|
 * |   4   	|   	|    	|     	|    	|  8  	|  17 	|
 * |   5   	|   	|    	|     	|    	|     	|  9  	|
 *
 *
 * Explanation:
 * Our expression is: `5 - 8 + 7 * 4 - 8 + 9`.
 *
 * Each element corresponds to a particular column index as below:
 *
 * ```
 * column 0 = element 5
 * column 1 = element 8
 * column 2 = element 7
 * column 3 = element 4
 * column 4 = element 8
 * column 5 = element 9
 * ```
 * So,
 * ```
 * 1. (i, j) = (0, 0) = 5
 * 2. (i, j) = (1, 1) = 8
 * 3. (i, j) = (2, 2) = 7
 * 4. (i, j) = (3, 3) = 4
 * 5. (i, j) = (4, 4) = 8
 * 6. (i, j) = (5, 5) = 9
 * ```
 * And each pair of two elements:
 * ```
 * 1. (i, j) = (0, 1) = 5 - 8 = -3
 * 2. (i, j) = (1, 2) = 8 + 7 = 15
 * 3. (i, j) = (2, 3) = 7 * 4 = 28
 * 4. (i, j) = (3, 4) = 4 - 8 = -4
 * 5. (i, j) = (4, 5) = 8 + 9 = 17
 * ```
 * Then, 3 elements and the minimum value from the possible combinations:
 * The expression is: `5 - 8 + 7 * 4 - 8 + 9`
 * ```
 * 1. (i, j) = (0, 2) = 5 - 8 + 7. Either it can be: 5 - (8 + 7) or (5 - 8) + 7. The minimum is 5 - (8 + 7) = -10.
 * 2. (i, j) = (1, 3) = 8 + 7 * 4. Either it can be: 8 + (7 * 4) or (8 + 7) * 4. The minimum is 8 + (7 * 4) = 36.
 * 3. (i, j) = (2, 4) = 7 * 4 - 8. Either it can be: 7 * (4 - 8) or (7 * 4) - 8. The minimum is 7 * (4 - 8) = -28.
 * 4. (i, j) = (3, 5) = 4 - 8 + 9. Either it can be: 4 - (8 + 9) or (4 - 8) + 9. The minimum is 4 - (8 + 9) = -13.
 * ```
 *
 * Similarly, the maximum table looks as shown below:
 *
 * Maximum:
 *
 * | i / j 	| 0 	|  1 	|  2 	|  3 	|  4 	|  5 	|
 * |:-----:	|:-:	|:--:	|:--:	|:--:	|:--:	|:--:	|
 * |   0   	| 5 	| -3 	|  4 	|    	|    	|    	|
 * |   1   	|   	|  8 	| 15 	| 60 	|    	|    	|
 * |   2   	|   	|    	|  7 	| 28 	| 20 	|    	|
 * |   3   	|   	|    	|    	|  4 	| -4 	|  5 	|
 * |   4   	|   	|    	|    	|    	|  8 	| 17 	|
 * |   5   	|   	|    	|    	|    	|    	|  9 	|
 *
 * We have already seen how each value for each single element and for each pair of two elements has been calculated.
 * We have also seen how the minimum values for each pair of 3 elements have been calculated and stored.
 *
 * Now, we will see how the maximum values for each pair of 3 elements have been calculated and stored.
 *
 * The expression is: `5 - 8 + 7 * 4 - 8 + 9`
 * ```
 * 1. (i, j) = (0, 2) = 5 - 8 + 7. Either it can be: 5 - (8 + 7) or (5 - 8) + 7. The maximum is (5 - 8) + 7 = 4.
 * 2. (i, j) = (1, 3) = 8 + 7 * 4. Either it can be: 8 + (7 * 4) or (8 + 7) * 4. The maximum is (8 + 7) * 4 = 60.
 * 3. (i, j) = (2, 4) = 7 * 4 - 8. Either it can be: 7 * (4 - 8) or (7 * 4) - 8. The maximum is (7 * 4) - 8 = 20.
 * 4. (i, j) = (3, 5) = 4 - 8 + 9. Either it can be: 4 - (8 + 9) or (4 - 8) + 9. The maximum is (4 - 8) + 9 = 5.
 * ```
 *
 * Now, the interesting part starts when there are more than 3 elements, or in other words,
 * when there are more than 2 combinations.
 *
 * | i / j 	| 0 	|   1   	|     2     	|       3       	|         4         	|           5           	|
 * |:-----:	|:-:	|:-----:	|:---------:	|:-------------:	|:-----------------:	|:---------------------:	|
 * |   0   	| 5 	| 5 - 8 	| 5 - 8 + 7 	| 5 - 8 + 7 * 4 	| 5 - 8 + 7 * 4 - 8 	| 5 - 8 + 7 * 4 - 8 + 9 	|
 * |   1   	|   	|   8   	|   8 + 7   	|   8 + 7 * 4   	|   8 + 7 * 4 - 8   	|   8 + 7 * 4 - 8 + 9   	|
 * |   2   	|   	|       	|     7     	|     7 * 4     	|     7 * 4 - 8     	|     7 * 4 - 8 + 9     	|
 * |   3   	|   	|       	|           	|       4       	|       4 - 8       	|       4 - 8 + 9       	|
 * |   4   	|   	|       	|           	|               	|         8         	|         8 + 9         	|
 * |   5   	|   	|       	|           	|               	|                   	|           9           	|
 *
 * Reference:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/13fourElements.png
 *
 * ```
 * 1. 5 - (8 + 7 * 4)
 *    1.1 -> 5 - (8 + (7 * 4)) = -31
 *    1.1 -> 5 - ((8 + 7) * 4) = -55 (minimum)
 *
 * 2. (5 - 8) + (7 * 4) = 25 (maximum)
 *
 * 3. ((5 - 8 + 7) * 4)
 *    3.1 -> (5 - (8 + 7)) * 4 = -44
 *    3.1 -> ((5 - 8) + 7) * 4 = 16
 * ```
 *
 * For example, `5 - 8 + 7 * 4 => (i, j) = (0, 3)`,
 * Now, we have already calculated and stored the possible subproblems of (i, j) = (0, 3).
 * Where? How?
 *
 * ```
 * 1. (i, j) = (0, 1) => 5 - 8 = -3. This is a part of one of the combinations, which is (5 - 8) + (7 * 4).
 * 2. (i, j) = (1, 2) => 8 + 7 = 15. This is a part of one of the combinations, which is 5 - (8 + 7) * 4.
 * 3. (i, j) = (2, 3) => 7 * 4 = 28. This is a part of one of the combinations, which is (5 - 8) + (7 * 4).
 * 4. We can see that we have already calculated the combination (5 - 8) + (7 * 4), and it gives us -3 + 28 = 25.
 * 5. Now, we need to calculate the remaining combinations and pick the minimum value.
 * 6. Interestingly, the expression `5 - (8 + 7) * 4` can be divided into two combinations further.
 * 7. `5 - (8 + 7) * 4` can either be (5 - (8 + 7)) * 4 = -10 * 4 = -44 or 5 - ( (8 + 7) * 4 ) = 5 - 60 = -55.
 * 8. Among all these combinations, the minimum value is clearly -55.
 * 9. Hence, the cell (i, j) = (0, 3) = -55.
 * 10. If we compare the minimum and the maximum tables, the value -55 results from `min(0, 0) - max(1, 3)`.
 *  10.1. That is, `5 - max(8 + 7 * 4)` = `5 - ( (8 + 7) * 4 )` = `5 - ( 15 * 4 )` = `5 - 60` = -55.
 * 11. This is one of the four combinations of our theory that we have seen in the beginning.
 * 12. Those four combinations are as follows:
 *  12.1. max1 operation max2
 *  12.2. max1 operation min2
 *  12.3. min1 operation min2
 *  12.4. min1 operation max2
 * ```
 *
 * Similarly, for `5 - 8 + 7 * 4 => (i, j) = (1, 4)`,
 * when we try all the combinations, we get the minimum value `-60`, and the maximum value `52`.
 *
 * How? How did we get those values? From which combinations? What did we do to get those combinations?
 * How did we divide?
 *
 * The mental math is that we consider each operation as the last one.
 * For example, the original expression is `5 - 8 + 7 * 4 - 8 + 9`,
 * and the range (i, j) = (1, 4) covers the expression `8 + 7 * 4 - 8`.
 *
 * ```
 * There are a total of 3 operations: `+, *, and -`.
 * 1. If `+` between `8 + 7` is the last operation, it becomes: `8 + (7 * 4 - 8)`.
 * We can divide that further into the following subproblems:
 * 1.1. If `*` between `7 * 4` is the last operation, it becomes: `8 + ( 7 * (4 - 8) ) = -20`.
 * 1.2. If `-` between `4 - 8` is the last operation, it becomes: `8 + ( (7 * 4) - 8 ) = 28`.
 * 2. If `*` between `7 * 4` is the last operation, it becomes: `(8 + 7) * (4 - 8) = -60. (**_minimum_**)
 * 3. If `-` between `4 - 8` is the last operation, it becomes: `(8 + 7 * 4) - 8`.
 * We can divide that further into the following subproblems:
 * 3.1. If `+` between `8 + 7` is the last operation, it becomes `( 8 + (7 * 4) ) - 8 = 28`.
 * 3.2. If `*` between `7 * 4` is the last operation, it becomes `( ( 8 + 7 ) * 4 ) - 8 = 52` (**_maximum_**).
 * ```
 *
 * So, the table looks as shown below:
 *
 * References:
 * 1. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 * 2. res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/17fourElementsTable.png
 *
 * The expression: `5 - 8 + 7 * 4 - 8 + 9`
 * Range: `(i, j) = (1, 4) = 8 + 7 * 4 - 8`.
 *
 * Minimum:
 *
 * | i / j 	| 0 	|  1 	|  2  	|  3  	|  4  	|  5  	|
 * |:-----:	|:-:	|:--:	|:---:	|:---:	|:---:	|:---:	|
 * |   0   	| 5 	| -3 	| -10 	| -55 	|     	|     	|
 * |   1   	|   	|  8 	|  15 	|  36 	| -60 	|     	|
 * |   2   	|   	|    	|  7  	|  28 	| -28 	|     	|
 * |   3   	|   	|    	|     	|  4  	|  -4 	| -13 	|
 * |   4   	|   	|    	|     	|     	|  8  	|  17 	|
 * |   5   	|   	|    	|     	|     	|     	|  9  	|
 *
 * Maximum:
 *
 * | i / j 	| 0 	|  1 	|  2 	|  3 	|  4 	|  5 	|
 * |:-----:	|:-:	|:--:	|:--:	|:--:	|:--:	|:--:	|
 * |   0   	| 5 	| -3 	|  4 	| 25 	|    	|    	|
 * |   1   	|   	|  8 	| 15 	| 60 	| 52 	|    	|
 * |   2   	|   	|    	|  7 	| 28 	| 20 	|    	|
 * |   3   	|   	|    	|    	|  4 	| -4 	|  5 	|
 * |   4   	|   	|    	|    	|    	|  8 	| 17 	|
 * |   5   	|   	|    	|    	|    	|    	|  9 	|
 *
 * If we fill each cell in both the minimum and the maximum tables, they look like as shown below:
 *
 * References:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/03maximumArithmeticExpression/21theCompleteTablesBothMinAndMaxForAllElements.png
 *
 * Minimum:
 *
 * | i / j 	| 0 	|  1 	|  2  	|  3  	|  4  	|   5  	|
 * |:-----:	|:-:	|:--:	|:---:	|:---:	|:---:	|:----:	|
 * |   0   	| 5 	| -3 	| -10 	| -55 	| -63 	|  -94 	|
 * |   1   	|   	|  8 	|  15 	|  36 	| -60 	| -195 	|
 * |   2   	|   	|    	|  7  	|  28 	| -28 	|  -91 	|
 * |   3   	|   	|    	|     	|  4  	|  -4 	|  -13 	|
 * |   4   	|   	|    	|     	|     	|  8  	|  17  	|
 * |   5   	|   	|    	|     	|     	|     	|   9  	|
 *
 * Maximum:
 *
 * | i / j 	| 0 	|  1 	|  2 	|  3 	|  4 	|  5  	|
 * |:-----:	|:-:	|:--:	|:--:	|:--:	|:--:	|:---:	|
 * |   0   	| 5 	| -3 	|  4 	| 25 	| 65 	| 200 	|
 * |   1   	|   	|  8 	| 15 	| 60 	| 52 	|  75 	|
 * |   2   	|   	|    	|  7 	| 28 	| 20 	|  35 	|
 * |   3   	|   	|    	|    	|  4 	| -4 	|  5  	|
 * |   4   	|   	|    	|    	|    	|  8 	|  17 	|
 * |   5   	|   	|    	|    	|    	|    	|  9  	|
 *
 * Ok. What do we do with these tables? How does that help? How do we convert them into code?
 *
 * ## ----------------------- Key-Point: 09: The pattern of filling the table -----------------------
 *
 * We can see that there is a pattern in the way we filled the table.
 * The pattern looks like this:
 *
 * ```
 * 1. First, we filled single elements.
 * 2. Then, we filled pairs of two elements.
 * 3. Then, we filled pairs of three elements.
 * 4. and so on...
 * ```
 *
 * ## ----------------------- Other important Observations: Key-Points -----------------------
 *
 * ```
 * 1. For the given range (i, j), we can split it into two subproblems as (i, k) and (k + 1, j).
 * 2. We need four combinations.
 * 3. We need two tables to store all four combinations' minimum and maximum values.
 * 4. When the values of i and j are the same, it indicates a single element.
 * 5. First, we fill single elements, then pairs of two, then pairs of three, and so on until we get one single pair
 * of the entire original problem.
 * 6. The maximum table cell (0, 5) gives the final maximum value.
 * It covers the complete, final, single pair of `n` elements where `n` is the number of `numbers` in the given
 * expression. For example, the expression `5 - 8 + 7 * 4 - 8 + 9` has six numbers. So, `n = 6`.
 * Hence, (0, 5) represents the maximum value for the single pair `5 - 8 + 7 * 4 - 8 + 9`,
 * which is the original problem.
 * ```
 *
 *
 *
 *
 *
 */
fun main() {


}