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
* Now, the problem with the normal DFS for this problem is that we keep moving from one SSC to another SSC.
* But an SSC has a relevant important property.
* Once we leave an SSC, we can't go back.
* What if we block this direction?
* How can we block it?
* The path that leaves an SSC goes outward and connects with another SSC.
* So, we reverse this direction!
* When we reverse the directions of the original graph, we call it a transposed graph.
* Now, the SSC is self-contained, quarantined.
* But then how do we even explore other vertices?
* And isn't it true that this part solves the problem only for the first SCC?
* If we can't leave the first SCC, how do we even count all the SCCs?
* Fair point.
---
* So, to explore all the other vertices and determine their corresponding SCC, we have the adjacency list.
* But if we directly iterate through the adjacency list, we again get a problem.
* We have reversed the directions (edges).
* But one SSC is still connected (although in reverse direction) with another SCC.
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
* For example, if we start from $SSC_1$, we might get $SCC_2$ along the way.
* And in this case, it is accurately true to say that $SCC_2$ will finish first before we finish $SCC_1$. 
---
* Now, we observe the topological sort.
* The interesting part of the topological sort is that it gives a single vertex per SSC first.
* And the rest of the associated vertices sit at the end.
* It is like first row is reserved for a leader per SSC and the other team members of the same SSC sit in the end.
---
* The intuition is that we first use the topological sort on the given graph.
* It will give us a particular order of vertices.
* The sort by post-visit time in descending order puts the sink vertex last. 
---
* Now, the 

## Next

* 