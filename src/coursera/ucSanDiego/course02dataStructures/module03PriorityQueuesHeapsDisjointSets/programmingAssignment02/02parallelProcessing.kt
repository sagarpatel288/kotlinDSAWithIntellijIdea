package coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment02

/**
 * # References / Resources
 *
 * [GitHub: Network Packet Processing](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8a9ba3fa778f0787cd6d51bb3761c26a198215a0/src/coursera/ucSanDiego/course02dataStructures/module01/section04assignmentProblems/03networkPacketProcessingSimulation.kt)
 * [Local: Network Packet Processing](src/coursera/ucSanDiego/course02dataStructures/module01/section04assignmentProblems/03networkPacketProcessingSimulation.kt)
 *
 *
 *
 * # Problem Introduction
 *
 * In this problem, you will simulate a program that processes a list of jobs in parallel. Operating systems such
 * as Linux, macOS or Windows all have special programs in them called schedulers which do exactly this with
 * the programs on your computer.
 *
 * # Problem Description
 *
 * ## Task
 *
 * You have a program that is parallelized and uses `𝑛` independent threads to process the given list of `𝑚`
 * jobs. Threads take jobs in the order they are given in the input. If there is a free thread, it immediately
 * takes the next job from the list. If a thread has started processing a job, it doesn’t interrupt or stop
 * until it finishes processing the job. If several threads try to take jobs from the list simultaneously, the
 * thread with the smaller index takes the job. For each job, you know exactly how long it will take any thread
 * to process this job, and this time is the same for all the threads. You need to determine for each job that
 * which thread will process it, and when it will start processing.
 *
 * ## Input Format
 *
 * The first line of the input contains integers, `𝑛` and `𝑚`.
 * The second line contains `𝑚` integers `𝑡_𝑖` — the times in seconds it takes any thread to process the `𝑖-th` job.
 * The times are given in the same order as they are in the list from which threads take jobs.
 * Threads are indexed starting from 0.
 *
 * ## Constraints
 *
 * `1 ≤ 𝑛 ≤ 10^5; 1 ≤ 𝑚 ≤ 10^5; 0 ≤ 𝑡_𝑖 ≤ 10^9`.
 *
 * ## Output Format
 *
 * Output exactly `𝑚` lines. `𝑖-th` line (0-based index is used) should contain two space-separated integers —
 * the 0-based index of the thread which will process the `𝑖-th` job, and the time in seconds when it will start
 * processing that job.
 *
 * ## Time Limit
 *
 * C: 1 sec, C++: 1 sec, Java: 4 sec, Python: 6 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 6 sec, Ruby: 6 sec,
 * Scala: 6 sec.
 *
 * ## Memory Limit
 *
 * 512MB.
 *
 * ## Sample-01
 *
 * ### Input:
 * ```
 * 2 5
 * 1 2 3 4 5
 * ```
 *
 * ### Output:
 * ```
 * 0 0
 * 1 0
 * 0 1
 * 1 2
 * 0 4
 * ```
 *
 * * The two threads try to simultaneously take jobs from the list, so the thread with index `0` actually takes the
 * first job and starts working on it at the moment `0`.
 * * The thread with index `1` takes the second job and starts working on it, also at the moment `0`.
 * * After `1` second, thread `0` is done with the first job and takes the third job from the list, and starts
 * processing it immediately at time `1`.
 * * One second later, thread `1` is done with the second job and takes the fourth job from the list, and
 * starts processing it immediately at time `2`.
 * * Finally, after `2` more seconds, thread `0` is done with the third job and takes the fifth job from the
 * list, and starts processing it immediately at time `4`.
 *
 * ## Sample-02
 *
 * ### Input:
 * ```
 * 4 20
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * ```
 *
 * ### Output:
 * ```
 * 0 0
 * 1 0
 * 2 0
 * 3 0
 * 0 1
 * 1 1
 * 2 1
 * 3 1
 * 0 2
 * 1 2
 * 2 2
 * 3 2
 * 0 3
 * 1 3
 * 2 3
 * 3 3
 * 0 4
 * 1 4
 * 2 4
 * 3 4
 * ```
 *
 * * Jobs are taken by `4` threads in packs of `4`, processed in `1` second, and then the next pack comes.
 * * This happens `5` times starting at moments `0`, `1`, `2`, `3`, and `4`.
 * * After that, all the `5 × 4 = 20` jobs are processed.
 *
 *
 *
 */
class ParallelProcessing {

}