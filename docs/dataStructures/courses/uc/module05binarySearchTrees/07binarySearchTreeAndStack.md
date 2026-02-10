# Binary Search Tree (BST) Traversal: Quick Revision

<!-- TOC -->
* [Binary Search Tree (BST) Traversal: Quick Revision](#binary-search-tree-bst-traversal-quick-revision)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Example](#example)
    * [Depth-First-Search](#depth-first-search)
      * [Depth-First-Search: Pre-Order: Parent(Root)-Left-Right](#depth-first-search-pre-order-parentroot-left-right)
      * [Can a particular order of push and pop operations of a stack produce DFS-Pre-Order traversal of a BST?](#can-a-particular-order-of-push-and-pop-operations-of-a-stack-produce-dfs-pre-order-traversal-of-a-bst)
        * [Pseudocode Of BST-Pre-Order Using A Stack](#pseudocode-of-bst-pre-order-using-a-stack)
        * [How to remember?](#how-to-remember)
      * [Depth-First-Search: In-Order: Left-Parent(Root)-Right](#depth-first-search-in-order-left-parentroot-right)
      * [Can a particular order of push and pop operations of a stack create BST `In-Order` traversal?](#can-a-particular-order-of-push-and-pop-operations-of-a-stack-create-bst-in-order-traversal)
        * [Pseudocode Of BST-In-Order Using A Stack](#pseudocode-of-bst-in-order-using-a-stack)
        * [How to remember?](#how-to-remember-1)
        * [How is it possible that the stack is empty, but we still have a valid `currentNode` to proceed, or the `currentNode` is invalid, but the stack is not empty?](#how-is-it-possible-that-the-stack-is-empty-but-we-still-have-a-valid-currentnode-to-proceed-or-the-currentnode-is-invalid-but-the-stack-is-not-empty)
      * [Depth-First-Search: Post-Order: Left-Right-Parent(Root)](#depth-first-search-post-order-left-right-parentroot)
      * [Can we produce the BST-Post-Order Traversal using a Stack?](#can-we-produce-the-bst-post-order-traversal-using-a-stack)
        * [The Trick](#the-trick)
        * [Pseudocode Of Post-Order Traversal Using A Stack](#pseudocode-of-post-order-traversal-using-a-stack)
        * [How to remember?](#how-to-remember-2)
    * [Breadth-First-Search (Level-By-Level-Top-To-Bottom-Left-To-Right)](#breadth-first-search-level-by-level-top-to-bottom-left-to-right)
  * [Problem Description](#problem-description)
    * [Problem Introduction](#problem-introduction)
    * [Problem Description](#problem-description-1)
      * [Task](#task)
      * [Input Format](#input-format)
      * [Constraints](#constraints)
      * [Output Format](#output-format)
    * [Sample 01](#sample-01)
      * [Input](#input)
      * [Output](#output)
    * [Sample 2](#sample-2)
      * [Input](#input-1)
      * [Output](#output-1)
  * [Code](#code)
  * [Complexity Analysis](#complexity-analysis)
    * [Time Complexity](#time-complexity)
    * [Space Complexity](#space-complexity)
  * [Questions And Answers](#questions-and-answers)
    * [Why using a stack to traverse or validate a binary search tree is better?](#why-using-a-stack-to-traverse-or-validate-a-binary-search-tree-is-better)
  * [ToDos](#todos)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References

* [Trees](../module01BasicDataStructures/section03trees/010trees.md)
* [Tushar Roy: Binary Search Tree-BST: Pre-Order Traversal Using A Stack](https://youtu.be/elQcrJrfObg?si=0mJhLS3Z-k05a6O0)
* [Tushar Roy: Binary Search Tree-BST: In-Order Traversal Using A Stack](https://youtu.be/nzmtCFNae9k?si=6imsFFpH3wHfZzbx)
* [Tushar Roy: Binary Search Tree-BST: Post-Order Traversal Using A Stack](https://youtu.be/xLQKdq0Ffjg?si=3GOfPN1gVtUu7Daz)

## Example

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TB
    n1(("50")) --> n2(("40")) & n3(("60"))
    n2 --> n4(("30")) & n5(("45"))
    n3 --> n6(("55")) & n7(("70"))
    n4 --> n8(("25"))
    n4 --> n9(("35"))
    n5 --> n10(("43"))
    n5 --> n11(("47"))
    n6 --> n12(("53"))
    n6 --> n13(("57"))
    n7 --> n14(("65"))
    n7 --> n15(("80"))
```

### Depth-First-Search

#### Depth-First-Search: Pre-Order: Parent(Root)-Left-Right

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70, 65, 80

---

* Visit the current node eagerly.
* Then, keep going on the left side.
* Cover the left side.
* Then, cover the right side.

---

* Standing at any current node, we can ask, look, and check:
* Did I cover the parent first?
    * Does the parent come first before the left side?
* After me as a parent, do I cover the left side?
* After covering my left side, do I cover the right side?
    * Does the right side come after the left side?

---

#### Can a particular order of push and pop operations of a stack produce DFS-Pre-Order traversal of a BST?

* Yes.
* We start with the root node.
* The current node points to the root node.

1. We push the current node to the stack.
2. We perform the pop operation.
3. If the current node has a right child, we push it to the stack.
4. If the current node has a left child, we push it to the stack.
5. Repeat the steps from 2 to 4 until the stack becomes empty.

![010buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/010buildBstUsingStack.png)

* A stack follows LIFO.
* So, the node we want to process (output, print, etc.) first, should be added last in the stack.
* We start our journey with the root.
* We add it to the stack.

![020buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/020buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  50  |
+------+
```

* And then, we pop it, we get `50`, and we process it.

> 50,

* But after we pop it, we add its right child first, followed by the left child.

![030buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/030buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
+------+
|  40  |
+------+
|  60  |
+------+
```

* We pop it, we get `40`, and we process it.

> 50, 40,

* After we pop it, we add its right child, and then its left child.

![040buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/040buildBstUsingStack.png)

```ascii

|      |
|      |
+------+
|  30  |
+------+
|  45  |
+------+
|  60  |
+------+

```

* We pop `30`, and process it.

> 50, 40, 30

* Then, we add its right child followed by the left child.

![050buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/050buildBstUsingStack.png)

```ascii

|      |
|      |
+------+
|  25  |
+------+
|  35  |
+------+
|  45  |
+------+
|  60  |
+------+

```

* We pop `25`, and process it.

> 50, 40, 30, 25,

* Then, we add its right child followed by the left child.
* It doesn't have any child.
* So, we pop the next element.

![060buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/060buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  35  |
+------+
|  45  |
+------+
|  60  |
+------+

```

* We pop `35`, and process it.

> 50, 40, 30, 25, 35,

* Then, we add its right child followed by its left child.
* But it doesn't have any child.
* So, we pop the next element.

![070buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/070buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  45  |
+------+
|  60  |
+------+
```

* We pop `45`, and process it.

> 50, 40, 30, 25, 35, 45,

* Then, we add its right child followed by its left child.

![080buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/080buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  43  |
+------+
|  47  |
+------+
|  60  |
+------+

```

* We pop `43`, and process it.

> 50, 40, 30, 25, 35, 45, 43,

* Then, we add its right child first, followed by its left child.
* But it doesn't have any child.
* So, we pop the next element.

![090buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/090buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  47  |
+------+
|  60  |
+------+

```

* We pop `47`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47,

* Then, we add its right child, followed by the left child.
* But, it doesn't have any child.
* So, we pop the next element.

![100buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/100buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
+------+
|  60  |
+------+

```

* We pop `60`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60,

* Then, we add its right child, followed by the left child.

![110buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/110buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  55  |
+------+
|  70  |
+------+

```

* We pop `55`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55

* Then, we add its right child, followed by the left child.

![120buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/120buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  53  |
+------+
|  57  |
+------+
|  70  |
+------+

```

* We pop `53`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53,

* Then, we add its right child, followed by the left child.
* But, it doesn't have any child.
* So, we pop the next element.

![130buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/130buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  57  |
+------+
|  70  |
+------+

```

* We pop `57`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57,

* Then, we add its right child, followed by the left child.
* But, it doesn't have any child.
* So, we pop the next element.

![140buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/140buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  70  |
+------+

```

* We pop `70`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70,

* Then, we add its right child, followed by the left child.

![150buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/150buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
+------+
|  65  |
+------+
|  80  |
+------+

```

* We pop `65`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70, 65,

* Then, we add its right child, followed by the left child.
* But, it doesn't have any child.
* So, we pop the next element.

![160buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/160buildBstUsingStack.png)

```ascii

|      |
|      |
|      |
|      |
|      |
+------+
|  80  |
+------+
```

* We pop `80`, and process it.

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70, 65, 80

![170buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/170buildBstUsingStack.png)

* Then, we add its right child, followed by the left child.
* But, it doesn't have any child.
* So, we pop the next element.

![180buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/180buildBstUsingStack.png)

* But, the stack is empty.
* So, we are done.

---

* For the given binary search tree:

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TB
    n1(("50")) --> n2(("40")) & n3(("60"))
    n2 --> n4(("30")) & n5(("45"))
    n3 --> n6(("55")) & n7(("70"))
    n4 --> n8(("25"))
    n4 --> n9(("35"))
    n5 --> n10(("43"))
    n5 --> n11(("47"))
    n6 --> n12(("53"))
    n6 --> n13(("57"))
    n7 --> n14(("65"))
    n7 --> n15(("80"))
```

* The output order we have got using a stack is:

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70, 65, 80

* And it is indeed, `DFS-Pre-Order`.
* Clearly, we can produce the `DFS-Pre-Order` using a stack.

---

##### Pseudocode Of BST-Pre-Order Using A Stack

* Start with the root node.
* Current node is the root node.

1. Push the node to the stack.
2. Pop.
3. Push the right child if any.
4. Push the left child if any.
5. Repeat 2 to 4 until the stack is empty.

```kotlin

stack.addLast(root)
while (stack.isNotEmpty()) {
    val currentNode = stack.removeLast()
    if (currentNode.rightChild != null) {
        stack.addLast(currentNode.rightChild)
    }
    if (currentNode.leftChild != null) {
        stack.addLast(currentNode.leftChild)
    }
}
```

##### How to remember?

* `pre-order` means eagerly push the root.
* Then, inside the while loop:
  * pop
  * push right child first
  * And then push left child
  * We push the left child after the right child so that we can pop the left child first, before the right child to simulate/replicate `Left-Right` part of `Parent(Root)-Left-Right`.

#### Depth-First-Search: In-Order: Left-Parent(Root)-Right

> 25, 30, 35, 40, 43, 45, 47, 50, 53, 55, 57, 60, 65, 70, 80

---

* Did I cover the left side first?
* Only if yes, then cover the current node.
* Then, cover the right side.

---

* Standing at any current node, we can ask, look, and check:
* Did I cover the left side before I cover myself?
    * Left side comes before me.
    * I come after the left side.
* Am I in the middle of my left and right side?
* Do I cover the right side after me?
    * Right side comes after me.

---

#### Can a particular order of push and pop operations of a stack create BST `In-Order` traversal?

* Yes.
* We start with the root.
* The current node is the root.
* Then, we follow the below pattern:

1. If the current node is valid, we push it to the stack.
2. After the push operation, the current node points to the left child.
3. If the current node is invalid (null), we perform the pop operation.
4. After the pop operation, the current node points to the right child.

* The below is the step-by-step visual presentation (illustration).

![200bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/200bstInOrderUsingStack.png)

![210bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/210bstInOrderUsingStack.png)

![220bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/220bstInOrderUsingStack.png)

![230bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/230bstInOrderUsingStack.png)

![240bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/240bstInOrderUsingStack.png)

![250bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/250bstInOrderUsingStack.png)

![260bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/260bstInOrderUsingStack.png)

![270bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/270bstInOrderUsingStack.png)

![280bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/280bstInOrderUsingStack.png)

![290bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/290bstInOrderUsingStack.png)

![300bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/300bstInOrderUsingStack.png)

![310bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/310bstInOrderUsingStack.png)

![320bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/320bstInOrderUsingStack.png)

![330bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/330bstInOrderUsingStack.png)

![340bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/340bstInOrderUsingStack.png)

![350bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/350bstInOrderUsingStack.png)

![360bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/360bstInOrderUsingStack.png)

![370bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/370bstInOrderUsingStack.png)

![380bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/380bstInOrderUsingStack.png)

![390bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/390bstInOrderUsingStack.png)

![400bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/400bstInOrderUsingStack.png)

![410bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/410bstInOrderUsingStack.png)

![420bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/420bstInOrderUsingStack.png)

![430bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/430bstInOrderUsingStack.png)

![440bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/440bstInOrderUsingStack.png)

![450bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/450bstInOrderUsingStack.png)

![460bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/460bstInOrderUsingStack.png)

![470bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/470bstInOrderUsingStack.png)

![480bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/480bstInOrderUsingStack.png)

![490bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/490bstInOrderUsingStack.png)

![500bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/500bstInOrderUsingStack.png)

![510bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/510bstInOrderUsingStack.png)

##### Pseudocode Of BST-In-Order Using A Stack

* We start with the root node.
* The current node points to the root node.

1. As long as the node is valid, keep pushing it to the stack.
2. After each push, the current node points to the left child.  
(We keep going to the left side as long as we can go, and we push each node we visit to the stack along the way.)
3. If the node is invalid (when there is no more left child), perform the pop operation.
4. After the pop, the current node points to the right child.
5. Repeat steps 1 to 4 until the stack is empty.

```kotlin

var currentNode = root
while (currentNode != null || stack.isNotEmpty()) {
    while (currentNode != null) {
        stack.push(currentNode)
        currentNode = currentNode.leftChild
    }
    val poppedNode = stack.pop()
    // After the pop operation, the stack might be empty.
    // But we might still have a right child to push.
    currentNode = poppedNode.rightChild
    // At this point, we don't know whether the currentNode is valid.
}

```

##### How to remember?

* Keep pushing the left children as log as it is possible.
* When there is no more left child, pop and add the right child.
* Repeat.

##### How is it possible that the stack is empty, but we still have a valid `currentNode` to proceed, or the `currentNode` is invalid, but the stack is not empty?

* For example:

![430bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/430bstInOrderUsingStack.png)

* After we pop the node `57`, the `currentNode` points to `null`, but the stack is not empty.
* It means that the `currentNode` is invalid, but the stack is not empty.
* We can see the same pattern for all the leaf nodes as below:

![250bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/250bstInOrderUsingStack.png)

![280bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/280bstInOrderUsingStack.png)

![320bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/320bstInOrderUsingStack.png)

![350bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/350bstInOrderUsingStack.png)

![400bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/400bstInOrderUsingStack.png)

![430bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/430bstInOrderUsingStack.png)

![470bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/470bstInOrderUsingStack.png)

* On the other hand, we can also see the pattern where the stack is empty, but we still have the `currentNode` to proceed.
* For example:

![360bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/360bstInOrderUsingStack.png)

* After we pop the node `50`, the stack is empty, but the `currentNode` points to the right child of `50`, which is `60`.
* So, the stack is empty, but we still have a valid `currentNode` to proceed.
* We can see the same pattern again as shown below:

![440bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/440bstInOrderUsingStack.png)

![480bstInOrderUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/480bstInOrderUsingStack.png)

#### Depth-First-Search: Post-Order: Left-Right-Parent(Root)

> 25, 35, 30, 43, 47, 45, 40, 53, 57, 55, 65, 80, 70, 60, 50

---

* Cover the left side first.
* Then, cover the right side.
* In the end, cover the current parent node of both left and right side.

---

* Standing at any current node, we can ask, look, and check:
* Did I cover the left side first?
* Does the left side is followed by the right side?
    * Does the left side come before the right side?
* Did I cover the right side before me?
    * Does the right side come before me?

---

#### Can we produce the BST-Post-Order Traversal using a Stack?

* Yes. And the trick is:

##### The Trick

**Pre-Order**
> Parent(Root)-Left-Right

**Modified Pre-Order**
> Parent(Root)-Right-Left

**Reversed Modified Pre-Order**
> Left-Right-Parent(Root), which is exactly the Post-Order!

* It means that, we will perform the **modified pre-order** operations.
* Once we finish the process, we reverse the list.
* The reversed list is exactly the **BST-Post-Order-Traversal**.
* Below is the step-by-step visual presentation (illustration).

![610buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/610buildBstUsingStack.png)

![620buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/620buildBstUsingStack.png)

![630buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/630buildBstUsingStack.png)

![640buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/640buildBstUsingStack.png)

![650buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/650buildBstUsingStack.png)

![660buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/660buildBstUsingStack.png)

![670buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/670buildBstUsingStack.png)

![690buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/690buildBstUsingStack.png)

![700buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/700buildBstUsingStack.png)

![710buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/710buildBstUsingStack.png)

![720buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/720buildBstUsingStack.png)

![730buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/730buildBstUsingStack.png)

![740buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/740buildBstUsingStack.png)

![750buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/750buildBstUsingStack.png)

![760buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/760buildBstUsingStack.png)

![770buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/770buildBstUsingStack.png)

![780buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/780buildBstUsingStack.png)

![790buildBstUsingStack.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/790buildBstUsingStack.png)

##### Pseudocode Of Post-Order Traversal Using A Stack

```kotlin

val result = mutableListOf<Int>()
val currentNode = root
stack.push(currentNode)
while (stack.isNotEmpty()) {
    val poppedNode = stack.pop()
    result.add(poppedNode.key)
    if (poppedNode.leftChild != null) {
        stack.push(poppedNode.leftChild)
    }
    if (poppedNode.rightChild != null) {
        stack.push(poppedNode.rightChild)
    }
}
return result.reverse()

```

##### How to remember?

* `p` of `post-order` starts with `p` of `pre-order`.
* `pre-order` is `parent (root) -> left -> right`.
* Modified `pre-order` is `parent (root) -> right -> left`.
* Reverse of `modified pre-order` is `left -> right -> parent (root)`.
* So, we follow the modified `pre-order` and then reverse the result.
* First, we traverse the tree according to the modified `pre-order`.
* So, similar to `pre-order`, we eagerly push the root node into the stack.
* Then, as long as the stack is not empty, we do the following:
* Pop a node from the stack.
* First, Push the left child.
* Then, push the right child.
* We push the right child after the left child so that we can pop the right child first and simulate the `right-left` part of the modified `pre-order`, which is `parent (root) -> right -> left`.
* This is exactly the opposite of the `pre-order` sequence.
* In `pre-order`, we want to simulate `left -> right` part.
* So, we push the right child first, and then the left child.
* Here, we push the left child first, and then the right child to simulate `right -> left` part.

### Breadth-First-Search (Level-By-Level-Top-To-Bottom-Left-To-Right)

> 50, 40, 60, 30, 45, 55, 70, 25, 35, 43, 47, 53, 57, 65, 80

---

* We can produce the BFS output using a queue.

---

## Problem Description

### Problem Introduction

* In this problem, you will implement in-order, pre-order and post-order traversals of a binary tree.
* These traversals were defined in the week 1 lecture on tree traversals, but it is very useful to practice implementing
  them to understand binary search trees better.

### Problem Description

#### Task

* You are given a rooted binary tree.
* Build and output its in-order, pre-order and post-order traversals.

#### Input Format

* The first line contains the number of vertices `𝑛`.
* The vertices of the tree are numbered from `0` to `𝑛 − 1`.
* Vertex `0` is the root.
* The next `𝑛` lines contain information about vertices `0, 1, ..., 𝑛−1` in order.
* Each of these lines contains three integers $key_i$, $left_i$, and $right_i$.
* $key_i$ is the key of the 𝑖-th vertex, $left_i$ is the index of the left
  child of the 𝑖-th vertex, and $right_i$ is the index of the right child of the 𝑖-th vertex.
* If 𝑖 doesn’t have left or right child (or both), the corresponding $left_i$ or $right_i$ (or both) will be equal to
  `−1`.

#### Constraints

* $1 ≤ 𝑛 ≤ 10^5$;
* $0 ≤ key_i ≤ 10^9$;
* $−1 ≤ left_i, right_i ≤ 𝑛 − 1$.
* It is guaranteed that the input represents a valid binary tree.
* In particular, if $left_i \neq -1$ and $right_i \neq −1$, then $left_i \neq right_i$.
* Also, a vertex cannot be a child of two different vertices.
* Also, each vertex is a descendant of the root vertex.

#### Output Format

* Print three lines.
* The first line should contain the keys of the vertices in the in-order
  traversal of the tree.
* The second line should contain the keys of the vertices in the pre-order traversal of the tree.
* The third line should contain the keys of the vertices in the post-order traversal of the tree.

### Sample 01

#### Input

> 5  
> 4 1 2  
> 2 3 4  
> 5 -1 -1  
> 1 -1 -1  
> 3 -1 -1
>

![05buildBst.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/05buildBst.png)

#### Output

> 1 2 3 4 5  
> 4 2 1 3 5  
> 1 3 2 5 4  
> 2
>

### Sample 2

#### Input

> 10  
> 0 7 2  
> 10 -1 -1  
> 20 -1 6  
> 30 8 9  
> 40 3 -1  
> 50 -1 -1  
> 60 1 -1  
> 70 5 4  
> 80 -1 -1  
> 90 -1 -1
>

![07buildBst.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/07buildBst.png)

#### Output

> 50 70 80 30 90 40 0 20 10 60  
> 0 70 50 40 30 80 90 20 60 10  
> 50 80 90 30 40 70 10 60 20 0
>

## Code

[010binarySearchTree.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)

## Complexity Analysis

### Time Complexity

* `O(n)` for each operation, because we visit each node once for every operation.
* So, `Pre-Order` takes `O(n)`.
* `In-Order` takes `O(n)`.
* `Post-Order` takes `O(n)`.

### Space Complexity

* The stack uses `O(h)` where `h` is the height of the binary tree.
* Notice that the maximum size of the stack depends on the tree structure.
* In the worst case, the tree is skewed and `O(h)` = `O(n)`.
* In the best case, the tree is balanced and `O(h)` = `O(log n)`.

## Questions And Answers

### Why using a stack to traverse or validate a binary search tree is better?

## ToDos

* Translate the explanation into code for each step.
  * Possible Reference: [RopeStringSubstringCutPaste.md](77ropeStringSubstringCutPaste.md)
* Bring-in more questions from here:
  * [Print BinarySearchTree.kt](../../../../../src/courses/uc/course02dataStructures/module05binarySearchTrees/010binarySearchTree.kt)

## Next

