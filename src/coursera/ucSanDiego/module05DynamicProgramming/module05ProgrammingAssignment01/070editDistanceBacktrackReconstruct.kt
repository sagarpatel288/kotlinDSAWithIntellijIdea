package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * # ----------------------- Reference / Prerequisite -----------------------
 *
 * [Edit Distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/1801544f382beb3d55e53db02db4ac342e334ba2/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 *
 * # ----------------------- Thoughts-to-code -----------------------
 *
 * Backtracking (reconstruction) starts with the last point. That's why, we call it backtracking.
 * And we continue the iteration until we reach the start point (index 0).
 *
 * We use the same reason (resource, method, condition, comparison) we used to
 * move from `i - 1` (previous) to `i` (next).
 *
 * Recall the
 * [Edit Distance](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/1801544f382beb3d55e53db02db4ac342e334ba2/src/coursera/ucSanDiego/module05DynamicProgramming/module05ProgrammingAssignment01/050editDistanceMatchStrings.kt)
 * problem.
 *
 *
 * To backtrack, we use the same method/s (condition/s).
 *
 */
fun main() {

    fun minEditDistance(target: String, reference: String): Pair<Array<IntArray>, Int> {
        val targetLength = target.length
        val referenceLength = reference.length
        val operations = Array(targetLength + 1) { IntArray(referenceLength + 1) }
        for (i in 0..targetLength) {
            operations[i][0] = i
        }
        for (j in 0..referenceLength) {
            operations[0][j] = j
        }
        for (i in 1..targetLength) {
            for (j in 1..referenceLength) {
                if (target[i - 1] == reference[j - 1]) {
                    operations[i][j] = operations[i - 1][j - 1]
                } else {
                    val costOfDelete = operations[i - 1][j] + 1
                    val costOfInsertion = operations[i][j - 1] + 1
                    val costOfSubstitute = operations[i - 1][j - 1] + 1
                    operations[i][j] = minOf(costOfDelete, costOfInsertion, costOfSubstitute)
                }
            }
        }
        return operations to operations[targetLength][referenceLength]
    }

    fun backtrackEditDistance(operations: Array<IntArray>, target: String, reference: String) {
        // We start with the last cell of the 2D array `operations`.
        // The size of the `operations` 2D array is: (targetLength + 1)(referenceLength + 1).
        // So, the last index of the `operations` 2D array is: (targetLength, referenceLength).
        // Hence, we start with the index pointer `targetLength` and the index pointer `referenceLength`.
        // Gradually, we will move towards the index (0, 0).
        var targetPointer = target.length
        var referencePointer = reference.length
        var numberOfOperations = 0
        while (targetPointer > 0 || referencePointer > 0) {
            // Checking the last characters of each string.
            if (targetPointer > 0) {
                println("Target character: ${target[targetPointer - 1]}")
            }
            if (referencePointer > 0) {
                println("Reference character: ${reference[referencePointer - 1]}")
            }
            // Recall that the size of the 2D array `operations` is (targetLength + 1)(referenceLength + 1).
            // Hence, to retrieve the corresponding character from each string,
            // we need to subtract 1 from each index pointer.
            if (targetPointer > 0 && referencePointer > 0
                && target[targetPointer - 1] == reference[referencePointer - 1]
            ) {
                // Characters match. We don't perform any operation. Move diagonal backward.
                targetPointer--
                referencePointer--
            } else {
                // Recall that the operations array has size (n + 1)(m + 1).
                // So, the `targetLength` represents the last row, and
                // the `referenceLength` represents the last column.
                // It does not give `index out of bound` exception.
                // Because the 2D array `operations` has one more size (+1) than the given strings.
                // As per `Wagner-Fischer` theory,
                // when we backtrack, we start from the last cell of the 2D array `operations`.
                // So, we start from the `length` index of the 2D array `operations`.
                val current = operations[targetPointer][referencePointer]
                val deleteCost = if (targetPointer > 0) {
                    operations[targetPointer - 1][referencePointer] + 1
                } else {
                    Int.MAX_VALUE
                }
                val insertCost = if (referencePointer > 0) {
                    operations[targetPointer][referencePointer - 1] + 1
                } else {
                    Int.MAX_VALUE
                }
                val substituteCost = if (targetPointer > 0 && referencePointer > 0) {
                    operations[targetPointer - 1][referencePointer - 1] + 1
                } else {
                    Int.MAX_VALUE
                }
                when (current) {
                    deleteCost -> {
                        println("We delete target character. Number of operations: ${++numberOfOperations}")
                        targetPointer--
                    }
                    insertCost -> {
                        println("We insert reference character. Number of operations: ${++numberOfOperations}")
                        referencePointer--
                    }
                    substituteCost -> {
                        println("We substitute the target character with the reference character. Number of operations: ${++numberOfOperations}")
                        targetPointer--
                        referencePointer--
                    }
                }
            }
        }
    }

    val target = readln().trim()
    val reference = readln().trim()
    val editDistance = minEditDistance(target, reference)
    println(editDistance.second)
    backtrackEditDistance(editDistance.first, target, reference)
}