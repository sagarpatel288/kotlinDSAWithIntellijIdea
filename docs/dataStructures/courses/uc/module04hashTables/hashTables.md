# Hash Tables

<!-- TOC -->
* [Hash Tables](#hash-tables)
  * [Resources / References](#resources--references)
  * [Time complexity in various data structures](#time-complexity-in-various-data-structures)
  * [Problem/Requirement](#problemrequirement)
  * [ToDo](#todo)
<!-- TOC -->

## Resources / References

**Overview**
* Very good overview - short and sweet - clear and concise: Gate Smashers
* [Gate Smashers](https://youtu.be/W5q0xgxmRd8?si=3rhBhehtPSW5O-7w)

**Application**
* [Monis Yousuf](https://youtu.be/pMM9cIAFAug?si=4dtGaEEywYDxLPiP)
* [Simplilearn](https://youtu.be/jmtzX-NPFDc?si=dCNRHOF93cFlI4w7)

**Internals**
* [Arpit Bhayani](https://youtube.com/playlist?list=PLsdq-3Z1EPT2UnueESBLReaVSLIo_BuAc&si=mbgu-ku-Zw5_9nil)

## Time complexity in various data structures

* ToDo: A table to show various time complexities for various data structures for various operations like insert, search(access, get), update, delete, etc.  
* Maybe in the form of tables? with notes?
* [WIP - Comparison](../revision/comparison.md)

## Problem/Requirement

* Faster search. 
* Faster insertion and deletion.
* Storing key-value pairs.

## ToDo

* What problem does it solve? (Why do we need it? Benefits?)
* How? (How does it work? Implementation. Analysis.)
* When to use it?
* Comparison.

## Hash Function

### Introduction

* A `hash function` takes a `key` as an argument, and generates a unique `hash code` for the given key.
* The `hash function` is a deterministic function. 
* It means that it always generates the same `hash code` for the same key.
* Also, the type and size of the input key can be anything.
* So, the domain and size of the input key can be anything.
* This is the reason we often call the domain of the key a `universe`.
* But the domain and size of the `hash code` remain the same.
* A `hash code` is always a `whole number`.
* And the size of the `hash code` remains the same for all the different keys.
* We then compress this `hash code` to produce an index from `[0,..,m-1]`, where `m` is the size of the `hash table`.  
* To compress the `hash code`, we use `hashCode % size of the hash table`.
* So, `index = hashCode % m,` where `m` is the size of the hash table.
* So, this is how the given `key` becomes an `index`.
* The range of this index is called **cardinality**.
* It means that the total number of possible unique output.
* We know that this range is limited. `R = [0,..,m-1]`, where `m` is the size of the hash table. 
* But the domain and size of the input `key` are almost infinite.
* It means that we might end up getting the same `index` for different `keys`.
* This incident is called `collision`.
* Technically, if more than one `keys` get the same `index` value, we call it `collision`.
* For example, we have only two seats, but we have sold them to 10 different people.
* Clearly, more than one person will try to claim the seat.
* So, we have limited seats for an unlimited number of people. 
* We will be learning more about the `collision` a bit later.
* We will also see that it should be impossible to produce the input `key` from the output.
* It means that the `hash function` should be `irreversible`. 
* But for now, let us see the technical definition of the `hash function`.

### Technical Definition

#### Formula

$$
h: U \to [0, \dots, m - 1]
$$

* `h` The hash function.
* `:` Read as: "is a function that maps".
* `U` The domain, the universe of all possible keys.
* `->` Read as `to`
* `[0,..,m - 1]` The range, the set of all possible array indices.

#### Definition

* A hash function `h` is a deterministic function that maps an input key `k` of arbitrary size from a large domain `U` (the universe of keys) to a fixed-size output `h(k)` (also known as a `hash code`), which is then compressed to a smaller index within the range `R = [0,..,m - 1]`, where `m` is the size of the `hash table`. 

### Load Factor

* We might think that if we have a large hash table (so, a higher cardinality), we might get fewer collisions.
* But then we spend more memory. Right?
* And if we have a smaller cardinality, we increase the possibility of collision, right?
* So, how do we manage this? Is there a standard ratio or measurement?
* Yes. We call it a **load factor**.

$$
\text{Load factor } \alpha = \frac{n}{m}, \text{ where n is the number of items, and m is the hash table size}
$$

* We try to maintain this ratio at around `0.75`.
* We will also learn more about it.

### Properties

* A hash function should be deterministic.
* It means that the same input should produce the same output.
* The output must be of fixed size.
* The hash function should be irreversible.
* It means that it should be impossible to get the input key from the output.
* The hash function should be fast enough. 
* So that we can perform various operations such as find, insert, update, delete, etc., fast enough.
* The possibility of collision should be minimized.
