# String Hashing

<!-- TOC -->
* [String Hashing](#string-hashing)
  * [ToDo](#todo)
  * [Previously/Prerequisites/References](#previouslyprerequisitesreferences)
  * [Problem Description](#problem-description)
  * [Solution](#solution)
    * [But how do we represent the power of x?](#but-how-do-we-represent-the-power-of-x)
  * [Notation](#notation)
  * [Expression](#expression)
  * [Note](#note)
  * [Extra](#extra)
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

### But how do we represent the power of x?

* Although we have a built-in `pow` function, we use the "**Horner's Method**", which is faster. 
* Let us continue our example.

$$
\text{hash of cat} = (c.code * x^2) + (a.code * x^1) + (t.code * x^0)
$$

```kotlin

/**
 * @param cat Input string (key)
 * @param p Modulo to limit the output according to the hash table size
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
* When `i = 0`, we get:

$$
(0 * x^0) + t.code = 0 + t.code = t.code = 116
$$

* When `i = 1`, we get:

$$
(116 * x) + (a.code) = 116x + 97
$$

* When `i = 2`, we get:

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


## Expression

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

## Extra

* The normal `string.hashCode()` implementation in `java` (and hence, in `kotlin`) uses `x = 31` for some technical reason.

## Next