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
class Node<T>(var data: T?, var next: Node<T>?)

class SinglyLinkedListWithoutTail<T>() {
    private var head: Node<T>? = null
    // A dedicated size variable so we can get the size in O(1) anytime instead of in O(n).
    private var size = 0

    fun size() = size

    // We use this condition in almost all the functions. Hence, a separate and dedicated function.
    fun isEmpty() = head == null

    // Add an item to the front (top, start, first) of the list. Time complexity is O(1).
    fun pushFront(key: T) {
        val newNode = Node(key, null)
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
        size++
        // Now, the `curr` pointer (finger) points (refers) to the newly created and added last item (newNode).
        curr?.next = newNode
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

}