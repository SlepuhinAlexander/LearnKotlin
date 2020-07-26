package ch04.par1

// Запечатанные классы
// Запечатанный класс ограничивает возможность создания подклассов:
// ВСЕ ВОЗМОЖНЫЕ прямые подклассы запечатанного класса должны быть вложены в этот запечатанный суперкласс.
sealed class Expr {
    // sealed класс по умолчанию открыт, его можно наследовать
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int = when (e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.left) + eval(e.right)
}
