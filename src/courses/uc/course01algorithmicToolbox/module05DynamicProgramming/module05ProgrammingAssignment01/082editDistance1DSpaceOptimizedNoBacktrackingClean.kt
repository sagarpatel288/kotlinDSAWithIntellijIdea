package courses.uc.course01algorithmicToolbox.module05DynamicProgramming.module05ProgrammingAssignment01

class EditDistanceSpaceOptimized {
    fun findEditDistanceSpaceOptimized(target: String, reference: String): Int {
        val (shorter, longer) = if (target.length <= reference.length) {
            target to reference
        } else {
            reference to target
        }
        var prev = IntArray(shorter.length + 1)
        var curr = IntArray(shorter.length + 1)
        for (i in 0..shorter.length) {
            prev[i] = i
        }
        for (i in 1..longer.length) {
            curr[0] = i // 0th column for each row
            for (j in 1..shorter.length) {
                if (longer[i - 1] == shorter[j - 1]) {
                    curr[j] = prev[j - 1]
                } else {
                    val deletionCost = prev[j] + 1
                    val insertionCost = curr[j - 1] + 1
                    val replacementCost = prev[j - 1] + 1
                    curr[j] = minOf(deletionCost, insertionCost, replacementCost)
                }
            }
            val temp = prev
            prev = curr
            curr = temp
        }
        return prev[shorter.length]
    }
}

fun main() {
    val target = readln()
    val ref = readln()
    val solver = EditDistanceSpaceOptimized()
    val answer = solver.findEditDistanceSpaceOptimized(target, ref)
    println(answer)
}