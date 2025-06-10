package coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists

import com.sun.tools.javac.Main
import coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists.LearnDoublyLinkedListWithTail.*

/**
 * DoublyLinkedListWithTail.kt
 *
 * A comprehensive implementation of a doubly linked list with tail pointer in Kotlin.
 *
 * Features:
 * - Standard doubly linked list operations (push, pop, insert, remove, etc.)
 * - Cycle detection and maintenance
 * - Multiple strategies for maintaining cycles during reversal:
 *      1. By indices
 *      2. By object references
 *      3. By tail-to-entry pattern
 * - Suitable for interview preparation and deep understanding of linked list edge cases.
 *
 * Author: [Sagar Patel]
 * Date: [Tuesday, 10/Jun/25]
 */
class LearnDoublyLinkedListWithTail() {

    sealed interface MaintainCycleBy {
        data object NONE: MaintainCycleBy
        // E.g., after reverse, the cycle will have the same start and end indices as it was before.
        // In this case, the objects (nodes) might be different from the before-reverse-structure.
        // For example, object:index = 70(0) <--> 60(1) <--> 50(2) <--> 90(3) --> points back to 60(1).
        // After reverse, 90(0) <--> 50(1) <--> 60(2) <--> 70(3) --> points back to 50(1).
        // Before and after the reverse, the cycle remains the same between index 1 and 3.
        data object INDICES: MaintainCycleBy
        // E.g., after reverse, the same objects (nodes) will be connected to maintain the cycle.
        // In this case, the indices might be changed, than the before-reverse-structure.
        // For example, object:index = 70(0) <--> 60(1) <--> 50(2) <--> 90(3) --> points back to 60(1).
        // After reverse, 90(0) <--> 50(1) <--> 60(2) --> points back to 90(0). So, we might lose the object, 70.
        // Before and after the reverse, the cycle remains the same between the objects 90 and 60.
        data object OBJECTS: MaintainCycleBy
        // E.g., after reverse, the cycle-end-object will be the tail node, and the cycle-entry-object (node) will
        // remain the same.
        // In this case, the cycle-entry-object (node) remains the same, but the cycle-end-object might be different
        // from the before-reverse-structure.
        // For example, object:index = 70(0) <--> 60(1) <--> 50(2) <--> 90(3) --> points back to 60(1).
        // After reverse, 90(0) <--> 50(1) <--> 60(2) <--> 70(3) --> points back to 60(2).
        // Before and after the reverse, the cycle between the cycle-start-object 60 and the tail remains the same.
        data object TAIL_TO_START_OBJECT: MaintainCycleBy
    }

    /**
     * Represents a single node in a doubly linked list.
     *
     * @param T The type of data stored in the node.
     * @property data The value held by this node.
     * @property prev Reference to the previous node.
     * @property next Reference to the next node.
     */
    class Node<T>(var data: T?, var prev: Node<T>?, var next: Node<T>?) {
        override fun toString(): String {
            return " data: $data --> "
        }
    }

    /**
     * Doubly linked list with a tail pointer and cycle support.
     *
     * Provides methods for insertion, deletion, cycle creation/detection, and reversal
     * while maintaining cycles in multiple ways.
     */
    class DoublyLinkedListWithTail<T>() {
        private var head: Node<T>? = null
        private var tail: Node<T>? = null
        private var size = 0

        fun isEmpty() = head == null

        fun size() = size

        fun pushFront(data: T?) {
            val newNode = Node(data, null, head)
            if (isEmpty()) {
                //region If the list is empty,
                // head = tail = newNode,
                // And the newNode's next and prev pointers will be (point to) null (nil).
                head = newNode
                tail = newNode
                //endregion
                size++
                return
            }
            //region If the list has only one element
            if (head?.next == null) {
                // newNode's next points to the existing head
                newNode.next = head
                // Existing head's prev points to the newNode
                head?.prev = newNode
                // The existing head becomes the tail
                tail = head
                // The newNode becomes the head
                head = newNode
                size++
                return
            }
            //endregion
            //region Otherwise,
            // newNode's next points to the existing node
            newNode.next = head
            // Existing head's prev points to the newNode
            head?.prev = newNode
            // The newNode becomes the head
            head = newNode
            size++
            //endregion
            return
        }

        fun topFront(): T? {
            return head?.data
        }

