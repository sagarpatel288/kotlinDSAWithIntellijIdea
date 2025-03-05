package coursera.ucSanDiego.module02AlgorithmicWarmUp

fun main() {

    data class Range(val start: Int, val end: Int)

    fun rangeSumQuery(input: MutableList<Int>, queries: List<Range>): List<Int> {
        if (input.isEmpty()) {
            return listOf(0)
        }
        for (i in 1..input.lastIndex) {
            input[i] = input[i - 1] + input[i]
        }
        val result = mutableListOf<Int>()
        for (range in queries) {
            if (range.start <= 0 || range.end > input.size) {
                println("Invalid range: start: ${range.start} end: ${range.end} for the input size: ${input.size}")
                continue
            }
            val rangeSum = if (range.start == 1) {
                input[range.end - 1]
            } else {
                input[(range.end - 1)] - input[(range.start - 2)]
            }
            result.add(rangeSum)
        }
        return result
    }

    val input = readln().split(" ").map { it.toInt() }.toMutableList()
    val ranges = mutableListOf<Range>()
    for (i in 1..4) {
        val range = readln().split(" ").map { it.toInt() }
        ranges.add(Range(range[0], range[1]))
    }
    println(rangeSumQuery(input, ranges).joinToString(" "))
}