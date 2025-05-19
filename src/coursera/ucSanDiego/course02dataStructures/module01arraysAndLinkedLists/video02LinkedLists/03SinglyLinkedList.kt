package coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists

/**
 * A node that we use in a linked list.
 *
 * # Why a simple, regular, normal class instead of a data class?
 *
 * Because:
 *
 * * We expect a node as a mutable object whose data and next pointer can be changed.
 * For example, when we perform `popFront` and `popBack` operations.
 *
 * * A `data class` is an idiomatic, immutable, and value-semantic holder.
 *
 * * A `normal regular class` gives us the flexibility to implement both the shallow copy and the deep copy on our own,
 * based on the requirements.
 *
 * * However, a `data class` performs a `shallow copy` by default.
 *
 * * A shallow copy is like a shortcut in Windows or an alias in macOS.
 * On the surface, we get a new folder, but it still refers to the original content.
 * A change in a file via the shortcut or the alias is a change in the original file.
 *
 * * A shallow copy is like giving our original homework inside a new container to someone.
 * Someone can make changes directly to our original homework.
 *
 * * However, a deep copy is like copying the entire folder.
 * If we change a file in the copied folder, it does not change the file in the original location.
 *
 * * A deep copy is like giving a photocopy of our homework.
 * If someone makes any changes to the photocopy, our original homework remains untouched and unaffected.
 *
 * * It is a traditional (conventional) practice that the `Node` class is usually a `regular normal class.`
 *
 * * But, if we have some special requirements where using a `data class` helps more than a `regular normal class,`
 * then, of course, we can use a `data class` also.
 *
 * # How to decide whether to use a `data class` or a `normal, regular class`?
 *
 * * Immutability, Value semantics, and Shallow copy by default --> Data Class
 * * Mutability, and Explicit shallow copy and/or deep copy logic --> Regular, normal class.
 *
 * # What does it mean by `value semantics`?
 *
 * * A `data class` uses value semantics.
 * * It means that if the values of two objects are the same, then the objects are equal by default,
 * unless we override the default behavior.
 */
class Node<T>(var data: T?, var next: Node<T>?) {
    override fun toString(): String {
        // If we print "next," it will print all the data simultaneously! It will be like a recursive call!
        return "data: $data"
    }
}

class SinglyLinkedListWithoutTail<T>() {
    private var head: Node<T>? = null
    // A dedicated size variable so we can get the size in O(1) anytime instead of in O(n).
    // The "size" variable is also useful when we want to perform (call) "addItemAtIndex," "removeItemAtIndex," etc.
    private var size = 0

    fun size() = size

    // We use this condition in almost all the functions. Hence, a separate and dedicated function.
    fun isEmpty() = head == null

    // Add an item to the front (top, start, first) of the list. Time complexity is O(1).
    fun pushFront(data: T?) {
        val newNode = Node(data, null)
        // The list is empty.
        if (isEmpty()) {
            head = newNode
        } else {
            // The list either has only one item or has multiple items.
            newNode.next = head
            head = newNode
        }
        size++
    }

    // Read (get) the front (top, start, first) most item of the list. Time complexity is O(1).
    fun topFront(): T? {
        // If the list is empty, the "head" would be null, and the function returns null.
        // If the list has either one item or multiple items, it would return the first item's data.
        return head?.data
    }

    // Remove and read the front (top, start, first) most item of the list. Time complexity is O(1)
    fun popFront(): T? {
        //region
        // If the list is empty, item = null and head (null) and head?.next = null. So, null = null. No exception.
        // If the list has only one item, then head?.next = null. So, head = head?.next = null.
        // If the list has more than one item, then head = head?.next, the head points to the previously second (now first) item.
        val item = head?.data
        head = head?.next
        size--
        return item
        //endregion
    }

    // Add an item to the back (last, tail, end) of the list. Worst-case time complexity is O(n).
    fun pushBack(value: T?) {
        val newNode = Node(value, null)
        // If the list is empty, the time complexity is O(1).
        if (isEmpty()) {
            head = newNode
            size++
            return
        }
        // If the list has only one item, the time complexity is O(1).
        // We can also use the "size" function.
        if (head?.next == null) {
            head?.next = newNode
            size++
            return
        }
        // If the list has more than one item, the time complexity is O(n).
        // Set up a pointer (just like a finger), start from the head, until we reach the current last item.
        // To visualize, use two or more fingers.
        var curr = head
        while (curr?.next != null) {
            curr = curr.next
        }
        // Now, the `curr` pointer (finger) points (refers) to the newly created and added last item (newNode).
        curr?.next = newNode
        size++
    }

