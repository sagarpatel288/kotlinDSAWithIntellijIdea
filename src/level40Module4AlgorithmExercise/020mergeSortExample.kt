package level40Module4AlgorithmExercise

fun main() {

    var count = 1

    fun conquer(array: IntArray, startIndex: Int, mid: Int, endIndex: Int) {
        val tempArray = IntArray(endIndex - startIndex + 1)
        var currentIndexOfLeft = startIndex
        var currentIndexOfRight = mid + 1
        var currentIndexOfSortedArray = 0

        while (currentIndexOfLeft <= mid && currentIndexOfRight <= endIndex) {
            println("\n :conquer: :tempArray: ${tempArray.toList()} startIndex: $startIndex mid: $mid endIndex: $endIndex leftElement: ${array[currentIndexOfLeft]} rightElement: ${array[currentIndexOfRight]} \n")
            if (array[currentIndexOfLeft] <= array[currentIndexOfRight]) {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
            } else {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
            }
        }

        while (currentIndexOfLeft <= mid) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
        }

        while (currentIndexOfRight <= endIndex) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
        }

       println(" :conquer: sorted tempArray: ${tempArray.toList()} \n")

        for (i in tempArray.indices) {
            print(" :conquer: tempArray: ${tempArray.toList()} startIndex: $startIndex i: $i \n")
            array[startIndex + i] = tempArray[i]
        }

        println(" :conquer: sortedArray: ${array.toList()} \n")

    }

    fun divide(array: IntArray, startIndex: Int, endIndex: Int) {
        println(" :divide: array: ${array.toList()} count: $count startIndex: $startIndex endIndex: $endIndex")
        count++
        if (startIndex < endIndex) {
            val mid = startIndex + (endIndex - startIndex) / 2
            divide(array, startIndex, mid)
            divide(array, mid + 1, endIndex)
            conquer(array, startIndex, mid, endIndex)
        }
    }

    divide(intArrayOf(51, 42, 33, 24, 15), 0, 4)
}