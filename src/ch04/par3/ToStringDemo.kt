package ch04.par3

class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name='$name', postalCode=$postalCode)"

    override fun equals(other: Any?) = this === other ||
            (other is Client && name == other.name && postalCode == other.postalCode)

    override fun hashCode() = 31 * name.hashCode() + postalCode
}

fun main() {
    val client1 = Client("Alice", 342562)
    val client2 = Client("Alice", 342562)
    println("client1 = $client1")
    println("client2 = $client2")
    println("client1 == client2 : ${client1 == client2}") // сравнение объектов методом equals
    println("client1 === client2 : ${client1 === client2}") // сравнение указателей
    val processed = hashSetOf(client1)
    println(processed.contains(client2))
}