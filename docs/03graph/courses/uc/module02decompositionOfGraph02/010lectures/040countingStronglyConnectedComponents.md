# Counting strongly connected components

## Prerequisites

* [Basic Introduction.md](010basicIntroduction.md)
* [Simple Graph Ops.md](012simpleGraphOps.md)
* [Exploring Graph Traversal.md](020exploringGraphTraversal.md)
* [Bfs Graph Traversal.md](023bfsGraphTraversal.md)
* [Dfs Graph Traversal.md](026dfsGraphTraversal.md)
* [Cycle Detection In Graph Using Dfs.md](028cycleDetectionInGraphUsingDfs.md)
* [Cycle Detection In Graph Using Bfs.md](030cycleDetectionInGraphUsingBfs.md)
* [Number Of Islands.md](032numberOfIslands.md)
* [Directed Acyclic Graph Intro.md](010directedAcyclicGraphIntro.md)
* [Topological Sort On Dag.md](020topologicalSortOnDag.md)
* [Strongly Connected Components.md](030stronglyConnectedComponents.md)

## References

* [Shradha Madam](https://youtu.be/lqY8TE0P1S8?si=ZmetY9PZoCfk_TCm)

## Concept, Thought Process

* We want to count strongly connected components of a directed graph.
* If we use a simple DFS exploration, we get a problem.
* We explore all the vertices of the graph.
* We keep going from one SCC to another SCC.
* And we can't identify when that happens.
* So, we can't count strongly connected components in this normal, simple, straightforward way.
* So, we use multiple theories to solve this problem.
* We will use a couple of theories that we have already learned:
  * [Dfs Graph Traversal.md](026dfsGraphTraversal.md)
  * [Number Of Islands.md](032numberOfIslands.md)
  * [Directed Acyclic Graph Intro.md](010directedAcyclicGraphIntro.md)
  * [Topological Sort On Dag.md](020topologicalSortOnDag.md)
  * [Strongly Connected Components.md](030stronglyConnectedComponents.md)
---

* ![Strongly Connected Components.webp](../../../../../../assets/images/03graph/courses/uc/module02decompositionOfGraph02/040countingSCCs/010stronglyConnectedComponents.webp)

---
* According to the definition of an SCC, if we can reach from `v` to `u` and back from `u` to `v`, then it is an SCC.
* So, the naive approach would be to find all the neighbors of `v` first.
* And then for each such neighbor, we try to find that if we can reach back to `v`.
* And if we can reach back to `v`, then that neighbor is part of the SCC of `v`.
* And we not only do this for one `v`, we do it for all the vertices of the entire graph.
* The graph can have multiple SCCs.
* It means that while trying to figure out one SCC, for example the SCC of `v`, we visit vertices of other SCCs as well.
* And it happens for each vertex.
* It works, but it takes $O(V^2 + VE)$.
* //ToDo: Explain why/how it is: $O(V^2 + VE)$
* We want to improve it.
---
* Now, the problem with the normal DFS for this problem is that we keep moving from one SCC to another SCC.
* We can't get an idea when we leave one SCC or when we enter to a new SCC.
* And we need to know that to restrict ourselves for one particular SCC at a time to improve the time complexity.
* So, it turns out that an SCC has a relevant important property.
* Once we leave an SCC, we can't go back.
* What if we block this direction?
* How can we block it?
* The path that leaves an SCC goes outward and connects with another SCC.
* So, we reverse this direction and block (trap) the SCC!
* Where and how do we reverse the direction?
* We need to reverse the direction exactly at the point where one SCC connects with the other SCC.
* But how do we get to know when that happens?
* We don't get to know it yet.
* So, instead of trying to figure it out to reverse only a few edges, we reverse all the edges!
* So, instead of reversing only the edges that connect one SCC to another SCC, we reverse all the edges!

* ![Strongly Connected Components Transposed Graph.webp](../../../../../../assets/images/03graph/courses/uc/module02decompositionOfGraph02/040countingSCCs/020stronglyConnectedComponentsTransposed.webp)

* When we reverse the directions of the original graph, we call it a transposed graph.
* Now, if we start from `0`, we get `1`, we get `2`, we get back to `0`.
* It is a cycle, and we conclude that we finished and covered one SCC.
* So, the SCC looks self-contained, quarantined.
* We take the other unvisited vertex from the adjacency list.
* We get `1` and `2` as already visited.
* So, we start with `3` and it goes to `0` which is already visited.
* So, `3` is the second SCC.
* We again take the remaining unvisited vertex from the adjacency list.
* We get `4` and it points to `3` which is already visited.
* So, `4` is the third SCC.
* So, we got a total of 3 SCCs, and it looks like it works.
* But what if we had started from `4` instead of `0`?
* Then we would have ended up in the same dilemma that we are trying to solve, and we thought that we have solved it!
* So, if we start counting the SCCs from `4`, we get `3`, `0`, `1`, `2`, get back to `0`, which is already visited, but we already stepped out of one SCC to other SCC.
* We already visited vertices of other SCCs.
* So, it works only if we start from the "correct" vertex.
* So now, the problem is, how do we determine the "correct" vertex?
---
* First of all, let us define the "correct" vertex here.
* We saw that when we started with the sink SCC, it worked.
* Otherwise, it did not work.
* So, we want to ensure that we always start with the sink vertex.
---
* Now, what is a sink vertex?
* It is the vertex that does not have any outward edge.
* A graph can have many sink vertices.
* How do we find the sink vertex?
* We find it based on its property.
* A sink vertex will have the shortest post-visit time than its sources/parents/ancestors.
* There is no concept of parents or ancestors or successors in the graph. 
* But we use these terms to indicate the relation between the two vertices or to distinguish or to convey which vertex we visited earlier.
* So, how do we arrange and sort the vertices by post-visit time?
* We use the topological sort.
---
* Ok. So, to identify from which vertex we should start our exploration, we use the topological sort.
* And to trap each SCC, we use the transposed graph.
* And once we have the topological sort order and the transposed graph, we finally apply the DFS on the transposed graph.
* We start with the unvisited vertex, and it will cover all the vertices that belong to that particular SCC.
* Because each SCC is trapped, we get out of the loop, and get the unvisited vertex from the topological sort order.
* We repeat the process.
* Every time we start a new exploration with the unvisited vertex, it indicates a new SCC.
* The count logic is a little bit similar to the count island problem we have seen earlier.
---
* ToDo://
* The problem with the adjacency list if we follow it.
* If we just follow the adjacency list, it could be in any order.
* We cannot say that
* The problem with the normal DFS order if we follow it.
* The sink concept.
* The problem that the topological order solves.
---
* So, to explore all the other vertices and determine their corresponding SCC, we have the adjacency list.
* But if we directly iterate through the adjacency list, we again get a problem.
* We have reversed the directions (edges).
* But one SCC is still connected (although in reverse direction) with another SCC.
* Or, we might miss the original sink.
* Because the second last SCC does not go to the original sink.
* The original sink goes to the second last SCC in the transposed graph.
* It means that we need to start with the original sink then.
* But if we start with the sink, we get the same problem in the transposed graph, too.
* We keep moving from one SCC to another SCC and we can't determine when that happens.
* Fair point.
---
* Let us take a small example.
* A --> B
* It is a DAG.
* If we have a vertex (or an SCC) from A to B and if we start from A, we might unnecessarily cover B.
* We can't determine when that happens.
* But if we start from B, it works.
* Because "B" is a sink vertex. 
* It is a quarantined (self-contained) vertex.
* There is no outward direction from it.
* And when "A" gets its turn, "B" is already visited!
* So, the question is:
* In which order can we start exploring the vertices such that:
  * As soon as we finish the exploration, it would imply that we have covered an SCC - same as the island problem.
  * And we should be able to cover all the SCCs.
* And if we start from the sink, it seems possible.
* How do we find the sink?
---
* Now, we have already learned about the pre-visit and post-visit timestamps.
* It is clear that a subroutine will always finish first.
* For example, if we start from $SCC_1$, we might get $SCC_2$ along the way.
* And in this case, it is accurately true to say that $SCC_2$ will finish first before we finish $SCC_1$. 
---
* Now, we observe the topological sort.
* The interesting part of the topological sort is that it gives a single vertex per SCC first.
* And the rest of the associated vertices sit at the end.
* It is like first row is reserved for a leader per SCC and the other team members of the same SCC sit in the end.
---
* The intuition is that we first use the topological sort on the given graph.
* It will give us a particular order of vertices.
* The sort by post-visit time in descending order puts the sink vertex last. 
---
* Now, the 

## Next

* 