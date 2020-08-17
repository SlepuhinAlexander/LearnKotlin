package ch10.par2


import kotlin.reflect.KFunction2
import kotlin.reflect.full.memberProperties

class Person(val name: String, val age: Int)

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y

var counter = 0

fun main() {
    /*
    * KClass - представление класса в механизме рефлексии. Ссылка на класс. Аналог java.lang.Class.
    * Из ссылки на класс можно получить доступ ко всем объявлениям в этом классе и его суперклассах.
    *
    * Чтобы получить ссылку на класс нужно указать ::class после имени класса.
    *
    * Чтобы узнать класс объекта в рантайме, сначала нужно получить его java-класс: с помощью свойства javaCass (прямой
    * эквивалент метода java.lang.Object.getClass()); а затем обратиться к его свойству-расширению kotlin
    * */
    val person = Person("Alice", 29)
    val kClass = person.javaClass.kotlin
    /* javaClass вернёт Class<Person>. kotlin вернёт KClass<Person> */
    println(kClass.simpleName) // Person
    kClass.memberProperties.forEach { print("${it.name} ") } // age name

    /*
    * KCallable - это суперинтерфейс для всех функций и свойств. он использует метод call, с помощью которого можно
    * вызвать функцию или геттер свойства.
    * */
    val kFunction = ::foo
    kFunction.call(42)

    /*
    * Интерфейс KFunction наследуется от KCallable и имеет метод invoke с конкретным количеством аргументов
    * */
    val kFunction2: KFunction2<Int, Int, Int> = ::sum
    println(kFunction2.invoke(1, 2) + kFunction2(3, 4)) // 10
    /*
    * Интерфейс KFunctionN - синтетический тип, генерируется компилятором. Объявления исходного кода не найти :)
    * */

    /*
    * Для свойств верхнего уровня удобнее использовать метод get интерфейса KProperty0 | KMutableProperty0
    * Но можно использовать и KCallable.call
    * */
    val kProperty = ::counter
    // варианты обращения к сеттеру
    kProperty.set(20)
    kProperty.setter.call(42)
    // варианты обращения к геттеру
    println(kProperty.get())
    println(kProperty.getter.call())

    /*
    * свойства-члены класса представлены интерфейсом KProperty1 / MutableProperty1
    * где первый типовой аргумент - тип получателя свойства, а второй - тип самого свойства.
    * */
    val person1 = Person1("Alice", 29)
    val personNameProperty = Person1::name
    val personAgeProperty = Person1::age
    println(personNameProperty.get(person1)) // Alice
    personAgeProperty.set(person1, 33)
    println(person1.age) // 33
}

class Person1(val name: String, var age: Int)

/*
* Все интерфейсы, дающие доступ к исходному коду, наследуются от KAnnotatedElement - т.к. все объявления могут иметь
* аннотации
* KClass используется для представления классов и объектов.
* KProperty представляет любые свойства, а KMutableProperty -- только var (изменяемые) свойства.
* KFunction представляет функции.
* Интерфейсы Getter и Setter представляют геттеры-сеттеры свойства (для извлечения их аннотаций, например). Наследуются
* от KFunction.
* */
