package ch04.par1

import java.io.Serializable

// В Kotlin вложенные классы не имеют явного доступа к экземпляру внешнего класса

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Outer {
    /*
    * класс объявленный внутри внешнего класса по умолчанию является вложенным:
    * (в смысле Java это будет статический внутренний класс)
    * он НЕ ИМЕЕТ ссылку на экземпляр внешнего класса */
    class Nested

    /*
    * чтобы объявленный внутри внешнего класса был внутренним (обладающим ссылкой на экземпляр внешнего класса)
    * его нужно отметить inner.
    * (в смысле Java это будет обычный внутренний класс, не статический, не вложенный)
    * */
    inner class Inner {
        // ссылку на внешний класс можно получить как this@ТипВнешнегоКласса, где this - экземпляр внутреннего класса.
        fun getOuterReference() = this@Outer
    }
}