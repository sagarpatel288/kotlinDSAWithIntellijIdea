# Binary Search Tree (BST) Traversal: Quick Revision

<!-- TOC -->
* [Binary Search Tree (BST) Traversal: Quick Revision](#binary-search-tree-bst-traversal-quick-revision)
  * [Prerequisites/References](#prerequisitesreferences)
  * [Example](#example)
    * [Depth-First-Search](#depth-first-search)
      * [Depth-First-Search: Pre-Order: Parent(Root)-Left-Right](#depth-first-search-pre-order-parentroot-left-right)
      * [Depth-First-Search: In-Order: Left-Parent(Root)-Right](#depth-first-search-in-order-left-parentroot-right)
      * [Depth-First-Search: Post-Order: Left-Right-Parent(Root)](#depth-first-search-post-order-left-right-parentroot)
    * [Breadth-First-Search (Level-By-Level-Top-To-Bottom-Left-To-Right)](#breadth-first-search-level-by-level-top-to-bottom-left-to-right)
  * [Next](#next)
<!-- TOC -->

## Prerequisites/References

* [Trees](../module01BasicDataStructures/section03trees/010trees.md)

## Example

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TB
    n1(("50")) --> n2(("40")) & n3(("60"))
    n2 --> n4(("30")) & n5(("45"))
    n3 --> n6(("55")) & n7(("70"))
    n4 --> n8(("25"))
    n4 --> n9(("35"))
    n5 --> n10(("43"))
    n5 --> n11(("47"))
    n6 --> n12(("53"))
    n6 --> n13(("57"))
    n7 --> n14(("65"))
    n7 --> n15(("80"))
```

### Depth-First-Search

#### Depth-First-Search: Pre-Order: Parent(Root)-Left-Right

> 50, 40, 30, 25, 35, 45, 43, 47, 60, 55, 53, 57, 70, 65, 80 

---
* Visit the current node eagerly.
* Then, keep going on the left side.
* Cover the left side.
* Then, cover the right side.
---
* Standing at any current node, we can ask, look, and check:
* Did I cover the parent first?
  * Does the parent come first before the left side?
* After me as a parent, do I cover the left side?
* After covering my left side, do I cover the right side?
  * Does the right side come after the left side?
---

#### Depth-First-Search: In-Order: Left-Parent(Root)-Right

> 25, 30, 35, 40, 43, 45, 47, 50, 53, 55, 57, 60, 65, 70, 80

---
* Did I cover the left side first?
* Only if yes, then cover the current node.
* Then, cover the right side.
---
* Standing at any current node, we can ask, look, and check:
* Did I cover the left side before I cover myself?
  * Left side comes before me.
  * I come after the left side.
* Am I in the middle of my left and right side?
* Do I cover the right side after me?
  * Right side comes after me.
---

#### Depth-First-Search: Post-Order: Left-Right-Parent(Root)

> 25, 35, 30, 43, 47, 45, 40, 53, 57, 55, 65, 80, 70, 60, 50  

---
* Cover the left side first.
* Then, cover the right side.
* In the end, cover the current parent node of both left and right side.
---
* Standing at any current node, we can ask, look, and check:
* Did I cover the left side first?
* Does the left side is followed by the right side?
  * Does the left side come before the right side?
* Did I cover the right side before me?
  * Does the right side come before me?
---

### Breadth-First-Search (Level-By-Level-Top-To-Bottom-Left-To-Right)

> 50, 40, 60, 30, 45, 55, 70, 25, 35, 43, 47, 53, 57, 65, 80

---
* 
---

## Next

