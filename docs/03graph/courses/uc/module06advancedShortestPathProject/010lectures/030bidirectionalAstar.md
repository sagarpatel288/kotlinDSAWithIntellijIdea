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
* Think of this like having two different thermometers with different offsets.
* We cannot conclude the final actual temperature by adding their values.
* We must first handle the offset.
* In our case, heuristics are our offsets.
* So, how do we handle them?
* So, what do we do?
---

**Differences of the heuristics**

* ![030differenceInBidirectionalAstar.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/030differenceInBidirectionalAstar.webp)

* The positive value indicates that the vertex is near the source and far away from the target.
* The negative value indicates that the vertex is far away from the source and near the target.
* If the difference is `0`, it indicates that the vertex is in the middle.

---

* Now, the only information that we know about the heuristics is (or we expect, assume that) the heuristics are consistent, and they satisfy the below consistency condition:

$$
h(u) &le; weight(u, v) + h(v)  
$$
$$
h(u) - h(v) &le; weight(u, v)
$$

* The consistent heuristics convey that for the adjacent vertices (u, v), the difference of their individual heuristics cannot exceed their edge weight.
* Using this information, we want to come up with some definite stop condition.
* In the A-Star algorithm, each vertex gets two heuristics due to forward and backward search.
* So, it becomes:

$$
\text{For u}: h_{T}(u) - h_{S}(u) 
$$

$$
\text{For v}: h_{T}(v) - h_{S}(v)
$$

* And their difference becomes:

$$
|\ (\ h_{T}(u)  -  h_{S}(u) \ )\ - (\ h_{T}(v)  -  h_{S}(v) \ )\ |
$$

$$
|\ (\ h_{T}(u)  -  h_{S}(v) \ )\ | + |\ (\ h_{T}(u)  -  h_{S}(v) \ )\ |
$$

$$
&le; |\ weight(u, v) + weight(u,v)\ |
$$

$$
&le; 2\ ( \ weight(u, v) \ )
$$

* Using this condition, we want to stop when:

$$
&ge; 2\ ( \ weight(u, v) \ )
$$

---


* Now, the questions is, how do we accommodate this with our priority queue structure?
* 

## Questions

* How does the difference of the heuristic from the target to the vertex v, and from the source to the vertex, v help? Why did we take the difference instead of taking the addition that we do in the bidirectional Dijkstra? What causes this change? Is there any role of the heuristic consistency there (that the difference between the heuristic u and heuristic v must be equal to or smaller than the edge weight between u and v)? What role? Why do we have this consistency? What if the heuristics are not consistent? What if they don't satisfy this consistency? Or maybe we are trying to ensure consistent heuristics through these formulas and math for the definite point (stop condition). I believe the answer is in the term "balanced priorities." I am unsure. What does that term mean, what does it indicate, what is the importance of that term? 
* What do we mean by "balance the forward and the backward search around the same potential?"
* Why do we add the positive potential to the forward queue and the same negative potential to the backward queue? What does it indicate? What does it do? How does it help?
* How did we get the equations of the forward and backward queue? How do we justify those equations?  
* For the source vertex S and the target vertex T, we still apply the actual cost from the source to the vertex v + the actual cost from the vertex v to the target = the complete path. But I believe I am missing the idea that connects us with these actual costs. And I also believe that the complications (difficulties) come from the fact that we store `f` to the priority queue, and not merely the actual values. So maybe, all these formulas are to get idea of the actual values from the stored heuristics, but I am unsure. 
* Why do we have a different main while loop condition than the bidirectional Dijkstra? Why do we process even if one of the queues is exhausted/empty? What does that indicate? How does that help?
* I feel disconnected. I believe I am missing many things and hence, I am unable to connect with the implementation with proper reasoning and understanding. 
---
* How does taking the difference of the heuristics help? I understood what it indicates. And now I am trying to understand its importance, need, impact, and contribution to solution. What does it contribute to the solution? How does it contribute to the solution? It might be a part of the bigger picture that I might be missing.
* How did we take that p(v)? On what basis? How did we come up with that formula? Why that formula only? What does it indicate? Why do we use p(v)? How does it help? Does it indicate initial potential of any vertex or general potential of any vertex at any time? I believe it is to come up with some ground level information using which we can get a deterministic lower bound, but I am unsure.
* I believe there must be some reason behind our goal to come up with these formulas for the forward and backward search priority queues. For example, if we take the reference of the `min stack using an array in O(1)` example, we multiply the value by `2` whenever we get the value that is less than the current minimum. Because it is a mathematical guarantee that the value will be smaller than the current min. So that whenever we pop the item and if it is less than the current min, we understand that it is an encoded value. Once we find that it is an encoded value, we can restore the previous minimum to the global `min` variable using the encoded value. Similarly, I believe there must be some concrete reason (maybe a mathematical guarantee or something else) behind these formulas. They might be establishing some guarantee.
* What is the general, overall idea, intuition, inspiration, and association principle for this entire process? I believe we are doing this process to come up with a reliable, deterministic, and confident stopping condition. But I am unable to connect all these different pieces together.
* I believe it all starts with this consistency guarantee: $h(u) &le; weight(u, v) + h(v)$. Which is, $h(u) - h(v) &le; weight(u, v)$. I believe that consistent heuristics convey that: "The difference of the individual heuristics of two adjacent vertices cannot exceed their edge weight." So, we might want to come up with a relevant stop condition. If we want to start from this information, and build all the other formulas and connections, including the formulas of forward and backward search priority queues, how do we do that? Using this information, we want to come up with some definite stop condition. Start from what we already know using the association principle. Use metaphors, analogies, oversimplification, non-technical examples, layman's language, and then model each point into the actual technical explanation and implementation, step by step, with justification. I believe this will work for me. This will help me connect everything and make sense of everything. I believe this will help me understand why we do what we do.
* Why don't we do something like we process by f(v), but we make the stop condition same as the bidirectional Dijkstra by using the actual distance (`gScore`) of the priority queue's item? I believe the reason is that the entire A* algorithm is based on and uses the estimated values to guide the search and to determine the actual shortest path. Now, I believe that there is a possibility that the next item (or any subsequent) item with higher heuristics (potential) has the shortest path. I am unsure about the answer to this question.   
* I understand that this process requires consistent heuristics. In general, do we get consistent heuristics or inconsistent heuristics? What will be the solution for the inconsistent heuristics? Start from what we already know using the association principle. Use metaphors, analogies, oversimplification, non-technical examples, layman's language, and then model each point into the actual technical explanation and implementation, step by step, with justification.
* We may need to take actual values using the simplest possible graph to understand the stop condition.