        fun popFront(): T? {
            // If the list is empty, we don't have anything to return, but null (nil).
            if (isEmpty()) return null
            val popFront = head
            //region If the list has only 1 item,
            // And if we take that item, the head and the tail become null.
            if (head?.next == null) {
                head = null
                tail = null
                size--
                return popFront?.data
            }
            //endregion
            //region Otherwise,
            // The next node of the head becomes the new head,
            // And we have to nullify the "prev" pointer of the new head as it might still point to the old "head."
            head = head?.next
            head?.prev = null
            size--
            //endregion
            return popFront?.data
        }

        fun pushBack(data: T?) {
            val newNode = Node(data, null, null)
            //region If the list is empty,
            // head = tail = newNode
            if (isEmpty()) {
                head = newNode
                tail = newNode
                size++
                return
            }
            //endregion
            //region If the list has only one item,
            // its existing next must be null.
            // So, we need to make it (head?.next or tail?.next) point the newNode.
            if (head?.next == null) {
                head?.next = newNode
                // The newNode's prev points to the head or tail (the only item).
                newNode.prev = head
                // The newNode becomes the tail.
                tail = newNode
                size++
                return
            }
            //endregion
            //region Otherwise,
            // The existing tail?.next points to the newNode instead of null,
            // and newNode.prev points to the existing tail,
            // and the newNode becomes the new tail (i.e., the tail shifts to the newly added node).
            tail?.next = newNode
            newNode.prev = tail
            tail = newNode
            size++
            //endregion
        }

        fun topBack(): T? {
            return tail?.data
        }

        fun popBack(): T? {
            if (isEmpty()) return null
            if (head?.next == null) {
                val popBack = head
                head = null
                tail = null
                size--
                return popBack?.data
            }
            val popBack = tail
            tail = tail?.prev
            tail?.next = null
            size--
            return popBack?.data
        }

        fun getIndexOutOfBoundsExceptionMessage(index: Int): String {
            return "Index is $index, and size is $size"
        }

        fun nodeAt(index: Int): Node<T>? {
            require(index in 0..<size) {
                getIndexOutOfBoundsExceptionMessage(index)
            }
            var curr = head
            repeat(index) {
                curr = curr?.next
            }
            return curr
        }

        fun insertAt(index: Int, data: T?) {
            require(index in 0..size) {
                getIndexOutOfBoundsExceptionMessage(index)
            }
            if (index == 0) {
                return pushFront(data)
            }
            if (index == size) {
                return pushBack(data)
            }
            val curr = nodeAt(index - 1)
            val newNode = Node(data, curr, curr?.next)
            curr?.next = newNode
            newNode.next?.prev = newNode
            size++
        }

        fun getItemAt(index: Int): T? {
            require(index in 0..<size) {
                getIndexOutOfBoundsExceptionMessage(index)
            }
            if (index == 0) {
                return topFront()
            }
            if (index == size - 1) {
                return topBack()
            }
            return nodeAt(index)?.data
        }

        fun removeAt(index: Int): T? {
            require(index in 0..<size) {
                getIndexOutOfBoundsExceptionMessage(index)
            }
            if (index == 0) {
                return popFront()
            }
            if (index == size - 1) {
                return popBack()
            }
            val curr = nodeAt(index - 1)
            val itemToRemove = curr?.next
            curr?.next = itemToRemove?.next
            itemToRemove?.next?.prev = curr
            size--
            return itemToRemove?.data
        }

        fun setAt(index: Int, data: T?) {
            require(index in 0..<size) {
                getIndexOutOfBoundsExceptionMessage(index)
            }
            nodeAt(index)?.data = data
        }

