# Merge Tables

<!-- TOC -->
* [Merge Tables](#merge-tables)
* [References / Resources / Prerequisites](#references--resources--prerequisites)
* [Problem Introduction](#problem-introduction)
* [Problem Description](#problem-description)
  * [Task](#task)
  * [Input Format](#input-format)
  * [Constraints](#constraints)
  * [Output Format](#output-format)
  * [Time Limits](#time-limits)
  * [Memory Limit](#memory-limit)
  * [Sample 1.](#sample-1)
    * [Input](#input)
    * [Output](#output)
  * [Sample 2](#sample-2)
    * [Input](#input-1)
    * [Output](#output-1)
    * [Explanation](#explanation)
* [Thought Process](#thought-process)
  * [Given](#given)
  * [Find](#find)
  * [Hints](#hints)
    * [How do we find whether a table contains real data or a symlink?](#how-do-we-find-whether-a-table-contains-real-data-or-a-symlink)
    * [How do we arrange the given data and find the required data?](#how-do-we-arrange-the-given-data-and-find-the-required-data)
    * [Other points](#other-points)
  * [Implementation](#implementation)
  * [Time Complexity](#time-complexity)
  * [Space Complexity](#space-complexity)
  * [Grader Output](#grader-output)
<!-- TOC -->

# References / Resources / Prerequisites

* [Local: DisjointSet-DSU Dissection](disjointSets02dissection.md)

* [GitHub: DisjointSet-DSU Dissection](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/30b2b5290ee2209988564fa5a1a073319f7b437d/docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsImplementation/disjointSets02dissection.md)

* [Local: DSU Implementation](disjointSets03Implementation.md)

* [GitHub: DSU Implementation](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/aa9df3452444381432bf8eb3c730f3151acd24f5/docs/dataStructures/courses/uc/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsImplementation/disjointSets03Implementation.md)

# Problem Introduction

* In this problem, your goal is to simulate a sequence of merge operations with tables in a database.

# Problem Description

## Task

* There are `𝑛` tables stored in some database. 
* The tables are numbered from `1 to 𝑛`. 
* All tables share the same set of columns. 
* Each table contains either several rows with real data or a symbolic link to another table.
* Initially, all tables contain data, and `𝑖-th` table has $𝑟_𝑖$ rows. 
* You need to perform `𝑚` of the following operations:
* Consider table number $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$. 
* Traverse the path of symbolic links to get to the data. 
* That is,
```
while 𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖 contains a symbolic link instead of real data
do
𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖 ← symlink(𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖)
```
* Consider the table number $𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$ and traverse the path of symbolic links from it in the same manner as for $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$.
* Now, $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$ and $𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$ are the numbers of two tables with real data.
* If $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖 ≠ 𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$, copy all the rows from table $𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$ to table $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$, then clear the table $𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$, and instead of real data, put a symbolic link to $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$ into it.
* Print the maximum size among all $𝑛$ tables (recall that size is the number of rows in the table).
* If the table contains only a symbolic link, its size is considered to be 0.
* See examples and explanations for further clarification.

## Input Format

* The first line of the input contains two integers `𝑛` and `𝑚` — the number of tables in the database, and the number of merge queries to perform, respectively.
* The second line of the input contains `𝑛` integers $𝑟_𝑖$ — the number of rows in the `𝑖-th` table.
* Then follow `𝑚` lines describing merge queries.
* Each of them contains two integers $𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖$ and $𝑠𝑜𝑢𝑟𝑐𝑒_𝑖$ — the numbers of the tables to merge.

## Constraints

* $1 ≤ 𝑛, 𝑚 ≤ 100 000;$ 
* $0 ≤ 𝑟_𝑖 ≤ 10 000;$ 
* $1 ≤ 𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖, 𝑠𝑜𝑢𝑟𝑐𝑒_𝑖 ≤ 𝑛.$

## Output Format

* For each query, print a line containing a single integer — the maximum of the sizes of all tables (in terms of the number of rows) after the corresponding operation.

## Time Limits

* C: 2 sec, C++: 2 sec, Java: 14 sec, Python: 6 sec. C#: 3 sec, Haskell: 4 sec, JavaScript: 6 sec, Ruby: 6 sec, Scala: 14 sec.

## Memory Limit

* 512MB

## Sample 1.

### Input

```
5 5
1 1 1 1 1
3 5
2 4
1 4
5 4
5 3
```

---

**Understanding the input**

`5 5`

* First line `n m` = `5 5`.
* So, there are a total of 5 tables, and a total of 5 queries.

`1 1 1 1 1`

* The size of the first table is `1`, and so on...
* Each table has the same size, that is `1`.

`3 5`

* Destination is 3, and source is 5.
* So, we are asked to merge 5 into 3.

`2 4`

* Merge 4 into 2.

`1 4`

* Merge 4 into 1.

`5 4`

* Merge 4 into 5.

`5 3`

* Merge 3 into 5.

**More detailed explanation of each query is given in the output section.**

---

### Output

```
2
2
3
5
5
```

* In this sample, all the tables initially have exactly 1 row of data.
* Consider the merging operations:

**1.**

`Destination = 3, Source = 5`

* All the data from the table 5 is copied to table number 3. 
* Table 5 now contains only a symbolic link to table 3, while table 3 has 2 rows. 
* 2 becomes the new maximum size.
---

* We merge table 5 into table 3.
* Table 5 points to table 3.
* The size of Table 3 gets increased by 1.

**Behavior Matching With DSU**

* We merge node 5 into node 3.
* Node 5 is an index in an array and its value (= parent) is now 3.
* It suggests that the parent of Node 5 is Node 3.
* As a result of merging, node 3 gets increased by 1.
* When we merge one tree or subtree into another, the new root parent gets more children.
* So, the size of the new root parent is increased by the size of the node we have merged.
* This is an example of union by size, where we increase the size of the root parent after every merge.
---

**2.**

`Destination = 2, Source = 4`

* 2 and 4 are merged in the same way as 3 and 5.

---

**Behavior Matching With DSU**

* Even after the merge, 2 is still the maximum size.
* Because:
* Table 3 has 2 raws.
* So, the size of the table 3 is 2.
* Table 2 has 2 raws.
* So, the size of the table 2 is 2.
---

**3.**

`Destination = 1, Source = 4`

* We are trying to merge 1 and 4. 
* But 4 has a symbolic link pointing to 2. 
* So we actually copy all the data from the table number 2 to the table number 1, clear the table number 2 and put a symbolic link to the table number 1 in it.
* Table 1 now has 3 rows of data, and 3 becomes the new maximum size.

---

**Behavior Matching With DSU**

* We are trying to merge 1 and 4.
  * We are trying to merge node 1 and node 4.
* `4` has a symbolic link pointing to `2`.
  * It is like the node 4 (= index 4) has a value (= parent) `2`.
  * And it suggests that the parent of node 4 is `2`.
  * Notice that the size of the node `2` is `2`. 
* So we actually copy all the data from the table 2 to the table 1, clear the table 2, and put a symbolic link to the table 1 in it.
  * It is like merging the node 2 into the node 1.
  * Clearing the table 2 and putting the symbolic link to the table 1 in it is like clearing the existing parent value of node 2 (= index 2) and putting the root parent value there (= 1).
  * So the value of the node 2 (= index 2) becomes 1.
  * It suggests that the parent of node 2 (= index 2) is 1.
  * Now, the size of the node `2` was `2`.
  * After we merged it into `1`, the size of `1` becomes `3`.
  * So, size `3` is the maximum size now.

---

**4.**

`Destination = 5, Source = 4`

* Traversing the path of symbolic links from 4, we have 4 → 2 → 1, and the path from 5 is 5 → 3.
* So we are actually merging tables 3 and 1.
* We copy all the rows from the table number 1 into the table number 3, and now the table number 3 has 5 rows of data, which is the new maximum.

---

**Behavior Matching With DSU**

* We want to merge 4 into 5.
* Node 4 (= index 4)'s value (= parent) is 2, index 2's value is 1, and index 1's value is 1.
* So, node 1 (= index 1) is the root node, and its size is 3.
* Similarly, node 5 (= index 5)'s value (= parent) is 3, and index 3's value is 3.
* So, node 3 (= index 3) is the root node, and its size is 2.
* So, merging node 4 into 5, means merging node 1 into 3.
* Node 3 becomes the root node, and its size becomes 2 + 3 = 5.
* So, the new maximum size is 5.

---

**5.**

* All tables now directly or indirectly point to table 3, so all other merges won’t change anything.

---

**Behavior Matching With DSU**

* All tables (= nodes, indices) now directly or indirectly point to (= has the root parent value) table 3 (= node 3, index 3).
* It means there is only one DSU now.
* Every other node is just a member of this DSU.
* And we can perform the union (= merge) operation between two members only if there root nodes are different.
* So, we cannot perform any other union (= merge) operation now.

---

## Sample 2

### Input

```
6 4
10 0 5 0 3 3
6 6
6 5
5 4
4 3
```

### Output

```
10
10
10
11
```

### Explanation

* In this example, tables have different sizes.
* Let us consider the operations:

**1.**

* Merging the table number 6 with itself doesn’t change anything, and the maximum size is 10 (table number 1).

**2.**

* After merging the table number 5 into the table number 6, the table number 5 is cleared and has size 0, while the table number 6 has size 6.
* Still, the maximum size is 10.

**3.**

* By merging the table number 4 into the table number 5, we actually merge the table number 4 into the table number 6 (table 5 now contains just a symbolic link to table 6). 
* So the table number 4 is cleared and has size 0, while the table number 6 has size 6. 
* Still, the maximum size is 10.

**4.**

* By merging the table number 3 into the table number 4, we actually merge the table number 3 into the table number 6 (table 4 now contains just a symbolic link to table 6). 
* So the table number 3 is cleared and has size 0, while the table number 6 has size 11, which is the new maximum size.

# Thought Process

## Given

* `n` tables, and `m` queries.
* Row sizes for each table (size of each table calculated by the number of rows).
* Space-separated `m` queries for and between the two tables (it is possible to have the same table in the query).

## Find

* After performing each query, print the maximum size across all the tables.
* That is to say, print the size of the table that has the maximum size out of all the tables.

## Hints

* `symlinks` and `merge` queries between two tables (even when both the tables are the same) suggest `DSU`.
* For example:

> Each table contains either several rows with real data or a symbolic link to another table.

* If we visit table `a` and see it has no real data, only a `symlink` pointing to table `b`, then `b` is a parent of `a`.
* Similarly, the statement:

> If `𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖` ≠ `𝑠𝑜𝑢𝑟𝑐𝑒_𝑖`, copy all the rows from table `𝑠𝑜𝑢𝑟𝑐𝑒_𝑖` to table `𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖`, then clear the table `𝑠𝑜𝑢𝑟𝑐𝑒_𝑖`, and instead of real data, put a symbolic link to `𝑑𝑒𝑠𝑡𝑖𝑛𝑎𝑡𝑖𝑜𝑛_𝑖` into it.

* In terms of `DSU`, it sounds like `union` between `source_i` and `destination_i`, where `source_i` is no longer the parent of itself.
* After the `union`, the `source_i` becomes the `child` of the `destination_i`, and `destination_i` becomes the parent of the `source_i`.

* Also, the statement:

> copying all the rows from the table `source_i` to table `destination_i`, then clear the table `source_i`

* In terms of `DSU`, it is like after we perform the `union` operation between two nodes, the size of the resultant parent node increases by:

```
parent node size = parent node size + child node size
```

### How do we find whether a table contains real data or a symlink?

* Initially, each table is the parent of itself only.
* Only after we merge the two different tables, the resultant child table gets the symlink that points to the parent.
* In the classic and conventional `DSU` data structure, we can have two different arrays.
* A `parent` array that holds the information about `what is the parent of this table (index)`, and a `size` array that holds the information about the `size` of each table.
* The initial size of each table is already given.
* When we merge the two different tables, we update the `parent` of the resultant child and `size` of the resultant parent.
* When we perform the `find(a)` operation, and if the `parent` array returns the same value `a`, it means that `a` is the root node. 
* Here, `a` is the parent of itself.
* Otherwise, if we get a different value, then it is the `symlink` we are looking for.
* In terms of `DSU`, it points to the `parent`.

### How do we arrange the given data and find the required data?

* We don't have any built-in `DSU`.
* So, we need to build one.
* We will have two arrays: `parent` and `size`.
* The size of the `parent` and `size` arrays is equal to the `number of tables`.
* Initially, in the `parent` array, each index will be the parent of itself only.
* However, we will fill the `size` array as per the given `number of rows` for each table.
* So, `the number of rows for a particular table` is the `size of the particular table`.
* Here, we can find the `initial max size`, which we can compare and update if required after each `merge` operation.
* We perform the `union` operation based on the given `m` queries, one by one, one after another.
* After each query, we update the `parent` of the resultant child, `size` of the resultant child may become `0`, and we update the `size` of the resultant parent.
* After doing that, we check if the `resultant parent` has more `size` than the `previous max size`.

### Other points

* Note that each `m` query treats the number of tables as per `1-based` index system.

## Implementation

[MergeTable.kt](../../../../../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment02/03mergeTable.kt)

## Time Complexity

* Initially, we iterate through the given [listOfEachTableSize] to find the `maxSize` across the tables.
* So, if the [listOfEachTableSize] is `n`, where `n` is the number of tables, then it is `O(n)`.
* Then, we have two operations: `find` and `union`, out of which, the `union` operation dominates.
* According to Robert Tarjan's Analysis, `m` operations in DSU takes `O(m * log^{*} n)` time, where `n` is the number of tables in our case, and `m` is the number of `merge` queries.
* So, the overall time complexity becomes `O(n + (m * log^{*}(n)))`.
* Note that, we often use `alpha(n)` instead of `log^{*}(n)`.

## Space Complexity

* We take an `IntArray` of size [listOfEachTableSize] and a `sizeOfTables` list of size [listOfEachTableSize].
* So, it makes the total auxiliary space of `O(2n)` where `n` is the number of tables.
* Hence, the space complexity is `O(n)`.

## Grader Output

```
Good job! (Max time used: 0.87/4.00, max memory used: 162140160/536870912.)
```