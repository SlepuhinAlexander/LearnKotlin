package ch07.par1

import java.math.BigDecimal
import java.time.LocalDate

data class Point(val x: Int, val y: Int) {
    /*
    * Kotlin позволяет "перегружать" стандартные операторы языка для работы с выбранными типами данных.
    * Для того чтобы воспользоваться этой возможностью, интересующий класс должен реализовать функцию со специальным
    * именем (называемую соглашение), соответствующую оператору.
    * Например, для переопределения оператора + класс должен реализовать функцию plus (с аргументом того же типа данных)
    * Функция не обязательно должна принадлежать классу, это так же может быть и функция-расширения.
    * Таким образом, можно расширить (дополнить) класс (например, класс из java-библиотеки), не меняя его, но при этом
    * получить возможность использовать перегруженный оператор для него.
    * */
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    /* функция, реализующая соглашение, должна быть явно отмечена ключевым словом operator */
}

/* функция не обязательно должна принадлежать классу, она может быть функцией-расширением */
operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)
/*
* Kotlin ограничивает список операторов которые могут быть перегружены и не дозволяет определять свои, нестандартные
* операторы.
* */
/*
* Список функций и операторов доступных для перегрузки:
* plus         +
* unaryPlus    +
* plusAssign   +=
* minus        -
* unaryMinus   -
* minusAssign  -=
* inc          ++
* dec          --
* times        *
* timesAssign  *=
* div          /
* divAssign    /=
* rem          %
* remAssign    %=
* not          !
* equals       ==  !=
* compareTo    <   >   <=  >=
* contains     in  !in
* rangeTo      ..
* get          [...]
* set          [...] = ...
* invoke       (...)
* */
/*
* Перегруженные операторы сохраняют тот же приоритет выполнения, что есть у стандартных операторов.
* Например, операторы *, / и % имеют одинаковый приоритет, и он выше, чем приоритет операторов + и -.
* */
/*
* Из Java-кода перегруженные операторы можно использовать вызывая полное имя функции.
* Если в Java-коде присутствует метод с подходящим именем, то при вызове этого кода из Kotlin можно использовать
* синтаксис перегруженных операторов: требуется только совпадение имени метода (и количества) с ожидаемым.
* Если метод в Java-коде есть, но назван иначе, можно легко в Kotlin определить функцию-расширения с нужным именем и
* просто делегировать её выполнение нужному методу java-класса.
* */

/*
* Операнды не обязаны быть одинаковых типов, например:
* */
operator fun Point.times(scale: Double) = Point((x * scale).toInt(), (y * scale).toInt())

/*
* Переопределённые операторы НЕ ОБЛАДАЮТ коммутативностью. Чтобы иметь возможность менять операнды местами, должна быть
* определена и вторая функция, реализующая оператор с обратным порядком операндов:
* */
operator fun Double.times(point: Point) = Point((this * point.x).toInt(), (this * point.y).toInt())

/*
* Тип результата-значения, возвращаемого оператором, тоже может отличаться:
* */
operator fun Char.times(count: Int) = toString().repeat(count)

/*
* В Kotlin отсутствуют побитовые операторы. Для выполнения таких операций над числовыми типами используются обычные
* функции (поддерживающие инфиксную запись).
* Для своих типов данных также можно определить эти функции.
* shl   сдвиг влево со знаком
* shr   сдвиг вправо со знаком
* ushr  беззнаковый сдвиг вправо
* and   поразрядное И
* or    поразрядное ИЛИ
* xor   поразрядное ИСКЛЮЧАЮЩЕЕ ИЛИ
* inv   поразрядная инверсия
* */

/*
* в норме, если переопределён оператор +, то и += также будет работать (если переменная изменяема, конечно)
* но никто не запрещает переопределить оператор += отдельно.
* например, для коллекций он может означать добавление элемента в коллекцию
* */
operator fun <T> MutableCollection<T>.plusAssign(element: T) {  // фактически, эта функция уже есть в стандартной
    this.add(element)                                           // библиотеке коллекций Kotlin
}

/*
* функции унарных операторов не принимают аргументов:
* */
operator fun Point.unaryMinus() = Point(-x, -y)

operator fun BigDecimal.inc() = this + BigDecimal.ONE

/*
* Оператор == транслируется в вызов метода equals И ПРОВЕРКУ НА РАВЕНСТВО NULL
* a == b -> a?.equals(b) ?: (b == null)
* Оператор != транслируется так же, только результат вызова инвертируется.
* Операторы == и != можно спокойно использовать с nullable операндами
*
* Класс Point является дата-классом, поэтому для него метод equals уже сгенерирован компилятором автоматически.
* Реализация выглядит примерно так:
* class Point(val x: Int, val y: Int) {
*   override fun equals(obj: Any?): Boolean {
*       if (obj === this) return true
*       if (obj !is Point) return false
*       return obj.x == x && obj.y == y
*   }
* }
*
* Оператор == уже переопределён в классе Any, поэтому в данном классе указан override и нет необходимости указывать
* operator.
* Также equals нельзя определить как расширение класса, т.к. она уже реализована (и наследуется из класса Any) и всегда
* имеет приоритет над расширением.
*
* Оператор строгого соответствия === сравнивает ссылки на объекты и не допускает переопределения.
* */

/*
* Оператор сравнения compareTo (для типов данных, реализующих интерфейс Comparable) позволяет сравнить два объекта в
* смысле порядка следования.
* Операторы сравнения транслируются следующим образом:
* a < b -> a.compareTo(b) < 0
* a <= b -> a.compareTo(b) <= 0
* a > b -> a.compareTo(b) > 0
* a >= b -> a.compareTo(b) >= 0
* */

