package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

/**
 * * Given:
 * * Two piles of `n` rocks.
 * * A player can pick up a rock either from a pile or from both the piles.
 * * The player who takes the last turn and leaves no stone behind wins the game.
 *
 * * Output:
 * * Print all the possible winning and losing configurations.
 *
 * * Thought Process:
 * * We have `n` rocks in each pile.
 * * And depending upon the choice of a player, we get many combinations.
 * * For example, a player can make (10, 10) into (9, 9) or (9, 10), or (10, 9).
 * * And depending upon each choice, we get next set of combinations.
 * * So basically, there are many combinations.
 * * So, instead of trying to solve the original and large problem `(n, n)`, we start small.
 * * We start small from (0, 0) and slowly, gradually move towards (n, n).
 * * This pattern indicates the bottom-up (tabulation) approach (dynamic programming).
 * * The `dpTable` that we take is: `Array(rocks + 1) { BooleanArray(rocks + 1) }`.
 * * We store the boolean value for each configuration.
 * * The boolean value `true` indicates that the configuration is a winning configuration.
 * * And the boolean value `false` indicates that the configuration is a losing configuration.
 * * We start with the default (base-case) configuration.
 * * We know that when there is no rock in any pile, it is a losing configuration.
 * * If a player receives such a configuration, the player who receives it, loses the game.
 * * The translation of this configuration is: `dp[0][0]` where there is no rock in any pile.
 * * So, we manually set the value of this default (base-case) configuration `dp[0][0]` as `false`.
 * * And then, we gradually build all the configurations on top of it.
 */
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
                // A state [i, j] is a winning state if it can hand over or end-up with being a losing state.
                // If we can hand over a losing configuration, then the current state [i, j] is a winning state.
                // If the configuration that we hand over after removing a rock from each pile is a losing state,
                // then the current state [i, j] has a winning possibility for us.
                // Another way to look at this is:
                // If the current state [i, j] leads and ends-up with a losing state,
                // it means that the current state [i, j] is a winning state.
                // Because if we are handing over this current state [i, j] to the opponent,
                // the opponent will have to modify it, and there is a possible move that can make the opponent lose
                // the game.
                // And if the opponent loses the game, we win the game.
                // And it happened because we had this [i, j] state.
                // That's why and that's how we say that it is a winning state (configuration).
                // Because it forces the receiver of this state to make some changes and at least one of those
                // changes can end-up being a losing state.
                // We can think of this from the receiver's perspective, too.
                // Suppose that we receive this [i, j] state.
                // Now, it is our turn, and we have to make a move.
                // If our move ends-up with being a losing state, it means that the configuration that we had
                // received, pushed us towards the losing state, made us lose the game.
                // That's why, we can say that the configuration we had received was a winning configuration.
                // So, a configuration that can ends-up with being a losing configuration, is a winning configuration.
                if (i > 0 && j > 0 && !dp[i - 1][j - 1]) canWin = true
                // Similarly:
                // If the configuration that we are handing over after removing a rock from the left pile,
                // is a losing state, then the current state [i, j] is a winning state.
                if (i > 0 && !dp[i - 1][j]) canWin = true
                // And again, the same way:
                // If the configuration that we are handing over after removing a rock from the right pile,
                // is a losing state, then the current state [i, j] is a winning state.
                if (j > 0 && !dp[i][j - 1]) canWin = true
                dp[i][j] = canWin
                // We can print right from here, too.
                println("left: $i right: $j canWin: $canWin")
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