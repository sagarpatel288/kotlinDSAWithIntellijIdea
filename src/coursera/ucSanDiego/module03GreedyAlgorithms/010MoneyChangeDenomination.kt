package coursera.ucSanDiego.module03GreedyAlgorithms

/**
 * Problem Statement:
 *
 * Money Change Problem:
 *
 * Compute the minimum number of coins needed to change the given value into coins with denominations 1, 5, and 10.
 *
 * Input: An integer money.
 *
 * Output: The minimum number of coins with denominations 1, 5, and 10 that changes money.
 *
 * In this problem, you will implement a simple greedy algorithm used by cashiers all over the world.
 * We assume that a cashier has unlimited number of coins of each denomination.
 *
 * Input format. Integer money.
 * Output format. The minimum number of coins with denominations 1, 5, 10 that changes money.
 *
 * Constraints:
 * 1 ≤ money ≤ 10 raised to the power 3.
 *
 * Sample 1.
 * Input:
 * 2
 * Output:
 * 2
 *
 * 2 = 1 + 1.
 *
 * Sample 2.
 * Input:
 * 28
 * Output:
 * 6
 *
 * 28 = 10 + 10 + 5 + 1 + 1 + 1.
 *
 * ----------------------- Explanation -----------------------
 *
 * 1. We get the input (money).
 * 2. We divide the money by highest value first: By 10.
 * 3. The quotient represents number of coins, and the remainder represents the remaining amount.
 * 4. We divide the remaining amount by the second-highest value: By 5.
 * 5. Again, the quotient represents number of coins, and the remainder represents the remaining amount.
 * 6. We don't need to divide the remaining amount by 1. We add the remaining amount to the number of coins needed.
 *
 * ----------------------- Alert! -----------------------
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
 * We will explore how dynamic programming can address this issue later.
 *
 * For now, let us solve it using the greedy approach.
 *
 */
fun main() {

    fun getMinimumCoinsNeeded(money: Long): Long {
        // Handle the edge cases.
        // If the money is less than 5, we need the total number of 1 INR coins equal to the given money.
        if (money <= 4L) return money
        // If the money is equal to 5 or 10, we need only 1 coin.
        // If the money is 5 INR, we need 1 coin of 5 INR.
        // If the money is 10 INR, we need 1 coin of 10 INR.
        if (money == 5L || money == 10L) return 1
        var numberOfCoinsNeeded = 0L
        // numberOfCoinsNeeded = numberOfCoinsNeeded + (money / 10).
        // The quotient represents the number of coins needed for the given money.
        numberOfCoinsNeeded += (money / 10)
        // The remainder is money left.
        var moneyLeft = money % 10
        if (moneyLeft != 0L) {
            // numberOfCoinsNeeded = numberOfCoinsNeeded + (moneyLeft / 5)
            // The quotient represents the number of coins needed for the given money.
            numberOfCoinsNeeded += (moneyLeft / 5)
            // moneyLeft = moneyLeft % 5
            // The remainder is money left.
            moneyLeft %= 5
        }
        if (moneyLeft != 0L) {
            // numberOfCoinsNeeded = numberOfCoinsNeeded + (moneyLeft / 1)
            // => numberOfCoinsNeeded = numberOfCoinsNeeded + moneyLeft (because a/1 = a)
            numberOfCoinsNeeded += (moneyLeft)
        }
        return numberOfCoinsNeeded
    }

    val money = readln().toLong()
    println(getMinimumCoinsNeeded(money))
}