package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

/**
 * ## ----------------------- Grader Output -----------------------
 * (Max time used: 0.10/3.00, max memory used: 46772224/536870912.)
 */
fun main() {

    fun minOperationsWithStations(target: Int): Pair<Int, List<Int>> {
        // Container to store minimum operations for each number.
        // A container of `target + 1` size with default value `0`.
        val operations = IntArray(target + 1)
        // Base case: To reach `1`, we need `0` operations. Because `1` is the starting point.
        operations[1] = 0
        // Bottom-up.
        for (i in 2..target) {
            // This is always possible to reach `i` from `i - 1` using the `+1` operator.
            operations[i] = operations[i - 1] + 1
            // If `i` is divisible by `2`, we can reach `i` directly from `i/2` using the `*2` operator on `i/2`.
            if (i % 2 == 0) {
                operations[i] = minOf(operations[i], operations[i/2] + 1)
            }
            // If `i` is divisible by `3`, we can reach `i` directly from `i/3` using the `*3` operator on `i/3`.
            if (i % 3 == 0) {
                operations[i] = minOf(operations[i], operations[i/3] + 1)
            }
        }
        // Backtracking.
        var currentNumber = target
        val path = mutableListOf<Int>()
        while (currentNumber > 1) {
            path.add(currentNumber)
            when {
                currentNumber % 3 == 0 && operations[currentNumber] == operations[currentNumber/3] + 1 -> {
                    currentNumber /= 3
                }
                currentNumber % 2 == 0 && operations[currentNumber] == operations[currentNumber/2] + 1 -> {
                    currentNumber /= 2
                }
                operations[currentNumber] == operations[currentNumber - 1] + 1 -> {
                    currentNumber -= 1
                }
            }
        }
        // Caution! Possible point of mistake!
        // We add `curr`, and not `1`. Maybe, to keep it dynamic.
        path.add(currentNumber)
        return operations.last() to path.reversed()
    }

    val input = readln().toInt()
    val result = minOperationsWithStations(input)
    println(result.first)
    println(result.second.joinToString(" "))
}