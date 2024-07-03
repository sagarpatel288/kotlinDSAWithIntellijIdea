package level30Module3AlgorithmExercise

fun main() {

    /**
     *
     * | Iteration 	| Current Position 	| Current Fuel 	| Next Stop 	| Distance to Next Stop 	| Can Reach Next Stop? 	| Decision     	|
     * |-----------	|------------------	|--------------	|-----------	|-----------------------	|----------------------	|--------------	|
     * | 0         	| 0                	| 400          	| 200       	| 200                   	| TRUE                 	| Don't refill 	|
     * | 1         	| 200              	| 200          	| 375       	| 175                   	| TRUE                 	| Don't refill 	|
     * | 2         	| 375              	| 25           	| 550       	| 175                   	| FALSE                	| Refill       	|
     * | 3         	| 550              	| 400          	| 750       	| 200                   	| TRUE                 	| Don't refill 	|
     * | 4         	| 750              	| 200          	| 950       	| 200                   	| TRUE                 	| Don't refill 	|
     *
     */
    fun getMinimumRefills(totalDistance: Int, fullTankFuel: Int, stops: List<Int>): Int {
        val stopsIncludingDestination = stops + totalDistance
        var currentFuel = fullTankFuel
        var currentPosition = 0
        var numberOfRefills = 0

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