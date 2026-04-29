# Problem: TL;DR: Overview

* 1 processor.
* S buffer size.
* N network packets.
* $A_t$ Arrival time.
* $P_t$ Processing time.
* Packets arrived when the buffer is full are dropped (-1).
* Print process start time for each packet.

**When do we process a packet?**

* If the buffer is empty, the arrival time is the process start time.
* Otherwise, the process finish time of the previous packet or the arrival time of this packet (as it can arrive late and make the processor wait).

```kotlin

val processStartTime = if (buffer.isEmpty()) arrivalTime else maxOf(processFinishTime, arrivalTime)

```

**How do we get the process finish time?**

```kotlin

val processFinishTime = processStartTime + processTime

```

* Looks marry-go-round?
* Because it seems that the `processStartTime` depends on the `processFinishTime` of the previous packet and `processFinishTime` of the previous packet depends on the `processStartTime` of the packet!
* That's when the base case comes to rescue!
* For the very first packet, the `arrivalTime` becomes the `processStartTime` and from this point, we start getting the `processFinishTime` of the previous and current packets.

**Buffer**

* The buffer is a queue that follows `FIFO-First-In-First-Out` order.

```kotlin

val buffer = ArrayDeque<Int>(size)

```

**What do we add in it?**

* We add `processFinishTime` to it.

```kotlin

val buffer = ArrayDeque<Int>(size)
val (arrivalTime, processTime) = readln().split(" ").map { it.toLong() }
val processStartTime = if (buffer.isEmpty()) arrivalTime else maxOf(buffer.last(), arrivalTime)
stringBuilder.append("$processStartTime")
val processFinishTime = processStartTime + processTime
buffer.addLast(processFinishTime)
```

**Drop Case**

* But we need to drop the packet if it arrives when the buffer is full.

```kotlin

if (arrivalTime < buffer.last())
    drop
```

**Release Case**

* And we need to release (leave, exit) the packets that have finished their processing.

```kotlin

while (buffer.isNotEmpty() && buffer.first() < arrivalTime) {
    buffer.removeFirst()
}

```

**Order**

* Now, we need to make things in order.
* 