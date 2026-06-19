package courses.uc.course01algorithmicToolbox.module06DynamicProgramming02

/**
 * # ----------------------- Grader Output -----------------------
 *
 * Good job! (Max time used: 0.09/4.00, max memory used: 43331584/536870912.)
 */
fun main() {

    fun canPartitionWithEqualSums(values: List<Int>, subsets: Int): Boolean {
        val totalSum = values.sum()
        if (totalSum == 0) return true
        if (totalSum % subsets != 0) return false
        val targetSum = totalSum / subsets
        if (values.maxOrNull()!! > targetSum) return false
        val sorted = values.sortedDescending()
        val selected = BooleanArray(values.size)


        fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
            if (subsets == 1) return true
            if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)
            for (i in startIndex..<sorted.size) {
                if (!selected[i] && currentSum + sorted[i] <= targetSum) {
                    selected[i] = true
                    if (canPartition(i + 1, subsets, currentSum + sorted[i])) return true
                    selected[i] = false
                }
            }
            return false
        }
        return canPartition(0, subsets, 0)
    }

    val totalIntegers = readln().toInt()
    val values = readln().split(" ").map { it.toInt() }
    val answer = canPartitionWithEqualSums(values, 3)
    println(if (answer) 1 else 0)
}