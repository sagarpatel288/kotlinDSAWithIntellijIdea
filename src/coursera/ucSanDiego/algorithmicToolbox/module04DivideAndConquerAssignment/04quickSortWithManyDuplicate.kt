package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquerAssignment

import kotlin.random.Random

/**
 * Problem Statement:
 *
 * Reference:
 * res/coursera/ucSanDiego/module04DivideAndConquerAssignment/04quickSortWithManyDuplicate.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/1f49a44ed3cb9b9b1002491188fc08326c9379c8/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/04quickSortWithManyDuplicate.png)
 *
 * Speeding-up RandomizedQuickSort Problem:
 * Sort a given sequence of numbers (that may contain duplicates) using a modification of RandomizedQuickSort
 * that works in O(n log n) expected time.
 *
 * Input: An integer array with n elements that may contain duplicates.
 *
 * Output: Sorted array (generated using a modification of RandomizedQuickSort) that works in O(n log n) expected
 * time.
 *
 * Input format:
 *
 * The first line of the input contains an integer n.
 * The next line contains a sequence of n integers a0,a1,...,an−1.
 *
 * Output format:
 *
 * Output this sequence sorted in non-decreasing order.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 10^5;
 * 1 ≤ ai ≤ 10^9 for all 0 ≤ i <n.
 *
 * Sample:
 *
 * Input:
 *
 * 5
 *
 * 2 3 9 2 2
 *
 * Output:
 *
 * 2 2 2 3 9
 *
 * ----------------------- Explanation -----------------------
 *
 * This is the 3-way Dutch Flag partitioning quicksort that we have seen earlier.
 *
 * Reference:
 *
 * [3wayPartitioning in quickSort](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/1f49a44ed3cb9b9b1002491188fc08326c9379c8/src/coursera/ucSanDiego/module04DivideAndConquer/110quickSort3wayPartitioning.kt)
 *
 * Time complexity:
 *
 * Best and average case:
 *
 * O(n log n) because we divide (split) the problem into halves, and we get log n levels to get the problem of size 1.
 * At each level, we iterate through the n elements to compare the elements, to move the pointers, and to swap the
 * elements. So, O(n) work for each level. Hence, the total time complexity is O(n log n).
 *
 * Worst-case:
 *
 * O(n^2)
 *
 * Space complexity:
 *
 * Best and average case:
 *
 * O(log n) because of the recursion stack (depth).
 *
 * Worst-case:
 *
 * O(n)
 *
 * Knowledge: Lessons to learn and understand:
 *
 * 1. The recursion function always starts with a base condition.
 * 2. The base condition for a valid iteration: startIndex < endIndex.
 * 3. 3-pointers: lessThan, middle, and greaterThan.
 */
fun main() {

    fun swapElements(input: MutableList<Int>, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun quickSortWithManyDuplicate(input: MutableList<Int>, start: Int, end: Int) {
        if (start < end) {
            var lessThan = start
            var middle = start
            var greaterThan = end
            val randomIndex = Random.nextInt(start, end + 1)
            swapElements(input, randomIndex, end)
            val pivot = input[end]
            while (middle <= greaterThan) {
                when {
                    input[middle] < pivot -> {
                        if (middle != lessThan && input[middle] != input[lessThan]) {
                            swapElements(input, middle, lessThan)
                        }
                        lessThan++
                        middle++
                    }

                    input[middle] > pivot -> {
                        if (input[greaterThan] != input[middle]) {
                            swapElements(input, middle, greaterThan)
                        }
                        greaterThan--
                    }

                    else -> {
                        middle++
                    }
                }
            }
            quickSortWithManyDuplicate(input, start, lessThan - 1)
            quickSortWithManyDuplicate(input, greaterThan + 1, end)
        }
    }

    val totalElements = readln().toInt()

    val input = readln().split(" ").map {
        it.toInt()
    }.toMutableList()

    quickSortWithManyDuplicate(input, 0, input.lastIndex)
    println(input.joinToString(" "))
}