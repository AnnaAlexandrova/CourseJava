package fytyr.idea_projects.course_java.Main;

import fytyr.idea_projects.course_java.Range.Range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Range range = new Range(5, 15);
        System.out.println("Диапазон находится от " + range.getFrom() + " до " + range.getTo());

        double length = range.getLength();
        System.out.println("Длина диапазона составляет: " + length);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        double userNumber = scanner.nextDouble();

        if (range.isInside(userNumber)) {
            System.out.println("Данное число входит в диапазон");
        } else {
            System.out.println("Данное число не входит в диапазон");
        }
        System.out.println();

        Range newRange = new Range(15, 20);

        Range rangeIn = range.getIntersection(newRange);
        if (rangeIn == null) {
            System.out.println("Интервалы не пересекаются");
        } else {
            System.out.println("Пересечение интервалов : " + rangeIn.getFrom() + ", " + rangeIn.getTo());
        }
        System.out.println();

        Range[] union = range.getUnion(newRange);

        System.out.println("Объединение двух интервалов : ");

        for (Range e : union) {
            System.out.println(e.getFrom() + ", " + e.getTo());
        }
        System.out.println();

        System.out.println("Разность двух интервалов : ");

        Range[] difference = range.getDifference(newRange);

        for (Range e : difference) {
            System.out.println(e.getFrom() + ", " + e.getTo());
        }
    }
}
