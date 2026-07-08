package courses.uc.course02dataStructures.module04hashTables

import java.util.LinkedList

class StringHashingWithChain(private val m: Int) {
    // It has been explicitly informed in the problem statement to take 1_000_000_007L only to match their answer!
    private val prime = 1_000_000_007L
    // It has been explicitly informed in the problem statement to take 263L only to match their answer!
    private val x = 263L
    private val hashTable = Array(m) { LinkedList<String>() }

    private fun hash(input: String): Int {
        var hash = 0L
        // It has been explicitly informed in the problem statement to follow right to left polynomial hashing.
        // So that we can match their answer!
        for (i in (input.lastIndex downTo 0)) {
            val code = input[i].code.toLong()
            hash = ((hash * x) + code) % prime
        }
        hash = (hash % prime + prime) % prime
        return ((hash % m + m) % m).toInt()
    }

    fun query(input: String): String {
        val answer = StringBuilder()
        val query = input.split(" ")
        when(query[0]) {
            "add" -> {
                val value = query[1]
                val index = hash(value)
                val chain = hashTable[index]
                if (!chain.contains(value)) {
                    chain.addFirst(value)
                }
            }
            "del" -> {
                val value = query[1]
                val index = hash(value)
                val chain = hashTable[index]
                chain.remove(value)
            }
            "find" -> {
                val value = query[1]
                val index = hash(value)
                val chain = hashTable[index]
                answer.append(if (chain.contains(value)) "yes\n" else "no\n")
            }
            "check" -> {
                val index = query[1].toInt()
                val chain = hashTable[index]
                answer.append("${chain.joinToString(" ")}\n")
            }
        }
        return answer.toString()
    }
}

fun main() {
    val m = readln().toInt()
    val solver = StringHashingWithChain(m)
    val totalQueries = readln().toInt()
    val answer = StringBuilder()
    repeat(totalQueries) {
        val query = readln()
        answer.append(solver.query(query))
    }
    println(answer)
}