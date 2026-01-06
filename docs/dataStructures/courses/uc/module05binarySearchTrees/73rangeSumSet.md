# Which data structure should we use for the following requirements?
* Up to 1,00,000 operations
* Add
* Delete
* Find
* Range Sum Query (Based on values, not indices): `l <= val <= r`.

* Suppose, we have 50,000 elements, and 50,000 range sum queries.
* The range sum query is based on values, not indices.
* If we do not have an ordered data structure based on values, then we have to scan and check each element that lies in the range.
* It means, each range sum query will have to scan 50,000 elements.
* So, 50,000 range sum queries will take 50,000 * 50,000 = TLE!
* It means, any data structure that takes `O(n)` doesn't work. 
* We need a faster way to find elements whose values lie in the range.
* Only an ordered data structure can do range sum query fast enough.
* But a BST can be skewed.
* What are the other options?
* Self-balancing trees like AVL or Splay trees.
* An AVL tree is a strictly self-balancing tree.
* It means that it can take more rotations.
* A Splay tree is a roughly self-balancing tree.
* It means that it can take fewer rotations.
* In a Splay tree, we can split the tree into `< l`, `> r`, and `[l, r]`.
* Also, each node can store the sum of its left and right subtrees.
* So, `node.sum = node.key + node.left.sum + node.right.sum`.
* This way, the root of the `[l, r]` part quickly gives us the sum of the range.
* And then, we can merge them back together.
* Reference: [Splay Trees](70splayTrees.md).

# The `Node` class based on the requirements and responsibilities

* A `Node` contains a `key`.
* A `Node` must know its left child and right child. 
  * In other words, it must know its left subtree and right subtree.
* A `Node` must know its parent. For example, we use it to decide the rotation.
* A `Node` must know the sum of its left and right subtrees.
  * So that we don't have to traverse the whole tree to find the sum of the subtree.
  * It helps us find the sum of the subtrees in `O(1)`.

# Operations

* After understanding why we need to use a Splay tree, and designing the `Node` class, we can understand the operations.
* We know the public APIs: `add`, `delete`, `find`, and `rangeSum`.
* We understood that `rangeSum` uses `split` and `merge`.
* Similarly, `add` also uses `split` and `merge`.
* In the `merge` operation, we call `findMax` on the left tree. 
* Except `split` and `merge`, each operation uses `splay` in the end to make the recently (last) accessed node the root node.
* The `splay` operation brings the node to the root of the tree by performing rotations.
* We use `rotateRight` and `rotateLeft` to perform various rotations like `zig`, `zig-zig`, and `zig-zag`.
* After every rotation, various properties of a few nodes may change.
* For example, the parent, left child, right child, height, and sum.
* So, we need the `update` function.
* We would call `update` after every rotation.
* And we call `update` after every `split` and `merge` operations, too.