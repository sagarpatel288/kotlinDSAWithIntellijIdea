package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment03

fun main() {

    data class Pile(val left: Int, val right: Int) : Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.left }, { it.right })
        }

        override fun toString(): String {
            return "(Pile: leftSide = $left and rightSide = $right)"
        }
    }

    fun initConfigWithBaseCases(maxMoves: Int, config: MutableMap<Pile, Boolean>) {
        for (i in 0..maxMoves) {
            for (j in 0..maxMoves) {
                if (i + j in 1..maxMoves) {
                    config[Pile(i, j)] = true
                }
            }
        }
    }

    fun fillConfigs(left: Int, right: Int, config: MutableMap<Pile, Boolean>): Boolean {
        val pile = Pile(left, right)
        config[pile]?.let { !it }

        var canWin = false
        val validMoves = listOf(
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
        for ((leftMove, rightMove) in validMoves) {
            if (left >= leftMove && right >= rightMove) {
                canWin = canWin || fillConfigs(left - leftMove, right - rightMove, config)
            }
        }
        config[pile] = canWin
        return !canWin
    }

    fun printConfig(config: Map<Pile, Boolean>) {
        config.entries.sortedBy { it.key }.forEach { (pile, boolean) -> println("$pile --> $boolean") }
    }

    val maxRocks = readln().toInt()
    val maxMoves = readln().toInt()
    val config = mutableMapOf<Pile, Boolean>()
    initConfigWithBaseCases(maxMoves, config)
    fillConfigs(maxRocks, maxRocks, config)
    printConfig(config)
}