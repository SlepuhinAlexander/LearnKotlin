package ch03.par3

open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a View!")

fun Button.showOff() = println("I'm a Button!")

fun main() {
    val view: View = Button()
    view.click()
    view.showOff()
}