    // Read (get) the back (last, tail, end) item of the list. Worst-case time complexity is O(n).
    fun topBack(): T? {
        // If the list is empty.
        if (isEmpty()) {
            return null
        }
        // If the list has only one item.
        if (head?.next == null) {
            return head?.data
        }
        // If the list has more than one item, the time complexity is O(n).
        // To visualize, use two or more fingers.
        var curr = head
        while (curr?.next != null) {
            // keep doing (continue, do) curr = curr.next as long as curr?.next != null
            curr = curr.next
        }
        return curr?.data
    }

    // Remove and read the back (last, end, tail) item of the list. Worst-case time complexity is O(n).
    fun popBack(): T? {
        // The list is empty. Time complexity is O(1).
        if (isEmpty()) {
            return null
        }
        // The list has only one item. Time complexity is O(1).
        if (head?.next == null) {
            val item = head
            // Remove the item.
            head = null
            size--
            return item?.data
        }
        // If the list has more than one item, the time complexity is O(n).
        // To visualize, use two or more fingers.
        var curr = head
        while (curr?.next?.next != null) {
            curr = curr.next
        }
        val oldLastItem = curr?.next
        // Break the connection between the second-to-last item and the last item.
        curr?.next = null
        size--
        return oldLastItem?.data
    }

    /**
     * Add an item to the given index.
     *
     * * Key lemma (Key point, Key fact):
     * It means that the item at `index - 1` (the item at the previous index) will get the new `next` connection.
     *
     * For example:
     *
     * Suppose we have a list of items as: `A (index 0) --> B (index 1) --> C (index 2) --> D (index 3)`
     * Now, we want to add an `item X` at `index 2`.
     * It means that the item at `index 1`, which is `B`, will point to the `new item X` instead of `C`.
     *
     * ```
     * 1. So, we will travel up to item B, which is `index - 1`.
     * 2. Create a new node (item) X.
     * 3. The next connection of item X will be the same as B's next.
     * 4. So, `newNode.next = B.next`
     * 5. And then, we change the next connection of B to make it `B.next = newNode`. (Splice in the `item X`).
     * ```
     *
     * In the end, the list becomes:
     * `A (index 0) --> B (index 1) --> X (index 2) --> C (index 3) --> D (index 4)`.
     */
    fun addItemAtIndex(index: Int, item: T?) {
        // > size and not >= size, because we can add an item to the back (end, last, tail).
        // For example, if we have two items at indices 0 and 1, the size is two, and we can add an item at index 2.
        // If the list size is 2, then the last index at which we can add an item is 2, not more than that.
        if (index > size) {
            throw IndexOutOfBoundsException()
        }
        if (index == 0) {
            return pushFront(item)
        }
        if (index == size) {
            return pushBack(item)
        }
        var curr = head
        // Iteration. Time complexity is O(n).
        // Stand right before the index to change the previous connection.
        repeat (index - 1) {
            // Jump `curr` to the next item until we reach `index - 1`.
            curr = curr?.next
        }
        // The next pointer of the current and newly added items will be the same for a while.
        val newNode = Node(item, curr?.next)
        // Now, change the `next` connection of the previous item. Splice in the newly added item.
        curr?.next = newNode
        size++
    }

    /**
     * Remove the item from the given index.
     *
     * * Key lemma (Key facts):
     * We need to travel up to `index - 1` to change the connection of the item at the previous index.
     *
     * For example, suppose we have a list as below:
     *
     * ```
     * A (index 0) --> B (index 1) --> C (index 2) --> D (index 3)
     * ```
     *
     * And we want to remove `item C`.
     * It means that the previous item, that is `item B`, will get a new `next` connection.
     * The `item B's next pointer` will point to the `item D` instead of the `item C`.
     *
     * ```
     * 1. So, we travel up to the `index - 1` position.
     * 2. previous.next = target.next
     * ```
     *
     * Finally, the list becomes:
     *
     * ```
     * A (index 0) --> B (index 1) --> D (index 2)
     * ```
     */
    fun removeItemAtIndex(index: Int): T? {
        // Index equal to or greater than the size gives the "Index out of bounds exception".
        // We cannot have an item to remove at the size index or beyond.
        // For example, if the list has two items, at `indices 0 and 1`, the `size is 2`,
        // and trying to `remove the item at index 2` will give the "Index out of bounds exception."
        if (index >= size) {
            throw IndexOutOfBoundsException()
        }
        // Removing the first item is equal to the "popFront."
        if (index == 0) {
            return popFront()
        }
        // Removing the last item is equal to the "popBack."
        if (index == size - 1) {
            return popBack()
        }
        // Perform "curr = curr?.next until index - 1" to reach the item at the previous index (the previous item).
        var curr = head
        repeat (index - 1) {
            curr = curr?.next
        }
        // We are at the previous item. The item before the target index.
        // The next item is the target item that we want to remove.
        val itemToRemove = curr?.next
        // At present (Originally, initially), curr?.next = itemToRemove
        // So, we need to replace this connection with the "itemToRemove.next" to bypass the "itemToRemove."
        curr?.next = itemToRemove?.next
        size--
        return itemToRemove?.data
    }

