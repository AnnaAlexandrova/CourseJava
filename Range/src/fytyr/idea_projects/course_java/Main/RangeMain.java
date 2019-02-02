package fytyr.idea_projects.course_java.Main;

import fytyr.idea_projects.course_java.Range.Range;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Range range = new Range(-50, 25.5);
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

        Range newRange = new Range(-40, 25.5);

        try {
            Range rangeIn = range.getRangeIntersection(newRange);

            assert rangeIn != null;
            System.out.println("Пересечение интервалов : " + rangeIn.getFrom() + ", " + rangeIn.getTo());

        } catch (NullPointerException e) {
            System.out.println("Интервалы не пересекаются");
        }
        System.out.println();

        Range[] mergeRange = range.getRangeMerger(newRange);

        System.out.println("Объединение двух интервалов : ");

        for (Range e : mergeRange) {
            System.out.println(e.getFrom() + ", " + e.getTo());
        }
        System.out.println();

        System.out.println("Разность двух интервалов : ");

        Range[] rangeDifference = range.getRangeDifference(newRange);

        for (Range e : rangeDifference) {
            System.out.println(e.getFrom() + ", " + e.getTo());
        }
    }
}
