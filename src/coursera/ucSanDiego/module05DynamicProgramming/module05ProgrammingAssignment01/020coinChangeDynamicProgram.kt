package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

fun main() {

    fun minimumCoins(targetAmount: Int, coinDenominations: List<Int>): Int {
        // Create a cheat-sheet (Memoization, cache).
        val minCoins = IntArray(targetAmount + 1) { Int.MAX_VALUE }
        // We know the answer when the target amount is 0.
        minCoins[0] = 0
        // Try each amount.
        for (amount in 1..targetAmount) {
            // Try each coin for the given/target amount.
            for (coin in coinDenominations) {
                // Check if we can use the current coin.
                // Also check if we get a valid case after using the coin.
                if (amount >= coin && minCoins[amount - coin] != Int.MAX_VALUE) {
                    minCoins[amount] = minOf(minCoins[amount], minCoins[amount - coin] + 1)
                }
            }
        }
        return minCoins[targetAmount]
    }

    val targetAmount = readln().toInt()
    val coinDenominations = listOf(1, 3, 4)
    val answer = minimumCoins(targetAmount, coinDenominations)
    println(answer)
}