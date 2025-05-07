package coursera.ucSanDiego.algorithmicToolbox.module04DivideAndConquerAssignment

/**
 * Problem Statement:
 *
 * Reference:
 * res/coursera/ucSanDiego/module04DivideAndConquerAssignment/02binarySearchFirstOccurrence.png
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/83228aa738b9eaba070488ff2839b17f4c85a082/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/02binarySearchFirstOccurrence.png)
 *
 * Binary Search with Duplicates Problem:
 *
 * Find the index of the first occurrence of a key in a sorted array.
 *
 * Input:
 *
 * A sorted array of integers (possibly with duplicates) and an integer q.
 *
 * Output:
 *
 * Index of the first occurrence of q in the array or “−1” if q does not appear in the array.
 *
 * Input format.
 *
 * The first two lines of the input contain an integer n and a sequence k0 ≤ k1 ≤···≤ kn−1 of n positive integers
 * in non-decreasing order.
 * The next two lines contain an integer m and m positive integers q0, q1,..., qm−1.
 *
 * Output format.
 *
 * For all i from 0 to m−1, output the index 0 ≤ j ≤ n−1 of the first occurrence of qi (i.e., kj= qi) or −1,
 * if there is no such index.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 3·10^4;
 * 1 ≤ m ≤ 10^5;
 * 1 ≤ ki ≤ 10^9 for all 0 ≤ i < n;
 * 1 ≤ qj ≤ 10^9 for all 0 ≤ j <m.
 *
 * Sample:
 *
 * Input:
 *
 * 7
 *
 * 2 4 4 4 7 7 9
 *
 * 4
 *
 * 9 4 5 2
 *
 * Output:
 *
 * 6 1 -1 0
 *
 * Time complexity:
 *
 * We divide the problem into halves. Hence, it is O(log n) for each query.
 * So, for a total of m queries, it is O(m log n).
 *
 * Space complexity:
 *
 * We create an array to store the resultant indices which has the same size of the given query list.
 * Hence, it is O(m) where m is the size of the query list.
 *
 * Coursera's Grader output:
 * (Max time used: 0.64/2.00, max memory used: 77828096/536870912.)
 *
 */
fun main() {

    fun binarySearchFirstOccurrenceIndex(input: List<Int>, query: Int): Int {
        var start = 0
        var end = input.lastIndex
        var resultIndex = -1
        while (start <= end) {
            val mid = start + (end - start) / 2
            when {
                input[mid] == query -> {
                    resultIndex = mid
                    end = mid - 1
                }
                input[mid] > query -> {
                    end = mid - 1
                }
                input[mid] < query -> {
                    start = mid + 1
                }
            }
        }
        return resultIndex
    }

    fun binarySearchResultIndices(input: List<Int>, queryList: List<Int>) {
        val resultIndices = IntArray(queryList.size)
        for ((index, element) in queryList.withIndex()) {
            val resultIndex = binarySearchFirstOccurrenceIndex(input, element)
            resultIndices[index] = if (resultIndex < 0) -1 else resultIndex
        }
        println(resultIndices.joinToString(" "))
    }

    val totalInputElements = readln().toInt()
    val sortedInput = readln().split(" ").map {
        it.toInt()
    }
    val totalQueries = readln().toInt()
    val queryList = readln().split(" ").map {
        it.toInt()
    }
    binarySearchResultIndices(sortedInput, queryList)
}