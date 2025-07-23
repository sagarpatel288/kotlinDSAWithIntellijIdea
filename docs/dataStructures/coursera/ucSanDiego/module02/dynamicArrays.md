# References / Resources

* [Reducible: Dynamic Arrays](https://youtu.be/5AllG-i_yto?si=SnwlIy0MStKN3CTD)
* [Reducible: Amortized Analysis](https://youtu.be/Ij7NQ-0mIVA?si=O5O1xvr6XrKuXc2N)
* [122 Videos](https://youtu.be/L8cXZ_4RHt8?si=SpCTF8SHVxBzo2ci)

## What is a dynamic array? How a dynamic array is different from a fixed size static array?

* A fixed size static array has a fixed size capacity.
* We cannot add more items to it than its maximum capacity.
* However, unlike a fixed size static array, a dynamic array can resize itself. 
* It can grow as we add more items, and it can shrink as we remove items.
* Kotlin `ArrayList` is an example of a `Dynamic Array`.

## What is the time complexity of adding more items to a dynamic array?

* For a dynamic array, `Amortized time complexity` of adding a new item is `O(1)`.
* Worst-case time complexity, is `O(n)`.

## What is the difference between the `Amortized` and `Worst-Case` Time Complexity of a dynamic array?

* `Amortized Time Complexity` is an `Average Time Complexity`.
* For example, if we have `n` operations, then `Amortized Cost` is the `cost of those n operations divided by n`.
* In a dynamic array, we double the size of the array when the array hits the maximum capacity.
* Doubling the size is a worst-case, which takes `O(n)` time.
* However, we don't have to double the size frequently.
* So, if we split the cost across worst-case and best-case scenarios, on average, we get `O(1)` time complexity.
* So, the `Amortized` time complexity is less than the expensive operations, and more than the cheap operations.
* The purpose is to shift our focus from the worst-case analysis to average time analysis, 
and give a more realistic analysis.
* 

## How do we resize the array?

* As soon as the array reaches maximum capacity, we create a new array twice the size of the previous array.
* We copy all the items from the previous array and insert each item into the newly created array.
* Similarly, as soon as the number of items becomes less than half the size of the array, we shrink the array 
by creating a new array based on the number of items.
* The purpose is to limit the resize operations in such a way that we get an amortized time complexity of `O(1)`.

## Why do we double the size of an array? Isn't it inefficient? Don't we waste too much space also?

* Other schemes, such as resizing the array by one or n more blocks of memory, take `O(n^2)` time.
* On the other hand, doubling the array size takes `O(n)` in the worst case, which happens very infrequently.
* So, the average time, known as the amortized time of doubling the array, is `O(1)` only.
* For space, when the array reaches its maximum capacity, we double its size. 
* It means that when we double the size, it is half full. So, at max, we get 50% unused allocated memory.
* In terms of Big-O, it is `O(n)`.
* Also, we shrink the array size as soon as we find that the number of items is less than half the size of the array.

## What is the mathematical proof of the Amortized Analysis?

* Let us assume that we start with an array whose maximum capacity is `1` at the moment.

```
              ┌─────┐
   Indices    │     │
──────────────└─────┘
   Elements   │     │
              └─────┘

```

* We add the first item. This is the first insertion. 
* The array is full now.

```
              ┌─────┐
   Indices    │  0  │
──────────────└─────┘
   Elements   │  1  │
              └─────┘

```

* If the number of insertions is denoted by `i`, then `i = 1` made the array full.
* Next, when `i = 2` arrives, we don't have space.
* Notice that for `i = 2`, the total number of insert operations `n = 2`.
* And for `n = 2`, the resize happens at `n - 1` so that we can insert the `nth` element.
* This is going to be an interesting pattern that we always resize the array at `n - 1`.
* Now, we resize the array. We double the size of the array.
* So, the array capacity becomes 2 now.
* We now have the room for `i = 2` (second) operation.
* The insertion number `i = 1` made the array full.
* And for insertion `i = 2`, we have to resize the array. 

```
              ┌─────┌─────┐
   Indices    │  0  │  1  │
──────────────└─────└─────┘
   Elements   │  1  │  2  │
              └─────└─────┘
```

* When `i = 3`, we don't have the room.
* So, `i = 3` forces us to resize the array.
* Notice that for each `ith` operation that forces the resize, `i - 1` is always going to be a `power of 2`.
* For example, `i = 3` forces the resize operation. So, `i - 1 = 3 - 1 = 2`, which is a `power of 2`. 

```
              ┌─────┌─────┐─────┌─────┐
   Indices    │  0  │  1  │  2  │     │
──────────────└─────└─────┘─────└─────┘
   Elements   │  1  │  2  │  3  │     │
              └─────└─────┘─────└─────┘
```

* The insertion operation `i = 4` takes `O(1)`.
* But when `i = 4`, the array becomes full.
* The insertion operation `i = 4` made the array full.

```
              ┌─────┌─────┐─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │
──────────────└─────└─────┘─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │
              └─────└─────┘─────└─────┘
```

* The insertion operation `i = 4` made the array full.
* When `i = 5`, it will force us to resize the array.
* Again, `i = 5` means `n = 5`. And it means the resize happens at `n - 1 = 4` so that we can insert the `nth` element.
* Also, `i = 5` forced the resize operation. And `i - 1 = 5 - 1 = 4` is a `power of 2`.
* Once we resize, the insertion operations from `i = 5` to `i = 8` will take `O(1)` time.

```
              ┌─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │  4  │     │     │     │
──────────────└─────└─────┘─────└─────┘─────└─────└─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │  5  │     │     │     │
              └─────└─────┘─────└─────┘─────└─────└─────└─────┘

```

* When `i = 8`, it will make the array full.
* When `i = 9`, it will force us to resize the array.
* Again, `i = 9` means `n = 9`. The resize happens after `n - 1` so that we can insert the `nth` element.
* Also, `i = 9` forced the resize operation. And `i - 1 = 9 - 1 = 8` is a `power of 2`.
* Once we resize, the insertion operations from `i = 9` to `i = 16` will take `O(1)` time.

```
// i = 8 makes the array full.


              ┌─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │
──────────────└─────└─────┘─────└─────┘─────└─────└─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │
              └─────└─────┘─────└─────┘─────└─────└─────└─────┘

// i = 9 forces resize operation.

              ┌─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┐─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │     │     │     │     │     │     │     │
──────────────└─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │  9  │     │     │     │     │     │     │     │
              └─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘

```

* When `i = 16`, it will make the array full.
* The insertion operation `i = 17` will force the resize operation.
* Again, `i = 17` means `n = 17` and the resize happens at `n - 1` to insert the `nth` element.
* Also, `i = 17` forced the resize operation. And `i - 1 = 17 - 1 = 16` is a `power of 2`.
* Once we resize, the insertion operations from `i = 17` to `i = 32` will take `O(1)` time.

```
// i = 16 makes the array full.

              ┌─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┐─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │  9  │  10 │  11 │  12 │  13 │  14 │  15 │
──────────────└─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │  9  │  10 │  11 │  12 │  13 │  14 │  15 │  16 │
              └─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘

// i = 17 forces the resize operation.

              ┌─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐─────┌─────┐─────┌─────┐─────┌─────┐─────┌─────┐─────┌─────┌─────┌─────┐
   Indices    │  0  │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │  9  │  10 │  11 │  12 │  13 │  14 │  15 │  16 │  17 │  18 │  19 │  20 │  21 │  22 │  23 │  24 │  25 │  26 │  27 │  28 │  29 │  30 │  31 │
──────────────└─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘─────└─────┘─────└─────┘─────└─────└─────└─────┘
   Elements   │  1  │  2  │  3  │  4  │  5  │  6  │  7  │  8  │  9  │  10 │  11 │  12 │  13 │  14 │  15 │  16 │  17 │  18 │  19 │  20 │  21 │  22 │  23 │  24 │  25 │  26 │  27 │  28 │  29 │  30 │  31 │  32 │
              └─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘─────└─────└─────└─────┘─────└─────┘─────└─────┘─────└─────┘─────└─────┘─────└─────└─────└─────┘

```

* **We can observe an interesting pattern here.**

### Which insertion operations made the array full?

* It is when: i = 1, 2, 4, 8, 16, 32, and so on... 

### Which insertion operations force resize operations?

* It is when: i = 3, 5, 9, 17, 33, and so on...
* In other words, each `ith` operation where `i - 1` is a `power of 2` forces the resize operation.

### When does the last resize operation happen to insert the `nth` element?

* The last resize operation happens at `n - 1`.

### Table format:

```
| i (insertion #) 	| i - 1 	| Is i-1 a power of 2? 	| Resize needed? 	|
|:---------------:	|:-----:	|:--------------------:	|:--------------:	|
|        1        	|   0   	|          No          	|       No       	|
|        2        	|   1   	|       Yes (2⁰)       	|       Yes      	|
|        3        	|   2   	|       Yes (2¹)       	|       Yes      	|
|        4        	|   3   	|          No          	|       No       	|
|        5        	|   4   	|       Yes (2²)       	|       Yes      	|
|        6        	|   5   	|          No          	|       No       	|
|        7        	|   6   	|          No          	|       No       	|
|        8        	|   7   	|          No          	|       No       	|
|        9        	|   8   	|       Yes (2³)       	|       Yes      	|
```

### The mathematical pattern:

```
1. It means that all those `i`th insertion operations where `i - 1` is power of 2, force the resize operation.
2. The last resize can happen at `n - 1` to insert the `nth` element.
```  

#### What happens in the resize process?

* We create a new array that is twice the size of the previous one.
* We first copy all the items from the previous array, which takes `O(n)` time.
* We insert the new item for which we had to resize the array, which takes `O(1)` time.
* In other words, we can say that the:
```
The ith insertion operations that force the resize take: 
The insertion cost of all the previous items + 1 (the insertion cost of itself)
= The cost of (i - 1) items + 1 
```
* Now, all the other insert operations that do not force resize take `O(1)` time.
* Only those insertion operations that force a resize take `The cost of (i - 1) + 1`.
* It means:
```
For any insert operation, the cost is:
= Either (The cost of inserting the new item itself + The cost of inserting all the previous items) OR (The cost of inserting the new item itself + 0)   
= Either 1 + (i - 1) OR 1 + 0.
```
* If we denote an insert operation by $c_i$, then the mathematical term is:

$$
c_i = 1 +
\begin{cases}
i - 1 & \text{if } i - 1 \text{ is a power of } 2 \\
0 & \text{otherwise}
\end{cases}
$$

* Now, let us assume that the range of our insert operation is up to `n`.
* So, we have the insert operations from `1` to `n`. 
* We can split these insert operations into two parts.
```
1. All the `ith` insert operations that do not require any extra cost.  
2. All the `i - 1` insert operations that require extra cost.
``` 
* If we combine (add, sum) all the `ith` insert operations that take `O(1)` time, it cannot be greater than `n`.
* So, it is a simple math: We have `n` insert operations, and each insert operation takes `O(1)` time.
* So, the total time of all the insert operations is `O(1 * n)` = `O(n)`.
* We can express this process of combining (addition/summation of) all the `ith` insert operations that take `O(1)` time as below:
* [Prerequisites: Summation/Sigma Notation](https://www.khanacademy.org/math/ap-calculus-ab/ab-integration-new/ab-6-3/a/review-summation-notation)

$$
\sum_{i=1}^{n} 1 = n
$$

* So, that was the sum of all the `ith` insert operations that do not require any extra cost.
* And then we are left with all the `(i - 1)th` insert operations that force the resize operation.
* We can express this process of combining (addition/summation of) all the `(i - 1)th` insert operations as below:

$$
\sum_{\substack{i=1 \\ i-1\ \text{is a power of}\ 2}}^{n} (i-1)
$$

* Note that the cost of inserting itself is already counted in the first part. 
* So, the second part is the sum of all the `(i - 1)th` insert operations that require some extra cost.
* Together, they give the total cost of all the insert operations (from i = 1 to n) as below:

$$
\sum_{i=1}^{n} c_i = \sum_{i=1}^{n} 1 + \sum_{\substack{i=1 \\ i-1\ \text{is a power of}\ 2}}^{n} (i-1)
$$

* Now, we know that the total number of insert operations are `n`.
* So, we cannot exceed `n`.
* And we know that the `ith` operation, where `i - 1` is a `power of 2`, forces the resize.
* So, let us denote (express) it in math:

$$
(i - 1) = 2^j
$$

* So, we can rewrite the total cost expression as below:

$$
\sum_{i=1}^{n} c_i = \sum_{i=1}^{n} 1 + \sum_{\substack{j=0}}^{n} 2^j
$$

* And we also know that the last resize operation happens at `n - 1`.
* So, how many such `power of 2` can we fit (get) in such a way that it does not exceed `n - 1`?

#### How do we find the "Power Of 2" in math when the target is (n - 1)?

* [Prerequisites: Logarithms](https://www.khanacademy.org/math/algebra2/x2ec2f6f830c9fb89:logs/x2ec2f6f830c9fb89:log-intro/a/intro-to-logarithms)
* How do we find "How many powers of 2 make 16"? We use logarithms.

$$
\log_{2}(16) = 4
$$

* So, how do we express the statement "Some powers of 2 that make n - 1"? We use logarithms.

$$
\log_{2}(n - 1)
$$

* It means that we can change the upper limit of the second part and rewrite the total cost expression as below: 

$$
\sum_{i=1}^{n} c_i = n + \sum_{j=0}^{\lfloor \log_{2}(n-1) \rfloor} 2^j
$$

* Now, the second part looks like a geometric series as below:

$$
\sum_{j=0}^{k} 2^j = 2^{k+1} - 1
$$

$$
\text{where k is }\ \lfloor \log_{2}(n-1) \rfloor
$$

* Now, we know that the power of 2, $2^k$ will always be less than or equal to `(n - 1)`.
* So, we can write it as:

$$
2^k <= (n - 1)
$$

* Now, we can multiply both sides by $2^1$ as below:

$$
2^{k} * 2^{1} <= 2^1 (n - 1)
$$

* Which is equivalent to: [Prerequisites: Exponents](https://www.khanacademy.org/math/cc-eighth-grade-math/cc-8th-numbers-operations/cc-8th-exponent-properties/v/exponent-properties-involving-products)

$$
2^{k + 1} <= 2(n - 1)
$$

* Now, it is obvious that the term `2(n - 1)` cannot be greater than `2n`.
* So, for simplicity and convenience, we can say:

$$
2^{k + 1} <= 2n
$$

* So, if we replace the second term with `2n`, then the total cost becomes:

$$
\sum_{i=1}^{n} c_i <= n + 2n
$$

$$
\sum_{i=1}^{n} c_i <= 3n
$$


* Now, the definition of the `Amortized Cost` is: 

```
The cost of the total number of operations divided by the total number of operations
```

* The cost of the total number of operations is at most `3n`, and the total number of operations is `n`.
* So:

$$
\textbf{Amortized Cost} = \frac{\text{Total Cost}}{n} \leq \frac{3n}{n} = 3 = O(1)
$$

## The banker method of Amortized Analysis

![Banker Method Gif](../../../../../res/coursera/ucSanDiego/course02dataStructures/module02/video03bankersMethodForAmortizedAnalysis/030bankerMethodAnimation.gif)

* The banker's method of Amortized Analysis contains the following major points:

* Each insert operation consumes 3 tokens.
    * 1 token to insert the element itself.
    * 1 token as a prepaid, reserved, saved money to be used in case we have to create a new array and move the element 
from the old array to the new array. So, it represents the moving charge or shifting charge that we get and save when we
insert the element initially.
    * 1 token, again as a prepaid, reserved, saved money for a buddy element in case we have to move the buddy element in 
the future from an old array to a new array. So, it covers the moving or shifting charge of a buddy element.
  
### How do we select the buddy element?

$$
\frac{\text{capacity}}{2} \text{ Elements back from the current element}
$$

### Process:

#### Inserting a

* Let us imagine that we have an element "a" to push into an array (Dynamic Array).
* So, we create an array of size 1. 
* And we push the element "a" to the array.
* Remember that each push operation costs 3 tokens.
* So, the first token we have used to insert the element "a" into the array.
* The second token is saved for the future move of the element.
* And there is no buddy element at the moment. The element "a" is the only element in the array.
* So, in this particular case, we lose the third token.
* The array is full now.
* Notice that by the time the array becomes full, each element in the array has enough funds to pay the moving charge. 

#### Inserting b

* The array is full.
* We create a new array of size 2, which is twice the size of the old array.
* And we have to move the old elements from the old array to the new array.
* Now, remember that we have already prepaid the moving charge of the element "a" when we initially inserted it.
* So, we use it and move the old element "a" to the new array.
* Now, we insert the new element "b" to the new array.
* Remember that each insertion costs 3 tokens.
* We use the 1st token to insert the "b" into the array.
* We save 1 token for the future move of the "b".
* We give 1 token to the buddy element, "a", for its future move.
* The array is full.
* Notice that by the time the array becomes full, each element in the array has enough funds to pay the moving charge.

#### Inserting c

* The array is full.
* We create a new array of size 4, which is twice the size of the old array.
* We need to move each old element from the old array to the new array.
* Remember that when we inserted the element "b", it covered the moving charges of itself and its buddy element, "a".
* So, we use it and move "a, b" to the new array.
* We insert the new element "c" and it consumes 1 token.
* We save 1 token for its future move.
* We save 1 token for the future move of the buddy element, "a".
* At this point, "b" does not have any reserved money to cover its moving charge in the future.
* It is looking for a buddy element that covers its moving charge.
* Anyway. The array is of size 4, and we have 3 elements.
* The array can include 1 more element.
* Hopefully, this future element will cover the moving charge of "b".

#### Inserting d

* The array has room to include "d".
* We pay 1 token to insert "d".
* We save 1 token for the future move of "d".
* We save 1 token for the future move of the buddy element, "b".
* Now, all the elements have the required tokens to cover their moving charges.
* The array is full.
* Notice that by the time the array becomes full, each element in the array has enough funds to pay the moving charge. 

#### Inserting e

* The array is full.
* We create a new array of size 8, which is twice the size of the old array.
* We need to move old elements to the new array.
* Do they have the required money to bear and pay the moving charge?
* Yes, they have.
* When we initially inserted the element "c", it covered the moving charges of itself and "a".
* When we initially inserted the element "d", it covered the moving charges of itself and "b".
* So, we use this saved money and move old elements to the new array.
* We insert the new element "e". It consumes 1 token.
* We save 1 token for the future move of "e".
* We save 1 token for the future move of the buddy element, "a".
* The array size is 8 and the inserted elements are: "a, b, c, d, e".
* Each new element that we may insert in the future will cover the moving cost of itself and the buddy element.
* By the time the array becomes full, each element gets enough funds to pay the moving cost.

### Observation:

* Each insertion operation costs 3 tokens, and it is a constant charge.
* This charge can be different for a different data structure.
* We may need to calculate that for a particular data structure.
* But, the point is, it is a constant charge.
* And a constant cost means `O(1)`.

## The Physicist's Method (Potential Method) Of Amortized Analysis

* The idea is that we represent different states of the data structure as different potentials using the `phi function`.
* And by "state of the data structure", we mean any measurable property that changes as we perform operations.
* The most common measurable properties in our case are size and capacity.
* We assume that it is incremental with time.
* For example, initially, the data structure has 0 size, 0 capacity. So, the potential is 0.
* Then, we insert an item into the array. So, the potential becomes 1.
* And so on...
* Hence, we can say that the `phi function` represents a snapshot of the state of the data structure for a 
particular time.

### The rules (assumption) of the potential function:

* The state of the data structure at time `0` is `0`:
- $\Phi(h_0) = 0$

* The state of the data structure at time `t` is always greater than or equal to `0`. It cannot be negative.:
- $\Phi(h_t) \geq 0$

### Intuition:

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

```
Amortized Cost = Actual Cost + Potential Difference 
```

### Mathematical Representation:

#### Part-01: Mathematical Representation:

* Actual Cost is represented as: $$c_t$$
* Old state (Old Potential, a.k.a. "before") is: $$ \Phi(h_{t - 1})$$
* New state (New Potential, a.k.a. "after") is: $$ \Phi(h_t)$$
* Potential difference is: $$ \Phi(h_t) - \Phi(h_{t-1})$$
* Hence, Amortized cost is: $$c_t + \Phi(h_t) - \Phi(h_{t-1})$$

##### Amortized Cost

$$c_t + \Phi(h_t) - \Phi(h_{t-1})$$

* The sum of the actual cost of each operation is (where we have a total of `1` to `n` operations):
  $$
  \sum_{i=1}^{n} c_i 
  $$

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
\sum_{i = 1}^{n} c_i + \phi(h_n) \text{And it is clearly >= } \sum_{i = 1}^{n} c_i
$$

* The last formula effectively says that the sum of the amortized cost of each element is at least the sum of the actual 
cost of each element.
* What we get here is the lower bound.
* In other words, the sum of the amortized cost of each element cannot be less than the sum of the actual cost of each 
element.

#### Part-02: Mathematical Representation:

* Now, at any given point in time, the general formula to represent the potential of the data structure is:

$$
\phi(h) = 2 * size - capacity
$$

##### How did we come up with that formula?

###### Part-01: Potential Formula:

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

###### Part-02: Potential Formula:

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

#### Part-03: Mathematical Representation:

* Now, when we insert an element, we get two possibilities.
* 1. Either we have room in the data structure. So, we don't have to resize the array. 
* 2. Or the array is full. So, we have to resize the array.
* Let us calculate the amortized cost for each case.

##### Case-01: We have the room. We don't have to resize the array.

* What is the formula for calculating amortized cost?

```
Amortized Cost = Actual Cost + Potential Difference
```

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

##### Case-02: We don't have a room. We double the array size.

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

```
Amortized Cost = Actual Cost + Potential Difference
```

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



## What is the space complexity of a dynamic array?

* For the best case, it is `O(n)`, where `n` is the number of items.
* For the worst-case, it is `O(2n)`. However, we drop the constants for the complexity analysis.
* So, overall, it is `O(n)` only.