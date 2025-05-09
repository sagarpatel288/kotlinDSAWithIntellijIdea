package coursera.ucSanDiego.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * For the problem statement and explanation, please refer to the below file:
 * [Edit Distance Ready to backtrack](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/9fadc49e4a41893cc8e05b45f018b039d4c19976/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 */
fun main() {

    fun minEditDistance(target: String, reference: String): Int {
        val targetLength = target.length
        val referenceLength = reference.length
        val operations = Array(targetLength + 1) { IntArray(referenceLength + 1) }
        // Questions to confirm your understanding:
        // Why do we start from `0` and go up to `targetLength` instead of `operations.size - 1`?
        // What does it signify?
        for (i in 0..targetLength) {
            // Questions to confirm your understanding:
            // Why do we always take `[0]` in the `operations[i][0]`? What does it signify?
            operations[i][0] = i
        }
        // Questions to confirm your understanding:
        // Why do we start from `0` and go up to `referenceLength` instead of `operations.size - 1`?
        // What does it signify?
        for (j in 0..referenceLength) {
            // Questions to confirm your understanding:
            // Why do we always take `[0]` in the `operations[0][j]`? What does it signify?
            operations[0][j] = j
        }
        // Questions to confirm your understanding:
        // Why do we take iterate from `1` to `targetLength` in the outer for loop? What does it signify?
        // Why do we take iterate from `1` to `referenceLength` in the inner for loop? What does it signify?
        // What will happen if the `targetLength` and `referenceLength` are different,
        // and we switch the end-points of both the outer for loop and the inner for loop?
        // That is to say, what will happen if we iterate up to the `referenceLength` in the outer for loop,
        // and up to the `targetLength` in the inner for loop? What does this exercise signify, teach, and convey?
        for (i in 1..targetLength) {
            for (j in 1..referenceLength) {
                if (target[i - 1] == reference[j - 1]) {
                    // This is a key-lemma that we need to remember!
                    operations[i][j] = operations[i - 1][j - 1]
                } else {
                    // These are the key-lemmas that we need to remember!
                    val costOfInsertion = operations[i][j - 1] + 1
                    val costOfDeletion = operations[i - 1][j] + 1
                    val costOfSubstitute = operations[i - 1][j - 1] + 1
                    operations[i][j] = minOf(costOfInsertion, costOfDeletion, costOfSubstitute)
                }
            }
        }
        return operations[targetLength][referenceLength]
    }

    val target = readln().trim()
    val reference = readln().trim()
    println(minEditDistance(target, reference))
}