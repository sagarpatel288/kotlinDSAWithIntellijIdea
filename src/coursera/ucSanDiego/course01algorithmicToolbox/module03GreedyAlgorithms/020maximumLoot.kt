package coursera.ucSanDiego.course01algorithmicToolbox.module03GreedyAlgorithms

/**
 * Problem statement-------------------
 *
 * Maximizing the Value of the Loot Problem:
 *
 * Find the maximal value of items that fit into the backpack.
 *
 * Input:
 *
 * The capacity of a backpack W as well as the weights (w1,...,wn) and costs (c1,...,cn) of n different compounds.
 *
 * Capacity of the back-pack: W
 *
 * Weight of a compound: w
 *
 * Cost of the compound: c
 *
 * Compound = Material, Item, Things, etc.
 *
 *
 * Output:
 *
 * The maximum total value of fractions of items that fit into the backpack of the given capacity:
 * i.e., the maximum value of c1 ·f1 +···+cn ·fn such that
 * w1·f1+···+wn·fn ≤ W and 0 ≤ fi ≤ 1 for all i (fi is the fraction of the i-th item taken to the backpack).
 *
 * Fraction = Unit
 * c * f = Cost of the compound * Fraction (Unit. E.g., gram, litter, pieces, etc.) of the compound
 * w * f = Weight of the compound * Fraction of the compound
 *
 * A thief breaks into a spice shop and finds four pounds of saffron,
 * three pounds of vanilla, and five pounds of cinnamon.
 * His backpack fits at most nine pounds, therefore he cannot take everything.
 * Assuming that the prices of saffron, vanilla, and cinnamon are $5000, $200, and $10, respectively,
 * what is the most valuable loot in this case?
 *
 * If the thief takes u1 pounds of saffron, u2 pounds of vanilla, and u3 pounds of cinnamon,
 * the total value of the loot is:
 *
 * (5000 · u1/4) + (200 · u2/3) + (10 · u3/5).
 *
 * The thief would like to maximize the value of this expression subject to the following constraints:
 *
 * u1 ≤ 4, u2 ≤ 3, u3 ≤ 5, u1 + u2 + u3 ≤ 9.
 *
 * I.e., The thief cannot take any compound beyond the availability limit of the compound.
 * I.e.,  u1/4 indicates that only 4 units (It can be mg, g, kg, pound, etc.) of Saffron is available.
 * Hence, the thief cannot loot more than 4 units of the saffron.
 *
 * Input format.
 *
 * The first line of the input contains the number n of compounds and the capacity W of a backpack.
 * The next n lines define the costs and weights of the compounds.
 * The i-th line contains the cost ci and the weight wi of the i-th compound.
 *
 * Output format.
 *
 * Output the maximum value of compounds that fit into the backpack.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 10^3,
 * 0 ≤ W ≤2·10^6;
 * 0 ≤ ci ≤2·10^6,
 * 0 < wi ≤2·10^6
 *
 * for all 1 ≤ i ≤ n. All the numbers are integers.
 *
 * Bells and whistles.
 *
 * Although the Input to this problem consists of integers, the Output may be non-integer.
 * Therefore, the absolute value of the difference between the answer of your program and the optimal value
 * should be at most 10 to the power of 3.
 * To ensure this, output your answer with at least four digits after the decimal point
 * (otherwise your answer, while being computed correctly, can turn out to be wrong because of the rounding issues).
 *
 * Sample 1.
 *
 * Input:
 *
 * 3 50
 * 60 20
 * 100 50
 * 120 30
 *
 * Output:
 *
 * 180.0000
 *
 * To achieve the value 180, the thief takes the entire first compound and the entire third compound.
 *
 * Sample 2.
 *
 * Input:
 *
 * 1 10
 * 500 30
 *
 * Output:
 * 166.6667
 *
 * The thief should take ten pounds of the only available compound.
 *
 *
 * -----------------------Explanation-----------------------
 *
 * The name of the problem can be maximumShopping as well - just to be positive.
 * So, here is the thought process.
 * The problem statement gives the following data:
 * Total number of items, maximum capacity of a backpack, and
 * Total cost of each Item along with available units.
 * The problem statement or core requirement is to fill a backpack with the items
 * in such a way that the total cost becomes maximum.
 * So:
 * 1. We need to take the maximum units of the costlier Item first. It will be a descending order.
 * So, the costlier Item will go first until the backpack is full (reaches the maximum capacity)
 * or the Item runs out of stock. Then, the less costly Item will go. The last Item will be the cheapest one.
 * 2. So, we got a certain order (descending order where the costlier Item goes first and the cheapest Item goes last)
 * to fill the backpack.
 * 3. How do we find the costlier Item? We need to calculate the `price per unit`.
 * The order is sorted by the descending order of the `price per unit.`
 * 4. How do we calculate the `price per unit`? It's a simple math. Cost / Weight.
 * 5. Here is the catch. We need to make sure that the `price per unit` is of type `Double`.
 * For example, if the cost is `500`, the weight is `30`, and we take the `price per unit` as `Int`, then we get
 * `price per unit = 16`. It means that if we do `price per unit` multiplied by `weight`,
 * it should give us the total cost, which is `500`. But it gives `16 * 30 = 480`!
 * So, keep in mind that the `price per unit` will be of type `Double`.
 * 6. Ok, so we have `price per unit`, and we need to sort the given items
 * by the descending order of the `price per unit`.
 * This means that we will save (store) the given items into a collection so that we can sort them.
 * 7. To sort the collection of items by the descending order of the `price per unit`,
 * the element type of the collection must have the property `price per unit.`
 * It means that we need to create a custom data class where we can have this `price per unit` property.
 * 8. The data class will have the following properties:
 * Total cost of the Item, available units, and price per unit.
 * We get the first two properties from the user input. So, they will be the constructor properties.
 * We will use those properties to create the additional property `price per unit.`
 * 9. So, we get user input for each Item; we store it in a collection where the element data type will be a custom
 * one (e.g., Item), and the item data class will have a computed property called `price per unit` using which we will
 * sort the collection by descending order (highest `price per unit` to lowest `price per unit`)
 * once we have all the items.
 */
