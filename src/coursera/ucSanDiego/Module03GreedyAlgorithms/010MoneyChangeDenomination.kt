package coursera.ucSanDiego.Module03GreedyAlgorithms

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