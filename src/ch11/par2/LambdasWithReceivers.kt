package ch11.par2

/*
* Для объявления функции, использующей лямбда-выражение с получателем, аргумент этой функции должен иметь тип
* функции-расширения для типа-получателя.
* например:
* */
fun buildAString(builderAction: StringBuilder.() -> Unit): String {
    /*
    * параметр builderAction объявлен как функция-расширение к некоторому типу. Этот тип и является получателем.
    * */
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}

fun main() {
    /*
    * Для использования функции, передаём в её параметры лямбда-выражение. Объявленный в выражении получатель
    * (тип StringBuilder) становится неявным получателем вызываемых функций: ссылкой this
    * можно вызвать как this.функция()
    * а можно this вообще опустить.
    * */
    val s = buildAString {
        this.append("Hello, ")
        append("World!")
    }
    println(s) // Hello, World!

    // Можно такой тип функции-расширения сохранить в переменную и использовать дальше:
    val appendExclamation: StringBuilder.() -> Unit = { /*this.*/append("!") }
    val stringBuilder = StringBuilder("Hi")
    // использование получает такой же синтаксис как и обычные функции-расширения
    stringBuilder.appendExclamation()
    println(stringBuilder)
    // Эту переменную можно передавать и как аргумент того же типа:
    println(buildAString(appendExclamation))
}

// с помощью библиотечной функции apply (тоже имеющей тип функции-расширения для использования с лямбда-выражениями с
// получателями) можно сократить до одной строки:
fun constructString(builderAction: StringBuilder.() -> Unit) = StringBuilder().apply(builderAction).toString()
