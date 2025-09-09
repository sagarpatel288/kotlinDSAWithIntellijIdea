package courses.uc.course01algorithmicToolbox.module02AlgorithmicWarmUp

fun main() {

    fun getSurvivorPosition(numberOfRebels: Int, killingFactorInterval: Int): Int {
        if (numberOfRebels <= 1) return 0
        var survivorPosition = 0
        for (i in 2..numberOfRebels) {
            survivorPosition = (survivorPosition + killingFactorInterval) % i
        }
        return survivorPosition
    }

    val values = readln().split(" ").map {
        it.toInt()
    }

    println(getSurvivorPosition(values[0], values[1]))

}