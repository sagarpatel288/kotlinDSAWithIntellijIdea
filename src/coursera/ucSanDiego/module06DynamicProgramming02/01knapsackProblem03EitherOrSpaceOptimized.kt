package coursera.ucSanDiego.module06DynamicProgramming02

/**
 * # ----------------------- Prerequisites (Base References) -----------------------
 *
 * The solution with backtracking:
 * 1. src/coursera/ucSanDiego/module06DynamicProgramming02/01knapsackProblem02EitherOrWithBacktracking.kt
 *
 * The pictorial presentation of the space-optimized solution:
 * res/coursera/ucSanDiego/module06DynamicProgramming02/02knapsack01_either_or_without_repetition/01knapsack_01_gold_bars_space_optimized.png
 *
 * # ----------------------- Explanation -----------------------
 *
 * 1. The space-optimized solution of the 0/1 knapsack problem (Bounded, And Without Repetition) is
 * another way (a bit different perspective, slightly different approach) to solve the same problem.
 *
 * The important (critical) observation (pattern) of this approach is,
 * we take a single 1D array to solve the problem.
 *
 * Based on the backtracking solution of the same problem, we already know that,
 * we use the values from the previous row.
 *
 * 2. The second difference is, we start bottom-up for the item rows, but top-down (max to min) for the weight columns.
 *
 * Let us visualize this. Please find the below initial setup.
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|  0 	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * We take a single 1D array, and we know that when there is no item, regardless of the weight capacity,
 * each cell will have the value 0.
 *
 * Also, as we mentioned earlier, we start the item iteration as usual, bottom-up, but,
 * for the weights, we iterate from top-down (max to min), up to the weight of the currently selected item.
 *
 * Let us translate it into the code:
 *
 * ```
 * We start the item iteration as usual, bottom-up
 * = for (item in items)
 * ```
 * And
 * ```
 * But, for the weights, we iterate from top-down (max to min), up to the weight of the currently selected item.
 * = for (weight in capacity downTo item)
 * ```
 *
 * So, we start when the weight is 10, and continue till the weight becomes equal to the weight capacity of the
 * currently selected item.
 *
 * The currently selected item is 0. So, we start from the maximum weight capacity 10, and iterate till the weight
 * becomes 0.
 *
 * Just because there is no item, each cell will have the value 0.
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Now, we take the first item.
 * Again, we start from the maximum weight capacity 10, and iterate till the weight capacity becomes equal to the
 * weight capacity of the currently selected item, which is 4.
 *
 * Let us see how it looks:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Let us understand it.
 * We use the same 1D array, that we have used when there was no item.
 * So, the currently selected item 1 (Weight 1) gets the same values as per the previous row.
 *
 * Let us make it clear.
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Now, we start from the maximum weight capacity, 10.
 * So, the cell is cell(1, 10).
 * The possibility is: Either it can include the currently selected item, or not.
 *
 * If it cannot include the currently selected item, then we don't change the existing value.
 * The existing value is the same as an optimal value without the currently selected item.
 *
 * We can include the currently selected item only if the weight of the currently selected item is less than or equal
 * to the current weight capacity.
 *
 * Let us code this particular condition:
 *
 * Current item:
 *
 * ```
 * for (item in items) {
 *     for (weight in capacity downTo item) {
 *         current item is: `item` in items.
 *     }
 * }
 * ```
 *
 * The weight of the currently selected item:
 *
 * ```
 * The weight of the currently selected item
 * = item itself represents its weight only!
 * = item
 * ```
 * So,
 *
 * ```
 * We can include the currently selected item only if the weight of the currently selected item is less than or equal
 * to the current weight capacity.
 * = if (item <= weight)
 * ```
 *
 * Let us code from the beginning to understand it properly:
 *
 * ```
 * for (item in items) {
 *     for (weight in capacity downTo item) {
 *         if (item <= weight) {
 *            The weight of the currently selected item is less than or equal to the current weight capacity.
 *            We can either include the currently selected item, or not.
 *         }
 *     }
 * }
 * ```
 *
 * If we include the item, we need to consider (calculate) the optimal solution for the remaining weight that we might
 * get after including the current item.
 *
 * The thing is, the existing array also contains the optimal solution for the remaining weight that we might get
 * after including the current item.
 *
 * How?
 *
 * Let us translate the explanation into the code.
 *
 * ```
 * if we include the currently selected item,
 * = Consider that we are including the currently selected item,
 * = Currently selected item,
 * = item
 * = 1 (Weight 1)
 * ```
 *
 * Let us code from the beginning:
 *
 * ```
 * for (item in items) {
 *     for (weight in capacity downTo item) {
 *         if (item <= weight) {
 *            array[weight] = maxOf(array[weight], item + array[weight - item])
 *         }
 *     }
 * }
 * ```
 *
 * The `array[weight]` represents the value from the previous row.
 * So, it represents the value without including the currently selected item.
 *
 * The `item + ` indicates that we are including (have included) the currently selected item.
 *
 * And the ` + array[weight - item]` represents the optimal value for the remaining weight that we might get after
 * adding the currently selected item.
 *
 */
fun main() {

    fun maxShopping(capacity: Int, items: List<Int>) {

    }

    val capacityAndItems = readln().split(" ").map { it.toInt() }
    val items = readln().split(" ").map { it.toInt() }
    maxShopping(capacityAndItems[0], items)
}