# Find number of islands

## Prerequisite

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)
* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)
* [Cycle Detection In Graph Using Dfs.md](028cycleDetectionInGraphUsingDfs.md)
* [Cycle Detection In Graph Using Bfs.md](030cycleDetectionInGraphUsingBfs.md)

## References

* [Shradha Madam](https://youtu.be/AME6baBpswY?si=UFXS-BY6zbrB769z)
* [LeetCode](https://leetcode.com/problems/number-of-islands)

## Problem

```markdown

| 1 | 1 | 0 | 0 | 0 |
|---|---|---|---|---|
| 1 | 1 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 | 0 |
| 0 | 0 | 0 | 1 | 1 |

```

* We have given a 2D grid.
* And we have given the definition of an island.
* `1` indicates land.
* `0` indicates water.
* An island is a group of adjacent `1`s.
* We need to find the number of islands.

---

## Perspective, Understanding, Intuition


* We will model this problem as if we are going to find the total connected components of a graph.
* Consider the given grid as an adjacency matrix representation of an undirected graph.
* We have already seen the concept of connected components in a graph.
* And we know that a disconnected graph can have multiple connected (but independent) components.
---

```markdown

| Grid concept                                    | Graph concept                 |
| ----------------------------------------------- | ----------------------------- |
| A land cell `(i,j)`                             | Vertex                        |
| Two horizontally/vertically adjacent land cells | Edge                          |
| Connected group of land cells                   | Connected component           |
| Island                                          | Connected component           |
| `visited[i][j]`                                 | `visited[vertex]`             |
| Four directions                                 | Possible neighbors            |
| DFS/BFS                                         | Connected-component traversal |


```

---
* Let us understand how.
* Let us take a small portion from the given problem.

```markdown

| 1 | 1 | 0 |
|---|---|---|
| 1 | 1 | 0 |
| 0 | 0 | 1 |

---

(0,0) ---- (0,1)
|           |
|           |
(1,0) ---- (1,1)

```

* For example, we have given the above 2D grid.
* For simplicity, we can add (r, c) to identify the row and column values.

```markdown

| (r, c) | 0        | 1        | 2        |
|--------|----------|----------|----------|
| 0      | 1 (0, 0) | 1 (0, 1) | 0 (0, 2) |
| 1      | 1 (1, 0) | 1 (1, 1) | 0 (1, 2) |
| 2      | 0 (2, 0) | 0 (2, 1) | 1 (2, 2) |

```

* `(0, 0)` is a land.
* Now, let us check its surrounded neighbors.
* We get 4 surrounded neighbors.
---
* The top is: (i - 1, j)
* The right: (i, j + 1)
* The bottom: (i + 1, j)
* The left: (i, j - 1)
---
* For `(0, 0)`:
* The top neighbor is invalid: (-1, 0)
* Or in terms of a graph language, we can say that there is no more vertex on top of the subject vertex.
* The right side neighbor is: (0, 1) which is `1`.
* Or in terms of a graph language, we can say that there is a connected vertex on the right side of the subject vertex.
* The bottom neighbor is: (1, 0) which is also `1`.
* Or in graph language, we can say that there is a connected vertex on the bottom of the subject vertex.
* The left neighbor is invalid: (0, -1)
* Or in graph language, there is no more vertex on the left side of the subject vertex.
* So, all the valid sides (neighbors) have `1`.
* We have got two land neighbors: Right side (0, 1) and bottom side (1, 0).
* It means that we can try to cover more of the connected land part.
* There can be more land part in island.
* Or in terms of a graph language, there can be more connected vertices.
* So, we repeat this process for each neighbor.
---
* Let us explore the right side neighbor: `(0, 1)` which is `1`.
* We repeat the same process for this vertex/land.
* We check all the 4 neighbors of this vertex/land.
* The top neighbor is invalid: (-1, 1)
* Or in graph language, we say that there is no connected vertex on top of the subject vertex.
* The right side neighbor is: (0, 2) which is `0` and `0` indicates water.
* Or in graph language, we say that there is no connected vertex on the right side of the subject vertex.
* The bottom neighbor is: (1, 1) which is `1`.
* Or in graph language, we say that there is a connected vertex on the bottom side of the subject vertex.
* The left neighbor is: (0, 0) which we have already visited. 
* Or in graph language, we have already marked the neighbor as visited.
---
* Now, we are going to explore `(1, 1)` which is `1`.
* The top neighbor is (0, 1) which we have already marked as visited.
* The right neighbor is (1, 2) which is `0` and `0` indicates water.
* In graph language, it indicates that there is no more connected vertex on the right side of the subject vertex.
* The bottom neighbor is (2, 1) which is `0` (water).
* In graph language, it indicates that there is no more connected vertex on the bottom of the subject vertex.
* Then we have the left neighbor (1, 0) which is `1`.
* So now, we have one neighbor (connected land/vertex) (1, 0) to explore.
---
* Now, we are going to explore `(1, 0)` which is `1`.
* The top neighbor is (0, 0) which we have already visited.
* The right side neighbor is (1, 1) which we have already visited.
* The bottom side neighbor is (2, 0) which is `0` (water).
* It means that there is no more connected vertex at the bottom side of the subject vertex.
* The left side neighbor is invalid: (1, -1)
* It means that the control goes back for the remaining neighbors of the previous vertex, `(1, 1)`.
* The previous vertex `(1, 1)` does not have any other neighbor other than this already visited neighbor (1, 0).
* So, the control goes back for the remaining neighbors of `(0, 1)`.
* The only valid neighbor of (0, 1) was (1, 1) that we have already marked as visited.
* So, the control goes back for the remaining neighbors of `(0, 0)`.
* The remaining neighbor is (1, 0) that we have already marked as visited.
* And with this, we have covered all the connected neighbors, land, vertices.
* We have covered one connected component.
* That's one island.
---
* We repeat this process for all the cells.
---
* If we notice the pattern, we keep exploring the connected neighbors until we exhaust for each component.
* This is the same mechanism we have used during DFS and BFS traversal of an undirected graph.
---
* Now, the question is, how do we model this into code?
* Let us start with the four sides.
* We have given a grid, and we explore the 4 sides.
* We repeat the proecess for each neighbor.
* And the 4 sides are the neighbors. 
* We repeat the process for the 4 sides:

```kotlin

dfs(i - 1, j) // top
dfs(i, j + 1) // right
dfs(i + 1, j) // bottom
dfs(i, j - 1) // left

```

* When do we stop?
* When the neighbor is water (`0`), or when we have already visited the neighbor, or when the neighbor is invalid.
* What does it mean by an invalid neighbor?
* An index which is out of bounds to the given grid.
---
* Notice that this is a grid.
* So, we need two parameters for each cell: i, j.
* And we need a 2D visited boolean array for each cell to determine whether we have already visited it or not.


```kotlin

if (i !in 0..<grid.size || j !in 0..<(grid[0].size) || grid[i][j] == '0' || visited[i][j]) {
    return
}
``` 

* It means that the function expects: i, j, n, m, grid, and visited boolean array. 
* So, the function becomes:

```kotlin

fun dfs(i: Int, j: Int, grid: Array<CharArray>, visited: List<BooleanArray>) {
    if (i !in 0..<grid.size || j !in 0..<(grid[0].size) || grid[i][j] == '0' || visited[i][j]) {
        return
    }
    visited[i][j] = true
    dfs(i - 1, j, grid, visited) // top
    dfs(i, j + 1, grid, visited) // right
    dfs(i + 1, j, grid, visited) // bottom
    dfs(i, j - 1, grid, visited) // left
}

```

* We repeat this process for each unvisited cell, and we only explore a land `1`.
* In other words, if it is a land, and if we have not marked it as visited, we explore it via the `dfs` function.
* The `dfs` function covers all the vertices of the connected component and marks them visited.
* In other words, the `dfs` function covers the entire island.
* Every time the call stack becomes empty, the control goes back to the parent function.
* Every time the outer loop calls the `dfs` function, it means that we are going to explore an unvisited land.
* In other words, we are going to explore another connected component, which means another island.

```kotlin

fun countIslands(val grid: Array<CharArray>) {
    val visited = List(grid.size) { BooleanArray(grid[0].size) { false } }
    var islands = 0
    for (i in 0..<grid.size) {
        for (j in 0..<(grid[0].size)) {
            if (grid[i][j] == '1' && !visited[i][j]) {
                dfs(i, j, grid, visited)
                islands++
            }
        }
    }
}

```

## Time Complexity

* `O(rows * columns)` from the two nested for-loops

## Space Complexity

* `O(rows * columns)` by the visited boolean array

## Implementation

* [Count islands using DFS of an undirected graph](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/0db0bee5172de800ada2ca73d318ba4966155280/src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/040countIslands.kt)

## Next

* 