package courses.uc.course01algorithmicToolbox.module04DivideAndConquer

/**
 * # References / Resources
 *
 * * [Jenny's Lectures](https://youtu.be/jlHkDBEumP0?si=FHxIzfEXQHItEj-7)
 * * [Abdul Bari Sir](https://youtu.be/mB5HXBb_HY8?si=Kjko3lQNQHdG5kG_)
 * * [Apna College](https://youtu.be/unxAnJBy12Q?si=cVx4DUuxRC3cre69)
 * * [Animation by hello_byte](https://youtube.com/shorts/QFrgq60Y6mw?si=rdI5z_1SbCJLHSO5)
 * * [Animation by boraxalgo](https://youtube.com/shorts/dZhFmu19N9U?si=bZjILcFJ-hffHEd6)
 *
 * # Problem statement
 *
 * * Explain `Merge Sort` with an example, or sort the given input array using the merge-sort algorithm:
 * **OR**
 * * Sort the given array in such a way that the best, average, and worst-case runtime complexity remains `O(n log n)`,
 * and the space complexity does not increase more than `O(n)`.
 * **OR**
 * * Sort the given array using the divide and conquer technique.
 *
 * ## Explanation
 *
 * * Merge Sort is a "divide and conquer" sorting algorithm.
 * * It works by breaking down a list into smaller sub-lists until each sublist contains only one element
 * (which is considered sorted by itself, and it is our base case),
 * and then merging those sub-lists back together in a sorted order.
 * * This approach ensures that the final output is a sorted list.
 *
 * **How do we divide an array into sub-lists until the size of each sublist becomes 1?**
 *
 * * We do that with the help of the middle index.
 * * We recursively find the middle index, and each time, the array shrinks.
 *
 * **And how do we find the middle index?**
 *
 * ```
 * val mid = start + (end - start) / 2
 * ```
 *
 * **What do we do after we find the middle index?**
 *
 * * Once we find the middle index, we divide the input array into two parts.
 * * From the start to the middle index, and from the middle + 1 to the end index.
 * * So, basically, we get a left part and a right part.
 * * Then, we compare each element from both parts. We insert the smaller element into the new list.
 * * The size of the new list will be `end - start + 1`.
 *
 * **How do we compare? What do we do to compare each element from both parts?**
 *
 * * With the help of 3 pointers, iteration, and updating the original input array using the temporary sorted array.
 *
 * **Pointers**
 *
 * * We take three markers (pointers).
 * * One for the left part. The left part starts with the start index.
 * * Hence, the initial position of the left pointer will be the start index.
 * * One for the right part. The right part starts from the middle + 1 index.
 * * Hence, the initial position of the right pointer will be the middle + 1 index.
 * * One for the newly created list. It starts with the index 0.
 * * We increase it as we insert new elements into the list.
 *
 * **Iteration**
 *
 * * Now, as long as the left pointer is less than or equal to the middle index, and the right pointer is less than or
 * equal to the end index, we compare the elements of both parts and insert the smallest element into the newly created
 * list.
 * * Once a particular part is finished, we insert the remaining elements into the newly created list.
 *
 * **Updating the original input array**
 *
 * * Once the iteration is finished, we read the values from the newly created list,
 * and update the original input array as per the below formula:
 * ```
 * originalInputArray[start + i] = sortedArray[i]
 * ```
 *
 * ## Time Complexity
 *
 * * The best, average, and worst-case runtime complexity of a merge-sort algorithm is O(n log n).
 *
 * **How? Why?**
 *
 * * Because we always cut the input into two halves until the size of each sub-problem becomes 1.
 * * The divide process takes `O(1)` runtime, and we can do it until `(log n)` levels.
 * * So, we get a total of `(log n)` levels.
 * * The merge (conquer) process involves comparing elements and merging them back in sorted order.
 * * We iterate through each element for the given parts.
 * * Hence, it takes `O(n)` runtime.
 * * We compare and merge for each level.
 * * So, it becomes `n * log n`.
 * * So, the runtime complexity of a merge-sort algorithm is `O(n log n)`.
 *
 * ## Space Complexity
 *
 * * We create a temporary array of size `n` during the conquer process (compare and merge in sorted array).
 * * So, it takes `O(n)` space.
 * * The recursion stack also takes `O(log n)` space, but it is lower than the space required by the temp array, `O(n)`.
 * * And as we take the dominant term, we can say that the space complexity of a merge-sort algorithm is `O(n)`.
 *
 * ## Questions
 *
 * * Why don't we simply divide the input into two parts and then call the merge function,
 * which compares each element of both sides and inserts the smaller element into the sorted array?
 *
 * **Answer**
 *
 * * While it is possible, the fact that neither the left nor the right part is sorted,
 * increases the complexity and inefficiency when we attempt to insert the element into the sorted array.
 *
 * ## Key-Points
 *
 * **`mergeSort` function**
 * * Middle index, two parts, and recursive call
 *
 * **`merge` function**
 * * 3 markers (pointers) and their initial values
 * * 3 separate (independent) `while` loops, and
 * * Copy from the temp array
 *
 * **Copy formula**
 * ```
 * `original[i + startIndex] = temp[i]`
 * ```
 *
 * ## Knowledge: Lessons to learn and understand
 *
 * **The exercise teaches us**
 *
 * * How to sort a collection?
 * * How to divide a collection?
 * * How to merge a collection in sorted order?
 * * How to validate the index range of a collection? (start < end)
 *
 */
