package courses.uc.course01algorithmicToolbox.module02AlgorithmicWarmUp

import java.util.Scanner

/**
 * Last Digit of Fibonacci Number Problem:
 *
 * Compute the last digit of the n-th Fibonacci number.
 *
 * Input: An integer n.
 *
 * Output: The last digit of the n-th Fibonacci number.
 *
 * Input format. An integer n. Output format. The last digit of Fn.
 *
 * Constraints. 0 ≤ n ≤ 10 to the 6th (106).
 *
 * Fibonacci series example:
 * 0, 1, 1, 2, 3, 5, 8, 13...
 *
 * Sample 1.
 *
 * Input:
 *
 * 3
 *
 * Output:
 *
 * 2
 *
 * F3 = 2.
 *
 * Sample 2.
 *
 * Input:
 *
 * 139
 *
 * Output:
 *
 * 1
 *
 * F139 =50095301248058391139327916261.
 *
 * ----------------------- Key-lemma (Key-point) -----------------------
 *
 * Calculating the nth Fibonacci number directly is computationally expensive,
 * especially for large values of n (up to 10 to the 6th).
 * By reducing n using the Pisano period,
 * we only need to compute the Fibonacci number for a much smaller index, which is much faster.
 *
 * For a given modulo m, the nth Fibonacci number a,
 * f(a) % m = f(b) % m where b = a % p and p = The Pisano period.
 * Here, a is congruent to b mod m. I.e., a ≅ b mod m. It means that, a % m = b % m.
 *
 * We can also write it as, a ≅ b mod m.
 * It means that, a mod m = b mod m. How to read it? We read it as “A is congruent to b mod m.”
 *
 * Let us see, understand, and learn it through a practical.
 * We will use comments to help us understand the code.
 */
