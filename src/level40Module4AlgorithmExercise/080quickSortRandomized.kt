package level40Module4AlgorithmExercise

import kotlin.random.Random

fun main() {

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, startIndex: Int, endIndex: Int): Int {
        // The `until` param of Random.nextInt(from, until) is exclusive (not included). Hence, we do: +1 to include it.
        val randomIndex = Random.nextInt(startIndex, endIndex + 1)
        swapElements(input, randomIndex, endIndex)
        val pivot = input[endIndex]
        var markerIndex = startIndex - 1
        for (j in startIndex..<endIndex) {
            if (input[j] <= pivot) {
                markerIndex++
                if (markerIndex != j) {
                    swapElements(input, markerIndex, j)
                }
            }
        }
        swapElements(input, ++markerIndex, endIndex)
        return markerIndex
    }

    fun quickSort(input: IntArray, startIndex: Int, endIndex: Int) {
        if (startIndex < endIndex) {
            val partitionIndex = getPartitionIndex(input, startIndex, endIndex)
            quickSort(input, startIndex, partitionIndex - 1)
            quickSort(input, partitionIndex + 1, endIndex)
        }
    }

    val input = intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    quickSort(input, 0, input.lastIndex)
    println(": :main: sortedArray: ${input.toList()}")
}