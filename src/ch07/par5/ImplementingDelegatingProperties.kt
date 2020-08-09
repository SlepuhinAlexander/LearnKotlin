package ch07.par5

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/*
* пример: уведомление обработчиков событий о том, что свойство объекта изменило значение.
*
* класс PropertyChangeSupport управляет списком обработчиков и передаёт им события PropertyChangeEvent
* */
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person1(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
    /*
    * ObservableProperty является делегатом для свойств Person1.
    * Компилятор реализует теневое свойство через делегата и методы доступа к свойству, через методы делегата. Примерно:
    * val _salary = ObservableProperty(salary, changeSupport)
    * var salary: Int
    *   get() = _salary.getValue(this, property)
    *   set(value) { _salary.setValue(this, property, value) }
    * */
}

/*
* выделим класс, который будет хранить значение свойства и присылать необходимые уведомления
* */
class ObservableProperty(var propertyValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person1, property: KProperty<*>): Int = propertyValue
    operator fun setValue(p: Person1, property: KProperty<*>, newValue: Int) {
        val oldValue = propertyValue
        propertyValue = newValue
        changeSupport.firePropertyChange(property.name, oldValue, newValue)
    }
}

/* вместо класса ObservableProperty можно использовать библиотечный класс Delegates с тем же смыслом */
class Person2(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val observer = {
        property: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(property.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

fun main() {
    val p = Person1("Dmitry", 34, 2000)
    p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    })
    p.age = 35 // Property age changed from 34 to 35
    p.salary = 2100 // Property salary changed from 2000 to 2100
}

/*
* Правила делегирования свойств.
* Пусть есть класс, делегирующий свойство
*
* class C {
*   var prop: Type by MyDelegate()
* }
*
* val c = C()
*
* Экземпляр MyDelegate скрыто хранит свойство (назовём <delegate>)
* и использует объект типа KProperty (назовём <property>)
*
* class C {
*   private val <delegate> = MyDelegate()
*
*   var prop: Type
*       get() = <delegate>.getValue(this, <property>)
*       set(value: Type) = <delegate>.setValue(this, <property>, value)
* }
*
* */
