package coursera.ucSanDiego.course01algorithmicToolbox.module03GreedyAlgorithms

fun main() {

    /**
     * Visual references:
     *
     * res/level30Module3AlgorithmExercise/distinctNumbers.png
     *
     * [Interactive Visual](https://discrete-math-puzzles.github.io/puzzles/balls-in-boxes/index.html)
     *
     * Maximum Number of Prizes
     * Distinct Summands Problem
     * Represent a positive integer as the sum of the maximum number of pairwise distinct positive integers.
     * Input: A positive integer n. Output: The maximum k such that n can be represented as the sum a1 +···+ak of k distinct positive integers.
     *
     * You are organising a competition for children and have n candies to give as prizes.
     * You would like to use these candies for top k places in this competition with a restriction that
     * a higher place gets a larger number of candies.
     * To make as many children happy as possible, you need to find the largest value of k for which it is possible.
     *
     * Input format. An integer n.
     * Output format. In the first line, output the maximum number k such that
     * n can be represented as the sum of k pairwise distinct positive integers.
     * In the second line, output k pairwise distinct positive integers that sum up to n
     * (if there are multiple such representations, output any of them).
     * Constraints. 1 ≤ n ≤ 10^9.
     * Sample 1.
     *
     * Input:
     *
     * 6
     *
     * Output:
     *
     * 3
     *
     * 1 2 3
     *
     *
     * Sample 2.
     *
     * Input:
     *
     * 8
     *
     * Output:
     *
     * 3
     *
     * 1 2 5
     *
     * Sample 3.
     *
     * Input:
     *
     * 2
     *
     * Output:
     *
     * 1
     *
     * 2
     *
     * What I see here is that there is no fixed limit on the number of children.
     * We only have a total number of prizes (n candies to give as prizes).
     * This means that we can distribute the limited number of prizes among the unlimited number of children
     * in our own way with a few mentioned conditions.
     *
     * We can have prizes for all the children with the condition that the higher the place (rank),
     * the more the prizes, and two different places (ranks) cannot get the same amount of prizes.
     * I.e., each prize number for each child must be unique (k distinct positive integers).
     * For example, a particular child can get 0 prize but the number 0 must not be repeated for any other child.
     * I.e., no other child can get 0 prize.
     *
     * [Interactive Reference](https://discrete-math-puzzles.github.io/puzzles/balls-in-boxes/index.html )
     *
     * Let us say we have 4 prizes.
     * We start with 0 and keep adding 1 to the last number until the sum exceeds n.
     * So, we begin with 0 + 1 + 2 + 3 = 6. This exceeds the total prizes of 4.
     * Therefore, we need to remove 3 (the last number). Hence, it becomes 0 + 1 + 2 = 3.
     * Next, we add the remaining amount, 4 - 3 = 1, to the last number.
     * So, it becomes 0 + 1 + (2 + 1) = 0 + 1 + 3.
     *
     * If we observe, we can see that we need to keep track of
     * 1. The amount we need to add.
     * 2. The summand.
     * 3. The remaining amount.
     *
     * The amount we need to add starts at 1, and we continue increasing it by 1.
     *
     * Let us do it for n = 8.
     *
     * | Step 	| Current Integer 	| Summands  	| Summands<br>Result 	| Remaining<br>(Total - Summands)  	| Next Integer<br>(Current + 1) 	|
     * |------	|-----------------	|-----------	|--------------------	|----------------------------------	|-------------------------------	|
     * | 1    	| 1               	| [1]       	| 1                  	| 8 -1 = 7                         	| 2                             	|
     * | 2    	| 2               	| [1, 2]    	| 1 + 2 = 3          	| 8 - 3 = 5                        	| 3                             	|
     * | 3    	| 3               	| [1, 2, 3] 	| 1 + 2 + 3 = 6      	| 8 - 6 = 2                        	| 4                             	|
     *
     */
    fun distinctDistribution(total: Int): List<Int> {
        // We start with 1, and we will keep increasing it by 1.
        var current = 1
        // Initially, before adding any value, the summand is 0.
        // Summand is the total of all the values we add.
        var summand = 0
        // This is the bucket where we add our values.
        val mutableListOfDistinctSummand = mutableListOf<Int>()
        // We continue adding the values as long as the summand is less than or equal to the given total value (limit).
        while(summand <= total) {
            // We add values to our bucket.
            mutableListOfDistinctSummand.add(current)
            // Key-point: Summand = summand + current value.
            summand += current
            // We increase the value by 1.
            current++
            // After increasing the value by 1, if we find that it exceeds to the remaining amount,
            // we replace the last added value by: last added value + remaining value.
            val remainingAmount = total - summand
            if (current > remainingAmount) {
                val lastElement = mutableListOfDistinctSummand[mutableListOfDistinctSummand.size - 1]
                val newLastElement = lastElement + remainingAmount
                mutableListOfDistinctSummand[mutableListOfDistinctSummand.size - 1] = newLastElement
                break
            }
        }
        return mutableListOfDistinctSummand
    }

    val total = readln().toInt()
    val distinctDistributionAscending = distinctDistribution(total).sorted()
    println(distinctDistributionAscending.size)
    println(distinctDistributionAscending.joinToString(" "))
}