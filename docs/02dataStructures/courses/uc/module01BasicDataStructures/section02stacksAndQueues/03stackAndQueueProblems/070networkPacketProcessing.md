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

# Parallel Processing Problem

## Given

* N Threads
* M Jobs
* Process time each job takes
* Free thread takes the job
* Thread with the lowest index wins in case of the tie

## Find

* Which thread starts processing which job at what time (start time)

## Thought Process

* Suppose we take a queue and apply the same approach of **Network Packet Processing**.
* So, we would add the **Finish Time** to the queue.
* Maybe, we would take a comparable class to identify the thread index.
* So, maybe a comparable class with thread index and finish time.
* Here, `finishTime` is mutable because we might have less of threads than jobs. 
* So, we might want to re-use the threads to assign new jobs and update the `finishTime` of the thread based on these new jobs.

```kotlin

class Thread(val threadIndex: Int, var finishTime: Long)

```

* Initially, when we add threads to the queue, `finishTime` will be `0` for all the threads.
* So, to assign the `job` to the `free` thread, we might release the threads that are `free` from the queue.
* Here, `free` means whose `finishTime` is less than or equal to the arrival time of the recent `job` on hand.
* But we don't have the `arrivalTime` of any `job`!
* All we have is a `processTime` for each `job`!
* So, how do we calculate the `finishTime`?
* So maybe initially, when a thread gets the first job, the `processTime` becomes the `finishTime`.
* Otherwise, `finishTime = finishTime + processTime`.
* So:

```kotlin

val finishTime = if (finishTime == 0) processTime else finishTime + processTime

```

* But the problem asks us to find the `startTime`.
* So, initially, when a thread gets the first job, the `startTime` is `0`.
* And when a thread gets the first job, the `finishTime` is also `0`.
* And the `startTime` of any `job` is the `finishTime` of the previous job.
* So:

```kotlin

val startTime = finishTime 
val finishTime = if (finishTime == 0) processTime else finishTime + processTime
```

* Now, let us assume that we have 5 threads and they got their first job as below:

```markdown

Thread-0 → job-0 → Start Time = 0, Process Time = 5
Thread-1 → job-1 → Start Time = 0, Process Time = 5
Thread-2 → job-2 → Start Time = 0, Process Time = 4
Thread-3 → job-3 → Start Time = 0, Process Time = 6
Thread-4 → job-4 → Start Time = 0, Process Time = 7

```

* Now, to assign the `6th Job`, we can only release the `first` thread from the queue.
* But in reality, `thread-2` has finished its job earlier.
* So, the `6th Job` should be assigned to `thread-2`.
* How do we do that?
* We need a structure that pushes the thread that finishes its job to the front.
* In more specific technical terms, we need a structure that pushes the thread whose `finishTime` is the `minimum` to the front.
* We will see this structure in detail in the upcoming lessons of: `Min-Heap`.
* Reference: [Parallel Processing](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/024dffd343f10f12b9473ea0396ed5ff9db4b872/docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section02priorityQueuesUsingHeaps/topic04buildBinaryHeap/030parallelProcessing.md)