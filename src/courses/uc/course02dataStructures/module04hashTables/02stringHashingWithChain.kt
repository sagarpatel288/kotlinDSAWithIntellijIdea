package courses.uc.course02dataStructures.module04hashTables

import java.util.LinkedList

/**
 * # Problem Introduction
 *
 * * In this problem you will implement a hash table using the chaining scheme.
 * * Chaining is one of the most popular ways of implementing hash tables in practice.
 * * The hash table you will implement can be used to implement a phone book on your phone or to store the password
 * table of your computer or web service (but don’t forget to store hashes of passwords instead of the passwords
 * themselves, or you will get hacked!).
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * In this task, your goal is to implement a hash table with lists chaining.
 * * You are already given the number of buckets `m` and the hash function.
 * * It is a polynomial hash function:
 *
 * ```
 *
 *          /  __ |S| - 1      i       \
 * h(S)  =  | \           S[i]x  mod p | mod m
 *          | /__ i = 0                |
 *          \                          /
 *
 * ```
 *
 * where `S[i]` is the `ASCII code` of the `i-th` symbol of `S`, `p = 1 000 000 007` and `x = 263`.
 *
 * * Your program should support the following kinds of queries:
 * ```
 * add string
 * ```
 * * Insert string into the table. If there is already such string in the hash table, then just ignore the query.
 * ```
 * • del string
 * ```
 * * Remove string from the table. If there is no such string in the hash table, then just ignore the query.
 * ```
 * find string
 * ```
 * * Output “yes" or “no" (without quotes) depending on whether the table contains string or not.
 * ```
 * check i
 * ```
 * * Output the content of the `i-th` list in the table.
 * * Use spaces to separate the elements of the list.
 * * If `i-th` list is empty, output a blank line.
 * * When inserting a new string into a hash chain, you must insert it in the beginning of the chain.
 *
 * #### Input Format
 *
 * * There is a single integer `m` in the first line - the number of buckets you should have.
 * * The next line contains the number of queries N.
 * * It’s followed by N lines, each of them contains one query in the format described above.
 *
 * #### Constraints
 *
 * ```
 * 1 <= N <= 10^5;
 * N/5 <= m <= N.
 * ```
 *
 * * All the strings consist of latin letters.
 * * Each of them is non-empty and has length at most 15.
 *
 * #### Output Format
 *
 * * Print the result of each of the find and check queries, one result per line,
 * in the same order as these queries are given in the input.
 *
 * #### Time Limits
 *
 * C: 1 sec, C++: 1 sec, Java: 5 sec, Python: 7 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 7 sec,
 * Ruby: 7 sec, Scala: 7 sec.
 *
 * #### Memory Limit
 *
 * 512MB.
 *
 * #### Sample 1
 *
 * ##### Input
 * ```
 * 5
 * 12
 * add world
 * add HellO
 * check 4
 * find World
 * find world
 * del world
 * check 4
 * del HellO
 * add luck
 * add GooD
 * check 2
 * del good
 * ```
 * ##### Output
 * ```
 * HellO world
 * no
 * yes
 * HellO
 * GooD luck
 * ```
 *
 * ##### Explanation
 *
 * * The `ASCII code` of `w` is `119`, for `o` it is `111`, for `r` it is `114`, for `l` it is `108`,
 * and for `d` it is `100`.
 * * Thus, `h(“world") = (119 + 111 * 263 + 114 * 2632 + 108 * 2633 + 100 * 2634 mod 1 000 000 007) mod 5 = 4.
 * * It turns out that the hash value of `HellO` is also 4.
 * * Recall that we always insert in the beginning of the chain, so after adding “world" and then “HellO" in the same
 * chain index 4, first goes “HellO" and then goes “world".
 * * Of course, “World" is not found, and “world" is found, because the strings are case-sensitive,
 * and the codes of `W` and `w` are different.
 * * After deleting “world", only “HellO" is found in the chain 4.
 * * Similarly to “world" and “HellO", after adding “luck" and “GooD" to the same chain 2, first goes “GooD"
 * and then “luck".
 *
 * #### Sample 2.
 *
 * ##### Input
 * ```
 * 4
 * 8
 * add test
 * add test
 * find test
 * del test
 * find test
 * find Test
 * add Test
 * find Test
 * ```
 *
 * ##### Output
 * ```
 * yes
 * no
 * no
 * yes
 * ```
 *
 * * Adding “test" twice is the same as adding “test" once, so first `find` returns “yes".
 * * After `del`, “test" is no longer in the hash table.
 * * First time `find` doesn’t find “Test” because it was not added before,
 * and strings are case-sensitive in this problem.
 * * Second time “Test” can be found, because it has just been added.
 *
 * #### Sample 3.
 *
 * ##### Input:
 * ```
 * 3
 * 12
 * check 0
 * find help
 * add help
 * add del
 * add add
 * find add
 * find del
 * del del
 * find del
 * check 0
 * check 1
 * check 2
 * ```
 * ##### Output:
 * ```
 * no
 * yes
 * yes
 * no
 * add help
 * ```
 *
 * ##### Explanation:
 * * Note that you need to output a blank line when you handle an empty chain.
 * * Note that the strings stored in the hash table can coincide with the commands used to work with the hash table.
 *
 * ## Solution
 *
 * * [Reference: Local: Hash Table Module](docs/dataStructures/courses/uc/module04hashTables/05hashTables.md)
 * * [Reference: GitHub: Hash Table Module](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/0eca71edafe63bbfc83d0aaaca40ff9312a9cdd4/docs/dataStructures/courses/uc/module04hashTables/05hashTables.md)
 *
 * ### Data Structure
 *
 * * The problem explicitly asks us to implement a hash table using the given hash function formula, prime factor, and
 * the random `x` value.
 * * We need to implement a hash function.
 * * We need to create and use a custom class to replicate a hash table.
 * * We have already learned in the `Hash Table Module` that we can use an [ArrayList] where each index (bucket) would
 * implement a [java.util.LinkedList] to handle the collision efficiently.
 * * Also, using the [java.util.LinkedList] avoids `O(n)` shifting cost when we insert or delete an item at any
 * arbitrary index.
 *
 * ### Time Complexity
 *
 * * We have two major parts to consider for the time complexity.
 * * Operations and [hash].
 * **Operation**
 * * It is given in the constraint that:
 * ```
 *  N
 *  - < =  m < =  N
 *  5
 * ```
 * * It means that `m` is at least `N over 5`.
 * * The load factor can be maximum if `m` is the minimum, and the minimum `m` is `N over 5`.
 * * It means that the maximum load factor can be:
 * ```
 *                          Maximum N
 * Maximum load factor  =   ---------
 *                          Minimum m
 *
 *
 * ```
 * ```
 *                          n     N
 * Maximum load factor  =   -  =  -  =  5
 *                          m     N
 *                                -
 *                                5
 *
 *
 * ```
 * * Each operation take at most `O(1 + ⍺)` time, where `⍺ = Maximum Load Factor`.
 * * Now, the maximum load factor is `5`, which is a constant time.
 * * It means that each operation takes `O(1)` time on average.
 * * Total queries are `N`.
 * * Hence, it becomes `O(N)`.
 *
 * **Hash**
 * * To generate a hash code, we perform an iteration for the given input string.
 * * Each iteration covers the entire string length to generate a hash code.
 * * If the input string length is `L`, then each query takes `O(L)` time.
 * * For `N` queries, the total time becomes `O(N * L)`.
 * * But the maximum `L` is bounded by `15`, which is a constant.
 * * So again, the total time becomes: `O(N)`.
 *
 * **Worst Case**
 * * Total queries: N
 * * Maximum string length: L
 * * Maximum chain size: k
 * * Now, it is possible that `k == N`.
 * * It means that the total items are equal to the total queries.
 *
 * **Hash**
 * * For each query:
 * * We iterate through the string of length `L`.
 * * So, for total `N` queries, it becomes: `O(N * L)`.
 * * But since `L` is bounded by `15`, we can consider it as a constant and ignore it.
 * * So, the total worst-case time for the [hash] function becomes `O(N)`.
 *
 * **Operation**
 * * We iterate through the `LinkedList` to check if the item exists.
 * * With each query, the size of the `LinkedList` keeps increasing in the worst-case.
 * * It means that each subsequent query takes `+1` time than the previous query.
 * * The last query might take `N - 1` time.
 * * The summation of `1 + 2 + .. + (N-1)` simplifies to `N^2`.
 * * Then, the worst-case time complexity becomes: `O(N^2)` for travelling through the LinkedList.
 * * Worst-case time complexity is: `O(N^2)`.
 *
 * ### Space Complexity
 * * The hash table size is `m`.
 * * Total queries are `N`.
 * * It means that we might store up to `N` data.
 * * Also, each data (input string) can have maximum length of `L`.
 * * Hence, the total data size becomes `N * L`.
 * * But, `L` is bounded by `15`.
 * * So, total space becomes: `O(m + N)`.
 *
 * ### Grader output
 * ```
 * Good job! (Max time used: 0.57/2.00, max memory used: 88514560/536870912.)
 * ```
 *
 */
