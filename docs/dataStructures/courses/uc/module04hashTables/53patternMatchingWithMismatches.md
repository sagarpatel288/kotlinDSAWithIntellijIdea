# Pattern matching with k-allowed mismatches

<!-- TOC -->
* [Pattern matching with k-allowed mismatches](#pattern-matching-with-k-allowed-mismatches)
  * [Problem Introduction](#problem-introduction)
  * [Problem Description](#problem-description)
    * [Task](#task)
    * [Input Format](#input-format)
    * [Constraints](#constraints)
    * [Output Format](#output-format)
    * [Time Limits](#time-limits)
    * [Memory Limit](#memory-limit)
    * [Sample 1](#sample-1)
      * [Input:](#input)
      * [Output:](#output)
      * [Explanation:](#explanation)
  * [Solution](#solution)
    * [Intuition](#intuition)
      * [Naive approach: Brute Force](#naive-approach-brute-force)
      * [Overview Of Idea: Overall Approach / Thought Process](#overview-of-idea-overall-approach--thought-process)
      * [Binary Search And Prefix Hashes With Double Hashing](#binary-search-and-prefix-hashes-with-double-hashing)
      * [Outer `for-loop`](#outer-for-loop-)
    * [Observation and Insights: ToDo// Confirm this understanding.](#observation-and-insights-todo-confirm-this-understanding)
      * [Variables](#variables)
      * [Text: Sliding Window](#text-sliding-window)
      * [Pattern](#pattern)
    * [TL;DR](#tldr)
  * [Time Complexity](#time-complexity)
  * [Space Complexity](#space-complexity)
  * [Grader output](#grader-output)
<!-- TOC -->

## Problem Introduction

* A natural generalization of the pattern matching problem is the following:
* Find all text locations where the distance from the pattern is sufficiently small.
* This problem has applications in text searching (where mismatches correspond to typos) and bioinformatics (where mismatches correspond to mutations).

## Problem Description

### Task

* For an integer parameter `k` and two strings $t = t_0t_1···t_{m-1}$ and $p = p_{0}p_{1} ··· p{n-1}$,
* we say that `p` occurs in `t` at position `i` with at most `k` mismatches if the strings
* `p` and $t[i : i + p) = t_{i}t_{i+1} ···t_{i+n-1}$ differ in at most k positions.

### Input Format

* Every line of the input contains an integer `k` and two strings `t` and `p` consisting of lower case Latin letters.

### Constraints

```
0 <= k <= 5, 1 <= |t| <= 200 000, 1 <= |p| <= min{|t|, 100 000}
```

* The total length of all t’s does not exceed 200 000, the total length of all p’s does not exceed 100 000.

### Output Format

* For each triple `(k, t, p)`, find all positions $0 < i_1 < i_2 < ··· < i_l < |t|$ where `p` occurs in `t` with at most `k` mismatches. 
* Output $l$ and $i_1, i_2,...,i_l$.

### Time Limits

C: 2 sec, C++: 2 sec, Java: 5 sec, Python: 40 sec. C#: 3 sec, Haskell: 4 sec, JavaScript: 10 sec, Ruby: 10 sec, Scala: 10 sec.

### Memory Limit

* 512 MB

### Sample 1

#### Input:

```
0 ababab baaa
1 ababab baaa
1 xabcabc ccc
2 xabcabc ccc
3 aaa xxx
```

#### Output:
```
0
1 1
0
4 1 2 3 4
1 0
```

#### Explanation:

* For the first triple, there are no exact matches.
* For the second triple, `baaa` has distance one from the pattern.
* For the third triple, there are no occurrences with at most one mismatch.
* For the fourth triple, any (length three) substring of `p` containing at least one `c` has distance at most two from `t`.
* For the fifth triple, `t` and `p` differ in three positions.

## Solution

### Intuition

#### Naive approach: Brute Force

**How do we naively compare two strings?**  
**Pseudocode (core part) of naive implementation**  

* We compare two strings character by character.
* So, it looks like below:

```
// This outer loop increments the pointer of the text string
for (t in 0 until text.length) {
    // matchLen for each text window
    var matchLen = 0
    // This inner loop increments the pointer of the pattern string
    for (p in 0 until pattern.length) {
        // Comparing characters
        if (text[t++] != pattern[p]) {
            // With each mismatch, we increase the "mismatch" counter
            mismatches++
        } else {
            // If it is a match, we increment the "matchLen" counter
            matchLen++
        }
        if (mismatch > k) {
            // We crossed the maximum allowed mismatches
            matchLen = 0
            break
        }
    }
    // Result based on the mismatch counter
    if (mismatch <= k) {
        result.add(t) // The text index from where the pattern matching with allowed mismatches starts
    }
}
```

* Now, the very first thing we need to improve here is applying the boundary to the maximum possible window size (length).
* For example, if the text window is `abcdefgh`, and the pattern window is `abc`, we know that there is no point of taking any text window longer than the size (length) of the pattern window.
* Because a longer text substring than the pattern itself can never be a match.
* So, we avoid such cases.

**How do we ensure that we don't compare a text window longer than the pattern window?**

* By applying the boundary on the text window.

```kotlin

for (i in 0 until (text.length - pattern.length)) {
    
}

```

* It means that the starting index of the text window can go only up to `text.length - pattern.length`.
* For example, if the text is `abcdefgh`, and the pattern is `xgh`, the starting index of the last text window will be `text.length - pattern.length = 8 - 3 = 5`.
* So, the starting index of the last text window is `i = 5` and the last text window that we can get is `fgh`.
* The next big thing we need to improve here is correcting (eliminating) the process that compares two substrings of different lengths.
* For example:

```kotlin

for (i in 0 .. (text.length - pattern.length)) {
    
    for (j in 0 until pattern.length) {
        
    }
}

```

* The problem with this lexical for-loops is that we compare two substrings of different lengths.
* And we know that they will never match.
* For example, suppose that the text is `abcdefgh`, and the pattern is `xyabcdef`.
* Now, suppose that the starting index of the text window is `i = 0`.
* So, we will get multiple text windows like below:

```
a, ab, abc, abcd, abcde, abcdef, abcdefg, abcdefgh
```

* Similarly, when `j = 0`, we will get multiple pattern windows like below:

```
x, xy, xya, xyab, xyabc, xyabcd, xyabcde, xyabcdef
```

* Now, what is the point of comparing the text window `a` with the pattern window `xy`, `xya`, or any other pattern window whose length is greater than (or smaller than) the text window?
* They will never match.
* It means that we want to keep the pattern window length similar to the text window length.
* We may change the starting index of the pattern window to cover each substrings of the pattern window having the same length as the text window.

**And how far this starting index of the pattern can go(move)?**

* We cannot move the starting index of the pattern beyond the `pattern.length`.
* So, it will be like:

```kotlin

for (i in 0 .. (text.length - pattern.length)) {
    var p = 0 // Starting index of the pattern
    while (p < pattern.length) {
        
    }
}

```

**How do we find the length?**

**How do we ensure that we compare two substrings (windows) of equal lengths only and avoid redundant comparisons?**

#### Overview Of Idea: Overall Approach / Thought Process

*  

#### Binary Search And Prefix Hashes With Double Hashing

**String comparison using binary search**

* Now, in the `longest common substring` problem, we have learned that the `binary search` helps string comparison.

* Reference: [05longestCommonSubstring03.kt](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/486d9c13da5ad1afbce3b65a636389d7b1c838a9/src/courses/uc/course02dataStructures/module04hashTables/05longestCommonSubstring03.kt)

* The idea is, instead of comparing character by character, we check characters in bulk.
* And `Characters in bulk` means substrings.
* And when we want to compare substrings, we use hash codes.
* So, the idea is:
* We use precomputed prefix hashing for the text and pattern strings.
* The binary search gives us a length.
* We use the following formula to compare the substrings:

```
H(a, l) = [ H(a + l) - { H(a) * x^l } ] % prime
```

* And to reduce the collisions, we can use double hashing.
* Let us add a pinch of binary search and precomputed prefix hashing mixed with double hashing.

**Precomputed prefix double hashing**

```
val prime1 = 1_000_000_007L
val prime2 = 1_000_000_079L
val xBase = 263L
val xTextPowers1 = LongArray(text.length + 1)
val xTextPowers2 = LongArray(text.length + 1)
val xPatternPowers1 = LongArray(pattern.length + 1)
val xPatternPowers2 = LongArray(pattern.length + 1)

textHashes1[0] = 0L
textHashes2[0] = 0L
patternHashes1[0] = 0L
pattenHashes2[0] = 0L
xTextPowers1[0] = 1L
xTextPowers2[0] = 1L
xPatternPowers1[0] = 1L
xPatternPowers2[0] = 1L

for (i in 1 until text.length) {
    xTextPowers1[i] = (xTextPowers1[i - 1] * xBase) % prime1
    xTextPowers2[i] = (xTextPowers2[i - 1] * xBase) % prime2
}

for (i in 1 until pattern.length) {
    xPatternPowers1[i] = (xPatternPowers1[i - 1] * xBase) % prime1
    xPatternPowers2[i] = (xPatternPowers2[i - 1] * xBase) % prime2
}

// Precomputed prefix double hashing of the text string
for (i in 0 until text.length) {
    textHashes1[i + 1] = ( ( textHashes1[i] * xBase ) + text[i].code.toLong() ) % prime1
    textHashes2[i + 1] = ( ( textHashes2[i] * xBase ) + text[i].code.toLong() ) % prime2
}

// precomputed prefix double hashing of the pattern string
for (i in 0 until pattern.length) {
    patternHashes1[i + 1] = ( (patternHashes1[i] * xBase) + pattern[i].code.toLong() ) % prime1
    patternHashes2[i + 1] = ( (patternHashes2[i] * xBase) + pattern[i].code.toLong() ) % prime2
}

fun textHashes(startIndex: Int, length: Int): Pair<Long, Long> {
    val longHash1 = textHashes1[startIndex + length]
    val shortHash1 = textHashes1[startIndex]
    val sub1 = (shortHash1 * xTextPowers1[length]) % prime1
    val hash1 = (longHash1 - sub1) % prime1

    val longHash2 = textHashes2[startIndex + length]
    val shortHash2 = textHashes2[startIndex]
    val sub2 = (shortHash2 * xTextPowers2[length]) % prime2
    val hash2 = (longHash2 - sub2) % prime2

    return hash1 to hash2
}

fun pattenHashes(startIndex: Int, length: Int): Pair<Long, Long> {
    val longHash1 = patternHashes1[startIndex + length]
    val shortHash1 = patternHashes1[startIndex]
    val sub1 = (shortHash1 * xPatternPowers1[length]) % prime1
    val hash1 = (longHash1 - sub1) % prime1

    val longHash2 = patternHashes2[startIndex + length]
    val shortHash2 = patternHashes2[startIndex]
    val sub2 = (shortHash2 * xPatternPowers2[length]) % prime2
    val hash2 = (longHash2 - sub2) % prime2

    return hash1 to hash2
}
```

**Binary Search**

```
val mid = start + (end - start) / 2
(hash1a, hash2a) = textHashes(t, mid)
(hash1b, hash2b) = patternHashes(p, mid)
if (hash1a == hash1b && hash2a == hash2b) {
    // See if the longer length matches
    start = mid + 1
} else {
    end = mid - 1
}
```

* A couple of reflective questions and observation here.

**We have to stop the binary search at some point. How do we stop? What will be the condition?**

* The condition will be: `while (start <= end) binarySearch`.
* Let us put this condition.

```
while (start <= end) {
    val mid = start + (end - start) / 2
    val (hash1a, hash2a) = textHashes(t, mid)
    val (hash1b, hash2b) = patternHashes(p, mid)
    if (hash1a == hash1b && hash2a == hash2b) {
        // See if the longer length matches
        start = mid + 1
    } else {
        // See if the shorter length matches
        end = mid - 1
    }
}
```

#### Outer `for-loop`  

```kotlin

for (i in 0 .. (text.length - pattern.length)) {
    
}
```

* This is the sliding window of the text string that moves from left to right, character by character.
* And for the window length, it uses the inner binary search.
* We will find hashes of different substrings of different lengths.
* For example, for length 1, 2, 3, ... < text.length
* Here, `t` represents the starting index of the window.
* It means that for each `t`, we will have different substrings of different lengths.
* For example, suppose `text = abcdefg`.
* So, when `t = 0`, we get different substrings like `a`, `ab`, `abc`, `abcd`, `abcde`, `abcdef`, `abcdefg`, etc.
* Similarly, when `t = 1`, we get different substrings like `b`, `bc`, `bcd`, `bcde`, `bcdef`, `bcdefg`, etc.
* And so on...
* We compare these windows with the pattern.
* Note that we are going to use binary search.
* It means that we optimize this process by discarding certain windows and substrings.
* For example, we may not start from length = 1.
* We may start from length = 3.
* And if we find that certain window matches with the pattern, we skip all the windows having length less than 3.
* And, if we find that there is no match with length 3, we discard all the windows having length more than 3.

**How do we compare the windows of the text with the windows of the pattern?**

* Binary search gives us a length to try.
* Using this length, we can find and compare the hash codes of different substrings of this given length.
* However, the formula needs the starting point, too.

$H(a, l) = (H(a + l) - (H(a) * base^{l})) \mod  prime$

**How do we get these starting points?**

* The outer `for-loop` gives us the starting point for the **text window**.

```kotlin

// The `i` represents the starting point for each window
for (i in 0 .. (text.length - pattern.length))

```

* Similarly, the `while-loop` gives us the starting points for the **pattern windows**.

```kotlin

var p = 0 // The starting index of the pattern window. 
while (p < pattern.length) {
    
}

```

**How do we arrange these two `loops`?**

* According to the problem statement, we are finding the patterns (with allowed mismatches) in the text.
* And the problem conveys that $text.length >= pattern.length$.  
* So, the **text-for-loop** becomes the outer-for-loop, and the **pattern-while-loop** becomes the inner-while-loop.
* So, it goes as follows:

```kotlin

for (i in 0 .. (text.length - pattern.length)) {
    var p = 0
    while (p < pattern.length) {
        // We get `length` from the binary search.
    }
}

```

**Binary search: How do we get `start` and `end` to get the `mid` length?**

* Now, in order to get the `length`, the binary search needs two things: **Start** and **End**.
* So, how do we get that?
* We might think about the `i` of the `outer-for-loop`, and `j` of the `inner-for-loop`.
* But are they mutable variables or fixed values?
* Because we have seen that we take these `start` and `end` variables as mutable variables in the binary search process.
* So that we can change their values during our binary search.
* They act as boundaries or a range that we increase or decrease (i.e., change, mutate) during the binary search process. 
* Let us recall the objective.
* The `i` of the `outer-for-loop` represents the starting index of a text window.
* The `j` of the `inner-for-loop` represents the starting index of a pattern window.
* Let us say, the text is `abcdefgh`, and the pattern is `xyabcdef`.
* And for a particular value of `i`, for example, suppose `i = 0`, we will get the following windows (substrings) of different lengths for the text:
```
a, ab, abc, abcd, abcde, abcdef, abcdefg, abcdefgh
```
* Suppose that at some point, we are at window `abc`.
* At this moment, the starting index of the text window is `i = 0`.
* And the length of the window is `3`.
* Now, we will be comparing this window with each pattern window having the same length.
* So, we will be comparing the text window `abc` with the following pattern windows:
```
xya, yab, abc
```
* At some point, we find that the text window `abc` matches with the pattern window when the starting index of the pattern window is `p = 2`.
* And the moment we find a matching window, we want to increase the bar.
* We want to stretch the length to see the longest common substring at this point.
* We want to check how far they can go (how far they match).
* Now, the purpose of the binary search is to reduce these efforts to a logarithmic time.
* So, instead of increasing the length of the window by 1 each time, we want to take certain `length`.
* And based on the result of this `length`, either we increase the bar or decrease the bar.
* For example, suppose the starting index of the text window is `i = 0`, and length of the window is `l = 4`.
* So, it is the `abcd` substring (window) of the text.
* Now, we find that it matches with the starting index of the pattern window `p = 2` and `l = 4`.
* Now, we don't need to compare any other windows (substrings) of the text where `i = 0` and `l <= 4` with any other windows (substrings) of the pattern where `p = 2` and `l <= 4`.
* Because we know that all such windows will match with each other.
* So, we want to increase the bar.
* Now, we are interested in `l > 4`.
* The length `l` is based upon the values of `start` and `end`.
* And if we take (treat) `i` as the `start` value, we cannot change it.
* Because `i` is a fixed, immutable value of the relevant `for-loop`.
* So, we take some extra variables.
* We store the value of `i` into a mutable variable.
* So, it becomes: 

```kotlin

for (i in 0..(text.length - pattern.length)) {
    var t = i
    var p = 0
    while (p < pattern.length) {
        
    }
}

```
* Let us understand what each variable represents here.

> `i` represents the starting index of the text window. But it is a fixed (immutable) value that we cannot change.  
> In a binary search, we change the `start` and the `end` boundary or range.  
> It means that we need these `start` and `end` variables as mutable variables.  
> So, we take a mutable variable `t` that represents the starting index of the text window, but it is mutable.  
> Then, we change the starting index of the pattern to get different pattern windows that we can compare with the text window.  
> Again, this starting index has to be mutable.  
> So, we take `p` that represents the starting index of the pattern window.    

* Now, we know that `p` represents the starting index of the pattern.
* And we move `p` to get different windows of the pattern.
* For example, if the pattern is `xyabcde`, and `p = 2`, then the longest possible length and the last pattern window is:

```
abcde
```

* Notice that if pattern is `xyabcde`, and when `p = 2`, the longest length and the last pattern window is `pattern.length - p = 7 - 2 = 5`.
* That's the `end` boundary.
* Beautiful math!
* So, it is like:

```kotlin

start = 0
end = pattern.length - p

```

**Where does this `start` and `end` boundary go?**

* According to the problem, `text.length >= pattern.length`.
* So, we compare each `text window` with all the `pattern windows`.
* So, it goes like below:

```kotlin

for (i in 0..(text.length - pattern.length)) {
    var t = i
    var p = 0
    while (p < pattern.length) {
        start = 0
        end = pattern.length - p
        while (start <= end) {
            mid = start + (end - start) / 2
            // Find and compare the substrings of length `mid`
        }
    }
}

```

**Do you understand what each line convey and do in the above code?**

**Why do we take the smallest possible length, `start = 0` instead of `start = 1`?**

**Why don't we just take only those windows which has a length of $pattern.length$ ?**  

**Why don't we compare the entire pattern with the text windows?** 

* Because if we only take the entire length, we will not be able to know about the number of mismatches if there are any.

**How can we get to know that allowing k-mismatches would make two different substrings equal?**

* For example, suppose that at some point, we compared two substrings: `abc` and `abx`.
* Now, the hash codes of these two substrings will not match.
* However, we are allowed to consider it a "match" if "k-allowed mismatches = 1".
* So, how do we incorporate this factor?

---

* Well, that's a good point.
* Between "abc" and "abx", the exact matching (common) substrings would be "ab".
* So, the length of the matching (common) substrings would become the value of the variable `matchLen`.
* In this example, it will be `matchLen = 2`.
* Now, if we are yet to cover the entire string, after the `matchLen`, we will land on the `mismatch` character.
* We will see it in action soon.

**And what about those two starting indices: `t` and `p`? How do we get them?**

* Input requires the total number of substrings we found, followed by their starting indices in the text.
* How do we find the starting indices of matched substrings?

```
for (t in 0 until (text.length - pattern.length)) {
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
    }
}
```

* Now, we need to add a couple of more things here.
* `matchLen` counter
* `mismatches` counter
* Starting index of the pattern matching with mismatches

**Did you understand why do we need to add them?**

**Starting index of the pattern matching (with allowed mismatches) in the text string**

* We have been asked to provide the starting index of the pattern matching with mismatches.
* For example, if the text is `abcdef` and the pattern is `abx` with `k = 1`, then we need to output: `1 0`.
* In the output, `1` represents the total matched patterns.
* `0` represents the starting index in the text that matches the pattern (can use allowed mismatches).

**matchLen counter = jump**

* Why do we use it?
* It is our `jump`.
* This is where we leverage the binary search implementation.
* For example, suppose the text is `abcdef` and the pattern is `abcxyz` and the allowed mismatch is `k = 1`.
* Now, as long as we learn it through the binary search that `abc` matches, we can skip the character-by-character
* matching for `b` and `c`, and we can jump over `d` in the text and `x` in the pattern.
* So, we can directly assign that pointer (index) `t = 3` and `p = 3`.
* Let us implement this part.

```
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why do we take `matchLen` here between these two `while` loops?
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen
    }
}
```

* And notice the interesting pattern here.
* The point where the binary search exits is the `mismatch` character `d` in the text Vs `x` in the pattern.
* But this is valid only `if index p is less than pattern.length`.
* For example, `p += matchLen` could make the `p` jump to `pattern.length`.
* For example, if the text was `abcdef` and the pattern was `abc`, the `jump` would make `p = 3`.

```
p = p + matchLen
p = 0 + 3
p = 3
```

* It means that at the end of the binary search, if `p < pattern.length`, then `p += matchLen` represents the mismatch position.
* Let us implement this.

```
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take the `matchLen` here between these two `while` loops?
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            mismatches++
        }
    }
}
```

* This setup, the outer `for` loop, and the two inner `while` loops, where the innermost `while` loop represents a binary search, is essentially a `character-by-character` comparison with the power of `binary search`.
* Imagine a digital game where we have this `binary search` power.
* We use this `binary search` power, and it generates `matchLen` that we can use to `jump`.
* Every jump is an acceleration and helps us finish the process (work) faster.
* However, it is also possible that we always get `matchLen = 0`.
* For example, the text string is `abcdef`, the pattern string is `xyz`, and `k = 0`.
* After using the `binary search` power, we find that `matchLen = 0` only.
* It means that:

```
t += matchLen
t = 0 + 0
t = 0 // This doesn't make any progress. It was at 0, and it ended up at `0` only after the binary search.
```

* And similarly

```
p += matchLen
p = 0 + 0
p = 0 // Same here. It did not make any progress. It was at 0, and it ended up at `0` only after the binary search.
```

* So, fall back to character-by-character comparison (process).
* We couldn't jump.
* But we can walk.
* So, we make `t` and `p` one step forward.

```
t++
p++
```

* But we need to ensure that `p++` does not go beyond `pattern.length`.
* So, it becomes safer as:

```
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            mismatches++
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
}
```

**Why do we `jump` the pointers `t` and `p` two times?**

```
        // First time
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            mismatches++
            // Second times
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
```

* Suppose the text string is `abcdef` and the pattern is `abcxyz`.
* Now, we started the process with pointer `t = 0 = character a` of the text string.
* The binary search gives us `matchLen = 3`.
* So, we jump as:

```
t += matchLen
t = t + matchLen
t = 0 + 3
t = 3 // at character `d` of the text string `abcdef`.
```

* And:

```
p += matchLen
p = p + matchLen
p = 0 + 3
p = 3 // at character `x` of the pattern string `abcxyz`.
```

* See the magic of the binary search result here.
* As long as `p < pattern.length`, the `t += matchLen` and `p += matchLen` always end up on the `mismatch`.
* We count that.
* That's why we perform `mismatches++`.
* It means we have already acknowledged (and hence processed!) the comparison of the current `t` and `p` positions.
* So, we need to move on!
* How do we move on?

```
t++
t = t + 1
t = 3 + 1
t = 4 // at character `e` of the text string `abcdef`.
```

* And

```
p++
p = p + 1
p = 3 + 1
p = 4 // at character `y` of the pattern string `abcxyz`.
```

* The binary search gives us the `matchLen`.
* And by jumping over the `matchLen`, we land upon the `mismatch`.
* Imagine the `matchLen` as a highway, and `mismatch` as a local, rough road.
* The moment we cross the `matchLen`, we land upon the local-rough road.
* We not only know the `matchLen`, but we also know the `mismatch`.
* So, we not only jump over the `matchLen`, but we also jump over the `mismatch`.
* That's why:

```
        // First time
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            mismatches++
            // Second times
            // Already acknowledged the mismatch. Move on.
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
```

**mismatch counter**

* We need to keep track of our wild card - the number of allowed mismatches.
* For example, `text = abcdef` and `pattern = abx`, and `k = 1`.
* We can use `k = 1` to consider `c = x` in `abc = abx`.
* If `k` was `0`, and we find that `mismatches > k`, we can't consider `abc = abx`.
* In that case where `k = 0`, the pattern `abx` does not fit in the text `abcdef`.
* With every mismatch, we can use the allowed `mismatches` wildcard.

**When, where, and how do we use the mismatch counter?**

* How does the binary search work here?
* If we find a match, we increase the length to see if we can find a longer substring match.
* If we do not find a match, we decrease the length to see if we can find a shorter substring match.
* See, at some point, the binary search finishes.
* What does the end of the binary search indicate?

> It covered all the possible lengths (from 0 to pattern.length).

* The binary search gives us the `matchLen` that we can use to make a `jump`.
* Once we make a jump, and if we are still under `p < pattern.length`, we land upon a `mismatch`.
* When and where the `matchLen` ends, the `mismatch` starts.
* Otherwise, if the `mismatch` was not a mismatch, it would have become part of the `matchLen`!
* Hence, after the `matchLen`, it is always a `mismatch` (- as long as `p < pattern.length`).
* So, it becomes:

```
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
            mismatches++
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
}
```

**What does the `while (p < pattern.length)` indicate?**

* It indicates the `character-by-character` comparison part of the `pattern`.
* The pattern index `p` always starts with `p = 0` before and above this `while` loop.
* Inside this `while` loop, we use the `binary search`.
* The `binary search` gives us the `matchLen`.
* We jump over the `matchLen` and land upon the `mismatch`.
* We cross the `mismatch` by `p++`.
* We do this to cover the entire pattern length.
* But we need to ensure that these increments of `p` do not go beyond the `pattern.length`.
* Because `p` is something that we use as a starting index inside the `binary search` with length `mid` of `pattern`.
* We use it as `pattern(p, mid)` to compare a substring with the text substring `text(t, mid)`.
* So, we need to ensure that we don't get `IndexOutOfBounds` exception.
* Hence, `p` must be less than `pattern.length`.
* And that's why this condition and this loop: `while (p < pattern.length)`.

**How do we discard the comparison as long as we find too many mismatches?**

* We can do this after we increase the `mismatches` counter.
* So, it becomes:

```
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
            mismatches++
            if (mismatches > k) {
                // If the window at `t` has too many mismatches, discard it.
                break
            }
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
}
```

**How do we finally store the result: Starting index of the pattern matching with mismatches?**

* What will be the starting index of the pattern matching with mismatches?
* It is `i`.
* (It is not `t`. We use `t` for comparison and jump. But the starting index remains `i`.)
* So, we need to store it at the end of the comparison, before we start the next comparison.
* So, it becomes:

```
val result = mutableListOf<Int>()
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take the `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
            mismatches++
            if (mismatches > k) {
                // If the window at `t` has too many mismatches, discard it.
                break
            }
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
    if (mismatches <= k && matchLen + mismatches == pattern.length) {
        result.add(i)
    }
}
```

* The outermost `while` loop, `while (p < pattern.length)`, starts with `p = 0` and covers the pattern length.
* Every time we find a `mismatch`, we increase the `mismatches` counter.
* If the `mismatches` counter is `<= k`, it inherently means that we have found a match.
* However, just in case where `pattern.length > text.length`, we can put this extra condition:

```
matchLen + mismatches == pattern.length
```

**Where should we initialize the `mismatches` counter?**

* Before we start the outermost `while` loop: `while (p < pattern.length)`.
* Because it is the `while` loop that compares the pattern against a new text window that starts with `i`.
* So, we initialize the `mismatches` counter as soon as we start the new window.
* So, it becomes:

```
val result = mutableListOf<Int>()
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0 until (text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Start the `mismatches` counter as soon as we start the new text window.
    var mismatches = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take the `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
            mismatches++
            if (mismatches > k) {
                // If the window at `t` has too many mismatches, discard it.
                break
            }
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
    if (mismatches <= k && matchLen + mismatches == pattern.length) {
        result.add(i)
    }
}
```

**Where should we initialize the `matchLen` variable? Why not as soon as we start the new text window?**

* It is the `binary search` that gives the `matchLen` for the current window.
* So, we initialize before the `binary search`.
* If we initialize it at the start of the new text window, it will be an accumulation of multiple `p` characters and multiple `mid` lengths.
* It is the binary search that gives us the `matchLen` result.
* So, the `matchLen` must be re-initialized before every binary search.
* The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
* But we are also changing `p`.
* Each `p` represents a unique comparison.
* For example, text = `abcdef`, and pattern = `xycdef`.
* Now, for `t = 2`, it might go as below:
* For every `t`, `p` starts with `0`.
* It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
* Now, `p` moves to the next position.
* So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
* Again, `p` moves to the next position.
* So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
* The `matchLen` result is for specific starting positions `t` and `p`.
* We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
* Changing `p` must reset the `matchLen`.
* Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
* That's why the right place to initialize (or reset) the `matchLen` is between `while (p < patten.length)` and before we start the binary search.

**What should be the `end-index limit` for the sliding window?**

* It should be: `text.length - pattern.length` inclusive.
* For example, if the text string is `abcdef` and the pattern is `xyz`, then we would go till `i = 3`.
* So, we would go till `i = text.length - pattern.length = 6 - 3 = 3 = d`.
* So, the sliding window loop becomes:

```
for (i in 0..(text.length - pattern.length)) {
    // The rest of the code as it is
}
```

* So, the code becomes:

```
val result = mutableListOf<Int>()
// Did you understand the purpose of this outer for loop?
// This is the sliding window of the text string that moves from left to right, character by character.
// And for the window length, it uses the inner binary search.
for (i in 0..(text.length - pattern.length)) {
    // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
    var t = i
    // Did you understand why we take the variable `p` outside the `while` loops?
    // For each new text window, we start pattern comparison from the beginning.
    // This is to check if the `pattern` starts from `t`.
    var p = 0
    // Start the `mismatches` counter as soon as we start the new text window.
    var mismatches = 0
    // Did you understand the purpose of this outer while loop?
    while (p < pattern.length) {
        // binary search
        var start = p
        // Did you understand this?
        // Suppose, the pattern is `xycdef`, where |P| = 6, and `p = 3` representing the character `d`.
        // Now, the longest possible substring length from this `p` position is: `pattern.length - p`.
        // That is: `pattern.length - p = 6 - 3 = 3`.
        // We use `start` and `end` to calculate `mid`, and `mid` represents a substring length.
        // The `start` represents the smallest possible substring length.
        // And `end` represents the highest possible substring length.
        // We keep changing the `start`, `end`, and `p` values based on the `match length`.
        // Hence, we need to adjust the value of the highest possible substring length.
        // And this is the formula to ensure we consider and calculate it properly.
        var end = pattern.length - p
        // Did you understand why we take the `matchLen` here between these two `while` loops?
        // It is the binary search that gives us the `matchLen` result.
        // So, the `matchLen` must be re-initialized before every binary search.
        // The binary search tells us the maximum `matchLen` starting from `t` in text, and `p` in pattern.
        // But we are also changing `p`.
        // Each `p` represents a unique comparison.
        // For example, text = `abcdef`, and pattern = `xycdef`.
        // Now, for `t = 2`, it might go as below:
        // For every `t`, `p` starts with `0`.
        // It might compare `cde` with `xyc`, then `cd` with `xy`, then `c` with `x`.
        // Now, `p` moves to the next position.
        // So, it might compare `cde` with `ycd`, then `cd` with `yc`, then `c` with `y`.
        // Again, `p` moves to the next position.
        // So, it might compare `cde` with `cde`, then `cdef` with `cdef`.
        // The `matchLen` result is for specific starting positions `t` and `p`.
        // We should not apply, accumulate, or mix this `matchLen` with a different starting position, `p`.
        // Changing `p` must reset the `matchLen`.
        // Hence, every time we change `p`, and before we start the binary search, we reset the `matchLen`.
        var matchLen = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            val (hash1a, hash2a) = textHashes(t, mid)
            val (hash1b, hash2b) = patternHashes(p, mid)
            if (hash1a == hash1b && hash2a == hash2b) {
                matchLen = mid
                // See if the longer length matches
                start = mid + 1
            } else {
                // See if the shorter length matches
                end = mid - 1
            }
        }
        t += matchLen
        p += matchLen

        if (p < pattern.length) {
            // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
            mismatches++
            if (mismatches > k) {
                // If the window at `t` has too many mismatches, discard it.
                break
            }
            t++
            p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
        }
    }
    if (mismatches <= k && matchLen + mismatches == pattern.length) {
        result.add(i)
    }
}
```

### Observation and Insights: ToDo// Confirm this understanding.

#### Variables

* `i` = Text index. Starting position of the sliding window.
* `t` = Text index. Always starts with `t = i`. It uses the "Binary search" power ("Glance and jump").
* `p` = Pattern index. Always starts with `0` for each new text window.
* `start, end, mid` = Binary search team. `mid` is the "Glance" length based on "start" and "end".

#### Text: Sliding Window

* Inside the window, it always moves forward, stays within the boundaries, and tries (compares) different lengths.

#### Pattern

* It always starts with `0` for every new sliding window.
* It increases with `t`.
* In the worst case, it matches character by character.
* But it doesn't go beyond the "k-Allowed mismatches" limit.
* So, the moment we find more mismatches than allowed, we break the loop and slide the (start a new) window.

### TL;DR

* Precomputed prefix hashing
* Sliding window for the text string
* Initialize the `mismatches` counter
* Binary search for length: Glance and Jump
* Jump over `matchLen`
* Count mismatches
* If `mismatches <= k` and `matchLen + mismatches == pattern.length` --> Maybe, we have found a matching pattern!
* Otherwise, slide the window until we reach the end of the text string.
* In one sentence:

> It quickly finds the `matchLen` (using allowed mismatches), where the matched substring starts from the position `t` in the text and `p` in the pattern.


## Time Complexity

* Precomputed prefix hashes for the text and the pattern: |T| + |P|
* Binary search: log(|P|)
* The binary search runs for: |T| Times (But exits as long as mismatches > k. So, runs for `k + 1` times.)
* Binary search total time: |T| * k * log(|P|) (Took `k` from `k + 1`, ignoring the constant `+ 1`).
* Hence, the total time becomes:

```
= Precomputation cost + Main loop cost
= (|T| + |P|) + ( |T| * k * log(|P|) )
```

* Now, compared to the precomputation cost, the main loop cost is higher due to the multiplicative nature.

```
|T| * k * log(|P|) >> |T|
```

* Similarly:

```
|T| * k * log(|P|) >> |P|, given that |T| >= |P|
```

* Hence, the dominant term is:

```
O( |T| * k * log(|P|) )
```

* Now:
* As per the problem statement, `k <= 5`, which makes `k` a small constant.
* In that case, the effective total time becomes:

```
O(|T| * log(|P|))
```

## Space Complexity

* We use multiple arrays of size: |T| and |P| for the precomputation.
* Hence, the auxiliary space is: O(|T| + |P|).

## Grader output
```
Good job! (Max time used: 2.50/5.00, max memory used: 147873792/536870912.)
```