package level40Module4AlgorithmExercise

fun main() {

    /**
     * Explain the count sort algorithm with an example or sort the following array using the counting sort algorithm.
     * A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1].
     *
     * The count sort is a non-comparison based algorithm to sort the given input.
     * We use the count sort algorithm when the maximum value element of the given input,
     * does not grow faster than the size of the input.
     * Here, we find the maximum value element and create a count array starting from the minimum value element up to
     * the maximum value element.
     * Then, we store the frequency (repetition) count for each element.
     * Based on the frequency, we adjust the position and finally provide the sorted array.
     * Let us consider an example of: A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1].
     * The minValue is -5 and maxValue is 2. The size of the array is 10.
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
         * If the minValue is null, print the input and return (complete the function here).
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the minValue is -5.
         */
        val minValue = array.minOrNull() ?: return println(array.toList())

        /**
         * If the maxValue is null, print the input and return (complete the function here).
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the maxValue is 2.
         */
        val maxValue = array.maxOrNull() ?: return println(array.toList())

        println(": :countSort: maxValue: $maxValue minValue: $minValue")


        /**
         * We need a count array where we can store the frequency (repetition) count.
         * In our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * -5 comes 2 times, -3 comes 1 time, -4 comes 1 time, 1 comes 3 times, 0 comes 2 times, and 2 comes once.
         * So, what we do is, we take these values (we refer to them as keys actually as they can internally represent
         * actual values), i.e., [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] and create equivalent indices.
         * So, the array will have these [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] indices.
         * It means, we will have [-5, -4, -3, -2, -1, 0, 1, 2] indices.
         * So, the count array will have a total of 7 indices, and a size of 8.
         * We know that the indices gets ascending order sequence, and it cannot be a negative value.
         * To solve that, we will use normalisation. But first, we need to find the size of the count array.
         * And then we store respective (associated, corresponding) frequency count for each index.
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
         * Count frequency of repetition for each element for the input array and store it to the count array.
         * We iterate through the original array, access each element, and increase the corresponding count.
         * While doing this, we also need to consider negative numbers and normalise it.
         * To normalise a negative number means to make it a positive number, and we do it by subtracting the minimum
         * value from all the elements.
         * Why do we normalise? Because, we will be using indices to represent the actual elements and
         * then these indices will have a value that would represent the number of count (frequency, repetition)
         * for each (corresponding, associated) element.
         * For our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * The first element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0 => Occurs once.
         * So, countArray[0]++ => countArray[0] = Value 1.
         * It means, we have the element (key, value, or whatever you call it) 0 once.
         * As we can see, -5 is index 0 after the normalisation, and
         * it has the value 1 which means, it has occurred once.
         * At the time of de-normalisation, we would add the minValue to get the actual element value.
         * So, it will be 0 + minValue = 0 + (-5) = -5 once.
         * However, for now, it will be 0 once.
         * Thus, we convert actual element (-5) into an index (0). So, the corresponding (associated) index for the
         * element (-5) is index (0) (i.e., the index 0 represents the element -5) and an index cannot be a negative
         * value. So, we use normalisation.
         *
         * The second element: -3 - (minValue) = -3 - (-5) = -3 + 5 = Index 2. => Occurs once.
         * So, countArray[2]++ => countArray[2] = Value 1.
         * It means, we have the element 2 once.
         * As we can see, -3 is index 2 after the normalisation, and it has the value 1 which means,
         * it has occurred once.
         * Again, we will get the actual value at the time of de-normalisation.
         * So, during the de-normalisation, we will add the `minValue` and it will be: 2 + (-5) = -3 once.
         * However, for now, it will be 2 once.
         * So, we converted the actual element (-3) into an index (2) and it has the value 1.
         * The index (2) represents the element (-3).
         *
         * The third element: -4 - (minValue) = -4 - (-5) = -4 + 5 = Index 1. => Occurs once.
         * So, countArray[1]++ => countArray[1] = Value 1.
         * Here, we converted the actual element (-4) into an index (1) and it has the value 1.
         * I.e., So far, it (the element "-4" = index 1) has occurred once.
         * The index (1) represents the element (-4).
         *
         * The fourth element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0. => Occurs twice.
         * So, countArray[0]++ => countArray[0] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (-5) into an index (0) and it has the value 2.
         * I.e., So far, it (the element "-5" = index 0) has occurred twice.
         * The index (0) represents the element (-5).
         *
         * The fifth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs once.
         * So, countArray[6]++ => countArray[6] = Value 1.
         * Here, we converted the actual element (1) into an index (6) and it has the value 1.
         * I.e., So far, it (the element "1" = index 6) has occurred once.
         * The index (6) represents the element (1).
         *
         * The sixth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs once.
         * So, countArray[5]++ => countArray[5] = Value 1.
         * Here, we converted the actual element (0) into an index (5) and it has the value 1.
         * I.e., So far, it (the element "0" = index 5) has occurred once.
         * The index (5) represents the element (0).
         *
         * The seventh element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs twice.
         * So, countArray[6]++ => countArray[6] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (1) into an index (6) and it has the value 2.
         * I.e., So far, it (the element "1" = index 6) has occurred twice.
         * The index (6) represents the element (1).
         *
         * The eighth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs twice.
         * So, countArray[5]++ => countArray[5] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (0) into an index (5) and it has the value 2.
         * I.e., So far, it (the element "0" = index 5) has occurred twice.
         * The index (5) represents the element (0).
         *
         * The ninth element: 2 - (minValue) = 2 - (-5) = 2 + 5 = Index 7. => Occurs once.
         * So, countArray[7]++ => countArray[7] = Value 1.
         * Here, we converted the actual element (2) into an index (7) and it has the value 1.
         * I.e., So far, it (the element "2" = index 7) has occurred once.
         * The index (7) represents the element (2).
         *
         * The tenth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs thrice.
         * So, countArray[6]++ => countArray[6] = 2 (existed) + 1 = Value 3.
         * Here, we converted the actual element (1) into an index (6) and it has the value 3.
         * I.e., So far, it (the element "1" = index 6) has occurred thrice.
         * The index (6) represents the element (1).
         */
        for (number in array) {
            println(": :countSort: input number: $number minValue: $minValue: number - minValue: ${number - minValue}")
            countArray[number - minValue]++
        }

        println(": :countSort: countArray: ${countArray.toList()}")

        /**
         * Shift and assign the positions.
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
         * Start taking the values from the right side of the original array.
         */
        for (i in array.indices.reversed()) {
            // Take the value from the original array.
            val value = array[i]
            // Take the position from the countArray.
            val position = countArray[value - minValue] - 1
            println(": :countSort: reverse travelling: i $i value: $value position: $position")
            // Reduce the repetition by 1 as we have already taken 1 occurrence.
            countArray[value - minValue]--
            // Place the value at the position in the resulting sort array.
            sortedArray[position] = value
        }

        println(array.toList())
    }

    val array = intArrayOf(-5, -3, -4, -5, 1, 0, 1, 0, 2, 1)
    countSort(array)
}