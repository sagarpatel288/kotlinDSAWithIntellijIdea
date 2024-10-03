package level40Module4AlgorithmExercise

fun main() {

    var quickSortFunCount = 0
    var getPartitionIndexFunCount = 0
    var swapElementsFunCount = 0

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        swapElementsFunCount++
        println(": :swapElements: $swapElementsFunCount before: input: ${input.toList()} positionOne: $positionOne positionTwo: $positionTwo")
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
        println(": :swapElements: $swapElementsFunCount after: input: ${input.toList()}")
    }

    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        getPartitionIndexFunCount++
        val pivot = input[end]
        var partitionIndex = start - 1
        println(": :getPartitionIndex: $getPartitionIndexFunCount input: ${input.toList()} start: $start end: $end pivot: $pivot initialPartitionIndex: $partitionIndex")
        for (j in start..<end) {
            if (input[j] <= pivot) {
                partitionIndex++
                if (partitionIndex != j) {
                    println(": :getPartitionIndex: $getPartitionIndexFunCount input: ${input.toList()} partitionIndex: $partitionIndex j: $j")
                    swapElements(input, partitionIndex, j)
                }
            }
        }
        //This is for printing, understanding, and acknowledgement purpose.
        // Otherwise, we could have used pre-increment while calling the swapElements function and it would work.
        // The actual code can use: swapElements(input, ++partitionIndex, end) without first doing partitionIndex++ separately.
        partitionIndex++
        println(": :getPartitionIndex: $getPartitionIndexFunCount before swapping: input: ${input.toList()} start: $start end: $end pivot: $pivot partitionIndex: $partitionIndex")
        swapElements(input, partitionIndex, end)
        println(": :getPartitionIndex: $getPartitionIndexFunCount after swapping: input: ${input.toList()} start: $start end: $end pivot: $pivot partitionIndex to return: $partitionIndex")
        return partitionIndex
    }

    fun quickSort(input: IntArray, start: Int, end: Int) {
        quickSortFunCount++
        println(": :quickSort: $quickSortFunCount input: ${input.toList()} start: $start end: $end")
        if (start < end) {
            val partitionIndex = getPartitionIndex(input, start, end)
            println(": :quickSort: $quickSortFunCount input: ${input.toList()} start: $start end: $end partitionIndex received: $partitionIndex endIndexOfLeftPart: ${partitionIndex - 1}")
            quickSort(input, start, partitionIndex - 1)
            println(": :quickSort: $quickSortFunCount left part finished: input: ${input.toList()} start was: $start end was: ${partitionIndex - 1}")
            println(": :quickSort: $quickSortFunCount right part begins: input: ${input.toList()} start: ${partitionIndex + 1} end: $end")
            quickSort(input, partitionIndex + 1, end)
            println(": :quickSort: $quickSortFunCount right part finished: input: ${input.toList()} start was: ${partitionIndex + 1} end was: $end")
        }
    }

    val input = intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    quickSort(input, 0, input.lastIndex)
    println(": :main: sortedArray: ${input.toList()}")
}