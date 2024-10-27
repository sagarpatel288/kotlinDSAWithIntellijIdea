package level20Week2AlgorithmicWarmUp

import kotlin.system.exitProcess

fun  main() {

    /**
     * Problem Statement:
     * Range Sum Queries Problem:
     * Given an integer array and a set of ranges in it, compute the sum for the given range / each range.
     *
     * ----------------------- Explanation -----------------------
     *
     * We have a collection [originalCollection].
     * We have an index pair [indexPair].
     * We need to find and give sum of all the elements between and inclusive of the index pair [indexPair].
     * So, for example, suppose we have a collection [originalCollection] as [3, 5, 4, 2, 1, 9]
     * and the index pair [indexPair] as [2, 4], then we need to do addition of all the elements of the [originalCollection]
     * between and inclusive of indices [2, 4]. I.e., 4 + 2 + 1 = 7.
     * Note that the collection [originalCollection] does not have to be sorted, and it can contain negative numbers as well.
     *
     * Now, we can do it manually. We would iterate from the left index up to the right index,
     * and we would keep the track of the total value.
     * but it takes O(N*Q) time complexity.
     *
     * Consider an array [3, 5, 4, 2, 1, 9] and multiple queries asking for sums of sub-arrays:
     *
     * Suppose we have a query asking for the sum of elements between indices 1 and 4 (5, 4, 2, 1).
     * We iterate through the elements between indices 1 and 4 to find the sum.
     * If you get Q such queries, you would need to iterate through different ranges Q times.
     *
     * So, as a better efficient solution, we use a prefixedSum collection.
     * Each element of the preFixedSum collection will be an accumulative sum from the index 0 up to the current index.
     * For example:
     * the 2nd element of the preFixedSum will represent the accumulative sum of the given [originalCollection]
     * from the index 0 up to the index 2 inclusively.
     *
     * | Indices                                      	| 0 	| 1 	| `2` 	| 3  	| `4`  	| 5  	|
     * |----------------------------------------------	|---	|---	|---	|----	|----	|----	|
     * |                                              	|   	|   	|   	|    	|    	|    	|
     * | Original <br>Values                          	| 3 	| 5 	| `4` 	| `2`  	| `1`  	| 9  	|
     * |                                              	|   	|   	|   	|    	|    	|    	|
     * | Accumulative <br>Prefixed <br>Sum Collection 	| 3 	| `8` 	| 12 	| 14 	| `15` 	| 24 	|
     *
     * So, we create (define) a new collection called `prefixedSumCollection`.
     * Now, let us understand what is happening here and how we add elements there.
     *
     * If we notice, each `i+1` element of the prefixedCollectionSum
     * is a sum of prefixedSumCollection[i] and originalCollection[i + 1].
     *
     * If we iterate as `for(i in originalCollection.indices)`,
     * then we must take care of the `originalCollection(i+1)` part as it can give the `ArrayIndexOutOfBound` exception.
     * So, we can iterate up to `for(i in originalCollection.indices - 1)`.
     *
     * We can also notice that the accumulative sum of index pair (2, 4) for the [originalCollection] is
     *
     * = prefixedSumCollection[4] - prefixedSumCollection[1]
     *
     * = 15 - 8 = 7.
     *
     * Hence, the formula is:
     * `s(l, r) = p(r) - p(l - 1)`
     * where s is the original collection, p is the prefixedSum collection, l is the left side index, and
     * r is the right side index.
     *
     * However, there is a catch. What if the range of sum index pair starts with 0? E.g., (0, 5)?
     * The answer should be 24 but the formula gives
     * p(5) - p(0 - 1) = p(5) - p(-1) = `ArrayIndexOutOfBound` exception!
     *
     * So, maybe we can use the `if` condition. If the left side (starting index) is 0,
     * the answer will be: p(r) where r is the right side.
     *
     * What if we want to get rid of `l - 1` part in such a way that it would apply to the case even when l = 0?
     * So that we don't have to add the `if` condition, and we can stick to the original formula.
     *
     * If we revise the purpose of `l-1`, we notice that in order to get the right answer,
     * the value that we need to subtract, is one index behind.
     *
     * For example, for the range sum of index pair s(2, 4), the value that we need to subtract is 8, which is 1 index
     * behind (before) the given left side index 2.
     *
     * What if the value we need to subtract gets the same left index? I.e., in our example, how can we arrange our
     * `prefixedSumCollection` in such a way that the value that we need to subtract, that is 8, gets the index 2
     * instead of 1?
     *
     * | Original <br>Indices                         	|   	| 0 	| 1 	| `2` 	| 3  	| `4`  	| 5  	|
     * |----------------------------------------------	|---	|---	|---	|---	|----	|----	|----	|
     * |                                              	|   	|   	|   	|   	|    	|    	|    	|
     * | Original <br>Values                          	|   	| 3 	| 5 	| `4` 	| `2`  	| `1`  	| 9  	|
     * |                                              	|   	|   	|   	|   	|    	|    	|    	|
     * | Prefixed<br>Sum<br>Collection<br>Indices     	| 0 	| 1 	| `2` 	| 3 	| 4  	| 5  	| 6   	|
     * |                                              	|   	|   	|   	|   	|    	|    	|    	|
     * | Accumulative <br>Prefixed <br>Sum Collection 	| 0 	| 3 	| `8` 	| 12 	| 14 	| `15` 	| 24 	|
     *
     *
     * Now, the value (8) that we need to subtract is exactly at the given left side index (2) of the prefixedSumCollection.
     * Also notice that the right side value (15) is at the (r + 1) index of the `prefixedSumCollection` now.
     *
     * So, the formula becomes:
     * s(l, r) = p(r + 1) - p(l)
     * s(2, 4) = p(4 + 1) - p(2) = p(5) - p(2) = 15 - 8 = 7.
     * s(0, 5) = p(5 + 1) - p(0) = p(6) - p(0) = 24 - 0 = 24.
     *
     * So, the benefit is:
     * 1. We do not get the `IndexOutOfBound` exception when the left side is 0.
     * 2. We do not need to introduce an additional (special, extra) if condition when the left side is 0.
     *
     * Once we construct the prefix sum array, each query can be answered in O(1) time
     * by subtracting the relevant values from the prefix sum array.
     *
     * Suppose we need to find the sum of elements from index 1 to 4. The result is given by:
     * prefixSum[4 + 1] - prefixSum[1]
     * Which would be 15 - 3 = 12.
     *
     * Constructing the prefix sum array requires O(N) time since we iterate over the array once.
     * Each query, after constructing the prefix sum array, takes O(1) time to answer.
     * Thus, for Q queries, the total runtime would be O(N + Q), which is far better than O(N * Q) for large N and Q.
     *
     * Hence, prefixed sum approach improves the performance (efficiency) by reducing the runtime.
     *
     * Check the visual reference:
     * res/level20Week2AlgorithmicWarmUp/rangeSumPrefixedSum.png
     *
     * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/088aa5f944f2fdea04dce1d79d06b7bfe3b586de/res/level20Week2AlgorithmicWarmUp/rangeSumPrefixedSum.png)
     *
     * TL;DR:
     *
     * 1. Build a pre-defined accumulative sum array of size input + 1 and set [0] = 0.
     * 2. Store cumulative sum to pre-defined sum array by iterating through the input array.
     * 3. Cumulative sum formula: preSum[i + 1] = preSum[i] + input[i]
     * 4. Range sum formula:
     * for the given s(l, r), preSum[r + 1] - preSum[l].
     *
     */
    fun giveRangeSumQueriesImmutable(originalCollection: List<Int>, indexPair: List<Int>) {
        // Set the size of preSum as input + 1.
        val preFixedSumCollection = IntArray(originalCollection.size + 1)
        // Set preSum[0] = 0.
        preFixedSumCollection[0] = 0
        // Set the remaining cumulative values of the remaining indices by iterating and using the original input
        for (i in originalCollection.indices) {
            // for i = 0, prefixedSumCollection's 1 = prefixedSumCollection's 0 + originalCollection's 0
            // for i = 1, prefixedSumCollection's 2 = prefixedSumCollection's 1 + originalCollection's 1
            // for i = 2, prefixedSumCollection's 3 = prefixedSumCollection's 2 + originalCollection's 2
            // ...and so on...
            preFixedSumCollection[i + 1] = preFixedSumCollection[i] + originalCollection[i]
        }
        // Give s(l, r)
        val rightEnd = indexPair[indexPair.size - 1]
        val leftEnd = indexPair[0]
        // Use the formula: s(l, r) = preSum(r + 1) - preSum(l)
        println(preFixedSumCollection[rightEnd + 1] - preFixedSumCollection[leftEnd])
    }

    fun start() {
        try {
            println("Please enter space separated elements")
            val intArray = readln().split(" ").map {
                it.toInt()
            }
            println("Please enter space separated starting index and ending index of a range")
            val pair = readln().split(" ").map {
                it.toInt()
            }
            giveRangeSumQueriesImmutable(intArray, pair)
        } catch (e: Exception) {
            println("Terminated with the exception: $e")
        }

    }

    fun startOrStop() {
        println("Please enter 1 to continue or any other key to exit")
        try {
            if (readln().toInt() == 1) {
                start()
            } else {
                exitProcess(200)
            }
        } catch (e: Exception) {
            println("Terminated with the exception: $e")
        }
    }

    startOrStop()
}