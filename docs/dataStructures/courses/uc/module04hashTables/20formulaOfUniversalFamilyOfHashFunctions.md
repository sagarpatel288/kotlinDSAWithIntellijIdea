# Formula Of The Universal Family Of Hash Functions

<!-- TOC -->
* [Formula Of The Universal Family Of Hash Functions](#formula-of-the-universal-family-of-hash-functions)
  * [Previously, prerequisites, references](#previously-prerequisites-references)
  * [Warm-up](#warm-up)
  * [Notation](#notation)
  * [Expression](#expression)
  * [Example](#example)
  * [Interview Questions](#interview-questions)
    * [If we have integer keys up to 10 digits, what will be a good prime number `p` for a hash function?](#if-we-have-integer-keys-up-to-10-digits-what-will-be-a-good-prime-number-p-for-a-hash-function)
    * [If we have integer keys from `-100` to `100`, then what will be a good prime number `p` for a hash function?](#if-we-have-integer-keys-from--100-to-100-then-what-will-be-a-good-prime-number-p-for-a-hash-function)
    * [Why do we have these variables `a` and `b` with a specific range, and the prime number `p` greater than the input key `x`?](#why-do-we-have-these-variables-a-and-b-with-a-specific-range-and-the-prime-number-p-greater-than-the-input-key-x)
  * [ToDo](#todo)
  * [Next](#next)
<!-- TOC -->

## Previously, prerequisites, references

* [Hash Tables](05hashTables.md)
* [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md)
* [Load Factor And Rehashing](15loadFactorAndRehashing.md)

## Warm-up

* We have learned that a [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md) helps avoid the targeted bad input. 
* How do we come up with such a universal family?
* Is there any formula?
* Yes. There is a formula to create (build) the universal family of hash functions.
* We are going to study the same.

## Notation

* `h`: A hash function
* `x`: Input key
* `m`: Cardinality
* `p`: A prime number that is greater than `x`.
* `a` and `b`: Two randomly selected numbers where $1 \leq a \leq p - 1$, and $0 \leq b \leq p - 1$.
* It means `a` must be greater than or equal to `1`, but less than or equal to `p - 1`.
* Similarly, `b` must be greater than or equal to `0`, but less than or equal to `p - 1`.

## Expression

$$
h(x) = ((ax + b) \mod p) \mod m
$$

* `x` is the input key.
* `p` is the prime number that is greater than `x`.
* `a` and `b` are random parameters.
* `a` can be anything between `1` and `p - 1`.
* When the upper bound is `p`, we can say that there are a total of `p` choices.
* As the parameter `a` can take any number except `0` from this range `p`, we can say that the parameter `a` has `p - 1` choices. 
* The parameter `b` can be anything between `0` and `p - 1`.
* The parameter `b` has the full range of choice.
* So, we can say that the parameter `b` has `p` choices.
* So, we get `p(p - 1)` combinations `(a, b)`.
* It means that the universal family of hash functions `H`, contains `p(p - 1)` unique hash functions.
* Each choice of `(a, b)` gives a unique hash function, $h_{a, b}$.
* The family of these hash functions is the universal family, `H`.
* The design of the hash family guarantees that when we select a random hash function, the collision probability for a fixed pair of keys is less than or equal to $\frac{1}{m}$.
* The universal family of such hash functions, `H` is:

$$
H_p = \lbrace{ h_{a,b}(x) = ((ax + b) \bmod p) \bmod m \rbrace} \text{ for all } a,b: 1 \le a \le p-1, 0 \le b \le p-1
$$

* $H_p$ means `H` is the set of hash functions or the universal family of hash functions that is defined by (we are talking about the particular set or family that uses) modular arithmetic with prime `p`.
* $\lbrace{....\rbrace}$ indicates a set (collection) of hash functions. 
* $h_{a, b}(x)$ means a hash function (from the hash family) that processes the input key `x`, using two parameters (or variables) `a` and `b` of random values.
* $(a * x + b) \mod p$ indicates the main (core) general hash function formula (the calculation) that generates a hash code.
* $\mod m$ indicates that we compress and map the generated hash code to the hash table index using `modulo m`, where `m` is the hash table size (cardinality).   
* $1 \leq a \leq p-1, \; 0 \leq b \leq p-1 \;$ indicates the range (limit) of parameters (variables) `a` and `b`.   

## Example

* `m` = `5`. So, indices `0 to 4`.
* `n` = `10` (the total number of keys we plan to insert).
* `p` = `13`.
* `a` = random between 1 and 12 = `3`.
* `b` = random between 0 and 12 = `7`.
* The random hash function is: $h(x) = ((3x + 7) \mod 13) \mod 5$.
* if `x` = 10, then $h(10) = ((30 + 7) \mod 13) \mod 5 = (11 \mod 5) = 1$.
* The input key `x = 10` gets the index `1`.

## Interview Questions

### If we have integer keys up to 10 digits, what will be a good prime number `p` for a hash function?

* 10000000003, because the prime number must be greater than the input key.

### If we have integer keys from `-100` to `100`, then what will be a good prime number `p` for a hash function?

* 211, because first we add `100` to each integer key to make it a positive number.
* We make each integer key a positive number because we want to ensure that the result of our hash function is not negative.
* We keep the result of a hash function positive because we map it to an index of the hash table, and an index cannot be negative.
* So, the key with the highest value is `200`.
* And we take the prime number that is greater than the input key.
* So, we take `p = 211`.

### Why do we have these variables `a` and `b` with a specific range, and the prime number `p` greater than the input key `x`?

**Why do we have these variables `a`, `b`, and `p`? How do they help?**

* It gives a finite prime field, multiplicative inverse, randomized permutations, random shifts, pairwise independence, linear transformation, uniform distribution, and minimum collisions.
* //ToDo: Explain each term with examples.
* 

**How does it give minimum collisions?**

* Collision means:

$$
(ax + b) \equiv (ay + b) \mod p 
$$

* Cancel out `b`

$$
a(x - y) \equiv 0 \mod p
$$

* Since, $x \neq y$, we can say that $x - y \neq 0$.  
* Because, $p$ is a prime, $(x - y)$ has inverse $\mod p$. (\\ToDo: I believe that to understand this line, we first need to understand the term "Multiplicative inverse in the finite field of prime $p$.")   
* So, when we multiply both sides by inverse:

$$
a \equiv 0 \mod p
$$

* But it is possible only when $a = 0$.
* That's why, we exclude $a = 0$.
* So, we can say that at most one choice of `a` can cause a collision. (\\ToDo: I did not understand this line!)
* Now, we know that the total possible values of `a` are `p - 1`.
* So, the probability of collision is: $<= {1 \over p}$.
* After $\mod m$, the probability of collision is: $<= { 1 \over m }$.

**Why do we take `p` as a prime number?**

* If `p` was a composite number, then we could have more collisions. (\\ToDo: How? Examples?).
* Zero divisors exist. (\\ToDo: What is that? Examples?)
* $a(x - y) \equiv 0 \mod p$ will have multiple solutions. (\\ToDo: How? Examples?)
* It increases the probability of collision.
* Prime number ensures uniqueness. (\\ToDo: How? Examples?).

**Why do we keep `p > x`?** 

* Because if `p <= x`, then we get more collisions.
* For example, suppose the maximum possible `x` is `100`.
* Now, if `p <= 100`, suppose `p = 10`.
* Then:

```markdown

x = 10, x % p = 10 % 10 = 0
x = 20, x % p = 20 % 10 = 0
x = 30, x % p = 30 % 10 = 0
... and so on...
```

* We can see many collisions.
* Now, suppose `p > 100`, suppose `p = 101`.
* Then:

```markdown

x = 10, x % p = 10 % 101 = 10
x = 20, x % p = 20 % 101 = 20
x = 30, x % p = 30 % 101 = 30
... and so on...
```

* So, if `p > x`, and when `p` is a prime number, then we can preserve the original value of `x`.

**Why do we have the variables `a` and `b`?**

* Because without `a` and `b`, the equation becomes `x mod p`.
* It means that all the values of `x` that are divisible by `p` will map to the same index in the hash table.
* In other words, all the values of `x` that are some multiple of `p` will map to the same index in the hash table.
* For example: Suppose, `p = 11`.

```markdown

x = 1, x % p = 1 % 11 = 1.
x = 12, x % p = 11 % 11 = 1.
x = 23, x % p = 23 % 11 = 1.
x = 34, x % p = 34 % 11 = 1.
... and so on...
```

* To avoid such a situation, we take the multiplier `a` and the offset `b`.

**Why does `ax mod p` where `p` is a prime number which is greater than the maximum possible `x` not work?**

* //TODO: Answer with examples.

**Why does `1 <= a <= p - 1`, and `0 <= b <= p - 1`?**

* If we only use `p`, then it becomes `x mod p`, which is predictable.
---
* If `a = 0`, then `h(x) = (0 * x + b ) % p = b % p`.
* It means irrespective of the value of `x`, the value of `h(x)` will be the same for all values of `x`.
* Every value of `x` will map to the same index in the hash table.
* It is a very bad hash function.
---
* Having `1 <= a <= p - 1` ensures a permutation, where each value of `x` maps to a unique output.
* This helps in uniform distribution.
---
* Having `0 <= b <= p - 1` acts as an offset (shift).
* So, the total number of possible combinations of `a` and `b` is `p * (p - 1)`.
* It reduces the collision probability of two different values of `x`.

## ToDo

* Open Vs. closed addressing
* Linear Probing
* Quadratic Probing
* Division Method
* Multiplication Method
* Then, Universal Method
* Double Hashing
* Answer: Why primes are used in hash functions?

## Next

* [String Hashing](25stringHashing.md)
* [Find A Substring](30findSubstring.md)
* [Hash Questions](35hashQuestions.md)
* [Hashing In Blockchain](40hashingInBlockchain.md)
* [Precomputed Prefixed Hashes](45precomputedPrefixHashes.md)
* [String Hashing Revision](50stringHashingRevision.md)
* [Relevant DSA Problems](60relevantDsaProblems.md)