package ch06.par1

// оператор безопасного вызова '?.' совершит вызов и вернёт результат, если вызывающий объект не null,
// либо проигнорирует вызов и вернёт сразу null, если вызывающий объект null
fun printAllCaps(s: String?) = println(s?.toUpperCase())

class Employee(val name: String, val manager: Employee? = null)

// оператор безопасного вызова можно применять и к свойствам
fun managerName(employee: Employee) = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address? = null)
class Person(val name: String, val company: Company? = null)

// можно объединять несколько операторов безопасного вызова в одно выражение:
fun Person.countryName(): String {
    val country = this.company?.address?.country
    return if (country != null) country else "Unknown"
}

// оператор Элвис возвращает значение отличное от нуля (первый аргумент оператора)
// либо свой второй аргумент, если первый равен null
fun strLength(s: String?) = s?.length ?: 0

// функцию countryName тоже можно сделать компактнее с помощью Элвис-оператора
fun countryName1(p: Person) = p.company?.address?.country ?: "Unknown"

fun printShippingLabel(p: Person) {
    val address = p.company?.address ?: throw IllegalArgumentException("No address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city $country")
    }
}

fun main() {
    printAllCaps("abc") // ABC
    printAllCaps(null) // null

    val ceo = Employee("Da Boss")
    val developer = Employee("Bob Smith", ceo)
    println(managerName(ceo)) // null
    println(managerName(developer)) // Da Boss

    println(strLength("abc")) // 3
    println(strLength(null)) // 0

    val address = Address("Elsestr.47", 80687, "Munich", "Germany")
    val jetBrains = Company("JetBrains", address)
    val dmitry = Person("Dmitry", jetBrains)
    printShippingLabel(dmitry)
    /*
    * Elsestr.47
    * 80687 Munich Germany
    * */
    printShippingLabel(Person("Alex"))
    // Exception in thread "main" java.lang.IllegalArgumentException: No address
}
