package ch04.par4

import java.io.File

// объявление object объединяет в себе объявление класса и переменной этого класса в одном выражении
// object может иметь всё то же, что и класс: свойства, функции, блоки инициализации и т.д, кроме:
// object не может иметь конструкторов -- создание экземпляра объекта происходит здесь же, сразу же, а не где либо ещё.
object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) println("${person.name} calculated")
    }
}

fun main() {
    Payroll.allEmployees.addAll(listOf(Person("Bob"), Person("Alice")))
    Payroll.calculateSalary()
    println(Payroll.allEmployees.sortedWith(Person.NameComparator))

    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))
}

// объект может наследовать класс и/или реализовывать интерфейсы
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File) = o1.path.compareTo(o2.path, ignoreCase = true)
}

data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person) = o1.name.compareTo(o2.name)
    }
}
