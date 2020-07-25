package ch03.par3

fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

fun main() {
    val list = arrayListOf("one", "two", "eight")
    println(list.joinToString(" "))
    println(list.join(" "))
}