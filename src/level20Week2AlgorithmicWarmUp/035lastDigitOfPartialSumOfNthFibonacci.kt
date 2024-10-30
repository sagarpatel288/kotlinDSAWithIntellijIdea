package level20Week2AlgorithmicWarmUp

/**
 * How to remember? (This is a trick to remember something using the association principle, not a proof of the theory.):
 *
 * We remember 3 formulas in a sequence. We start with the sum.
 *
 * Sum of nth Fibonacci: S(n):
 *
 * Sum = a + b. a and b are two elements. n + 2. But, we were asked f(sum of nth). So, we do - 1.
 * I.e. (n + 2) - 1. So, it is: f(n + 2) - 1.
 *
 *  Partial sum of n and m fibonacci: S(m, n):
 *
 *  Keep `(n + 2) - ` from the above equation as it is.
 *  Now, The m is the remaining one part. m + 1.
 *  So, f(n + 2) - f(m + 1).
 *
 *  Sum of square of nth Fibonacci:
 *
 *  After normal sum of fibonacci, we have this sum of square of fibonacci.
 *  Keep f(m + 1) as it is from the above equation.
 *  Square means, m * m, right? So, one m is the remaining part in the equation.
 *  So, it is: f(m + 1) f(m).
 *  Now, you take m or n, it doesn't matter.
 *  So, it is: f(n + 1) f(n).
 *  Also, the order doesn't matter in multiplication.
 *  So, it is: f(n) * f(n + 1).
 */
fun main() {

    /**
     * Gives the Pisano period.
     * According to the Pisano theory,
     * a % m = b % m if b = a % p, where p is the Pisano period and p <= (m * m).
     * Here, b is significantly smaller than `a`. We do this to reduce the large computation.
     * We can understand the importance of the Pisano period by using an analogy of a clock.
     * We know that a 12 hours clock repeats itself after every 12 o'clock.
     * Here, 12 is a modular (mod). Now, suppose the current time is 11 o'clock.
     * If someone asks us what will be the time after 1000 hours, instead of adding 1000 hours to the current time,
     * we can reduce our computation and increase the speed of the result by using the below equation:
     * 1000 % 12 = 4. So, instead of adding 1000 hours to the current time 11, we can simply add 4.
     * Hence, the answer is: 11 + 4 = 3 (Remember, we are talking about a clock!).
     * Here, 12 was a known length for a 12 hours clock, after which the cycle, the sequence pattern repeats itself.
     * However, to know such a unique length for an arbitrary series, we need to find it.
     * What we find here, is known as the Pisano period, and we can find it if we know the value of the modular (mod).
     * We are already passing this required [modulo] to find the Pisano period.
     */
    fun getPisanoPeriod(modulo: Long): Long {
        // F(0) % modulo = 0L
        var start = 0L
        // F(1) % modulo = 1L
        var next = 1L
        // We have the first two remainders (sequence pattern pair)
        // Now, we will monitor (observe) the next occurrence of the pair (0, 1)
        // The point at which we confirm the repetition, is the Pisano period.
        var pisanoLength = 0L
        for (i in 0..(modulo * modulo)) {
            val newNext = (start + next) % modulo
            start = next
            next = newNext
            pisanoLength++

            // Stop the loop when we find the repetition of the sequence pattern pair (0, 1)
            if (start == 0L && next == 1L) {
                return pisanoLength
            }
        }
        return pisanoLength
    }

    /**
     * Any number % 10 gives the last digit.
     * Nth Fibonacci number is Fn.
     * The last digit of nth Fibonacci number is Fn % 10
     * The nth Fibonacci number is an addition of the last two (preceding) Fibonacci numbers.
     * So, Fn % 10
     *
     * = (Fn1 + Fn2) % 10
     *
     * Instead of finding the actual value of the Fn1 and Fn2, we can use the Modular Arithmetic.
     *
     * = {(Fn1 % 10) + (Fn2 % 10)} % 10
     */
    fun getLastDigitOfNthFibonacci(nthFibonacci: Long): Long {
        // F(0) % 10 = 0
        var previous = 0L
        // F(1) % 10 = 1
        var current = 1L
        // We need to keep adding the two numbers up to the given nthFibonacci.
        // However, we already have 0th and 1st elements using which we will find the 2nd element.
        // So, the loop starts from 2 instead of 0.
        for (i in 2..nthFibonacci) {
            val next = (previous + current) % 10
            previous = current
            current = next
        }
        return current
    }

    /**
     * [start] is the starting element from which we want to perform the addition up to the [end].
     * [end] is the last element up to which we want to perform the addition.
     * Let us assume the [start] point is m and the [end] point is n.
     * S(m, n) mod 10
     *
     * = {F(n+2) - F(m+1)} mod 10
     *
     * = (Fnp - Fmp) mod 10 where Fnp = F(n+2) % p and Fmp = F(m+1) % p
     *
     * Here, p is the Pisano period.
     *
     * = {(Fnp mod 10) - (Fmp mod 10)} mod 10
     */
    fun getLastDigitOfPartialSumOfNthFibonacci(start: Long, end: Long): Long {
        val pisanoPeriod = getPisanoPeriod(10L)
        if (start == end) {
            if (start == 0L) return 0
            val reducedN = start % pisanoPeriod
            return getLastDigitOfNthFibonacci(reducedN)
        }
        val reducedEndPlusTwo = (end + 2) % pisanoPeriod
        val reducedStartPlusOne = (start + 1) % pisanoPeriod
        val reducedEndMod = getLastDigitOfNthFibonacci(reducedEndPlusTwo)
        val reducedStartMod = getLastDigitOfNthFibonacci(reducedStartPlusOne)
        // We are interested in the last digit of the (a - b)
        // But, b can be larger than `a` in our case as they are just remainders.
        // For example, the last digit so far we have calculated can be 0 at this moment.
        // According to the formula, we would do, 0 - 1 = -1.
        // We want to avoid the negative number. So, we add 10.
        // Hence, it becomes: 0 - 1 + 10 = 9 % 10 = 9.
        // Why do we do % 10 here at this stage also?
        // Because, this is a common code.
        // So, it applies even when we get one of the values as 4 as a last digit after the above code.
        // Now, when we do 4 - 1 + 10 = we get 13, and we do not want to return 13.
        // Hence, the % 10 will make it: 13 % 10 = 3, the last digit we want.
        // So, in the end, % 10 ensures that we get the last digit.
        // So, adding 10 does not change the last digit, and it saves us from the negative results.
        // The final % 10 ensures that we get the right last digit.
        return (reducedEndMod - reducedStartMod + 10) % 10
    }

    val start = readln().toLong()
    val end = readln().toLong()
    println(getLastDigitOfPartialSumOfNthFibonacci(start, end))
}
