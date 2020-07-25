package ch02.par3.color

import ch02.par3.color.Color.*

fun getMnemonic(color: Color) = when (color) {
    RED -> "Каждый"
    ORANGE -> "Охотник"
    YELLOW -> "Желает"
    GREEN -> "Знать"
    BLUE -> "Где"
    INDIGO -> "Сидит"
    VIOLET -> "Фазан"
}

fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "тёплый"
    GREEN -> "нейтральный"
    BLUE, INDIGO, VIOLET -> "холодный"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("грязный цвет")
}

fun mixWhen(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
    (c1 == BLUE && c2 == YELLOW) || (c1 == YELLOW && c2 == BLUE) -> GREEN
    (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
    else -> throw Exception("грязный цвет")
}

fun main() {
    for (color in values()) println("$color : ${color.rgb()}")
    println()
    for (color in values()) println("$color mnemonic: ${getMnemonic(color)}")
    println()
    for (color in values()) println("$color warmth: ${getWarmth(color)}")
    println()
    for (c1 in values()) for (c2 in values()) {
        try {
            println("$c1+$c2=${mix(c1, c2)}")
        } catch (ignored: Exception) {
        }
    }
    println()
    for (c1 in values()) for (c2 in values()) {
        try {
            println("$c1+$c2=${mixWhen(c1, c2)}")
        } catch (ignored: Exception) {
        }
    }
}