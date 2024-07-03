package level30Module3AlgorithmExercise

fun main() {

    /**
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
    fun getMinimumRefills(totalDistanceToTravel: Int, tankCapacity: Int, stops: List<Int>): Int {
        // This is important to understand that we start travelling from 0
        var currentPosition = 0
        // It is given that when we start our travelling, the fuel tank is full.
        var currentFuel = tankCapacity
        // When we start travelling from 0, the initial value of the numberOfRefills is 0.
        var numberOfRefills = 0
        // It is important to understand that
        // stops do not include the starting point (0) and the last destination (totalDistanceToTravel)
        // E.g., if the `totalDistanceToTravel` is 500, the `stops` values do not include it.
        // Hence, adding the destination as the last stop.
        val stopsIncludingDestination = stops + totalDistanceToTravel

        // Each index represents a stop
        for (i in stopsIncludingDestination.indices) {
            // If the distance between the next stop and the current position is beyond the tank capacity,
            // we don't get the chance to refill the tank and hence, we return -1.
            val nextStop = stopsIncludingDestination[i]
            val distanceToNextStop = nextStop - currentPosition
            if (distanceToNextStop > tankCapacity) return -1
            // The rate of decreasing the fuel is directly proportional to the distance we travel.
            currentFuel -= distanceToNextStop
            // Once we reach to the next stop, the stop becomes our current position.
            currentPosition = nextStop

            // i = next stop from the current position.
            // i + 1 = two stops ahead from the current position.
            // If we're not at the last stop (size - 1),
            // AND we cannot reach the gas station after the next one (i + 1) with our current fuel,
            // THEN we need to refill at the current gas station (at the current position).
            if (i < stopsIncludingDestination.size - 1 && stopsIncludingDestination[i + 1] - currentPosition > currentFuel) {
                currentFuel = tankCapacity
                numberOfRefills++
            }
        }
        return numberOfRefills
    }

    val totalDistanceToTravel = readln().toInt()
    val tankCapacity = readln().toInt()
    val totalNumberOfStops = readln().toInt()
    // It is important to understand that
    // stops do not include the starting point (0) and the last destination (totalDistanceToTravel)
    // E.g., if the `totalDistanceToTravel` is 500, the `stops` values do not include it.
    val stops = readln().split(" ").map {
        it.toInt()
    }
    println(getMinimumRefills(totalDistanceToTravel, tankCapacity, stops))
}