package ch10.par1

/*
* У аннотации Deprecated есть доп. параметр типа @ReplaceWith (тоже аннотация)
* Он подскажет на какой функцию нужно заменить использование. IDE даже сможет самостоятельно заменить.
* */
@Deprecated(message = "Use removeAt(index) instead", replaceWith = ReplaceWith("removeAt(index)"))
fun remove(index: Int) {
}

fun removeAt(index: Int) {
}

fun main() {
    /*remove(1)*/
    /*'remove(Int):Unit is deprecated. Use removeAt(index) instead'*/
    removeAt(1)
}

/*
* Аннотации могут иметь параметры определённых типов:
* - простые типы
* - строки
* - перечисления
* - ссылки на классы
* - классы других аннотаций
* - массивы из перечисленного.
*
* Чтобы передать ссылку на класс, нужно использовать синтаксис ::class
* @MyAnnotation(MyClass::class)
*
* Чтобы передать другую аннотацию, использовать символ @ не требуется.
* @Deprecated(message = "Use removeAt(index) instead", replaceWith = ReplaceWith("removeAt(index)"))
*
* Чтобы передать массив в аргумент, используется arrayOf(...)
* @RequestMapping(path = arrayOf("/foo", "/bar"))
* */

/*
* Аргументы аннотаций должны быть известны на этапе компиляции.
* */

/*
* Аннотируемый элемент можно указать с помощью объявления цели. Например:
* @get:Rule
* указывает что аннотация @Rule применяется к геттеру свойства.
*
* class HasTempFolder {
*   @get:Rule
*   val folder = TemporaryFolder()
*
*   @Test
*   fun testUsingTempFolder() {
*       val createdFile = folder.newFile("myFile.txt")
*       val createdFolder = folder.newFolder("subfolder")
*   }
* }
*
* Аннотации, объявленные в Java, по умолчанию применяются к полю
* А поле в Kotlin всегда private.
*
* Все варианты целей:
* - property -- применение к свойству в целом. Java-аннотации не могут применяться с этим объявлением цели.
* - field -- применение к полю непосредственно.
* - get -- к геттеру
* - set -- к сеттеру
* - receiver -- к получателю для функции-расширения или свойства/расширения
* - param -- к параметру конструктора
* - setparam -- к параметру сеттера
* - delegate -- к полю, хранящему делегата для делегированного свойства.
* - file -- к классу, содержащему свойства и функции верхнего уровня, объявленные в файле. Аннотации с целью file
*   должны находиться на верхнем уровне перед директивой package.
*
* Например
* @file:JvmName("StringFunctions")
* изменяет (на указанное значение) имя сгенерированного класса для объявленных в этом файле свойств и функций верхнего
* уровня
* */

/*
* Аннотации для совместимости с Java:
* - @Volatile -- прямо соответствует ключевому слову volatile
* - @Strictfp -- прямо соответствует ключевому слову strictfp
* - @JvmName -- изменяет имя Java-метода или поля, сгенерированного на основе Kotlin-объявления
* - @JvmStatic -- применяется к методам объектов или объектов-компаньонов, чтобы со стороны Java они выглядели как
*   статические методы
* - @JvmOverloads -- указание компилятору сгенерировать перегруженные версии методов (функций) с параметрами, имеющими
*   значения по умолчанию
* - @JvmField -- устанавливает доступ непосредственно к полю свойства, без геттеров-сеттеров. Соответствует публичному
*   полю в Java.
* */

// Объявление аннотаций
annotation class MyAnnotation

/*
* Аннотация не может иметь тела (исполняемого кода)
* Параметры для аннотаций указываются в основном конструкторе.
* */
annotation class MyAnnotation1(val name: String)
/*
* При использовании аннотации параметры передаются как при вызове обычного конструктора: можно использовать значения по
* умолчанию, именованные аргументы.
* */

// мета-аннотации
/*
* мета-аннотации - аннотации, применяемые к аннотациям.
* например, @Target - указывает к каким целям может применяться аннотация: свойствам, функциям, классам, и так далее.
* Если нужно несколько целей - указываются несколько
* */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class TargetedAnnotation

/*
* Для объявления своей мета-аннотации, нужно ей указать целью ANNOTATION_CLASS
* */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class BindingAnnotation

@BindingAnnotation
annotation class MyBinding
/*
* мета-аннотация @Retention указывает доступность аннотации:
* AnnotationRetention.SOURCE -- используется только в исходном коде, но не сохраняется при компиляции
* AnnotationRetention.BINARY -- сохраняется в после компиляции но не доступно в рантайме (не применимо для рефлексии)
* AnnotationRetention.RUNTIME -- сохраняется в после компиляции и доступно в рантайме (применимо для рефлексии)
*
* полностью Соответствует аннотации @Retention со значениями RetentionPolicy из Java.
* только здесь значение по умолчанию RUNTIME
* */

// ссылки на классы в параметрах аннотаций.
/*
* В качестве параметров аннотаций могут выступать ссылки на классы.
* Например @DeserializeInterface из библиотеки JKid позволяет указать какой класс нужно использовать при десериализации
* для свойства с типом интерфейс: т.е. какой класс, имплементирующий этот интерфейс, надо использовать.
*
* interface Company {
*     val name: String
* }
*
* data class CompanyImpl(override val name: String) : Company
*
* data class Employee(
*         val name: String,
*         @DeserializeInterface(CompanyImpl::class)
*         val company: Company
* )
*
* синтаксис ::class указывает, что используется ссылка на класс.
* */
/*
* аннотация объявлена как:
*
* annotation class DeserializeInterface(val targetClass: KClass<out Any>)
*
* Тип KClass - это аналог типа java.lang.Class из Java. Он используется для хранения ссылок на классы Kotlin.
* типовой параметр указывает а какой класс может ссылаться данная ссылка.
* Например, CompanyImpl::class имеет тип KClass<CompanyImpl>
* */
