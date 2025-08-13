package coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets

/**
 * # References / Resources
 *
 * * [Local HeapSort.md](docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * * [GitHub HeapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a8ba31ba7a33b54b77215516b7bc98eed35ed671/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 *
 * # Why do we have this function? What does it do? How does it do? Why does it do it that way? How does it help?
 *
 * * We have this function to sort the given [array] in ascending order using the `Heap Sort` algorithm.
 * * It converts the given [array] into a valid binary max heap tree by calling the [buildHeap] function.
 * * The [buildHeap] function ensures that the maximum value is at the root (index 0).
 * * Then, the [extractMax] function swaps the root (maximum) value with the last unsorted value.
 * * It reduces the end-index-boundary of the unsorted array, and grows the sorted portion from right-to-left.
 * * The swapping might violate the max heap property.
 * * So, the [extractMax] calls the [siftDown] function immediately after the swapping, but with the reduced end-index.
 * * The reduced end-index indicates that post end-index, we have placed the right elements at their right positions.
 * * It also indicates that the previous [extractMax] operation might have placed the previous max value at the end.
 * * And we do not want to consider (or include) that "end" while trying to repair the max heap tree via [siftDown].
 * * So, we exclude that "end" where we have placed the previous maximum value in its right position.
 * * So, it places the highest value at the last index, the second-highest value at the second last index, and so on...
 * * So, this [extractMax] process rearranges (mutates) the given [array] in right-to-left descending order.
 * * Which, in the end, makes the given [array] sorted in left-to-right ascending order.
 *
 * # Time Complexity
 *
 * * It uses the [buildHeap] function, whose realistic time complexity is `O(n)`.
 * * It calls the [extractMax] function almost `n` times.
 * * The [extractMax] function uses the [siftDown] function.
 * * The [siftDown] function has time complexity of `O(log n)` for each call.
 * * Hence, the total time complexity of this function is `O(n log n)` in all cases (Best, average, worst).
 *
 * # Space Complexity
 *
 * * It is an `in-place` algorithm.
 * * We do not use any additional memory that depends on or grows with the input size.
 * * Hence, the space complexity of this function is `O(1)`.
 *
 * @param array The [IntArray] that we want to sort into ascending order.
 */
fun heapSort(array: IntArray) {
    // Edge cases. Base conditions.
    // A heap with 0 or 1 element is already sorted.
    if (array.size <= 1) return
    // Build heap
    buildHeap(array)
    // We iterate from the last index to the 1st index, and avoid 0th index.
    // Because when we arrange all the indices from 1 to the last index, the index `0` is automatically arranged.
    for (i in array.size - 1 downTo 1) {
        // Filling the array from right-to-left (from the last to the first index).
        // The `i` is the final, true position of the maximum element.
        // And notice we start placing the maximum elements from right-to-left.
        extractMax(array, i)
    }
}

/**
 * # Why do we have this function? What does it do? How does it do? Why does it do it that way? How does it help?
 *
 * * We have this function to convert the given [array] into a binary max heap tree.
 * * It calls [siftDown] function for each index starting from `(n/2) - 1` down to `0` to cover every subtree.
 * * The reason we start from `(n/2) - 1` is that after this index, all the other nodes are last-level leaves only.
 * * And the reason to go backward from `(n/2) - 1` is to cover all the nodes and every subtree.
 * * Once the loop is finished, we expect that our [array] becomes a valid binary max heap tree.
 *
 * # Key-Lemma (or Key-Point)
 *
 * * We start from the `(n / 2) - 1` index. And the end-index will be [Array.lastIndex] of the [array] only.
 * * Because we need to ensure the subtree that the last parent makes with its child node(s) is a valid max heap.
 * * So, the last parent is at `(n / 2) - 1` index, and the last child can be at the [Array.lastIndex].
 * * We compare this and all the other parent-child pairs and form a valid max heap tree from bottom to top.
 * * The bottom-to-top approach ensures that by the time we process any parent node, its child subtrees are `heapified`.
 * * So, it is like heapifying smaller subtrees of the bottom before we heapify larger subtrees.
 * * Also, when we first start building the heap, we have not yet extracted and placed the maximum element.
 * * So, we cannot shrink the end-index boundary yet.
 * * For more information about the concept of "shrinking the end-index boundary," check [extractMax].
 *
 * # Time Complexity
 *
 * * The [siftDown] process takes `O(log n)` time.
 * * And we call [siftDown] function for `(n/2)` times.
 * * So, the worst-case time complexity of this function can be `O(n log n)`.
 * * However, as per the realistic analysis, the time complexity of this function is `O(n)` only.
 * * Because only the root node might travel the full `log n` height.
 * * All the other nodes have less height than `log n`.
 * * In fact, as we move closer to the leaves, the height of the nodes keeps decreasing.
 * * Also, we cut the majority of the nodes from this process that are the last-level leaves.
 * * Hence, the realistic time complexity of this function is `O(n)`.
 *
 * # Space Complexity
 *
 * * We do not use any additional memory that depends on or grows with the input size.
 * * Hence, the space complexity of this function is `O(1)`.
 *
 */
