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
     * as part of the kid’s area or the adult area. OK?
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
     * The left part, start index to partitionIndex - 1 and the right part, partitionIndex + 1 to end index.
     * We recursively call this function for each part until the size of a sub-array becomes 1.
     * However, we do not create separate arrays.
     * The partition function adjusts (modifies) the original array using indices only.
     * So, to determine whether the range is valid or not, we compare the start and end indices.
     * The start index will be less than then end index if the range contains at least 2 elements.
     */
    fun quickSort(input: IntArray, start: Int, end: Int) {
        quickSortFunCount++
        println(": :quickSort: funCount: $quickSortFunCount input: ${input.toList()} start: $start end: $end")
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

    val input = intArrayOf(-3, 8, -2, 1, 6, -5, 3, 4)
    val temp = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
//    quickSort(input, 0, input.lastIndex)
    quickSort(temp, 0, temp.lastIndex)
//    println(": :main: sortedArray: ${input.toList()}")
    println(": :main: sortedArray: ${temp.toList()}")
}