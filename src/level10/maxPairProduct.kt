package level10

import java.util.Scanner

fun main() {
    // Let us take the first input from the console
    val scanner = Scanner(System.`in`)
    val minimumTotalInputs = 2
    val maximumTotalInputs = 2 * 100000
    // The first input represents the maximum number of elements for the subsequent input
    var totalInputs = scanner.nextInt()
    while (totalInputs < minimumTotalInputs) {
        println("Please enter a positive number greater than or equal to $minimumTotalInputs")
        totalInputs = scanner.nextInt()
    }
    while (totalInputs > maximumTotalInputs) {
        println("Please enter a positive number less than or equal to $maximumTotalInputs")
        totalInputs = scanner.nextInt()
    }
    // Let us take the number of inputs based on the above [totalInputs] value.
    // The [totalInputs] is the size of the array.
    val inputs = Array(totalInputs){0}
    for(i in 0 until totalInputs) {
        // Save each input in an array.
        inputs[i] = scanner.nextInt()
    }
    // We have all the inputs. We need to find max pair product now.
    // The max pair product means the pair of first and second large (max) number.
    // Hence, we need to find the first and second large number from all the elements.
    var firstLargestNumber = 0
    var firstLargestNumberIndex = 0
    var secondLargestNumber = 0
    var secondLargestNumberIndex = 0

    // Finding the first large number
    for (i in inputs.indices) {
        if (inputs[i] > firstLargestNumber) {
            firstLargestNumber = inputs[i]
            firstLargestNumberIndex = i
        }
    }

    // Finding the second large number
    for (i in inputs.indices) {
        if (inputs[i] > secondLargestNumber && i != firstLargestNumberIndex) {
            secondLargestNumber = inputs[i]
            secondLargestNumberIndex = i
        }
    }

    /*// Printing for the sake of debugging and acknowledgement
    println("The first largest number is $firstLargestNumber at $firstLargestNumberIndex")
    println("The second largest number is $secondLargestNumber at $secondLargestNumberIndex")*/

    // Printing the product of the two largest numbers available
//    println("The product of the two largest number is: ${firstLargestNumber * secondLargestNumber}")
    println((firstLargestNumber * secondLargestNumber).toLong())
}