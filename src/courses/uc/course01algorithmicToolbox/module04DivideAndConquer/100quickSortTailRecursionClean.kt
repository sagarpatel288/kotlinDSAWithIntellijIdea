package courses.uc.course01algorithmicToolbox.module04DivideAndConquer

import kotlin.random.Random

fun main() {

    fun swapElement(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        val randomIndex = Random.nextInt(start, end+ 1)
        swapElement(input, randomIndex, end)
        val pivot = input[end]
        var marker = start - 1
        for (i in start..<end) {
            if (input[i] <= pivot) {
                marker++
                if (marker != i) {
                    swapElement(input, marker, i)
                }
            }
        }
        swapElement(input, ++marker, end)
        return marker
    }

    fun quickSort(input: IntArray, startIndex: Int, endIndex: Int) {
        var start = startIndex
        var end = endIndex
        while (start < end) {
            val partitionIndex = getPartitionIndex(input, start, end)
            if (partitionIndex - start < end - partitionIndex) {
                // Since the left part is smaller than the right part, we use recursion for the left part.
                quickSort(input, start, partitionIndex - 1)
                // We use iteration for the right part. So, we update the `start` position for the right part.
                start = partitionIndex + 1
            } else {
                // Since the right part is smaller than the left part, we use recursion for the right part.
                quickSort(input, partitionIndex + 1, end)
                // We use iteration for the left part. So, we update the `end` position for the left part.
                end = partitionIndex - 1
            }
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: randomized quicksort with tail recursion elimination: sorted array: ${input.toList()}")
}