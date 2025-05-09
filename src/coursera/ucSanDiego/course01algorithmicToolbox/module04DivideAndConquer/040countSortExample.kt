package coursera.ucSanDiego.course01algorithmicToolbox.module04DivideAndConquer

fun main() {

    /**
     * Explain the count sort algorithm with an example or sort the following array using the counting sort algorithm.
     * A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1].
     *
     * OR:
     *
     * Sort the given array (input) where:
     * 1.
     * The growth rate between the maximum element and size of the input grows at less than quadratic rate.
     * That is, the difference between the maximum element and the input size is less than quadratic.
     * 2.
     * We want to maintain the runtime complexity to O(n + k) in best, average, and even in worst-case,
     * where n is the number of elements in the input array,
     * and k is the difference between the maximum value and the minimum value of the input array.
     * Also, k does not increase significantly larger (quadratic) than the size of the input array.
     * 3.
     * We want to maintain the space complexity to not exceed O(n).
     *
     * OR:
     *
     * Sort the given input (array) using a non-comparison based algorithm.
     *
     * Sagar: Let me tell you a story!
     *
     *
     * A few kids wanted to watch a movie in a theater. The total number of kids was close to the age of the oldest child.
     * The manager asked the guard to find both the youngest and the oldest child, and then arrange all the kids by age,
     * ensuring that the youngest would get a seat first and the oldest would get a seat last.
     * The manager also wanted to respect and maintain the order in which the kids purchased their tickets.
     * For example, if there are two kids, A and B, of the same age, and kid A purchased the ticket first,
     * then kid A would take priority over kid B.
     *
     * Now, the guard asked all the kids to come together, close to one another.
     * Then, the guard picked up the first kid and gave him two labels: Youngest and Oldest.
     * Later, the guard went through all the kids one by one and compared each kid with the selected one.
     * Every time the guard found a younger kid, he would take the Youngest label from the older kid and
     * give it to the new kid, who was younger than the older one.
     *
     * For example, let us assume that the first kid is A. Kid A has both the Youngest and the Oldest labels.
     * Now, the guard iterates through all the kids.
     * Let us assume that the second kid is B.
     * The guard compares A and B.
     * If the guard finds that B is younger than A, he would take the Youngest label from kid A and give it to kid B.
     *
     * Similarly, if the guard finds an older kid, he would take the Oldest label from the older kid and
     * give it to the new kid who is older than the previous one.
     *
     * At the end of this process, the guard identifies the youngest and the oldest kids.
     *
     * So, the guard found the two boundaries: the youngest age and the oldest age.
     *
     * Now, the guard writes all the numbers between the youngest kid’s age and the oldest kid’s age on a wall,
     * including both the youngest and oldest kids' ages.
     * Each number on the wall represents a column where multiple kids can fit.
     *
     * Then, he asks each kid to stand under the number of their age written on the wall.
     * He ensured that if multiple kids were under the same column due to having the same age,
     * the child who purchased the ticket first would go first.
     *
     * Now, he needs to allocate seats to each kid.
     *
     * He starts a cumulative count.
     * That is, he writes the total number of kids in the first column on the floor.
     * As he moves to the second column, he adds the previous number,
     * which is the total number of kids in the first column, to the total number of kids in the second column.
     *
     * For example, if there are 3 kids in the first column, he would write 3 on the floor in front of the first column.
     * If there are 2 kids in the second column, he would write 3 + 2 = 5 on the floor in front of the second column.
     *
     * Let us visualize this before we write the cumulative count.
     * Imagine we have two columns.
     * The first column has the number 3 in front of it, indicating that there are a total of 3 kids in the column,
     * and all the kids under this column are of the same age.
     * The kid who purchased the ticket first, goes first, followed by the second kid, and then the third kid.
     * The guard writes the number 3 in front of the column, representing the next available seat number to be assigned.
     *
     * Now, let us visualize the second column, which has the number 2 in front of it.
     * This means there are a total of 2 kids in the column, and all the kids under it will be of the same age.
     * The first kid who purchased the ticket goes first, followed by the second kid.
     *
     * The guard now writes a cumulative count in front of each column on the floor.
     * Therefore, the first column retains the same number, 3, in front of it.
     * However, the second column now has a total of 3 + 2 = 5.
     *
     * Now, let us understand how this works.
     * The number in front of each column represents the next available seat number to be assigned.
     *
     * We start from the last column, the second column at the moment.
     *
     * The 5th kid, who is at the top of the second column, receives the 5th seat.
     * As we allocate the seat, we reduce the number, making the number in front of the column on the floor now 4.
     * Next, the 4th kid gets seat number 4.
     * All the kids from the second column have received their seats, so we move on to the first column.
     *
     * For the first column, the third kid, who is at the top of this column, receives seat number 3.
     * After allocating this seat, we reduce the number again, so the number in front of the column is now 2.
     * The second kid then gets seat number 2, and we reduce the number again, bringing it down to 1.
     * Finally, the first kid receives seat number 1.
     *
     * With such a plan in mind, the guard continues to write the cumulative count in front of each column on the floor
     * until all the columns have been accounted for.
     *
     * The cumulative count represents the seat numbers for each kid in the theater, starting from the youngest.
     * Essentially, it tells the guard in which order the kids should enter the theater to get their seats.
     *
     * Now that the cumulative count is complete, it's time to start assigning seats.
     *
     * The guard calls each kid, starting from the oldest one, to ensure they all line up correctly based on their age.
     * This means he starts with the kids in the last column and moves towards the first column.
     *
     * For each kid, he checks the number written on the floor in front of their age column,
     * which tells him the seat number the kid should take.
     * After assigning the seat, he reduces the number written on the floor by one.
     * This way, he ensures that each kid gets a unique seat number and no seat is left unassigned.
     *
     * For example, let us assume that there are 4 kids of age 9.
     * Initially, the guard wrote the number 12 in front of the column for age 9,
     * meaning the next available seat number for age 9 is 12.
     * The guard assigns the first kid of age 9 to seat 12, and then reduces the number in front of the column to 11,
     * so that the next kid of age 9 will get seat 11.
     *
     * The guard continues this process until all the kids are assigned seats,
     * making sure that the youngest kids get the front seats and the oldest kids get the seats at the back.
     *
     * By the end of this exercise, all the kids are seated in the correct order from the youngest to the oldest,
     * and the guard successfully accomplishes the manager's requests.
     *
     * Thus, the kids are sorted based on their ages, and they all enjoy the movie!
     *
     * ----------------- Key Points --------------------
     *
     * Finding Youngest and Oldest:
     * The guard iterated through all the kids to find out who the youngest and oldest were,
     * similar to how we determine the range (minimum and maximum values) in the counting sort algorithm.
     *
     * Age Columns:
     * Writing numbers on the wall to represent the age columns was like creating an array to keep track of the count
     * of each element in the counting sort algorithm. It is our count array.
     * Each column in the story represents an index of the count array.
     * We will fill in the values for each index in the next step.
     *
     * Standing Under The Age Columns:
     * When each kid stood under the number representing their age,
     * it represented counting the occurrences of each value in the input array.
     * Earlier, in the previous step, we prepared empty indices without any values for the count array.
     * This moment in the story represents how we fill in the values for each index.
     *
     * Cumulative Count:
     * Writing cumulative counts on the floor in front of each column helped determine the seat number for each kid.
     * This step is analogous to calculating the cumulative count to find the correct positions for elements.
     *
     * Assigning Seats:
     * Assigning seats to the kids by iterating from the oldest age group to the youngest age group ensured that
     * each kid was placed in the correct position.
     * This is similar to placing elements in the correct sorted position in counting sort,
     * where we iterate in reverse to maintain stability and avoid overwriting.
     *
     * This story represents the counting sort process in a relatable and visual way,
     * showing how each step corresponds to the operations in the algorithm.
     *
     * ------------ Technical Story----------------
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
     * 1. Find boundaries (Min and Max)
     * 2. Create a count array (Range/Size: max - min + 1)
     * 3. Store occurrences / repetition with normalization: countArray[element - min]++
     * 4. Store a cumulative count: countArray[i] = countArray[i - 1] + countArray[i] => from 1..<countArray.size
     * 5. Traverse reverse: input.indices.reversed(), take original value from input[i],
     * find target index position using the countArray as countArray[value - min] - 1,
     * resultArray[target index position] = value, and then mark the seat as allocated by countArray[value - min]--
     *
     * When to use?
     * It is an efficient sorting algorithm for sorting integers when the range of values (minimum to maximum)
     * is not significantly larger than the number of elements to be sorted.
     *
     * For example, if we have an array A[3, 1, 2, 3, 2, 1, 4, 3], we can see that the range of values are
     * 1 to 4, and it is not significantly larger than the number of elements (= size of the array = n) that is 8.
     *
     * Pros:
     * The time complexity of the counting sort algorithm is almost linear, i.e., O(n)
     * that is lower than the other fastest comparison based sorting algorithm such as a merge sort algorithm
     * whose time complexity is slightly higher, i.e., O(n log n).
     *
     * Cons:
     * To use the counting sort algorithm, the range of values, especially the maximum value element
     * must not grow faster (or larger) than the size of the input array.
     *
     * For example, if we have an array A[3, 1, 2, 3, 2, 1, 4, 3], we can see that the range of values are 1 to 4
     * where the maximum value element is 4, and it is not significantly larger than the number of elements
     * (= size of the array = n) that is 8. Here, we can use the counting sort algorithm.
     *
     * However, if we have an array of size 100 (= n) and the maximum value is 100,00 (= n raised to the power of 2),
     * then the max value here grows faster than the size of the input array as we increase the maximum value
     * while keeping the size of the array the same.
     * Here, we better use the merge sort algorithm than the counting sort algorithm.
     *
     * Time Complexity:
     *
     * O(n + k): The counting sort algorithm runs in linear time,
     * where n is the number of elements in the input array and
     * k is the range of input values, also known as the difference between the maximum and the minimum value of the
     * input array or the size of the count array.
     *
     * O(n) when we iterate through the original input array to build the count array, and then
     * O(k) when we iterate through the count array to store the cumulative count.
     *
     * The added complexity of handling negative numbers does not affect the overall time complexity.
     *
     * Also, we drop the constants when we provide the Big-O value. That is why we drop the O(n) runtime complexity
     * of the reversed iteration on the original input array.
     *
     * Note that, it is not a nested for loop. It is a separate for loop. That's why it has its own O(n) runtime
     * complexity.
     *
     * So, instead of O(2n + k), we provide O(n + k).
     *
     * Space Complexity:
     *
     * O(k) to store the count array and O(n) to create a new and sorted array.
     * We take these two major and dominant factors, and ignore space complexity of other small and temporary variables.
     * So, the space complexity of the count sort algorithm is O(n + k).
     *
     */
    fun countSort(array: IntArray) {

        /**
         * If the size of the input array is less than or equal to 1, print the input array and return.
         * We print the input as an indication that the array which has only one element, is already sorted!
         * There is only 1 kid to watch the movie! We don't need to establish a sorting order in that case!
         */
        if (array.size <= 1) {
            println(array.toList())
            return
        }

        /**
         * This is when the guard gives the `Youngest` label to the first kid.
         *
         * First, we find the `minValue` and the `maxValue`. Why?
         * We find the `minValue` and the `maxValue` to get the range.
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the minValue is -5.
         *
         * Complexity:
         * The space complexity of this variable is: O(1).
         */
        var minValue = array[0]

        /**
         * This is when the guard gives the `Oldest` label to the first kid.
         *
         * We find the `minValue` and the `maxValue`. Why?
         * We find the `minValue` and the `maxValue` to get the range.
         * For our example, [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1], the maxValue is 2.
         *
         * Complexity:
         * The space complexity of this variable is O(1).
         */
        var maxValue = array[0]

        /**
         * This is when the guard compares each kid with the selected kid
         * and transfers the `Youngest` label if he finds the younger kid
         * and transfers the `Oldest` label when he finds the older kid.
         *
         * With a single iteration, we find both the [minValue] and the [maxValue].
         * If we use `array.minOrNull()` and `array.maxOrNull()`, then we iterate the input array two times.
         *
         * Complexity:
         * The time complexity of this operation is O(n).
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
         * If [minValue] and [maxValue] are equal,
         * It means that all the elements in the given input array hold the same value.
         * In that case, we don't need to do anything.
         * We can say that the given input array is already sorted.
         *
         */
        if (minValue == maxValue) {
            println(": :countSort: ${array.toList()}")
            return
        }

        /**
         * This is when the guard draws columns on the wall.
         *
         * Based on the `minValue` and `maxValue`, we get a range (size).
         * Based on the size, we create a countArray.
         *
         * Why do we create the countArray?
         * We need a count array where we can store the occurrences (repetition) count.
         * In our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * -5 comes 2 times, -3 comes 1 time, -4 comes 1 time, 1 comes 3 times, 0 comes 2 times, and 2 comes once.
         * So, what we do is, we take these values (we refer to them as keys actually as they can internally represent
         * actual values), i.e., [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] and create equivalent indices.
         * So, the array will have these [-5, -3, -4, -5, 1, 0, 1, 0, 2, 1] indices.
         * It means, we will have [-5, -4, -3, -2, -1, 0, 1, 2] indices.
         * So, the count array will have a total of 7 indices, and a size of 8.
         * We know that the indices gets ascending order sequence, and it cannot be a negative value.
         * To solve that, we will use normalization. But first, we need to find the size of the count array.
         * And then we store respective (associated, corresponding) occurrences count for each index.
         *
         * For example, the index -5 gets the value 2 as it comes 2 times. It means, -5 will have a value as 2.
         * the index -3 gets the value 1 as it comes 1 time, the index -4 gets the value 1 as it comes once,
         * the index 1 gets the value 3 as it comes 3 times, the index 0 gets the value 2 as it comes twice,
         * and the index 2 gets the value 1 as it comes once.
         *
         * The indices will be a range starting from the minValue -5 and goes up to the maxValue 2.
         * To arrange these indices in a particular order, i.e., in ascending order,
         * we use maxValue and minValue to get the required range of indices.
         *
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
         *
         * Complexity:
         * The space complexity of this variable is O(k) where k = max - min + 1 = the size of the countArray.
         * Note that, this is not the size of the original input array. The original input array can have independent
         * duplicate values across different indices, and it can also miss many values between the max and the min.
         * For example: inputArray[-3, 3, -3, 3] => size = 4.
         * However, the countArray gets the range of values, starting from the min to the max.
         * The countArray covers all the values between and including the max and the min.
         * For the same example: countArray[-3, -2, -1, 0, 1, 2, 3] => size = 7.
         * Hence, the size of both original input array and countArray can grow independently.
         * It depends on these two values: max and min.
         */
        val countArray = IntArray(maxValue - minValue + 1)


        /**
         * This is when the kids move into their respective columns based on their age in the story.
         *
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
         * While doing this, we also need to consider negative numbers and normalize it.
         * To normalize a negative number means to make it a positive number, and we do it by subtracting the minimum
         * value from all the elements.
         * We subtract each and every element by `minValue`. So, it does not change the size of the count array.
         * But, Why do we normalize?
         * Because, we will be using indices to represent the actual elements and indices cannot be negative.
         * These indices will have a value that would represent the number of occurrence count (repetition)
         * for each (corresponding, associated) element.
         *
         * Example, please?
         * For our example, A[-5, -3, -4, -5, 1, 0, 1, 0, 2, 1],
         * The first element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0 => Occurs once.
         * So, countArray[0]++ => countArray[0] = Value 1.
         * It means, we have the element (key, value, or whatever you call it) 0 once.
         * As we can see, -5 is index 0 after the normalization, and
         * it has the value 1 which means, it has occurred once.
         * Thus, we convert actual element (-5) into an index (0). So, the corresponding (associated) index for the
         * element (-5) is index (0) (i.e., the index 0 represents the element -5) and an index cannot be a negative
         * value. So, we use normalization.
         * Here, the original value (-5) is the `original value - minValue` = `-5 - (-5)` = 0th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The second element: -3 - (minValue) = -3 - (-5) = -3 + 5 = Index 2. => Occurs once.
         * So, countArray[2]++ => countArray[2] = Value 1.
         * It means, we have the element 2 once.
         * As we can see, -3 is index 2 after the normalization, and it has the value 1 which means,
         * it has occurred once.
         * So, we converted the actual element (-3) into an index (2) and it has the value 1.
         * The index (2) represents the element (-3).
         * Here, the original value (-3) is the `original value - minValue` = `-3 - (-5)` = 2nd index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The third element: -4 - (minValue) = -4 - (-5) = -4 + 5 = Index 1. => Occurs once.
         * So, countArray[1]++ => countArray[1] = Value 1.
         * Here, we converted the actual element (-4) into an index (1) and it has the value 1.
         * I.e., So far, it (the element "-4" = index 1) has occurred once.
         * The index (1) represents the element (-4).
         * Here, the original value (-4) is the `original value - minValue` = `-4 - (-5)` = 1st index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The fourth element: -5 - (minValue) = -5 - (-5) = -5 + 5 = Index 0. => Occurs twice.
         * So, countArray[0]++ => countArray[0] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (-5) into an index (0) and it has the value 2.
         * I.e., So far, it (the element "-5" = index 0) has occurred twice.
         * The index (0) represents the element (-5).
         * Here, the original value (-5) is the `original value - minValue` = `-5 - (-5)` = 0th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The fifth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs once.
         * So, countArray[6]++ => countArray[6] = Value 1.
         * Here, we converted the actual element (1) into an index (6) and it has the value 1.
         * I.e., So far, it (the element "1" = index 6) has occurred once.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The sixth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs once.
         * So, countArray[5]++ => countArray[5] = Value 1.
         * Here, we converted the actual element (0) into an index (5) and it has the value 1.
         * I.e., So far, it (the element "0" = index 5) has occurred once.
         * The index (5) represents the element (0).
         * Here, the original value (0) is the `original value - minValue` = `0 - (-5)` = 5th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The seventh element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs twice.
         * So, countArray[6]++ => countArray[6] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (1) into an index (6) and it has the value 2.
         * I.e., So far, it (the element "1" = index 6) has occurred twice.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The eighth element: 0 - (minValue) = 0 - (-5) = 0 + 5 = Index 5. => Occurs twice.
         * So, countArray[5]++ => countArray[5] = 1 (existed) + 1 = Value 2.
         * Here, we converted the actual element (0) into an index (5) and it has the value 2.
         * I.e., So far, it (the element "0" = index 5) has occurred twice.
         * The index (5) represents the element (0).
         * Here, the original value (0) is the `original value - minValue` = `0 - (-5)` = 5th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The ninth element: 2 - (minValue) = 2 - (-5) = 2 + 5 = Index 7. => Occurs once.
         * So, countArray[7]++ => countArray[7] = Value 1.
         * Here, we converted the actual element (2) into an index (7) and it has the value 1.
         * I.e., So far, it (the element "2" = index 7) has occurred once.
         * The index (7) represents the element (2).
         * Here, the original value (2) is the `original value - minValue` = `2 - (-5)` = 7th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * The tenth element: 1 - (minValue) = 1 - (-5) = 1 + 5 = Index 6. => Occurs thrice.
         * So, countArray[6]++ => countArray[6] = 2 (existed) + 1 = Value 3.
         * Here, we converted the actual element (1) into an index (6) and it has the value 3.
         * I.e., So far, it (the element "1" = index 6) has occurred thrice.
         * The index (6) represents the element (1).
         * Here, the original value (1) is the `original value - minValue` = `1 - (-5)` = 6th index of the count array.
         * This formula helps to understand the mapping between the countArray and the original input array during de-normalization.
         *
         * Key-point, Conclusion:
         * `original value` of the input array maps to `value - minValue` of the countArray as an index.
         * The value of the index of the countArray represents the count (repetition, occurrences).
         * Later, if we want to find the occurrence count (repetition) of a particular value,
         * and we have the original value, we can use the above formula to first get the corresponding (associated)
         * index (that maps the original value) of the countArray, and then, we can take the value of that particular index
         * of the countArray that represents the occurrence count (repetition).
         * This formula helps to understand the mapping between the countArray and the original input array
         * during normalization and de-normalization.
         *
         * Complexity:
         * The time complexity of this step is: O(n).
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
         * This is when the guard writes cumulative number in front of each column.
         *
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
         * Let us understand this with an example that we can visualize.
         * We have multiple groups to watch a movie.
         * Each group can have multiple members and each member can occupy one seat.
         * So, for example,
         * if the first group (first index) has 2 members and the second group (second index) has 3 members,
         * when we do cumulative count, we set the value 5 on the second group, and it conveys that,
         * by the time we place both the first and the second groups, we have already assigned 5 seats.
         * It conveys that, 5 seats are occupied.
         * This information helps us to understand from which seat the third group will start.
         *
         * Another example: Imagine you have a line of people waiting to buy movie tickets.
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
         *
         * Complexity Analysis:
         * We travel according to the size of the [countArray].
         * We cannot denote the size of the [countArray] by `n` as we have already taken it for the input array.
         * So, if the size of the [countArray] is k, where k = max - min + 1,
         * then the time complexity of this step is: O(k).
         *
         */
        for (i in 1..<countArray.size) {
            println(": :countSort: shifting positions: i: $i countArray[i]: ${countArray[i]} i-1: ${countArray[i - 1]}")
            countArray[i] = countArray[i - 1] + countArray[i]
        }

        println(": :countSort: countArray after position shifting: ${countArray.toList()}")

        /**
         * This is the theater or the seats in the story.
         *
         * Take the value from the original array and position from the count array.
         *
         * Complexity:
         * The space complexity of this step is: O(n), where n is the size of the original input array.
         */
        val sortedArray = IntArray(array.size)

        /**
         * This is when the guard calls each kid, starting from the oldest column.
         * This is when the guard starts iterating from the last column and allocates seats.
         *
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
         * Complexity:
         * The time complexity of this step is: O(n).
         *
         * Total (overall) complexity of the whole count sort algorithm:
         *
         * Time complexity:
         * O(n) to decide min and max
         * + O(n) counting the occurrences with normalization
         * + O(k) cumulative count
         * + O(n) allocating seats, preparing the final sorted resultant array
         * = 3-Big-O(n) + O(k)
         * = O(n + k) because:
         * 1. We drop the constants (it is 3 here) in Big-O notation.
         * 2. n and k grows independently as we have seen that while declaring the count array,
         * where n is the size of the original input array and k is the size of the count array.
         *
         * Hence, the overall time complexity of the entire count sort algorithm is: O(n + k).
         * Similarly, the overall space complexity of the entire count sort algorithm is: O(n + k).
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
            // This represents the guard reducing the cumulative number after allocating the seat in the story.
            countArray[value - minValue]--
        }

        println(sortedArray.toList())
    }

    val array = intArrayOf(-5, -3, -4, -5, 1, 0, 1, 0, 2, 1)
    val input = intArrayOf(-1, 1, -3)
    countSort(array)
}