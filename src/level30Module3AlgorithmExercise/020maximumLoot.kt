package level30Module3AlgorithmExercise

/**
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