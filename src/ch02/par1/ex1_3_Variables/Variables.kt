package ch02.par1.ex1_3_Variables

import kotlin.random.Random

// Тип может быть определён из контекста.
const val question/*: String*/ = "The Ultimate Question of Life, The Universe, and Everything"

const val answer/*: Int */ = 42

fun doSomething() {
    // не инициализированная переменная обязана иметь указание типа
    val another: Double
    another = 33.0
    // val -- неизменяемое значение (ссылка), не может быть переопределено после первого присвоения
    // another = 11.0
    println("another=${another}")

    // var -- изменяемое значение (ссылка) = переменная
    var aVariable = 11
    aVariable = 22
    aVariable = 33
    println("aVariable=${aVariable}")
}

fun main() {
    println(question)
    println(answer)
    doSomething()
    println(multipleValInitialization())
}

// если гарантировано будет выполнена только одна из инициализаций, переменную val можно инициализировать несколькими
// способами
fun multipleValInitialization(): String {
    val message: String
    if (Random.nextBoolean()) message = "success" else message = "failed"
    return message
}