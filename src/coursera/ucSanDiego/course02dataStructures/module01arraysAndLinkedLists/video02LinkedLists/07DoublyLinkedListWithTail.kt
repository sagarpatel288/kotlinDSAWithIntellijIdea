package coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists

import coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists.LearnDoublyLinkedListWithTail.*

class LearnDoublyLinkedListWithTail() {

    class Node<T>(var data: T?, var prev: Node<T>?, var next: Node<T>?) {
        override fun toString(): String {
            return " data: $data --> "
        }
    }

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

        fun insertAt(index: Int, data: T?) {
            if (index < 0 || index > size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            if (index == 0) {
                return pushFront(data)
            }
            if (index == size) {
                return pushBack(data)
            }
            var curr = head
            repeat(index - 1) {
                curr = curr?.next
            }
            val newNode = Node(data, curr, curr?.next)
            curr?.next = newNode
            newNode.next?.prev = newNode
            size++
        }

        fun getItemAt(index: Int): T? {
            if (index < 0 || index >= size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
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

        fun removeAt(index: Int): T? {
            if (index < 0 || index >= size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            if (index == 0) {
                return popFront()
            }
            if (index == size - 1) {
                return popBack()
            }
            var curr = head
            repeat(index - 1) {
                curr = curr?.next
            }
            val itemToRemove = curr?.next
            curr?.next = itemToRemove?.next
            itemToRemove?.next?.prev = curr
            size--
            return itemToRemove?.data
        }

        fun setAt(index: Int, data: T?) {
            if (index < 0 || index >= size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            var curr = head
            repeat(index) {
                curr = curr?.next
            }
            curr?.data = data
        }

        fun reverse() {
            if (isEmpty() || head?.next == null) return
            var prevNode: Node<T>? = null
            var curr = head
            tail = curr
            var index = 0
            val set = mutableSetOf<Node<T>?>()
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle detected at index $index with data ${curr.data}")
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
            if (fromIndex < 0 || fromIndex >= size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(fromIndex))
            }
            var target = head
            repeat(fromIndex) {
                target = target?.next
            }
            tail?.next = target
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
                    while (slow != fast) {
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
    val doublyLinkedList = DoublyLinkedListWithTail<Int>()
    println("pushFront 1 ${doublyLinkedList.pushFront(1)}}")
    println("pushFront 2 ${doublyLinkedList.pushFront(2)}}")
    println("pushFront 3 ${doublyLinkedList.pushFront(3)}}")
    println("printList: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("topFront: ${doublyLinkedList.topFront()}")
    println("reverse: ${doublyLinkedList.reverse()}")
    println("printList after reverse: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("popFront: ${doublyLinkedList.popFront()}")
    println("printList after popFront: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("pushBack 4: ${doublyLinkedList.pushBack(4)}")
    println("printList after pushBack 4: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("pushBack 5: ${doublyLinkedList.pushBack(5)}")
    println("printList after pushBack 5: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("topBack: ${doublyLinkedList.topBack()}")
    println("popBack: ${doublyLinkedList.popBack()}")
    println("printList after popBack: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("pushBack 6: ${doublyLinkedList.pushBack(6)}")
    println("printList after pushBack 6: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("getAt index 3: ${doublyLinkedList.getItemAt(3)}")
    println("setReplaceAt index 3 data 0: ${doublyLinkedList.setAt(3, 0)}")
    println("printList after setReplaceAt index 3: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("removeAt index 3: ${doublyLinkedList.removeAt(3)}")
    println("printList after removeAt index 3: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("addInsertAtIndex 3, 10: ${doublyLinkedList.insertAt(3, 10)}")
    println("printList after addInsertAtIndex 3, 10: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("reverse: ${doublyLinkedList.reverse()}")
    println("printList after reverse: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("toList: ${doublyLinkedList.toList()}")
    println("size: ${doublyLinkedList.size()}")
    println("isEmpty: ${doublyLinkedList.isEmpty()}")
    println("hasCycle: ${doublyLinkedList.hasCycle()}")
    println("pushBack 7: ${doublyLinkedList.pushBack(7)}")
    println("printList after pushBack 7: ${doublyLinkedList.printList()}")
    println("pushBack 8: ${doublyLinkedList.pushBack(8)}")
    println("printList after pushBack 8: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("pushBack 9: ${doublyLinkedList.pushBack(9)}")
    println("printList after pushBack 9: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("pushBack 10: ${doublyLinkedList.pushBack(10)}")
    println("printList after pushBack 10: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("createCycle at index 2: ${doublyLinkedList.createCycle(2)}")
    println("printList after createCycle: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("hasCycle: ${doublyLinkedList.hasCycle()}")
    println("findStartCycle: ${doublyLinkedList.findStartCycleNode()?.data}")
    println("breakCycle: ${doublyLinkedList.breakCycle()}")
    println("printList after breakCycle: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
    println("hasCycle: ${doublyLinkedList.hasCycle()}")
    println("contains 10: ${doublyLinkedList.contains(10)}")
    println("clear: ${doublyLinkedList.clear()}")
    println("printList after clear: ${doublyLinkedList.printList()}")
    doublyLinkedList.printList()
}