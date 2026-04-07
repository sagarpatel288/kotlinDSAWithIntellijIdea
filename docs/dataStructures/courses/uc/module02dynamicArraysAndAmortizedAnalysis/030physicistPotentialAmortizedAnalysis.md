# Amortized Analysis Of Dynamic Arrays

<!-- TOC -->
* [Amortized Analysis Of Dynamic Arrays](#amortized-analysis-of-dynamic-arrays)
  * [Previously](#previously)
  * [The Physicist's Method (Potential Method) Of Amortized Analysis](#the-physicists-method-potential-method-of-amortized-analysis)
    * [The rules (assumption) of the potential function](#the-rules-assumption-of-the-potential-function)
    * [Intuition](#intuition)
    * [Mathematical Representation](#mathematical-representation)
      * [Part-01: Mathematical Representation: Notations, Relations, And Expressions](#part-01-mathematical-representation-notations-relations-and-expressions)
        * [Amortized Cost](#amortized-cost)
      * [Part-02: Mathematical Representation: The General Potential Formula](#part-02-mathematical-representation-the-general-potential-formula)
        * [How did we come up with that formula?](#how-did-we-come-up-with-that-formula)
          * [Part-01: Potential Formula](#part-01-potential-formula)
          * [Part-02: Potential Formula](#part-02-potential-formula)
      * [Part-03: Mathematical Representation: Amortized Analysis](#part-03-mathematical-representation-amortized-analysis)
        * [Case-01: We have the room. We don't have to resize the array](#case-01-we-have-the-room-we-dont-have-to-resize-the-array)
        * [Case-02: We don't have a room. We double the array size](#case-02-we-dont-have-a-room-we-double-the-array-size)
<!-- TOC -->

## Previously

* [Dynamic Arrays.md](010dynamicArrays.md)

## The Physicist's Method (Potential Method) Of Amortized Analysis

* The idea is that we represent different states of the data structure as different potentials using the `phi function`.
* And by "state of the data structure," we mean any measurable property that changes as we perform operations.
* The most common measurable properties in our case are size and capacity.
* We assume that it is incremental with time.
* For example, initially, the data structure has 0 size, 0 capacity. So, the potential is 0.
* Then, we insert an item into the array. So, the potential becomes 1.
* And so on...
* Hence, we can say that the `phi function` represents a snapshot of the state of the data structure for a
  particular time.

### The rules (assumption) of the potential function

* The state of the data structure at time `0` is `0`:

    * $\Phi(h_0) = 0$

* The state of the data structure at time `t` is always greater than or equal to `0`. It cannot be negative.:

    * $\Phi(h_t) \geq 0$

* However, please note that the difference between the two potential states can be negative, showing a decrease
  (expense, use) in the potential. (Analogy: Spending money.)

### Intuition

* We have learned in the [Banker Method](#the-banker-method-of-amortized-analysis) that each operation has an
  additional charge than the actual charge.
* The actual charge was the cost of inserting the element itself.
* But we pay extra (prepaid) for moving the element itself and for moving a buddy element in the future.
* So, we know that there is some additional cost beyond the actual cost for each operation.
* And we use that extra (additional) cost when we move the element and a buddy element from an old array to a new array.
* In other words, we use that additional cost to move the element and a buddy element from one state to another state.
* Moving an element from one state to another state is a state difference.
* It means that the extra cost is equal to (or corresponds to) the state difference.
* In the potential method, we refer to "state" as "potential".
* So, we refer to "State Difference" as "Potential Difference."
* We continue with this idea (analysis, analogy, concept) and give a formula as below:

> [!Important]
>
> Amortized Cost = Actual Cost + Potential Difference

### Mathematical Representation

#### Part-01: Mathematical Representation: Notations, Relations, And Expressions

* Actual Cost is represented as: $c_t$
* Old state (Old Potential, a.k.a. "before") is: $\Phi(h_{t - 1})$
* New state (New Potential, a.k.a. "after") is: $\Phi(h_t)$
* Potential difference is: $\Phi(h_t) - \Phi(h_{t-1})$
* Hence, Amortized cost is: $c_t + \Phi(h_t) - \Phi(h_{t-1})$

##### Amortized Cost

$$c_t + \Phi(h_t) - \Phi(h_{t-1})$$

* The sum of the actual cost of each operation is (where we have a total of `1` to `n` operations):
  $$\sum_{i=1}^{n} c_i$$

* We have the amortized cost for each element.
* So, the sum of the amortized cost for each element becomes:

$$
\sum_{i = 1}^{n} ({c_i + \phi(h_i) - \phi(h_{i - 1}}))
$$

* If we replace the index`i` with the actual values, we see an interesting pattern.

$$
c_1 + \phi(h_1) - \phi(h_0) + c_2 + \phi(h_2) - \phi(h_1) + c_3 + \phi(h_3) - \phi(h_2) +..+ c_n + \phi(h_n) - \phi(h_{n - 1})
$$

* If we observe the pattern, each subsequent (next, succeeding) operation cancels out something from the previous operation.
* For example:
* When `i = 2`, it has $\\-\phi(h_1)$ and it cancels out the $\phi(h_1)$ of `i = 1`.
* Similarly, `i = 3` has $\\-\phi(h_2)$ and it cancels out the $\phi(h_2)$ of `i = 2`.
* In the end, we are left with:

$$
\\-\phi(h_0) + \phi(h_n)
$$

* And we know that $\phi(h_0)$ is `0`.
* So, we are left with $\phi(h_n)$.
* And of course, we need to count (sum) all the $c_i$.
* So, it becomes:

$$
\sum_{i = 1}^{n} c_i + \phi(h_n) \text{ And it is clearly >= } \sum_{i = 1}^{n} c_i
$$

* The last formula effectively says that the sum of the amortized cost of each element is at least the sum of the actual
  cost of each element.
* What we get here is the lower bound.
* In other words, the sum of the amortized cost of each element cannot be less than the sum of the actual cost of each
  element.

#### Part-02: Mathematical Representation: The General Potential Formula

* Now, at any given point in time, the general formula to represent the potential of the data structure is:

$$
\phi(h) = 2 * size - capacity
$$

##### How did we come up with that formula?

###### Part-01: Potential Formula

* According to the [potential theory rules](#the-rules-assumption-of-the-potential-function), we want to ensure
  the positive and incremental potential.
* The formula ensures a couple of things as follows:
* Initially, the potential is zero.

$\phi(h) = 2 * size - capacity$

$\phi(h_0) = 2 * 0 - 0 = 0 >= 0$

* Initially, there is no element in the array. So, `size = 0`.
* When there is no element, we don't keep the data structure in memory. So, `capacity = 0`.
* And our potential function satisfies this condition that initially, the potential will be `0`.
* The potential will not be negative.
* The formula confirms that the `size` does not exceed the `capacity`.
* As the `size` increases, the `potential` increases as well.
* The factor of `2` on `size` represents `extra charge` to each element for each insert operation.
* The "Subtracting capacity" represents the idea that we `use the reserved money` as we resize the array.
* So, it is not like we keep charging (and saving) extra cost (potential) for each insert operation and never use it.
* We use it when we resize the array, when we create a new array twice the size of the old array.
* This usage is a subtraction (deduction) in our saved (reserved) money (or potential) at the time of resizing the array.
* And how much do we use? It is exactly equivalent to the `old capacity`.
* When the array is full, and we get a new element to insert, we create a new array, and we have to move each old
  element to the new array.
* How many elements do we move? We move elements equal to the value of the `old capacity`.
* Each shifting charges 1 token. And the number of elements we move is equal to the `capcity`.
* So, the money we spend on shifting is `1 * capacity` = `capacity`.
* We need to deduct that from our savings (or `potential` in this case).

###### Part-02: Potential Formula

* We will take the minimum and the maximum size to understand the potential formula.

> Minimum Size:

* At any given point in time, the `size` cannot be less than $\frac{capacity}{2}$.
>
* For example, when the array size is `2`, and if we want to insert the third element, we create a new array of twice
  the size of the old array.
* Then, we insert the element for which we had to resize the array, and the number of elements we get is `3`,
  and the `capacity` is `4`.
>
* Here, we can clearly see that `size` is at least $\frac {capacity}{2} + 1$.
>
* If we replace the `size` with $\frac {capacity}{2} + 1$, what we get is:

$$
2 * \frac {capacity}{2} + 1 - capacity = 1 >= 0
$$

* It means that it satisfies the condition that at any given point in time, the potential will never be negative.

> Maximum Size:

* The size is maximum when it is equal to the capacity.
* Also, it is easy to understand that when `size` is equal to the `capacity`, it represents the highest potential.
* Again, we can replace the `size` with `capacity` and it becomes:

$$
(2 * capacity) - capacity = capacity >= 0
$$

> Conclusion:

* So, the potential function satisfies the rules.
* The initial potential is zero.
* Even at the time of minimum or maximum size, the potential is not negative.

#### Part-03: Mathematical Representation: Amortized Analysis

* Now, when we insert an element, we get two possibilities.
    * Either we have room in the data structure. So, we don't have to resize the array.
    * Or the array is full. So, we have to resize the array.
* Let us calculate the amortized cost for each case.

##### Case-01: We have the room. We don't have to resize the array

* What is the formula for calculating amortized cost?

> [!Important]
>
> Amortized Cost = Actual Cost + Potential Difference

$$
c_i + (\phi(h_i) - \phi(h_{i - 1}))  
$$

* What is the cost of inserting an element without resizing or shifting? 1.
* So, $c_i = 1$.

$$
1 + (\phi(h_i) - \phi(h_{i - 1}))
$$

* What is the general potential formula?

$$
\phi(h) = 2 * size - capacity
$$

* Applying the general potential formula to each potential state:

$$
1 + (2 * size_i - capacity_i) - (2 * size_{i - 1} - capacity_{i - 1}))
$$

* Do we increase the capacity? No.
* It means that there is no difference in the capacity.
* So, the difference in capacity cancels itself out.
* So, we are left with:

$$
1 + (2 * size_i) - (2 * size_{i - 1})
$$

* Now, we can rewrite:

$$
(2 * size_i) - (2 * size_{i - 1}) = 2 * (size_i - size_{i - 1})
$$

* Hence, we can rewrite the expression as below:

$$
1 + 2 * (size_i - size_{i - 1})
$$

* What is the difference in size? 1. Because we add one element. So, we increase the size by 1.
* So, it becomes:

$$
1 + 2 * (1) = 3 = \text{A Constant Time, The exact value we got in the Banker's Method!}
$$

##### Case-02: We don't have a room. We double the array size

* When do we resize? When the array is full = it means that the size is equal to the capacity.
* So, initially we have `size = capacity.`
* Then, we double the capacity.
* So, when `size = capacity,` it is the `before state`.
* And how do we represent the `before (previous) state`? It is `i - 1`.
* So:

$$
size_{i - 1} = capacity_{i - 1}
$$

* Let us denote this equality by `k`.
* So, it becomes:

$$
k = size_{i - 1} = capacity_{i - 1}
$$

* And what is the amortized cost?

> [!Important]
>
> Amortized Cost = Actual Cost + Potential Difference

* So, it becomes:

$$
c_i + \phi(h_i) - \phi(h_{i - 1})
$$

* And, what is the general potential expression?

$$
\phi(h) = 2 * size - capacity
$$

* So, it becomes:

$$
c_i + (2 * size_i - capacity_i) - (2 * size_{i - 1} - capacity_{i - 1})
$$

* We know that for the previous (before) state, `size = capacity`.
* And we have taken $k = size_{i - 1} = capacity_{i - 1}$.
* So, we can rewrite the expression as:

$$
c_i + (2 * size_i - capacity_i) - (2 * k - k)
$$

* $2k - k = k$. So, it becomes:

$$
c_i + (2 * size_i - capacity_i) - k
$$

* Now, what is the new size $size_i$ after we add the element?
* Well, it becomes the `old size + 1` = `k + 1`.
* Because we have just added one element to the old size.
* So, we can replace the `new size` $size_i$ with $k + 1$.
* Hence, the expression becomes:

$$
c_i + (2 * (k + 1) - capacity_i) - k
$$

* Now, what is the new capacity after we double the array size?
* Well, it is 2 * old capacity.
* How did we represent the old capacity? It is `k`.
* Recall that $size_{i - 1} = capacity_{i - 1} = k$.
* So, the new capacity, $capacity_i = 2 \text{ old capacity} = 2k$.
* Hence, we can rewrite the expression as:

$$
c_i + (2 * (k + 1) - 2k) - k
$$

* Now:

$$
2 * (k + 1) - 2k = 2k + 2 - 2k = 2
$$

* So, the expression becomes:

$$
c_i + 2 - k
$$

* Now:

$$
c_i = \text{ The cost of moving } \\size_{i - 1} \text{ elements} + \text { The cost of inserting the new element, which is 1}
$$

* So, basically, it is the cost of $size_i$ elements.
* Because when we add one element to the previous size $size_{i - 1}$, it becomes the new size, $size_i$.
* As $size_i = size_{i - 1} + 1$, we can rewrite the expression as:

$$
c_i = size_i
$$

* So, the expression becomes:

$$
size_i + 2 - k
$$

* And we know that $size_i = size_{i - 1} + 1 = k + 1$.
* So, we can rewrite the expression as:

$$
(k + 1) + 2 - k  = 3 \text{ (Again, the same constant time that we had calculated using the Banker's Method!)}
$$