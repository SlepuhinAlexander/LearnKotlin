package ch06.par1

fun sendEmailTo(email: String) {
    println("sending email to $email")
}

/*
* функция let превращает объект вызова в параметр лямбда-выражения:
* вызывает переданный блок кода и передаёт туда вызвавший объект в качестве параметра
* */
fun main() {
    var email: String? = "yole@example.com"
    // безопасный вызов функции let выполнит лямбда-выражение только если email не равно null
    email?.let { sendEmailTo(it) } // sending email to yole@example.com
    email = null
    email?.let { sendEmailTo(it) } // ничего не произойдёт
}
