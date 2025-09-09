package courses.ucSanD.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ## ----------------------- Problem Statement -----------------------
 *
 * Money Change Again Problem:
 *
 * Imagine you're a cashier at a store, and you have an infinite supply of coins with denominations of 1, 3, and 4.
 * A customer gives you a certain amount of money, and your task is to give them the exact change using as few coins as
 * possible. You can't just grab coins randomly or greedily; you need a systematic way to ensure you're giving the
 * least number of coins.
 *
 * Compute the minimum number of coins needed to change the given value into coins with denominations 1, 3, and 4.
 *
 * Input:
 *
 * An integer money.
 *
 * Output:
 *
 * The minimum number of coins with denominations 1, 3, and 4 that changes money.
 *
 * ## ----------------------- Recapitulation: Greedy Approach Vs. Dynamic Programming Example  -----------------------
 *
 * The greedy strategy works for certain coin systems called canonical coin systems
 * (e.g., the US coin system of 1, 5, 10, 25). These systems are specifically designed so that the greedy approach
 * always produces the optimal solution.
 *
 * In non-canonical coin systems (like 1, 8, and 20), the greedy approach can fail because the denominations are not
 * structured in a way that ensures optimality.
 *
 * For example, we want to change 24 into coins, and the coin denominations are 1, 8, and 20.
 * The greedy approach would select a coin of 20, followed by 4 coins of 1, resulting in a total of 5 coins.
 * However, the optimal solution would be to use 3 coins of 8!
 *
 * Similarly, if our coin denominations are 4, 10, and 25, and we want to make change for 41, the greedy approach would
 * first take a coin of 25 (leaving a remaining amount of 41 - 25 = 16), followed by a coin of 10 (leaving a remaining
 * amount of 16 - 10 = 6), followed by a coin of 4 (leaving a remaining amount of 6 - 4 = 2), ultimately concluding that
 * the change is not possible (which is an incorrect answer)!
 *
 * In contrast, if we use dynamic programming (whether through recursion with memoization or a bottom-up approach),
 * we find that the minimum number of coins needed consists of one coin of 25 and four coins of 4,
 * resulting in 25 + (4 * 4) = 41, which is the correct answer.
 *
 * ## ----------------------- Continue: Problem Statement -----------------------
 *
 * As we already know, a natural greedy strategy for the change problem does not work correctly for any set of
 * denominations. For example, for denominations 1, 3, and 4, the greedy algorithm will change 6 cents using
 * three coins (4 + 1 + 1) while it can be changed using just two coins (3 + 3).
 *
 * Your goal now is to apply dynamic programming for solving the Money Change Problem for denominations 1, 3, and 4.
 *
 * Input format:
 *
 * Integer money.
 *
 * Output format:
 *
 * The minimum number of coins with denominations 1, 3, and 4 that changes money.
 *
 * Constraints:
 *
 * 1 ≤ money ≤ 10^3
 *
 * Sample:
 *
 * Input:
 *
 * 34
 *
 * Output:
 *
 * 9
 *
 * 34 = 3 + 3 + 4 + 4 + 4 + 4 + 4 + 4 + 4.
 *
 * ## ----------------------- Story Time -----------------------
 *
 * res/courses/ucSanD/module05DynamicProgramming/02coinChangeImage01.jpg
 * [Image 01](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/616179aa6febdec7aa9828c06479a9d918b7b59d/res/coursera/ucSanDiego/module05DynamicProgramming/02coinChangeImage01.jpg)
 *
 * res/courses/ucSanD/module05DynamicProgramming/02coinChangeImage02.png
 * [Image 02](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/616179aa6febdec7aa9828c06479a9d918b7b59d/res/coursera/ucSanDiego/module05DynamicProgramming/02coinChangeImage02.png)
 *
 * res/courses/ucSanD/module05DynamicProgramming/02coinChangeImage03.png
 * [Image 03](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/616179aa6febdec7aa9828c06479a9d918b7b59d/res/coursera/ucSanDiego/module05DynamicProgramming/02coinChangeImage03.png)
 *
 * Imagine you are a cashier at a store. A customer walks in and hands you a certain amount of money,
 * asking for exact change using the fewest number of coins. You have an infinite supply of coins with denominations of
 * 1, 3, and 4. As the cashier, you need to figure out the best way to give the change.
 * But your manager has asked you to use a systematic approach to ensure you always give the customer the minimum number
 * of coins. This is to ensure that we can provide our service to as many customers as we can.
 *
 *
 * ### Episode-01: The cheat-sheet initialization (Born of the cheat-sheet):
 *
 * Before you start helping the customer, you create a cheat sheet (array `minCoins`) where you’ll record the
 * minimum number of coins needed for every possible amount of money, from 0 to the target amount.
 *
 * You think,
 * 1. "If I know the minimum number of coins needed for smaller amounts, I can use that information to calculate the
 * minimum coins for larger amounts. Let me prepare a table where I can write down the answers for every amount."
 * 2. "The table (cheat sheet) will also help me as a reference of already calculated coins for every amount. I can
 * quickly check the row of the target amount to find if I have already calculated minimum number of coins for it."
 * 3. You create the cheat sheet where there are two columns. The target amount and the minimum coins.
 * 4. You start the target amount from 0. You put 0 to the target amount and 0 as the minimum coins.
 * 5. The default value for each target amount is set to `Infinity` and it indicates, either you have not calculated it
 * so far, or the target amount is not possible to change with the available coin denominations.
 *
 * This intuition or story part is equivalent to the below code:
 *
 * ```
 * val minCoins = IntArray(targetAmount + 1) { Int.MAX_VALUE }
 * ```
 *
 * ### Episode:02: The base case:
 *
 * The first row where the target amount is 0 in the story indicates the below code:
 *
 * ```
 * minCoins[0] = 0
 * ```
 *
 * It says that, you don't need to give any change if the customer does not have any money.
 * If the target amount is 0, the number of coins needed is also 0.
 *
 * ### Episode:03: 1 to target amount, try each coin:
 *
 * Now, you start solving the problem for every amount from 1 to the target amount.
 * For each amount, you’ll try all the available coins (1, 3, and 4) to see which one gives the best result.
 *
 * You say, "Let me start with the smallest amount (1) and work my way up to the target amount.
 * For each amount, I’ll try all the coins and see which one helps me give the fewest coins."
 *
 * We can convert this part of the story into the below code:
 *
 * ```
 * for (amount in 1..targetAmount) {
 *     for (coin in coinDenominations) {
 *
 * ```
 *
 * You loop through every amount (1 to targetAmount) and for each amount,
 * you try using each coin denomination (1, 3, and 4).
 *
 * * **Note:**
 *
 * ```
 * // For each amount, I will try all the coins.
 * for (amount in 1..targetAmount) {
 *     for (coin in coinDenominations) {
 *     ...
 *     }
 * }
 * ```
 * * The above part is important.
 * * This particular note will be more clear when we re-visit this example problem after completing
 * the dynamic programming module.
 * * When we know that we need to use the dynamic programming with the bottom-up approach,
 * and we need to find the `min` for the target, we use the nested `for loops` somewhat like below:
 *
 * ```
 * for (target in start..end) {
 *     for (option in optionList) {
 *     // This way, we try the target with each available option to find the `min`.
 *     ...
 *     }
 * }
 * ```
 *
 * ### Episode-04: Check if we can use the coin
 *
 * Before you use a coin, you check if it’s possible to use it for the current amount. Specifically, you check:
 *
 * 1. Is the coin smaller than or equal to the current amount?
 * 2. Will using this coin lead to a valid solution?
 *
 * You think,
 *
 * 1. "If the customer wants $3 and I try to use a $4 coin, that won’t work because it’s too big.
 * 2. Also, if the remaining amount after using a coin is impossible to make, I shouldn’t use that coin."
 *
 * 2.1: For example, if I have the target amount as $3, and coin denominations are 2, 3, and 4, and the current coin
 * while I am trying all the coins is $2, then if I use this current coin, then the remaining amount will be $1 for
 * which I don't have any coin denomination. And because I started the bottom-up approach, from the target amount 0 to
 * all the way up, I should have already calculated the minimum number of coins required for $1. And when I check it,
 * I find that it has the default value instead of the minimum number of coins which indicates that when the
 * target amount is $1, and coin denominations are 2, 3, and 4, we can't change it.
 * It is an invalid case that we need to avoid. So, we also check this part.
 *
 * This part of the story can be translated into the below code:
 *
 * ```
 * if (amount >= coin && minCoins[amount - coin] != Int.MAX_VALUE) {
 * ```
 *
 * Explanation:
 *
 * 1. `amount >= coin`: Ensures the coin is small enough to be used.
 * 2. `minCoins[amount - coin] != Int.MAX_VALUE`: Ensures the remaining amount (amount - coin) can actually be made
 * with the available coins.
 *
 * ### Episode: 05: Updating the cheat-sheet:
 *
 * If the coin can be used, you calculate the total number of coins needed by:
 *
 * 1. Looking up the minimum coins needed for the remaining amount (minCoins[amount - coin]).
 * 2. Adding 1 for the coin you just used. (The coin that we used in `amount - coin` counts as a (one) coin.)
 * 3. Comparing this result with the current value in `minCoins[amount]` and keeping the smaller value.
 * 3.1.: The initial default value would be `Int.MAX_VALUE`. Then, we replace it with the result of the first coin that
 * we try while trying each coin for this amount. Then, we compare this result with the next coin and keep (store) the
 * minimum value in the cheat-sheet (the array: `minCoins`). We do this for each coin we try.
 *
 * You say, "If I use this coin, the remaining amount will be smaller. Let me check how many coins I’ll need for the
 * remaining amount and add 1 for the coin I just used. I’ll then compare it with my previous best answer for this
 * amount and keep the better (minimum) one."
 *
 * This part of the story represents the below code:
 *
 * ```
 * minCoins[amount] = minOf(minCoins[amount], minCoins[amount - coin] + 1)
 * ```
 *
 * 1. `minCoins[amount - coin] + 1`: Calculates the total remaining coins needed if you use the current coin.
 * 2. `minOf`: Ensures you keep the minimum value (the best solution) for the current amount.
 *
 * ### Episode: 06: Returning the result: The conclusion:
 *
 * Once you’ve filled out your cheat sheet for all amounts, you simply return the result for the target amount.
 * You proudly announce, "I’ve calculated the minimum number of coins needed for every amount.
 * For the customer’s amount, I’ll just look it up in my cheat sheet and give them the answer!"
 *
 * This part of the story signifies the below code:
 *
 * ```
 * return minCoins[targetAmount]
 * ```
 *
 * You return the precomputed result from your cheat sheet for the target amount.
 *
 * ### ----------------------- **Mapping the Story to Code** -----------------------
 *
 * Here’s how each part of the story relates to the code:
 *
 * | **Story Part**                                             | **Code**                                                                                         |
 * |------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
 * | Preparing your cheat sheet                                 | `val minCoins = IntArray(targetAmount + 1) { Int.MAX_VALUE }`                                    |
 * | Base case: No coins needed for 0 money                    | `minCoins[0] = 0`                                                                               |
 * | Solving for each amount                                    | `for (amount in 1..targetAmount) {`                                                             |
 * | Trying all coins                                           | `for (coin in coinDenominations) {`                                                            |
 * | Checking if a coin can be used                            | `if (amount >= coin && minCoins[amount - coin] != Int.MAX_VALUE) {`                             |
 * | Updating the cheat sheet with the minimum coins           | `minCoins[amount] = minOf(minCoins[amount], minCoins[amount - coin] + 1)`                       |
 * | Returning the result                                       | `return minCoins[targetAmount]`                                                                |
 *
 *
 *
 * ## ----------------------- Complexity Analysis -----------------------
 *
 * ### ----------------------- Time Complexity -----------------------
 *
 * 1. The outer loop runs for all amounts from 1 to amount. Thus, the number of iterations is proportional to amount.
 * Cost: `O(amount)`
 * 2. For each amount, the inner loop iterates over all the coin denominations (coins).
 * If there are n coins, the inner loop runs n times for each amount.
 * Cost: `O(coins)` per iteration of the outer loop.
 * 3. Combining both loops, the total time complexity is:
 * `O(amount × n)`
 * This means the time complexity grows linearly with the target amount and the number of coin denominations.
 *
 * ### ----------------------- Space Complexity -----------------------
 *
 * 1. The dp array requires storage for the `target amount + 1` elements.
 * Cost: `O(amount)`.
 * 2. A few extra variables are used for iteration and storage (e.g., coin, min), but these are constant and do not
 * depend on the input size.
 * Cost: `O(1)`.
 * 3. The total space complexity is:
 * `O(amount)`
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 *
 * (Max time used: 0.09/2.00, max memory used: 44609536/536870912.)
 *
 * ## ----------------------- Visual References (Technical) -----------------------
 *
 * res/courses/ucSanD/module05DynamicProgramming/010coinChangeDiagramAndCode005.png
 * [Technical image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5c2bd501ee249905ca50a5f618474aaa77c26271/res/coursera/ucSanDiego/module05DynamicProgramming/010coinChangeDiagramAndCode005.png)
 *
 * res/courses/ucSanD/module05DynamicProgramming/010coinChangeTabulation010.png
 * [Table](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5c2bd501ee249905ca50a5f618474aaa77c26271/res/coursera/ucSanDiego/module05DynamicProgramming/010coinChangeTabulation010.png)
 *
 * ## ----------------------- Extra -----------------------
 *
 * 1. The solution (approach) we have used to solve the problem is known as the
 * `dynamic programming using the bottom-up approach`.
 *  1.1.: In this approach, we start from the base-case and proceed all the way up towards the target.
 *  1.2.: The cheat-sheet (storage, container, `minCoins` array) we have used in the solution is known as
 * `memoization (cache)` and it helps avoid re-calculation. The memoization (cache) technique significantly reduces the
 * run-time cost.
 *
 */
