package coursera.ucSanDiego.course02dataStructures.module01.section04assignmentProblems

/**
 * # Problem-Statement: Compute tree height
 *
 * ## Problem Introduction:
 *
 * * Trees are used to manipulate hierarchical data such as hierarchy of categories of a retailer or the directory
 * structure on your computer.
 * * They are also used in data analysis and machine learning both for hierarchical clustering
 * and building complex predictive models, including some of the best-performing in practice
 * algorithms like Gradient Boosting over Decision Trees and Random Forests.
 * * In the later modules of this course, we will introduce balanced binary search trees (BST) —
 * a special kind of trees that allows to very efficiently store, manipulate and retrieve data.
 * * Balanced BSTs are thus used in databases for efficient storage and actually in virtually any non-trivial programs,
 * typically via built-in data structures of the programming language at hand.
 * * In this problem, your goal is to get used to trees.
 * * You will need to read a description of a tree from the input, implement the tree data structure,
 * store the tree and compute its height.
 *
 * ## Problem Description:
 *
 * * **Task.**
 *
 * * You are given a description of a rooted tree.
 * * Your task is to compute and output its height.
 * * Recall that the height of a (rooted) tree is the maximum depth of a node, or the maximum distance from a
 * leaf to the root.
 * * You are given an arbitrary tree, not necessarily a binary tree.
 *
 * * **Input Format.**
 *
 * * The first line contains the number of nodes `𝑛`.
 * * The second line contains `𝑛` integer numbers from `−1 to 𝑛 − 1` — parents of nodes.
 * * If the `𝑖-th` one of them (`0 ≤ 𝑖 ≤ 𝑛 − 1`) is `−1`, node `𝑖` is the root,
 * otherwise it’s 0-based index of the parent of 𝑖-th node.
 * * It is guaranteed that there is exactly one root.
 * * It is guaranteed that the input represents a tree.
 *
 * * **Constraints.**
 * ```
 * 1 ≤ 𝑛 ≤ 10^5
 * ```
 *
 * * **Output Format.**
 *
 * * Output the height of the tree.
 *
 *
 */
fun computeTreeHeight() {

}