package level40Module4AlgorithmExercise

import kotlin.random.Random

fun main() {

    fun swapElements(input: IntArray, positionOne: Int, positionTwo: Int) {
        val temp = input[positionOne]
        input[positionOne] = input[positionTwo]
        input[positionTwo] = temp
    }

    /**
     * Beautiful explanation:
     * [Visual presentation using cards](https://youtu.be/9pdkbqGwUhs?si=OOJVvFFfhA6_3I3w)
     *
     * Assume that we have 6 cards of 3 colors. Blue, White, and Red.
     * Our job is to arrange the cards in such an order that all the blue cards come first, followed by white cards,
     * followed by red cards.
     *
     * Now,
     * We take 3 markers.
     * The first marker, known as the blue marker, indicates the end, the last position of the color blue.
     * All the cards to the left of the blue marker should be of color blue.
     * The middle marker, known as the white marker, indicates the beginning of the color white.
     * It can also indicate the first unknown value (card) or the current position of an iteration.
     * The last marker, known as the red marker, indicates the beginning of the color red.
     * All the cards to the right of the red marker should be of color red.
     *
     * The initial array is: [unknown, unknown, unknown, unknown, unknown, unknown].
     * We put the first and the middle or current marker at the beginning, at index 0 and the red marker in the end, at index 5.
     * So, the blue marker is at index 0.
     * The white marker is at index 0.
     * The red marker is at index 5.
     *
     * All the cards are upside down, unknown yet.
     * We start the iteration and reveal each card, one by one, with the iteration.
     * During the iteration, we reveal the first card.
     * We find that, the card is red, we swap the card with the last card and decrease the red marker.
     * So that we get right cards to the right of the red marker.
     * So, now the red marker is at index 4.
     * The blue marker is still at index 0.
     * The white marker is also at index 0.
     * And we can say that all the cards to the right of the red marker, are of color red.
     * Now, the array becomes: [unknown, unknown, unknown, unknown, unknown, red].
     * So, what we did here is, we put the known card to its known region, and we get an unknown card in exchange.
     * We need to reveal it.
     *
     * Now, let us say we reveal the first unknown card at index 0 again, and we find that it is red, again.
     * So, we swap it with the current position of the red marker, which is at index 4.
     * We decrease the red marker.
     * So, now the red marker is at index 3.
     * The blue marker is still at index 0.
     * The white marker is also at index 0.
     * And the array is: [unknown, unknown, unknown, unknown, red, red].
     *
     * We again reveal the first unknown card at index 0, and we find that it is a white card.
     * The white color is a middle color, a middle region in our expected sorting result.
     * We don't swap anything if we find that the element belongs to the middle region.
     * The reason is, if we arrange the first and the last regions, the middle region should be arranged on its own!
     * (Magic!)!.
     * However, the first card is not unknown now. Hence, we increase the middle or current marker.
     * So, the middle or current marker is at index 1 now.
     * The blue marker is still at index 0.
     * The red marker is at index 3.
     *
     * Now, we reveal the card at index 1, and we find that it is a blue card.
     * So, we swap it with the current position of the blue marker, which is at index 0.
     * It means, at index 0, we get a blue card, and at index 1, we have a white card.
     * We also increase the middle or current marker and the blue marker.
     * Because, the index 1 is not unknown anymore. We got the white card when we swapped it with the blue card.
     * And we get all the blue cards to the left of the blue marker.
     * So, the array becomes: [blue, white, unknown, unknown, red, red].
     * The blue marker is at index 1.
     * The middle or current marker is at index 2.
     * The red marker is at index 3.
     *
     * The middle or current marker is at index 2. We reveal the card. We find that it is a blue card.
     * So, we swap it with the current position of the blue marker, which is at index 1.
     * And we increase the middle or current marker because index 1 is not an unknown value anymore.
     * And we increase the blue marker as well so that we get all the blue cards to the left of the blue marker.
     * So, the array becomes: [blue, blue, white, unknown, red, red].
     * The blue marker is at index 2.
     * The middle or current marker is at index 3.
     * The red marker is at index 3.
     *
     * The middle or current marker is at index 3. We reveal the card. We find that it is a white card.
     * The white card belongs to the middle region and if we find an element that belongs to the middle region,
     * we don't swap it with any other element, because we don't need to.
     * Because, when we arrange only the first and the last region, the middle region should be arranged on its own!
     * (Magic!)!.
     * However, the card at index 3 is not unknown anymore. So, we increase the middle or current marker.
     * So, the array becomes: [blue, blue, white, white, red, red].
     * The blue marker is at index 2.
     * The red marker is at index 3.
     * The middle or current marker is at index ?. If you notice, once the middle or current marker crosses the red marker, there is no
     * unknown value left!! We have already finished the iteration and sorted the cards!! (Magic!)!.
     *
     * 1. We start with middle or current and blue markers at the first index (start) and the red marker at the last index (end).
     * 2. If we find that a card belongs to the last region, we swap it with the last card, we decrease the red marker.
     *    So that all the red cards stay to the right of the red marker.
     *    However, we do not increase the middle or current or the blue marker.
     *    Because the card we got in exchange, is yet unknown.
     * 3. If we find that a card belongs to the first region, we increase both the blue marker and the middle or current marker.
     *    Because, the card is not unknown anymore, and all the blue card should stay to the left of the blue marker.
     * 4. If we find that a card belongs to the middle region, we don't swap it with any other card.
     *    However, we increase the middle or current marker. Because, the card is not unknown anymore.
     * 5. When we find that the middle or current marker crosses the red marker, we should stop the iteration.
     */
    fun quickSort(input: IntArray, start: Int, end: Int) {
        if (start >= end) return
        println(": :quickSort: input: -----------------before random pivot: ${input.toList()} start: $start end: $end")
        val randomIndex = Random.nextInt(start, end + 1)
        swapElements(input, randomIndex, end)
        val pivot = input[end]
        var lessThanMarker = start
        var greaterThanMarker = end
        var currentPointer = start
        println(": :quickSort: input: -----------------after random pivot: ${input.toList()} pivot: $pivot lessThan: $lessThanMarker greaterThan: $greaterThanMarker")
        while (currentPointer <= greaterThanMarker) {
            println(": :quickSort: loop: input: ${input.toList()} current: $currentPointer greaterThan: $greaterThanMarker")
            when {
                input[currentPointer] < pivot -> {
                    println(": :quickSort: lessThan: $lessThanMarker input: ${input.toList()} currentPointer: $currentPointer")
                    if (currentPointer != lessThanMarker && input[currentPointer] != input[lessThanMarker]) {
                        swapElements(input, lessThanMarker, currentPointer)
                    }
                    lessThanMarker++
                    currentPointer++
                    println(": :quickSort: after swap: lessThan: $lessThanMarker currentPointer: $currentPointer input: ${input.toList()}")
                }
                input[currentPointer] > pivot -> {
                    println(": :quickSort: greaterThan: $greaterThanMarker input: ${input.toList()} currentPointer: $currentPointer")
                    if (input[currentPointer] != input[greaterThanMarker]) {
                        swapElements(input, currentPointer, greaterThanMarker)
                    }
                    greaterThanMarker--
                    println(": :quickSort: after swap: greaterThan: $greaterThanMarker currentPointer: $currentPointer input: ${input.toList()}")
                }
                else -> {
                    currentPointer++
                    println(": :quickSort: elseEqual: Now, current: $currentPointer")
                }
            }
        }
        quickSort(input, start, lessThanMarker - 1)
        quickSort(input, greaterThanMarker + 1, end)
    }

    fun getInput(): IntArray {
        return intArrayOf(4, 9, 4, 4, 2, 6, 4, 3, 8)
    }

    val input = getInput()
    quickSort(input, 0, input.lastIndex)
    println(": :main: 3-way quicksort partitioning with random pivot: ${input.toList()}")
}