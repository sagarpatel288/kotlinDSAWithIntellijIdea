# Designing a good hash function (Parameters to consider, the thought process, implications, etc.)

<!-- TOC -->
* [Designing a good hash function (Parameters to consider, the thought process, implications, etc.)](#designing-a-good-hash-function-parameters-to-consider-the-thought-process-implications-etc)
  * [Prerequisites, resources, and references](#prerequisites-resources-and-references)
  * [ToDo](#todo)
  * [Terminologies](#terminologies)
  * [Problem Statement](#problem-statement)
  * [Expectations](#expectations)
  * [Background](#background)
  * [Pigeonhole Principle](#pigeonhole-principle)
  * [Observation](#observation)
  * [Universal Family Of Hash Functions](#universal-family-of-hash-functions)
    * [Introduction](#introduction)
    * [Technical Definition](#technical-definition)
      * [Notation](#notation)
      * [Definition](#definition)
  * [Next](#next)
<!-- TOC -->

## Prerequisites, resources, and references

* [Hash Table](05hashTables.md)

## ToDo

* Explain the impact of a hash function.
  * Should the example image(s) go here?
* Explain: How do we intentionally choose time efficiency over space (the trade-off, priority) and why?
* Images? Showing a good and a bad hash function (impact) examples?
  * Too much wasted space (Almost double or maybe triple?).
  * Too many collisions. (All the elements go to the same index.)
* Do we need to prove that the collision probability is at most $\frac{1}{m}$?

## Terminologies

* `n` is a total number of objects or keys that we want to map to indices of range `0...m-1`.
* The domain of `n` is almost infinite.
* `m` is the hash table size or the size of the index-range or all the possible index values for the `n` objects.
* The size of `m` is limited.
* Pigeonhole Principle: If there are more pigeons than the pigeonholes, there exists at least one pigeonhole that will get more than one pigeons. 
* Collision - when different `keys` get (map to) the same hash-table index.
* `c` is the longest chain in the hash-table where we find multiple objects (`keys`) at the same hash-table index.

## Problem Statement

* Design an efficient hash function.

## Expectations

* The hash function must be deterministic.
* Uniform distribution of the keys (input) to get fewer collisions.

## Background

* We have learned that a hash table uses a hash function to map an input object (key) to the corresponding index in the hash table.
* If the hash function always generates the same index regardless of the input, all the input objects (keys) go to the same index in the hash table.
* When more than one object (key) go to the same index, we call it collision.
* If all the objects (keys) go to the same index, it is the pathological hash function that gives the highest collisions.
* To store multiple objects (keys) to the same index, we might use **separate chaining**, where each slot (index) implements (uses) a linked list.
* And then we can prepend or append the new items (that keep coming at the same index) to the list.
* However, the longer the list, more the time we take to perform various operations such as find(contains), insert(put), delete, etc.

![110hashFunctionImplications.png](../../../../../assets/images/dataStructures/uc/module04HashTables/110hashFunctionImplications.png)

* On the other hand, if the hash function uniformly distributes the input objects (keys), we get less collisions.
* It means that we don't have to travel through a long chain to perform various operations such as find(contains), insert(put), delete, etc.
* It means that we can perform various operations faster.
* Due to uniform distribution, we spread all the input objects (keys) evenly across the `m` slots (indices).
* It helps us perform various operations in `O(1)`.
* Hence, the efficiency of a hash table depends on the underlying hash function.

## Pigeonhole Principle

* If we have more pigeons than the pigeonholes, then at least one pigeonhole would contain more than one pigeons.
* The size of input objects (keys) `n` is greater than the cardinality of a hash table, `m`.
* It means that there must be at least one slot (index) that would contain more than one objects (keys).
* When the same index contains more than one objects (keys), we call it "collision".
* Or in other words, when multiple objects (keys) go to the same index, we call it "collision".
* If we know the hash function and these objects (keys) that always go to the same index, we can intentionally create worst-case "collision". 

## Observation

* The efficiency of a hash table depends on the efficiency of the underlying hash function.
* For a total of `n` objects (keys) when distributed across `m` slots (indices), where `n >> m`:

$$
\text{ Longest chain, c} \geq \frac{n}{m}
$$

* The longest chain must be greater than or equal to the size of an average chain.
* For any hash function, there can be an input that can produce the worst-case collision. 

![110hashFunctionImplications.png](../../../../../assets/images/dataStructures/uc/module04HashTables/110hashFunctionImplications.png)

* For example as shown in the image, the hash function uniformly distributes the keys `[k1, k2,...,k6]`, where the longest chain, `c = 3` for `n = 6`.
* However, we know that certain bad input always collides, for example `k3, k4, and k6`.
* It means that if we have these fixed pairs of keys from the set of bad input, which is `k3, k4, and k6`, we know that it will always collide for our hash function.
* Because the hash function is fixed and we know the bad input for it.
* Let us assume that the input `n = 3` and the keys are `[k3, k4, k6]`. 
* Now, the same hash function that used to distribute the keys uniformly, now produces the worst-case chain, where `c = 3 = n`.
* That is to say, worst-case input is inevitable when we have a single, fixed hash function.
* This is where the idea of multiple hash functions rises.

## Universal Family Of Hash Functions

### Introduction

* While playing the rock-paper-scissor, if we always choose the "rock", then the opponent can easily identify our pattern, and defeat us with the "paper".
* Because our pattern is predictable.
* What if we select our option randomly?
* The "paper" can beat the "rock", but not the "scissor"!
* While the opponent is showing the "paper", we might be showing the "scissor"!
* And the next time when the opponent shows the "rock", we might show the "paper"!
* Basically, we reduce our chances of defeat if our pattern is not predictable.
* So, the randomness helps here.
* Similarly, we have seen that a single and fixed hash function will always have a "bad input" as per [pigeonhole principle](#pigeonhole-principle) and our [observation](#observation).
* But what if we have multiple hash functions and then we randomly choose a hash function?
* Then the probability of any fixed pairs of keys to introduce collision is reduced.
* Because a "bad input" for a particular hash function might not be bad for another randomly and secretly selected hash function.
* And if we choose our hash function randomly and secretly, then the chances of creating collisions for any fixed pairs of keys are reduced. 
* The idea of having multiple hash functions and selecting a random hash function is to proactively reduce the collision probability for any fixed pair of keys.

### Technical Definition

$$
P_{r(h \in H)}[h(k1) = h(k2)] \leq \frac{1}{m}
$$

#### Notation

* $P_r$ means the probability of
* $h \in H$ means the randomly selected hash function `h` is a part of the universal family `H` that contains more than one hash functions.
* `k1` and `k2` are two distinct (different) objects (keys) from the domain of universe `U`.
* `m` is the number of slots in our hash table.
* The cardinality of each hash function of the set `H` is `m`. 

#### Definition

* A set of hash functions, `H` is called a universal family of hash functions, if for any two different (distinct) keys `k1` and `k2` from the domain of universe `U`, the probability of their collision is less than or equal to `1 over m` when the hash function `h` is chosen randomly from the set of `H`.

## Next

* [Load Factor And Rehashing](15loadFactorAndRehashing.md)