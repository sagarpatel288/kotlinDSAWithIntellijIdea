# Computing the minimum number of flight segments

## Prerequisites

* [Shortest Path.md](../010lectures/020shortestPath.md)

## Problem Introduction

* You would like to compute the minimum number of flight segments to get from one city to another one. 
* For this, you construct the following undirected graph: 
* Vertices represent cities, there is an edge between two vertices whenever there is a flight between the corresponding two cities. 
* Then, it suffices to find a shortest path from one of the given cities to the other one.

## Problem Description

### Task 

* Given an undirected graph with 𝑛 vertices and 𝑚 edges and two vertices 𝑢 and 𝑣, compute the length of the shortest path between 𝑢 and 𝑣 (that is, the minimum number of edges in a path from 𝑢 to 𝑣).

### Input Format 

* A graph is given in the standard format. 
* The next line contains two vertices 𝑢 and 𝑣.

### Constraints 

$$
2 ≤ 𝑛 ≤ 105, 0 ≤ 𝑚 ≤ 105, 𝑢 ̸= 𝑣, 1 ≤ 𝑢, 𝑣 ≤ 𝑛.
$$

### Output Format 

* Output the minimum number of edges in a path from 𝑢 to 𝑣, or −1 if there is no path.

### Time Limits

```markdown

| language  | C | C++ | Java | Python | C# | Haskell | JavaScript | Ruby | Scale |
|-----------|---|-----|------|--------|----|---------|------------|------|-------|
| time(sec) | 2 | 2   | 3    | 10     | 3  | 4       | 10         | 10   | 6     |

```

### Memory Limit

* 512 MB

## Samples

### Sample 1

**Input**

```markdown
4 4
1 2
4 1
2 3
3 1
2 4
```

**Output**

```markdown
2
```

**Explanation**

* ![Shortest Path Assignment Prob 01.webp](../../../../../../assets/images/03graph/courses/uc/module03pathsInGraph01/100assignments/010shortestPathAssignmentProb01.webp)

* There is a unique shortest path between vertices 2 and 4 in this graph: 2 − 1 − 4.

### Sample 2

**Input**

```markdown
5 4
5 2
1 3
3 4
1 4
3 5
```

**Output**

```markdown
-1
```

**Explanation**

* ![Shortest Path Assignment Prob 02.webp](../../../../../../assets/images/03graph/courses/uc/module03pathsInGraph01/100assignments/020shortestPathAssignmentProb02.webp)

* There is no path between vertices 3 and 5 in this graph.

## Implementation

* [Shortest Path Min Flight Segments.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module03pathsInGraph01/010shortestPathMinFlightSegments.kt)
