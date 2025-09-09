package courses.uc.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment01

/**
 *
 * # References / Resources
 * This is a more practical implementation of
 * [DisjointSet].
 *
 * Here, we use [treeSize] array to perform the [unionByTreeSize] operation instead of using `height`.
 * The reason is that the [treeSize] can tell us about the number of nodes of a particular `subtree`.
 * However, we cannot get this information if we use `height`.
 *
 * # Time Complexity
 *
 * * As per Robert Tarjan's Analysis, for `m` operations in `DSU`, it is $m * log^{*}(n)$, where `n` is total nodes.
 *
 * # Space Complexity
 *
 * * We use two arrays of the given [size] to maintain the `parent` and `size` information.
 * * So, if [size] = `n`, then it is `O(n)`.
 *
 */
class DisjointSetBySize(private val size: Int) {

    /**
     * `parent[i]` shows the `parent` of index `i`.
     * If `parent[i] = i`, then `i` is the root node.
     * Initially, each element is the parent of itself to indicate a total [size] of disjoint sets.
     * As we perform the [unionByTreeSize] operation, we update the [parent] information.
     */
    private val parent = IntArray(size) { it }

    /**
     * `treeSize[i]` indicates the total number of nodes for a subtree whose root node is `i`.
     * It is inclusive. So, one node = size 1.
     * Initially, all the sets are disjoint.
     * Hence, in the beginning, the size of each node is `1`.
     */
    private val treeSize = IntArray(size) { 1 }

    /**
     * Finds the root node for the given node [x] using [parent].
     */
    fun findRoot(x: Int): Int {
        require(x in 0..<size) {
            IllegalArgumentException("Index $x is out of bounds for size $size")
        }
        if (parent[x] == x) {
            return x
        }
        parent[x] = findRoot(parent[x])
        return parent[x]
    }

    /**
     * Performs the `union` operation using the [treeSize] information.
     * We find the root nodes of the given [x] and [y] nodes.
     * If they share the same root node, they are already in the same set. We immediately return `false`.
     * Otherwise, we compare the size of each root node.
     * We hang a smaller tree on the larger tree and increase the size of the larger tree.
     * If the size of both the trees is the same, we hang one tree on another, and increase the size of the root.
     */
    fun unionByTreeSize(x: Int, y: Int): Boolean {
        // The `findRoot` function handles and validates the input
        val rootOfX = findRoot(x)
        val rootOfY = findRoot(y)

        if (rootOfX == rootOfY) {
            return false
        }

        if (treeSize[rootOfX] < treeSize[rootOfY]) {
            parent[rootOfX] = rootOfY
            treeSize[rootOfY] += treeSize[rootOfX]
        } else {
            parent[rootOfY] = rootOfX
            treeSize[rootOfX] += treeSize[rootOfY]
        }

        return true

    }

    /**
     * Provides the [treeSize] of the tree that includes the given [node].
     * So, we find the root of the given [node].
     * And then we find the size of that root node using [treeSize].
     */
    fun getTreeSizeFrom(node: Int): Int {
        // The function `findRoot` handles and validates the input
        val rootNode = findRoot(node)
        return treeSize[rootNode]
    }

    /**
     * To determine whether the given nodes [x] and [y] share the same `set`.
     * If they share the same `root node`, they belong to the same set.
     */
    fun hasSameRootNode(x: Int, y: Int): Boolean {
        // The `findRoot` function handles and validates the input
        val rootOfX = findRoot(x)
        val rootOfY = findRoot(y)
        return rootOfX == rootOfY
    }

}