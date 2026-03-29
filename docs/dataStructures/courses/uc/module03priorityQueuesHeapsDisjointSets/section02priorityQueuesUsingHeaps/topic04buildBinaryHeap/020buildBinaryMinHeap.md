# Binary Min Heap

<!-- TOC -->
* [Binary Min Heap](#binary-min-heap)
* [References / Resources / Prerequisites](#references--resources--prerequisites)
* [Problem Introduction](#problem-introduction)
* [Problem Description](#problem-description)
  * [Task](#task)
  * [Input Format](#input-format)
  * [Constraints](#constraints)
  * [Output Format](#output-format)
  * [Time Limits](#time-limits)
  * [Memory Limit](#memory-limit-)
  * [Sample 1](#sample-1)
    * [Input](#input)
    * [Output](#output)
  * [Sample 2.](#sample-2)
    * [Input](#input-1)
    * [Output](#output-1)
* [Thought Process](#thought-process)
  * [buildHeap](#buildheap)
  * [siftDown](#siftdown)
  * [Helper Functions](#helper-functions)
* [Time Complexity](#time-complexity)
* [Space Complexity](#space-complexity)
<!-- TOC -->

# References / Resources / Prerequisites

* [Local: heapSort.md](docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)

* [GitHub: heapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a244469d45534a60ac027a4925e07ac3f1d256e3/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)


* [Local: buildMaxHeap.md](010buildBinaryMaxHeap.md)

* [GitHub: buildMaxHeap.md](010buildBinaryMaxHeap.md)

# Problem Introduction

* In this problem, you will convert an array of integers into a heap.
* This is the crucial step of the sorting algorithm called HeapSort. 
* It has a guaranteed worst-case running time of `𝑂(𝑛 log 𝑛)` as opposed to QuickSort’s average running time of `𝑂(𝑛 log 𝑛)`. 
* QuickSort is usually used in practice because, typically, it is faster, but HeapSort is used for external sort when you need to sort huge files that don’t fit into the memory of your computer.

# Problem Description

## Task

* The first step of the HeapSort algorithm is to create a heap from the array you want to sort.   
* By the way, did you know that algorithms based on Heaps are widely used for external sort, when you need to sort huge files that don’t fit into the memory of a computer?  
* Your task is to implement this first step and convert a given array of integers into a heap.   
* You will do that by applying a certain number of swaps to the array.  
* The `Swap` is an operation that exchanges elements `𝑎_𝑖` and `𝑎_𝑗` of the array `𝑎` for some `𝑖` and `𝑗`. 
* You will need to convert the array into a heap using only `𝑂(𝑛)` swaps.
* Note that you will need to use a min-heap instead of a max-heap in this problem.

## Input Format

* The first line of the input contains a single integer `𝑛`. 
* The next line contains `𝑛` space-separated integers `𝑎_𝑖`.

## Constraints

* $1 ≤ 𝑛 ≤ 100 000;$ 
* $0 ≤ 𝑖, 𝑗 ≤ 𝑛 − 1;$ 
* $0 ≤ 𝑎_0, 𝑎_1, . . . , 𝑎_{𝑛−1} ≤ 10^9$.
* All the $𝑎_𝑖$ are distinct.

## Output Format

* The first line of the output should contain a single integer `𝑚` — the total number of swaps.
* `𝑚` must satisfy conditions `0 ≤ 𝑚 ≤ 4𝑛`. The next `𝑚` lines should contain the swap operations used to convert the array 𝑎 into a heap.
* Each swap is described by a pair of integers `𝑖`, `𝑗` — the 0-based indices of the elements to be swapped. 
* After applying all the swaps in the specified order, the array must become a heap, that is, for each `𝑖` where `0 ≤ 𝑖 ≤ 𝑛 − 1`, the following conditions must be true:


* If $2𝑖 + 1 ≤ 𝑛 − 1$, then $𝑎_𝑖 < 𝑎_{2𝑖+1}$.
* If $2𝑖 + 2 ≤ 𝑛 − 1$, then $𝑎_𝑖 < 𝑎_{2𝑖+2}$.


* Note that all the elements of the input array are distinct. 
* Note that any sequence of swaps that has length at most `4𝑛`, and after which your initial array becomes a correct `heap`, will be graded as correct.

## Time Limits

* C: 1 sec, C++: 1 sec, Java: 3 sec, Python: 3 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 3 sec, Ruby: 3 sec, Scala: 3 sec.  

## Memory Limit 

* 512MB

## Sample 1

### Input

```
5
5 4 3 2 1
```

### Output

```
3
1 4
0 1
1 3
```

* After swapping elements `4` in position `1` and `1` in position `4`, the array becomes `5 1 3 2 4`.
* After swapping elements `5` in position `0` and `1` in position `1`, the array becomes `1 5 3 2 4`.
* After swapping elements `5` in position `1` and `2` in position `3`, the array becomes `1 2 3 5 4`, which is already a heap, because:

$𝑎_0 = 1 < 2 = 𝑎_1, 𝑎_0 = 1 < 3 = 𝑎_2, 𝑎_1 = 2 < 5 = 𝑎_3, 𝑎_1 = 2 < 4 = 𝑎_4$.

## Sample 2.

### Input

```
5
1 2 3 4 5
```

### Output

```
0
```

* The input array is already a heap, because it is sorted in increasing order.

# Thought Process

* [Local: buildMaxHeap.md](010buildBinaryMaxHeap.md)

* [GitHub: buildMaxHeap.md](010buildBinaryMaxHeap.md)

* Similar to building a max-heap, we can build a min-heap.
* The only difference is that, we ensure that the parent node is smaller than the smallest child node.
* Also, there is no sorting.
* So, we can avoid sorting the given input.
* We just need to build the min heap.

## buildHeap

```kotlin

fun buildHeap(array: IntArray) {
    for (i in ((array.size/2) - 1) downTo 0) {
        siftDown(array, i)
    }
}

```

## siftDown

```kotlin

private fun siftDown(array: IntArray, index: Int) {
    var parent = index
    while (hasLeftChild(parent, array)) {
        var min = getLeftChildIndex(parent)
        if (hasRightChild(parent, array) && array[getRightChild(parent)] < array[min]) {
            min = getRightChild(parent)
        }
        if (array[parent] <= array[min]) {
            break
        } else {
            swap(array, parent, min)
            parent = min
        }
    }
}

```

## Helper Functions

```kotlin

private fun getLeftChildIndex(index: Int) = (2 * index) + 1

private fun getRightChildIndex(index: Int) = (2 * index) + 2

private fun hasLeftChild(index: Int, array: IntArray): Boolean {
    return getLeftChildIndex(index) in array.indices
}

private fun hasRightChild(index: Int, array: IntArray): Boolean {
    return getRightChildIndex(index) in array.indices
}

private fun swap(array: IntArray, positionOne: Int, positionTwo: Int) {
    if (positionOne !in array.indices || positionTwo !in array.indices) return
    
    val temp = array[positionOne]
    array[positionOne] = array[positionTwo]
    array[positionTwo] = temp
}

```

# Time Complexity

* It is `O(n)`.
* We might feel that it should be `O(n log n)`, because we perform the [siftDown] operation almost `n` times.
* However, the realistic analysis reveals that it is `O(n)` only.
* Please check the `heapSort.md` file for more details.

* [Local: heapSort.md](docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)

* [GitHub: heapSort.md](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/a244469d45534a60ac027a4925e07ac3f1d256e3/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03HeapSort/heapSort.md)

* [Local: buildMaxHeap.md](010buildBinaryMaxHeap.md)

* [GitHub: buildMaxHeap.md](010buildBinaryMaxHeap.md)

# Space Complexity

* This is an in-place-algorithm that does not require any auxiliary space.
* In that way, it is `O(1)`.
* However, if we count the output space, then it is `O(m)`, where `m` is the size of the `swaps`.