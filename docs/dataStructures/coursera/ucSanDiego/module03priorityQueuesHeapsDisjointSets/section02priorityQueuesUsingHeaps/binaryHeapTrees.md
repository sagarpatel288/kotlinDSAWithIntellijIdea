# Binary Heap Trees

[Previous: Priority Queue And Naive Implementations: priorityQueues.md](../section01priorityQueuesIntroduction/priorityQueues.md)

## Binary Max Heap Tree

### Definition

![010maxHeapBinaryTreeExample.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/010maxHeapBinaryTreeExample.png)

![020nonBinaryMaxHeapTree.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/020nonBinaryMaxHeapTree.png)

* It is a binary tree where the parent node (the top node of an edge) is always greater than or equal to the child 
  node (the bottom node of the edge).
* And it is a binary tree. It means that a node can have 0, 1, or 2 children.
* A node cannot have more than 2 children.

### Common Operations

* getMax(or peek), insert(or add), extractMax(or poll), changePriority, remove, etc.

### Time Complexity Of Each Operation

#### GetMax (or peek, max)

![030getMaxOfBinaryMaxHeapTree.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/030getMaxOfBinaryMaxHeapTree.png)

* For a binary max heap tree, as per the definition, the root node has the highest value.
* So, we simply return the root node.
* It is `O(1)` time.

#### Insert (or add) = `SiftUp` 

![040insertOfBinaryMaxHeap.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/040insertOfBinaryMaxHeap.png)

* We insert a new element as a leaf.
* It may violate the binary max heap [structure (rules)](#definition).
* So, to ensure that the structure remains valid, we perform the **SiftUp** procedure.
* In the **SiftUp** procedure, we compare the new element with the parent.
* If the parent is smaller than the new element, we swap the elements (or, in other words, their positions or 
  relationships in the tree).
* We repeat this process until we get a [valid structure](#definition).
* We can see that we insert the element as a leaf and travel upwards towards the root.
* So, the time complexity of this operation depends on the tree height.
* Hence, it is `O(tree height)`.

##### How to remember? 

* `add` is `append` = `up` from the `end`!
* Imagine a nurse who has **a syringe or a needle (an injection)**.
  * She **inserts** it into a vein and **pushes the plunger upward**.
* Imagine a **carjacking**.
  * We **insert** the jack under the vehicle's jack point.
  * We use the lever to push the jack arms (lifting arms) **upward** to lift the car. 

#### ExtractMax (or poll) = `SiftDown` 

![060extractMaxOfBinaryMaxHeap.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/060extractMaxOfBinaryMaxHeap.png)

##### How to remember? The story: Story Time

* Imagine that there was a tyrant king with maximum power, position, and ego.
* Pulling down the king became the topmost priority of the people in the kingdom.
* However, people could not pull down the king as long as he was in the highest position.
* Also, people cannot detach the king without a good replacement. Otherwise, the kingdom would've become vulnerable.
* So, people needed a good replacement for the king. 
* People started looking for someone down-to-earth and meeting the bottom-to-top system requirements.
* People found a diamond-like person from the bottom of the system (a leaf).
* To ensure kingdom stability, people pulled the king down to the bottom position (leaf) and exiled (removed) him. 
* Then, people pushed someone (originally, a leaf) from the bottom to the top of the system.
* However, to ensure long-term kingdom stability, people arranged a continuous and competitive process:
  * As long as there is a better option, people would swap the positions.
  * If there are multiple better options, people would select the best one with the highest value.
  * This process ensures that only the most eligible person becomes the new king. 
* Similarly, we replace the top element (root) with the bottom element (leaf).
* Once the element with the highest priority (or value) is at the bottom, we exile (remove) it.
* We push the original and old bottom element (the old, original leaf) to the top.
* Then, if we find that a child is more valuable than the parent node, we swap the positions. 
* If we find that both the children are higher than the parent node, we select the one with the highest value.
* We continue this process.
* This is how we maintain the stability in the kingdom (binary max heap tree).

##### How does `ExtractMax` work?

* We cannot simply detach the root. We need a replacement.
* So, we select a leaf node and swap the positions of the root node and the leaf node.
* Then, we ensure that the current root node has the highest value (priority).
* If it is smaller than any of the two children, we swap the positions.
* If both children are higher than the parent node, we select the one who has the highest value (priority).
* We continue this process until we ensure [a stable structure of the binary max heap tree](#definition).
* As a result of this process, the leaf node might travel all the way from top to bottom.
* The traversal is clearly proportional to the tree height.
* Hence, the time complexity of this process is `O(tree height)`.

##### How to remember? 02

* Remember the nurse from the [insert operation](#insert-or-add--siftup-)?
  * She pulls the plunger downward to extract the blood from the vein. Right?

#### changePriority: Increase = `SiftUp`, Decrease = `SiftDown`.

![070changePriorityBinaryMaxHeap.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/070changePriorityBinaryMaxHeap.png)

* Increasing the priority is like inserting an element. So, we follow the `SiftUp` process.
  * In the `SiftUp` process, we compare the element with the parent node.
  * If the element has a higher value than the parent node, we swap the positions.
  * We continue this process until we get [a stable binary max heap structure](#definition).
* Decreasing the priority is like extracting an element. So, we follow the `SiftDown` process.
  * In the `SiftDown` process, we compare the element with the child node.
  * If the element has a lower value than the child node, we swap the positions.
  * If the element has a lower value than both the children, we swap with the child with the highest value.
  * We continue this process until we get a [stable binary max heap structure](#definition).
* Both the `SiftUp` and `SiftDown` process has `O(tree height)` time complexity.
* Hence, the `changePriority` operation also gets the `O(tree height)` time complexity. 

#### Remove

![080removeBinaryMaxHeap.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/080removeBinaryMaxHeap.png)

##### The Story Time

* Remember the [leaf element](#extractmax-or-poll--siftdown-) that made it to the top of the kingdom?
* What happens if this new king is also (or becomes, or is made) a tyrant? 
* So, this is the second part of the story, ok?
* People identified the root cause of the tyrannical king.
* They found that the king was made tyrannical by a friend of the king.
* People found that person and decided to exile that person from the kingdom.
* But it was not easy. The king would never let it happen. After all, they were friends!
* In this case, people have two problems. The king and the friend.
* So, people executed a clever plan.
* People increased the power and position of the friend so much that the friend became a new king.
* Now, people have only one problem. The new king.
* The king has the highest power.
* So, people called the same [extractMax](#extractmax-or-poll--siftdown-) process.
* And this is how people ensure the stability in the kingdom.

##### Process

* So, similar to the story, we increase the priority of the element we want to remove.
* The priority of the element becomes infinite.
* We increased the priority. So, it follows the **SiftUp** process.
* Then, we follow the **extractMax** process.
* Both the **increasePriority(changePriority)** and the **extractMax** processes have `O(tree height)` time complexity.
* So, the **remove** process also gets the `O(tree height)` time complexity.

### Question: Binary Max Heap Tree Height

![050questionBinaryMaxHeapHeight.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/050questionBinaryMaxHeapHeight.png)