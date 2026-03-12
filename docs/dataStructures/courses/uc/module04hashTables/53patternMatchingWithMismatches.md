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
  * [Dry Run](#dry-run)
  * [while (start <= end)](#while-start--end-)
  * [Quick Example](#quick-example)
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
* `p` and $t[i : i + p) = t_{i}t_{i+1} ···t_{i+n-1}$ differ in at most `k` positions.

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
**Pseudocode (core part) of naive implementation:**  

* We want to check if the pattern exists (one or multiple times) in the text with `k` allowed mismatches.
* We compare two strings character by character.
* For example, suppose we have the following text and pattern:

```

Text:     a   a   b   b   a   b   a   a   b

Pattern:  a   a   a   b   

k (Allowed mismatches) = 1


                |   |   |   |   |   |   |   |   |   |    
---------------------------------------------------------
                |   |   |   |   |   |   |   |   |   |    
 Indices (i)    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |    
                |   |   |   |   |   |   |   |   |   |    
----------------|---|---|---|---|---|---|---|---|---|----
                |   |   |   |   |   |   |   |   |   |    
 Text           | a | a | b | b | a | b | a | a | b |    
                |   |   |   |   |   |   |   |   |   |    
----------------|---|---|---|---|---|---|---|---|---|----
                |   |   |   |   |   |   |   |   |   |    
 Pattern        | a | a | a | b |   |   |   |   |   |    
                |   |   |   |   |   |   |   |   |   |    
---------------------------------------------------------
                |   |   |   |   |   |   |   |   |   |    

```

* Now, it is given that the text length is greater than or equal to the pattern.
* In other words, pattern length is less than or equal to the text.
* To determine if the text contains the given pattern, we need to check each index `i` starting from `i = 0`. 
* For each index, we form a text window of the pattern's length and compare each character of this window with the corresponding characters of the pattern.
* For example, suppose that the text is `aabbabaab`, the pattern is `aaab`, and the `k(Allowed Mismatches)` is `1`.
* Now, when `i = 0`, we get the text window `aabb` of pattern's length.
* Note that `i` indicates the starting index of a text window.
* We compare each character of this window with each character of the pattern.
* So, to cover each character of this text window, we take a variable `t`.
* The variable `t` starts from `i`, and moves character by character to cover all the characters of the text window.
* Similarly, to cover each character of the pattern window, we take a variable `p`.
* The variable `p` always starts from `0`, because `0` is the starting index of the pattern.
* So, it looks like below:

> i = 0; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.   
> t = 0; p = 0; Text[0] Vs. Pattern[0] = a Vs. a = Match!  
> t = 1; p = 1; Text[1] Vs. Pattern[1] = a Vs. a = Match!  
> t = 2; p = 2; Text[2] Vs. Pattern[2] = b Vs. a = Mismatch!  
> t = 3; p = 3; Text[3] Vs. Pattern[3] = b Vs. b = Match!  

* While iterating over the pattern, if we find that number of mismatches exceeds than the k-allowed mismatches, we conclude that the text window that starts from `i` has more mismatches than allowed.
* So, we exit the pattern loop and repeat the comparison for the next `i`.
* Once we finish iterating over the pattern, if the total number of mismatches we found is less than or equal to the k-allowed mismatches, we conclude that the text window that starts from `i` matches with the pattern.
* In this case, we found that the text window that starts from `i = 0` matches with the pattern with at most `k` mismatches.
* So, we add `i = 0` to the result.
* We checked all the characters of the pattern.
* We finished the iteration over the pattern.
* So, let us see the next text window that starts from `i = 1`.

> i = 1; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.      
> t = 1; Text[1] Vs. Pattern[0] = a Vs. a = Match!    
> t = 2; Text[2] Vs. Pattern[1] = b Vs. a = Mismatch!  
> t = 3; Text[3] Vs. Pattern[2] = b Vs. a = Mismatch!  
> The number of mismatches exceeded than the allowed mismatches.    
> So, there is no point in continuing the comparison with other characters of the pattern.    
> So, we exit the pattern iteration and repeat the comparison for the next `i`.    

* Now, `i = 2`:

> i = 2; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.    
> t = 2; Text[2] Vs. Pattern[0] = b Vs. a = Mismatch!  
> t = 3; Text[3] Vs. Pattern[1] = b Vs. a = Mismatch!  
> The number of mismatches exceeded than the allowed mismatches.  
> So, there is no point in continuing the comparison with other characters of the pattern.  
> So, we exit the pattern iteration and repeat the comparison for the next `i`.  

* Now, `i = 3`:

> i = 3; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.    
> t = 3; Text[3] Vs. Pattern[0] = b Vs. a = Mismatch!  
> t = 4; Text[4] Vs. Pattern[1] = a Vs. a = Match!  
> t = 5; Text[5] Vs. Pattern[2] = b Vs. a = Mismatch!  
> The number of mismatches exceeded than the allowed mismatches.  
> So, there is no point in continuing the comparison with other characters of the pattern.  
> So, we exit the pattern iteration and repeat the comparison for the next `i`.  

* Now, `i = 4`:

> i = 4; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.    
> t = 4; Text[4] Vs. Pattern[0] = a Vs. a = Match!  
> t = 5; Text[5] Vs. Pattern[1] = b Vs. a = Mismatch!  
> t = 6; Text[6] Vs. Pattern[2] = a Vs. a = Match!  
> t = 7; Text[7] Vs. Pattern[3] = a Vs. b = Mismatch!  
> The number of mismatches exceeded than the allowed mismatches.    
> And we also finished the iteration over the pattern.    
> So, we repeat the comparison for the next `i`.    

* Now, `i = 5`:

> i = 5; `t` for the text window starts from `i`, but `p` for the pattern always starts from `0`.      
> t = 5; Text[5] Vs. Pattern[0] = b Vs. a = Mismatch!    
> t = 6; Text[6] Vs. Pattern[1] = a Vs. a = Match!    
> t = 7; Text[7] Vs. Pattern[2] = a Vs. a = Match!    
> t = 8; Text[8] Vs. Pattern[3] = b Vs. b = Match!    
> We finished the iteration over the pattern.
> After finishing the iteration over the pattern, the total number of mismatches we found is less than or equal to the k-allowed mismatches.
> It means that the text window that starts from `i = 5` matches with the pattern with at most `k` allowed mismatches.
> So, we add `i = 5` to the result.

* Now, we cannot take `i = 6`, because that will give us the text window `aab` whose length is less than the pattern.
* Similarly, we cannot take `i = 7`, because that will give us the text window `ab` whose length is less than the pattern.
* Similarly, we cannot take `i = 8`, because that will give us the text window `b` whose length is less than the pattern.
* It means that the last point `i` can go for a text window is `5` for the text whose length is `9` when the pattern length is `4`.
* If we do the math, it is `text.length - pattern.length = 9 - 4 = 5`.
* So, `i` can only go from `0` to `text.length - pattern.length`.

```
// This outer loop increments the starting index of the text window
for (i in 0 .. (text.length - pattern.length)) {
    // A variable that increments the pointer within each text window 
    var t = i 
    // This inner loop increments the pointer of the pattern string
    for (p in 0 until pattern.length) {
        // Comparing characters
        if (text[t++] != pattern[p]) {
            // With each mismatch, we increase the "mismatch" counter
            mismatches++
            if (mismatches > k) {
                // We crossed the maximum allowed mismatches
                // Exit the pattern loop and try next `i` if possible
                break
            }
        }
    }
    // We finished iterating over the pattern
    // Let us see how many mismatches we encoutered along the way
    // Result based on the mismatch counter
    if (mismatch <= k) {
        // The text window that starts from `i` matches with the pattern with at most `k` mismatches
        result.add(i)
    }
}
```

* Now, suppose that the text length is `2_00_000` and the pattern length is `1_00_000`. 
* Then, with this naive approach, we are going to take `T * P = 2_00_000 * 1_00_000 = 2_00_000_00_000 = TLE!` time!
* So, we need to come up with a better solution.
* The main problem in the naive algorithm is that we are checking character by character.

---

* What if we can check characters in bulk (substrings)?
* But how do we decide the length?
* We don't know what is the best length to take for the comparison.
* So, we take the help of the binary search.
* The binary search quickly finds the length that matches (common substring) in the logarithmic time.
* And when the `match` ends, the `mismatch` starts!
* So, if we fly over the `match`, we land on the `mismatch`.
* The benefit here is that we quickly cover the `matching` part.
* We cover the `matching` part in a logarithmic time instead of taking `|T| * |P|` time.

---

* Now, our objective is to get a `length` from the binary search.
* Then, we will compare the substrings of this `length`.
* If they match, we will try the longer `length`.
* If they don't match, we will try the shorter `length`.
* When we exhaust (after trying the possible lengths, by concluding something, or anyhow), we will have a `matchLen`.
* The `matchLen` means a common substring between the text and the pattern of some `length`.
* It means that, after this `matchLen`, we have a `mismatch`.

---

* For example, suppose the text has 10,000 characters, the pattern has 1000 characters, and `k = 0`.
* Also, each text window has first 999 characters identical with the pattern.
* Only the last character of each text window is different from the last character of the pattern.
* Only the last character causes each text window mismatch with the pattern window.
* The naive approach will find this after 999 steps, on the 1000th step for each text window.
* On the other hand, the binary search finds this `matchLen = 999` and the `mismatch` significantly faster. 
* We have some idea about how the binary search works.

---

* It might check the longest common substring for the `length = 500`.
* It will find that, it is a match.
* So, it will increase the bar (the range).
* Next time, maybe it will check for the `length = 750`.
* Again, it will find that, it is a match.
* So, it increases the bar again.
* Now, it might check for `length = 1000`.
* It will not match.
* So, it decreases the bar.
* Now, it might check for `length = 875`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 940`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 970`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 985`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 992`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 996`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `length = 998`.
* It will be a match.
* So, it increases the bar.
* Now, it might check for `legnth = 999`.
* It will be a match.
* So, it increases the bar.
* But now the range becomes invalid.
* So, we can see how quickly we could find the `matchLen` that follows the possible `mismatch`.
* We didn't try `999` times.
* So, let us implement the binary search.

---

**How do we implement the binary search?**  
**Where do we implement the binary search?**  
**What does it need?**  
**How does it get it?**  

---

* A binary search needs a range or boundaries: `start` and `end`.
* We are expecting to get a `length`.
* So, these `start` and `end` boundaries/ranges represent the `minimum length` and the `maximum length` respectively.

---

**What is the `maximum length`?**

---

* 

//ToDo: 

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

**How does that limit the window length?**

* //ToDo: Explain.

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
* We may change the starting index of the pattern window to cover each substring (window) of the pattern having the same length as the text window (substring).

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


```

 // Sliding window on the text                      
                                                    
 for (i in 0 .. (text.length - pattern.length))     
                                                    
|    t = i; p = 0                                   
|                                                   
|                                                   
+-------  while (p < pattern.length)                
                                                    
         // Comparing every character of the pattern
                                                    
          |                                         
          |                                         
          |                                         
          |                                         
          |                                         
          +--------  while (start < = end)          
                                                    
          |          // Using the binary search     
          |                                         
          |                    |                    
          |                    |                    
          |                    |                    
          |                    v                    
          |                                         
          |                   t += matchLen         
          |                                         
          |                   p += matchLen         
          |                                         
          |                   mismatches++          
          |                                         
          |                   t++                   
          |                                         
          |                   p++                   
          |                                         
          |                                         
                                                    
          mismatches <= k?                          
                                                    
          Add `i` to the result.                    

```

---

* We compare every text window that starts from `i` with the pattern.
* We take `t` to track (monitor) how many characters we have processed for the text window.
* The variable `t` starts from `i`.
* We take `p` to track (monitor) how many characters we have processed for the pattern.
* The variable `p` starts from `0`.
* Without binary search, we would have to compare every character of the pattern with the text window.
* So, inside the pattern iteration, we take binary search.
* Using the binary search, we get the best `matchLen` and total `mismatches` quickly.
* After the binary search, we make `t` and `p` jump over the `matchLen`, and land on the `mismatch`.
* If `mismatches > k`, `p > pattern.length`, or `t > text.length`, we break the loop for the current `i`, and repeat for the next `i`.
* After the pattern iteration, if `mismatches <= k`, we add `i` to the result.

---

* Imagine that our left index finger `i` is on the starting index of the text window.
* And imagine that the variable `t` is our right index finger on the same text window.
* The variable `t` represents the index of a particular character which is being checked or going to be checked next.
* In other words, once we finish checking a particular character, the variable `t` moves forward, past the character.
* So, the past values of the variable `t` represents the number of characters we have already checked so far.
* Initially, `t = i`.
* Now, if `t` moves character-by-character, then it becomes too slow.
* So, we provide the support of the binary search power to the variable `t`.
* The binary search provides the `matchLen` using which the variable `t` can skip (fly over) all the matched characters, and land on the `mismatch` character.
* In other words, instead of checking character by character, we check characters in bulk (substring/s).
* To check characters in bulk (substring/s), we use binary search power.
* The binary search represents the `length` generation machine.
* We get a particular `length = l` from the binary search to compare and check.
* Now, if we find that it matches, it means that all the substrings having length less than `l` also match.
* For example, if we find that `abcd` is the common substring, then `a`, `ab`, `abc` are also common substrings that we don't have to check.
* We can say that the substring that starts from `a` and has a length of `4` is the common substring.
* So, we get the `matchLen = 4`.
* Similarly, if we find that `abcd` does not match, then it means that there is no point in checking a substring that starts from `a` and has a length of `> 4`. 
* Because if `abcd` does not match, then it is obvious that `abcde`, `abcdef`, `abcdefg`, etc. will also not match.
* So, the idea is to get the `matchLen` using the binary search power.
* The benefit of using the binary search power is to quickly find the `matchLen` and the next `mismatch`.
* For example, if the text window is `abcdefg` and the pattern window is `abcxefg`, we quickly find that `abc` is the `matchLen`.
* And the immediate character past the `matchLen` represents the `mismatch`.
* So, once we jump over the `matchLen`, we land on the `mismatch`.
* So ultimately, the binary search power helps us find the `mismatch` quickly.
* Now, the binary search requires `start` and `end` boundaries/ranges.
* Here, the binary search power gives a particular `length` to check.
* So, the variables `start` and `end` represent the `length` boundaries/ranges.
* In other words, `start` represents the `minimum length` and the `end` represents the `maximum length`.
* Based on these values, we find the `mid` length, that we give to the prefix hash formula.
* Now, we know that we are comparing the text window with the pattern window.
* And it is given that the $text.length >= pattern.length$.
* So, we are sure that the `end` value cannot be greater than the `pattern.length`.
* However, we are not comparing each text window with the entire pattern length.
* For example, what is the point of checking the text window `ab` with the pattern window `abcxefg` as it will never match.
* In other words, we should not compare the text window whose length is different from the pattern window or the other way around.
* In the same way, we cannot always compare the entire pattern with each text window.
* Because if the substrings do not match with each other, we cannot know the number of mismatches.
* It means that we have to compare each text window with the fragments of the pattern window using the binary search power.
* If we find that the substrings match with each other, we increase the length bar.
* Otherwise, we decrease the length bar.
* Now, to monitor (track) the number of characters we have checked on the text window, we use the variable `t`.
* Similarly, to monitor (track) the number of characters we have checked on the pattern window, we use the variable `p`.
* For example, suppose the text is `abcde`, and the pattern is `xybcx`.
* So, when `i = 0`, we get different text windows as below:

```
a, ab, abc, abcd, abcde
```

* Suppose, the current text window is `abc`.
* So, we compare this text window with all the pattern windows.
* So, we get the following pattern windows of the same length:

```
xyb -> Starting index p = 0   
ybc -> Starting index p = 1   
bcx -> Starting index p = 2  
```

* It means that we need some variable to monitor (track) the number of characters we have processed so far on the pattern for a particular window.
* So, we take the variable `p`.
* Now, using this variable `p`, we decide the `end` value.
* For example, if `p = 0`, then we can compare the entire `pattern.length`.
* If `p = 1`, then the maximum pattern length we can compare is `pattern.length - 1`.
* For example, if the pattern is `xybcx`, which is of length `5`.
* And if `p = 1`, then the maximum pattern that we can compare is `ybcx` whose length is `4` which is `pattern.length - p = 5 - 1 = 4`.
* So, we get `end = maximum length` value using `p`.
* We use this value for the binary search process to find the `mid length`.
* Then, we give this `length` to the prefix hash formula. 
* The prefix hash formula needs two things: The starting index of the substring, and the `length`.
* The variable `t` provides the starting index for the text window, and the variable `p` provides the starting index for the pattern.
* We compare each text window with all the pattern windows.
* For each new text window, the pattern window starts from `p = 0`.

**When and how do we change the text and the pattern window?**

* //ToDo:

**//ToDo: We may need to correct a few things above.**

**Overview**

//ToDo: We may need to correct a few things here.

* A text window starts from `i`.
* `t` starts from `i` and keeps increasing as long as `t < text.length`.
* `p` starts from `0` and keeps increasing as long as `p < pattern.length`. 
* `p` provides `end`.
* `end` helps us find `mid` within the binary search.
* We compare the substrings: `text[t, t + mid]` and `pattern[p, p + mid]`.
* If they match:
  * `t += matchLen` and `p += matchLen`.
  * `t` and `p` land on the `mismatch`.
  * So, `mismatch++`
  * We continue comparing the next characters.
  * So, `t++` and `p++`.
* If they didn't match:
  * `mismatch++`
  * We continue comparing the next characters.
  * So, `t++`, and `p++`.
* But before we continue with the new `t` and `p`, we need to ensure:
  * `mismatches <= allowed`
  * `p < pattern.length`
  * `t < text.length`
* Otherwise, we repeat the game with `i++`.
* But before we repeat the game with the new `i`:
  * If `mismatches <= allowed` by the time we finished the pattern, we add `i` to the result.
  * Because the text window that starts from this `i` has a pattern with allowed mismatches.
* We continue this game as long as `i < text.length`.

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

* We compare each text window with all the pattern windows.
* To get the different pattern windows, we change (move) the starting index in the pattern.
* Based on the starting index value, we get the maximum (longest) possible length of the window.
* And that's how we get the `end` value for our binary search.
* Using these `start` and `end` values, we find the `mid` length.
* And now we have the starting index of the text window (`t`), the starting index of the pattern window (`p`), and the common length for both of them (`mid`).
* We use these `t`, `p`, and `mid` values to get the hash codes of a substring using the below formula:

$$
H(a, l) = (H(a + l) - (H(a) * base^{l})) \mod prime
$$

* Where, `a` is the starting index, and `l` is the length.

**Do you understand what each line convey and do in the above code?**

* 

**Why do we take the smallest possible length, `start = 0` instead of `start = 1`?**

* 

**Why don't we just take only those windows which has a length of $pattern.length$ ?**  

* 

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


## Dry Run

**Text: `abcde`**  
**Pattern: `bcx`**      

> i = 0; t = 0; p = 0; mismatches = 0;    
> start = 0; // The minimum length   
> end = pattern.length - p = 3 - 0 = 3; // The maximum length        
> mid = start + (end - start) / 2 = 0 + (3 - 0) / 2 = 0 + 1 = 1;      
> Text[t, t + mid] = Text[from 0th index, length = 1] = a;  
> Pattern[p, p + mid] = Pattern[from 0th index, length = 1] = b;   

* `a` does not match with `b`.
* So, we decrease the length bar.
* `end = mid - 1 = 1 - 1 = 0`.
* `start` was also `0`.
* So, `mid = start + (end -start)/2` = 0 + (0 - 0)/2 = 0 + 0 = `0`.
* It means that, we are going to compare empty strings, no character!
* So, they will match!
* So, `matchLen = mid = 0`.
* After a match, we increase the length bar.
* So, we increase the `minimum length`.
* So, we increase the `start`.
* So, `start = mid + 1 = 0 + 1 = 1`
* But `end` is `0`.
* So, `start > end`.
* It means that we are done with finding the `matchLen`.
* We have found that how far the text window that starts from `i = 0` match with the pattern.
* It means that a `mismatch` (a `pothole`) has interrupted (stopped) the process.
* It means that we have found the `mismatch` (the `pothole`). 
* Now, `t` and `p` jump over the `matchLen`.
* But there was no match.
* So, `matchLen = 0`.

```kotlin

t += matchLen // t = t + matchLen = 0 + 0 = 0
p += matchLen // p = p + matchLen = 0 + 0 = 0
```

* Now, after the binary search process, `t` and `p` land on the `mismatch`.
* So, we increase the `mismatches`. 
* But, increasing the `mismatches` is worth only if `mismatches <= k`, `p < pattern.length`, and `t < text.length`.
* So, `mismatches++`.
* And if increasing the `mismatches` makes `mismatches > k`, we break out of the loop.

```kotlin

if (mismatches <= k && p < pattern.length) {
    mismatches++ // Total mismatches = 0 + 1 = 1
    if (mismatches > k) {
        break
    }
}
```

* Now, we continue the hope that a text window starting from `i = 0` might match with the pattern as long as `mismatches <= k` and `p < pattern.length`.
* It means that as long as `mismatches <= k` and `p < pattern.length`, we continue checking and comparing the next characters.
* So, after acknowledging the fact that the current positions of `t` and `p` indicate a `mismatch` by increasing the `mismatches` counter, we move `t` and `p` past this `mismatch` character to check the next character.
* But if `mismatches > k` or `p > pattern.length` or `t > text.length`, it would mean that the text window that starts with `i` has more mismatches than allowed.
* So, in that case, we would move on to the next `i`.

```kotlin

if (mismatches <= k && p < pattern.length) {
    mismatches++ // Total mismatches = 0 + 1 = 1
    if (mismatches > k) {
        break
    }
    t++
    p++
} else {
    break
}
```

* Note that before moving `t` and `p`, we confirm that `t < text.length` and `p < pattern.length`.
* We ensure that trying to retrieve the character at `t` from the `Text` or at `p` from the `Pattern` does not cause `IndexOutOfBoundsException`.

* At this point:

> mismatches = 1  
> i = 0; t = 1; p = 1;    
> start = 0;  
> end = pattern.length - p = 3 - 1 = 2;    
> mid = start + (end - start)/2 = 0 + (2 - 0)/2 = 0 + 1 = 1;    
> Text[t, t + mid] = Text[from 1st index, length = 1] = b;    
> Pattern[p, p + mid] = Pattern[from 1st index, length = 1] = c;    

* Note that for the text window that starts from `i = 0`, this is the second iteration where we are checking the second character.
* `b` of the text window does not match with the `c` of the pattern window.  
* So, we decrease the length bar.
* It means, we decrease the maximum length.
* So, `end = mid - 1` = 1 - 1 = `0`.  
* So now, `mid = start + (end - start)/2` = 0 + (0 - 0)/2 = 0 + 0 = `0`.  
* It means that we are going to compare the empty substrings.  
* So, they will match!
* So, `matchLen = mid = 0`.
* So, we increase the minimum length bar.
* So, `start = mid + 1` = 0 + 1 = `1`.
* But `end = 0`.
* So, `start > end`.
* It means the range is exhausted.
* By now, we have found the `matchLen`.
* The `matchLen` is the length of the longest substring that matches.
* It starts from `t` in the text and `p` in the pattern.
* The current value of `matchLen` is `0`.
* It means that the character at position `t` in the text window and the character at position `p` in the pattern window does not match.
* After the binary search, the current position of the variables `t` and `p` jump over the `matchLen` and land on the `mismatch`.
* So:

```kotlin

t += matchLen // t = t + matchLen = 1 + 0 = 1
p += matchLen // p = p + matchLen = 1 + 0 = 1
```

* And if the `mismaches <= kAllowedMismatches`, `p < pattern.length`, and `t < text.length`, then we can increase the `mismatches` counter and move `t` and `p` past the `mismatch`.

```kotlin

if (mismatches <= kAllowedMismatches && p < pattern.length) {
    mismatches++ // Total mismatches = 1 + 1 = 2
    // But if after increasing the `mismatches` counter, if it exceeds `kAllowedMismatches`, we can `break` early
    if (mismatches > kAllowedMismatches) {
        break
    }
    // Move `t` and `p` past the `mismatch`
    t++
    p++
}

```

* We find that total `mismatches = 2`, which is greater than `kAllowedMismatches = 1`.
* It means that a text substring that starts from `i = 0` has at least `2` mismatches, which is greater than `kAllowedMismatches = 1`.
* So, there is no point in checking further.
* So, we break the loop.
* Note that the loop we are breaking at this point is the `while (p < pattern.length)`.

//ToDo: Complete the dry run!

## while (start <= end) 

* `start` is the minimum length.
* `end` is the maximum length.
* We have a range between `start` and `end` to check.
* We compare a substring whose length is between this range.
* When we start, `t = i`, and `p = 0`.
* `p = 0` indicates the first character of the pattern.
* Now, we are checking character by character only, but we are taking a chance using the binary search power.
* For example, in a naive way, we might compare the first character of the text with the first character of the pattern, and then the second character, and then the third character, and so on.
* But when we use the binary search power, we might compare the first 10 characters (a substring whose length is 10).
* If they match, we increase the length bar.
* So, if they match, we might compare a substring whose length is more than 10.
* The benefit is we did not have to compare first 9 characters one by one.
* When we find that a substring of length 10 matches, it means the substring whose length is less than 10 also matches.
* So, we could quickly jump over 10 characters at once.
* Similarly, if they did not match, we decrease the length bar.
* So, if they did not match, we might compare a substring whose length is less than 10.
* The benefit is we could quickly get the fact that any substring whose length is equal to or greater than 10 does not match.
* We can discard all the substrings whose length is equal to or greater than 10.
* And try to find a substring whose length is less than 10.
* Hence, the purpose of the binary search is to quickly find the `matchLen`.
* When we get the `matchLen`, we jump over it, and land on the `mismatch`.
* This way, we quickly find the `mismatch`.
* To find the `matchLen`, we use the binary search power that uses `start` and `end` to calculate the `mid` length.
* This is just a chance, and we take it because it reduces the time to the logarithmic.
* If we find that `mid` is the `matchLen`, we might jump over more than one character at once.
* Otherwise, in the worst case, we might compare character by character anyway.
* But even in that case where we compare character by character, we exit (change `i`) as soon as we find the `mismatches > kAllowed`.


## Quick Example

**Text = "abcde"**    
**Pattern = "bcx"**  
**k = 1**  

> i = 0  

**`t = 0`**  

* Compare `abc` with `bcx`. (Indicates that the binary search gave us `mid = 3`)  
  * Did not match!
  * `matchLen = 0`.
  * Try a shorter length!
* Compare `a` with `b`. (Indicates that the binary search gave us `mid = 1`)
  * Did not match!
  * `matchLen = 0`.
  * That was the smallest possible length we could try and compare!
  * Increase the mismatch counter: `mismatches = 1`.
* The loop exhausts (start > end) as we tried all the possible lengths within the range (start <= end).
  
> i = 1

**`t = 1`**  

* Compare `bc` of `bcde` with `bc` of `bcx`. (Indicates that the binary search gave us `mid = 2`)
  * Matched!
  * `matchLen = 2`
  * Let us try a longer length!
* Compare `bcd` with `bcx`. (Indicates that the binary search gave us `mid = 3`)
  * Did not match!
  * `matchLen = 2`.
  * `mismatches = 1`.
  * t++
  * p++
* The loop exhausts (p > pattern.length).
* `mismatches <= k`
* So, add `i` to the result.

> i = 2

**`t = 2`**

* Compare `c` of `cde` with `b` of `bcx`. (Indicates that the binary search gave us `mid = 1`)
  * Did not match!
  * `mismatches = 1`
  * t++ // t = 2 + 1 = 3
  * p++ // p = 0 + 1 = 1
  * That was the smallest possible length we could try and compare!
  * Let us try to compare the next characters.
* Compare `d` of `de` with `c` of `bcx`. (Indicates that `t = 3` and `p = 1`)
  * Did not match!
  * `mismatches = 2`
  * `mismatches > k`
  * Break

**No need to check for any `i` that is greater than `(text.length - pattern.length)`**.

> The answer is: `1`.    
> That means, Text[from 1st index] = `bcd` matches with the pattern `bcx` with `kAllowedMismatches = 1`.    



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