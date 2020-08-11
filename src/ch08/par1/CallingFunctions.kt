package ch08.par1

fun twoAndThree(operation: (Int, Int) -> Int) = println("The result is ${operation(2, 3)}")

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun main() {
    // вызов функции высшего порядка не отличается от вызова других функций: передаются аргументы,
    // в данном случае в качестве аргумента-функции передаётся явное лямбда-выражение.
    twoAndThree { a, b -> a + b } // The result is 5
    twoAndThree { a, b -> a * b } // The result is 6

    println("ab1c".filter { it in 'a'..'z' }) // abc
}

/*
* Для Java типы функций объявляются как интерфейсы: переменная с типом функции - это реализация интерфейса FunctionN с
* одним методом invoke
* Function0<R> - не принимает аргументов и возвращает тип R
* Function1<P1,R> - принимает 1 аргумент типа P1, и возвращает тип R
* и так далее.
* */
