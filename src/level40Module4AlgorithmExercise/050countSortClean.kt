package level40Module4AlgorithmExercise

fun main() {

    fun countSort(inputArray: IntArray) {
        // If the size of the inputArray is less than or equal to 1, the input array is already sorted.
        // So, we print the input array and return (complete) the function.
        if (inputArray.size <= 1) {
            println(inputArray.toList())
            return
        }
        // Find an element that has the Maximum value.
        // If we find null, it means, all the elements are null in the input array.
        // In that case, we simply print the input array and return (complete) the function.
        val max = inputArray.maxOrNull() ?: return println(inputArray.toList())

        // Find an element that has the minimum value.
        // If we find that the min value is null, we simply print the input array and return (complete) the function.
        val min = inputArray.minOrNull() ?: return println(inputArray.toList())

        // Size of the countArray
        val countArray = IntArray(max - min + 1)

        // Count the occurrences of each element with normalisation.
        for (element in inputArray) {
            countArray[element - min]++
        }

        // Cumulative count
        for (i in 1..<countArray.size) {
            countArray[i] += countArray[i - 1]
        }

        val result = IntArray(inputArray.size)

        // Allocating seats (index positions) to the elements
        for (i in inputArray.indices.reversed()) {
            val element = inputArray[i]
            val targetIndexPosition = countArray[element - min] - 1
            // Allocate the right seat (index position) to the element
            result[targetIndexPosition] = element
            // Reduce (decrease) the allocated seat.
            countArray[element - min]--
        }

        println(result.toList())
    }

    val input = intArrayOf(3, -1, 0, -3,  3)
    countSort(input)
}