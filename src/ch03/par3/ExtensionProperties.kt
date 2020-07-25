package ch03.par3

val String.lastChar: Char?
    get() = if (isEmpty()) null else get(length - 1)
var StringBuilder.lastChar: Char?
    get() = if (isEmpty()) null else get(length - 1)
    set(value) {
        if (isEmpty()) return else value?.let { setCharAt(length - 1, it) }
    }

fun main() {
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}