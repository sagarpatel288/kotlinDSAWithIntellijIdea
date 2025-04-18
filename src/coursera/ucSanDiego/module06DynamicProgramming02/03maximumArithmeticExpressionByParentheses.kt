package coursera.ucSanDiego.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
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
 *
 */
fun main() {


}