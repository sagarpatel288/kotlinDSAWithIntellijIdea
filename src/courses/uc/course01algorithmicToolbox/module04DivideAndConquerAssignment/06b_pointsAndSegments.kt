package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

/**
 * If you get this error:
 * `error: the feature "data objects" is only available since language version 1.9`
 * Then, you can either increase the compiler language version or remove the `data` keyword from the `data object`.
 *
 * ----------------------- Grader Output -----------------------
 *
 * (Max time used: 1.10/8.00, max memory used: 109531136/536870912.)
 */
sealed class EventType(val order: Int) {
    data object START: EventType(+1)
    data object QUERY: EventType(0)
    data object END: EventType(-1)
}

fun main() {

    data class Event(val point: Int, val type: EventType, val index: Int = - 1)

    fun countSegments(segments: List<Pair<Int, Int>>, queryPoints: List<Int>): List<Int> {
        val result = IntArray(queryPoints.size)
        val events = mutableListOf<Event>()
        for (segment in segments) {
            events.add(Event(segment.first, EventType.START))
            events.add(Event(segment.second, EventType.END))
        }
        for ((index, queryPoint) in queryPoints.withIndex()) {
            events.add(Event(queryPoint, EventType.QUERY, index))
        }
        events.sortWith(compareBy({ it.point }, { -it.type.order }))
        var activeSegments = 0
        for (event in events) {
            when (event.type) {
                EventType.START -> {
                    activeSegments++
                }
                EventType.END -> {
                    activeSegments--
                }
                EventType.QUERY -> {
                    result[event.index] = activeSegments
                }
            }
        }
        return result.toList()
    }

    val totalSegmentsAndPoints = readln().split(" ").map { it.toInt() }
    val totalSegments = totalSegmentsAndPoints[0]
    val segments = mutableListOf<Pair<Int, Int>>()
    for (i in 1..totalSegments) {
        val segment = readln().split(" ").map { it.toInt() }
        segments.add(segment[0] to segment[1])
    }
    val queryPoints = readln().split(" ").map { it.toInt() }
    val result = countSegments(segments, queryPoints)
    println(result.joinToString(" "))
}