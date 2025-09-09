package courses.ucSanD.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

fun main() {

    data class Pile(val leftRocks: Int, val rightRocks: Int): Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.leftRocks }, { it.rightRocks })
        }
        override fun toString(): String {
            return "(Pile(left = $leftRocks and Right = $rightRocks)"
        }
    }

    fun saveConfigs(leftRocks: Int, rightRocks: Int, config: MutableMap<Pile, Boolean>): Boolean {
        val pile = Pile(leftRocks, rightRocks)
        if (config.contains(pile)) {
            config[pile]?.let {
                return !it
            }
        }
        if (leftRocks == 0 && rightRocks == 1) {
            config[pile] = true
        }
        if (leftRocks == 1 && rightRocks == 0) {
            config[pile] = true
        }
        if (leftRocks == 1 && rightRocks == 1) {
            config[pile] = true
        }
        var canWin = false
        if (leftRocks > 0 && rightRocks > 0) {
            canWin = canWin || saveConfigs(leftRocks - 1, rightRocks - 1, config)
        }
        if (leftRocks > 0) {
            canWin = canWin || saveConfigs(leftRocks - 1, rightRocks, config)
        }
        if (rightRocks > 0) {
            canWin = canWin || saveConfigs(leftRocks, rightRocks - 1, config)
        }
        config[pile] = canWin
        return !canWin
    }

    val config = mutableMapOf<Pile, Boolean>()
    saveConfigs(10, 10, config)
    config.entries.sortedBy { it.key }.forEach { (pile, boolean) -> println("$pile --> $boolean") }
}