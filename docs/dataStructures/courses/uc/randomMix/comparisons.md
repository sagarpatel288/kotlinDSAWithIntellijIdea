# Comparisons of data structures and algorithms

<!-- TOC -->
* [Comparisons of data structures and algorithms](#comparisons-of-data-structures-and-algorithms)
* [Data Structures](#data-structures)
* [Which data structure to choose: When and why](#which-data-structure-to-choose-when-and-why)
<!-- TOC -->


# Data Structures


| DS            | Access                                  | Search                                        | Insert                                      | Delete                                                   | Memory | Main Trade-off                                 | Note |
|---------------|-----------------------------------------|-----------------------------------------------|---------------------------------------------|----------------------------------------------------------|--------|------------------------------------------------|------|
| Array         | O(1)                                    | O(n)                                          | O(n)                                        | O(n)                                                     | Low    | Fast access, expensive updates                 |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Dynamic Array | O(1)                                    | O(n)                                          | O(1)*: Amortized Cost                       | O(n)                                                     | Medium | Flexibility vs resize cost                     |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Singly LL     | O(n)                                    | O(n)                                          | O(1): Inserting a head.                     | O(1): Deleting the head.                                 | Medium | Fast updates, slow access                      |      |
|               |                                         |                                               | O(1): Inserting a tail using a tail node.   | O(1): Known previous node.                               |        |                                                |      |
|               |                                         |                                               | O(1): Known previous node.                  | O(n): Deleting the tail (with or without the tail node). |        |                                                |      |
|               |                                         |                                               | O(n): In any other case.                    | O(n): Finding the node that we want to delete.           |        |                                                |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Doubly LL     | O(n)                                    | O(n)                                          | O(1): Inserting a head.                     | O(1): Deleting the head.                                 | High   | Backward traversal vs memory                   |      |
|               |                                         |                                               | O(1): Inserting a tail using a tail node.   | O(1): Deleting the tail using the tail node.             |        |                                                |      |
|               |                                         |                                               | O(1): Known previous or next node.          | O(1): Known previous or next node.                       |        |                                                |      |
|               |                                         |                                               | O(n): Inserting at the last without a tail. | O(n): Deleting the tail without the tail node.           |        |                                                |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Stack         | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium | Simplicity vs flexibility                      |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Queue         | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium | FIFO vs arbitrary access                       |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Deque         | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium | Two ends vs middle access                      |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Tree          | O(n)                                    | O(n)                                          | O(n)                                        | O(n)                                                     | Medium | Hierarchy vs complexity                        |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| BST           | O(log n) avg                            | O(log n) avg                                  | O(log n) avg                                | O(log n) avg                                             | Medium | Can become unbalanced!                         |      |
|               |                                         |                                               |                                             |                                                          |        | E.g. Inserting a sorted data!                  |      |
|               |                                         |                                               |                                             |                                                          |        | E.g., 1, 2, 3, 4, 5.                           |      |
|               |                                         |                                               |                                             |                                                          |        | It will be a linked list!                      |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| AVL/RB Tree   | O(log n)                                | O(log n)                                      | O(log n)                                    | O(log n)                                                 | High   | Guaranteed balance vs rotations                |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Heap          | O(1): Finding the extremum (max or min) | O(1): Searching for the extremum (max or min) | O(log n): Due to `SiftUp`                   | O(log n): Due to `SiftDown`                              | Low    | Fast min/max only                              |      |
|               | O(n): Finding an arbitrary element      | O(n): Searching an arbitrary element          |                                             |                                                          |        |                                                |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |
| Hash Table    | N/A                                     | O(1) avg                                      | O(1) avg                                    | O(1) avg                                                 | High   | Memory + collisions                            |      |
|               |                                         |                                               |                                             |                                                          |        | It can be a linked list or a tree!             |      |
|               |                                         |                                               |                                             |                                                          |        | E.g., if every item hashes to the same bucket! |      |
|               |                                         |                                               |                                             |                                                          |        |                                                |      |


# Which data structure to choose: When and why

| Requirement    | Sacrifice         | Choose        |
|----------------|-------------------|---------------|
| Fast indexing  | Insert/Delete     | Array         |
| Fast append    | Occasional resize | Dynamic Array |
| Fast insertion | Random access     | Linked List   |
| LIFO behavior  | Flexibility       | Stack         |
| FIFO behavior  | Arbitrary access  | Queue         |
| Fast min/max   | General search    | Heap          |
| Fast lookup    | Extra memory      | Hash Table    |
| Ordered search | Complex balancing | BST/AVL       |