fun main() {

    /**
     * Function to calculate the minimum number of coins required to form the target amount using the given coin
     * denominations.
     *
     * @param targetAmount: The amount for which we need to find the minimum number of coins.
     * @param coinDenominations: A list of coin denominations available.
     * @return The minimum number of coins needed to form the target amount.
     */
    fun minCoins(targetAmount: Int, coinDenominations: List<Int>): Int {
        // Episode 01: The cheat-sheet initialization with default values:
        // Think of `minCoins` as a cheat sheet that tells you the minimum number of coins needed for every amount
        // from 0 to the target amount.
        // For example:
        // - `minCoins[0]` tells you how many coins are needed to make 0 (answer: 0).
        // - `minCoins[5]` will eventually tell you how many coins are needed to make 5.
        // - Initially, we don't know the answers for any amounts except `0`, so we set all values to `Int.MAX_VALUE`
        // (a placeholder for "infinity").
        //
        // Step 1: Create an array to store the minimum number of coins for every amount from 0 to targetAmount.
        // Why size `targetAmount + 1`?
        // Because money starts from 1, whereas the array index starts from 0.
        // We need to store the result for amount `0` as well (1-based index).
        // An int array to store minimum number of coins for each target amount (bottom-up: 0 to targetAmount).
        // Initialize with a very large value (infinity).
        // We initialize each index with `Int.MAX_VALUE` for the comparison purpose during `minOf`.
        val minCoins = IntArray(targetAmount + 1) { Int.MAX_VALUE }
        // Episode 02: The Base Case — To make the amount `0`, we need `0` coins.
        // The minimum number of coins needed for the target amount 0 is: 0.
        minCoins[0] = 0
        // Episode 03: 1 to target amount, try each coin:
        // Chapter 3.1: Iterate over all amounts from 1 to targetAmount.
        // Start with the smallest amount (1) and work your way up to the target amount.
        // For each amount, ask: "What is the minimum number of coins I can use to make this amount?"
        // And then you try all the combinations of the available coin denominations.
        for (amount in 1..targetAmount) {
            // Chapter 3.2: Try every coin denomination to compute the minimum number of coins for the current amount.
            // Calculating the target amount with each coin.
            for (coin in coinDenominations) {
                // Episode 04: Check if we can use the coin.
                // Check if the coin can be used for this amount.
                // 1. For each amount, we ask: Can we use this coin?
                // So, the `amount >= coin` checks if it is possible to use the current `coin` for the current `amount`.
                // For example, if the amount is 3 and the coin is 5, then we can't use the coin denomination `5` for
                // the amount `3`. So, the condition is: Only if the amount >= coin, we can use the coin.
                // 2. minCoins[amount - coin] != Int.MAX_VALUE represents one-step ahead. Let us understand.
                // Assume that the coin denominations are 2, 4, and 5.
                // We start with the base-case where the target amount is 0. We need 0 coins to change 0 amount.
                // So, we store minCoins[0] = 0. Then, we increase the target amount and use each coin.
                // So, amount = 1, which is < (less than) coin 2.
                // It means, it is not possible to use any coin denomination when the target amount is 1 and
                // coin denominations are 2, 4, and 5. So, minCoins[1] = Int.MAX_VALUE. We continue the iteration.
                // So, amount = 2, which is >= coin 2. The remaining amount is 0. We store minCoins[2] = 1.
                // Then, amount = 3, which is >= coin 2. But, if we do amount - coin = 3 - 2 = we get 1, and we know,
                // minCoins[1] = Int.MAX_VALUE, which indicates that, if we get such a situation, then it will not
                // be possible to use any coin denominations further, and we cannot proceed further. We are blocked.
                // So, we want to avoid this kind of situation. We want to avoid such a coin usage that would give us
                // this kind of situation. That's why this check: minCoins[amount - coin] != Int.MAX_VALUE.
                // So, it checks if the coin can be used for this amount:
                // 1. Ensure the remaining amount (amount - coin) is non-negative.
                // 2. Ensure the remaining amount (amount - coin) can actually be made with the given coins.
                // -> minCoins[amount - coin] != Int.MAX_VALUE means the remaining amount is valid and can be made.
                // -> If minCoins[amount - coin] == Int.MAX_VALUE, it means the remaining amount is impossible to make,
                // so we skip this coin.
                // Conclusion: minCoins[amount - coin] != Int.MAX_VALUE checks (validates) that if we use `this`
                // (current) coin for the current amount, will it be possible to make it for the remaining amount with
                // the valid (available, given) coin denominations?
                // So, it validates the remaining amount.
                if (amount >= coin && minCoins[amount - coin] != Int.MAX_VALUE) {
                    // Episode 05: Updating the cheat-sheet:
                    // Storing the result that gives the minimum number of coins.
                    // Let us understand this line of code with an example.
                    // Suppose, the target amount is 5 and we use the coin denomination 3.
                    // Then, the remaining amount is `5 - 3 = amount - coin` and when we use the coin denomination 3,
                    // we use `1` coin. So, `+ 1` indicates the coin we have used.
                    // However, this is not the complete story.
                    // Let us understand what the `minCoins[amount - coin] + 1` code says.
                    // Assume that we want to find the distance from point A (bottom) to point C (top target).
                    // So, what we can do is, if we know the distance between C to B (reduction, smaller) and
                    // B to A (down to bottom), we can find the distance between A to C using the addition of C to B
                    // and B to A.
                    // Similarly, if the target amount is 5 and when we use the coin denomination 3, it says that:
                    // We can find the minimum number of coins needed for the target amount 5 if (or once) we know
                    // the minimum number of coins needed for the target amount 2.
                    // So, minCoins for 5 = minCoins for (5 - 3 = 2) + 1 (1 for the 1 coin of 3 that we have used).
                    // It means that the minimum number of coins for the target amount 5 is equal to the minimum number
                    // of coins for the target amount 2, plus 1.
                    // That is to say, Add 1 to the minimum number of coins needed for the target amount 2 to find the
                    // minimum number of coins needed for the target amount 5.
                    // For our example:
                    // If the minimum number of coins needed for the target amount 2 is 2, then the minimum number of
                    // coins needed for the target amount 5 is 2 + 1 = 3.
                    // Imagine that you give me the amount 5 to change. I have 3 coin denominations: 1, 3, and 4.
                    // I need to give you the minimum number of coins.
                    // So, I select one coin of value 3, and then I ask myself: If I use a coin of value 3 to change 5,
                    // then after I use the coin, what will be the remaining amount? And,
                    // What will be the number of coins needed for the remaining amount if I use a coin of value 3 to
                    // change 5.
                    // I will have to calculate for the remaining amount, which will be 5 - 3 = 2.
                    // So, I note down somewhere (so that I don't forget) that once I calculate the minimum number of
                    // coins needed for the remaining target amount 2, I need to add 1 to the result because
                    // I have used 1 coin of value 3 in the process.
                    // Later, I figure out that I need 2 coins of value 1 to make 2.
                    // So, I add 1 to this result => 2 + 1 to tell you that it will be 3 coins to change 5.
                    // Now, about the first part: minOf(minCoins[amount]
                    // If the target amount is 5, and the coin denominations are 1, 3, and 4, then there are multiple
                    // ways we can change the target amount. Indeed, the internal (inner) for loop does the same.
                    // It tries all the coins (combinations) one-by-one.
                    // For example, when we try the coin denomination 1, minCoins[5] = 5.
                    // Similarly, when we try the coin denomination 3, the remaining amount is 5 - 3 = 2.
                    // We have already stored (calculated) minCoins[2] and let us assume that minCoins[2] = 2.
                    // Total coins minCoins[5] = minCoins[2] + 1 for the coin of value 3 we have used in the process.
                    // = 2 + 1 coins resulting in minCoins[5] = 3.
                    // Now, we already have stored minCoins[5] value 5. But, we need to select the minimum.
                    // So, we compare: minCoins[5] = 5 Vs. minCoins[5 - 3] + 1 = minCoins[2] + 1 = 2 + 1 = 3.
                    // That's how the complete line of code becomes as below.
                    // It updates minCoins[5] from 5 to 3 as a result of minOf(minCoins[5], minCoins[5-3] + 1).
                    minCoins[amount] = minOf(minCoins[amount], minCoins[amount - coin] + 1)
                }
            }
        }
        // Episode 06: Returning the result.
        // Return the result for the target amount.
        return minCoins[targetAmount]
    }

    // Reading input.
    val targetAmount = readln().toInt()
    // Available coin denominations.
    val coinDenominations = listOf(1, 3, 4)
    // Compute the result.
    val result = minCoins(targetAmount, coinDenominations)
    // Print the result.
    println(result)
}