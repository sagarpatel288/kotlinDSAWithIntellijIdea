package level40Module4AlgorithmExercise

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

        println(" :conquer: after adding the remaining left part: before adding the remaining right part: tempArray: ${tempArray.toList()}")
        while (currentIndexOfRight <= endIndex) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
        }

       println(" :conquer: after if-else and all the while loops: sorted tempArray: ${tempArray.toList()} \n")

        for (i in tempArray.indices) {
            print(" :conquer: copying from the tempArray: ${tempArray.toList()} startIndex: $startIndex i: $i \n")
            // Why `startIndex + i`? Because the tempArray will always start with the index 0,
            // and it does not represent always the correct associated / corresponding index of the original array,
            // where we are putting the elements by copying from the tempArray.
            // The size of the tempArray can be different at different stages/iterations.
            // For example, if the problem is [51, 42, 33, 24, 60], then at some point, the tempArray can have size 2
            // for the right side part [24, 60].
            // If we simply take i, 60 can go to the second place at index 1 in the original array,
            // which will give the wrong answer.
            // The `startIndex + i` saves us. So, 60 is at i = 1 in the tempArray and `startIndex` would be 3.
            // So, we would place 60 at the `startIndex + i` = `3 + 1` = 4th index in the original array
            // which is the correct one.
            array[i] = tempArray[i]
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