package coursera.ucSanDiego.algorithmicToolbox.module06DynamicProgramming02

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
 * Why up to the weight of the currently selected item only instead of 1?
 *
 * Because when the weight is less than the currently selected item,
 * we cannot take the currently selected item anyway.
 *
 * In that case, it is best to use the existing value where the item weight does not exceed the current weight capacity.
 *
 * Stay tuned to understand what the existing value is and other things.
 *
 * Let us understand one step at a time.
 *
 * Let us understand the iteration part first.
 *
 * Let us translate it into code:
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
 * weight capacity of the currently selected item, which is 1.
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
 * The existing value is the same as the optimal value without the currently selected item.
 *
 * If we can include the currently selected item, and want to include the currently selected item:
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
 * The `array[weight]` represents the existing value from the previous row.
 * So, it represents the value without including the currently selected item.
 *
 * The `item + ` indicates that we are including (have included) the currently selected item.
 *
 * And the ` + array[weight - item]` represents the optimal value for the remaining weight that we might get after
 * adding the currently selected item.
 *
 * So, it becomes:
 *
 * ```
 * cell(1, 10)
 * = array[10]
 * = maxOf ( array[10], 1 + array[10 - 1] )
 * = maxOf ( 0 (existing value without adding the current item weight 1), 1 + array [ 9 ] )
 * = maxOf ( 0, 1 + 0 )
 * = 1
 * ```
 * So, the table becomes:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  1 	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Similarly, the entire row will become:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Now, when we start (reach) the item 2 weight 4, it will get the existing values as below:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * The weight iteration starts from 10 and will go up to the currently selected item weight, 4.
 * Current cell(2, 10):
 * ```
 * = cell(2, 10)
 * = array[10]
 * = maxOf ( array[10], 4 + array[10 - 4] )
 * = maxOf ( 1, 4 + array[6] )
 * = maxOf (1, 4 + 1 )
 * = maxOf (1, 5)
 * = 5
 * ```
 *
 * So, the table becomes:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  5 	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Similarly, the entire row (item 2 weight 4) will get the values as below:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 5 	| 5 	|  5 	|
 * | 3 (Weight 8) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Now, when we go to the item 3 weight 8, it will get the existing values as below:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 5 	| 5 	|  5 	|
 *
 * The current cell is cell(3, 10). It will get the value as below:
 *
 * ```
 * cell(3, 10)
 * = array[10]
 * = maxOf ( existing value without current item, current item weight + array[10 - current item weight] )
 * = maxOf ( array[10], 8 + array[10 - 8] )
 * = maxOf ( 5, 8 + array[2] )
 * = maxOf ( 5, 8 + 1 )
 * = maxOf ( 5, 9 )
 * = 9
 * ```
 *
 * So, the table becomes:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 5 	| 5 	|  9 	|
 *
 * Similarly, the entire row will get the values as below:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 1 (Weight 1) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 8 	| 9 	|  9 	|
 *
 *
 * And the answer is:
 * ```
 * = array[capacity]
 * = array[10]
 * = 9
 * ```
 *
 * # ----------------------- Why did we go max to min for the weight iteration? -----------------------
 *
 * Because, if we go from min to max, and when we want to add the optimal value for the remaining weight,
 * What we get is the optimal value, including the current item, and not excluding the current item.
 *
 * For example, let us say the maximum weight capacity of the knapsack is 10,
 * and the weights are 4, 1, 8.
 *
 * Now, the cell(1, 4..7) will have the values 4.
 * The container gets the default value, 0.
 * And when we reach the weight 8, the formula:
 * ```
 * cell(1, 8)
 * = array[8]
 * = maxOf (array[8], 4 + array[8 - 4])
 * ```
 * Now, the part: ```array[8 - 4]``` represents the value for the current item, 4.
 * And it is, 4. So, the answer becomes 4 + 4 = 8, which is wrong, because:
 *
 * 1. We cannot repeat the same item weight!
 *
 * So, when we start from min to max weight iteration,
 * when we try to add the remaining weight or the previous weight,
 * we get the value of the current weight,
 * and it leads to the repetition, which is not allowed in the problem.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * ## ----------------------- Time Complexity Analysis -----------------------
 *
 * We use two for loops, one inside another.
 * A weight loop inside the item loop.
 * ```
 * So, the time complexity is: O(number of items * maximum weight capacity of the knapsack)
 * = O(n * W)
 * ```
 *
 * ## ----------------------- Space Complexity Analysis -----------------------
 *
 * We use one 1D array of size maximum weight capacity of the knapsack + 1.
 * We ignore constants.
 * ```
 * So, the space complexity is:
 * = O(Maximum weight capacity of the knapsack)
 * = O(W)
 * ```
 */
fun main() {

    fun maxShopping(capacity: Int, items: List<Int>) {
        // Container
        val container = IntArray(capacity + 1)
        for (item in items) {
            for (weight in capacity downTo item) {
                container[weight] = maxOf(container[weight], item + container[weight - item])
            }
        }
        println(container[capacity])
    }

    val capacityAndItems = readln().split(" ").map { it.toInt() }
    val items = readln().split(" ").map { it.toInt() }
    maxShopping(capacityAndItems[0], items)
}