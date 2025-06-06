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

/**
 * # Resources and references:
 *
 * [Singly Linked List](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/fa88641df26851c504c5a0b640fe9b5f853cef68/res/coursera/ucSanDiego/course02dataStructures/module01arraysAndLinkedLists/video02linkedLists)
 *
 * # ----------------------- How to remember? -----------------------
 *
 * Use your 5 fingers of any hand or leg to visualize any operation of the Linked List.
 * For example, I use the fingers of my right hand to visualize any operation of the Linked List.
 * Such as:
 * [pushFront], [topFront], [popFront],
 * [pushBack], [topBack], [popBack],
 * [addItemAtIndex], [getItemAtIndex], [setReplace], [removeItemAtIndex], etc.
 *
 */
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
    fun getItemAtIndex(index: Int): T? {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException()
        }
        if (index == 0) {
            return topFront()
        }
        if (index == size - 1) {
            return topBack()
        }
        val nodeAtIndex = getNodeAtIndex(index)
        return nodeAtIndex?.data
    }

    fun getNodeAtIndex(index: Int): Node<T>? {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException()
        }
        var curr = head
        repeat(index) {
            curr = curr?.next
        }
        return curr
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
     * So, as we have seen in the [getItemAtIndex] function,
     * ```
     * To get the item at the given index x, we perform ```curr = curr?.next``` index x times.
     * ```
     * When we get the target item, we perform `item?.data = givenData` to replace (set) the existing data with the
     * given data.
     */
    fun setReplace(index: Int, data: T?) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException()
        }
        val nodeAtIndex = getNodeAtIndex(index)
        nodeAtIndex?.data = data
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
        val set = mutableSetOf<Node<T>?>()
        var index = 0
        while (curr != null) {
            if (set.contains(curr)) {
                println("The cycle starts at index $index, with data ${curr.data}")
                return false
            }
            set.add(curr)
            if (curr.data == data) {
                return true
            }
            // do "curr = curr.next" as long as "curr != null."
            // Only the last item's next pointer can point to the "null."
            curr = curr.next
            index++
        }
        return false
    }

    /**
     * In-place reverse function is a standard best practice.
     * It changes the original object
     * (In our case, currently it is [coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists.SinglyLinkedListWithoutTail]).
     *
     * For example, suppose the original list is:
     * ```
     * 3 --> 5 --> 7 --> 9
     * ```
     * After the [reverse] function, it becomes:
     * ```
     * 3 <-- 5 <-- 7 <-- 9
     * ```
     * Which is the same as:
     * ```
     * 9 --> 7 --> 5 --> 3
     * ```
     *
     * Now, notice how we change the `next pointer`.
     * For example, ```3 --> 5``` becomes ```3 <-- 5```.
     *
     * Note that in the original list, `3` was the `head`.
     * And in the reversed version, it becomes the last element.
     * Hence, its next pointer will be null.
     *
     * So, we want to achieve:
     *
     * ```
     * Changing the next pointer of the node "3" from 3 --> 5 to Null <-- 3.
     * The next pointer of the first item of the original list should point to the "Null".
     * ```
     *
     * So, if we take `3` as the `current` (target) node. Then, it becomes:
     *
     * ```
     * var prev: Node<T>? = null (Destination).
     * val next = curr.next (so, it is 3.next = 5. Hence, the next is 5.)
     * curr.next = prev (Instead of pointing towards 5, the node 3 points to null now. So, it is null <-- 3)
     * prev = curr (Now, the variable "prev" is shifted from the initial "null" value to node "3". "3" is "prev" now.)
     * curr = next ("5" is the new "curr" now.)
     * ```
     *
     * So, we have successfully changed the next pointer of the first node, 3.
     * Now, to change the next pointer of the next node, which is 5, let us check if we can repeat the same steps.
     *
     * ```
     * -------------------------------------------
     * Original: 3 --> 5 --> 7 --> 9
     * Reverse: Null <-- 3 <-- 5 <-- 7 <-- 9
     * Current target: 5 (which is the "curr" variable as per the last execution)
     * Destination: 3 (which is the "prev" variable as per the last execution)
     * -------------------------------------------
     * Repeating the steps:
     * val next = curr.next (5.next = 7. The node "7" is the new "next".)
     * curr.next = prev (Changed from 5 --> 7 to 3 <-- 5).
     * prev = curr ("5" is the new "prev" now.)
     * curr = next ("7" is the new "curr" now.)
     * ```
     *
     * It seems to be working. It means we can use recursion or iteration.
     * Let us code:
     *
     * ```
     * // Initially, the "prev" variable is null. It is "var," because we change it continuously.
     * // It is outside of the loop, because we will need the last stored value inside the loop.
     * var prev: Node<T>? = null
     * // The first target is the head. We start from the head.
     * // It is also "var," because we change it continuously. Our target changes continuously, one after another.
     * // It is also outside of the loop, because we will need the last stored value inside the loop.
     * var curr = head
     * // We want to repeat this process for each non-null node.
     * while (curr != null) {
     *     val next = curr.next
     *     curr.next = prev
     *     prev = curr
     *     curr = next
     * }
     * head = prev // Because "prev" is the new, last, latest "curr," and the last target is the new "head."
     * ```
     */
    fun reverse() {
        var prev: Node<T>? = null
        var curr = head
        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        head = prev
    }

    /**
     * The key-lemma here is: Robert Floyd's Tortoise and Hare Algorithm.
     * ```
     * 1. Two pointers: Slow and Fast, starting the race from the head.
     * 2. The slow pointer moves 1 step forward, while the fast pointer moves 2 steps forward.
     * 3. We do this while the condition is, at any point, fast != null && fast.next != null.
     * 4. If at any point, slow == fast, there is a cycle. We return true.
     * 5. Otherwise, we return false.
     * ```
     * So, the code translation is:
     * ```
     * Two pointers starting from the head.
     * var slow = head
     * var fast = head
     * ```
     * ```
     * The slow pointer moves 1 step forward, while the fast pointer moves 2 steps forward.
     * slow = slow?.next // moves 1 step forward
     * fast = fast.next?.next // moves 2 steps forward
     * ```
     * ```
     * We do this while the condition is: fast != null && fast.next.next != null
     * while (fast != null && fast.next != null) {
     *     slow = slow?.next
     *     fast = fast.next?.next
     * }
     * ```
     * ```
     * If at any point, slow == fast, there is a cycle. So, we return true.
     * Otherwise, we return false.
     * while (fast != null && fast.next != null) {
     *     slow = slow?.next
     *     fast = fast.next?.next
     *     if (slow == fast) return true
     * }
     * return false
     * ```
     * The complete code:
     * ```
     * var slow = head
     * var fast = head
     * while (fast != null && fast.next != null) {
     *     slow = slow?.next
     *     fast = fast.next?.next
     *     if (slow == fast) return true
     * }
     * return false
     * ```
     */
    fun hasCycle(): Boolean {
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) return true
        }
        return false
    }

    /**
     * This is a helper function to create and then test a cycle in the
     * [coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists.SinglyLinkedListWithoutTail].
     *
     * A cycle in the Singly Linked List is not a good idea as it can lead to an infinite loop.
     * Hence, this is absolutely for testing purposes only.
     *
     * For example, we can [createCycle] and then [findStartCycle].
     */
    fun createCycle(targetIndex: Int) {
        if (targetIndex < 0 || targetIndex >= size) {
            throw IndexOutOfBoundsException("Index is: $targetIndex, and the size is: $size")
        }
        if (isEmpty()) return
        var last = head
        while (last?.next != null) {
            last = last.next
        }
        var target = head
        repeat(targetIndex) {
            target = target?.next
        }
        last?.next = target
    }

    /**
     * Find the entry point (start) of the cycle in a Singly Linked List.
     *
     * We have already seen the [hasCycle] function, which detects whether there is a cycle.
     * This function, [findStartCycle] is an extended version (requirements) of the problem.
     * It answers(fulfills) the question(requirement): If there is a cycle, find its entry point (start).
     *
     * Let us try to understand the intuition.
     *
     * Suppose we have a singly linked list as below:
     *
     * ```
     *   A --> B --> C --> D --> E
     *               ^           |
     *               |           |
     *               |___________|
     *
     * - The cycle starts at node C.
     * - E points back to C, forming a loop.
     * ```
     *
     * Now, let us find where the tortoise and hare meet.
     * Both the slow pointer (tortoise) and the fast pointer (hare) start from the head.
     * The slow pointer (tortoise) takes 1 step forward, while the fast pointer (hare) takes 2 steps forward.
     * So, the journey goes as below:
     *
     * ```
     * 1.
     * Slow pointer (tortoise) from A to B
     * Fast pointer (hare) from A to C
     *
     * 2.
     * Slow pointer (tortoise) from B to C
     * Fast pointer (hare) from C to E
     *
     * 3.
     * Slow pointer (tortoise) from C to D
     * Fast pointer (hare) from E to D (Notice that the fast pointer has completed "1" cycle to meet the slow pointer).
     *
     * 4.
     * They meet at the node (junction) D.
     *
     * 5. Observation:
     * The fast pointer (hare) must complete at least "1" full cycle to meet the slow pointer (tortoise).
     * However, it is possible that the fast pointer (hare) has to complete multiple cycles to meet the tortoise.
     * So, let us denote this "number of full cycles" as "nC" where "C" is the cycle length and "n" is the number of
     * full cycles. So, "n" can be 1 or more.
     * ```
     *
     * Now, we can divide (denote) the entire journey and the path as below:
     *
     * ```
     * 1. The length from the head to the cycle entry (we don't know exactly yet). Let us call it L.
     * 2. The length from the cycle entry point to the meeting point, the slow pointer (tortoise) has travelled = X.
     * 3. The length of the cycle is C.
     * 4. And as we have observed, "nC" is the number of cycles the fast pointer (hare) has completed to meet the tortoise.
     * ```
     * So, it becomes:
     * ```
     *   |---- L ----|--X--|
     *   A --> B --> C --> D --> E
     *               ^           |
     *               |           |
     *               |___________|
     *               |---- C ----|
     *
     * ```
     * Let us convert the journey into a mathematical representation.
     *
     * ```
     * 1. The slow pointer (tortoise) moves 1 step forward.
     * 2. The fast pointer (hare) moves 2 steps forward.
     * 3. The slow pointer (tortoise) has travelled "L + X" distance to meet the fast pointer (hare).
     * 4. And the fast pointer (hare) has travelled "L + X + nC" to meet the slow pointer (tortoise).
     * 5. Now, the fast pointer (hare) moves at twice the speed of the slow pointer (tortoise).
     * 6. So, to make the slow pointer (tortoise) equal to the fast pointer (hare), the slow pointer (tortoise) has to
     * travel twice as much as it has already travelled.
     * 7. So, it becomes: 2(L + X) = L + X + nC.
     * 8. It means that, if the slow pointer (tortoise) travels twice as much as it has already travelled, it can meet
     * the fast pointer (hare), right?
     * 9. Now, let us simplify the math.
     * ----------------------------------------
     * 2L + 2X = L + X + nC
     * 2L + 2X - L - X = nC
     * L + X = nC // We get "L + X" when we multiply "C" by some "n". It means that "L + X" is a multiple of "C."
     * // We can say that the result of "L + X" or "(L + X) - 0" is a multiplication of "C".
     * (L + X) - 0 = nC ------------------------------(1)
     * ```
     * Now, according to modular arithmetic:
     * ```
     * Let us assume that C is 5.
     * Then, (any multiple of 5) mod C = 0.
     * For example,
     * (5 * 2) mod 5 = 0
     * (5 * 3) mod 5 = 0
     * (5 * 4) mod 5 = 0, and so on...
     * ----------------------------------------
     * Also, if the result of "a - b" is a multiple of C, then:
     * a ≅ b (mod C) // Which means that a mod C = b mod C
     * For example, if a is 12, and b is 2, then a - b is 10, which is a multiple of 5. Then, we can say:
     * 12 ≅ 2 mod 5 // Because 12 mod 5 = 2, and 2 mod 5 = 2.
     * So, if the result of "a - b" is a multiplication of C, then a ≅ b (mod C) ------------------------------(2)
     * ```
     * Now, if we use the conclusion (2) for the result (1), then we get:
     * ```
     * (L + X) - 0 = nC ------------------------------(1)
     * If the result of "a - b" is a multiplication of C, then a ≅ b (mod C) ------------------------------(2)
     * If we consider "(L + X)" as "a", and "0" as "b", then it is:
     * a - b = nC
     * a ≅ b (mod C)
     * // Restoring the actual values
     * L + X ≅ 0 (mod C)
     * L ≅ - X (mod C) ------------------------------(3)
     * ```
     * Now, what does equation (3) convey? Let us understand it.
     * ```
     *   |---- L ----|--X--|
     *   A --> B --> C --> D --> E
     *               ^           |
     *               |           |
     *               |___________|
     *               |---- C ----|
     *
     * 1. Recall that "L" is the length between the start (head) and the cycle entry point.
     * 2. "X" is the distance the slow pointer (tortoise) has travelled after the cycle entry point to meet the hare.
     * 3. "C" is the total length of the cycle.
     * 4. Now, if we notice, it is obvious that if we travel "-X" from the meeting point, we reach the cycle entry point.
     * 5. But, in a Singly Linked List, we cannot travel backward.
     * 6. However, travelling "-X" and "C - X" is the same.
     * 7. Because if the slow pointer (tortoise) has already travelled "X," and "C" is the length of the cycle,
     * it has to travel the remaining "C - X" to complete the cycle. So that the total will be "X + C - X = C."
     * 8. Then, what about "mod C?" Well, "mod C" ensures that we do not go out of the cycle.
     * It is the same concept we have learned before,
     * such as the last digit of the nth Fibonacci Number and the Josephus problem.
     * Reference: src/coursera/ucSanDiego/course01algorithmicToolbox/module02AlgorithmicWarmUp/020hugeNthFibonacciModuloDynamic.kt
     * Reference: src/coursera/ucSanDiego/course01algorithmicToolbox/module02AlgorithmicWarmUp/090Josephus.kt
     * 9. So, what is the conclusion? What does the equation "L ≅ -X (mod C)" convey?
     * 10. It says that when our LHS (L) and RHS (-X mod C = C-X) become equal (where they end up, where they meet),
     * that will be the cycle entry point.
     * 11. We don't know the actual values of L, X, or C.
     * But we know that "L" starts from "Head," and "C-X" starts right after "X."
     * So, on one side (LHS), we start from the "head," and on the other side (RHS), we start from "X."
     * 12. From the head, moving "L" steps forward will land us on the cycle entry point,
     * and from "X" (the meeting point), moving "C-X" steps forward will land us on the cycle entry point.
     * 13. We take one step at a time, and when they meet, it is the cycle entry point.
     * When we move one pointer from the head and one from the meeting point, each taking one step at a time,
     * their meeting point will be the cycle entry, because the number of steps needed from each starting position
     * to the entry point is the same modulo the cycle length.
     * ```
     * Code Translation:
     * ```
     * Moving one step forward from the head and the meeting point until they meet
     * slow = head
     * while (slow != fast) {
     *     slow = slow?.next
     *     fast = fast?.next //fast is already at the meeting point when we detected the cycle.
     *     if (slow == fast) {
     *         return slow //or fast. This is the cycle entry point.
     *     }
     * }
     * ```
     * Interesting observation:
     * ```
     * 1. The number of steps from the head to the cycle entry point,
     * and the number of steps from the meeting point to the cycle entry point is the same modulo the cycle length.
     * 2. In other words, the difference between their starting point (head to meeting point) is the cycle length.
     * ```
     *
     */
    fun findStartCycle(): Node<T>? {
        if (!hasCycle()) return null
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) {
                slow = head
                while (slow != fast) {
                    slow = slow?.next
                    fast = fast?.next
                    if (slow == fast) {
                        return slow
                    }
                }
            }
        }
        return null
    }

    fun breakCycle(): Boolean {
        if (isEmpty()) {
            println("The list is empty!")
            return false
        }
        if (!hasCycle()) {
            println("The list does not have a cycle!")
            return false
        }
        val startOfCycle = findStartCycle()
        var curr = head
        var index = 0
        while (curr?.next != startOfCycle) {
            curr = curr?.next
            index++
        }
        curr?.next = null
        println("The cycle broken at index $index, with data: ${curr?.data}")
        return true
    }

    fun getIndexOf(data: T?): Int? {
        if (isEmpty()) {
            println("The list is empty!")
            return null
        }
        val set = mutableSetOf<Node<T>?>()
        var curr = head
        var index = 0
        while (curr?.data != data) {
            if (set.contains(curr)) {
                println("The list has cycle at index: $index, with data: ${curr?.data}")
                return null
            }
            set.add(curr)
            if (curr?.data == data) {
                return index
            }
            curr = curr?.next
            index++
        }
        return null
    }

    /**
     * A simple, standard "toList" function which will provide (convert) the list of [Node.data]
     */
    fun toList(): List<T?> {
        val mutableList = mutableListOf<T?>()
        var curr = head
        while (curr != null) {
            mutableList.add(curr.data)
            curr = curr.next
        }
        return mutableList
    }

    fun clear() {
        head = null
        size = 0
    }

    fun printList() {
        if (isEmpty()) {
            println("The singly linked list is empty!")
            return
        }
        var curr = head
        var index = 0
        val set = mutableSetOf<Node<T>?>()
        val stringBuilder = StringBuilder()
        while (curr != null) {
            if (set.contains(curr)) {
                println("The list has cycle at index $index, with data ${curr.data}")
                break
            }
            set.add(curr)
            stringBuilder.append("${curr.data} --> ")
            curr = curr.next
            index++
        }
        stringBuilder.append("---- End Of The List ----")
        println(stringBuilder)
    }

}

