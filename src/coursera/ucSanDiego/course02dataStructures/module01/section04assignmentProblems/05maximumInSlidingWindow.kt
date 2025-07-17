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
 * // Index i;
 * // Window size m = 3;
 * // Total elements are n;
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
 * * **Introduction:**
 * >-------
 *
 * * The sliding window `moves` from `left to right`.
 * * The `size` of the sliding window is `m = 3`.
 * * We will take a container in which we will keep adding the indices or elements to form valid windows.
 * * We will see what and why we add in the container with reason (rationale).
 * * Once we have a valid window in our container, we evaluate the max value.
 * * We will take a separate `max array` to store the `max` values of each window.
 * * We start from the `index i = 0`.
 *
 * >-------
 * * **What do we add to the container, and why?**
 * >-------
 *
 * * **Part-01:**
 *
 * * We have a couple of options: Either we can add indices or values or both.
 * * Now, let us see which one helps.
 *
 * * _What is our objective?_
 *
 * * To find the `Maximum Value` for each `Sliding Window`.
 *
 * * _What is the `Sliding Window`?_
 *
 * * It is a process.
 *
 * * _What kind of process?_
 *
 * * We are given an array.
 * * We are given a `window size`.
 * * We need to start from the starting index of the given array, and cover the indices in such a way that
 * the total indices are equal to the size of the given `window size`.
 * * So, for example, the first window contains the indices `[0, 1, 2]`.
 * * We find the maximum value for this window.
 * * We find values for each index. We compare them. And we find the maximum value among them.
 * * We store the maximum values for each window in a separate container for output.
 * * And then, we add the next index to form the next window.
 * * After adding the next index, we need to remove the indices that are not part of this new window.
 * * So, the next window contains the indices `[1, 2, 3]`.
 * * We can see that we added the new `index 3`, and removed the old `index 0`, which is not part of this window.
 * * We find the maximum value for this window: `[1, 2, 3]`.
 * * We store the result.
 * * We repeat this process until we cover the last index.
 * * The last window would contain the indices: `[5, 6, 7]`.
 *
 * * **Part-02:**
 *
 * * Now, as we know, every time we add 1 index, we remove 1 index if required to form a valid window.
 *
 * * **How can we know if it is time to remove the old index?**
 *
 * * We know that we are scanning and covering each index of the given array.
 * * We know that we are adding each index, one by one, one after another, to a container to form a valid window size.
 * * We know that we are increasing index `i` by 1 as we move from left to right to form new windows.
 * * We know that a valid window size is `m = 3`.
 * * So, at any point, if we count `window size m = 3` steps back from the current `i`, we get the starting index of the window.
 * * We can remove all the indices that are less than (before) this starting index point of the window.
 * * For example, when we start with `index 0`, it does not form a valid window. It is: `[0]`.
 * * So, we increase the index by 1 (we walk the index 1 step forward).
 * * So, the `index` is `1`. And the window is: `[0, 1]`.
 * * Notice how we add each new index to the back of the container.
 * ```
 * * It means that we need a container that supports something like `pushBack`.
 * ```
 * * Also, notice how adding each next index to the back moves old indices to the front.
 * * It is like each new index pushes the old indices to the front.
 * ```
 * * It clearly means that old indices are at the front.
 * ```
 * * Our current window is: `[0, 1]`.
 * * It is still not a valid window. So, we walk the index 1 step forward.
 * * So, the `index` is `2`. And, the window is: `[0, 1, 2]`.
 * * We find and store the maximum value for this window.
 * * Now, when we add the next `index 3`, the window becomes: `[0, 1, 2, 3]`.
 * * It exceeds the valid window size.
 * * _What is the window that we want to form?_
 * * It is `[1, 2, 3]`.
 * * _What is the current window after adding the next index?_
 * * It is `[0, 1, 2, 3]`.
 * * So, we need to remove the `index 0`.
 * * Notice that the old indices that we want to remove are at the front of the container.
 * ```
 * * So, we need a container that supports something like `popFront`.
 * ```
 * * Now, we are at `index 3`. The current index position is `index 3`. It is the `end index` of the window
 * that we want to form.
 * * If we find the starting index of this window, we can remove all the indices that are less than the starting index
 * to form our next valid window.
 * * Now, we can see that when the `index` is `3`, and we go `window size m = 3` steps backward,
 * we find the starting index of this window.
 * * So, it is `current i - windowSize = 3 - 3 = 0`.
 * * But, we can see that the starting index of this window is `1`, and not `0`.
 * * Because counting excludes the starting point, `index 3`.
 * * So, it is a gap of `+ 1`.
 * * So, to get the correct starting index of the window, we need to `add 1`.
 * * So, it becomes:
 * * Starting index of the window = `i - m + 1` = `3 - 3 + 1` = `1`.
 * * All the indices that are less than `i - m + 1`, we need to remove them to respect the window size.
 * * So, the conclusion is:
 * ```
 * After adding a new index, we remove the old index to maintain the window size.
 * We find and remove such indices at the front of the container.
 * ```
 * * And the formula is:
 * ```
 * Starting index of any window => i - m + 1 // Where `i` is `index` at current position, and `m` is the window size.
 * Remove all the indices that are less than this.
 * ```
 * * We know that all the indices that are less than the starting index point of a valid window,
 * are at the front of the container.
 * * So, it becomes (Code Translation):
 * ```
 * // Remove all the indices from the front of the container that are less than the starting index point of this window.
 * while (container.isNotEmpty() && container.front() < i - m + 1) {
 *     container.removeFront()
 * }
 * ```
 *
 * * **Part-03:**
 *
 * * We might have understood by now that we need to add `indices` to our container.
 * * _Why? Why do we add indices to the container?_
 * * Because it helps us form and move valid windows.
 * * With the help of the index value, we can get the corresponding element value.
 * * Then, we can compare these values to find and store the maximum value for a particular window.
 * * We `pushBack` a new index and `popFront` the old index to form a new window.
 * * We repeat this process until the index reaches `arraySize - 1` to cover all the indices and all the valid windows.
 *
 * >-------
 * * **The Process: The Container And The Maximum Value For Each Window:**
 * >-------
 *
 * ```
 * // Current index i = 0;
 * // Valid window size m = 3;
 * // Our container status:
 *
 *               ┌─────┐
 *    Indices    │  0  │
 * ──────────────└─────┘
 *    Elements   │  1  │
 *               └─────┘
 *
 *
 * ```
 * * Let us scan and cover the given array starting from the index 0.
 * * Before we add this `index 0`, we may need to remove some items from the container to make room for the upcoming
 * item.
 * * But the container is empty only.
 * * So, we don't need to remove any item.
 * * We can proceed further.
 * * Now, when `index i = 0`, we don't get any window. Because it covers only 1 element. It doesn't form a valid window.
 * * After adding `index 0`, we may need to remove old indices from the container to not exceed the valid window size.
 * * But, there are no such indices that are less than `i - m + 1 = 0 - 3 + 1 = -2` in the container.
 * * So, we don't need to remove any item.
 * * We can proceed further.
 * * So, we add the next `index i = 1`.
 * * But before we add the next `index i = 1`, we try to make room for it in our container.
 * * In the container, we are storing indices.
 * * Now, let us compare values at these indices.
 * * So, for example, currently, we have only one index, `0`.
 * * The value at `index 0` is `1`.
 * * Now, we are about to add the next `index = 1`.
 * * The value at `index 1` is `3`.
 * * Now, if we compare both these values, the current last value `1`, and the upcoming value `3`,
 * the current last value `1` is smaller than the upcoming value `3`.
 * * Now, we are finding the `maximum value for each window`.
 * * Our objective is to find the `maximum value for each window`.
 * * So, we are certain that for the current window, the smaller value `1` cannot be the `maximum value` of the window.
 * * Because the upcoming value `3` is already greater than the existing last value, `1`.
 * * So, to make room for the upcoming value, we remove all such smaller values from our container.
 * * So, the condition is:
 * ```
 * Before trying to insert the next index,
 * Remove all the values from the container that are smaller than the upcoming value.
 * ```
 * * The `index 0` with `element value 1` is at the front.
 * * To remove `index 0` from the front side of the container, we need something like `popFront`.
 * * Once we remove all such values, only then do we add the next index.
 * * Notice that we removed the `index 0` from front, but added the `index 1` from back.
 * * So, to add `indices` to the container, we need something like `pushBack`.
 * * So, our container becomes:
 *
 * ```
 * // Current last index i = 1; the element value of the current last index is 3;
 * // Current container status:
 *
 *               ┌─────┐
 *    Indices    │  1  │
 * ──────────────└─────┘
 *    Elements   │  3  │
 *               └─────┘
 * ```
 * * Notice how removing items from the front that are smaller than the upcoming value
 * ensures that the value of the front index in the container will be the maximum value of the window.
 * * Again, this process ensures that the value of the front index is the maximum value of the window.
 * * Now, after adding the next index, we need to ensure that we form a valid window.
 * * To ensure that we don't exceed the valid window size, we remove all the indices that are less than
 * the starting index of this window.
 * * The starting index of this window is, `i - m + 1 = 1 - 3 + 1 = -3`.
 * * There are no such indices in the container that are less than the starting index point of the window.
 * * So, we can't remove more items at the moment.
 * * But for `index i = 1`, we still don't get any window. Because it covers only 2 elements. Indices = [0, 1].
 * * So, we add the next index, `index i = 2`.
 * * But before we add the next index, what do we do?
 * * We remove all the values that are smaller than the upcoming value.
 * * What is the current last value? `3`.
 * * What is the upcoming value? `-1`.
 * * Is the current last value smaller than the upcoming value? No.
 * * So, we don't remove anything.
 * * We add the upcoming index using `pushBack`.
 * * So, our container becomes:
 *
 * ```
 * // Current last index i = 2; the element value of the current last index is -1;
 * // Container size is 2, but the current window size is 3.
 * // Because the index is 2.
 * // The index 2 says (signifies):
 * // "
 * // I have covered the indices [0, 1, 2].
 * // Don't worry about the missing items.
 * // I have removed the items that can never be the max value for this window.
 * // "
 *
 *               ┌─────┌─────┐
 *    Indices    │  1  │  2  │
 * ──────────────└─────└─────┘
 *    Elements   │  3  │ - 1 │
 *               └─────└─────┘
 *
 *
 * ```
 * * So, when `index i = 2`, we get our first window.
 * * What do we do after adding a new index?
 * * We remove all the indices from the front of the container that are less than the starting index point of the window.
 * * What is the starting index point of this window?
 * * Starting index point `i - m + 1 = 2 - 3 + 1 = 0`.
 * * Do we have any indices in the container that are less than the starting index point of the window?
 * * There are no indices in the container at the moment that are less than the starting index point of the window.
 * * So, we don't need to remove anything else.
 *
 * >-------
 * * **Why didn't we avoid/skip the `index 2` because the value at `index 2` is `-1`, which can never be the max value for this window?**
 * >-------
 *
 * * Yes, the value at `index 2` is `-1` and it cannot be the max value for `this` window.
 * * However, our window is moving from left to right.
 * * So, at one point, the `index 1` will be dropped, and the `index 2` will become the starting index point of another window.
 * * For that window, we don't know the next two items yet.
 * * It can be less than the value at `index 2`, that is `-1`.
 * * So, this is the reason we remove only those items that are last (hence, left to the upcoming value),
 * and smaller than the upcoming value.
 * * If the upcoming value itself is smaller than the last item, we don't skip the upcoming value.
 * * We add the upcoming value anyway.
 *
 * >-------
 * * **Ok. What is the mathematical formula to identify the minimum index value from which we start getting windows?**
 * * This is useful to be ready to expect and collect the results (max value) for each window.
 * >-------
 *
 * * As we know, we got our first window when `i = 2`.
 * * Notice that it is not our container size that signifies the window size.
 * * Because the container size at this point is only 2.
 * * It is the index value (not the value at that index) that signifies the window size.
 * * So, to get our first window, `i` has to be `m - 1` = 3 - 1 = `2`.
 * * Then only we start getting our windows and results for each window.
 * * So, the mathematical formula (or the condition that we need to use in code) is (Code Translation):
 * ```
 * if (i >= m - 1) {
 *     // It is a valid window. We can expect and collect the result (max value) of the window.
 * }
 * ```
 *
 * >-------
 * * **How do we find the max value for each window?**
 * >-------
 *
 * * If we notice the current container, the `max value` for the current window is the `front` item (Magic!).
 * ```
 *
 * // Notice that once a window is formed, the `max value` of the window is at the front side of the container.
 *
 *               ┌─────┌─────┐
 *    Indices    │  1  │  2  │
 * ──────────────└─────└─────┘
 *    Elements   │  3  │ - 1 │
 *               └─────└─────┘
 *
 * ```
 * * We store the indices in the container, and the first index at the moment is `1`.
 * * And the value at `index 1` is `3`, which is the maximum value of the current window.
 * * This is going to be an interesting and consistent pattern (magic!) throughout this process.
 * * We call this magical pattern, `Monotonic increasing/decreasing`.
 * * `Monotonic Increasing` means the process that arranges the items in an increasing order.
 * * `Monotonic Decreasing` means the process that arranges the items in a decreasing order.
 * * In this particular process (Maximum In Sliding Window), it is `Monotonic Decreasing`, and we will see how.
 *
 * >-------
 * * **Window: 01**
 * >-------
 *
 * ```
 * // Scanning (coverage) status:
 * // Current index i = 2;
 * // Window size is `m = 3`;
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
 *
 * // Our Container:
 *
 *               ┌─────┌─────┐
 *    Indices    │  1  │  2  │
 * ──────────────└─────└─────┘
 *    Elements   │  3  │ - 1 │
 *               └─────└─────┘
 *
 * ```
 *
 * * So, when `i` is `m - 1` = 3 - 1 = `2`, we get our first window.
 *
 * * **Window 1:**
 *
 * * Indices [0, 1, 2] = Window 1; Elements [1, 3, -1];  max = 3;
 * * Notice that no left element is greater than 3.
 * * The maximum value of this window is the value of the front index!
 * * max array: [3];
 * * So, it is:
 * ```
 * val max = container.front()
 * ```
 * * So, once we are ready to expect and collect the result of each window,
 * we can simply consider the current front item of the container as the maximum value for a particular window.
 * * So, it is:
 * ```
 * // When the current index i is >= m - 1, we can expect and collect the max value of the window.
 * if (i >= m - 1) {
 *     maxResults.add(container.front())
 * }
 * ```
 * * Let us move to the next index:
 *
 * >-------
 * * **Window: 02**
 * >-------
 *
 * ```
 * // Scanning (Coverage) Status:
 * // Current index i = 3;
 * // Window size `m = 3`;
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
 * // Container status:
 *
 *               ┌─────┌─────┐
 *    Indices    │  1  │  2  │
 * ──────────────└─────└─────┘
 *    Elements   │  3  │ - 1 │
 *               └─────└─────┘
 *
 *
 * ```
 *
 * * Increase index by 1. Move the index to the right side by 1 position.
 * * The last index value was `index i = 2`.
 * * Now, the upcoming index is `index i = 3`.
 * * But before we `pushBack` the upcoming index, what do we do?
 * * We remove all the values from the container that are smaller than the upcoming value.
 * * What is the current last value? `-1`.
 * * What is the upcoming value? `-3`.
 * * Is the last value smaller than the upcoming value? No.
 * * So, we don't remove anything.
 * * We `pushBack` the upcoming index in the container.
 * * So, our container becomes:
 *
 * ```
 * // Our Container:
 *
 *               ┌─────┌─────┌─────┐
 *    Indices    │  1  │  2  │  3  │
 * ──────────────└─────└─────└─────┘
 *    Elements   │  3  │ - 1 │ - 3 │
 *               └─────└─────└─────┘
 *
 * ```
 *
 * * What do we do after adding the new index?
 * * We remove all the indices from the front that are smaller than the starting index point of this window.
 * * What is the starting index point of this window?
 * * Starting index point `i - m + 1 = 3 - 3 + 1 = 1`.
 * * Do we have any indices that are less than the starting index point of this window?
 * * There are no indices in the container that are less than the starting index point of the window.
 * * So, we don't need to remove anything else.
 * * How do we get the max value of this window?
 * * The front item is the max value of this window, which is `index 1`, `element value 3`.
 * * So:
 *
 * * **Window 2:**
 *
 * * Indices [1, 2, 3] = Window 2; Elements [3, -1, -3]; max = 3;
 * * Notice that no left element is greater than 3.
 * * The maximum value of this window is the value of the front index!
 * * So:
 * * max array [3, 3];
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
 *
 * Our Container So Far:
 *
 *               ┌─────┌─────┌─────┐
 *    Indices    │  1  │  2  │  3  │
 * ──────────────└─────└─────└─────┘
 *    Elements   │  3  │ - 1 │ - 3 │
 *               └─────└─────└─────┘
 *
 *
 * ```
 *
 * * The last index was `index i = 3`.
 * * Now, the upcoming index is `index i = 4`.
 * * What do we do before we `pushBack` the upcoming index?
 * * We remove all the values from the last that are smaller than the upcoming value.
 * * What is the current last value? It is `-3`.
 * * What is the upcoming value? It is `5`.
 * * So, we remove the last value.
 * * Hence, the container becomes:
 *
 * ```
 * // Our Container at the moment (After removing the last smaller value, -3):
 *
 *               ┌─────┌─────┐
 *    Indices    │  1  │  2  │
 * ──────────────└─────└─────┘
 *    Elements   │  3  │ - 1 │
 *               └─────└─────┘
 *
 * ```
 *
 * * We repeat this process as long as the last value is smaller than the upcoming value.
 * * So, we ask the same questions again.
 * * What is the last value? It is `-1`.
 * * What is the upcoming value? It is `5`.
 * * Is the last value smaller than the upcoming value? Yes.
 * * So, we remove the last value.
 * * Hence, the container becomes:
 *
 * ```
 * // Our Container at the moment (After removing the last smaller value, -1):
 *
 *               ┌─────┐
 *    Indices    │  1  │
 * ──────────────└─────┘
 *    Elements   │  3  │
 *               └─────┘
 *
 * ```
 *
 * * We repeat this process as long as the last value is smaller than the upcoming value.
 * * We ask the same questions again.
 * * What is the last value? It is `3`.
 * * What is the upcoming value? It is `5`.
 * * Is the current last value smaller than the upcoming value? Yes.
 * * So, we remove the last value.
 * * Hence, the container becomes:
 *
 * ```
 * // The container is empty now after we removed the last smaller value, 3.
 *
 *               ┌─────┐
 *    Indices    │     │
 * ──────────────└─────┘
 *    Elements   │     │
 *               └─────┘
 *
 * ```
 *
 * * We repeat the same process again as long as the last value is smaller than the upcoming value.
 * * So, we ask the same questions again.
 * * What is the current last value?
 * * Well, the container is empty. So, there is no last value.
 * * Ok. It means we can go ahead and `pushBack` the upcoming value, `5`.
 * * So, the container becomes:
 *
 * ```
 * // Current status of the container:
 *
 *               ┌─────┐
 *    Indices    │  4  │
 * ──────────────└─────┘
 *    Elements   │  5  │
 *               └─────┘
 *
 * ```
 *
 * * Now, what do we do after adding an index?
 * * We remove all the indices from the front that are smaller than the starting index point of this window.
 * * What is the starting index point of this window?
 * * Starting index point `i - m + 1 = 4 - 3 + 1 = 2`.
 * * Do we have any indices that are smaller than the starting index point of this window?
 * * No, we don't have such indices.
 * * So, we don't remove anything else.
 * * How do we get the max value of this window?
 * * The value of the front index is the max value of this window.
 * * So:
 *
 * * **Window 3:**
 *
 * * Indices [2, 3, 4] = Window 3; Elements [-1, -3, 5]; max = 5;
 * * Notice that no left element is greater than 5.
 * * The maximum value of this window is the value of the front index!
 * * So:
 * * max array: [3, 3, 5];
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
 * // Container status at the moment:
 *
 *               ┌─────┐
 *    Indices    │  4  │
 * ──────────────└─────┘
 *    Elements   │  5  │
 *               └─────┘
 *
 * ```
 *
 * * The last index was, `index i = 4`.
 * * Now, the upcoming index is, `index i = 5`.
 * * What do we do before we `pushBack` the upcoming index?
 * * We remove all the values from the last that are smaller than the upcoming value.
 * * What is the current last value? It is `5`.
 * * What is the upcoming value? It is `3`.
 * * Is the current last value smaller than the upcoming value? No.
 * * So, we don't remove anything yet.
 * * We `pushBack` the next index.
 * * So, the container becomes:
 *
 * ```
 * // Current status of the container:
 *
 *               ┌─────┌─────┐
 *    Indices    │  4  │  5  │
 * ──────────────└─────└─────┘
 *    Elements   │  5  │  3  │
 *               └─────└─────┘
 *
 * ```
 *
 * * What do we do after adding a new index?
 * * We remove all the indices from the front that are smaller than the starting index of this window.
 * * What is the starting index point of this window?
 * * Starting index point `i - m + 1 = 5 - 3 + 1 = 3`.
 * * Do we have any indices that are smaller than the starting index point of this window?
 * * No. We don't have such indices.
 * * So, we don't remove anything else.
 * * How do we get the max value of this window?
 * * The value of the front index is the max value of this window.
 * * So:
 *
 * * **Window 4:**
 *
 * * Indices [3, 4, 5] = Window 4; Elements [-3, 5, 3];  max = 5;
 * * Notice that no left element is greater than 5.
 * * The maximum value of this window is the value of the front index!
 * * max array: [3, 3, 5, 5];
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
 * // Current status of the container:
 *
 *               ┌─────┌─────┐
 *    Indices    │  4  │  5  │
 * ──────────────└─────└─────┘
 *    Elements   │  5  │  3  │
 *               └─────└─────┘
 *
 *
 * ```
 *
 * * The last index was, `index i = 5`.
 * * Now, the upcoming index is, `index i = 6`.
 * * What do we do before we `pushBack` the upcoming index?
 * * We remove all the values from the last that are smaller than the upcoming value.
 * * What is the current last value? It is `3`.
 * * What is the upcoming value? It is `6`.
 * * Is the current last value smaller than the upcoming value? Yes.
 * * So, we remove it.
 * * Hence, our container becomes:
 *
 * ```
 * // Current status of the container after removing the last value, `3`.
 *
 *               ┌─────┐
 *    Indices    │  4  │
 * ──────────────└─────┘
 *    Elements   │  5  │
 *               └─────┘
 *
 * ```
 *
 * * We repeat the same process as long as we have smaller last values than the upcoming value.
 * * So, what is the current last value? It is `5`.
 * * What is the upcoming value? It is `6`.
 * * Is the current last value smaller than the upcoming value? Yes.
 * * So, we remove it.
 * * Hence, our container becomes:
 *
 * ```
 * // The current status of the container after removing the last value, `5`.
 * // The container is empty now.
 *
 *               ┌─────┐
 *    Indices    │     │
 * ──────────────└─────┘
 *    Elements   │     │
 *               └─────┘
 *
 * ```
 *
 * * We repeat the same process as long as we have smaller last values than the upcoming value.
 * * So, what is the current last value? Well, the container is empty. There is no value in it.
 * * Ok. So, we can `pushBack` the next index.
 * * So, the container becomes:
 *
 * ```
 * // The current status of the container:
 *
 *               ┌─────┐
 *    Indices    │  6  │
 * ──────────────└─────┘
 *    Elements   │  6  │
 *               └─────┘
 *
 * ```
 *
 * * Now, what do we do after adding a new index?
 * * We need to ensure the window size.
 * * We need to remove all the indices that are smaller than the starting index point of this window.
 * * So, what is the starting index point of this window?
 * * Starting index point of this window `i - m + 1 = 6 - 3 + 1 = 4`.
 * * Are there any indices that are smaller than the starting index point of this window?
 * * No. We don't have such indices.
 * * So, we don't remove anything else.
 * * How do we get the max value of this window?
 * * The value of the front index is the max value of this window.
 * * So:
 *
 * * **Window 5:**
 *
 * * Indices [4, 5, 6] = Window 5; Elements [5, 3, 6];   max = 6;
 * * Notice that no left element is greater than 6.
 * * The maximum value of this window is the value of the front index!
 * * max array: [3, 3, 5, 5, 6];
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
 * // The current status of the container:
 *
 *               ┌─────┐
 *    Indices    │  6  │
 * ──────────────└─────┘
 *    Elements   │  6  │
 *               └─────┘
 *
 *
 * ```
 *
 * * The last index was `index i = 6`.
 * * Now, the upcoming index is `index i = 7`.
 * * What do we do before we `pushBack` the new index?
 * * We remove all the last values that are smaller than the upcoming value.
 * * What is the current last value? It is `6`.
 * * What is the upcoming value? It is `7`.
 * * Is the current last value smaller than the upcoming value? Yes.
 * * So, we remove it.
 * * Hence, our container becomes:
 *
 * ```
 * // The current status of the container after removing the last value, `6`.
 *
 *               ┌─────┐
 *    Indices    │     │
 * ──────────────└─────┘
 *    Elements   │     │
 *               └─────┘
 *
 * ```
 *
 * * We repeat the same process as long as we have smaller last values than the upcoming value.
 * * So, what is the current last value?
 * * Well, the container is empty. We don't have any value in it at the moment.
 * * Ok. So, we `pushBack` the upcoming `index 7`.
 * * So, the container becomes:
 *
 * ```
 * // The current status of the container is:
 *
 *               ┌─────┐
 *    Indices    │  7  │
 * ──────────────└─────┘
 *    Elements   │  7  │
 *               └─────┘
 *
 * ```
 *
 * * What do we do after we `pushBack` a new index?
 * * We remove all the indices from the front that are smaller than the starting index point of this window.
 * * What is the starting index point of this window?
 * * Starting index point of this window `i - m + 1 = 7 - 3 + 1 = 5`.
 * * Are there any indices that are smaller than the starting index point of this window?
 * * No. We don't have such indices.
 * * So, we don't need to remove anything else.
 * * So, how do we find the max value of this window?
 * * Well, the value of the front index is the maximum value for this window.
 * * So:
 *
 * * **Window 6:**
 *
 * * Indices [5, 6, 7] = Window 6; Elements [3, 6, 7];   max = 7;
 * * Notice that no left element is greater than 7.
 * * The maximum value of this window is the value of the front index!
 * * max array: [3, 3, 5, 5, 6, 7];
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
 * ### Story Points and Code Translation:
 *
 * #### Data Structure: Which data structure do we use and why?
 *
 * * We remove the smaller last values from the last before we `pushBack` the new index. It is `popBack` or `removeLast`.
 * * We push the next index to the back to form a new window. It is `pushBack` or `addLast`.
 * * We remove the old indices from the front side as we form a new window. It is `popFront` or `removeFirst`.
 * * The maximum value of a particular window is the value of the front index. It is `topFront` or `first`.
 * * The `Deque` is a data structure that can do these `removeLast`, `addLast`, `removeFirst`, and `first` operations
 * efficiently in `O(1)` time.
 *
 * #### Why can't we take an array?
 *
 * * Because we remove indices from the front that are smaller than the starting index of the current window.
 * * It can cause `O(n)` time in an array as each next item will have to shift to fill the gap.
 *
 * ##### What do we add to and remove from the `Deque`?
 *
 * * We add `indices` to the `back` of the `Deque` using `pushBack` or `addLast`.
 * * And we remove `indices` from the `front` of the `Deque` using `popFront` or `removeFirst`.
 *
 * #### Why do we add `indices` and not values?
 *
 * * Because adding indices helps us find the starting and ending index of the current window.
 * * Once we know the starting index, we can remove all the indices that are smaller than the starting index.
 * * This process ensures that we include (consider) only the relevant indices of the current window.
 *
 * #### What is the condition to check and be ready to expect and collect the result?
 *
 * * We start getting proper windows only after `index i >= m - 1 = 3 - 1 = 2`.
 * * When the `index i` is `index i >= m - 1`, we can get the `max value` result.
 * ```
 * // `i` is the current index position and `m` is the given window size
 * if (i >= m - 1) {
 *
 * }
 * ```
 *
 * #### How do we make room for the upcoming index?
 *
 * * We keep only the feasible (possible) `max` indices in the container.
 * * If the value of the last index of the container is smaller than the value of the upcoming index,
 * the smaller value can never be the `max` value.
 * * So, we remove such values from the last that are smaller than the value of the upcoming index.
 * * This is how we make room for the upcoming index.
 *
 * #### Why do we remove the smaller values from the last only, and not from the front?
 *
 * * The process ensures that we get the maximum value of the current window from the front only.
 * * And the process also ensures that we get the `Monotonic Decreasing` pattern in the `Deque`.
 * * It means that if we remove the front item, we might lose the potential maximum value.
 *
 * #### What is the monotonic decreasing pattern?
 *
 * * The `non-increasing` pattern in which each next element is either equal to or smaller than the previous value.
 * * For example: `[3, 3, 5, 5, 6, 7]` is a `monotonic increasing` pattern.
 * * And `[7, 7, 5, 5, 3, 2, 1]` is a `monotonic decreasing` pattern.
 *
 * #### What is a strictly increasing or decreasing pattern?
 *
 * * In a strictly increasing pattern, the next item must be greater than the previous item. It cannot be equal.
 * * For example: `[3, 5, 6, 7]` is a `strictly increasing` pattern.
 * * In a strictly decreasing pattern, the next item must be smaller than the previous item. It cannot be equal.
 * * For example: `[7, 5, 3, 2, 1]` is a `strictly decreasing` pattern.
 *
 * #### Why do we not skip the upcoming value (index) if it is smaller than the last value?
 *
 * * Regardless of value (weight), the previous value will be removed as we move and form a new window.
 * * For the next window, the present smaller upcoming value can be the maximum value for the future window.
 * * Because the old indices will eventually be removed to form the next valid window.
 * * So, the current maximum value will be removed as we move and form the next window,
 * which can make the present upcoming smaller value a maximum value for the next window.
 * * So, the upcoming value can be the maximum value for the next window as we slide (move) the window.
 * * For example, the upcoming value can be a starting index of the next window, where it can be the maximum value.
 * * For example: `[3, 2, -1]`. `2` and `-1` are smaller than `3` for this window.
 * * But, as we move and form a new window, it can be: `[2, -1, -2]`. Here, `2` is the maximum value.
 * * Again, the next window can be: `[-1, -2, -3]`. Here, `-1` is the maximum value.
 * * And so on...
 * * So, even though at the moment the upcoming value might be a smaller one than the previous value,
 * but as we move and form new windows, it can be the maximum value compared to the next unknown values.
 * * That's why we don't skip the upcoming values.
 * * We only remove the values from the last that are smaller than the upcoming value,
 * and we remove the indices from the front that are no longer part of the next window.
 *
 * #### How do we get the `maximum value` of a particular window?
 *
 * * The value of the first index in the deque is the maximum value of the window.
 *
 * #### How does that happen that we get the `maximum value` of the window at the front of the deque?
 *
 * * Because we kept removing the values from the last that are smaller than the upcoming value.
 * * It ensures that the value at the front is the maximum value of the window.
 * * It is as if the value with a higher weight pushes the smaller values out from the front of the deque.
 *
 * #### How do we remove the smaller last values?
 *
 * >-------
 * OR, in other words:
 * >-------
 *
 * #### Code translation for removing the last values that are smaller than the upcoming value:
 * * Make a room for the upcoming index.
 * * If applicable, `popBack` (or `removeLast`) before we `pushBack` (or `addLast`) the upcoming index.
 *
 * ```
 * while (deque.isNotEmpty() && array[deque.last()] <= array[i]) {
 *     deque.removeLast()
 * }
 * ```
 *
 * #### Code translation to `pushBack` (or `addLast`) the next index:
 *
 * ```
 * deque.addLast(i)
 * ```
 *
 * #### Code translation to remove indices that are smaller than the starting index point of the current window:
 * * Remove indices that are not part of the current window.
 *
 * ```
 * while (deque.isNotEmpty() && deque.front() < (i - m + 1)) {
 *     deque.removeFirst()
 * }
 * ```
 *
 * #### Code translation of finding (retrieving, extracting) the max value:
 *
 * ```
 * val maxValueOfTheWindow = itemList[deque.first()] // Where `itemList` is given array (or list)
 * ```
 *
 * #### Code translation to find and store the max value of the current window:
 *
 * ```
 * if (i >= m - 1) {
 *     results.add(itemList[deque.first()])
 * }
 * ```
 *
 * ## Time Complexity:
 *
 * * We travel (iterate) through each index once through the outermost for loop.
 * * It takes `O(n)` time.
 * * Each operation in each while loop takes `O(1)` time only.
 * * So, the overall time complexity is `O(n)`.
 *
 * ## Space Complexity:
 *
 * * We take a `results` array (or list) to store the result for the output purpose.
 * * Otherwise, the core algorithm uses a `deque` whose maximum size is equal to the window size.
 * * At any given point in time, the `deque` contains at most `m = window size` elements.
 * * So, the overall auxiliary space complexity is `O(m)` where `m = window size`.
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 *
 * ```
 * Good job! (Max time used: 0.48/2.00, max memory used: 74780672/536870912.)
 * ```
 *
 *
 */
fun findMaxInSlidingWindow(windowSize: Int, itemList: List<Int>): String {
    if (itemList.isEmpty()) return ""
    val results = mutableListOf<Int>()
    val deque = ArrayDeque<Int>()
    for (i in 0..<itemList.size) {
        // Remove all the values from the last that are smaller than the upcoming value.
        while (deque.isNotEmpty() && itemList[deque.last()] <= itemList[i]) {
            deque.removeLast()
        }
        // Add the next index.
        deque.addLast(i)
        // Remove all the indices from the front that are smaller than the starting index of this window.
        while (deque.isNotEmpty() && deque.first() < (i - windowSize + 1)) {
            deque.removeFirst()
        }
        // If it is a valid window size, collect the max value from the front of the deque.
        if (i >= windowSize - 1) {
            results.add(itemList[deque.first()])
        }
    }
    return results.joinToString(" ")
}

fun main() {
    val totalItems = readln().toInt()
    val itemList = readln().split(" ").map { it.toInt() }
    val windowSize = readln().toInt()
    println(findMaxInSlidingWindow(windowSize, itemList))
}