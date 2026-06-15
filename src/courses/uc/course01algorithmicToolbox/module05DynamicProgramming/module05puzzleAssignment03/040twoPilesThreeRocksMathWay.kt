package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment03

/**
 * # Visual
 *
 * * [Two Piles Three Rocks](https://discrete-math-puzzles.github.io/puzzles/three-rocks-game/index.html)
 *
 * # Reference:
 *
 * * [Two piles two rocks](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/c6ed9afabb21727399e01ea092485adb2018e334/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/010twoPilesOfRocks.kt)
 *
 * * [Two piles three rocks](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/db90bea6551619a0026dbd89939c13207c1fc158/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment03/010twoPilesThreeRocks.kt)
 *
 *
 */
class TwoPilesThreeRocks {
    data class State(val p1: Int, val p2: Int) {
        val total = p1 + p2
        override fun toString() = "Pile: P1: $p1 P2: $p2"
    }

    private fun isWinningConfig(state: State, maxPerTurn: Int): Boolean {
        val total = state.total
        // If we can take up to 3 rocks per turn, then having a total of 4 rocks is a losing configuration.
        // Because if someone gets a total of 4 rocks, they can take only up to 3 rocks.
        // And then the opponent will take the remaining rocks to win the game.
        // Similarly, if someone gets a total of 5 rocks, they take 1 rock, and leave 4 rocks for the opponent.
        // Because 4 is the losing config.
        // If someone gets a total of 6 rocks, they take 2 rocks, and leave 4 rocks for the opponent.
        // If someone gets a total of 7 rocks, they take 3 rocks, and leave 4 rocks for the opponent.
        // If someone gets a total of 8 rocks, then it is a losing configuration.
        // If we continue this pattern, we will find that if the total rocks are perfectly divisible by (maxPerTurn + 1),
        // then the current configuration is a losing configuration.
        val losingConfig = maxPerTurn + 1
        val isLosingConfig = total % losingConfig == 0
        return !isLosingConfig
    }

    fun printState(totalPerPile: Int, maxPerTurn: Int) {
        for (i in 0..totalPerPile) {
            for (j in 0..totalPerPile) {
                val state = State(i, j)
                println("$state canWin: ${isWinningConfig(state, maxPerTurn)}")
            }
        }
    }
}

fun main() {
    val (totalPerPile, maxPerTurn) = readln().split(" ").map { it.toInt() }
    val solver = TwoPilesThreeRocks()
    solver.printState(totalPerPile, maxPerTurn)
}