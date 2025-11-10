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
    // Policy: A single node without any parents or children has a height of 1.
    // The height of the leaf node is 1.
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

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * Performs `Right Rotation` on the given [node].
     *
     * **PURPOSE:**
     * * We perform the right rotation on the [node] when the [node] is heavy on the left side.
     * * We cannot rotate a null [node]. So, a non-null parameter/argument for the function.
     * * Similarly, we return a non-null and balanced [node], a new node that takes the place of this [node].
     * * So, a non-null return type.
     */
    private fun rotateRight(node: AvlNode): AvlNode {
        // Left cannot be null, because we are doing `rotateRight` on the `node`.
        // It means that the `node` is left-heavy!
        val left = node.left!!
        // No null-safe operator on the `left` because we have used `!!` before.
        val rightOfleft = left.right
        left.right = node
        node.left = rightOfleft
        // Update height from children to parent order
        updateHeight(rightOfleft)
        updateHeight(node)
        updateHeight(left)
        // Return `node.left` that has taken the place of the incoming `node`.
        return left
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * Rotates the given [node] left side to rebalance it.
     *
     * **PURPOSE:**
     * * We perform the left rotation on the given [node] when it is heavy on the right side.
     * * We expect that the incoming [node] is not null.
     * * We return the new non-null node that takes the place of this incoming [node].
     */
    private fun rotateLeft(node: AvlNode): AvlNode {
        // We are rotating the incoming `node` on the left side.
        // It means that the incoming `node` is heavy on the right side.
        // So, we can safely expect that the right side of this incoming `node` is not null.
        val right = node.right!!
        // We don't need to use the null-safe operator on the `right` node, because we have already used `!!` before.
        val leftOfRight = right.left
        right.left = node
        node.right = leftOfRight
        // Update the height from children to parent order
        updateHeight(leftOfRight)
        updateHeight(node)
        updateHeight(right)
        // Return the `node.right` that has taken the place of the incoming `node`.
        return right
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * *
     *
     * **PURPOSE:**
     * *
     *
     * * To insert a [key], we need to [find] the right place.
     * * After inserting the [key], we need to update the height of the relevant nodes (ancestors).
     * * Then, we need to ensure that this [AvlTree] is still balanced.
     * * So, we check the [balanceFactor].
     * * If we find that the [insert] operation has caused an imbalance, we [balance] this [AvlTree] using rotations.
     * * The appropriate rotation function should also update the height of the relevant nodes.
     * * We reassign the [root] to the updated one, because the height and probably also the balance factor of the
     * [root] changes after the [insert] operation.
     * * This is the public API, and it uses the private helper function [insert].
     * * The private helper function [insert] gives the [root] node with the updated height and ensures the balance.
     */
    fun insert(key: Int) {
        root = insert(root, key)
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * It is a recursive function that takes a parent node [node] under which we want to insert a new node of [key].
     *
     * **PURPOSE:**
     * * Performs standard BST insert operation for the given [key].
     * * The insert operation might change the height of several ancestors.
     * * It can also make the AVL-Tree imbalance.
     * * This [insert] operation updates the height of each ancestor and ensures the balance.
     * * It checks the balance factor of the relevant nodes.
     * * If required, it performs the relevant rotation(s) on the relevant nodes to keep the AVL-Tree balanced.
     *
     * **HOW:**
     * * We start from the [root] node to insert a new node of [key].
     * * It follows a standard `BST-Binary Search Tree` approach.
     * * We are using recursion here.
     * * After insertion, we [updateHeight] of the parent node.
     * * Then, we check the [balanceFactor] of the node.
     * * If we find that the [node] is unbalanced, we perform the relevant rotation on it to rebalance it.
     * * We repeat this process of updating the height, checking the balance factor, and rebalancing if required.
     * * We do it from the parent [node] of the new node of [key] all the way up to the [root] node.
     * * Finally, we return the updated [root].
     *
     * @param node The [AvlNode] under which we insert a new [AvlNode] of [key].
     * @param key The [AvlNode.keyValue] property of the new [AvlNode] that we want to insert as a child of [node].
     * @return The updated [root]. Because inserting a new node can change the height of the [root].
     */
    private fun insert(node: AvlNode?, key: Int): AvlNode {
        // Standard BST insert.
        // Base case.
        if (node == null) {
            // The default height of the [AvlNode] is `1` only. So, we don't need to do anything else.
            // Hence, we return.
            return AvlNode(key)
        }
        // We don't need to use the null safe operator `?` because we have already checked the null node case before.
        if (key > node.keyValue) {
            node.right = insert(node.right, key)
        } else if (key < node.keyValue) {
            node.left = insert(node.left, key)
        } else {
            // We don't allow duplicate nodes/keys
            return node
        }

        // Update the height of this ancestor node as it has a new child
        updateHeight(node)

        // Ensure the balance
        val bf = balanceFactor(node)
        if (bf > 1 && balanceFactor(node.left) >= 0) {
            // This `node` is heavy on the left side. So, we need to `rotateRight`.
            return rotateRight(node)
        }
        if (bf > 1 && balanceFactor(node.left) < 0) {
            // There is an LR-imbalance. So, we need to perform the double rotation: LR-Rotation.
            // We can safely expect a non-null left child when the node is LR-imbalanced.
            // The resultant node of the rotation becomes the `node.left`.
            node.left = rotateLeft(node.left!!)
            return rotateRight(node)
        }
        if (bf < -1 && balanceFactor(node.right) <= 0) {
            // The node is heavy on the right side. So, we need to rotate it towards the left side.
            return rotateLeft(node)
        }
        if (bf < -1 && balanceFactor(node.right) > 0) {
            // There is an RL-imbalance. So, we need to perform the double rotation: RL-Rotation.
            // It is an RL-imbalance.
            // So, we can safely expect a non-null right child of the unbalanced node.
            // The resultant node of the rotation becomes the `node.right`.
            node.right = rotateRight(node.right!!)
            return rotateLeft(node)
        }
        // Return the node if there was(is) no imbalance
        return node
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * **WHAT:**
     * * A public API to delete an [AvlNode] of the given [key] from this [AvlTree].
     *
     * **PURPOSE:**
     * * Deletes the [AvlNode] of the given [key] from this [AvlTree].
     * * It also updates the [root] as deleting a node can change the height of the [root].
     *
     * **HOW:**
     * * It uses a private helper function [delete].
     */
    fun delete(key: Int) {
        root = delete(root, key)
    }

    /**
     * Returns the next larger node
     */
    private fun nextLarger(node: AvlNode): AvlNode? {
        var curr = node.right
        while (curr?.left != null) {
            curr = curr.left
        }
        return curr
    }

    /**
     * **Prerequisites/References:**
     * [Local: BinarySearchTrees](docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     * [GitHub: BinarySearchTrees](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d140df22a9b3f47aba79591122f399f67ea211c3/docs/dataStructures/courses/uc/module05binarySearchTrees/15binarySearchTreesBSTBalance.md)
     *
     * [Local: BST Basic Operations](docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
     * [GitHub: BST Basic Operations](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/68ba000c11a35863c194531eca2f277eb7d6f984/docs/dataStructures/courses/uc/module05binarySearchTrees/10binarySearchTreesBSTsBasicOperations.md)
     *
     * **IMPORTANT:**
     * * To understand this [delete] operation, please go through the above `BST Basic Operations` file first.
     * * It has visual representation (images) of this [delete] operation.
     * * It helps understand the code.
     *
     * **WHAT:**
     * * It takes [node] and [key].
     * * It deletes an [AvlNode] of [key].
     * * It uses [node] to find, travel, and compare the [AvlNode] of [key] through this [AvlTree].
     *
     * **PURPOSE:**
     * * It takes [node] to find, travel, and compare the [AvlNode] of [key] to delete from this [AvlTree].
     * * It also updates the height of all the ancestors of the deleted node.
     * * It also checks the balance factor of each ancestor.
     * * If it finds any unbalanced node, it rebalances it using the relevant rotation.
     * * It returns the updated [root].
     */
    private fun delete(node: AvlNode?, key: Int): AvlNode? {
        // If the node is null, it means that we finished travelling the entire tree,
        // and we couldn't find any [AvlNode] having the given [key].
        // So, in that case, we return `null`.
        if (node == null) {
            return null
        }

        // Standard BST style of finding and deleting the [AvlNode] of [key]
        // We don't need to use the null-safe operator on the [node] now,
        // because we have already checked for the case when the given [node] is null.
        if (key > node.keyValue) {
            node.right = delete(node.right, key)
        } else if (key < node.keyValue) {
            node.left = delete(node.left, key)
        } else {
            // We found the node to delete.
            // Recall the 3 cases: 0 child, 1 child, and 3 children.
            // Here, `node` is the [AvlNode] we want to delete.
            if (node.left == null) {
                // The node that we want to delete does not have a left child.
                // So, we return the right child.
                // The `nodeToDelete.right` is attached to one of the two previous assignments:
                // It might end up as: node.left = nodeToDelete.right or node.right = nodeToDelete.right
                return node.right
            } else if (node.right == null) {
                // If the node that we want to delete does not have a right child,
                // we return its left child.
                // The `nodeToDelete.left` is attached to one of the two previous assignments:
                // It might end up as: node.left = nodeToDelete.left or node.right = nodeToDelete.left
                return node.left
            } else {
                // This is a tricky case. The node that we want to delete has two children.
                // In that case, we are interested in the `nextLarger` child of the `nodeToDelete`.
                // We replace the `nodeToDelete.key` with `nextLarger.key`.
                // And then, we delete the `nextLarger`.
                // It means, we call this [delete] function, and pass `node.right` and `nextToLarger.key`.
                // But that can change the structure of the AVL-Tree.
                // The deletion can cause a rotation and can bring a different node than the original node.
                // We need to ensure the correct and proper link to that different node.
                // It will be on the right side of `node`.
                // So, the code becomes:
                val nextLarger = nextLarger(node)
                // We are sure that the `nextLarger` node cannot be null.
                // Because, to be a non-null `nextLarger` node, the `node` must have a right child.
                // This code executes after the previous two `if` conditions.
                // It means that the `node` has two children.
                // It guarantees that the `node` has a right child.
                // And thus, the `nextLarger` node cannot be null.
                node.keyValue = nextLarger?.keyValue!!
                node.right = delete(node.right, nextLarger.keyValue)
            }
        }

        updateHeight(node)
        val bf = balanceFactor(node)
        if (bf > 1 && balanceFactor(node.left) >= 0) {
            // The `node` is heavy on the left side. We need to rotate it to the right side to rebalance it.
            return rotateRight(node)
        }
        if (bf > 1 && balanceFactor(node.left) < 0) {
            // The `node` is LR-imbalanced. So, we need to perform the double rotation: LR-Rotation.
            // RL-imbalance ensures that the `node` has a left child.
            // So, we can use `node.left!!`.
            node.left = rotateLeft(node.left!!)
            return rotateRight(node)
        }
        if (bf < -1 && balanceFactor(node.right) <= 0) {
            // The `node` is heavy on the right side.
            // So, we need to rotate the `node` towards the left side.
            return rotateLeft(node)
        }
        if (bf < -1 && balanceFactor(node.right) > 0) {
            // The `node` has RL-imbalance.
            // So, we need to perform the double rotation: RL-Rotation.
            // The `RL-Imbalance` ensures that the `node` has `right` child.
            // So, we can use `node.right!!`.
            node.right = rotateRight(node.right!!)
            return rotateLeft(node)
        }
        return node
    }
}