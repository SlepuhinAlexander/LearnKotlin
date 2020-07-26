package ch04.par3

data class Client1(val name: String, val postalCode: Int) {
    // уже имплементирован
    // fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client1(name, postalCode)
}

fun main() {
    val bob = Client1("Bob", 973293)
    println(bob.copy(postalCode = 382555))
}