fun main() {

    /**
     * Key Point: A data class that we can add to a list and then sort the list by [pricePerUnit]
     */
    data class Item(val cost: Int, val totalAvailableWeight: Int) {
        // Key point: If we take `Int` instead of `Double`, then we get wrong `pricePerUnit`!
        // E.g., cost = 500, totalWeight = 30,
        // If the `pricePerUnit` is `Int`, then it becomes `16`
        // and if we do `16` * `30`, it gives us `480` instead of `500`!
        // Key point:
        // Also, cost/weight gives dollar per pound or dollar per kilogram, etc. - How much does it cost per pound?
        // Whereas, weight/cost gives pound per dollar, kilogram per dollar, etc. - How much weight do we get per dollar?
        val pricePerUnit: Double = (cost.toDouble() / totalAvailableWeight.toDouble())
    }

    /**
     * Sort the [listOfItems] by maximum price per unit.
     * Fill the backpack starting with the maximum price per unit until we reach the [backpackCapacity].
     */
    fun getMaximumLootValue(backpackCapacity: Int, listOfItems: List<Item>): Double {
        // Key Point: New variable to store the "Sort by price per unit by descending order!"
        val sortedItems = listOfItems.sortedByDescending {
            it.pricePerUnit
        }
        var totalValue = 0.0
        var availableCapacity = backpackCapacity
        for (i in sortedItems) {
            // key point:
            // We need to keep this in mind that as we fill the backpack,
            // the available capacity of the backpack is reduced by the units we have taken.
            // And once the available capacity becomes 0, we need to break the loop and return.
            if (availableCapacity == 0) break
            // Key-lemma: minOf
            // We take whatever the minimum out of these two: item.totalAvailableWeight, availableCapacity.
            // Because, we cannot take more items or units if there is not enough availableCapacity.
            // Similarly, we cannot take more than available units of the item!
            val unitsToTake = minOf(i.totalAvailableWeight, availableCapacity)
            totalValue += unitsToTake.toDouble() * (i.pricePerUnit)
            availableCapacity -= unitsToTake
        }
        return totalValue
    }

    val totalItemsAndBackpackCapacity = readln().split(" ").map {
        it.toInt()
    }

    val listOfItems = mutableListOf<Item>()

    // Key Points:
    // 1. The for loop.
    // 2. The for loop starts with 1 instead of 0 because what we have is the total number of items, not the indices.
    // So, it is basically the size.
    // If we want to start it with 0, then we can give the end point in the traditional `size-1` way. It is also OK.
    // 3. A data class to add to a collection so that we can sort it by the price per unit.
    for (i in 1..totalItemsAndBackpackCapacity[0]) {
        val totalCostAndTotalAvailableUnits = readln().split(" ").map {
            it.toInt()
        }
        listOfItems.add(Item(totalCostAndTotalAvailableUnits[0], totalCostAndTotalAvailableUnits[1]))
    }

    // Key point: "%.4f".format => It means, 4 digits after the decimal points.
    println("%.4f".format(getMaximumLootValue(totalItemsAndBackpackCapacity[1], listOfItems)))
}