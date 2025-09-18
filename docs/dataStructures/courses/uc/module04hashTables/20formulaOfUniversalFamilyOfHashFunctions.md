# Formula Of The Universal Family Of Hash Functions

<!-- TOC -->
* [Formula Of The Universal Family Of Hash Functions](#formula-of-the-universal-family-of-hash-functions)
  * [Previously, prerequisites, references](#previously-prerequisites-references)
  * [Warm-up](#warm-up)
  * [Notation](#notation)
  * [Expression](#expression)
  * [Example](#example)
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
* $h_{a, b}(x)$ means a hash function (from the hash family) that processes the input key, `x` using two parameters (or variables) `a` and `b` of random values.
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

## Next
