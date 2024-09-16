package level40Module4AlgorithmExercise

fun main() {

    /**
     * Explain the count sort algorithm with an example or sort the following array using the counting sort algorithm.
     * A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1].
     *
     * The count sort is a non-comparison based algorithm to sort the given input.
     * Non-comparison based algorithm means, we do not compare elements with each other.
     * In other words, we sort elements without comparing them with each other.
     * We use the count sort algorithm when the maximum value element of the given input,
     * does not grow faster than the size of the input.
     * Here, we find the maximum value element and create a count array starting from the minimum value element up to
     * the maximum value element.
     * Then, we store the occurrences (repetition) count for each element.
     * Based on the occurrences, we adjust the position and finally provide the sorted array.
     * Let us consider an example of: A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1].
     * The minValue is -5 and maxValue is 2. The size of the array is 10.
     *
     * TL;DR: (Key-points):
     * 1. max - min + 1 = Range (Size)
     * 2. countArray[element - min]++ = Count occurrences with normalisation
     * 3. countArray[i] = countArray[i - 1] + countArray[i] => Cumulative count from 1..<countArray.size
     * 4. input.indices.reversed(), take original value from input[i],
     * find target index position using the countArray as countArray[value - min] - 1,
     * resultArray[target index position] = value, and then mark the seat as allocated by countArray[value - min]--
     */
    fun countSort(array: IntArray) {

        /**
         * If the size of the input array is less than or equal to 1, print the input array and return.
         */
        if (array.size <= 1) {
            println(array.toList())
            return
        }

        /**
         * First, we find the `minValue` and the `maxValue`. Why?
         * We find the `minValue` and the `maxValue` to get the range.
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the minValue is -5.
         */
        var minValue = array[0]

        /**
         * We find the `minValue` and the `maxValue`. Why?
         * We find the `minValue` and the `maxValue` to get the range.
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the maxValue is 2.
         */
        var maxValue = array[0]

        /**
         * With a single iteration, we find both the [minValue] and the [maxValue].
         * If we use `array.minOrNull()` and `array.maxOrNull()`, then we iterate the input array two times.
         */
        for (element in array) {
            if (element < minValue) {
                minValue = element
            }
            if (element > maxValue) {
                maxValue = element
            }
        }

        println(": :countSort: maxValue: $maxValue minValue: $minValue")


        /**
         * Based on the `minValue` and `maxValue`, we get a range (size).
         * Based on the size, we create a countArray. Why do we create the countArray?
         * We need a count array where we can store the occurrences (repetition) count.
         * In our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * -5 comes 2 times, -3 comes 1 time, -4 comes 1 time, 1 comes 3 times, 0 comes 2 times, and 2 comes once.
         * So, what we do is, we take these values (we refer to them as keys actually as they can internally represent
         * actual values), i.e., [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] and create equivalent indices.
         * So, the array will have these [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] indices.
         * It means, we will have [-5, -4, -3, -2, -1, 0, 1, 2] indices.
         * So, the count array will have a total of 7 indices, and a size of 8.
         * We know that the indices gets ascending order sequence, and it cannot be a negative value.
         * To solve that, we will use normalisation. But first, we need to find the size of the count array.
         * And then we store respective (associated, corresponding) occurrences count for each index.
         * For example, the index -5 gets the value 2 as it comes 2 times. It means, -5 will have a value as 2.
         * the index -3 gets the value 1 as it comes 1 time, the index -4 gets the value 1 as it comes once,
         * the index 1 gets the value 3 as it comes 3 times, the index 0 gets the value 2 as it comes twice,
         * and the index 2 gets the value 1 as it comes once.
         * The indices will be a range starting from the minValue -5 and goes up to the maxValue 2.
         * To arrange these indices in a particular order, i.e., in ascending order,
         * we use maxValue and minValue to get the required range of indices.
         * To create such an array, we need to pre-calculate the size of the required array.
         * Here also we use the minValue and the maxValue to get the size of the array.
         * maxValue - minValue = 2 - (-5) = 2 + 5 = 7.
         * The maxValue is 2 and the minValue is -5.
         * We need an array that can store the values from the minValue up to the maxValue.
         * So, it should be able to store (it should have the following range):
         * [-5, -4, -3, -2, -1, 0, 1, 2].
         * We can see that, we need to have a size of maxValue - minValue + 1
         * to include both the starting value (minValue) and the ending value (maxValue).
         * Hence, the formula for the size is: maxValue - minValue + 1.
         * As we initialize the integer array with the size, all the indices will have the default value 0.
         */
        val countArray = IntArray(maxValue - minValue + 1)


        /**
         * Key-point:
         * What?
         * Map (convert) each element of the input to an index (such that the index represents the original element),
         * Count occurrences (repetition) of each element of the input, and store it to the count array as a value.
         *
         * Why?
         * When we map (convert) the original elements into indices, we almost sort the elements, because
         * indices are already sort by nature. The indices start with 0, and goes up to the last element.
         * And as a value of each index, we store the occurrences, so that we get to know that how many seats (indices)
         * a particular element will occupy.
         *
         * For example, let us assume that there are 5 groups (elements) to purchase a movie ticket.
         * However, the members of each group are mixed and unorganized. So, we ask each member about the associated
         * group and increase the count for the group. At the end, we find the total members for each group.
         * So, this particular step is like counting the members for each group.
         * There can be the same group type, i.e., element-key, but the underlying property, such as,
         * ID, hashCode, or memory can be different.
         * In that case, it is important to maintain the correct order to achieve the stability.
         * Also, there can be the same number of members (occurrence count) for two or more groups.
         *
         * How?
         * We iterate through the original array, access each element, and increase the corresponding count.
         * While doing this, we also need to consider negative numbers and normalise it.
         * To normalise a negative number means to make it a positive number, and we do it by subtracting the minimum
         * value from all the elements.
         * We subtract each and every element by `minValue`. So, it does not change the size of the count array.
         * But, Why do we normalise?
         * Because, we will be using indices to represent the actual elements and indices cannot be negative.
         * These indices will have a value that would represent the number of occurrence count (repetition)
         * for each (corresponding, associated) element.
         *
         * Example, please?
         * For our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * The first element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0 => Occurs once.
         * So, countArray[0]++ => countArray[0] = Value 1.
         * It means, we have the element (key, value, or whatever you call it) 0 once.
         * As we can see, -5 is index 0 after the normalisation, and
         * it has the value 1 which means, it has occurred once.
         * Thus, we convert actual element (-5) into an index (0). So, the corresponding (associated) index for the
         * element (-5) is index (0) (i.e., the index 0 represents the element -5) and an index cannot be a negative
         * value. So, we use normalisation.
         * Here, the original value (-5) is the `original value - minValue` = `-5 - (-5)` = 0th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The second element: -3 - (minValue) = -3 - (-5) = -3 + 5 = Index 2. => Occurs once.
         * So, countArray[2]++ => countArray[2] = Value 1.
         * It means, we have the element 2 once.
         * As we can see, -3 is index 2 after the normalisation, and it has the value 1 which means,
         * it has occurred once.
         * So, we converted the actual element (-3) into an index (2) and it has the value 1.
         * The index (2) represents the element (-3).
         * Here, the original value (-3) is the `original value - minValue` = `-3 - (-5)` = 2nd index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The third element: -4 - (minValue) = -4 - (-5) = -4 + 5 = Index 1. => Occurs once.
         * So, countArray[1]++ => countArray[1] = Value 1.
         * Here, we converted the actual element (-4) into an index (1) and it has the value 1.
         * I.e., So far, it (the element "-4" = index 1) has occurred once.
         * The index (1) represents the element (-4).
         * Here, the original value (-4) is the `original value - minValue` = `-4 - (-5)` = 1st index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The fourth element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0. => Occurs twice.
         * So, countArray[0]++ => countArray[0] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (-5) into an index (0) and it has the value 2.
         * I.e., So far, it (the element "-5" = index 0) has occurred twice.
         * The index (0) represents the element (-5).
         * Here, the original value (-5) is the `original value - minValue` = `-5 - (-5)` = 0th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The fifth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs once.
         * So, countArray[6]++ => countArray[6] = Value 1.
         * Here, we converted the actual element (1) into an index (6) and it has the value 1.
         * I.e., So far, it (the element "1" = index 6) has occurred once.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The sixth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs once.
         * So, countArray[5]++ => countArray[5] = Value 1.
         * Here, we converted the actual element (0) into an index (5) and it has the value 1.
         * I.e., So far, it (the element "0" = index 5) has occurred once.
         * The index (5) represents the element (0).
         * Here, the original value (0) is the `original value - minValue` = `0 - (-5)` = 5th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The seventh element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs twice.
         * So, countArray[6]++ => countArray[6] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (1) into an index (6) and it has the value 2.
         * I.e., So far, it (the element "1" = index 6) has occurred twice.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The eighth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs twice.
         * So, countArray[5]++ => countArray[5] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (0) into an index (5) and it has the value 2.
         * I.e., So far, it (the element "0" = index 5) has occurred twice.
         * The index (5) represents the element (0).
         * Here, the original value (0) is the `original value - minValue` = `0 - (-5)` = 5th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The ninth element: 2 - (minValue) = 2 - (-5) = 2 + 5 = Index 7. => Occurs once.
         * So, countArray[7]++ => countArray[7] = Value 1.
         * Here, we converted the actual element (2) into an index (7) and it has the value 1.
         * I.e., So far, it (the element "2" = index 7) has occurred once.
         * The index (7) represents the element (2).
         * Here, the original value (2) is the `original value - minValue` = `2 - (-5)` = 7th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * The tenth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs thrice.
         * So, countArray[6]++ => countArray[6] = 2 (existed) + 1 = Value 3.
         * Here, we converted the actual element (1) into an index (6) and it has the value 3.
         * I.e., So far, it (the element "1" = index 6) has occurred thrice.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalisation.
         *
         * Key-point, Conclusion:
         * `original value` of the input array maps to `value - minValue` of the countArray as an index.
         * The value of the index of the countArray represents the count (repetition, occurrences).
         * Later, if we want to find the occurrence count (repetition) of a particular value,
         * and we have the original value, we can use the above formula to first get the corresponding (associated)
         * index (that maps the original value) of the countArray, and then, we can take the value of that particular index
         * of the countArray that represents the occurrence count (repetition).
         * This formula helps to understand the mapping between the countArray and the original input array
         * during normalisation and de-normalisation.
         */
        for (element in array) {
            println(": :countSort: input element: $element minValue: $minValue: element - minValue: ${element - minValue}")
            countArray[element - minValue]++
        }

        println(": :countSort: countArray: ${countArray.toList()}")

        //region Unstable solution
        // Key-point:
        // If maintaining the order of the original elements does not matter, we can provide the solution
        // as per the below code.
        // What?
        // The indices are already sorted by now.
        // And we know that each index represents (maps to) the corresponding original element.
        // And we also know that the values of these indices are the counts (occurrences, repetition) for each element.
        // So, we access the indices and values from the countArray one by one.
        // We take the original input array only, we start with index 0,
        // place the elements and repeat according to its count value.
        // With each placement, we increase the index of the original input array to move forward as one element can
        // occupy only one position (seat).
        // Also, we know that to normalise the elements, we subtracted each element by `minValue`.
        // Hence, while placing the original element back from the countArray to the original input array,
        // we add the `minValue` to each element.
        /*var index = 0
        for ((element, count) in countArray.withIndex()) {
            println(": :countSort: unstable solution: index: $index element: $element count: $count")
            repeat(count) {
                array[index++] = element + minValue
            }
        }
        println(": :countSort: unstable solution: output: ${array.toList()}")*/
        //endregion

        /**
         * Key-Point:
         * What?
         * Cumulative count.
         * As we know, the indices of the countArray represents or maps to the original elements of the input and
         * the values of the countArray represents occurrence count (repetitions) of each element of the input.
         * Now, we will perform and store cumulative count.
         * For example, the current value (count) of the second element will become a cumulative count as:
         * (The count of the first element + the count of the second element).
         * Similarly, the current value (count) of the third element will become:
         * (The count of the first element + the count of the second element + the count of the third element).
         *
         * Why?
         * It will tell us how many more elements are there less than or equal to the value of the current element.
         * For example, if the value of third element (2nd index) in the countArray is 4, it means, there are
         * 4 more elements in a queue waiting to be placed.
         *
         * For example, imagine you have a line of people waiting to buy movie tickets.
         * Each person knows how many people are ahead of them in the line.
         * This information allows each person to determine their exact position in the line.
         * Similarly, in our counting sort, the cumulative count helps each number determine its position
         * in the sorted array by knowing how many numbers are ahead of it (or equal to it).
         *
         * So, it is like: First, we counted members for each group, and now, we are counting total members up to a
         * particular member to understand how many members are ahead (or equal) to this particular member.
         *
         * So, we convert (or say, map or associate) the individual occurrence counts into the cumulative count to get
         * corresponding relative positions.
         *
         * How?
         * For example, let us say, if the first index of the countArray has 3 as a value.
         * It means, the associated element that maps to this index will occupy 3 positions (We counted the repetition,
         * and count does not start with 0. Hence, we will stick to this count representation as of now).
         * Hence, it is obvious that the second element will start after the first element finishes all its
         * allocated positions/seats. So, the second element will start from the fourth position/seat.
         *
         * Note that what we get here is the ending (last) allocated position/seat (or a cumulative count)
         * as a value for the index.
         * For example, as we said, if the first element (0th index) has 3 seats and the second element (1st index)
         * has 2 allocated positions/seats, then the first element (0th index) gets the same value as 3,
         * but the second element (1st index) gets the updated value as:
         * (3 positions for the first element) + (2 positions for the second element) = 5th position/seat, that is a
         * cumulative count.
         *
         * Hence, the formula is:
         * The element countArray[index] starts after countArray[index - 1] and occupies countArray[index] positions/seats.
         *
         * So now, the indices of the countArray represents the original elements and the value of each index
         * in the countArray represents the cumulative count of allocated positions/seats up to the element.
         */
        for (i in 1..<countArray.size) {
            println(": :countSort: shifting positions: i: $i countArray[i]: ${countArray[i]} i-1: ${countArray[i - 1]}")
            countArray[i] = countArray[i - 1] + countArray[i]
        }

        println(": :countSort: countArray after position shifting: ${countArray.toList()}")

        /**
         * Take the value from the original array and position from the count array
         */
        val sortedArray = IntArray(array.size)

        /**
         * Key-point:
         * What?
         * Start taking the values from the right side of the original array to make it a stable solution.
         * The stable solution keeps the position orders of the original values as it is.
         *
         * Why?
         * Think of this as assigning seats to people based on their ticket numbers.
         * The cumulative count tells us the "seat number" for each person.
         * As each person sits down, the count is decremented to keep track of the next available seat for people.
         * So that when we see the reservation chart (occupied seats), we get the correct information of the available
         * seats.
         *
         * Ok, but why to traverse from reverse?
         * Traversing in reverse ensures that the sort remains stable.
         * Stability means that if two elements are equal,
         * their relative order in the input is preserved in the sorted output.
         * So, it is like as if we start allocating seats from the bottom and move towards the top.
         * For example, if member A and member B have the same group type 1, and
         * if member A comes first in the group, then during the seat allocation,
         * as we traverse from reverse, we first allocate the seat to member B, the bottom seat, and
         * then we allocate the seat to member A, the top seat.
         * We can understand this by the following visual as well:
         * res/level40Module4AlgorithmExercise/countSortResultantArrayPt3.png
         *
         * How?
         * How do we get the original value from the original input array?
         * original element value = originalInputArray[index]
         *
         * Where do we find this value of the original input array in the countArray?
         * As an index in the countArray.
         * As we know, the original value maps as an index in the countArray.
         * And, if we remember the formula, the formula is:
         * original value of the input array = `original value - minValue` index of the countArray.
         * And if we remember, the value of this index (of the countArray) represents cumulative count
         * (cumulative allocated positions/seats) now.
         * Also, we got this cumulative count (cumulative allocated positions/seats) using the occurrences count (repetition).
         * The count system starts with 1 and the index system starts with 0.
         * For example, during the counting of the occurrences of each element, when we find the first occurrence,
         * we placed (assigned) the value 1 for the element and not 0.
         * (Recall the code available a few steps back): `countArray[element - minValue]++`.
         * Here, when we say `++`, it starts with 1.
         * So, if we want to map the cumulative count (cumulative allocated positions/seats) to indices,
         * we subtract the value by 1.
         * We subtract the value by 1, because we want to find the position/seat that is based on the index system,
         * that starts with 0, and not the count system, that starts with 1.
         * The "Subtracting by 1 (The `- 1`)" part here basically converts count into a position/seat that is based on the
         * index system.
         * For example, if we have two elements, placed in a row, one element per index (seat),
         * first element is at the first index, and the second element is at the second index, and if we want to find
         * the index (position, seat) number of the second element, what do we do? We simply subtract by 1, right?
         * Another example, if we have 5 (which is total count) elements,
         * and we want to place each element at a particular index,
         * one element per index, then what will be the index (position, seat) number of the last element?
         * As we know, that the index system starts with 0, and the count system starts with 1, so we simply
         * subtract the count 5 by 1, which gives us the index number 4, for the last element.
         * So basically, index position = count value - 1.
         * Thus, the target (resultant) index position = countArray[original element value - minValue] - 1.
         *
         * So now, we have the value and the corresponding target index position.
         *
         * And once we take and allocate a seat (shifting) from the countArray to the resultantArray,
         * we should reduce that from the countArray.
         * So, countArray[original element value - minValue]--
         * So that when we see the reservation chart (occupied seats), we get the correct information of the available
         * seats.
         *
         */
        for (i in array.indices.reversed()) {
            // Take the value from the original array.
            val value = array[i]
            // Take the index position from the countArray.
            val indexPosition = countArray[value - minValue] - 1
            println(": :countSort: reverse travelling: i $i value: $value countArrayValue: ${countArray[value - minValue]} position: $indexPosition")
            // Place the value at the position in the resulting sort array.
            sortedArray[indexPosition] = value
            // Reduce the repetition by 1 as we have already taken 1 occurrence/position.
            countArray[value - minValue]--
        }

        println(sortedArray.toList())
    }

    val array = intArrayOf(-5, -3, -4, -5, 1, 0, 1, 0, 2, 1)
    val input = intArrayOf(-1, 1, -3)
    countSort(array)
}