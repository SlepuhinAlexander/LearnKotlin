package ch05.par1

fun printMessagesWithPrefix(prefix: String, messages: Collection<String>) {
    // Если лямбда-выражение объявлено в функции, то оно имеет доступ ко всем её параметрам и локальным переменным
    messages.forEach { println("$prefix $it") }
}

// В отличие от Java лямбда-выражения могут работать с НЕ-финальными переменными и могут изменять их.
fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        when {
            it.startsWith("4") -> clientErrors++
            it.startsWith("5") -> serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors serverErrors")
}

fun main() {
    val errors = listOf("403 Forbidden", "404 not found")
    printMessagesWithPrefix(messages = errors, prefix = "Error:")
    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCounts(responses)
}