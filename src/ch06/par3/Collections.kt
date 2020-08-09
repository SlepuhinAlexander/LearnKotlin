package ch06.par3

import java.io.BufferedReader
import java.io.StringReader

// коллекция может хранить nullable элементы:
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        result.add(line.toIntOrNull())
    }
    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    for (number in numbers) {
        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }
    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}

// refactored
fun addValidNumbers1(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    /* функция filterNotNull оставляет в коллекции только не-null значения (и соответственно меняет тип коллекции) */
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

/*
* Коллекции в котлин различаются на неизменяемые (immutable) и изменяемые (mutable).
* Базовый интерфейс коллекции в Kotlin: kotlin.collections.Collection
* Этот интерфейс предоставляет методы чтения, перебора коллекции, получения её элементов, проверки наличия элемента в
* коллекции, НО не имеет методов изменения коллекции, в том числе добавления элементов в коллекцию.
* Все коллекции по умолчанию -- неизменные.
* Чтобы изменять коллекцию, нужно использовать дочерний интерфейс kotlin.collections.MutableCollection : он
* дополнительно имеет методы добавления / удаления элементов и очистки коллекции.
* */
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (element in source) target.add(element)
}

/* коллекции не являются неизменными в прямом смысле: у ссылки на объект нет методов изменения коллекции, но сам
объект может быть изменён (например по другой ссылке) */
fun mutateCollection() {
    val mutableLink: MutableCollection<Int> = arrayListOf(1, 2, 3)
    val link: Collection<Int> = mutableLink
    println(link)
    // link.add(5) -- по ссылке на коллекцию её нельзя изменить
    mutableLink.add(5) // -- но по другой ссылке на эту коллекцию она могла быть изменена
    println(link)
    /*
    * в java-код, ожидающий коллекцию, можно передать как Collection, так и MutableCollection.
    * Компилятор kotlin никак не может отследить, будет ли коллекция изменена на стороне java.
    *
    * Аналогично, kotlin не может отследить, не добавит ли java-код в коллекцию null элементы (в то время как с точки
    * зрения kotlin коллекция останется из not null типов)
    * */
}

/*
* Коллекции полученные от java являются коллекциями с неизвестным статусом изменяемости: код на kotlin должен сам
* определить, работать с ней как с изменяемой или как с неизменной коллекцией.
* Аналогично, элементы коллекции будут иметь платформенный тип: неизвестно, nullable элементы коллекции или нет.
* */

fun main(args: Array<String>) {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    println(numbers)
    addValidNumbers(numbers)
    addValidNumbers1(numbers)

    val source: Collection<Int> = arrayListOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target) // [1, 3, 5, 7]

    mutateCollection()
    // [1, 2, 3]
    // [1, 2, 3, 5]
}
