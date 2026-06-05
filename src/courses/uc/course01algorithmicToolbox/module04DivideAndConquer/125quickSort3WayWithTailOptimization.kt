package courses.uc.course01algorithmicToolbox.module04DivideAndConquer

import kotlin.random.Random

class QuickSort3WayWithTailOptimization {

    private fun swap(input: IntArray, one: Int, two: Int) {
        val old = input[one]
        input[one] = input[two]
        input[two] = old
    }

    fun quickSort3WayWithTailOptimization(input: IntArray, start: Int, end: Int) {
        var curStart = start
        var curEnd = end
        while (curStart < curEnd) {
            // Random pivot to efficiently handle already sorted input
            val random = Random.nextInt(curStart, curEnd + 1)
            swap(input, random, curEnd)
            val pivot = input[curEnd]
            var left = curStart
            var cur = curStart
            var right = curEnd
            // Caution! Possible point of mistake!
            // We need to handle the `cur == curEnd` case because that `value` is still unrevealed and unhandled.
            // Fixes the same values at their final positions
            // 3-Way Partitions to handle the duplicate items efficiently
            while (cur <= curEnd) {
                when {
                    input[cur] > pivot -> {
                        swap(input, cur, right)
                        right--
                    }
                    input[cur] < pivot -> {
                        swap(input, cur, left)
                        cur++
                        left++
                    }
                    else -> {
                        cur++
                    }
                }
            }
            // Tail-recursion optimization to reduce the stack space
            if (left - curStart < curEnd - right) {
                // Left part is smaller. Send it to the recursion.
                quickSort3WayWithTailOptimization(input, curStart, left - 1)
                // Handle the right part within the loop.
                curStart = right + 1
            } else {
                // Right part is smaller. Send it to the recursion.
                quickSort3WayWithTailOptimization(input, right + 1, curEnd)
                // Handle the left part within the loop.
                curEnd = left - 1
            }
        }
    }
}

fun main() {
    val input = readln().split(" ").map { it.toInt() }.toIntArray()
    if (input.size <= 1) {
        println(input.joinToString(" "))
        return
    }
    val solver = QuickSort3WayWithTailOptimization()
    solver.quickSort3WayWithTailOptimization(input, 0, input.size - 1)
    println(input.joinToString(" "))
}