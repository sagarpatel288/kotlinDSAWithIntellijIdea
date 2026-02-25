# Implement A Hash Table With Separate Chaining

<!-- TOC -->
* [Implement A Hash Table With Separate Chaining](#implement-a-hash-table-with-separate-chaining)
  * [Prerequisites](#prerequisites)
  * [Introduction](#introduction)
  * [Thought Process](#thought-process)
  * [Reading the input](#reading-the-input)
  * [Add (insert, put)](#add-insert-put)
  * [Delete (remove)](#delete-remove)
  * [Find (contains)](#find-contains)
  * [Check(print)](#checkprint)
<!-- TOC -->

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
    * check(i) ( print(i) ): Print the contents of the hash table at index i (`table[i]` gives us a linked list. So, we
      need to print the contents of that linked list.).

## Thought Process

**What do we need to implement a hash table with separate chaining?**

* We need a hash function that takes a string, generates a hash value, and maps it to an index in the hash table.
* A hash function uses a prime number to keep it a finite field, a random number as a base, and the cardinality as a
  modulus to limit the distribution within the hash table.
* Cardinality is a hash table size or the number of buckets or a range of possible indices the input can be mapped to.
* The hash table uses a fixed-size array, and we might expand it when we need.
* Each bucket of this fixed-size array is a linked list as a collision resolution technique.
* So, we can proceed it as follows:

```kotlin

val prime = 1_000_000_007L
val base = 31L
val cardinality = // Is it fixed, or dynamic? Do we get it from the user?
val table = Array<LinkedList<String>>(cardinality) { LinkedList() }

fun hash(input: String): Int {
    var hashCode = 0L
    for (i in input.lastIndex downTo 0) {
        hashCode = ((hashCode * base) + input[i].code.toLong()) % prime
    }
    hashCode = (hashCode % cardinality + cardinality) % cardinality
    return hashCode.toInt()
}
```

* Now, suppose that we get the cardinality from the user.
* Where do we use the cardinality? We use it to initialize the hash table.
* It means that we can initialize the hash table only after we get the cardinality from the user.
* It means that we do the late initialization of the hash table.
* So, we need to make the hash table a `lateinit` property.

```kotlin

lateinit var table: Array<LinkedList<String>>

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val cardinality = reader.readLine()?.toInt() ?: 0
    table = Array(cardinality) { LinkedList() }
}

```

## Reading the input

* Suppose that we get the queries from the user.

```kotlin

val reader = BufferedReader(InputStreamReader(System.`in`))
val cardinality = reader.readLine()?.toInt() ?: 0
table = Array(cardinality) { LinkedList() }
val queries = reader.readLine()?.toInt() ?: 0
if (queries <= 0) return
repeat(queries) {
    val line = reader.readLine()
    val token = StringTokenizer(line)
    val query = token.nextToken()
    when (query) {
        "add" -> {
            val input = token.nextToken()
            //...
        }
        "delete" -> {
            val input = token.nextToken()
            //...
        }
        "find" -> {
            val input = token.nextToken()
            //...
        }
        "check" -> {
            val index = token.nextToken()
            //...
        }
    }
}
```

## Add (insert, put)

**How do we perform the `add` (insert or put) operation?**  
**What do we get for the `add` (insert or put) operation?**

* Suppose that the user gives the `add input` query.
* Now, we give this `input` to our `hash` function.
* The `hash` function returns the index of the hash table.
* The `index` of the hash table gives a linked list.
* And before we add the `input` to the linked list, we need to check whether the linked list contains the `input`.
* We add the `input` to the linked list only if the linked list does not contain the `input` already.

```kotlin

val index = hash(input)
val chain = table[index]
if (!chain.contains(input)) {
    chain.addFirst(input)
}
```

## Delete (remove)

**How do we perform the `delete` (remove) operation?**  
**What do we get for the `delete` (remove) operation?**

* Suppose that the user gives the `delete input` query.
* Now, we give this `input` to our `hash` function.
* The `hash` function returns the index of the hash table.
* The `index` of the hash table gives a linked list.
* And we remove the `input` from the linked list.

```kotlin

val index = hash(input)
val chain = table[index]
chain.remove(input)
```

## Find (contains)

**How do we perform the `find` (contains) operation?**  
**What do we get for the `find` (contains) operation?**

* Suppose that the user gives the `find input` query.
* Now, we give this `input` to our `hash` function.
* The `hash` function returns the index of the hash table.
* The `index` of the hash table gives a linked list.
* And we check whether the linked list contains the `input`.

```kotlin

val index = hash(input)
val chain = table[index]
val found = chain.contains(input)
println(if (found) "yes" else "no")
```

## Check(print)

**How do we perform the `check` operation?**  
**What do we get for the `check` operation?**  

* Suppose that the user gives the `check index` query.
* The `index` of the hash table gives a linked list.
* We print the linked list.

```kotlin

val chain = table[index]
val result = chain.joinToString(" ")
println(result)
```
