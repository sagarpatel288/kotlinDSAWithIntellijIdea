package level40Module4AlgorithmExercise

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

    fun divide(array: IntArray, startIndex: Int, endIndex: Int) {
        if (array.size == 1) {
            println("The array has only one element and hence, it is already sorted!")
        }
        if (startIndex < endIndex) {
            // A better way to find the midIndex.
            // If we do (startIndex + endIndex) / 2, the addition can cross the integer boundary.
            val midIndex = startIndex + (endIndex - startIndex) / 2
            // Left half part of the incoming array.
            divide(array, startIndex, midIndex)
            // Right half part of the incoming array.
            divide(array, midIndex + 1, endIndex)
            conquer(array, startIndex, midIndex, endIndex)
            println(array.toList())
        }
    }

    divide(intArrayOf(51, 42, 33, 24, 15), 0, 4)
}