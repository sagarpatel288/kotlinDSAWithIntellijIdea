# Bellman Ford Algorithm

## Prerequisites

* [DijkstrasAlgorithm.md](030dijkstrasAlgorithm.md)

## Concept

* Earlier, we have seen the Dijkstra's Algorithm to find the shortest path (distance).
* And there was an important invariant (or assumption).

> Once we extract the (distance to vertex) pair from the `min-heap`, it cannot have a shorter distance than it.

* Let us use it again:

![Dijkstra On Negative Weight.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/04bellmanFordAlgorithmOfShortestPath/010dijkstraOnNegativeWeight.webp)

* As we can see in the image, the invariant (or assumption) fails when there is a negative weight on the edge.
* We might think that what is the problem if we add (-10 to B) again to the `min-heap` after we have already extracted (5 to B)?
* The problem is, if B had many outgoing edges, then adding (-10 to B) forces us (the algorithm) to inspect those outgoing edges again.
* And it breaks the promised time complexity of the algorithm.
* Because the promised time complexity does not include this case.

## Time Complexity

## Space Complexity

## Next