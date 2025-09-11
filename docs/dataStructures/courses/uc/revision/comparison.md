# Comparison of various data structures

## Sorted

### Array

**Insert**

* `O(n)` because inserting an element might cause `shifting` of elements.
* Also, in a sorted array, to insert an element, we need to perform the `search` (scan) operation to find the right position for the new element.
* But the array is already sorted. So, the `binary search` operation might take `O(log n)` time.

**Access**

* `O(1)`

**Search**

* `O(n)`

**Delete**

* `O(n)` due to `shifting` of the elements.
* Also,

## Unsorted