# Building roads to connect cities

## Problem Introduction

* In this problem, the goal is to build roads between some pairs of the given cities such that there is a path between any two cities and the total length of the roads is minimized.

## Problem Description

### Task 

* Given 𝑛 points on a plane, connect them with segments of minimum total length such that there is a path between any two points. 
* Recall that the length of a segment with endpoints (𝑥1, 𝑦1) and (𝑥2, 𝑦2) is equal to 
$\sqrt{(𝑥1 − 𝑥2)^2 + (𝑦1 − 𝑦2)^2}$.

### Input Format 

* The first line contains the number 𝑛 of points. 
* Each of the following 𝑛 lines defines a point (𝑥𝑖 , 𝑦𝑖).

### Constraints 

* $1 ≤ 𝑛 ≤ 200; −10^3 ≤ 𝑥𝑖, 𝑦𝑖 ≤ 10^3$ are integers. 
* All points are pairwise different, no three points lie on the same line.

### Output Format 

* Output the minimum total length of segments. 
* The absolute value of the difference between the answer of your program and the optimal value should be at most $10^{−6}$. 
* To ensure this, output your answer with at least seven digits after the decimal point (otherwise your answer, while being computed correctly, can turn out to be wrong because of rounding issues).

### Time Limit

| language   	 | C 	 | C++ 	 | Java 	 | Python 	 | C# 	 | Haskell 	 | JavaScript 	 | Ruby 	 | Scala 	 |
|-------------|----|------|-------|---------|-----|----------|-------------|-------|--------|
| time (sec) 	 | 2 	 | 2   	 | 3    	 | 10     	 | 3  	 | 4       	 | 10         	 | 10   	 | 6     	 |

### Memory Limit

* 512 MB

### Sample 1

**Input**

```markdown
4
0 0
0 1
1 0
1 1
```

**Output**

```markdown
3.000000000
```

![010buildingRoads.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/100assignments/010buildingRoads.webp)

* An optimal way to connect these four points is shown below. 
* Note that there exists other ways of connecting these points by segments of total weight 3.

### Sample 2

**Input**

```markdown
5
0 0
0 2
1 1
3 0
3 2
```

**Output**

```markdown
7.064495102
```

![020buildingRoads.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/100assignments/020buildingRoads.webp)

* An optimal way to connect these five points is shown above.

## Thought Process

