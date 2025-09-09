package courses.ucSanD.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment02

/**
 *
 * # References / Resources / Prerequisites
 *
 * [Local: heapSort.md](docs/dataStructures/courses/ucSanD/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * [GitHub: heapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a244469d45534a60ac027a4925e07ac3f1d256e3/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 *
 * # Problem Introduction
 *
 * In this problem, you will convert an array of integers into a heap. This is the crucial step of the sorting
 * algorithm called HeapSort. It has a guaranteed worst-case running time of `𝑂(𝑛 log 𝑛)` as opposed to QuickSort’s
 * average running time of `𝑂(𝑛 log 𝑛)`. QuickSort is usually used in practice because, typically, it is faster, but
 * HeapSort is used for external sort when you need to sort huge files that don’t fit into the memory of your computer.
 *
 * # Problem Description
 *
 * ## Task
 *
 * The first step of the HeapSort algorithm is to create a heap from the array you want to sort. By the
 * way, did you know that algorithms based on Heaps are widely used for external sort, when you need
 * to sort huge files that don’t fit into the memory of a computer?
 *
 * Your task is to implement this first step and convert a given array of integers into a heap. You will
 * do that by applying a certain number of swaps to the array. The `Swap` is an operation that exchanges
 * elements `𝑎_𝑖` and `𝑎_𝑗` of the array `𝑎` for some `𝑖` and `𝑗`. You will need to convert the array into a heap using
 * only `𝑂(𝑛)` swaps. Note that you will need to use a min-heap instead of a max-heap in this problem.
 *
 * ## Input Format
 *
 * The first line of the input contains a single integer `𝑛`. The next line contains `𝑛` space-separated
 * integers `𝑎_𝑖`.
 *
 * ## Constraints
 *
 * 1 ≤ 𝑛 ≤ 100 000; 0 ≤ 𝑖, 𝑗 ≤ 𝑛 − 1; 0 ≤ 𝑎_0, 𝑎_1, . . . , 𝑎_{𝑛−1} ≤ 10^9. All the `𝑎_𝑖` are distinct.
 *
 * ## Output Format
 *
 * The first line of the output should contain a single integer `𝑚` — the total number of swaps.
 * `𝑚` must satisfy conditions `0 ≤ 𝑚 ≤ 4𝑛`. The next `𝑚` lines should contain the swap operations used
 * to convert the array 𝑎 into a heap. Each swap is described by a pair of integers `𝑖`, `𝑗` — the 0-based
 * indices of the elements to be swapped. After applying all the swaps in the specified order, the array
 * must become a heap, that is, for each `𝑖` where `0 ≤ 𝑖 ≤ 𝑛 − 1`, the following conditions must be true:
 *
 * 1. If `2𝑖 + 1 ≤ 𝑛 − 1`, then `𝑎_𝑖 < 𝑎_{2𝑖+1}`.
 * 2. If `2𝑖 + 2 ≤ 𝑛 − 1`, then `𝑎_𝑖 < 𝑎_{2𝑖+2}`.
 *
 * Note that all the elements of the input array are distinct. Note that any sequence of swaps that has
 * length at most `4𝑛`, and after which your initial array becomes a correct `heap`, will be graded as correct.
 *
 * ## Time Limits.
 *
 * C: 1 sec, C++: 1 sec, Java: 3 sec, Python: 3 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 3 sec, Ruby: 3 sec,
 * Scala: 3 sec.
 *
 * ## Memory Limit. 512MB.
 *
 * ## Sample 1.
 *
 * ### Input:
 * ```
 * 5
 * 5 4 3 2 1
 * ```
 *
 * ### Output:
 * ```
 * 3
 * 1 4
 * 0 1
 * 1 3
 * ```
 *
 * * After swapping elements 4 in position 1 and 1 in position 4, the array becomes 5 1 3 2 4.
 * * After swapping elements 5 in position 0 and 1 in position 1, the array becomes 1 5 3 2 4.
 * * After swapping elements 5 in position 1 and 2 in position 3, the array becomes 1 2 3 5 4, which is
 * already a heap, because `𝑎_0 = 1 < 2 = 𝑎_1, 𝑎_0 = 1 < 3 = 𝑎_2, 𝑎_1 = 2 < 5 = 𝑎_3, 𝑎_1 = 2 < 4 = 𝑎_4.`
 *
 * ## Sample 2.
 *
 * ### Input
 * ```
 * 5
 * 1 2 3 4 5
 * ```
 *
 * ### Output
 * ```
 * 0
 * ```
 *
 * * The input array is already a heap, because it is sorted in increasing order.
 *
 *
 * # Time Complexity
 *
 * * It is `O(n)`.
 * * We might feel that it should be `O(n log n)`, because we perform the [siftDown] operation almost `n` times.
 * * However, the realistic analysis reveals that it is `O(n)` only.
 * * Please check the `heapSort.md` file for more details.
 *
 * * [Local: heapSort.md](docs/dataStructures/courses/ucSanD/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * * [GitHub: heapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a244469d45534a60ac027a4925e07ac3f1d256e3/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * # Space Complexity
 *
 * * This is an in-place algorithm that does not require any auxiliary space.
 * * In that way, it is `O(1)`.
 * * However, if we count the output space, then it is `O(m)`, where `m` is the size of the [swaps].
 *
 *
 * # Coursera's Grader Output
 *
 * Good job! (Max time used: 0.73/2.00, max memory used: 93962240/536870912.)
 */
