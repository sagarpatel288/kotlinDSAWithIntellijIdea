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

* **Array.** We can use arrays to read (get) any element in `O(1)` time.
* **What is an array? (Type):** Arrays are linear and contiguous data structure.
* **Means?** It means that elements are arranged sequentially, and their memory addresses are adjacent. All the elements are next to each-other without any gap. 
* **So?** We get two major benefits from a data structure that is both linear and contiguous. 
* **1. Random Access in `O(1)`:** How? If we know the address of the array, we can get the address of any element in `O(1)` using a simple math. It means that we get `random access` in constant time, `O(1)`.
* **Example?** For example, suppose the array is at memory location 100 and each element occupies 10 bytes of memory, we can get the 3rd element at 100 + (10 * 3) = 130.
* **2. Cache-Efficiency:** Contiguous memory means loading one element often loads neighbour elements also, due to spatial (positional) location. So, loading the next elements are relatively faster than loading it from the slow main memory, because these next elements are already in the cache memory. It is like reading a book with a bookmark. All the pages are sequential and next to each-other. We can jump to any page and get a smooth reading experience. It is linear and contiguous.
* **2 Benefits are over. What is next? Does it have any limitations? Does that mean arrays are perfect? If arrays have these two major benefits, then why do we have other data structures?:** Arrays have their own limitations. 
* **Which limitations?** There are at least 3 problems with arrays. 
* **1. Random insert/delete:** Random insert/delete takes `O(n)` time. For example, inserting an item in the middle or deleting an item from the front or from the middle can cost `O(n)`, because each item will have to shift to fill the gap.
* **2. Fixed Size:** Arrays have a fixed size which can cause unused allocated memory.
* **3. Dynamic Array:** To solve the fixed size issue, we can use a `Dynamic Array` like an `ArrayList` or a `MutableList` in `Kotlin`. However, a dynamic array can cause `O(n)` time complexity in resizing. However, the resizing happens infrequently, and the overall average cost (amortized cost) of adding an element is `O(1)`.
 

## Which data structure is suitable if we want to insert an element in the middle or after a certain item in O(1) time?

* **A Linked List:** We can use a linked list to insert an element in the middle or after a certain element in `O(1)` time.
* **What is a linked list? (Type):** A linked list is a linear, but non-contiguous data structure.
* **So?** We get at least 1 benefit with at least 6 other problems! 
* **The Benefit:** We can insert an item after a particular node in `O(1)` time. How? In a linked list, each item has an address of the next item, which we can use to splice-in the new item.
* **And What are the problems?** We get at least 6 problems.
* **1. Finding an item:** Unlike arrays, linked lists are not contiguous data structure. It means that, we lose the benefit of getting `random access` in `O(1)` time. It is like a treasure hunt game. Each node holds an address of only one, immediate next node. So, we always have to start with the head node and travel up to the target node one by one. We have to ask each node what is the address of the next node. Because, all the nodes are scattered in the memory. This brings the next major disadvantage.
* **2. Caching-Inefficiency:** Just because all the nodes are stored in a non-contiguous and scattered way in different memory addresses, the cache memory cannot predict and load all the next nodes at once. It means that, we cannot get benefit of the cache memory. So, the system has to find and get an item from the slow main memory.  
* **3. Adding to the back (last):** Without a tail, adding an item to the end takes `O(n)` time. However, with an additional node called `tail`, we can do it in `O(1)` time, but then we get an overhead of one additional node that we have to take care of and manage.
* **4. Reading the back (last):** Again, without a tail, it takes `O(n)` time and with tail, it takes `O(1)` time.
* **5. Deleting the back (last):** For a singly linked list, with or without a tail, it takes `O(n)` time only. Because, when we delete the last node, we need to make the old second-last node a new tail node. So, we have to travel at least up to the second last node. Because, we cannot move into the backward direction in a singly linked list. However, for a doubly linked list, deleting the last node takes `O(1)` time, but we have to manage an additional pointer called, `prev` for each node. So, we get many properties to manage and this brings the next disadvantage.   
* **6. Property Overhead:** We need to take care of these pointers, head, and tail properties, whenever we change the neighbour items, head, or tail by performing any operations like insert, or delete.
* **Conclusion:** That's why, we prefer an `ArrayList` over a `LinkedList` even though `LinkedList` can have less time complexity for adding an item or deleting an item. 

## Which data structure is suitable if we want to insert an item from rear (back) and read or remove the item from front?

