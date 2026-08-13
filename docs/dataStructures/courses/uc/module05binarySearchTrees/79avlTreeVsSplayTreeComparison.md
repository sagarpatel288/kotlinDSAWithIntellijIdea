# AVLTree Vs. Splay Tree

**The node class and members**

* var vs. val properties
* Do we ever change the key?
* Nullable or non-nullable?
* What does the function take?
* Does the function take nullable or non-null arguments?
* What does the function return?
* Does the function return nullable or non-null type?
* Is the function recursive or iterative or a combination of both or something else?
* The answers depend upon the implementation, the problems we solve, and the approach we take to solve the problems.
* For example, an AVLTree might have a `var key` because we replace it during the `delete` operation.
* Especially, it is for the case when the target that we want to delete has left and right children.
* To be continued... 

**Parent pointer**

* AVLTree is a strictly balanced binary search tree.
* It maintains the balance factor of each node.
* The balance factor is measured by height.
* Balance factor = | left.height - right.height | <= 1.
* And we use the size property for the `kth smallest key`.
* So, it does not require the parent pointer (property).
* But, it requires height and size properties.
---
* A splay tree is a roughly balanced binary search tree.
* It ensures that the last accessed node becomes the root node.
* A node is a root node when its parent is null or becomes null.
* So, it uses the parent pointer.

**Rotation Types**

* AVLTree has:
  * Right rotation (LL Cause)
  * Left rotation (RR Cause)
  * RL Rotation (RL Cause)
  * LR Rotation (LR Cause)
---
* Whereas, a Splay Tree has:
  * Zig: Right Rotation
  * Zag: Left Rotation
  * Zig-Zig: Right-Right Rotation
  * Zag-Zag: Left-Left Rotation
  * Zig-Zag: Right-Left Rotation
  * Zag-Zig: Left-Right Rotation

**Rotation Conditions And Timing**

* After every insert, delete, and merge operation in AVLTree, we call the update function.
* The update function follows the rebalance.
* Rebalance calculates the balance factor.
* Only if the balance factor is out of range, we perform one or more rotations to restore the strict balance.  
* The split operation also uses the merge operation.
* So, the split operation also ultimately follows the update and rebalancing process. 
---
* Every find, insert, and delete operations strictly follow the splay operation.
* A successful merge operation also follows the splay operation as we find and splay the max of the left subtree.
* 

**Rotation Determination**

* An AVL Tree uses the balance factor.
* If the balance factor is < -1, it is either left or RL rotation.
* If the right child is <= 0, it is left rotation. Otherwise, RL rotation.
* RL rotation is caused by left-of-right.
* So, first we rotate the right child on the right side.
* Then we rotate the node on the left side.
* Similarly, if the balance factor is > 1, it is either right or LR rotation.
* If the left child is >= 0, it is right rotation. Otherwise, LR rotation.
* LR rotation is caused by right-of-left.
* So, first we rotate the left child on the left side.
* Then we rotate the node on the right side.
---
* A Splay Tree uses grandparent, parent, and child.
* If the grandparent is null, it is either right or left rotation.
* If the node is a left child, we rotate the parent on the right side (zig).
* Otherwise, we rotate the parent on the left side to move the child upward (zag).
* If the parent and the child are on the same side of the grandparent, it is either zig-zig or zag-zag.
* First we rotate the grandparent and then parent on the same side.
* If the parent and the child are on different sides of the grandparent, it is either zig-zag or zag-zig.
* First we rotate the parent and then the grandparent.

**Insert**

**Delete**

* In an AVL Tree, delete is a recursive function.
* It follows the typical binary search with the base condition.
* The parent gets replacement.
* So, it returns a nullable node.
* It returns a nullable node because it is possible that the target has no child (successor)!
* When we find the target, we have 3 cases:
* The target has 0, 1, or 2 children.
* If the target has 0 child, the base condition returns null and the parent gets it.
* If the target has 1 child, left or right, it takes the place of the target.
* Otherwise, we replace the target key with the successor key (next larger).
* Then, we delete the successor to avoid the duplicate keys in the tree.
* We rebalance the parent and return.
* Every insert and delete operation updates the root node!
---
* In case of a splay tree, it is straightforward.
* We use split and merge.
* We find the target and splay it.
* The target becomes the root.
* If the root key does not match, we return.
* Otherwise, we disconnect the children.
* If the root has only 1 child, we return it.
* Otherwise, we find the max on the left and splay it.
* And then we connect the right child with the left max.