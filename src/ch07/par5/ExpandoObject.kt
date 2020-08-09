package ch07.par5

/*
* Делегирование широко применяется в объектах с динамически определяемым набором атрибутов.
* Такие объекты называются РАСШИРЯЕМЫМИ ОБЪЕКТАМИ (expando objects)
* */
class Person3 {
    private val _attributes = hashMapOf<String, String>() // произвольные дополнительные атрибуты

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes // обязательный атрибут
    /*
    * в стандартной библиотеке определены функции-расширения getValue и setValue для стандартных интерфейсов Map и
    * MutableMap.
    *
    * вызов p.name фактически транслируется в _attributes.getValue(p, prop), который возвращает _attributes[prop.name]
    * */
}

fun main() {
    val p = Person3()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data) p.setAttribute(attrName, value)
    println(p.name)
}
