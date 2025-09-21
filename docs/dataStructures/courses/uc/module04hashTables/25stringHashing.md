# String Hashing

<!-- TOC -->
* [String Hashing](#string-hashing)
  * [ToDo](#todo)
  * [Previously/Prerequisites/References](#previouslyprerequisitesreferences)
  * [Problem Description](#problem-description)
  * [Solution](#solution)
    * [But how do we represent the power of x?: Implementation](#but-how-do-we-represent-the-power-of-x-implementation)
  * [Notation](#notation)
  * [Expression: The Polynomial family of hash functions](#expression-the-polynomial-family-of-hash-functions)
  * [Note](#note)
  * [Extra](#extra)
  * [Part-02: Output of the polynomial hashing as an input to the integer hashing](#part-02-output-of-the-polynomial-hashing-as-an-input-to-the-integer-hashing)
  * [Collision Probability](#collision-probability-)
  * [Running Time](#running-time)
    * [But don't we have a for loop that depends on the input string length?](#but-dont-we-have-a-for-loop-that-depends-on-the-input-string-length)
  * [Interview Questions](#interview-questions)
  * [Next](#next)
<!-- TOC -->

## ToDo

## Previously/Prerequisites/References

* [Hash Tables](05hashTables.md)
* [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md)
* [Load Factor And Rehashing](15loadFactorAndRehashing.md)
* [Formula Of The Universal Hash Family](20formulaOfUniversalFamilyOfHashFunctions.md)

## Problem Description

* We have learned how to convert an input key `x` into a hash code and then map it to an `index` of a hash table.
* But we have seen only the examples where the input key `x` is a number.
* So, we use the [formula of the universal hash family](20formulaOfUniversalFamilyOfHashFunctions.md).
* But what if the input key `x` is a string?
* How do we process it in the formula: $((ax + b) \mod p) \mod m$, where all the other notations (symbols) are numeric except `x`?
* That's what we are going to learn about in this section.
* That is: How to generate a hash code from the input string and map it to the hash table index?
* We call it: String Hashing.

## Solution

* The idea is to treat a string input as a number only.
* For example, `947` is $(9 * 10^2) + (4 * 10^1) + (7 * 10^0)$.
* So, we can treat any whole number as a polynomial with base `10`.
* In the string hashing, we use the numerical value of each character (`ASCII` or `Unicode`, depending upon the type of the character) and instead of using the base `10`, we use a random number, `x`.
* For example, suppose the string is `cat`.
* Now, we use the `ASCII` value of each character. 
* `c = 99`, `a = 97`, and `t = 116`. 
* So, the formula becomes:

$$
\text{hash} = (c.code * x^2) + (a.code * x^1) + (t.code * x^0)
$$

* To limit the output of this formula (to prevent overflow), we use `modulo p`.

$$
\text{hash} \mod p
$$

* The reason behind a random `x` is to generate a random hash function instead of a single, fixed hash function.
* The random `x` creates the universal family of hash functions.

### But how do we represent the power of x?: Implementation

* Although we have a built-in `pow` function, we use the "**Horner's Method**", which is faster. 
* Let us continue our example.

$$
\text{hash of cat} = (c.code * x^2) + (a.code * x^1) + (t.code * x^0)
$$

```kotlin

/**
 * @param cat Input string (key)
 * @param p Modulo to limit the output to prevent integer overflow
 * @param x A random number
 */
fun hashCode(cat: String, p: Int, x: Int): Int {
    var hash = 0
    for (i in cat.length - 1 downTo 0) {
        hash = ((hash * x) + cat[i].code) % p
    }
    // Avoid negative hash value
    if (hash < 0) {
        hash += p
    }
    return hash
}


```

* So, initially, `hash = 0`, and we start with the last character of the string, and then move downwards to the first character of the string. 
* When `i = 2`, we get:

$$
(0 * x^0) + t.code = 0 + t.code = t.code = 116
$$

* When `i = 1`, we get:

$$
(116 * x) + (a.code) = 116x + 97
$$

* When `i = 0`, we get:

$$
((116x + 97) * x) + c.code  
= ((116x + 97) * x) + 99   
= (116 * x^2 + 97 * x + 99)  
$$

* Which is a polynomial representation of the given input string (key), `cat`.

## Notation

* `x` is a random number (In practice, `31` or `257` is common).
* `p` is a large prime number we use to perform modular arithmetic.
* `S` is an input string (key).
* `|S|` is the absolute length of the input string.
* `i` index position.
* `S[i]` A character of string `S` at index position `i`.


## Expression: The Polynomial family of hash functions

$$
\mathcal{P}_p = (\{ h_{p}^{x}(S) = \sum_{i=0}^{|S|-1} S[i]x^i \bmod p )\}
$$


* $P_p$: The polynomial family of the hash functions `P`, based on the prime number `p` (each hash function of this family uses the same large prime number, `p`) is defined as the set (or collection) of...
* $h_{p}^{x}(S)$: The hash function `h` defined by the large prime number `p` and a random number `x`, takes the input string (key), `S`.
* $\sum_{i=0}^{|S| - 1}$: Sum of the following expression where the index position `i` starts from `0` and goes up to the absolute length of the input string `S` minus `1`.
* $(S[i]x^{i})$: The code of the character at index position `i` of the given input string `S`, multiplied by the random number `x` to the power of `i`.
* $\pmod p$: Perform modular arithmetic on the above output using the large prime number `p` to limit (restrict) the output value (to prevent overflow). 

## Note

* The hash function of a string represents the general $(ax + b)$ part of the universal family of hash functions.
* It means that we have a slightly different hash function formula when the input key is a string.
* Otherwise, the $\pmod m$ part of the formula for the universal family of the hash functions remains the same.
* In the [expression](#expression), $S[i]x^i$ indicates that the power of `x` increases as we move forward and cover from left to right, character by character in the string input. 
* But the [implementation](#but-how-do-we-represent-the-power-of-x-implementation) introduces a slightly different order where the power of x increases as we move from right to left character of the string input. 
* However, the purpose, polynomial pattern, properties, and behavior remain the same.
* That is, we take an input string, and our hash function generates a hash code using a polynomial pattern so that when we compress it and map it to a hash table index, we get minimum collisions.

## Extra

* The normal `string.hashCode()` implementation in `java` (and hence, in `kotlin`) uses `x = 31` for some technical reason.

## Part-02: Output of the polynomial hashing as an input to the integer hashing

* The polynomial hash function: 

$$
\mathcal{P}_p = (\{ h_{p}^{x}(S) = \sum_{i=0}^{|S|-1} S[i]x^i \bmod p )\}
$$

* The polynomial hash function for a string is just the first part of the string hashing. 
* Here, `p` is a large prime number.
* So, the result might be an integer between `0` and `p - 1`.
* But we need to compress it according to the hash table size.
* So, the output integer number of our polynomial hash function becomes `x` for the universal family of hash functions.
* The universal family of hash functions:

$$
H_p = \lbrace{ h_{a,b}(x) = ((ax + b) \bmod p) \bmod m \rbrace} \text{ for all } a,b: 1 \le a \le p-1, 0 \le b \le p-1
$$

* So, the overall expression of string hashing is:

$$
h_m(S) = h_{a,b}(h_x(S)) \mod m
$$

## Collision Probability 

* The probability that two different input strings would generate the same output of the [polynomial hashing](#expression-the-polynomial-family-of-hash-functions) is $\frac{L}{p}$, where `L` is the length of the largest string, and `p` is a large prime number.
* We have already seen that the probability of collision in the [universal family of hash functions](10universalFamilyOfHashFunctions.md#universal-family-of-hash-functions) is $\frac{1}{m}$. 
* So, the total probability becomes:

$$
\frac{L}{p} + \frac{1}{m}
$$

* But, if we take $p >= m * L$, then:

$$
\frac{L}{p} = \frac{L}{mL} = \frac{1}{m}
$$

* So, the total probability becomes:

$$
\frac{1}{m} + \frac{1}{m} = \frac{2}{m}
$$

* It is still a constant probability.
* We can represent it as:

$$
O(\frac{1}{m})
$$

## Running Time

* In the [Load Factor](15loadFactorAndRehashing.md#explanation) section, we have learned that the running time of a hash table is directly proportional to the longest chain.
* The longest chain is clearly greater than or equal to the average chain.
* If we have `n` keys, then the average chain length is $\frac{n}{m}$.
* We then consider some constant amount of work that we have to perform anyway, regardless of the input size.
* For example, calculating the hash code, compressing the hash code, accessing the array index, etc.
* If we add that constant time also, then it becomes:

$$
O(1 + \frac{n}{m})
$$

* We take $\alpha = \frac{n}{m}$.
* So, it becomes:

$$
O(1 + \alpha)
$$

### But don't we have a for loop that depends on the input string length?

* While generating an integer from the given input string, we might perform a [for-loop](#but-how-do-we-represent-the-power-of-x-implementation).
* In general, we bound the string limit.
* For example, we do not allow a username to be more than 20 characters.
* In such cases, it is still a small constant.
* But if we have a really long string input, then the running time becomes:

$$
O(L + \alpha) \text { where L is a length of the input string}
$$

* We still have different methods to improve it.
* For example, caching the hash, precomputed prefix hash, etc.
* We will cover these optimization techniques later.

## Interview Questions



## Next