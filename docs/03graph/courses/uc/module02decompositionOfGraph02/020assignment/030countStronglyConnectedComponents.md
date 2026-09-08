# Count strongly connected components in a DAG

# Checking Whether Any Intersection in a City is Reachable from Any Other

## Problem Introduction

* The police department of a city has made all streets one-way. 
* You would like to check whether it is still possible to drive legally from any intersection to any other intersection. 
* For this, you construct a directed graph: vertices are intersections, there is an edge (𝑢, 𝑣) whenever there is a (one-way) street from 𝑢 to 𝑣 in the city. 
* Then, it suffices to check whether all the vertices in the graph lie in the same strongly connected component.

## Problem Description

### Task 

* Compute the number of strongly connected components of a given directed graph with 𝑛 vertices and 𝑚 edges.

### Input Format 

* A graph is given in the standard format.

### Constraints

$$
1 ≤ 𝑛 ≤ 10^4, 0 ≤ 𝑚 ≤ 10^4.
$$

### Output Format 

* Output the number of strongly connected components.

### Time Limits

```markdown

| language  | C | C++ | Java | Python | C#  | Haskell | JavaScript | Ruby | Scala |
|-----------|---|-----|------|--------|-----|---------|------------|------|-------|
| time(sec) | 1 | 1   | 1.5  | 5      | 1.5 | 2       | 5          | 5    | 3     |

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
```

**Output**

```markdown
2
```

**Explanation**

* This graph has two strongly connected components: {1, 3, 2}, {4}.

### Sample 2

**Input**

```markdown
5 7
2 1
3 2
3 1
4 3
4 1
5 2
5 3
```

**Output**

```markdown
5
```

**Explanation**

* This graph has five strongly connected components: {1}, {2}, {3}, {4}, {5}.


## Implementation

* [Count Strongly Connected Components.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module02decompositionOfGraph02/040countStronglyConnectedComponents.kt)

## Next

