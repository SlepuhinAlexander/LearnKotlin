package ch05.par1

data class Person(val name: String, val age: Int)

fun findTheOldest(people: Collection<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    findTheOldest(people)
    // the same
    println(people.maxBy { it.age })
    // the same
    println(people.maxBy(Person::age))
}