package ch04.par2

class User8(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println(
                """
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent() // field - идентификатор поля, соответствующего свойству. чтение
            )
            field = value // запись нового значения
        }
}

fun main() {
    val user = User8("Alice")
    user.address = "Elesenhimerstrasse 47, 80687 Muenchen"
}