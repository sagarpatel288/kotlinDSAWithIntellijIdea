# Disjoint Set Implementation

<!-- TOC -->
* [Disjoint Set Implementation](#disjoint-set-implementation)
* [Prerequisites / References / Resources](#prerequisites--references--resources)
* [Time Complexity](#time-complexity)
* [Space Complexity](#space-complexity)
* [Note](#note)
* [Implementation](#implementation)
<!-- TOC -->
 
# Prerequisites / References / Resources

* [Local: DSU Intro](disjointSets.md)  

* [GitHub: DSU Intro](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/30b2b5290ee2209988564fa5a1a073319f7b437d/docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsImplementation/disjointSets.md)  

* [Local: DSU Dissection](docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsNaiveImplementation/disjointSets02dissection.md)  

* [GitHub: DSU Dissection](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/e6201bad2968159c293b35f003f3a18228cd8248/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsNaiveImplementation/disjointSets02implementation.md)  

* Implement DisjointSet (Union-Find Set) using `Union By Rank` and `Path Compression` heuristics.
* The [rank] array shows the upper bound of the height for a particular `index` of the [rank] array.
* We use that information to perform [unionByRank] operation.
* We hang a shorter tree on the larger tree to keep the tree height shallow.
* If both the trees are of the same height, we hang any one tree on another tree and increase the rank of the root.

# Time Complexity

* According to Robert Tarjan's Analysis, the realistic running time of `m` operations in `DSU` is:
* $m * log^{*}(n)$ where `n` is the total number of nodes.

# Space Complexity

* We use two arrays of the given [size] to maintain `parent` and `upper bound of the height`.
* So, if [size] = `n`, then the space complexity is `O(n)`.

# Note

* Please note that in practical use, we use `size` instead of `height` to perform the `union` operation.
* The `size` represents the number of nodes.
* The reason is that we can get the `size` of any subtree in `O(1)`.
* So, please check the alternative implementation also:
* [DisjointSetBySize]

# Implementation

[DisjointSet UnionByRank Implementation.kt](../../../../../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/03DisjointSetsUnionFindDSU.kt)

[DisjointSet UnionBySize Implementation.kt](../../../../../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/03disjointSetsUnionFindUsingSize.kt)