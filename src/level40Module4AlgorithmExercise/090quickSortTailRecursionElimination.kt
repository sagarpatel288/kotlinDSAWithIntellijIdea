package level40Module4AlgorithmExercise

import kotlin.random.Random

/**
 * Image reference: res/level40Module4AlgorithmExercise/RandomizedQuicksortTailCallOptimizationFlowDiagram.svg
 */
fun main() {

    var quickSortFunCount = 0
    var whileCount = 0
    var getPartitionFunCount = 0
    var swapFunCount = 0

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        val pivotIndex = Random.nextInt(start, end + 1)
        swapElements(input, pivotIndex, end)
        val pivot = input[end]
        var marker = start - 1
        for (i in start..<end) {
            if (input[i] <= pivot) {
                marker++
                if (marker != i) {
                    swapElements(input, marker, i)
                }
            }
        }
        swapElements(input, ++marker, end)
        return marker
    }

    fun quickSort(input: IntArray, startIndex: Int, endIndex: Int) {
        println(": :quickSort: funCount: ${++quickSortFunCount} input: ${input.toList()} startIndex: $startIndex endIndex: $endIndex")
        var start = startIndex
        var end = endIndex
        while (start < end) {
            println(": :quickSort: whileCount: ${++whileCount} start: $start end: $end")
            val partitionIndex = getPartitionIndex(input, start, end)
            println(": :quickSort: partitionIndex: $partitionIndex start: $start end: $end")
            if (partitionIndex - start < end - partitionIndex) {
                quickSort(input,  start, partitionIndex - 1)
                start = partitionIndex + 1
            } else {
                quickSort(input, partitionIndex + 1, end)
                end = partitionIndex - 1
            }
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: randomized quickSort with tail recursion elimination: ${input.toList()}")
}