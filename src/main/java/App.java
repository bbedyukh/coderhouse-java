import model.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] arg) {
        Person person = new Person("Juan", "Perez");
        Person person2 = new Person("Francisco", "Hernandez");
        Person person3 = new Person("Pablo", "Garcia");
        Person person4 = new Person("Ignacio", "Ferrari");
        Person person5 = new Person("Cristina", "Villalba");

        List<Person> personas = Arrays.asList(person, person2, person3, person4, person5);

        System.out.println("Ordenado alfabéticamente por nombre");
        personas.stream()
                .sorted(Comparator.comparing(Person::getFirstName))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Ordenado alfabéticamente por apellido");
        personas.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Ordenado inversamente alfabéticamente por apellido");
        personas.stream()
                .sorted(Comparator.comparing(Person::getLastName).reversed())
                .forEach(System.out::println);
    }
}
