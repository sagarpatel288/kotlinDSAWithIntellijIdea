package courses.uc.course02dataStructures.module04hashTables

/**
 * # Problem: Find pattern (substring) in Text
 *
 * ## Problem Introduction
 *
 * * In this problem, your goal is to implement the Rabin–Karp’s algorithm.
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * In this problem, your goal is to implement the Rabin–Karp’s algorithm for searching the given pattern in the given
 * text.
 *
 * ### Input Format
 *
 * * There are two strings in the input: the pattern P and the text T.
 *
 * ### Constraints
 *
 * ```
 * 1 <= |P| <= |T| <= 5 · 10^5.
 * ```
 *
 * * The total length of all occurrences of P in T doesn’t exceed 10^8.
 * * The pattern and the text contain only latin letters.
 *
 * ### Output Format
 *
 * * Print all the positions of the occurrences of P in T in the ascending order.
 * * Use 0-based indexing of positions in the the text T.
 *
 * ### Time Limits
 *
 * C: 1 sec, C++: 1 sec, Java: 5 sec, Python: 5 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 3 sec, Ruby: 3 sec,
 * Scala: 3 sec.
 *
 * ### Memory Limit
 *
 * 512MB
 *
 * ### Sample 1.
 *
 * #### Input:
 * ```
 * aba
 * abacaba
 * ```
 * #### Output:
 * ```
 * 0 4
 * ```
 * #### Explanation:
 *
 * * The pattern `aba` can be found in positions `0` (`abacaba`) and `4` (`abacaba`) of the text `abacaba`.
 *
 * ### Sample 2.
 *
 * #### Input:
 * ```
 * Test
 * testTesttesT
 * ```
 * #### Output:
 * ```
 * 4
 * ```
 * #### Explanation:
 *
 * * Pattern and text are case-sensitive in this problem.
 * * Pattern `Test` can only be found in position `4` in the text `testTesttesT`.
 *
 * ### Sample 3.
 *
 * #### Input:
 * ```
 * aaaaa
 * baaaaaaa
 * ```
 * #### Output:
 * ```
 * 1 2 3
 * ```
 * * Note that the occurrences of the pattern in the text can be overlapping, and that’s ok, you still need to output
 * all of them.
 *
 *
 * # Solution
 *
 * ## Hints
 * * The problem has explicitly asked us to use the **Rabin-Karp Algorithm**.
 *
 * ## Time Complexity
 *
 * * We can split the time complexity into two main parts:
 * * Hashing and Comparison
 *
 * **Hashing**
 * * To find a hash code of the given string, we iterate through the length of the string.
 * * So, for the pattern of length `|P|`, it is `O(|P|)`.
 * * Each substring of text `T` has the same length as `|P|`.
 * * But, only the first substring takes the `O(|P|)` time.
 * * All the other (subsequent) substrings take `O(1)` time due to rolling hash (sliding window).
 * * If the total substrings of `T` is `T - P`, then the total time becomes `O(|T| - |P|)`.
 * * Hence, the total hashing time for the pattern and all the substrings of the text is `O(|T|)`.
 *
 * **Comparison**
 * * We compare character by character and iterate up to the length of the pattern.
 * * Hence, each comparison takes `O(|P|)` time.
 * * According to the polynomial string hashing, the collision (false alarms) probability is less than `1`.
 * * Hence, we can safely say that the total time of all the comparisons is also `O(|P|)`.
 *
 * **Total**
 * * Hence, the total time complexity is: `O(|T| + |P|)`.
 *
 * ## Space Complexity
 * * We are not using any auxiliary space that depends, and grows with the input size - except the `result` list.
 * * The `result` list is the `output` requirement.
 * * Hence, the space complexity is `O(1)`.
 * * In the worst case, the total occurrences of the pattern can happen at `q` times.
 * * Then, the `result` size becomes `q`.
 * * In that case, the space complexity is `O(q)`.
 * * And if we count the input, the total space complexity is: `O(|T| + |P|)`.
 *
 * ## Grader Output
 * ```
 * Good job! (Max time used: 0.56/3.00, max memory used: 109912064/536870912.)
 * ```
 *
 * ## Interview Questions
 *
 * **Why do we choose the prime number, `p = 1_000_000_007L` and not a different one?**
 *
 * * We need a large `p` for two reasons.
 * * First, to reduce the collision probability.
 * * The collision probability is given by `L/p`.
 * * Hence, if the `p` is larger, the collision probability reduces.
 * * As a result, we get fewer "false alarms."
 * * And it ultimately improves our time complexity (efficiency).
 * * Because we don't have to unnecessarily double-check the substring and pattern.
 * * Second, it prevents overflow.
 * * `p = 10^9 + 7` is the first prime that goes beyond the billion.
 * * It is a standard prime choice in programming.
 * * It fits comfortably within `64-bit` `Long`.
 *
 * **Why do we choose the base, `x = 263L` and not a different one?**
 *
 * * The base should be a prime number and greater than the character alphabet.
 * * The input is case sensitive.
 * * Hence, the total characters are 26*2 = 52.
 * * `x = 263` is a prime number and is well above this `52`.
 *
 * **Why didn't we take the immediate prime after `52` instead of `x = 263`?
 *
 * * We can take, and it can perform fast computation compared to a large `x = 263`.
 * * For example, `Java` uses `x = 31` only.
 * * The benefit of a large `x` prime is uniform distribution and fewer collisions.
 * * For example, with base 10, the weight difference between `4` in `45` is significant compared to `4` in `54`.
 * * This spreads out the hash code more effectively and aggressively.
 * * It reduces the chances of different inputs getting the same hash code.
 * * So, it all comes down to a balanced decision between fast computation Vs. effective distribution.
 *
 * **Why did we start the iteration from `i = 1` while calculating the `baseWithHighestPower`?**
 * **How do we calculate the highest degree (or power) of the base in code?**
 *
 * * The `baseWithHighestPower` is our result.
 * * Initially, it is `1L`, which represents `x^0 = 1L`.
 * * It means that we have already covered the `0th` index.
 * * The highest degree is `|P| - 1`.
 * * It means that if the pattern length is `3`, then we want to achieve the final result as `x^2`.
 * * Now, the loop starts from `i = 1`, and goes until |P| (stops before the length).
 * * When `i = 1`, it becomes `result * x = 1L * x`. The result becomes `x`. So, it is `x^1`.
 * * Next, when `i = 2`, it becomes `result * x = x * x = x^2`.
 * * We stop here, because the pattern length is `3`.
 * * So, we start with `i = 1`, and it goes like `x^1, x^2, and so on... until |P|`.
 *
 * **Why do we need to perform `% p` while calculating the `baseWithHighestPower`?**
 *
 * * To prevent the overflow.
 * * To keep the `baseWithHighestPower` value within the data type range `Long` that we have taken.
 *
 * **Why didn't we use the `Math.pow` function to calculate the `baseWithHighestPower`?**
 *
 * * The `Math.pow` function can cause overflow.
 *
 */
