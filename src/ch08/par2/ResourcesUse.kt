package ch08.par2

import java.io.BufferedReader
import java.io.FileReader

/*
* в Kotlin нет синтаксиса try-with-resources из Java, но эту задачу можно решить с помощью функции высшего порядка use
* из стандартной библиотеки:
* */
fun readFirstLineFromFile(path: String): String = BufferedReader(FileReader(path)).use { br -> return br.readLine() }
/*
* use - встраиваемая функция-расширение, которая применяется к Closeable ресурсам и принимает параметр-функцию
* (лямбда-выражение) функция вызывает лямбда-выражение и гарантирует закрытие ресурса вне зависимости от успешности
* выполнения функции-параметра.
*
* в теле лямбда-выражения используется нелокальный возврат (return)
* */