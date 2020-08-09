package ch05.par2

data class Author(val name: String)
data class Book(val title: String, val authors: List<Author>)

fun main() {
    val alice = Author("Alice")
    val bob = Author("Bob")
    val carol = Author("Carol")
    val books = listOf(Book("Java", listOf(alice, bob)), Book("Kotlin", listOf(bob, carol)))
    /*
    * Функция flatMap делает mapping каждого элемента коллекции в Iterable согласно переданному лямбда-выражению,
    * а затем коллекционирует в общий Iterable набор
    * результат удобно преобразовать к новой коллекции.
    * */
    println(books.flatMap { it.authors }.toList())
    // [Author(name=Alice), Author(name=Bob), Author(name=Bob), Author(name=Carol)]
    println(books.flatMap(Book::authors).toSet())
    // [Author(name=Alice), Author(name=Bob), Author(name=Carol)]

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() }) // [a, b, c, d, e, f]

    /*
    * Функция flatten собирает единую коллекцию из коллекции коллекций
    * */
    val words = listOf(listOf("abc", "bcd", "cde"), listOf("def", "feg"), listOf("egh"))
    println(words) // [[abc, bcd, cde], [def, feg], [egh]]
    println(words.flatten()) // [abc, bcd, cde, def, feg, egh]
}