# Any other factor than two for resizing a dynamic array

<!-- TOC -->
* [Any other factor than two for resizing a dynamic array](#any-other-factor-than-two-for-resizing-a-dynamic-array)
  * [Previously](#previously)
  * [Why is any other factor other than two not optimal?](#why-is-any-other-factor-other-than-two-not-optimal)
    * [How did we get this average cost per insertion formula?](#how-did-we-get-this-average-cost-per-insertion-formula)
      * [The Geometric Series](#the-geometric-series)
    * [The Conclusion](#the-conclusion)
<!-- TOC -->

## Previously

* [Dynamic Arrays](010dynamicArrays.md)
* [Banking Amortized Analysis.md](020bankingAmortizedAnalysis.md)
* [Physicist Potential Amortized Analysis.md](030physicistPotentialAmortizedAnalysis.md)

## Why is any other factor other than two not optimal?

* The expression of average cost per insertion is:

$$
\frac {C}{C - 1}
$$

* It is clear that only when `C = 2` we get the minimum value.

### How did we get this average cost per insertion formula?

* We already know that:

$$
\text{Average Cost} = \frac{\text{Total Cost of n operations}}{n}
$$

* And we also know that:

$$
\text{Total cost of n operations} = \text{Total cost of each cheap operation + Total cost of each expensive operation}
$$

* Now, we know that:

$$
\text{Total cost of each cheap operation} <= n
$$

* And for the total cost of each expensive operation, we have already seen that each resize (expensive) operation
  happens at `power of constant factor (multiplication factor)` where the `constant` is the factor we use to resize the
  array. [Reference: Resize frequency](#which-insertion-operations-force-resize-operations).
* It means that each resize operation happens at:

$$
C^0 + C^1 + C^2 + C^3 + \dots + C^m \text{ where } C^m <= n
$$

> Why $C^m <= n$?

* Because the last resize operation might happen to insert the `nth` element.
* And we first resize, and then insert the `nth` element.
* So, it is obvious that the last resize operation must happen before we insert the `nth` element.
* It means that the last resize operation, $C^m$ is less than `n`.

#### The Geometric Series

* Now, the pattern of resize frequency is a geometric series.

$$
S = 1 + C + C^2 + \dots + C^m = \frac{C^{m+1} - 1}{C - 1}
$$

* We know that:

$$
C^m <= n \text { Because it is the last resize operation}
$$

* So: The next operation:

$$
C^{m + 1} \text{ goes beyond n}
$$

* So:

$$
C^{m + 1} > n
$$

* If we combine these two relationships, it becomes:

$$
C^m <= n < C^{m + 1}
$$

* It is interesting to notice that either $C^m$ or $C^{m + 1}$ must be near around $n$.
* So, even if we take the largest one, we can evaluate them as:

$$
\mathcal{C}^{m+1} = \mathcal{C}^{m} * {C}^{1} \approx \mathcal{C}^{1} * n \quad (\text{since } n \text{ is close to } \mathcal{C}^m).
$$

* Hence, the simplification of the geometric series becomes:

$$
\frac{\mathcal{C}n - 1}{\mathcal{C} - 1}
$$

* It is obvious that `-1` of the $C_n - 1$ is negligible. So, we can avoid it.
* Hence, it becomes:

$$
\text{Total cost of each expensive operation} = \frac{\mathcal{C}n}{\mathcal{C} - 1}
$$

* So, the total cost becomes:

$$
\text{Total Cost } = n + \frac{\mathcal{C}n}{\mathcal{C} - 1}
$$

* We can simplify it as below:

$$
\text{Total Cost } = n + \left (\frac{\mathcal{C}}{\mathcal{C} - 1} \right)n
$$

* So, the average cost is:

$$
\text {Average Cost} = \frac{\text{ Total Cost of n operations}}{n}
$$

$$
\text{=} \frac{n + \left (\frac{\mathcal{C}}{\mathcal{C} - 1} \right)n}{n}
$$

$$
\text{=} \frac{n}{n} + \frac{\left (\frac{\mathcal{C}}{\mathcal{C} - 1} \right)n}{n}
$$

$$
\text{= } 1 + \left (\frac{\mathcal{C}}{\mathcal{C} - 1} \right)
$$

$$
\approx \left (\frac{\mathcal{C}}{\mathcal{C} - 1} \right)
$$

### The Conclusion

* Any constant factor forms a beautiful geometric series and forms the ultimate formula of the average cost as
  $\frac{C}{C - 1}$.
* Using `C = 2` here gives an ultimate balance between the number of resize operations we have to perform and the
  unused allocated space.
* Hence, `2` is the most optimal factor of the resizing. ❤️