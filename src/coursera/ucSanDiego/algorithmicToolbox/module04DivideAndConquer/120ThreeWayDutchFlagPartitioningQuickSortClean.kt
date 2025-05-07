package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquer

import kotlin.random.Random

fun main() {

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun quickSort(input: IntArray, start: Int, end: Int) {
        if (input.size <= 1) {
            println(": :quickSort: sorted: ${input.toList()}")
            return
        }
        if (start < end) {
            val randomIndex = Random.nextInt(start, end + 1)
            swapElements(input, randomIndex, end)
            val pivot = input[end]
            var lessThan = start
            var current = start
            var greaterThan = end
            while (current <= greaterThan) {
                when {
                    input[current] < pivot -> {
                        if (current != lessThan && input[current] != input[lessThan]) {
                            swapElements(input, current, lessThan)
                        }
                        lessThan++
                        current++
                    }
                    input[current] > pivot -> {
                        if (input[current] != input[greaterThan]) {
                            swapElements(input, current, greaterThan)
                        }
                        greaterThan--
                    }
                    else -> {
                        current++
                    }
                }
            }
            quickSort(input, start, lessThan - 1)
            quickSort(input, greaterThan + 1, end)
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(4, 9, 4, 4, 2, 6, 4, 3, 8)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: 3-way-Dutch-flag-partitioning quicksort: ${input.toList()}")
}