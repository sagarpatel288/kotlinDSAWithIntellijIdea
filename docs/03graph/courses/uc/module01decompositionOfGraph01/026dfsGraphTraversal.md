# DFS Traversal in a graph

## Prerequisites

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)

## References

* 

## Concept

* Similar to the `BFS Traversal`, we use a `visited boolean array`.
* We iterate through the adjacency list to cover each vertex.

```kotlin

fun dfsAll() {
    val visited = BooleanArray(size) { false }
    for (vertex in adjacencyList.indices) {
        // If the vertex is not visited, we pass it to the `dfsTraversal` along with the `visited` boolean array.
        if (!visited[vertex]) {
            dfsTraversal(vertex, visited)
        }
    }
}

```

* The `DFS Traversal` function is recursive.
* What does it do recursively?
* **Print the incoming vertex, get the neighbors, recall itself for each unvisited neighbor.**

```kotlin

fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(vertex)
    val neighbors = adjacencyList[vertex]
    neighbors.forEach {
        if (!visited[it]) {
            dfsTraversal(it, visited)
        }
    }
}
```

## Dry Run

* Suppose that we have the following disconnected graph (this works for a connected graph, too).

* ![Disconnected Graph.png](../../../../../assets/images/03graph/courses/uc/module01decompositionOfGraph01/02exploringGraph/047disconnectedGraph.png)

* We need the adjacency list for the `DFS Traversal` (as well as for the `BFS Traversal`).
* The adjacency list is:

```markdown

| Vertex | Neighbors |
|--------|-----------|
| 0      | 1, 2      |
| 1      | 0         |
| 2      | 0, 3, 4   |
| 3      | 2         |
| 4      | 2, 5, 6   |
| 5      | 4         |
| 6      | 4         |
| 7      | 8         |
| 8      | 7         |

```

* Once we have the adjacency list, we define a `visited boolean array`.
* We follow the process for each vertex.
* So, we iterate through the given adjacency list.
* The `dfs` function looks like below:

```kotlin

fun dfsAll() {
    val visited = BooleanArray(size) { false }
    // Try each vertex
    // Vertex is an index of the adjacency list
    // An index of the adjacency list represents the vertex
    for (vertex in adjacencyList.indices) {
        // 0, 1, 2, 3, 4, 5, 6, 7, 8
        // For each unvisited vertex, we pass it to the `dfsTraversal` function along with the `visited` boolean array.
        // First, we get `0`
        // `0` is not visited, yet.
        // So, we pass it to the `dfsTraversal` along with the `visited` boolean array.
        if (!visited[vertex]) {
            dfsTraversal(vertex, visited)
        }
    }
}

```

* The `dfsTraversal` function looks like:

```kotlin

// 0, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(vertex) // Prints 0
    visited[vertex] = true // Marks `0` as visited
    val neighbors = adjacencyList[vertex] // Neighbors of `0` = 1, 2
    // For each neighbor
    // We add (enqueue) each unvisited neighbor to the queue
    neighbors.forEach {
        // 1, 2
        // None of them have been visited, yet.
        if (!visited[it]) {
            dfsTraversal(it, visited) // passing `1`
        }
    }
}
```

* The `dfsTraversal` gets `1` and prints `1`.
* So, the print order becomes:

```markdown

0, 1
```

* Now, `1` is in the function and we have already printed it.
* So, we process the remaining process: Get the neighbor and recall the self for each unvisited vertex.

```kotlin

// 1, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(1)
    val neighbors = adjacencyList[vertex] // Neighbors of `1` = 0
    // 0
    neighbors.forEach {
        // But we have already visited `0`
        // `0` has been marked `visited`
        // So, `0` does not pass the `if` condition
        if (!visited[it]) {
            
        }
    }
}
```

* Now, the control goes back to the outer for-loop.

```kotlin

fun dfsAll() {
    val visited = BooleanArray(size) { false }
    // 0, 1, 2, 3, 4, 5, 6, 7, 8
    for (vertex in adjacencyList.indices) {
        // 0 and 1 have been marked `visited`
        // So, this time we get `2`
        if (!visited[vertex]) {
            dfsTraversal(vertex, visited)
        }
    }
}

```

* The `dfsTraversal` gets `2` along with the `visited` boolean array.

```kotlin

// 2, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(2)
    val neighbors = adjacencyList[vertex] // Neighbors of `2` = 0, 3, 4 
    neighbors.forEach {
        // 0, 3, 4
        // But we have already marked `0` as visited.
        // So, only 3 and 4 pass the `if` condition.
        if (!visited[it]) {
            dfsTraversal(it, visited) // 3
        }
    }
}

```

* This time, we print `2`.
* So, the print order becomes:

```markdown

0, 1, 2
```

* We get the neighbors of the incoming argument (`2`), which is `0, 3, 4`.
* We recursively call the function for each unvisited neighbor.
* `0` is already visited.
* So, it does not pass the `if` condition.
* So, only `3` and `4` pass through the `if` condition.
* So next, the `dfsTraversal` gets `3`.

