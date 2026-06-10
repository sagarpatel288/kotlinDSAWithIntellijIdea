package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

class SweepLinePointsAndSegments {

    // A sealed class instead of a sealed interface.
    // Because the children share the same property: `priority`.
    // And we don't want to implement it and repeat it for each child.
    // So, a shared property = a sealed class.
    sealed class EventType(val priority: Int) {
        // Total three priorities: Start (-1), Query (0), and End (+1).
        // Start gets the highest priority, followed by the query, followed by the End.
        data object START: EventType(-1)
        data object QUERY: EventType(0)
        data object END: EventType(+1)
    }

    data class Event(val point: Int, val type: EventType, val queryIndex: Int = -1): Comparable<Event> {
        override fun compareTo(other: Event): Int {
            // Sort primarily by point coordinate, and secondarily by event type priority.
            if (this.point != other.point) {
                return this.point.compareTo(other.point)
            }
            return this.type.priority.compareTo(other.type.priority)
        }
    }

    fun countSegments(segments: List<Pair<Int, Int>>, queries: List<Int>): IntArray {
        val events = mutableListOf<Event>()
        // Create events for the start and end of each segment.
        for (segment in segments) {
            events.add(Event(segment.first, EventType.START))
            events.add(Event(segment.second, EventType.END))
        }
        // Create events for each query point, tracking their original indices.
        for ((index, query) in queries.withIndex()) {
            events.add(Event(query, EventType.QUERY, index))
        }
        // Sort all events to process them in a sweep-line fashion.
        events.sort()
        var activeSegments = 0
        val result = IntArray(queries.size)
        // Sweep through the events, updating the count of active segments and recording
        //query results.
        for (event in events) {
            when (event.type) {
                EventType.START -> activeSegments++
                EventType.END -> activeSegments--
                EventType.QUERY -> {
                    result[event.queryIndex] = activeSegments
                }
            }
        }
        return result
    }
}

fun main() {
    val (totalSeg, totalQueries) = readln().split(" ").map { it.toInt() }
    val segments = mutableListOf<Pair<Int, Int>>()
    repeat(totalSeg) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        segments.add(Pair(start, end))
    }
    val queries = readln().split(" ").map { it.toInt() }
    val solver = SweepLinePointsAndSegments()
    val answer = solver.countSegments(segments, queries)
    println(answer.joinToString(" "))
}