fun main() {
    val singlyLinkedList = SinglyLinkedListWithoutTail<Int>()
    singlyLinkedList.printList()
    singlyLinkedList.pushFront(10)
    singlyLinkedList.pushFront(20)
    singlyLinkedList.pushFront(30)
    singlyLinkedList.pushFront(40)
    singlyLinkedList.printList()
    println("topFront: " + singlyLinkedList.topFront())
    singlyLinkedList.printList()
    println("popFront: " + singlyLinkedList.popFront())
    singlyLinkedList.printList()
    singlyLinkedList.pushBack(50)
    singlyLinkedList.pushBack(60)
    singlyLinkedList.pushBack(70)
    singlyLinkedList.printList()
    println("topBack: " + singlyLinkedList.topBack())
    singlyLinkedList.printList()
    println("popBack: " + singlyLinkedList.popBack())
    singlyLinkedList.printList()
    println("popBack: " + singlyLinkedList.popBack())
    singlyLinkedList.printList()
    singlyLinkedList.addItemAtIndex(3, 100)
    singlyLinkedList.printList()
    println("getItemAtIndex 3: " + singlyLinkedList.getItemAtIndex(3))
    singlyLinkedList.printList()
    singlyLinkedList.removeItemAtIndex(3)
    singlyLinkedList.printList()
    singlyLinkedList.setReplace(3, 90)
    println("getItemAtIndex 3: " + singlyLinkedList.getItemAtIndex(3))
    singlyLinkedList.printList()
    println("contains 90? " + singlyLinkedList.contains(90))
    println("contains 100? " + singlyLinkedList.contains(100))
    println("size: " + singlyLinkedList.size())
    singlyLinkedList.clear()
    println("size: " + singlyLinkedList.size() + " :isEmpty?: " + singlyLinkedList.isEmpty())

}