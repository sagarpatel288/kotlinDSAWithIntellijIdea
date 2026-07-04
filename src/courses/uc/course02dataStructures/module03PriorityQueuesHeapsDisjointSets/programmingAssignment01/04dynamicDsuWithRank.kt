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
     * * [050dynamicDsuWithSize](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c1f537cd112f68e88224cd641f72c547fa61398/src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/050dynamicDsuWithSize.kt)
     */
    fun find(x: T): T {
        makeSet(x)
        if (parent[x] != x) {
            parent[x] = find(parent[x]!!)
        }
        return parent[x]!!
    }

    /**
     * * We should avoid the usage of the `non-null assertion operator` as it is considered as a `code smell`.
     * * Please check:
     * * [050dynamicDsuWithSize](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c1f537cd112f68e88224cd641f72c547fa61398/src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/050dynamicDsuWithSize.kt)
     */
    fun unionByRank(x: T, y: T): Boolean {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return false
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
        return true
    }

    fun connected(x: T, y: T): Boolean {
        return find(x) == find(y)
    }


}