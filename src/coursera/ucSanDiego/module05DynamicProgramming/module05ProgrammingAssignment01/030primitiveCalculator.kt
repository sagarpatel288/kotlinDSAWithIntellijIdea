package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ## ----------------------- Problem Statement -----------------------
 *
 * Primitive Calculator Problem:
 *
 * Find the minimum number of operations needed to get a positive integer n from 1 by using only three operations:
 * add 1, multiply by 2, and multiply by 3.
 *
 * Input:
 *
 * An integer n.
 *
 * Output:
 *
 * The minimum number of operations “+1”, “×2”, and “×3” needed to get n from 1.
 *
 * You are given a calculator that only performs the following three operations with an integer x:
 *
 * add 1 to x, multiply xby 2, or multiply xby 3.
 *
 * Given a positive integer n, your goal is to find the minimum number of operations needed to obtain n starting
 * from the number 1.
 *
 * Input format:
 *
 * An integer n.
 *
 * Output format: (Note, the underscore symbol `_` indicates a subscript.).
 *
 * In the first line, output the minimum number kof operations needed to get n from 1.
 * In the second line, output a sequence of intermediate numbers. That is, the second line should contain positive
 * integers a_0, a_1,..., a_k such that a_0 = 1, a_k = n and for all 1 ≤ i ≤ k, a_i is equal to either
 * a_(i−1) + 1, 2a_(i−1), or 3a_(i−1).
 *
 * If there are many such sequences, output any one of them.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 10^6
 *
 * Sample 1:
 *
 * Input:
 *
 * 1
 *
 * Output:
 *
 * 0
 * 1
 *
 * Sample 2:
 *
 * Input:
 *
 * 96234
 *
 * Output:
 *
 * 14
 *
 * 1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
 *
 * Another valid output in this case is “1 3 9 10 11 33 99 297 891 2673 8019 16038 16039 48117 96234”.
 *
 *
 * ## ----------------------- Recapitulation -----------------------
 *
 * Example Where a Greedy Approach Fails Compared to DP: n = 12
 *
 * Intuition:
 *
 * 	•	Dynamic Programming (DP) will carefully consider the minimal steps needed at each intermediate value to find
 * 	    the optimal solution.
 * 	•	A naive greedy approach (for example: always trying to multiply by the largest factor possible, and only
 * 	    using +1 when forced) may choose steps that seem immediately good but lead to more operations overall.
 *
 * Optimal DP Solution for n = 12:
 *
 * 	•	From 1, the optimal path to 12 with minimal operations is:
 *
 * 1 → 3 (multiply by 3)
 * 3 → 6 (multiply by 2)
 * 6 → 12 (multiply by 2)
 *
 * 	•	Total steps: 3
 *
 * This is indeed the minimal number of operations as found by a DP solution.
 *
 * A Naive Greedy Approach:
 *
 * Imagine a greedy strategy where at each step, you first try to multiply by 3 if it doesn’t exceed n.
 * If that fails, try multiplying by 2 if it doesn’t exceed n. If both multiplications exceed n, only then add 1.
 *
 * Let’s apply this naive greedy logic:
 *
 * 	•	Start at 1. Target is 12.
 * 	•	Try *3: 1 * 3 = 3 ≤ 12, good!
 * 	•	Current path: 1 → 3 (1 operation)
 *
 * 	•	At 3. Target is 12.
 * 	•	Try *3: 3 * 3 = 9 ≤ 12, good!
 * 	•	Current path: 1 → 3 → 9 (2 operations)
 *
 * 	•	At 9. Target is 12.
 * 	•	Try *3: 9 * 3 = 27 > 12 (overshoot, can’t do that)
 * 	•	Try *2: 9 * 2 = 18 > 12 (overshoot, can’t do that)
 * 	•	Must do +1: 9 → 10
 * 	•	Current path: 1 → 3 → 9 → 10 (3 operations)
 *
 * 	•	At 10. Target is 12.
 * 	•	Try *3: 10 * 3 = 30 > 12, can’t do
 * 	•	Try *2: 10 * 2 = 20 > 12, can’t do
 * 	•	Must do +1: 10 → 11
 * 	•	Current path: 1 → 3 → 9 → 10 → 11 (4 operations)
 *
 * 	•	At 11. Target is 12.
 * 	•	Try *3: 11 * 3 = 33 > 12, can’t do
 * 	•	Try *2: 11 * 2 = 22 > 12, can’t do
 * 	•	Must do +1: 11 → 12
 * 	•	Current path: 1 → 3 → 9 → 10 → 11 → 12 (5 operations)
 *
 * Greedy Steps: 5 operations.
 *
 * DP Steps: 3 operations.
 *
 * This clearly shows that a naive greedy approach (trying the largest multiplication first at every step) leads to
 * more steps (5) than the DP approach (3 steps), thus failing to find the optimal solution.
 *
 *
 */
fun main() {



    val input = readln().toInt()
}