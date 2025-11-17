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
 * **Policy:**
 * * The height of a null node is 0.
 * * So, the height of the leaf node is 1.
 *
 * **ToDo:**
 * * Explain time and space complexity for each function.
 *
 */
data class AvlNode(
    var keyValue: Int,
    var left: AvlNode? = null,
    var right: AvlNode? = null,
    // Policy: A single node without any parents or children has a height of 1.
    // The height of a null node is 0.
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
        // The children of `rightOfLeft` are unaffected.
        // So, there is no change in the height of the `rightOfLeft`.
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
        // The children of `leftOfRight` are unaffected.
        // So, there is no change in the height of the `leftOfRight`.
        updateHeight(node)
        updateHeight(right)
        // Return the `node.right` that has taken the place of the incoming `node`.
        return right
    }

    private fun rebalance(node: AvlNode): AvlNode {
        updateHeight(node)
        val bf = balanceFactor(node)
        when {
            // This `node` is heavy on the left side. So, we need to `rotateRight`.
            bf > 1 && balanceFactor(node.left) >= 0 -> return rotateRight(node)
            bf > 1 && balanceFactor(node.left) < 0 -> {
                // This is ultimately a left-sided tree only, but there is an LR-imbalance.
                // So, we need to perform the double rotation: LR-Rotation.
                // We can safely expect a non-null left child when the node is LR-imbalanced.
                // In an LR-imbalance, first we rotate the `node.left` towards the left side.
                // The resultant node of the rotation becomes the `node.left`.
                // Then we rotate the unbalanced `node` towards the right side.
                node.left = rotateLeft(node.left!!)
                return rotateRight(node)
            }
            // The node is heavy on the right side. So, we need to rotate it towards the left side.
            bf < -1 && balanceFactor(node.right) <= 0 -> return rotateLeft(node)
            bf < -1 && balanceFactor(node.right) > 0 -> {
                // The tree is right-sided, but there is an RL-imbalance.
                // So, we need to perform the double rotation: RL-Rotation.
                // So, first we rotate the `node.right` towards the right side.
                // It is an RL-imbalance.
                // So, we can safely expect a non-null right child of the unbalanced node.
                // The resultant node of the rotation becomes the `node.right`.
                // And then we rotate the unbalanced `node` towards the left side.
                node.right = rotateRight(node.right!!)
                return rotateLeft(node)
            }
            // The `node` is already balanced. No need to do anything. We return the already balanced node as it is.
            else -> return node
        }
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
        // We found a vacant place.
        // So, we create a new [AvlNode] of the given [key] and place at this vacant place.
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
            // We don't allow duplicate nodes/keys.
            // If an [AvlNode] of the given [key] already exists, we do nothing. We don't change the structure.
            // We just return the [AvlNode] of the given [key].
            return node
        }

        // Update the height of this ancestor node as it has a new child
        updateHeight(node)

        // Ensure the balance and return the balanced node.
        // At this point, we are sure that the `node` is not null.
        // Hence, the `rebalance` cannot return a `null` value.
        return rebalance(node)!!
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
     * * The [node] argument (parameter) is nullable, because we may not find the [key] even after travelling the tree.
     * * It returns a nullable [AvlNode] to indicate that we couldn't find the [key].
     * * When we can't find the [key], we can't [delete] it, and we don't need to update anything else.
     * * To delete the [key], we first need to find it.
     * * So, we start with the [root] node.
     * * It also updates the height of all the ancestors of the deleted node.
     * * It also checks the balance factor of each ancestor.
     * * If it finds any unbalanced node, it rebalances it using the relevant rotation.
     * * Finally, it returns the updated [root].
     *
     * @param node The [AvlNode] using which we can find the direction of the [AvlNode] of the given [key]
     * @param key We want to find and delete the [AvlNode] having this [key]
     * @return [AvlNode] Returns null if we can't find the [AvlNode] of the given [key]
     * Otherwise, returns the updated [root] from where we started the journey of finding and deleting the [key]
     */
    private fun delete(node: AvlNode?, key: Int): AvlNode? {
        // If the node is null, it means that we finished travelling the entire tree,
        // and we couldn't find any [AvlNode] having the given [key].
        // In other words, we fell off the tree, and we couldn't find the [AvlNode] having the given [key].
        // So, in that case, we return `null`.
        if (node == null) {
            return null
        }

        // Standard BST style of finding and deleting the [AvlNode] of [key].
        // We don't need to use the null-safe operator `?` on the [node] now,
        // because we have already checked for the case when the given [node] is null.
        if (key > node.keyValue) {
            // If the given [key] is greater than the current node's key, find from `node.right`, and delete the [key].
            // The resultant parent node will be assigned to `node.right`.
            node.right = delete(node.right, key)
        } else if (key < node.keyValue) {
            // If the given [key] is less than the current node's key, find from `node.left`, and delete the [key].
            // The resultant parent node will be assigned to `node.left`.
            node.left = delete(node.left, key)
        } else {
            // We found the node to delete.
            // Recall the 3 cases: 0 child, 1 child, and 3 children.
            // Here, `node` is the [AvlNode] we want to delete.
            // To understand, we will call it `nodeToDelete`.
            if (node.left == null) {
                // The node that we want to delete does not have a left child.
                // So, we return the right child.
                // The `nodeToDelete.right` is attached to one of the two previous assignments:
                // It might end up as: node.left = nodeToDelete.right or node.right = nodeToDelete.right
                // It means that we replace (cut, bypass) this `nodeToDelete` with `nodeToDelete.right`.
                // It means that there will be no reference to this `nodeToDelete`.
                // So, it will be garbage-collected.
                return node.right
            } else if (node.right == null) {
                // If the node that we want to delete does not have a right child,
                // we return its left child.
                // The `nodeToDelete.left` is attached to one of the two previous assignments:
                // It might end up as: node.left = nodeToDelete.left or node.right = nodeToDelete.left
                // It means that we replace (cut, bypass) this `nodeToDelete` with `nodeToDelete.left`.
                // It means that there will be no reference to this `nodeToDelete`.
                // So, it will be garbage-collected.
                return node.left
            } else {
                // This is a tricky case. The node that we want to delete has two children.
                // In that case, we are interested in the `nextLarger` child of the `nodeToDelete`.
                // We replace the `nodeToDelete.key` with `nextLarger.key`.
                // And then, we delete the `nextLarger`.
                // It means, we call this [delete] function, and pass `node.right` and `nextToLarger.key`.
                // But that can change the structure of the AVL-Tree.
                // The deletion can cause a rotation and can bring a different node than the original node.
                // We need to ensure the correct and proper link between this node and that other node.
                // It will be on the right side of `node`.
                // Because by definition of the BST, the `nextLarger` node of `nodeToDelete` is on the right side of
                // the `nodeToDelete`.
                // And this `node` is the `nodeToDelete`.
                // First, we go to the right side, and then we find the minimum.
                // So, the code becomes:
                val nextLarger = findMin(node.right)
                // We are sure that the `nextLarger` node cannot be null.
                // Because, to be a non-null `nextLarger` node, the `node` must have a right child.
                // This code executes after the previous two `if` conditions.
                // It means that the `node` has two children.
                // It guarantees that the `node` has a right child.
                // And thus, the `nextLarger` node cannot be null.
                node.keyValue = nextLarger?.keyValue!!
                // The `node` says:
                // My key has just been replaced by the `nextLarger.key`.
                // It means that there are two similar (duplicate) `nextLarger.key`.
                // The one that I have just received and the original one.
                // So, we need to delete the original one.
                // To delete the original `nextLarger,` we first need to find it.
                // And I know that we can start our hunting from my right side: `node.right`.
                // Because that's the direction from which we get my `nextLarger`.
                // But deleting the `nextLarger` will change the height of many ancestors.
                // It may or may not change the AVL-Tree structure.
                // There can be a rotation, and it can replace my existing right node with a different one.
                // I just want to ensure that whether it is the same old node, or a different node,
                // it will be on my right side.
                // So, the `delete` function might update and rebalance all the ancestors up to my right side.
                // So, I need to reassign my right side.
                node.right = delete(node.right, nextLarger.keyValue)
            }
        }
        // Ensure the balance. Return the balanced node.
        return rebalance(node)
    }

    fun find(key: Int): AvlNode? {
        // We finished travelling the tree.
        // We fell off the tree, but we couldn't find the [AvlNode] of [key].
        if (root == null) {
            return null
        }
        var curr = root
        while (curr != null) {
            curr = when {
                key > curr.keyValue -> curr.right
                key < curr.keyValue -> curr.left
                else -> return curr
            }
        }
        // We finished travelling the tree.
        // We fell off the tree, but we couldn't find the [AvlNode] of [key].
        return curr
    }

    fun min(): AvlNode? {
        return findMin(root)
    }

    private fun findMin(node: AvlNode?): AvlNode? {
        if (node == null) return null
        var curr = node
        // `curr` is a `var`. It is mutable and nullable. So, we have to use the null-safe operator `?` on it.
        while (curr?.left != null) {
            // We have already checked for the null-safety on the `curr` in the `while` condition.
            // So, we don't have to use it again.
            curr = curr.left
        }
        return curr
    }

    fun max() = findMax(root)

    /**
     * * The reason we accept a nullable [node] is because of the public function [max].
     * * The public function [max] can be called even when [root] is null.
     * * And that is the reason the return type is also null.
     * * The time complexity of this function is `O(log n)`.
     * * The space complexity of this function is `O(1)`.
     */
    private fun findMax(node: AvlNode?): AvlNode? {
        if (node == null) return null
        var curr = node
        while (curr?.right != null) {
            curr = curr.right
        }
        return curr
    }

    /**
     * * A recursive function instead of an iterative function, because we don't just find and delete the node.
     * * We need to maintain the links and balance each ancestor till the root node as we move back to the top.
     * * So, it is like saying:
     * * "Hey [rootNode], I want you to find and delete the maximum node you have in your AvlTree.
     * And when you delete the maximum node, one or more parents or grandparents might become unbalanced.
     * So, I want you to return the balanced AVLTree's balanced root node."
     * * The [rootNode] delegates this task to its right node,
     * * And we keep delegating (passing on) the task until there is no more right node.
     * * But the last right node might have a left child.
     * * We don't want to abandon it and make it an orphan.
     * * It takes the place of its parent. Its parent was to the right side of its grandparent.
     * * So, the possible left child of the rightmost node will also go to the right side.
     * * Basically, the possible left child of the rightmost node takes the place of its parent.
     * * And then, we need to travel backwards to the top while rebalancing all the parent nodes we face.
     * * So, in the end, what we return is a balanced root node.
     * * So, we don't just delete a node. We return a balanced AVLTree with a balanced root node.
     * * The time complexity and space complexity of this recursive function is `O(log n)`.
     */
    private fun deleteMax(rootNode: AvlNode?): AvlNode? {
        if (rootNode == null) return null
        if (rootNode.right != null) {
            rootNode.right = deleteMax(rootNode.right)
        } else {
            // This possible left child of the last rightmost node links to the above `rootNode.right = ` assignment.
            // It means that this possible left child takes the place of its parent node.
            // It means that no one refers to the parent of this left child (the rightmost node) anymore.
            // So, the rightmost node is garbage collected.
            return rootNode.left
        }
        // We just deleted a node.
        // In other words, we just replaced a node with its possible left child.
        // This possible `left` child has been placed at `rootNode.right = `.
        // It means that the children of the `rootNode` might have been changed.
        // So, we must rebalance the `rootNode` before we return.
        // And this is a recursive function.
        // This is how we keep returning a balanced node from the bottom parent to the root node after deleting a node.
        // This is a balance journey from children to the root, from successors to ancestors.
        // When the recursion function finally returns the actual and balanced `root` node,
        // we are sure that the `root` node, successors, and the AvlTree are balanced.
        return rebalance(rootNode)
    }

    /**
     * * None of the parameters of this function [mergeTwoAvlTrees] is nullable.
     * * Because, before we call this function from the [mergeTwoAvlTrees], we already check if any node is null.
     * * And this is the reason it returns a non-null [AvlNode].
     * * The maximum traveling to find the subtree where `| height difference | is <= 1`, cannot be more than `O(log n)`.
     * * The merge process between a subtree and a tree is `O(1)`.
     * * Each rebalance operation takes `O(1)`.
     * * And we cannot have more than `log n` rebalance operations.
     * * So, the total rebalance operation is also `O(log n)`.
     * * This is a recursive function.
     * * And the call stack cannot be more than `O(log n)`.
     */
    private fun mergeTwoAvlTrees(leftTreeNode: AvlNode, rightTreeNode: AvlNode, pivot: AvlNode): AvlNode {
        val leftTreeHeight = height(leftTreeNode)
        val rightTreeHeight = height(rightTreeNode)
        return when {
            kotlin.math.abs(leftTreeHeight - rightTreeHeight) <= 1 -> {
                pivot.left = leftTreeNode
                pivot.right = rightTreeNode
                // The height difference between the trees is already controlled and balanced.
                // So, we just need to update the height of the pivot and return.
                // We don't need to rebalance the pivot in this condition.
                updateHeight(pivot)
                pivot
            }

            leftTreeHeight > rightTreeHeight -> {
                // The left tree is taller than the right tree.
                // It is not possible not to have `leftTreeNode.right` at this point.
                // Keep going towards the right side of the left tree until we find a subtree where:
                // abs(height(leftSubtree) - height(rightTree)) <= 1
                // When that happens, we fall into the above first condition.
                // We would merge those two trees with the pivot: The left subtree, the right tree, and the pivot.
                // And we would return that tree with its root (pivot) and re-attach it here via the assignment:
                // `leftTreeNode.right = ...`
                // We have to use the non-null assertion because the data type [AvlNode] has the [right] child nullable.
                // It is technically true that an [AvlNode] can have a null right child, but not in this case.
                // So, we have to use the non-null assertion.
                // I don't know if we can do it in a better way (tight signature, and no code-smell).
                leftTreeNode.right = mergeTwoAvlTrees(leftTreeNode.right!!, rightTreeNode, pivot)
                // Clearly, the children and hence, the height of the `leftTreeNode` might have been changed.
                // So, before we return the `leftTreeNode`, we need to rebalance it.
                rebalance(leftTreeNode)
            }

            else -> {
                // This is the condition when rightTreeHeight > leftTreeHeight
                // It is not possible not to have `rightTreeNode.left` at this point.
                // We keep going towards the left side of the right tree until we find a subtree for which:
                // abs( height(leftTree) - height(rightSubtree) ) <= 1.
                // And when that happens, we fall into the first condition where the | height difference | is <= 1.
                // There, we merge those two trees and the pivot: The right subtree, the left tree, and the pivot.
                // It returns us the root (pivot) of that merged tree.
                // We get it here and re-attach via the assignment:
                // `rightTreeNode.left = ...`
                rightTreeNode.left = mergeTwoAvlTrees(leftTreeNode, rightTreeNode.left!!, pivot)
                // The children and hence the height of the `rightTreeNode` might have been changed.
                // We must rebalance it.
                rebalance(rightTreeNode)
            }
        }
    }

    /**
     * * This function merges two AVLTrees.
     * * The condition is that they are AVLTrees; So, they must be balanced.
     * * One AVLTree can be taller than the other, or both trees can have the same height.
     * * Now, in an AVLTree, the left subtree is always smaller in values than the right subtree.
     * * In other words, the left subtree is light and the right subtree is heavy.
     * * The left (light) subtree can be taller or shorter than the right (heavy) subtree, and vice-versa.
     * * So, the right (heavy) subtree can be taller or shorter than the left (light) subtree.
     * * We need to return a merged AVLTree.
     * * We return an AVLTree.
     * * It means that the tree we return must be a balanced AVLTree.
     * * The entire process of merging two AVLTrees is asymptotically `O(log n)`, which is an amazing efficiency.
     * * It uses a recursive function.
     * * And the maximum memory is also asymptotically `O(log n)`.
     */
    fun mergeTwoAvlTrees(leftTreeRoot: AvlNode?, rightTreeRoot: AvlNode?): AvlTree {
        val mergedTree = AvlTree()
        if (leftTreeRoot == null) {
            mergedTree.root = rightTreeRoot
            return mergedTree
        }
        if (rightTreeRoot == null) {
            mergedTree.root = leftTreeRoot
            return mergedTree
        }
        // At this point, we are sure that the `leftTreeRoot` is not null.
        // So, the `findMax` cannot return a null value.
        // So, the `leftMax` cannot be a null value.
        // The `findMax` returns a nullable return type considering the case where the `root` node is null.
        // So, we have to use the non-null assertion.
        // I am unsure if we can do it in a better way.
        // The `findMax` function takes `O(log n)` time.
        val leftMax = findMax(leftTreeRoot)!!
        val pivot = AvlNode(leftMax.keyValue)
        // This is the new balanced root of the left tree after deleting the max node from it.
        // The `deleteMax` function also takes `O(log n)` time.
        val leftRoot = deleteMax(leftTreeRoot)
        // It is possible that the left tree had only one node - the root node that we deleted (extracted).
        // In that case, the `leftRoot` can be null, and we still need to proceed.
        // Because we have the `rightTreeRoot` to attach to the right side of the `pivot`.
        // That's why it becomes important to know and understand when to take a nullable variable, property, or parameter.
        if (leftRoot == null) {
            pivot.right = rightTreeRoot
            // If the leftTree has become null because it had only one node and we made it the pivot,
            // we need to rebalance the `pivot` after we attach the `rightTree` to its right side.
            val mergedRoot = rebalance(pivot)
            mergedTree.root = mergedRoot
            return mergedTree
        }
        // At this point, we are sure that the `leftRoot` is not null.
        val mergedRoot = mergeTwoAvlTrees(leftRoot, rightTreeRoot, pivot)
        mergedTree.root = mergedRoot
        return mergedTree
    }

    fun printInOrder() {
        printInOrder(root)
    }

    private fun printInOrder(node: AvlNode?) {
        if (node == null) return
        printInOrder(node.left)
        println(node.keyValue)
        printInOrder(node.right)
    }
}