package courses.uc.course02dataStructures.module04hashTables

/**
 * # Problem
 *
 * ## Problem Introduction
 *
 * * In this problem you will implement a simple phone book manager.
 *
 * ## Problem Description
 *
 * ### Task
 *
 * * In this task, your goal is to implement a simple phone book manager.
 * * It should be able to process the following types of user’s queries:
 *
 * ```
 * add number name
 * ```
 *
 * * It means that the user adds a person with name `name` and phone number `number` to the phone book.
 * * If there exists a user with such number already, then your manager has to overwrite the corresponding name.
 *
 * ```
 * del number
 * ```
 *
 * * It means that the manager should erase a person with number `number` from the phone book.
 * * If there is no such person, then it should just ignore the query.
 *
 * ```
 * find number
 * ```
 * * It means that the user looks for a person with phone number `number`.
 * * The manager should reply with the appropriate `name`, or with string “not found" (without quotes) if there is
 * no such person in the book.
 *
 * ### Input Format
 *
 * * There is a single integer 𝑁 in the first line — the number of queries.
 * * It’s followed by 𝑁 lines, each of them contains one query in the format described above.
 *
 * ### Constraints
 * ```
 * 1 ≤ 𝑁 ≤ 10^5
 * ```
 * * All phone numbers consist of decimal digits, they don’t have leading zeros,
 * and each of them has no more than 7 digits.
 * * All names are non-empty strings of latin letters, and each of them has length at most 15.
 * * It’s guaranteed that there is no person with name “not found".
 *
 * ### Output Format
 *
 * * Print the result of each find query — the name corresponding to the phone number or
 * “not found" (without quotes) if there is no person in the phone book with such phone number.
 * * Output one result per line in the same order as the find queries are given in the input.
 *
 * ### Time Limits
 *
 * * C: 3 sec, C++: 3 sec, Java: 6 sec, Python: 6 sec. C#: 4.5 sec, Haskell: 6 sec, JavaScript: 9 sec,
 * Ruby: 9 sec, Scala: 9 sec.
 *
 * ### Memory Limit
 *
 * * 512MB
 *
 * ### Sample 1.
 *
 * #### Input:
 * ```
 * 12
 * add 911 police
 * add 76213 Mom
 * add 17239 Bob
 * find 76213
 * find 910
 * find 911
 * del 910
 * del 911
 * find 911
 * find 76213
 * add 76213 daddy
 * find 76213
 * ```
 *
 * #### Output:
 * ```
 * Mom
 * not found
 * police
 * not found
 * Mom
 * daddy
 * ```
 *
 * #### Explanation
 *
 * * 76213 is Mom’s number, 910 is not a number in the phone book, 911 is the number of police, but then
 * it was deleted from the phone book, so the second search for 911 returned “not found".
 * * Also, note that when the daddy was added with the same phone number 76213 as Mom’s phone number, the contact’s
 * name was rewritten, and now search for 76213 returns “daddy" instead of “Mom".
 *
 * ### Sample 2.
 *
 * #### Input:
 * ```
 * 8
 * find 3839442
 * add 123456 me
 * add 0 granny
 * find 0
 * find 123456
 * del 0
 * del 0
 * find 0
 * ```
 *
 * #### Output:
 * ```
 * not found
 * granny
 * me
 * not found
 * ```
 *
 * #### Explanation:
 *
 * * Recall that deleting a number that doesn’t exist in the phone book doesn’t change anything.
 *
 * ## Solution
 *
 * ### Time Complexity
 *
 * #### Best and Average Cases:
 *
 * * Each operation takes `O(1)` time. Thanks to a good hash function and design of the [Map].
 * * `N` queries take `O(N)` time.
 *
 * #### Worst-Case:
 *
 * * Note that we use the built-in [Map] that uses a good hash function and has a good design.
 * * However, if we still think about the worst-case, it means we assume that all the keys go into the same index.
 * * In that case, the total time is `O(N * N)` = `O(N^2)`.
 *
 * **When does this happen?**
 *
 * * All the `add` queries get the same index to store the value.
 * * Each `add` query is not just an `append` or `prepend` operation in a `LinkedList Chain`.
 * * It also checks if there exists the same `key`.
 * * To find if there exists the same `key`, we have to travel the whole chain.
 * * Only if we find that there is no such `key`, do we `add` the `new value` to the chain.
 * * Otherwise, if we find an existing `key`, we have to `update` the value.
 * * In this case, each incremental `add` query takes more time than the previous `add` query.
 * * Because each `add` query can increase the chain size.
 * * This simplifies to `O(N * N) = O(N^2)` worst-time complexity.
 *
 * ### Space Complexity
 *
 * * We use a [MutableMap] of size equivalent to number of `add` queries.
 * * So, if the number of `add` queries is `n`, then the space complexity is `O(n)`.
 *
 * ### Coursera's Grader Output
 *
 * ```
 * Good job! (Max time used: 0.54/6.00, max memory used: 86454272/671088640.)
 * ```
 *
 */
fun main() {

    val totalQueries = readln().toInt()
    val phoneBook = mutableMapOf<Int, String>()
    val result = StringBuilder()
    repeat(totalQueries) {
        val query = readln().split(" ")
        val command = query[0]
        when (command) {
            "add" -> {
                val number = query[1].toInt()
                val name = query[2]
                phoneBook[number] = name
            }
            "del" -> {
                val number = query[1].toInt()
                phoneBook.remove(number)
            }
            "find" -> {
                val number = query[1].toInt()
                val name = phoneBook[number]
                result.append(name ?: "not found").append("\n")
            }
        }
    }
    print(result.toString())
}