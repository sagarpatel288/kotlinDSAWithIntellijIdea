package coursera.ucSanDiego.algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

fun main() {

    fun minimumCoins(targetAmount: Int, coinDenominations: List<Int>): Pair<Int, List<Int>> {
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
        // Backtracking (Reconstruction). Identify which coins we have used to change the target money.
        // If this is not clear at this moment, checkout the next example:
        // Primitive Calculator.
        // https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f8687e7831a6788ed57387ab9be15a40dc2bb7a0/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/030primitiveCalculator.kt
        var indexPointer = targetAmount
        val path = mutableListOf<Int>()
        while (indexPointer > 0) {
            for (option in coinDenominations) {
                if (indexPointer - option >= 0 && minCoins[indexPointer] == minCoins[indexPointer - option] + 1) {
                    path.add(option)
                    indexPointer -= option
                    break
                }
            }
        }
        return minCoins[targetAmount] to path.reversed()
    }

    val targetAmount = readln().toInt()
    val coinDenominations = listOf(1, 3, 4)
    val answer = minimumCoins(targetAmount, coinDenominations)
    println(answer.first)
    println(answer.second.joinToString(" "))
}