        fun getIndexOf(data: T?): Int? {
            if (isEmpty()) return null
            var index = 0
            var curr = head
            val set = mutableSetOf<Node<T>?>()
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle detected from node data ${curr.data}")
                    return null
                }
                set.add(curr)
                if (curr.data == data) {
                    return index
                }
                curr = curr.next
                index++
            }
            return null
        }

        fun findCycleIndices(): Pair<Int, Int>? {
            if (!hasCycle()) return null
            var slow = head
            var fast = head
            var cycleStartIndex = 0
            var cycleEndIndex = 0
            while (fast != null && fast.next != null) {
                slow = slow?.next
                fast = fast.next?.next
                if (slow == fast) {
                    slow = head
                    if (slow == fast) {
                        cycleStartIndex = 0
                        break
                    } else {
                        while (slow != fast) {
                            slow = slow?.next
                            fast = fast?.next
                            cycleStartIndex++
                            if (slow == fast) {
                                break
                            }
                        }
                    }
                }
            }
            var curr = slow
            while (curr?.next != slow) {
                curr = curr?.next
                cycleEndIndex++
            }
            println("Cycle: Start index $cycleStartIndex, End index: $cycleEndIndex")
            return cycleStartIndex to cycleEndIndex
        }

        fun findCycleObjects(): Pair<Node<T>?, Node<T>?>? {
            if (!hasCycle()) return null
            val cycleStartObject: Node<T>? = findStartCycleNode()
            var curr = cycleStartObject
            while (curr?.next != cycleStartObject) {
                curr = curr?.next
            }
            println("Cycle: start object data: ${cycleStartObject?.data}, end object data: ${curr?.data}")
            return cycleStartObject to curr
        }


        /**
         * Reverses the doubly linked list in-place.
         *
         * Algorithm: In-place reversal of a doubly linked list
         * 1. Traverse the list, swapping the `next` and `prev` pointers for each node.
         * 2. After traversal, update the head and tail pointers.
         * 3. If a cycle exists, restore the cycle according to the chosen strategy.
         * 4. Edge case: If the list is empty or has one node, do nothing.
         *
         * Cycle Maintenance Strategies:
         *
         * | Strategy               | What is Preserved?          | Example (Before -> After)         |
         * |------------------------|-----------------------------|-----------------------------------|
         * | NONE                   | No cycle                    | No cycle after reversal           |
         * | INDICES                | Same indices                | 3->1 before, 3->1 after           |
         * | OBJECTS                | Same node objects           | NodeA->NodeB before/after         |
         * | TAIL_TO_START_OBJECT   | Tail points to entry node   | Tail->Entry before/after          |
         *
         * Example of a doubly linked list with a cycle:
         *
         * [70] <-> [60] <-> [50] <-> [90]
         *                    ^         |
         *                    |_________|
         *
         * Option 1: After reversal (maintaining cycle by indices):
         *
         * [90] <-> [50] <-> [60] <-> [70]
         *                    ^         |
         *                    |_________|
         *
         * Option 2: After reversal (maintaining cycle by objects):
         *
         * [90] <-> [50] <-> [60] <-> [70]
         *  ^        |
         *  |________|
         *
         * Option 3: After reversal (maintaining cycle by tail-to-start-cycle-object):
         *
         * [90] <-> [50] <-> [60] <-> [70]
         *           ^                 |
         *           |_________________|
         *
         * Time Complexity: O(N)
         * Space Complexity: O(1)
         *
         * @param maintainCycleBy Determines how cycles are preserved during reversal.
         *      - NONE: No cycle is maintained.
         *      - INDICES: Preserve cycle between original indices.
         *      - OBJECTS: Preserve cycle between original node objects.
         *      - TAIL_TO_START_OBJECT: Tail points to the cycle's entry node.
         */
        fun reverse(maintainCycleBy: MaintainCycleBy) {
            if (isEmpty() || head?.next == null) return
            // 1. Record cycle information as needed for restoration.
            val cycleIndices = if (maintainCycleBy == MaintainCycleBy.INDICES) {
                findCycleIndices()
            } else {
                null
            }
            val cycleObjects = if (maintainCycleBy == MaintainCycleBy.OBJECTS) {
                findCycleObjects()
            } else {
                null
            }
            val cycleStartObject = findStartCycleNode()
            println("DoublyLinkedListWithTail: :reverse: maintainCycleBy: $maintainCycleBy cycleIndices: ${cycleIndices?.first}, ${cycleIndices?.second} cycleObjects data: ${cycleObjects?.first?.data}, ${cycleObjects?.second?.data}")

            // 2. Reverse the list by swapping next and prev pointers.
            var prevNode: Node<T>? = null
            var curr = head
            tail = head
            var index = 0
            val set = mutableSetOf<Node<T>?>()
            while (curr != null) {
                // Prevent infinite loop on cycle
                if (set.contains(curr)) {
                    println("Cycle detected at meeting point $index with data ${curr.data}")
                    break
                }
                set.add(curr)
                val next = curr.next
                curr.prev = curr.next
                curr.next = prevNode
                prevNode = curr
                curr = next
                index++
            }
            head = prevNode
            // 3. Restore the cycle as per the chosen strategy.
            if (maintainCycleBy != MaintainCycleBy.NONE) {
                when (maintainCycleBy) {
                    MaintainCycleBy.INDICES -> {
                        cycleIndices?.let { indices ->
                            createCycleBetweenIndices(indices.first, indices.second)
                            println("Cycle between indices ${indices.first} and ${indices.second}")
                        } ?: run {
                            println("The cycle indices are null!")
                        }
                    }
                    MaintainCycleBy.TAIL_TO_START_OBJECT -> {
                        cycleStartObject?.let { start ->
                            tail?.next = cycleStartObject
                            println("Cycle between the tail data ${tail?.data} whose next data is ${cycleStartObject.data}")
                        } ?: run {
                            println("The cycle start object is null!")
                        }
                    }
                    MaintainCycleBy.OBJECTS -> {
                        cycleObjects?.let { objects ->
                            createCycleBetweenObjects(cycleObjects.second, cycleObjects.first)
                            println("Cycle between the object data ${cycleObjects.first?.data} and ${cycleObjects.second?.data}")
                        } ?: run {
                            println("The cycle start and end objects are null!")
                        }
                    }
                    else -> {
                        println("MaintainCycleBy is $maintainCycleBy, hasCycle() ${hasCycle()}, size: $size")
                    }
                }
            }

        }

        fun createCycleBetweenIndices(startIndex: Int, endIndex: Int) {
            val cycleStart = nodeAt(startIndex)
            val cycleEnd = nodeAt(endIndex)
            cycleEnd?.next = cycleStart
            println("Cycle between: ${cycleEnd?.data} at index $endIndex and ${cycleStart?.data} at index $startIndex")
        }

        fun createCycleBetweenObjects(startObject: Node<T>?, endObject: Node<T>?) {
            var start = head
            var end = head
            var curr = head
            val set = mutableSetOf<Node<T>?>()
            while (curr != null) {
                if (set.contains(curr)) {
                    break
                }
                set.add(curr)
                when (curr) {
                    startObject -> {
                        start = curr
                        curr = curr.next
                    }
                    endObject -> {
                        end = curr
                        curr = curr.next
                    }
                    else -> {
                        curr = curr.next
                    }
                }
            }
            end?.next = start
            println("Cycle between object data: Start ${start?.data} and ${end?.data}")
        }

        fun toList(): List<T?> {
            val list = mutableListOf<T?>()
            if (isEmpty()) return list
            var curr = head
            val set = mutableSetOf<Node<T>>()
            var index = 0
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle detected at index $index, with data ${curr.data}")
                    break
                }
                set.add(curr)
                list.add(curr.data)
                curr = curr.next
                index++
            }
            return list
        }

        fun clear() {
            head = null
            tail = null
            size = 0
        }

        fun hasCycle(): Boolean {
            if (isEmpty()) return false
            var slow = head
            var fast = head
            var index = 0
            while (fast != null && fast.next != null) {
                slow = slow?.next
                fast = fast.next?.next
                index++
                if (slow == fast) {
                    println("Cycle detected (meeting point) at step $index, with data ${slow?.data}")
                    return true
                }
            }
            return false
        }

        fun createCycle(fromIndex: Int) {
            require(fromIndex in 0..<size) {
                getIndexOutOfBoundsExceptionMessage(fromIndex)
            }
            val target = nodeAt(fromIndex)
            tail?.next = target
            println("Tail is: ${tail?.data} whose next data is: ${tail?.next?.data}")
        }

        fun breakCycle(): Boolean {
            if (!hasCycle()) return false
            val startOfCycleNode = findStartCycleNode()
            var curr = startOfCycleNode
            while (curr?.next != startOfCycleNode) {
                curr = curr?.next
            }
            println("Cycle broken at node having data ${curr?.data} whose next data was ${curr?.next?.data}")
            curr?.next = null
            tail = curr
            return true
        }

        fun findStartCycleNode(): Node<T>? {
            if (isEmpty() || head?.next == null) return null
            var slow = head
            var fast = head
            var index = 0
            while (fast != null && fast.next != null) {
                slow = slow?.next
                fast = fast.next?.next
                if (slow == fast) {
                    slow = head
                    if (slow == fast) {
                        return slow
                    }
                    while (true) {
                        slow = slow?.next
                        fast = fast?.next
                        index++
                        if (slow == fast) {
                            return slow
                        }
                    }
                }
            }
            return null
        }

        fun contains(data: T?): Boolean {
            if (isEmpty()) return false
            var curr = head
            val set = mutableSetOf<Node<T>>()
            var index = 0
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle detected at index $index, with data ${curr.data}")
                    return false
                }
                set.add(curr)
                if (curr.data == data) {
                    return true
                }
                curr = curr.next
                index++
            }
            return false
        }

        fun printList() {
            val stringBuilder = StringBuilder()
            var curr = head
            val set = mutableSetOf<Node<T>>()
            var index = 0
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle meeting point at step $index, with data ${curr.data}")
                    break
                }
                set.add(curr)
                stringBuilder.append(curr.data).append(" --> ")
                curr = curr.next
                index++
            }
            stringBuilder.append("-- End Of The List --")
            println(stringBuilder)
        }
    }
}

