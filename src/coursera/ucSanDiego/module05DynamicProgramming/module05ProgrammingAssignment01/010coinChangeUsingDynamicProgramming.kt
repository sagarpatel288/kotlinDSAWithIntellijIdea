package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ----------------------- Problem Statement -----------------------
 *
 * Money Change Again Problem:
 *
 * Compute the minimum number of coins needed to change the given value into coins with denominations 1, 3, and 4.
 *
 * Input:
 *
 * An integer money.
 *
 * Output:
 *
 * The minimum number of coins with denominations 1, 3, and 4 that changes money.
 *
 * ----------------------- Recapitulation -----------------------
 *
 * The greedy strategy works for certain coin systems called canonical coin systems
 * (e.g., the US coin system of 1, 5, 10, 25). These systems are specifically designed so that the greedy approach
 * always produces the optimal solution.
 *
 * In non-canonical coin systems (like 1, 8, and 20), the greedy approach can fail because the denominations are not
 * structured in a way that ensures optimality.
 *
 * For example, we want to change 24 into coins, and the coin denominations are 1, 8, and 20.
 * The greedy approach would select a coin of 20, followed by 4 coins of 1, resulting in a total of 5 coins.
 * However, the optimal solution would be to use 3 coins of 8!
 *
 * ----------------------- Continue: Problem Statement -----------------------
 *
 * As we already know, a natural greedy strategy for the change problem does not work correctly for any set of
 * denominations. For example, for denominations 1, 3, and 4, the greedy algorithm will change 6 cents using
 * three coins (4 + 1 + 1) while it can be changed using just two coins (3 + 3).
 *
 * Your goal now is to apply dynamic programming for solving the Money Change Problem for denominations 1, 3, and 4.
 *
 * Input format:
 *
 * Integer money.
 *
 * Output format:
 *
 * The minimum number of coins with denominations 1, 3, and 4 that changes money.
 *
 * Constraints:
 *
 * 1 ≤ money ≤ 10^3
 *
 * Sample:
 *
 * Input:
 *
 * 34
 *
 * Output:
 *
 * 9
 *
 * 34 = 3 + 3 + 4 + 4 + 4 + 4 + 4 + 4 + 4.
 *
 */
fun main() {


}