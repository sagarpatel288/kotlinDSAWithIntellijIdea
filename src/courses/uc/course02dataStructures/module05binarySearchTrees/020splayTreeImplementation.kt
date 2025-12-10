package courses.uc.course02dataStructures.module05binarySearchTrees

/**
 * # Prerequisites/References
 * * [Local File](docs/dataStructures/courses/uc/module05binarySearchTrees/70splayTrees.md)
 * * [GitHub File](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5a2e8434fe79b140afedf34d5fd6542b3152451e/docs/dataStructures/courses/uc/module05binarySearchTrees/70splayTrees.md)
 *
 */
data class Node<T : Comparable<T>>(
    val key: T,
    var parent: Node<T>? = null,
    var left: Node<T>? = null,
    var right: Node<T>? = null
) : Comparable<Node<T>> {

    fun isLeftChild() = parent?.left == this
    fun isRightChild() = parent?.right == this
    override fun compareTo(other: Node<T>): Int {
        // This comparison order is critical here.
        // If this key is smaller than the other key, it will produce the negative result.
        // If this key is greater than the other key, it will produce the positive result.
        // The contract says that negative result goes left, positive result goes right.
        // This gives an ascending order.
        return key.compareTo(other.key)
    }
}

/**
 * # Amortized Time Complexity
 * * `O(log n)`
 *
 * # Space Complexity
 * * `O(n)`, where `n` is the number of nodes
 */
class SplayTree<T : Comparable<T>> {
    var root: Node<T>? = null
        private set

    /**
     *
     */
    private fun rotateRight(node: Node<T>?) {
        if (node == null) return
        // Read the target node
        val target = node.left ?: return

        // Target child
        node.left = target.right
        target.right?.parent = node

        // Target replaces the parent
        target.parent = node.parent
        if (target.parent == null) root = target
        else if (node.isLeftChild()) node.parent?.left = target
        else node.parent?.right = target

        // Current parent node become the right child of the target node
        target.right = node
        node.parent = target
    }

    /**
     *
     */
    private fun rotateLeft(node: Node<T>?) {
        if (node == null) return
        // Read the target node
        val target = node.right ?: return

        // Update the target child
        node.right = target.left
        target.left?.parent = node

        // Target replaces the current parent node
        target.parent = node.parent
        if (target.parent == null) root = target
        else if (node.isLeftChild()) node.parent?.left = target
        else node.parent?.right = target

        // The current parent node becomes the target child
        target.left = node
        node.parent = target
    }

    /**
     *
     */
    private fun splay(node: Node<T>?) {
        if (node == null) return
        while (node.parent != null) {
            val parent = node.parent!!
            val grandParent = node.parent?.parent

            if (grandParent == null) {
                if (node.isLeftChild()) {
                    rotateRight(parent)
                } else {
                    rotateLeft(parent)
                }
            } else {
                if (node.isLeftChild() && parent.isLeftChild()) {
                    rotateRight(grandParent)
                    rotateRight(parent)
                } else if (node.isRightChild() && parent.isRightChild()) {
                    rotateLeft(grandParent)
                    rotateLeft(parent)
                } else if (node.isRightChild() && parent.isLeftChild()) {
                    rotateLeft(parent)
                    rotateRight(grandParent)
                } else {
                    rotateRight(parent)
                    rotateLeft(grandParent)
                }
            }
        }
        root = node
    }

    /**
     *
     */
    fun search(key: T): Node<T>? {
        if (root == null) return null
        var curr = root
        var lastAccessed = curr
        while (curr != null) {
            lastAccessed = curr
            curr = if (key < curr.key) {
                curr.left
            } else if (key > curr.key) {
                curr.right
            } else {
                splay(curr)
                return curr
            }
        }
        splay(lastAccessed)
        return null
    }

    /**
     *
     */
    fun insert(key: T) {
        val node = Node(key)
        if (root == null) {
            root = node
            return
        }
        var curr = root
        var parent = curr
        while (curr != null) {
            parent = curr
            if (key < curr.key) {
                curr = curr.left
            } else if (key > curr.key) {
                curr = curr.right
            } else {
                splay(curr)
                return
            }
        }
        if (parent != null) {
            if (key < parent.key) {
                parent.left = node
            } else if (key > parent.key) {
                parent.right = node
            }
        }
        node.parent = parent
        splay(node)
    }

    /**
     *
     */
    fun delete(key: T) {
        if (root == null) return
        val target = search(key) ?: return
        val leftRoot = target.left
        val rightRoot = target.right

        // Disconnect (delete) the target
        leftRoot?.parent = null
        rightRoot?.parent = null
        target.left = null
        target.right = null

        if (leftRoot == null) {
            root = rightRoot
            return
        }

        var max = leftRoot
        while (max?.right != null) {
            max = max.right
        }
        splay(max)
        max?.right = rightRoot
        rightRoot?.parent = max
        root = max
    }
}