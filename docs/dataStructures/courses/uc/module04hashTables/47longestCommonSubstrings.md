# Longest Common Substrings

## Prerequisites

* [Hash Tables](05hashTables.md)
* [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md)
* [Load Factor And Rehashing](15loadFactorAndRehashing.md)
* [Formula Of Universal Family Of Hash Functions](20formulaOfUniversalFamilyOfHashFunctions.md)
* [String Hashing](25stringHashing.md)
* [Find A Substring](30findSubstring.md)
* [Hash Questions](35hashQuestions.md)
* [Hashing In Blockchain](40hashingInBlockchain.md)
* [Precomputed Prefix Hashes](45precomputedPrefixHashes.md)

## Problem

**[Match Substring](30findSubstring.md)**

* In the [Match Substring](30findSubstring.md) problem, we had a separate pattern and text.
* And we were asked to find the number of times the pattern appears in the text.
* In that case, we first computed the hash of the pattern.
* Then, we used the rolling hash to compute the hash of the substrings of the text.

**[Precomputed Prefix Hashes](45precomputedPrefixHashes.md)**

* In the [Precomputed Prefix Hashes](45precomputedPrefixHashes.md) problem, we had a text.
* We were given starting index of a substring 1, starting index of a substring 2, and the length of the substring.
* And we were asked to find if the two substrings are equal.
* In that case, we could get different lengths in different queries.
* So, we first precomputed the prefix hashes of the text and stored them in an array.
* Each index would represent the length of the substring.
* Each index stores the hash of the substring of the corresponding length.
* And then, we used a formula to get the hash of any substring of any length in $O(1)$ time.

**Longest Common Substrings**

* In this problem, we are given two strings.
* And we are asked to find the longest common substring.
* A common substring is a substring that appears in both the strings.
* We need to print the starting indices of the longest common substring from both the strings.
* And we also need to print the length of the longest common substring.