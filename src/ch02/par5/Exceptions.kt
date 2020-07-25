package ch02.par5

import java.util.*

fun main() {
    println(readNumber("Give a number: "))
}

fun readNumber(message: String): Int? {
    print(message)
    val scanner = Scanner(System.`in`)
    return try {
        val line = scanner.nextLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        scanner.close()
    }
}