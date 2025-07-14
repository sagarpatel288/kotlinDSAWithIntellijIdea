package coursera.ucSanDiego.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem Description:
 *
 * ## Problem Introduction:
 *
 * * Stack is an abstract data type supporting the operations `Push()` and `Pop()`.
 * * It is not difficult to implement it in a way that both these operations work in constant time.
 * * In this problem, your goal will be to implement a stack that also supports finding the maximum value and to
 * ensure that all operations still work in constant time.
 *
 * ## Problem Description:
 *
 * * **Task.**
 *
 * * Implement a stack supporting the operations Push(), Pop(), and Max().
 *
 * * **Input Format:**
 *
 * * The first line of the input contains the number `𝑞` of queries.
 * * Each of the following `𝑞` lines specifies a query of one of the following formats: `push v`, `pop`, or `max`.
 *
 * * **Constraints:**
 * ```
 * 1 ≤ 𝑞 ≤ 400 000;
 * 0 ≤ 𝑣 ≤ 10^5;
 * ```
 *
 * * **Output Format:**
 *
 * * For each `max` query, output (on a separate line) the maximum value of the stack.
 *
 * * **Sample 1:**
 *
 * * _Input:_
 * ```
 * 5
 * push 2
 * push 1
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 2
 * 2
 * ```
 *
 * * _Explanation:_
 *
 * * After the first two push queries, the stack contains elements 1 and 2.
 * * After the pop query, the element 1 is removed.
 *
 * * **Sample 2:**
 *
 * * _Input:_
 * ```
 * 5
 * push 1
 * push 2
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 2
 * 1
 * ```
 *
 * * **Sample 3:**
 *
 * * _Input:_
 * ```
 * 10
 * push 2
 * push 3
 * push 9
 * push 7
 * push 2
 * max
 * max
 * max
 * pop
 * max
 * ```
 *
 * * _Output:_
 * ```
 * 9
 * 9
 * 9
 * 9
 * ```
 *
 * * **Sample 4:**
 *
 * * _Input:_
 * ```
 * 3
 * push 1
 * push 7
 * pop
 * ```
 *
 * * _Output:_
 * ```
 * ```
 *
 * * _Explanation:_
 *
 * * The output is empty since there are no max queries.
 *
 * * **Sample 5:**
 *
 * * _Input:_
 * ```
 * 6
 * push 7
 * push 1
 * push 7
 * max
 * pop
 * max
 * ```
 * * _Output:_
 * ```
 * 7
 * 7
 * ```
 */
fun main() {

}