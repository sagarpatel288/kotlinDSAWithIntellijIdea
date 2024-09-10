package level40Module4AlgorithmExercise

fun main() {

    fun countSort(array: IntArray) {

        if (array.size <= 1) {
            println(array.toList())
            return
        }

        var minValue = 0
        var maxValue = 0

        // Find min value element (Starting of the range of values)
        for (i in array.indices) {
            if (array[i] <= minValue) {
                minValue = array[i]
            }
        }

        // Find max value element (Ending of the range of values)
        for (i in array.indices) {
            if (array[i] >= maxValue) {
                maxValue = array[i]
            }
        }

        println(": :countSort: maxValue: $maxValue minValue: $minValue")

        val countArray = IntArray(maxValue - minValue + 1)

        // Count frequency of repetition for each element
        for (i in array.indices) {
            countArray[array[i]]++
        }

        println(": :countSort: countArray: ${countArray.toList()}")

        // Assign the position
        for (i in 1..<countArray.size) {
            countArray[i] = countArray[i - 1] + countArray[i]
        }

        println(": :countSort: countArray with position shifting: ${countArray.toList()}")

        // Take the value from the original array and position from the count array
        val sortedArray = IntArray(array.size)

        for (i in array.indices.reversed()) {
            val value = array[i]
            val position = countArray[value] - 1
            println(": :countSort: reverse travelling: i $i value: $value position: $position")
            countArray[value]--
            sortedArray[position] = value
        }

        println(sortedArray.toList())
    }

    val array = intArrayOf(3, 1, 2, 3, 2, 1, 4, 3)
    countSort(array)
}