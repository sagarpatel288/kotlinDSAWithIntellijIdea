package coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets

/**
 * # Implement a binary max heap tree data structure
 *
 * ## References / Resources:
 *
 * ### Trees
 *
 * [Local trees.md](../../../../../docs/dataStructures/coursera/ucSanDiego/module01BasicDataStructures/section03trees/trees.md).
 *
 * [GitHub trees.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/19e0b04eac7682842989adac42d1813c543e3be1/docs/dataStructures/coursera/ucSanDiego/module01BasicDataStructures/section03trees/trees.md)
 *
 * ### Priority Queues
 *
 * [Local PriorityQueues.md](../../../../../docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section01priorityQueuesIntroduction/priorityQueues.md)
 *
 * [GitHub priorityQueues.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/ab5477df1cd69c051ac84650605d3077dea796ab/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section01priorityQueuesIntroduction/priorityQueues.md)
 *
 * ### Binary Heap Trees
 *
 * [Local binaryHeapTrees.md](../../../../../docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
 *
 * [GitHub binaryHeapTrees.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/407184585eb6b5b03b0ea9d1066cfc0f8249204d/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic02BinaryHeapTrees/binaryHeapTrees.md)
 *
 * ### Complete Binary Trees
 *
 * [Local completeBinaryTrees.md](../../../../../docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
 *
 * [GitHub completeBinaryTrees.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/04e362b8cb0101459304ce5ba6ad17fe20edcc52/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic03CompleteBinaryTrees/completeBinaryTrees.md)
 *
 * ## Thought Process
 *
 * ### What is a binary max heap tree?
 *
 * * It is a complete binary tree where all the levels are filled completely except perhaps the last level.
 * * The last level must be filled from left to right.
 * * And every parent node `n` is greater than or equal to the child nodes.
 * * Hence, the maximum value is always available at the root node.
 *
 * ### What condition should be fulfilled by the data structure of a complete binary tree?
 *
 * * Either the data structure must implement the comparable interface, or we must provide an external comparator.
 * * It means that our class might look:
 *
 * ```
 * class BinaryMaxHeap<T: Comparable<T>>() {
 *
 * }
 * ```
 *
 * ### In which data structure can we represent a complete binary tree?
 *
 * * Array.
 * * It means that we will be using an `ArrayList` (or precisely, a `mutableList`) as an underlying data structure.
 * * So, we can have something like:
 *
 * ```
 * val heap = mutableListOf<T>()
 * ```
 *
 * ### How do we get random access in `O(1)`? What are the formulas?
 *
 * * Considering 1-based index:
 * * Parent of index `i` is `⌊i/2⌋`
 * * Left child of index `i` is `2 * i`
 * * Right child of inex `i` is `(2 * i) + 1`
 *
 * #### But arrays usually follow the 0-based index, right? How do we manage it?
 *
 * * For 0-based index, the formulas are:
 * * Parent of index `i` is `⌊(i - 1)/2⌋` // 1 index behind than the 1-based index.
 * * Left child of index `i` is `(2 * i) + 1`. // 1 index ahead of the 1-based index.
 * * Right child of index `i` is `(2 * i) + 2`. // 1 index ahead of the 1-based index.
 *
 * * It means that we might have the following helper functions:
 *
 * ```
 * fun getParentIndexOf(index: Int): Int {
 *     // Only if `index > 0` because `0` is the root node. It cannot have a parent.
 *     return if (index > 0) (index - 1)/2 else -1
 * }
 *
 * fun getLeftChildIndexOf(index: Int): Int {
 *     // Check for a positive, valid index
 *     return if (index >= 0) (2 * index) + 1 else -1
 * }
 *
 * fun getRightChildIndexOf(index: Int): Int {
 *     // Check for a positive, valid index
 *     return if (index >= 0) (2 * index) + 2 else -1
 * }
 *
 * fun hasParentIndexOf(index: Int) = getParentIndexOf(index) >= 0
 *
 * // If the resultant index goes beyond the `heap size`, it clearly means that the result (left child) does not exist.
 * // And `heap.size - 1` because `size` starts with counting `1`, whereas `index` starts with `0`.
 * // For example, if the `heap size` is 2, the last node, the child index of the root index `0` is `1`,
 * // which is `size - 1`.
 * fun hasLeftChildIndexOf(index: Int) = getLeftChildIndexOf(index) <= heap.size - 1
 *
 * // Same as above. `size - 1` is a standard way of finding/getting the last element in an array.
 * // If the resultant index goes beyond `size - 1`, it means that the element does not exist in the array.
 * fun hasRightChildIndexOf(index: Int) = getRightChildIndexOf(index) <= heap.size - 1
 * ```
 *
 * ### What are the common operations of a binary max heap tree?
 *
 * * max (or getMax), insert (or add), swiftUp, swapPositions, remove a node, extractMax, swiftDown, changePriority,
 * etc.
 *
 * ### How do we maintain (keep, sustain, preserve) the complete binary tree for all the operations?
 *
 * #### Insert
 * * When we insert a new element, we need to ensure that we insert it as the immediate next neighbour of the current
 * last node. This way, the new element will be left-aligned, and we follow the left-to-right order.
 * * Then, we can follow the `SiftUp` procedure.
 *
 * #### How do we do that?
 *
 * * It is natural for an `ArrayList` (or to be more specific, a `mutableList`) to `insert` a new element at the end.
 * * So, adding an element at the end of a `mutableList` is:
 *
 * ```
 * fun insert(newElement: Int) {
 *     heap.add(newElement)
 *     siftUp(heap.lastIndex) //or siftUp(heap.size - 1) Both indicates the last index.
 * }
 * ```
 *
 * ##### And what about the `siftUp` or the `heapifyUp` process? How do we do that?
 *
 * * In the `SiftUp` process, we compare (that's why the `data type` must be `comparable`) the newly inserted element
 * with the parent element.
 * * We get the parent index, and use it to get the element at that index.
 * * Once we get the parent value from the parent index, we compare it with the newly inserted element value.
 * * If the newly inserted element is greater than the parent, we swap the positions (indices).
 * * Clearly, it requires a helper method to swap the positions, like [swap].
 * * Now, we need to repeat this process as long as the `parent index` is greater than `0`.
 * * To repeat the process, we use a `while` loop.
 * * So, the method looks like:
 *
 * ```
 * private fun siftUp(childIndex: Int) {
 *     var childIndex_ = childIndex
 *     while (hasParentIndexOf(childIndex_) && heap[childIndex_] > heap[getParentIndexOf(childIndex_))]) {
 *         val parentIndex = getParentIndexOf(childIndex_)
 *         swap(childIndex_, parentIndex)
 *         childIndex_ = parentIndex
 *     }
 * }
 * ```
 *
 * #### extractMax
 * * We replace the root node with the last node.
 * * And then, we follow the `SiftDown` procedure.
 * * So, it looks like:
 *
 * ```
 * fun extractMax(): T? {
 *     if (heap.isNotEmpty()) {
 *         // Take the root element
 *         val max = heap[0]
 *         // Set the last element at the root
 *         heap[0] = heap.last
 *         // Remove the last element
 *         heap.removeAt(heap.lastIndex) //or heap.remove(heap.last)
 *         // Maintain the max heap properties
 *         siftDown(0)
 *         // Return the extracted max value
 *         return max
 *     } else {
 *         return null // We can have another function that would throw an exception in this case.
 *     }
 * }
 * ```
 *
 * #### siftDown or heapifyDown
 *
 * * Here, we compare the element with the child node.
 * * If the element is smaller than the child node, we swap the elements/positions.
 * * If both the children are greater than the element, we select the highest child.
 * * We continue this process until we reach the last index of the underlying `mutableList`.
 * * So, `heapifyDown` looks like: [siftDown]
 *
 * #### changePriority
 *
 * * If the priority has been increased, we follow the `siftUp` process.
 * * If the priority has been decreased, we follow the `siftDown` process.
 * * It looks like:
 *
 * ```
 * fun changePriority(atIndex: Int, newValue: T) {
 *     if (atIndex < 0) return
 *     val oldValue = heap[atIndex]
 *     heap[atIndex] = newValue
 *     if (oldValue < newValue) {
 *         siftUp(atIndex)
 *     } else if (oldValue > newValue) {
 *         siftDown(atIndex)
 *     }
 * }
 * ```
 *
 * ## Critical Points
 *
 * **Correct `index` usages in**
 * * [hasParent], [getParentIndexOf], [hasLeftChild], [getLeftChildIndexOf], [hasRightChild], [getRightChildIndexOf],
 * [siftUp], [siftDown], etc.
 *
 */
