package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

/**
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
 * # Pattern
 *
 * * If we look at the logs, there is a definite pattern for the losing state.
 * * If both the left and the right numbers are Even, then it is a losing state.
 * * Otherwise, it is a winning state.
 * * So, we will use this simple mathematical pattern to solve the problem.
 *
 * # Complexity Analysis
 *
 * * If printing is not a part of the complexity analysis, then figuring out a particular state is just O(1).
 * * And there is no extra-memory that grows or depends on the input size.
 * * So, the space complexity is also O(1).
 * * Isn't it amazing?
 */
class TwoPilesOfRocksMaths {
    private fun isWinningState(left: Int, right: Int): Boolean {
        val isLosingState = (left % 2 == 0) && (right % 2 == 0)
        return !isLosingState
    }
    fun printStates(totalRocksPerPile: Int) {
        for (i in 0..totalRocksPerPile) {
            for (j in 0..totalRocksPerPile) {
                val isWinningState = isWinningState(i, j)
                println("Pile(left: $i, right: $j): canWin = $isWinningState")
            }
        }
    }
}

fun main() {
    val totalRocksPerPile = readln().toInt()
    val solver = TwoPilesOfRocksMaths()
    solver.printStates(totalRocksPerPile)
}