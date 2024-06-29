package level30Module3AlgorithmExercise

/**
 * The name of the problem can be: maximumShopping as well - just to be positive.
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
        val pricePerUnit: Double = (cost.toDouble() / totalAvailableWeight.toDouble())
    }

    /**
     * Sort the [listOfItems] by maximum price per unit.
     * Fill the backpack starting with the maximum price per unit until we reach the [backpackCapacity].
     */
    fun getMaximumLootValue(backpackCapacity: Int, listOfItems: List<Item>): Double {
        // Key Point: New variable to store the Sort by price per unit by descending order!
        val sortedItems = listOfItems.sortedByDescending {
            it.pricePerUnit
        }
        var totalValue = 0.0
        var availableCapacity = backpackCapacity
        for (i in sortedItems) {
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
    // 2. A data class to add to a collection so that we can sort it by the price per unit.
    for (i in 1..totalItemsAndBackpackCapacity[0]) {
        val totalCostAndTotalAvailableUnits = readln().split(" ").map {
            it.toInt()
        }
        listOfItems.add(Item(totalCostAndTotalAvailableUnits[0], totalCostAndTotalAvailableUnits[1]))
    }

    // Key point: "%.4f".format
    println("%.4f".format(getMaximumLootValue(totalItemsAndBackpackCapacity[1], listOfItems)))
}