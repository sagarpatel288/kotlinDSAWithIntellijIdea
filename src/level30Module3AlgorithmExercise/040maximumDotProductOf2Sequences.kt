package level30Module3AlgorithmExercise

fun main() {

    /**
     * Problem statement:
     *
     * Input: Two sequences of n positive integers: price1,..., priceN and clicks1,..., clicksN.
     *
     * Output:
     * The maximum value of price1 · c1 +···+ priceN · cN, where c1,...,cN is a permutation of clicks1,..., clicksN.
     *
     * You have n = 3 advertisement slots on your popular Internet page,
     * and you want to sell them to advertisers.
     * They expect, respectively, clicks1 = 10, clicks2 = 20, and clicks3 = 30 clicks per day.
     * You found three advertisers willing to pay price1 = $2, price2 = $3, and price3 = $5 per click.
     * How would you pair the slots and advertisers to maximize the revenue?
     *
     * | Clicks 	| Prices 	|
     * |--------	|--------	|
     * | 10     	| 2      	|
     * | 20     	| 3      	|
     * | 30     	| 5      	|
     *
     * [Image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/6a246554a9d668f3ff5711ab12e0af9d06bd0aab/res/level30Module3AlgorithmExercise/maxProduct.png)
     *
     * For example, the highlighted pairing in the image shown above gives revenue of 10·5 + 20·2 + 30·3 = 180 dollars,
     * while the black one results in revenue of 10 · 3 + 20 · 5 + 30 · 2 = 190 dollars.
     *
     * Input format.
     *
     * The first line contains an integer n, the second one contains a sequence of integers price1,...,priceN,
     * the third one contains a sequence of integers clicks1,..., clicksN.
     *
     * Output format.
     *
     * Output the maximum value of (price1 · c1 +···+ priceN · cn),
     * where c1,...,cn is a permutation of clicks1,..., clicksN.
     *
     * Constraints. 1 ≤ n ≤ 10^3;  0 ≤ pricei, clicksi ≤ 10^5 for all 1 ≤ i ≤ n.
     *
     * Sample 1.
     * Input:
     *
     * 1
     *
     * 23
     *
     * 39
     *
     * Output:
     *
     * 897
     *
     * 897 = 23·39.
     *
     * Sample 2.
     *
     * Input:
     *
     * 3
     *
     * 2  3  9
     *
     * 7  4  2
     *
     * Output:
     *
     * 79
     *
     * 79 = 7·9 + 2·2 + 3·4.
     *
     * -----------------------------Explanation-------------------
     *
     * If we observe the examples, we need to multiply maximum value of one group to the maximum value of another group,
     * in non-increasing manner.
     *
     * For example, if one group has: 4 and 7, and another group has 3 and 9,
     * we would do: (7 * 9) + (4 * 3) to get the maximum product.
     *
     * Another example: If one group has 3, 4, 5, and another group has 6, 7, 8,
     * then we would do: (5 * 8) + (4 * 7) + (3 * 6) to get the maximum product.
     *
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