package courses.uc.course02dataStructures.module05binarySearchTrees

/**
 * **Prerequisites/References:**
 * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
 * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
 *
 * **WHAT**:
 * * A data class that represents a single node of an AVL-Tree.
 *
 * **PURPOSE**:
 * * We use its various properties for various [AvlTree] (Self-Balanced BST) operations.
 *
 * * For example:
 * `find` uses [keyValue] to decide the direction,
 * `rebalancing` uses [height], etc.
 *
 * @param keyValue: The value of the node. This must be [Comparable].
 * @property left: The left child of this node.
 * @property right: The right child of this node.
 * @property height: Height of this node. Height is the longest path from this node to the leaf node.
 *
 * * Policy: The height of the leaf node is 1.
 *
 */
data class AvlNode(
    var keyValue: Int,
    var left: AvlNode? = null,
    var right: AvlNode? = null,
    // Policy: A single node without any parent and children has a height of 1.
    // So, the height of the leaf node is 1.
    var height: Int = 1
)

/**
 * **Prerequisites/References:**
 * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
 * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
 *
 * **WHAT:**
 * * This represents an AVL-Tree data structure.
 * * This class manages the entire AVL-Tree (A self-balanced binary search tree).
 *
 * **PURPOSE:**
 * * An efficient [find] operation than a simple binary search tree.
 * * Because this [AvlTree] data structure keeps the binary search tree balanced after every [insert] or [delete] operation.
 * * Provides public API such as [insert], [find], [delete], etc.
 */
class AvlTree {

    // Unlike a heap, where the insert operation starts with the bottom of the tree (leaf),
    // in an [AvlTree], we always travel from the [root].
    // So, [root] is the single entry point of this data structure.
    var root: AvlNode? = null

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * A safe helper function to get the height of an [AvlNode].
     *
     * **PURPOSE:**
     * * We use and compare the height of the left and right subtrees to [balance] the [AvlTree].
     * * The purpose of this helper function is to return `0` if the [avlNode] is null.
     * * It indicates that the height of a null [AvlNode] is `0`.
     * * So that we don't have to check every time on [AvlNode.height].
     * * This helper function avoids too many null-safe calls (checks) every time we want to get the height of an [AvlNode].
     *
     * @param avlNode The [AvlNode] to check for the height.
     * @return Either the stored [AvlNode.height] or `0` if the [avlNode] is null.
     */
    private fun height(avlNode: AvlNode?): Int {
        return avlNode?.height ?: 0
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * Recalculates and updates the height of the given [avlNode].
     *
     * **PURPOSE:**
     * * After every [insert], or [delete] operation, we need to [balance] the [AvlTree].
     * * The [balance] operation uses various rotations such as [rotateLeft], [rotateRight], [rotateLeftRight], and
     * [rotateRightLeft].
     * * It might change the position of one or more [AvlNode]s.
     * * When an [AvlNode] gets a new position, it might also change its height.
     * * So, we use this [updateHeight] function on the changed [avlNode] to recalculate and update its height.
     *
     * @param avlNode The node for which we need to recalculate and update the height.
     */
    private fun updateHeight(avlNode: AvlNode?) {
        // `1` for the node itself + the longest path to the leaf node.
        // Or we can also say:
        // `1` for the node itself + the height of the longest (tallest) child.
        // The `maxOf` function does not accept a nullable value.
        // But, our helper function [height] handles it.
        // So, this is the reason (purpose) of that helper function [height].
        avlNode?.height = 1 + maxOf(height(avlNode.left), height(avlNode.right))
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * Gives the balance factor of the given [avlNode]
     *
     * **PURPOSE:**
     * * This is the measurement we use to decide whether the [AvlTree] is balanced (or not).
     * * This is the core property of an AVL-Tree's node.
     * * For a controlled-balanced AVL-Tree:
     * ```
     * | balance factor | = | node.left.height - node.right.height | <= 1
     * ```
     * * If there is a different balance factor, then we need to [balance] the [AvlTree].
     *
     * @param avlNode The [AvlNode] for which we need to check the balance factor.
     *
     */
    private fun balanceFactor(avlNode: AvlNode?): Int {
        // If the node is null, it is perfectly balanced!
        if (avlNode == null) return 0
        return height(avlNode.left) - height(avlNode.right)
    }



}