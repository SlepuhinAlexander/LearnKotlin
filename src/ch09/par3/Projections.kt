package ch09.par3

import kotlin.reflect.KClass

/*
* Использование * вместо типового аргумента.
* Синтаксис проекции со звёздочкой сообщает об отсутствии информации о типовом аргументе. Например List<*> означает
* список элементов неизвестного типа.
*
* MutableList<*> != MutableList<Any?>
* MutableList<Any?> - список элементов ЛЮБОГО типа.
* MutableList<*> - список элементов КОНКРЕТНОГО, но неизвестно какого, типа.
*
* Синтаксис со звёздочкой используется когда информация о типовом аргументе не имеет значения: код не использует
* методов, ссылающихся на типовой параметр в сигнатуре, или только читает данные без учёта их типа.
* */
fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) println(list.first())
    /* first() вернёт тип Any?, но этого и достаточно */
}

interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input >= 0
}

object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T : Any> registerValidator(
        kClass: KClass<T>, fieldValidator: FieldValidator<T>
    ) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> =
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}

fun main(args: Array<String>) {
    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)
    println(Validators[String::class].validate("Kotlin")) // true
    println(Validators[Int::class].validate(42)) // true
}
