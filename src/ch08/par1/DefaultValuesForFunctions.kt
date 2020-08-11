package ch08.par1

// как и для любых других аргументов функций, для функций высшего порядке можно установить значение по умолчанию для
// аргумента, являющегося функцией
fun <T> Collection<T>.joinToString1(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // вводим новый аргумент-функцию (он отвечает за преобразование элемента коллекции к строке)
    // и устанавливаем ему значение по умолчанию
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

// можно оставить значение по умолчанию для аргумента-функции равным null
// но тогда вызывать такую функцию высшего порядка нужно безопасно: с проверкой на null
fun <T> Collection<T>.joinToString2(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        // безопасно вызываем функцию из аргумента transform,
        // и если она не задана, то используем некоторую реализацию по умолчанию.
        val elementToString = transform?.invoke(element) ?: element.toString()
        result.append(elementToString)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString1()) // Alpha, Beta
    println(letters.joinToString1 { it.toLowerCase() }) // alpha, beta
    println(letters.joinToString1(separator = "! ", postfix = "!", transform = { it.toUpperCase() })) // ALPHA! BETA!
}
