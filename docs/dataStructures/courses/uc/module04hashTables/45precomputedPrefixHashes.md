# Precomputed Prefixed Hashes: Storing incremental substrings

<!-- TOC -->
* [Precomputed Prefixed Hashes: Storing incremental substrings](#precomputed-prefixed-hashes-storing-incremental-substrings)
  * [Prerequisites](#prerequisites)
  * [Explanation](#explanation)
    * [Range-Sum Query Example](#range-sum-query-example)
    * [Precomputed Prefixed Hashes](#precomputed-prefixed-hashes)
      * [Example](#example)
  * [Double Hashing](#double-hashing)
  * [TL;DR Summary](#tldr-summary)
  * [Comparison with the Rabin-Karp's fixed-window rolling hashing Technique](#comparison-with-the-rabin-karps-fixed-window-rolling-hashing-technique)
  * [Applications](#applications)
  * [Next](#next)
<!-- TOC -->

## Prerequisites

* [Hash Tables](05hashTables.md)
* [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md)
* [Load Factor And Rehashing](15loadFactorAndRehashing.md)
* [Formula Of Universal Family Of Hash Functions](20formulaOfUniversalFamilyOfHashFunctions.md)
* [String Hashing](25stringHashing.md)
* [Find A Substring](30findSubstring.md)
* [Hash Questions](35hashQuestions.md)
* [Hashing In Blockchain](40hashingInBlockchain.md)

## Explanation

### Range-Sum Query Example

* Remember the [Range sum query problem](../../../../../src/courses/uc/course01algorithmicToolbox/module02AlgorithmicWarmUp/100RangeSumQueryImmutable.kt)?

![rangeSumPrefixedSum.png](../../../../../res/courses/uc/course01algorithmicToolbox/module02AlgorithmicWarmUp/rangeSumPrefixedSum.png)

* We have an `array`: `[3, 5, 4, 2, 1, 9]`
* We will get a range of indices. 
* For example: `(2, 4)` where `2` and `4` are the indices.
* We need to give the sum of the elements between these given indices (indices are inclusive).
* For example: The sum of the range `(2, 4)` is `4 + 2 + 1 = 7`.
* A naive approach would use a `for loop` for every range-sum query.
* A better approach is to use the `precomputed prefixed sum`.
* We take another array and store the incremental (accumulative) sum.
* Let us call it a `prefixed` array.
* For example: 
* Initially: `prefixed[0] = 0`. 
* Then, `prefixed[1] = prefixed[0] + array[0] = 0 + 3 = 3`.
* Then, `prefixed[2] = prefixed[1] + array[1] = 3 + 5 = 8`.
* Then, `prefixed[3] = prefixed[2] + array[2] = 8 + 4 = 12`.
* Then, `prefixed[4] = prefixed[3] + array[3] = 12 + 2 = 14`.
* Then, `prefixed[5] = prefixed[4] + array[4] = 14 + 1 = 15`.
* Then, `prefixed[6] = prefixed[5] + array[5] = 15 + 9 = 24`.
* Now, we can find any range-sum-query in `O(1)` time!
* The formula is: `range(l, r) = prefixed[r + 1] - prefixed[l]`.
* For example: To find the sum of the range `(2, 4)`, we will do the following:
* `(2, 4) = prefixed[5] - prefixed[2] = 15 - 8 = 7`

### Precomputed Prefixed Hashes

* Similar to the [Range-Sum Query](#range-sum-query-example) idea, we store incremental string hashing.
* However, we know that a `string hashing` uses a `polynomial hashing`.
* So, the formula is different.
* Also, the purpose of `precomputed prefixed hashes` is to provide `string hashing` of any substring query in `O(1)`.
* We get a `Text` string (similar to `array` in the [Range-sum query](#range-sum-query-example)).
* Then, we get `n` queries.
* Each query contains: `(a, b, l)`. 
* Here, `a` is the starting index of a substring 1, `b` is the starting index of a substring 2, and `l` is the length of each substring.
* Now, we need to compare whether these two substrings are equal.
* A naive approach would process each substring separately and compare them.
* That would take `O(l)` for each query, as the length of each substring in a particular query is `l`.
* It means that the total time would be `O(n * l)`.
* We can do it in a better way.
* We take an array, called `prehash`.
* We treat each index of the `prehash` as a length.
* So, `prehash[0] = length 0`, `prehash[1] = length 1`, `prehash[2] = length 2`, and so on...
* And as we have seen in the [Find Substring](30findSubstring.md#recurrence-of-a-polynomial-hash-function-of-a-string), the power of base plays a vital role in string hashing.
* So, we take a separate array called `powersOfBase`.
* Here, we treat each index as a power of the base.
* So, `powersOfBase[0] = x^0`, `powersOfBase[1] = x^1`, `powersOfBase[2] = x^2`, and so on...
* Now, any substring has a starting index and an end index.
* And the formula for getting the hashcode of any substring is:
$$
hash = (prefixHash[a + l] - prefixHash[a] * powersOfBase[l]) \pmod p
$$
* In this way, we get the hashcode of each substring in `O(1)` time!
* For a total of `n` queries, it takes `O(n)` time!

#### Example

* Suppose the `Text` string is "`abacaba`".
* We want to find the hash code of the substring "`caba`".
* Now, for "`caba`", starting index, `a = 3`, and length `l = 4`.
* It means that `prefixHash[a + l] = prefixHash[3 + 4] = prefixHash[ 7 ]`.
* As we know, the index of `prefixHash` represents the length of the substring starting from index `0`.
* It means that `prefixHash[7] = abacaba`.
* We know the [String Hashing](25stringHashing.md).
* So, the polynomial hashing of "`abacaba`" looks like below:

$$
H(abacaba) = (a * x^6) + (b * x^5) + (a * x^4) + (c * x^3) + (a * x^2) + (b * x^1) + (a * x^0)
$$

* Similarly, as per the [formula](#precomputed-prefixed-hashes), we also need `prefixHash[a] = prefixHash[3] = aba`.

$$
H(aba) = (a * x^2) + (b * x^1) + (a * x^0)
$$

* And as per the [formula](#precomputed-prefixed-hashes), we also need `powersOfBase[l] = powersOfBase[4] = x^4`
* Applying the [formula](#precomputed-prefixed-hashes)

$$
H(a, l) = (\; H(a + l) - H(a) * x^l \;) \pmod p
$$

$$
= (a * x^6) + (b * x^5) + (a * x^4) + (c * x^3) + (a * x^2) + (b * x^1) + (a * x^0) - \left((\; (a * x^2) + (b * x^1) + (a * x^0) \;)\right) \; x^4 \pmod p
$$

$$
= (a * x^6) + (b * x^5) + (a * x^4) + (c * x^3) + (a * x^2) + (b * x^1) + (a * x^0) - (a * x^6) - (b * x^5) - (a * x^4) \pmod p 
$$

$$
= (c * x^3) + (a * x^2) + (b * x^1) + (a * x^0) \pmod p
$$

* Which is exactly the polynomial hash of the substring "`caba`" of the original `Text` string "`abacaba`".

## Double Hashing

* We have seen in the [Rabin-Karp Find-Match Pattern](30findSubstring.md) that after the hash codes of two substrings match, we double-check by the manual `areEqual` method to confirm if the substrings are truly a match or not.  
* We have to do that manual check due to the collision probability.
* We can reduce that collision probability even further and avoid the manual `areEqual` method.
* We use two different `prime` numbers and calculate the hash code of each substring using both `prime` numbers.

$$
\text{Hash code } h_1 \text{ of substring } S_1 = h_1(S_1) \text{ Using prime1}
$$

$$
\text{Hash code } h_2 \text{ of substring } S_1 = h_2(S_1) \text{ Using prime2}
$$

* Each substring gets two different hash codes.
* Now, it is extremely rare (almost impossible) to have a "false alarm" where two different hash codes of the two different substrings match, but the substrings are still different - this can almost never happen.
* In other words, if two different hash codes of a substring $S_1$ match with the corresponding hash codes of a substring $S_2$, we can safely say that these substrings are equal. 
* So, if $h_1(S_1) == h_1(S_2) \;\text{ && }\; h_2(S_1) == h_2(S_2)$, we can safely say that these two substrings (or strings) $S_1$ and $S_2$ are indeed equal.
* This technique is known as double-hashing.
* The trade-off is that we avoid the manual `areEqual` method, where we have to iterate and cover each character up to the length of the given substring (or string).
* If the length of these substrings or strings is very large, it consumes a lot of time.
* To avoid that, we use extra space.
* ToDo: Maybe we need to explain more, give some examples, maybe visuals. Maybe we need to improve this section.

## TL;DR Summary

* `a` = Starting index of a string or a substring
* `l` = Length of a string or a substring
* `prefixedHash[i]` = Polynomial string hash of length `i` (We treat an index of the `prefixedHash` as a length of a string or a substring)
* `powersOfBase[i]` = Power (degree) of the base `x` (A random prime number. E.g., `263` or `31`).
* Get the polynomial string hash of any substring `(a, l)` as below:

$$
H(a, l) = \left(\;prefixedHash[a + l] - (prefixedHash[a] * x^l)\;\right) \pmod p
$$

## Comparison with the Rabin-Karp's fixed-window rolling hashing Technique

* We might think that the Rabin-karp's fixed-window rolling hashing technique also gives us the hash code of any substring of length `l` in $O(1)$ time.
* Then, why should we use the `prefixedHash` array?
* Well, the answer is that the Rabin-Karp's fixed-window rolling hashing technique relies on the fact that the length of the substring (`l`) is fixed.
* Whereas, the `prefixedHash` array allows us to retrieve any substring of any length in $O(1)$ time.
* If we only use the Rabin-Karp's fixed-window rolling hashing technique in such a case, we will have to re-roll for each substring that has a different length.
* The Rabin-karp's fixed-window rolling hashing technique is useful when we have a string (text) that we need to compare with a fixed-length substring or pattern.
  * In such a case, we calculate the hash code of the substring or pattern.
  * Let us assume that the length of the substring or pattern is `l`.
  * And then we start with the index `0` of the string (text), and then keep rolling the substring window of length `l`.
* The `prefixedHash` array is useful when we need to compare different substrings of different lengths.
  * For example, we might compare two substrings of length, say, `l`.
  * Then, we might compare two substrings of length, say, `m`.
  * Then, we might compare two substrings of length, say, `n`.
  * And so on...
* It should be noted that under the hood, the `prefixedHash` array also uses the Rabin-Karp's fixed-window rolling hashing technique only.
* It should also be noted that we can use the `prefixedHash` array instead of the Rabin-Karp's fixed-window rolling hashing technique.
  * It means that the problem that uses the Rabin-Karp's fixed-window rolling hashing technique as a solution, can also be solved using the `prefixedHash` array.
  * But, the problem that uses the `prefixedHash` array as a solution, cannot be solved using the Rabin-Karp's fixed-window rolling hashing technique only.

## Applications

* Compare substrings quickly.
* Check palindrome substrings.
* Detect duplicates.
* Longest common substring.
* Rabin–Karp improvements.
* Binary search on answer + substring comparison.

## Next

* [String Hashing Revision](50stringHashingRevision.md)
* [Relevant DSA Problems](60relevantDsaProblems.md)
