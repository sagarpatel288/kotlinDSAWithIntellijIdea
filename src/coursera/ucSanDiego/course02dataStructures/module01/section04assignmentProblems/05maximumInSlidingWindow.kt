package coursera.ucSanDiego.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem Description:
 *
 * ## Problem Introduction:
 *
 * * Given a sequence 𝑎1, . . . , 𝑎𝑛 of integers and an integer 𝑚 ≤ 𝑛,
 * find the maximum among {𝑎𝑖, . . . , 𝑎𝑖+𝑚−1} for every 1 ≤ 𝑖 ≤ 𝑛 − 𝑚 + 1.
 * * A naive 𝑂(𝑛𝑚) algorithm for solving this problem scans each window separately.
 * * Your goal is to design an 𝑂(𝑛) algorithm.
 *
 * ## Problem Statement (A different version, in other words):
 *
 * Given an array of integers and a window size,
 * find the maximum value in every contiguous subarray (window) of that size.
 *
 * ## Problem Description:
 *
 * * **Input Format:**
 *
 * * The first line contains an integer 𝑛, the second line contains 𝑛 integers 𝑎1, . . . , 𝑎𝑛 separated by spaces,
 * the third line contains an integer 𝑚.
 *
 * * ** Input In other words:**
 *
 *  * - You are given:
 *  *   - An integer `n`: the number of elements in the array.
 *  *   - An array of `n` integers: `a1, a2, ..., an`.
 *  *   - An integer `m` (where `1 ≤ m ≤ n`): the size of the sliding window.
 *  * - For every window of size `m`, output the maximum value in that window.
 *
 * * **Constraints:**
 * ```
 * 1 ≤ 𝑛 ≤ 10^5,
 * 1 ≤ 𝑚 ≤ 𝑛, 0 ≤ 𝑎𝑖 ≤ 10^5 for all 1 ≤ 𝑖 ≤ 𝑛.
 * ```
 *
 * * **Output Format:**
 *
 * Output:
 * ```
 * max{𝑎𝑖, . . . , 𝑎𝑖+𝑚−1} for every 1 ≤ 𝑖 ≤ 𝑛 − 𝑚 + 1.
 * ```
 *
 * ### Understanding the problem, data structure, pattern, thought process, intuition, and code translation:
 *
 * * [Shraddha - Apna College](https://youtu.be/XwG5cozqfaM?si=DkGpvwnXLTy-RdzC)
 * * [MIK - codestorywithMIK](https://youtu.be/29OnjVQ-fk4?si=rSzYlLqIMOWhaOqE)
 *
 * #### Understanding The Problem:
 *
 * ```
 *
 * Index i;
 * Window size m = 3;
 * Total elements are n;
 *
 *                                  ━━━━━━━━━━━━━━━━━
 *                                 ┃     Window 4    ┃
 *                                 ┃                 ┃
 *                      ───────────┃─────       ─────┃───────────
 *                     │           ┃     │     │     ┃           │
 *                     │ Window 2  ┃     │     │     ┃ Window 6  │
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *               │           ┃     │     │     ┃           │
 *               │ Window 1  ┃     │     │     ┃  Window 5 │
 *                ───────────┃─────       ─────────────────
 *                           ┃                 ┃
 *                           ┃    Window 3     ┃
 *                            ━━━━━━━━━━━━━━━━━
 *
 *
 * ```
 *
 * >-------
 *
 * * **Introduction:**
 *
 * >-------
 *
 * * The sliding window `moves` from `left to right`.
 * * The `size` of the sliding window is `m = 3`.
 * * We will take a container in which we will keep adding the indices or elements to form valid windows.
 * * Once we have a valid window in our container, we evaluate the max value.
 * * We will take a separate `max array` to store the `max` value of each window.
 * * We start from the `index i = 0`.
 * ```
 * Current index i = 0;
 * Valid window size m = 3;
 * Current window size is 1.
 *
 *               ┌─────┐
 *    Indices    │  0  │
 * ──────────────└─────┘
 *    Elements   │  1  │
 *               └─────┘
 *
 *
 * ```
 *
 * * Now, when `index i = 0`, we don't get any window. Because it covers only 1 element.
 * * So, we add the next `index i = 1`.
 * * But before we add the next `index i = 1`, notice an interesting fact.
 * * Currently, we have only one element, which is `1`.
 * * And the value of the next index we are going to add is `3`.
 * * Now, if the current last element value (inside our container) is less than the incoming value
 * (that we read from the given array), we are sure that the current last element cannot be the maximum value of the
 * current window. (Re-read this point if required.)
 * * For example, the current element value is `1`, and the incoming value is `3`.
 * * The incoming value `3` is greater than `1`.
 * * Both `1` and `3` are parts of the current window.
 * * We want to find the maximum value of the current window.
 * * It is obvious that `1` cannot be the answer, because we already have a higher value than it.
 * * So, to make room for the incoming value, we remove such values.
 * * So, our container becomes: //ToDo
 * * //ToDo: Why don't we avoid/skip the next incoming value if it is smaller than the existing max value?
 *
 * ```
 * Current index i = 1;
 * Valid window size m = 3;
 * Current window size is 2.
 *
 *               ┌─────┌─────┐
 *    Indices    │  0  │  1  │
 * ──────────────└─────└─────┘
 *    Elements   │  1  │  3  │
 *               └─────└─────┘
 *
 *
 * ```
 * * When `index i = 1`, we still don't get any window. Because it covers only 2 elements.
 * * So, we add the next index, `index i = 2`.
 * ```
 * Current index i = 2;
 * Valid window size m = 3;
 * Current window size is 3.
 *
 *               ┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │
 * ──────────────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │
 *               └─────└─────└─────┘
 *
 *
 * ```
 * * When `index i = 2`, we get our first window.
 *
 * >-------
 *
 * * **So, what is the mathematical formula to identify the minimum index value from which we start getting windows?**
 *
 * >-------
 *
 * * We get our first window when `i = 2`.
 * * So, to get our first window, `i` has to be `m - 1` = 3 - 1 = `2`.
 * * Then only we start getting our windows and results for each window.
 * * So, the mathematical formula (or condition) is:
 * ```
 * if (i >= m - 1) {
 *     // We start getting valid windows.
 * }
 * ```
 *
 * >-------
 * * **Window: 01**
 * >-------
 *
 * ```
 * Current index i = 2;
 * Window size is `m = 3`;
 *
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *               │                 │
 *               │ Window 1        │
 *                ─────────────────
 *
 * ```
 *
 * * So, when `i` is `m - 1` = 3 - 1 = `2`, we get our first window.
 *
 * * **Window 1:**
 *
 * * Indices [0, 1, 2] = Window 1; Elements [1, 3, -1];  max = 3;
 * * max array: [3]; (Notice that no left element is greater than 3.)
 *
 * >-------
 * * **Window: 02**
 * >-------
 *
 * ```
 * Current index i = 3;
 * Window size `m = 3`;
 *
 *                      ─────────────────
 *                     │                 │
 *                     │ Window 2        │
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *
 * ```
 *
 * * Increase index by 1. Move the index to the right side by 1 position.
 * * The last index value was `index i = 2`.
 * * Now, the index is `index i = 3`.
 * * Notice that the `index 3` is after the `index 2`.
 * * It is like we have pushed the new `index 3` to the `back`.
 * * So, to push a new index, we need to have something like `pushBack`.
 * * Index `i = 3`; cover `m = 3` elements;
 * * Now, if we keep the `0`th index, the window size becomes `4`.
 * * So, the index `0` should be removed for this window.
 * * The window size must be `m = 3`.
 * * So, if we go 3 steps backward from current `i`, we get the `starting index of the window`.
 * * All the indices that are less than the starting index of this window, we need to remove them.
 * * So, it is: `i - 3` = `3 - 3` = `0`.
 * * But, we can see that the starting index of the window is `1`, and not `0`.
 * * Because we have a `0-based index` system, and `counting` math (addition, subtraction, etc.) is `1-based`.
 * * So, it is a gap of `+1`.
 * * So, to get the correct starting index of the window, we need to `add 1`.
 * * So, it becomes:
 * * Starting index of the window = `i - m + 1` = `3 - 3 + 1` = `1`.
 * * All the indices which are less than `i - m + 1 = 1`, we need to remove them.
 * * `0` is less than `i - m + 1` = `3 - 3 + 1` = `1`.
 * * So, we need to remove it.
 * * So, if we want to remove `0`, we need something like `popFront`. Because it is the front index.
 * * So now, we get our next proper window.
 *
 * * **Window 2:**
 *
 * * Indices [1, 2, 3] = Window 2; Elements [3, -1, -3]; max = 3;
 * * max array [3, 3]; (Notice that no left element is greater than 3.)
 *
 * >-------
 * * **Window: 03**
 * >-------
 *
 * ```
 * Current index i = 4;
 * Window size m = 3;
 *
 *
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *                           ┃                 ┃
 *                           ┃                 ┃
 *                           ┃                 ┃
 *                           ┃                 ┃
 *                           ┃    Window 3     ┃
 *                            ━━━━━━━━━━━━━━━━━
 *
 *
 * ```
 *
 * * The last index was `index i = 3`.
 * * Now, the index is `index i = 4`.
 * * Notice that the `index 4` is after the `index 3`.
 * * So, to push the new `index 4` to the `back`, we need something like `pushBack`.
 * * Index `i = 4`; cover `m = 3` elements.
 * * Now, we need to remove `index 1` from this window. Otherwise, the window size becomes `4`.
 * * The index `1` should be removed for this window.
 * * We can clearly see that `index 1` is at the front.
 * * So, to remove the `front` index (or indices), we need something like `popFront`.
 * * Remove all the indices which are less than `i - m + 1` = `4 - 3 + 1` = `2`.
 * * Notice that `index i = 1` is less than `i - m + 1` = `4 - 3 + 1` = `2`.
 * * Once we remove all such indices, we get our 3rd window.
 *
 * * **Window 3:**
 *
 * * Indices [2, 3, 4] = Window 3; Elements [-1, -3, 5]; max = 5;
 * * max array: [3, 3, 5]; (Notice that no left element is greater than 5.)
 *
 * >-------
 * * **Window: 04**
 * >-------
 *
 * ```
 * Current index i = 5;
 * Window size m = 3;
 *
 *                                  ━━━━━━━━━━━━━━━━━
 *                                 ┃     Window 4    ┃
 *                                 ┃                 ┃
 *                                 ┃                 ┃
 *                                 ┃                 ┃
 *                                 ┃                 ┃
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *
 *
 * ```
 *
 * * The last index was, `index i = 4`.
 * * Now, the index is, `index i = 5`.
 * * Again, to `push` the new `index 5` after the `index 4`, we need something like `pushBack`.
 * * Index `i = 5`; cover `m = 3` elements.
 * * Now, we need to remove all the indices which are less than `i - m + 1` to keep the window size `m`.
 * * Notice that `index i = 2` is less than `i - m + 1` = `5 - 3 + 1` = `3`.
 * * All the indices which are less than `i - m + 1`, are at the front side.
 * * To remove them all (old indices), we need something like `popFront`.
 * * Once we remove all such indices, we get our 4th window.
 *
 * * **Window 4:**
 *
 * * Indices [3, 4, 5] = Window 4; Elements [-3, 5, 3];  max = 5;
 * * max array: [3, 3, 5, 5]; (Notice that no left element is greater than 5.)
 *
 * >-------
 * * **Window: 05**
 * >-------
 *
 * ```
 * Current index i = 6;
 * Window size m = 3;
 *
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *                                       │                 │
 *                                       │        Window 5 │
 *                                        ─────────────────
 *
 *
 * ```
 *
 * * The last index was, `index i = 5`.
 * * Now, the index is, `index i = 6`.
 * * Again, to `push` the new `index i = 6` after the `index i = 5`, we need something like `pushBack`.
 * * Index `i = 6`; cover `m = 3` elements.
 * * Now, we remove all the indices which are less than `i - m + 1` = `6 - 3 + 1` = `4`.
 * * So, the `index 3` should be removed for this window.
 * * All the indices which are less than `i - m + 1` are at the front side.
 * * To remove them all (old indices), we need something like `popFront`.
 * * Once we remove all such indices, we get our 5th window.
 *
 * * **Window 5:**
 *
 * * Indices [4, 5, 6] = Window 5; Elements [5, 3, 6];   max = 6;
 * * max array: [3, 3, 5, 5, 6]; (Notice that no left element is greater than 6.)
 *
 * >-------
 * * **Window: 06**
 * >-------
 *
 * ```
 * Current index i = 7;
 * Window size m = 3;
 *
 *                                              ─────────────────
 *                                             │                 │
 *                                             │       Window 6  │
 *               ┌─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐
 *    Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
 * ──────────────└─────└─────└─────└─────┘─────└─────└─────└─────┘
 *    Elements   │  1  │  3  │ - 1 │ - 3 │  5  │  3  │  6  │  7  │
 *               └─────└─────└─────└─────└─────└─────└─────└─────┘
 *
 *
 * ```
 *
 * * The last index was `index i = 6`.
 * * Now, the index is `index i = 7`.
 * * Again, to `push` the new `index i = 7` after the `index i = 6`, we need something like `pushBack`.
 * * Index `i = 7`; cover `m = 3` elements.
 * * Remove all the indices which are less than `i - m + 1` = `7 - 3 + 1` = `5`.
 * * So, the index `4` should be removed for this window.
 * * All the indices which are less than `i - m + 1`, are at the front side.
 * * To remove them all (old indices), we need something like `popFront`.
 * * Once we remove all such indices, we get our 6th window.
 *
 * * **Window 6:**
 *
 * * Indices [5, 6, 7] = Window 6; Elements [3, 6, 7];   max = 7;
 * * max array: [3, 3, 5, 5, 6, 7]; (Notice that no left element is greater than 7.)
 *
 * >-------
 * * **Can we get more windows?**
 * >-------
 *
 * * Once `index i = elementSize - 1`, we cannot go beyond that.
 * * Otherwise, we get `IndexOutOfBoundsException`.
 * * Current index is, `index i = 7`, and `elementSize - 1` is `8 - 1` = `7`.
 * * So, we cannot go beyond this. We have covered all the possible windows.
 *
 * #### Data Structure:
 *
 * * We push the next index to the back to form a new window. It is `pushBack`.
 * * We remove the old indices from the front side as we form a new window. It is `popFront`.
 * * The `Deque` is a data structure that can do `pushBack` and `popFront`.
 *
 * ##### What do we add to and remove from the `Deque`?
 *
 * * We add `indices` to the `back` of the `Deque` using `pushBack`.
 * * And we remove `indices` from the `front` of the `Deque` using `popFront`.
 *
 * * **Result:**
 *
 * * We start getting proper windows only after `index i >= m - 1 = 3 - 1 = 2`.
 * * When the `index i` is `index i >= m - 1`, we can get the `max value` result.
 * ```
 * if (i >= m - 1) {
 *
 * }
 * ```
 *
 * * **
 *
 *
 */
fun main() {

}