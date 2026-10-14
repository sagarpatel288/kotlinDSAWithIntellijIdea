# Bidirectional A*

## Prerequisites

* [Dijkstras Algorithm.md](../../module04pathsInGraph02/010lectures/030dijkstrasAlgorithm.md)
* [Bidirectional Dijkstra.md](010bidirectionalDijkstra.md)
* [Bidirectional Astar.md](030bidirectionalAstar.md)

## Thought Process

* Let us recall the overview of the bidirectional Dijkstra's process.
* We search in two directions: Forward and backward.
* We take 2 arrays to store the distance: Forward and backward.
* We take 2 priority queues: Forward and backward.
* We store `(distance, vertex)` in each queue.
* The `distance` is the actual distance from the source vertex to the `vertex`.
* The distance associated with the polled vertex is the final.
* The vertex cannot get a smaller (shorter) distance than that.
* Also, if the vertex that we poll from one queue has a tentative distance stored in the relevant distance array of the other side, then:
* `forwardDist[u] + weight(u, v) + backwardDist[v]` = `complete path`.
* If this complete path is less than the shortest complete path found so far, then we update the global `min` or `shortestPathDistance` variable.
* It means that the global `min` is our upper bound, and it expects a lower value.
* Also, the item that we remove from the queue cannot be smaller than the previous item, because we do not allow negative weights.
* It means that the item that we poll from the queue is our lower bound.
* It says that "the next item cannot be smaller than me!".
* All these together, give us the stop condition:
* Stop when: `lowerBound >= upperBound`.
* I.e., stop when: `forwardDist[u] + weight(u, v) + backwardDist[v] >= min`.
* One of the critical parts that makes this possible is the fact that we store the actual distance in the priority queues.
* However, in the A* algorithm, we store:
* `f(v)`, which is `f(v) = g(v) + h(v)`.
* `f(v)` is not the actual distance.
* It means that we cannot conclude it definitely that adding `f(v)` of each priority queue will be the final and actual shortest distance.
* So, what do we do?
---
* 

## Questions

* How does the difference of the heuristic from the target to the vertex v, and from the source to the vertex, v help? Why did we take the difference instead of taking the addition that we do in the bidirectional Dijkstra? What causes this change? Is there any role of the heuristic consistency there (that the difference between the heuristic u and heuristic v must be equal to or smaller than the edge weight between u and v)? What role? Why do we have this consistency? What if the heuristics are not consistent? What if they don't satisfy this consistency? Or maybe we are trying to ensure consistent heuristics through these formulas and math for the definite point (stop condition). I believe the answer is in the term "balanced priorities." I am unsure. What does that term mean, what does it indicate, what is the importance of that term? 
* What do we mean by "balance the forward and the backward search around the same potential?"
* Why do we add the positive potential to the forward queue and the same negative potential to the backward queue? What does it indicate? What does it do? How does it help?
* How did we get the equations of the forward and backward queue? How do we justify those equations?  
* For the source vertex S and the target vertex T, we still apply the actual cost from the source to the vertex v + the actual cost from the vertex v to the target = the complete path. But I believe I am missing the idea that connects us with these actual costs. And I also believe that the complications (difficulties) come from the fact that we store `f` to the priority queue, and not merely the actual values. So maybe, all these formulas are to get idea of the actual values from the stored heuristics, but I am unsure. 
* Why do we have a different main while loop condition than the bidirectional Dijkstra? Why do we process even if one of the queues is exhausted/empty? What does that indicate? How does that help?
* I feel disconnected. I believe I am missing many things and hence, I am unable to connect with the implementation with proper reasoning and understanding. 