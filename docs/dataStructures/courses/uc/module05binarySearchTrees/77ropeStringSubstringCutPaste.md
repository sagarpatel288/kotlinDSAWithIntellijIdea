# Problem

* A string `S` of length `n` is given.
* There are `q` queries.
* Each query has three integers: `i`, `j`, and `k`.
* `i` and `j` represent the start and end indices of a substring of `S` respectively.
* Cut the substring.
* Paste the substring at the `k-th` position.
* `1 <= |S| <= 300000`
* `1 <= q <= 100000`

## Thought Process
 
* A `String`, `StringBuilder`, `Substring`, and `append` stores characters in a contiguous array.
* To cut a substring, and to paste a substring, it travels through the array.
* To cut a substring, it travels from the start index to the end index.

![1050ropeStringCutPasteNaive.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1050ropeStringCutPasteNaive.png)

* So, it is `O(length of the substring)` operation.
* To cut the substring, we need to perform the `remove` operation that removes the substring from the original string.
* When we remove the substring, all the characters after the substring are shifted to the left.
* It takes `O(string length n - substring end index + 1)`, that is a linear time operation.
* And when we paste the substring, it can again cause shifting.
* That is another linear time operation.
* If the string length is `300000` and there are `100000` queries, it will take `300000 * 100000 = TLE` time!

### Perspective

* In the previous lecture, we saw that we cannot use a contiguous data structure.  
* 
**What else can we use?**  
* 
* What are we doing here? What operations do we perform here?
* Cut and paste. 
* How are we supposed to do it? What process can do it?
* A `String` contains characters.
* These characters are arranged in a particular order (sequence).
* To cut (remove) a substring, we have the start and end indices of the substring.
* The characters that we want to remove are between these start and end indices (range).
* In that sense, a substring is a set of characters within a particular range.
* We remove (delete) this substring from the original string.
* And then, we paste (insert) the substring at the given position.
* The delete and insert operations change the order of the characters.
* We can say that we are keep changing the order of the characters.
* We keep manipulating the order of the characters.
* So, it is a sequence or order manipulation problem.
* We keep re-ordering the characters.
* This re-ordering is not an automatic consequence, like shifting in an array.
* This re-ordering is a controlled re-ordering, where we need to establish and maintain a particular order.

**Which data structure keeps things in order?**  
**Which data structure do we use to perform deletion, insertion, and controlled re-ordering efficiently?**

* When the `data` has a particular order, and we want to perform deletion, insertion, and re-ordering efficiently, we use an ordered data structure.
* Trees are ordered data structures.
* An ordered data structure uses a `key` (as a `value`) to compare and arrange the data.
* What if we consider a `character` as a `key` of a `node`?
* It becomes a value based ordered data structure.
* Trees are good value based ordered data structure that supports fast insert and fast delete.
* But in a normal tree, we need to travel more to find a specific character.
* That applies to a binary tree, too.
* If we use a `Binary Search Tree`, it can be skewed.
* So, we look for the self-balancing binary search tree.
* An AVL-Tree is a strict self-balancing binary search tree.
* It means that it requires more rotations to balance itself.
* A Splay-Tree is a flexible self-balancing binary search tree.
* It means that it requires fewer rotations to balance itself.
* So, we use a Splay-Tree.  
* 
**But, how do we use a character as a key?**   
**How do we use the character key to navigate and traverse the tree?**

* In a binary search tree, we find a particular node by comparing the value of the node with the value of the root or current node.
* If `currentNode.key > target.key`, we go to the left side of the current node.
* If `currentNode.key < target.key`, we go to the right side of the current node.
* If `currentNode.key == target.key`, we have found the target node.
* So, we compare the `key` value and traverse the tree.
* The comparison based traversal works because we know that if it is a valid binary search tree, then `node.left.key < node.key < node.right.key`.
* And we also know that if it is a valid binary search tree, then the `in-order` traversal of the tree will give us the sorted order (ascending) of the keys.

```markdown

+----+---+---+---+---+---+----+
|  1 | 2 | 3 | 4 | 5 | 6 | 7  |
+----+---+---+---+---+---+----+

```

* Now, let us simply replace these numbers with characters.

```markdown

┌────│───│───│───│───│───│────┐
│    │   │   │   │   │   │    │
│  a │ b │ c │ d │ e │ f │ g  │
│    │   │   │   │   │   │    │
└────│───│───│───│───│───│────┘

```

* We compare the `key` value to find the target node.
* But we can do it in a different way as well using an additional property called `size`.
* For example, let us give each character the `size` property. 

```markdown

             ┌────│───│───│───│───│───│────┐
             │    │   │   │   │   │   │    │
             │  a │ b │ c │ d │ e │ f │ g  │
             │    │   │   │   │   │   │    │
             ┌────│───│───│───│───│───│────┐
             │    │   │   │   │   │   │    │
Left size    │  0 │ 1 │ 2 │ 3 │ 4 │ 5 │ 6  │
             │    │   │   │   │   │   │    │
             └────│───│───│───│───│───│────┘

```

