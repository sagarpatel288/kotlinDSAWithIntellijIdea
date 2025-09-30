package courses.uc.course01algorithmicToolbox.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
 *
 * Prerequisites to understand the current problem:
 * 1. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt
 * 2. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/100longestCommonSequencesOfTwoSequencesWithBacktracking.kt
 * 3. src/courses/uc/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt
 *
 * References of the current problem:
 * 1. res/courses/uc/module06DynamicProgramming02/02equalSumPartition/02equalSumPartition.png
 * 2. res/courses/uc/module06DynamicProgramming02/02equalSumPartition/03equalSumPartitionMathSimplification.png
 *
 * 3-Partition Problem:
 *
 * Partition a set of integers into three subsets with equal sums.
 *
 * Input:
 *
 * A sequence of integers v_1,v_2,...,v_n.
 *
 * Output:
 *
 * Check whether it is possible to partition them into three subsets with equal sums, i.e.,
 * check whether there exist three disjoint sets S1,S2,S3 ⊆ {1,2,...,n}
 * such that S1 ∪ S2 ∪ S3 = {1,2,...,n}
 * and
 *
 * ```
 *  __                        __                        __
 * \                  V   =  \                  V   =  \                  V
 * /__ i epsilon  S    i     /__ i epsilon  S    j     /__ i epsilon  S    k
 *                 1                         2                         3
 * ```
 *
 * How did I use mathematical expressions, and greek symbols?
 * 1. Use [An online latex editor](https://www.sciweavers.org/free-online-latex-equation-editor#).
 *
 *      1.1 We can select an image, adjust the symbols, syntax, notation, values, etc.
 *      1.2 Generate the image.
 *      1.3 We also get a code-comment!
 *
 * 2. Use OCR, paste the text here, adjust the format, space, indents, etc., cover it inside the code format, etc.
 *
 * ```
 *
 *   ∑      V_i     =       ∑      V_j      =       ∑      V_k
 * i ∈ S_1                i ∈ S_2                 i ∈ S_3
 *
 * ```
 *
 * Three pirates are splitting their loot consisting of n items of varying value.
 * Can you help them to evenly split the loot?
 *
 * Input format:
 *
 * The first line contains an integer n. The second line contains integers v1,v2,...,vn separated by spaces.
 *
 * Output format:
 *
 * Output 1, if it is possible to partition v1,v2,...,vn into three subsets with equal sums, and 0 otherwise.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 20, 1 ≤ vi ≤ 30 for all i.
 *
 * Sample 1:
 *
 * Input:
 *
 * 4
 *
 * 3 3 3 3
 *
 * Output:
 *
 * 0
 *
 * Sample 2:
 *
 * Input:
 *
 * 1
 *
 * 30
 *
 * Output:
 *
 * 0
 *
 * Sample 3:
 *
 * Input:
 *
 * 13
 *
 * 1 2 3 4 5 5 7 7 8 10 12 19 25
 *
 * Output:
 *
 * 1
 *
 * Because: 1 + 3 + 7 + 25 = 2 + 4 + 5 + 7 + 8 + 10 = 5 + 12 + 19.
 *
 * # ----------------------- Explanation, Thought Process, Code Translation, Etc. -----------------------
 *
 * ```
 * Catch Point-1: Partition a set of integers + into three subsets + with equal sums
 *
 * Catch Point-2: Input: A sequence of integers v_1,v_2,...,v_n.
 *
 * Catch Point-3: Check whether it is possible to partition them into + three subsets + with equal sums, i.e.,
 * ```
 *
 * How do we check whether it is possible to partition the given integers into three subsets with equal sums?
 *
 * What does that mean even?
 *
 * ```
 * Point-01: We have a list of integers.
 * Point-02: We need to split these integers into three subsets (three separate lists, containers).
 * Point-03: with equal sums = such that the sum of elements of each subset should be equal.
 * ```
 *
 * ## ----------------------- Key-lemma: Math Magic -----------------------
 *
 * We can divide (split) the given integers into 3 subsets such that the sum of elements of each subset is
 * equal, only if we can completely divide (no remainder, exact/perfect division) the sum of the given integers by 3.
 *
 * If we cannot completely divide (no remainder, exact/perfect division) the total sum of the given integers by 3,
 * we cannot split the given integers into 3 subsets with equal sums.
 *
 * For example, let us assume that we have the following integers:
 *
 * 1 3 4
 *
 * Now, the sum of these integers is: 8
 * If we divide 8 by 3, we get certain remainder.
 * So, it is not an exact (perfect, whole) division.
 * Hence, we cannot split these given integers into 3 subsets with equal sums.
 *
 * Now, let us take another example. Let us assume that we have the following integers:
 *
 * ```
 * 10 5 8 2 7 4
 * ```
 *
 * The sum of the given integers is: 36
 * If we divide 36 by 3, we get no remainder.
 * So, it is an exact (perfect) division.
 * Hence, maybe (Repeat: Maybe) we can split these integers into 3 subsets with equal sums.
 *
 * How?
 *
 * ```
 * [10, 2], [5, 7], [8, 4]
 * ```
 *
 * So, remember the first and primary condition:
 *
 * ```
 * If we want to split the given integers into 3 subsets with equal sum,
 * the sum of the given integers must be an exact (perfect, whole, no remainder) division by 3.
 * ```
 *
 * So, we can translate the first and primary condition into the below code:
 *
 * ```
 * if (totalSum % 3 == 0) must be true.
 * ```
 *
 * In other words,
 *
 * ```
 * if (totalSum % 3 != 0) we can return false from here. We can declare that it is impossible to split the given
 * integers into 3 subsets with equal sums. So, no need to proceed further.
 * ```
 *
 * However, please note that the primary condition alone is not enough to split the given integers into 3 subsets
 * with equal sums.
 *
 * Why? How?
 *
 * Let us take an example. Let us assume that we have the following integers:
 *
 * 2, 7, 3
 *
 * Now, the sum of the given integers is 12.
 * 12 (the total sum) is an exact (perfect, whole, complete) division by 3. We get no remainder.
 * However, we cannot split the given integers into 3 subsets with equal sums.
 *
 * For example, let us assume that the 3 subsets are:
 *
 * [2], [7], [3]
 *
 * Now, the sum of all the elements of subset 1 is S_1 = 2.
 * Similarly, the sum of all the elements of subset 2 is S_2 = 7.
 * And finally, the sum of all the elements of subset 3 S_3 = is 3.
 *
 * We can see that S_1 ≠ S_2 ≠ S_3.
 *
 * ```
 * So, we need to make sure that the sum of the elements of each subset should be the same (equal).
 * ```
 *
 * But before we make the sum of the elements of each subset, how do we assign (assort, select, and store) the elements
 * into each subset? How do we split the integers?
 *
 * Let us revisit our example.
 *
 * The integers are:
 *
 * ```
 * 10 5 8 2 7 4
 * ```
 *
 * The sum of the integers is:
 *
 * ```
 * 36
 * ```
 *
 * The sum of the integers is perfectly (exactly, whole, completely) divisible by 3 without any remainder.
 *
 * ```
 * 36 % 3 == 0
 * ```
 *
 * So, we conclude that maybe (why maybe? We have already answered it a few lines back!) we can split these integers
 * into 3 subsets with equal sums.
 *
 * _**How do we split the integers into each subset?**_
 *
 * We do what we usually do in a dynamic programming. That is:
 *
 * ```
 * We try each option.
 * ```
 *
 * But before we try each option, we need to decide:
 *
 * _**What will be the sum of all the elements of each subset?**_
 *
 * The answer is:
 *
 * ```
 * The quotient.
 * ```
 *
 * Yes, the quotient. Let us revisit our example.
 *
 * The integers are:
 *
 * ```
 * 10 5 8 2 7 4
 * ```
 *
 * The sum of the integers is:
 *
 * ```
 * 36
 * ```
 *
 * The sum of the integers is perfectly divisible by 3.
 *
 * ```
 * 36 % 3 == 0
 * ```
 *
 * And what do we get when we divide the sum of the integers by 3?
 *
 * ```
 * 36 / 3 = 12 (target sum of each subset)
 * ```
 *
 * So, we need to make sure that the sum of the elements of each subset must be exactly 12.
 *
 * _**How do we make it sure that the sum of the elements of each subset must be equal to the target sum?**_
 *
 * ```
 * We try each option.
 * ```
 *
 * _**How?**_
 *
 * We iterate through each integer, try various combinations, and store it into each subset,
 * until we find that the sum of the elements is equal to the `target sum.`
 *
 * So, we try different combinations of integers for each subset,
 * and once we find that a particular combination makes the sum of the elements of a particular subset equal to the
 * target sum, we are done with that subset. So, we change the subset.
 *
 * We try each option. Hence, we will use the tabulation approach (Bottom-up Dynamic Programming).
 *
 * Let us understand this with our example.
 *
 * The integers are:
 *
 * ```
 * 10 5 8 2 7 4
 * ```
 *
 * So, first we use 10. We add it to the first subset.
 *
 * ```
 * [10]
 * ```
 *
 * The sum of the elements of this subset is less than the target sum.
 * Hence, we can add a different element now.
 *
 * ```
 * [10, 5]
 * ```
 *
 * But it exceeds the target sum. So, we remove it.
 *
 * ```
 * [10]
 * ```
 *
 * Now, we add another integer.
 *
 * ```
 * [10, 8]
 * ```
 *
 * Again, it exceeds the target sum. So, we remove it.
 *
 * ```
 * [10]
 * ```
 *
 * Again, we try another integer.
 *
 * ```
 * [10, 2]
 * ```
 *
 * This time, the sum of the elements of this subset is equal to the target sum.
 * Hence, we can move on to create (fill) the second subset now.
 *
 * We again start with 10.
 *
 * ```
 * [10]
 * ```
 *
 * But, the integer `10` is already a part of the first subset.
 * Can we add `10` to another subset as well?
 *
 * Let us check (focus, observe, notice, catch) the remaining part of the problem statement.
 *
 * ```
 * Catch Point-4: check whether there exist three disjoint sets S1,S2,S3 ⊆ {1,2,...,n}
 *
 * Catch Point-5: such that S1 ∪ S2 ∪ S3 = {1,2,...,n}
 *
 * Catch Point-6: And
 *
 *
 *   ∑      V_i     =       ∑      V_j      =       ∑      V_k
 * i ∈ S_1                i ∈ S_2                 i ∈ S_3
 *
 *
 * Catch Point-7: Output 1, if it is possible to partition v1,v2,...,vn into three subsets with equal sums,
 * and 0 otherwise.
 * ```
 *
 * The phrase _**disjoint sets**_ indicates that no element of any subset should be repeated in other subset.
 * In other words, if a particular integer exists in one subset, it should not exist in another subset.
 *
 * Hence, we cannot use `10` for other subsets. Because, we have already used it for the first subset.
 *
 * So, how do we track whether a particular integer is already selected and exist in one subset?
 *
 * We can use a container of boolean where we can store the selected integer.
 *
 * How?
 *
 * We will use a boolean array (container) having the same size of the given integers.
 * Now, the value of each index of the boolean array will convey that whether the value of the same index of the given
 * integers has been used for a particular subset or not.
 *
 * Let us understand this with an example.
 *
 * The given integers are:
 *
 * ```
 * 10 5 8 2 7 4
 * ```
 *
 * The size of the given integers is:
 *
 * ```
 * 6
 * ```
 *
 * We will take a boolean array (container) of the same size.
 *
 * ```
 * val selected = BooleanArray(integers.size)
 * ```
 *
 * The value of each index (true or false) of the `selected` boolean array conveys that,
 * whether the value (integer) of the same index of the integers has been used (selected) or not for a particular subset.
 *
 * An example will help us understand the same. So, let us understand it with an example.
 *
 * Example:
 *
 * ```
 * [false, false, false, false, false, false] indicates that none of the integers has been selected so far.
 *
 * [true, false, false, false, false, false] indicates that out of [10, 5, 8, 2, 7, 4], the value at index 0,
 * that is the value `10` has been selected for a particular subset.
 *
 * [true, false, false, true, false, false] indicates that out of [10, 5, 8, 2, 7, 4], the value at index 0 and 3,
 * that are the values `10` and `3` have been selected for a particular subset.
 * ```
 *
 * Now, we know that, as per the problem statement, if we have selected a particular integer for a particular subset,
 * we should not use the same integer for any other subset.
 *
 * That means, before we consider any integer for a particular subset, we must check:
 *
 * ```
 * !selected[integer item]
 * ```
 *
 * Ok. But, how do we select the integers in the first place for each subset?
 *
 * ```
 * We need to try each option and different combinations to ensure that the sum of each element of a particular
 * subset equals to the target sum. Because, the given input integers are not sorted.
 * ```
 *
 * Whenever we need to try each option and select the best (optimal, max, min, etc.) way to reach target,
 * we use the tabulation approach.
 *
 * References:
 * 1. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt
 * 2. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/100longestCommonSequencesOfTwoSequencesWithBacktracking.kt
 * 3. src/courses/uc/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt
 *
 * And if we observe the above references, we can see that the last cell represents the answer.
 *
 * Also, if we recall the previous (recent) problem, the 0/1 bounded knapsack problem with backtracking
 * (Reference: src/courses/uc/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt),
 * we can relate it with the current problem.
 *
 * How?
 *
 * ```
 * 1. The maximum weight capacity of a knapsack = The target sum of each subset.
 * 2. The items of the 0/1 knapsack problem = The integers of the current problem.
 * ```
 *
 * It means, we need a 2D-container (a table) where the columns would represent the target sum,
 * and the rows would represent the integers.
 *
 * Now, for our example, where the input is `[10, 5, 8, 2, 7, 4]`,
 * the `total sum` is `36,`
 * and the `target sum` is `12,`
 * the table looks like below:
 *
 *
 * |   	| i / j 	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	| 11 	| 12 	|
 * |---	|:-----:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|:--:	|:--:	|
 * | 0 	|   0   	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|  0 	|  0 	|
 * | 1 	|   10  	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 * | 2 	|   5   	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 * | 3 	|   8   	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 * | 4 	|   2   	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 * | 5 	|   7   	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 * | 6 	|   4   	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|    	|    	|
 *
 * Let us understand the table.
 *
 * The cell(0, 0) = (row 0, column 0) conveys that the integer is `0,` and the target sum is also `0`.
 * The cell(1, 1) = (row 1, column 1) conveys that the integer is `10,` and the target sum is `1.`
 * The cell(1, 10) = (row 1, column 10) conveys that the integer is `10,` and the target sum is also `10.`
 * The cell (1, 12) = (row 1, column 12) conveys that the integer is `10,` and the target sum is `12.`
 * The cell(2, 10) = (row 2, column 10) conveys that the integers are `10,` and `5,` and the target sum is `10.`
 *
 * And the last cell:
 * The cell(6, 12) = (row 6, column 12) indicates that the integers are `10,` `5,` `8,` `2,` `7,` and `4,`
 * and the target sum is `12.`
 *
 * It means that if the last cell gets the same value as the target sum,
 * it must have used a proper combination of the given integers.
 *
 * And we have already seen how to find this combination.
 * (Reference: The backtracking part of the:
 * src/courses/uc/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt)
 *
 * As we find the integers we have used for a particular subset,
 * we would mark such integers as selected.
 *
 * How do we find which integers have we used?
 *
 * Using the backtracking process.
 * (Reference: src/courses/uc/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt)
 *
 * During the backtracking process, as we find the selected integers,
 * we would update the `selected` boolean array.
 *
 * We find the integer we have used for a particular subset.
 * We find the index of the integer.
 * We mark the same index as `true` in our `selected` boolean.
 * It will convey that the integer value of the same index in the integers array has been used.
 *
 * We do the same for each subset, and we would use (consider, select) only unselected integers.
 *
 * However, we still have a problem even with the tabulation approach. Let us see the problem.
 *
 * Problem:
 *
 * Let us assume that we have the following input:
 *
 * ```
 * 6
 *
 * 3 4 6 9 7 10
 * ```
 *
 * Here, we can see that:
 *
 * ```
 * Total sum = 39
 * Target sum = 39/3 = 13
 * ```
 *
 * Now, even the tabulation approach would give:
 *
 * ```
 * subset 1 = 3 + 4 + 6 = 13 = target sum.
 * subset 2 = 10 ≠ target sum.
 * subset 3 = 9 ≠ target sum.
 * subset ≠ subset ≠ subset.
 * answer = 0.
 * ```
 *
 * Why does that happen?
 *
 * Because, we thought that the k partitions with equal sum problem is similar to the 0/1 knapsack problem.
 * So, we used the same approach.
 *
 * Let us understand how the "k partitions with equal sums" problem is slightly different from the 0/1 knapsack problem.
 *
 * In the 0/1 knapsack problem, we have only 1 knapsack,
 * and we want to fill maximum weight (or profits, values) that does not exceed the knapsack capacity.
 *
 * In the k partitions with equal sum problem, we have multiple knapsacks.
 *
 * For example,
 *
 * ```
 * Point-01: 3 partitions with equal sum has 3 knapsacks. Similarly, k partitions with equal sum has k partitions.
 * Point-02: The maximum capacity of each knapsack is the same (target sum).
 * Point-03: All the knapsack should be filled with maximum weight that does not exceed the knapsack capacity.
 * ```
 * Things are getting more differences from here.
 * ```
 * Point-04: The total weight of each filled knapsack must be equal to the knapsack capacity.
 * Point-05: The total weight of each filled knapsack must be equal.
 * Point-06: No item should be left unselected.
 * ```
 *
 * Now, let us take a look at our table when the input is: 3 4 6 9 7 10.
 *
 * |   	| i / j 	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	| 11 	| 12 	| 13 	|
 * |---	|:-----:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|:--:	|:--:	|:--:	|
 * | 0 	|   0   	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|  0 	|  0 	|  0 	|
 * | 1 	|   3   	| 0 	| 0 	| 0 	| 3 	| 3 	| 3 	| 3 	| 3 	| 3 	| 3 	|  3 	|  3 	|  3 	|  3 	|
 * | 2 	|   4   	| 0 	| 0 	| 0 	| 3 	| 4 	| 4 	| 4 	| 7 	| 7 	| 7 	|  7 	|  7 	|  7 	|  7 	|
 * | 3 	|   6   	| 0 	| 0 	| 0 	| 3 	| 4 	| 4 	| 4 	| 7 	| 7 	| 9 	| 10 	| 10 	| 10 	| 13 	|
 * | 4 	|   9   	| 0 	| 0 	| 0 	| 3 	| 4 	| 4 	| 4 	| 7 	| 7 	| 9 	| 10 	| 10 	| 12 	| 13 	|
 * | 5 	|   7   	| 0 	| 0 	| 0 	| 3 	| 4 	| 4 	| 4 	| 7 	| 7 	| 9 	| 10 	| 11 	| 12 	| 13 	|
 * | 6 	|   10  	| 0 	| 0 	| 0 	| 3 	| 4 	| 4 	| 4 	| 7 	| 7 	| 9 	| 10 	| 11 	| 12 	| 13 	|
 *
 * The problem is, it is still greedy! Let me elaborate more on this.
 *
 * The tabulation approach selects the items [3, 4, 6] for the first subset.
 * The remaining subsets are left with the items [9, 7, 10] making it impossible for the remaining subsets to get the
 * equal sum of 13.
 *
 * However, if we look at the example carefully, it is possible to have 3 partitions with equal sums as below:
 *
 * ```
 * [3, 10], [4, 9], [6, 7]
 * ```
 *
 * So, what is the solution?
 *
 * ```
 * We must select elements for a particular subset in such a way that the remaining elements can form the remaining
 * subsets!
 * ```
 *
 * It means that we need to try multiple solutions!
 * And for each solution, we need to check whether we can get the solutions for the remaining subsets or not.
 * Because, we cannot select a combination of integers that would make the solution for other subsets impossible.
 *
 * What do we do then?
 *
 * Every time we pick up an element (the integer) for a particular subset, we need to check:
 *
 * ```
 * Will it be possible to form the k partitions with equal sums with the remaining elements?
 * Only if it is true, we proceed further (take the next element). Otherwise, we discard the last selection.
 * ```
 *
 * In other words,
 *
 * ```
 * The result (selection, combination) of one subset affects other subsets.
 * ```
 *
 * Hence,
 *
 * ```
 * Before we complete and finalize the selection for the ongoing (current) subset,
 * we need to check the result (consequences) of the other subsets with the remaining elements.
 * ```
 *
 * Also, there is no linear, and straightforward way to logical build up the solution for each subset where we can use
 * the result of previous subset to fill the current subset, and so on.
 * Because, there can be multiple combinations spread and split across different indices.
 *
 * All these theoretical arguments is to highlight the major characteristics of the problem that suggest (encourage)
 * us to use the `Recursion` approach instead of the `Tabulation` approach.
 *
 * So now, if we are convinced that we need to use recursion, let us define the base case first.
 *
 * We know that the recursion approach must have a base case from which the recursion can return.
 *
 * What can be that base case in this problem?
 *
 * ```
 * 1. If the current sum of the current subset equals the target sum, we are done.
 * 2. OR if we are tracking the current index, and it reaches the end, before the current sum for the current subset
 * can be equal to the target sum, we are done.
 * ```
 *
 * Now, let us assume that we have the input as below:
 *
 * ```
 * 6
 *
 * 3 4 6 9 7 10
 * ```
 *
 * And let us assume that one subset has selected one element: 3.
 * So, the question is: Will it be possible to form 3 subsets with the remaining elements?
 *
 * Similarly, let us assume that at one point, one subset becomes: [3, 10]
 * So, the question becomes: Will it be possible to form 2 subsets with the remaining elements?
 * Notice that we decreased the initial k by 1.
 *
 * The important observation here is:
 *
 * ```
 * The number of subset requirement starts with the maximum (target number of subset).
 * And once we select and finalize a few elements for a particular subset,
 * the number of subset requirement is reduced (decreased) to k - 1 with the remaining elements.
 * ```
 *
 * Also, if we have successfully formed `k - 1` partitions,
 * then the remaining elements must form the last remaining partition.
 * Because, we have started the process only after confirming that:
 *
 * point-01: `totalSum % k == 0,`
 * point-02: By the time we are left with only one subset to form, math says it will be true.
 *
 * Why? How?
 *
 * Let us revisit the example.
 *
 * The input is:
 *
 * ```
 * 6
 *
 * 3 4 6 9 7 10
 * ```
 *
 * Initially, the total number of subset requirement is `k = 3.`
 *
 * Now, according to our progress where only 1 subset is remained,
 * we have successfully formed `k - 1` subsets as below:
 *
 * ```
 * [3, 10], [4, 9]
 * ```
 *
 * The remaining elements automatically forms the remaining `k - 1` subset as below:
 *
 * ```
 * [6, 7]
 * ```
 *
 * So, we can add this to our base condition as below:
 *
 * ```
 * if k = 1 return true. Because, we must have already checked that whether or not it will be possible
 * to form a valid subset when k = 1, before we reached k = 1, at the time of finalizing k = 2.
 * ```
 *
 * Now, when (how) do we finalize a subset?
 *
 * ```
 * When the `current sum` becomes equal to the `target sum` for a particular subset.
 *
 * Once that happens, we decrease k by 1.
 * That is to say, we change the number of subset requirement for the remaining elements.
 * Because, we have already selected a few elements as a part of the current subset.
 * And according to the problem statement, we cannot repeat the same elements in multiple subsets.
 * ```
 *
 * What are the parameters do we need to track?
 *
 * ```
 * 1. We try each element. So, definitely the index.
 * 2. We start with the maximum number of subset requirements and reduce it to 1. So, definitely the number of subset
 * requirement k for the remaining elements.
 * 3. We reduce the number of subset requirement by 1 once the `current sum` of the current subset becomes equal
 * to the `target sum.` So, definitely the `Current sum.`
 * ```
 *
 * So, the function can be:
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int)
 * ```
 *
 * What will be the return type?
 *
 * ```
 * The following question:
 * "Will it be possible to form the k partitions with equal sums with the remaining elements?"
 * clearly suggests the return type to be "Boolean."
 * ```
 *
 * So, the function header becomes:
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean
 * ```
 *
 * Let us add the base cases to the function body.
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
 *     // if the number of subset requirement is 1, return true.
 *     // Because, at this stage, we must have confirmed that the remaining elements can form the last remaining subset.
 *     if (subsets == 1) return true
 *
 *     // if the `currentSum` is equal to the `targetSum`, we are done with the "current number of subset requirements."
 *     // Hence, reduce the number of subset requirement by 1 and repeat the process.
 *     // We will need to check all the elements from the beginning (0th index),
 *     // and the `currentSum` will also reset to 0 for the upcoming (next) subset.
 *     if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)
 *
 *     // After the base cases, here comes the actual logic, the core part,
 *     // where we try each element for the current number of subset requirement.
 * }
 * ```
 *
 * How do we try each element? Using a loop.
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
 *     // if the number of subset requirement is 1, return true.
 *     // Because, at this stage, we must have confirmed that the remaining elements can form the last remaining subset.
 *     if (subsets == 1) return true
 *
 *     // if the `currentSum` is equal to the `targetSum`, we are done with the "current number of subset requirements."
 *     // Hence, reduce the number of subset requirement by 1 and repeat the process.
 *     // We will need to check all the elements from the beginning (0th index),
 *     // and the `currentSum` will also reset to 0 for the upcoming (next) subset.
 *     if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)
 *
 *     // After the base cases, here comes the actual logic, the core part,
 *     // where we try each element for the current number of subset requirement.
 *    for (i in startIndex..<values.size) {
 *        // Before we select and finalize an element for the current subset,
 *        // we need to check something.
 *    }
 * }
 * ```
 *
 * Before we finalize (confirm, select) any element, what will be the condition? What do we need to check?
 *
 * 1. How do we check whether the element has already been selected for any subset or not? Using the boolean array.
 * 2. If we add the value of the current element to the `currentSum,` the result must be less than or equal to the
 * `targetSum.`
 *
 * ```
 * 1. The element must not be selected (locked).
 * 2. If we select the current element, it will add the value to the `currentSum.`
 * The resultant `currentSum` must be equal to or less than the `targetSum.`
 * ```
 *
 * So, the function becomes:
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
 *     // if the number of subset requirement is 1, return true.
 *     // Because, at this stage, we must have confirmed that the remaining elements can form the last remaining subset.
 *     if (subsets == 1) return true
 *
 *     // if the `currentSum` is equal to the `targetSum`, we are done with the "current number of subset requirements."
 *     // Hence, reduce the number of subset requirement by 1 and repeat the process.
 *     // We will need to check all the elements from the beginning (0th index),
 *     // and the `currentSum` will also reset to 0 for the upcoming (next) subset.
 *     if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)
 *
 *     // After the base cases, here comes the actual logic, the core part,
 *     // where we try each element for the current number of subset requirement.
 *    for (i in startIndex..<values.size) {
 *        // Before we select and finalize an element for the current subset,
 *        // we need to check 2 things.
 *        if (!selected[i] && currentSum + values[i] <= targetSum) {
 *           // Finalize (lock, select) the element for the current subset.
 *           selected[i] = true
 *           // Now, select the next index for the current subset and
 *           // pass the current sum argument value as `currentSum + values[i]`.
 *           // if that returns true, decrease the number of subset requirement.
 *        }
 *    }
 * }
 * ```
 *
 * How do we determine whether selecting the current element is an optimal solution or not?
 *
 * If we select (consider, lock) the current element as a part of the current subset,
 * will the remaining elements can form the remaining subsets?
 *
 * ```
 * if selecting (considering) the current element as a part of the current subset is the right decision,
 * the remaining elements will form the remaining subsets.
 * Otherwise, deselect (unlock) the element.
 * ```
 *
 * 1. The `remaining elements` start with selected `index i + 1`.
 * 2. The number of subset requirement remains as it is at this point.
 * We change it only when the `currentSum` becomes equal to the `targetSum.`
 * Hence, no change in the number of subset requirement at this moment.
 * 3. Selecting the current element increases the `currentSum,` as: `currentSum + values[i]`
 * 4. Deselect (unlock) the element = `selected[i] = false`.
 *
 * This translates to the below code:
 *
 * ```
 * if (canPartition(i + 1, subsets, currentSum + values[i]) return true
 * selected[i] = false
 * ```
 *
 * Hence, the function becomes:
 *
 * ```
 * fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
 *     // if the number of subset requirement is 1, return true.
 *     // Because, at this stage, we must have confirmed that the remaining elements can form the last remaining subset.
 *     if (subsets == 1) return true
 *
 *     // if the `currentSum` is equal to the `targetSum`, we are done with the "current number of subset requirements."
 *     // Hence, reduce the number of subset requirement by 1 and repeat the process.
 *     // We will need to check all the elements from the beginning (0th index),
 *     // and the `currentSum` will also reset to 0 for the upcoming (next) subset.
 *     if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)
 *
 *     // After the base cases, here comes the actual logic, the core part,
 *     // where we try each element for the current number of subset requirement.
 *    for (i in startIndex..<values.size) {
 *        // Before we select and finalize an element for the current subset,
 *        // we need to check 2 things.
 *        if (!selected[i] && currentSum + values[i] <= targetSum) {
 *           // Finalize (lock, select) the element for the current subset.
 *           selected[i] = true
 *           // Now, select the next index for the current subset and
 *           // pass the current sum argument value as `currentSum + values[i]`.
 *           // if that returns true, decrease the number of subset requirements.
 *           if (canPartition(i + 1, subsets, currentSum + values[i]) return true
 *           selected[i] = false
 *        }
 *    }
 * }
 * ```
 *
 * What will be the default return type of the function?
 *
 * Let us see.
 *
 * Before we return a default value, we try all the configurations.
 * And if we successfully form and set all the valid configurations, we return true.
 *
 * If we have tried all the combinations, and could not form the required configuration,
 * we declare that the given input values does not form valid k partitions with equal sums.
 *
 * ```
 * Declare that the given input values does not form valid k partitions with equal sums.
 * ```
 *
 * This explanation suggests (translates to) the default value to be `false`.
 *
 * Other observations:
 *
 * Point-1. We need and use a few values inside the recursive function `canPartition,`
 * which are `targetSum,` and `selected` boolean array. It means, we need to define these values outside.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * ## ----------------------- Time Complexity Analysis -----------------------
 *
 * Reference image:
 * res/courses/uc/module06DynamicProgramming02/02equalSumPartition/05_kPartitionsWithEqualSumRecursionComplexityTree.png
 *
 * Explanation:
 *
 * 1. Initially, we have only 1 element, and for each element, we have 2 choices: Either we select it or not.
 * It means, at level 1 = Depth is 1 => 1 element = 2 possibilities = 2 operations.
 * We can write 2 operations as 2^1 where the exponent 1 indicates the number of elements to consider.
 *
 * 2. Similarly, at level 2 = Depth is 2 => 2 elements = 4 possibilities = 4 operations.
 * We can write 4 operations as 2^2 where the exponent 2 indicates the number of elements to consider.
 *
 * 3. Then, at level 3 = Depth is 3 and there are 3 elements = 8 possibilities = 8 operations.
 * We can write 8 operations as 2^3 where the exponent 3 indicates the number of elements to consider.
 *
 * 4. The pattern suggests that we will have n levels where n is the number of elements.
 * The pattern also suggests that at level n, the number of operations will be 2^n.
 * If we consider the number of operations at each level, sum up them, and take the dominant term,
 * it turns out to be 2^n when the number of elements to consider is n.
 *
 * 5. Hence, the time complexity of the K-Partitions with Equal Sum using recursion is O(2^n).
 *
 * ## ----------------------- Space Complexity -----------------------
 *
 * Other than small variables, the maximum memory we use is by taking the boolean array of the input size.
 * Also, the recursion stack is directly proportional to the number of elements, n.
 * Hence, the space complexity is O(n).
 *
 * # ----------------------- Grader Output -----------------------
 *
 * Good job! (Max time used: 0.09/4.00, max memory used: 43331584/536870912.)
 *
 */
fun main() {
    // This function attempts to partition the array into three subsets with equal sum.
    fun canPartitionIntoThreeSubsets(values: IntArray): Int {
        // Calculate the total sum of all elements in the array.
        val totalSum = values.sum()

        // If the total sum is not divisible by 3, partitioning into three equal subsets is impossible.
        if (totalSum % 3 != 0) return 0

        // Calculate the target sum for each subset.
        val targetSum = totalSum / 3

        // Boolean array to track whether an element has been used in a subset.
        val selected = BooleanArray(values.size) { false }

        // Helper function to recursively attempt partitioning into subsets.
        fun canPartition(startIndex: Int, subsets: Int, currentSum: Int): Boolean {
            // If only one subset is left to fill, it means the remaining elements naturally form the last subset.
            if (subsets == 1) return true

            // If the current subset reaches the target sum, start forming the next subset.
            if (currentSum == targetSum) return canPartition(0, subsets - 1, 0)

            // Try to add each unvisited element to the current subset.
            for (i in startIndex..<values.size) {
                println("Subset: $subsets current sum: $currentSum selected: ${selected.toList()}")
                // Check if the element can be added without exceeding the target sum.
                if (!selected[i] && currentSum + values[i] <= targetSum) {
                    // Mark the element as visited.
                    selected[i] = true

                    // Wait. Stay here. Check the future.
                    // Is selecting the `i` the right decision?
                    // Yes (return true), if the remaining elements can form valid subsets.
                    // Otherwise, deselect the `i`. We will continue the iteration for(i in startIndex..<values.size).
                    // Recursively attempt to form the current subset with the remaining elements (i + 1).
                    if (canPartition(i + 1, subsets, currentSum + values[i])) return true

                    // Backtrack: unmark the element and try the next possibility.
                    selected[i] = false
                }
            }

            // Return false if no valid subset configuration is found.
            return false
        }

        // Start the partitioning process with 3 subsets, an initial sum of 0, and starting index 0.
        return if (canPartition(0, 3, 0)) 1 else 0
    }

    // Read the number of integers (not actually used in the function).
    val totalIntegers = readln().toInt()

    // Read the list of integers and convert them into an IntArray.
    val integers = readln().split(" ").map { it.toInt() }

    // Call the partition function and store the result.
    val answer = canPartitionIntoThreeSubsets(integers.toIntArray())

    // Print the result: 1 if partitioning is possible, otherwise 0.
    println(answer)
}
