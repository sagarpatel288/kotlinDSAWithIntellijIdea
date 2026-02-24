# Implement A Hash Table With Separate Chaining

## Prerequisites

* [05hashTables.md](05hashTables.md)    
* [10universalFamilyOfHashFunctions.md](10universalFamilyOfHashFunctions.md)    
* [15loadFactorAndRehashing.md](15loadFactorAndRehashing.md)  
* [20formulaOfUniversalFamilyOfHashFunctions.md](20formulaOfUniversalFamilyOfHashFunctions.md)  
* [25stringHashing.md](25stringHashing.md)

## Introduction

* We want to implement a hash table with separate chaining to store strings.
* We want to support the following operations:
  * insert (add or put)
  * delete (remove)
  * search (find or contains)
  * print(i) --> Print the contents of the hash table at index i (It will be a linked list).

## Thought Process

**What do we need to implement a hash table with separate chaining?**
  
* We need a hash function that takes a string, generates a hash value, and maps it to an index in the hash table.
* A hash function uses a prime number to keep it a finite field, a random number as a base, and the cardinality as a modulus to limit the distribution within the hash table.
* Cardinality is a hash table size or the number of buckets or a range of possible indices the input can be mapped to.
* The hash table uses a fixed-size array, and we might expand it when we need.
* Each bucket of this fixed-size array is a linked list as a collision resolution technique.
* So, we can proceed it as follows:

```kotlin

val prime = 1_000_000_007L
val base = 31L
lateinit var table: Array<LinkedList<String>>
var cardinality = 0

fun hash(input: String): Int {
    var hashCode = 0L
    for (i in input.lastIndex downTo 0) {
        hashCode = ((hashCode * base) + input[i].code.toLong()) % prime
    }
    hashCode = (hashCode % cardinality + cardinality) % cardinality
    return hashCode.toInt()
}
```