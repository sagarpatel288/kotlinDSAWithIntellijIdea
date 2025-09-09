package courses.ucSanD.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

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
 * In the first line, output the minimum number `k` of operations needed to get n from 1.
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
 * ## ----------------------- Thought Process -----------------------
 *
 * * We need to find minimum number of operations to reach `n`.
 * So, we will try all the options (`+1`, `*2`, and `*3`) for each number (station), and use `minOf`.
 * To use `minOf`, we need to store (remember) each answer.
 *
 * * How do we do that? How do we use all the operators? What do we do to store (save, remember) the answers?
 * 
 * * To store the answers, we can use an array: `operations`.
 *
 * * `operations[n]` says the number of operations required to reach `n`.
 * It means, the index `n` represents a number (a station), and the value represents corresponding (associated)
 * number of operations required to reach `n`.
 *
 * * It means, the size of the `operations` array must be `n + 1`,
 * so that we can store the value for `operations[n]`.
 * If we take the size of the `operations` array as `n - 1`, then
 * as soon as we try to store or read the value for `operations[n]`,
 * we will get the `index out of bound` exception.
 *
 * * So, the size of the `operations` array will be `n + 1`.
 *
 * * We want to try each number (station). The base-case (starting point) is `1`.
 * Hence, we don't need any step (0 steps) to reach `1`.
 * So, for the base-case, we can say (store): `operations[1] = 0`.
 *
 * * For the remaining numbers, from the number `2` to the target number, we can use a `loop`.
 * ```
 * for (i in 2..target)
 * ```
 * Note that we start from the base case (bottom) and drive (iterate) all the way up to the `target`.
 * Hence, this approach is known as the bottom-up approach.
 *
 * * Now, we can reach a number (station) `i` in 3 ways: Either using `+1`, or using `*2`, or using `*3`.
 *
 * * `+1` indicates that we have arrived at `i` from `i - 1` using the `+1` operator on `i - 1`.
 * It means that, the previous station was `i - 1`, and the current station is `i`.
 * We can reach `i` from the previous station `i - 1` by using the `+1` operator on `i - 1`,
 * and it is one more operation from `i - 1` to reach `i`.
 *
 * * `*2` indicates that we have arrived at `i` from `i/2` using the `*2` operator on `i/2`.
 * It means that, the previous station was `i/2`, and the current station is `i`.
 * If this is true, then dividing the current station `i` by `2` must give us a non-decimal, non-fractional,
 * whole digit, an integer number.
 * In other words, the current station `i` must be divisible by `2`.
 *
 * * How do we check whether the current number `i` is divisible by `2` or not?
 * ```
 * if (i % 2) == 0
 * ```
 * If this is true, we can reach `i` from the previous station `i/2` by using the `*2` operator on `i/2`,
 * and it is one more operation from `i/2` to reach `i`.
 *
 * * `*3` indicates that we have arrived at `i` from `i/3` using the `*3` operator on `i/3`.
 * It means that, the previous station was `i/3`, and the current station is `i`.
 * If this is true, then dividing the current station `i` by `3` must give us a non-decimal, non-fractional,
 * whole digit, an integer number.
 * In other words, the current station `i` must be divisible by `3`.
 *
 * * How do we check whether the current number `i` is divisible by `3` or not?
 * ```
 * if (i % 3) == 0
 * ```
 * If this is true, we can reach `i` from the previous station `i/3` by using the `*3` operator on `i/3`,
 * and it is one more operation from `i/3` to reach `i`.
 *
 * To reach a number `i`, we can only come from (the previous station can be):
 *
 * * `i - 1` (subtracting 1).
 *
 * * `i / 2` (if `i` is divisible by `2`).
 *
 * * `i / 3` (if `i` is divisible by `3`).
 *
 * Each of these transitions adds one step to the total count.
 * It can be one more operation of `+1` from `i - 1`, or one more operation of `*2` from `i/2`, or
 * one more operation of `*3` from `i/3`.
 * So, the minimum steps to reach `i` depend on the minimum steps to reach one of these three possible predecessors.
 *
 * * How do we store the `minimum` of these three possible options?
 * * We use `minOf`.
 * * How do we use `minOf`?
 * ```
 * operations[i] = operations[i - 1] + 1
 * if (i % 2 == 0) operations[i] = minOf(operations[i], operations[i/2] + 1)
 * if (i % 3 == 0) operations[i] = minOf(operations[i], operations[i/3] + 1)
 * ```
 *
 * * Ok, but what do we do after storing the number of minimum operations required to reach each number?
 * * We also need to print the path we took to reach the destination. How do we do that?
 *
 * * We use `backtracking`.
 *
 * * What is `backtracking`? What do we do there? How?
 *
 * * `Backtracking` uses the data that we have prepared so far to identify the path we took to reach the destination.
 *
 * * Let us understand what we do in `backtracking` and how we do it.
 *
 * * The `What` and `How` part of the `Backtracking`:
 *
 * * The `operations` array indicates the number of minimum operations required to reach each number (index).
 * The value of the last index (the target number) already indicates the number of minimum operations required to
 * reach it. However, the problem statement also asks us to print the steps, the paths we took to reach the
 * target number.
 *
 * * Now, consider each number as a station and the target number as a destination.
 * We know the last station. It is our target number, the destination.
 * So, we will start from the destination, and go back to find the stations at which we stopped (the paths we took).
 * * This is a backward journey. Hence, we call it `Backtracking`.
 *
 * * How do we do that?
 * To start from the destination, and go back down to the starting point, we can use a `loop`.
 * ```
 * k = n // We start from the target number, the destination, the last station at which we stopped.
 * while (k > 1) // We continue until we reach the starting point.
 * ```
 *
 * * Now, we must have arrived at station `k` either:
 * From `i - 1` using the `+1` operator, or from `i/2` using the `*2` operator, or from `i/3` using the `*3` operator.
 *
 * * How do we find which operator we have used to reach `k`? And how does it help identify the path we took?
 *
 * * The `k` in the `operations[k]` represents an index, a number, a station,
 * and `operations[k-1]` indicates the previous station.
 * * We know that we have used bottom-up approach, and we have covered each number up to the target number without
 * missing any number. So, it is a continuous increment by 1. Recalling the loop:
 * ```
 * for (i in 2..target)
 * ```
 *
 * * Now, it is a fact that we can always come (reach) `k` from `k-1` using the `+1` operator.
 * For example, we can always reach to `4` from `3` using the `+1` operator on `3`.
 * ```
 * operations[k] = operations[k - 1] + 1 // This is always possible.
 * ```
 * * When we use `+1` operator to reach `k`, the previous station is `k - 1`. Let us elaborate more on this.
 * * The `operations[k]` gives the number of minimum operations required to reach the station `k`.
 * * Similarly, `operations[k - 1]` gives the number of minimum operations required to reach the station `k - 1`.
 * * Now, if the number of minimum operations required to reach `k`, that is `operations[k]`, is one more (+1),
 * than the number of minimum operations required to reach `k - 1`, that is `operations[k - 1]`,
 * then, we can conclude that the previous station was `k - 1`,
 * and using the `+1` operator, we reached the station `k`.
 * * Conclusion: The previous station was `k - 1`.
 * * `k - 1` becomes the new current station, and we continue this process until we reach the base case.
 *
 * * However, when it comes to take the shortest path, minimum stops during the journey,
 * we might have reached `k` from `k/2` using the `*2` operator.
 * * For example, we can reach `4` from `2` using the `*2` operator.
 *
 * * How do we know if we have used `*2` operator on `k/2` to reach `k`?
 * * This is possible only if `k` is divisible by `2`.
 * * So, let us check that: Did we reach `k` from `k/2`?
 * ```
 * if (k % 2 == 0 && operations[k] == operations[k/2] + 1
 * ```
 * * If this condition is true, it means that `k` is divisible by `2`,
 * and the difference between the `operations[k]` and `operations[k/2]` is `1`.
 * * Hence, we can conclude that this additional operation (the difference) is `*2` on the `k/2` to reach `k`.
 * * In other words, we can reach `k` directly from `k/2`, and it is possible if `k` is divisible by `2`, and
 * when we use the `*2` operator on `k/2`.
 * * It means that the previous station where we stopped was `k/2`. Then, we used `*2` operator on `k/2` to reach `k`.
 * * Conclusion: The previous station was `k/2`.
 * * `k/2` becomes the new current station, and we continue this process until we reach the base case.
 *
 * * Similarly, we might have arrived at `k` from `k/3` using the `*3` operator on `k/3`.
 * So, let us check that: Did we reach `k` from `k/3`?
 * ```
 * if (k % 3 == 0 && operations[k] == operations[k/3] + 1
 * ```
 * * If this condition is true, it means that `k` is divisible by `3`, and the difference between
 * `operations[k]` and `operations[k/3]` is `1`.
 * * This additional operation (the difference) is `*3` operator on `k/3` to reach `k`.
 * * It means that the previous station where we stopped was `k/3`. Then, we used `*3` operator on `k/3` to reach `k`.
 * * Conclusion: The previous station where we stopped was `k/3`.
 * * `k/3` becomes the new current station, and we continue this process until we reach the base case.
 *
 * * TL;DR:
 *
 * * We know the base case, we solve the problem by building up solutions for smaller numbers, from bottom to top,
 * and use it to find the solution for larger numbers. This is called dynamic programming using the bottom-up approach.
 *
 * This is a similar approach to what we have done recently in
 * src/courses/ucSanD/module05DynamicProgramming/module05ProgrammingAssignment01/010coinChangeUsingDynamicProgramming.kt
 * [coin change dp](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5d36eb9619c8a13d0b6e57de316b2e3cc5397347/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/010coinChangeUsingDynamicProgramming.kt)
 *
 * ### ----------------------- Dynamic Programming Insight: -----------------------
 *
 * Dynamic programming works by solving a problem incrementally. For this problem:
 *
 * 1. Start from 1 (the base case), which takes 0 steps to reach.
 * 2. Gradually compute the minimum steps for 2, 3, 4, ..., up to n.
 * 3. Use previously computed results to calculate the next number efficiently.
 *
 * ### ----------------------- Key-Lemma (Core Idea): -----------------------
 *
 * To compute the minimum steps for a number `i`, use:
 *
 * ```
 * operations[i] = 1 + min ( operations[i-1], operations[i/2](if divisible), operations[i/3](if divisible) )
 * ```
 *
 * Here, `operations[i]` represents the minimum steps to reach `i`.
 *
 * ### ----------------------- Reconstructing the Path (Backtracking): -----------------------
 *
 * Once we know the minimum steps for `n`, we can backtrack to figure out the sequence of numbers:
 *
 * 1. Start at `n`.
 * 2. Move to the predecessor (either `n-1`, `n/2`, or `n/3`) that led to the minimum steps.
 * 3. Repeat until we reach 1.
 *
 * ### ----------------------- TL;DR: -----------------------
 *
 * 1. We know the base-case. So, start with the known base-case.
 * 2. Store answers while going through bottom-to-top.
 *    * We have 3 ways to reach the target. Hence, we will try all the 3 ways, and then use `minOf`.
 * 3. Backtrack.
 *
 * ### ----------------------- Thoughts-to-code -----------------------
 *
 * #### The container (a cheat-sheet) to store the answers while going through the base-case (bottom) to top.
 *
 * ```
 * val operations = IntArray(target + 1) // + 1 because the base-case starts from 1, but we need to include index 0 too.
 * ```
 * Here, each index of the `operations` array represents a number, a station in our journey
 * whose final destination is the `target`.
 *
 * #### Storing the base-case answer:
 *
 * ```
 * operations[1] = 0
 * ```
 * The value of each index represents (says, signifies, indicates) how many operations it takes to reach the number.
 * For example, `operations[1] = 0` indicates that to reach the index (station) `1`, it takes `0` operation.
 * (Because it is the starting point from where our journey begins.)
 *
 * #### Going through the base-case (bottom) to top.
 *
 * ```
 * for (number in 2..target) // Starts from 2 because we already know the base-case answer.
 * ```
 *
 * #### Trying all the given ways and storing the answer that gives the minimum operations
 *
 * ```
 * operations[i] = operations[i - 1] + 1 // One more operation from `operations[i-1]`.
 * // If `i` is divisible by 2, we might have reached to `i` by multiplying 2 to `i/2`.
 * // So, one more operation from `operations[i/2]`.
 * if (i % 2 == 0) operations[i] = minOf(operations[i], operations[i/2] + 1)
 * // Or if `i` is divisible by 3, we might have reached to `i` by multiplying 3 to `i/3`.
 * // So, one more operation from `operations[i/3]`.
 * if (i % 3 == 0) operations[i] = minOf(operations[i], operations[i/3] + 1)
 * ```
 *
 * Why `+ 1`?
 *
 * Because it represents the step we take to move from a predecessor to the current number
 * (or from the previous to the next number - it depends on the perception, where we look at. Not a big deal.
 * It always takes one step, one operation to move from one step to the next step).
 *
 * The number of operations to reach to the `operations[i - 1]`, `operations[i/2]`, or `operations[i/3]` and then,
 * from there to the `i`th number.
 *
 * Think of it like taking a series of steps on a staircase. Each step you take costs you “1” unit of effort.
 * If you know how much effort (how many steps) it took to reach the step below you,
 * then to stand on the next step (the next number), you’ll need that previous effort plus one more step.
 * Similarly, if you could jump from a lower step
 * (like half or a third of the current step number if it’s perfectly divisible), that jump also counts as
 * just one operation. So, no matter which way you got there—by adding 1, doubling, or tripling — the cost of
 * that particular move is always “one operation.”
 *
 * For example:
 *
 * We can reach the number `3` by:
 *
 *  * Either adding `+1` to `i - 1` = `3 - 1` = `2`. So, by adding `+1` to `2`.
 *  * Or, by multiplying `* 3` to the `i / 3` = `3 / 3` = `1`. So, by multiplying `1` with `3`.
 *  * In any of the above cases, it takes `1` operation.
 *
 * Similarly, we can reach the number `4` by:
 *
 * * Either adding `+1` to `i - 1` = `4 - 1` = `3`. So, by adding `+1` to `3`.
 * * Or, by multiplying `*2` to the `i / 2` = `4 / 2` = `2`. So, by multiplying `2` with `2`.
 * * In any of the above cases, it takes `1` operation.
 *
 * In other words:
 *
 * * `operations[i - 1] + 1` means: “The minimum operations to get to `i - 1` plus one more operation to get from
 * `i - 1` to `i`.”
 * * `operations[i/2] + 1` (if `i` is even) means: “The minimum operations to get to `i/2` plus one more operation to
 * double that to get `i`.”
 * * `operations[i/3] + 1` (if `i` is divisible by 3) means: “The minimum operations to get to `i/3` plus one operation
 * to triple that to reach `i`.”
 *
 * For example, if `i` = 4, we might say:
 * How many steps does it take to reach `4 - 1 = 3`?
 * Well, 3 is less than 4, and since we are using the bottom-up approach, we must have saved the value of
 * `operations[3]`. Let us assume that the value of `operations[3]` is 1. That is to say, 1 --> 3.
 * We get to `3` from `1` when we multiply `1` directly with `3`. So, it is 1 step.
 * And then to reach `4`, we have 3 options. We can either add 1, or multiply by 2 or 3.
 * But, multiplying by 2 or 3 does not give a valid answer (it exceeds 4 instead of matching, reaching at 4).
 * However, adding 1 reaches 4, and adding 1 is 1 operation. So, it is `operations[3] + 1`.
 * It signifies (implies, says) that to reach `4`, it takes `1` more operation than the number of operations
 * we took to reach `3`.
 *
 * _Remember that the `operations` array has stored the **number of minimum operations** required to reach `n`,
 * where `n` is an index._
 *
 * #### Backtracking:
 *
 * Once we know how many moves (operations) it takes to get to `n`, we also want to know which moves to take
 * (because we need to print that). We can start at `n` and trace back to `1`. For example:
 *
 * If `operations[6] = 2`, it tells us that it takes `2` steps to reach `6`.
 * But how did we get there? Was it from 5, 3, or some other number? Which path did we take?
 * At which stations did we stop before reaching `n`?
 *
 * Backtracking answers this question by retracing the steps based on the logic used to compute `operations`.
 *
 * We can ask (the backtracking question):
 *
 * * Did we come from `n-1`? Was that route the cheapest?
 * * Or if `n` is divisible by `2`, did we come from `n/2`?
 * * Or if `n` is divisible by `3`, did we come from `n/3`?
 *
 * Start at the Target Number (`n`):
 *
 * We know the minimum number of steps to reach `n` is stored in `operations[n]`.
 * Add `n` to the sequence. It is a known stop (station) in our journey, because it is our destination.
 * Now, we want to find the stations at which we stopped before arriving at the destination.
 *
 * Find the Predecessor of `n`:
 *
 * To find the number before `n` in the sequence, check which operator (from the three possible ones)
 * gave the minimum value for `operations[n]`:
 *
 * * Subtract 1: Check if `operations[n] == operations[n-1] + 1`.
 * If yes, we have arrived at `n` by adding `+1` to the `n - 1`.
 *
 * * Divide by 2: If `n` is divisible by 2, check if `operations[n] == operations[n/2] + 1`.
 * If yes, then we have arrived at `n` by multiplying `n/2` with `*2`.
 *
 * * Divide by 3: If `n` is divisible by 3, check if `operations[n] == operations[n/3] + 1`.
 * If yes, then we have arrived at `n` by multiplying `n/3` with `*3`.
 *
 * * If the answer is `+1` to `n - 1`, then the predecessor is `n - 1`.
 * * If the answer is `*2` with `n/2`, then the predecessor is `n/2`.
 * * If the answer is `*3` with `n/3`, then the predecessor is `n/3`.
 *
 * If we call this predecessor `k`, then the backtracking path so far is `n --> k`.
 * Add the predecessor to the sequence and move to this predecessor.
 * Repeat the process until we reach 1.
 *
 * Keep repeating the process: add the current number to the sequence and find its predecessor.
 * Stop when you reach 1.
 *
 * Reverse the Sequence:
 *
 * Since we are tracing backward from `n to 1`, the sequence will be in reverse order.
 * Reverse it at the end to get the correct order from `1 to n`.
 *
 * Example Walkthrough to explain the idea, the strategy:
 *
 * Let’s take an example where
 * `n = 10`.
 *
 * Step 1: Compute the `operations` Array:
 *
 * Using the dynamic programming logic:
 *
 * ```
 * operations[1] = 0 // Zero step to reach `1`.
 * operations[2] = 1 (from 1 → 2) // One step to reach `2`.
 * operations[3] = 1 (from 1 → 3 by multiplying 1 directly with 3) // One step to reach `3`.
 * operations[4] = 2 (from 1 → 2 → 4) // Two steps to reach `4`.
 * operations[5] = 3 (from 1 → 2 → 4 → 5) // Three steps to reach `5`.
 * operations[6] = 2 (from 1 → 3 → 6) // Two steps to reach `6`.
 * operations[7] = 3 (from 1 → 3 → 6 → 7) // Three steps to reach `7`.
 * operations[8] = 3 (from 1 → 2 → 4 → 8) // Three steps to reach `8`.
 * operations[9] = 2 (from 1 → 3 → 9) // Two steps to reach `9`.
 * operations[10] = 3 (from 1 → 3 → 9 → 10) // Three steps to reach `10`.
 * ```
 *
 * Step 2: Backtrack from `n = 10`:
 *
 * Start at `10`:
 *
 * ```
 * operations[10] = 3.
 * ```
 *
 * Check predecessors:
 *
 * ```
 * 10 → 9 (subtract 1): operations[9] + 1 = 2 + 1 = 3 (valid).
 * 10 → 5 (divide by 2): operations[5] + 1 = 3 + 1 = 4 (not valid).
 * 10 → 3 (divide by 3): Not possible (10 is not divisible by 3).
 * ```
 *
 * Choose `9` as the predecessor. Add `9` to the sequence.
 * So, it becomes `10 --> 9`.
 *
 * Move to `9`:
 *
 * ```
 * operations[9] = 2.
 * ```
 *
 * Check predecessors:
 *
 * ```
 * 9 → 8 (subtract 1): operations[8] + 1 = 3 + 1 = 4 (not valid).
 * 9 → 3 (divide by 3): operations[3] + 1 = 1 + 1 = 2 (valid).
 * ```
 *
 * Choose `3` as the predecessor. Add `3` to the sequence.
 * So, it becomes `10 --> 9 --> 3`.
 *
 * Move to `3`:
 *
 * ```
 * operations[3] = 1.
 * ```
 *
 * Check predecessors:
 * ```
 * 3 → 2 (subtract 1): operations[2] + 1 = 1 + 1 = 2 (not valid).
 * 3 → 1 (divide by 3): operations[1] + 1 = 0 + 1 = 1 (valid).
 * ```
 *
 * Choose `1` as the predecessor. Add `1` to the sequence.
 * So, it becomes `10 --> 9 --> 3 --> 1`.
 *
 * Stop at `1`:
 *
 * Step 3: Reverse the Sequence:
 *
 * The sequence traced backward is `[10, 9, 3, 1]`. Reverse it to get `[1, 3, 9, 10]`.
 * So, the stations at which we stopped are: 1, 3, 9, and 10.
 *
 * And how do we convert this idea, thought, plan, strategy into the relevant code?
 *
 * ```
 * var k = n // Start at the target number
 * while (k > 1) {
 *     sequence.add(k) // Add the current number to the sequence
 *     when {
 *         operations[k] == operations[k - 1] + 1 -> k -= 1 // Move to k-1
 *         k % 2 == 0 && operations[k] == operations[k / 2] + 1 -> k /= 2 // Move to k/2
 *         k % 3 == 0 && operations[k] == operations[k / 3] + 1 -> k /= 3 // Move to k/3
 *     }
 * }
 * sequence.add(1) // Add the starting number
 * sequence.reverse() // Reverse to get the correct order
 *
 * ```
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 * (Max time used: 0.11/3.00, max memory used: 46972928/536870912.)
 *
 */
