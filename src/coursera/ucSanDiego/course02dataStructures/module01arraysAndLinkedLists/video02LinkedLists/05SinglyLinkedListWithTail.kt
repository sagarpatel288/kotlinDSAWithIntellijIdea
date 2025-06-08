package coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists

import coursera.ucSanDiego.course02dataStructures.module01arraysAndLinkedLists.video02LinkedLists.LearnSinglyLinkedListWithTail.*

/**
 * # Resources and references:
 *
 * [Singly Linked List](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/fa88641df26851c504c5a0b640fe9b5f853cef68/res/coursera/ucSanDiego/course02dataStructures/module01arraysAndLinkedLists/video02linkedLists)
 */
class LearnSinglyLinkedListWithTail() {

    class Node<T>(var data: T?, var next: Node<T>?) {
        override fun toString(): String {
            return " :$data --> "
        }
    }

    /**
     *
     *
     * # ----------------------- How to remember? -----------------------
     *
     * Use your 5 fingers of any hand or leg to visualize any operation of the Linked List.
     * For example, I use the fingers of my right hand to visualize any operation of the Linked List.
     * Such as:
     * [pushFront], [topFront], [popFront],
     * [pushBack], [topBack], [popBack],
     * [addInsertAtIndex], [getItemDataAtIndex], [setReplaceItemDataAtIndex], [removeItemAtIndex], etc.
     *
     */
    class SinglyLinkedListWithTail<T>() {
        private var head: Node<T>? = null
        private var tail: Node<T>? = null
        private var size = 0

        fun size() = size

        fun isEmpty() = head == null

        fun pushFront(data: T?) {
            head = Node(data, head)
            if (head?.next == null) {
                tail = head
            }
            size++
        }

        fun topFront(): T? {
            return head?.data
        }

        fun popFront(): T? {
            if (isEmpty()) return null
            val popFront = head
            if (head?.next == null) {
                head = null
                size--
                return popFront?.data
            }
            head = head?.next
            popFront?.next = null
            size--
            return popFront?.data
        }

        fun pushBack(data: T?) {
            val newNode = Node(data, null)
            if (isEmpty()) {
                head = newNode
                tail = newNode
                size++
                return
            }
            if (head?.next == null) {
                head?.next = newNode
                tail = newNode
                size++
                return
            }
            tail?.next = newNode
            tail = newNode
            size++
        }

        fun topBack(): T? {
            return tail?.data
        }

        fun popBack(): T? {
            if (isEmpty()) return null
            val popBack = tail //or head, it should be the same.
            if (head?.next == null) {
                head = null
                tail = null
                size--
                return popBack?.data
            }
            var curr = head
            while (curr?.next != tail) {
                curr = curr?.next
            }
            curr?.next = null
            tail = curr
            return popBack?.data
        }

        private fun getIndexOutOfBoundsExceptionMessage(index: Int) = "Index is $index, and size is $size"

