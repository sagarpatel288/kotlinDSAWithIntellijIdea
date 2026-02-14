# Hashing in Blockchain

<!-- TOC -->
* [Hashing in Blockchain](#hashing-in-blockchain)
  * [Prerequisites](#prerequisites)
  * [Overview](#overview)
  * [Programming Problems](#programming-problems)
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

## Overview

* We have many transactions.
* We hash each transaction. We call it leaves.
* Then, we pair and merge the hashes of two transactions and create another hash.
* We keep doing it until we cover all the hashes of all the transactions and get a single hash for them.
* We call it a Merkle Root.
* We add this Merkle Root to a blockchain.
* The hash of a previous block is included in the current block.
* Now, we want to check a particular transaction.
* So, we give the transaction ID to a block explorer.
* A block explorer is a set of websites that have already indexed the entire blockchain.
* It gives us the corresponding block.
* Then, we perform the Merkle Proof to check the transaction.

## Programming Problems

* [01phoneBook.kt](../../../../../src/courses/uc/course02dataStructures/module04hashTables/01phoneBook.kt)
* [02stringHashingWithChain.kt](../../../../../src/courses/uc/course02dataStructures/module04hashTables/02stringHashingWithChain.kt)

## Next

* [Precomputed Prefixed Hashes](45precomputedPrefixHashes.md)
* [String Hashing Revision](50stringHashingRevision.md)
* [Relevant DSA Problems](60relevantDsaProblems.md)