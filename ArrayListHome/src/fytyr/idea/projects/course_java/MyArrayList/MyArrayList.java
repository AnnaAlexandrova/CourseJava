package fytyr.idea.projects.course_java.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 7, 2, 6, 3, 8, 9, 15, 26, 7, 0));

        numbers.removeIf(integer -> integer % 2 == 0);

        System.out.println(numbers);
    }
}
