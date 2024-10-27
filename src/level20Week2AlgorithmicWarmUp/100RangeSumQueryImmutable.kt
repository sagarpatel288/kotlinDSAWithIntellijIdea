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
     * Check the visual reference:
     * res/level20Week2AlgorithmicWarmUp/rangeSumPrefixedSum.png
     *
     * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/088aa5f944f2fdea04dce1d79d06b7bfe3b586de/res/level20Week2AlgorithmicWarmUp/rangeSumPrefixedSum.png)
     *
     */
    fun giveRangeSumQueriesImmutable(originalCollection: List<Int>, indexPair: List<Int>) {
        val preFixedSumCollection = IntArray(originalCollection.size + 1)
        preFixedSumCollection[0] = 0
        for (i in originalCollection.indices) {
            // for i = 0, prefixedSumCollection's 1 = prefixedSumCollection's 0 + originalCollection's 0
            // for i = 1, prefixedSumCollection's 2 = prefixedSumCollection's 1 + originalCollection's 1
            // for i = 2, prefixedSumCollection's 3 = prefixedSumCollection's 2 + originalCollection's 2
            // ...and so on...
            preFixedSumCollection[i + 1] = preFixedSumCollection[i] + originalCollection[i]
        }
        val rightEnd = indexPair[indexPair.size - 1]
        val leftEnd = indexPair[0]
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