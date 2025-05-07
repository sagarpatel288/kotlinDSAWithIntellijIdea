package coursera.ucSanDiego.algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment03

/**
 *  ----------------------- Problem Statement -----------------------
 *
 *  There are two piles of ten rocks. In each turn, you may take up to three
 *  rocks. The player who takes the last rock wins the game. Your opponent
 *  moves first.
 *
 *  We need to develop a program that will help us win the game.
 *
 * Input:
 *
 * 10 rocks for each pile.
 *
 * Output:
 *
 * Print the log statement for each configuration (set-up) of the rocks, indicating whether
 * it is a winning or losing configuration.
 *
 * Example:
 *
 * Pile(left=0, right=3) -> true
 *
 * ----------------------- Explanation -----------------------
 *
 * The solution, concept, logic, process, etc. are similar to
 * [010twoPilesTwoRocks](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/89b5b6d2b439efcd76486a3be4049b4b161ce4f8/src/coursera/ucSanDiego/module05DynamicProgramming/module05puzzleProgram/010twoPilesOfRocks.kt)
 *
 */
fun main() {

    data class Pile(val leftSide: Int, val rightSide: Int) : Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.leftSide }, { it.rightSide })
        }

        override fun toString(): String {
            return "(Pile: leftSide = $leftSide and rightSide = $rightSide)"
        }
    }

    class TwoPilesThreeRocks() {

        fun initConfig(leftSide: Int, rightSide: Int, config: MutableMap<Pile, Boolean>) {
            saveBaseCases(config)
            fillConfig(leftSide, rightSide, config)
        }

        private fun saveBaseCases(config: MutableMap<Pile, Boolean>) {
            val maxPick = 3
            // Add single side configs using a loop to avoid repetitive code.
            for (i in 1..maxPick) {
                config[Pile(i, 0)] = true
                config[Pile(0, i)] = true
            }
            // Add remaining combinations.
            config[Pile(1, 1)] = true
            config[Pile(1, 2)] = true
            config[Pile(2, 1)] = true
        }

        private fun fillConfig(leftSide: Int, rightSide: Int, config: MutableMap<Pile, Boolean>): Boolean {
            val pile = Pile(leftSide, rightSide)
            config[pile]?.let { return !it }

            var canWin = false
            if (leftSide >= 3) {
                canWin = canWin || fillConfig(leftSide - 3, rightSide, config)
            }
            if (leftSide >= 2 && rightSide >= 1) {
                canWin = canWin || fillConfig(leftSide - 2, rightSide - 1, config)
            }
            if (leftSide >= 2) {
                canWin = canWin || fillConfig(leftSide - 2, rightSide, config)
            }
            if (leftSide >= 1 && rightSide >= 2) {
                canWin = canWin || fillConfig(leftSide - 1, rightSide - 2, config)
            }
            if (leftSide >= 1 && rightSide >= 1) {
                canWin = canWin || fillConfig(leftSide - 1, rightSide - 1, config)
            }
            if (leftSide >= 1) {
                canWin = canWin || fillConfig(leftSide - 1, rightSide, config)
            }
            if (rightSide >= 3) {
                canWin = canWin || fillConfig(leftSide, rightSide - 3, config)
            }
            if (rightSide >= 2) {
                canWin = canWin || fillConfig(leftSide, rightSide - 2, config)
            }
            if (rightSide >= 1) {
                canWin = canWin || fillConfig(leftSide, rightSide - 1, config)
            }
            config[pile] = canWin
            return !canWin
        }

        fun printConfig(config: MutableMap<Pile, Boolean>) {
            config.entries.sortedBy { it.key }.forEach { (pile, boolean) -> println("$pile --> $boolean") }
        }
    }

    val config = mutableMapOf<Pile, Boolean>()
    val twoPilesThreeRocks = TwoPilesThreeRocks()
    twoPilesThreeRocks.initConfig(10, 10, config)
    twoPilesThreeRocks.printConfig(config)
}