package ch06.par3

/*
* массив в Kotlin это параметризованный (дженерик) класс, где тип элементов определяется аргументом типа.
*
* */

fun <T> printArray(arr: Array<T>) {
    for (i in arr.indices) println("Argument $i is ${arr[i]}")
    /*
    * indices - свойство-расширение массива, возвращающее диапазон его индексов
    * arr[i] - обращение к элементу номер i
    * */
}

fun main() {
    // варианты создания массива: функция arrayOf или arrayOfNulls
    val arr = arrayOf(3, 7, 9)
    printArray(arr)
    val nulls = arrayOfNulls<Int>(3)
    printArray(nulls)
    // конструктор Array: принимает размер массива и лямбда-выражения для инициализации элементов
    val letters = Array(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(" ")) // a b c d e f g h i j k l m n o p q r s t u v w x y z

    val strings = listOf("a", "b", "c")
    println("%s%s%s".format(*strings.toTypedArray()))
    /*
    * функция-расширение для коллекций toTypedArray преобразует коллекцию в массив
    * оператор развёртывания * преобразует массив в набор аргументов для vararg аргумента функции format
    * */

    // объявленные таким образом массивы всегда имеют ссылочные типы в качестве элементов.
    // для массивов из примитивных типов необходимо использовать тип IntArray, CharArray и так далее
    // создание массива примитивов: конструктор
    val ints = IntArray(4) // создаёт массив заданного размера и заполняет значениями по умолчанию (нулями / false)
    println(ints.joinToString(", ")) // 0, 0, 0, 0
    // создание массива примитивов: конструктор + лямбда
    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString(", ")) // 1, 4, 9, 16, 25
    // создание массива примитивов: фабричная функция с vararg аргументом
    val fibonacci = intArrayOf(1, 1, 2, 3, 5, 8, 13, 21)
    println(fibonacci.joinToString(", ")) // 1, 1, 2, 3, 5, 8, 13, 21

    letters.forEachIndexed { index, element -> println("Argument $index is: $element") }
    /*
    * функция-расширение forEachIndexed расширяет все массивы (в т.ч. массивы примитивов) и применяет переданное
    * лямбда-выражение ко всем элементам массива
    * */
}
