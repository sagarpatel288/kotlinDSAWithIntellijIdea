package courses.uc.course01algorithmicToolbox.module06DynamicProgramming02

/**
 * # ----------------------- Problem statement -----------------------
 *
 * This is the same problem that we have seen below:
 * src/courses/uc/module06DynamicProgramming02/01knapsackProblem01EitherOr.kt
 *
 * The additional requirement of this problem is: Backtracking.
 *
 * # ----------------------- Prerequisites (Base References) -----------------------
 *
 * We have already seen backtracking examples below:
 * 1. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt
 * 2. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/100longestCommonSequencesOfTwoSequencesWithBacktracking.kt
 *
 * # ----------------------- Backtracking (Reconstruction) Of 0/1 Knapsack Problem -----------------------
 *
 * We know that we use the same formula for backtracking that we might have used to fill the table.
 *
 * Before we recall the formulae(e) of filling the 0/1 knapsack problem table, let us revisit the filled table itself.
 * It will help us understand (associate, correlate, visualize) the formula(e) better.
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 5 	| 5 	|  5 	|
 * | 3 (Weight 8) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 8 	| 9 	|  9 	|
 *
 * Let us also recall a few helpful facts (obvious, logical facts, theory, analysis, observation, pattern, etc.).
 *
 * 1. If we cannot pick up the current item `i` for the current weight `w`, we copy the [i - 1] answer.
 * Because, the [i - 1] is already an optimal solution for `w.`
 * 2. If we pick up the current item `i`, we must calculate the remaining weight. So,
 * ```
 * The remaining weight = Current capacity (w) - weight of the currently selected item (wi).
 * ```
 * And then, we must add the optimal solution of the remaining weight to get the maximum weight (optimal solution)
 * for the current weight. So,
 * ```
 * Maximum value for the current weight = currently selected item's weight + maximum value of the remaining weight
 * ```
 * How do we get the maximum value of the remaining weight?
 * ```
 * Maximum value of the remaining weight = table[i - 1][remaining weight]
 * ```
 * Why do we use [i - 1]?
 * ```
 * table[i - 1] indicates the value without the currently selected item.
 * ```
 * Then, how do we express (consider, convey, represent) the currently selected item?
 * ```
 * items[i - 1] represents currently selected item.
 * ```
 *
 * # ----------------------- The Meaning Of Overall Expression (Formulae) -----------------------
 *
 * Let us recall the formula(e) for filling the knapsack table.
 *
 * ```
 * if (items[i - 1] <= weight) {
 *    table[i][weight] = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
 * } else {
 *    table[i][weight] = table[i - 1][weight]
 * }
 * ```
 *
 * Now, let us understand each line and what each line represents.
 *
 * ```
 * if (items[i - 1] <= weight)
 * ```
 * It conveys that:
 * ```
 * If the currently selected item's weight is less than or equal to the current weight capacity ______
 * ```
 * If the currently selected item's weight is less than or equal to the current weight capacity, we can include the
 * currently selected item for the current weight capacity. Right?
 *
 * How do we express that? That is what we represent inside the `if` condition. Let us understand that part.
 *
 * ```
 * if (items[i - 1] <= weight) {
 *     table[i][weight] = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
 * }
 * ```
 *
 * Let us understand each part:
 *
 * ```
 * table[i][weight] represents (asks) what will be the maximum value if the current maximum weight capacity is w,
 * and the number of items are `i`.
 * ```
 * Now, either we can exclude the current item, or we can include the current item. So, we have two options.
 * And we select the maximum value of these two options.
 *
 * How do we convey (translate) that into the code?
 *
 * Let us do that step-by-step.
 *
 * ```
 * maxOf(exclude current item, include current item)
 * ```
 *
 * How do we convert the statement "exclude current item," into the code?
 *
 * ```
 * If the current item is `i`, then table[i - 1][weight] represents that we did (do) not include the current item.
 * ```
 * So, it becomes:
 *
 * ```
 * table[i][weight] = maxOf(table[i - 1][weight], include the currently selected item)
 * ```
 *
 * Now, how do we convert the statement, "include the currently selected item," into the code?
 *
 * 1. So basically, we add the currently selected item into the previous optimal solution that does not have the current
 * item yet. So, the previous step where we do not have the current item yet.
 * 2. And if the previous step did not have the current item yet, it must exclude the weight of the current item, too.
 * I mean, it is not possible to have the weight of the current item without having the current item. Right?
 * So, we need to exclude the weight of the currently selected item, too. (This is an important step.).
 *
 * ```
 * 1. The currently selected item is: items[i - 1]
 * 2. The previous optimal solution without the current item for the same weight capacity: table[i - 1][?]
 * 3. Exclude the weight of the current item from the previous optimal solution: weight - items[i - 1]
 * 4. Overall expression when we include the current item: items[i - 1] + table[i - 1][weight - items[i - 1]]
 * ```
 * Hence, the expression becomes:
 *
 * ```
 * table[i][weight]
 * = maxOf(exclude current item, include current item)
 * = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
 * ```
 *
 * Now comes the else part:
 *
 * ```
 * if (items[i - 1] <= weight) {
 *    table[i][weight] = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
 * } else {
 *    table[i][weight] = table[i - 1][weight]
 * }
 * ```
 * The else part conveys that:
 *
 * If the weight of the current selected item, which is `items[i - 1]` is greater than the current weight capacity,
 * then obviously, we cannot include the current item.
 *
 * So, in that case, for the current weight capacity, we cannot include the currently selected item.
 * And hence, the maximum (optimal) value for the current weight capacity is the same as it was with the previous item.
 * And it will be the maximum (optimal) value for the current weight capacity.
 *
 * How do we express it in code? The else part.
 *
 * ```
 * else {
 *      table[i][weight] = table[i - 1][weight]
 * }
 * ```
 *
 * Another way (perspective, conclusion, the other way around) to look at the else part is (Vice Versa):
 *
 * ```
 * If the value table[i][weight] is equal to the value table[i - 1][weight],
 * it means that we have not included the current item.
 * ```
 *
 * The else part will be the core (heart) of the backtracking process.
 * Because we want to know whether we have selected a particular item as part of the solution.
 *
 * So, if the value with the currently selected item for the current weight capacity is the same as the value with
 * the previous item for the same current weight capacity, then it means that we have not selected the current item.
 *
 * On the other hand, if we have selected the current item for the current weight, the value includes the weight
 * of the currently selected item.
 *
 * Now, as we know, the last cell gives the final solution of the original problem, and we have already seen
 * previous examples of backtracking.
 *
 * References:
 * 1. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/070editDistanceBacktrackReconstruct.kt
 * 2. src/courses/uc/module05DynamicProgramming/module05ProgrammingAssignment01/100longestCommonSequencesOfTwoSequencesWithBacktracking.kt
 *
 * So, we know that we start from the last cell and move backward.
 * So, we start with the last row i, and then we move to the previous row, i - 1.
 * So basically, we keep reducing the row by 1.
 *
 * Hence, the next step will be to find whether we have included the `i - 1` item or not.
 * And the solution of `i - 1` item must not contain the `i`th item.
 *
 * So, we need to go to `i - 1,` and we need to exclude the `i`th item.
 * Because, now we want to find the optimal (maximum) value without the `i`th item.
 * And when we exclude the currently selected item `i`, we also exclude the weight of the currently selected item
 * from the current weight capacity.
 *
 * So, the weight becomes:
 * ```
 * currentWeight
 * = currentWeight - weight of the currently selected item, which is the part of the solution.
 * = currentWeight - items[i - 1]
 * ```
 * Hence, the row will be [i - 1], and the column will be `[currentWeight - items[i - 1]]`.
 *
 * It means, if we find that we have selected the current item `i` to the solution,
 * to find the next item, we need to go to row `i - 1` (so, we need to reduce the row by 1),
 * and we need to exclude the weight of the currently selected item to find the right column of the weight.
 *
 *
 */
fun main() {

    fun maxShopping(capacity: Int, items: List<Int>) {
        // Container
        val table = Array(items.size + 1) { IntArray(capacity + 1) }
        // Bottom-up iteration
        for (i in 1..items.size) {
            for (weight in 1..capacity) {
                if (items[i - 1] <= weight) {
                    table[i][weight] = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
                } else {
                    table[i][weight] = table[i - 1][weight]
                }
            }
        }
        println(table[items.size][capacity])
        // Backtracking
        val selectedItems = mutableListOf<Int>()
        var currentCapacity = capacity
        // Start with the last row and keep reducing the row by 1.
        for (i in items.size downTo 1) {
            // If the value with this item is different from the value without this item for the same weight capacity,
            // it means that we have included this item.
            if (table[i][currentCapacity] != table[i - 1][currentCapacity]) {
                // Yes, we have included this item as a part of the solution.
                selectedItems.add(items[i - 1])
                // Now, find the optimal value without this item.
                currentCapacity -= items[i - 1]
            }
        }
        println(selectedItems)
    }

    val capacityAndItems = readln().split(" ").map { it.toInt() }
    val items = readln().split(" ").map { it.toInt() }
    maxShopping(capacityAndItems[0], items)
}