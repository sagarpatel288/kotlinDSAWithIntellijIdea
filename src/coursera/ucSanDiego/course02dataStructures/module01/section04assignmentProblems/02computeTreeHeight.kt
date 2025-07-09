package coursera.ucSanDiego.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem-Statement: Compute tree height
 *
 * ## Problem Introduction:
 *
 * * Trees are used to manipulate hierarchical data such as hierarchy of categories of a retailer or the directory
 * structure on your computer.
 * * They are also used in data analysis and machine learning both for hierarchical clustering
 * and building complex predictive models, including some of the best-performing in practice
 * algorithms like Gradient Boosting over Decision Trees and Random Forests.
 * * In the later modules of this course, we will introduce balanced binary search trees (BST) —
 * a special kind of trees that allows to very efficiently store, manipulate and retrieve data.
 * * Balanced BSTs are thus used in databases for efficient storage and actually in virtually any non-trivial programs,
 * typically via built-in data structures of the programming language at hand.
 * * In this problem, your goal is to get used to trees.
 * * You will need to read a description of a tree from the input, implement the tree data structure,
 * store the tree and compute its height.
 *
 * ## Problem Description:
 *
 * * **Task.**
 *
 * * You are given a description of a rooted tree.
 * * Your task is to compute and output its height.
 * * Recall that the height of a (rooted) tree is the maximum depth of a node, or the maximum distance from a
 * leaf to the root.
 * * You are given an arbitrary tree, not necessarily a binary tree.
 *
 * * **Input Format.**
 *
 * * The first line contains the number of nodes `𝑛`.
 * * The second line contains `𝑛` integer numbers from `−1 to 𝑛 − 1` — parents of nodes.
 * * If the `𝑖-th` one of them (`0 ≤ 𝑖 ≤ 𝑛 − 1`) is `−1`, node `𝑖` is the root,
 * otherwise it’s 0-based index of the parent of 𝑖-th node.
 * * It is guaranteed that there is exactly one root.
 * * It is guaranteed that the input represents a tree.
 *
 * * **Constraints.**
 * ```
 * 1 ≤ 𝑛 ≤ 10^5
 * ```
 *
 * * **Output Format.**
 *
 * * Output the height of the tree.
 *
 * ** Sample 1.**
 *
 * * Input:
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 * * Output:
 * ```
 * 3
 * ```
 * * The input means that there are 5 nodes with numbers from 0 to 4, node 0 is a child of node 4, node 1
 * is the root, node 2 is a child of node 4, node 3 is a child of node 1 and node 4 is a child of node 1.
 * * To see this, let us write numbers of nodes from 0 to 4 in one line and the numbers given in the input in
 * the second line underneath:
 * ```
 * 0  1 2 3 4
 * 4 -1 4 1 1
 * ```
 * * Now we can see that the node number 1 is the root, because −1 corresponds to it in the second line.
 * * Also, we know that the nodes number 3 and number 4 are children of the root node 1.
 * * Also, we know that the nodes number 0 and number 2 are children of the node 4.
 *
 * ** Sagar: More Formal Explanation: **
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 *
 * * The first line represents number of nodes: 5
 * * The second line represents the parent of `ith` node.
 * * So, for example: The parent of first node (0th node because we have been told that it is 0-based index) is index 4.
 * ```
 * Index 4 is the parent of index 0.
 * ```
 * So, we can note down it as:
 * ```
 * |         Total Nodes        	| 5 	|    	|   	|   	|   	|
 * |:--------------------------:	|:-:	|----	|---	|---	|---	|
 * |        The ith Node        	| 0 	|    	|   	|   	|   	|
 * | The parent of the ith Node 	| 4 	| -1 	| 4 	| 1 	| 1 	|
 * ```
 * ```
 * ┌─────┐
 * │  4  │
 * └─────┘
 *    │
 *    │
 *    │
 * ┌─────┐
 * │  0  │
 * └─────┘
 * ```
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 * * The parent of 2nd node (index 1) is -1 (it means that the second node here, index 1 node is the root node).
 * ```
 * Index 1 is the root node.
 * ```
 * * So, we can note down it as below:
 * ```
 * |         Total Nodes        	| 5 	|                   	|   	|   	|   	|
 * |:--------------------------:	|:-:	|-------------------	|---	|---	|---	|
 * |        The ith Node        	| 0 	| 1 <br>(Root Node) 	|   	|   	|   	|
 * | The parent of the ith Node 	| 4 	|         -1        	| 4 	| 1 	| 1 	|
 * ```
 * ```
 * ┌─────┐
 * │  1  │
 * └─────┘
 * ```
 * * Next, the parent node of index 2.
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 * * The parent of index 2 node is index 4 node.
 * * It means that the 0th index node and the 2nd index node are siblings. And their parent index node is 4.
 *
 * ```
 * Index 4 is the parent of index node 0 and index node 2.
 * ```
 * * So, we can note down it as below:
 * ```
 * |         Total Nodes        	| 5 	|                   	|   	|   	|   	|
 * |:--------------------------:	|:-:	|-------------------	|---	|---	|---	|
 * |        The ith Node        	| 0 	| 1 <br>(Root Node) 	| 2 	|   	|   	|
 * | The parent of the ith Node 	| 4 	|         -1        	| 4 	| 1 	| 1 	|
 * ```
 * ```
 *        ┌─────┐
 *        │  4  │
 *        └─────┘
 *        /      \
 *       /        \
 *      /          \
 *     /            \
 * ┌─────┐        ┌─────┐
 * │  0  │        │  2  │
 * └─────┘        └─────┘
 *
 * ```
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 * * The parent of the index 3 node is index 1.
 * * And if we remember, index 1 node is the root node.
 * ```
 * Index 1 is the parent of index node 3. And remember that index 1 is the root node.
 * ```
 * * We can note down it as below:
 * ```
 * |         Total Nodes        	| 5 	|                   	|   	|   	|   	|
 * |:--------------------------:	|:-:	|-------------------	|---	|---	|---	|
 * |        The ith Node        	| 0 	| 1 <br>(Root Node) 	| 2 	| 3 	|   	|
 * | The parent of the ith Node 	| 4 	|         -1        	| 4 	| 1 	| 1 	|
 * ```
 *
 * ```
 *        ┌─────┐                   ┌─────┐
 *        │  4  │                   │  1  │
 *        └─────┘                   └─────┘
 *        /      \                     │
 *       /        \                    │
 *      /          \                   │
 *     /            \               ┌─────┐
 * ┌─────┐        ┌─────┐           │  3  │
 * │  0  │        │  2  │           └─────┘
 * └─────┘        └─────┘
 *
 * ```
 * ```
 * 5
 * 4 -1 4 1 1
 * ```
 * * Index 1 is the parent of the index node 4.
 * * Recall that index 1 is also the parent of the index node 3.
 * * It means that index node 1 is a parent of index nodes 3 and 4.
 * * So, index nodes 3 and 4 are siblings.
 * * And index node 4 is a parent of index nodes 0 and 2.
 * * We can note down it as below:
 * ```
 * |         Total Nodes        	| 5 	|                        	|   	|   	|   	|
 * |:--------------------------:	|:-:	|------------------------	|---	|---	|---	|
 * |        The ith Node        	| 0 	|      1 <br>(Root Node) 	| 2 	| 3 	| 4 	|
 * | The parent of the ith Node 	| 4 	|           -1           	| 4 	| 1 	| 1 	|
 * ```
 * * So, it becomes:
 * ```
 *         ┌─────┐
 *         │  1  │
 *         /─────\
 *        /       \
 *       /         \
 *      /           \
 *     /             \
 * ┌─────┐         ┌─────┐
 * │  3  │         │  4  │
 * └─────┘         └─────┘
 *                 /      \
 *                /        \
 *               /          \
 *              /            \
 *          ┌─────┐        ┌─────┐
 *          │  0  │        │  2  │
 *          └─────┘        └─────┘
 *
 *
 * ```
 * * The height of this tree is 3, because the number of vertices (nodes) on the path from root 1 to leaf 2 is 3.
 *
 * * **Sagar Notes:**
 * * Vertices = Nodes.
 * * For height, Some resources count the edges between the target node (root node in our case), and the leaf node that
 * forms the longest path.
 * * Whereas some resources can count the nodes (inclusive) between the target node and the leaf node that forms the
 * longest path.
 * * We should be flexible enough to adopt and manage the given definition.
 *
 * **Sample 2.**
 *
 * *Input:*
 * ```
 * 5
 * -1 0 4 0 3
 * ```
 * *Output:*
 * ```
 * 4
 * ```
 * *Explanation:*
 *
 * - The input means that there are 5 nodes with numbers from 0 to 4, node 0 is the root, node 1 is a child
 * of node 0, node 2 is a child of node 4, node 3 is a child of node 0 and node 4 is a child of node 3.
 * - The height of this tree is 4, because the number of nodes on the path from root 0 to leaf 2 is 4.
 *
 * ** Sagar: More Formal Explanation:**
 *
 * ```
 * Total Nodes: 5
 * iTh Node:            0 1 2 3 4
 * Parent of ith Node: -1 0 4 0 3
 * ```
 * * 0th node is a root node.
 * * 1 and 3 are children of the 0th node.
 * * Or in other words, 0th node has two children: 1 and 3.
 * * So, it becomes:
 * ```
 *         ┌─────┐
 *         │  0  │
 *         /─────\
 *        /       \
 *       /         \
 *      /           \
 *     /             \
 * ┌─────┐         ┌─────┐
 * │  1  │         │  3  │
 * └─────┘         └─────┘
 *
 * ```
 * * 4 is the parent of 2.
 * * 3 is the parent of 4. It means, 3 has one child, 4. And as per the above statement, 4 has one child 2.
 * * So, it becomes:
 * ```
 *         ┌─────┐
 *         │  0  │
 *         /─────\
 *        /       \
 *       /         \
 *      /           \
 *     /             \
 * ┌─────┐         ┌─────┐
 * │  1  │         │  3  │
 * └─────┘         └─────┘
 *                    │
 *                    │
 *                    │
 *                    │
 *                 ┌─────┐
 *                 │  4  │
 *                 └─────┘
 *                    │
 *                    │
 *                    │
 *                    │
 *                 ┌─────┐
 *                 │  2  │
 *                 └─────┘
 *
 * ```
 *
 *
 * ## Thought Process:
 *
 * * [Pre-requisites: Trees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7453412a5be96aabf3394a77bbc5763c995a509f/docs/dataStructures/module01/section03trees/trees.md)
 * * We need to build (construct) a tree from the given data.
 * * Then, we need to find the distance between the root node (for that, we need to know the root node)
 * and the leaf node that forms the longest path. (For that, we may need to remember the path for each variation.)
 * * To find the distance between the root node and the leaf node that forms the longest path,
 * we need to travel the tree.
 * * We can travel a tree in two ways: DFS and BFS.
 * * DFS uses stack that can cause overflow.
 * * Hence, we will go with BFS.
 * * In BFS, we use a queue, and we travel level by level.
 * * To travel level-by-level, we need to know what is the next level (children).
 * * We have given parents of each index. Using this, we need to build the root and the children information.
 * * For example: Once we know the root node and its direct children, that will be next level. And then comes children
 * of the children which covers the next level. And so on...
 * * So basically, we need to know the root node and children of each node.
 *
 * * **How do we prepare children information?**
 * * **How can we know what are the children of a particular parent?**
 *
 * * Let us look at the given sample:
 * ```
 * Total Nodes: 5
 * iTh Node:            0 1 2 3 4
 * Parent of ith Node: -1 0 4 0 3
 * ```
 * * We travel through the given parent information.
 * * If the value is -1, then the index is the root node.
 * * Otherwise, the index is a child of the value.
 * * So, we add the index as a child for the given value.
 *
 * * **Where do we add? How do we add?**
 *
 * * We create a list of list, where each item can have multiple items as children.
 * * It means each item acts a parent, and we store the corresponding list of children for that parent.
 *
 * * **Confusing? Let us see it.**
 * ```
 * Create a list of size n where each item is also a mutable list to which we can add items.
 * = List(n) { mutableListOf<Int>() }
 * ```
 * * **Why did we take a fixed size of immutable `List(n)`?**
 *
 * * Because we treat the index of this outer list as a parent node, and we have given a fixed size of total nodes.
 * * We are not going to add more nodes than what we have been given.
 *
 * * **Why did we take the inner list as a mutable list?**
 *
 * * Because we add children as we iterate through the parent list.
 * * We don't have all the children information for each parent on hand.
 * * Otherwise, we would not have to create such a nested list (known as adjacency list).
 *
 * * **What does it mean by iterating through the parent list? What is the parent list? Where is it?**
 *
 * * Let us see that:
 * ```
 * val totalNodes = readln().toInt()
 * val parentList = readln().split(" ").map { it.toInt() }.toList() //This is the list of parents
 * ```
 *
 * * **How do we use this list of parents?**
 *
 * * Let us see that:
 * ```
 * Total Nodes: 5
 * iTh Node:            0 1 2 3 4
 * Parent of ith Node: -1 0 4 0 3
 * ```
 * * We can see that each **index** is a child of the **item value** unless the value is -1.
 * * For example, the value at 0th index is -1. So, the node 0 is the root node.
 * * Note 1. Next, the value at index 1 is 0. It means, 0 is the parent of 1. 1 is the child of 0. -----------------(1)
 * * Next, the value at index 2 is 4. It means, 4 is the parent of 2. 2 is the child of 4.
 * * Note 2. Next, the value at index 3 is 0. It means, 0 is the parent of 3 (also). 3 is the child of 0. ----------(2)
 * * Next, the value at index 4 is 3. It means, 3 is the parent of 4. 4 is the child of 3.
 *
 * * If we observe the note 1 and 2, we can see that children information is scattered.
 * * So, we iterate through this list of parents.
 * ```
 * val parentList = readln().split(" ").map { it.toInt() }.toList()
 * for (child in 0..<parentList.size) {
 *     val parent = parentList[child]
 * }
 * ```
 * * So, we got the parent of a particular child.
 *
 * * **But, how can we know how many children does this parent has?**
 *
 * * We know that a parent can have multiple children.
 * * So, we create this list of lists.
 * ```
 * val parentChildrenList = List(n) { mutableListOf<Int>() }
 * ```
 * * Here, the index of the outer list acts a parent.
 * * Because if we observe, the value of a parent cannot go beyond the given `n` and it will be between `-1 to n-1`
 * as given in the problem statement and as we can see again:
 * ```
 * Total Nodes: 5
 * iTh Node:            0 1 2 3 4
 * Parent of ith Node: -1 0 4 0 3 //The value of a parent cannot go beyond the given `n`.
 * ```
 *
 * * So, the index of outer list acts as a parent, and we would add children to it.
 *
 * * **How? How do we add children to parent?**
 *
 * * Let us see that.
 *
 * ```
 * val n = readln().toInt() // The total number of nodes.
 * val parentList = readln().split(" ").map { it.toInt() }.toList()
 * val parentChildrenList = List(n) { mutableListOf<Int>() }
 * var root = 0
 * for (child in 0..<parentList.size) {
 *     val parent = parentList[child]
 *     if (parent != -1) {
 *         parentChildrenList[parent].add(child)
 *     } else {
 *         root = child
 *     }
 * }
 * ```
 *
 * * If we notice, the index of the `parentChildrenList` is a parent that holds the value in the form of `List of children`.
 * * Again, the index of the `parentChildrenList` is a parent, and the value is `List of children` for that parent.
 * * Now that we know how many children a particular parent node has, we can travel level-by-level.
 *
 * * **Sounds irrelevant and disconnected?**
 *
 * * Let us understand.
 * * Recall the BFS traversal of a tree. [BFS Traversal In A Tree](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7453412a5be96aabf3394a77bbc5763c995a509f/docs/dataStructures/module01/section03trees/trees.md)
 * * We add a root node to a queue.
 * * And then we run a while loop.
 * * We run the while loop as long as the queue is not empty.
 * * Inside the while loop, we deque the node.
 * * And add its children. Yes, we add the children of dequeued node.
 * * We continue the process.
 * * And it forms the level-by-level traversal.
 *
 * * **How does this `BFS Traversal In A Tree` concept helps us here?**
 *
 * * So, the idea is:
 * * We have this `parentChildrenList`.
 * * In the `parentChildrenList`, each index represents a parent along with the information of its children.
 * * Let us assume that we start with the root node, we add it to the queue.
 * * Now, the size of this root node is obviously 1.
 * * So, we will make a loop. (Yes, a loop inside a loop.)
 * * And this inner loop will repeat a process `s` times, where `s` is the size of the current item in the queue.
 * * For example, when the root node is in the queue, the queue size is 1.
 * * So, it will repeat a process 1 time.
 *
 * * **What is the process?**
 *
 * * The process is, remove the current item from the queue.
 * * And add its children to the queue. Yes, we add children of the dequeued node.
 *
 * * **From where do we get the children of the dequeued node?**
 *
 * * From the `parentChildrenList`.
 * * First, we add the `root` node to the `queue`.
 * * And this root node we have found from the early process.
 * * Let us see it again:
 *
 * ```
 * val n = readln().toInt()
 * val parentList = readln().split(" ").map { it.toInt() }.toList()
 * val parentChildrenList = List(n) { mutableListOf<Int>() }
 * var root = 0 // <------------------------------------------ We update it as below:
 * for (child in 0..<parentList.size) {
 *     val parent = parentList[child]
 *     if (parent != -1) {
 *         parentChildrenList[parent].add(child) // Add the child to this parent.
 *     } else {
 *         root = child // <---------------------------------- Here, we find the root node.
 *     }
 * }
 * ```
 *
 * * **So, How do we find the children of the root node and add it to the queue?**
 *
 * * Let us see that.
 *
 * ```
 * val queue = ArrayDeque<Int>()
 * queue.addLast(root)
 * while (queue.isNotEmpty()) {
 *     val size = queue.size
 *     repeat(size) {
 *         val dequeued = queue.removeLast()
 *         queue.addAll(parentChildrenList[dequeued]) // We add children of the dequeued parent node.
 *     }
 * }
 * ```
 *
 * * So, the `root` node is a `parent` node.
 * * And it is stored as an index in the `parentChildrenList`.
 * * It holds the value in the form of a `children list`.
 * * So, we extract and add children of the dequeued node by passing the dequeued node as an index to the
 * `parentChildrenList`.
 *
 * * **Ok, But how does this help in calculating the height of the tree?**
 *
 * * Well, the idea is:
 * * Each time we get out of the inner loop, we go to the next level.
 * * And as we go the next level, we increase the height.
 * * We can understand this better with visualization.
 *
 * * **Let us assume that we have the following tree:**
 *
 *```
 * Level-1
 * -------------->                  Les
 *                                 /   \
 *                                /     \
 * Level-2                       /       \
 * -------------->          Cathy         Sam
 *                          /   \         /   \
 * Level-3                 /     \       /     \
 * -------------->     Alex    Frank  Nancy   Violet
 *                                             /    \
 * Level-4                                    /      \
 * -------------->                         Tony     Wendy
 *
 *
 * ```
 * * Take the initial height as 0.
 * * Now, we add `Les` (which is the root node) to the queue.
 * * We start the while loop.
 * ---------------------------------
 * * The condition is: Continue the while loop as long as the queue is not empty.
 * * We go inside the while loop.
 * * We get the size of the queue as 1.
 * * Now, we have an inner loop. We repeat the following process 1 time.
 *
 * * **1st time:**
 *
 * * Dequeue the item. So, we get `Les`.
 * * Add its children. We get the children from the `parentChildrenList`.
 * * The children `Cathy` and `Sam` are in the queue now.
 * * We exit the inner loop. Notice that we just have finished the 1st level.
 * * So, we increase the height by 1. Hence, height = 1.
 * ---------------------------------
 * * Now, the while loop checks whether the queue is empty.
 * * The queue is not empty as we have added the children of `Les` earlier.
 * * We go inside the while loop.
 * * We get the size of the queue as 2.
 * * So, we will repeat the inner loop 2 times.
 *
 * * **1st time:**
 *
 * * Dequeue the item. So, we get `Cathy`.
 * * Add children of `Cathy`. We get the children from the `parentChildrenList`.
 * * The children are `Alex` and `Frank`. We add them into the queue.
 * * So, the queue has: `Sam, Alex, Frank`.
 *
 * * **2nd time:**
 *
 * * Deque the item. So, we get `Sam`.
 * * Add children of `Sam`. We get the children from the `parentChildrenList`.
 * * The children are `Nancy` and `Violet`. We add them into the queue.
 * * So, the queue has: `Alex, Frank, Nancy, Violet`.
 * * We exit the inner loop. Notice that we just have finished the 2nd level.
 * * So, we increase the height by 1. Hence, height = 2.
 * ---------------------------------
 * * Now, the while loop checks whether the queue is empty.
 * * The queue is not empty as it has: `Alex, Frank, Nancy, Violet`.
 * * We go inside the while loop.
 * * We get the size of the queue as 4.
 * * So, we will repeat the inner loop 4 times.
 *
 * * **1st time:**
 *
 * * Dequeue the item. So, we get `Alex`.
 * * Add children of `Alex`.
 * * So, we get an empty list of children from the `parentChildrenList`.
 * * Now, the queue has: `Frank, Nancy, Violet`.
 *
 * * **2nd Time:**
 *
 * * Deque the item. So, we get `Frank`.
 * * Add children of `Frank`.
 * * So, we get an empty list of children from the `parentChildrenList`.
 * * Now, the queue has `Nancy, Violet`.
 *
 * * **3rd Time:**
 *
 * * Deque the item. So, we get `Nancy`.
 * * Add children of `Nancy`.
 * * So, we get an empty list of children from the `parentChildrenList`.
 * * Now the queue has `Violet`.
 *
 * * **4th Time:**
 *
 * * Dequeue the item. So, we get `Violet`.
 * * Add children of `Violet`.
 * * So, we get a list of children from the `parentChildrenList`.
 * * We add these children to the queue.
 * * The queue has: `Tony, Wendy`.
 * * We exit the inner loop. Notice that we just have finished the 3rd level.
 * * So, we increase the height by 1. Hence, height = 3.
 * ---------------------------------
 * * Now, the while loop checks whether the queue is empty.
 * * The queue is not empty as it has: `Tony, Wendy`.
 * * We go inside the while loop.
 * * We get the size of the queue as: 2.
 * * So, we will repeat the inner loop 2 times.
 *
 * * **1st Time:**
 *
 * * Dequeue the item. So, we get `Tony`.
 * * Add children of `Tony`.
 * * So, we get an empty list of children from the `parentChildrenList`.
 * * Now, the queue has `Wendy`.
 *
 * * **2nd Time:**
 *
 * * Dequeue the item. So, we get `Wendy`.
 * * Add children of `Wendy`.
 * * So, we get an empty list of children from the `parentChildrenList`.
 * * Now, the queue is empty.
 * * We exit the inner loop. Notice that we just have finished the 4th level.
 * * So, we increase the height by 1. Hence, height = 4.
 * ---------------------------------
 * * Now, the while loop checks whether the queue is empty.
 * * The queue is empty.
 * * We return the height.
 *
 * * **Let us write the code of this part:**
 *
 * ```
 * val queue = ArrayDeque<Int>()
 * var height = 0
 * queue.addLast(root)
 * while (queue.isNotEmpty()) {
 *     val size = queue.size
 *     repeat(size) {
 *         val dequeued = queue.removeFirst()
 *         queue.addAll(parentChildrenList[dequeued])
 *     }
 *     // As we exit the inner loop, increase the height by 1.
 *     height++
 * }
 * return height
 * ```
 *
 * # Time Complexity:
 *
 * * We travel the parent list once. The size of the parent list is `n`, where `n` is the total number of nodes.
 * * So, it is `O(n)`.
 * * Then, we have this while loop, that also runs `n` times by covering each node at each level.
 * * So, it is also `O(n)`.
 * * The total time complexity becomes `O(2n)`.
 * * But, we drop (ignore) the constants in complexity analysis.
 * * Why? Because the constant becomes insignificant as `n` grows larger.
 * * So, the net total time complexity is `O(n)`.
 *
 * # Space Complexity:
 *
 * * We have a parent list of size `n`, where `n` is the total number of nodes.
 * * We have a `parentChildrenList` of size `n`.
 * * We have a `queue` of size `n`.
 * * So, it becomes like `O(n + n + n)` = `O(3n)`.
 * * But again, we drop (ignore) the constants in complexity analysis.
 * * Why? Because the constant becomes insignificant as `n` grows larger.
 * * So, the net total space complexity is `O(n)`.
 *
 * # Coursera's Grader Output:
 *
 * ```
 * Good job! (Max time used: 0.47/2.00, max memory used: 87289856/536870912.)
 * ```
 *
 *
 */
fun computeTreeHeight(totalNodes: Int, parentList: List<Int>): Int {
    val parentChildrenList = List(totalNodes) { mutableListOf<Int>() }
    var root = 0
    // Use `until` while submitting to coursera to avoid the compiler error!
    for (child in 0 ..<parentList.size) {
        val parent = parentList[child]
        if (parent != -1) {
            parentChildrenList[parent].add(child)
        } else {
            root = child
        }
    }
    val queue = ArrayDeque<Int>()
    var height = 0
    queue.addLast(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        repeat(size) {
            val dequeued = queue.removeFirst()
            queue.addAll(parentChildrenList[dequeued])
        }
        height++
    }
    return height
}

fun main() {
    val totalNodes = readln().toInt()
    val parents = readln().split(" ").map { it.toInt() }.toList()
    println(computeTreeHeight(totalNodes, parents))
}