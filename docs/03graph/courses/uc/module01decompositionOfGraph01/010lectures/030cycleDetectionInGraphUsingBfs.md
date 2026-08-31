# Cycle detection in an undirected graph using BFS

## Prerequisite

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)
* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)
* [Cycle Detection In Graph Using Dfs.md](028cycleDetectionInGraphUsingDfs.md)

## References

* [Shradha Madam](https://youtu.be/MIjOkApZ39g?si=3p6H9_pP4lN5IKTi)

## Concept

* We are going to use the same `parent-neighbor-visited-unvisited` concept that we have used in:
  * [Cycle Detection In Graph Using Dfs.md](028cycleDetectionInGraphUsingDfs.md)
* We are going to use almost the same `BFS Traversal` approach that we have used in:
  * [Bfs Graph Traversal.md](023bfsGraphTraversal.md)

---

* So, the rule is:

> If the neighbor vertex is visited, but it is not the parent, we have got a cycle! 

* So, the code becomes something like below:

```kotlin

fun hasCycle(): Boolean {
    val visited = BooleanArray(size) { false }
    var hasCycle = false
    for (vertex in adjacencyList.indices) {
        if (!visited[vertex]) {
            hasCycle = hasCycleUsingBfs(vertex, -1, visited)
        }
    }
    return hasCycle
}

fun hasCycleUsingBfs(vertex: Int, parent: Int, visited: BooleanArray): Boolean {
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.addLast(Pair(vertex, parent))
    visited[vertex] = true
    while (queue.isNotEmpty()) {
        val pop = queue.removeFirst()
        val source = pop.first
        println(source) // Optional
        val parent = pop.second
        val neighbors = adjacencyList[source]
        neighbors.forEach {
            if (!visited[it]) {
                queue.addLast(it, source, visited)
            } else if (it != parent) {
                println(it) // Optional
                return true
            }
        }
    }
    return false
}

```

## Implementation

* [Cycle detection in a graph](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/9ebecde9affadc011bd586c35c0d7c1801181472/src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/030cycleInUndirectedGraph.kt)

## Next

* 