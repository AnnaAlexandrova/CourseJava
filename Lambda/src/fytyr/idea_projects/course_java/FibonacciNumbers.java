package fytyr.idea_projects.course_java;

import java.util.stream.Stream;

public class FibonacciNumbers {
    public static void main(String[] args) {
        int count = 20;
        Stream.iterate(2, x -> x + 1)
                .map(x -> Math.round(
                        ((Math.pow(((1 + Math.sqrt(5)) / 2), x))
                                - Math.pow((1 - Math.sqrt(5) / 2), x))
                                / Math.sqrt(5)))
                .limit(count)
                .forEach(System.out::println);
    }
}
