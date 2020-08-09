package ch06.par1

/*
* типы данных, приходящие в котлин из java в общем случае воспринимаются как тип с неизвестной поддержкой null:
* может быть он nullable, а может быть и non-null
* программа должна явно разрешать эту неопределённость
*
* если есть объявление @Nullable String в Java, то в Kotlin это объявление будет представлено как String?
* если есть объявление @NotNull String в Java, то в Kotlin это объявление будет представлено как String
* в таких случаях неопределённости с поддержкой null - нет.
*
* А вот если аннотации не указаны, то тип Java становится ПЛАТФОРМЕННЫМ ТИПОМ для Kotlin
* Платформенный тип - это тип, для которого Kotlin не смог определить допустимость null
* Компилятор позволит работать с таким типом и как с nullable и как с non-null,
* ответственность перекладывается на программиста
*
* платформенные типы компилятор помечает нотацией '!', например String!
* нельзя объявить переменную платформенного типа в Kotlin - они могут только прийти из кода на Java
* но можно встретить платформенные типы, например, в сообщении об ошибке.
* */

fun yellAtSafe(person: Person2) {
    println((person.name ?: "Anyone").toUpperCase() + "!!!")
}

fun main(args: Array<String>) {
    yellAtSafe(Person2(null))
}
