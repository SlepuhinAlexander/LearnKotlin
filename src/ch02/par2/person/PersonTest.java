package ch02.par2.person;

public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("Bob", true);
        System.out.println("person.getName()=" + person.getName());
        System.out.println("person.isMarried()=" + person.isMarried());
    }
}
