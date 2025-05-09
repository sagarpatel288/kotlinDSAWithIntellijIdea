package coursera.ucSanDiego.course01algorithmicToolbox.module04DivideAndConquer

import kotlin.random.Random

/**
 * Explain (demonstrate, illustrate) the randomized quicksort algorithm.
 * OR:
 * Sort the given input array using the quicksort algorithm where (or handle the case when)
 * the input array can be already sorted, either in ascending or in descending order (so, it can be a reversed array).
 * OR:
 * Sort the given input array using the quicksort algorithm and reduce the possibility
 * of worst-case runtime complexity O(n squared) to O(n log n).
 *
 * Bottom line:
 * The quicksort algorithm to sort an array that handles the case when the input array
 * is already sorted either in ascending or in descending order.
 *
 * The quicksort algorithm is highly efficient on average,
 * but its efficiency can degrade dramatically to O(n²) in some worst-case scenarios.
 * The worst-case occurs when the pivot chosen for partitioning ends up being extremely unbalanced—
 * for example, always selecting the smallest or largest element,
 * which can happen when the input array is already sorted or nearly sorted.
 *
 * A random pivot increases the chances of a balanced partition,
 * and a balanced partition reduces the problem size more quickly than a highly unbalanced partition.
 * This allows us to reach the base case, where the problem size is 1, faster.
 * Consequently, we achieve a shallower recursion depth.
 *
 * How? Because, each recursion reduces the problem size faster (roughly at the rate of n/2.).
 * So, we reach the base condition (where the problem size is 1) faster.
 *
 * Technically, when we use a random pivot and obtain a balanced partition,
 * we end up with roughly two equal parts of the original problem.
 * This means the random pivot divides the problem roughly as n/2.
 * According to the recursion relationship, we achieve log n levels (a logarithmic depth),
 * resulting in an overall runtime complexity of O(n log n) on an average.
 *
 * Conversely, if we use a fixed pivot position
 * (a.k.a. a deterministic, definite, or a predictive pivot position. E.g., either always the first or the last),
 * we increase the likelihood of consistently selecting either the smallest or the largest pivot element.
 * This raises the chances of creating an unbalanced partition, which slows down the reduction of the problem size.
 * Also, we end up comparing each element with almost all other elements, resulting in deep recursion.
 * As a result, we take more time (iterations and comparisons) to reach the base case, where the problem size is 1.
 *
 * Technically, if we always select either the smallest or the largest element as a pivot,
 * we reduce the problem size by (n-1) with each recursion and get a linear depth.
 * Thus, according to the arithmetic series and the recurrence relation, we obtain a runtime complexity of O(n squared).
 *
 * Here's a more detailed explanation:
 *
 * 1. Random Pivot: When a pivot is chosen randomly,
 * it tends to reduce the chances of consistently choosing the worst-case scenarios
 * (like the largest or smallest element in a sorted or reverse-sorted array).
 * This randomness increases the likelihood of obtaining a balanced split of the dataset.
 *
 * 2. Balanced Partition: A balanced partition means that the pivot divides the array into two sub-arrays
 * that are roughly equal in size. For example, if you have an array of size ( n ),
 * ideally, each sub-array would end up being about n/2.
 * This balance is crucial because it reduces the depth of recursion in QuickSort.
 *
 * 3. Faster Problem Size Reduction: When the problem is split into smaller, more equal partitions,
 * the number of total partitioning operations (or recursive calls) required to reach the base case
 * (where the size of the problem is 1) is minimised. This leads to an overall better time complexity,
 * ideally approaching O(n log n) for QuickSort, as opposed to the worst case of O(n^2),
 * which occurs when the partitions are highly unbalanced
 * (e.g., when the pivot is always the smallest or the largest element).
 *
 * In summary, by selecting a random pivot, QuickSort can consistently achieve balanced partitions,
 * which allows it to reduce the problem size more quickly and efficiently,
 * leading to faster sorting times in both average and best-case scenarios.
 *
 * Let us understand this with an example:
 *
 * The below table shows a deterministic (fixed pivot) quicksort algorithm.
 *
 * | Step 	| Array Before Partition 	| Partition Pivot 	|   Left Part  	| Right Part 	| Total Comparisons 	| Recursive Depth 	|
 * |:----:	|:----------------------:	|:---------------:	|:------------:	|:----------:	|:-----------------:	|:---------------:	|
 * |   1  	|     [1, 2, 3, 4, 5]    	|        5        	| [1, 2, 3, 4] 	|     []     	|         4         	|        1        	|
 * |   2  	|      [1, 2, 3, 4]      	|        4        	|   [1, 2, 3]  	|     []     	|         3         	|        2        	|
 * |   3  	|        [1, 2, 3]       	|        3        	|    [1, 2]    	|     []     	|         2         	|        3        	|
 * |   4  	|         [1, 2]         	|        2        	|      [1]     	|     []     	|         1         	|        4        	|
 * |   5  	|           [1]          	|        1        	|      []      	|     []     	|         0         	|        5        	|
 *
 * The below table shows the randomized quicksort algorithm.
 *
 * | Step 	| Array Before Partition 	| Partition Pivot 	| Left Part 	| Right Part 	| Total Comparisons 	| Recursive Depth 	|
 * |:----:	|:----------------------:	|:---------------:	|:---------:	|:----------:	|:-----------------:	|:---------------:	|
 * |   1  	|     [1, 2, 3, 4, 5]    	|        3        	|   [1, 2]  	|   [4, 5]   	|         4         	|        1        	|
 * |   2  	|         [1, 2]         	|        2        	|    [1]    	|     []     	|         1         	|        2        	|
 * |   3  	|         [4, 5]         	|        5        	|    [4]    	|     []     	|         1         	|        2        	|
 * |   4  	|           [1]          	|        1        	|     []    	|     []     	|         0         	|        3        	|
 * |   5  	|           [4]          	|        4        	|     []    	|     []     	|         0         	|        3        	|
 *
 * To visualize the comparison, to understand how [1, 2] and [4, 5] fall as having the same recursive depth (2), and
 * to understand how come [1] and [4] have the same recursive depth [3], please check the below images:
 *
 * @see [quickSortPartitionImage01](res/level40Module4AlgorithmExercise/quickSortPartitionImage01.png)
 * @see [quickSortPartitionImage02](res/level40Module4AlgorithmExercise/quickSortPartitionImage02.png)
 *
 */
