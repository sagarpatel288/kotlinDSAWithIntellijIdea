# BFS Traversal Of A Graph

## Prerequisites

* [Tree Traversals.md](../../../../02dataStructures/courses/uc/module01BasicDataStructures/section03trees/020treeTraversals.md)
* [Compute Tree Height Using BFS.md](../../../../02dataStructures/courses/uc/module01BasicDataStructures/section03trees/050computeTreeHeight.md)
* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)

## References

* [Tree Traversals.md](../../../../02dataStructures/courses/uc/module01BasicDataStructures/section03trees/020treeTraversals.md)
* [Compute Tree Height Using BFS.md](../../../../02dataStructures/courses/uc/module01BasicDataStructures/section03trees/050computeTreeHeight.md)
* [Shradha Madam](https://youtu.be/scQITTLgFJo?si=K-HBG0PKY3-Ta6He)

## Concept

* Previously, we have seen that:

```kotlin

class Graph(val size: Int) {
    val adjacencyList = List(size) { mutableListOf<Int>() }
    
    fun addEdges(a: Int, b: Int) {
        adjacencyList[a].add(b)
        adjacencyList[b].add(a)
    }
    
    fun printAdjacencyList() {
        val stringBuilder = StringBuilder()
        for ((index, neighbors) in adjacencyList.withIndex()) {
            stringBuilder.append("Vertex: $index Neighbors: ")
            neighbors.forEach {
                stringBuilder.append(" $it, ")
            }
            stringBuilder.append("\n")
        }
        println(stringBuilder)
    }
}

```

* BFS says cover the neighbor first.
* In a BST, we start from the root.
* In a graph, we can start from any vertex.
* The important point is that once we visit a node, we should mark it as visited.
* Because, a graph can have a cycle, and we do not want to keep traveling infinitely in a loop.
* And the size of this boolean array will be equivalent to the size of the vertex list, which is equivalent to the adjacency list.
* But we already have the `size` property from the constructor.
* So, we will use it.
* It is shared by the adjacency list and the boolean array.
* So, this will look something like below:

```kotlin

fun bfsTraversalOfGraph(start: Int) {
    if (start !in adjacencyList.indices) return
    val visited = BooleanArray(size)
}

```

* Now, we want to start from the given `start` vertex.
* But if we remember, we are using the direct addressing method.
* So, we treat the vertex value as the vertex index.
* So, if it is out of the range of the adjacency index range, we don't have that value and we return.
* Otherwise, we continue the program.
---
* Now, we use almost the same concept of **BFS Traversal in a Tree/BST**.
* References:
* [Compute Tree Height Using BFS.md](../../../../02dataStructures/courses/uc/module01BasicDataStructures/section03trees/050computeTreeHeight.md)
* So, we use a queue as below:

```kotlin

val queue = ArrayDeque<Int>()

```

* We add (enqueue, push) the given `start` vertex eagerly.

```kotlin

queue.addLast(start)

```

* And when we push a vertex to the queue, it means that we have visited it.
* So, we mark it as visited.
* And to mark it as visited, we have the visited boolean array.
* Again, we are using the direct addressing method.
* So, the value and location of the vertex are equal to the index.
* We go to that index in the visited boolean array, and mark it as visited.

```kotlin

visited[start] = true

```

* Similar to the **BFS Traversal in a Tree/BST**, we start the loop.
* As long as the queue is not empty, we pop the element.
* And remember, this is a queue.
* So, we `addLast` and `removeFirst`.
* We print it.
* And we add its immediate neighbors to the queue.
* And we get its immediate neighbors from the adjacency list.
* But the adjacency list can repeat the vertices.
* For example, if there is an edge between A and B, then B will be there in the neighbor list of A and A will be there in the neighbor list of B.
* Also, a vertex can be a neighbor of multiple other vertices.
* But we don't want to add the vertex if we have already visited it before.
* That's the point (time) when we use the entries of the visited boolean array.
* We add (enqueue, push) only unvisited vertices to the queue.
* So, it will be something like below:

```kotlin

while (queue.isNotEmpty()) {
    val pop = queue.removeFirst()
    stringBuilder.append(" $pop, ")
    val neighbors = adjacencyList[pop]
    neighbors.forEach {
        if (!visited[it]) {
            queue.addLast(it)
            visited[it] = true
        }
    }
}

```

* And finally, we print the BFS order.

## Disconnected graph

* A disconnected graph might look like below:

* ![Disconnected Graph.png](../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/02exploringGraph/045disconnectedGraph.png)

* To cover all the vertices, including the disconnected, we wrap/call the normal `BFS` function in a loop like below:

```kotlin

val visited = BooleanArray(size) { false }
for (vertex in adjacencyList) {
    if (!visited[vertex]) {
        bfs(vertex, visited)
    }
}

```

* For that, we may need to modify the existing `BFS` function slightly to accept the incoming `visited boolean array`.
* So, it becomes something like below: 

```kotlin

fun bfsTraversal(start: Int, visited_: BooleanArray? = null) {
    var visited = visited_
    if (visited == null) {
        visited = BooleanArray(size) { false }
    }
}

```

## Implementation 

* [Graph Traversal.kt](../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/020graphTraversal.kt)

## Next

* [Connectivity.md](035connectivity.md)