fun main() {

    fun minOperationsShortestPath(targetNumber: Int): Pair<Int, List<Int>> {
        // A container to store the minimum number of operations required for each number.
        val operationsPaths = IntArray(targetNumber + 1)
        operationsPaths[1] = 0 // Starting point. It takes 0 operations to reach 1.
        // Handle edge-cases.
        if (targetNumber == 1) return 0 to mutableListOf(1)
        // Bottom-up approach.
        for (i in 2..targetNumber) {
            // We can always reach `i` from `i - 1` using the `+1` operator on `i - 1`.
            // For example, we can always reach `4` from `3` using the `+1` operator on `3`.
            operationsPaths[i] = operationsPaths[i - 1] + 1
            // Let us check if we can jump (reach) from `i/2` to `i`.
            if (i % 2 == 0) {
                // One more operation from the `i/2`, and it must be `*2`.
                operationsPaths[i] = minOf(operationsPaths[i], operationsPaths[i/2] + 1)
            }
            // Let us check if we can jump (reach) from `i/3` to `i`.
            if (i % 3 == 0) {
                // One more operation from the `i/3`, and it must be `*3`.
                operationsPaths[i] = minOf(operationsPaths[i], operationsPaths[i/3] + 1)
            }
        }

        //Backtracking. Identifying the path, the stops, the stations from the beginning to the destination.
        var currentNumber = targetNumber
        val stops = mutableListOf<Int>()
        while (currentNumber > 1) {
            stops.add(currentNumber)
            when {
                operationsPaths[currentNumber] == operationsPaths[currentNumber - 1] + 1 -> {
                    currentNumber -= 1
                }
                currentNumber % 2 == 0 && operationsPaths[currentNumber] == operationsPaths[currentNumber / 2] + 1 -> {
                    currentNumber /= 2
                }
                currentNumber % 3 == 0 && operationsPaths[currentNumber] == operationsPaths[currentNumber / 3] + 1 -> {
                    currentNumber /= 3
                }
            }
        }
        stops.add(currentNumber) //The starting point = 1.
        return (operationsPaths.last() to stops.reversed())
    }

    val input = readln().toInt()
    val result = minOperationsShortestPath(input)
    println(result.first)
    println(result.second.joinToString(" "))
}