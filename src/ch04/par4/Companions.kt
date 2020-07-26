package ch04.par4

class A {
    // один из объектов может быть компаньоном.
    // Вызов функций объекта-компаньона не требует указания его имени и похож на вызов статических методов в Java.
    // Объявление объекта-компаньона может не задавать его имя, тогда ему будет скрыто присвоено имя Companion.
    companion object {
        fun c() {
            println("A.c()")
        }
    }

    object B {
        fun c() {
            println("A.B.c()")
        }
    }
}

// Фабричные методы
class User private constructor(val nickname: String) {

    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))
    }
}

fun getFacebookName(accountId: Int) = "fb:$accountId"

class Person1(val name: String) {
    companion object Loader {
        fun fromJSON(json: String) = Person1(json.substringAfter("name: '").substringBefore("'"))
    }
}

// Реализация интерфейса в объекте-компаньоне
interface JSONFactory<T> {
    fun fromJSON(json: String): T
}

class Person2(val name: String) {
    companion object : JSONFactory<Person2> {
        override fun fromJSON(json: String) =
            Person2(json.substringAfter("name: '").substringBefore("'"))
    }
}

fun <T> loadFromJson(factory: JSONFactory<T>, json: String) = factory.fromJSON(json)

// объекты-компаньоны тоже можно расширять:
class Person3(val firstName: String, val lastName: String) {
    companion object {
        // пустой объект-компаньон, например
    }
}

fun Person3.Companion.fromJson(json: String) = Person3("first", "last")

fun main() {
    A.c()
    A.B.c()

    val userList = listOf(
        User.newFacebookUser(123),
        User.newSubscribingUser("alice@facebook.com")
    )
    userList.forEach { user: User -> println(user.nickname) }

    val person1 = Person1.Loader.fromJSON("{name: 'Alice'}")
    val person2 = Person1.fromJSON("{name: 'Brent'}")
    println("person1.name=${person1.name}")
    println("person2.name=${person2.name}")

    // Можно передать сразу содержащий класс: неявно вместо него будет использован объект-компаньон
    loadFromJson(Person2/*.Companion*/, "{name: 'Bob'}")

    println(Person3.fromJson("{}").firstName)
}
