package fytyr.idea_projects.course_java;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        LinkedList<Person> people = new LinkedList<>();

        people.add(new Person("Ivan", 25));
        people.add(new Person("Boris", 29));
        people.add(new Person("Vlad", 15));
        people.add(new Person("Vadim", 18));
        people.add(new Person("Igor", 17));
        people.add(new Person("Egor", 25));
        people.add(new Person("Ivan", 35));

        String names = people.stream().map(Person::getName).distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(names);

        OptionalDouble avgAge = people.stream().filter(person -> person.getAge() < 18).mapToInt(Person::getAge).average();
        avgAge.ifPresent(System.out::println);

        Map<String, Double> personsByName = people.stream().collect(Collectors.groupingBy(Person::getName,
                Collectors.averagingInt(Person::getAge)));
        personsByName.forEach((name, age) ->
                System.out.printf("name %s: %.2f%n", name, age));

        people.stream().filter(person -> person.getAge() > 20 && person.getAge() < 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName).forEach(name -> System.out.print(name + " "));
    }
}
