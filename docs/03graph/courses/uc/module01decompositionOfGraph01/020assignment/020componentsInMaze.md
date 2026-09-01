# Ensure exit in maze as graph

## Prerequisites

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)
* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)
* [Cycle Detection In Graph Using Dfs.md](028cycleDetectionInGraphUsingDfs.md)
* [Cycle Detection In Graph Using Bfs.md](030cycleDetectionInGraphUsingBfs.md)
* [Number Of Islands.md](032numberOfIslands.md)

## References

* [Gray Utopia](https://youtu.be/DDPdnywfxuM?si=GBxUbR9f7OEVP9ye)

## Problem

**Adding Exits to a Maze**

**Problem Introduction**

* Cont. from the previous problem:
  * [Maze as graph](010mazeAsGraph.md)
* Now you decide to make sure that there are no dead zones in a maze, that is, that at least one exit is reachable from each cell. 
* For this, you find connected components of the corresponding undirected graph and ensure that each component contains an exit cell.
  
**Problem Description**
  
**Task** 

* Given an undirected graph with `𝑛` vertices and `𝑚` edges, compute the number of connected components in it.

**Input Format**

* A graph is given in the standard format.

**Constraints** 

$$
1 ≤ 𝑛 ≤ 10^3, 0 ≤ 𝑚 ≤ 10^3
$$
  
**Output Format** 

Output the number of connected components.

**Time limits**

```markdown

| language  | C | C++ | Java | Python | C#  | Haskell | JavaScript | Ruby | Scale |
|-----------|---|-----|------|--------|-----|---------|------------|------|-------|
| time(sec) | 1 | 1   | 1.5  | 5      | 1.5 | 2       | 5          | 5    | 3     |

```

**Memory Limit**

* 512 MB

**Sample 1**

**Input**

```markdown
4 2
1 2
3 2
```

**Output**

```markdown
2
```

## Concept, Thought Process

* The problem and the required output are not aligned.
* If we focus on the required output, this problem is similar to:
* [Number of islands](../010lectures/032numberOfIslands.md)

## Implementation

* [Components In Maze.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module01decompositionOfGraph01/070componentsInMaze.kt)