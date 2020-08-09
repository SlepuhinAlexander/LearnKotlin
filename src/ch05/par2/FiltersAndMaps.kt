package ch05.par2

data class Person(val name: String, val age: Int)

fun main() {
    val list = listOf(1, 2, 3, 4)
    /*
    * filter
    * Принимает на вход предикат и возвращает новую коллекцию в которой только отфильтрованные элементы
    * */
    println(list.filter { it % 2 == 0 }) // [2, 4]

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 }) // [Person(name=Bob, age=31)]

    /*
    * map
    * принимает на вход лямбда-выражение, конвертирующее элемент коллекции в какой-то другой объект
    * в результате возвращает коллекцию из новых (конвертированных) элементов
    * */
    println(list.map { it * it * 10 }) // [10, 40, 90, 160]
    println(people.map(Person::name)) // [Alice, Bob]

    // имена всех людей старше 30
    println(people.filter { it.age > 30 }.map(Person::name)) // [Bob]

    // имена самых взрослых людей
    val maxAge = people.maxBy(Person::age)?.age
    println(people.filter { it.age == maxAge }.map(Person::name)) // [Bob]

    val map = mapOf(0 to "zero", 1 to "one")
    println(map.mapKeys { it.key * 100 }.mapValues { (it.value + " hundred(s)").toUpperCase() })
    // {0=ZERO HUNDRED(S), 100=ONE HUNDRED(S)}
}