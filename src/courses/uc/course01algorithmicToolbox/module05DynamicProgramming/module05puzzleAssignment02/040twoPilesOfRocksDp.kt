package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

class TwoPilesOfRocks {

    data class Pile(val left: Int, val right: Int): Comparable<Pile> {
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, {it.left}, {it.right})
        }

        override fun toString() = "Pile(left: $left, right: $right)"
    }

    fun fillResult(rocks: Int): Map<Pile, Boolean> {
        val dp = Array(rocks + 1) { BooleanArray(rocks + 1) }
        dp[0][0] = false
        for (i in 0..rocks) {
            for (j in 0..rocks) {
                // Skip [0,0] because we have already defined it.
                if (i == 0 && j == 0) continue
                // Otherwise:
                // Caution! Possible point of mistake!
                // We reset the `canWin` inside the inner `for` loop, just before our calculation.
                var canWin = false
                // A state [i, j] is a winning state if it can hand over a losing state.
                // If we can hand over a losing configuration, then the current state [i, j] is a winning state.
                // If the configuration that we hand over after removing a rock from each pile is a losing state,
                // then the current state [i, j] has a winning possibility for us.
                if (i > 0 && j > 0 && !dp[i - 1][j - 1]) canWin = true
                // If the configuration that we are handing over after removing a rock from the left pile,
                // is a losing state, then the current state [i, j] is a winning state.
                if (i > 0 && !dp[i - 1][j]) canWin = true
                // If the configuration that we are handing over after removing a rock from the right pile,
                // is a losing state, then the current state [i, j] is a winning state.
                if (j > 0 && !dp[i][j - 1]) canWin = true
                dp[i][j] = canWin
            }
        }
        val map = mutableMapOf<Pile, Boolean>()
        for (i in 0..rocks) {
            for (j in 0..rocks) {
                map[Pile(i, j)] = dp[i][j]
            }
        }
        return map
    }
}

fun main() {
    val totalRocksPerPile = readln().toInt()
    val solver = TwoPilesOfRocks()
    val answer = solver.fillResult(totalRocksPerPile)
    answer.entries.sortedBy { it.key }.forEach { (pile, state) ->
        println("$pile = $state")
    }
}