        fun getItemDataAtIndex(index: Int): T? {
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

        fun setReplaceItemDataAtIndex(index: Int, data: T?) {
            if (index < 0 || index >= size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            var curr = head
            repeat(index) {
                curr = curr?.next
            }
            curr?.data = data
        }

        fun addInsertAtIndex(index: Int, data: T?) {
            if (index < 0 || index > size) {
                throw IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            if (index == 0) {
                return pushFront(data)
            }
            if (index == size - 1) {
                return pushBack(data)
            }
            var curr = head
            repeat(index - 1) {
                curr = curr?.next
            }
            curr?.next = Node(data, curr?.next)
            size++
        }

        fun removeItemAtIndex(index: Int): T? {
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
            size--
            return itemToRemove?.data
        }

        fun hasCycle(): Boolean {
            if (isEmpty() || head?.next == null) return false
            var slow = head
            var fast = head
            while (fast != null && fast?.next != null) {
                slow = slow?.next
                fast = fast.next?.next
                if (slow == fast) {
                    return true
                }
            }
            return false
        }

        fun reverse() {
            if (isEmpty()) {
                throw IllegalArgumentException("The list is empty!")
            }
            if (hasCycle()) {
                throw IllegalArgumentException("The list has cycle!")
            }
            var prev: Node<T>? = null
            var curr = head
            tail = head
            while (curr != null) {
                val next = curr.next
                curr.next = prev
                prev = curr
                curr = next
            }
            head = prev
        }

        fun toList(): List<T?> {
            val list = mutableListOf<T?>()
            var curr = head
            while (curr != null) {
                list.add(curr.data)
                curr = curr.next
            }
            return list
        }

        fun clear() {
            head = null
            tail = null
            size = 0
        }

        fun createCycle(fromIndex: Int) {
            var target = head
            repeat(fromIndex) {
                target = target?.next
            }
            tail?.next = target
        }

        fun findStartCycle(): Node<T>? {
            if (!hasCycle()) return null
            if (isEmpty()) return null
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

        fun printList() {
            if (isEmpty()) {
                return println("The list is empty!")
            }
            val stringBuilder = StringBuilder()
            var curr = head
            val set = mutableSetOf<Node<T>>()
            var index = 0
            while (curr != null) {
                if (set.contains(curr)) {
                    stringBuilder.append(" The cycle starts at index $index with data: ${curr.data}")
                    break
                }
                stringBuilder.append(curr.data).append(" --> ")
                set.add(curr)
                curr = curr.next
                index++
            }
            stringBuilder.append(" -- Null --")
            println(stringBuilder)
        }

        fun contains(data: T?): Boolean {
            if (isEmpty()) return false
            var curr = head
            val set = mutableSetOf<Node<T>>()
            while (curr != null) {
                if (set.contains(curr)) {
                    break
                }
                set.add(curr)
                if (curr.data == data) {
                    return true
                }
                curr = curr.next
            }
            return false
        }

        fun breakCycle() {
            if (isEmpty()) {
                return println("The list is empty!")
            }
            if (!hasCycle()) {
                return println("There is no cycle!")
            }
            val startOfCycle = findStartCycle()
            var curr = startOfCycle
            // TODO: This logic seems to be wrong! It prematurely breaks the list and loses the items.
            while (curr?.next != startOfCycle) {
                curr = curr?.next
            }
            println("Cycle broken: cycle end-data: ${curr?.data} whose next data was: ${curr?.next?.data}")
            curr?.next = null
            tail = curr
        }

        fun getFirstMatchedIndexOf(data: T?): Int? {
            if (isEmpty()) {
                return null
            }
            val set = mutableSetOf<Node<T>>()
            var curr = head
            var index = 0
            while (curr != null) {
                if (set.contains(curr)) {
                    println("The cycle starts at index $index, with data: ${curr.data}")
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
    }
}

fun main() {
    val sll = SinglyLinkedListWithTail<Int>()
    println("printList: " + sll.printList())
    println("pushFront 10: ${sll.pushFront(10)}")
    println("printList after pushFront 10: ${sll.printList()}")
    println("pushFront 20: ${sll.pushFront(20)}")
    println("printList after pushFront 20: ${sll.printList()}")
    println("pushFront 30: ${sll.pushFront(30)}")
    println("printList after pushFront 30: ${sll.printList()}")
    println("pushFront 40: ${sll.pushFront(40)}")
    println("printList after pushFront 40: ${sll.printList()}")
    println("topFront: " + sll.topFront())
    println("printList after topFront: ${sll.printList()}")
    println("popFront: " + sll.popFront())
    println("printList after popFront: ${sll.printList()}")
    println("pushBack 50: ${sll.pushBack(50)}")
    println("printList after pushBack 50: ${sll.printList()}")
    println("pushBack 60: ${sll.pushBack(60)}")
    println("printList after pushBack 60: ${sll.printList()}")
    println("pushBack 70: ${sll.pushBack(70)}")
    println("printList after pushBack 70: ${sll.printList()}")
    println("getIndexOf 50: ${sll.getFirstMatchedIndexOf(50)}")
    println("printList after getIndexOf 50: ${sll.printList()}")
    println("getIndexOf 100: ${sll.getFirstMatchedIndexOf(100)}")
    println("printList after getIndexOf 100: ${sll.printList()}")
    println("reverse: ${sll.reverse()}")
    println("printList after reverse: ${sll.printList()}")
    println("topBack: " + sll.topBack())
    println("printList after topBack: ${sll.printList()}")
    println("popBack: " + sll.popBack())
    println("printList after popBack: ${sll.printList()}")
    println("popBack: " + sll.popBack())
    println("printList after popBack: ${sll.printList()}")
    println("insertAt: 3: data: 100 ${sll.addInsertAtIndex(3, 100)}")
    println("printList after insertAt: 3, data 100: ${sll.printList()}")
    println("getItemAtIndex 3: " + sll.getItemDataAtIndex(3))
    println("removeItemAtIndex 3: " + sll.removeItemAtIndex(3))
    println("printList after removeItemAtIndex 3: ${sll.printList()}")
    println("setReplaceAtIndex 3:, Data: 90, ${sll.setReplaceItemDataAtIndex(3, 90)}")
    println("printList after setReplaceAtIndex 3:, Data: 90: ${sll.printList()}")
    println("getItemAtIndex 3: " + sll.getItemDataAtIndex(3))
    println("contains 90? " + sll.contains(90))
    println("contains 100? " + sll.contains(100))
    println("size: " + sll.size())
    println("createCycleAtIndex: 3: ${sll.createCycle(3)}")
    println("printList after createCycleAtIndex 3: ${sll.printList()}")
    println("hasCycle: ${sll.hasCycle()}")
    println("reverse: ${sll.reverse()}")
    println("printList after reverse: ${sll.printList()}")
    println("findStartCycle: ${sll.findStartCycle()}")
    println("breakCycle: ${sll.breakCycle()}")
    println("hasCycle: ${sll.hasCycle()}")
    println("printList after breakCycle: ${sll.printList()}")
    println("clear: ${sll.clear()}")
    println("size: " + sll.size() + " :isEmpty?: " + sll.isEmpty())
}