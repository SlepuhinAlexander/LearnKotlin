package ch02.par2.geometry.shapes

import kotlin.random.Random

class Rectangle(val width: Int, val height: Int) {
    val isSquare: Boolean
        /* у свойства isSquare переопределён геттер-метод */
        get() = width == height
}

fun createRandomRectangle(): Rectangle = Rectangle(Random.nextInt(10, 20), Random.nextInt(10, 20))

