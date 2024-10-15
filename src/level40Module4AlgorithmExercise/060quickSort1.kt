package level40Module4AlgorithmExercise

/**
 * Explain or demonstrate quickSort algorithm. (aka, deterministic quicksort or fixed pivot quicksort).
 * Please note that: (Disclaimer, Pre-Note, Conditions, Known data and facts, etc.)
 * The array (input) is not sorted or nearly sorted.
 * The array (input) does not contain any duplicate element.
 */
fun main() {

    var quickSortFunCount = 0
    var getPartitionIndexFunCount = 0
    var swapElementsFunCount = 0
    var iterationCount = 0
    var markerChangeCount = 0

    /**
     * Why?
     *
     * We iterate through a given input array.
     * The goal is to divide (split) the array into two parts.
     * The left part will have all the elements smaller or equal to the pivot element.
     * The right part will have all the elements greater than the pivot element.
     * Now, as we iterate, if we find a greater element than the pivot to the left side of the pivot,
     * we want to swap (exchange, relocate, move) the greater element to the right side of the pivot.
     *
     * ---------
     * Think of an event where we expect all the kids to be in the front row, followed by the adults.
     * Therefore, we inspect (review, analyse, iterate through) the front row.
     * If we find an adult in the front row, we replace the adult with a kid—if there is a kid available.
     * How do we accomplish this?
     * We continue the iteration to find a kid whom we can exchange (swap, relocate, move) with the adult,
     * ensuring that the kid takes priority in the front row.
     * If there are no more kids, it means the goal has already been achieved.
     * However, if we do find a kid, we exchange (swap, relocate, move) the positions of the adult and the kid,
     * ensuring that the kid is prioritised in the front row.
     *
     * -----------
     * Now, how does the swapping procedure work?
     * -----------
     * We vacate the adult's position and allocate a temporary location. (The temp variable).
     * We move the kid to the adult's position.
     * We move the adult to the kid's position.
     *
     * ------------
     * Observation:
     * ------------
     * If we observe, when we perform the swapping,
     * it indicates that the range or region of the kid's area is expanding. Right?
     * Immediately following this area, we encounter the adult’s area. Right?
     * And then, it is up to us to decide whether to consider the pivot (reference) element
     * as a part of the kid’s area or the adult area. OK?
     */
    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        swapElementsFunCount++
        println(": :swapElements: $swapElementsFunCount before: input: ${input.toList()} positionOne: $positionOne positionTwo: $positionTwo")
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
        println(": :swapElements: $swapElementsFunCount after: input: ${input.toList()}")
    }

    /**
     * Why?
     * -------------
     *
     * Ultimate Goal: To sort the given input array.
     *
     * -------------
     * The partition process:
     * -------------
     * Once we select the pivot element and start comparing other elements, we divide the array into two sub-arrays.
     * The element lower or equal to the pivot element goes to the left side,
     * and the element higher than the pivot element goes to the right side.
     * Essentially, we divide the array into two sub-arrays using the pivot element.
     * This process is known as partition.
     *
     * ---------------
     * Ok. But, why do we do this? How does it help? What does it do?
     * ---------------
     * The strategy we use to sort the given input array involves selecting a pivot element as a reference
     * and comparing all other elements with the pivot.
     * This comparison helps us determine whether the element we are evaluating is smaller than, equal to,
     * or greater than the pivot element.
     *
     * ----------------
     * Ok. Then? I mean, So what? How does that help?
     * ----------------
     * Once we determine that an element is smaller than or equal to the pivot element,
     * we place it to the left of the pivot element.
     * If we find that an element is greater than the pivot element, we position it to the right of the pivot element.
     *
     * -----------------
     * Ok. Then what? What does it do? I mean, How does it help?
     * -----------------
     * It divides the array into two parts: those smaller than or equal to the pivot element,
     * and those greater than the pivot element.
     * And more importantly, it positions the pivot element in its final and correct place. (Magic!)
     * And when we recursively repeat this process for each part,
     * it ultimately places all the elements in their correct final position within a sorted array.
     *
     * ------------------
     * Wait, What? How? Example?
     * ------------------
     * Suppose, we have an array as [9, 8, 6, 5, 7].
     * We take the last element as the pivot element.
     * We start the iteration to compare all the other elements with the pivot element.
     * We find that 9 is greater than 7.
     * Therefore, we mark 9 (an adult person) as suspicious and continue our iteration to see if we can find any kids.
     * We find 6, which is smaller than our pivot element, 7.
     * So, we say, “Hey 6, please go and sit with the kids. Mr. 9 will give you the seat.”
     * And we tell Mr. 9, “Hey 9, please go and sit with the adults. Take the old seat of Mr. 6.”
     * Therefore, we swap (exchange) the positions of 9 (adult) and 6 (kid).
     * After that, we mark the first seat (index) as checked, tested, and confirmed as okay.
     * So now, the array becomes: [6, 8, 9, 5, 7].
     * We have not yet finished our iteration.
     * Therefore, we will continue marking (checking) each seat until we complete the iteration.
     * After the first index (seat), our mark moves to the second index, where we find Mr. 8.
     * He is greater than 7. Our iteration then finds a kid, 5.
     * So, we will swap the positions of Mr. 8 and Mr. 5.
     * Now, the array becomes [6, 5, 9, 8, 7].
     * Once we finish the iteration, we need to decide whether to include the pivot element to the left side or right side.
     * We decided to include it to the left side.
     * Hence, we swap the marker +1 position (that is, index 2 and the number 9) with our pivot element, 7.
     * So, the array becomes: 6, 5, 7, 8, 9.
     * Notice the magic here! The number 7 is in its correct position for a sorted array!
     * We need to repeat the same process for the left part,
     * which ranges from the start index (index 0) to the pivot-1 index (index 1),
     * and the right part, which ranges from the pivot + 1 index (index 3) to the end index (index 4).
     *
     * ---------------------
     * So, to determine which index has been marked as tested and confirmed okay for the sorted array,
     * allowing us to proceed further with the remaining left and right parts, we need the partition index.
     * In our case, this is currently index 2 (element 7) in our example.
     * That's why, the below function!
     *
     */
    fun getPartitionIndex(input: IntArray, start: Int, end: Int): Int {
        getPartitionIndexFunCount++
        val pivot = input[end]
        var partitionIndex = start - 1
        println(": :getPartitionIndex: funCount: $getPartitionIndexFunCount input: ${input.toList()} start: $start end: $end pivot: $pivot initialPartitionIndex: $partitionIndex")
        for (j in start..<end) {
            println(": :getPartitionIndex: iterationCount: ${++iterationCount}")
            if (input[j] <= pivot) {
                partitionIndex++
                markerChangeCount++
                println(": :getPartitionIndex: markerChangeCount: $markerChangeCount")
                if (partitionIndex != j) {
                    println(": :getPartitionIndex: funCount: $getPartitionIndexFunCount markerChangeCount: $markerChangeCount input: ${input.toList()} partitionIndex: $partitionIndex j: $j")
                    swapElements(input, partitionIndex, j)
                }
            }
        }
        // This is for printing, understanding, and acknowledgement purpose.
        // Otherwise, we could have used pre-increment while calling the swapElements function and it would work.
        // The actual code can use: swapElements(input, ++partitionIndex, end) without first doing partitionIndex++ separately.
        partitionIndex++
        println(": :getPartitionIndex: $getPartitionIndexFunCount before swapping: input: ${input.toList()} start: $start end: $end pivot: $pivot partitionIndex: $partitionIndex")
        swapElements(input, partitionIndex, end)
        println(": :getPartitionIndex: $getPartitionIndexFunCount after swapping: input: ${input.toList()} start: $start end: $end pivot: $pivot partitionIndex to return: $partitionIndex")
        return partitionIndex
    }

    /**
     * Why?
     *
     * ---------------------
     * This is the function that we call to sort the given input array.
     * ---------------------
     *
     * What does the function do?
     *
     * It calls another function to get a partition index.
     * The partition index is an index that has been resolved and marked as tested okay.
     * It conveys that the element at partition index is in its final and correct position in the final sorted array.
     * It means, we get two remaining parts to resolve:
     * The left part, that ranges from the start index to partitionIndex - 1 and
     * the right part, that ranges from the partitionIndex + 1 to the end index.
     * We recursively call this function for each part until the size of a sub-array becomes 1.
     * However, we do not create separate arrays.
     * The partition function adjusts (modifies) the original array using indices only.
     * So, to determine whether the range is valid or not, we compare the start and end indices.
     * The start index will be less than then end index if the range contains at least 2 elements.
     * Also, in addition to validating the range, we use [start] and [end] indices to select a pivot within this range,
     * to compare the elements within this range,
     * to fix the final and correct position of the selected pivot within this range,
     * and to get a partition index within this range.
     *
     * More detailed elaboration:
     *
     * Why [input], [start], and [end] parameters?
     *
     * The answer lies in understanding what we do in the quick sort algorithm.
     * So, the answer is: We need them to do whatever we do in the quick sort algorithm.
     *
     * So, what do we do in the quicksort algorithm?
     *
     * We sort the input array using the comparison.
     * For comparison, we select a pivot (the reference).
     *
     * And what do we do in the comparison?
     * If we find that the element is less than or equal to the pivot, we keep the element to the left of the pivot.
     * If we find that the element is greater than the pivot, we keep the element to the right of the pivot.
     *
     * Why and how?
     * Why?
     * In a sorted array, a smaller element sits left to the greater element.
     * And a greater element, sits right to the smaller element.
     *
     * How do we do this? Please elaborate more on this.
     *
     * We select a pivot. We compare each element with the pivot.
     * To compare each element, we iterate through the given range.
     *
     * We take a marker index whose value indicates a position that confirms a correct and sorted arrangement up to that point.
     * We take an iteration index, whose job will be to compare each element one by one with the pivot.
     *
     * We want to make sure that the elements smaller than or equal to the pivot element, stay to the left of the pivot.
     * If we find a greater element than the pivot, we continue our iteration to find an element that is smaller than
     * or equal to the pivot. If we find such an element, smaller than or equal to the pivot, we swap its position with
     * the greater element. This exercise ensures that a smaller element stays left to the greater element.
     *
     * Difficult to visualize? Let us understand it with an example:
     *
     * For example: Suppose the input is: [9, 8, 6, 5, 7]
     * We select 7 as a pivot. We compare 9 with 7 and learn that it is greater than the pivot.
     * The marker index does not confirm the first position (index) as tested ok.
     * So, we continue our iteration to find an element that is smaller than or equal to the pivot.
     * Next, we find 8, which is also greater than the pivot.
     * So, we continue moving forward the iteration index.
     * Next, we find 6, which is indeed smaller than the pivot.
     * So, we swap its position with the greater element that we found earlier.
     * The marker index gives the past position and the iteration index gives the current position.
     * Using it, we swap the positions of the greater and the smaller elements.
     * So, 6 gets the position of 9, which is at index 0, and 9 gets the old position of 6, which is at index 2.
     * The marker index marks the first index (0) as confirmed and tested ok.
     * So, the array becomes: [6, 8, 9, 5, 7].
     * The marker confirms that on the first index position (0), the element is smaller than or equal to the pivot.
     * The marker stays at first index position that confirms that up to this point, all the elements are either
     * smaller than or equal to the pivot.
     * The iteration index continues the journey till the end of the given range.
     *
     * Later, the iteration index finds one more smaller element (5) at index (3) than the pivot (7).
     * The marker has confirmed and tested the first index position (0) ok, now its second index position (1)'s turn.
     * So, we swap the positions of 8 and 5.
     * The array was: [6, 8, 9, 5, 7].
     * The array becomes: [6, 5, 9, 8, 7].
     * The marker confirms that on the second index position (1), the element is smaller than or equal to the pivot.
     * The marker stays at the second index position (1) that confirms that up to this point, all the elements are either
     * smaller than or equal to the pivot.
     * And the iteration index has already finished its end-1 coverage.
     *
     * If we observe, we kept expanding the region of the elements that are smaller than or equal to the pivot, and
     * we kept pushing the elements that are greater than the pivot to the boundary line.
     *
     * By the time the iteration index reaches end-1 index, we know that the element after marker is either equal to the
     * pivot element or greater than the pivot element.
     *
     * So, the exercise confirms that the elements after the marker index, are either equal to or greater than
     * the pivot element, except the last element that we have selected as a pivot.
     *
     * So, if we swap the pivot element with marker + 1, we can confirm that all the elements to the left of the pivot
     * are smaller than or equal to the pivot element, and all the elements to the right of the pivot, are greater than
     * the pivot.
     *
     * How? Is it? Difficult to visualize? Let us do it and see.
     *
     * What is the current marker position? Index 2, right?
     * Let us swap the marker + 1 position with the pivot position.
     * The marker position was at index 2, so the position we need to swap with the pivot is index 3 (element 9).
     * So, the current array [6, 5, 9, 8, 7] becomes [6, 5, 7, 8, 9].
     *
     * A couple of magical observations here:
     * 1. In the end of this exercise for the given range, the pivot element finds its final and correct position in the
     * sorted array. We can see that, the pivot element 7 is at index 2 and in the final sorted array [5, 6, 7, 8, 9]
     * also, the position of the element 7 is at index 2. It means, the exercise fixes the pivot element at its final
     * and correct position.
     *
     * 2. We can also see that all the elements smaller than or equal to the pivot element are to the left of the pivot,
     * and all the elements greater than the pivot element are to the right of the pivot.
     *
     * Essentially, what we get here is: Two parts. And if we consider each part as a separate array (hypothetically),
     * and repeat the same exercise for it, we would get one more element at its final and correct position.
     *
     * 2.1. However, a special observation is, we don't create new separate array to achieve the same. Instead, we use
     * indices. We use the range. We know, that 7 is at its final and correct position. So now, we need to do the same
     * exercise for the indices, ranging from the index position 0 to 1, that is [6, 5],
     * and for the indices, ranging from the index position 3 to 4, that is [8, 9].
     *
     * 2.1.1. So, what we use here is, a range. We fix one element at its final and correct position in the sorted array,
     * then we need remaining parts, remaining ranges. So that, we can do the same exercise for each part (range), and
     * fix each element at its final and correct position in the sorted array.
     *
     * That means, we would do the same exercise (a.k.a., recursion), for the remaining part [6, 5] and [8, 9].
     * The array up to this point is: [6, 5, 7, 8, 9].
     * We can see that the left part, [6, 5] has the start index position 0 and the end-index position 1.
     * And we can see that the right part, [8, 9] has the start index position 3 and the end-index position 4.
     *
     * We can see that with each iteration, we can get different ranges, and
     * these ranges can have different start and end indices.
     * To know this start and end indices, we have these [start] and [end] parameters here!
     *
     * TL;DR: We need [start], and [end]:
     * To get a partition index for the given range.
     * To find how much, how long, from when and up to which point we need to iterate through [input] to avoid repetition.
     * To decide the base condition for a recursion process or for an iterative process.
     * For example,
     * [start] == [end] means the [input] array has one element.
     * [start] > [end] means, an invalid range, or an empty array.
     * [start] < [end] means, the [input] has more than one element.
     * [start] < [end] is our base condition.
     *
     * And we need [input] to iterate through it, so that we can compare each element with a pivot, and swap positions
     * in such a way that all the elements smaller than or equal to the pivot, stays left to the pivot, and all the
     * elements greater than the pivot, stays right to the pivot.
     */
    fun quickSort(input: IntArray, start: Int, end: Int) {
        quickSortFunCount++
        println(": :quickSort: funCount: $quickSortFunCount input: ${input.toList()} start: $start end: $end")
        // start < end confirms that the [input] array has more than one element.
        // start == end means the [input] array has one element.
        // start > end means, an invalid range, or an empty array.
        // start < end is our base condition.
        if (start < end) {
            val partitionIndex = getPartitionIndex(input, start, end)
            println(": :quickSort: $quickSortFunCount input: ${input.toList()} start: $start end: $end partitionIndex received: $partitionIndex endIndexOfLeftPart: ${partitionIndex - 1}")
            quickSort(input, start, partitionIndex - 1)
            println(": :quickSort: $quickSortFunCount left part finished: input: ${input.toList()} start was: $start end was: ${partitionIndex - 1}")
            println(": :quickSort: $quickSortFunCount right part begins: input: ${input.toList()} start: ${partitionIndex + 1} end: $end")
            quickSort(input, partitionIndex + 1, end)
            println(": :quickSort: $quickSortFunCount right part finished: input: ${input.toList()} start was: ${partitionIndex + 1} end was: $end")
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