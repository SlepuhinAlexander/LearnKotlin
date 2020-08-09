package ch06.par1

// оператор as осуществляет явное приведение типов
// если его применить к null, то будет выброшено ClassCastException

// оператор as? - оператор безопасного приведения типа
// он пытается привести значение к заданному типу или возвращает null, если фактический тип отличается от ожидаемого.
class Person1(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson1 = other as? Person1 ?: return false
        /* если other null или не является типом Person1 то оператор as? вернёт null,
         * а элвис оператор при этом вернёт false
         **/
        return otherPerson1.firstName == firstName && otherPerson1.lastName == lastName
    }

    override fun hashCode() = firstName.hashCode() * 31 + lastName.hashCode()
}

fun main() {
    val p1 = Person1("Dmitry", "Jemerov")
    val p2 = Person1("Dmitry", "Jemerov")
    println(p1 == p2) // true
    println(p1.equals(42)) // false
}
