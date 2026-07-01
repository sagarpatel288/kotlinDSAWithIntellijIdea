# ToDos

<!-- TOC -->
* [ToDos](#todos)
  * [Topics](#topics)
  * [Ideas, Key-lemmas, Formulas](#ideas-key-lemmas-formulas)
  * [Edge Cases](#edge-cases)
  * [Common Mistakes](#common-mistakes)
  * [Format](#format)
<!-- TOC -->

## Topics

* Ensure that each problem has the associated documentation's link.
  * Ensure that we have a proper and dedicated documentation (a Markdown file) for each problem that explains many things and answers to many questions and doubts. 
* Understand and explain: `compareTo`.
* Isn't it fascinating to realize and experience how the same simple data structure serves differently with different approaches?
  * For example, a simple array when it comes to different sorting methods like selection sort, merge sort, etc.
  * And then when we use it as an internal data structure for queues, stacks, priority queue (heap), heap sort, DSU (Disjoint Set), hash tables, etc.
  * And when we alter a stack, we get a binary search tree, and self-balancing binary search trees like AVL, SplayTree, etc.
  * Small tweaks change the behavior.
  * Small changes and big impact.
  * Document these small tweaks.
  * Start from the original and progressively, step-by-step move forward.
* Connecting a row problem like [Merge Table.kt](../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment02/03mergeTable.kt) with [Disjoint Sets Dissection.md](dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsImplementation/disjointSets02dissection.md) is fascinating.
  * This kind of imagination, connection, simulation, relation, conversion, translation, transformation, mapping and modeling, reduction, and immersion of pattern and behavior matching is fascinating.
  * This is a fascinating example of pattern recognition, and problem reduction (mapping and modeling) skill.

## Ideas, Key-lemmas, Formulas

* Euclidean theory of GCD and LCF.
* Fibonacci formulas.
* Range sum prefixed.
* Euclidean formula to find the distance between two points.
* Formulas of: Edit distance to match two strings.
* Formulas of: Longest common subsequence.
* Combinatorial Game Theory: Piles and rocks.
* 0/1 knapsack formula.
* Finding the minimum value in `O(1)` in a stack using the clever encoding-decoding technique.
  * Reference: Min stack.
* The circular array instead of a plain array as an underlying data structure for a queue to prevent `O(n)` shifting cost.
* The fast and slow pointers, both start from the head, slow moves one step at a time, fast moves two steps at a time, if there is a cycle, they will meet, otherwise at some point, fast or `fast.next` will be `null`.
  * Reference: In a linked list: To detect a cycle, to find the start of the cycle, to break the cycle, and to find the middle point.
* Formulas of: Binary heap tree to get parent and children.
* Heap Sort Trick: We start from `n/2` and go down to `0`. Can you explain why?
* 

## Edge Cases

* Empty data
* Invalid data
* Wrong data

## Common Mistakes

* Integer overflow

## Format

* Prerequisites/References.
* Standard learning format.
* The need (necessity) that gave the birth to the idea.
* People that gave the idea.
* Time taken for the idea.
* Complexity analysis of 3 cases: Worst, average, best.
  * For the entire data structure + For each method.
  * And if applicable, the amortized cost analysis.
* Comparisons of complexity analysis.
* Where and when to use what, why, and how.
* Ensure proper public, private, var, val properties.
  * We might want `private set` for a `var`.
* Concurrency (Thread-safety) explanation for each topic.
* Previous, and Next.
* Questions and answers.
* Relevant LeetCode problems.
* Standard proofread and approval.