# Prims Algorithm

![055primDsuOverview.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/055primDsuOverview.webp)

![KruskalVsPrimMst.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/060primVsKruskalMst.webp)

* The Prim's Algorithm constantly asks: 
* What is the cheapest safe edge that leaves the current tree and adds a new vertex?
* So that it can grow the current tree gradually using the cheapest safe edge.
---
* We start with a vertex.
* In other words, we have an existing tree.
* Initially, it might contain only one vertex.
* Then, we inspect all the outgoing edges of the selected vertex.
* We select the cheapest safe edge.
* If it connects an already selected vertex, we skip it.
* Otherwise, add the end vertex (neighbor) to the selected vertex container.
* We repeat the process until we cover all the vertices.
* And it will take exactly `V - 1` edges.
---
* As the tree grows, we might get multiple outgoing edges from multiple vertices.
* How do we select the cheapest edge out of all those outgoing edges?
* We use a `min-heap`.
---
* What do we add to the `min-heap` and when?
* We start with one vertex.
* We eagerly add all the outgoing edges of it as `(weight, to)` to the `min-heap`. 
* Then, we run a loop.
* As long as the `min-heap` is not empty:
* We extract the vertex.
* If it is already a selected vertex, skip.
* Otherwise, we mark it as selected.
* Retrieve the edges.
* Add them to the `min-heap`.
---
* So, what do we add to the `min-heap`?
* When we extract, we want to get the edge that has the minimum weight among all the other edges of the `min-heap`.
* So, definitely the weight of the edge.
* And then we need to know which vertex it brings in to the tree, which vertex it connects to.
* In other words, which vertex the existing tree is going to get after paying the cost of that weight.
* So, the information of the upcoming vertex that is a candidate to become a part of the existing and growing tree.
* So, `(weight, vertex)`.
---
* And we want to ensure that we don't entertain the edge that connects back to the already selected vertex.
* So, we need to keep track of the selected vertices.
* And for that, we use the `selected Boolean Array`.
---
* Eagerly add all the outgoing edges of the start vertex as `(weight, to)` to the `min-heap`.
* Repeat as long as the `min-heap` is not empty or until we reach `V - 1` selected edges.
* Poll.
* Poll always gives the minimum weight (cost) to add the associated vertex.
* If the associated vertex is already in our `selected` list, then skip.
* Otherwise, this is the time to stamp, to mark it as visited.
* Once we mark it as visited, we can increase the count of `selectedEdges` by +1.
* And we add the associated edge weight to our `minCost` as `minCost += edge.weight`.
* Then, we add all the edges of the associated vertex to the `min-heap`.
* We can exit early using the condition:
* `if (selectedEdges == (totalVertices - 1))`.
---

## Implementation

* [020primsAlgorithm.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module05minimumSpanningTrees/part01concepts/020primsAlgorithm.kt)

## Time Complexity

* We might add all the edges to the `min-heap`.
* The `binary min-heap` takes: $O(E log E)$ time to maintain the heap properties and functionalities.
* But if $E <= V^2$, then $log(E) <= log(V^2)$ = $log(E) = 2 log V$.
* We drop the constant.
* So, it becomes, $O(E log V)$.
* If we use an `array` instead of a `min-heap`, then finding the min is a linear operation (scan).
* So, it takes $O(E)$ time for each edge.
* For E edges, it becomes $O(E^2)$ time.
* But we exit early using `V - 1`, so it is $O(V^2)$ for an array based `min-heap`.

## Space Complexity

* We use the adjacency list to get the edges of the vertex, which takes: $O(V + E)$.
* We store the selected vertices, which takes: $O(V)$.
* We add at most E edges to the `min-heap`, which takes: $O(E)$.
* The dominant cost is: $O(V + E)$.

## Questions

* Why do we say that the Prim's Algorithm is a greedy algorithm?
* Because it repeatedly asks: 
* Among all the possible ways of expanding the tree, what is the local cheapest option?
---
* Is it possible to get different MSTs for the same graph? How?
* Yes. 
* If we start from a different vertex, we might get an MST with a different shape/structure. 
* But the overall cost will always be the same for the same graph.
---
* Does it work with negative weights and/or the negative cycles?
* Yes.
* Because we focus on the `minimum weight` among all the available options to expand the current (existing) tree.
* So, an edge with the negative weight does not create any problem.
---
* Does it work for a graph with a cycle?
* Yes.
* Because we attach only the `unvisited` vertex through the cheapest safe edge.
* So, the `visited` or `selected` Boolean Array prevents (avoids) cycles.
---
* What happens if the graph is disconnected (having multiple isolated connected components)?
* In that case, similar to the Kruskal's Algorithm, `selectedEdges` cannot be equal to `V - 1`.
---
* What happens if there is a self-loop or parallel-edges or equal-weighted edges?
* This is again, similar to the Kruskal's Algorithm.
* We select the cheapest edge among all the available options to expand the tree.
* And once we mark the vertex as `selected`, we avoid all the future edges that connects to the already `selected` vertex.
* So, the `selected` condition prevents (avoids) cycles.

## Next