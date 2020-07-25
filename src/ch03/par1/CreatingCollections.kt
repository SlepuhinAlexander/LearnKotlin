package ch03.par1

val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty three")
val strings = listOf("first", "second", "fourteen")
val numbers = setOf(1, 14, 2)

fun main() {
    println(set.javaClass)
    println(set)
    println(list.javaClass)
    println(list)
    println(map.javaClass)
    println(map)
    println("strings.last()=${strings.lastOrNull()}")
    println("numbers.max()=${numbers.max()}")
}