package courses.uc.course02dataStructures.module03PriorityQueuesHeapsDisjointSets.programmingAssignment01

class DynamicDsuWithSize<T> {
    private val parent = mutableMapOf<T, T>()
    private val size = mutableMapOf<T, Int>()

    private fun makeSet(a: T): T {
        if (a !in parent) {
            parent[a] = a
            size[a] = 1
        }
        return a
    }

    fun find(a: T): T {
        if (a !in parent) makeSet(a)
        // We avoid using the `non-null assertion (double bang)` operator.
        // Because it is seen as "bypassing the safety based on the assumptions that might change."
        // It is like telling the system:
        // "Disable your safety. I guarantee that it will be always non-null. If I am wrong, crash the program!"
        // So, using the `non-null assertion operator` is considered as a `code smell`.
        // At this stage, we are sure that `parent[a]` cannot be null.
        // So, we can safely cast `parent[a]` as T (which is a non-null value).
        // But `as` is a type converter (unsafe cast).
        // It strictly expects a non-null value.
        // Otherwise, it throws an exception.
        // Whereas `as?` is a safe-cast that returns `null` in case of a failure.
        // However, if a node is missing in a set, what is its parent? Itself!
        // So, we translate this `DSU` specific context using: `parent[a] ?: a`
        val curParent = parent[a] ?: a
        if (curParent != null && curParent == a) return a
        // Storing the returned value in a variable for the readability
        val root = find(curParent)
        // Saving a non-null value
        parent[a] = root
        // Returning a non-null value
        return root
    }

    fun union(a: T, b: T): Boolean {
        val rootOfA = find(a)
        val rootOfB = find(b)
        if (rootOfA == rootOfB) return false
        // If we do not have the `size` data of a node, then the node has the default size `1`.
        val sizeOfA = size[rootOfA] ?: 1
        val sizeOfB = size[rootOfB] ?: 1
        if (sizeOfA > sizeOfB) {
            parent[rootOfB] = rootOfA
            size[rootOfA] = sizeOfA + sizeOfB
        } else {
            parent[rootOfA] = rootOfB
            size[rootOfB] = sizeOfB + sizeOfA
        }
        return true
    }

    fun sizeOfSet(a: T): Int {
        val rootOfA = find(a)
        return size[rootOfA]!!
    }

    fun areConnected(a: T, b: T): Boolean {
        val rootOfA = find(a)
        val rootOfB = find(b)
        return rootOfA == rootOfB
    }

    override fun toString(): String {
        return buildString {
            appendLine("Parent: $parent")
            appendLine("Size: $size")
        }
    }
}