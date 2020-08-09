package ch06.par1

// оператор !! - утверждение, что значение оператора не равно null
// оператор !! преобразует значение к соответствующему не-null типу, и если всё же значение было null выбросит
// KotlinNullPointerException
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun main() {
    ignoreNulls(null)
}