package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

/**
 * Problem Statement:
 *
 * Number of Inversions Problem:
 *
 * Compute the number of inversions in a sequence of integers.
 *
 * Input: A sequence of n integers a1,...,an.
 * Output: The number of inversions in the sequence,
 * i.e., the number of indices i < j such that ai > aj.
 *
 * The number of inversions in a sequence measures how close the sequence is to being sorted.
 * For example, a sequence sorted in the non-descending order contains no inversions,
 * while a sequence sorted in the descending order contains n(n−1)/2 inversions (every two elements form an inversion).
 *
 * For example: [2, 2, 3, 5] is a non-descending arrangement and hence, it does not contain any inversions.
 * However, [5, 3, 2, 1] is a sorted sequence in the descending order. It contains the following inversions:
 * (5, 3), (5, 2), (5, 1)
 * (3, 2), (3, 1)
 * (2, 1).
 *
 * Input format:
 *
 * The first line contains an integer n, the next one contains a sequence of integers a1,...,an.
 *
 * Output format:
 *
 * The number of inversions in the sequence.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 30000, 1 ≤ ai ≤ 10^9 for all 1 ≤ i ≤ n.
 *
 * Sample:
 *
 * Input:
 *
 * 5
 *
 * 2 3 9 2 9
 *
 * Output:
 *
 * 2
 *
 * The two inversions here are (2, 4) (a2 = 3 > 2 = a4) and (3, 4) (a3 = 9 > 2 = a4).
 *
 * ----------------------- Explanation -----------------------
 *
 * What is an inversion?
 *
 * An inversion means, the index i is less than the index j (i < j), but the value of a[i] > a[j].
 * For example, [5, 3, 2, 1].
 *
 * 1. Here, index i = 0 has the value a[i] = 5 which is greater than the index j = 1 => a[j] => a[1] => 3.
 * 2. Similarly, index i = 0 has the value a[i] = 5 which is greater than the index j = 2 => a[j] => a[2] => 2.
 * 3. The index i = 0 has the value a[i] = 5 which is greater than the index j = 3 => a[j] => a[3] => 1.
 *
 * 4. The index i = 1 has the value a[i] = 3 which is greater than the index j = 2 => a[j] => a[2] => 2.
 * 5. The index i = 1 has the value a[i] = 3 which is greater than the index j = 3 => a[j] => a[3] => 1.
 *
 * 6. The index i = 2 has the value a[i] = 2 which is greater than the index j = 3 => a[j] => a[3] => 1.
 *
 * So, we have got a total of 6 inversions.
 *
 * TL;DR:
 *
 * When an element at left side is greater than the element at right side, it is an inversion.
 *
 * ----------------------- Thought Process -----------------------
 *
 * 1. We compare a left element and the right element.
 * 2. The most efficient comparison-based algorithm is the merge-sort algorithm,
 * where we compare the left-side element with the right-side element and insert the small element using 3 pointers:
 * The left pointer, right pointer, and the sorted pointer.
 * 3. During the merge-sort algorithm, we get an opportunity to compare and check if the left-side element is greater
 * than the right-side element.
 * 4. This is the time, this is the opportunity we can take a count variable and increase it whenever we find that the
 * left-side element is greater than the right-side element.
 * 5. However, the key-point, the key-lemma here is, if a left-side element of a sorted array is greater than the
 * right-side element of a sorted-array, then all the other remaining elements of the left-side are also greater than
 * the right-side element.
 * 6. For example, on the left side, we have: [6, 9, 11, 11, 15] and on the right side, we have: [8, 17].
 * Now, if we find that 9 > 8, it means, all the other elements of the left side will also be greater
 * than the right side element 8. Because, both the parts are sorted.
 * Hence, on the left side, next element will be equal to or greater than the previous element, 9.
 * So, if one element of the left side, element 9, is greater than the right side element 8,
 * then, all the other (next, upcoming) elements of the left side will also be greater than 8.
 * For example, 9 is greater than 8. It means, all the other (next, upcoming, remaining) elements of the left-side,
 * 11, 11, and 15 are also greater than the right-side element 8.
 * So, we count these inversions easily.
 * Hence, we add the number of remaining elements on the left-side to the inversion count in such cases.
 *
 * ----------------------- Time Complexity -----------------------
 *
 * We use the merge-sort algorithm. It divides the original problem into two parts. So, we get log n levels to get
 * each problem of size 1. At each level, we travel through all the elements, n available in each part.
 * Hence, the time complexity is O(n log n).
 *
 * ----------------------- Space Complexity -----------------------
 *
 * We use a temp array to insert the elements. The maximum size of this array is equal to the input array.
 * Hence, the space complexity is O(n).
 *
 * ----------------------- Coursera's Grader Output -----------------------
 *
 * (Max time used: 0.37/2.00, max memory used: 62820352/536870912.)
 *
 * ----------------------- TL;DR -----------------------
 *
 * 1. Use merge-sort where we compare left-side and right-side elements, which is the fastest and stable
 * comparison-based algorithm.
 * 2. If one element of the left-side is greater than an element of the right-side,
 * then all the remaining elements of the left-side must be greater than the right-side element.
 * 3. Hence, we add the number of remaining elements on the left-side to the inversion count in such cases.
 *
 */
