package ch02.par4

fun isDigit(c: Char) = c in '0'..'9'

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun recognize(c: Char) = when {
    isDigit(c) -> "It's a digit!"
    isLetter(c) -> "It's a letter!"
    else -> "I don't know"
}

fun main() {
    for (c in '0'..'z') println("$c: ${recognize(c)}")
}