class BinaryMaxHeap<T: Comparable<T>>() {

    private val heap = mutableListOf<T>()

    private fun getParentIndexOf(index: Int) = if (index > 0) (index - 1) / 2 else -1

    private fun getLeftChildIndexOf(index: Int) = if (index >= 0) (2 * index) + 1 else -1

    private fun getRightChildIndexOf(index: Int) = if (index >= 0) (2 * index) + 2 else -1

    private fun hasParent(index: Int): Boolean {
        val parentIndex = getParentIndexOf(index)
        // A parent index can be the `0th` index
        return parentIndex >= 0 && parentIndex < heap.size
    }

    private fun hasLeftChild(index: Int): Boolean {
        val leftChildIndex = getLeftChildIndexOf(index)
        // A left child index can never be the `0th` index
        // Because the `0th` index represents the root element
        return leftChildIndex > 0 && leftChildIndex < heap.size
    }

    private fun hasRightChild(index: Int): Boolean {
        val rightChildIndex = getRightChildIndexOf(index)
        // A right child index can never be the `0th` index
        // Because the `0th` index represents the root element
        return rightChildIndex > 0 && rightChildIndex < heap.size
    }

    private fun swap(positionOne: Int, positionTwo: Int) {
        if (positionOne >= 0 && positionOne < heap.size && positionTwo >= 0 && positionTwo < heap.size) {
            val temp = heap[positionOne]
            heap[positionOne] = heap[positionTwo]
            heap[positionTwo] = temp
        }
    }

