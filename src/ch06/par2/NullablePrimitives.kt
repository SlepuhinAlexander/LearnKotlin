package ch06.par2

/*
* nullable простые типы Kotlin (например Int?) не могут быть представлены в Java как примитивы, поэтому всегда
* компилируются в обёртки.
* */

data class Person(val name: String, val age: Int? = null) {
    fun isOlderThan(other: Person) : Boolean? {
        if (age == null || other.age == null) return null
        return age > other.age
    }
}

fun main() {
    println(Person("Sam", 35).isOlderThan(Person("Amy", 42))) // false
    println(Person("Sam", 35).isOlderThan(Person("Jane"))) // null
}
