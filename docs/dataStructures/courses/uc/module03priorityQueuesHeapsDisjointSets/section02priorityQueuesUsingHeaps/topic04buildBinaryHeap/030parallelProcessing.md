# Parallel Processing

<!-- TOC -->
* [Parallel Processing](#parallel-processing)
* [References / Resources / Prerequisites](#references--resources--prerequisites)
* [Problem Introduction](#problem-introduction)
* [Problem Description](#problem-description)
  * [Task](#task)
  * [Input Format](#input-format)
  * [Constraints](#constraints)
  * [Output Format](#output-format)
  * [Time Limit](#time-limit)
  * [Memory Limit](#memory-limit)
  * [Sample-01](#sample-01)
    * [Input:](#input)
    * [Output:](#output)
  * [Sample-02](#sample-02)
    * [Input:](#input-1)
    * [Output:](#output-1)
  * [Thought Process](#thought-process)
    * [Given Data](#given-data)
    * [Output Data](#output-data)
    * [Hints](#hints)
      * [Data Structure](#data-structure)
      * [Process](#process)
* [Time Complexity](#time-complexity)
* [Space Complexity](#space-complexity)
* [Grader Output](#grader-output)
<!-- TOC -->

# References / Resources / Prerequisites

* [GitHub: Network Packet Processing](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8a9ba3fa778f0787cd6d51bb3761c26a198215a0/src/coursera/ucSanDiego/course02dataStructures/module01/section04assignmentProblems/03networkPacketProcessingSimulation.kt)

* [Local: Network Packet Processing](src/courses/uc/course02dataStructures/module01/section04assignmentProblems/03networkPacketProcessingSimulation.kt)

* [GitHub: buildMinHeap](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/aebc9756abce91df3f5ff51efc7c0efde5d0b0de/src/coursera/ucSanDiego/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment02/01buildMinHeap.kt)

* [Local: buildMinHeap](src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment02/01buildMinHeap.kt)

# Problem Introduction

* In this problem, you will simulate a program that processes a list of jobs in parallel. 
* Operating systems such as Linux, macOS or Windows all have special programs in them called schedulers which do exactly this with the programs on your computer.

# Problem Description

## Task

* You have a program that is parallelized and uses `𝑛` independent threads to process the given list of `𝑚` jobs. 
* Threads take jobs in the order they are given in the input. 
* If there is a free thread, it immediately takes the next job from the list. 
* If a thread has started processing a job, it doesn’t interrupt or stop until it finishes processing the job. 
* If several threads try to take jobs from the list simultaneously, the thread with the smaller index takes the job. 
* For each job, you know exactly how long it will take any thread to process this job, and this time is the same for all the threads. 
* You need to determine for each job that which thread will process it, and when it will start processing.

## Input Format

* The first line of the input contains integers, `𝑛` and `𝑚`.
* The second line contains `𝑚` integers $𝑡_𝑖$ — the times in seconds it takes any thread to process the `𝑖-th` job.
* The times are given in the same order as they are in the list from which threads take jobs.
* Threads are indexed starting from 0.

## Constraints

$1 ≤ 𝑛 ≤ 10^5;$ 

$1 ≤ 𝑚 ≤ 10^5;$ 

$0 ≤ 𝑡_𝑖 ≤ 10^9$

## Output Format

* Output exactly `𝑚` lines. 
* `𝑖-th` line (0-based index is used) should contain two space-separated integers as follows:
* The 0-based index of the thread which will process the `𝑖-th` job, and the time in seconds when it will start processing that job.

## Time Limit

* C: 1 sec, C++: 1 sec, Java: 4 sec, Python: 6 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 6 sec, Ruby: 6 sec, Scala: 6 sec.

## Memory Limit

* 512MB.

## Sample-01

### Input:

```
2 5
1 2 3 4 5
```

### Output:

```
0 0
1 0
0 1
1 2
0 4
```

* The two threads try to simultaneously take jobs from the list, so the thread with index `0` actually takes the first job and starts working on it at the moment `0`.
* The thread with index `1` takes the second job and starts working on it, also at the moment `0`.
* After `1` second, thread `0` is done with the first job and takes the third job from the list, and starts processing it immediately at time `1`.
* One second later, thread `1` is done with the second job and takes the fourth job from the list, and starts processing it immediately at time `2`.
* Finally, after `2` more seconds, thread `0` is done with the third job and takes the fifth job from the list, and starts processing it immediately at time `4`.

## Sample-02

### Input:

```
4 20
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
```

### Output:

```
0 0
1 0
2 0
3 0
0 1
1 1
2 1
3 1
0 2
1 2
2 2
3 2
0 3
1 3
2 3
3 3
0 4
1 4
2 4
3 4
```

* Jobs are taken by `4` threads in packs of `4`, processed in `1` second, and then the next pack comes.
* This happens `5` times starting at moments `0`, `1`, `2`, `3`, and `4`.
* After that, all the `5 × 4 = 20` jobs are processed.

## Thought Process

### Given Data

* Number of threads, `n`.
* Number of jobs, `m`.
* Process time for each job.

### Output Data

* Which thread will take which job, and when will it finish the job?
* So, basically, a total of `m` lines where each line contains two values (space-separated).
* Each line shows the information for each job sequentially, as per the given order of jobs.
* The first value indicates the thread index that takes and processes a particular job.
* The second value indicates the start time of the job.

**Example**

* 0 0 --> Line `0` indicates that the job `0` will be taken by the thread `0` and will be started at time `0`.
* 1 0 --> Line `1` indicates that the job `1` will be taken by the thread `1` and will be started at time `0`.
* 0 5 --> Line `2` indicates that the job `2` will be taken by the thread `0` and will be started at time `5`.

---

* `m` threads
* `n` jobs: Each job with the time it takes

**Thread Index**

* Print by thread index (hidden), the job index (indicating the job it took), and the starting (processing) time of the job in that thread, by that thread.
* We need a data class that holds the thread index, the job index it took, and the starting (processing) time of the job in that thread, by that thread.

**Which job does a thread take?**

* It depends on the time.
* If a thread is free, and has the smallest index compared to other free threads, then this thread gets the job.
* It means that we implement the `comparable` interface that compares two different threads based on their `free` availability and `thread index`.

**What does it mean by a `Free Thread`? How do we convert it into the code? How to represent it in a code?**

* The phrase `Free` is associated with `Time`.

**From where do we get this `Time`?**

* The only data we have that gives the `Time` is from a `Job`.
* Each `job` has a duration that indicates how much time it takes.

**How do we associate this `Job Time` data with the `Free Thread` phrase?**

* Suppose that a job takes `30` seconds or whatever the time unit is.
* It means that the thread that processes this job, once the job process begins, the thread will be free after `30` seconds.

**When does the job process begin? How do we get the job start time?**

* As soon as the thread is free.

**And when is a thread free?**

* As soon as it finishes processing a job.

**When does a thread finish processing a job?**

* After the duration of the job.

* The `start time` depends on the `finish time` of the previous job!
* It looks like a circle (marry-go-round).
* But, we can use the base case.
* When there is no previous job, the time starts from `0`, and we have provided with the `finish time` in the form of the `duration - the time each job takes`.
* After that, after the base case, each new job starts after the **previous job finishes** and runs for the **duration**.
* We can convert this into a mathematical formula:

> Start time = previousJob.finishTime

* When there is no previous job, the start time is `0`.
* And it will finish at (The job gets finished at):

> Finish time = start time + job.duration

**What do we do with the `Start Time` and `Finish Time`? Why did we find it? How do we connect it with the phrase `Free Thread`?**

* We calculated the `start time` and the `finish time` so that we can know which thread will be **free** the **first**.
* The thread whose `finish time` is the **smallest**, the **earliest**, is the thread that gets **free** the **first**.
* And the thread that gets **free** the **first** and has the **smallest** index, gets the job to process.
* In other words, the thread that finishes the job/s the **earliest** and has the **smallest** index, gets the next job to process.
* The words **earliest**, **first**, and **smallest** represent the **extremum**.
* And when we are continuously looking for the **extremum**, we use a priority queue.

---

* We need a heap that gives us the thread whose `finish time` is the **smallest**, the **earliest**.
* We can have a `min heap` that gives us the thread whose `finish time` is the **smallest**, the **earliest**.
* The default priority queue in Kotlin (via Java) is a `min heap` only.
* It means that we can use the default priority queue.

**What if there are multiple threads with the same `finish time`?**  

* It is clearly given that the thread that gets free the first, is the thread that gets the job first.  
* And if there are multiple free threads, then the thread with the smallest index gets the job first.  
* So, we need to implement a custom `comparable` interface to follow the given instructions.  
* The first comparison is based on the smallest, the earliest `finish time`.   
* And the second comparison is based on the thread index.    

**The Thread Class**

```kotlin

data class Thread(val threadIndex: Int, val finishTime: Int) : Comparable<Thread> {
  override fun compareTo(other: Thread): Int {
      if (this.finishTime != other.finishTime) {
          return this.finishTime.compareTo(other.finishTime)
      }
      return this.threadIndex.compareTo(other.threadIndex)
  }
}

```

**How do we add these threads to the priority queue?**

* The input gives us the total threads, `n`.
* We add these `n` threads to the priority queue:

```kotlin

val priorityQueue = PriorityQueue<Thread>()
repeat(n) { index ->
    priorityQueue.add(Thread(index, 0))
}

```

**How do we provide job to these threads?**  
**From where do we get the jobs? How do we get the jobs?**  
**From where do we get the job that we can provide to these threads?**  

* We get the jobs from the input.
* These jobs are 0-indexed, and they are given in the same order in the input.
* So, we can collect all the jobs from the input, and then we can provide them to the threads using a `for` loop.

**How do we collect the jobs from the input?**

```kotlin

val jobs = mutableListOf<Int>()
while (token.hashNextToken()) {
    val job = token.readInteger() // The time it takes to complete
    jobs.add(job)
}

```

**How do we provide these collected jobs to the threads?**

```kotlin

val stringBuilder = StringBuilder()
for (job in jobs) {
    // This is how we get the eligible/available thread
    // poll gives us the thread with the earliest finish time and the smallest index
    val availableThread = priorityQueue.poll()
    // The finish time of the previous job is the start time of the current job
    val startTime = availableThread.finishTime
    val finishTime = startTime + job
    stringBuilder.append("${availableThread.threadIndex} $startTime")
    // This is a critical line:
    // This is how the thread gets the job
    priorityQueue.add(Thread(availableThread.threadIndex, finishTime))
}

```

### Hints

#### Data Structure

* It is given that:
> If there is a free thread, it immediately takes the next job from the list.
* The thread that becomes `free` first and earliest, picks up the `job` first.
* It clearly means that we need to sort the `threads` based on their `finish time`.
* Also, the statement:
> The thread that becomes `free` **first and earliest**
* It clearly shows a `priority`.
* So, we use a `priority queue`.

#### Process

**But once we add the `job` to the `thread,` don't we need to update our `sorting`?**

* This is somewhat tricky. 
* We don't actually `update` the `same thread`.
* We create and add a new `thread` object with the same `index`, but with a new `finish time` to the `queue`.
* The reason is: The built-in `priority queue` does not support the `change priority` operation.
* So, it becomes the `add` operation of the `priority queue`.
* But we have a limited number of threads. 
* We need to maintain that. 
* We can't keep adding new threads.
* It means that when we `pick up` the thread from the `priority queue`, it is actually the `poll` operation.
* When we `pick up` a thread based on the priority, we remove it from the priority queue.
* So that we maintain the number of threads.

**How do we add `threads` to the `priority queue`?**

* Each `thread` holds two information: `threadIndex`, and `finishesAt`.
* So, we can create a data class and implement the `Comparable` interface.
* Because a `priority queue` needs to know how to process `min`, `max`, `sort`, etc., which require comparison.

```
data class ThreadState (val threadIndex: Int, val finishTime: Long): Comparable<ThreadState> {
    override fun compareTo(other: ThreadState): Int {
        if (this.finishTime != other.finishTime) {
            // `finishTime` is a `Long` data type that implements `Comparable`.
            return this.finishTime.compareTo(other.finishTime)
        }
        // `threadIndex` is a `Int` data type that implements `Comparable`.
        return this.threadIndex.compareTo(other.threadIndex)
    }
}
```

**How do we calculate the `finishTime`?**

> `Thread finish time` = Process start time of the job + Process time the job takes

**How do we find the `process start time of the job`?**

> `Process start time` = Process finish time of the last job

**Example**

* Initially, the thread does not have any job to process.
* So, the initial `finishTime` of the thread is `0`.

```
process finish time = 0
```

* Now, assume that the thread gets a job that takes `5` ms time.
* It means that, for this job, the `process start time = 0`, and the `process finish time = 5`.

```
process start time = 0 // which is the initial, default process finish time.
process finish time = 5 // which is the `process start time = 0` + `process time of this job = 5`.
```

* Next, suppose the thread gets a new job that takes `3` ms time.
* For this new job:

```
process start time = 5 // which is the `process finish time` of the `last job`.
process finish time = 8 // which is the `process start time = 5` + `process time of this job = 3`.
```

* And so on...

# Time Complexity

**Separate Loop-01**

* To add threads in the `priority queue`. It takes `O(n)`, where `n` is the number of threads.

**Separate Loop-02**

* To process each job, assign the thread, and store the result. It takes `O(m)`, where `m` is the number of jobs.

**Operations**

* The `poll` operation takes `O(log n)` time (It includes `extractMin` and `maintainHeap`) for each execution.
* We are doing it `m` times. 
* So, it becomes `O(m log n)`.
* The `add` operation takes `O(log n)` time (It includes `insert` and `maintainHeap`) for each execution.
* We are doing it `m` times. 
* So, it becomes `O(m log n)`.

**Conclusion**

* The overall (and dominant) time complexity is `O(m log n)`.


# Space Complexity

* We create and use a `priority queue` of size `n`, where `n` is the `number of threads`.
* So, it becomes `O(n)`.
* We also create and use a `job process time list` of size `m`, where `m` is the `number of jobs`.
* So, it becomes `O(m)`.
* We also create and use a `string builder` to store and print the output.
* But since it is part of the `output`, we don't consider it as part of the `algorithm`.
* Thus, the auxiliary space we use here is `O(n + m)`.

# Grader Output

```
Good job! (Max time used: 0.66/4.00, max memory used: 89063424/536870912.)
```

