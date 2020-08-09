package ch05.par1

fun main() {
    val alice = Person("Alice", 21)
    val getAge = Person::age
    println(getAge(alice))
    run(::salute)

    val createPerson = ::Person
    val bob = createPerson("Bob", 31)
    println(bob)

    // можно использовать ссылку на метод конкретного экземпляра класса
    val bobAge = bob::age
    println(bobAge())
}

fun salute() = println("Salute!")