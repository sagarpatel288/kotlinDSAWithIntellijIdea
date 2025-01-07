package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * This is part-2 (extended version, continuation) of:
 * [Longest common sub-sequences of the two sequences](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f93409f4cca0bf91b007613fd5c6ebff4c30df3a/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/090longestCommonSubsequenceOfTwoSequencesStrings.kt)
 *
 * And it is based on:
 * [Edit distance with backtracking](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/be74523bc7fb51c51d6418039bdc885dcadc55eb/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt)
 *
 */
fun main() {

    fun longestCommonSequencesWithBacktracking(refList: List<Int>, targetList: List<Int>): Pair<Int, Array<IntArray>> {
        val table = Array(refList.size + 1) { IntArray(targetList.size + 1) }
        for (i in 0..refList.size) {
            table[i][0] = 0
        }
        for (j in 0..targetList.size) {
            table[0][j] = 0
        }
        for (i in 1..refList.size) {
            for (j in 1..targetList.size) {
                if (refList[i - 1] == targetList[j - 1]) {
                    table[i][j] = 1 + table[i - 1][j - 1]
                } else {
                    val prevRow = table[i - 1][j]
                    val prevColumn = table[i][j - 1]
                    table[i][j] = maxOf(prevRow, prevColumn)
                }
            }
        }
        return table[refList.size][targetList.size] to table
    }

    /**
     * To reconstruct, we start from the last cell of the table, and we move backwards up to the first cell.
     * We use the same logic we have used to build (fill) the table.
     */
    fun longestCommonSequencesBacktracking(refList: List<Int>, targetList: List<Int>, table: Array<IntArray>) {
        var refIndexPointer = refList.size
        var targetIndexPointer = targetList.size
        while (refIndexPointer > 0 || targetIndexPointer > 0) {
            if (refIndexPointer > 0
                && targetIndexPointer > 0
                && refList[refIndexPointer - 1] == targetList[targetIndexPointer - 1]) {
                println("Matches: Reference element is: ${refList[refIndexPointer - 1]} and Target element is: ${targetList[targetIndexPointer - 1]}")
                refIndexPointer--
                targetIndexPointer--
            } else {
                val prevRow = table[refIndexPointer - 1][targetIndexPointer]
                val prevColumn = table[refIndexPointer][targetIndexPointer - 1]
                if (prevRow > prevColumn) {
                    refIndexPointer--
                } else {
                    targetIndexPointer--
                }
            }
        }
    }

    val refSize = readln().toInt()
    val refList = readln().split(" ").map { it.toInt() }
    val targetSize = readln().toInt()
    val targetList = readln().split(" ").map { it.toInt() }
    val answer = longestCommonSequencesWithBacktracking(refList, targetList)
    println("Longest common sequences length is ${answer.first}")
    longestCommonSequencesBacktracking(refList, targetList, answer.second)
}