package ch07.par4

import ch07.par1.Point

/*
* Мультидекларации (destructuring declarations) позволяют распаковать единое составное значение и использовать его для
* инициализации нескольких переменных
* val (a, b) = p -> val a = p.component1(); val b = p.component2()
* */

/*
* для дата-классов функции componentN сгенерированы компилятором автоматически.
* Но можно их определить и для класса не являющегося классом данных
* */
class Point1(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

/*
* мультидекларации удобны для возвращения нескольких значений из функции: достаточно объединить эти значения в одном
* классе данных и вернуть его как результат работы функции.
* с помощью мультидекларации будет легко распаковать каждый из результатов.
* */
data class NameComponents(val name: String, val extension: String)

fun splitFileName(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

fun main() {
    val p = Point(10, 20)
    val (a, b) = p
    println(a) // 10
    println(b) // 20
    /*
    * вызов мультидекларации равносилен последовательному вызову функций componentN() у присваиваемого объекта.
    * в дата-классах компилятор автоматически сгенерирует функции component1() .. componentN() для каждого компонента
    * дата-класса.
    * */
    println(p.component1()) // 10

    val (name, ext) = splitFileName("Multideclarations.kt")
    println(name) // Multideclarations
    println(ext) // kt

    /*
    * Функции component1 .. component5 реализованы в массивах и коллекциях
    * */
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val array = arrayOf(10, 11, 12, 13, 14)
    println(list.component5()) // 5
    println(array.component4()) // 14

    /*
    * Для ещё более простого возврата нескольких значений из фукнции можно использовать библиотечные классы Pair и
    * Triple
    * */

    /*
    * мультидекларации можно использовать и в объявлении переменных цикла. Например, для обхода словаря:
    * */
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    for ((key, value) in map) println("$key -> $value")
    /*
    * Oracle -> Java
    * JetBrains -> Kotlin
    * */
    /*
    * Фактически этот код транслируется в:
    * for (entry in map.entries) {
    *   val key = entry.component1()
    *   val value = entry.component2()
    *   println("$key -> $value")
    * }
    *
    *
    * */
}