* **A Queue.:** We can use a queue to insert an item from back and get it from the front in a `FIFO - First In First Out` manner. 
* **What is a Queue? (Type):** The data structure of the queue depends on the underlying (internal) data structure that we use. We can use a circular array or a linked list. However, whether we use an array or a linked list as an internal data structure, the behavior of the queue remains the same: We enqueue an item from rear and dequeue the item from front. This behavior principle has a pattern name, which is our next point.
* **Behavior Principle:** It follows **FIFO - First-In-First-Out** principle, whether we use an array or a linked list in the implementation. This implementation detail brings the next point. 
* **Implementation:** We can implement a queue using a circular array with fixed size capacity or using a linked list with dynamic capacity.
* **Using A Circular Array:** If we write a queue using a circular array, we need to manage 2 or 3 extra properties such as `readIndex,` `writeIndex`, and `size`. The reason of these 2-3 properties is our next point.
* **Why these 2-3 Properties?:** We use `readIndex` and `writeIndex` to reduce the dequeue time complexity from `O(n)` (that happens due to item shifting in an array) to `O(1)` and we use the `size` property to avoid `One Empty Slot - The Unused Allocated Memory`.
* **Time Complexity:** The time complexity of these two main functions, `enqueue` and `dequeue` of the queue becomes `O(1)`.
* **Space Complexity:** The space complexity remains `O(n)` that we use to create the array, where `n` is the number of items in the queue.
* **Using A Linked List:** If we write a queue using a linked list, it grows dynamically.
* **Enqueue Implementation:** The `pushBack` functionality of the linked list becomes the `enqueue` functionality of the queue.
* **Dequeue Implementation:** The `topFront` and `popFront` functionalities of the linked list become the `dequeue` functionality of the queue.
* **What if we enqueue from the front side and dequeue from the rear side?** Then, deque operation takes `O(n)` time in a singly linked list, whether we use a tail node or not. Because, we have to travel up to the second-last node to make it a new tail. And if we don't use the tail, we have to travel up to the second-last node anyway, to make the next pointer of the second-last node `null`.
* **What if we use a doubly linked list?** We can use, and it will finish the dequeue operation in `O(1)` time even though if we dequeue it from the back side. But, we have to manage these extra properties like head, tail, next, prev, etc.   
* **Time Complexity:** If we enqueue from the back, and dequeue from the front, the time complexity of these two main functions of the queue, `enqueue` and `dequeue` is `O(1)` only when we use a linked list for the queue.
* **Space Complexity:** The space complexity remains `O(n)` for the queue implementation using a linked list due to the linked list we create.
* Here, `n` stands for number of items in the queue.

## Which data structure is suitable if we want to track a recursion?

* **A Stack:** For a recursion, we use the `stack` data structure.
* **What is a stack?** The data structure of the stack depends on the underlying (internal) data structure that we use. We can use an `array` or an `arrayDeque` or a `linked list`. Regardless of the underlying data structure, the behavior of the stack remains the same. We push (add) and pop (read, remove) the items from the same side. This behavior has a pattern name, which is our next point.
* **Behavior Principle** It follows **LIFO - Last-In-First-Out** principle.
* **Implementation:** We can use an array or a linked list to create a stack.
* **Using an array:** We can add an item to the end or remove an item from the end.
* **Time Complexity:** `O(1)` for all the three major functions: Push, top, and pop. 
* **Space Complexity:** `O(n)` where `n` is the number of items.
* **Using a linked list:** The `pushFront` becomes the `add` or `insert` operation for the `stack` and `topFront` and `popFront` becomes the `remove` operation of the `stack`.
* **Why front?** To perform the add and remove operations in `O(1)` time. Because, if we use `pushBack` to remove the item, we must travel up to the second-last item to make its next pointer null, which takes `O(n)` time without the `doubly linked list`.
* **Doubly Linked List:** We can use a doubly linked list, but it introduces an overhead of the `next` and the `previous` pointers for each node.
* **Time Complexity:** A singly linked list that uses the `pushFront`, `topFront`, and `popFront` functions to add and remove items, takes `O(1)` time for adding or removing an item.
* **Space Complexity:** `O(n)` where `n` is the number of items.

## Which data structure is suitable for a file directory?

* **A Tree:** We use a `Tree` for a file directory. Because, a file directory can have multiple children and a tree node can also have multiple children.
* **What is a tree? (Type):** A tree is a hierarchical, connected, and acyclic graph data structure with undirected path.
* **Hierarchical means?:** Hierarchical data structure means data has organized levels, and parent-child relationships between them.  
* **Acyclic means?:** Acyclic means absence of a cycle. In an acyclic data structure like a tree, we cannot start from a node, follow the (sequence of) edges, and return to the same node without repeating (retracing, backward) any node, or edge.   
* **Connected means?:** A connected data structure like a tree cannot have an isolated or an orphan node unless it is the only node of the tree. A tree that has only one node is a degenerate or a pathological tree. Otherwise, every pair of nodes are connected through an undirected path. That's why we call it a connected data structure.
* **Undirected path means?:** The edges do not have directions. That's why, we call this type of path an undirected path.   
* **If it is undirected, then how do we travel? Tree Traversals:** There are mainly two types of tree traversals: `DFS,` that stands for `Depth-First-Search` and `BFS`, that stands for `Breadth-First-Search`.
* **DFS:** In `DFS`, we travel one subtree completely before travelling a sibling subtree.
* **Data Structure Implementation:** `DFS` uses a stack to track and manage the traversal.
* **DFS Subtypes:** There are mainly three types of `DFS`: `In-Order,` `Pre-Order,` and `Post-Order.`
* **In-Order:** It follows `Left-Root-Right` sequence.
* **Pre-Order:** It follows `Root-Left-Right` sequence.
* **Post-Order:** It follows `Left-Right-Root` sequence.
* **BFS:** In `BFS`, we travel level by level. That's why, we also call it `Level-Order-Traversal`.
* **Data Structure Implementation:** `BFS` uses a queue to track and manage the traversal.
* **Time-Complexity:** The time complexity of `BFS` and `DFS` is `O(n)` as we need to visit each node once.
* **Space-Complexity:** The space complexity of `BFS` and `DFS` is `O(n)` as the underlying data structure that we use, `Stack` for `DFS` and `Queue` for `BFS` has the same size of tree, which is equivalent to the number of nodes. 