    /**
     * Ensure to add the new element [value] as the immediate next neighbour of the current last element.
     * And then call the [siftUp] function to maintain and keep the tree as a binary max heap tree
     *
     * # Time Complexity
     *
     * * Adding an element into a mutable list is `O(1)`.
     * * But it may violate the binary max heap tree.
     * * So, the worst-case time complexity is `O(log n)`.
     *
     * # Space Complexity
     *
     * * Other than [heap], we are not using anything else.
     * * All the small variables we might take are constant.
     * * So, the overall space complexity of this [insert] function (excluding the [heap]) is `O(1)`.
     */
    fun insert(value: T) {
        heap.add(value)
        siftUp(heap.lastIndex)
    }

    /**
     * # What do we do in the [siftUp] process? Why do we have this function?
     *
     * * In a binary max heap tree, the parent is always greater than or equal to the children.
     * * This function checks if the child is greater than the parent.
     * * We compare the element with the parent element.
     * * As long as the child element is greater than the parent element, we keep swapping the element positions.
     * * So, the child becomes the parent. (Promotion)
     * * We do that until we reach the root that does not have any parent.
     * * It means that we need to check if the element has a parent or not before comparison.
     */
    private fun siftUp(fromIndex: Int) {
        if (heap.isEmpty() || fromIndex <= 0 || fromIndex > heap.lastIndex) return
        var childIndex = fromIndex
        // As long as the child is greater than the parent, we keep swapping the positions.
        while (hasParent(childIndex) && heap[childIndex] > heap[getParentIndexOf(childIndex)]) {
            val parentIndex = getParentIndexOf(childIndex)
            swap(childIndex, parentIndex)
            // Now, the child becomes the parent.
            childIndex = parentIndex
        }
    }

    /**
     * Gives the max element from the root (0th index).
     * Swaps the positions of the max element with the last element.
     * Removes the last element (originally, the root, the max value).
     * Calls [siftDown] to maintain the binary max heap tree properties.
     *
     * # Time Complexity
     *
     * * Accessing the max element is always `O(1)` due to the random access property via a mutable list.
     * * However, before we remove the max element, we replace it with the last element.
     * * Which might violate the binary max heap tree, in most cases (unless all the nodes are of the same value!).
     * * To maintain the binary max heap tree, we perform [siftDown].
     * * Hence, the time complexity of this function is `O(log n)`.
     *
     * # Space Complexity
     *
     * * If we exclude [heap], then all the small variables we take are constant.
     * * Hence, the space complexity of this function is `O(1)`.
     */
    fun extractMax(): T? {
        if (heap.isEmpty()) return null
        // Extract the root element
        val max = heap[0]
        // Swap the root element with the last leaf
        swap(0, heap.lastIndex)
        // Remove the last element (originally, it was the root earlier)
        heap.removeAt(heap.lastIndex)
        // Maintain the tree as a binary max heap tree
        siftDown(0)
        return max
    }

