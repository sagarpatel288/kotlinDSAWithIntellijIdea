package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment03

/**
 * # Piles and Rocks: One pile at a time (per turn)
 *
 * * This is a variant of the previous "Piles and Rocks" game.
 * * Here, a player can take stones only from one pile at a time (per turn).
 * * The player who takes the last stone wins the game.
 *
 * # Reference
 *
 * * [Two Piles And Rocks: Recursion](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/c6ed9afabb21727399e01ea092485adb2018e334/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/010twoPilesOfRocks.kt)
 *
 * * [Two Piles And Rocks: Dynamic Programming](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7addc079adc4c41984786eaa6aa90eaf4a4f5c38/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/040twoPilesOfRocksDp.kt)
 *
 * # Visual
 *
 * [Take the last stone](https://discrete-math-puzzles.github.io/puzzles/take-the-last-stone/index.html)
 * [Two Piles, Three Stones](https://discrete-math-puzzles.github.io/puzzles/three-rocks-game/index.html)
 *
 */
class TwoPilesOnePilePerTurn {
    data class State(val p1: Int, val p2: Int) {
        val total = p1 + p2
        override fun toString() = "Config: P1: $p1 P2: $p2 "
    }

    /**
     * | Rocks | Lose Or Win | Distance from the losing state | How powerful the position is for winning |
     * |-------|-------------|--------------------------------|------------------------------------------|
     * | 0     | L           | 0                              | 0                                        |
     * | 1     | W           | 1                              | 1                                        |
     * | 2     | W           | 2                              | 2                                        |
     * | 3     | W           | 3                              | 3                                        |
     * | 4     | L           | 0                              | 0                                        |
     * | 5     | W           | 1                              | 1                                        |
     * | 6     | W           | 2                              | 2                                        |
     * | 7     | W           | 3                              | 3                                        |
     * | 8     | L           | 0                              | 0                                        |
     *
     * * If we get `0` rocks, we lose.
     * * So, it is a losing configuration.
     * * If we get a total of `1`, `2`, or `3` rocks, we can take it all and win.
     * * So, they are winning configurations.
     * * If we get a total of `4` rocks, we can take only up to `3` rocks.
     * * The opponent can take all the remaining rocks and win.
     * * So, getting a total of `4` rocks is a losing configuration.
     * * If we notice, the formula to get to know how powerful the position is for winning is `n % 4`.
     * * And `4` is `maximum allowed rocks to take + 1`.
     * * And we can see that `maximum allowed rocks to take + 1` is a losing configuration.
     * * And, when we perform `rocks % losingConfig`, we get certain level.
     * * For example, `5`, `9`, `13`, `17`, `21`, they are all at level 1.
     * * Similarly, `6`, `10`, `14`, `18`, `22`, they are all at level 2.
     * * And so on...
     * * Now, if we have two different piles of different levels, how do we calculate their total strength?
     * * We use the `XOR` operator.
     * * If `a xor(b) == 0`, it means that it is a perfect balance.
     * * If anyone gets the perfect balance, they are forced to make it unbalanced!
     * * In other words, getting a perfectly balanced game is a losing configuration.
     * * Because, we are forced to make it unbalanced.
     * * And when we make it unbalanced, the opponent will re-balance it.
     * * We will have to make it unbalanced, again.
     * * This process will continue until we get (0, 0).
     * * That's why, getting a perfectly balanced game is a losing configuration.
     * * And the answer to "Why XOR?" leads to "Sprague-Grundy Theorem."
     * * The "Sprague-Grundy Theorem" is a part of the combinatorial game theories.
     */
    private fun isWinningConfig(state: State, maxPerTurnPerPile: Int): Boolean {
        val losingConfig = maxPerTurnPerPile + 1
        // The leverage level
        // If it is `0`, we are unarmed.
        // If it is `0`, it is a losing configuration.
        // What type of position is the left pile?
        val p1WinLevel = state.p1 % losingConfig
        // What type of position is the right pile?
        val p2WinLevel = state.p2 % losingConfig

        val xorSum = p1WinLevel xor(p2WinLevel)
        // If current state is perfectly balanced (xor == 0), we are forced to make it unbalanced.
        // Our move will make the state unbalanced.
        // We will hand over an unbalanced state and the opponent will hand over the balanced state back to us.
        // This will continue until we get (0, 0) and we lose.
        // It means that if the current state is a balanced state, it is a losing configuration.
        val isLosingConfig = xorSum == 0
        return !isLosingConfig
    }

    fun printState(totalRocksPerPile: Int, maxPerTurnPerPile: Int) {
        for (i in 0..totalRocksPerPile) {
            for (j in 0..totalRocksPerPile) {
                val state = State(i, j)
                println("Pile(left: $i, right: $j canWin: ${isWinningConfig(state, maxPerTurnPerPile)}")
            }
        }
    }
}

fun main() {
    val (totalRocksPerPile, maxPerTurnPerPile: Int) = readln().split(" ").map { it.toInt() }
    val solver = TwoPilesOnePilePerTurn()
    solver.printState(totalRocksPerPile, maxPerTurnPerPile)
}