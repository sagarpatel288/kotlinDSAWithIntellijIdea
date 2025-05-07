package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquer

/**
 * Problem Statement Example --------------------------------
 *
 * Explain and demonstrate (illustrate) selection sort:
 * OR
 * Explain and demonstrate a sorting algorithm that is an in-place algorithm and has minimum space-complexity.
 * OR
 * Explain and demonstrate a sorting algorithm that is memory efficient (but with poor time-complexity).
 *
 * -------------------------------- Explanation
 *
 * Selection Sort is a simple sorting algorithm that sorts an array by repeatedly finding the minimum element
 * (considering ascending order) from the unsorted portion of the array and moving it to the beginning.
 * The algorithm maintains two sub-arrays:
 *
 * The sub-array that is already sorted.
 *
 * The remaining sub-array that is unsorted.
 *
 * What do we do exactly?
 *
 * We compare each index with all the remaining indices.
 * While comparing, if we find an index that has a lower value element,
 * then we swap the positions of the elements so that the lower value element gets the lower index value
 * to get the non-decreasing ascending order.
 *
 * For example, we start an iteration from 0 and go up to the last index of the input.
 *
 * We assume that the first index is the minimum index and holds the smallest value,
 * which we compare with the rest of the indices.
 *
 * The comparison of i with the rest of the indices introduces a nested for loop from i + 1 up to the last index.
 *
 * If we find a smaller value than our minimum index, we take the minimum index label from the first index
 * and assign it to this smaller index.
 *
 * We continue the iteration, performing the same action whenever we
 * find an index that holds a value smaller than the minimum index.
 *
 * After the first iteration, if the minimum index is not equal to the current index of the iteration,
 * we swap the values.
 *
 * We do this until we reach the last index.
 *
 * So, it's like we select the current index, assign it as the minimum index, compare it with the other elements,
 * swap if necessary, and then select the next index. We do this up to the last index of the input.
 *
 * Time-Complexity:
 *
 * The time-complexity of the selection sort is O(n squared) for all 3 cases (best, average, and worst).
 *
 * Space-Complexity:
 *
 * It is an in-place algorithm. It means, it does not require additional space for another array,
 * except a constant amount of extra space. Hence, the space-complexity of the selection sort is O(1).
 *
 * How does the space complexity of the selection sort is O(1)?
 * Why does the space complexity of the selection sort is O(1)?
 *
 * The selection sort uses a temporary variable to store `minIndex` and a `temp` variable during the swapping process.
 * Both these temporary variables do not depend on the original input size.
 *
 * Apart from these two temporary variables, the selection sort does not create any additional and critical memory
 * that depends on the size of the original input.
 *
 * Hence, we can say that, the space complexity of the selection sort is O(1).
 *
 *
 */
fun main() {

    fun selectionSort(input: MutableList<Int>) {
        if (input.size <= 1) {
            println(": :selectionSort: sorted: ${input.toList()}")
            return
        }
        // Going through each index of the incoming list
        for (i in input.indices) {
            // Taking the current index and will compare it with the rest of the elements and indices.
            // `minIndex` means, an index that has the lower value element than it's successive indices.
            var minIndex = i

            // Comparing the element at the current index with the element of each index
            for (j in i + 1..< input.size) {
                // If we find that the element at the current index (i) is greater than the element at a particular index (j),
                // then we conclude that the index j has a smaller value element than the element at index i.
                // The key point here is, we compare input[j] with input[minIndex] instead of input[j] with input[i].
                if (input[j] < input[minIndex]) {
                    // We find that j is the `minIndex`, the index which has a lower value element than the index i.
                    minIndex = j
                }
            }

            // Avoid unnecessary swapping
            if (i != minIndex) {
                // Get the element from the current index
                val oldValue = input[i]
                // Set the element at the current index from the element at the minIndex
                input[i] = input[minIndex]
                // Set the element of the current index at the index j.
                input[minIndex] = oldValue
            }
        }
        println(input)
    }

    /*val inputArray = readln().split(" ").map {
        it.toInt()
    }.toMutableList()*/

    fun getInput(): IntArray {
        return intArrayOf(-5, -3, -4, -5, 1, 0, 1, 0, 2, 1)
    }

    val input = getInput()

    selectionSort(input.toMutableList())
}