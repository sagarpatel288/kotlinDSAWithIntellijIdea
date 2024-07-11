package level30Module3AlgorithmExercise

fun main() {

    /**
     * We can get the maximum dot product (multiplication) of two sequences, [prices] and [clicks]
     * when we sort both the sequences, [prices] and [clicks] by descending order in such a way that
     * for both the sequences, the first element gets the highest value and the last element gets the lowest value.
     * For example, if we have the sequence [prices] as [9, 6, 8] and [clicks] as [3, 4, 5], then we first
     * sort both of them in the descending order where the [prices] becomes [9, 8, 6] and the [clicks] becomes [5, 4, 3].
     * Once we do that, the dot product (multiplication) of each element from each sequence gives the highest value.
     * For our example, (9 * 5) + (8 * 4) + (6 * 3) that is the highest possible dot product.
     */
    fun maxProduct(prices: List<Long>, clicks: List<Long>): Long {
        val sortedPrices = prices.sortedDescending()
        val sortedClicks = clicks.sortedDescending()
        var maxProduct = 0L
        for (i in sortedPrices.indices) {
            maxProduct += (sortedPrices[i] * sortedClicks[i])
        }
        return maxProduct
    }

    val total = readln().toInt()
    val prices = readln().split(" ").map {
        it.toLong()
    }
    val clicks = readln().split(" ").map {
        it.toLong()
    }
    println(maxProduct(prices, clicks))
}