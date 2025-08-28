package coursera.ucSanDiego.course02dataStructures.module03PriorityQueuesHeapsDisjointSets

/**
 * # References / Resources
 *
 * [Local: DSU](docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsNaiveImplementation/disjointSets02implementation.md)
 *
 * [GitHub: DSU](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/e6201bad2968159c293b35f003f3a18228cd8248/docs/dataStructures/coursera/ucSanDiego/module03priorityQueuesHeapsDisjointSets/section04DisjointSetsNaiveImplementation/disjointSets02implementation.md)
 *
 * Implement DisjointSet (Union-Find Set) using `Union By Rank` and `Path Compression` heuristics.
 *
 */
class DisjointSet(private val size: Int) {

    /**
     * To store the parent of each index/node.
     * Each index represents a node.
     * The value of each index represents the parent of the corresponding node.
     * Initially, the index and the value are the same.
     * Which indicates that we have several independent, disjoint sets.
     */
    private val parent = IntArray(size) { it }

    /**
     * Again, each index represents a node.
     * But here, we store the rank of each node instead of parent information.
     * A rank represents the upper bound of the height for the corresponding node.
     * Initially, each node is a separate, independent tree.
     * So, the initial rank of each node is `1`.
     */
    private val rank = IntArray(size) { 1 }

    /**
     * Finds the root node of the given [x].
     *
     * Time Complexity:
     * The realistic running time of all the `m` operations of DSU is $m * log^{*}(n)$ which is almost constant.
     *
     * Space Complexity:
     * We are not using any additional memory that depends on or grows with the input size.
     * Hence, it is `O(1)`.
     *
     * @param x Consider it as the node ID. And in terms of the [parent] array, it is an index.
     * If the values of the given [x] and `parent[x]` are the same, then we can say [x] is the root node.
     * Otherwise, `parent[x]` gives the parent of [x].
     * For example, if `parent[x] = y`, then we would find the parent of `y` by `parent[y]`.
     * This process continues until the `index` and the `value` are the same, which indicates a root node.
     *
     * @return The root of the given node [x] or `-1` in case of failure, exception, or illegal argument
     */
    fun findRoot(x: Int): Int {
        // Basic checks.
        if (x !in 0..<size) return -1

        // Base condition.
        if (parent[x] == x) {
            return x
        }

        // Path compression.
        parent[x] = findRoot(parent[x])
        return parent[x]
    }

    /**
     * Performs the `union` operation between the given [x] and [y], only if their root nodes are different.
     * Because the `union` operation is possible only if the two elements are from different sets.
     * If the root node of both the elements is the same, then both the elements are from the same set.
     *
     * [rank] shows the upper bound height of a particular node.
     * So, we first find the root nodes of given [x] and [y].
     * Then, we compare the height of both the root nodes.
     * Then, if there is a height difference, we hang a short tree on the large tree.
     * In this case, we don't increase the [rank] of any tree.
     * If both the root nodes have the same [rank], then we make one of the root nodes a parent of another root node.
     * And in this case, we increase the [rank] of the parent root node by 1.
     *
     * Time Complexity:
     * The realistic running time of all the `m` operations of DSU is $m * log^{*}(n)$, which is almost constant.
     *
     * Space Complexity:
     * We are not using any additional memory that depends on or grows with the input size.
     * So, it is `O(1)`.
     *
     * @return `True` if we successfully merge the given nodes [x] and [y]. Otherwise, returns `False`.
     *
     */
    fun unionByRank(x: Int, y: Int): Boolean {
        // Basic checks.
        if (x !in 0..<size || y !in 0..<size) return false

        val rootOfX = findRoot(x)
        val rootOfY = findRoot(y)

        if (rootOfX == rootOfY) {
            return false
        }
        // Union By Rank
        if (rank[rootOfX] < rank[rootOfY]) {
            parent[rootOfX] = rootOfY
        } else if (rank[rootOfY] < rank[rootOfX]) {
            parent[rootOfY] = rootOfX
        } else {
            parent[rootOfY] = rootOfX
            rank[rootOfX]++
        }
        return true
    }

    /**
     * If the root node of the given [x] and [y] nodes is the same, then both the given nodes are in the same set.
     *
     * Time Complexity:
     * We use the [findRoot] function here.
     * And the realistic running time of all the `m` operations of DSU is $m * log^{*}(n)$.
     *
     * Space Complexity:
     * We are not using any additional memory that depends on or grows with the input size.
     * So, the space complexity is `O(1)`.
     */
    fun hasSameSet(x: Int, y: Int): Boolean {
        if (x !in 0..<size || y !in 0..<size) return false
        return findRoot(x) == findRoot(y)
    }


    /**
     * Gives the upper bound height of the root node of the given [x].
     *
     * Time Complexity:
     * We use the [findRoot] function here.
     * The realistic running time of all the `m` operations of DSU is $m * log^{*}(n)$.
     *
     * Space Complexity:
     * We are not using any additional memory that depends on or grows with the input size.
     * Hence, it is `O(1)`.
     */
    fun findSetSize(x: Int): Int {
        val rootOfX = findRoot(x)
        if (rootOfX !in 0..<size) return -1
        return rank[rootOfX]
    }
}