* Now, each character knows how many characters are on its left side.
* For example, `a` knows that there are `0` characters on its left side.
* `b` knows that there are `1` characters on its left side.
* `c` knows that there are `2` characters on its left side, and so on.
* Now, whether we say "Character at 4th index," or we say the "5th Character," or we say "The character before which there are 4 characters," it is the same thing.
* When we say "Character at 4th index," we focus on the current index of the node and traverse accordingly.
* And when we say "The node before which there are 4 characters," we focus on the `node.left.size` property of the current node and traverse accordingly.
* Suppose that the root node is `d`, and we want to find the `4th` character.
* We know that `d` has `3` characters on its left side.
* So, `d` is the `4th` character.
* So, if `left size + 1` is equal to the target position, then the current node is the target node.
* Now, suppose that we want to find the `2nd` character.
* We are at `d`.
* `d` has `3` characters on its left side.
* So, if `left.size + 1` is greater than the target position, then we go to the left side of the current node.
* Now, suppose that we want to find the `6th` character.
* We are at `d`.
* `d` has `3` characters on its left side.
* So, if `left.size + 1` is less than the target position, then we go to the right side of the current node.
* So, the conclusion is that if we know the `size` property of each node, then we can find the `kth` character using the `left.size` formula.

![1055bstKthSmallestKey.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1055bstKthSmallestKey.png)


![1065ropeStringCutPasteSplayTree.webp](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1065ropeStringCutPasteSplayTree.webp)


* The formula for the `size` property is `size = 1 + leftSize + rightSize`.
* This is something we have learned in the previous module:
  * [Reference: Kth Smallest Element in a BST](50avlTreeFindKthSmallKey.md)
---
* Now, if we want to find the `14th` key, and if we start from the root node, we know that the `14 > 8`, so we go to the right side of the root node.
* `8.right` is `12` and `14 > 12`, so we go to the right side of `12`.
* `12.right` is `14` and that is our target node.
* Notice that we compare the position for the traversal and not the `key` value.
* We still need to convert this idea into code.
---
* We can find a node using the `size` property also.
* We want to find the `14th` key.
* We start from the root.
* So, `node = root`.
* The `node.left.size + 1` = `7 + 1` = `8`.
* So, it is clear that the `14th` key cannot be in the left subtree of the root node.
* Because the total number of keys in the left subtree of the root node is `7` only, and `14 > 7`.
* So, we go to the right side of the root node.
* We follow the same procedure.
* The total number of keys (nodes) we have checked are: `node.left.size + 1 = 8`.
* The `current node` becomes: `node = node.right = h.right = l`.
* The new `k` becomes: `k - 8 = 14 - 8 = 6`.
* For the current node, `l.left.size + 1` = `3 + 1` = `4`.
* So, it is clear that the key we are looking for cannot be in the left subtree of `i`.
* Because the total number of keys in the left subtree of `l` is `3` only, and `6 > 3`.
* So, we go to the right side of `l`.
* We follow the same procedure.
* The total number of keys (nodes) we have checked are: `node.lef.size + 1 = 3 + 1 = 4`.
* The `current node` becomes: `node = node.right = l.right = n`.
* The new `k` becomes: `k - 4 = 6 - 4 = 2`.
* For the current node, `n.left.size + 1 = 1 + 1 = 2`.
* It means that the key we are looking for is the current node `n`.
---

**Perspective**

* Characters in a string are in a particular order.
* We can represent the string using the `in-order` traversal of a binary search tree.
* The binary search tree is not an index based data structure.
* But, we can know how many characters are in left side of a particular character using the `size` property.
* This gives us implicit index behavior that we can use to find a character.
* The number of characters before a particular character in a string is equal to the `node.left.size` (the number of nodes in its left subtree) in the `in-order` traversal of the binary search tree.
* If it is a leaf node or a right node, then we consider and include ancestors.
* For example, `c` is the leaf node, and it is the right node of `b`.
* It does not have any left subtree.
* But, the size of the `parent.left.size = 1 + parent (1) = 2`.
* So, the number of characters before `c` is `2`.

**Perspective**

* We treat the given original string structure as a valid binary search tree.

![1090ropeStringCutPasteSplayTree.webp](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1090ropeStringCutPasteSplayTree.webp)

* Once we set up the binary search tree, the cut-and-paste operations become "split" and "merge" operations of a splay tree.
* In this way, we take less time to find a node, or multiple nodes in the given range.

## How do we form the initial binary search tree? Do we have to perform many insertions?

* Each insertion is `O(log n)` time in a splay tree.
* So, if the length of the string is `n`, then the time complexity is `O(n log n)`.
* Now, we get the original input string in one shot.
* We are not getting the original input string character by character.
* So, we don't have to perform insertion per character.
* We already know the order of the characters in the original input string.
* We already know the middle character of the original input string.
* So, we get the middle character of the original input string, and make it the root node.
* All the characters before the middle character go to the left subtree.
* All the characters after the middle character go to the right subtree.
* We update the size of the root node.
* We do the same for the left and right subtrees.
* The input keeps changing, but the process remains the same.
* So, this is a recursive process.
* We access each character in `O(1)`.
* And then, we update the pointers and properties like `size`, which is `O(1)`.
* So, if the length of the string is `n`, then the time complexity of building the BST is `O(n)`.

