package ch05.par3

import java.io.File


fun main() {
    /*
    * функция generateSequence генерирует последовательность по итеративному алгоритму: из текущего элемента
    * последовательности вычисляется следующий.
    * потенциально, последовательность бесконечна.
    * */
    val naturalNumbers = generateSequence(0) { it + 1 }
    /*
    * takeWhile ограничивает последовательность: будут браться элементы до тех пор пока выполняется предикат
    * */
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    // sum -- завершающая операция; без завершающей операции последовательность не будет вычислена
    println(numbersTo100.sum()) // 5050

    println(File("/Users/svtk/.HiddenDir/a.txt").isInHiddenDir()) // true
}

fun File.isInHiddenDir() = generateSequence(this) { it.parentFile }.any(File::isHidden)
