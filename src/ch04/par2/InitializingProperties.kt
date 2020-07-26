package ch04.par2

// Интерфейс может содержать объявление абстрактного свойства.
interface User6 {
    val nickname: String
    // сам интерфейс не имеет состояния: только реализующий класс может решить, будет ли он хранить значение как поле,
    // или только использовать методы доступа
}

class PrivateUser(override val nickname: String) : User6
/*
* override в параметре конструктора показывает что nickname реализует абстрактное свойство интерфейса.
* поскольку свойство отмечено val -- будет создано и поле для хранения значения nickname
* */

class SubscribingUser(val email: String) : User6 {
    override val nickname: String
        get() = email.substringBefore('@')
}
/*
* абстрактное свойство nickname реализуется через переопределение свойства, но не в конструкторе: следовательно, поле
* не создаётся, а только переопределяется метод доступа (в данном случае чтения)
* А сам класс получает ещё одно свойство в классическом понимании и использует его как ему необходимо: например, для
* вычисления nickname
* */

class FacebookUser(val accountId: Int) : User6 {
    override val nickname = getFacebookName(accountId)
}
/*
* свойство реализуется через присвоение значения напрямую. Поле для хранения свойства будет также создано
* */

fun getFacebookName(accountId: Int): String = accountId.toString()

fun main() {
    println(PrivateUser("test@kotlinlang.org").nickname) // test@kotlinlang.org
    println(SubscribingUser("test@kotlinlang.org").nickname) // test
}

/*
* абстрактное свойство email обязано быть переопределено в подклассах
* свойство с реализацией (без хранения состояния) nickname может быть просто унаследовано подклассами.
* */
interface User7 {
    val email: String
    val nickname: String
        get() = email.substringBefore('@')
}