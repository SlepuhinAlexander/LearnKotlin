package ch06.par1

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) println("Please fill in the required fields")
}

// public inline fun CharSequence?.isNullOrBlank() = this == null || this.isBlank()
/*
* функция isNullOrBlank объявлена как расширения для nullable типа CharSequence и корректно внутри себя проверяет
* случай когда объект действительно равен null
*
* для вызова такой функции оператор безопасного вызова не требуется: компилятор знает, что функция объявлена у nullable
* типа
*
* такие функции могут быть только функциями-расширениями, т.к. в самом классе функция не может быть вызвана, если
* объект класса на самом деле будет null
*
* ссылка this в теле такой функции может ссылаться на null, поэтому её нужно проверять явно.
* */

fun main() {
    verifyUserInput(" ")
    verifyUserInput(null)
}
