# Exploring A Graph (Traversal)

## Prerequisites

* 

## References

* 

## Problem examples

**Check all the rooms of a floor**

* Suppose we are playing a game. 
* The game has a high-rise building.
* The building has several floors, each floor has several rooms, and the game has exactly one exit door.
* We can say that the rooms of a particular floor are connected through a common direct or indirect passage.
* We are given a particular floor, and we need to check all the rooms of the floor before we can exit.
* How do we ensure that we have checked all the floors?

**Map**

* We want to go from a place A to a place B.
* First, we need to check whether there is any connectivity or not.
* We want to find all the reachable (available) connectivities before we can find the shortest path.
* We can also do it with B instead of A.
* Because in the end, we want to find the connectivity between them.
* The objective (emphasized, weight, focus) is to **find all the paths** that can connect A and B. 
* The use case is, we want to find all the routes (paths) that can help us connect (or reach) from point A to point B.

**So, the point is, how can we explore all the paths and how can we conclude that we have visited (covered) them all?** 

## Intuition (Idea)

### Prerequisites

* [BST Traversal](../../../../02dataStructures/courses/uc/module05binarySearchTrees/07binarySearchTreeAndStack.md)

---

* Suppose that we have a graph as shown in the image, and we want to find all the (reachable-) paths of the vertex, A. 



* We use the adjacent list.
* We start with a particular vertext (which is in our case, A).
* We mark it as visited and add it to a stack.
* Then, we visit its neighbor, mark it as visited, and add it to the stack.
* We repeat this process as long as we have a non-null vertex.
* Once we hit the end, we pop the vertex from the stack.
* And we visit the neighbor of this popped vertex.
* We mark it as visited, and add it to the stack.
* We repeat this process to cover all the paths that are connected with the vertex, A.

## Next

* 