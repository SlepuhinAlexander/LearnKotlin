package ch02.par1.ex1_2_Functions

// Тело функции состоящее из одного выражения может быть преобразовано в тело-выражение.
// Для таких функций тип возвращаемого значения можно вычислить автоматически: указание типа можно опустить
fun sum(a: Int, b: Int)/*: Int*/ = a + b

/*
fun sum(a: Int, b: Int): Int {
    return a + b
}
*/

fun max(a: Int, b: Int) = if (a > b) a else b

fun main() {
    println("3+2=${sum(3, 2)}")
    println("max(3,2)=${max(3, 2)}")
}