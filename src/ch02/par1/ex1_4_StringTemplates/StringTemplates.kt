package ch02.par1.ex1_4_StringTemplates

fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!")

    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}!")
    }

    println("Hello, ${if (args.isNotEmpty()) args[0] else "Kotlin"}!")
}