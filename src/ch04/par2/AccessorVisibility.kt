package ch04.par2

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val lengthCounter = LengthCounter()
    // lengthCounter.counter += 11; // cannot assign: setter is private
    lengthCounter.addWord("Hi!")
    println("lengthCounter.counter = ${lengthCounter.counter}") // lengthCounter.counter = 3
}
