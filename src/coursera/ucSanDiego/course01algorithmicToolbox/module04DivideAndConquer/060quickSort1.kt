package coursera.ucSanDiego.course01algorithmicToolbox.module04DivideAndConquer

/**
 * # Quick Sort Algorithm
 *
 * * Explain or demonstrate the quickSort algorithm. (aka, deterministic quicksort or fixed pivot quicksort).
 * * Please note that: (Disclaimer, Pre-Note, Conditions, Known data and facts, etc.)
 * * The array (input) is not sorted or nearly sorted. The array is not reversed.
 * * The array (input) does not contain any duplicate elements.
 * * Stability is not a concern here.
 * * The solution can have a maximum time complexity of `O(n log n)` and a maximum space complexity of `O(log n)`.
 *
 * ## References / Resources
 *
 * * [Apana College - Shradha](https://youtu.be/QXum8HQd_l4?si=65Hz5e3oZo_Yb5dw)
 * * [Jenny's Lectures](https://youtu.be/QN9hnmAgmOc?si=XGJmFrhStWHS7vap)
 * * [Visualization by cups](https://youtu.be/MZaf_9IZCrc?si=kbN4GFYw8RorQRds)
 * * [Animation](https://youtu.be/pM-6r5xsNEY?si=ImLHHNPViIvfF_sc)
 * * [Animation](https://youtube.com/shorts/gptBZml12lU?si=CbDFdZKWPGQETaLe)
 * * [Animation](https://youtube.com/shorts/t40PfJDPWkk?si=PHph8LQdpqw32KVA)
 *
 * ## Time Complexity
 *
 * * Given that the input is not already in any form of sorted order (ascending, descending, or duplicate),
 * we are sure that we have already eliminated the `worst-case` possibility.
 * * Hence, the time complexity of this algorithm for the given input is `O(n log n)` where `n` is the input size.
 * * However, please note that if the `input` is sorted in any form (ascending, descending, or duplicate),
 * we might end up with an unbalanced partition of the given `input`.
 * * Which can result into the `O(n^2)` time complexity.
 * * Because in that case, we always compare each element with almost every other element.
 * * The geometric series/arithmetic progression series would then simplify to `O(n^2)` time complexity.
 * * So, overall:
 * * The best and the average time-complexity is: `O(n log n)`
 * * The worst case time complexity is: `O(n^2)`.
 *
 * ## Space complexity
 *
 * * The `Quick Sort` algorithm is an `in-place` algorithm.
 * * It means that we do not need auxiliary memory that depends on or grows with the input size.
 * * However, if we count the `recursion stack`, then it is:
 * * `O(n)` for the worst-case due to unbalanced partitions (more recursive depth).
 * * `O(log n)` for the average and best case due to balanced partitions (less recursive depth).
 *
 * ## Next
 *
 * * [Next: Part-02: Randomization By: GATE Applied Course](https://youtu.be/HY64dw_Af94?si=DnxKbcwXevD3tzV5)
 * * [Randomization By: The GATEHUB](https://youtu.be/nUlEfx-HgII?si=i9KMK7FTWHIZgn0-)
 * * [3-Flags-Dutch Quick Sort by CodeSmart](https://youtu.be/9pdkbqGwUhs?si=OOJVvFFfhA6_3I3w)
 *
 */
