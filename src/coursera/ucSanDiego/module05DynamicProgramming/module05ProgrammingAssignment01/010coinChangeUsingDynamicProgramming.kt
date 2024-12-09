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
 * Similarly, if our coin denominations are 4, 10, and 25, and we want to make change for 41, the greedy approach would
 * first take a coin of 25 (leaving a remaining amount of 41 - 25 = 16), followed by a coin of 10 (leaving a remaining
 * amount of 16 - 10 = 6), followed by a coin of 4 (leaving a remaining amount of 6 - 4 = 2), ultimately concluding that
 * the change is not possible (which is an incorrect answer)!
 *
 * In contrast, if we use dynamic programming (whether through recursion with memoization or a bottom-up approach),
 * we find that the minimum number of coins needed consists of one coin of 25 and four coins of 4,
 * resulting in 25 + (4 * 4) = 41, which is the correct answer.
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
 * ----------------------- Coursera's Grader Output -----------------------
 *
 * (Max time used: 0.09/2.00, max memory used: 44609536/536870912.)
 *
 */
fun main() {

    fun minCoins(targetAmount: Int, coinDenominations: List<Int>): Int {
        // An int array to store minimum number of coins for each target amount (bottom-up: 0 to targetAmount)
        val minCoins = IntArray(targetAmount + 1) {Int.MAX_VALUE}
        // The minimum number of coins needed for the target amount 0 is: 0.
        minCoins[0] = 0
        for (amount in 1..targetAmount) {
            // Calculating the target amount with each coin
            for (coin in coinDenominations) {
                if (amount >= coin && minCoins[amount - coin] != Int.MAX_VALUE) {
                    // Taking the result that gives the minimum number of coins
                    minCoins[amount] = minOf(minCoins[amount], minCoins[amount - coin] + 1)
                }
            }
        }
        return minCoins[targetAmount]
    }

    val targetAmount = readln().toInt()
    val coinDenominations = listOf(1, 3, 4)
    val result = minCoins(targetAmount, coinDenominations)
    println(result)
}