/*
* Соглашения для коллекций и диапазонов
*
* */
class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    // модификатор operator уже применён к функции интерфейса Comparable
    override fun compareTo(other: Person) = compareValuesBy(this, other, Person::firstName, Person::lastName)
    /*
    * функция compareValuesBy из стандартной библиотеки принимает два объекта и набор функций как эти объекты
    * сравнивать:
    * функции вызываются по очереди для сравнения переданных объектов: если совпадения нет возвращается результат
    * сравнения текущей функцией; если текущая функция дала совпадение, вызывается следующая функция сравнения из
    * переданного набора; если функций не осталось (все они дали совпадение), возвращается ноль.
    *
    * Для Java-классов реализующих интерфейс Comparable, вызов сравнения в коде на котлин может использовать операторы
    * сравнения без каких-либо дополнительных действий.
    * */
}

/*
* Для доступа к элементам коллекции используется оператор индекса [...]
* Также оператор индекса используется для доступа к элементам словаря(map) по ключам.
*
* Можно переопределить оператор индекса и для своих классов.
* Параметром оператора get не обязательно должен быть индекс Int: для доступа к элементам словаря используется ключ,
* который, вообще говоря, может быть любым типом.
* */
operator fun Point.get(index: Int) = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

/*
* аналогично можно переопределить оператор индекса для изменения значения
* x[a] = b -> x.set(a, b)
* */
data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) = when (index) {
    0 -> x = value
    1 -> y = value
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

/*
* Для проверки вхождения в коллекцию или диапазон, или для итерации по ним используется оператор in
* Можно определить этот оператор и для своего класса
* a in b -> b.contains(a)
* */
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point) =
    p.x in upperLeft.x until lowerRight.x && p.y in upperLeft.y until lowerRight.y

/*
* оператор диапазона .. представляет собой краткую форму вызова функции rangeTo
* start..end -> start.rangeTo(end)
* функция rangeTo возвращает диапазон.
* Если класс реализует интерфейс Comparable, то диапазоны автоматически поддерживает оператор rangeTo
* */

/*
* оператор in так же используется для итерации по коллекции / диапазону.
* он транслируется в вызов iterator() и последовательный вызов hasNext() и next() для всех элементов итератора.
* Можно переопределить iterator() в своём классе для выполнения собственных итераций с помощью оператора in
* */
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start
        override fun hasNext() = current < endInclusive
        override fun next() = current.apply { current = plusDays(1) }
    }

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2) // Point(x=40, y=60)
    /*
    * Вызов такого оператора для типа данных, реализовавших соглашения, равносилен вызову функции.
    * Это просто иная, более удобная, синтаксическая запись: компилятор подставит вызов функции вместо оператора.
    * */
    println(p1.plus(p2)) // Point(x=40, y=60)
    println(p1 + p2 == p1.plus(p2)) // true
    // фактически в этом сравнении тоже используется перегруженный оператор == , реализованный функцией equals

    println(p2 - p1) // Point(x=20, y=20)

    println(p1 * 1.5) // Point(x=15, y=30)
    println(1.5 * p1) // Point(x=15, y=30)

    println('a' * 7) // aaaaaaa

    // поразрядные функции
    println(0x0F and 0xF0) // 0
    println(0x0F or 0xF0) // 255
    println(0x1 shl 4) // 16

    var point = Point(1, 2)
    point += Point(3, 4)
    println(point) // Point(x=4, y=6)

    val numbers = ArrayList<Int>()
    numbers += 42
    numbers += 33
    println(numbers) // [42, 33]
    numbers -= 33
    println(numbers) // [42]

    val list = arrayListOf(1, 2)
    list += 3
    val newList = list + listOf(4, 5)
    println(list) // [1, 2, 3]
    println(newList) // [1, 2, 3, 4, 5]

    println(-p1) // Point(x=-10, y=-20)

    var bd = BigDecimal.ZERO
    // логика пред- и постфиксной записи инкремента и декремента сохраняется как в java
    println(bd++) // 0
    println(++bd) // 2

    println(Point(10, 20) == Point(10, 20)) // true
    println(Point(10, 20) != Point(5, 5)) // true

    val person1 = Person("Alice", "Smith")
    val person2 = Person("Bob", "Johnson")
    println(person1 < person2) // true

    val p = Point(10, 20)
    println(p[1]) // 20

    val mp = MutablePoint(10, 20)
    mp[1] = 42
    println(mp) // MutablePoint(x=10, y=42)

    val rect = Rectangle(p1, p2)
    println(Point(20, 30) in rect) // true
    println(Point(5, 5) in rect) // false

    /*
    * оператор диапазона .. представляет собой краткую форму вызова функции rangeTo
    * start..end -> start.rangeTo(end)
    * функция rangeTo возвращает диапазон.
    * Если класс реализует интерфейс Comparable, то диапазоны автоматически поддерживает оператор rangeTo
    * */
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vacation) // true

    val n = 9
    println(0..(n + 1)) // 0..10
    (0..n).forEach { print(it) } // 0123456789
    println()

    val newYear = LocalDate.ofYearDay(2020, 1)
    val daysOff = newYear.minusDays(1)..newYear.plusDays(7)
    for (dayOff in daysOff) println(dayOff)
    /*
    * 2019-12-31
    * 2020-01-01
    * 2020-01-02
    * 2020-01-03
    * 2020-01-04
    * 2020-01-05
    * 2020-01-06
    * 2020-01-07
    * */
}
