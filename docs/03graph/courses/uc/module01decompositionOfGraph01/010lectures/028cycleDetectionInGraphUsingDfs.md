# Cycle detection in a graph (Undirected, bidirectional) using DFS

## Prerequisite

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)
* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)

## References

* [Shradha Madam](https://youtu.be/OZClCpPQDR4?si=hhkQFRSrQIriofYg)

## Concept

**Cycle in a graph**

* ![Cycle In A Graph.png](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/014simpleGraphSample.png)

* We can see that if we start traveling the given graph without any exit condition, hoping that we will get a dead-end where there will be no next vertex to visit, we keep running in a loop.
* For example, if we start from `0`, it will be an infinite loop like `0, 1, 2, 0, 1, 2, 0, 1, 2, 0...` and so on.
* So, there is a cycle.
* And we want to determine whether there is a cycle or not.
* So, how do we detect that?

**Observation**

* What is cycle?

* ![Cycle.webp](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/002cycle.webp)

* We might think that if we can go from "A" to "B" and also from "B" to "A", then there is a cycle between "A" and "B".
* But it is normal in an undirected graph.
* That's why we call it "bidirectional graph".
* In an undirected graph, if there is an edge between "A" and "B", it means that we can go from "A" to "B" and we can also go from "B" to "A".
* And it is not a cycle in an undirected (bidirectional) graph.

---
* So, what is the cycle in an undirected graph?

![Cycle In An Undirected Graph.webp](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/020cycleInUndirectedGraph.webp)

* If there are multiple ways to reach from "A" to "B", then there is a cycle.
* For example, in the given image, we can see that the last image has a cycle.
* We can observe that "0" can reach "2" as "0 → 1 → 2".
* And "0" can reach "2" as "0 → 2" as well.
* So, there is a cycle.
* Now, although there is no "parent" or "child" concept in a graph, for the sake of understanding purpose only, we use it here.
* Informally, we would say if we reach from "A" to "B" as A → B, then "A" is the parent of "B".
* And as we are going from "A" to "B" as A → B, it means that first we visit "A" and then we visit "B".
* So, it is normal to say that the parent vertex is the visited vertex.
* Or we might say it the other way:
* The visited vertex is the parent vertex.
* And if we get a vertex which is already visited, but not the parent, we will pay special attention.
* With that in mind, "2" has multiple parents.
* When we follow "0 → 1 → 2", "1" is the parent of "2".
* And when we follow "0 → 2", "0" is the parent.
* A vertex can have multiple parents, and it doesn't indicate a cycle.
* For example, in the second image, we can see that "1" has two parents: "0" and "2".
* Similarly, in the third image, we can see that "2" has two parents: "0" and "1".
* So, having multiple parents is fine, and it does not indicate a cycle in an undirected graph.
* Now, let us observe what happens when there is a cycle in an undirected graph.
* We take the last image to follow.
* Suppose we start with "0".
* For the vertex "0", there is no parent because we started the exploration from it. 
* We get "0", then we get "1".
* "1" is unvisited.
* For "1", the parent is "0".
* From "1", we get "2".
* "2" is unvisited.
* For "2", the parent is "1".
* From "2", we get "0".
* "0" is already visited, but it is not the parent!
* And this is the point that indicates that there is a cycle!
* So, if exploration of a particular vertex reveals a neighbor which is already visited, but it is not the parent, then it indicates the cycle.
---
* Now, we already know how to explore a vertex or a graph.
* We have already learned about DFS and BFS to explore a vertex or a graph.
* But how do we model our conclusion about the cycle detection of an undirected graph into the explore logic?
---
* We know that the vertex from which we start exploration gets no parent.
* And then as we move forward, we may find a neighbor or neighbors, and then we explore each neighbor.
* So, while exploring the neighbor, how about attaching the parent information there?
* In DFS, we already send the vertex that we want to explore, and the visited boolean array.
* Along with that, we would also send the parent information for the vertex that we are about to explore.
* So, it becomes:
```kotlin

fun dfs(vertex: Int, visited: BooleanArray, parent: Int) {
    
}

```

**DFS Logic**

* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)

* We use almost the same `DFS` concept to detect the cycle.
* Now, we might eagerly assume that if the `visited` condition is true, it indicates the cycle.
* For example, the `if (!visited[it]).... else -> there is a cycle`.
* But that's not true.
* For example, suppose we have the following simple graph:

