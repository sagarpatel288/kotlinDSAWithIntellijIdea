package coursera.ucSanDiego.module04DivideAndConquerAssignment

/**
 * Problem Statement:
 *
 * [Reference](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d2cdd413f20830cd8d27bd0b27aadc1a6f8598f0/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/01binarySearch.png)
 *
 * Search multiple keys in a sorted sequence of keys.
 *
 * Input:
 *
 * A sorted array K of distinct integers and an array Q= [q0,...,qm−1] of integers.
 *
 * Output:
 *
 * For each qi, check whether it occurs in K.
 *
 * Input format.
 *
 * The first two lines of the input contain an integer n and
 * a sequence k0 <k1 <...<kn−1 of n distinct positive integers in increasing order.
 *
 * The next two lines contain an integer m and m positive integers q0,q1,...,qm−1.
 *
 * Output format.
 *
 * For all i from 0 to m−1, output an index 0 ≤ j ≤ n−1 such
 * that kj= qi, or −1, if there is no such index.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 3·10^4;
 * 1 ≤ m ≤ 10^5;
 * 1 ≤ ki ≤ 10^9 for all 0 ≤ i < n;
 * 1 ≤ qj ≤ 10^9 for all 0 ≤ j < m.
 *
 * Sample:
 *
 * Input:
 *
 * 5
 *
 * 1 5 8 12 13
 *
 * 5
 *
 * 8 1 23 1 11
 *
 * Output:
 *
 * 2 0 -1 0 -1
 *
 * Queries 8, 1, and 1 occur at positions 2, 0, and 0, respectively, while
 * queries 23 and 11 do not occur in the sequence of keys.
 *
 *
 */
fun main() {

    /**
     * [input] is already sorted.
     * The time complexity of this solution is O(m log n) because we do binary search (log n) for m queries.
     * The space complexity of the solution is O(m) to store the result, which is equivalent to the size of the query.
     */
    fun searchIndicesOfElements(input: List<Int>, queries: List<Int>): IntArray {
        val resultArray = IntArray(queries.size)
        for ((index, element) in queries.withIndex()) {
            val resultIndex = input.binarySearch(element)
            resultArray[index] = if (resultIndex < 0) -1 else resultIndex
        }
        return resultArray
    }

    // Remember this: How we take input.
    val totalElements = readln().toInt()
    // Remember this: How we convert the input into a list of desired type.
    val input = readln().split(" ").map {
        it.toInt()
    }
    val totalQueries = readln().toInt()
    val queries = readln().split(" ").map {
        it.toInt()
    }

    val result = searchIndicesOfElements(input, queries)

    // Remember this: How we print the result as per request.
    println(result.joinToString(" "))
}