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
* **What else can we use?**
* We want fast delete, and fast insert.
* A `String` contains characters, and these characters have a specific order.
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
* **How do we use a character as a key?**
* 