package ch05.par2

fun main() {
    val people = listOf(
        Person("Alice", 31),
        Person("Bob", 29),
        Person("Carol", 31)
    )
    /*
    * Функция groupBy группирует все элементы коллекции по указанному критерию и собирает результат в карту
    * ключи - все существующие значения критерия
    * значения - листы из элементов коллекции, удовлетворяющие этому значению критерия
    * */
    println(people.groupBy { it.age })
    /*
    * {
    *   31=[Person(name=Alice, age=31), Person(name=Carol, age=31)],
    *   29=[Person(name=Bob, age=29)]
    * }
    * */

    val words = listOf("collection", "map", "list", "array", "array list", "hash map", "hash set")
    println(words.groupBy(String::first))
    /*
    * {
    *   c=[collection],
    *   m=[map],
    *   l=[list],
    *   a=[array, array list],
    *   h=[hash map, hash set]
    * }
    * */
}