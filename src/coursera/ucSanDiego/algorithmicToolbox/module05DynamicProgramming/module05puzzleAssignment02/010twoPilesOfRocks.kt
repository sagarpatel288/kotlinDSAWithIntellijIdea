package coursera.ucSanDiego.algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

/**
 *
 *  ----------------------- Problem Statement -----------------------
 *
 *  There are two piles of ten rocks. In each turn, you may either take one
 *  rock from a single pile, or one rock from both piles. The player that
 *  takes the last rock wins the game. Your opponent moves first.
 *
 *  We need to develop a program that will help us in winning the game.
 *
 *  Input: 10 rocks for each pile.
 *
 *  Output: Print the log statement for each configuration (set-up) of the rocks, indicating whether
 *  it is a winning or losing configuration.
 *
 *  Example:
 *  Pile(left=0, right=1) -> true
 *
 */
fun main() {

    /**
     *
     * At any given time, we might have certain rocks in one pile and certain rocks in another pile.
     * We need to package up (lock? store? snapshot?) this information into a single object as a state.
     * That's why, the Data class `Pile`, to represent the state of the piles.
     * So that we can figure out whether a particular state is a winning configuration or not.
     * It means that we will have several different states (arrangements) and the associated conclusion (Boolean?),
     * that we will log (record, store) into a sheet (Map?).
     *
     * Once we fill the entire sheet, we can refer to it for any given configuration
     * (a certain number of rocks in pile one and a certain number of rocks in pile two),
     * and determine whether this configuration is a winning configuration or not.
     *
     * We can name these two piles as top and bottom, or left and right.
     *
     * We implement the `Comparable<Pile>` interface to make the collection of piles eligible for the `sortedBy` call.
     * Without implementing the `Comparable<Pile>` interface, the `sortedBy` call on the collection of piles will give
     * us a compile-time error.
     *
     * We want to implement the `sortedBy` feature to read and understand the configuration properly and clearly.
     *
     * @param left Represents the number of rocks in the left pile.
     * @param right Represents the number of rocks in the right pile.
     */
    data class Pile(val left: Int, val right: Int) : Comparable<Pile> {
        // The custom comparator to print the logs in sorted, intuitive, predictable, easy-to-understand,
        // and ascending order.
        // For example,
        // The comparator takes two elements, and it will first compare their `left` variables.
        // Let us assume that these two elements are (0, 3), and (0, 2).
        // If the left variables are the same, then the comparator compares the `right` variables.
        // So, as we first compare the `left` variables, the sorted result looks like below:
        // (0, 2) and then (0, 3).
        // If the maximum number of rocks is 10 for each pile, then the final sorted result looks like below:
        // (0, 1), (0, 2)...(0, 10), (1, 1), (1, 2)..(1, 10)..(2, 1), (2, 2)..(2, 10)..(3, 1), (3, 2)..(3, 10)..(10, 10).
        override fun compareTo(other: Pile): Int {
            return compareValuesBy(this, other, { it.left }, { it.right })
        }

        // The custom `toString` method provides a clear and easily understandable representation of the
        // `Pile` data class.
        // When we print the `Pile` object, we need to know which `int` represents which `pile`.
        // Hence, adding explicit `left=` and `right=` gives a clear picture of how many rocks are there in each pile.
        override fun toString(): String {
            return "Pile(left=$left, right=$right)"
        }
    }

    class Game {
        // The `map` is our sheet where we store (log, record) the conclusion for a particular configuration,
        // to understand whether the given configuration is a winning configuration or not.
        val resultMap: MutableMap<Pile, Boolean> = mutableMapOf()

        // Initialize the result map with base cases and calculate results
        fun initializeResults(maxRocks: Int) {
            // Base cases.
            // These are the winning configurations. (Therefore, they are true).
            // The player who achieves any of these configurations wins.
            // Thus, it is crucial that we do not allow our opponent to access any of the configurations listed below
            // after our move.
            // Our move must not result in any of the configurations below.
            // After our move, the configuration must not resemble the ones listed below.
            // We know the base cases; therefore, we store (cache) them.
            // This technique is known as memoization in dynamic programming.
            resultMap[Pile(1, 0)] = true
            resultMap[Pile(0, 1)] = true
            resultMap[Pile(1, 1)] = true

            // Compute results for all possible pile configurations
            fillResults(maxRocks, maxRocks)
        }


        /**
         * Imagine a player facing a game situation with two piles of rocks.
         * The goal is to ensure a win. To do this, the player evaluates all possible moves:
         *
         * Move Type 1: Take one rock from both piles.
         *
         * Move Type 2: Take one rock from the left pile.
         *
         * Move Type 3: Take one rock from the right pile.
         *
         * For each move, the player asks:
         *
         * "If I make this move, will I leave the opponent in a losing position?"
         *
         * The player starts with no confidence of winning: ```canWin = false```.
         *
         * The player evaluates Move Type 1:
         *
         * The recursive function checks if the opponent can win after this move.
         * If the opponent cannot win, the player feels confident and sets ```canWin = true```.
         *
         * If Move Type 1 doesn’t guarantee a win, the player evaluates Move Type 2.
         * Similarly, if Move Type 2 doesn’t work, the player evaluates Move Type 3.
         *
         * If any of the moves leaves the opponent in a losing position, the player can confidently win.
         * The `||` operator ensures that as soon as one winning move is found, no further moves need to be evaluated.
         *
         * Short-Circuit Optimization:
         *
         * The `||` operator stops evaluating further moves as soon as a winning move is found,
         * saving unnecessary computation.
         *
         * Recursive function to compute results
         */
        private fun fillResults(left: Int, right: Int): Boolean {
            val currentPile = Pile(left, right)

            // If the result is already computed, return the opposite.
            // Why opposite? Because we have calculated what the `canWin` result will be after we make a move.
            // Does it provide a winning position to the opponent? If so, it is a `losing move` for us.
            // Conversely, if it leads to a losing position for the opponent, it means it is a `winning move` for us.
            // The current player should try to set (give) the resultant configuration
            // (the configuration after taking the move) as a losing configuration for the opponent to win the game.
            // It is very important to understand this perspective.
            // The incoming parameters or arguments, left and right, is a configuration that we get,
            // the reader (caller) of this function gets, and not the configuration that we give.
            // Now, the vantage point of the two players are opposite.
            // So, we flip the value.
            // For example, if the previous player has already set it (stored value) as a "winning configuration",
            // then we have to flip it because as a reader, as a caller of this function, we get that configuration,
            // and it becomes a "losing configuration" for us.
            resultMap[currentPile]?.let { return !it }

            // A boolean variable that represents whether the current player can win from the current state of the piles
            // (left and right).
            var canWin = false

            // A recursive call to check the result of the game after making a specific move.
            // Recursive exploration of all possible moves.
            // true: If the opponent can win from that resulting game state.
            // false: If the opponent cannot win from that resulting game state
            // (and thus the current player has a winning strategy from the current state).
            // We know the base cases and have stored (cached) them.
            // This is known as memoization in dynamic programming.
            // We start with the top (higher or upper value than the base cases) and reduce the problem size until it
            // becomes a base case. This approach is called the top-down approach in dynamic programming.
            // We try all the possible moves to see if there is a possibility of the winning configuration.
            // We try all the possible moves and then store the result.
            // Maybe one of the moves, or multiple moves give (set) a winning configuration,
            // or maybe none of the moves give (set) the winning configuration.
            // Whatever the result we get after trying all the possible moves, we store it.
            if (left > 0 && right > 0) {
                // Take one rock from both the piles.
                // If `canWin` is already true, the expression remains true, and no further evaluation is needed.
                // This is because the player has already found a winning move,
                // so there’s no need to evaluate the remaining moves.
                // Short-Circuit Optimization: The `||` operator stops evaluating further moves as soon as
                // a winning move is found, saving unnecessary computation.
                // If the current player take one rock from each pile,
                // does it result into a winning configuration or a losing configuration?
                canWin = canWin || fillResults(left - 1, right - 1)
            }
            if (left > 0) {
                // Take one rock from the left pile.
                // If the current player take one rock from the left pile,
                // does it result into the winning configuration or a losing configuration?
                canWin = canWin || fillResults(left - 1, right)
            }
            if (right > 0) {
                // Take one rock from the right pile.
                // If the current player take one rock from the right pile,
                // does it result into the winning configuration or a losing configuration?
                canWin = canWin || fillResults(left, right - 1)
            }

            // Store the result.
            // The reader, the caller of this function (current player), performs the storage operation.
            resultMap[currentPile] = canWin

            // Return the result for the current player (opposite of the stored result).
            // Why opposite? Because we have calculated what the `canWin` result will be after we make a move.
            // Does it provide a winning position to the opponent? If so, it is a `losing move` for us.
            // Conversely, if it leads to a losing position for the opponent, it means it is a `winning move` for us.
            // So, if any of the available moves result in a winning configuration,
            // then the current setup (configuration) is a losing configuration for the current player.
            // Similarly, if any of the available moves result in a losing configuration,
            // then the current setup (configuration) is a winning configuration for the current player.
            // The current player should take a move in such a way that the resulting configuration is a losing
            // configuration. It means that when it is the opponent's turn, the opponent gets a losing configuration.
            // In other words, the current player should try to set (give) the resultant configuration
            // (the configuration after taking the move) is a losing configuration for the opponent to win the game.
            // The reader, the caller of this function, returns the opposite because of the vantage point of the
            // reader, the caller of this function is opposite to the other (next) player,
            // who is receiving this return value.
            // We return the vantage point of the other (next) player receiving this return value.
            // And the vantage point of the opponent (next, other) player is opposite to our vantage point.
            // Hence, we return the opposite.
            return !canWin
        }
    }

    // Initialize the game and print the results
    val game = Game()
    game.initializeResults(10)

    // Print the computed results.
    // If we did not implement the `Comparable` interface, then `sortedBy { it.key }` would have given a compile-time
    // error.
    game.resultMap.entries.sortedBy { it.key }.forEach { (pile, result) ->
        println("$pile -> $result")
    }
}