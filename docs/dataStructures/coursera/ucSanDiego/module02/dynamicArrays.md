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



## What is the space complexity of a dynamic array?

* For the best case, it is `O(n)`, where `n` is the number of items.
* For the worst-case, it is `O(2n)`. However, we drop the constants for the complexity analysis.
* So, overall, it is `O(n)` only.