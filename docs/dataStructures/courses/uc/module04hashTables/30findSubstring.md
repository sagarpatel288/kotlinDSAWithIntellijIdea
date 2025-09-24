# Find Substring

## Problem Statement

* Given a string `s` and a pattern `p`, we need to print the starting indices of `p` that we find within `s`.
* For example, if `s = abcdefabcdef`, and `p = abc`, then we can find the pattern `p` at `indices = 0, 6` in `s`.  

## Examples

* Searching for a word in a browser.
* Matching a pattern while studying genes.
* Matching a pattern in a computer system. 
* Etc.

## Naive Approach

```kotlin

fun findString(s: String, p: String) {
    val startingIndices = mutableListOf<Int>()
    for(i in 0..<(s.length - p.length)) {
        if (s[i] != p[0]) break
        if (s.substring(i, i + p.length) == p) {
            startingIndices.add(i)
        }
    }
    println(startinIndices)
}
```

* The comparison of the substring `S'` and `P` can be represented as below.

```kotlin

private fun areEqual(sub: String, p: String): Boolean {
    if (sub.length != p.length) return false
    for (i in 0..<sub.length) {
        if (sub[i] != p[i]) {
            return false
        }
    }
    return true
}
```

### Time Complexity

* The outer `for loop` iterates through almost every character of the given string `s` in the worst-case.
* And then when we compare the substring with the given pattern `p`, it checks each character.
* So, if the length of the string `s` is `n`, and the length of the pattern `p` is `m`, then it becomes `O(n * m)`.

### Problems

* The running time is inefficient.
* We get many false alarms (also known as spurious hits).
* For example, suppose `S` is `aaaa.....a`, where the length of `S` is 1000. 
* And the pattern `P` is `aaaa....b`, where the length of `P` is 10.
* Now, in this case, we would compare many substrings of `S` only to find that it is different than the pattern `P` when we reach the last character.
* We can do better than this.

## Rabin-Karp Algorithm 

* The idea is to use **string hashing** to compare each substring `S'` of the given string `S` with the pattern, `P`.
* Because we know that the collision probability of two different strings in string hashing is very low, if we take the prime number $p \geq (m * L)$, where `m` is the cardinality, and `L` is the length of the string.
* Reference: [String Hashing](25stringHashing.md#collision-probability-).
* So, we can reduce at least the number of false alarms.

### Initial Implementation (Without optimization)

* Find the hash code of the pattern, `P`.
* Find the hash code of each substring of `S`.
* If the hash code of a substring matches the hash code of the pattern `P`, compare the substring with the pattern. 

