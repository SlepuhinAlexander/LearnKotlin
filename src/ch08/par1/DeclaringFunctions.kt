package ch08.par1

// функция высшего порядка - это функция, которая принимает в качестве аргументов и/или возвращает другие функции
// то есть это функция, которая принимает и/или возвращает ссылку на функцию или лямбда-выражение.

fun main() {
    // явное определение типа функции
    val sum: (Int, Int) -> Int = { x, y -> x + y } // принимает два Int, возвращает Int
    val action: () -> Unit = { println(42) } // принимает ничего, возвращает ничего

    // если тип функции можно вывести, указывать его не обязательно:
    val diff = { x: Int, y: Int -> x - y }
    val hello = { println("hello world") }

    // nullable типы данных в функциях тоже допустимы, разумеется
    val nullable: () -> Int? = { null }
    // сам тип данных "функция" тоже может быть nullable:
    val nullableFun: ((Int, Int) -> Int)? = null

    // объявление функции может иметь именованные параметры:
    fun performRequest(
        url: String,
        callback: (code: Int, content: String) -> Unit
    ) {
        run { callback }
    }

    val url = "http://kotl.in"
    // именованные параметры не накладывают ограничения на использование функции.
    // используются для удобства чтения кода и для авто-дополнения.
    performRequest(url) { code, content -> println("$code: $content") }
    performRequest(url) { code, response -> if (code == 404 || response.isBlank()) println("not found") }


}
