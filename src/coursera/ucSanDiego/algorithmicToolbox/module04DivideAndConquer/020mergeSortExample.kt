package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquer

/**
 * Problem statement:
 *
 * Explain `Merge Sort` with an example or sort the given input array using the merge-sort algorithm:
 * OR
 * Sort the given array in such a way that the best, average, and worst-case runtime complexity remains O(n log n),
 * and the space complexity does not increase more than O(n).
 * OR
 * Sort the given array using the divide and conquer technic.
 *
 * ----------------------- Explanation -----------------------
 *
 * Merge Sort is a "divide and conquer" sorting algorithm.
 * It works by breaking down a list into smaller sub-lists until each sublist contains only one element
 * (which is considered sorted by itself, and it is our base case),
 * and then merging those sub-lists back together in a sorted order.
 * This approach ensures that the final output is a sorted list.
 *
 * How do we divide an array into sub-lists until the size of each sublist becomes 1?
 *
 * To divide the main array into sub-arrays until each sub-array has only one element
 * (i.e., until each sub-array size is no more than 1), we do the following:
 *
 * With the help of the middle index. We recursively find the middle index, and each time, the array shrinks.
 *
 * And how do we find the middle index?
 *
 * `val mid = start + (end - start) / 2`
 *
 * What do we do after we find the middle index?
 *
 * Once we find the middle index, we divide the input array into two parts.
 * From the start to the middle index, and from the middle + 1 to the end index.
 * So, basically, we get a left part and a right part.
 *
 * Then, we compare each element from both parts. We insert the smaller element into the new list.
 *
 * The size of the new list will be `end - start + 1`.
 *
 * How do we compare? What do we do to compare each element from both parts?
 *
 * With the help of 3 pointers, iteration, and updating the original input array using the temporary sorted array.
 *
 * Pointers:
 *
 * We take three markers (pointers).
 *
 * One for the left part. The left part starts with the start index.
 * Hence, the initial position of the left pointer will be the start index.
 *
 * One for the right part. The right part starts from the middle + 1 index.
 * Hence, the initial position of the right pointer will be the middle + 1 index.
 *
 * One for the newly created list. It starts with the index 0. We increase it as we insert new elements into the list.
 *
 * Iteration:
 *
 * Now, as long as the left pointer is less than or equal to the middle index
 * and the right pointer is less than or equal to the end index,
 * we compare the elements of both the parts and insert the smallest element to the newly created list.
 *
 * Once a particular part is finished, we insert the remaining elements to the newly created list.
 *
 * Updating the original input array:
 *
 * Once the iteration is finished, we read the values from the newly created list,
 * and update the original input array as per the below formula:
 *
 * `originalInputArray[start + i] = sortedArray[i]`
 *
 * Time Complexity:
 *
 * The best-case runtime complexity of a merge-sort algorithm is O(n log n).
 * The average-case runtime complexity of the merge-sort algorithm is also O(n log n).
 * And the worst-case runtime complexity of the merge-sort algorithm is also O(n log n).
 *
 * How? Why?
 *
 * Because we always cut the input into two halves until the size of each sub-problem becomes 1.
 * The divide process takes O(1) runtime, and we can do it until (log n) levels.
 * So, we get a total of (log n) levels.
 *
 * The merge (conquer) process involves comparing elements and merging them back in sorted order.
 * We iterate through each element for the given parts.
 * Hence, it takes O(n) runtime.
 *
 * We compare and merge for each level.
 * So, it becomes n * log n.
 * So, the runtime complexity of a merge-sort algorithm is O(n log n).
 *
 * Space Complexity:
 * We create a temporary array of size n during the conquer process (compare and merge in sorted array).
 * So, it takes O(n) space.
 * The recursion stack also takes O(log n) space, but it is lower than the space required by the temp array, O(n).
 * And as we take the dominant term, we can say that the space complexity of a merge-sort algorithm is O(n).
 *
 * ----------------------- Questions -----------------------
 *
 * 1. Why don't we simply divide the input into two parts and then call the conquer function,
 * which compares each element of both sides and inserts the smaller element into the sorted array?
 *
 * Ans:
 * While it is possible, the fact that neither the left nor the right part is sorted,
 * increases the complexity and inefficiency when we attempt to insert the element into the sorted array.
 *
 *
 * ----------------------- Knowledge: Lessons to learn and understand -----------------------
 *
 * The exercise teaches us:
 *
 * 1. How to sort a collection?
 * 2. How to divide a collection?
 * 3. How to merge a collection in ascending order?
 * 4. How to validate the index range of a collection? (start <= end)
 *
 */
