# A-Star (A*) Algorithm

![010a*algorithm.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/010a*algorithm.webp)

* An example of having an estimated value is when we have the co-ordinates of our current location, and the co-ordinates of the target location.
* Then, we can draw a straight line between our current location and the target location.
* And we ensure that our search does not go far away from that line.
---
* Think of this as a triangle-inequality rule: 
* The estimate from a location to the destination cannot exceed the cost of taking one step to a neighbor plus the neighbor's estimate.
---
* A consistent heuristic satisfies a stronger condition for every edge from u to v:
* $h(u) <= weight(u, v) + h(v)$
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
* 

## Questions

* What is h(t)? Why does it have to be 0? What does it imply?
