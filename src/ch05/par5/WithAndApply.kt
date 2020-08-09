package ch05.par5

fun main() {
    println(alphabet())
    /*
    * ABCDEFGHIJKLMNOPQRSTUVWXYZ
    * Now I know the alphabet!
    * */
    println(alphabet2())
    /*
    * ABCDEFGHIJKLMNOPQRSTUVWXYZ
    * Now I know the alphabet!
    * */
}

fun alphabet() =
    /*
    * Функция with позволяет применить несколько операций над одним и тем же объектом не повторяя его имени
    * Первый аргумент функции with -- получатель, методы которого будут использоваться.
    * Второй аргумент функции with -- лямбда-выражение, которое должно выполниться для получателя
    * Внутри лямбда-выражения ссылка this указывает на получателя
    * */
    with(StringBuilder()) {
        for (letter in 'A'..'Z') this.append(letter)
        // ссылку this можно опустить и использовать методы или свойства получателя напрямую.
        /*this.*/append('\n').append("Now I know the alphabet!")
        /*this.*/toString()
    }

fun alphabet2() =
    /*
    * Функция apply действует похожим образом, только всегда возвращает объект-получатель
    * (т.е. переданный объект-аргумент)
    * */
    StringBuilder().apply {
        for (letter in 'A'..'Z') append(letter)
        append('\n').append("Now I know the alphabet!")
    }.toString()
/*
* Функция apply вернула объект StringBuilder, с которым были произведены преобразования внутри лямбда-выражения
* А затем мы результат привели к строке
* */

/*
* библиотечная функция buildString использует ту же функцию apply:
* - создаёт объект StringBuilder
* - через apply к нему принимает лямбда-выражение
* - через apply получает построенный StringBuilder
* - возвращает String результат через StringBuilder.toString()
* */
fun alphabet3() = buildString {
    for (letter in 'A'..'Z') append(letter)
    append('\n')
    append("Now I know the alphabet!")
}