fun main() {

    /**
     * # What and why of this [merge] function
     *
     * * The [mergeSort] function gives the two parts that this [merge] function needs to merge in sorted order.
     *
     * # How does this function do that?
     *
     * * The `[startIndex] to [mid]` is a left part and `[mid] + 1 to [endIndex]` is a right part of the given [array].
     * * For example, the original array might `[8, 10, 7, 3, 5]`.
     * * In that case, we might get `[startIndex] = 0`, `[mid] = 2`, and `[endIndex] = 4`.
     * * It means that the left part is from index `[startIndex] to [mid] = 0 to 2`.
     * * The right part is from index `[mid] + 1 to [endIndex] = 3 to 4`.
     * * The [merge] function compares each element of these left and right parts.
     * * To compare and to ensure that we don't cross the boundaries of any part, we use `pointers` or `markers`.
     * * For example, the `leftPointer` will move from `[startIndex] to [mid] = 0 to 2`.
     * * And the `rightPointer` will move from `[mid] + 1 to [endIndex] = 3 to 4`.
     * * We ensure that the iteration of the left part goes up to the [mid] and not beyond that.
     * * Similarly, we ensure that the iteration of the right part does not go beyond the [endIndex].
     * * We compare each element of both parts, one by one.
     * * So, the `leftPointer` iterates from `[startIndex]`, and the `rightPointer` iterates from `[mid] + 1` index.
     * * We compare the elements.
     * * For example: `array[leftPointer] Vs. array[rightPointer]`.
     * * And insert it into a new `temp` array in sorted order.
     * * For example, `temp[tempPointer++] = result of comparison`.
     * * So, we need one more pointer, `tempPointer`, to insert the comparison result into the `temp` array.
     *
     * ## Example
     *
     * * For example, suppose we have `L[33, 42, 51]` and `R[15, 24]`.
     * * We compare `33` Vs. `15`.
     * * We insert `15` into the `temp array`.
     * * Now, we compare the remaining element `33`, of the left side, with the next remaining element `24`,
     * of the right side.
     * * So, we insert `24` into the temp array.
     * * Once a particular part completes the iteration, we insert the remaining elements.
     * * So, in our example, once we insert `24` into the `temp` array, the right part completes the iteration.
     * * Hence, we can insert the remaining elements, `33, 42, and 51` of the left side into the `temp` array.
     * * Once we finish the iteration of both parts, we iterate through the `temp` array and insert the elements
     * back to the original [array] using the [startIndex].
     * * So in that sense, we modify the existing [array] using the sorted `temp` array and `[startIndex]`.
     */
    fun merge(array: IntArray, startIndex: Int, mid: Int, endIndex: Int) {
        val tempArray = IntArray(endIndex - startIndex + 1)
        var currentIndexOfLeft = startIndex
        var currentIndexOfRight = mid + 1
        var currentIndexOfSortedArray = 0

        // This while loop is to compare and then insert the elements.
        while (currentIndexOfLeft <= mid && currentIndexOfRight <= endIndex) {
            if (array[currentIndexOfLeft] <= array[currentIndexOfRight]) {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
            } else {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
            }
        }

        // Note that these two while loops are outside (after) the first while loop.
        // This while loop is to insert the remaining elements without comparison,
        // because the right part has finished the iteration.
        // Hence, we need to add all the remaining elements of the left part.
        while (currentIndexOfLeft <= mid) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
        }

        // Similar to the above while loop, this while loop is to insert elements without comparison.
        // Because, the left part has finished the iteration.
        // Hence, we need to add all the remaining elements of the right part.
        while (currentIndexOfRight <= endIndex) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
        }

        // We have inserted the elements in sorted order in the temp array.
        // Now, we need to use this temp array to insert the elements in sorted order in the original array.
        for (i in tempArray.indices) {
            // Why `startIndex + i`? Because the `tempArray` will always start with the index 0,
            // and it does not always represent the correct associated / corresponding index of the original array,
            // where we are putting the elements by copying from the `tempArray`.
            // The size of the `tempArray` can be different at different stages/iterations.
            // For example, if the problem is `[51, 42, 33, 24, 60]`,
            // then at some point, the `tempArray` can have size `2` for the right side part `[24, 60]`.
            // If we simply take `i`, `60` can go to the second place at index `1` in the original array,
            // which will give the wrong answer.
            // The `startIndex + i` saves us. So, `60` is at `i = 1` in the `tempArray`, where `startIndex` is `3`.
            // So, we would place `60` at the `startIndex + i` = `3 + 1` = 4th index in the original array
            // which is the correct one.
            // So, the `startIndex` is our offset here.
            array[startIndex + i] = tempArray[i]
        }
    }


    /**
     * # Pre-requisite
     *
     * * Recursion.
     *
     * # About this function: [mergeSort]
     *
     * ## What does this function do? Why? How?
     *
     * * The function recursively divides the input [array] by half until the size of the sub-array becomes 1.
     * * We do not create new arrays or sub-arrays. We cut the [array] using indices.
     * * When we cut an [array] in half, we get two parts.
     * * We recursively cut the [array] in half until the size of each part becomes 1.
     * * At that moment, we compare the two parts and merge them back into sorted order.
     * * We do this recursively to sort the [array].
     *
     * ## How?
     *
     * * We calculate the middle point (index) of the given (incoming) [array].
     * * So, we get two parts.
     * * One part before and up to the middle point, and the second part, after the middle point.
     * * We recursively call the function for each part until the size of each part becomes 1.
     * * Once we reach the base case (where the size of a sub-array is 1) for both the parts,
     * we call the [merge] function.
     * * The [merge] function compares both the parts and merges them back in sorted order.
     * * We can understand the function with the following example:
     * * Suppose the original input array is: `[51, 42, 33, 24, 15]`
     *
     * * Then:
     *
     * | Step                  	| Left Part    	| Right Part 	| Merged Result        	|
     * |-----------------------	|--------------	|------------	|----------------------	|
     * | Initial Split         	| [51, 42, 33] 	| [24, 15]   	|                      	|
     * | Sorting [51, 42, 33]  	| [51, 42]     	| [33]       	|                      	|
     * | Sorting [24, 15]      	| [24]         	| [15]       	|                      	|
     * | Merge [51] & [42]     	| [51]         	| [42]       	| [42, 51]             	|
     * | Merge [42, 51] & [33] 	| [42, 51]     	| [33]       	| [33, 42, 51]         	|
     * | Merge [24] & [15]     	| [24]         	| [15]       	| [15, 24]             	|
     * | Final Merge           	| [33, 42, 51] 	| [15, 24]   	| [15, 24, 33, 42, 51] 	|
     */
    fun mergeSort(array: IntArray, startIndex: Int, endIndex: Int) {
        if (array.size <= 1) {
            return
        }
        // start == end means the array has one element.
        // start > end means, an invalid range, or an empty array.
        // start < end confirms that the array has more than one element.
        // start < end is our base condition.
        if (startIndex < endIndex) {
            // We cut the array into two halves.
            // To cut the array into two halves, we need to find the middle.
            // A better way to find the mid that limits the number within the type boundary.
            // i.e., If we do (startIndex + endIndex) /2, the addition can cross the `integer` boundary!
            val mid = startIndex + (endIndex - startIndex) / 2
            // The left part (startIndex to mid), where the mid-index becomes the endIndex.
            mergeSort(array, startIndex, mid)
            // The right part (mid + 1 to endIndex) where the mid + 1 becomes the startIndex.
            // The below function will get executed only after the above first recursive call is completed.
            mergeSort(array, mid + 1, endIndex)
            // Only after the above two recursive calls are completed, this call will happen.
            merge(array, startIndex, mid, endIndex)
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(51, 42, 33, 24, 15)
    }

    val input = getInput()
    mergeSort(input, 0, input.lastIndex)
}