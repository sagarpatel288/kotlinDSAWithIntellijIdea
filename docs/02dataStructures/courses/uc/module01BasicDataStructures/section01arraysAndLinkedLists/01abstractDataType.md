# ADT - Abstract Data Type

<!-- TOC -->
* [ADT - Abstract Data Type](#adt---abstract-data-type)
  * [What is an abstract data type (ADT)?](#what-is-an-abstract-data-type-adt)
    * [ADT vs. Data Structure](#adt-vs-data-structure)
    * [Analogy in Programming Languages](#analogy-in-programming-languages)
    * [Practical Example](#practical-example)
    * [Why are ADTs Important?](#why-are-adts-important)
<!-- TOC -->

## What is an abstract data type (ADT)?

* ADT provides information about what a particular data structure does (behavior/contract) in a concise way without revealing the information about the "how" part.
* An Abstract Data Type (ADT) is a **mathematical model** for a data type, defined by its behavior from the point of view of a user. It specifies:
1.  A collection of **values** (the data).
2.  A set of **operations** on that data (the behavior or contract).

Crucially, an ADT describes **what** a data type does, without specifying **how** it does it. The implementation details are hidden from the user, a principle known as **data abstraction** or **information hiding**.

---

### ADT vs. Data Structure

It's important to distinguish between an ADT and a data structure:

*   **Abstract Data Type (ADT):** This is the theoretical concept (a mathematical model), the blueprint. It defines (represents, shares) the contract—the operations and their expected outcomes. For example, a `List` is an ADT. Its contract includes operations like `add`, `remove`, `get`, and `size`.

*   **Data Structure:** This is the concrete implementation of an ADT. It's the "how." For the `List` ADT, the data structure could be:
    *   An `ArrayList` (using a dynamic array).
    *   A `LinkedList` (using nodes and pointers).

Both `ArrayList` and `LinkedList` are valid data structures that implement the `List` ADT. A developer using a `List` doesn't need to know which underlying structure is being used; they only need to rely on the defined operations.

### Analogy in Programming Languages

In object-oriented programming, this concept is often realized through **interfaces** or **abstract classes**.

*   An **interface** in Kotlin or Java defines the ADT. It lists the methods (the operations) that a class must implement.
*   A **concrete class** that `implements` the interface provides the data structure (the implementation).

### Practical Example

```kotlin
// The ADT (The "what")
interface List<T> {
    fun add(element: T)
    fun get(index: Int): T
    fun removeAt(index: Int)
    fun size(): Int
}

// A concrete data structure (The "how")
class ArrayList<T> : List<T> {
    // ... implementation using a dynamic array ...
    override fun add(element: T) { /* ... */ }
    override fun get(index: Int): T { TODO("Not yet implemented") }
    override fun removeAt(index: Int) { /* ... */ }
    override fun size(): Int { TODO("Not yet implemented") }
}

// Another concrete data structure (Another "how")
class LinkedList<T> : List<T> {
    // ... implementation using nodes and pointers ...
    override fun add(element: T) { /* ... */ }
    override fun get(index: Int): T { TODO("Not yet implemented") }
    override fun removeAt(index: Int) { /* ... */ }
    override fun size(): Int { TODO("Not yet implemented") }
}
```

### Why are ADTs Important?

*   **Encapsulation:** They hide complex implementation details behind a simple interface.
*   **Modularity & Flexibility:** The underlying data structure of an ADT can be swapped out (e.g., changing from an `ArrayList` to a `LinkedList`) without changing the client code that uses it, as long as the contract (interface) remains the same. This is crucial for performance tuning and maintenance.
*   **Reusability:** ADTs provide well-defined, reusable components for building more complex systems.