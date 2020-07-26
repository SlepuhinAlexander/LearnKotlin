package ch04.par4

import java.awt.Button
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

fun main() {
    val button = Button("new")
    button.addMouseListener(object : MouseAdapter() {
        /*
        * объект-выражение объявляет класс и создаёт его экземпляр, но не присваивает ему имени.
        * его можно сохранить в переменную при необходимости.
        * */
        override fun mouseClicked(e: MouseEvent?) {
            println("mouse clicked!")
        }

        override fun mouseEntered(e: MouseEvent?) {
            println("mouse entered!")
        }
    })

    val button2 = Button("clickable")
    button2.addActionListener { println("action performed!") }
}

/*
* В отличие от анонимных внутренних классов в Java, которые могут наследовать только один класс или реализовывать
* только один интерфейс, анонимный объект в Kotlin может реализовывать несколько интерфейсов или вовсе ни одного.
*
* Анонимные объекты не являются синглтонами! при каждом выполнении создаётся новый объект.
* */

// анонимный объект МОЖЕТ работать с не-финальными переменными и может менять их при необходимости.
fun countClicks(button: Button) {
    var clickCount = 0

    button.addActionListener { clickCount++ }
}