fun buildHeap(array: IntArray) {
    // Repair the binary max heap tree from the given index to the top for each node and every subtree
    for (i in ((array.size / 2) - 1) downTo 0) {
        siftDown(array, i, array.lastIndex)
    }
}

/**
 * # Why do we have this function? What does it do? How does it do? Why does it do it that way? How does it help?
 *
 * * We have this function to ensure and maintain the binary max heap tree properties.
 * * It compares the parent with the max child, which must be within the given [endIndexInclusive].
 * * If the parent has both children within the given [endIndexInclusive], it selects the maximum child.
 * * If the parent is less than the max child, and if the child is within the given [endIndexInclusive], it swaps the positions.
 * * It means that the child becomes the parent, and the parent becomes the child.
 * * We continue this process until we ensure and maintain the binary max heap tree from the [fromIndex] up to the given [endIndexInclusive].
 *
 * # Time-Complexity
 *
 * * The maximum height of a binary max heap tree is `log n`.
 * * Hence, the time-complexity of this function is `O(log n)`.
 *
 * # Space-Complexity
 *
 * * We do not use any memory that depends on and grows with the input size.
 * * So, the space-complexity is `O(1)`.
 *
 * @param fromIndex Start repairing the binary max heap tree from this index up to the top (root) index `0`
 * @param endIndexInclusive Decides when to stop, what to ignore, helpful when we keep reducing the index-end boundary
 */
fun siftDown(array: IntArray, fromIndex: Int, endIndexInclusive: Int) {
    // Base Condition.
    if (fromIndex !in 0..endIndexInclusive) return
    var parentIndex = fromIndex
    while (hasLeftChild(parentIndex, endIndexInclusive)) {
        var maxChildIndex = getLeftChildIndex(parentIndex)
        if (hasRightChild(parentIndex, endIndexInclusive) && array[getRightChildIndex(parentIndex)] > array[maxChildIndex]) {
            maxChildIndex = getRightChildIndex(parentIndex)
        }
        // If the parent is greater than or equal to the max child, the max heap property is satisfied
        if (array[parentIndex] >= array[maxChildIndex]) {
            break
        }
        // If the parent is smaller than the max child, swap
        if (maxChildIndex <= endIndexInclusive && array[parentIndex] < array[maxChildIndex]) {
            swap(array, parentIndex, maxChildIndex)
        }
        parentIndex = maxChildIndex
    }
}

fun getLeftChildIndex(parentIndex: Int) = 2 * parentIndex + 1

fun hasLeftChild(parentIndex: Int, endIndex: Int): Boolean {
    val leftChildIndex = getLeftChildIndex(parentIndex)
    // Ideally, `0` cannot be a `left child`.
    return (leftChildIndex in 0..endIndex)
}

fun getRightChildIndex(parentIndex: Int) = (2 * parentIndex) + 2

fun hasRightChild(parentIndex: Int, endIndex: Int): Boolean {
    val rightChildIndex = getRightChildIndex(parentIndex)
    return (rightChildIndex in 0..endIndex)
}

fun swap(array: IntArray, positionOne: Int, positionTwo: Int) {
    if (positionOne !in 0..<array.size || positionTwo !in 0..<array.size) return
    array[positionOne] = array[positionTwo].also {
        array[positionTwo] = array[positionOne]
    }
}

