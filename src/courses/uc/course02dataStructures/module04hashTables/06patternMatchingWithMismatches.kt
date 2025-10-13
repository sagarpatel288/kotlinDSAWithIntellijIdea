package courses.uc.course02dataStructures.module04hashTables

/**
 * # Pattern matching with mismatches
 *
 * ## Problem Introduction
 *
 * * A natural generalization of the pattern matching problem is the following:
 * * Find all text locations where the distance from the pattern is sufficiently small.
 * * This problem has applications in text searching (where mismatches correspond to typos)
 * and bioinformatics (where mismatches correspond to mutations).
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * For an integer parameter `k` and two strings `t = t0t1 ···tm-1` and `p = p0p1 ··· pn-1`,
 * we say that `p` occurs in `t` at position `i` with at most `k` mismatches if the strings
 * `p` and `t[i : i + p) = titi+1 ···ti+n-1` differ in at most k positions.
 *
 * ### Input Format
 *
 * * Every line of the input contains an integer `k` and two strings `t` and `p` consisting of lower case Latin letters.
 *
 * ### Constraints
 * ```
 * 0 <= k <= 5, 1 <= |t| <= 200 000, 1 <= |p| <= min{|t|, 100 000}
 * ```
 * * The total length of all t’s does not exceed 200 000, the total length of all p’s does not exceed 100 000.
 *
 * ### Output Format
 *
 * * For each triple `(k, t, p)`, find all positions 0 < i1 < i2 < ··· < il < |t| where p occurs in t
 * with at most k mismatches. Output l and i1, i2,...,il.
 *
 * ### Time Limits
 *
 * C: 2 sec, C++: 2 sec, Java: 5 sec, Python: 40 sec. C#: 3 sec, Haskell: 4 sec, JavaScript: 10 sec, Ruby: 10 sec,
 * Scala: 10 sec.
 *
 * ### Memory Limit
 * 512MB.
 *
 * ### Sample 1
 *
 * #### Input:
 * ```
 * 0 ababab baaa
 * 1 ababab baaa
 * 1 xabcabc ccc
 * 2 xabcabc ccc
 * 3 aaa xxx
 * ```
 *
 * #### Output:
 * ```
 * 0
 * 1 1
 * 0
 * 4 1 2 3 4
 * 1 0
 * ```
 *
 * #### Explanation:
 *
 * * For the first triple, there are no exact matches.
 * * For the second triple, `baaa` has distance one from the pattern.
 * * For the third triple, there are no occurrences with at most one mismatch.
 * * For the fourth triple, any (length three) substring of `p` containing at least one `c` has distance at most two
 * from `t`.
 * * For the fifth triple, `t` and `p` differ in three positions.
 *
 * ## Solution
 *
 * ### Intuition
 *
 * **How do we naively compare two strings?**
 * **Pseudocode (core part) of naive implementation**
 *
 * * We compare two strings character by character.
 * * So, it looks like below:
 *
 * ```
 * // This outer loop increments the pointer of the text string
 * for (t in 0 until text.length) {
 *     // This inner loop increments the pointer of the pattern string
 *     for (p in 0 until pattern.length) {
 *         // Comparing characters
 *         if (text[t] != pattern[p]) {
 *             // With each mismatch, we increase the "mismatch" counter
 *             mismatches++
 *         } else {
 *             // If it is a match, we increment the "matchLen" counter
 *             matchLen++
 *         }
 *         if (mismatch > k) {
 *             // We crossed the maximum allowed mismatches
 *             matchLen = 0
 *             break
 *         }
 *     }
 *     // Result based on the mismatch counter
 *     if (mismatch <= k) {
 *         result.add(t) // The text index from where the pattern matching with allowed mismatches starts
 *     }
 * }
 * ```
 *
 * **String comparison using binary search**
 *
 * * Now, in the `longest common substring` problem, we have learned that the `binary search` helps string comparison.
 * * The idea is, instead of comparing character by character, we check characters in bulk.
 * * And `Characters in bulk` means substrings.
 * * And when we want to compare substrings, we use hash codes.
 * * So, the idea is:
 * * We use precomputed prefix hashing for the text string and the pattern string.
 * * The binary search gives us a length.
 * * We use the following formula to compare the substrings:
 *
 * ```
 * H(a, l) = [ H(a + l) - { H(a) * x^l } ] % prime
 * ```
 *
 * * And to reduce the collisions, we can use double hashing.
 * * Let us add a pinch of binary search and precomputed prefix hashing mixed with double hashing.
 *
 * **Precomputed prefix double hashing**
 * ```
 * val prime1 = 1_000_000_007L
 * val prime2 = 1_000_000_079L
 * val xBase = 263L
 * val xTextPowers1 = LongArray(text.length + 1)
 * val xTextPowers2 = LongArray(text.length + 1)
 * val xPatternPowers1 = LongArray(pattern.length + 1)
 * val xPatternPowers2 = LongArray(pattern.length + 1)
 *
 * textHashes1[0] = 0L
 * textHashes2[0] = 0L
 * patternHashes1[0] = 0L
 * pattenHashes2[0] = 0L
 * xTextPowers1[0] = 1L
 * xTextPowers2[0] = 1L
 * xPatternPowers1[0] = 1L
 * xPatternPowers2[0] = 1L
 *
 * for (i in 1 until text.length) {
 *     xTextPowers1[i] = (xTextPowers1[i - 1] * xBase) % prime1
 *     xTextPowers2[i] = (xTextPowers2[i - 1] * xBase) % prime2
 * }
 *
 * for (i in 1 until pattern.length) {
 *     xPatternPowers1[i] = (xPatternPowers1[i - 1] * xBase) % prime1
 *     xPatternPowers2[i] = (xPatternPowers2[i - 1] * xBase) % prime2
 * }
 *
 * // Precomputed prefix double hashing of the text string (Given that pattern.length <= text.length)
 * for (i in 0..text.length - pattern.length) {
 *     textHashes1[i + 1] = ( ( textHashes1[i] * xBase ) + text[i].code.toLong() ) % prime1
 *     textHashes2[i + 1] = ( ( textHashes2[i] * xBase ) + text[i].code.toLong() ) % prime2
 * }
 *
 * // precomputed prefix double hashing of the patten string
 * for (i in 0 until pattern.length) {
 *     patternHashes1[i + 1] = ( (patternHashes1[i] * xBase) + pattern[i].code.toLong() ) % prime1
 *     patternHashes2[i + 1] = ( (patternHashes2[i] * xBase) + pattern[i].code.toLong() ) % prime2
 * }
 *
 * fun textHashes(startIndex: Int, length: Int): Pair<Long, Long> {
 *     val longHash1 = textHashes1[startIndex + length]
 *     val shortHash1 = textHashes1[startIndex]
 *     val sub1 = (shortHash1 * xTextPowers1[length]) % prime1
 *     val hash1 = (longHash1 - sub1) % prime1
 *
 *     val longHash2 = textHashes2[startIndex + length]
 *     val shortHash2 = textHashes2[startIndex]
 *     val sub2 = (shortHash2 * xTextPowers2[length]) % prime2
 *     val hash2 = (longHash2 - sub2) % prime2
 *
 *     return hash1 to hash2
 * }
 *
 * fun pattenHashes(startIndex: Int, length: Int): Pair<Long, Long> {
 *     val longHash1 = patternHashes1[startIndex + length]
 *     val shortHash1 = patternHashes1[startIndex]
 *     val sub1 = (shortHash1 * xPatternPowers1[length]) % prime1
 *     val hash1 = (longHash1 - sub1) % prime1
 *
 *     val longHash2 = patternHashes2[startIndex + length]
 *     val shortHash2 = patternHashes2[startIndex]
 *     val sub2 = (shortHash2 * xPatternPowers2[length]) % prime2
 *     val hash2 = (longHash2 - sub2) % prime2
 *
 *     return hash1 to hash2
 * }
 * ```
 *
 * **Binary Search**
 *
 * ```
 * val mid = start + (end - start) / 2
 * (hash1a, hash2a) = textHashes(t, mid)
 * (hash1b, hash2b) = patternHashes(p, mid)
 * if (hash1a == hash1b && hash2a == hash2b) {
 *     // See if the longer length matches
 *     start = mid + 1
 * } else {
 *     end = mid - 1
 * }
 * ```
 *
 * * A couple of reflective questions and observation here.
 *
 * **We have to stop the binary search at some point. How do we stop? What will be the condition?**
 *
 * * The condition will be: `while (start <= end) binarySearch`.
 * * Let us put this condition.
 *
 * ```
 * while (start <= end) {
 *     val mid = start + (end - start) / 2
 *     val (hash1a, hash2a) = textHashes(t, mid)
 *     val (hash1b, hash2b) = patternHashes(p, mid)
 *     if (hash1a == hash1b && hash2a == hash2b) {
 *         // See if the longer length matches
 *         start = mid + 1
 *     } else {
 *         // See if the shorter length matches
 *         end = mid - 1
 *     }
 * }
 * ```
 *
 * **And what about those two starting indices: t and p? How do we get them?**
 *
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (t in 0 until text.length) {
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * * Now, we need to add a couple of more things here.
 * * matchLen counter
 * * mismatches counter
 * * Starting index of the pattern matching with mismatches
 *
 * **Did you understand why we need to add them?**
 * **Starting index of the pattern matching (with allowed mismatches) in the text string**
 *
 * * We have been asked to provide the starting index of the pattern matching with mismatches.
 * * For example, if the text is `abcdef` and the pattern is `abx` with `k = 1`, then we need to output: `1 0`.
 * * In the output, `1` represents the total matched patterns.
 * * `0` represents the starting index in the text that matches the pattern (can use allowed mismatches).
 *
 * **matchLen counter = jump**
 *
 * * Why do we use it?
 * * It is our `jump`.
 * * This is where we leverage the binary search implementation.
 * * For example, suppose the text is `abcdef` and the pattern is `abcxyz` and the allowed mismatch is `k = 1`.
 * * Now, as long as we learn it through the binary search that `abc` matches, we can skip the character-by-character
 * matching for `b` and `c`, and we can jump over `d` in the text and `x` in the pattern.
 * * So, we can directly assign that pointer (index) `t = 3` and `p = 3`.
 * * Let us implement this part.
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (i in 0 until text.length) {
 *     // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
 *     var t = i
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         // Did you understand why do we take `matchLen` here between these two `while` loops?
 *         var matchLen = 0
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 matchLen = mid
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *         t += matchLen
 *         p += matchLen
 *     }
 * }
 * ```
 * * And notice the interesting pattern here.
 * * The point where the binary search exits is the `mismatch` character `d` in the text Vs `x` in the pattern.
 * * But this is valid only `if index p is less than pattern.length`.
 * * For example, `p += matchLen` could make the `p` jump to `pattern.length`.
 * * For example, if the text was `abcdef` and the pattern was `abc`, the `jump` would make `p = 3`.
 * ```
 * p = p + matchLen
 * p = 0 + 3
 * p = 3
 * ```
 * * It means that at the end of the binary search, if `p < pattern.length`, then `p += matchLen` represents the mismatch
 * position.
 * * Let us implement this.
 *
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (i in 0 until text.length) {
 *     // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
 *     var t = i
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         // Did you understand why do we take `matchLen` here between these two `while` loops?
 *         var matchLen = 0
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 matchLen = mid
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             mismatches++
 *         }
 *     }
 * }
 * ```
 *
 * * This setup, the outer `for` loop, and the two inner `while` loops, where the inner most `while` loop represents
 * the binary search, is essentially a `character-by-character` comparison with the power of `binary search`.
 * * Imagine a digital game where we have this `binary search` power.
 * * We use this `binary search` power and it generates `matchLen` that we can use to `jump`.
 * * Every jump is an acceleration and helps us finish the process (work) faster.
 * * However, it is also possible that we always get `matchLen = 0`.
 * * For example, the text string is `abcdef`, the pattern string is `xyz`, and `k = 0`.
 * * After using the `binary search` power, we find that `matchLen = 0` only.
 * * It means that:
 * ```
 * t += matchLen
 * t = 0 + 0
 * t = 0 // This doesn't make any progress. It was at 0 and it ended up at 0 only after the binary search.
 * ```
 * * And similarly
 * ```
 * p += matchLen
 * p = 0 + 0
 * p = 0 // Same here. It did not make any progress. It was at 0 and it ended up at 0 only after the binary search.
 * ```
 * * So, fall back to character-by-character comparison (process).
 * * We couldn't jump.
 * * But we can walk.
 * * So, we make `t` and `p` one step forward.
 * ```
 * t++
 * p++
 * ```
 * * But we need to ensure that `p++` does not go beyond `pattern.length`.
 * * So, it becomes safer as:
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (i in 0 until text.length) {
 *     // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
 *     var t = i
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         // Did you understand why do we take `matchLen` here between these two `while` loops?
 *         var matchLen = 0
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 matchLen = mid
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             mismatches++
 *             t++
 *             p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
 *         }
 *     }
 * }
 * ```
 * **Why do we `jump` the pointers `t` and `p` two times?**
 * ```
 *         // First time
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             mismatches++
 *             // Second times
 *             t++
 *             p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
 *         }
 * ```
 * * Suppose the text string is `abcdef` and the pattern is `abcxyz`.
 * * Now, we started the process with pointer `t = 0 = character a` of the text string.
 * * The binary search gives us `matchLen = 3`.
 * * So, we jump as:
 * ```
 * t += matchLen
 * t = t + matchLen
 * t = 0 + 3
 * t = 3 // at character `d` of the text string `abcdef`.
 * ```
 * And:
 * ```
 * p += matchLen
 * p = p + matchLen
 * p = 0 + 3
 * p = 3 // at character `x` of the pattern string `abcxyz`.
 * ```
 * * See the magic of the binary search result here.
 * * As long as `p < pattern.length`, the `t += matchLen` and `p += matchLen` always end up on the `mismatch`.
 * * We count that.
 * * That's why we perform `mismatches++`.
 * * It means we have already acknowledged (and hence processed!) the comparison of the current `t` and `p` positions.
 * * So, we need to move on!
 * * How do we move on?
 * ```
 * t++
 * t = t + 1
 * t = 3 + 1
 * t = 4 // at character `e` of the text string `abcdef`.
 * ```
 * * And
 * ```
 * p++
 * p = p + 1
 * p = 3 + 1
 * p = 4 // at character `y` of the pattern string `abcxyz`.
 * ```
 * * The binary search gives us the `matchLen`.
 * * And by jumping over the `matchLen`, we land upon the `mismatch`.
 * * Imagine the `matchLen` as a highway, and `mismatch` as a local, rough road.
 * * The moment we cross the `matchLen`, we land upon the local-rough road.
 * * We not only know the `matchLen`, but we also know the `mismatch`.
 * * So, we not only jump over the `matchLen`, but we also jump over the `mismatch`.
 * * That's why:
 * ```
 *         // First time
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             mismatches++
 *             // Second times
 *             // Already acknowledged the mismatch. Move on.
 *             t++
 *             p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
 *         }
 * ```
 *
 * **mismatch counter**
 *
 * * We need to keep track of our wild card - the number of allowed mismatches.
 * * For example, `text = abcdef` and `pattern = abx`, and `k = 1`.
 * * We can use `k = 1` to consider `c = x` in `abc = abx`.
 * * If `k` was `0`, and we find that `mismatches > k`, we can't consider `abc = abx`.
 * * In that case where `k = 0`, the pattern `abx` does not fit in the text `abcdef`.
 * * With every mismatch, we can use the allowed `mismatches` wildcard.
 *
 * **When, where, and how do we use the mismatch counter?**
 *
 * * How does the binary search work here?
 * * If we find a match, we increase the length to see if we can find a longer substring match.
 * * If we do not find a match, we decrease the length to see if we can find a shorter substring match.
 * * See, at some point, the binary search finishes.
 * * What does the end of the binary search indicate?
 * > It covered all the possible lengths (from 0 to pattern.length).
 * * The binary search gives us the `matchLen` that we can use to make a `jump`.
 * * Once we make a jump, and if we are still under `p < pattern.length`, we land upon a `mismatch`.
 * * When and where the `matchLen` ends, the `mismatch` starts.
 * * Otherwise, if the `mismatch` was not a mismatch, it would have become the part of the `matchLen`!
 * * Hence, after the `matchLen`, it is always a `mismatch` (- as long as `p < pattern.length`).
 * * So, it becomes:
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (i in 0 until text.length) {
 *     // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
 *     var t = i
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         // Did you understand why do we take `matchLen` here between these two `while` loops?
 *         var matchLen = 0
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 matchLen = mid
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
 *             mismatches++
 *             t++
 *             p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
 *         }
 *     }
 * }
 * ```
 *
 * **What does the `while (p < pattern.length)` indicate?**
 *
 * * It indicates the `character-by-character` comparison part of the `pattern`.
 * * The pattern index `p` always starts with `p = 0` before and above this `while` loop.
 * * Inside this `while` loop, we use the `binary search`.
 * * The `binary search` gives us the `matchLen`.
 * * We jump over the `matchLen` and land upon the `mismatch`.
 * * We cross the `mismatch` by `p++`.
 * * We do this to cover the entire pattern length.
 * * But we need to ensure that this increments of `p` does not go beyond the `pattern.length`.
 * * Because `p` is something that we use as a starting index inside the `binary search` with length `mid` of `pattern`.
 * * We use it as `pattern(p, mid)` to compare a substring with the text substring `text(t, mid)`.
 * * So, we need to ensure that we don't get `IndexOutOfBounds` exception.
 * * Hence, `p` must be less than `pattern.length`.
 * * And that's why this condition and this loop as `while (p < pattern.length)`.
 *
 * **How do we discard the comparison as long as we find too many mismatches?**
 *
 * * We can do this after we increase the `mismatches` counter.
 * * So, it becomes:
 *
 * ```
 * // Did you understand the purpose of this outer for loop?
 * // This is the sliding window of the text string that moves from left to right, character by character.
 * // And for the window length, it uses the inner binary search.
 * for (i in 0 until text.length) {
 *     // The text pointer starts with `i`, but we may `jump` based on `matchLen` provided by the binary search.
 *     var t = i
 *     // Did you understand why do we take this variable `p` outside the `while` loops?
 *     var p = 0
 *     // Did you understand the purpose of this outer while loop?
 *     while (p < pattern.length) {
 *         // binary search
 *         var start = p
 *         var end = pattern.length - p // Did you understand this?
 *         // Did you understand why do we take `matchLen` here between these two `while` loops?
 *         var matchLen = 0
 *         while (start <= end) {
 *             val mid = start + (end - start) / 2
 *             val (hash1a, hash2a) = textHashes(t, mid)
 *             val (hash1b, hash2b) = patternHashes(p, mid)
 *             if (hash1a == hash1b && hash2a == hash2b) {
 *                 matchLen = mid
 *                 // See if the longer length matches
 *                 start = mid + 1
 *             } else {
 *                 // See if the shorter length matches
 *                 end = mid - 1
 *             }
 *         }
 *         t += matchLen
 *         p += matchLen
 *
 *         if (p < pattern.length) {
 *             // After the `jump`, we land upon a mismatch - as long as `p < pattern.length`.
 *             mismatches++
 *             if (mismatches > k) {
 *                 // If the window at `t` has too many mismatches, discard it.
 *                 break
 *             }
 *             t++
 *             p++ // It is safe here to move `p` one step forward as long as it is less than `pattern.length`.
 *         }
 *     }
 * }
 * ```
 *
 * **How do we finally store the result: Starting index of the pattern matching with mismatches?**
 *
 * ### TL;DR
 *
 * * Precomputed prefix hashing
 * * Binary search for length
 * * Counting mismatches
 * * If matched lengths + counted mismatches = pattern length --> Maybe, we have found the answer and can exit.
 * * Otherwise, slide the window until we reach the end of the text string.
 */
fun main() {

}
