# Comparisons of data structures and algorithms

<!-- TOC -->
* [Comparisons of data structures and algorithms](#comparisons-of-data-structures-and-algorithms)
* [Data Structures](#data-structures)
* [Trie and Graphs](#trie-and-graphs)
  * [Graph: Adjacency List](#graph-adjacency-list)
  * [Graph: Adjacency Matrix](#graph-adjacency-matrix)
  * [Which one to choose: When and Why](#which-one-to-choose-when-and-why)
* [Which data structure to choose: When and why](#which-data-structure-to-choose-when-and-why)
<!-- TOC -->


# Data Structures

| DS                  | Access                                  | Search                                        | Insert                                      | Delete                                                   | Memory        | Main Trade-off                                      | Note                               |
|---------------------|-----------------------------------------|-----------------------------------------------|---------------------------------------------|----------------------------------------------------------|---------------|-----------------------------------------------------|------------------------------------|
| Array               | O(1)                                    | O(n)                                          | O(n)                                        | O(n)                                                     | Low           | Fast access, expensive updates                      |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Dynamic Array       | O(1)                                    | O(n)                                          | O(1)*: Amortized Cost                       | O(n)                                                     | Medium        | Flexibility vs resize cost                          |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Singly LL           | O(n)                                    | O(n)                                          | O(1): Inserting a head.                     | O(1): Deleting the head.                                 | Medium        | Fast updates, slow access                           |                                    |
|                     |                                         |                                               | O(1): Inserting a tail using a tail node.   | O(1): Known previous node.                               |               |                                                     |                                    |
|                     |                                         |                                               | O(1): Known previous node.                  | O(n): Deleting the tail (with or without the tail node). |               |                                                     |                                    |
|                     |                                         |                                               | O(n): In any other case.                    | O(n): Finding the node that we want to delete.           |               |                                                     |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Doubly LL           | O(n)                                    | O(n)                                          | O(1): Inserting a head.                     | O(1): Deleting the head.                                 | High          | Backward traversal vs memory                        |                                    |
|                     |                                         |                                               | O(1): Inserting a tail using a tail node.   | O(1): Deleting the tail using the tail node.             |               |                                                     |                                    |
|                     |                                         |                                               | O(1): Known previous or next node.          | O(1): Known previous or next node.                       |               |                                                     |                                    |
|                     |                                         |                                               | O(n): Inserting at the last without a tail. | O(n): Deleting the tail without the tail node.           |               |                                                     |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Stack               | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium        | Simplicity vs flexibility                           |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Queue               | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium        | FIFO vs arbitrary access                            |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Deque               | O(n)                                    | O(n)                                          | O(1)                                        | O(1)                                                     | Medium        | Two ends vs middle access                           |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Tree                | O(n)                                    | O(n)                                          | O(n)                                        | O(n)                                                     | Medium        | Hierarchy vs complexity                             |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| BST                 | O(log n) avg                            | O(log n) avg                                  | O(log n) avg                                | O(log n) avg                                             | Medium        | Can become unbalanced!                              |                                    |
|                     |                                         |                                               |                                             |                                                          |               | E.g. Inserting a sorted data!                       |                                    |
|                     |                                         |                                               |                                             |                                                          |               | E.g., 1, 2, 3, 4, 5.                                |                                    |
|                     |                                         |                                               |                                             |                                                          |               | It will be a linked list!                           |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| AVL/RB Tree         | O(log n)                                | O(log n)                                      | O(log n)                                    | O(log n)                                                 | High          | Guaranteed balance vs rotations                     |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Heap                | O(1): Finding the extremum (max or min) | O(1): Searching for the extremum (max or min) | O(log n): Due to `SiftUp`                   | O(log n): Due to `SiftDown`                              | Low           | Fast min/max only                                   |                                    |
|                     | O(n): Finding an arbitrary element      | O(n): Searching an arbitrary element          |                                             |                                                          |               |                                                     |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Hash Table          | N/A                                     | O(1) avg                                      | O(1) avg                                    | O(1) avg                                                 | High          | Memory + collisions                                 |                                    |
|                     |                                         |                                               |                                             |                                                          |               | It can be a linked list or a tree!                  |                                    |
|                     |                                         |                                               |                                             |                                                          |               | E.g., if every item hashes to the same bucket!      |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Trie (Prefix Tree)  | O(L)                                    | O(L)                                          | O(L)                                        | O(L)                                                     | High          | Fast prefix matching Vs. massing memory             | App: Dictionaries, Auto-Complete,  |
|                     |                                         |                                               |                                             |                                                          |               | It can have many empty pointers                     | Spell Checkers, Prefix Matching    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Graph (Adj. List)   | O(V) to access neighbors: Worst         | O(V + E)                                      | O(1) Vertex (Amortized)                     | O(V + E): Deleting a Vertex or entire graph              | Low/Medium    | Saves memory for sparse graphs Vs. slow edge lookup | App: When E << V^2                 |
|                     |                                         |                                               | O(1) Edge (Amortized)                       | O(V): Deleting an Edge                                   |               |                                                     |                                    |
|                     |                                         |                                               |                                             |                                                          |               |                                                     |                                    |
| Graph (Adj. Matrix) | O(1) Edge                               | O(V^2)                                        | O(V^2): Insert a Vertex                     | O(V^2): Delete a Vertex                                  | O(V^2) = High | Fast edge lookup Vs. memory waste for sparse graphs | App: For densed graphs             |
|                     |                                         |                                               | O(1): Add an edge                           | O(1): Remove an edge                                     |               | O(V^2) Memory waste for sparse graphs               |                                    |



# Trie and Graphs

## Graph: Adjacency List

*  

| Operation               | Complexity   |
|-------------------------|--------------|
| Is edge (u, v) present? | O(degree(u)) |
| Add edge                | O(1)         |
| Remove edge             | O(degree(u)) |
| Iterate neighbors       | O(degree(u)) |
| BFS/DFS                 | O(V + E)     |


*  

## Graph: Adjacency Matrix

* 

| Operation               | Complexity |
|-------------------------|------------|
| Is edge (u, v) present? | O(1)       |
| Add edge                | O(1)       |
| Remove edge             | O(1)       |
| Iterate neighbors       | O(V)       |
| BFS/DFS                 | O(V²)      |

*  

## Which one to choose: When and Why

* 

| Requirement                                  | Sacrifice                            | Choose                   |
|----------------------------------------------|--------------------------------------|--------------------------|
| Fast prefix search/autocomplete              | Large memory consumption             | Trie                     |
| Dictionary lookup independent of total words | Extra nodes and pointers             | Trie                     |
| Sparse graph (few edges)                     | O(degree(v)) edge checks             | Graph (Adjacency List)   |
| Dense graph (many edges)                     | O(V²) memory                         | Graph (Adjacency Matrix) |
| Constant-time edge existence queries         | Huge memory usage                    | Graph (Adjacency Matrix) |
| Efficient graph traversal (BFS/DFS)          | Slower edge lookups                  | Graph (Adjacency List)   |
| Dynamic graph updates                        | Slightly more complex implementation | Graph (Adjacency List)   |
| Complete graphs or all-pairs algorithms      | Wasted space on sparse graphs        | Graph (Adjacency Matrix) |

* 

# Which data structure to choose: When and why

* 

| Requirement                    | Sacrifice                   | Choose             |
|--------------------------------|-----------------------------|--------------------|
| Fast indexing                  | Insert/Delete               | Array              |
| Flexible size                  | Occasional resize cost      | Dynamic Array      |
| Fast insertion/deletion        | Random access               | Linked List        |
| LIFO behavior                  | Arbitrary access            | Stack              |
| FIFO behavior                  | Arbitrary access            | Queue              |
| Access from both ends          | Middle access               | Deque              |
| Hierarchical data              | Complexity                  | Tree               |
| Ordered search                 | Possible imbalance          | BST                |
| Guaranteed O(log n) operations | Rotations and extra logic   | AVL/Red-Black Tree |
| Fast min/max                   | General searching           | Heap               |
| Fast average lookup            | Extra memory and collisions | Hash Table         |
| Fast prefix matching           | High memory usage           | Trie               |
| Sparse graph representation    | Slower edge lookup          | Adjacency List     |
| Constant-time edge checks      | O(V²) memory                | Adjacency Matrix   |