package courses.ucSanD.course02dataStructures.module01.section02stacksAndQueues.video01Stack

class StackUsingLinkedList<T>() {
    private data class Node<T>(var value: T, var next: Node<T>?)

    /**
     * We cannot use `public` property [head] that exposes the `private` class [Node].
     * Also, we don't want to set [head] externally.
     * We don't want to expose or access the [head] from the outside.
     * However, we change the value of the [head] in various operations (functions, actions),
     * such as [push], [pop], etc.
     * It does not depend on any other property, but it depends on other actions (functions).
     * All these characteristics suggest that it should be a `private var` property.
     */
    private var head: Node<T>? = null

    /**
     * We don't want to set [size] from outside.
     * But we want to access it from outside.
     *
     * It does not depend on any other property.
     * But it depends on various actions such as [push], [pop], etc.
     *
     * All these characteristics suggest that it should be a `var` property, with `private set`.
     */
    var size = 0
        private set

    /**
     * We want to access [isEmpty] from the outside,
     * but we don't want to set [isEmpty] from the outside.
     *
     * [isEmpty] directly depends on the internal property [head],
     * and it does not directly depend on any other actions (functions).
     *
     * All these characteristics suggest that [isEmpty] should be a `val` and computed property.
     */
    val isEmpty: Boolean
        get() = head == null

    /**
     * Time complexity is O(1), because the operation is constant, and does not depend on size or input.
     * Space complexity is also O(1), because we don't use any additional memory.
     */
    fun push(value: T) {
        head = Node(value, head)
        size++
    }

    /**
     * Time complexity is O(1), because the operation does not depend on size or input.
     * Hence, the operation is constant.
     *
     * Space complexity is also O(1). Because we do not use additional memory here.
     */
    fun top(): T? {
        if (isEmpty) throw NoSuchElementException("The stack is empty!")
        return head?.value
    }

    /**
     * Time complexity is O(1), because the operation does not depend on size or input.
     * Hence, the time complexity is constant.
     *
     * Space complexity is O(1), because we use a single variable, and it does not depend on the size or input.
     * Hence, the space complexity is also constant.
     */
    fun pop(): T? {
        val top = top()
        head = head?.next
        size--
        return top
    }

    /**
     * Time complexity is O(n), because we have to travel through the entire stack to print each value.
     * Space complexity is also O(n), because each string represents each value.
     */
    override fun toString(): String = buildString {
        var curr = head
        while (curr != null) {
            append("value: ${curr.value} --> ")
            curr = curr.next
        }
        append("-- End Of The Stack --")
    }

}

fun main() {
    val sll = StackUsingLinkedList<Int>()
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
    println("Push 10: ${sll.push(10)}")
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
    println("Push 20: ${sll.push(20)}")
    println("Push 30: ${sll.push(30)}")
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
    println("print stack: $sll")
    println("top: ${sll.top()}")
    println("isEmpty: ${sll.isEmpty}")
    println("pop: ${sll.pop()}")
    println("print stack: $sll")
    println("size: ${sll.size}")
    println("pop: ${sll.pop()}")
    println("print stack: $sll")
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
    println("pop: ${sll.pop()}")
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
    println("pop: ${sll.pop()}")
    println("isEmpty: ${sll.isEmpty}")
    println("size: ${sll.size}")
}