    /**
     * Get the item available at the given index.
     *
     * For example, suppose the list is:
     *
     * ```
     * A (index 0) --> B (index 1) --> C (index 2) --> D (index 3)
     * ```
     *
     * And we want to get the item from `index 2`.
     *
     * For example, initially, the `curr = head = index 0`.
     * Then, the `curr` becomes `curr?.next` once, and now `curr` is at index 1 (item B).
     * Again, the `curr` becomes `curr?.next` (second time), and now the `curr` is at index 2 (item C).
     * To get the item from `index 2`, we moved the `curr` item 2 times.
     * Hence, we can say that:
     *
     * * Key lemma (Key-point, Key fact):
     *
     * ```
     * To get the item from `index x`, we perform ```curr = curr?.next``` x times.
     * ```
     *
     * So,
     *
     * ```
     * 1. We start from the head, where index == 0.
     * 2. And we repeat the operation ```curr = curr?.next``` index times.
     * ```
     *
     */
    fun get(index: Int): T? {
        if (index >= size) {
            throw IndexOutOfBoundsException()
        }
        if (index == 0) {
            return topFront()
        }
        if (index == size - 1) {
            return topBack()
        }
        var curr = head
        repeat(index) {
            curr = curr?.next
        }
        return curr?.data
    }

    /**
     * * Key lemma (Key-point, Key fact):
     * Set (replace) what? Only the data of the node, and not the entire node!
     *
     * So, the connection of the previous item and the target item remains the same.
     * We get the target item from the given index and change the data. That's it!
     *
     * For example, suppose we have a list as:
     *
     * ```
     * A (index 0) --> B (index 1) --> C (index 2) --> D (index 3)
     * ```
     *
     * And we want to change the data (set, replace) at `index 2` to `X`.
     * So, as we have seen in the [get] function,
     * ```
     * To get the item at the given index x, we perform ```curr = curr?.next``` index x times.
     * ```
     * When we get the target item, we perform `item?.data = givenData` to replace (set) the existing data with the
     * given data.
     */
    fun setReplace(index: Int, data: T?) {
        if (index >= size) {
            throw IndexOutOfBoundsException()
        }
        var curr = head
        repeat(index) {
            curr = curr?.next
        }
        curr?.data = data
    }

    /**
     * Check whether any node (item) contains the given [data].
     *
     * * Key lemma (Key-point, Key fact):
     *
     * 1. We need to iterate through the list. But note that we are not changing any connection (the next pointer).
     * Now, the singly linked list can contain a node whose data might be null, but it cannot contain entirely "null"
     * item without "data" and the "next" values.
     *
     * 2. We know that the last item's next pointer points to "null."
     * It means that after the last item, there are no more items.
     * In other words, during the iteration, when "item == null," we have covered all the items.
     *
     * 3. So, we `start` from the `head`, do ```curr = curr.next``` as long as ```curr != null```.
     * Which is:
     *
     * ```
     * var curr = head
     * while (curr != null) {
     *     curr = curr.next
     * }
     * ```
     *
     * 4. However, we iterate to find if at any point ```curr.data == givenData```.
     * If we find ```curr.data == givenData```, we return true. Otherwise, we continue the iteration.
     * So, it becomes:
     *
     * ```
     * var curr = head
     * while (curr != null) {
     *     if (curr.data == givenData) return true
     *     curr = curr.next
     * }
     * ```
     *
     */
    fun contains(data: T?): Boolean {
        if (isEmpty()) {
            return false
        }
        var curr = head
        // Look at the condition carefully. It is "curr != null."
        // Because the variable "curr" represents an item, and not the "item?.data."
        // The list can have an item whose data is "null,"
        // but it cannot have a complete "null" item without the data, and the next pointer.
        // When that happens, when there is a "null" item without the data and the next pointer,
        // It means that we have covered all the items on the list. We have iterated through the complete list.
        // Because only the last item's next pointer can point to a complete "null" item.
        // How to remember? Well, we are not changing any connection (next pointer).
        // We just want to iterate until we find "curr.data == givenData."
        while (curr != null) {
            if (curr.data == data) {
                return true
            }
            // do "curr = curr.next" as long as "curr != null."
            // Only the last item's next pointer can point to the "null."
            curr = curr.next
        }
        return false
    }

    fun clear() {
        head = null
        size = 0
    }

    fun printList() {
        var curr = head
        while (curr != null) {
            println("data: ${curr.data} next: ${curr.next}")
            curr = curr.next
        }
        println("End of the list")
    }

}