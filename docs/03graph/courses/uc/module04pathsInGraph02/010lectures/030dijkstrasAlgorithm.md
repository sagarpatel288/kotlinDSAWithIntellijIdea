# Dijkstra's Algorithm

## Prerequisites

*

## References

* [Spanning Tree](https://youtu.be/EFg3u_E6eHU?si=9LMk9KDV1Xl8Z264)

## Concept

* 
---
* Why does it use a priority queue instead of a normal queue?
* To find the shortest path, we have to take the shortest path that is already known.
* It means that we always want to explore the nodes in the ascending order of their distance from the source node.
* And this is possible through the priority queue, as we can get the extremum (min or max) efficiently in `O(log n)` time.
* In a normal queue, if the queue has "BC", then it will process "B" first and then "C".
* It doesn't matter for the normal queue which distance is the shortest, which node is the closest to the source node. 
---
* Does Dijkstra's Algorithm use BFS?
* No. BFS uses a queue and a queue follows FIFO.
* Dijkstra's Algorithm uses min-heap.
---


## Next

* 