# Bidirectional Dijkstra

## Prerequisites

## References

* [Practical Visual Comparison By PhysicsFX](https://youtu.be/JHgk9ZgHXjY?si=GGQ_LUa716OMFUZM)

## Concept

* Assume that there are two friends: A and B.
* They want to meet.
* We have three options: 
  * A travels towards B.
  * B travels towards A.
  * Both travel towards each other.
* Now, it is natural that if they both travel towards each other, they will meet faster.
* If we total the work done by A + the work done by B, it is almost similar to the cost if either only A had traveled towards B or B had traveled towards A.
* But then why do we say that when both travel towards each other, it is faster compared to when only one of them travel towards the other?
* Because when they both travel towards each other, the travel space that each one covers is reduced by almost half the total space.
* This is the general intuition behind Bidirectional Dijkstra.
---
* We saw the idea (intuition) that if both the source and the target travel towards each other, it improves the performance.
* In the practice, we don't travel simultaneously.
* But it doesn't change the fact that we still reduce the search space.
* And we can still get some benefits.
* To understand, we take an example of a circle.
---

![010bidirectionalDijkstra.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/010bidirectionalDijkstra.webp)

* We can see that Bidirectional Dijkstra is almost twice the faster than the normal Dijkstra.
* But there is even more practical insights.
---
* Six Handshakes

![020sixHandshakes.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/020sixHandshakes.webp)

* We are going to see a significant difference in the performance between a simple Dijkstra's Algorithm Vs. the Bidirectional Dijkstra's Algorithm.
* Assume that in a social network, a person knows 100 people, and each one of them knows another 100 people, and each one of them knows another 100 people, and so on...
* We can represent it in the form of circles. 
* We start with one person, and we draw a circle around the person to indicate the direct connection between that person and the 100 people.
* And because each one of this 100 people knows another 100 people, we draw another circle around those 100 people and so on...
* The two closest circles indicate the direct connections.
* Otherwise, every time we go beyond the direct connections, it is like crossing a level.
* Each level grows by 100 times.
* So, in the first level, we have 100 people ($100^1$).
* In the second level, we get 100 * 100 people ($100^2$).
* In the third level, we get 100 * 100 * 100 people ($100^3$).
* And so on...
* In the sixth level, we get ($100^6$) = 1 Trillion people.
---
* 
* Now, assume that we want to find a target person from the last level.
* The conceptual work for the normal Dijkstra's Algorithm would be to visit approximately $100^6 = 10^{12}$ people.
* On the other hand, if we use the Bidirectional Dijkstra, it becomes $100^3 + 100^3 = 2 * 10^6$.
* This difference is enormous.
---
* But how do we travel backward from the target vertex T to the source vertex S?
* We use two graphs.
* The original graph and the reversed graph.

![030reversedGraph.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/030reversedGraph.webp)

* And then we perform normal Dijkstra on both the graphs.
* In the original graph, we travel from the source vertex S to the target vertex T.
* And in the reversed graph, we travel from the target vertex T to the source vertex S.
* And when we find a common vertex from both the forward and the backward traversal, we stop.
---
* However, notice that meeting at the middle, or finding the common vertex point does not mean that the global shortest path goes through that vertex only.
* For example:

![040bidirectionalDijkstra.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/040bidirectionalDijkstra.webp)

* We need to understand what the `dist` array and the `priority queue` represent in the Dijkstra's algorithm.
* So, let us recall some implications of them in the normal Dijkstra's algorithm.
---
* Initially, all the values in the `dist` is `MAX`.
* Then, we start with the source and set its `dist` value to `0`.
* Then, we add `(distance, vertex)` to the priority queue.
* And then, as long as the priority queue is not empty, we repeat the below process:
* We extract the vertex.
* We inspect the outgoing edges.
* Each outgoing edge gives us the ending (destination, target, to) vertex.
* We call it discovery.
* It is like as if we are saying "we discovered a path to this vertex".
* The path doesn't have to be the final path.
* This is just a discovery.
* We check the `dist` value of that ending (destination, target, to) vertex.
* If we find that `dist[to] > dist[from] + weight(from, to)`, then we relax the edge.
* We update the corresponding `dist` for `to`.
* And when we relax the edge, we add `(distance, to)` to the priority queue.
* Now, adding `(distance, vertex)` to the priority queue does not mean that the `distance` is the final shortest distance for the `vertex`.
* We might add the same or different `distance` values for the same `to` vertex in the priority queue due to different connections and paths.
* So, a priority queue can have the same vertex with different values.
* Each `(distance, vertex)` value in the priority queue says that "there is a path to this vertex, and it costs us the `distance` value."
* We get the final shortest distance of a vertex only when we `poll` from the priority queue.
---
* The values in `dist` may decrease, but it cannot increase.
* Whenever we store or update some value in `dist`, it is like as if we are saying **"the value cannot be more than this."**
* So, it is like as if we are storing the **upper bound** in the `dist` and we keep decreasing this **upper bound** in the `dist` whenever we get the opportunity.
* We get this opportunity through **edge relaxation**.
* Similarly, the values of the `top` elements in our `min - heap priority queue` keep increasing.
* It starts with `0` and then it increases.
* Each `top` element conveys that "shortest path cannot be shorter than this".
* In other words, each `top` element has the **lower bound** of the shortest path.
* And as we make progress, this lower bound keeps increasing.
* Also, once we `poll` from the priority queue, the corresponding `dist` value is final. 
* It cannot decrease further.
* So, the `poll` says something like "the value cannot be smaller than this".
* The `poll` operation finalizes the corresponding `dist` value.
* In terms of distance, for the `(distance, vertex)` we get from `poll`, we can say that the `vertex` cannot have any shorter (smaller) distance than this `distance`.  
---
* So, in short, each value in the `dist` is the "upper bound of the shortest path" that goes through the corresponding vertex.
* In other words, it says that "the shortest path that goes through me cannot be larger than this value."
* Each `top` element of the `min-heap priority queue` is the "lower bound of the shortest path".
* In other words, it says that "the shortest path will be at least of this value."
* The `poll` stamps (finalizes) the shortest path for the corresponding vertex.
* In other words, it says that "the shortest distance to this vertex cannot be shorter/smaller than this value."
---
* So, we look for a vertex that both forward and backward search have finalized.
* So, a vertex that they both have `polled`.
* We take the corresponding `dist` value of such a vertex.
* Using that value, we perform: `min = minOf(min, value)`.
* Here, `min` represents the **upper bound**.
* It says that "We are expecting a lower value than this".
---
* If we combine all these representatives and their implications (perspectives), we get:
* Upper bound from `min`.
* Lower bound from the `top` elements of the priority queue.
* At any point, if we ever find that `lower bound >= upper bound`, it becomes our **stop condition**.
---
**Recap:**

* Bidirectional Dijkstra Search = Forward Search + Backward Search
* Forward Search = Original graph = From S to T
* Backward Search = Reversed graph = From T to S
* When do we stop?
* In the middle?
* Nah! The middle doesn't have to be the shortest path!
* Or: The shortest path doesn't have to go through the middle.
* Then?
* We track the complete path.
* Why? Because we are trying to find the shortest complete path!
* Ok. How do we track the complete path?
* Or in other words, how do we find the shortest complete path among all the complete paths we find?
```kotlin
var min = minOf(min, distance)
```
* Every time we get a complete path, we maintain the `min` among them.
* But how do we get the complete path?
* When we get the final distance of a particular vertex.
* When do we get the final distance for a particular vertex?
* When we `poll`.
* Ok. So, when we `poll`, we get the final distance for a particular vertex.
* So what? Then what? What do we do with that? 
* How do we use that to get the complete path?
* Whenever we get the final distance for a vertex in any of the searches, we check the `dist` value of other search for the same vertex.
* If it is not the default value in other search, then:
```kotlin
if (distOther[v] != MAX) {
  val distance = distThis[v] + distOther[v]
  min = minOf(min, distance)
}
```
* This is how we maintain the total minimum distance among all the complete paths we find.
* Until when do we keep finding the complete path and keep maintaining the minimum among them?
* The stop condition is:
```markdown
if (lowerBound >= upperBound) {
    stop, exit, no need to find the complete path for the remaining vertex
}
```

## Fragmented Points To Resolve, Connect, Organize Later

* `dist` gives `minimum lower bound`.
* Poll on the `priority queue` gives final - The path that goes from this extracted vertex cannot be any smaller than this? Again, it looks like a lower bound only.
* Observe the pattern of the values we poll from the priority queue. It keeps increasing.
* Observe the pattern of the `dist`. It keeps decreasing.
* So, saying "common vertex" is equal to saying "known complete path".
* And then, we use `min` to find the cheapest path among all the known complete paths.

## Questions

* Why is it so that Bidirectional Dijkstra is even faster in the case of social network than compared to the road network?
* Why is this applicable to undirected and non-negative weighted graph only?
* How do we interpret the phrase "when we find a common vertex processed by forward and backward search"? Is it related to discovery or finalization? How and when do we conclude it?
* How do we calculate, and conclude the complete path? When?