package ch08.par3

data class Person(val name: String, val age: Int)

/*
* ключевое слово return в лямбда-выражении производит выход ИЗ ФУНКЦИИ, в которой ВЫЗЫВАЕТСЯ ЭТО ЛЯМБДА-ВЫРАЖЕНИЕ, а не
* из самого выражения.
* Это справедливо только если функция с функцией-параметром является встраиваемой
* Это называется нелокальным возвратом: возврат идёт из внешнего блока, а не из блока, содержащего return
* */
fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

/*
* Лямбда-выражения поддерживают так же локальный возврат. Для его осуществления используются метки:
* */
fun lookForAlice1(people: List<Person>) {
    people.forEach label@{ if (it.name == "Alice") return@label }
    /* return@label вернёт выполнение кода к метке label@ (к следующей инструкции за ней) */
    println("Alice might be somewhere")
}

/*
* метки можно использовать и для выражения this для получения ссылки на отмеченный объект-приёмник лямбда-выражения
* */
fun oneTwoThree() {
    println(StringBuilder().apply sb@{
        listOf(1, 2, 3).apply {
            this@sb.append(this.toString())
        }
    })
}

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun main() {
    lookForAlice(people) // Found!
    lookForAlice1(people) // Alice might be somewhere
    oneTwoThree() // [1, 2, 3]
}