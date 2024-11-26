package coursera.ucSanDiego.module05DynamicProgramming.module05puzzleAssignment02

fun main() {

    data class Pile(val leftSide: Int, val rightSide: Int) : Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.leftSide }, { it.rightSide })
        }

        override fun toString(): String {
            return "(Rocks: leftSide = $leftSide and rightSide = $rightSide)"
        }
    }

    class TwoPilesRockGame() {

        fun initConfig(leftSideRocks: Int, rightSideRocks: Int, config: MutableMap<Pile, Boolean>) {
            saveBaseCase(config)
            fillConfig(leftSideRocks, rightSideRocks, config)
        }

        fun printConfig(config: MutableMap<Pile, Boolean>) {
            config.entries.sortedBy { it.key }.forEach { (pile, boolean) -> println("$pile --> $boolean") }
        }

        private fun saveBaseCase(config: MutableMap<Pile, Boolean>) {
            config[Pile(0, 1)] = true
            config[Pile(1, 0)] = true
            config[Pile(1, 1)] = true
        }

        private fun fillConfig(leftSide: Int, rightSide: Int, config: MutableMap<Pile, Boolean>): Boolean {
            val pile = Pile(leftSide, rightSide)
            config[pile]?.let { return !it }

            var canWin = false
            if (leftSide > 0 && rightSide > 0) {
                canWin = canWin || fillConfig(leftSide - 1, rightSide - 1, config)
            }
            if (leftSide > 0) {
                canWin = canWin || fillConfig(leftSide - 1, rightSide, config)
            }
            if (rightSide > 0) {
                canWin = canWin || fillConfig(leftSide, rightSide - 1, config)
            }
            config[pile] = canWin
            return !canWin
        }
    }

    val twoPilesRockGame = TwoPilesRockGame()
    val config = mutableMapOf<Pile, Boolean>()
    twoPilesRockGame.initConfig(10, 10, config)
    twoPilesRockGame.printConfig(config)
}