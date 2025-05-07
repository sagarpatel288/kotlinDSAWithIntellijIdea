package coursera.ucSanDiego.algorithmicToolbox.module03GreedyAlgorithms

fun main() {

    /**
     *
     * Problem Statement:
     *
     * Car Fueling Problem:
     * Compute the minimum number of gas tank refills to get from one city to another.
     *
     * Input:
     *
     * Integers d and m, as well as a sequence of integers stop1 < stop2 < ··· < stopn.
     *
     * Output:
     *
     * The minimum number of refills to get from one city to another if a car can travel at most m miles on a full tank.
     * The distance between the cities is d miles and there are gas stations at distances stop1, stop2,...,stopn
     * along the way. We assume that a car starts with a full tank.
     *
     * Input format.
     *
     * The first line contains an integer d.
     * The second line contains an integer m.
     * The third line specifies an integer n.
     * Finally, the last line contains integers stop1, stop2,..., stopn.
     *
     * Output format.
     *
     * The minimum number of refills needed. If it is not possi- ble to reach the destination, output −1.
     *
     * Constraints:
     *
     * 1 ≤ d ≤ 10^5.
     * 1 ≤ m ≤ 400.
     * 1 ≤ n ≤ 300.
     * 0 < stop1 < stop2 < ···< stopn < d.
     *
     * Sample 1.
     *
     * Input:
     *
     * 950 = d = distance between the start point and the end point
     * 400 = m = mileage of the car
     * 4 = n = Total number of gas stations in between
     * 200 375 550 750 = Number of gas stations at distance from the start point
     *
     * Output:
     *
     * 2
     *
     * The distance between the cities is 950,
     * the car can travel at most 400 miles on a full tank.
     * It suffices to make two refills: at distance 375 and 750.
     * This is the minimum number of refills as with a single refill one would only be able to travel at most 800 miles.
     *
     * Sample 2.
     *
     * Input:
     *
     * 10
     *
     * 3
     *
     * 4
     *
     * 1 2 5 9
     *
     *
     * Output:
     *
     * -1
     *
     * Because, One cannot reach the gas station at point 9 as the previous gas station is too far away.
     *
     * Sample 3.
     *
     * Input:
     *
     * 200
     *
     * 250
     *
     * 2
     *
     * 100 150
     *
     * Output:
     *
     * 0
     *
     * There is no need to refill the tank as the car starts with a full tank and
     * can travel for 250 miles whereas the distance to the destination is 200 miles.
     *
     * ------------------Explanation-------------------
     *
     * Visual references:
     *
     * [Visual reference](https://discrete-math-puzzles.github.io/puzzles/car-fueling/)
     *
     * 1. We have total distance to travel, d.
     * 2. We know the mileage of the car, m.
     * 3. The car starts with the full tank.
     * 4. We know the total number of gas stations in the journey.
     * 5. We can calculate the fuel as we travel.
     * 5. We can calculate the distance we need to travel to reach the next gas station to refill our fuel.
     * 6. So, when we arrive at a particular gas station, we calculate how much distance we have already traveled,
     * so that we can determine how much fuel is left, and then assess if the remaining fuel is sufficient
     * to reach the next gas station.
     * 7. If it is sufficient, we refill the fuel to the next gas station. Otherwise, we refill the fuel at the
     * current gas station.
     *
     *
     * | Iteration 	| Current Position 	| Current Fuel 	| Next Stop 	| Distance to Next Stop 	| Can Reach Next + 1 Stop? 	| Decision 	|
     * |-----------	|------------------	|--------------	|-----------	|-----------------------	|--------------------------	|----------	|
     * | 0         	| 0                	| 400          	| 200       	| 200                   	| 375                      	| True     	|
     * | 1         	| 200              	| 200          	| 375       	| 175                   	| 350                      	| True     	|
     * | 2         	| 375              	| 25           	| 550       	| 175                   	| 200                      	| False    	|
     * | 3         	| 550              	| 400          	| 750       	| 200                   	| 400                      	| True     	|
     * | 4         	| 750              	| 200          	| 950       	| 200                   	| -                        	| -        	|
     * | 5         	| 950              	| 0            	| -         	| -                     	| -                        	| -        	|
     *
     */
    fun getMinimumRefills(totalDistance: Int, fullTankFuel: Int, stops: List<Int>): Int {
        // It is important to understand that
        // stops do not include the starting point (0) and the last destination (totalDistanceToTravel)
        // E.g., if the `totalDistanceToTravel` is 500, the `stops` values do not include it.
        // So, we might receive the stops as follows: 100, 150, 250, 300, and 400.
        // See, it does not include the destination at `500` km.
        // Hence, adding the destination as the last stop.
        val stopsIncludingDestination = stops + totalDistance
        // It is given that when we start our travelling, the fuel tank is full.
        var currentFuel = fullTankFuel
        // This is important to understand that we start travelling from 0
        var currentPosition = 0
        // When we start travelling from 0, the initial value of the numberOfRefills is 0.
        var numberOfRefills = 0

        // Each index represents a stop at a particular distance.
        // The value at each index is distance.
        for (i in stopsIncludingDestination.indices) {
            // First, we check whether we can reach to the next stop with the current fuel or not.
            val nextStop = stopsIncludingDestination[i]
            val distanceToTravel = nextStop - currentPosition
            // If required, refill fuel to reach to the next stop.
            if (distanceToTravel > currentFuel) {
                currentFuel = fullTankFuel
                numberOfRefills++
            }
            // If we find that, even with the full tank, we cannot reach to the next stop,
            // return -1 to indicate that it is not possible to reach to the next stop.
            if (distanceToTravel > currentFuel) {
                return -1
            }
            // As we travel, the fuel is reduced.
            // The deduction in fuel is directly proportional to the distance we travel.
            currentFuel -= distanceToTravel
            // Once we reach to the next stop, the stop becomes our current position.
            currentPosition = nextStop
        }
        return numberOfRefills
    }

    val totalDistanceToTravel = readln().toInt()
    val mileage = readln().toInt()
    val numberOfStops = readln().toInt()
    val stops = readln().split(" ").map {
        it.toInt()
    }

    println(getMinimumRefills(totalDistanceToTravel, mileage, stops))

}