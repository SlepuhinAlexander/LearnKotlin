package ch03.par2

import strings.*

fun main() {
    val list = listOf(1, 2, 3)
    println(list.joinToString("; ", "(", ")"))
    println(list.joinToString( separator = "; ", prefix = "[", postfix = "]"))
    println(list.joinToString())
    println(list.joinToString( separator = "; "))
    val s = "a sample string"
    val ss : String? = null
    println(s.lastChar())
    println(ss?.lastChar())
}