```kotlin

// 3, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(3)
    val neighbors = adjacencyList[vertex] // Neighbors of `3` = 2
    neighbors.forEach {
        // `2` is already marked as visited.
        // So, `2` will not pass the `if` condition.
        if (!visited[it]) {
            
        }
    }
}

```

* The `dfsTraversal` gets `3`.
* It prints it.
* So, we get the print order as:

```markdown

0, 1, 2, 3

```

* Then we get the neighbors of the incoming vertex, and recursively call the function for each unvisited node.
* The neighbor list of `3` is `2`.
* And we have already marked `2` as visited.
* So, the control goes back to the parent function in the call stack.
* This time, `dfsTraversal` gets `4` as the remaining neighbor of `2`.

```kotlin

// 4, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(4)
    val neighbors = adjacencyList[vertex] // Neighbors of `4` = 2, 5, 6
    neighbors.forEach {
        // 2, 5, 6
        // We have already marked `2` as visited
        // So, only 5 and 6 pass the `if` condition
        if (!visited[it]) {
            dfsTraversal(it, visited) // 5, visited
        }
    }
}

```

* The `dfsTraversal` gets `4` and eagerly prints the incoming vertex.
* So, the print order becomes:

```markdown

0, 1, 2, 3, 4
```

* Then, we get the neighbors of `4`, which are `2, 5, 6`.
* We have already marked `2` as visited.
* So next, the `dfsTraversal` gets `5`.

```kotlin

// 5, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(5)
    val neighbors = adjacencyList(vertex) // Neighbors of `5` = 4, 6
    neighbors.forEach {
        // We have already marked `4` as visited.
        // So, only `6` passes the `if` condition.
        if (!visited[it]) {
            dfsTraversal(it, visited) // 6
        }
    }
}
```

* The `dfsTraversal` prints the incoming vertex, `5`.
* So, the print order becomes:

```markdown

0, 1, 2, 3, 4, 5
```

* Then, we get the neighbors of `5` and recursively call the function for each unvisited neighbor.
* The vertex `5` has two neighbors: `4, 6`.
* We have already marked `4` as visited.
* So next, the `dfsTraversal` gets `6`.

```kotlin

// 6, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(vertex)
    val neighbors = adjacencyList[vertex] // Neighbors of `6` = 4
    // 4
    neighbors.forEach {
        // We have already marked `4` as visited.
        // So, it does not pass the `if` condition.
        if (!visited[it]) {
            
        }
    }
}

```
* The `dfsTraversal` gets and prints the incoming vertex, `6`.
* So, the print order becomes:

```markdown

0, 1, 2, 3, 4, 5, 6
```

* Then, we get the neighbors of `6`, which is `4`.
* But we have already marked `4` as visited.
* So, the control goes back to the outer for-loop.

```kotlin

fun dfsAll() {
    val visited = BooleanArray(size) { false }
    for (vertex in adjacencyList.indices) {
        // 0, 1, 2, 3, 4, 5, 6, 7, 8
        // We have already marked 0, 1, 2, 3, 4, 5, and 6 as visited.
        // So the next candidate is: 7 
        if (!visited[vertex]) {
            dfsTraversal(vertex, visited) // 7, visited
        }
    }
}

```

* The `dfsTraversal` function gets and prints the incoming vertex, `7`.

```kotlin

// 7, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(vertex)
    val neighbors = adjacencyList(vertex) // Neighbors of `7` = 8
    neighbors.forEach {
        // 8
        // We have not visited 8, yet.
        // So, `8` passes the `if` condition.
        if (!visited[it]) {
            dfsTraversal(it, visited)
        }
    }
}
```

* The `dfsTraversal` gets and prints the incoming vertex, `7`.
* So, the print order becomes:

```markdown

0, 1, 2, 3, 4, 5, 6, 7
```

* Then, we get the neighbors of `7`, which is `8`.
* So next, the `dfsTraversal` gets `8`.

```kotlin

// 8, visited
fun dfsTraversal(vertex: Int, visited: BooleanArray) {
    println(vertex)
    val neighbors = adjacencyList[vertex] // Neighbors of `8` = `7`
    // 7
    neighbors.forEach {
        // We have already marked `7` as visited.
        // So, `7` does not pass the `if` condition.
        if (!visited[it]) {
            
        }
    }
}
```

* The `dfsTraversal` function gets and prints the incoming vertex, `8`.
* So, the print order becomes:

```markdown

0, 1, 2, 3, 4, 5, 6, 7, 8
```

* Then, we get the neighbor list of `8`: `7`
* We recursively call the function for each unvisited neighbor.
* But we have already marked `7` as visited.
* So, the control goes back to the outer for-loop.

```kotlin

fun dfsAll() {
    val visited = BooleanArray(size) { false }
    for (vertex in adjacencyList.indices) {
        // 0, 1, 2, 3, 4, 5, 6, 7, 8
        // We have already marked all the vertices as visited.
        // So, the for-loop exhausts.
        if (!visited[vertex]) {
            
        }
    }
}
```

## Implementation

* [Graph Traversal.kt](../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/020graphTraversal.kt)

## Next

* 