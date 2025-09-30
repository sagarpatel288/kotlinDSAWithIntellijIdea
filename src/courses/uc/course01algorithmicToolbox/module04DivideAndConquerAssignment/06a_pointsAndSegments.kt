package courses.uc.course01algorithmicToolbox.module04DivideAndConquerAssignment

/**
 * ----------------------- Points and Segments Problem: -----------------------
 *
 * Visual references:
 *
 * res/courses/uc/module04DivideAndConquerAssignment/06a_LotterySegment.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a882c2ce8c809ddefc054cbf8737cdcf6643695f/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/06a_LotterySegment.png)
 *
 * res/courses/uc/module04DivideAndConquerAssignment/06b_PointsAndSegments.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a882c2ce8c809ddefc054cbf8737cdcf6643695f/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/06b_PointsAndSegments.png)
 *
 * res/courses/uc/module04DivideAndConquerAssignment/06c_PointsAndSegmentEvents.png
 *
 * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a882c2ce8c809ddefc054cbf8737cdcf6643695f/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/06c_PointsAndSegmentEvents.png)
 *
 * Given a set of points and a set of segments on a line, compute, for each point, the number of segments
 * it is contained in.
 *
 * Input:
 *
 * A list of segments and a list of points.
 *
 * Output:
 *
 * The number of segments containing each point.
 *
 * You are organizing an online lottery. To participate, a person bets on a single integer.
 * You then draw several segments of consecutive integers at random.
 * A participant’s payoff is proportional to the number of segments that contain the participant’s number.
 * You need an efficient algorithm for computing the payoffs for all participants.
 * A simple scan of the list of all ranges for each participant is too slow since your lottery is very popular:
 * you have thousands of participants and thousands of ranges.
 *
 * Input format:
 *
 * The first line contains two non-negative integers n and m defining the number of segments
 * and the number of points on a line, respectively.
 * The next n lines contain two integers li,ri defining the i-th segment [li,ri].
 * The next line contains m integers defining points p1,...,pm.
 *
 * Output format:
 *
 * m non-negative integers k1,...,kp where ki is the number of segments that contain pi.
 *
 * Constraints:
 *
 * 1 ≤ n,m ≤ 50000;
 * −10^8 ≤ li ≤ ri ≤ 10^8 for all 1 ≤ i ≤ n;
 * −10^8 ≤ pj ≤ 10^8 for all 1 ≤ j ≤ m.
 *
 * Sample 1:
 *
 * Input:
 *
 * 2 3
 *
 * 0 5
 *
 * 7 10
 *
 * 1 6 11
 *
 * Output:
 *
 * 1 0 0
 *
 * We have two segments and three points.
 * The first point lies only in the first segment while the remaining two points are outside all the segments.
 *
 * Sample 2:
 *
 * Input:
 *
 * 1 3
 *
 * -10 10
 *
 * -100 100 0
 *
 * Output:
 *
 * 0 0 1
 *
 * Sample 3:
 *
 * Input:
 *
 * 3 2
 *
 * 0 5
 *
 * -3 2
 *
 * 7 10
 *
 * 1 6
 *
 * Output:
 *
 * 2 0
 *
 * ----------------------- Story Time -----------------------
 *
 * Imagine a restaurant that wants to analyze its busiest hours.
 * Every customer enters the restaurant at a specific time (arrival) and leaves at a specific time (departure).
 *
 * The restaurant has a few queries:
 * "How many customers are in the restaurant at a particular time?"
 *
 * You, as the manager, decide to solve this problem efficiently because counting manually for each query is tedious.
 *
 * Characters and Elements in the Story:
 *
 * Customers:
 *
 * Each customer enters the restaurant at a specific time and leaves after spending some time dining.
 * Customers are like the segments in the original problem.
 *
 * Queries:
 *
 * These represent the specific times when you want to know how many customers are currently inside the restaurant.
 * Queries are like the points in the original problem.
 *
 * Events:
 *
 * An event happens when:
 *
 * 1. A customer enters the restaurant (start of a segment).
 * 2. A customer leaves the restaurant (end of a segment).
 * 3. A query checks how many customers are present at a given time.
 *
 * Example:
 *
 * Input:
 *
 * Customers (Segments): (0, 5), (7, 10), (1, 6), (11, 15)
 *
 * Queries (Points): 1, 6, 11
 *
 * Events Timeline:
 *
 * Types: +1 for start (left), -1 for end (right), and 0 for query.
 *
 * The normal sorting of type: -1, 0, +1.
 * But, when we use -type (minus sign): +1, 0, -1.
 *
 * Sort with (compareBy ({it.time}, {-it.type}) ) :
 *
 * Sorted by time and -type. It means, if we have a segment (1, 4) and (4, 8) and a query-point 4, then, it will be:
 *
 * 1(+1) start-segment, 4(+1) start-segment, 4(0) query, 4(-1) end-segment, 8(-1) end-segment.
 *
 * So, at the time of query, we have 2 customers. This is important. If we want to prioritize it in a different way,
 * then we have to adjust the sortWith function call, especially the it.type part.
 *
 * For example, without minus sign, sortWith(compareBy({it.time}, {it.type}) ) would prioritize the type as:
 * -1, 0, +1. In that case, for the segments (1, 4) and (4, 8), and the query-point 4, the sorting will be:
 *
 * 1(+1) start-segment, 4(-1) end-segment, 4(0) query, 4(+1) start-segment, 8(-1) end-segment.
 *
 * And the output will be 0.
 *
 * So, with -it.type, the sorting of for our example will be:
 *
 * 0(+1), 1(+1), 1(0), 5(-1), 6(0), 6(-1), 7 (+1), 10(-1), 11(+1), 11(0), 15(-1).
 *
 *
 * | Time | Type            | Effect                            |
 * |------|-----------------|------------------------------------|
 * | 0    | Customer enters | `activeCustomers += 1` → `1`      |
 * | 1    | Customer enters | `activeCustomers += 1` → `2`      |
 * | 1    | Query           | Record `activeCustomers = 2`      |
 * | 5    | Customer leaves | `activeCustomers -= 1` → `1`      |
 * | 6    | Query           | Record `activeCustomers = 1`      |
 * | 6    | Customer leaves | `activeCustomers -= 1` → `0`      |
 * | 7    | Customer enters | `activeCustomers += 1` → `1`      |
 * | 10   | Customer leaves | `activeCustomers -= 1` → `0`      |
 * | 11   | Customer enters | `activeCustomers += 1` → `1`      |
 * | 11   | Query           | Record `activeCustomers = 1`      |
 * | 15   | Customer leaves | `activeCustomers -= 1` → `0`      |
 *
 * Output:
 *
 * [2, 1, 1]
 *
 * At time 1, there were 2 customers.
 *
 * At time 6, there was 1 customer.
 *
 * At time 11, there was 1 customer.
 *
 * ----------------------- Complexity Analysis -----------------------
 *
 * Time-Complexity:
 *
 * The major operations we do are:
 * 1. Iterating over the segments.
 * 2. Iterating over the query-points.
 * 3. Sorting the event-list, which is based upon the segments and the query-points.
 * 4. Iterating over the event list.
 *
 * The dominant factor here is, sorting the event-list.
 * As we have seen during the merge-sort complexity analysis, any comparison-based sorting algorithm take O(n log n)
 * time-complexity on an average, where n is the size of the input.
 * Here, we sort the event-list, which is based upon the size of the segments (n) + the size of the query-points (m).
 * Hence, the time-complexity becomes: O( (n + m) log (n + m) ).
 *
 * Space-complexity:
 *
 * The major variables we create:
 *
 * 1. The event data class.
 * 2. The event-list.
 * 3. The result list.
 *
 * The dominant factor here is, the event-list, which is based upon the segment size (n) + the query-points (m).
 * Hence, the space-complexity is O(n + m).
 *
 * ----------------------- Grader Output -----------------------
 * (Max time used: 1.10/8.00, max memory used: 104460288/536870912.)
 *
 * ----------------------- TL;DR: -----------------------
 * 1. Create the event data class and store: point, type, and index for the query-points. The default value of the
 * index for the start-segments and end-segments will be -1. We use this index to store the result count during the
 * iteration of the event-list.
 * 2. Sort the event-list as: sortWith(compareBy( {it.point}, {-it.type} )): The minus sign of -it.type is important
 * here.
 * 3. Iterate through the event-list. Increase or decrease the count based on the event-type. Use the count when the
 * element type is query during the iteration. Use the `eventElement.index` to assign the position and count as a value.
 */
