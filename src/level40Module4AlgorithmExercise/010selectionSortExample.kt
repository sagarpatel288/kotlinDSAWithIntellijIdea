package level40Module4AlgorithmExercise

fun main() {

    fun selectionSort(input: MutableList<Int>) {
        // Going through each index of the incoming list
        for (i in input.indices) {
            // Taking the current index and will compare it with the rest of the elements and indices.
            // `minIndex` means, an index that has the lower value element than it's successive indices.
            var minIndex = i

            // Comparing the element at the current index with the element of each index
            for (j in i + 1..< input.size) {
                // If we find that the element at the current index (i) is greater than the element at a particular index (j),
                // then we conclude that the index j has a smaller value element than the element at index i.
                // The key point here is, we compare input[j] with input[minIndex] instead of input[j] with input[i].
                if (input[j] < input[minIndex]) {
                    // We find that j is the `minIndex`, the index which has a lower value element than the index i.
                    minIndex = j
                }
            }

            // Get the element from the current index
            val oldValue = input[i]
            // Set the element at the current index from the element at the minIndex
            input[i] = input[minIndex]
            // Set the element of the current index at the index j.
            input[minIndex] = oldValue
        }
        println(input)
    }

    val inputArray = readln().split(" ").map {
        it.toInt()
    }.toMutableList()

    selectionSort(inputArray)
}