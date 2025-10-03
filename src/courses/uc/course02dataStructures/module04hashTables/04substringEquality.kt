package courses.uc.course02dataStructures.module04hashTables

/**
 * # Problem: Substring Equality
 *
 * ## Problem Introduction
 *
 * * In this problem, you will use hashing to design an algorithm that is able to preprocess a given string `s`
 * to answer any query of the form “are these two substrings of `s` equal?” efficiently.
 * * This, in turn, is a basic building block in many string processing algorithms.
 *
 * ## Problem Description
 *
 * ### Input Format
 *
 * * The first line contains a string `s` consisting of small Latin letters.
 * * The second line contains the number of queries `q`.
 * * Each of the next `q` lines specifies a query by three integers `a`, `b`, and `l`.
 *
 * ### Constraints
 * ```
 * 1 <= |s| <= 500 000.
 * 1 <= q <= 100 000.
 * 0 <= a, b <= |s| - l (hence the indices `a` and `b` are 0-based).
 * ```
 *
 * ### Output Format
 *
 * * For each query, output “Yes” if
 * ```
 *  s    s        ...s           =  =   s    s        ...s
 *   a    a + 1       a + l - 1          b    b + 1       b + l - 1
 *
 * ```
 * and "No" otherwise.
 *
 * ### Time Limits
 *
 * * C: 1 sec, C++: 1 sec, Java: 2 sec, Python: 10 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 5 sec, Ruby: 5 sec,
 * Scala: 5 sec.
 *
 * ### Memory Limit
 *
 * * 512MB.
 *
 * ### Sample 1
 *
 * #### Input
 * ```
 * trololo
 * 4
 * 007
 * 243
 * 351
 * 132
 * ```
 *
 * #### Output:
 * ```
 * Yes
 * Yes
 * Yes
 * No
 * ```
 *
 * #### Explanation
 * ```
 * 007 → trololo = trololo
 * 243 → tr`olo`lo = trol`olo`
 * 351 → tro`l`olo = trolo`l`o
 * 132 → t`ro`lolo ≠ tro`lo`lo
 * ```
 *
 * ## Solution
 *
 * * Reference: [Local: Precomputed Prefixed Hashes](docs/dataStructures/courses/uc/module04hashTables/45precomputedPrefixHashes.md)
 * * Reference: [GitHub: Precomputed Prefixed Hashes](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/917ab5c533a37698b912a02a20322ab4409e4ba3/docs/dataStructures/courses/uc/module04hashTables/45precomputedPrefixHashes.md)
 *
 * ## Time Complexity
 *
 * **Hashing**
 * * We iterate through each character of the input [string] once.
 * * Hence, the time complexity is `O(|S|)`.
 *
 * **Queries**
 * * If there are `n` queries, then it is `O(n)`.
 *
 * **Total**
 * * Hence, the total time becomes `O(|S| + n)`.
 *
 * ## Space Complexity
 *
 * * We take a total of 4 arrays of size [string].
 * * But we drop the constant `4` while calculating complexity.
 * * Hence, the auxiliary (algorithm) space complexity is `O(|S|)`.
 * * Output string size depends on the number of queries.
 * * If the number of queries is `n`, then it is `O(n)`.
 * * Hence, the total space complexity is `O(|S| + n)`.
 *
 * ## Grader Output
 * ```
 * Good job! (Max time used: 0.73/3.50, max memory used: 105889792/536870912.)
 * ```
 */
private class SubstringEqualitySolver(private val string: String) {

    private val prime1 = 1_000_000_007L
    private val prime2 = 1_000_000_009L
    private val xBase = 263L
    private val prefixHashes1: LongArray
    private val prefixHashes2: LongArray
    private val powersOfBase1: LongArray
    private val powersOfBase2: LongArray

    init {
        val stringLength = string.length
        prefixHashes1 = LongArray(stringLength + 1)
        prefixHashes2 = LongArray(stringLength + 1)
        powersOfBase1 = LongArray(stringLength + 1)
        powersOfBase2 = LongArray(stringLength + 1)

        // Base case for powers of base: x^0 = 1
        powersOfBase1[0] = 1L
        powersOfBase2[0] = 1L

        // Precompute all prefix hashes and power of xBase
        for (i in 1..stringLength) {
            // Calculate substring hashes using the Horner's Method
            prefixHashes1[i] = (prefixHashes1[i - 1] * xBase + string[i - 1].code.toLong()) % prime1
            prefixHashes2[i] = (prefixHashes2[i - 1] * xBase + string[i - 1].code.toLong()) % prime2
            powersOfBase1[i] = (powersOfBase1[i - 1] * xBase) % prime1
            powersOfBase2[i] = (powersOfBase2[i - 1] * xBase) % prime2
        }
    }

    private fun getSubstringHashes(startingIndex: Int, length: Int): Pair<Long, Long> {
        val hashOfLongPrefix1 = prefixHashes1[startingIndex + length]
        val hashOfShortPrefix1 = prefixHashes1[startingIndex]
        val powerOfBase1 = powersOfBase1[length]

        var substringHash1 = hashOfLongPrefix1 - (hashOfShortPrefix1 * powerOfBase1) % prime1
        substringHash1 = (substringHash1 % prime1 + prime1) % prime1

        val hashOfLongPrefix2 = prefixHashes2[startingIndex + length]
        val hashOfShortPrefix2 = prefixHashes2[startingIndex]
        val powerOfBase2 = powersOfBase2[length]

        var substringHash2 = hashOfLongPrefix2 - (hashOfShortPrefix2 * powerOfBase2) % prime2
        substringHash2 = (substringHash2 % prime2 + prime2) % prime2

        return substringHash1 to substringHash2
    }

    fun areEqual(startingIndex1: Int, startingIndex2: Int, length: Int): Boolean {
        val (substringHash1a, substringHash2a) = getSubstringHashes(startingIndex1, length)
        val (substringHash1b, substringHash2b) = getSubstringHashes(startingIndex2, length)
        return substringHash1a == substringHash1b && substringHash2a == substringHash2b
    }
}

fun main() {
    val input = readln()
    val totalQueries = readln().toInt()
    val stringBuilder = StringBuilder()
    val solver = SubstringEqualitySolver(input)
    repeat(totalQueries) {
        val query = readln().split(" ").map { it.toInt() }
        stringBuilder.append(
            if (solver.areEqual(query[0], query[1], query[2])) {
                "Yes\n"
            } else "No\n"
        )
    }
    println(stringBuilder.toString())
}