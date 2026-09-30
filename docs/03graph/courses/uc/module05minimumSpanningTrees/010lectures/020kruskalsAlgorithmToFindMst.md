# Kruskal's Algorithm

![035kruskalDsuOverview.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/035kruskalDsuOverview.webp)

![Kruskal Mst.webp](../../../../../../assets/images/03graph/courses/uc/module05minimumSpanningTrees/050kruskalMst02.webp)

* We always pick the edge with the least (minimum) weight as long as it does not create a cycle.
* In other words, we select the edge with the least available weight out of the entire graph even if it is not connected to the selected graph as long as it does not create any cycle.
* It is like we start with one edge, and we keep adding more edges by selecting the edge with the least available weight without creating a cycle.
* So, it selects the next lightest edge that does not create a cycle.
* Initially, it might look disconnected (isolated) edges, but by the end of the algorithm, we get a proper tree with the minimum cost.

---
* Selects the edge that has the least weight and doesn’t create a cycle.
* Initially, imagine that we have a forest of trees where each tree is an individual set.
* Then, we sort the edges in non-decreasing order by their weights.
* We process each edge one by one.
* Each edge gives us two vertices.
* We find the parent (leader) of each vertex.
* If their parents (leaders) are different, they belong to a different set.
* This is to avoid the cycle.
* If they belong to a different set, we merge (union) them.
* After the union, they share the same parent (leader).
* They become part of the same set.
* We repeat this process for `V - 1` times.
---

* To identify whether two objects are connected, we use DSU.
  * [DisjointSetsUnion.md](../../../../../02dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsImplementation/disjointSets.md)

![080unionByRank01.png](../../../../../../assets/images/02dataStructures/uc/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/lessons01explanation/080unionByRank01.png)

* We use an array and the direct-addressing method.
* Initially, each object is an individual, independent set.
* In other words, each object is a leader/parent of their own.
* The `find` operation reveals their leader/parent.
* Before we `union` two objects, we check their parents using `find`.
* If their parents/leaders are different, it means that they are disjoint, disconnected, different sets. So, we can join, union them.
* If they share the same parent/leader, it means that they are part of the same set.
* It means that they are already connected.
* So, we discard the union operation on them.
---
* In the Kruskal's Algorithm, we want to check whether taking an edge creates a cycle or not.
* An edge consists of two vertices.
* So, we check the leaders/parents of these vertices.
* If they share the same parents/leaders, then they are already connected.
* It means that taking (including) such an edge will create a cycle.
* So, we discard such an edge.
* And if their parents are different, we join, union them.
* Once we union them, they share the same parent.
---
* Initially, each vertex is its own parent.
* We use an array and a direct addressing method.
* An index represents the vertex and the value represents the parent.
---
```kotlin

val dsu = IntArray(vertices) { it }
```
---
* To connect two vertices, we need an edge.
* We want to select the edge with the least weight.
* So, we sort the edges in a non-decreasing order by weight.
* So that the edge with the least weight is on the top (first), and the edge with the largest weight is at the bottom (last).
---
```kotlin

val sortedEdges = edges.sortBy { it.weight }

```
---
* We need `V - 1` edges.
* So, we repeat the process for `V - 1` times, and we pick up the edge from the `sortedEdges`.
---
```kotlin

repeat(vertices - 1) {
    val edge = sortedEdges[it]
}
```
---
```kotlin

for (i in 0..<vertices) {
    val edge = sortedEdges[i]
    val from = edge.from
    val to = edge.to
    val fromParent = find[from]
    val toParent = find[to]
    if (fromParent != toParent) {
        union(from, to)
    }
}
```
---
* But we also need to provide the final cost.
* So, we take `minCost`, initialize it to `0`, and update it for every edge we use.
---
```kotlin
minCost = 0
for (i in 0..<vertices) {
    val edge = sortedEdges[i]
    val from = edge.from
    val to = edge.to
    val fromParent = find[from]
    val toParent = find[to]
    if (fromParent != toParent) {
        union(from, to)
        minCost += edge.weight
    }
}
```
---
* The find and union code of the DSU will be as it is.
* [Disjoint Sets Union Find Using Rank.kt](../../../../../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/03disjointSetsUnionFindUsingRank.kt)
* [Disjoint Sets Union Find Using Size.kt](../../../../../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/03disjointSetsUnionFindUsingSize.kt)
---
* Find:

```kotlin

private fun find(a: Int): Int {
    if (parent[a] = a) return a
    parent[a] = find(parent[a])
    return parent[a]
}

private fun union(a: Int, b: Int): Boolean {
    val aParent = find[a]
    val bParent = find[b]
    if (aParent == bParent) return false
    val aRank = rank[aParent]
    val bRank = rank[bParent]
    if (aRank > bRank) {
        parent[bParent] = aParent
    } else if (bRank > aRank) {
        parent[aParent] = bParent
    } else {
        parent[bParent] = aParent
        rank[a]++
    }
    return true
}

```


---
* Why do we say that this is a greedy algorithm?
* Because at each iteration, we are looking for the cheapest option available locally.
---
* Does it work with negative weights?
* Yes. The goal is to minimize the overall cost of the spanning tree and we always select the minimum weight as long as it does not create a cycle.
* So, negative weights do not create any problems.
---
* Does this work when the given graph has cycles?
* Yes, because we deliberately avoid cycles by using `union`.
* If an edge consisting of two vertices share the same parent/leader, it is an indication that including such an edge will create a cycle.
* So, we avoid such edges.
* And hence, the resultant minimum spanning tree does not contain any cycles.

## Next