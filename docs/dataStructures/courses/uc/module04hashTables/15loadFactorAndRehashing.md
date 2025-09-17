# Load Factor Of Hash Table

<!-- TOC -->
* [Load Factor Of Hash Table](#load-factor-of-hash-table)
  * [Previously, prerequisites, and references](#previously-prerequisites-and-references)
  * [Load Factor](#load-factor)
    * [Notation](#notation)
    * [Expression](#expression)
    * [Explanation](#explanation)
  * [Rehashing](#rehashing)
<!-- TOC -->

## Previously, prerequisites, and references

* [Hash Tables](05hashTables.md)
* [Universal Family Of Hash Functions](10universalFamilyOfHashFunctions.md)

## Load Factor

* We have seen the role of a [hash function](05hashTables.md) in a hash table.
* We have also seen the [universal family of hash functions](10universalFamilyOfHashFunctions.md).
* Now, we are going to discuss another important property of a hash table.
* This is the property we use to measure and maintain the performance of a hash table.
 
### Notation

* `n` is the total items (keys)
* `m` is the hash table size (also known as cardinality, the size of the range of indices, the size of the total possible output indices).

### Expression

* Ratio: $\alpha = \frac{n}{m}$: Keys per slot on average = Load Factor.
* Running time of a hash table: $O(1 + \alpha)$

### Explanation

* For example, if the load factor is `0.5`, it means that on average, 50% of the hash table is empty. (We don't split a single key into two halves and store each part in a different slot!)
* If the load factor is `0.9`, it means that the hash table is almost full, and we expect collisions.
* If the load factor is `2`, it means that on average, each slot stores two keys.
* It means that we are already getting collisions, and the average chain length is `2`. 
* If the load factor is larger, it indicates that the space is congested, and we have collisions. 
* So, we spend more time on various operations such as search (find, contains), insert, delete, etc.
* Because we have to travel through chains.
* It means that we need to increase the hash table size to uniformly spread out the distribution of the keys (objects) and decrease the collisions.
* Clearly, the performance of a hash table is directly proportional to the load factor.
* So, we can represent it as $O(\alpha)$.
* However, in addition to the hash table operations, there is always a constant amount of work that we have to perform regardless of the hash table size.  
* For example, calculating a hash code, compressing the hash code, accessing the array index, etc.
* That counts as $O(1)$.
* Hence, the total average running time of a hash table is $O(1 + \alpha)$.
* If $\alpha$ is less than `1`, it becomes $O(1)$.
* If $\alpha$ is more than `1`, it becomes $O(\alpha)$.

## Rehashing

* So, in the [load factor](#load-factor) section, we have learned that a load factor is a critical metric for the hash table's performance.
* But what if `n` keeps changing?
* How do we maintain a good load factor?
* How do we maintain the hash table size?
* We use the same idea that we use for the [Dynamic Arrays](../module02dynamicArraysAndAmortizedAnalysis/dynamicArrays.md).
* We constantly monitor the load factor after each operation.
* If we find that the load factor is about to cross the limit, we change the hash table size.
* Changing the hash table size is a linear operation.
* But we don't have to perform it frequently.
* Hence, the amortized cost of resizing the hash table is $O(1)$.
* But changing the hash table size brings a challenge.
* For example, the initial hash table size, `m` is `10`. 
* It means that the cardinality of the hash function is `10`.
* Now, if we find that we need to increase the hash table size, we double the hash table size so that it becomes `20`.
* Since `m` has changed, the old indices are incorrect for the new hash table.
* We have doubled the hash table size because the old hash table was too congested.
* And now we want to uniformly distribute the same keys to this new and large hash table.
* So, we need a way to place all the existing keys into their new, correct, and uniformly distributed locations in the new hash table.
* This procedure is called **rehashing**.
* We iterate through the old hash table, reapply the hash code, use the new cardinality `m` to compress the generated hash code, and map each key into the new hash table. 