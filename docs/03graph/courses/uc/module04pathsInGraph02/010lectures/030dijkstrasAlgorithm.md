# Dijkstra's Algorithm

## Prerequisites

* [Edge Relaxation.md](020edgeRelaxation.md)

## References

* [Spanning Tree](https://youtu.be/EFg3u_E6eHU?si=9LMk9KDV1Xl8Z264)
* [Abdul Bari Sir](https://youtu.be/XB4MIexjvY0?si=D_Z_2n7Z3nlacc6D)
* [Computerphile - Using paper cards. I love this approach.](https://youtu.be/GazC3A4OQTE?si=lsnTPVUU55cF_Oh4)

## Concept

* Suppose that we have the following graph:
* ![Dijkstras Algorithm.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/03dijkstrasAlgorithm/010DijkstrasAlgorithm.webp)
* We want to find the shortest distance from A → D.
* Node "A" is our source node and node "D" is our destination node.
* If there were only two nodes, A and D, and if there was a direct edge from A to D, we would have taken the edge weight and concluded it as the shortest path.
* But here, we have many nodes and there are more than one possible routes (subpaths, sidepaths) from A to D.
* So, we want to try them all and then declare the final shortest path.
* But if we try each route one by one, we would re-visit many nodes and path along the way.
* And it will be very inefficient.
* So, we break this large problem into a smaller problem.
* We focus on the neighbors first because there is a direct edge for them.
* And we repeat this for each neighbor.
* But we still need to store the distance information.
* So, we take the `dist` array.
* Ultimately, we are going to store the distance of each vertex.
* So, the size of the `dist` array will be equal to the total number of `vertices`.
* Initially, we don't know the distance of any node.
* So, by default the value for each vertex will be `MAX_VALUE` in the `dist`.
* However, we know the distance of the source node "A" from "A", which is "0".
* So, it becomes `dist[A] = 0`.
* Also, similar to the [Shortest Path.md](../../module03pathsInGraph01/010lectures/020shortestPath.md) problem, we need a `prev` array to reconstruct the path back from the destination to the source node and then reverse it to get the actual path.
* So, we will take a `prev` array.
* And the size of this `prev` array will also be equal to the total number of `vertices`.
* Because we are going to store the **previous vertex** information for each vertex we visit.
* Initially, the default value in the `prev` array can be `-1`. 
---
* Now, we start with the source node, "A".
* Node "A" is our current node.
* We are standing at the node "A".
---
* What is the shortest known distance of "A" from "A"?
* It is `dist[A] = 0`.
* Who are the neighbors (having direct edge with the current node "A")?
* The neighbors are: B and C.
* Currently, `dist[B] = MAX_VALUE` and `dist[C] = MAX_VALUE`.
* However, the distance from the direct edge between A → B is `4`.
* It means that we have found a shorter distance than the currently known distance of B.
* So, we update (reduce) the shortest known distance of B.
* So, it becomes `dist[B] = 4`.
* And we also update the `prev` array.
* So, `prev[B] = A`.
* Together, the `dist` and the `prev` arrays indicate that the shortest path to B is via A, and it is 4.
* Now, we check the next neighbor of A.
* Currently, `dist[C] = MAX_VALUE`.
* But the distance from the direct edge from A → C is `1`.
* It means that we have found a shorter distance than the currently known distance of C.
* So, we update (reduce) the shortest known distance of C.
* So, it becomes `dist[C] = 1`.
* And we also update the `prev` array.
* So, `prev[C] = A`.
* Together, the `dist` and the `prev` arrays indicate that the shortest path to C is via A, and it is 1.
* Alright. We are done for the node "A". We checked its neighbors.
* Now, we explore the next vertex.
* But which vertex will we pick up first between B and C?
---
* ![Dijkstras Algorithm is Closest First.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/03dijkstrasAlgorithm/030DijkstrasAlgorithm03.webp)
* As shown in the image, we know that the distance between A → B is 10.
* And we know that the distance between A → C is 5.
* We don't know anything else, yet.
* We want to find the shortest distance from A → Z.
* We have two options:
* Either we can first explore the path that goes via B, or we can first explore the path that goes via C.
* The general intuition (inclination) is first to try (explore) the path that goes via C.
* Because it is already shorter.
* It doesn't always end-up causing the shortest path.
* But that is what Dijkstra had used to find the shortest path.
* And when we are asked to represent the Dijkstra's Algorithm to find the shortest path, we use the same original approach.
* We don't modify it.
* And to decide which vertex is the closest one from the source node, he used a `min-heap`.
* So, we will use a `min-heap` to decide and process the closest vertex first.
---
* Notice that this is neither BFS nor DFS.
* It uses two steps:
* Relax the neighbors + Select from the `min-heap`.
* Every time we relax a vertex, we push (offer) it to the `min-heap`.
* And to explore the next vertex, we use `poll` on the `min-heap`. 
* Coincidentally, it can end up with BFS or DFS, but it is never the intention here.
* In fact, the closer intention is to explore the closest (shortest) known path first (greedy).
* The algorithm constantly follows and tries the tentative shortest known path first (greedy). 
---
* So, the pattern, the approach of the progress, the invariant, the steps of relaxing and exploring the vertices is:
* Relax the neighbors.
* Add relaxed neighbors to the `min-heap`.
* Explore using the `min-heap`.
* Repeat.
---
* So, in our example, we will first explore `C` compared to `B`.
* ![Dijkstras Algorithm.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/03dijkstrasAlgorithm/010DijkstrasAlgorithm.webp)
* Because `C` is the closest vertex to the source node than `B`.
* Ok. So now, our current node is C. 
* We repeat the same process that we did for the vertex, A.
* The node C has two neighbors: B and D.
* Now comes the interesting part.
* We are going to use the [Edge Relaxation.md](020edgeRelaxation.md).
* We know that `dist[C] = 1`.
* And `dist[B] = 4`.
* But the distance from the direct edge from C to B is `2` only.
* It means that, A to C is `1` and C to B is `2`.
* So, the total distance from A to B via C is 1 + 2 = `3`, which is smaller than the current `dist[B]`.
* It means that we have found a better (shorter, smaller) path to reach B.
* If we represent the shortest distance from A to B as `dist[B]`, the shortest distance from A to C as `dist[C]`, and the distance from the direct edge between C and B as `w(C, B)`, then:
* `dist[B] > dist[C] + w(C, B)`.
* Whenever this happens, we update (reduce) the larger distance with the shorter distance we have just found.
* So, it becomes:
* 
```kotlin
if (dist[B] > dist[C] + weight(C, B)) {
    dist[B] = dist[C] + weight(C, B)
}
```
* So now, `dist[B]` is `3`.
* And with this, we also update the `prev` array.
* Now, the shortest path to B goes through C.
* So, `prev[B] = C`.
* Together, `dist` and `prev` indicate that the shortest distance from A to B is `3` and it goes through `C`.
* And then, we have another remaining neighbor of C, which is D.
* Currently, `dist[D]` is `MAX_VALUE`.
* But if we go through C, it is `dist[C] + weight(C, D)`.
* So, it becomes: `dist[D] = 1 + 5 = 6`.
* And this path goes through, C.
* So, we also update `prev[D] = C`.
* Together, `dist` and `prev` indicate that the shortest distance from A to D is `6` and it goes through `C`.
* And with this, we are done with C.
* Next, we explore the remaining neighbor of A, which is B.
---
* The node B has one neighbor, D.
* Currently, `dist[D]` is `6` via C.
* But the distance of the direct edge from B to D is `1`.
* And `dist[B]` is `3`.
* Together, it makes `dist[D] > dist[B] + weight(B, D)`.
* Because, `6 > 3 + 1`.
* So, we update (reduce) `dist[D]` to `dist[B] + weight(B, D)` = `3 + 1` = `4`.
* And this path goes through B.
* So, we also update the `prev[D] = B`.
* Together, `dist` and `prev` indicate that the shortest distance to reach D is `4`, and it goes via `B`.
---
* We have finished exploring all the neighbors of the node B.  
---
* Next, it is node D.
* Node D is the destination node.
---
* Now, we know that the shortest distance from A to D is 4, and it goes via B.
* But, we also need to reconstruct the path.
* So, we use the same technique we have used in the: [Shortest Path.md](../../module03pathsInGraph01/010lectures/020shortestPath.md).
* So, the shortest path from A to D is: ACBD, and the distance is 4.
---
* TL:DR
* We take `dist` array to store the distance of all the nodes from the source node.
* We also take the `prev` array to store the `previous` vertex information to understand through which (`previous`) vertex we reached.
* The size of the `dist` and the `prev` are equal to the total number of `vertices`.
* The default value in the `dist` is: `MAX_VALUE`.
* The default value in the `prev` is: `-1`.
* We start with the source node.
* We pick up the closest vertex first using the direct edge weight and min-heap.
* We update (reduce) the distance of the vertex. 
* If `dist[v] > dist[u] + weight(u, v)`, then `dist[v] = dist[u] + weight(u, v)`.
* Accordingly, we update `prev` based on the `prev/parent` vertex information.
* We add the relaxed vertex to the `min-heap`.
* We explore the next vertex using `poll` on the `min-heap`.
* We repeat the process until the `min-heap` is empty, we find the destination, or we hit the dead-end (finished entire graph).
---
* Story to Code:
* Arsenal / Tools to find the shortest path using the Dijkstra's Algorithm:
* Two arrays: `dist`, `prev`.
* Each of size: `vertices`.
* Default values: `dist` = `MAX_VALUE`, `prev` = `-1`.
* Exploration: Start from the source node, then choose the vertex with the smallest known distance. It doesn't have to be a neighbor.
* How to choose the next vertex with the smallest distance? Using `min-heap`.
* To select it from the `min-heap`, we need to have distance and corresponding vertex in the `min-heap`.
* When and how do we add (push, offer) them to the `min-heap`?
* ![Dijkstras Algorithm Data Format.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/03dijkstrasAlgorithm/040dijkstrasAlgorithmDataFormat.webp)
* Distance is the edge weight.
* Unlike unweighted graphs, here we get weight along with the edges.
* We are talking about the distance of all the nodes from the source node.
* It means that if an edge is (vertex, distance), then it represents the distance of that vertex from the source node.
* So, we can have a data class for edge.
* It can be something like: `data class Edge(val vertex: Int, val distance: Int)`.
* And we are expecting the distance and vertex values for each edge from the input.
* Now, each vertex can have multiple edges, and it represents neighbors.
* It means that each vertex can get a list of edges.
* So, it becomes: `data class Vertex(val vertex: Int, val edges: List<Edge>)`.
* And we can have multiple vertices.
* So, it becomes: `List<Vertex>`.
* And remember that we use the direct addressing method.
* So, the size of this list of vertices is equal to the total vertices.
* So, it becomes: `List(vertices) { mutableListOf<Edge>() }`.
* Here, the index of the `List` represents the vertex.
* And each vertex has the `mutableListOf<Edge>`.
* Now, the distance-level layer is based on the source node.
* So, we need to know the source node as well.
* And it is something that we cannot create or assume on our own.
* So, we are also expecting that the source node will be given in the input.
* Now, we have all the required data to understand and process the edge, the distance, and the associated vertex.
* Index `0` of the `List` will give us the list of edges of the vertex `0`.
* And each edge will give us two things: Vertex and Distance.
* It represents that `0` is directly connected to the `Vertex` and the weight/distance is `Distance`.
* We derive the actual `vertex` and `distance` from each edge.
* So, this is how we get the distance.
* Once we get the vertex, we check if we can relax it.
* If we can relax it, we add it to the `min-heap` along with the new, updated, reduced distance.
* When and how do we relax the vertex?
* Initially, we will add the `source` to the `min-heap`.
* Then, we run a while loop.
* We get the neighbors through the edges.
* And if relax the vertex according to the formula, we add it to the `min-heap`.
* The `min-heap` keeps the vertex with the shortest path on top.
* To keep the vertex with the shortest path on top, we must give the (Distance, Vertex) to the `min-heap`.
* So that it can compare and auto-sort based on the `distance`.
* Otherwise, we can also provide a custom comparator.
* The goal is to keep the shortest distance on top.
* And if there are multiple similar distances, the vertex with the smallest index takes the priority and stays on top.
* If the `min-heap` is not empty, we `poll` the top vertex.
* And we repeat the same process until the `min-heap` is empty.
* But when do we update the `prev` array?
* The `prev` array stores the information of the previous/parent vertex through which we reached the current vertex.
* And we can get this information when we get the neighbors from the vertex.
* So, when we get the neighbors to relax them, we have the required information to update `prev`.
* Now, let us use all these details, tools, information to model the story into the code:
---
```kotlin

fun shortestPathUsingDijkstra(source: Int, vertices: List<Vertex>) {
    val dist = Array<Int>(totalVertices) { Int.MAX_VALUE }
    val prev = Array<Int>(totalVertices) { -1 }
    val minHeap = PriorityQueue<Pair<Int, Int>>()
    dist[source] = 0
    minHeap.add(Pair(0, source))
    while (minHeap.isNotEmpty()) {
        val (distance, vertex) = minHeap.poll()
        val vertexWithEdges = vertices[vertex]
        for ((neighbor, distance) in vertexWithEdges.edges) {
            if (dist[neighbor] > dist[vertex] + distance) {
                dist[neighbor] = dist[vertex] + distance
                minHeap.add(dist[neighbor], neighbor)
            }
            prev[neighbor] = vertex
        }
    }
    // Shortest path from source to each node
    val stringBuilder = StringBuilder()
    dist.forEach {
        stringBuilder.append("$it , ")
    }
    println(stringBuilder)
}

```
---
* If we notice, we broke the original large problem into a smaller version.
* And when we got multiple options, we choose the extremum (minimum) first.
* And we eventually tried all the options.
* And we repeated the same process at each incremental stage.
* And we gradually built the solution for the original, larger problem.
* All these properties imply that this is a greedy approach.
* So, this algorithm is classified as a greedy algorithm.
---
* What is the concept of the known region in the Dijkstra's Algorithm?
---
* Why does it use a priority queue instead of a normal queue?
* To find the shortest path, we have to take the shortest path that is already known.
* It means that we always want to explore the nodes in the ascending order of their distances from the source node.
* And this is possible through the priority queue (min-heap), as we can maintain and get the extremum (min or max) efficiently in `O(n log n)` time. 
---
* Does Dijkstra's Algorithm use BFS?
* No. BFS uses a queue and a queue follows FIFO.
* Dijkstra's Algorithm uses min-heap.
---


## Next

* 