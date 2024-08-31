package level40Module4AlgorithmExercise

fun main() {

    var divideFunCount = 1
    var ifOfDivideCount = 1

    fun conquer(array: IntArray, startIndex: Int, mid: Int, endIndex: Int) {
        val tempArray = IntArray(endIndex - startIndex + 1)
        var currentIndexOfLeft = startIndex
        var currentIndexOfRight = mid + 1
        var currentIndexOfSortedArray = 0

        while (currentIndexOfLeft <= mid && currentIndexOfRight <= endIndex) {
            println("\n :conquer: incomingArray: ${array.toList()} :tempArray: ${tempArray.toList()} startIndex: $startIndex currentIndexOfLeft: $currentIndexOfLeft mid: $mid endIndex: $endIndex currentIndexOfRight: $currentIndexOfRight leftElement: ${array[currentIndexOfLeft]} rightElement: ${array[currentIndexOfRight]} currentIndexOfSortedArray: $currentIndexOfSortedArray \n")
            if (array[currentIndexOfLeft] <= array[currentIndexOfRight]) {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
            } else {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
            }
        }

        println(" :conquer: after the first while loop: before adding the remaining left part: tempArray: ${tempArray.toList()}")
        while (currentIndexOfLeft <= mid) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
        }

        println(" :conquer: after the second while loop: before adding the remaining right part: tempArray: ${tempArray.toList()}")
        while (currentIndexOfRight <= endIndex) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
        }

       println(" :conquer: after if-else and all the while loops: sorted tempArray: ${tempArray.toList()} \n")

        for (i in tempArray.indices) {
            print(" :conquer: copying from the tempArray: ${tempArray.toList()} startIndex: $startIndex i: $i \n")
            array[startIndex + i] = tempArray[i]
        }

        println(" :conquer: sortedArray: ${array.toList()} \n")

    }

    fun divide(array: IntArray, startIndex: Int, endIndex: Int) {
        println(" :divide: array: ${array.toList()} divideFunCount: ${divideFunCount++} startIndex: $startIndex endIndex: $endIndex")
        if (startIndex < endIndex) {
            // A better way to find the mid that limits the number within the type boundary.
            // i.e., If we do (startIndex + endIndex) /2, the addition can cross the `integer` boundary!
            val mid = startIndex + (endIndex - startIndex) / 2
            println(" :divide: ifOfDivideCount: ${ifOfDivideCount++} midIndex: $mid ")
            // The left part (startIndex to mid) where the mid-index becomes the endIndex.
            divide(array, startIndex, mid)
            // The right part (mid + 1 to endIndex) where the mid + 1 becomes the startIndex.
            // The below function will get executed only after the above first recursive call is completed.
            divide(array, mid + 1, endIndex)
            // Only after the above two recursive calls are completed, this call will happen.
            conquer(array, startIndex, mid, endIndex)
        }
    }

    divide(intArrayOf(51, 42, 33, 24, 15), 0, 4)
}