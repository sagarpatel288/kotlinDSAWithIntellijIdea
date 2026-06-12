package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

class EditDistanceWithBacktracking {
    fun findEditDistanceWithBacktracking(target: String, ref: String): Pair<Int, List<String>> {
        val n = target.length
        val m = ref.length
        val ops = Array(n + 1) { IntArray(m + 1) }
        for (i in 0..n) {
            ops[i][0] = i
        }
        for (j in 0..m) {
            ops[0][j] = j
        }
        for (i in 1..n) {
            for (j in 1..m) {
                if (target[i - 1] == ref[j - 1]) {
                    ops[i][j] = ops[i - 1][j - 1]
                } else {
                    val insertionCost = ops[i][j - 1] + 1
                    val deletionCost = ops[i - 1][j] + 1
                    val replacementCost = ops[i - 1][j - 1] + 1
                    ops[i][j] = minOf(insertionCost, deletionCost, replacementCost)
                }
            }
        }
        val editDistanceAnswer = ops[n][m]
        // Backtracking
        // Last pointers in the table
        var targetPointer = n
        var refPointer = m
        val backtracking = mutableListOf<String>()
        var operationNum = 0
        backtracking.add("Target: $target Ref: $ref")
        // Caution! Possible point of mistake!
        // We use the OR condition because one of the two pointers (strings) can get exhausted earlier than the other!
        while (targetPointer > 0 || refPointer > 0) {
            if (targetPointer > 0) {
                backtracking.add("Comparing: Target ch: ${target[targetPointer - 1]}")
            }
            if (refPointer > 0) {
                backtracking.add("Comparing: Ref ch: ${ref[refPointer - 1]}")
            }
            // If both the targetPointer > 0 and refPointer > 0, then only we can do:
            if (targetPointer > 0 && refPointer > 0 && target[targetPointer - 1] == ref[refPointer - 1]) {
                backtracking.add("Matched! Target: ${target[targetPointer - 1]} Ref: ${ref[refPointer - 1]}")
                targetPointer--
                refPointer--
            } else {
                // Otherwise
                val inserted = refPointer > 0
                        && ops[targetPointer][refPointer] == ops[targetPointer][refPointer - 1] + 1
                val deleted = targetPointer > 0
                        && ops[targetPointer][refPointer] == ops[targetPointer - 1][refPointer] + 1
                val replaced = targetPointer > 0 && refPointer > 0
                        && ops[targetPointer][refPointer] == ops[targetPointer - 1][refPointer - 1] + 1
                operationNum++
                backtracking.add("Operation number: $operationNum")
                when {
                    inserted -> {
                        refPointer--
                        backtracking.add("Inserted!")
                    }
                    deleted -> {
                        targetPointer--
                        backtracking.add("Deleted!")
                    }
                    replaced -> {
                        targetPointer--
                        refPointer--
                        backtracking.add("Replaced!")
                    }
                    else -> {
                        backtracking.add("Error at target pointer: $targetPointer and ref pointer: $refPointer")
                    }
                }
            }
        }
        backtracking.add("Finished!")
        return editDistanceAnswer to backtracking
    }
}

fun main() {
    val target = readln()
    val ref = readln()
    val solver = EditDistanceWithBacktracking()
    val (editDistanceOps, backtrackingOps) = solver.findEditDistanceWithBacktracking(target, ref)
    println("Edit distance operations: $editDistanceOps")
    println("Operations: ${backtrackingOps.joinToString("\n")}")
}