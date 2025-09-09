package courses.uc.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem Statement:
 *
 * ## Problem Introduction:
 *
 * * In this problem, you will implement a program to simulate the processing of network packets.
 *
 * ## Problem Description:
 *
 * * **Task:**
 *
 * * You are given a series of incoming network packets, and your task is to simulate their processing.
 * * Packets arrive in some order.
 * * For each packet number `𝑖`, you know the time when it arrived `𝐴𝑖`,
 * and the time it takes the processor to process it `𝑃𝑖` (both in milliseconds).
 * * There is only one processor, and it processes the incoming packets in the order of their arrival.
 * * If the processor starts to process some packet, it doesn’t interrupt or stop until it finishes the processing
 * of this packet, and the processing of packet `𝑖` takes exactly `𝑃𝑖` milliseconds.
 * * The computer processing the packets has a network buffer of fixed size `𝑆`.
 * * When packets arrive, they are stored in the buffer before being processed.
 * * However, if the buffer is full when a packet arrives (there are `𝑆` packets which have arrived before this packet,
 * and the computer hasn’t finished processing any of them), it is dropped and won’t be processed at all.
 * * If several packets arrive at the same time, they are first all stored in the buffer
 * (some of them may be dropped because of that — those which are described later in the input).
 * * The computer processes the packets in the order of their arrival, and it starts processing the next available
 * packet from the buffer as soon as it finishes processing the previous one.
 * * If at some point the computer is not busy, and there are no packets in the buffer, the computer just waits for the
 * next packet to arrive.
 * * Note that a packet leaves the buffer and frees the space in the buffer as soon as the computer finishes processing
 * it.
 *
 * * **Input Format:**
 *
 * * The first line of the input contains the size `𝑆` of the buffer and the number `𝑛` of incoming
 * network packets.
 * * Each of the next `𝑛` lines contains two numbers.
 * * `𝑖-th` line contains the time of arrival `𝐴𝑖` and the processing time `𝑃𝑖` (both in milliseconds) of the `𝑖-th` packet.
 * * It is guaranteed that the sequence of arrival times is non-decreasing (however, it can contain the exact same times
 * of arrival in milliseconds — in this case, the packet that is earlier in the input is considered to have arrived
 * earlier).
 *
 * * **Constraints:**
 *
 * * All the numbers in the input are integers.
 * ```
 * 1 ≤ 𝑆 ≤ 10^5;
 * 0 ≤ 𝑛 ≤ 10^5;
 * 0 ≤ 𝐴𝑖 ≤ 10^6;
 * 0 ≤ 𝑃𝑖 ≤ 10^3;
 * 𝐴𝑖 ≤ 𝐴𝑖 + 1 for 1 ≤ 𝑖 ≤ 𝑛 − 1.
 * ```
 *
 * * **Output Format:**
 *
 * * For each packet output either the moment of time (in milliseconds) when the processor
 * began processing it or −1 if the packet was dropped (output the answers for the packets in the same
 * order as the packets are given in the input).
 * * Output an empty string if there was no packet.
 *
 * * **Sample 1:**
 *
 * * Input:
 * ```
 * 1 0
 * ```
 * * Output:
 * ```
 * ```
 * * Explanation:
 *
 * * If there are no packets, you shouldn’t output anything.
 *
 * * **Sample 2:**
 *
 * * Input:
 * ```
 * 1 1
 * 0 0
 * ```
 * * Output:
 * ```
 * 0
 * ```
 * * Explanation:
 *
 * * The only packet arrived at time 0, and computer started processing it immediately.
 *
 * * **Sample 3:**
 *
 * * Input:
 * ```
 * 1 2
 * 0 1
 * 0 1
 * ```
 *
 * * Output:
 * ```
 * 0
 * -1
 * ```
 *
 * * Explanation:
 *
 * * The first packet arrived at time 0, the second packet also arrived at time 0, but was dropped, because
 * the network buffer has size 1, and it was full, with the first packet already.
 * * The first packet started processing at time 0, and the second packet was not processed at all.
 *
 * * **Sample 4:**
 *
 * * Input:
 * ```
 * 1 2
 * 0 1
 * 1 1
 * ```
 *
 * * Output:
 * ```
 * 0
 * 1
 * ```
 *
 * * Explanation:
 *
 * * The first packet arrived at time 0, the computer started processing it immediately, and finished at time 1.
 * * The second packet arrived at time 1, and the computer started processing it immediately.
 *
 * ## Thought Process:
 *
 * ### Given Data
 *
 * * Buffer size `S`, Total number of packets `n`,
 * arrival time of each packet `Ai,` and process time of each packet `Pi`.
 *
 * ### Required Data
 *
 * * Start time of each packet
 *
 * ### Hints to find, build, and provide the required data from the given data
 *
 * * **When will the buffer start processing a packet? What will be the `process start` time of a packet?**
 *
 * * If there is no packet in the buffer, if the buffer is empty, then as soon as the packet arrives.
 * * Otherwise, as soon as the buffer finishes processing the previous packet.
 * * And if the packet arrives after some time, after the buffer finishes the previous packet,
 * where the arrival time is greater than the process finish time of the previous packet,
 * then the arrival time becomes the process start time.
 *
 * **Example-01**
 * * The buffer is empty.
 * * A packet arrives.
 * * The buffer will immediately start processing it as soon as it arrives.
 * * In this case, the process start time is the arrival time of the packet itself.
 *
 * **Example-02**
 * * The buffer finishes processing all the packets by `1 PM`.
 * * But, the new packet may not arrive immediately.
 * * So, the buffer waits for the new packet to arrive.
 * * The new packet arrives at `3 PM`.
 * * So, in this case, the maximum value is the arrival time.
 * * So, in this case, the arrival time is greater than the process finish time of the last packet.
 * * So, the process start time of this new packet will be its arrival time.
 *
 * **Example-03**
 * * The new packet arrives at `1 PM`.
 * * But, the last packet process finishes at `3 PM`.
 * * So, in this case, the maximum value is the process finish time of the last packet.
 * * So, the process start time of this new packet will be the process finish time of the last packet.
 *
 * **Conclusion**
 * > The `process start time` of any packet is =
 * > `if` (buffer is empty) arrival time of the packet
 * > `else` maxOf(process finish time of the last item, arrival time of this new packet)
 *
 * * **When will the buffer finish processing the previous packet?**
 *
 * * Based on the given `process time` of the packet, and the `process start` time of the packet.
 * * So,
 * > The `process finish time = process start time + process time`.
 *
 * ### Data Structure
 *
 * * It is given in the problem description that:
 * ```
 * There is only one processor, and it processes the incoming packets in the order of their arrival.
 * ```
 * * The above statement clearly indicates a queue data structure that follows
 * `Order of their arrival` = `first come, first served`, which is the `FIFO - First In First Out` principle.
 *
 * ### Process
 *
 * * **What do we add to the buffer queue? Arrival time, process time, or something else?**
 *
 * * We have the arrival time and the process time of an item (packet).
 * * And it is given that:
 * ```
 * Note that a packet leaves the buffer and frees the space in the buffer as soon as the computer finishes processing it.
 * ```
 * * So:
 * ```
 * We add `Process Finish Time` to the buffer queue so that we can remove the items whose processes have finished.
 * This will make room for the new items. (This will enable us to enqueue the new items to the buffer queue.)
 * ```
 * * **Why do we add `Process Finish Time` to the buffer queue?**
 *
 * * Because it helps us find and remove items that have finished their processes by the arrival time of the next item.
 *
 * * **How does the process finish time help us find and remove an item?**
 *
 * * Let us see the given **Sample 4:**
 *
 * * Input:
 * ```
 * // Buffer size is 1, and there will be 2 items (packets).
 * 1 2
 * // The first packet arrives at 0ms, process time takes 1 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Well, the buffer queue is empty. So, we don't need to remove any item.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // No, the buffer queue is not full.
 * // Ok. So, let us find and print the process start time of this item.
 * // The buffer queue was empty. So, this is the first and the last item.
 * // So, the process starts at the arrival time, which is 0 ms, and takes 1 ms process time.
 * // It means that if the buffer queue is empty, the process start time = arrival time.
 * // And the process finish time = process start time + process time = 0 + 1 = 1.
 * // Now, the Buffer is full. (Because the buffer size is 1.)
 * 0 1
 * // A new item arrives at 1 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Only those items that have finished their process by the arrival time of this new item.
 * // Has any item finished its process by the arrival time of this item, which is 1 ms?
 * // Yes. The first item.
 * // Notice that the process finish time of the first item is less than or equal to the arrival time of this item.
 * // So, we can remove the first item, because it has finished its process.
 * // So that we can make room for this new item in the buffer queue.
 * // So, first, we remove the items that have finished their process.
 * // So that we can make room for the new items.
 * // So, knowing the process finish time helps us find and remove the items that have finished their processes,
 * // and it enables us to enqueue the new items in the buffer queue.
 * // We removed the first item.
 * // We can ask again.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Well, the buffer queue is empty. So, we don't need to remove any item.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue this item.
 * // No, the buffer queue is not full.
 * // Ok. So, let us find and print the process start time of this item.
 * // The buffer queue was empty. So, this is the first and the last item.
 * // So, the process starts at the arrival time, which is 1 ms, and takes 1 ms process time.
 * // It means that if the buffer queue is empty, the process start time = arrival time.
 * // And, the process finish time = process start time + process time = 1 + 1 = 2.
 * // This is the first and the last item in the buffer queue at the moment.
 * // Now the buffer queue is full. (Because the buffer size is 1.)
 * 1 1
 * ```
 *
 * * Output:
 * ```
 * 0 // Process start time of the first item.
 * 1 // Process start time of the second item.
 * ```
 *
 * * **How can we know the process finish time of an item? How do we calculate it? What will be the code?**
 *
 * * Well, it is a simple math:
 * ```
 * Process finish time = Process start time + Process time (The time it takes to finish the process, Pi)
 * ```
 *
 * * **How do we find the `Process Start Time`?**
 *
 * * Let us look at the following example:
 *
 * * Input:
 * ```
 * // Buffer size is 1, and there will be 2 items (packets).
 * 3 3
 * // The first packet arrives at 0 ms, process time takes 5 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Well, the buffer queue is empty. So, we don't need to remove any item.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // No, the buffer queue is not full.
 * // Ok. So, let us find and print the process start time of this item.
 * // The buffer queue was empty. So, this is the first and the last item.
 * // So, the process starts at the arrival time, which is 0 ms, and takes 5 ms process time.
 * // It means that if the buffer queue is empty, the process start time = arrival time.
 * // And the process finish time = process start time + process time = 0 + 5 = 5.
 * // This is the first and the last item in the buffer queue at the moment.
 * 0 5
 * // A new item arrives at 1 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Only those items that have finished their process by the arrival time of this new item.
 * // Has any item finished its process by the arrival time of this item, which is 1 ms?
 * // No. The first item finishes the process at 5 ms.
 * // Notice that the process finish time of the first item is not less than or equal to the arrival time of this item.
 * // So, we cannot remove the first item, because it has not finished its process.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // No, the buffer queue is not full yet.
 * // Ok. So, let us find and print the process start time of this item.
 * // OK. Can we enqueue this item to the buffer queue?
 * // Well, the buffer queue is not full yet. So, we can enqueue this item.
 * // What will be the process start time of this item?
 * // Well, it is not an empty buffer queue. So, the process start time cannot be the arrival time of this item.
 * // And, we cannot start processing this item until and unless the previous item finishes its process.
 * // The finish time of the last item is 5 ms.
 * // So, the process start time of this item will be 5 ms, and it takes 5 ms process time.
 * // So, the process finish time = process start time + process time = 5 + 5 = 10.
 * // Notice that the process start time of this item is the process finish time of the last item (which is 5),
 * // which is maximum (greater) than the arrival time of this item (which is 1).
 * // This is the last item in the buffer queue at the moment.
 * 1 5
 * // A new item arrives at 12 ms.
 * // Can we remove any items from the buffer queue to make room for this new item?
 * // Only those items that have finished their process by the arrival time of this new item.
 * // Has any item finished the process by the arrival time of this item, which is 12 ms?
 * // Yes. The first item has finished the process at 5 ms.
 * // Notice that the process finish time of the first item is less than or equal to the arrival time of this item.
 * // So, we can remove the first item.
 * // Now, the second item that had arrived at 1 ms becomes the new first item in the buffer queue.
 * // We ask the same question again as long as we can remove all the items that have finished their processes.
 * // Can we remove any items from the buffer queue to make room for this new item?
 * // Only those items that have finished their process by the arrival time of this new item.
 * // Has any item finished the process by 12 ms?
 * // Yes. The first item has finished the process at 10 ms.
 * // Notice that the process finish time of the first item is less than or equal to the arrival time of this item.
 * // So, we can remove the first item.
 * // We ask the same question again as long as we can remove all the items that have finished their processes.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Well, the buffer queue is empty. So, we don't need to remove any item.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // No, the buffer queue is not full.
 * // Ok. So, let us find and print the process start time of this item.
 * // The buffer queue was empty. So, this is the first and the last item.
 * // So, the process starts at the arrival time, which is 12 ms, and takes 2 ms process time.
 * // And the process finish time = process start time + process time = 12 + 2 = 14.
 * // Notice that the process start time of this item is its own arrival time (which is 12),
 * // which is maximum (greater) than the process finish time of the last item (which was 10).
 * // Now, this is the first and the last item in the buffer queue at the moment.
 * 12 2
 * ```
 *
 * * Output:
 * ```
 * 0 // Process start time of the first item
 * 5 // Process start time of the second item
 * 12 // Process start time of the third item
 * ```
 *
 * * From the above example, we can conclude that:
 * ```
 * Process start time = if (bufferQueue.isEmpty()) arrival else (maxOf(bufferQueue.last(), arrival))
 * ```
 * And once we calculate and print the process start time, we can calculate the process finish time.
 * ```
 * Process finish time = Process start time (calculated above) + Process time (given)
 * ```
 * * We add this process finish time to the buffer queue, and now we know why we add the process finish time.
 * * It helps us find and remove items that have finished their processes by the arrival time of the next item.
 * * We have also seen and learned how to find and print the process start time.
 *
 * * Now, let us understand when we need to drop an item.
 * * Dropping an item means we don't enqueue and process it.
 * * It is given in the problem description that:
 *
 * ```
 * However, if the buffer is full when a packet arrives (there are `𝑆` packets which have arrived before this packet,
 * and the computer hasn’t finished processing any of them), it is dropped and won’t be processed at all.
 * ```
 *
 * * Let us understand it by re-visiting the sample 3:
 *
 * * **Sample 3:**
 *
 * * Input:
 * ```
 * // Buffer size is 1 and there are 2 packets.
 * 1 2
 * // The first item arrives at 0 ms, and process time takes 1 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Well, the buffer is already empty! So, we don't need to remove any item.
 * // Is the buffer full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // No. The buffer queue is not full.
 * // So, we can enqueue this item to the buffer and process it.
 * // Ok. So, let us find and print the process start time of this item.
 * // The buffer queue was empty. So, this is the first and the last item.
 * // So, the process starts at the arrival time, which is 0 ms, and takes 1 ms process time.
 * // It means that if the buffer queue is empty, the process start time = arrival time.
 * // And the process finish time of this item is: Process start time + process time = 0 +  1 = 1 ms.
 * // Now, the buffer is full. (Because the buffer size is 1.)
 * 0 1
 * // A new item arrives at 1 ms.
 * // Can we remove any item from the buffer queue to make room for this new item?
 * // Hmm. Maybe. The buffer has an item. Let us check if we can remove it. What items can we remove?
 * // Only those items that have finished their process by the arrival time of this new item.
 * // Has any item finished its process by the arrival time of this item, which is 0 ms?
 * // No. The first item finishes the process at 1 ms, and this item has arrived at 0 ms.
 * // The process finish time of the first item is not less than or equal to the arrival time of this item.
 * // So, we cannot remove the first item, because it has not finished its process.
 * // Is the buffer queue full?
 * // Because if it is full, we have to drop this item. We cannot enqueue and process this item.
 * // Yes. The buffer queue is full.
 * // Hence, we drop this item.
 * 0 1
 * ```
 *
 * * Output:
 * ```
 * 0
 * -1
 * ```
 *
 * * Explanation:
 *
 * * The first packet arrived at time 0, the second packet also arrived at time 0, but was dropped, because
 * the network buffer has size 1, and it was full, with the first packet already.
 * * The first packet started processing at time 0, and the second packet was not processed at all.
 *
 * * **Conclusion:**
 *
 * ```
 * We need to drop the packet if it arrives when the buffer is full and its arrival time is before the system finishes
 * processing the earlier packet.
 * ```
 * * **What if the buffer is not full, and a new packet arrives before the system finishes processing the previous packets?**
 *
 * * For example:
 *
 * ```
 * 2 2 // The buffer size is 2, and there are 2 packets.
 * 0 5 // The first packet arrives at 0, and the system takes 5 ms to finish processing it.
 * 1 2 // Meanwhile, before the system finishes processing the previous packet, a second packet arrives.
 * ```
 *
 * * Well, in this case:
 * * The buffer was not full; the buffer still had capacity.
 * * So, regardless of arrival time, the second packet gets into the buffer.
 * * We don't drop it, because the buffer has the capacity to process it.
 *
 * * So, the conclusion is:
 * ```
 * We drop a packet only if the buffer is full, and the arrival time of the packet is before the system finishes
 * processing the first packet.
 * ```
 *
 * * **Do we need to sort the packets by their arrival time?**
 *
 * * No. The system (the buffer) processes the packets as per the given input order only.
 * * It is given that:
 * ```
 * It is guaranteed that the sequence of arrival times is non-decreasing (however, it can contain the exact same times
 * of arrival in milliseconds — in this case, the packet that is earlier in the input is considered to have arrived
 * earlier).
 * ```
 * * Hence, we consider the input to be an expected order only and don't change that order.
 *
 * * **Summary:**
 *
 * * Which data structure to use? Queue. Because it is given that:
 * ```
 * It (the buffer) processes the incoming packets in the order of their arrival
 * = Order of their arrival
 * = First come, first served
 * = First-In-First-Out
 * = Queue
 * ```
 * * What do we add to the queue?
 * ```
 * Process finish time.
 * = So that we can remove the items that have finished their process by the arrival time of the next item.
 * ```
 * * How do we calculate the process finish time?
 * ```
 * Process finish time = Process start time + Process time
 * ```
 * * How do we calculate the process start time?
 * ```
 * Process start time = if (bufferQueue.isEmpty()) arrival else maxOf(bufferQueue.last(), arrival)
 * ```
 * * When do we drop the item?
 * ```
 * If we cannot remove any item, and the queue is full.
 * ```
 * * What is the pseudocode for removing an item?
 * ```
 * while (bufferQueue.isNotEmpty() && bufferQueue.first() <= arrival) {
 *     bufferQueue.removeFirst()
 * }
 * ```
 *
 * ## Time Complexity:
 *
 * * We have to iterate `n` times, where `n` is the total number of items (packets).
 * * So, it is `O(n)`.
 *
 * ## Space Complexity:
 *
 * * We take a queue whose size is `S` to enqueue items, where `S` is the given buffer size.
 * * And we print the process start time of each item, which is again `n`.
 * * So, it becomes `O(S + n)`.
 * * But, in Big-O complexity analysis, we drop the space that is not part of the computation.
 * * Because printing the output can be done without storing it anywhere.
 * * So, the space complexity is `O(S)`, where `S` is the buffer queue size.
 *
 * ## ----------------------- Coursera's Grader Output -----------------------
 * ```
 * Good job! (Max time used: 0.59/4.00, max memory used: 96755712/536870912.)
 * ```
 */
fun main() {
    val (bufferSize, totalItems) = readln().split(" ").map { it.toInt() }
    val bufferQueue = ArrayDeque<Long>()
    val stringBuilder = StringBuilder()
    repeat(totalItems) {
        val (arrival, processTime) = readln().split(" ").map { it.toLong() }
        // First, make room for the new item by removing the items that have finished their process
        while (bufferQueue.isNotEmpty() && bufferQueue.first() <= arrival) {
            bufferQueue.removeFirst()
        }
        // If the bufferQueue is full, we have to drop the item.
        if (bufferQueue.size == bufferSize) {
            stringBuilder.append("-1\n")
        } else {
            // Otherwise, enqueue the item. And print its process start time.
            // The items in the bufferQueue are of type Long. That's why we had to convert the input type to Long.
            // So that we can use the maxOf function here. It accepts the same types.
            val processStartTime = if (bufferQueue.isEmpty()) arrival else maxOf(bufferQueue.last(), arrival)
            stringBuilder.append("$processStartTime\n")
            // This addition can cause integer overflow. That's why we used a Long.
            val processFinishTime = processStartTime + processTime
            bufferQueue.addLast(processFinishTime)
        }
    }
    println(stringBuilder)
}