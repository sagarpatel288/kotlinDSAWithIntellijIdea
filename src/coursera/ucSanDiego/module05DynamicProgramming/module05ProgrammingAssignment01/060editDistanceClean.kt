package coursera.ucSanDiego.module05DynamicProgramming.module05ProgrammingAssignment01

fun main() {

    fun minEditDistance(target: String, reference: String): Int {
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