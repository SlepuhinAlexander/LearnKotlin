package ch02.par4

fun main() {
    val binaryReps = HashMap<Char, String>()
    for (c in 'A'..'Z') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) println("$letter : $binary")
    println()
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) println("$index: $element")
}