fun main() {

    fun mergeSortAndCount(input: MutableList<Int>, startIndex: Int, midIndex: Int, endIndex: Int): Int {
        var leftPointer = startIndex
        var rightPointer = midIndex + 1
        var sortedPointer = 0
        val tempSorted = IntArray(endIndex - startIndex + 1)
        var inversionCount = 0
        while (leftPointer <= midIndex && rightPointer <= endIndex) {
            if (input[leftPointer] <= input[rightPointer]) {
                tempSorted[sortedPointer++] = input[leftPointer++]
            } else {
                tempSorted[sortedPointer++] = input[rightPointer++]
                // This is the key-point.
                // In a sorted array, if an element from the left side is greater than the element of the right side,
                // all the other elements of the left side will also be greater than the element of the right side.
                // For example, we know that we have two sorted parts.
                // Let us assume that these parts are:
                // On the left side, we have: [6, 9, 11, 11, 15] and on the right side, we have: [8, 17].
                // Now, if we find that 9 > 8, it means, all the other elements of the left side will also be greater
                // than the right side element 8. Because, both the parts are sorted.
                // Hence, on the left side, next element will be equal to or greater than the previous element, 9.
                // So, if one element of the left side, element 9, is greater than the right side element 8,
                // then, all the other (next, upcoming) elements of the left side will also be greater than 8.
                // For example, 9 is greater than 8. It means, all the other (next, upcoming, remaining)
                // elements of the left-side, 11, 11, and 15 are also greater than the right-side element 8.
                // So, we count these inversions easily.
                // Hence, we add the number of remaining elements on the left-side to the inversion count in such cases.
                inversionCount += midIndex - leftPointer + 1
            }
        }
        while (leftPointer <= midIndex) {
            tempSorted[sortedPointer++] = input[leftPointer++]
        }
        while (rightPointer <= endIndex) {
            tempSorted[sortedPointer++] = input[rightPointer++]
        }
        for (i in tempSorted.indices) {
            input[startIndex + i] = tempSorted[i]
        }
        return inversionCount
    }
    
    fun divideMergeAndCount(input: MutableList<Int>, startIndex: Int, endIndex: Int): Int {
        var inversionCount = 0
        if (startIndex < endIndex) {
            val mid = startIndex + (endIndex - startIndex) / 2
            inversionCount += divideMergeAndCount(input, startIndex, mid)
            inversionCount += divideMergeAndCount(input, mid + 1, endIndex)
            inversionCount += mergeSortAndCount(input, startIndex, mid, endIndex)
        }
        return inversionCount
    }

    fun countInversions(input: MutableList<Int>) {
        val inversionCount = divideMergeAndCount(input, 0, input.lastIndex)
        println(inversionCount)
    }

    val totalElements = readln().toInt()

    val input = readln().split(" ").map {
        it.toInt()
    }.toMutableList()

    countInversions(input)
}