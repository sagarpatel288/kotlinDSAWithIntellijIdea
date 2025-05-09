package coursera.ucSanDiego.course01algorithmicToolbox.module03GreedyAlgorithms

fun main() {

    fun carFueling(totalDistance: Int, mileage: Int, stops: List<Int>): Int {
        var fuel = mileage
        var currentStop = 0
        var refill = 0
        val stopsWithDestination = stops + totalDistance
        for (stop in stopsWithDestination) {
            val distanceToTravel = stop - currentStop
            if (distanceToTravel > fuel) {
                fuel = mileage
                refill++
            }
            if (distanceToTravel > fuel) {
                return -1
            }
            fuel -= distanceToTravel
            currentStop = stop
        }
        return refill
    }

    val totalDistance = readln().toInt()
    val mileage = readln().toInt()
    val totalStops = readln().toInt()
    val stops = readln().split(" ").map { it.toInt() }
    println(carFueling(totalDistance, mileage, stops))
}