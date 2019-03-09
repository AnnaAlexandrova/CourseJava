package fytyr.idea.projects.course_java.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 5, 7, 1, 6, 3, 4, 5, 1, 2));
        ArrayList<Integer> newNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (!newNumbers.contains(number)) {
                newNumbers.add(number);
            }
        }

        System.out.println(newNumbers);
    }
}
