package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquer

/**
 * Explain `Merge Sort` with an example:
 *
 * Merge Sort is a "divide and conquer" sorting algorithm.
 * It works by breaking down a list into smaller sublists until each sublist contains only one element
 * (which is considered sorted by itself, and it is our base case),
 * and then merging those sublists back together in a sorted order.
 * This approach ensures that the final output is a sorted list.
 */
fun main() {

    fun conquer(array: IntArray, startIndex: Int, midIndex: Int, endIndex: Int) {
        var currentLeftIndex = startIndex
        var currentRightIndex = midIndex + 1
        var currentSortedIndex = 0
        val sortedArray = IntArray(endIndex - startIndex + 1)

        while (currentLeftIndex <= midIndex && currentRightIndex <= endIndex) {
            if (array[currentLeftIndex] <= array[currentRightIndex]) {
                sortedArray[currentSortedIndex++] = array[currentLeftIndex++]
            } else {
                sortedArray[currentSortedIndex++] = array[currentRightIndex++]
            }
        }

        while (currentLeftIndex <= midIndex) {
            sortedArray[currentSortedIndex++] = array[currentLeftIndex++]
        }

        while (currentRightIndex <= endIndex) {
            sortedArray[currentSortedIndex++] = array[currentRightIndex++]
        }

        for (i in sortedArray.indices) {
            array[startIndex + i] = sortedArray[i]
        }
    }

    fun divideAndConquer(array: IntArray, startIndex: Int, endIndex: Int) {
        if (array.size <= 1) {
            println(": :sorted: ${array.toList()}")
        }
        if (startIndex < endIndex) {
            // A better way to find the midIndex.
            // If we do (startIndex + endIndex) / 2, the addition can cross the integer boundary.
            val midIndex = startIndex + (endIndex - startIndex) / 2
            // Left half part of the incoming array.
            divideAndConquer(array, startIndex, midIndex)
            // Right half part of the incoming array.
            divideAndConquer(array, midIndex + 1, endIndex)
            conquer(array, startIndex, midIndex, endIndex)
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(51, 42, 33, 24, 15)
    }

    val input = getInput()
    divideAndConquer(input, 0, input.lastIndex)
    println(": :main: sorted: ${input.toList()}")
}