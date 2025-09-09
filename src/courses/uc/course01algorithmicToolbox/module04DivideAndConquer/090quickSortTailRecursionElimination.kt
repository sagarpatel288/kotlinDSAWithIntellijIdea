package courses.uc.course01algorithmicToolbox.module04DivideAndConquer

import kotlin.random.Random

/**
 * Problem statement:
 * Sort the given input array with the minimum space complexity and time complexity.
 * The input array can already be sorted (ascending or descending, reversed).
 * OR
 * Explain and demonstrate: Randomized quicksort algorithm using the tail recursion elimination.
 *
 * Answer:
 * A randomized quicksort algorithm reduces the possibility of getting a pivot that is always the smallest one or the
 * largest one. Hence, it increases the possibility of getting O(n log n) runtime complexity.
 *
 * However, recursion can introduce more stack memory. We can optimize it by using the tail recursion elimination.
 * Here, we compare the left and the right part that we get after the partition.
 * And we use recursion for the smaller part, and iteration for the larger part.
 *
 * This way, we reduce the recursion depth and thus, we reduce the stack memory.
 *
 * Image reference: res/level40Module4AlgorithmExercise/RandomizedQuicksortTailCallOptimizationFlowDiagram.svg
 */
fun main() {

    var quickSortFunCount = 0
    var whileCount = 0
    var getPartitionFunCount = 0
    var swapFunCount = 0

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        println(": :getPartitionIndex: funCount: ${++getPartitionFunCount} input: ${input.toList()} start: $start end: $end")
        val pivotIndex = Random.nextInt(start, end + 1)
        swapElements(input, pivotIndex, end)
        val pivot = input[end]
        println(": :getPartitionIndex: after pivot: input: ${input.toList()} pivot: $pivot start: $start end: $end")
        var marker = start - 1
        for (i in start..<end) {
            if (input[i] <= pivot) {
                marker++
                if (marker != i) {
                    swapElements(input, marker, i)
                }
            }
        }
        swapElements(input, ++marker, end)
        println(": :getPartitionIndex: returning the partitionIndex as: $marker for funCount: $getPartitionFunCount: input: ${input.toList()} start: $start end: $end")
        return marker
    }

    fun quickSort(input: IntArray, startIndex: Int, endIndex: Int) {
        println(": :quickSort: funCount: ${++quickSortFunCount} input: ${input.toList()} startIndex: $startIndex endIndex: $endIndex")
        var start = startIndex
        var end = endIndex
        while (start < end) {
            println(": :quickSort: whileCount: ${++whileCount} start: $start end: $end")

            // Whether we use the recursion or the iteration,
            // we call the `getPartitionIndex` function and go through the pivot, swapping, and partition process.
            // However, if we go through a recursion, we increase the stack memory.
            // Hence, we use recursion (calling the same function, `quickSort`) for the smaller part,
            // and use iteration (while loop) for the bigger (larger) part.
            // This process reduces the stack memory and improves the randomized quicksort algorithm.
            val partitionIndex = getPartitionIndex(input, start, end)
            println(": :quickSort: partitionIndex: $partitionIndex start: $start end: $end")
            if (partitionIndex - start < end - partitionIndex) {
                // We find that the left part is smaller than the right part.
                // Hence, we send the left part to the recursion.
                // And, we will handle the right part with the help of this ongoing while loop.
                quickSort(input,  start, partitionIndex - 1)
                println(": :quickSort: left recursion finished for: input: ${input.toList()} start: $start end: $end partitionIndex: $partitionIndex")

                // To handle the right part in the while loop, we need to update the `start` index/point/position.
                // Once we update the `start` position, the control goes to the while loop with the updated values.
                start = partitionIndex + 1
                println(": :quickSort: left recursion finished. new start? $start")
            } else {
                // We find that the right part is smaller than the left part.
                // Hence, we send the right part to the recursion.
                // And, we will handle the left part with the help of this ongoing while loop.
                quickSort(input, partitionIndex + 1, end)
                println(": :quickSort: right recursion finished for: input: ${input.toList()} start: $start end: $end partitionIndex: $partitionIndex")

                // To handle the left part in the while loop, we need to update the `end` index/point/position.
                // Once we update the `end` position, the control goes to the while loop with the updated values.
                end = partitionIndex - 1
                println(": :quickSort: right recursion finished. new end? $end")
            }
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: randomized quickSort with tail recursion elimination: ${input.toList()}")
}