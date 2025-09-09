package courses.ucSanD.course01algorithmicToolbox.module04DivideAndConquerAssignment

/**
 * Problem Statement:
 *
 * Reference:
 *
 * res/courses/ucSanD/module04DivideAndConquerAssignment/03majorityElementOccurrences.png
 *
 * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/415ab40c38d28640dd97b5615292c0e9f080fd85/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/majorityElementOccurrences.png)
 *
 * Majority Element Problem:
 *
 * Check whether a given sequence of numbers contains an element that appears more than half of the times.
 *
 * Input: A sequence of n integers.
 *
 * Output: 1, if there is an element that is repeated more than n/2 times, and 0 otherwise.
 *
 * Input format. The first line contains an integer n, the next one contains
 * a sequence of n non-negative integers. a0,...,an−1.
 *
 * Output format. Output 1 if the sequence contains an element that appears
 * more than n/2 times, and 0 otherwise.
 *
 * Constraints: 1 ≤ n ≤ 10^5; 0 ≤ ai ≤ 10^9 for all 0 ≤ i <n.
 *
 * Sample 1:
 *
 * Input:
 *
 * 5
 *
 * 2 3 9 2 2
 *
 * Output:
 *
 * 1
 *
 * 2 is the majority element.
 *
 * Sample 2:
 *
 * Input:
 *
 * 4
 *
 * 1 2 3 1
 *
 * Output:
 *
 * 0
 *
 * This sequence does not have a majority element (note that the element 1 is not a majority element).
 *
 * ----------------------- Explanation -----------------------
 *
 * The Concept:
 *
 * The majority element:
 *
 * For an array of size n, if an element is repeated more than n/2 times, that element is known as the majority element.
 *
 * For example:
 *
 * If we have an array of size 5, such as [9, 7, 7, 7, 8],
 * any element that is repeated more than n/2 = 5/2 = 2 times is a majority element.
 *
 * Thus, 7 is the majority element in the given array.
 *
 * Now, if we observe, even if each different element cancels out the majority element,
 * the majority element will still remain and ultimately prevail.
 *
 * For example, 9 cancels one 7, 8 cancels one more 7, and what remains is the majority element: 7.
 *
 * This holds true for any arrangement if there is a majority element.
 *
 * Story Time:
 *
 * In a distant kingdom, there was a wise ruler who wanted to identify the most beloved tradition among the people in
 * his vast land. Each year, the kingdom celebrated a wide array of festivals, and the ruler was eager to know
 * which one resonated with the majority of his subjects. If he could find this majority,
 * he could ensure the kingdom’s resources were best allocated to make this beloved tradition even grander.
 *
 * However, with so many people spread across the land, directly counting each person’s favorite tradition seemed
 * impossible. To solve this, the ruler devised a clever, efficient plan.
 *
 * The ruler set out across his kingdom with a single assistant who carried a small tablet to track only one piece of
 * information at a time: the “current favorite.”
 * The ruler and his assistant started with no assumption of what this might be;
 * they would simply walk through the crowds, village by village, one person at a time,
 * and listen to each citizen’s favorite tradition.
 *
 * As they listened, the assistant would write down the first person’s favorite tradition as the “current favorite”
 * and start counting it. If the next person they met had the same favorite tradition, they added to the count,
 * as this supported their assumption that it might be the majority.
 *
 * But whenever someone mentioned a different tradition, they decreased the count.
 *
 * If the count dropped to zero, the assistant would erase the current favorite from the tablet,
 * and the ruler would listen to the next person, setting their favorite tradition as the new “current favorite.”
 *
 * Then, the assistant would start a fresh count, repeating this process until they had met every person in the kingdom.
 *
 * By the time they reached the last person, they had a candidate tradition that seemed to be the favorite.
 *
 * But to confirm this, they made a second journey through the kingdom,
 * listening only for mentions of this candidate tradition.
 * If more than half the people in the kingdom indeed favored this tradition,
 * they knew they had found the true majority.
 *
 * With this efficient strategy, the ruler discovered the beloved tradition of his people without having to record
 * every single response.
 * Not only did he save time and effort, but he could now make the festivities of this cherished tradition
 * more magnificent than ever, much to the joy of his kingdom.
 *
 * How the Story Maps to the Algorithm:
 *
 * In this story:
 *
 * 1. Current Favorite represents the “candidate” element in the Boyer-Moore algorithm,
 * the potential majority we’re tracking. Also known as the `currentMajor` element.
 *
 * 2. Count Increment whenever a new person supports the current favorite aligns with increasing the count
 * when we see the candidate `currentMajor` again.
 *
 * 3. Count Decrement for different responses aligns with decreasing the count when an element differs.
 *
 * 4. Resetting to zero when the count reaches zero mirrors the process of selecting a new candidate
 * when support for the current one is exhausted.
 * This is the moment when the current candidate (element) becomes the new `currentMajor` element,
 * and at this point, the count is set to 1.
 * Whenever the count reaches zero, the current element becomes the `currentMajor`, and the count is reset to 1.
 *
 * 5. Final Confirmation by a second pass to verify the favorite tradition reflects the final count check to ensure the
 * `currentMajor` element we found in the previous step is truly the majority.
 *
 * This memorable journey, using just a tablet and simple tracking, mirrors the Boyer-Moore voting algorithm’s power
 * to identify a majority element efficiently.
 * It also helps capture the essence of why this strategy is effective for finding the majority among large data.
 *
 * The Boyer-Moore Voting Algorithm leverages a clever mathematical property of majority elements and how they behave
 * when counted against non-majority elements.
 *
 * Let's break down the mathematical insight and key lemma behind this algorithm to understand its magic:
 *
 * Mathematical Insight of Boyer-Moore Voting Algorithm:
 *
 * Majority Element Property:
 *
 * The majority element is defined as the element that appears more than half the times in an array of size n,
 * i.e., count > n/2.
 *
 * If an element appears more than half the time, it cannot be "canceled out" by the other elements.
 *
 * The Key Concept:
 *
 * Counting and Cancellation:
 *
 * If we pair up each occurrence of the majority element with a different element,
 * there will still be occurrences of the majority element left because it appears more frequently than the rest combined.
 *
 * This means that, after canceling out the majority element with the others,
 * the candidate left in the end must be the majority element if it exists.
 *
 * Two Phases:
 *
 * In Phase 1, we find a candidate using a counting method.
 * We increment count if the current element matches the candidate, and decrement count if it doesn’t.
 *
 * When count becomes 0, it indicates that the candidate has been effectively "canceled out" by other elements,
 * meaning we need to pick a new candidate.
 *
 * In Phase 2, we verify whether the candidate is actually a majority element by counting its occurrences.
 *
 * Mathematical Basis Behind Counting:
 *
 * If an element occurs more than n/2 times, then after canceling as many of those elements with the other elements,
 * there will still be at least one occurrence left.
 *
 * In other words, if we pair up each occurrence of the majority element with another different element,
 * there will still be leftover occurrences of the majority element because of its frequency.
 *
 * For example:
 *
 * Imagine an array of size n = 7, with a majority element x that appears 4 times.
 * The non-majority elements are all the other distinct elements, and they add up to 3 occurrences.
 * When you start pairing each occurrence of x with one of the other elements,
 * you will still be left with one occurrence of x since 4 - 3 = 1.
 *
 * Key Lemma:
 *
 * The key lemma in the Boyer-Moore Voting Algorithm is:
 *
 * If there exists a majority element in the array (i.e., an element with count > n/2),
 * then this element will always survive after the counting (Phase 1), no matter the order of elements.
 *
 * This lemma holds because the majority element cannot be completely "canceled out" by the rest of the elements,
 * as it (the majority element) occurs more times than all the other elements combined.
 *
 * Intuition with an Example:
 *
 * Suppose we have an array: [A, B, A, C, A, A, B] and we want to find if there is a majority element.
 *
 * Phase 1: Select a Candidate:
 *
 * We start with the first element (A) as the candidate and set count = 1.
 * Move to B: it is different from the candidate (A), so we decrement count (count = 0).
 * Since count = 0, we pick the next element (A) as the new candidate and set count = 1.
 * Move to C: it is different from A, so decrement count (count = 0).
 * Since count = 0, pick the next element (A) as the candidate (count = 1).
 * Move to A: it matches the candidate, so increment count (count = 2).
 * Move to B: it is different, so decrement count (count = 1).
 *
 * The candidate at the end of Phase 1 is A.
 *
 * Phase 2: Verify the Candidate
 *
 * Count the occurrences of A in the array: A appears 4 times.
 * Since 4 > n/2 (4 > 3.5), A is the majority element.
 *
 * Why Does This Work?
 *
 * The count is essentially a balance between the majority element and other elements.
 * When count becomes 0, it means the previous candidate has been canceled out by other elements,
 * indicating we need to look for a new candidate.
 *
 * If there is a majority element, it will always dominate the count, meaning it will end up being the last candidate.
 *
 * Summary:
 *
 * Mathematical Insight:
 *
 * The algorithm uses a clever counting technique to balance out the majority element against the non-majority elements.
 *
 * Key Lemma:
 *
 * If there is a majority element (count > n/2), it will always survive after the counting process in Phase 1.
 * Phase 2 ensures that the candidate is actually a majority element by counting its occurrences.
 *
 * This makes the Boyer-Moore Voting Algorithm very efficient with a time complexity of O(n)
 * and a space complexity of O(1).
 *
 * It finds a potential candidate in a single pass and then verifies it in a second pass.
 *
 * The counting and canceling out mechanism is what makes it so powerful,
 * allowing it to solve the problem in linear time while only using constant space.
 *
 * TL;DR:
 *
 * 1. Count 0 and when the count is 0, the current element is the `currentMajority`.
 * 2. Increment count for the same value and decrement for any other value.
 * 3. When the count becomes 0 again, the current element is the `currentMajority`.
 * 4. Count the occurrences of only the `currentMajority`. (The second phase, the second iteration)
 *
 * Time Complexity:
 *
 * We iterate through the given input 2 times. So, 2N. We drop the constants in Big-O.
 * So, the time complexity of the Boyer Moore's Algorithm to find the majority element is, O(n).
 *
 * Space Complexity:
 *
 * The count variable is all we need. Hence, the space complexity of the Boyer Moore Algorithm to find the majority
 * element is O(1).
 *
 * Knowledge: Lessons to learn and understand:
 *
 * 1. What is a majority element?
 * 2. How to find a majority element?
 * 3. Counting: Support (reinforce = increment) and canceling-out (decrement).
 */
fun main() {

    fun majorityElement(input: List<Int>) {
        var currentMajority = input[0]
        // Initially, set the count to 0.
        var count = 0
        for (element in input) {
            // If the count is 0, the current element is the new `currentMajority` element.
            if (count == 0) {
                currentMajority = element
            }
            // Increase the count if the current element is equal to the `currentMajority` element.
            if (element == currentMajority) {
                // Support, reinforce
                count++
            } else {
                // Cancel out. Decrease the count if the current element is not equal to the `currentMajority` element.
                count--
            }
        }
        // Second phase. Reset the count to 0. This time, we count the occurrences of the `currentMajority` element.
        count = 0
        // Iterate to count the occurrences of the `currentMajority` element.
        for (element in input) {
            if (element == currentMajority) {
                count++
            }
        }
        // If the `currentMajority` element occurs more than half times, it is the majority element.
        val result = if (count > (input.size / 2)) 1 else 0
        println(result)
    }

    val totalElements = readln().toInt()
    val input = readln().split(" ").map{
        it.toInt()
    }
    majorityElement(input)
}