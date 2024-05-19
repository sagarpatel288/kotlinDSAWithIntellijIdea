package one01

import java.util.Scanner

fun main() {
    // To take the input, we use the `Scanner` class.
    // We have to use the back-stick (``) around the `in` because the `in` is a reserved keyword in Kotlin.
    // The back-stick works like escape or ignore kind of thing.
   val scanner = Scanner(System.`in`)
    // The `scanner.nextInt()` expects an integer (a number without float/decimal) in the console as an input.
    // We used `scanner.nextInt()` two times to take the input from the console two times.
    println(scanner.nextInt() + scanner.nextInt())
}