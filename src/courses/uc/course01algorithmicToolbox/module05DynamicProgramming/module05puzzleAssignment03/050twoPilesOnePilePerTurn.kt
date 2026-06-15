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

    private fun isWinningConfig(state: State, maxPerTurnPerPile: Int): Boolean {
        val losingConfig = maxPerTurnPerPile + 1
        // The leverage level
        // If it is `0`, we are unarmed.
        // If it is `0`, it is a losing configuration.
        val p1ThreatLevel = state.p1 % losingConfig
        val p2ThreatLevel = state.p2 % losingConfig
        val xorSum = p1ThreatLevel xor(p2ThreatLevel)
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