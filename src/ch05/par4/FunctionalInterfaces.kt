package ch05.par4

import java.awt.event.ActionListener

/*
* Если есть функция, возвращающая экземпляр функционального интерфейса, то она не может вернуть лямбда-выражение
* напрямую. Его нужно завернуть в вызов SAM-конструктора
* */
fun createRunnable() = Runnable { println("all done!") }

fun main() {
    Thread(createRunnable()).start()

}