### Perspective: Building the BST

* This is like building a binary search tree from a sorted array.

### Pseudocode for building the BST

**Prerequisite/Reference**
  * [Merge Sort's Recursion](../../../../../src/courses/uc/course01algorithmicToolbox/module04DivideAndConquer/020mergeSortExample.kt)

**Function Header**

* We need to find the middle character of the original input string.
* So, we need have the original input string from which we can find the middle character.
* To find the middle character, we need to have the "range" from which we can find the middle character.
* A range is a pair of start and end indices.
* The function returns a root node, that represents the replica of the original input string in the form of a BST.

```kotlin

fun buildBST(input: String, start: Int, end: Int): Node {
    
}

```

**Function Body**

* We find the middle character of the original input string from the given range.

```kotlin

val middle = (start) + (end - start) / 2

```

* After finding the middle character, we create a node, and assign the middle character to the node.

```kotlin

val node = Node(input[middle]) // Node(char) 

```

* Now, the left part of the original input string becomes the left subtree of the node.

```kotlin

node.left = buildBST(input, start, middle - 1)

```

* Similarly, the right part of the original input string becomes the right subtree of the node.

```kotlin

node.right = buildBST(input, middle + 1, end)

```

* After assigning the left and right subtrees to the node, we assign the node as their parent.

```kotlin

node.left?.parent = node
node.right?.parent = node
```

* Once the bidirectional relationship is established, we need to update the size of the node (as the node has got new family members, the subtrees).

```kotlin

update(node)

```

* And finally, we return the node.

```kotlin

return node

```

* Now, a recursion function must have a base case.
* In this case, we want to continue the recursion as long as the start and end indices are valid.
* If the start index is greater than the end index, it means that it is an invalid range, and we return `null`.

```kotlin

if (start > end) return null

```

**buildBst: The complete pseudocode**

```kotlin

fun buildBst(input: String, start: Int, end: Int): Node {
    if (start > end) return null
    val mid = start + (end - start) / 2
    val node = Node(input[mid])
    node.left = buildBst(input, start, mid - 1)
    node.right = buildBst(input, mid + 1, end)
    node.left?.parent = node
    node.right?.parent = node
    update(node)
    return node 
}

```

## Designing the "Node" class

**What information do we need and use?**

* Pointers: left, right, and parent
* Key value to store the character
* Size for navigating the splay tree

```kotlin

private class Node(val key: Char) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
    var size: Long = 1 // The initial size of a node is 1
}

```

## How does the cut-and-paste operations become split-and-merge?




## Summary of representation, reconciliation, conversion, and transformation of the "rope string, substring cut-paste" problem into a splay tree problem

## Questions

### Why didn't we use Array/String/Linked-List for the rope substring cut-paste problem?

* Finding a character, finding a set of characters in a given range, shifting of characters after removing a set of characters, and shifting of characters after inserting a set of characters are all `O(n)` operations in an Array/String/Linked-List.

### Why didn't we use the segment tree for the rope substring cut-paste problem?

* Hard to rewire the segments.
* Not pointer-friendly.

### Why didn't we use the AVL-Tree or the Red-Black Tree for the rope substring cut-paste problem?

* It is a balanced binary search tree, which is a good thing to find, insert, and delete a node efficiently.
* However, it is a strictly balanced binary search tree.
* It means that it performs more rotations.
* So, it increases the complexity.
* On top of that, split and merge operations are also complex.

### Why didn't we use a Treap for the rope substring cut-paste problem?

* Split and merge operations are easy in a Treap.
* It also provides implicit indices.
* However, it needs randomization.
* ToDo: How does it need randomization? Why is it a problem?

### Why did we use a Splay Tree for the rope substring cut-paste problem?

* The cut-and-paste operations are re-ordering operations.
* We can simulate the cut-and-paste operations using the split and merge operations of a Splay Tree.
* Maintains the order.
* It is a flexible-balanced binary search tree.
* So, it performs fewer rotations.
* Split and merge operations are easy in a Splay Tree.
* Amortized complexity is `O(log n)`.

#### But what do we compare and how do we compare to traverse the Splay Tree?

* We compare the position for the traversal and not the `key` value.

#### Why did we store `size` instead of `index` in the Splay Tree?

* If we store `index`, then we need to update the `index` of many nodes after we change the structure due to shifting.
* If we store `size`, then we need to update the `size` of 3-4 nodes only after we change the structure by performing rotations as we mainly change grandparent, parent, and the child node.
* This difference in the number of nodes to be updated is huge, and it makes the difference in the complexity.

## Step-11: Mental Model

## Real-World Application

* Text editor.