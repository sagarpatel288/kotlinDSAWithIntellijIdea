package level40Module4AlgorithmExercise

import kotlin.random.Random

fun main() {

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun quickSort(input: IntArray, start: Int, end: Int) {
        if (start >= end) return
        println(": :quickSort: input: ${input.toList()} start: $start end: $end")
        val randomIndex = Random.nextInt(start, end + 1)
        swapElements(input, randomIndex, end)
        val pivot = input[end]
        var lessThan = start
        var greaterThan = end
        var iteration = start
        println(": :quickSort: input: ${input.toList()} pivot: $pivot lessThan: $lessThan greaterThan: $greaterThan")
        while (iteration <= end) {
            when {
                input[iteration] < pivot -> {
                    swapElements(input, lessThan, iteration)
                    lessThan++
                    iteration++
                }
                input[iteration] > pivot -> {
                    swapElements(input, iteration, greaterThan)
                    greaterThan--
                    iteration++
                }
                else -> {
                    iteration++
                }
            }
        }
        quickSort(input, start, lessThan - 1)
        quickSort(input, greaterThan + 1, end)
    }

    fun getInput(): IntArray {
        return intArrayOf(4, 9, 4, 4, 2, 6, 4, 3, 8)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: 3-way quicksort partitioning with random pivot: ${input.toList()}")
}