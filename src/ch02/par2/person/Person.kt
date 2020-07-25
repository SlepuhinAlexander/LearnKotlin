package ch02.par2.person

/*
* Java:
*
* public class Person {
*     private final String name;
*     private Boolean isMarried = false;
*
*     public Person(String name, Boolean isMarried){
*         this.name = name;
*         this.isMarried = isMarried;
*     }
*
*     public String getName(){
*         return name;
*     }
*
*     public Boolean isMarried(){
*         return isMarried;
*     }
*
*     public void setMarried(Boolean isMarried){
*         this.isMarried = isMarried;
*     }
*
*     public static void main(String[] args){
*         Person aPerson = new Person("Bob");
*         Person anotherPerson = new Person("John", true);
*         System.out.println(aPerson.getName());
*         System.out.println(anotherPerson.isMarried());
*     }
* }
* */

// классы в Kotlin - public по умолчанию
class Person(
    val name: String,
    var isMarried: Boolean = false
)
/*
* свойство name - неизменяемое, для него есть только геттер метод; и оно обязательно должно быть задано в конструкторе
* свойство isMarried - изменяемое, для него есть и геттер, и сеттер-метод.
* любому свойству (например isMarried) можно сразу указать значение по умолчанию -- оно будет использовано, если
* аргумент не передан. В таком случае конструктору не обязательно передавать значение свойства.
* */

fun main() {
    val aPerson = Person("Bob")
    val anotherPerson = Person("John", true)
    println("aPerson.name=${aPerson.name}")
    println("anotherPerson.isMarried=${anotherPerson.isMarried}")
}