package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment03

fun main() {

    data class Pile(val leftPileRocks: Int, val rightPileRocks: Int): Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.leftPileRocks }, { it.rightPileRocks })
        }

        override fun toString(): String {
            return "(Pile: leftSide = $leftPileRocks and rightSide = $rightPileRocks)"
        }
    }

    fun initConfigWithBaseCases(config: MutableMap<Pile, Boolean>) {
        for (i in 1..3) {
            config[Pile(i, 0)] = true
            config[Pile(0, i)] = true
        }
        config[Pile(1, 1)] = true
        config[Pile(1, 2)] = true
        config[Pile(2, 1)] = true
    }

    fun fillConfig(leftSideRocks: Int, rightSideRocks: Int, config: MutableMap<Pile, Boolean>): Boolean {
        val pile = Pile(leftSideRocks, rightSideRocks)
        config[pile]?.let { return !it }

        var canWin = false
        val possibleMoves = listOf(
            Pair(1, 0),
            Pair(1, 1),
            Pair(1, 2),
            Pair(2, 0),
            Pair(2, 1),
            Pair(3, 0),
            Pair(0, 1),
            Pair(0, 2),
            Pair(0, 3)
        )
        for ((leftMove, rightMove) in possibleMoves) {
            if (leftSideRocks >= leftMove && rightSideRocks >= rightMove) {
                canWin = canWin || fillConfig(leftSideRocks - leftMove, rightSideRocks - rightMove, config)
            }
        }
        config[pile] = canWin
        return !canWin
    }

    fun printConfig(config: Map<Pile, Boolean>) {
        config.entries.sortedBy { it.key }.forEach { (pile, boolean) -> println("$pile --> $boolean") }
    }

    val maxRocks = readln().toInt()
    val config = mutableMapOf<Pile, Boolean>()
    initConfigWithBaseCases(config)
    fillConfig(maxRocks, maxRocks, config)
    printConfig(config)
}