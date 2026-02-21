# Hash Questions

<!-- TOC -->
* [Hash Questions](#hash-questions)
  * [Prerequisites](#prerequisites)
  * [Trade-off: Which trade-off does a hash table make to achieve performance?](#trade-off-which-trade-off-does-a-hash-table-make-to-achieve-performance)
  * [What is "Uniform Distribution" by a hash function? What if it fails?](#what-is-uniform-distribution-by-a-hash-function-what-if-it-fails)
  * [Why do we use a linked list instead of an array for collision resolution?](#why-do-we-use-a-linked-list-instead-of-an-array-for-collision-resolution)
  * [Why do we have `Set` when we already have `Map`?](#why-do-we-have-set-when-we-already-have-map)
  * [What makes the Rabin-Karp algorithm more efficient?](#what-makes-the-rabin-karp-algorithm-more-efficient)
  * [Why do we use the universal family of hash functions instead of a single hash function?](#why-do-we-use-the-universal-family-of-hash-functions-instead-of-a-single-hash-function)
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

## Trade-off: Which trade-off does a hash table make to achieve performance?

* We intentionally spend more space to achieve the performance.
* We allocate a slightly larger array than required to keep the load factor low.
* It reduces the collisions and results in $O(1)$ average time to perform various operations like find, insert, delete, etc.

## What is "Uniform Distribution" by a hash function? What if it fails?

* "Uniform Distribution" means fewer or no collisions.
* If a hash function does not distribute the keys uniformly, we get more collisions.
* In case of separate chaining, the worst-case forms a single, long linked-list.
* In the worst-case scenario, the hash-table performance degrades and matches a linked-list's performance.

## Why do we use a linked list instead of an array for collision resolution?

* An array is a contiguous data structure.
* If we insert or delete an element at index `0`, all the other elements have to shift their positions.
* Worst-case shifting cost is $O(n)$. 
* Unlike an array, a linked list does not have the "shifting cost" of $O(n)$.
* We can perform the insert and delete operation in $O(1)$ by adjusting the pointers.

## Why do we have `Set` when we already have `Map`?

* `Set` provides separation of concerns.
* `Set` does not allow setting a placeholder value that might be exposed in `Map`'s `get`.
* Also, `Set` includes `union`, `intersection`, and `difference` operations that a `Map` does not need.
* Hence, `Set` has a focused and distinct purpose than `Map`.
* That's why we have these two different data structures, even though a `Set` internally uses a `Map` only.

## What makes the Rabin-Karp algorithm more efficient?

* The polynomial property that we use via "Rolling hash" and "Sliding Window" allows us to compute the hash code of the next substring in $O(1)$ time using the hash code of the previous substring.
* Also, we precompute the hash code of the pattern.
* So that we don't have to compute it every time when we compare it with a substring.

## Why do we use the universal family of hash functions instead of a single hash function?

* The universal family of hash functions provides more security via randomness.
* Even though the family is known, the two random variables `a` and `b` remain a secret.
* Hence, we cannot predict the exact hash function in use.
* Therefore, we prevent a "bad input" (Hash DoS attack).

## Next

* [Hashing In Blockchain](40hashingInBlockchain.md)
* [Precomputed Prefixed Hashes](45precomputedPrefixHashes.md)
* [String Hashing Revision](50stringHashingRevision.md)
* [Relevant DSA Problems](60relevantDsaProblems.md)