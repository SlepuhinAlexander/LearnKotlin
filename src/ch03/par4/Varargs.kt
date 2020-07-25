package ch03.par4

fun main(args: Array<String>) {
    val list = listOf("args: ", *args)
    println(list)
    val map = mapOf(1 to "one")
    val (number, name) = 1 to "one"
}