fun main() {

    /**
     * # Why?
     *
     * * We iterate through a given input array.
     * * The goal is to divide (split) the array into two parts.
     * * The left part will have all the elements smaller or equal to the pivot element.
     * * The right part will have all the elements greater than the pivot element.
     * * Now, as we iterate, if we find a greater element than the pivot to the left side of the pivot,
     * we want to swap (exchange, relocate, move) the greater element to the right side of the pivot.
     *
     * ## Story
     *
     * * Think of an event where we expect all the kids to be in the front row, followed by the adults.
     * * Therefore, we inspect (review, analyse, iterate through) the front row.
     * * If we find an adult in the front row, we replace the adult with a kid—if there is a kid available.
     *
     * **How do we accomplish this?**
     *
     * * We continue the iteration to find a kid whom we can exchange (swap, relocate, move) with the adult,
     * ensuring that the kid takes priority in the front row.
     * * If there are no more kids, it means the goal has already been achieved.
     * * However, if we do find a kid, we exchange (swap, relocate, move) the positions of the adult and the kid,
     * ensuring that the kid is prioritised in the front row.
     *
     * **Now, how does the swapping procedure work?**
     *
     * * We vacate the adult's position and allocate a temporary location. (The temp variable).
     * * We move the kid to the adult's position.
     * * We move the adult to the kids' position.
     *
     * ## Observation:
     *
     * * If we observe, when we perform the swapping, it indicates that the range or region of the kid's area is
     * expanding. Right?
     * * Immediately after following this area, we encounter the adult’s area. Right?
     * * And then, it is up to us to decide whether to consider the pivot (reference) element as a part of the kid’s
     * area or the adult area. OK?
     */
    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        require(positionOne in 0..input.lastIndex && positionTwo in 0..input.lastIndex) {
            "Index $positionOne and Index $positionTwo for size ${input.size} exceed bounds"
        }
        if (positionOne == positionTwo) return
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    /**
     * # Why?
     *
     * Ultimate Goal: To sort the given input array.
     *
     * # The partition process:
     * * Once we select the pivot element and start comparing other elements, we divide the array into two sub-arrays.
     * * The element lower or equal to the pivot element goes to the left side,
     * and the element higher than the pivot element goes to the right side.
     * * Let us assume that we have a `pivot`.
     * * Now, let us also assume that we have a `marker` index.
     * * If the `marker` index finds that `input[marker] <= pivot`, then it `marks` the current position `tested OK`.
     * * And moves on to check the next position. So, it becomes: `marker++`.
     * * We do this for the given range that is from `[start] to [end]`.
     * * So, we iterate from [start] to [end] and we check each element within this range.
     * * Essentially, we divide the array into two sub-arrays using the pivot element.
     * * This process is known as partition.
     *
     * **Ok. But, why do we do this? How does it help? What does it do?**
     * * The strategy we use to sort the given input array involves selecting a pivot element as a reference
     * and comparing all other elements with the pivot.
     * * This comparison helps us determine whether the element we are evaluating is smaller than, equal to,
     * or greater than the pivot element.
     *
     * **Ok. Then? I mean, So what? How does that help?**
     * * Once we determine that an element is smaller than or equal to the pivot element,
     * we place it to the left of the pivot element.
     * * If we find that an element is greater than the pivot element, we position it to the right of the pivot element.
     *
     * **Ok. Then what? What does it do? I mean, How does it help?**
     * * It divides the array into two parts: those smaller than or equal to the pivot element,
     * and those greater than the pivot element.
     * * And more importantly, **it positions the pivot element in its final and correct place. (Magic!)**
     * * And when we recursively repeat this process for each part,
     * it ultimately places all the elements in their correct final position within a sorted array.
     *
     * **Wait, What? How? Example?**
     * * Suppose, we have an array as `[9, 8, 6, 5, 7]`.
     * * We take the last element as the pivot element.
     * * We start the iteration to compare all the other elements with the pivot element.
     * * We find that 9 is greater than 7.
     * * Therefore, we mark 9 (an adult person) as suspicious and continue our iteration to see if we can find any kids.
     * * We find 6, which is smaller than our pivot element, 7.
     * * So, we say, “Hey 6, please go and sit with the kids. Mr. 9 will give you the seat.”
     * * And we tell Mr. 9, “Hey 9, please go and sit with the adults. Take the old seat of Mr. 6.”
     * * Therefore, we swap (exchange) the positions of 9 (adult) and 6 (kid).
     * * After that, we mark the first seat (index) as checked, tested, and confirmed as okay.
     * * So now, the array becomes: `[6, 8, 9, 5, 7]`.
     * * We have not yet finished our iteration.
     * * Therefore, we will continue marking (checking) each seat until we complete the iteration.
     * * After the first index (seat), our mark moves to the second index, where we find Mr. 8.
     * * He is greater than 7. Our iteration then finds a kid, 5.
     * * So, we will swap the positions of Mr. 8 and Mr. 5.
     * * Now, the array becomes `[6, 5, 9, 8, 7]`.
     * * Once we finish the iteration, we need to decide whether to include the pivot element to the left side or right
     * side.
     * * We decided to include it to the left side.
     * * Hence, we swap the marker +1 position (that is, index 2 and the number 9) with our pivot element, 7.
     * * So, the array becomes: `[6, 5, 7, 8, 9]`.
     * * Notice the magic here! The number 7 is in its correct position for a sorted array!
     * * We need to repeat the same process for the left part,
     * which ranges from the start index (index 0) to the pivot-1 index (index 1),
     * and the right part, which ranges from the pivot + 1 index (index 3) to the end index (index 4).
     * * So, to determine which index has been marked as tested and confirmed okay for the sorted array,
     * allowing us to proceed further with the remaining left and right parts, we need the partition index.
     * * In our case, this is currently index 2 (element 7) in our example.
     * * That's why we have the below function, and that's how the below function works!
     *
     */
    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        // We select the last element as a pivot element here.
        // It means, we will compare all the other elements between [start] and [end] range, with this pivot element.
        // We don't want to compare the pivot element with itself. So, the comparison will go up to <end.
        val pivot = input[end]
        // We want to start the marker confirmation from the [start] index position for the given range.
        // The [start] index position does not have to be 0 always.
        // Because, the [input] array does not have to be a full array always.
        // We may get different ranges for the different parts (left, and right) of the given input array.
        // The partition index must be within the given range, between [start] and [end] indices (positions).
        // So, we don't start the marker index from -1. We start from [start] - 1.
        // For example, if someone asks us to check the arrangement for the seats 8 to 16,
        // we start marking the check from the seat number 8, not from 0.
        // That's why our partitionIndex (a.k.a., a markerIndex) starts from [start] - 1, and not from -1.
        // [start - 1] indicates that we have not confirmed and tested ok any seat yet, but when we do,
        // the first seat will be [start], which can be 0, but it does not have to be 0 always.
        // In other words, `partitionIndex = start - 1` indicates the right, correct position of the pivot found so far.
        // If it is -1, it means that we have not found and set any final, correct, and concrete pivot position.
        // Hence, the initial value is: `start - 1`.
        var partitionIndex = start - 1

        // This iteration compares all the elements between the [start] and the [end] range, with our pivot element.
        // However, we have taken the last element as our pivot element,
        // and we don't want to compare the pivot element with itself.
        // So, the iteration goes up to <end, instead of <=end.
        for (j in start..<end) {
            if (input[j] <= pivot) {
                // When we find an element that is less than or equal to the pivot,
                // we increase the partitionIndex. So that we can say, all the elements to the left of the partitionIndex
                // are less than or equal to the pivot element.
                partitionIndex++
                // Note that we are going inside this block only if `input[j] <= pivot`.
                // We increase the marker index, the `partitionIndex` only when `input[j] <= pivot`.
                // We can provide an analogy for this process.
                // Every time we find that `input[j] <= pivot`, the `partitionIndex` will create a seat for the
                // element, which is currently at position `j`.
                // So, every time we find `input[j] <= pivot`, the `partitionIndex` will create a seat, and we will
                // move the element from the position `j` to the position created by the `partitionIndex`.
                // But then, what will happen to the element which is already at the `partitionIndex` position?
                // The element at the current `partitionIndex` position will move to the `j` position.
                // So basically, every time we find that `input[j] <= pivot`, the `partitionIndex` will create a seat
                // and we will swap the elements at positions `partitionIndex` and `j`.
                // It means that for the cases when `input[j] > pivot`, the `partitionIndex` does not make progress.
                // But in that case, `j` continues making progress.
                // It means that if we find that `partitionIndex` lags behind `j`,
                // we are sure that it is due to the case where `j` found an element which is greater than the `pivot`.
                // As soon as we find this, we immediately swap the elements at positions `partitionIndex` and `j`.
                // Why do we swap `j` with the current `partitionIndex`?
                // How can we ensure that the current `partitionIndex` needs to be swapped with `j`?
                // Shouldn't we swap `j` with the `partitionIndex + 1` instead of the `partitionIndex`?
                // Well, the point is, swapping also happens only when we find `input[j] <= pivot`.
                // And by the time we swap, so before we swap, the `partitionIndex` is already moved by 1.
                // And this time, the `partitionIndex` is indicating a greater value than the pivot!
                // We can better understand this with an example.
                // Suppose we have an array: `[3, 8, 5, 9, 7]`
                // We make the element `7` the `pivot` element.
                // `start` is index `0` and `end` is index `4`.
                // Initially, the `partitionIndex` is `start - 1 = 0 - 1 = -1`.
                // `3 <= 7`, so our `partitionIndex` moves 1 step forward and confirms that the index `0` is tested OK.
                // The `partitionIndex` is at position `0`.
                // Next, `8 > 7`. So, the `partitionIndex` does not move.
                // Next, `5 <= 7`. So, the `partitionIndex` moves 1 step forward, and now it is at position `1`!
                // We find that `partitionIndex` and `j` are not at the same position.
                // The `partitionIndex` is at position `1`, whereas `j` is at position `2`.
                // The current position of `j` indicates an element that is less than or equal to the pivot.
                // That's why we could enter into this `if` condition.
                // And the current position of `partitionIndex` indicates an element that is greater than the pivot.
                // If the position of `partitionIndex` and `j` were the same,
                // it would have meant that we did not find any element that is greater than the pivot.
                // But if we ever find an element that is smaller than or equal to the pivot,
                // we increase the `partitionIndex`, and if we still find that the position of the `partitionIndex`,
                // and `j` are not the same, it would mean that the current position of the `partitionIndex` shows an
                // element that is greater than the pivot.
                // And the current position of `j` would represent an element that is smaller than or equal to the
                // pivot.
                // That's why we swap the elements at the current positions of the `partitionIndex` and `j`.
                // Assume a movie theater where we expect the seating arrangement from younger to older.
                // Assume that there are two guards. 1. The partitionIndex. 2. The iteration j.
                // They both start the iteration from the same point, together, which is the `start`.
                // Once the partitionIndex checks the seat (tested OK or not-OK, it doesn't matter for j),
                // he will inform the same to iteration j, and iteration j will move on.
                // If the partitionIndex finds an older kid on the seat instead of a younger kid, he would stop there,
                // waiting for the iteration j to find any younger kid that they can exchange with the older kid.
                // That's why the iteration j continues the journey, while the partitionIndex can lag behind.
                // And if the partitionIndex is behind the iteration j, it means the partitionIndex
                // has found an older kid that we need to exchange if the iteration j finds a younger kid.
                if (partitionIndex != j) {
                    swapElements(input, partitionIndex, j)
                }
            }
        }
        // This is for printing, understanding, and acknowledgment purposes.
        // Otherwise, we could have used pre-increment while calling the swapElements function, and it would work.
        // The actual code can use: swapElements(input, ++partitionIndex, end) without first doing partitionIndex++ separately.
        partitionIndex++
        swapElements(input, partitionIndex, end)
        return partitionIndex
    }

    /**
     * # Why?
     *
     * * This is the function that we call to sort the given input array.
     *
     * ## What does the function do?
     *
     * * It calls another function to get a partition index.
     * * The partition index is an index that has been resolved and marked as tested okay.
     * * It conveys that the element at partition index is in its final and correct position in the final sorted array.
     * * It means that we get two remaining parts to resolve:
     * * The left part, that ranges from the start index to partitionIndex - 1 and
     * the right part, that ranges from the partitionIndex + 1 to the end index.
     * * We recursively call this function for each part until the size of a sub-array becomes 1.
     * * However, we do not create separate arrays.
     * * The partition function adjusts (modifies) the original array using indices only.
     * * So, to determine whether the range is valid or not, we compare the start and end indices.
     * * The start index will be less than the end index if the range contains at least 2 elements.
     * * Also, in addition to validating the range, we use [start] and [end] indices to select a pivot within this range,
     * to compare the elements within this range,
     * to fix the final and correct position of the selected pivot within this range,
     * and to get a partition index within this range.
     *
     * ## Detailed elaboration:
     *
     * **Why [input], [start], and [end] parameters?**
     *
     * * The answer lies in understanding what we do in the quick sort algorithm.
     * * So, the answer is: We need them to do whatever we do in the quick sort algorithm.
     *
     * **So, what do we do in the quicksort algorithm?**
     *
     * * We sort the input array using the comparison.
     * * For comparison, we select a pivot (the reference).
     *
     * **And what do we do in the comparison?**
     *
     * * If we find that the element is less than or equal to the pivot, we keep the element to the left of the pivot.
     * * If we find that the element is greater than the pivot, we keep the element to the right of the pivot.
     *
     * ### Why and how?
     *
     * **Why?**
     *
     * * In a sorted array, a smaller element sits to the left of the greater element.
     * * And a greater element, sits right to the smaller element.
     *
     * **How do we do this? Please elaborate more on this.**
     *
     * * We select a pivot. We compare each element with the pivot.
     * * To compare each element, we iterate through the given range.
     *
     * * We take a marker index whose value indicates a position that confirms a correct and sorted arrangement up to
     * that point.
     * * We take an iteration index, whose job will be to compare each element one by one with the pivot.
     *
     * * We want to make sure that the elements smaller than or equal to the pivot element stay to the left of the pivot.
     * * If we find a greater element than the pivot, we continue our iteration to find an element that is smaller than
     * or equal to the pivot.
     * * If we find such an element, smaller than or equal to the pivot, we swap its position with
     * the greater element.
     * * This exercise ensures that a smaller element stays left to the greater element.
     *
     * **Difficult to visualize? Let us understand it with an example:**
     *
     * * For example: Suppose the input is: `[9, 8, 6, 5, 7]`.
     * * We select `7` as a pivot.
     * * We compare `9` with `7` and learn that it is greater than the pivot.
     * * The `marker index` or the `partition index` does not confirm the first position (index) as tested ok.
     * * So, the `marker index` or the `partition index` stays at the position `0` only.
     * * And we continue our iteration to find an element that is smaller than or equal to the pivot.
     * * Next, we find 8, which is also greater than the pivot.
     * * So, we continue moving forward with the iteration index.
     * * Next, we find 6, which is indeed smaller than the pivot.
     * * So, we swap its position with the greater element that we found earlier.
     * * The `marker index` gives the past position, and the iteration index gives the current position.
     * * Using it, we swap the positions of the greater and the smaller elements.
     * * So, `6` gets the position of `9`, which is at index `0`, and `9` gets the old position of `6`, which is at
     * index `2`.
     * * The `marker index` marks the first index `0` as confirmed and tested ok.
     * * So, the array becomes: `[6, 8, 9, 5, 7]`.
     * * The `marker` confirms that on the first index position `0`, the element is smaller than or equal to the pivot.
     * * The `marker` stays at the first index position, which confirms that up to this point, all the elements are
     * either smaller than or equal to the pivot.
     * * The `iteration index` continues the journey till the end of the given range.
     * * Later, the `iteration index` finds one more smaller element `5` at index `3` than the pivot `7`.
     * * The `marker` has confirmed and tested the first index position `0` OK.
     * * Now, it's the second index position `1`'s turn.
     * * So, we swap the positions of `8` and `5`.
     * * The array was: `[6, 8, 9, 5, 7]`.
     * * The array becomes: `[6, 5, 9, 8, 7]`.
     * * The `marker` confirms that on the second index position `1`, the element is smaller than or equal to the pivot.
     * * The `marker` stays at the second index position `1` which confirms that up to this point, all the elements are
     * either smaller than or equal to the pivot.
     * * And the iteration index has already finished its end-1 coverage.
     *
     * ### Observation
     *
     * * If we observe, we kept expanding the region of the elements that are smaller than or equal to the pivot, and
     * we kept pushing the elements that are greater than the pivot to the boundary line.
     *
     * * By the time the iteration index reaches `end-1` index, we know that the element after the `marker` is either
     * equal to the pivot element or greater than the pivot element.
     *
     * * So, the exercise confirms that the elements after the `marker index`, are either equal to or greater than
     * the pivot element, except the last element that we have selected as a pivot.
     *
     * * So, if we swap the pivot element with `marker + 1`, we can confirm that all the elements to the left of the
     * pivot are smaller than or equal to the pivot element, and all the elements to the right of the pivot, are greater
     * than the pivot.
     *
     * **How? Is it? Difficult to visualize? Let us do it and see.**
     *
     * * What is the current `marker` position? Index 2, right?
     * * Let us swap the `marker + 1` position with the `pivot position`.
     * * The `marker` position was at index `2`, so the position we need to swap with the pivot is index `3` (element 9).
     * * So, the current array `[6, 5, 9, 8, 7]` becomes `[6, 5, 7, 8, 9]`.
     *
     * * A couple of magical observations here:
     * * At the end of this exercise for the given range, the pivot element finds its final and correct position in the
     * sorted array.
     * * We can see that the pivot element `7` is at index `2` and in the final sorted array `[5, 6, 7, 8, 9]`
     * also, the position of the element `7` is at index `2`.
     * * It means that the exercise fixes the pivot element at its final and correct position.
     *
     * * We can also see that all the elements smaller than or equal to the pivot element are to the left of the pivot,
     * and all the elements greater than the pivot element are to the right of the pivot.
     *
     * * Essentially, what we get here is: Two parts.
     * * And if we consider each part as a separate array (hypothetically), and repeat the same exercise for it,
     * we would get one more element at its final and correct position.
     *
     * * However, a special observation is that we don't create a new separate array to achieve the same.
     * * Instead, we use indices. We use the range.
     * * We know that `7` is at its final and correct position.
     * * So now, we need to do the same exercise for the indices, ranging from the index position 0 to 1, that is [6, 5],
     * and for the indices, ranging from the index position 3 to 4, that is [8, 9].
     * * So, what we use here is a range.
     * * We fix one element at its final and correct position in the sorted array, then we need the remaining parts,
     * that we get from the remaining ranges.
     * * So that we can do the same exercise for each part (range), and fix each element at its final and correct
     * position in the sorted array.
     * * That means, we would do the same exercise (a.k.a., recursion), for the remaining part [6, 5] and [8, 9].
     * * The array up to this point is: [6, 5, 7, 8, 9].
     * * We can see that the left part, `[6, 5]` has the `start index` position `0` and the `end-index` position `1`.
     * * And we can see that the right part, `[8, 9]` has the start index position `3` and the `end-index` position `4`.
     * * We can see that with each iteration, we can get different ranges, and these ranges can have different `start`
     * and `end` indices.
     * * To know this `start` and `end` indices, we have these [start] and [end] parameters here!
     *
     * ### TL;DR: We need [start], [end], and [input]:
     * * Because every time we divide the [input], we get two new parts, and each new part can have different
     * [start] and [end] indices.
     * **We need and track these [start] and [end]:**
     * * To decide a pivot element within this range.
     * * To get a partition index for the given range.
     * * To find how much, how long, from when, and up to which point, we need to iterate through [input] to avoid
     * repetition.
     * * To decide the base condition for the recursion process or for the iterative process.
     * **For example**
     * * [start] == [end] means the [input] array has one element.
     * * [start] > [end] indicates an invalid range, or an empty array.
     * * [start] < [end] indicates the [input] has more than one element.
     * * So, [start] < [end] is our base condition.
     * * And we need [input] to iterate through it, so that we can compare each element with a pivot, and swap positions
     * in such a way that all the elements smaller than or equal to the pivot stay left of the pivot, and all the
     * elements greater than the pivot stay to the right of the pivot.
     * * We need [input] for iteration and swapping.
     *
     * ## Key-points, key-lemmas
     *
     * * Pivot, partitionIndex, and swapping.
     * * Get the partition index from the [getPartitionIndex].
     * * Resolve the remaining parts by recursively calling this function for:
     * * `partitionIndex - 1` and `partitionIndex + 1`
     */
    fun quickSort(input: IntArray, start: Int, end: Int) {
        // start == end means the [input] array has one element.
        // start > end means, an invalid range, or an empty array.
        // start < end confirms that the [input] array has more than one element.
        // start < end is our base condition.
        if (start < end) {
            val partitionIndex = getPartitionIndex(input, start, end)
            quickSort(input, start, partitionIndex - 1)
            quickSort(input, partitionIndex + 1, end)
        }
    }

    fun getInputArray(): IntArray {
//        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        return intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    }

    val input = getInputArray()
    quickSort(input, 0, input.lastIndex)
    println(": :main: sortedArray: ${input.toList()}")
}