fun main() {

    /**
     * We want to print the last digit of a long number.
     * So, we use modulo. Any number modulo 10 gives the last digit of the number.
     */
    fun getFibonacciLastDigit(reducedN: Long, modulo: Long): Long {
        if (reducedN <= 1L) return reducedN
        var previous = 0L
        var current = 1L
        // We already have F(0) and F(1). Hence, we start from 2.
        // For example, let us assume that the reducedN is 5.
        // The reducedN is our upper limit and it is 5.
        // We are looking for the value of F(5).
        // If we start from 0, it will go up to and including 7.
        // Because, then 0 for F(2), 1 for F(3), 2 for F(4), 3 for F(5), 4 for F(6), 5 for F(7) which is wrong.
        // If we really want to start from 0, then we should subtract 2 from the reducedN. I.e., reducedN - 2,
        // acknowledging the fact that we already have two values. So, the upper limit will be reducedN - 2 = 5 - 2 = 3.
        // In that case, 0 for F(2), 1 for F(3), 2 for F(4), 3 for F(5), which is fine.
        for (i in 2..reducedN) {
            // Imagine you are adding two large numbers, but you only care about the last digit of the result.
            // Instead of adding the entire numbers, you can add their last digits
            // and take the remainder when divided by 10.
            // This gives you the last digit of the sum directly.
            // E.g. Instead of doing 21 + 34 = 55 % 10 = 5, <------------------
            // we can sum up last digits: 1 + 4 = 5 % 10 = 5. <----------------
            val newCurrent = (previous + current) % modulo
            previous = current
            current = newCurrent
        }
        return current
    }

    /**
     * We want to find at which point the nth fibonacci value % [modulo] repeats itself.
     * E.g., We may find that, when we do nth fibonacci number `f(n) % 10`,
     * the sequence repeats itself after 60 in such a way that
     * `f(4) % 10` and `f(64) % 10` give the same result: 3.
     * I.e., The last digit (that's what we get when we do `% 10`) of the nth fibonacci value
     * repeats after every 60 numbers.
     * The point at which it starts repeating itself, is our Pisano period.
     * So, in our example, for modulo 10, the Pisano period is 60.
     * I.e. After 60, the sequence (result) of `f(n) % 10` starts repeating itself.
     *
     * Also, it is a precomputed fact that the Pisano period for a given modulo
     * is less than or equal to modulus squared.
     * It is based on the number theory properties of modular arithmetic.
     *
     * We start from 0 and iterate up to m squared = m * m where m is the given modulo.
     *
     * Let us assume that the given modulus is 2.
     *
     * | Iteration 	| Nth Fibonacci Symbol 	| Addition(Index) 	| Addition(Value)  	| Addition(Result) 	| Mod 2( % 2) 	| Length 	|
     * |:---------:	|:--------------------:	|:---------------:	|:----------------:	|:----------------:	|:-----------:	|:------:	|
     * |           	|         F(0)         	|     Initial     	|      Initial     	|         0        	|      0      	|        	|
     * |           	|         F(1)         	|     Initial     	|      Initial     	|         1        	|      1      	|        	|
     * |     0     	|         F(2)         	|   F(0) + F(1)   	|       0 + 1      	|         1        	|      1      	|    1   	|
     * |     1     	|         F(3)         	|   F(1) + F(2)   	|       1 + 1      	|         2        	|      0      	|    2   	|
     * |     2     	|         F(4)         	|   F(2) + F(3)   	|       1 + 0      	|         1        	|      1      	|    3   	|
     *
     * We are finding the Pisano length, after which the sequence repeats.
     * Our initial pair is (0, 1), the first two elements.
     * Now, we start counting from 0 and go up to the point where we find that the sequence pattern
     * of the remainders repeats itself.
     *
     * So, basically, that's why we start our iteration from 0 instead of 2.
     *
     */
    fun findPisanoPeriodOfFibonacci(modulo: Long): Long {
        if (modulo == 0L) throw IllegalArgumentException("Modulo cannot be 0")
        var previous = 0L
        var next = 1L
        var length = 0L
        for (i in 0..(modulo * modulo)) {
            // Let us assume that f(8) = 21 and f(9) = 34.
            // So, f(10) = f(8) + f(9) = 21 + 34 = 55.
            // The last digit of 55 is 5. <---------------------------------------------------------
            // However, as we are interested in the last digit only,
            // instead of taking the whole number sum (21 + 34),
            // we can take the last digit of each (1 from 21 and 4 from 34),
            // sum up them (1 + 4), and we would still get the same answer: 5 <---------------------
            // The last digit of 21 + 34 = 5 and the last digit of 1 + 4 = 5 remain same. <---------
            // Applying the modular arithmetic theory
            // (a + b) % m = {(a % m) + (b % m)} % m
            // The variables previous and next are already the result of individual element % modular
            // And then what we want to observe is the sequence or pattern of such last digits.
            // I.e., We get nth fibonacci value, and then we perform nth fibonacci value % modulo to get the last digit.
            // Then, we want to spot the point at which the sequence of `f(n) % modulo` starts repeating itself.
            // The point at which it starts repeating itself, is our Pisano period.
            val lastDigitOfLastTwoFibonacciNumbers = (previous + next) % modulo
            previous = next
            next = lastDigitOfLastTwoFibonacciNumbers
            length += 1

            if (previous == 0L && next == 1L) {
                // Stop when the sequence repeats
                return length
            }
        }
        return length
    }

    /**
     * Gives the fibonacci value for the [nthFibonacci].
     */
    fun getFibonacciLastDigit(nthFibonacci: Long): Long {

        // If it is 0, the last digit is 0.
        // If it is 1, the last digit is 1.
        // Anything less than or equal to 1, returns the number itself.
        if (nthFibonacci <= 1L) return nthFibonacci

        // We are interested in the last digit of nth fibonacci.
        // Any number modulo 10 gives the last digit!
        // But, it is inefficient if we do that for each and every large fibonacci number.
        // There is a number theory property to optimise the approach.
        // For a particular modulo m, the sequence of result `f(n) % m` repeats itself.
        // This point is known as Pisano period.
        // If we find it, we can take the equivalent smaller number of a large nth fibonacci number,
        // because the result of `f(n) % m` would be same for both of them.
        // We have our modulo as 10. Let us find out the Pisano period.
        val pisanoPeriod = findPisanoPeriodOfFibonacci(10L)

        // We reduce the input (nth fibonacci number) modulo pisanoPeriod to get a much smaller equivalent of the input!
        // E.g., F(4) mod 10 and F(64) mod 10 will give the same answer: 3.
        // Where F(4) is the 4th element in the Fibonacci series and F(64) is the 64th element in the Fibonacci series.
        // Note that this is an element or index, not the value itself.
        val reducedN = (nthFibonacci % pisanoPeriod)

        // Let us find the last digit of the much smaller equivalent fibonacci number.
        // Any number modulo 10 gives the last digit!
        // So, we pass 10 as a modulo value.
        return getFibonacciLastDigit(reducedN, 10L)
    }

    // Take the input
    val scanner = Scanner(System.`in`)

    // Read the input
    val nthFibonacci = scanner.nextLong()

    // Prints the last digit of the nth fibonacci value.
    // Thought process and Key-points that solve the problem:
    // Pre-requisite:
    // Understand the definition of Fibonacci series
    // Key-lemma:
    // 1. The Pisano period that reduces the complexity and improves the efficiency.
    // 2. Modular arithmetic.
    println(getFibonacciLastDigit(nthFibonacci))
}