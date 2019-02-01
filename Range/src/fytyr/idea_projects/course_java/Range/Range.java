package fytyr.idea_projects.course_java.Range;

import java.util.Scanner;

public class Range {
    private double from;
    private double to;
    private static final double EPSILON = 1.0e-10;

    private Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    private double getFrom() {
        return from;
    }

    private void setFrom(double from) {
        this.from = from;
    }

    private double getTo() {
        return to;
    }

    private void setTo(double to) {
        this.to = to;
    }

    private double getLength() {
        return to - from;
    }

    private boolean isInside(double x) {
        boolean isInside = false;
        if ((x - from >= -EPSILON) && (to - x >= -EPSILON)) {
            isInside = true;
        }
        return isInside;
    }

    private double getIntervalLength(double number1, double number2) {
        if (Math.abs(number1 - number2) <= EPSILON) {
            return 0;
        } else if (number2 - number1 > EPSILON) {
            return number2 - number1;
        }
        return number1 - number2;
    }

    private boolean isIntersect(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        boolean isIntersect = false;

        if ((from1 > from2) && (from1 <= to2)) {
            isIntersect = true;
        } else if ((from1 <= from2) && (from2 <= to1)) {
            isIntersect = true;
        }
        return isIntersect;
    }

    private Range getIntervalIntersection(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        Range resultRange = new Range(0, 0);

        if (!isIntersect(range1, range2)) {
            return null;
        } else {
            if (from1 < from2) {
                if (to2 < to1) {
                    resultRange.setFrom(from2);
                    resultRange.setTo(to2);
                } else {
                    resultRange.setFrom(from2);
                    resultRange.setTo(to1);
                }
            } else {
                if (to1 < to2) {
                    resultRange.setFrom(from1);
                    resultRange.setTo(to1);
                } else {
                    resultRange.setFrom(from1);
                    resultRange.setTo(to2);
                }
            }
        }
        return resultRange;
    }

    private Range[] getIntervalMerger(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        if (isIntersect(range1, range2)) {
            Range range = new Range(0, 0);
            Range[] mergeRange = {range};

            if (from1 < from2) {
                if (to2 < to1) {
                    range.setFrom(from1);
                    range.setTo(to1);
                } else {
                    range.setFrom(from1);
                    range.setTo(to2);
                }
            } else {
                if (to1 < to2) {
                    range.setFrom(from2);
                    range.setTo(to2);
                } else {
                    range.setFrom(from2);
                    range.setTo(to1);
                }
            }
            return mergeRange;
        }

        Range[] mergeRange = new Range[2];

        if (from1 < from2) {
            mergeRange[0] = range1;
            mergeRange[1] = range2;
        } else {
            mergeRange[0] = range2;
            mergeRange[1] = range1;
        }
        return mergeRange;
    }

    private Range[] getIntervalDifference(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        Range result = new Range(0, 0);
        Range[] differRange = {result};

        Range[] differRange2 = new Range[2];

        if (!isIntersect(range1, range2)) {
            differRange[0] = range1;
        } else {
            if (from1 < from2) {
                if (to2 < to1) {

                    differRange2[0] = new Range(from1, from2);
                    differRange2[1] = new Range(to2, to1);

                    return differRange2;
                } else {
                    result.setFrom(from1);
                    result.setTo(from2);
                }
            } else {
                if (to2 < to1) {
                    result.setFrom(to2);
                    result.setTo(to1);
                } else {
                    return null;
                }
            }
        }
        return differRange;
    }

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

        double x = 25.5;
        double y = -50;
        System.out.println("Длина интервала  = " + range.getIntervalLength(x, y));
        System.out.println();

        Range range1 = new Range(4, 10);
        Range range2 = new Range(5, 7);

        try {
            Range rangeIn = range1.getIntervalIntersection(range1, range2);

            assert rangeIn != null;
            System.out.println("Пересечение интервалов : " + rangeIn.getFrom() + ", " + rangeIn.getTo());

        } catch (NullPointerException e) {
            System.out.println("Интервалы не пересекаются");
        }
        System.out.println();

        Range[] mergeRange = range1.getIntervalMerger(range1, range2);

        System.out.println("Объединение двух интервалов : ");

        for (Range e : mergeRange) {
            System.out.println(e.getFrom() + ", " + e.getTo());
        }
        System.out.println();

        try {
            Range[] rangeDifference = range1.getIntervalDifference(range1, range2);

            System.out.println("Разность двух интервалов : ");

            assert rangeDifference != null;
            for (Range e : rangeDifference) {
                System.out.println(e.getFrom() + ", " + e.getTo());
            }
        } catch (NullPointerException e) {
            System.out.println("Интервалы равны или уменьшаемый интервал полностью входит в вычитаемый ");
        }
    }
}