fun main() {

    fun rabinKarpFindSubstring(text: String, pattern: String): List<Int> {
        val result = mutableListOf<Int>()

        // Edge Cases
        if (pattern.length > text.length) {
            return result
        }

        // Set-up
        val prime = 1_000_000_007L
        val xBase = 263L
        var baseWithHighestPower = 1L

        // Calculate the highest power (degree): x^{|P| - 1}
        // We start the iteration with 1, because the initial power is already 1.
        for (i in 1 until pattern.length) {
            baseWithHighestPower = (baseWithHighestPower * xBase) % prime
        }

        // Calculate the polynomial hash for the first window and the pattern
        var textWindowHash = 0L
        var patternWindowHash = 0L
        for (i in 0 until pattern.length) {
            patternWindowHash = ((patternWindowHash * xBase) + pattern[i].code.toLong()) % prime
            textWindowHash = ((textWindowHash * xBase) + text[i].code.toLong()) % prime
        }

        // Comparison and rolling hash (sliding window)
        for (i in 0..text.length - pattern.length) {
            // Check if the hash matches
            if (textWindowHash == patternWindowHash) {
                // Double check after hash matches (end index is exclusive)
                if (text.substring(i, i + pattern.length) == pattern) {
                    result.add(i)
                }
            }
            // Rolling hash
            if (i < text.length - pattern.length) {
                val subtract = text[i].code.toLong() * baseWithHighestPower
                val add = text[i + pattern.length].code.toLong()
                // Subtract the old (outgoing) character
                textWindowHash -= subtract
                // Multiply by the base and prevent overflow
                textWindowHash = (textWindowHash * xBase) % prime
                // Add the new (next, incoming, upcoming) character and prevent overflow
                textWindowHash = (textWindowHash + add) % prime
                // Ensure positive value
                textWindowHash = (textWindowHash % prime + prime) % prime
            }
        }

        return result
    }

    val pattern = readln()
    val text = readln()
    val result = rabinKarpFindSubstring(text, pattern)
    println(result.joinToString(" "))
}