* ![Simple Graph Sample.png](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/010simpleGraphSample.png)

* And suppose we start the traversal from the vertex `0`.
* We mark it as visited.
* We get the neighbor list and we get `1`.
* We pass it to the recursive function.
* We mark it as visited.
* We get the neighbor list and we get `0`.
* We find that it is already visited.
* But that doesn't imply that there is a cycle!

---

**Perspective/Intuition**

* There is no parent concept in graphs.
* But if we are going from `0 to 1` and if we are at `1`, we can say that `0` is the parent of `1`.
* And in that case, because we have moved from `0 to 1`, it is obvious that `0` is already visited.
* It means that if `1` has a parent, it is already visited.

---

* Now, the cycle detection theory (for an undirected graph) says that:
* If a vertex has a neighbor which is already visited, but it is not the parent of the vertex, it is the back edge.
* In other words, there is a cycle.

---

* Let us understand this with an example.

* ![Simple Graph Cycle Sample.png](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/014simpleGraphSample.png)

* We start from `0`.
* We move to `1`.
* The vertex `1` has two neighbors: `0, 2`.
* The vertex `0` is the parent, and it is already visited.
* The vertex `2` is the neighbor, and we have not visited it, yet.
* So, we move from `1` to `2`.
* The parent of the vertex `2` is `1`.
* We mark `2` as visited.
* We get the neighbors of `2`, which gives us `0, 1`.
* `1` is the parent and it is already visited.
* Now, there is an interesting perspective/intuition.
* The vertex `2` has a neighbor `0`, it is already visited, but it is not the parent.
* That's the back edge.
* That's the cycle.
* So, the rule is:

> If the vertex has a neighbor which is not the parent, but still visited, that's the cycle.

---

* We can check the logic for an undirected graph that has no cycle.

* ![Simple Graph Sample.png](../../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/022cycleDetection/012simpleGraphSample.png)

* We start from `0`.
* We mark it as visited.
* We get the neighbors: `1`.
* It is not the parent.
* It is a neighbor, but we have not visited it, yet.
* So, we move to `1`.
* We mark it as visited.
* We get the neighbors: `0, 2`
* `0` is already visited, but it is the parent.
* `2` is a neighbor, but we have not visited it, yet.
* So, we move to `2`.
* We mark it as visited.
* We get the neighbors: `1`.
* `1` is already visited, but it is the parent.
* And there is no other neighbor.
* So, there is no cycle.

---

* The additional things than the `DFS Traversal of an undirected graph` are: 
* The `parent` argument, and the condition that determines the cycle: 
* And the condition is: `if the neighbor is already visited, but it is not the parent`.
* So, the code becomes:

```kotlin

fun hasCycle(): Boolean {
    val visited = BooleanArray(size) { false }
    var hasCycle = false
    for (vertex in adjacencyList.indices) {
        if (!visited[vertex]) {
            // When we start traveling from the `vertex`, there is no parent of it.
            // It is like we are going to start exploring an independent component of the graph.
            // And we have chosen to start with the `vertex`.
            // So, it is the starting point.
            // So, there is no parent.
            // So, we pass `-1` as a parent.
            hasCycle = hasCycleUsingDfs(vertex, -1, visited)
            if (hasCycle) return true
        }
    }
    return hasCycle
}

fun hasCycleUsingDfs(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
    println(vertex) // Optional
    visited[vertex] = true
    val neighbors = adjacencyList[vertex]
    neighbors.forEach {
        if (!visited[it]) {
            // For this neighbor `it`, the `vertex` is the parent.
            // Because we are moving from the `vertex` to `it`.
            // So, we pass `vertex` as the parent.
            if (hasCycleUsingDfs(it, vertex, visited)) return true
        } else if (it != parent) {
            // This neighbor is already visited, but it is not the parent
            println(it) // Optional
            return true
        }
    }
    return false
}

```

## Implementation

* [Cycle detection in a graph](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/9ebecde9affadc011bd586c35c0d7c1801181472/src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/030cycleInUndirectedGraph.kt)

## Next

* [Cycle Detection In Graph Using Bfs.md](030cycleDetectionInGraphUsingBfs.md)