fun main() {

    /**
     * Why?
     *
     * We have several segments and points.
     * Each segment has a start point and an end point.
     * We want to create a timeline on which we can set the segments and points.
     * So that, we can streamline everything and sort it in a unified way.
     *
     * How does that help?
     *
     * If everything is sorted, it helps to find out how many active segments (customers) are covered by each point
     * (at a particular time).
     *
     * How does that help? What is the plan? A complete picture.
     *
     * We have segments. Each segment has a start point and an end point. We have query points.
     * We use different types for the segment-start, segment-end, and the query points to distinguish, identify them.
     * They all are in numbers. We add these numbers into a list and then sort the list.
     * Then, we iterate through the list.
     * If we encounter a segment-start event, we increase the count of active segments.
     * If we encounter a segment-end event, we decrease the count to reflect the end of the segment.
     * There can be incidents where a new segment starts before the old segment finishes.
     * Our logic of increasing or decreasing the count of active segment can handle this because the list is sorted, and
     * we have types to identify whether it is a segment-start event or a segment-end event.
     * If we encounter a query-point, we have the count ready.
     *
     * To visualize the explanation, please check the problem-statement comments.
     * Specifically:
     *
     * res/courses/uc/module04DivideAndConquerAssignment/06c_PointsAndSegmentEvents.png
     *
     * [image](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/f6770717e97f32de4f9824eb236b71c58c4bada9/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/06c_PointsAndSegmentEvents.png)
     *
     */
    data class Event(val point: Int, val type: Int, val queryIndex: Int = -1)

    // Example input: Customers (arrivals and departures), Queries (times to check)
    fun countSegments(segments: List<Pair<Int, Int>>, queries: List<Int>): List<Int> {
        val events = mutableListOf<Event>()
        val results = IntArray(queries.size)

        // Add arrival and leaving events
        for ((start, end) in segments) {
            events.add(Event(start, +1)) // Customer enters
            events.add(Event(end, -1)) // Customer leaves
        }

        // Add query events (queryTime)
        for ((index, queryPoint) in queries.withIndex()) {
            events.add(Event(queryPoint, 0, index)) // Query at a specific time
        }

        // Sort events by time, breaking ties by type priority: +1, 0, and then -1.
        // The average time complexity of any sorting algorithm is O(n log n) as we have seen it during the merge-sort.
        events.sortWith(compareBy({ it.point }, { -it.type }))

        // Process events
        var activeCustomers = 0
        for (event in events) {
            when (event.type) {
                +1 -> activeCustomers++ // Customer enters
                -1 -> activeCustomers-- // Customer leaves
                0 -> results[event.queryIndex] = activeCustomers // Record query result
            }
        }

        return results.toList()
    }

    val segmentsAndQueries = readln().split(" ").map { it.toInt() }
    val totalSegments = segmentsAndQueries[0]
    val segments = mutableListOf<Pair<Int, Int>>()

    for (i in 1..totalSegments) {
        val segment = readln().split(" ").map {
            it.toInt()
        }
        segments.add(Pair(segment[0], segment[1]))
    }

    val queries = readln().split(" ").map { it.toInt() }

    val result = countSegments(segments, queries)
    println(result.joinToString(" "))
}