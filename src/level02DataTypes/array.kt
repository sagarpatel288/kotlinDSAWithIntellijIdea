package level02DataTypes

/**
* [Official Kotlin Array Link](https://kotlinlang.org/docs/arrays.html)
*/
fun main() {
    // An array has a fixed size.
    // For the static (known, hard-coded, pre-filled) elements, we can use `arrayOf`
    val staticArray = arrayOf(1, 5, 8, 9)

    // Let us print our `staticArray`
    for (i in staticArray.indices) {
        println("At index $i, the element of the static array is: ${staticArray[i]}")
    }

    // We can create an array of a fixed size pre-filled with `Null` values
    val nullArray = arrayOfNulls<Int>(5)

    // We can directly get the elements in a for loop
    for (i in nullArray) {
        // The `i` represents an element of the array. I.e., each element in the `nullArray`)
        println("The element of the nullArray is: $i")
    }

    // Or, we can iterate through indices
    for (i in nullArray.indices) {
        // The `i` represents an index starting from 0
        println("The index of the nullArray is: $i")
    }

    // We can create an array of a fixed size with a default value as well
    val defaultValueArray = Array(3) {"Default"}

    // Let us find out what the `defaultValueArray` has.
    for (i in defaultValueArray.indices) {
        println("The element in the default array is: ${defaultValueArray[i]} at index: $i")
    }

    // We can also use primitive-type arrays
    val primitiveTypeArray = IntArray(3)

    // Let us find out the default values of a primitive-type IntArray
    for (i in primitiveTypeArray.indices) {
        println("At index $i, the element of the primitiveIntTypeArray is: ${primitiveTypeArray[i]}")
    }

    // Let us also check the same for a `charArray`
    val primitiveTypeCharArray = CharArray(3)

    // Let us find out the default value of a primitive-type char array
    for (i in primitiveTypeCharArray.indices) {
        println("At index $i, the element of the primitiveTypeCharArray is: ${primitiveTypeCharArray[i]}")
    }
}