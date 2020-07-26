package ch04.par1

// модификаторы видимости (доступа)

/*
* public
* член класса доступен повсюду
* объявление верхнего уровня доступно повсюду
* является модификатором по умолчанию (можно не указывать)
*
* internal
* член класса доступен в пределах модуля для своего класса
* объявление классе доступно в пределах своего модуля
*
* protected
* члены класса доступны только в пределах своего класса и его наследников
* применяется только к членам класса
*
* private
* член класса доступен только в пределах своего класса
* объявление верхнего уровня (в том числе класса / интерфейса верхнего уровня) доступно только в пределах своего файла
* */

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

//fun TalkativeButton.giveSpeech() {  // err: public fun exposes internal class
//    yell()                          // err: yell() is private
//    whisper()                       // err: whisper() is protected
//}