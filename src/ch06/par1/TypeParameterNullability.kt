package ch06.par1

// типовые параметры в дженерик-функциях по умолчанию считаются nullable
fun <T> printHashCode(t: T) {
    // компилятор определяет T как тип Any? - т.е. любой тип, с поддержкой null
    println(t?.hashCode())
}

// если типовой параметр ограничить,
// то вызвать такую функцию с аргументом null или nullable типом компилятор уже не даст
fun <T: Any> printHash(t: T) {
    println(t.hashCode())
}

fun main() {
    printHashCode(null) // null
    printHash(42)
}
