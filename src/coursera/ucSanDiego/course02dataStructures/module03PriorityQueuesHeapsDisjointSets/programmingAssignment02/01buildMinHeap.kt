package coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment02

/**
 *
 * # ----------------------- Time Complexity -----------------------
 *
 * * It is `O(n)`.
 * * We might feel that it should be `O(n log n)`, because we perform the [siftDown] operation almost `n` times.
 * * However, the realistic analysis reveals that it is `O(n)` only.
 * * Please check the `heapSort.md` file for more details.
 *
 * * [Local: heapSort.md](docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * * [GitHub: heapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a244469d45534a60ac027a4925e07ac3f1d256e3/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)
 *
 * # ----------------------- Space Complexity -----------------------
 *
 * * This is an in-place algorithm that does not require any auxiliary space.
 * * In that way, it is `O(1)`.
 * * However, if we count the output space, then it is `O(m)`, where `m` is the size of the [swaps].
 *
 *
 * # ----------------------- Coursera's Grader Output -----------------------
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

