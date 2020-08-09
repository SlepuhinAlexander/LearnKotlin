package ch05.par2

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    val empty = listOf<Person>()
    /*
    * функция all проверяет все ли элементы коллекции удовлетворяют предикату
    * если коллекция пустая возвращает true
    * */
    println(people.all(canBeInClub27)) // false
    println(empty.all(canBeInClub27)) // true

    /*
    * функция any проверяет, есть ли хотя бы один элемент в коллекции, удовлетворяющий предикату
    * если коллекция пустая вернёт false
    * */
    println(people.any(canBeInClub27)) // true
    println(empty.any(canBeInClub27)) // false
    /*
    * функция count подсчитывает количество элементов удовлетворяющих предикату
    * */
    println(people.count(canBeInClub27)) // 1
    println(empty.count(canBeInClub27)) // 0
    /*
    * функция find возвращает первый элемент удовлетворяющий предикату, или null если таковой не найден
    * (например, если коллекция пустая)
    * findLast действует аналогично, только возвращает последний элемент удовлетворяющий предикату.
    * (применима для листов)
    * */
    println(people.find(canBeInClub27)) // Person(name=Alice, age=27)
    println(people.findLast(canBeInClub27)) // Person(name=Alice, age=27)
    println(empty.find(canBeInClub27)) // null
    /*
    * функция firstOrNull равносильна find
    * функция first аналогична, но если удовлетворяющий элемент не найден, - выбросит NoSuchElementException, но зато
    * никогда не вернёт null
    * */
    println(people.first(canBeInClub27)) // Person(name=Alice, age=27)
    println(people.last(canBeInClub27)) // Person(name=Alice, age=27)
}