package courses.ucSanD.course01algorithmicToolbox.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
 *
 * References:
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/03maximumArithmeticExpression.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/05singleElementsInDiagonalCells.png
 * 3. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
 * 4. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/07twoElements.png
 * 5. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/09minimumMaximum.png
 * 6. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/11allThreeElements.png
 * 7. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/13fourElements.png
 * 8. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 * 9. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/17fourElementsTable.png
 * 10. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/21theCompleteTablesBothMinAndMaxForAllElements.png
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
 * # ----------------------- Thought Process & Translating it into the code: Part-01 -----------------------
 *
 * ## ----------------------- Objective: Understanding the pattern: -----------------------
 *
 * Suppose that we have the following input:
 *
 * ```
 * 5 - 8 + 7 x 4 - 8 + 9
 * ```
 *
 * ## ----------------------- _** 01: The Characteristics, Pattern, and Approach**_ -----------------------
 *
 * _**Prerequisites (A few examples and practice of dynamic programming):**_
 * * src/courses/ucSanD/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt
 * * src/courses/ucSanD/module05DynamicProgramming/module05ProgrammingAssignment01/090longestCommonSubsequenceOfTwoSequencesStrings.kt
 * * src/courses/ucSanD/module05DynamicProgramming/module05ProgrammingAssignment01/120lcsOfThreeSequences.kt
 * * src/courses/ucSanD/module05DynamicProgramming/module05puzzleAssignment02/010twoPilesOfRocks.kt
 * * src/courses/ucSanD/module05DynamicProgramming/module05puzzleAssignment02/030twoPilesOfRocksOops.kt
 * * src/courses/ucSanD/module06DynamicProgramming02/01knapsackProblem01EitherOr.kt
 *
 * _**Bottom-up**_
 *
 * To solve `5 - 8 + 7 * 4 - 8 + 9`, we can start with smaller subproblems and gradually increment them.
 *
 * For example,
 *
 * * `5 - 8`, and then, increase the size of the problem gradually till we reach the original problem, as below:
 * * `5 - 8 + 7`
 * * `5 - 8 + 7 * 4`
 * * `5 - 8 + 7 * 4 - 8`
 * * `5 - 8 + 7 * 4 - 8 + 9`
 *
 * This is a classical bottom-up characteristic and pattern of a dynamic programming approach.
 *
 * ## ----------------------- 02: Classification of Operators and Digits (Numbers) -----------------------
 *
 * We have a single string containing operators and digits (numbers).
 * If we separate operators and digits (numbers) into separate containers, they become easy to use and manage.
 *
 * * A Reflective Question:
 * **_How do we separate symbols and digits (numbers)?_**
 *
 * Well, the problem statement says:
 *
 * ```
 * Each symbol at an `even position` of s is a `digit` (that is, an integer from 0 to 9),
 * while each symbol at an `odd position` is one of three operations from `{+, -, *}`.
 * ```
 *
 * * A Reflective Question:
 * _**How do we find odd and even?**_
 *
 * ```
 * if (i % 2 == 0) {
 *    // Even position
 * } else {
 *    // Odd position
 * }
 * ```
 *
 * * A Reflective Question:
 * _**What do we do after finding odd and even positions?**_
 *
 * At even positions, we would find digits and add them to the digit container.
 * At odd positions, we would find operators (symbols) and add them to the operator container.
 *
 * * A Reflective Question:
 * _**What will be the size of each container?**_
 *
 * According to the problem statement:
 *
 * ```
 * The only line of the input contains a `string s` of `length 2n+ 1` for `some n, with symbols s0,s1,...,s2n`.
 * Each symbol at an `even position` of s is a `digit` (that is, an integer from 0 to 9),
 * while each symbol at an `odd position` is one of three operations from `{+, -, *}`.
 * ```
 *
 * **_Math_**
 *
 * The length of the string is `2n + 1`,
 * where each character at an `even position` is a digit, and each character at an `odd position` is an operator.
 *
 * We have an example: `5 - 8 + 7 * 4 - 8 + 9`
 *
 * We can see that there are 6 digits, and 5 operators, making it a total length of `2n + 1 = 2(5) + 1 = 10 + 1 = 11`.
 * So, we can say that:
 *
 * ```
 * operators = digits - 1 or digits = operators + 1.
 * ```
 *
 * Hence,
 *
 * ```
 * digits + operators = 2n + 1 (total length of the given string)
 * digits + (digits - 1) = 2n + 1
 * 2digits - 1 = 2n + 1
 * 2digits = (2n + 1) + 1
 * 2digits = length + 1
 * digits = (length + 1) / 2
 *
 * For example: 5-8+7*4-8+9
 * Length is: 11
 * digits = (length + 1) / 2 = (11 + 1) / 2 = 12 / 2 = 6
 * operators = digits - 1 = 6 - 1 = 5
 * ```
 *
 * According to the problem statement, we can take the size of the digit container as `(length + 1) / 2`.
 *
 * So, for the given example, it will be `(11 + 1) / 2` = `12 / 2` = `6`.
 * And the operator container will be `size of the digit container - 1 = 6 - 1 = 5`.
 *
 * ```
 * val expressionLength = expressionString.length
 * val totalDigits = (expressionLength + 1) / 2
 * val digitContainer = IntArray(totalDigits)
 * val operatorContainer = IntArray(digitContainer.size - 1)
 *
 * for (i in 0..<expressionLength) {
 *     if (i % 2 == 0) {
 *        digitContainer.add(expressionString[i].toInt()) // But string[i] gives a char, and char.toInt() is deprecated!
 *     } else {
 *        operatorContainer.add(expressionString[i])
 *     }
 * }
 * ```
 *
 * As we can see, there is a problem!
 * `string[i]` gives a `char`, and we cannot directly convert a `char` into an `int`.
 *
 * So,
 *
 * * A Reflective Question:
 * _**How do we convert a `char` into an `int`?**_
 *
 * ```
 * // When there is a single `char` and we want to convert it into an `int`.
 * char - '0'
 * // When there is a single `char` and we want to convert it into an `int`.
 * char.digitToInt() // Throws exception if the `char` is not a `digit`.
 * ```
 *
 * * A Reflective Question:
 * _**How do we convert multiple characters that form multiple digits as a single, whole number into an int?**_
 *
 * For example, when we have a single character digit, such as anything between `0 to 9`, we can use:
 *
 * ```
 * Either char.digitToInt() or char - '0'
 * ```
 *
 * But what if we have something like: `45`?
 *
 * Well, in that case, we still convert each character into a digit one by one.
 *
 * For example, let us assume that we have a string as: `abc45def`.
 * Now, when we read the string character by character, and extract a digit character by character,
 * we get `4` and `5`. How do we convert it into `45`?
 *
 * Well, let us understand the number, `45`.
 *
 * ```
 * 45 = It is 4 (the previously extracted digit) * 10 + 5 (currently extracted digit)
 * ```
 *
 * Let us take another example: `abc802def`.
 *
 * ```
 * Extract: `8`
 * Extract: `0`, but `80` are together, one after another, multiple digits, but one whole number.
 * So, last extracted number (8 * 10) + 0 = 80.
 * Wait, there is still a digit. Extract: `2`.
 * Hmm, last extracted number `(80 * 10) + 2` = `800 + 2` = `802`.
 * ```
 *
 * * A Reflective Question:
 * **_Ok. What if there is a character between two whole numbers?_**
 *
 * For example: `abc802def45ghi`
 *
 * Well, in that case also, once we find that the whole number is over, we reset the previously calculated number.
 *
 * ```
 * After extracting 802, we find that the next character is not a digit.
 * So, we reset the previously calculated whole number value to 0.
 * Hence, next time when we face the digit `4`, the previous number is `0`.
 * So, it will be `(previously extracted number 0 * 10) + currently extracted number 4` = `0 + 4` = `4`.
 * Next, when we extract `5`, the previously extracted number is `4`.
 * Hence, `(previously extracted number 4 * 10) + 5` = `45`.
 * Again, the next character is not a digit. So again, we reset the previously calculated whole number to 0.
 * And so on...
 * ```
 *
 * **_Translating the explanation into The Code_**
 *
 * So, as long as the next character is still a digit, we can do:
 *
 * ```
 * for (i in 0..<string.length) {
 *     if (string[i].isDigit()) {
 *        var num = 0
 *        while(i < string.length && string[i].isDigit()) {
 *              num = num * 10 + string[i].digitToInt()
 *              i++
 *        }
 *        digits.add(num)
 *     } else {
 *        operators.add(string[i])
 *     }
 * }
 * ```
 *
 * * A Reflective Question:
 * **_ Is there a better way than iteration to classify digits and operators? _**
 *
 * **_ RegEx: The Alternative _**
 * ```
 * val string = "5 - 8 + 7 * 4 - 8 + 9"
 * // Segregate (classify, separate, extract) digits.
 * Regex("\\d+").findAll(string).map { it.value.toInt() }.toList()
 * = [5, 8, 7, 4, 8, 9]
 * // Classify operators.
 * Regex("[+\\-*]").findAll(expr).map { it.value }.toList() //
 * = [-, +, *, -, +]
 * ```
 *
 * **_ Understanding the RegEx _**
 *
 * ```
 * Anything between the " " is our RegEx.
 * \\d is a RegEx shorthand to match a digit between 0 and 9.
 * \\d matches any single digit character. That is to say, anything between 0 and 9.
 * + indicates one or more of the preceding items.
 * \\d+ One or more digits.
 * So, "\\d+" matches and extracts one or more digits like 5, 802, 45, etc.
 * ```
 *
 * **_What about operators? How do we extract and segregate operators using Regex?_**
 *
 * Similarly,
 *
 * ```
 * [ .. ] is a character class in RegEx. It matches any one character inside the brackets.
 * What are the different characters we want to match? +, -, and *.
 * So, we put them inside the bracket.
 * [+-*]
 * However, +, -, and * are special characters with distinct meanings in RegEx.
 * However, inside the character class brackets, the symbols + and * act as literals only.
 * But, the symbol - remains a special range operator in the character class brackets.
 * Hence, we need to escape -.
 * So, it becomes:
 * [+\\-*]
 * It matches the symbols: +, -, and *
 * ```
 *
 * So, if we use a `Regex` instead of an iteration, it becomes:
 *
 * ```
 * val string = "5 - 8 + 7 * 4 - 8 + 9" //or `readln()`
 * val digits = Regex("\\d+").findAll(string).map { it.value.toInt() }.toList()
 * val operators = Regex("[+\\-*]").findAll(string).map { it.value }.toList()
 * ```
 *
 * What a concise piece of code!
 *
 * **_ What if we have fraction numbers along with whole numbers? _**
 *
 * We can adjust the `Regex` based on requirements.
 * For example, if we want to match and extract fraction numbers along with whole numbers, the `Regex` can be:
 *
 * ```
 * val string = "5 - 8.5 + 75 * 4 - 108 + 9"
 * val digits = Regex("\\d+\\.\\d+").findAll(string).map { it.value.toDouble }.toList()
 * [5, 8.5, 75, 4, 108, 9]
 * ```
 *
 * Note: The dot (.) is a special character (shorthand) in Regex, and it signifies "any character."
 * Hence, if we want to match the literal dot (.), we have to escape it.
 *
 * **_ Back to the containers for Digits and Operators _**
 *
 * Before extracting and classifying digits and operators, we were discussing the size of their relevant containers.
 *
 * Well, if we go with the manual iteration instead of `Regex`, we can take a mutable list.
 * Because even though the problem statement says that a digit will be from `0 to 9`,
 * We should make it flexible enough to handle cases with multiple digits.
 *
 * For example, `5 - 82 + 750 * 45 - 8 + 9`
 *
 * So, it can be:
 *
 * ```
 * val digits = mutableListOf<Int>()
 * val operators = mutableListOf<Char>()
 *
 * for (i in 0..<string.length) {
 *     if (string[i].isDigit()) {
 *        var num = 0
 *        while (i < string.length && string[i].isDigit()) {
 *              num = (num * 10) + string[i].digitToInt()
 *              i++
 *        }
 *        digits.add(num)
 *     } else {
 *        operators.add(string[i])
 *     }
 * }
 * ```
 *
 * But if we go with the `Regex`, it becomes very short and clear as below:
 *
 * ```
 * val string = "5 - 8 + 7 * 4 - 8 + 9"
 * val digits = Regex("\\d+").findAll(string).map { it.value.toInt() }.toList() // [5, 8, 7, 4, 8, 9]
 * val operators = Regex("[+\\-*]").findAll(string).map { it.value }.toList() // [-, +, *, -, +]
 * ```
 *
 * * A Reflective Question:
 * **_ Ok. What do we do after segregating and classifying digits and operators? _**
 *
 * # ----------------------- Thought Process & Translating it into the code: Part-02 -----------------------
 *
 * ## --------- **_Key-point: 1: The last operation that splits the expression_** -----------
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
 * If `5 - 8 + 7 * 4 - 8 + 9` is a range of indices starting from `i to j`,
 * and the last operation Op_k = *, then:
 *
 * ```
 * For the given range (i, j), where i < j, we can split the expression into two subproblems as (i, k) and (k + 1, j).
 * ```
 *
 * In our example:
 *
 * The expression is `5 - 8 + 7 * 4 - 8 + 9`,
 * The range is from `i` to `j`,
 * The last operation for a moment (assumption) is `op_k = *`,
 * Which splits the original expression into two parts,
 * `(i, k)` = `5 - 8 + 7`, and
 * `(k + 1, j)` = `4 - 8 + 9`.
 *
 * ## ----------------------- _**Key-point: 2: Maximizing an expression**_ -----------------------
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
 * ## ----------------------- _**Key-point: 3: Max or Min? The Subtraction Example. **_ -----------------------
 *
 * However, knowing or considering only the maximum value is not enough. Why?
 * Because, if the last operation was subtraction, then we would want the subexpression one (minuend)
 * to be as large as possible, but the subexpression two (subtrahend) should be as small as possible to produce
 * the maximum value of the arithmetic operation. Right?
 *
 * ## ----------------------- _**Key-point: 4: The four combinations**_ -----------------------
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
 * ## ----------------------- _**Key-point: 5: What do a row, column, and a cell represent? **_ ------------------
 *
 * What happens when we place all the elements into a 2D-Matrix Table?
 *
 * Reference:
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/05singleElementsInDiagonalCells.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
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
 * **_Translation into the Code_**
 *
 * ```
 * // n is the number of digits
 * // We can also take `number of operators + 1` to include the case when there is no operator.
 * // Because we have seen that `the number of operators = the number of digits - 1`.
 * // Or in other words, `the number of digits = the number of operators + 1`.
 * val minTable = Array(n) { IntArray(n) }
 * val maxTable = Array(n) { IntArray(n) }
 * for (i in 0..<n) {
 *     minTable[i][i] = digits[i]
 *     maxTable[i][i] = digits[i]
 * }
 * ```
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
 * res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/06cellSignificance.png
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
 * 1. Each row signifies (represents) the starting index (digit, number) of a sub-expression.
 * 2. Each column signifies (represents) the end index (digit, number) of a sub-expression.
 * ```
 *
 * For example (Focus on the respective index of each digit/number of an expression):
 * * The `cell(0, 0)` represents a single number `5` (the smallest possible sub-expression = the base case).
 * * The `cell(5, 5)` represents a single number `9`.
 * * The `cell(0, 1)` represents a sub-expression `5 - 8`.
 * * The `cell(4, 5)` represents a sub-expression `8 + 9`.
 * * The `cell(0, 2)` represents a sub-expression `5 - 8 + 7`.
 * * The `cell(3, 5)` represents a sub-expression `4 - 8 + 9`.
 * * The `cell(0, 3)` represents a sub-expression `5 - 8 + 7 * 4`.
 * * The `cell(2, 5)` represents a sub-expression `7 * 4 - 8 + 9`.
 * * And so on...
 *
 * ## ----------------------- _**Key-point: 6: Two Tables**_ -----------------------
 *
 * Now, if you remember, we have concluded that we need both the maximum and minimum values.
 * Hence, we will use two tables. One table for the minimum values, and the other for the maximum values.
 *
 * **_We have filled in the base cases for each table. But how do we fill the entire table?_**
 *
 * The next point explains the same.
 *
 * ## ---------- _**Key-point: 7: The Process: Filling Up The Two Tables: Part-01: Introduction. **_ ----------
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
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/07twoElements.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/08allTwoElements.png
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
 * ## --------------- _**Key-Point: 8: Filling Up The Two Tables: Part-02: Minimum & Maximum Values **_ -------------
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
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/09minimumMaximum.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/11allThreeElements.png
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
 * res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/11allThreeElements.png
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
 * References:
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/13fourElements.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 * 3. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/17fourElementsTable.png
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
 * Observe that:
 *
 * ```
 * We consider each operator as the last operator for the same expression.
 * Then, we evaluate all the results and update the minimum and maximum values accordingly.
 * Finally, we assign these minimum and maximum values to the respective (relevant) tables.
 * ```
 *
 * So, the table looks as shown below:
 *
 * References:
 * 1. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 * 2. res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/17fourElementsTable.png
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
 * If we fill each cell in both the minimum and the maximum tables, they look as shown below:
 *
 * References:
 * res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/21theCompleteTablesBothMinAndMaxForAllElements.png
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
 * ## ------------------ Key-Point: 09: Filling Up The Tables: Part-03: The Code Conversion -------------------
 *
 * ### ----------------------- Chapter 01: The Operator Iteration < n -----------------------
 *
 * We can see that there is a pattern in the way we filled the table.
 * The pattern looks like this:
 *
 * ```
 * 1. First, we filled single elements. There was no operator.
 * 2. Then, we filled pairs of two elements. There was `1` operator between the two numbers.
 * 3. Then, we filled pairs of three elements. There were `2` operators among the three numbers.
 * 4. and so on...
 * ```
 *
 * In other words, we can say that:
 *
 * ```
 * We start with each and every single element, where there is no other element or operator.
 * In that case, the length of the number of operators included is 0, as it contains no operators.
 *
 * Then, we gradually increase the length of the number of operators to be included (considered).
 * For example, first, the length of the number of operators included is 1, so we filled all the pairs of two elements.
 *
 * Then, for length 2, we filled all the pairs of three elements.
 * And so on...
 *
 * We do this until we cover all the operators.
 *
 * The `length` indicates the number of operators we are considering.
 * Each operator splits the problem into a pair.
 * And each pair is a subproblem.
 * ```
 *
 * It further leads to the following pattern:
 *
 * ```
 * We start with the operator iteration. The iteration that gradually covers each operator one by one,
 * starting from `length = 1` up to the `length = equal to number of operators` available in the original expression.
 * ```
 *
 * There is one more observation related to the length of the operators we include.
 *
 * ```
 * The length of the number of operators included defines the length of a sub-expression.
 * ```
 *
 * For example, when `length = 1`, the length of a sub-expression becomes 2.
 * For example, `5 - 8`, `8 + 7`, `7 * 4`, `4 - 8`, `8 + 9`, etc.
 *
 * Similarly, when `length = 2`, the length of a sub-expression becomes 3.
 * For example, `5 - 8 + 7`, `8 + 7 * 4`, `7 * 4 - 8`, `4 - 8 + 9`, etc.
 *
 * And so on...
 *
 * Now, the original expression is: `5 - 8 + 7 * 4 - 8 + 9`
 * We can see that there are `n = 6 digits` and `n - 1 = 6 - 1 = 5` operators.
 * Hence, the range of the `operator iteration` is from `1 to n (exclusive)` or from `1 to n - 1 (inclusive)`.
 *
 * So, we can say that:
 *
 * ```
 * The upper-bound (end) index of the `operator iteration` will be `n` (exclusive) or `n - 1` (inclusive).
 * ```
 *
 * Hence, the `operator iteration` looks as shown below:
 *
 * ```
 * // Operator iteration that defines the length of a sub-expression.
 * for (length in 1..<n) {
 *
 * }
 * ```
 *
 * ### ----------------------- Chapter 02: The Numbers (Digits) Iteration < n - length  -----------------------
 *
 * Now, within the operator iteration, we make pairs because each operator splits the expression.
 * To make pairs, we need to iterate through the numbers to pick up the numbers.
 *
 * For example, the expression is `5 - 8 + 7 * 4 - 8 + 9`.
 * The operator iteration starts from length `1` and goes up to `n` (exclusive) or `n - 1` (inclusive).
 *
 * Let us assume that the current length is `1`.
 * It means, as we discussed earlier, there will be `1` operator.
 * It will form pairs of two elements. 1 element left and 1 right of the operator.
 *
 * How do we make pairs? A pair comprises digits, and digits are the parts of the expression.
 *
 * The expression is: `5 - 8 + 7 * 4 - 8 + 9`.
 *
 * It means that we need to cover all the digits (numbers) to make pairs of digits.
 *
 * Let us call the iteration that covers all the digits (numbers), the `numbers iteration` or `digits iteration`.
 *
 * Now, we want to cover each digit (number).
 * So, we might think that we would start from 0 and go up to `n - 1` = `6 - 1` = `5`.
 * However, if we go up to `n - 1` = index `5`, then what will be the pair?
 * For example, if we go up to `index 5` = `9`, what will be the next element to pair with it?
 *
 * Then, up to which point can the `number iteration` go?
 * What will be the `upper bound (end index)` of the `number iteration`?
 *
 * Now, the expression is: `5 - 8 + 7 * 4 - 8 + 9`.
 * The total number of digits in the expression is `n = 6`.
 *
 * When the length is 1, we could go up to index `4` in the `number iteration` to make the last pair `[8, 9]`.
 * The upper-bound index `4` (inclusive) is less than `n - length` = `6 - 1` = `5` (exclusive).
 *
 * If the length is 2, we can go up to index `3` in the `number iteration` to make the last pair `[4, 8 9]`.
 * Again, the upper-bound index `3` (inclusive) is less than `n - length` = `6 - 2` = `4` (exclusive).
 *
 * If the length is 3, we can go up to index `2` in the `number iteration` to make the last pair `[7, 4, 8, 9]`.
 * Again, the upper-bound index `2` (inclusive) is less than `n - length` = `6 - 3` = `3` (exclusive).
 *
 * If the length is 4, we can go up to index `1` in the `number iteration` to make the last pair `[8, 7, 4, 8, 9]`.
 * Again, the upper-bound index `1` (inclusive) is less than `n - length` = `6 - 4` = `2` (exclusive).
 *
 * If the length is 5, we can go up to index `0` in the `number iteration` to make the last pair `[5, 8, 7, 4, 8, 9]`.
 * Again, the upper-bound index `0` (inclusive) is less than `n - length` = `6 - 5` = `1` (exclusive).
 *
 * So, we can say that:
 *
 * ```
 * The upper-bound index (end-index) of the `number iteration` will be `n - length` (exclusive).
 * ```
 *
 * Hence, the `number iteration` looks as shown below:
 *
 * ```
 * // Operator iteration that defines the length of a sub-expression
 * for (length in 1..<n) {
 *     // Number (digits) iteration that defines the start-index of a sub-expression
 *     for (i in 0..<n-length) {
 *         // Stop index (end-index) for each pair?
 *     }
 * }
 * ```
 *
 * ### ----------------------- Chapter: 03: The Stop-Index Of A Pair: j = i + length  -----------------------
 *
 * Now, let us understand how many "number" elements each pair includes based on the `length` value,
 * and what the end index will be for each pair.
 *
 * Well, there is always `+1` numbers than the length of the operators included.
 *
 * For example, when the length is 1, each pair (subproblem) contains 2 elements.
 * For example, `5 + 8`, `8 - 7`, `7 * 4`, `4 - 8`, and `8 + 9`.
 * In the form of indices, it is `[0, 1], [1, 2], [2, 3], [3, 4], and [4, 5]`.
 *
 * Notice that we iterate through all the numbers to make pairs based on the `length` value.
 * Within this number iteration, the starting index is gradually increasing from 0.
 *
 * For example, in `[0, 1]`, the start index is `0`.
 * For `[1, 2]`, the start index is `1`.
 * For `[2, 3]`, the start index is `2`.
 * And so on...
 *
 * Let us understand how we decide the end index (upper bound) for each pair.
 *
 * In other words (reflective questions):
 *
 * For the original expression: `5 - 8 + 7 * 4 - 8 + 9`
 *
 * What to do when length is 1, start index is 0, and we want to stop at index 1 to form a pair of `[0, 1]`?
 * Well, let us use the data (values) we have. Length = 1 and start index i = 0.
 * Can we say that the desired (expected) stop index (1) = i (current start index = 0) + length (1) = 1?
 * It seems true for `[0, 1]`. But we need to verify it for other cases also.
 *
 * What to do when length is 1, start index is 1, and we want to stop at index 2 to form a pair of `[1, 2]`?
 * Again, stop index 2 = Current start index (i = 1) + length (1) = 2. Looks good.
 *
 * What to do when length is 1, start index is 2, and we want to stop at index 3 to form a pair of `[2, 3]`?
 * Again, stop index 3 = Current start index (i = 2) + length (1) = 3. It worked.
 *
 * It seems that the stop index for a pair is `i + length`. We will verify it for different lengths.
 *
 * What to do when length is 2, start index is 0, and we want to stop at index 2 to form a pair of `[0, 1, 2]`?
 *
 * Again, when the length is 2, each pair of subproblems contains 3 elements.
 * For example, `5 + 8 - 7`, `8 - 7 * 4`, `7 * 4 - 8`, `4 - 8 + 9`.
 * In the form of indices, it is `[0, 1, 2], [1, 2, 3], [2, 3, 4], [3, 4, 5]`.
 *
 * Again, the upper bound (end-index, inclusive) for each pair is `i + length` during the iteration.
 * For example, when i = 0, we get `0 (i) + 2 (length)` = `2 (inclusive stop index)` up to 2nd index = [0, 1, 2].
 *
 * The last index that formed the last pair is index 3 with the last pair [3, 4, 5].
 * The iteration of making pairs stopped at index 3 when the length is 2.
 * Because, if we consider index 4, there are not enough `number` elements left to make a pair of 3.
 *
 * So again, the stop index for each pair is `i + length` = `3 + 2` = `5`.
 *
 * Similarly:
 * What to do when length is 3, start index is 0, and we want to stop at index 3 to form a pair of `[0, 1, 2, 3]`?
 * Again, the stop index `i + length` = `0 + 3` = `3`.
 *
 * And so on...
 *
 * So, we can say that:
 *
 * ```
 * The end index of each pair is `i + length` (inclusive).
 * ```
 *
 * So, what will be the code of this iteration?
 *
 * ```
 * // Operator iteration that defines the length of a sub-expression.
 * for (length in 1..<n) {
 *     // Numbers (Digits) iteration that defines the start-index of a sub-expression.
 *     for (i in 0..<n - length) {
 *         // Stop index of each pair.
 *         val j = i + length
 *         // A subexpression that includes operator(s).
 *
 *     }
 * }
 * ```
 *
 * ### ----------------------- Chapter: 04: Include Operators: < j -----------------------
 *
 * Now, a sub-expression consists of both the number(s) (digits) and operator(s).
 * I mean, to call the pair `[5, 8]` a sub-expression, it must include the operator between them, right?
 * So that it will become a sub-expression as `(5 - 8)`.
 *
 * How do we pick up and include the operator(s)?
 *
 * Let us understand this with a tiny (toy) example.
 *
 * The original expression is: `5 - 8 + 7 * 4 - 8 + 9`
 *
 * Let us analyze the indices of a small sub-expression `5 - 8`.
 * The length of the number of operators to be included is `1`.
 * The `start index` of the sub-expression is `0`.
 * The `end (stop) index (inclusive)` of the pair is `i + length` = `1`.
 * The start index of the operator is `k = start index of the sub-expression = 0`.
 * The last operator is just before (exclusive) the stop(end) index of the pair (`1`).
 * The last operator index is `0`, which is `< stop-index of the pair`.
 *
 * Let us take another example.
 *
 * Let us assume that the sub-expression is `8 + 7 * 4`.
 * The length of the number of operators to be included is `2`.
 * The start-index of the sub-expression is `1`.
 * The start-index of the operator is `k = start index of the sub-expression = 1`.
 * The stop-index (inclusive) of the pair is `i + length` = `0 + 2` = `2`.
 * The last operator is at index just before (exclusive) the stop-index of the pair (`2`).
 * The last operator index is `1`, which is `< stop-index of the pair`.
 *
 * Let us take one last example.
 *
 * Let us assume the sub-expression is `7 * 4 - 8 + 9`.
 * Here, the length of the number of operators to be included is `3`.
 * The start-index of the sub-expression is `2`.
 * The start-index of the operator is `k = start index of the sub-expression = 2`.
 * The stop-index of the pair is `i + length` = `2 + 3` = `5`.
 * The last operator is at index just before (exclusive) the stop-index of the pair (`5`).
 * The last operator is at index `4`, which is again, `< stop-index of the pair`.
 *
 * So, can we say that:
 *
 * ```
 * The start-index of the operator is `k = start-index of the sub-expression`,
 * and the stop-index of the operator is `k < stop-index of the pair`.
 * ```
 *
 * In other words,
 *
 * ```
 * The range of the iteration that includes the operator(s) starts at the same start-index of the sub-expression,
 * and stops before the stop-index of the pair.
 * ```
 *
 * So, the code translation is:
 *
 * ```
 * // Operator iteration that defines the length of a sub-expression
 * for (length in 1..<n) {
 *     // Number (Digit) iteration that defines the start-index of a sub-expression
 *     for (i in 0..<n - length) {
 *         // Stop-index of a pair
 *         j = i + length
 *         // Sub-expression iteration that includes operator(s)
 *         for (k in i..<j) {
 *             // Different combinations of the sub-expression using the operator at index k as a split point.
 *         }
 *     }
 * }
 * ```
 *
 * Ok, what's next?
 *
 * ### ----------------------- _**Chapter: 05: Calculating The Four Combinations**_  -----------------------
 *
 * The original expression is: `5 - 8 + 7 * 4 - 8 + 9`
 *
 * Let us assume that at some point, we have a sub-expression as below:
 *
 * The sub-expression is: `8 + 7 * 4 - 8`.
 * The length of the number of operators included is `3`.
 * The start-index of the sub-expression is `1`.
 * The end-index of the sub-expression is `4`.
 * The start-index of the operator iteration will be the same as the start-index of the sub-expression, which is `1`.
 * The innermost iteration, which splits the current sub-expression into different combinations,
 * starts with the operator at the same index as the start-index of the sub-expression, which is `index 1 = +`,
 * and continues the iteration to form different combinations until the operator reaches the index,
 * just before (exclusive) the end-index of the pair (sub-expression).
 *
 * What does this mean?
 *
 * The innermost iteration will cover the operators `index 1 = +`, `index 2 = *`, and `index 3 = -` in order.
 *
 * What does that mean? How does it split the sub-expression?
 *
 * Each selection of an operator signifies that the currently selected operator is the last operator.
 * Each selected operator acts as a split point to form different combinations.
 *
 * For example, when the operator is `index 1 = +`, it splits the sub-expression as `8 + (7 * 4 - 8)`.
 * Similarly, when the operator is `index 2 = *`, it splits the sub-expression as `8 + 7 * (4 - 8)`.
 * And finally, when the operator is `index 3 = -`, it splits the sub-expression as `(8 + 7 * 4) - 8`.
 *
 * We can see that, as it happens in any classical Dynamic Programming,
 * Each part we get after splitting the subexpression is obviously smaller than the subexpression, and
 * It is something we have already computed.
 *
 * For example, when the `+` operator becomes the split point, we get `8 + (7 * 4 - 8)`.
 * Here, each part is obviously smaller than the original subexpression `8 + 7 * 4 - 8`.
 * Also, each part, `8` and `7 * 4 - 8`, has been computed already.
 *
 * When did we compute each part before splitting the subexpression? How?
 *
 * Well, we know that after handling the base case where there is only a single element, and no operator,
 * We started with the `length = 1`, where the `length` signifies the number of operators to include.
 * Then, we gradually increased the length up to `< n`.
 *
 * ```
 * // Operator length iteration that defines the length of a sub-expression
 * for (length in 1..<n) {
 *     // Number (Digit) iteration that defines the start-index of the sub-expression
 *     for (i in 0..<n - length) {
 *         // Stop-index of each sub-expression pair
 *         val j = i + length
 *         // Split-point iteration to form different combinations
 *         for (k in i..<j) {
 *
 *         }
 *     }
 * }
 * ```
 *
 * It means that, before we compute the example sub-expression `8 + 7 * 4 - 8`, where the `operator length` is `3`,
 * We must have computed all combinations with `operator length` 0 (base case), 1, and 2.
 *
 * Ok. How do we use these pre-computed results of smaller subproblems to solve the larger subproblem or problem?
 *
 * Or, in other words, where do we find these pre-computed results of the smaller subproblems?
 * So that we can use it to solve the larger subproblem or problem.
 *
 * Well, where do we store the result? In the `Minimum` and `Maximum` tables only, right?
 * And we have already seen it before; in which cell we store the results for a particular expression.
 *
 * For example, we have the sub-expression `8 + 7 * 4 - 8`,
 * And at the moment, the `+` operator is the last operator that splits the sub-expression.
 * So, we get two sub-expressions as `8` and `7 * 4 - 8`.
 *
 * Now, the original expression is: `5 - 8 + 7 * 4 - 8 + 9`.
 * The total numbers (digits) are: [5, 8, 7, 4, 8, 9].
 * The total operators are: [-, +, *, -, +].
 *
 * Now, the sub-expression in the hand is: `8 + 7 * 4 - 8`.
 * So, the current length of the number of operators to include is: `length = 3`.
 * The start-index of the sub-expression is `i = 1`.
 * The end-index is `j = i + length = 1 + 3 = 4`.
 * That is why `8` of `4 - 8` is the last number (digit) in the sub-expression `8 + 7 * 4 - 8`.
 * The range of operators that can split the sub-expression is `k in i..<j`.
 * It means the operators that can split the sub-expression are `k in 1..<4` = `+, *, and -`.
 * The index range of these operators is [1, 2, 3].
 * Currently, the split operator is at index `k = 1 = +`,
 * we get the left part `8`, which is stored at `cell(1, 1)`,
 * and we get the right part `7 * 4 - 8`, which is stored at `cell(2, 4)`.
 *
 * References:
 * res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/21theCompleteTablesBothMinAndMaxForAllElements.png
 *
 * So, can we say that the left part is `cell(i, k)` = `cell(1, 1)`,
 * and the right part is `cell(k + 1, j)` = `cell(2, 4)`?
 *
 * Let us confirm (verify) it for a couple more examples.
 *
 * The next split operator in the process is at index `k = 2 = *`.
 * We get the left part `8 + 7`, which is stored at `cell(1, 2)`,
 * and we get the right part `4 - 8`, which is stored at `cell(3, 4)`.
 *
 * Again, the left part `cell(1, 2)` = `cell(i, k)`,
 * and the right part `cell(3, 4)` = `cell(k + 1, j)`.
 *
 * Let us take one last example.
 *
 * The split operator is at index `k = 3 = -`.
 * The left part is `8 + 7 * 4`, which is stored at `cell(1, 3)`,
 * and the right part is `8`, which is stored at `cell(4, 4)`.
 *
 * Again, the left part `cell(1, 3)` = `cell(i, k)`,
 * and the right part `cell(4, 4)` = `cell(k + 1, j)`.
 *
 * It means that to get the left part, we can use `cell(i, k)`,
 * and to get the right part, we can use `cell(k + 1, j)`.
 *
 * So,
 *
 * ```
 * To get the left part: cell[i, k],
 * To get the right part: cell[k + 1, j].
 * ```
 *
 * What do we do after getting these parts?
 *
 * If you remember, we need to use the four combinations to get the higher value for the expression we split.
 *
 * Each part has two values:
 * The minimum value, stored in the `Minimum` table,
 * and the maximum value, stored in the `Maximum` table.
 *
 * So, it becomes:
 *
 * ```
 * min[i, k], max[i, k],
 * min[k + 1, j], max[k + 1, j].
 * ```
 *
 * The combination of these four values can give us two values for the expression we split:
 * The Minimum Value and The Maximum Value.
 *
 * Once we get these minimum and maximum values for each combination of `8 + 7 * 4 - 8`,
 * We move on to the next sub-expression, which can be: `7 * 4 - 8 + 9` for `length = 3`.
 *
 * We cover all possible combinations for `length = 3, 4, 5`,
 * And finally get the result in `cell(0, 5)` of the maximum table.
 * Because the `cell(0, 5)` of the maximum table represents the maximum value when `length = 5`,
 * And the start index is `0`. So, it is `5 - 8 + 7 * 4 - 8 + 9`, which is the original expression.
 *
 * Wait. How do we use those four combinations? And what happened to the split point operator itself?
 *
 * ### ----------------------- Chapter: 06: Split point operator -----------------------
 *
 * Once we have the split point operator and the four combinations, we calculate the values as below:
 *
 * ```
 * min(i, k) op min(k + 1, j),
 * min(i, k) op max(k + 1, j),
 * max(i, k) op max(k + 1, j), and
 * max(i, k) op min(k + 1, j) where `op` is our split operator.
 * ```
 * Let us translate it into code:
 *
 * ```
 * // Length of number of operators to include that defines the length of a sub-expression
 * for (length in 1..<n) {
 *     // The start-index of a sub-expression for each possible sub-expression for the given length
 *     for (i in 0..<n - length) {
 *         // The end-index of a sub-expression that starts with `i` and must be of length `length`.
 *         val j = i + length
 *         // Splitting the sub-expression into different combinations using the available operators within the sub-expression
 *         // Reference examples that explain why we need `minValue` and `maxValue` variables, and why we update them:
 *         // res/courses/ucSanD/module06DynamicProgramming02/03maximumArithmeticExpression/15fourElementsExample02.png
 *         // For a particular length, we consider each operator as the last operation for the same expression.
 *         // We evaluate each result and finalize the minimum and the maximum values for the relevant tables.
 *         var minValue = Int.MAX_VALUE
 *         var maxValue = Int.MIN_VALUE
 *         for (k in i..<j) {
 *             val op = operators[k]
 *             val one = calculate(min[i][k], min[k + 1][j], op)
 *             val two = calculate(min[i][k], max[k + 1][j], op)
 *             val three = calculate(max[i][k], max[k + 1][j], op)
 *             val four = calculate(max[i][k], min[k + 1][j], op)
 *             minValue = minOf(minValue, one, two, three, four)
 *             maxValue = maxOf(maxValue, one, two, three, four)
 *             min[i][j] = min
 *             max[i][j] = max
 *         }
 *     }
 * }
 *
 * // A function that calculates simple math between two numbers using the given operator
 * fun calculate(a: Int, b: Int, operator: String): Int {
 *     return when(operator) {
 *            "+" -> a + b
 *            "-" -> a - b
 *            "*" -> a * b
 *            else -> throw IllegalArgumentException()
 *     }
 * }
 *
 * ```
 *
 * We can conclude that:
 *
 * ```
 * 1. We start with the `length` of the operators. Length represents the number of operators included.
 * We start with `1` and iterate up to the length equal to the number of operators in the original expression.
 * 2. For each `length`, we iterate up to (exclusive) `n - length` for the `number iteration` to make different pairs.
 * 3. For each pair, the start index is `i`, and the end index is `i + length` (inclusive).
 * ```
 *
 * ## ----------------------- Important Observations: Summary Of The Key-Points -----------------------
 *
 * ```
 * 1. For the given range (i, j), we can split it into two subproblems as (i, k) and (k + 1, j).
 * 2. We need four combinations.
 * 3. We need two tables to store all four combinations' minimum and maximum values.
 * 4. When the values of i and j are the same, it indicates a single element.
 * 5. We start with `length = 1`, and then we gradually increase the value of the `length`
 * to increase the size (length) of the pairs until the `length` becomes the length of the original problem.
 * 6. The maximum table cell (0, 5) gives the final maximum value.
 * It covers the complete, final, single pair of `n` elements where `n` is the number of `numbers` in the given
 * expression. For example, the expression `5 - 8 + 7 * 4 - 8 + 9` has six numbers. So, `n = 6`.
 * Hence, (0, 5) represents the maximum value for the single pair `5 - 8 + 7 * 4 - 8 + 9`,
 * which is the original problem.
 * ```
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * ## ----------------------- Time Complexity -----------------------
 *
 * We have 3 lexical (nested) for loops.
 *
 * ```
 * // n is the number of digits in the given string expression
 * for (length in 1..<n) {
 *     for (i in 0..<n - length) {
 *         val j = i + length
 *         for (k in i..<j) {
 *              ...
 *         }
 *     }
 * }
 * ```
 * None of the for loop reaches `n`, but we drop the constants.
 * Hence, the time complexity is O(n^3).
 *
 * ## ----------------------- Space Complexity -----------------------
 *
 * We take two tables.
 *
 * ```
 * // n is the number of digits in the given string expression
 * val minTable = Array(n) { IntArray(n) }
 * val maxTable = Array(n) { IntArray(n) }
 * ```
 *
 * So, the maximum memory we use is n * n = n^2.
 * Hence, the space complexity is O(n^2).
 *
 * # ----------------------- Coursera's Grader Output -----------------------
 * Good job! (Max time used: 0.10/2.00, max memory used: 43524096/536870912.)
 *
 */
fun main() {

    fun calculate(a: Long, b: Long, operator: String): Long {
        return when (operator) {
            "+" -> {
                a + b
            }

            "-" -> {
                a - b
            }
            "*" -> {
                a * b
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    fun maximizeArithmeticExpression(string: String): Long {
        val digits = Regex("\\d+").findAll(string).map { it.value.toLong() }.toList()
        val numberOfDigits = digits.size
        val operators = Regex("[+\\-*]").findAll(string).map { it.value }.toList()
        val minTable = Array(numberOfDigits) { LongArray(numberOfDigits) }
        val maxTable = Array(numberOfDigits) { LongArray(numberOfDigits) }
        for (i in 0 until numberOfDigits) {
            minTable[i][i] = digits[i]
            maxTable[i][i] = digits[i]
        }
        // Length of the number of operators included that defines the length of a subexpression
        for (length in 1 until numberOfDigits) {
            //Start index of the subexpression
            for (i in 0 until numberOfDigits - length) {
                // End index of the subexpression to respect and limit the defined length of the subexpression
                val j = i + length
                var minValue = Long.MAX_VALUE
                var maxValue = Long.MIN_VALUE
                // Include operators
                for (k in i until j) {
                    val op = operators[k]
                    val one = calculate(minTable[i][k], minTable[k + 1][j], op)
                    val two = calculate(minTable[i][k], maxTable[k + 1][j], op)
                    val three = calculate(maxTable[i][k], maxTable[k + 1][j], op)
                    val four = calculate(maxTable[i][k], minTable[k + 1][j], op)
                    minValue = minOf(minValue, one, two, three, four)
                    maxValue = maxOf(maxValue, one, two, three, four)
                    minTable[i][j] = minValue
                    maxTable[i][j] = maxValue
                }
            }
        }
        // cell(0, n - 1) contains the maximum value
        return maxTable[0][numberOfDigits - 1]
    }

    val string = readln()
    val answer = maximizeArithmeticExpression(string)
    println(answer)
}