# Spanning Tree

## Prerequisites/References

* [SpanningTree](https://youtu.be/Yldkh0aOEcg?si=x9XfeOGvVHN3uasc)
  * Brian Yu. Harvard professor and head. Integrity. Simplification. Respect. 
  * Worth watching: [Introduction to AI -cs50 Harvard Edu](https://youtu.be/WbzNRTTrX0g?si=aiFelgbBHH1nhGOV)
* [Abdul Bari Sir](https://youtu.be/4ZlRH0eK-qQ?si=PEnhqZon5EeJmqvy)
* [Shradha Madam](https://youtu.be/inoM6jwj1CA?si=95884cT7zeF0ZFly)

## Problem

![010homesBuildingRoads.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/010homesBuildingRoads.webp)

![022graphToMinSpanTreeProblemExample.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/022graphToMinSpanTreeProblemExample.webp)

* We have some homes (or cities, machines, etc.), and we want to connect them with each other.
* We are interested in connectivity and minimum cost.
* We want to avoid cycles.
* For example, the result might look like below:

![020homesBuildingRoads.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/020homesBuildingRoads.webp)

* A path that connects all the vertices (homes, machines, cities, etc.).

## Concept

![024whyNoCycleInMinSpanTree.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/024whyNoCycleInMinSpanTree.webp)

* The problem:
* We have a few vertices, we want to connect them all, without any cycle, with minimum cost.
* Or there is an undirected, connected, cyclic, and weighted graph for which we want to remove all the cycles, and keep all the vertices connected using minimum cost. 
* The lemma:
> For an undirected and weighted graph, the optimal solution where we keep all the vertices connected without any cycle, and the sum of weight of edges is the minimum, then it is guaranteed that the result forms a tree.  
* The requirement is aligned with a tree.
* If all the vertices are connected, they are undirected, the path between the two vertices is unique, and there is no cycle it essentially becomes a tree.
* Because these are the properties of a tree.
* It means that we need to solve this problem considering the fact that we are going to form a tree, or the result will be a tree.
* A tree cannot have a cycle and a graph can have a cycle.
* A tree cannot have directed edges, but we can have a directed or an undirected graph.
* Also, a tree cannot have disconnected (isolated) nodes. Whereas, a graph can have separate, multiple, isolated connected components. 
* And if there are `V` vertices, then the tree gets exactly `V - 1` edges.
* So, to convert a graph into a tree, we need to ensure that we use an undirected and connected graph and the result does not have any cycle.
* In other words, an undirected, connected graph where there is no cycle is a tree.
* A spanning tree is a tree that covers (connects) all the vertices of a graph without any cycle by using `V - 1` edges.
* For example:

* ![Spanning Tree.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/030minSpanTree.webp)

* A minimum spanning tree or a minimum cost spanning tree is a tree that connects all the vertices of the graph, without any cycle, with minimum cost, using `V - 1` edges. 
* The term minimum corresponds to the minimum cost and minimum edges.
* For example, if we have `V` vertices (or nodes), then to form a tree, we need exactly `V - 1` edges.
* And to form the MST, the sum of the weight of these edges must be minimum.
* The term spanning corresponds to the fact that the resulting tree is still a connected graph, and the term tree corresponds to the properties of a tree.
* For example, the result is not cyclic, it is not directed, and it covers all the vertices of the original graph.
* We use MST (Minimum Spanning Tree) only for an undirected and weighted graph where vertices are connected. 
* There are two popular algorithms to find the MST.
  * Kruskal's Algorithm
  * Prims Algorithm

**Kruskal's Algorithm**

![035kruskalDsuOverview.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/035kruskalDsuOverview.webp)

![Kruskal Mst.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/050kruskalMst02.webp)

* We always pick the edge with the least (minimum) weight as long as it does not create a cycle.
* In other words, we select the edge with the least available weight out of the entire graph even if it is not connected to the selected graph as long as it does not create any cycle.
* It is like we start with one edge, and we keep adding more edges by selecting the edge with the least available weight without creating a cycle.
* So, it selects the next lightest edge that does not create a cycle.
* Initially, it might look disconnected (isolated) edges, but by the end of the algorithm, we get a proper tree with the minimum cost.

---
* Selects the edge that has the least weight and doesn’t create a cycle.
* Initially, imagine that we have a forest of trees where each tree is an individual set.
* Then, we sort the edges in non-decreasing order by their weights.
* We process each edge one by one.
* Each edge gives us two vertices.
* We find the parent (leader) of each vertex.
* If their parents (leaders) are different, they belong to a different set.
* This is to avoid the cycle.
* If they belong to a different set, we merge (union) them.
* After the union, they share the same parent (leader).
* They become part of the same set.
* We repeat this process for `V - 1` times.
---

**Prims Algorithm**

![055primDsuOverview.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/055primDsuOverview.webp)

![KruskalVsPrimMst.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/060primVsKruskalMst.webp)

* We always pick the edge with the least (minimum) weight as long as it connects the already selected vertices and does not create a cycle.
* We start with one vertex, check its outgoing edges, and select the edge with the least available weight that does not create any cycle, and we repeat this process as we add more vertices into the selected category.
* We start with one vertex, and we keep adding more vertices by selecting the edge with the least available weight without losing the connection, and without creating a cycle.
* In other words, we gradually grow the tree using the least available weight to attach a node, without losing the connection at any point, and without creating a cycle at any point.
* So, it selects the next lightest edge that connects (attaches) a vertex without creating a cycle.

## Next