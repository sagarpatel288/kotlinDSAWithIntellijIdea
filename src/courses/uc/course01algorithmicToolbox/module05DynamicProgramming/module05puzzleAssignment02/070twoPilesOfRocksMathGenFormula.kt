package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05puzzleAssignment02

/**
 * # References
 *
 * * [Two Piles And Rocks: Recursion](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/c6ed9afabb21727399e01ea092485adb2018e334/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/010twoPilesOfRocks.kt)
 *
 * * [Two Piles And Rocks: Dynamic Programming](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7addc079adc4c41984786eaa6aa90eaf4a4f5c38/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/040twoPilesOfRocksDp.kt)
 *
 * * [Two Piles Of Rocks: Using Math](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/cdfc567b78b17cb56e4f2b426990d2c50fde7e6f/src/courses/uc/course01algorithmicToolbox/module05DynamicProgramming/module05puzzleAssignment02/060twoPilesOfRocksMathsWay.kt)
 *
 * # General Math Formula
 *
 * This is the general math formula for the two piles of rocks game, where we are allowed to take rock/s
 * either from one pile or from both the piles.
 *
 * It means that it doesn't need or use Sprague and Grundy theory.
 * We can solve this type of problem using `losingConfig = maxPerPilePerTurn + 1` rule.
 * Then, `isLeftLosing = left % losingConfig == 0` and `isRightLosing = right % losingConfig == 0`.
 * If both `isLeftLosing && isRightLosing`, then it is a `isLosingConfig`.
 */
class TwoPilesOfRocksMathGenFormula {
    private fun isWinning(left: Int, right: Int, maxPerTurnPerPile: Int): Boolean {
        val losingConfig = maxPerTurnPerPile + 1
        val isLeftLosing = left % losingConfig == 0
        val isRightLosing = right % losingConfig == 0
        val isLosingConfig = isLeftLosing && isRightLosing
        return !isLosingConfig
    }

    fun printConfigs(maxRocksPerPile: Int, maxPerTurnPerPile: Int) {
        for (i in 0..maxRocksPerPile) {
            for (j in 0..maxRocksPerPile) {
                val isWinningConfig = isWinning(i, j, maxPerTurnPerPile)
                println("Left: $i, Right: $j, canWin: $isWinningConfig")
            }
        }
    }
}

fun main() {
    val (maxRocksPerPile, maxPerPilePerTurn) = readln().split(" ").map { it.toInt() }
    val solver = TwoPilesOfRocksMathGenFormula()
    solver.printConfigs(maxRocksPerPile, maxPerPilePerTurn)
}