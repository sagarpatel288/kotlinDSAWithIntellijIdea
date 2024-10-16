package level30Module3AlgorithmExercise

fun main() {

    /**
     * Maximum Salary
     * Largest Concatenate Problem
     * Compile the largest integer by concatenating the given integers.
     * Input: A sequence of positive integers.
     * Output: The largest integer that can be obtained by concatenating the given integers in some order.
     *
     * This is probably the most important problem in this book :).
     * As the last question on an interview,
     * your future boss gives you a few pieces of paper with a single number written on each of them and
     * asks you to compose the largest number from these numbers.
     * The resulting number is going to be your salary, so you are very motivated to solve this problem!
     *
     * Input format.
     * The first line of the input contains an integer n.
     * The second line contains integers a1,...,an.
     * Output format. The largest number that can be composed out of a1,...,an.
     * Constraints.
     * 1 ≤ n ≤ 100;
     * 1 ≤ ai ≤ 103 for all 1 ≤ i ≤ n.
     *
     * Sample 1.
     * Input:
     *
     * 2
     *
     * 21 2
     *
     *
     * Output:
     *
     * 221
     * Note that in this case, the above algorithm also returns an incorrect answer 212.
     *
     * Sample 2.
     * Input:
     *
     * 5
     *
     * 9 4 6 1 9
     *
     * Output:
     * 99641
     * The input consists of single-digit numbers only, so the algorithm above returns the correct answer.
     *
     * Sample 3.
     * Input:
     *
     * 3
     *
     * 23 39 92
     *
     * Output:
     * 923923
     * The (incorrect) LargestNumber algorithm nevertheless produces the correct answer in this case,
     * another reminder to always prove the correctness of your greedy algorithms!
     *
     * Understanding the problem:
     *
     * Arrange the given piece of numbers together in such a way that
     * the final result gives the largest number when we combine (concatenate) the given numbers.
     *
     * At first, we might think that we just need to add each integer to a list and
     * then sort the list by descending order (highest to lowest number).
     *
     * But let us take an example of 3 integers: 6, 61, 68.
     * If we sort it by descending order, it becomes 68, 61, 6. (68616).
     * However, the largest number when we combine (concatenate) those integers is: 68, 6, 61. (68661).
     * It means we are missing something in our thought process.
     *
     * The key-lemma here is: A custom comparator for sorting.
     *
     * The correct approach involves a clever comparison technique:
     *
     * __Convert to Strings:__ Convert each integer into a string.
     *
     * __Custom Comparison:__ Compare two strings not just by their lexicographic order (dictionary order),
     * but also by the result of concatenating them in both possible orders.
     *
     * __Descending Sorting:__ Sort the strings in descending order based on this custom comparison.
     *
     * __Concatenation:__ Concatenate the sorted strings to get the largest possible number.
     *
     * __What is Lexicographic Comparison?__
     *
     * Lexicographic comparison, also known as dictionary order or alphabetical order,
     * is a way to compare sequences of elements (like letters in words or digits in numbers).
     * The comparison is done element-by-element, from left to right:
     *
     * Example: Words
     *
     * Let's compare the words "apple" and "banana" lexicographically:
     *
     * First letters: 'a' vs. 'b' -> 'a' is smaller, so "apple" comes before "banana".
     *
     * Example: Numbers
     *
     * Comparing "21" and "2":
     *
     * Concatenations: "212" vs. "221"
     * Comparison: "221" is lexicographically larger, so "2" should come before "21" in the final number.
     * Finalize the larger concatenation result: "221"
     *
     */
    fun largestConcatenatedNumber(listOfIntegers: List<String>): String {
        // Sort the result of two strings (concatenation) in descending order
        val sortedIntegers = listOfIntegers.sortedWith { a, b ->
            // Descending order
            (b + a).compareTo(a + b)
        }
        // After sorting, the list sortedIntegers will have the largest concatenated results at the front (beginning).
        // By checking if (sortedIntegers[0] == "0"), we determine if the first (and thus the largest) element is "0".
        // If it is, then all elements in the list must be zeros, and we return "0" instead of "000000".
        // Otherwise, we concatenate all the elements in sortedIntegers using joinToString("").
        val largestNumber = if (sortedIntegers[0] == "0") "0" else sortedIntegers.joinToString("")
        return largestNumber
    }

    val totalIntegers = readln().toInt()
    val listOfIntegers = readln().split(" ") //Converted a list of integers into a list of strings
    println(largestConcatenatedNumber(listOfIntegers))
}