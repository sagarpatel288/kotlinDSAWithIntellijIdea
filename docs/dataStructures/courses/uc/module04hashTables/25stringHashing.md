# String Hashing

<!-- TOC -->
* [String Hashing](#string-hashing)
  * [ToDo](#todo)
  * [Previously/Prerequisites/References](#previouslyprerequisitesreferences)
  * [Problem Description](#problem-description)
  * [Solution](#solution)
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



## Next