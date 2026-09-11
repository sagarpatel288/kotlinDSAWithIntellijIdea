# Shortest Path

## Prerequisites

* [Path, Distance, And, Levels Intro.md](010pathDistanceAndLevelsIntro.md)

## Concept

* ![Shortest Path Using Distance Level Tree Without Cycle.webp](../../../../../../assets/images/03graph/courses/uc/module03pathsInGraph01/080video08shortestPathUsingDistanceLevelTreeWithoutCycle/010shortestPathUsingDistanceLevelTreeWithoutCycle.webp)

* Once we have the distance-level representation of a graph where the representation does not have any cycle, we can find the shortest path between two nodes.
* Because the distance-level representation is based on the distance between two nodes, and distance means the shortest path.
* We can call this distance-level representation a tree.
* And we apply BFS Traversal with a few more assignments to find the shortest path (Distance) between two nodes.
* The idea is that we would use two arrays: `dist` and `prev`.
* Each of size vertices.
* Initially, all the values in the `dist` will be "infinite", because we have not visited any node yet.
* We update the actual distance as we visit each node.
* And it will help us determine whether we have visited a particular node already.
* For example, if the distance value of a particular node is `infinite`, it means that we have not visited it yet.
* And we update the actual distance value only for the nodes that we are visiting for the first time.
* And we update the value based on the "+1" theory.
* For example, if we go from "A to B", then `dist[B] = dist[A] + 1`.
* Because going from A to B indicates that we have already visited "A" before we visited "B".
* In other words, we visited "B" while exploring "A.
* And as we can go from A to B, it also indicates that "B" is at "+ 1" distance-level than "A".
* So, if "A" is at distance-level 0, then "B" is at distance level "1".
* Similarly, we also update the `prev` array.
* So, `prev[B] = A`.
* And we do this when we explore the neighbors of "A" and we find "B" as one of the neighbors of "A".
* So, it becomes something like below:

```kotlin

fun bfs(root: Int, destination: Int) {
    val queue = ArrayDeque<Int>()
    queue.addLast(root)
    dist[root] = 0 // The distance from the root to the root is 0
    prev[root] = -1 // There is no previous vertex of root
    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        if (vertex == destination) break
        val neighbors = adjacencyList[vertex]
        neighbors.forEach {
            if (dist[it] == Int.MAX_VALUE) {
                dist[it] = dist[vertex] + 1
                prev[it] = vertex
            }
        }
    }
}

```

* So, we start our BFS traversal from the root node.
* If the root node is "A" and the destination node is "I", then the `prev` array gives us the shortest path from "A to I".
* And to get the shortest path from "I to A", we just need to reverse the `prev` array. 

## Note

* We are using the adjacency list for the BFS traversal.
* But we may think that we need to optimize it to remove any cycle first.
* So, maybe we need to have the "cycleDetection" function, that first detects the cycle.
* And then we break that cycle and create a new adjacency list that does not have any cycle.
* And then we use this new adjacency list for our BFS traversal.
* But we are already handling a cycle by using the `dist` check.
* We don't process the same node twice.
* So, we don't need to handle and break the cycle separately.

## Next

* 