fun main() {

    var swapElementCount = 0
    var getPartitionIndexFunCount = 0
    var iterationCount = 0
    var markerChangeCount = 0
    var quickSortFunCount = 0

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        println(": :swapElements: funCount: ${++swapElementCount}")
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    fun getPartitionIndex(input: IntArray, startIndex: Int, endIndex: Int): Int {
        println(": :getPartitionIndex: funCount: ${++getPartitionIndexFunCount}")
        // The `until` param of Random.nextInt(from, until) is exclusive (not included). Hence, we do: +1 to include it.
        val randomIndex = Random.nextInt(startIndex, endIndex + 1)
        // Why do we swap the random index with the end index?
        // We swap the random index-position with the last index-position.
        // So that, everything else, all the other logic, will remain same as the deterministic (fixed pivot) quicksort.
        // 1. If you remember, we select the pivot element from the selected random index, right?
        // 2. And then, we compare all the other elements with the pivot, right?
        // 3. If we swap the random index with the end index, we can simply iterate from start < end,
        // where end will be our pivot, and we don't compare the pivot with itself, right?
        // 4. Now, if we don't swap the random index position with the end index position,
        // we have to add a condition during the iteration to avoid unnecessary swapping.
        // E.g., if i == randomIndex, skip.
        // And we need to handle proper adjustment to the iteration index and the marker (partition) index as well.
        // E.g., whether to increase the marker (partition) index or not, if yes, then in which condition, etc.
        // 4.1 That particular if condition will be executed for n times, where n is the size of the iteration (start < end).
        // 4.2. We can avoid this unnecessary load by swapping the random index with the end index.
        swapElements(input, randomIndex, endIndex)
        val pivot = input[endIndex]
        var markerIndex = startIndex - 1
        for (j in startIndex..<endIndex) {
            println(": :getPartitionIndex: iterationCount: ${++iterationCount}")
            if (input[j] <= pivot) {
                println(": :getPartitionIndex: markerChangeCount: ${++markerChangeCount}")
                markerIndex++
                if (markerIndex != j) {
                    swapElements(input, markerIndex, j)
                }
            }
        }
        swapElements(input, ++markerIndex, endIndex)
        return markerIndex
    }

    fun quickSort(input: IntArray, startIndex: Int, endIndex: Int) {
        println(": :quickSort: funCount: ${++quickSortFunCount}")
        if (startIndex < endIndex) {
            val partitionIndex = getPartitionIndex(input, startIndex, endIndex)
            quickSort(input, startIndex, partitionIndex - 1)
            quickSort(input, partitionIndex + 1, endIndex)
        }
    }

    fun getInput(): IntArray {
//        intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
        return intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: randomized quicksort: sortedArray: ${input.toList()}")
}