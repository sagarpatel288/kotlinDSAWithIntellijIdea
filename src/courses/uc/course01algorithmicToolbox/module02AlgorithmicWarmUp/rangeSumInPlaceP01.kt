package courses.uc.course01algorithmicToolbox.module02AlgorithmicWarmUp

class RangeSumInPlace(private val input: MutableList<Int>) {

    init {
        // In-placed prefixed sum array
        for (i in 1 .. input.lastIndex) {
            input[i] = input[i - 1] + input[i]
        }
    }

    // 0-indexed queries, in-placed prefixed sum array
    fun rangeSumInPlace(start: Int, end: Int): Int {
        // Edge cases
        if (input.isEmpty()) return 0
        if (start !in input.indices || end !in input.indices || start > end) {
            throw RuntimeException("Invalid range for the array of size: ${input.size}!")
        }
        if (start == 0) return input[end]
        // Otherwise
        return input[end] - input[start - 1]
    }
}

fun main() {
    val input = readln().split(" ").map { it.toInt() }.toMutableList()
    val (start, end) = readln().split(" ").map { it.toInt() }
    val solver = RangeSumInPlace(input)
    println(solver.rangeSumInPlace(start, end))
}