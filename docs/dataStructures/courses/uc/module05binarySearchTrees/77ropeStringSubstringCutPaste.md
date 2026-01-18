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

### Perspective based on the requirements of the problem

* In the previous lecture, we saw that we cannot use a contiguous data structure.  
* 
**What else can we use?**  
* 
* What are we doing here? What operations do we perform here?
* Cut and paste. 
* How are we supposed to do it? What process can do it?
* A `String` contains characters.
* To cut (remove) a substring, we have the start and end indices of the substring.
* The characters that we want to remove are between these start and end indices (range).
* In that sense, a substring is a set of characters within a particular range.
* We remove this substring from the original string.
* And then, we paste (insert) the substring at the given position.
* We can say that we are keep changing the order of the characters.
* We keep manipulating the order of the characters.
* So, it is a sequence or order manipulation problem.
* We keep re-ordering the characters.
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
* The formula for the `size` property is `size = 1 + leftSize + rightSize`.

![1055bstKthSmallestKey.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1055bstKthSmallestKey.png)

![1060ropeStringCutPasteSplayTree.png](../../../../../assets/images/dataStructures/uc/module06programmingAssignments/1060ropeStringCutPasteSplayTree.png)

* This is something we have learned in the previous module:
  * [Reference: Kth Smallest Element in a BST](50avlTreeFindKthSmallKey.md)
* Now, if we want to find the `14th` key, and if we start from the root node, we know that the `14 > 8`, so we go to the right side of the root node.
* `8.right` is the `12` and `14 > 12`, so we go to the right side of `12`.
* `12.right` is the `14` and that is our target node.
* Notice that we compare the position for the traversal and not the `key` value.
* We still need to convert this idea into code.

**Perspective**

* We treat the given original string structure as a valid binary search tree.
* Once we set up the binary search tree, the cut-and-paste operations become "split" and "merge" operations of a splay tree.
* In this way, we take less time to find a node, or multiple nodes in the given range.

**How does the cut-and-paste operations become split-and-merge?**

* Step-8, reflective question#5.

## Real-World Application

* Text editor.