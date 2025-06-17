package coursera.ucSanDiego.course02dataStructures.module01.video02LinkedLists

import coursera.ucSanDiego.course02dataStructures.module01.video02LinkedLists.LearnSinglyLinkedListWithTail.*

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
            val popBack = tail
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
            size--
            return popBack?.data
        }

        private fun getIndexOutOfBoundsExceptionMessage(index: Int) = "Index is $index, and size is $size"

        fun nodeAt(index: Int): Node<T>? {
            require(index in 0..<size) {
                IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            var curr = head
            repeat(index) {
                curr = curr?.next
            }
            return curr
        }

        fun getItemDataAtIndex(index: Int): T? {
            require(index in 0..<size) {
                IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            if (index == 0) {
                return topFront()
            }
            if (index == size - 1) {
                return topBack()
            }
            val curr = nodeAt(index)
            return curr?.data
        }

        fun setReplaceItemDataAtIndex(index: Int, data: T?) {
            require(index in 0..<size) {
                IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            val curr = nodeAt(index)
            curr?.data = data
        }

        fun addInsertAtIndex(index: Int, data: T?) {
            require(index in 0..size) {
                IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
            }
            if (index == 0) {
                return pushFront(data)
            }
            if (index == size) {
                return pushBack(data)
            }
            val curr = nodeAt(index - 1)
            curr?.next = Node(data, curr?.next)
            size++
        }

        fun removeItemAtIndex(index: Int): T? {
            require(index in 0..<size) {
                IndexOutOfBoundsException(getIndexOutOfBoundsExceptionMessage(index))
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

        fun reverse(breakCycle: Boolean = true) {
            if (isEmpty()) {
                println("The list is empty!")
            }
            var prev: Node<T>? = null
            var curr = head
            tail = head
            val set = mutableSetOf<Node<T>?>()
            val startOfCycle = findStartCycle()
            while (curr != null) {
                if (set.contains(curr)) {
                    println("Cycle detected from the node data: ${curr.data}")
                    break
                }
                set.add(curr)
                val next = curr.next
                curr.next = prev
                prev = curr
                curr = next
            }
            head = prev
            println("breakCycle: $breakCycle tailData: ${tail?.data} startOfCycleData: ${startOfCycle?.data}")
            if (!breakCycle && startOfCycle != null) {
                tail?.next = startOfCycle
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

        fun createCycle(cycleStartIndex: Int) {
            val target = nodeAt(cycleStartIndex)
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
                    //region For a perfect circular linked list, the last item (tail) points to the first item (head)
                    // In that case, the meeting point is the cycle entry point, and also the head.
                    // If head is the meeting point, we have already found the cycle start point, which is the head.
                    // For example, 90 -> 50 -> 60 -> 70 -> points back to the head, 90.
                    // If the meeting point is 90, and when we move the slow pointer to the head, it is also 90.
                    // So, 90, the head itself is a cycle start point.
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
    println("Middle node data is: ${sll.findMiddleNode()?.data}")
    println("reverse: breakCycle = true: ${sll.reverse()}")
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
    println("createCycleAtIndex: 1: ${sll.createCycle(1)}")
    println("printList after createCycleAtIndex 3: ${sll.printList()}")
    println("hasCycle: ${sll.hasCycle()}")
    println("reverse: breakCycle = false: ${sll.reverse(false)}")
    println("printList after reverse: ${sll.printList()}")
    println("findStartCycle: ${sll.findStartCycle()}")
    println("breakCycle: ${sll.breakCycle()}")
    println("hasCycle: ${sll.hasCycle()}")
    println("printList after breakCycle: ${sll.printList()}")
    println("clear: ${sll.clear()}")
    println("size: " + sll.size() + " :isEmpty?: " + sll.isEmpty())
}