package ch02.par4

fun fizzBuzz(n: Int) = when {
    n % 15 == 0 -> "FizzBuzz "
    n % 3 == 0 -> "Fizz "
    n % 5 == 0 -> "Buzz "
    else -> "$n "
}

fun main() {
    println("\nfrom 1 to 100:")
    for (i in 1..100) print(fizzBuzz(i))
    println("\nfrom 1 to 100 (excluding) by 2:")
    for (i in 1 until 100 step 2) print(fizzBuzz(i))
    println("\nfrom 100 to 1 by 2:")
    for (i in 100 downTo 1 step 2) print(fizzBuzz(i))
}