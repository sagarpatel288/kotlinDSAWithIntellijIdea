# Disjoint Set Union (Also known as, Union Find)

<!-- TOC -->
* [Disjoint Set Union (Also known as, Union Find)](#disjoint-set-union-also-known-as-union-find)
  * [Resources / References](#resources--references)
  * [Disjoint Sets](#disjoint-sets)
  * [Find](#find)
  * [Union](#union)
  * [Array & Graph Representation](#array--graph-representation)
    * [Union By Rank](#union-by-rank-)
    * [Path Compression](#path-compression)
<!-- TOC -->

## Resources / References

* [Abdul Bari Sir](https://youtu.be/wU6udHRIkcc?si=huj_Km4_SKLZshdP)
* [codestorywithMIK: DSU: Part-01: Concept](https://youtu.be/AsAdKHkITBQ?si=jKFfP4miBOLYIgTZ)
* [codestorywithMIK: DSU: Part-02: Rank & Path Compression](https://youtu.be/iH3XVIVzl7M?si=azdvs1H431SH8LNk)
* [Coursera UC San Diego Data Structures](https://www.coursera.org/learn/data-structures)


## Disjoint Sets

![02disjointSetsIntroduction01.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/02disjointSetsIntroduction01.png)

* The image shows a forest, where we have several independent trees without any children.
  * [Forest tree reference](../../module01BasicDataStructures/section03trees/trees.md#forest)
  * They are disconnected. They are disjointed.
* We can create a child-parent relationship between them, as shown in the image below:

![010disjointSetUnionFindIntro_disjoint_sets.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/010disjointSetUnionFindIntro_disjoint_sets.png)

* We have two sets in the image, and they are disconnected.
* So, we call them **Disjoint Sets**.
* In the technical term, we would say:
  * When the intersection of sets is `NULL,` we call such sets **Disjoint Sets**.

## Find

* This is the operation that tells about the parent set of the target element.
  * In other words, it tells us about the set the element belongs to.
* For example, `1` belongs to `set 1`. `7` belongs to `set 2`.

## Union

![020disjointSetUnionFindIntro_disjoint_sets_union.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/020disjointSetUnionFindIntro_disjoint_sets_union.png)

* When we get two elements to perform the union operation on them.
  * First, we find their sets. Only if they belong to two different sets can we perform the union operation on them.
  * Second, when we perform a union operation on two elements, we get a different set.

## Array & Graph Representation

![42arraysAndGraphRepresentation.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/42arraysAndGraphRepresentation.png)

* We have two data:
  * The element value.
  * The parent value (or the set value it belongs to).
* Using these two data, we do a couple of things:
  * We can find if we have a particular element (the `find` operation).
  * We can find the set of a particular element.
  * We can find whether two elements belong to the same set (parent).
  * We can perform the `union` operation between elements that have different parents (sets).
* Let us assume that the indices are the values.
  * In that case, the actual values represent the parent value.
  * For example, in the given image, the parent of each index is itself.
  * In other words, initially, each element has a different set.
* Then, we create a union.
  * For example, the element `0` belongs to the set `0` and the element `1` belongs to the set `1`.
  * They are both in different sets.
  * So, we can perform the union operation on them.
* Once we perform the union operation, we need to decide who becomes the parent (leader) of both elements.
  * For example, if we perform the union between the elements (0, 1), we have two options.
  * Either the parent of `0` or `1` can become the parent of the new set `(0, 1)`.
* So, **is there any rule for making or selecting the parent?**
  * Yes. If the size (rank, weight) of each set (parents, subtrees) is equal, we can select anyone.
  * Otherwise, we always select and make the parent who has the higher rank (size, weight). 
  * So, the set (parent, subtree) with the higher rank (size, weight) becomes the parent. And the set (subtree) with the lower rank (size, weight) becomes the child.
* **Why do we have this rule?**
  * If we make a larger subtree the parent of a smaller subtree, it keeps the tree height shallow (short).
  * If we make a shorter subtree the parent of a larger subtree, it increases the tree height.
  * If the tree height is short (shallow), we can finish the traversal and relevant operations quickly.
  * If the tree height is taller (larger, deeper), we need more time to finish the traversal and relevant operations compared to the tree with a short (shallow) height.
* **How do we define the size (rank, weight) of a set (subtree)?**
  * It is just the height of the subtree (set). 
  * So, the taller set (subtree) becomes the parent, and the shorter set (subtree) becomes the child. 
* So, it goes like this:

![052unionOfZeroOne_01.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/052unionOfZeroOne_01.png)

* So, now it says that the parent of element `1` is `0`.
  * We could have selected `1` as a parent of `0` as well.
* We can continue this union process and make `2` a parent of `3`.

![060unionPart02.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/060unionPart02.png)

* Now, if we want to perform the union operation for `(0, 2)`, we have two options:
  * We can either make `0` or `2` the parent.
* Let us make `0` the parent of `2`.
* So, we get:

![065unionPart03.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/065unionPart03.png)

### Union By Rank 

* Now, if we want to perform the union operation between `(0, 4)`, the rule says that `4` must be a child of `0` because `0` is a taller tree than `4`.
* However, let us see what difference it makes.
* Let us make `4` a parent instead of `0` for a while.

![070unionPart04.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/070unionPart04.png)

* So, it makes the tree height `4` (where a level starts from `1`).
* And if we make `0` a parent of `4`, then we get:

![075unionPart05.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/075unionPart05.png)

* So, it makes the tree height `3` (where a level starts from `0`).
* Hence, we always make a taller tree a parent. It keeps the tree height shallow (short).
* However, it might be inefficient to calculate the height of both subtrees at runtime to decide which one should be the parent.
* But, we can use a clever technique to decide the taller parent in `O(1)` time.
* Every time a parent gets a child, we increase the rank (seniority, value, weight) of the parent.

* So, it goes as shown in the image below:

![080unionByRank01.png](../../../../../../assets/images/dataStructures/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section03disjointSetsUnionFind/080unionByRank01.png)

### Path Compression

* Now, if we continue the same example, and ask `3` about its parent, it will first point us towards `2`.
* We will ask `2` about its parent. It will then point us towards `0`.
* We will then ask `0` about its parent. 
* In the end, we find that `0` is the parent.
* **Can we store this information in `3`?**
* So, the next time we ask `3` about its parent, we can get a quick answer.
* **How do we store information in an array?**

```kotlin
parent[3] = findParent[2] = findParent[0]
```