fun main() {

    var divideFunCount = 1
    var ifOfDivideCount = 1

    /**
     * What?
     *
     * The [divideAndConquer] function gives the two parts that the [conquer] function needs to merge in sorted order.
     *
     * How?
     *
     * The [conquer] function creates a temporary array based on the given range: [startIndex] and [endIndex].
     * The [startIndex] to [mid] is a left part and [mid] + 1 to [endIndex] is a right part.
     * The [conquer] function compares each element of the [array]
     * within the provided range [startIndex], [mid], and [endIndex] for both the parts.
     *
     * However, we don't have two separate arrays to compare.
     * We have these [startIndex], [mid], and [endIndex].
     * So, the left part is: from [startIndex] to [mid].
     * And the right part is: from [mid] + 1 to [endIndex].
     *
     * So, we need to make sure that the left part does not exceed the limit and goes into the right part.
     * And vice versa. That is, we will make sure that we stop the iteration of the right part when it reaches the end.
     *
     * To achieve that, we check that the iteration of the left part goes up to the [mid] and not beyond that.
     * Similarly, we make sure that the iteration of the right part does not go beyond the [endIndex].
     *
     * We compare each element of both the parts one by one and insert it into a new temp array in sorted order.
     * Now, to compare each element of both parts, we need to keep track of the remaining elements in both the parts.
     *
     * So, we take one variable that keeps track of the left part (currentIndexOfLeft),
     * one variable for the right part (currentIndexOfRight),
     * and one variable that inserts the elements to the temp array in sorted order (currentIndexOfSortedArray).
     *
     * When we insert one element to the temp array,
     * we need to compare the remaining element of one side with the next remaining element of the other side.
     *
     * For example, suppose we have L[33, 42, 51] and R[15, 24].
     * We compare 33 Vs. 15. We insert 15 into the temp array.
     * Now, we compare the remaining element 33, of the left side with the next remaining element 24, of the right side.
     * So, we insert 24 to the temp array.
     *
     * Once a particular part completes the iteration, we insert the remaining elements.
     * So, in our example, once we insert 24 to the temp array, the right part completes the iteration.
     * Hence, we can insert the remaining elements 33, 42, and 51 of the left side to the temp array.
     *
     * Once we finish the iteration of both the parts, we iterate through the temp array and insert the elements
     * back to the original [array] using the [startIndex].
     */
    fun conquer(array: IntArray, startIndex: Int, mid: Int, endIndex: Int) {
        val tempArray = IntArray(endIndex - startIndex + 1)
        var currentIndexOfLeft = startIndex
        var currentIndexOfRight = mid + 1
        var currentIndexOfSortedArray = 0

        // This while loop is to compare and then insert the elements.
        while (currentIndexOfLeft <= mid && currentIndexOfRight <= endIndex) {
            println("\n :conquer: incomingArray: ${array.toList()} :tempArray: ${tempArray.toList()} startIndex: $startIndex currentIndexOfLeft: $currentIndexOfLeft mid: $mid endIndex: $endIndex currentIndexOfRight: $currentIndexOfRight leftElement: ${array[currentIndexOfLeft]} rightElement: ${array[currentIndexOfRight]} currentIndexOfSortedArray: $currentIndexOfSortedArray \n")
            if (array[currentIndexOfLeft] <= array[currentIndexOfRight]) {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
            } else {
                tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
            }
        }

        println(" :conquer: after the first while loop: before adding the remaining left part: tempArray: ${tempArray.toList()}")

        // Note that these two while loops are outside (after) the first while loop.
        // This while loop is to insert the remaining elements without comparison,
        // because the right part has finished the iteration.
        // Hence, we need to add all the remaining elements of the left part.
        while (currentIndexOfLeft <= mid) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfLeft++]
        }

        println(" :conquer: after adding the remaining left part: before adding the remaining right part: tempArray: ${tempArray.toList()}")
        // Similar to the above while loop, this while loop is to insert elements without comparison.
        // Because, the left part has finished the iteration.
        // Hence, we need to add all the remaining elements of the right part.
        while (currentIndexOfRight <= endIndex) {
            tempArray[currentIndexOfSortedArray++] = array[currentIndexOfRight++]
        }

       println(" :conquer: after if-else and all the while loops: sorted tempArray: ${tempArray.toList()} \n")

        // We have inserted the elements in sorted order in the temp array.
        // Now, we need to use this temp array to insert the elements in sorted order in the original array.
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
            array[startIndex + i] = tempArray[i]
        }
        println(" :conquer: sortedArray: ${array.toList()} \n")
    }


    /**
     * Pre-requisite: Recursion.
     *
     * This function:
     *
     * What?
     *
     * The function recursively divides the input [array] by half until the size of the sub-array becomes 1.
     * We do not create new arrays or sub-arrays. We cut the [array] using indices.
     * When we cut an [array] into half, we get two parts.
     * When we recursively cut the [array] into half, the size of each part becomes 1.
     * At that moment, we compare the two parts and merge them back into sorted order.
     * We do this recursively to sort the [array].
     *
     * How?
     *
     * We calculate the middle point (index) of the given (incoming) [array].
     * So, we get two parts. One part before and up to the middle point, and the second part, after the middle point.
     * We recursively call the function for each part until the size of each part becomes 1.
     *
     * Once we reach the base case (where the size of a sub-array is 1) for both the parts,
     * we call the [conquer] function.
     * The [conquer] function compares both the parts and merge them back in sorted order.
     *
     * We can understand the function with the below example:
     * Suppose the original input array is: [51, 42, 33, 24, 15]
     *
     * Then:
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
    fun divideAndConquer(array: IntArray, startIndex: Int, endIndex: Int) {
        println(" :divide: array: ${array.toList()} divideFunCount: ${divideFunCount++} startIndex: $startIndex endIndex: $endIndex")
        if (array.size <= 1) {
            println(": :divideAndConquer: sorted: ${array.toList()}")
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
            println(" :divide: ifOfDivideCount: ${ifOfDivideCount++} midIndex: $mid ")
            // The left part (startIndex to mid) where the mid-index becomes the endIndex.
            divideAndConquer(array, startIndex, mid)
            // The right part (mid + 1 to endIndex) where the mid + 1 becomes the startIndex.
            // The below function will get executed only after the above first recursive call is completed.
            divideAndConquer(array, mid + 1, endIndex)
            // Only after the above two recursive calls are completed, this call will happen.
            conquer(array, startIndex, mid, endIndex)
        }
    }

    fun getInput(): IntArray {
        return intArrayOf(51, 42, 33, 24, 15)
    }

    val input = getInput()
    divideAndConquer(input, 0, input.lastIndex)
    println(": :main: sorted: ${input.toList()}")
}