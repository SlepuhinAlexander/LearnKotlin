package ch04.par3

import java.util.stream.Stream

/*
* Делегирование: ключевое слово by
* Реализация всех методов интерфейса Collection<T> делегирована внутреннему (приватному) свойству innerList, который,
* собственно, и является коллекцией.
* Если необходимо изменить реализацию какого-то из делегированных методов - это можно указать явно.
* Остальные методы прописывать нет смысла - компилятор сгенерирует реализации делегирования автоматически
* */
class DelegatingCollection<T>(private val innerList: Collection<T> = ArrayList()) : Collection<T> by innerList

class CountingSet<T>(private val innerSet: MutableCollection<T> = HashSet()): MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val countingSet = CountingSet<Int>()
    countingSet.addAll(listOf(1,1,3))
    countingSet.remove(1)
    countingSet.addAll(setOf(1,1,1,1,2))
    println("${countingSet.objectsAdded} objects were added, ${countingSet.size} remain")
    // 5 objects were added, 3 remain
}
