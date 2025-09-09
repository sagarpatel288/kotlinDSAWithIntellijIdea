package courses.uc.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem Statement: Check brackets in the code
 *
 * ## Problem Introduction:
 *
 * * In this problem you will implement a feature for a text editor to find errors in the usage of brackets in the
 * code.
 *
 * ## Problem Description:
 *
 * **Task.**
 *
 * * Your friend is making a text editor for programmers.
 * * He is currently working on a feature that will find errors in the usage of different types of brackets.
 * * Code can contain any brackets from the set `[]{}()`, where the opening brackets are `[`,`{`, and `(` and
 * the closing brackets corresponding to them are `]`,`}`, and `)`.
 * * For convenience, the text editor should not only inform the user that there is an error in the usage
 * of brackets, but also point to the exact place in the code with the problematic bracket.
 *
 * * First priority is to find the first unmatched closing bracket which either doesn’t have an opening bracket before
 * it, like `]` in `]()`, or closes the wrong opening bracket, like `}` in `()[}`.
 * * If there are no such mistakes, then it should find the first unmatched opening bracket without the corresponding
 * closing bracket after it, like `(` in `{}([]`.
 * * If there are no mistakes, text editor should inform the user that the usage of brackets is correct.
 * * Apart from the brackets, code can contain big and small latin letters, digits and punctuation marks.
 * * More formally, all brackets in the code should be divided into pairs of matching brackets, such that in
 * each pair the opening bracket goes before the closing bracket, and for any two pairs of brackets either
 * one of them is nested inside another one as in `(foo[bar])` or they are separate as in `f(a,b)-g[c]`.
 * * The bracket `[` corresponds to the bracket `]`, `{` corresponds to `}`, and `(` corresponds to `)`.
 *
 * **Input Format**
 *
 * * Input contains one string `𝑆` which consists of big and small latin letters, digits, punctuation
 * marks and brackets from the set `[]{}()`.
 *
 * **Constraints**
 *
 * * The length of `𝑆` is at least `1` and at most `10^5`.
 *
 * **Output Format**
 *
 * * If the code in `𝑆` uses brackets correctly, output, `Success` (without the quotes).
 * * Otherwise, output the 1-based index of the first unmatched closing bracket, and if there are no unmatched closing
 * brackets, output the 1-based index of the first unmatched opening bracket.
 *
 * **Sample 1.**
 *
 * * Input:
 * ```
 * []
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * The brackets are used correctly: there is just one pair of brackets `[` and `]`, they correspond to each
 * other, the left bracket `[` goes before the right bracket `]`, and no two pairs of brackets intersect, because
 * there is just one pair of brackets.
 *
 * **Sample 2.**
 *
 * * Input:
 * ```
 * {}[]
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * The brackets are used correctly: there are two pairs of brackets — the first pair of `{` and `}`, and second pair
 * of `[` and `]` — and these pairs do not intersect.
 *
 * **Sample 3.**
 *
 * * Input:
 * ```
 * [()]
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * The brackets are used correctly: there are two pairs of brackets — the first pair of `[` and `]`, and second pair
 * of `(` and `)` — and the second pair is nested inside the first pair.
 *
 * **Sample 4.**
 *
 * * Input:
 * ```
 * (())
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * Pairs with the same types of brackets can also be nested.
 *
 * **Sample 5.**
 *
 * * Input:
 * ```
 * {[]}()
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * Here there are 3 pairs of brackets, one of them is nested into another one, and the third one is separate
 * from the first two.
 *
 * **Sample 6.**
 *
 * * Input:
 * ```
 * {
 * ```
 * * Output:
 * ```
 * 1
 * ```
 * **Explanation:**
 *
 * * The code `{` doesn’t use brackets correctly, because brackets cannot be divided into pairs (there is just
 * one bracket). There are no closing brackets, and the first unmatched opening bracket is `{`, and its
 * position is 1, so we output 1.
 *
 * **Sample 7.**
 *
 * * Input:
 * ```
 * {[}
 * ```
 * * Output:
 * ```
 * 3
 * ```
 * **Explanation:**
 *
 * * The bracket `}` is unmatched, because the last unmatched opening bracket before it is `[` and not `{`.
 * * It is the first unmatched closing bracket, and our first priority is to output the first unmatched closing
 * bracket, and its position is 3, so we output 3.
 *
 * **Sample 8.**
 *
 * * Input:
 * ```
 * foo(bar);
 * ```
 * * Output:
 * ```
 * Success
 * ```
 * **Explanation:**
 *
 * * All the brackets are matching, and all the other symbols can be ignored.
 *
 * **Sample 9.**
 *
 * * Input:
 * ```
 * foo(bar[i);
 * ```
 * * Output:
 * ```
 * 10
 * ```
 * **Explanation:**
 *
 * * `)` doesn’t match `[`, so `)` is the first unmatched closing bracket, so we output its position, which is 10.
 *
 * # Thought-Process And Code Translations:
 *
 * ## Main Requirements:
 *
 * * If we find a mismatched closing bracket than the last opening bracket, then we need to return the position of this
 * closing bracket.
 * * If we find an unclosed opening bracket, then we need to return the position of this opening bracket.
 * * Otherwise, we can print success.
 *
 * ## Meaning:
 *
 * * It means that, we need to track the position of the opening brackets and the closing brackets.
 * * Each bracket has its own position.
 * * So, we can bind bracket and its position with each other by creating a data class.
 * * This way, it will be easy for us to get the position of each bracket.
 * * So, we will create a data class. It may look like below:
 * ```kotlin
 * data class BracketWithPosition(val bracket: Char, val position: Int)
 * ```
 * * Now, the idea is to use a `stack` where everytime we get an opening bracket, we would add it to the `stack`.
 * * If we get a closing bracket, we would pop the item from the stack, and we will compare it with the closing bracket.
 * * For that, the stack must not be empty.
 *
 * * **If the stack is not empty:**
 *
 * * If they make a pair of `opening-closing` bracket, all good.
 * * If they don't match, we have got a wrong closing bracket.
 * * We need to return the position of this wrong closing bracket.
 *
 * * **If the stack is empty:**
 *
 * * If the parsed character is a closing bracket and the stack is empty.
 * * It means that we don't have the relevant opening bracket to compare with.
 * * We got a closing bracket before we get a relevant opening bracket.
 * * It is an error.
 * * So, we return the position of the closing bracket.
 * * And if the parsed character is not a bracket, we don't want to do anything. We just continue the iteration.
 *
 * * The code will look like below:
 * ```kotlin
 *
 * fun checkBracket(input: String): Int {
 *     val arrayDeque = ArrayDeque<BracketWithPosition>()
 *     for (i in 0..<input.length) {
 *         if (isOpeningBracket(input[i])) {
 *             arrayDeque.addLast(BracketWithPosition(input[i), i)
 *         } else if (isClosingBracket(input[i])) && arrayDeque.isNotEmpty()) {
 *             val poppedBracket = arrayDeque.removeLast()
 *             if (!isProperPair(poppedBracket.bracket, input[i])) {
 *                 return incoming.indexPosition + 1
 *             }
 *         } else if (isClosingBracket(input[i])) {
 *             return incoming.indexPosition + 1
 *         }
 *     }
 * }
 *
 * ```
 *
 * * In the end, if we find that our stack is empty, then it means that either we have removed all the opening brackets,
 * and they have matched with the relevant closing brackets.
 * * Because, if any of the item didn't match (get) the relevant closing bracket, we would have returned the position
 * of the closing bracket.
 * * So, we return `Success`.
 * * Or, the other meaning of an empty stack is that there was no input string at all.
 * * In that case, we want to return `Success` because we don't have position of any bracket, because there were no
 * brackets at all!
 * * Hence, in any case, in the end, if the stack is empty, we return `Success`.
 * * But what if the stack is not empty?
 * * Well, in that case, as we know that we add only opening brackets to the stack.
 * * And we need to return the position of the first unmatched opening bracket.
 * * So, we return the position of the first item.
 * * But in a stack, we can't get the position of the first item until and unless we pop all the other items.
 * * Yes, that is true.
 * * And to make it easy, we can take a `Deque` - `A double ended queue` instead of a `stack`.
 * * So, we would keep adding the elements from the back (rear) side as `deque.addLast()`.
 * * And when we want to get the first item that is there in the container longer than any other item, we can simply
 * use `deque.first()`.
 * * Once we get the first item, we can simply return its position as `item.indexPosition + 1` because we have been
 * asked to return the `1-based` index position.
 * * So, this part translates into the following code:
 *
 * ```kotlin
 *      return if (arrayDeque.isEmpty()) "Success" else arrayDeque.first().indexPosition + 1
 * ```
 * * But how can we return both `String` and `Int`?
 * * Right, so we can change the return type of our function to be `String` and we can convert the `position` into a
 * `String` before we return a position.
 *
 * ## Time Complexity:
 *
 * * We travel the entire string. So, it is `O(n)`.
 *
 * ## Space Complexity:
 *
 * * We take a `Deque` to store each bracket. In the worst case where all the characters are opening brackets only,
 * it would be `O(n)`.
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 *
 * Good job! (Max time used: 0.15/2.00, max memory used: 50716672/536870912.)
 *
 *
 */
data class BracketWithPosition(val bracket: Char, val indexPosition: Int)

fun isOpeningBracket(ch: Char): Boolean {
    return ch == '[' || ch == '{' || ch == '('
}

fun isClosingBracket(ch: Char) = ch == ']' || ch == '}' || ch == ')'

fun isProperPair(preExist: Char, new: Char): Boolean {
    return when (preExist) {
        '[' -> new == ']'
        '{' -> new == '}'
        '(' -> new == ')'
        else -> false
    }
}

fun checkBracketsInCode(input: String): String {
    if (input.isEmpty()) return "Success"
    // A deque - double ended queue to add and remove the items from back, and to get the first item from the front.
    val deque = ArrayDeque<BracketWithPosition>()
    for ((index, ch) in input.withIndex()) {
        // If it is an opening bracket, add it to the deque.
        if (isOpeningBracket(ch)) {
            deque.addLast(BracketWithPosition(ch, index))
        } else if (isClosingBracket(ch) && deque.isNotEmpty()) {
            // We don't want to compare the opening bracket with any random character that is not a bracket.
            // That is why the `isClosingBracket` condition.
            // And we want to ensure that we don't try to remove an item from an empty deque.
            // That is why the `deque.isNotEmpty` condition.
            val popped = deque.removeLast()
            // If the recently added opening bracket that we just have popped from the deque, doesn't match with the
            // closing bracket, we have got a wrong closing bracket.
            // If it is not a proper pair, return the index of the wrong closing bracket.
            if (!isProperPair(popped.bracket, ch)) {
                // We have been asked to return `1-based` index.
                // That's why the `index + 1`.
                return "${index + 1}"
            }
        } else if (isClosingBracket(ch)) {
            // We don't want to return the index of any character that is not a bracket.
            // That's why the `if` condition.
            // Also, we have been asked to return `1-based` index.
            // That's why the `index + 1`.
            return "${index + 1}"
        }
    }
    // If the deque is empty, all the opening bracket items of the deque have been successfully matched.
    // So, return `Success`.
    // Otherwise, return the first unmatched opening bracket position that has been waiting longer than any other item.
    // We add `+1` because we have been asked to return `1-based` index.
    return if (deque.isEmpty()) "Success" else "${deque.first().indexPosition + 1}"
}


fun main() {
    val input = readln()
    println(checkBracketsInCode(input))
}