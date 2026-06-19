# Linked Lists

## Includes

* Which need led us this transition from one data structure to another
* What changes along the way:
    * The underlying data structure
    * Supported operations
    * Time and space complexity of each supported operation
    * Miscellaneous
* Progressive comparison
    * Access, find, insert, update, delete, etc.
    * Best case, average case, worst-case with notes
    * Pros and cons
    * The drawback that the next data structure solves
* Miscellaneous

## Prerequisites / Previously

* [Arrays.md](010arrays.md)
* [Dynamic Arrays.md](020dynamicArrays.md)

## References / Resources

* 

## What

* We have seen it earlier that contiguous data structures like [Arrays.md](010arrays.md) and [Dynamic Arrays.md](020dynamicArrays.md) causes shifting cost. 
* To eliminate the shifting cost, we take non-contiguous data structure.
* But when we abandon the contiguous structure, we also lose the math that gives us random access in $O(1)$.
* So, to access the data, we rely on the pointers of the head, node, and tail.

![07detailedStructure.png](../../../../../assets/images/dataStructures/uc/module01basicDataStructures/030linkedList/07detailedStructure.png)

* The data itself must hold the memory address of the next data.
* Such a data structure is called a linked list.
* And the block that holds the data, and information of the next block, is called node.
* If the node holds the address of only the next node, then it is a singly linked list.
* If the node holds the address of both the next and the previous node, then it is called doubly linked list.
* We will have the address of the head only.
* The head is the first node.
* We keep the reference of the head through which we can travel through all the other nodes.
* The head will have the address of the next node.
* The next node will have the address of its next node.
* And so on... 
* At some point, the `next` value in a particular node will be null or empty.
* In other words, at some point, we reach a node whose `next` pointer will point to `null`.
* It means that there is no more `next` node.
* That will be the last node.
* So, the node whose `next` pointer points to `null`, is the last node.
* We can keep the last node handy same as the head, but it is optional.
* If we hold and maintain the reference of the last node, we call it the tail.
* With tail, getting the last element (node) is cheap.
* Without tail, to get the last element, we have to travel from the head down to the last node.

## Solves

* No shifting cost when we insert or remove an element.

## How

* Using a non-contagious and pointer based structure.
* Each node holds the memory address of the next node.
* Now, whenever we insert or remove an element, we just need to change the pointers of a couple of nodes.
* And changing the pointer takes $O(1)$ time only.

## Common Functions

```markdown

| Interface | Model              | Functions                                                       |
|-----------|--------------------|-----------------------------------------------------------------|
| List      | Collection         | add, get, remove                                                |
| Queue     | FIFO               | offer, peek, poll                                               |
| Deque     | Double Ended Queue | offerFirst, offerLast, peekFirst, peekLast, pollFirst, pollLast |
| Stack     | LIFO               | push, peek, pop                                                 |

```

```markdown

| Action                       | Returns null or Boolean on failure | Throws an exception on failure |
|------------------------------|------------------------------------|--------------------------------|
| Inserts at Head              | offerFirst                         | addFirst, push                 |
| Inserts at Tail              | offer, offerLast                   | add, addLast                   |
| Reads the Head               | peek, peekFirst                    | element, getFirst              |
| Reads the Tail               | peekLast                           | getLast                        |
| Removes and returns the Head | poll, pollFirst                    | remove, removeFirst, pop       |
| Removes and returns the Tail | pollLast                           | removeLast                     |

```

**`remove` overloads**

* `remove` when used for a `queue` throws exception on failure.
* `remove(int index)` throws `IndexOutOfBounds` for an invalid index.
* `remove(object o)` returns `true` if the item was found and removed. Otherwise, it returns `false`.



## Next (Types)


* [Singly Linked List With Tail](037singlyLinkedListWithTail.md)
* [Doubly Linked List With Tail](045doublyLinkedListWithTail.md)
* [Queues](050queues.md)
* [Stacks](060stacks.md)
* [Trees](070trees.md)
* [Priority Queues](080priorityQueues.md)
* [Disjoint Sets](090disjointSets.md)
* [Hash Tables](100hashTables.md)
* [Hash Map](105hashMap.md)
* [Hash Set](110hashSet.md)
* [Binary Search Trees](120binarySearchTrees.md)
* [Self Balancing Binary SearchTrees](130selfBalancingBinarySearchTrees.md)
* [AvlTree](135avlTree.md)
* [SplayTree](140splayTree.md)
* [Trie](145trie.md)
* [Graph](200graph.md)
* [Overview](300overview.md)
* [Comparison](comparison.md)
* [Data Structure Questions](dataStructureQuestions.md)