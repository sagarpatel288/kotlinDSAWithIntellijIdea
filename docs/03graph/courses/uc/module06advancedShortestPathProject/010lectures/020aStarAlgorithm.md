# A-Star (A*) Algorithm (Also known as Directed Search or Dijkstra with directed search)

## References

![005bidirectionalDijkstraVsAstarAlgo.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/005bidirectionalDijkstraVsAstarAlgo.webp)

* Reference: [PhysicsFX](https://youtu.be/JHgk9ZgHXjY?si=hAe_Dq11QiC_B6j0)
* The image is to help us visually understand the comparison of the bidirectional Dijkstra Vs. A* algorithm.

* Another visual (interactive) presentation is:
* [Red Blob Games](https://www.redblobgames.com/pathfinding/a-star/introduction.html)

* As usual, a beautiful card-stack presentation by Computerphile:
* [Computerphile](https://youtu.be/ySN5Wnu88nE?si=xBD_WvNpgeVx0PG6)
* This is so good! 
* It clearly shows how the algorithm works better and differently from the Dijkstra with good heuristics.

## Thought Process

![010a*algorithm.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/010a*algorithm.webp)

* An example of having an estimated value is when we have the co-ordinates of our current location, and the co-ordinates of the target location.
* Then, we can draw a straight line between our current location and the target location.
* And we ensure that our search does not go far away from that line.
---
* To remember the "Admissible Heuristic", we can use the below image:

![015straightLineVsActualPath.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/015straightLineVsActualPath.webp)

* The straight-line distance is the "Admissible Heuristic."
* It never overestimates the actual distance.
* The actual distance is always higher (longer) than the Admissible Heuristic.
  * (The image source is: [https://www.maps.ie/driving-distances/](https://www.maps.ie/driving-distances/))
* If $h(v)$ is a heuristic (estimated) value, and $d^{*}(v, t)$ is the actual value from the vertex to the target that we do not know yet, then we prefer that: 
* $0 <= h(v) <= d^{*}(v, t)$ 
---
* Think of this as a triangle-inequality rule: 
* The estimate from a location to the destination cannot exceed the cost of taking one step to a neighbor plus the neighbor's estimate.
---
* A consistent heuristic satisfies a stronger condition for every edge from u to v:
* $h(u) &le; weight(u, v) + h(v)$
* $h(u) - h(v) &le; weight(u, v)$
* The formula indicates that: 
* Moving from `u` to `v`, if we take the difference of their heuristics, it must be $&le; weight(u, v)$.
* Where, `u` is the start-vertex, and `v` is the end-vertex.
---

| Heuristic | Admissible? | Consistent? | Can A* guarantee the optimal path?                                                                                |
|-----------|-------------|-------------|-------------------------------------------------------------------------------------------------------------------|
| A         | Yes         | Yes         | Yes                                                                                                               |
| B         | Yes         | No          | Yes, provided the implementation reopens vertices when better routes are found and uses correct termination logic |
| C         | No          | No          | Not in general; it may return a suboptimal path                                                                   |
| D         | No          | Yes         | Not possible under the standard definitions when $h(t)=0$ and edge costs are nonnegative                          |

---
* How do we choose a good heuristic?
* Normally, we get the heuristic from the input itself.
* But in general, a heuristic depends on the true meaning of the edge weight and movement rules (capabilities).

![020heurisicDependsOnMovementRules.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/020heurisicDependsOnMovementRules.webp)

* For example, if the movement is possible only in four directions: up, down, left, or right, then we normally use the **Manhattan Distance** formula.
* $h = | x_1 - x_2 | + | y_1 - y_2 |$.
* It does not allow diagonal movement.
* For eight-direction movement where the diagonal movement is allowed, and each movement costs equal, we go with the **Chebyshev** heuristic.
* $h = max(|x_1 - x_2|, |y_1 - y_2|)$.
* When the straight-line (diagonal) distance between the source and the target, is lower bound, such as geometric movement with distance-based cost, we use the **Euclidean Distance**.
* $h = \sqrt{(x_1 - x_2)^{2} + (y_1 - y_2)^{2}}$
* And so on...
---
* Note that the A-Star algorithm without any heuristic values is the Dijkstra's Algorithm.
---

* In the A* algorithm, each vertex gets an estimated remaining value to the target.
* We call it a heuristic or sometimes, a potential of the vertex.
* So, each vertex gets a potential.
* How? Through a potential function.
* But this potential function has to ensure that it does not change the original shortest path.
* It can change the individual edge weights, but the original shortest path must remain the shortest one in the new graph, too.
* We call this new graph an adjusted graph, where each vertex gets potential from a potential function.
* We denote (express) this relation as:

$$
\pi: V &rightarrow; R 
$$

* It means that $\pi(v)$ gives some real number.
* Now, suppose that a vertex `u` has the potential, $\pi(u)$.
* And the vertex `v` has the potential, $\pi(v)$.
* If we move from `u` to `v`, the change in their potential becomes:

$$
\pi(v) - \pi(u)
$$

* Think of it like the change in the bank balance = final balance - initial balance.
* Or, change in the temperature = final temperature - initial temperature.
* And adjusted (new) bank balance = Original bank balance + Change in the bank balance.
* Or, adjusted (new) temperature = Original temperature + Change in the temperature.
* Similarly,
* Adjusted (new) edge weight = Original edge weight + Change in the potentials.

$$
l_{\pi}(u, v) = l(u, v) - \pi(u) + \pi(v)
$$

* It says that:

$$
\text{Adjusted edge weight = Original edge weight - Start vertex's potential + End vertex's potential}
$$

* We can test this formula on (for) a path from S to T.
* Suppose that the path is: S → a → b → T.
* For each edge, we get:

$$
l_{p}(S, a) = l(S, a) + \pi(a) - \pi(S)
$$

$$
l_{p}(a, b) = l(a, b) + \pi(b) - \pi(a)
$$

$$
l_{p}(b, T) = l(b, T) + \pi(T) - \pi(b)
$$

* And if we consider the complete path:

$$
l_{p}(S, T) = l(S, a) + \pi(a) - \pi(S) + l(a, b) + \pi(b) - \pi(a) + l(b, T) + \pi(T) - \pi(b) 
$$

$$
l_{p}(S, T) = l(S, a) - \pi(S) + l(a, b) + l(b, T) + \pi(T)
$$

$$
l_{p}(S, T) = l(S, T) - \pi(S) + \pi(T)
$$

* For any path (P), if the start-vertex `S` is fixed, and the end-vertex `T` is fixed, then:

$$
l_{p}(P) = l(P) - \pi(S) + \pi(T)
$$

* In the formula (expression), `P` represents the path we chose.
* `S` is the fixed start-vertex.
* And `T` is the fixed end-vertex.
* The change in the potential $\pi(T) - \pi(S)$ does not depend on the path we chose.
* And this is an important finding (conclusion).
* It represents the overall picture:

$$
\text{Adjusted edge weight = Original edge weight - Potential of the start vertex + Potential of the end vertex}
$$

* All the intermediate labels (potentials) disappeared.
* It means that if the start-vertex `S` is fixed, and the end-vertex `T` is fixed, then no matter what path we chose, we can calculate the adjusted edge weight for any path.
* Because whatever path we chose, we just have to add the overall change in the potential to the original value.
* And the overall change in the potential is:

$$
\text{Potential of the end-vertex - Potential of the start-vertex}
$$

* For example, we can have two different paths, $P_{1}$ and $P_{2}$.
* Then:

$$
l_{\pi}(P_{1}) = l(P_{1}) - \pi(S) + \pi(T)
$$

$$
l_{\pi}(P_{2}) = l(P_{2}) - \pi(S) + \pi(T)
$$

* The important point to notice here is that no matter which path we chose, the term $\pi(T) - \pi(S)$ remains the same as long as for both the paths, the start-vertex is S and the end-vertex is T.
* It means that we want to find the relative change, we get:

$$
l_\pi(P_{2}) - l_\pi(P_{1}) = l(P_{2}) - \pi(S) + \pi(T) - l(P_{1}) + \pi(S) - \pi(T)
$$

$$
l_\pi(P_{2}) - l_\pi(P_{1}) = l(P_{2}) - l(P_{1})
$$

* It means that the relative change of the adjusted edge weight in the adjusted graph is equal to the relative change in the original graph.
* We can understand this with a small example:

| Student | Original score | Fixed bonus | New score |
|---------|----------------|-------------|-----------|
| A       | 70             | +10         | 80        |
| B       | 90             | +10         | 100       |

* Adding a fixed bonus represents the potential.
* The difference of the old score = 90 - 70 = 20.
* The difference of the new score = 100 - 80 = 20.
* The difference of the new score = The difference of the old score.
* We can apply the same idea to the graph.
* 
---

* Suppose that $\pi(T)$ is `7` and $\pi(S)$ is `4`.
* So, the adjustment (change) becomes: $\pi(T) - \pi(S) = 7 - 4 = 3$.
* Now, if $l(P_{1})$ is $10$ and $l(P_{2})$ is $15$, their difference is 15 - 10 = 5.
* And $l(P_{1})$ is the shortest path.
* Now, in the adjusted graph, with potential adjustment, $l_{\pi}(P_1)$ becomes $l(P_{1}) + 3 = 10 + 3 = 13$.
* And $l_{\pi}(P_2)$ becomes $l(P_{2}) + 3 = 15 + 3 = 18$.
* Their difference is still $18 - 13 = 5$, and $l_{\pi}(P_1)$ is still the shortest path.
* This is an important achievement.
* The original shortest path of the original graph remains the shortest path in the adjusted graph, too.
* That's what we wanted to ensure and achieve.

---

* Now, the idea is to connect the two information we have:
* The actual edge weight + the estimated remaining value to the target.
* When we combine, we get:

$$
\text{ Estimated total cost = Actual cost incurred + Estimated remaining cost}
$$

$$
f(v) = g(v) + h(v)
$$

* The heuristic does not change the true result.
* It may change the order in which we explore the vertices compared to Dijkstra's algorithm.


---

## Steps to code

* We start from the source. 
* So, the actual value `g(s)` will be `0`.
* We get the heuristic value of the source, `h(s)`.
* We calculate the total: `f(s) = g(s) + h(s)`.
* We update the `dist` value of the source.
* We add it to the priority queue.
* And as long as the priority queue is not empty, we repeat:
* We inspect the outgoing edges of the source.
* We try to relax each edge.
* For each edge relaxation, we update (add it to) the priority queue.
* To reconstruct the path, we also update the `parents`.
---
* For edge relaxation, we iterate through the edge list of a particular vertex.
* So, we need:

```kotlin

data class Edge(val to: Int, val weight: Long)

val adjList = List(totalVertices) { mutableListOf<Edge>() }

```

---

* We want to return the result with the distance and the path.
* So, we need:


```kotlin

data class Result(val distance: Int, val path: List<Int>)

```

---

* In the Dijkstra or Bidirectional Dijkstra's algorithm, the priority queue gets: `(distance, vertex)`.
* Here, we want to prioritize based on `f(v)`.
* And in case of tie, we also want to consider the actual distance, `g(v)`.
* So, the priority queue will get:

```kotlin

data class QueueItem(val fScore: Long, val gScore: Long, val vertex: Int)

```

---

* Note that `gScore` is our `dist` (actual distance value).
* So, the `gScore` will also help us remove the stale entries from the queue as we have seen in the normal Dijkstra and Bidirectional Dijkstra. 

---

* When do we stop?
* When we `poll` the `target` from the priority queue.

---

* We also call it "Directed Search" because heuristics guide the search.
* The search is based upon the provided heuristics (estimated values).
* It is the heuristics driven search.
* Hence, it is a guided search or directed search.

--- 

## Time Complexity

* Same as the Dijkstra's algorithm.
* But practical performance can be considerably, significantly efficient.
---
* Building the priority queue: $O(V)$
* A `poll` operation on the priority queue: $O(log V)$
* Total `poll` operations: $O(E)$
* Total cost of the `poll` operations: $O(E log V)$
* Total cost becomes: $O(V + E \ \log\ V)$.
* For a simple graph, $E <= V^{2}$.
* So, we can simplify the final time complexity as:
* $O((V + E) \ \log\ V)$.

## Space Complexity

* Same as the Dijkstra's algorithm.
* $O(V + E)$.
* That comes from adjacency list, `gScore`, `parents`, `priority queue`, etc.

## Implementation

* [A-Star Algorithm.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module06advancedShortestPathProject/part01concepts/030aStarAlgorithm.kt)

## Which one to use when

| Situation                                                           | Recommended algorithm                    | Why                                                                             |
|---------------------------------------------------------------------|------------------------------------------|---------------------------------------------------------------------------------|

## Questions

* What is h(t)? Why does it have to be 0? What does it imply?
* What happens when we get negative heuristics?
* Does it follow the same order of vertices as the Dijkstra's algorithm? Why?
  * No. It depends on the estimated value.
  * However, if the estimated value "h" is "0" for all the vertices, then it processes the vertices same as the Dijkstra's algorithm.
* Do we also call it "Directed search?"
* When to use: Dijkstra Vs. Bidirectional Dijkstra Vs. A* Vs. Bidirectional A* Vs. BFS, etc.?
