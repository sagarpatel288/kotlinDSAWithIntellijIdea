package courses.uc.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment01

/**
 * Dynamic DSU where we don't know the size upfront, introduces memory overhead due to hashing and compression.
 */
class DynamicDsu<T> {
    private val parent = mutableMapOf<T, T>()
    private val rank = mutableMapOf<T, Int>()

    fun makeSet(x: T) {
        if (x !in parent) {
            parent[x] = x
            rank[x] = 0
        }
    }

    /**
     * * We should avoid the usage of the `non-null assertion operator` as it is considered as a `code smell`.
     * * Please check:
     * * [050dynamicDsuWithSize]
     */
    fun find(x: T): T {
        makeSet(x)
        if (parent[x] != x) {
            parent[x] = find(parent[x]!!)
        }
        return parent[x]!!
    }

    fun unionByRank(x: T, y: T) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return
        val rankX = rank[rootX]!!
        val rankY = rank[rootY]!!
        when {
            rankX < rankY -> {
                parent[rootX] = rootY
            }
            rankY < rankX -> {
                parent[rootY] = rootX
            }
            else -> {
                parent[rootY] = rootX
                rank[rootX] = rankX + 1
            }
        }
    }
}