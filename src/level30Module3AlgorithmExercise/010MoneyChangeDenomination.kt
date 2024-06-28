package level30Module3AlgorithmExercise

fun main() {

    fun getMinimumCoinsNeeded(money: Long): Long {
        if (money <= 4L) return money
        if (money == 5L || money == 10L) return 1
        var numberOfCoinsNeeded = 0L
        // numberOfCoinsNeeded = numberOfCoinsNeeded + (money / 10).
        // The quotient represents the number of coins needed for the given money.
        numberOfCoinsNeeded += (money / 10)
        // The remainder is money left.
        var moneyLeft = money % 10
        if (moneyLeft != 0L) {
            // numberOfCoinsNeeded = numberOfCoinsNeeded + (moneyLeft / 5)
            // The quotient represents the number of coins needed for the given money.
            numberOfCoinsNeeded += (moneyLeft / 5)
            // moneyLeft = moneyLeft % 5
            // The remainder is money left.
            moneyLeft %= 5
        }
        if (moneyLeft != 0L) {
            // numberOfCoinsNeeded = numberOfCoinsNeeded + (moneyLeft / 1)
            // => numberOfCoinsNeeded = numberOfCoinsNeeded + moneyLeft (because a/1 = a)
            numberOfCoinsNeeded += (moneyLeft)
        }
        return numberOfCoinsNeeded
    }

    val money = readln().toLong()
    println(getMinimumCoinsNeeded(money))
}