package coursera.ucSanDiego.course02dataStructures.module01.video02LinkedLists

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
class Node<T>(var data: T, var next: Node<T>?) {
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
    // Public to access during the merge two linked lists operation
    var head: Node<T>? = null

    // A dedicated size variable so we can get the size in O(1) anytime instead of in O(n).
    // The "size" variable is also useful when we want to perform (call) "addItemAtIndex," "removeItemAtIndex," etc.
    private var size = 0

    fun size() = size

    // We use this condition in almost all the functions. Hence, a separate and dedicated function.
    fun isEmpty() = head == null

    // Add an item to the front (top, start, first) of the list. Time complexity is O(1).
    fun pushFront(data: T) {
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
    fun pushBack(value: T) {
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
    fun addItemAtIndex(index: Int, item: T) {
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
        repeat(index - 1) {
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
        repeat(index - 1) {
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
    fun setReplace(index: Int, data: T) {
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
     * (In our case, currently it is [coursera.ucSanDiego.course02dataStructures.module01.video02LinkedLists.SinglyLinkedListWithoutTail]).
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
    fun reverse(breakCycle: Boolean = true) {
        var prev: Node<T>? = null
        var curr = head
        val last = head
        val set = mutableSetOf<Node<T>?>()
        val startCycle = findStartCycle()
        while (curr != null) {
            if (set.contains(curr)) {
                println("The cycle starts at index ${set.indexOf(curr)}")
                break
            }
            set.add(curr)
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        head = prev
        if (!breakCycle && startCycle != null) {
            last?.next = startCycle
        }
    }

    fun findMiddleNode(): Node<T>? {
        if (isEmpty()) return null
        if (hasCycle()) {
            println("The list has a cycle!")
            return null
        }
        var slow = head
        var fast = head
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }

    /**
     * # References:
     *
     * 1. [Striver, Raj, TakeUForward](https://youtu.be/jXu-H7XuClE?si=bETfXB3hwIB0DV2c)
     * 2. [Monica AI](https://monica.im/share/chat?shareId=uZBeKJIaViG0Axu3&locale=en)
     *
     * # Explanation:
     *
     * The idea is, we take a fake node, called "preHead."
     * ```
     * val preHead = Node(-1, null)
     * ```
     * Then, we iterate over the given lists until one of the lists gets exhausted.
     * ```
     * var temp1 = listOne.head
     * var temp2 = listTwo.head
     * while (temp1 != null && temp2 != null) {
     *     ...
     * }
     * ```
     *
     * Let us visualize the scene here.
     *
     * 1. The four variables. preHead, temp, temp1, and temp2.
     *
     * ```
     * // Create a node in the space (The King), not connected with anyone yet.
     * 1. val preHead = Node(-1, null)
     * // Create a helper (A chancellor) for the "preHead" that will help make the next connections.
     * // Selects the stones (items) from both the lists, releases and separates the item from the list,
     * // and puts on the way to prepare the walking path for the King.
     * 2. var temp = preHead // Lays stones on the mud, jumps on the stone, and repeats.
     * // A variable that keeps the track of the items covered (compared, released, and laid down) for the list one.
     * 3. var temp1 = listOne.head
     * // A variable that keeps the track of the items covered (compared, released, and laid down) for the list two.
     * 4. var temp2 = listTwo.head
     * ```
     *
     * 2. The current setup looks like below:
     *
     * ```
     * 1. Two lists:
     *
     * temp1
     *   1 ------> 3 ------> 5
     *
     *   2 ------> 4 ------> 6 ------> 8 ------> 10
     * temp2
     *
     * 2. The preHead and temp collaboration (chemistry):
     *
     * preHead
     *   ^
     *   |
     * temp
     * ```
     *
     * 3. Now, imagine that the helper variable (the chancellor) "temp" is at "preHead," (Besides the King),
     * and selects the stones from the lists.
     * Then, he releases and separates the selected stone from the list,
     * lays down the selected stone on the mud (connects the next item),
     * and jumps on the stone (moves on the next item it just has laid).
     *
     * And it keeps doing this (repeats) as long as it has stones (items) to select from the listOne and the listTwo.
     *
     * The selection of the next item (the stone) is based on a comparison.
     * The King has asked to select the smaller (inexpensive) stones first.
     * So, the chancellor selects the smaller one between the variable "temp1," and "temp2."
     *
     * That means, the next item of `temp` (or `preHead`, because currently `temp = preHead`)
     * is the smaller one between `temp1` and `temp2`.
     *
     * So, we can say that, `temp.next` = The smaller item between `temp1` and `temp2`.
     * Notice that `temp.next` means `preHead.next`, because `temp` represents `preHead` at the moment.
     *
     * 4. How do we convert this into the code?
     *
     * ```
     * if (temp1.data <= temp2.data) {
     *     temp.next = temp1
     * } else {
     *     temp.next = temp2
     * }
     * ```
     *
     * 5. Explanation:
     * We compare `temp1, and temp2.`
     * If the smaller item is `temp1`, then `temp.next = temp1`.
     * Otherwise, `temp.next = temp2`.
     *
     * 6. Now, the smaller item is `temp1`. So, the resultant setup looks like below:
     *
     * ```
     * 1. Two lists:
     *
     * temp1
     *   1 ------> 3 ------> 5
     *
     *   2 ------> 4 ------> 6 ------> 8 ------> 10
     * temp2
     *
     * 2. The preHead and temp collaboration (chemistry):
     *
     * preHead --> 1 (Because `temp.next` means `preHead.next` as `temp = preHead` at the moment)
     *   ^
     *   |
     * temp
     * ```
     *
     * 7. Now, the item `1` of the list one has been taken (compared, selected, released, and laid down).
     * So, the `temp1` variable moves to the next item.
     *
     * 7. How do we translate this into code?
     *
     * ```
     * // The "temp1" variable moves to the next item.
     * temp1 = temp1.next
     * ```
     *
     * 8. Similarly, if `temp2` was smaller than the `temp1`, we would have taken and connected the `temp2` item,
     * and then we would have moved the `temp2` variable to the next item.
     *
     * Which translates to:
     *
     * ```
     * // The "temp2" variable moves to the next item.
     * temp2 = temp2.next
     * ```
     *
     * 9. Let us add this code to the existing code:
     *
     * ```
     * // Compare the `temp1` and the `temp2` variables
     * if (temp1.data <= temp2.data) {
     *     // The `temp1` variable is smaller. So, we take it as the next connection.
     *     temp.next = temp1
     *     // We took and connected the `temp1` item. So, it moves to the next item.
     *     temp1 = temp1.next
     * } else {
     *     // The `temp2` variable is smaller. So, we take it as the next connection.
     *     temp.next = temp2
     *     // We took and connected the `temp2` item. So, it moves to the next item.
     *     temp2 = temp2.next
     * }
     * ```
     *
     * 10. Now, the setup is:
     *
     * ```
     * 1. Two lists:
     *
     * Done     temp1
     *   1 ------> 3 ------> 5
     *
     *   2 ------> 4 ------> 6 ------> 8 ------> 10
     * temp2
     *
     * 2. The preHead and temp collaboration (chemistry):
     *
     * preHead --> 1 (Because `temp.next` means `preHead.next` as `temp = preHead.`)
     *   ^
     *   |
     * temp
     * ```
     *
     * 11. Now, we need to connect the `next` item for `1`. So, the `temp` variable moves to `1`.
     * (Jumps on the stone it just has laid on the mud).
     *
     * Let us visualize this first.
     *
     * ```
     * 1. Two lists:
     *
     * Done     temp1
     *   1 ------> 3 ------> 5
     *
     *   2 ------> 4 ------> 6 ------> 8 ------> 10
     * temp2
     *
     * 2. The preHead and temp collaboration (chemistry):
     *
     * preHead --> 1 (Because `temp.next` means `preHead.next` as `temp = preHead.`)
     *             ^
     *             |
     *           temp
     * ```
     *
     * 12. How do we translate this into the code?
     *
     * ```
     * The "temp" variable moves to "1" which used to be "temp1."
     * (I.e., Once upon a time, "temp1" was at "1". Before the "temp1" moved to "3,", it was "temp1 = 1.").
     * temp = temp1 // Before we make temp1 = temp1.next
     * ```
     *
     * 13. So, the code becomes:
     *
     * ```
     * if (temp1.data <= temp2.data) {
     *     temp.next = temp1 // Connects the next item to the existing "preHead" chain.
     *     temp = temp1 // Moves itself to the last item of the existing "preHead" chain.
     *     temp1 = temp1.next // The "temp1" variable moves to the next item.
     * } else {
     *     temp.next = temp2 // Connects the next item to the existing "preHead" chain.
     *     temp = temp2 // Moves itself to the last item of the existing "preHead" chain.
     *     temp2 = temp2.next // The "temp2" variable moves to the next item.
     * }
     * ```
     *
     * 14. This process continues until one of the lists gets exhausted (no item left to cover).
     * Let us translate this into the code and write the complete code:
     *
     * ```
     * // An item in the space that does not have any next connection item yet.
     * var preHead = Node(-1, null)
     * // A helper for the "preHead" to establish the next connections.
     * var temp = preHead // The `temp` says: "I represent the `preHead`."
     * // A temporary variable to cover, travel through the list one, starting from the head.
     * var temp1 = listOne.head
     * // A temporary variable to cover, travel through the list two, starting from the head.
     * var temp2 = listTwo.head
     *
     * // Iteration until one of the lists gets exhausted (no item left to cover).
     * while (temp1 != null && temp2 != null) {
     *     if (temp1.data <= temp2.data) {
     *         temp.next = temp1 // lays the stone on the mud.
     *         temp = temp1 // Moves on the stone it just has laid on the mud.
     *         temp1 = temp1.next // Moves on the next item of the list to cover.
     *     } else {
     *         temp.next = temp2 // lays the stone on the mud.
     *         temp = temp2 // Moves on the stone it just has laid on the mud.
     *         temp2 = temp2.next // Moves on the next item of the list to cover.
     *     }
     * }
     *
     * // Suppose, the variable "temp2" becomes null. I.e., There are no stones (items) left in the listTwo.
     * // The listTwo gets exhausted. Now, we are left with the listOne only.
     * // We can select the stones (items) from the listOne only until the listOne also gets exhausted.
     * // But the listOne is already sorted and there is no item left in the listTwo to compare with.
     * // Hence, we can take the entire remaining items of the listOne starting from the "temp1."
     * // Why starting from the "temp1"?
     * // Because, we have already taken the items before the variable "temp1."
     * // And the items from the "temp1" are already connected in a chain.
     * // We have not released and separated the remaining items of the chain yet.
     * // So, we just need to connect with the "temp1" and we are done.
     *
     * // On the other hand,
     * // Suppose, the listOne gets exhausted first. I.e., there are no stones (items) left in the listOne.
     * // Now, we are left with the listTwo only.
     * // We can select items only from the listTwo now, until the listTwo also gets exhausted.
     * // But the listTwo is already sorted and there is no item left in the listOne to compare with.
     * // Hence, we can take all the remaining items of the listTwo starting from the "temp2."
     * // Why starting from the "temp2"?
     * // Because, we have already taken the items before the variable "temp2."
     * // And the items from the "temp2" are already connected in a chain.
     * // We have not released and separated the remaining items of the chain yet.
     * // So, we just need to connect with the "temp2" and we are done.
     *
     * // In short, we connect with the variable which is not null yet.
     * temp.next = temp1 ?: temp2
     * ```
     *
     * 15. The chancellor (the "temp" variable) has laid down all the stones (items) in a proper order.
     * Now, the next step of the king will be on the first stone (item) the chancellor has laid down.
     * In other words, the chain of stones (merged sorted lists) starts with the next step of the King.
     *
     * How to translate this into the code?
     *
     * ```
     * val mergedSortedList = SinglyLinkedListWithoutTail<Int>()
     * mergedSortedList.head = preHead.next
     * return mergedSortedList
     * ```
     *
     * 16. Full and final Code (Full and final Story): Complete Code (Complete Story) is given in the function body.
     */
    fun mergeTwoLinkedLists(
        listOne: SinglyLinkedListWithoutTail<Int>,
        listTwo: SinglyLinkedListWithoutTail<Int>
    ): SinglyLinkedListWithoutTail<Int> {
        // A separate node in the space
        val preHead = Node(-1, null)
        // A helper variable to establish next nodes to "preHead."
        var temp = preHead
        // A helper variable to cover each item of the listOne, starting from the head.
        var temp1 = listOne.head
        // A helper variable to cover each item of the listTwo, starting from the head.
        var temp2 = listTwo.head
        // Iterating through to compare the items of the listOne and listTwo.
        while (temp1 != null && temp2 != null) {
            if (temp1.data <= temp2.data) {
                temp.next = temp1 // Establishes the next node.
                temp = temp1 // Moves to the established node.
                temp1 = temp1.next // Moves to the next item.
            } else {
                temp.next = temp2 // Establish the next node.
                temp = temp2 // Moves to the established node.
                temp2 = temp2.next // Moves to the next item.
            }
        }
        // Establishes the remaining non-null chain as it is because the remaining part is already sorted.
        temp.next = temp1 ?: temp2
        val mergedList = SinglyLinkedListWithoutTail<Int>()
        mergedList.head = preHead.next
        return mergedList
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
     * [coursera.ucSanDiego.course02dataStructures.module01.video02LinkedLists.SinglyLinkedListWithoutTail].
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
                //region Considering a case where both the slow and the fast pointers meet at the head.
                // Hence, the head itself is a cycle entry (start) point.
                // It means that this is a perfect circular list where the last item points to the head.
                // For example, 70 --> 60 --> 50 --> 90 --> points back to the head, 70.
                // Now, it is possible that both the slow and the fast pointer meet at the node 70 itself.
                // In that case, we find that the head and the meeting point are at the same node.
                // So, we can safely conclude that the head itself is a starting (entry) point of the cycle,
                // and that this is a perfect circular-linked list.
                if (slow == fast) {
                    return slow
                }
                //endregion
                while (true) {
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
        var curr = startOfCycle
        while (curr?.next != startOfCycle) {
            curr = curr?.next
        }
        println("The cycle broken for the node with data: ${curr?.data} whose next data was ${curr?.next?.data}")
        curr?.next = null
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
            curr = curr?.next
            index++
        }
        return if (curr?.data == data) {
            index
        } else {
            null
        }
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
    val sll = SinglyLinkedListWithoutTail<Int>()
    println("printList: " + sll.printList())
    sll.printList()
    println("pushFront 10: ${sll.pushFront(10)}")
    println("printList after pushFront 10: ${sll.printList()}")
    sll.printList()
    println("pushFront 20: ${sll.pushFront(20)}")
    println("printList after pushFront 20: ${sll.printList()}")
    sll.printList()
    println("pushFront 30: ${sll.pushFront(30)}")
    println("printList after pushFront 30: ${sll.printList()}")
    sll.printList()
    println("pushFront 40: ${sll.pushFront(40)}")
    println("printList after pushFront 40: ${sll.printList()}")
    sll.printList()
    println("topFront: " + sll.topFront())
    println("printList after topFront: ${sll.printList()}")
    sll.printList()
    println("popFront: " + sll.popFront())
    println("printList after popFront: ${sll.printList()}")
    sll.printList()
    println("pushBack 50: ${sll.pushBack(50)}")
    println("printList after pushBack 50: ${sll.printList()}")
    sll.printList()
    println("pushBack 60: ${sll.pushBack(60)}")
    println("printList after pushBack 60: ${sll.printList()}")
    sll.printList()
    println("pushBack 70: ${sll.pushBack(70)}")
    println("printList after pushBack 70: ${sll.printList()}")
    sll.printList()
    println("getIndexOf 50: ${sll.getIndexOf(50)}")
    println("printList after getIndexOf 50: ${sll.printList()}")
    sll.printList()
    println("getIndexOf 100: ${sll.getIndexOf(100)}")
    println("printList after getIndexOf 100: ${sll.printList()}")
    sll.printList()
    println("Middle Node Data: ${sll.findMiddleNode()?.data}")
    println("reverse: Break Cycle: True: ${sll.reverse()}")
    println("printList after reverse: ${sll.printList()}")
    sll.printList()
    println("topBack: " + sll.topBack())
    println("printList after topBack: ${sll.printList()}")
    sll.printList()
    println("popBack: " + sll.popBack())
    println("printList after popBack: ${sll.printList()}")
    sll.printList()
    println("popBack: " + sll.popBack())
    println("printList after popBack: ${sll.printList()}")
    sll.printList()
    println("insertAt: 3: data: 100 ${sll.addItemAtIndex(3, 100)}")
    println("printList after insertAt: 3, data 100: ${sll.printList()}")
    sll.printList()
    println("getItemAtIndex 3: " + sll.getItemAtIndex(3))
    sll.printList()
    println("removeItemAtIndex 3: " + sll.removeItemAtIndex(3))
    println("printList after removeItemAtIndex 3: ${sll.printList()}")
    sll.printList()
    println("setReplaceAtIndex 3:, Data: 90, ${sll.setReplace(3, 90)}")
    println("printList after setReplaceAtIndex 3:, Data: 90: ${sll.printList()}")
    sll.printList()
    println("getItemAtIndex 3: " + sll.getItemAtIndex(3))
    sll.printList()
    println("contains 90? " + sll.contains(90))
    println("contains 100? " + sll.contains(100))
    println("size: " + sll.size())
    println("createCycleAtIndex: 3: ${sll.createCycle(3)}")
    println("printList after createCycleAtIndex 3: ${sll.printList()}")
    sll.printList()
    println("hasCycle: ${sll.hasCycle()}")
    println("reverse: Break Cycle: false: ${sll.reverse(false)}")
    println("printList after reverse: ${sll.printList()}")
    sll.printList()
    println("findStartCycle: ${sll.findStartCycle()}")
    println("breakCycle: ${sll.breakCycle()}")
    println("hasCycle: ${sll.hasCycle()}")
    println("printList after breakCycle: ${sll.printList()}")
    println("clear: ${sll.clear()}")
    println("size: " + sll.size() + " :isEmpty?: " + sll.isEmpty())

    val listOne = SinglyLinkedListWithoutTail<Int>()
    listOne.pushBack(1)
    listOne.pushBack(3)
    listOne.pushBack(5)
    listOne.pushBack(7)
    listOne.pushBack(9)
    listOne.pushBack(11)

    val listTwo = SinglyLinkedListWithoutTail<Int>()
    listTwo.pushBack(2)
    listTwo.pushBack(4)
    listTwo.pushBack(6)
    listTwo.pushBack(8)
    listTwo.pushBack(10)

    val list = SinglyLinkedListWithoutTail<Int>()

    println("Merge Lists: ${list.mergeTwoLinkedLists(listOne, listTwo).printList()}")
}