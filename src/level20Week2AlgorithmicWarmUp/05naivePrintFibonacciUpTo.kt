package level20Week2AlgorithmicWarmUp

fun main() {

    /**
     * If the input [upTo] is too large, it can overflow!
     */
    fun printFibonacciUpTo(upTo: Int) {
        var previous = 0
        var current = 1
        val fibonacciList = mutableListOf(previous, current)
        for (i in 0..upTo - 2) {
            val newCurrent = previous + current
            fibonacciList.add(newCurrent)
            previous = current
            current = newCurrent
        }
        println(fibonacciList)
    }

    val upto = readln().toInt()
    // If the input is too large, it can overflow!
    printFibonacciUpTo(upto)
}