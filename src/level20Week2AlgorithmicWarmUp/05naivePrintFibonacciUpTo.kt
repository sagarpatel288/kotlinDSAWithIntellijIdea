package level20Week2AlgorithmicWarmUp

/**
 * Write a program to print the fibonacci series up to the given input number n.
 *
 * The fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 *
 * So, if the input is 3, then print the fibonacci series up to:
 * 0, 1, 1, 2
 */
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