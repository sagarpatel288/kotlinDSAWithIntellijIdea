# Topological Sort In A Directed Graph

## Continued from:

* [Detect Cycle In A Directed Graph.md](010detectCycleInDirectedGraph.md)

## Problem Introduction

* Now, when you are sure that there are no cyclic dependencies in the given CS curriculum, you would like to find an order of all courses that is consistent with all dependencies. 
* For this, you find a topological ordering of the corresponding directed graph.

## Problem Description

### Task 

* Compute a topological ordering of a given directed acyclic graph (DAG) with 𝑛 vertices and 𝑚 edges.

### Input Format 

* A graph is given in the standard format.

### Constraints 

$$
1 ≤ 𝑛 ≤ 10^5, 0 ≤ 𝑚 ≤ 10^5. 
$$

* The given graph is guaranteed to be acyclic.

### Output Format 

* Output any topological ordering of its vertices. (Many DAGs have more than just one topological ordering. You may output any of them.)

### Time Limits

```markdown

| language  | C | C++ | Java | Python | C# | Haskell | JavaScript | Ruby | Scala |
|-----------|---|-----|------|--------|----|---------|------------|------|-------|
| time(sec) | 2 | 2   | 3    | 10     | 3  | 4       | 10         | 10   | 6     |

```

### Memory Limit

* 512 MB

## Samples

### Sample 1

**Input**

```markdown
4 3
1 2
4 1
3 1
```

**Output**

```markdown
4 3 1 2
```

### Sample 2

**Input**

```markdown
4 1
3 1
```

**Output**

```markdown
2 3 1 4
```

### Sample 3

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
5 4 3 2 1
```


