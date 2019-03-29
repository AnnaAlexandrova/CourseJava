package fytyr.idea_projects.course_java;

import java.util.stream.Stream;

public class FibonacciNumbers {
    public static void main(String[] args) {
        int count = 20;
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(count).forEach(n -> System.out.println(n[0]));
    }
}
