package ch08.par3

/*
* Анонимная функция - другой способ оформления блока кода для передачи в другую функцию.
* Это лишь синтаксическое различие. Все правила и требования те же как и для лямбда-выражений, принимаемых функцией
* высшего порядка.
* */
fun lookForAlice2(people: List<Person>) {
    people.forEach(fun(person) {
        if (person.name == "Alice") return
        println("${person.name} is not Alice")
    })
}

fun main() {
    lookForAlice2(people)
    // Bob is not Alice
}
