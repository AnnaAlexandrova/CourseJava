package fytyr.idea_projects.course_java;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class StreamOfRoots {
    public static void main(String[] args) {
        System.out.println("Введите необходимое количество чисел");
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        DoubleStream roots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt);
        roots.limit(count).forEach(System.out::println);
    }
}
