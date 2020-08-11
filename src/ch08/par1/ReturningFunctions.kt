package ch08.par1

// Функции высшего порядка могут не только принимать функции в качестве аргументов, но и возвращать функции как
// результат своей работы.

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

// Объявление возвращаемого результата-функции аналогично объявлению аргумента-функции
fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double = when (delivery) {
    Delivery.STANDARD -> { order -> 1.2 * order.itemCount }
    Delivery.EXPEDITED -> { order -> 6 + 2.1 * order.itemCount }
}

data class Person(val firstName: String, val lastName: String, val phoneNumber: String?)

class ContactListFilters {

    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    // функция реализующая предикат фильтрации контактов по некоторому правилу.
    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix =
            { p: Person -> p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix) }
        if (!onlyWithPhoneNumber) return startsWithPrefix
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }

}

fun main() {
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("Shipping costs: ${calculator(Order(3))}") // Shipping costs: 12.3

    val contacts = listOf(
        Person("Dmitry", "Jemerov", "123-4567"),
        Person("Svetlana", "Isakova", null)
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "Dm"
        onlyWithPhoneNumber = true
    }
    println(contacts.filter(contactListFilters.getPredicate()))
    // [Person(firstName=Dmitry, lastName=Jemerov, phoneNumber=123-4567)]
}