    /**
     * In a binary max heap tree, the parent must be greater than or equal to the children.
     * This function checks if the parent is smaller than the child.
     * If the parent is smaller than the child, it swaps the positions.
     * So, the parent becomes the child. (Demotion)
     * It keeps doing that as long as the parent is smaller than the child.
     * This is how it maintains the binary max heap tree.
     */
    private fun siftDown(fromIndex: Int) {
        if (heap.isEmpty() || fromIndex < 0 || fromIndex > heap.lastIndex) return
        var parentIndex = fromIndex
        // We want to compare the parent with the children.
        // It means that the very first condition is: Does the parent have any children?
        // A binary max heap tree follows left-to-right children.
        // It means that if the parent does not have a left child, the parent cannot have a right child!
        // Only if the parent has a left child, there is a possibility of a second child, the right child.
        while (hasLeftChild(parentIndex)) {
            // Until and unless we figure out the existence of the right child,
            // the index of the left child is the maximum child index.
            var maxChildIndex = getLeftChildIndexOf(parentIndex)
            // If the parent has a right child also, it can change the value of the maximum child index.
            // If the right child has a higher value than the left child, we update the maximum child index.
            if (hasRightChild(parentIndex) && heap[getRightChildIndexOf(parentIndex)] > heap[maxChildIndex]) {
                maxChildIndex = getRightChildIndexOf(parentIndex)
            }
            // If parent is greater than the max child, we are good. We can break (leave, end) the loop.
            if (heap[parentIndex] >= heap[maxChildIndex]) {
                break
            } else {
                // If the child has a higher value than the parent, we swap their positions
                // The parent becomes the child.
                swap(parentIndex, maxChildIndex)
                parentIndex = maxChildIndex
            }
        }
    }

    /**
     * # Why do we have this function?
     * * To change the priority (value) of a particular element.
     *
     * # Time Complexity
     *
     * * We might increase or decrease the existing value.
     * * However, it might violate the binary max heap tree.
     * * If we increase the priority, we call [siftUp] to maintain the binary max heap tree.
     * * If we decrease the priority, we call [siftDown] to maintain the binary max heap tree.
     * * So, the time complexity of this function is `O(log n)`.
     *
     * # Space Complexity
     *
     * * If we exclude [heap], we might take a few variables only.
     * * So, the space complexity of this function is `O(1)` only.
     */
    fun changePriorityOf(index: Int, newValue: T) {
        if (heap.isEmpty() || index < 0 || index > heap.lastIndex) return
        val oldValue = heap[index]
        heap[index] = newValue
        if (oldValue < newValue) {
            // We have increased the value. So, calling `siftUp` to maintain the binary max heap tree.
            siftUp(index)
        } else if (oldValue > newValue) {
            // We have decreased the value. So, calling `siftDown` to maintain the binary max heap tree.
            siftDown(index)
        }
    }

    /**
     * # Why do we have this function? What does it do?
     * * It gives the maximum element.
     * * As we maintain the binary max heap tree while we [insert], [extractMax], [changePriorityOf], etc.,
     * It is guaranteed that the element with the maximum value is at the root.
     *
     * # Time Complexity
     *
     * * `O(1)` due to the random access property of the mutable list, [heap].
     *
     * # Space Complexity
     *
     * `O(1)`
     */
    fun peekMax() = heap.firstOrNull()

    /**
     * # Time Complexity
     *
     * * `O(1)`
     *
     * # Space Complexity
     *
     * * `O(1)`
     */
    fun isEmpty() = heap.isEmpty()


    /**
     * # Time Complexity
     *
     * * `O(1)`
     *
     * # Space Complexity
     *
     * * `O(1)`
     */
    fun size() = heap.size
}

/**
 * # Why do we have this function? What does it do? How?
 *
 * * It removes an element from the given [index].
 * * To remove the element, it makes the priority of the element infinite or the maximum.
 * * To do so, it uses the [coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.BinaryMaxHeap.changePriorityOf]
 * * It guarantees that the element we want to remove is now at the root.
 * * So, now it calls the [coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.BinaryMaxHeap.extractMax]
 *
 * # ToDo: Concern
 *
 * * This extension function works only for [Int]!
 * * To make it work for all the classes that implement [Comparable], we need a different logic.
 * * In that case, we can use the `swap` logic with the last element.
 * * And if we can't compare, we might have to perform both `siftUp` and `siftDown`.
 *
 * # Time Complexity
 *
 * * Both the internal `changePriority` and `extractMax` methods have `O(log n)` time complexity.
 * * Both methods are in a sequential call, one after another. It is not a nested iterative call.
 * * So, the time complexity of this function is `O(log n)`.
 *
 * # Space Complexity
 *
 * * We don't use any variable or extra memory that depends on the input.
 * * So, the space complexity of this function is `O(1)`.
 */
fun BinaryMaxHeap<Int>.remove(index: Int): Boolean {
    if (index !in 0..<size()) return false
    // Make it infinite
    changePriorityOf(index, Int.MAX_VALUE)
    extractMax()
    return true
}