class MinHeapBuilder(private val input: IntArray) {

    /**
     * This is a global mutable property, but we don't expose it.
     */
    private val swaps = mutableListOf<Pair<Int, Int>>()

    fun build(): List<Pair<Int, Int>> {
        // If the size is less than or equal to 1,
        // we don't need to swap anything.
        // So, the number of swap operations are 0,
        // and the list that contains the positions of each swap operation will also be empty.
        if (input.size <= 1) return swaps
        val lastParent = (input.size / 2) - 1
        for (i in lastParent downTo 0) {
            siftDown(i)
        }
        return swaps
    }

    private fun siftDown(fromIndex: Int) {
        validateIndex(fromIndex)
        var parentIndex = fromIndex
        while (hasLeftChild(parentIndex)) {
            var minChildIndex = getLeftChildIndex(parentIndex)
            if (hasRightChild(parentIndex) && input[getRightChildIndex(parentIndex)] < input[minChildIndex]) {
                minChildIndex = getRightChildIndex(parentIndex)
            }
            if (input[parentIndex] <= input[minChildIndex]) {
                break
            } else {
                swap(parentIndex, minChildIndex)
                parentIndex = minChildIndex
            }
        }
    }

    private fun swap(positionOne: Int, positionTwo: Int) {
        validateIndex(positionOne, positionTwo)
        swaps.add(positionOne to positionTwo)
        input[positionOne] = input[positionTwo].also {
            input[positionTwo] = input[positionOne]
        }
    }

    private fun validateIndex(vararg index: Int): Boolean {
        index.forEach {
            if (it !in 0..input.lastIndex) throw IllegalArgumentException("Index $it is out of bounds for size ${input.size}")
        }
        return true
    }

    private fun getLeftChildIndex(index: Int) = (2 * index) + 1

    private fun getRightChildIndex(index: Int) = (2 * index) + 2

    private fun hasLeftChild(index: Int): Boolean {
        val leftChildIndex = getLeftChildIndex(index)
        return leftChildIndex in 1..input.lastIndex
    }

    private fun hasRightChild(index: Int): Boolean {
        val rightChildIndex = getRightChildIndex(index)
        return rightChildIndex in 1..input.lastIndex
    }
}

fun main() {
    val totalSize = readln()
    val input = readln().split(" ").map { it.toInt() }.toIntArray()
    val swaps = MinHeapBuilder(input).build()
    println(swaps.size)
    swaps.forEach {
        println("${it.first} ${it.second}")
    }
}