* Given: x and y co-ordinates of each point
* Output: The minimum cost of connecting all the points!
* Normally, the theoretical input we get is:
* (from, to, weight).
* Here, we might think that we will have to find the distance of all the points from each other.
* Then, we would follow either Kruskal's Algorithm or Prim's Algorithm.
* So, we would sort them by their distance values, add them to the priority queue, and process each.
* We might keep track of selected (or connected, included) points so that we don't re-connect existing point/s.
* The idea is partially correct.
* The correction is, we can start with any point.
* We don't need the distance of all the points from each other up front.
* We start with one vertex, and it is enough to have the distance of all the other points from that one vertex.
* We can build the solution based on what we already know.
* Initially, we don't know the cost of connecting any point.
* So, `minDistance` has default value `MAX`.
* Then, we select a starting point (It can be anything).
* Now, we know that the cost to connect it is 0, because it is the root of our tree.
* Let us say that starting point is A.
* So, we update `minDistance[A] = 0`.
* It conveys that: Bringing "A" into the tree costs us "0".
* In other words: Connecting "A" to the tree costs us "0".
* This perspective is important.
* Now, we find the distance of each point from A and update `minDistance` with the `min` value.
* Once we are done with A, we start the same process for all the other unselected points.
---
* But when and how do we finalize the point as selected?
* To answer that, let us first recall: 
* What does the Prim's Algorithm constantly ask? How does the Prim's Algorithm finalizes the selection?
* The Prim's Algorithm constantly asks:
* What is the cheapest known weight that can connect the existing tree to (bring in) an unselected vertex?
* Again, we start with one point and find the distance of all the other points from that one point.
* At this point, we may not know the distance of all the other points from each other.
* For example, if our starting point is A, we may find AB, AC, AD, and AE.
* We may not know the distance for BC, BD, BE, CD, CE, DE, etc., at this moment.
* But we don't need to know them at this moment up front either.
* For example, what we do at the core of the Prim's Algorithm?
* We start with a vertex.
* We add all its outgoing edges to the priority queue.
* And at this point, we did not care about all the remaining edges.
* Still, when we start poll, we mark it as the final selection.
* Because we know that the poll operation on the priority queue will give us the minimum.
* And that's the minimum cost to bring in an unselected vertex.
* However, instead of the priority queue, we have a simple array: `minDistance`.
* To find and get the `min` out of it, we use the standard linear search approach:
---
```kotlin
var min = Int.MAX_VALUE
for (i in 0..<size) {
    if (dist[i] < min) {
        min = i // Min is the index at which we get the minimum value
    }
}
```
---
```kotlin
var min = -1
for (i in 0..<size) {
    if (min != -1 && dist[i] < min) {
        min = i // Min is the index at which we get the minimum value
    }
}
```
---
* At the end of this linear search, we will finalize the value as selected.
* For example:
---
```kotlin
var min = Int.MAX_VALUE
for (i in 0..<size) {
    if (dist[i] < min) {
        min = i // Min is the index at which we get the minimum value
    }
}
selected[min] = true
cost += minDistance[min]
```
---
* So, we will be doing something similar.
---
* We start with A.
* So, "A" is already selected.
* And the distance from "A" to "A" is "0".
* A is our tree - A tree that has only one vertex.
* We want to bring in another unselected vertex from A.
* In other words, we want to connect an unselected vertex with A using the minimum cost.
* At this point, it is safe to say that we want to connect an unselected vertex with A.
* Because at this point, the tree has only one vertex: A.
* And to find the cheapest cost of connecting an unselected vertex with A means finding the minimum distance of all the points from A.
* And our `minDistance` has that data: AB, AC, AD, and AE.
* It means we have enough data to bring in an unselected vertex using the minimum cost.
* And how do we select the minimum out of the available data?
* Using the linear search.
* Let us assume that the minimum cost to bring in an unselected vertex is from AD.
* It means that the edge AD is selected.
* At this stage, we add all the outgoing edges of D to the priority queue in the theory.
* Here, we add all the distances of all the other points from D to the `minDistance`.
* But we exclude A, because A is already selected.
* Because if we don't exclude already selected vertices, we keep jumping between AD and DA.
* So, we use the condition: `selected[i]`.
* For example:
---

```kotlin
var min = Int.MAX_VALUE
for (i in 0..<size) {
    if (!selected[i] && dist[i] < min) {
        min = i
    }
}
selected[min] = true
cost += minDistance[min]
```

---
* This time, we get AD as the minimum distance.
* So, we mark D as the selected vertex.
* We add the cost.
* And now, we find the distance of DB, DC, and DE.
* We add them to the `minDistance`.
* We find the minimum.
* We finalize it.
* We find the distance of all the other points from it.
* We add them to the `minDistance`.
---
* And we repeat this process until we select all the vertices.
---
* But how do we find the distance?
* Using the Euclidean Distance formula.
---

## Time Complexity

* We repeat this process until we select all the vertices, V.
* What do we repeat in each turn?
* The linear search scans through the entire `minDistance`.
* The size of the `minDistance` is equal to the total vertices.
* Because we store the `minDistance` for each vertex.
* As it represents the cost of connecting each vertex.
* So, each turn costs O(V).
* V turns cost $O(V^2)$.
* And then we have one more loop.
* To find the distance of all the other points from the recently finalized (selected) vertex.
* So again, it calculates the distance of all the other points from the selected point.
* It excludes the selected (finalized) points.
* But let us consider the upper bound.
* Calculating the distance between two points is a constant operation.
* But we do it for at most V vertices (points) for each turn.
* So again, each turn takes O(V).
* V turns cost $O(V^2)$.
* So, the total time complexity is: $O(V^2)$.

## Space Complexity

* We use `minDistance` to store the cost of connecting each vertex (point).
* It is of size: O(V).
* We use `selected` to skip the selected vertices (points).
* It is of size: O(V).

## Implementation

* [Connecting co-ordinates.kt](../../../../../../src/courses/uc/course03algorithmsOngraph/courses/uc/module05minimumSpanningTrees/part02assignments/010buildingRoads.kt)

## Next