class HashTable {
    private val prime = 1_000_000_007L
    private val xRandom = 263L

    private lateinit var table: Array<LinkedList<String>>
    private var mCardinality: Int = 0

    /**
     * * This is the Horner's method to form a polynomial hash function.
     *
     * * For example:
     * * If the input string is `cat`, then the polynomial function would be:
     * ```
     * c(x^2) + a(x^1) + t(x^0)
     * = c(x^2) + a(x^1) + t
     * = (c(x) + a) * x) + t
     * ```
     * * If we observe the last simplification, it says:
     * **Multiply the current result by x and then add a next character**
     *
     * * For example:
     * * If `c` is the current result, then we multiply it by `x` and add the next character, `a`.
     * * Now, the current result becomes: `(c(x) + a)`.
     * * Repeat.
     * * So, we multiply the current result, `(c(x) + a)` by `x`.
     * * So, it becomes: `(c(x) + a)x` and then, we add the next character, `t`.
     * * So, it becomes: `(c(x) + a)x + t)`.
     *
     * **Real-world Simple Math Example: Forward Method**
     * * We have the number, `947`.
     * * We start with the current result, `hashCode = 0`.
     * * The `x-base` is `10` for the decimal numbers.
     * * The first number is `9`.
     * * So, it becomes:
     * ```
     * hashCode = (hashCode * 10) + 9
     * = (0 * 10) + 9
     * = 9
     * ```
     * * Next, we get `4`.
     * * So, it becomes:
     * ```
     * hashCode = (hashCode * 10) + 4
     * = (9 * 10) + 4
     * = 90 + 4
     * = 94
     * ```
     * * Next, we get `7`.
     * * So, it becomes:
     * ```
     * hashCode = (hashCode * 10) + 7
     * = (94 * 10) + 7
     * = 940 + 7
     * = 947
     * ```
     *
     * **The Twist: Backward Loop**
     *
     * * However, the instruction (and even the theory of hash tables) starts the iteration from the last character,
     * and goes down to the first character of the input string.
     * * It means that if the input string is `cat`, the polynomial function would be:
     * ```
     * c(x^0) + a(x^1) + t(x^2)
     * ```
     * * Because it goes like below:
     * * We start with `t`.
     * ```
     * hashCode = (hashCode * x) + t
     * = (0 * x) + t
     * = t
     * ```
     * * Next, we get the character `a`.
     * ```
     * hashCode = (hashCode * x) + a
     * = (t * x) + a
     * = tx + a
     * = a + tx
     * ```
     * * Next, we get the character `c`.
     * ```
     * hashCode = (hashCode * x) + c
     * = (a + tx)x + c
     * = ax + t(x^2) + c
     * = c + ax + t(x^2)
     * ```
     *
     * **Conclusion**
     *
     * * The character from which we start the iteration gets the highest degree.
     * * The character where we end the iteration gets the lowest degree (power 0).
     * * The formula remains the same:
     * ```
     * hashCode = ( (hashCode * x) + charCode) ) % prime //modulo to prevent overflow
     * ```
     *
     */
    private fun hash(input: String): Int {
        var hashCode = 0L

        for (i in input.lastIndex downTo 0) {
            val charCode = input[i].code.toLong()
            hashCode = (hashCode * xRandom + charCode) % prime
        }
        // Ensure a positive hashCode
//        hashCode = (hashCode % prime + prime) % prime
        // Ensure a positive index
        val index = (hashCode % mCardinality + mCardinality) % mCardinality
        return index.toInt()
    }

    fun processQueries() {
        val mHashTableSize = readln().toInt()
        val nQueries = readln().toInt()
        mCardinality = mHashTableSize
        // Initialize the hash table of given size with an empty linked list for each index (bucket)
        table = Array(mHashTableSize) { LinkedList() }
        val result = StringBuilder()
        repeat(nQueries) {
            val query = readln().split(" ")
            val command = query[0]
            when (command) {
                "add" -> {
                    val input = query[1]
                    val index = hash(input)
                    val chain = table[index]
                    // As per instructions: No duplicate. No overwrites.
                    if (!chain.contains(input)) {
                        chain.addFirst(input)
                    }
                }

                "del" -> {
                    val input = query[1]
                    val index = hash(input)
                    val chain = table[index]
                    chain.remove(input)
                }

                "find" -> {
                    val input = query[1]
                    val index = hash(input)
                    val chain = table[index]
                    result.append(if (chain.contains(input)) "yes" else "no").append("\n")
                }

                "check" -> {
                    val index = query[1].toInt()
                    val chain = table[index]
                    result.append(chain.joinToString(" ")).append("\n")
                }
            }
        }
        println(result.toString())
    }
}

fun main() {
    HashTable().processQueries()
}

