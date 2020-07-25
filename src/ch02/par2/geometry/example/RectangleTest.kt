package ch02.par2.geometry.example

import ch02.par2.geometry.shapes.createRandomRectangle

fun main() {
    val rectangle = createRandomRectangle()
    println("Rectangle width=${rectangle.width}, height=${rectangle.height} is a square? ${rectangle.isSquare}")
}