/**
 * # Why do we have this function? What does it do? How does it do? Why does it do it that way? How does it help?
 *
 * * We use this function to move the root element (with the maximum value) from index `0` to [lastAvailableUnsortedEndIndex].
 * * It swaps the positions of the root element (at index `0`) with the [lastAvailableUnsortedEndIndex].
 * * And then it does two things:
 * * * It marks the [lastAvailableUnsortedEndIndex] as **Tested OK** by limiting the [siftDown] process at `[lastAvailableUnsortedEndIndex] - 1`.
 * * * * This way, it tells the [siftDown] process about the ending boundary.
 * * * * This process is also known as shrinking down the unsorted section of the array from right-to-left.
 * * * * In another way, we can also call it growing the sorted section of the array from right-to-left.
 * * * * Both perspectives are interconnected and happen at the same time.
 * * * And second thing, it calls [siftDown] because swapping might have violated the binary max heap tree properties.
 * * * * In this process, we restore the max heap property for the reduced heap.
 * * * * For the [siftDown] process, we now consider the end-index of the [array] as `[lastAvailableUnsortedEndIndex] - 1`.
 * * * * Because, at the last index, at the [lastAvailableUnsortedEndIndex], we have already placed the element with the maximum value.
 * * * * So now, we need to deal with one less element.
 *
 * # Time-Complexity
 *
 * * Extracting the max element is `O(1)` only.
 * * In that way, the time complexity of this function would be `O(1)`.
 * * But this function also calls [siftDown], whose time complexity is `O(log n)`.
 * * So, we will consider the time complexity of this function as `O(log n)` only.
 *
 * # Space-Complexity
 *
 * * There is no additional memory we use here that depends on or grows with the input size.
 * * Hence, the space complexity is `O(1)`.
 */
fun extractMax(array: IntArray, lastAvailableUnsortedEndIndex: Int): Int {
    if (array.isEmpty()) return -1
    // Take the largest element from the root (index 0)
    val max = array[0]
    // Swap the largest element with the [endIndex]
    swap(array, 0, lastAvailableUnsortedEndIndex)
    // Restore the max heap properties for the reduced (shrunk) unsorted array.
    siftDown(array, 0, lastAvailableUnsortedEndIndex - 1)
    return max
}

fun main() {
    // Empty
    val empty = intArrayOf()
    heapSort(empty)
    println(empty.toList())

    // Single Positive
    val singlePositive = intArrayOf(9)
    heapSort(singlePositive)
    println(singlePositive.toList())

    // Single Negative
    val singleNegative = intArrayOf(-10)
    heapSort(singleNegative)
    println(singleNegative.toList())

    // Typical, normal, mix positive and negative
    val intArray = intArrayOf(-1, 9, -7, 8, -5)
    heapSort(intArray)
    println(intArray.toList())

    // Already sorted ascending and positive
    val sortedAscending = intArrayOf(5, 6, 7, 8, 9)
    heapSort(sortedAscending)
    println(sortedAscending.toList())

    // Already sorted ascending and negative
    val sortedAscendingNegative = intArrayOf(-5, -4, -3, -2, -1, 0)
    heapSort(sortedAscendingNegative)
    println(sortedAscendingNegative.toList())

    // Already sorted descending and positive
    val sortedDescending = intArrayOf(9, 8, 7, 6, 5)
    heapSort(sortedDescending)
    println(sortedDescending.toList())

    // Already sorted descending and negative
    val sortedDescendingNegative = intArrayOf(0, -1, -2, -3, -4, -5)
    heapSort(sortedDescendingNegative)
    println(sortedDescendingNegative.toList())

    // Duplicate and positive
    val duplicatePositive = intArrayOf(9, 9, 5, 5, 7, 7)
    heapSort(duplicatePositive)
    println(duplicatePositive.toList())

    // Duplicate and negative
    val duplicateNegative = intArrayOf(-9, -9, -3, -3, -10, -10)
    heapSort(duplicateNegative)
    println(duplicateNegative.toList())

    // All duplicates and positive
    val allDuplicatePositive = intArrayOf(5, 5, 5, 5, 5)
    heapSort(allDuplicatePositive)
    println(allDuplicatePositive.toList())

    // All duplicates and negative
    val allDuplicateNegative = intArrayOf(-10, -10, -10, -10, -10)
    heapSort(allDuplicateNegative)
    println(allDuplicateNegative.toList())
}