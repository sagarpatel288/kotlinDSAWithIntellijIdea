# AVLTree Vs. Splay Tree

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

* 