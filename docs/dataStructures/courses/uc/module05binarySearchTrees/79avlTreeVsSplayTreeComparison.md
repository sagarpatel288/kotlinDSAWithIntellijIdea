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

**Insert**

* 