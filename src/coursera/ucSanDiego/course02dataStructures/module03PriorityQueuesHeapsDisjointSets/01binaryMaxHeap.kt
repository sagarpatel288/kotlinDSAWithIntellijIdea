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
 * ##### And what about the `SiftUp` process? How do we do that?
 *
 * * In the `SiftUp` process, we compare (that's why the `data type` must be `comparable`) the newly inserted element
 * with the parent element.
 * * We get the parent index, and use it to get the element at that index.
 * * Once we get the parent value from the parent index, we compare it with the newly inserted element value.
 * * If the newly inserted element is greater than the parent, we swap the positions (indices).
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
 * #### siftDown
 *
 * * Here, we compare the element with the child node.
 * * If the element is smaller than the child node, we swap the elements/positions.
 * * If both the children are greater than the element, we select the highest child.
 * * We continue this process until we reach the last index of the underlying `mutableList`.
 * * So, it looks like:
 *
 * ```
 * ```
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
 */
class BinaryMaxHeap<T: Comparable<T>>() {

    private val heap = mutableListOf<T?>()

    /**
     * Ensure to add the new element [value] as the immediate next neighbour of the current last element.
     */
    fun insert(value: T) {
        heap.add(value)
        siftUp(heap.lastIndex)
    }

    private fun siftUp(fromIndex: Int) {

    }
}