package coursera.ucSanDiego.module06DynamicProgramming02

/**
 * # ----------------------- Problem Statement -----------------------
 *
 * Maximum Amount of Gold Problem:
 * (Generally known as: The 0/1 Knapsack problem. Either we pick up the entire item, or we don't pick up the item at all.)
 *
 * Given a set of gold bars of various weights and a backpack that can hold at most W pounds,
 * place as much gold as possible into the backpack.
 *
 * Input:
 *
 * A set of n gold bars of integer weights w1,...,wn and a backpack that can hold at most W pounds.
 *
 * Output:
 *
 * A subset of gold bars of maximum total weight not exceeding W.
 *
 * You found a set of gold bars and your goal is to pack as much gold as possible into your backpack,
 * that has capacity W, i.e., it may hold at most W pounds.
 *
 * There is just one copy of each bar and for each bar you can either take it or not
 * (you cannot take a fraction of a bar).
 *
 * Although all bars appear to be identical in the figure above, their weights vary as illustrated in the figure below.
 *
 * res/coursera/ucSanDiego/module06DynamicProgramming02/02knapsack01_either_or_without_repetition/0_2_knapsack_problem_0_1_either_or_without_repetition.png
 *
 * A natural greedy strategy is to grab the heaviest bar that still fits into the remaining capacity of the backpack,
 * and iterate.
 *
 * For the set of bars shown above and a backpack of capacity 20, the greedy algorithm would select gold bars
 * of weights 10 and 9. But an optimal solution, containing bars of weights 4, 6, and 10, has a larger weight!
 *
 * Input format:
 *
 * The first line of the input contains an integer W (capacity of the backpack) and the number nof gold bars.
 *
 * The next line contains n integers w1,...,wn defining the weights of the gold bars.
 *
 * Output format:
 *
 * The maximum weight of gold bars that fits into a backpack of capacity W.
 *
 * Constraints:
 *
 * 1 ≤ W ≤ 10^4;
 * 1 ≤ n ≤ 300;
 * 0 ≤ w1,...,wn ≤ 10^5.
 *
 * Sample:
 *
 * Input:
 *
 * 10 3
 *
 * 1 4 8
 *
 * Output:
 *
 * 9
 *
 * The sum of the weights of the first and the last bar is equal to 9.
 *
 * # ----------------------- References Of Pre-requisites -----------------------
 *
 * 1. Dynamic Programming.
 * So, all the problems of the directory/package:
 * src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01
 *
 * # ----------------------- Base Explanation: Base Thought Process -----------------------
 *
 * ## ---------------------- Why does the Greedy approach fail? How? ----------------------
 *
 * Greedy Approach Vs. Dynamic Programming:
 *
 * Recall the history (Recapitulation, Flash back):
 * src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/010coinChangeUsingDynamicProgramming.kt
 *
 * If the target amount to change is 24, and coin denominations are 1, 8, and 20.
 * The greedy approach would take 1 coin of 20, and 4 coins of 1, resulting in a total of 5 coins.
 * However, the dynamic programming would take 3 coins of 8, resulting in a total of only 3 coins.
 *
 * Coming back to the present problem (Present):
 *
 * Let us understand that with a simple example.
 * Let us assume that the maximum weight capacity of the knapsack is 10.
 * And we have 4 items with weight: 3, 4, 6, and 8.
 *
 * Now, a greedy approach will pick up the maximum weight 8 first.
 * However, there is no other item left using which we can fill the bag without exceeding the limit, which is 10.
 *
 * Now, a dynamic programming approach would consider and try each option, store each result, and pick up the best.
 * So, the dynamic programming approach can pick up the items having weights of 4, and 6. Hence, the total becomes 10.
 * This way, we could maximize the weight without exceeding the maximum capacity of the knapsack.
 * We could fill more items, more weight, more values, than the greedy approach.
 *
 * Conclusion: Dynamic Programming is more reliable, and optimal than the Greedy approach. Especially, for this example.
 *
 * ## How do we solve a problem using dynamic programming approach? What do we do in a dynamic programming?
 *
 * ### 1. Bottom-up approach:
 *
 * We start from 0 (the base case), and go all the way to the top (the end point, target, limit, or capacity.)
 *
 * ### 2. Memoization:
 *
 * As we go from 0 to top, we store the solutions of each problem, and use it to solve the larger problem.
 *
 * ### 3. Formula (Logic):
 *
 * We use the same formula, key-lemma, logic, or expression to solve the sub-problems,
 * and the larger (original) problem.
 *
 * For example, we use the same formula to solve the coin change problem when the target is x, y, or z.
 * The formula does not change with different target amounts.
 *
 * Similarly, we use the same formula to solve the edit distance problem when the target and reference length are
 * x and y.
 * The formula to solve the edit distance problem does not change if we change the length of the
 * target and the reference.
 *
 * The formula, key-lemma, logic, or expression to solve a particular problem (or sub-problem) can be different
 * for different problem statements, scenarios, contexts, etc.
 *
 * For example, we might have a different formula to solve the edit distance problem,
 * then the longest common sub-sequence.
 *
 * ### 4. Conclusion:
 *
 * The key-part here is the logic, the formula (expression, key-lemma).
 * We build and use the logic (formula, expression, key-lemma) based on the facts.
 * We select and connect the right facts into the right direction in the right way.
 * This process (selecting facts, building logic and formulas, and using it) is a part of logical thinking,
 * and the intuition of this process comes with experience, time, consistency, dedication, discipline, practice,
 * integrity, etc.
 * The continuous efforts and exploration into the process give (introduce) us new facts, and we can connect them
 * with the existing facts that we already know, that we are aware of, and by using the existing knowledge, data, and
 * facts, we play with the new data, and information to build, use, acknowledge, and be familiar with the new data,
 * and facts.
 * When this process continues, we get evolution.
 * So, understand the formulas (logic, expressions) properly to be able to solve unknown problems.
 *
 * # ----------------------- Building the logic: Step-By-Step -----------------------
 *
 * ## ----------------------- Formula -----------------------
 *
 * ### ----------------------- Initial Set Up -----------------------
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 2 (Weight 4) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * The columns 0 to 10 represents weights (weight capacity of the knapsack).
 * The rows 0 to 3 represents total items.
 *
 * The cell(0,0) = 0th row, and 0th column, represent 0 items and 0 weight capacity.
 *
 * The cell(3,10) = 3rd row (0th based index), 10th column (0th based index) represent 3 items, and 10 weight capacity.
 * The value of cell(3, 10) represent how many maximum gold bars we can take when there are a total of 3 gold bars,
 * and the knapsack weight capacity is 10. The 3 gold bars are having weight of: 1, 4, and 8.
 *
 * The cell(2,7) = 2nd row, 7th column, represent 2 items, and 7 weight capacity.
 * The value of cell(2, 7) represent how many maximum gold bars we can take when there are a total of 2 gold bars,
 * and the knapsack weight capacity is 7. The 2 gold bars are having weight of: 1, and 4.
 *
 * As we move from left side to right side, we increase the weight capacity.
 * As we move from top to down, we increase the total items.
 *
 * This is a 2D-Matrix (table), and we fill the table row-by-row.
 * It means, the index finger of the left hand will be on a particular item, until the index finger of the right hand
 * moves from 0 to the maximum weight capacity 10.
 *
 * Once the index finger of the right hand reaches 10, we start over. We change the row. We select the next row.
 * The index finger of the left hand will step-down one step. The index finger of the right hand start over.
 * The index finger of the right hand will start again from the 0th column, and will move up to the 10th column.
 *
 * We repeat this process until we fill each cell.
 *
 * ### ----------------------- cell(0, capacity) -----------------------
 *
 * When there is no item, irrespective of the knapsack capacity, all the cells of the 0th row will have the value 0.
 * It indicates that when there is no item (0 item), irrespective of the knapsack capacity, we can't pick up any item.
 *
 * Hence, the entire 0th row will have the value 0.
 *
 * ### ----------------------- cell(item, 0) -----------------------
 *
 * When the knapsack weight capacity is 0, irrespective of the total items, we can't pick up any item.
 *
 * Hence, the entire 0th column will have the value 0.
 *
 * ### ----------------------- cell(1, capacity) -----------------------
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * When there is only 1 item, and the knapsack weight capacity satisfies (greater than or equal to the item's weight),
 * we can place the item in the knapsack (to the cell).
 *
 * ### ----------------------- cell(2, capacity: 1..3) -----------------------
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	|   	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Even though when we have two items (of weight 1 and 4), we cannot pick up the item having weight 4 until we get
 * the knapsack weight capacity 4 or more. Till then, we can pick the item having weight 1.
 *
 * Notice the pattern here.
 * ```
 * We copied (i - 1) values.
 * ```
 * For example:
 * ```
 * cell(2, 1) = cell(1, 1)
 * cell(2, 2) = cell(1, 2)
 * cell(2, 3) = cell(1, 3)
 * ```
 * So, it looks like:
 * ```
 * cell(i, capacity) = cell(i - 1, capacity)
 * ```
 *
 * ### ----------------------- cell(2, capacity: 4) -----------------------
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 4 	|   	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * Recalling the expectations (requirements, problem statements, goals):
 *
 * ```
 * Output Format:
 *
 * The maximum weight of gold bars that fits into a backpack of capacity W.
 * ```
 *
 * The goal is to fit maximum weight.
 * Now, cell(2, 4) = 2 items (of having weight 1, and 4), and the knapsack weight capacity is 4.
 * We can pick up only one item for the given knapsack weight capacity: 4.
 * Either we can pick up the item having weight 1 or the item having weight 4.
 * Which item does satisfy the below condition (requirements, expectations, goal)?
 *
 * ```
 * The maximum weight of gold bars that fits into a backpack of capacity W.
 * ```
 *
 * The item having weight 4. Hence, cell(2, 4) = 4.
 *
 * Now comes the interesting magic of math.
 *
 * #### ----------------------- Building the formula from the observation, pattern, and behavior -----------------------
 *
 * The column 4 conveys that the maximum weight capacity of the knapsack is 4.
 * The value cell(2, 4) = 4 conveys that we have picked up the items whose total weight is 4.
 * The remaining weight capacity of the knapsack is: `4 - 4 = 0`.
 * If we go to the previous row:
 * `( i - 1 ) = ( 2 - 1 ) = 1` and the column `0` (the remaining weight capacity of the knapsack),
 * we find the value cell(1, 0) = 0, and it conveys that at this stage, we cannot add any other item.
 * This is a mathematical confirmation.
 * We proved the logic (common, and visual idea) mathematically,
 * and this theory (math) will help us build the logic (formula, expression).
 * The formula will convey (represent, prove, express, translate) the common, and visual idea that
 * if we have a knapsack of certain maximum capacity,
 * and we fill (pick up) a particular item (out of available options),
 * what will be the remaining capacity of the knapsack, and what will be the best optimal solution,
 * (the maximum weight we will be able to fill) for the remaining capacity of the knapsack.
 * This helps us pick up the best item(s) to meet our goal (fill maximum weight)
 * for the given weight capacity of the knapsack.
 *
 * The important point is:
 *
 * ```
 * Each cell represents the optimal solution (maximum weight we can fill),
 * for the given maximum weight capacity (column) of the knapsack.
 * ```
 *
 * We will use this information (math magic) as we move forward.
 * Remember that math is also a language.
 * Each formula expresses (conveys) something. That's why we call it an expression.
 *
 * ### ----------------------- cell(2, capacity: 5..10) -----------------------
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	|   	|   	|   	|   	|    	|
 * | 3 (Weight 8) 	| 0 	|   	|   	|   	|   	|   	|   	|   	|   	|   	|    	|
 *
 * cell(2, 5) = 2 items (weight 1 and 4), weight capacity 5.
 * We can take both the items: 1 + 4 = 5. Remaining weight is: 5 - 5 = 0.
 * So, we cannot take any additional item.
 * Hence, cell(2, 5) = 5.
 *
 * #### ------------- How do we do it mathematically? -------------
 *
 * Let us start from the beginning.
 *
 * 1. cell(2, 5) = 2 items, knapsack capacity is 5. The current item (row 2) is having weight 4.
 * 2. Let us pick the current item (wi): Weight 4.
 * 3. Remaining weight:
 *
 * ```
 * = cell(i - 1, capacity - wi)
 * = cell(2 - 1, 5 - 4)
 * = cell(1, 3)
 * = 1
 * = Magic! We can add `1` more, and yes, it is true!
 * ```
 *
 * 4. So, 4 + 1 = 5.
 * 5. Hence, cell(2, 5) = 5.
 *
 * However, earlier we have seen that:
 * ```
 * cell(i, capacity) = cell(i - 1, capacity)
 * ```
 * So, the formula can be:
 *
 * ```
 * cell(i, capacity) = maxOf ( cell(i - 1, capacity), wi + cell(i - 1, capacity - wi))
 * ```
 * Let us verify the formula:
 * ```
 * cell(2, 5)
 * = maxOf ( cell(i - 1, capacity), wi + cell(i - 1, capacity - wi) )
 * = maxOf ( cell(2 - 1, 5), 4 + cell(2 - 1, 5 - 4) )
 * = maxOf ( cell(1, 5), 4 + cell(1, 1) )
 * = maxOf ( 1, 4 + 1 )
 * = maxOf ( 1, 5)
 * = 5
 * ```
 *
 * ### ----------------------- Using the formula -----------------------
 *
 * We can fill the entire table using the above formula:
 *
 * |     i / w    	| 0 	| 1 	| 2 	| 3 	| 4 	| 5 	| 6 	| 7 	| 8 	| 9 	| 10 	|
 * |:------------:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:-:	|:--:	|
 * |       0      	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	| 0 	|  0 	|
 * | 1 (Weight 1) 	| 0 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	| 1 	|  1 	|
 * | 2 (Weight 4) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 5 	| 5 	|  5 	|
 * | 3 (Weight 8) 	| 0 	| 1 	| 1 	| 1 	| 4 	| 5 	| 5 	| 5 	| 8 	| 9 	|  9 	|
 *
 * ## ----------------------- More observation (patterns, behavior) -----------------------
 *
 * 1. The table (2D array) size to store the values. --> + 1 --> Size + 1, Options + 1, Target + 1, End + 1, Items + 1.
 * 2. Bottom-up journey (iteration). It will start from 0 or 1, and will go up to the target, limit, end point.
 *
 * # ----------------------- Complexity Analysis -----------------------
 *
 * ## ----------------------- Time Complexity Analysis -----------------------
 *
 * We have two nested for loops. If the number of items are `n`,
 * and the maximum weight capacity of the knapsack is, `W,`
 * then the time complexity is O(n * W).
 *
 * ## ----------------------- Space Complexity Analysis -----------------------
 *
 * We take a container (table) of size (n + 1)(W + 1).
 * Hence, the space complexity is also O(n * W).
 *
 * # ----------------------- Coursera's Grader Output -----------------------
 *
 * Good job! (Max time used: 0.11/4.00, max memory used: 54890496/536870912.)
 */
fun main() {

    fun maxShopping(capacity: Int, items: List<Int>) {
        // A container (table) of size + 1 to cover the 0th row and 0th column cases.
        val table = Array(items.size + 1) { IntArray(capacity + 1) }
        // The outside item loop starts from 1 as we have already covered the 0th row.
        for (i in 1..items.size) {
            // The inside weight loop starts from 1 as we have already covered the 0th column.
            for (weight in 1..capacity) {
                // Remember to read the item, and not the index, i!
                if (items[i - 1] <= weight) {
                    // Same! Remember to read the item, and not the index.
                    table[i][weight] = maxOf(table[i - 1][weight], items[i - 1] + table[i - 1][weight - items[i - 1]])
                } else {
                    // When we can't include the current item, we copy the previous setup.
                    table[i][weight] = table[i - 1][weight]
                }
            }
        }
        println(table[items.size][capacity])
    }

    val capacityAndItems = readln().split(" ").map { it.toInt() }
    val items = readln().split(" ").map { it.toInt() }
    maxShopping(capacityAndItems[0], items)
}