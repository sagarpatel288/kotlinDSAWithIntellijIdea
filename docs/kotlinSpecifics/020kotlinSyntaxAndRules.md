## ToDO

* Copy all the notes of Kotlin lessons from the Google Doc to this file.

## Maths

* Modulo
* Finding the last digit
* Fibonacci
* LCM (The Least Common Multiplier) and GCD (Greatest Common Divisor or HCF - Highest Common Factor)
* Logarithms
* Range sum formula (prefixed sum array)
* Euclidean formula to find the distance between two points.
* Formulas of: Edit distance to match two strings
* Formulas of: Binary heap tree to get parent and children.
* Trigonometry (Shapes, area, etc.)
* Discrete maths 
* Combinatorial Game Theory 
* Statistics

## Functions

* Do you know that in kotlin, a function parameter is a `val` (immutable)? 

## Time Complexity

* Logarithmic
* Linear
* Quadratic
* Exponential
* Best case
* Average case
* Worst case

## Data Structures

* IntArray Vs. arrayOf Vs. ArrayList
* Is it possible to have: `Array<T>(val size = 0)`? Why?
* Array<Any?>
* arrayOfNulls
* List Vs. listOf Vs. MutableList 
* ArrayDequeue and operations
* Stack and operations
* Queue and operations
* ArrayDeque and operations
* PriorityQueue and operations

## Collections

* IntArray Vs. List<Int>
* How do we initialize `IntArray`?
* How do we initialize `List`?
* How to define and declare list of list (adjacent list)?
* Iterate using list.withIndex()
* Repeat
* Various idiomatic operators and operations on a collection.
* Collection.map
* Collection.joinToString(" ") Vs. Collection.joinToString { }
* Collection.take, Collection.joinToString, Collection.takeIf
* Adjacency list: List (size) { mutableListOf<Int>() }
* sorted() Vs. sortedBy { } Vs. sortedWith { } 
* sortedDescending() Vs. sortedByDescending { }
* map[key] always returns a nullable type even when it is `mutableMapOf<T, T>()`!
  * Check: [04dynamicDsuWithRank](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/8c1f537cd112f68e88224cd641f72c547fa61398/src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/04dynamicDsuWithRank.kt)
  * Local: [04dynamicDsuWithRank.kt](../../src/courses/uc/course02dataStructures/module03PriorityQueuesHeapsDisjointSets/programmingAssignment01/04dynamicDsuWithRank.kt)
*   

```markdown

| Situation                            | Recommended           |
| ------------------------------------ | --------------------- |
| Known data/content                   | `arrayOf(...)`        |
| Need an empty/default-sized 2D array | `Array(rows) { ... }` |
| Need a mutable character row         | `CharArray(...)`      |
| Need a mutable Boolean row           | `BooleanArray(...)`   |


```

* For example, suppose we have a fixed set of `Array<CharArray>`, then it would look like:

```kotlin

val grid = arrayOf(
    charArrayOf('1', '1', '0', '0', '0'),
  charArrayOf('1', '1', '0', '0', '0'),
  charArrayOf('0', '0', '1', '0', '0'),
  charArrayOf('0', '0', '0', '1', '1')
)

```

* Reference: [PreVisit and PostVisit DFS Timestamps Of An Undirected Graph]()

## Generics

* 

## String, Input, and Readers

* BufferedReader
* InputStreamReader
* System.`in`
* StringBuilder Vs. buildString { }
* How to read, compare, and process characters (various operations)?
* How to get the character code?
* Various operators and operations on a string
* String formats (Decimal, date, time, etc.)
* Do you know that `println(stringBuilder)` works. 
  * We don't have to do: `println(stringBuilder.toString())`!
* 

## Interfaces and other things

* Comparable Vs. Comparator
* Explain `compareTo`: Why and how does the order matter?
* What is the difference between `(a + b).compareTo(b + a)` and `(b + a).compareTo(a + b)`?
* How do we get ascending and descending order using `compareTo`?
* `compareTo` Vs. `compareValuesBy`

## Exceptions and Errors

* What is the differences between `IllegalStateException` and `IllegalArgumentException`? Which one to use when, why, and how?
* Similarly: `EmptyStackException` Vs. `NoSuchElementException`.
* Similarly: `StackOverFlowError` Vs. `EmptyStackException`.
* Similarly: What is the `RuntimeException`?

## Bitwise Operators

* 

## Util

* Random.nextInt(inclusive, exclusive)

## Idioms

* Lambda functions
* Scope functions
* Which data type does the repeat block take?
* What does the lambda of the repeat block provide?