fun main() {
    val dll = DoublyLinkedListWithTail<Int>()
    println("printList: " + dll.printList())
    println("pushFront 10: ${dll.pushFront(10)}")
    println("printList after pushFront 10: ${dll.printList()}")
    println("pushFront 20: ${dll.pushFront(20)}")
    println("printList after pushFront 20: ${dll.printList()}")
    println("pushFront 30: ${dll.pushFront(30)}")
    println("printList after pushFront 30: ${dll.printList()}")
    println("pushFront 40: ${dll.pushFront(40)}")
    println("printList after pushFront 40: ${dll.printList()}")
    println("topFront: " + dll.topFront())
    println("printList after topFront: ${dll.printList()}")
    println("popFront: " + dll.popFront())
    println("printList after popFront: ${dll.printList()}")
    println("pushBack 50: ${dll.pushBack(50)}")
    println("printList after pushBack 50: ${dll.printList()}")
    println("pushBack 60: ${dll.pushBack(60)}")
    println("printList after pushBack 60: ${dll.printList()}")
    println("pushBack 70: ${dll.pushBack(70)}")
    println("printList after pushBack 70: ${dll.printList()}")
    println("getIndexOf 50: ${dll.getIndexOf(50)}")
    println("printList after getIndexOf 50: ${dll.printList()}")
    println("getIndexOf 100: ${dll.getIndexOf(100)}")
    println("printList after getIndexOf 100: ${dll.printList()}")
    println("reverse: maintainCycle: false: ${dll.reverse(MaintainCycleBy.NONE)}")
    println("printList after reverse: ${dll.printList()}")
    println("topBack: " + dll.topBack())
    println("printList after topBack: ${dll.printList()}")
    println("popBack: " + dll.popBack())
    println("printList after popBack: ${dll.printList()}")
    println("popBack: " + dll.popBack())
    println("printList after popBack: ${dll.printList()}")
    println("insertAt: 3: data: 100 ${dll.insertAt(3, 100)}")
    println("printList after insertAt: 3, data 100: ${dll.printList()}")
    println("getItemAtIndex 3: " + dll.getItemAt(3))
    println("removeItemAtIndex 3: " + dll.removeAt(3))
    println("printList after removeItemAtIndex 3: ${dll.printList()}")
    println("setReplaceAtIndex 3:, Data: 90, ${dll.setAt(3, 90)}")
    println("printList after setReplaceAtIndex 3:, Data: 90: ${dll.printList()}")
    println("getItemAtIndex 3: " + dll.getItemAt(3))
    println("contains 90? " + dll.contains(90))
    println("contains 100? " + dll.contains(100))
    println("size: " + dll.size())
    println("createCycleAtIndex: 1: ${dll.createCycle(1)}")
    println("printList after createCycleAtIndex 3: ${dll.printList()}")
    println("hasCycle: ${dll.hasCycle()}")
    println("reverse: maintainCycleBy Objects: ${dll.reverse(MaintainCycleBy.OBJECTS)}")
    println("printList after reverse: ${dll.printList()}")
    println("reverse: maintainCycleBy Indices: ${dll.reverse(MaintainCycleBy.INDICES)}")
    println("printList after reverse: ${dll.printList()}")
    println("reverse: maintainCycleBy TailToStartObject: ${dll.reverse(MaintainCycleBy.TAIL_TO_START_OBJECT)}")
    println("printList after reverse: ${dll.printList()}")
    println("findStartCycle: ${dll.findStartCycleNode()}")
    println("breakCycle: ${dll.breakCycle()}")
    println("hasCycle: ${dll.hasCycle()}")
    println("printList after breakCycle: ${dll.printList()}")
    println("clear: ${dll.clear()}")
    println("size: " + dll.size() + " :isEmpty?: " + dll.isEmpty())
}