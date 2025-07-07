## Information:

* An interviewer may or may not expect a deep answer along with the most relevant information.
* It depends on the body language and communication of the interviewer to identify and understand the expectations.
* It is OK to ask whether we are expected to give more detailed answers along with the most relevant and important points or just a short and sweet, to the point answer.

## Table Of Content
- [A data structure to get an item in O(1)](#which-data-structure-should-we-use-if-we-want-to-read-get-any-element-in-o1-time)
- [A data structure to insert an item in the middle in O(1)](#which-data-structure-is-suitable-if-we-want-to-insert-an-element-in-the-middle-or-after-a-certain-item-in-o1-time)
- [A data structure to insert an item to the back and get it from the front](#which-data-structure-is-suitable-if-we-want-to-insert-an-item-from-rear-back-and-read-or-remove-the-item-from-front)
- [A data structure to track recursion or add and remove an item from the same side](#which-data-structure-is-suitable-if-we-want-to-track-a-recursion)
- [A data structure for a file directory, HTML, XML, etc.](#which-data-structure-is-suitable-for-a-file-directory)

## Which data structure should we use if we want to read (get) any element in O(1) time?

* **Array.** The `random access` time complexity of an array is `O(1)` because it is a linear and contiguous data structure.
* **Means?** It means that if we know the address of the array, we can get any element using a simple math.
* **Example:** For example, suppose the array is at memory location 100 and each element occupies 10 bytes of memory, we can get the 3rd element at 100 + (10 * 3) = 130.
* **Random add/delete:** However, insertion or deletion can cost `O(n)` if it is not happening from the end-side, because each item will have to shift to fill the gap.
* **Fixed Size:** And we have a limitation of fixed size which can cause unused allocated memory.
* **Dynamic Array:** And a dynamic array can cause `O(n)` time complexity in resizing.
* **Still:** We may still use `Arrays` because they are `cache-friendly` due to their linear contiguous memory.
* **Contiguous Loading:** When we load one item, nearby items are also loaded due to spatial (positional) locality.
* **Result:** As a result, loading the next item is relatively fast as it is already available in the cache, which is faster than loading an item from the slow main memory.
* **Reading Analogy:** So it is like reading a book with a bookmark. Next time when we read a book, we just need to find the bookmark. All the pages are in a sequence only, one after another which makes the reading experience smooth. 

## Which data structure is suitable if we want to insert an element in the middle or after a certain item in O(1) time?

* **A Linked List**. Because the linked list will have a reference of the next item.
* So, we can use this information to splice-in the new item.
* **Finding the item:** However, finding the item itself after which we want to insert a new item can cost `O(n)` time complexity, because a linked list is not a contiguous data structure.
* **Singly Linked List Example:** For example, if it is a singly linked list, and we want to perform an insert or a delete operation, it can take `O(n)` to find the target node.
* **Doubly Linked List Example:** For a doubly linked list, we can perform this insert or delete operation in `O(1)` time, but it introduces an extra property to maintain the previous address. 
* **The Pointer Overhead:** Also, we need to take care of the pointer property, whenever we change the neighbour items, head, or tail by performing any operations like insert, or delete.
* **Memory Overhead:** Also, linked lists are not cache friendly. They are `cache inefficient` due to non-linear non-contiguous memory allocation.
* **Memory Scattering:** The items are scattered throughout the memory in a non-linear non-contiguous fashion.
* **Scattered Fetching:** Fetching scattered items causes `cache miss` where the CPU cannot predict exact location of next items.
* **Slow Fetching:** This `cache-inefficiency` makes linked list loading from the slow main memory.
* **Fetching Preference:** That's why, we prefer `ArrayList` over `LinkedList` even though `LinkedList` has less time complexity for adding an item or deleting an item.
* **Fetching Analogy:** It is like a treasure hunt where even though we have an address of the next item, we can't know the address of multiple items at once to fetch and load in the `cache line`. 

## Which data structure is suitable if we want to insert an item from rear (back) and read or remove the item from front?

* **A Queue.** In a queue, we enqueue an item from rear and dequeue the item from front.
* **Principle:** It follows **FIFO - First-In-First-Out** principle.
* **Implementation:** We can implement a queue using a circular array with fixed size capacity or using a linked list with dynamic capacity.
* **Using A Circular Array:** If we write a queue using a circular array, we need to manage 2 or 3 extra properties such as `readIndex,` `writeIndex`, and `size`.
* **Properties With Reason:** Why? We use `readIndex` and `writeIndex` to reduce the dequeue time complexity from `O(n)` (that happens due to item shifting in an array) to `O(1)` and we use the `size` property to avoid `One Empty Slot - The Unused Allocated Memory`.
* **Time Complexity:** The time complexity of these two main functions, `enqueue` and `dequeue` of the queue becomes `O(1)`.
* **Space Complexity:** The space complexity remains `O(n)` that we use to create the array, where `n` is the number of items in the queue.
* **Using A Linked List:** If we write a queue using a linked list, it grows dynamically.
* **Enqueue Implementation:** The `pushBack` functionality of the linked list becomes the `enqueue` functionality of the queue.
* **Dequeue Implementation:** The `topFront` and `popFront` functionalities of the linked list become the `dequeue` functionality of the queue.
* **Time Complexity:** The time complexity of these two main functions of the queue, `enqueue` and `dequeue` is `O(1)` only when we use a linked list for the queue.
* **Space Complexity:** The space complexity remains `O(n)` for the queue implementation using a linked list due to the linked list we create.
* Here, `n` stands for number of items in the queue.

## Which data structure is suitable if we want to track a recursion?

* **A Stack.** Because, it follows **LIFO - Last-In-First-Out** principle.
* **Add/delete:** We can add an item to the stack or remove an item from the stack from the same side, from the right (or top) side.
* **Characteristics:** So, the item that we add last (recently) gets removed first. That's why we say that it follows `LIFO - Last-In-First-Out`.
* **Process-Order Preference:** The recursion follows a lexical scope where we want to ensure that the process that has been started the most recently, finishes first.
* **Parent-Child Functions:** Here, a parent function waits for a child function's output.
* **LIFO and Recursion:** So, it follows `LIFO - Last-In-First-Out`, which makes a stack a suitable data structure for a recursion.
* **System:** The system uses a stack to track and manage the recursive function calls.
* **Implementation:** We can use an array or a linked list to create a stack.
* **Using an array:** We can add an item to the end or remove an item from the end.
* **Time Complexity:** `O(1)` as we use and manage `index` using an internal property.
* **Space Complexity:** `O(n)` where `n` is the number of items.
* **Using a linked list:** The `pushFront` becomes the `add` or `insert` operation for the `stack` and `topFront` and `popFront` becomes the `remove` operation of the `stack`.
* **Why front?** To perform the add and remove operations in `O(1)` time. Because, if we use `pushBack` to remove the item, we must travel up to the second-last item to make it a new tail, which takes `O(n)` time without the `doubly linked list`.
* **Doubly Linked List:** We can use a doubly linked list, but it introduces an overhead of the `next` and the `previous` pointers for each node.
* **Time Complexity:** A singly linked list that uses the `pushFront`, `topFront`, and `popFront` functions to add and remove items, takes `O(1)` time for adding or removing an item.
* **Space Complexity:** `O(n)` where `n` is the number of items.

## Which data structure is suitable for a file directory?

* **A Tree:** Because, a file directory can have multiple children which is similar to a tree node having multiple children.
* **Tree Traversals:** There are mainly two types of tree traversals: `DFS,` that stands for `Depth-First-Search` and `BFS`, that stands for `Breadth-First-Search`.
* **DFS:** In `DFS`, we travel one subtree completely before travelling a sibling subtree.
* **Implementation:** `DFS` uses a stack to track and manage the traversal.
* **DFS Subtypes:** There are mainly three types of `DFS`: `In-Order,` `Pre-Order,` and `Post-Order.`
* **In-Order:** It follows `Left-Root-Right` sequence.
* **Pre-Order:** It follows `Root-Left-Right` sequence.
* **Post-Order:** It follows `Left-Right-Root` sequence.
* **BFS:** In `BFS`, we travel level by level. That's why, we also call it `Level-Order-Traversal`.
* **Implementation:** `BFS` uses a queue to track and manage the traversal.
* **Time-Complexity:** The time complexity of `BFS` and `DFS` is `O(n)` as we need to visit each node once.
* **Space-Complexity:** The space complexity of `BFS` and `DFS` is `O(n)` as the underlying data structure that we use, `Stack` for `DFS` and `Queue` for `BFS` has the same size of tree, which is equivalent to the number of nodes. 

