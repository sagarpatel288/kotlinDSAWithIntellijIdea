package courses.uc.course01algorithmicToolbox.module04DivideAndConquer

fun main() {

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        val pivot = input[end]
        var partitionIndex = start - 1
        for (j in start..<end) {
            if (input[j] <= pivot) {
                partitionIndex++
                if (partitionIndex != j) {
                    swapElements(input, partitionIndex, j)
                }
            }
        }
        swapElements(input, ++partitionIndex, end)
        return partitionIndex
    }

    fun quickSort(input: IntArray, start: Int, end: Int) {
        if (start < end) {
            val partitionIndex =  getPartitionIndex(input, start, end)
            quickSort(input, start, partitionIndex - 1)
            quickSort(input, partitionIndex + 1, end)
        }
    }

    val input = intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    quickSort(input, 0, input.lastIndex)
    println(": :main: sorted array: ${input.toList()}")
}