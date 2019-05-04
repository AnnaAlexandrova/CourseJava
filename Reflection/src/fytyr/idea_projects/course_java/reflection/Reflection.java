package fytyr.idea_projects.course_java.reflection;

import fytyr.idea_projects.course_java.classes.ClassA;
import fytyr.idea_projects.course_java.classes.ClassB;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¬ведите им€ класса");
        String className1 = scanner.nextLine();
        Class a = Class.forName("fytyr.idea_projects.course_java.classes." + className1);

        System.out.println("¬ведите им€ класса");
        String className2 = scanner.nextLine();
        Class b = Class.forName("fytyr.idea_projects.course_java.classes." + className2);

        Field[] fieldsA = a.getDeclaredFields();
        getFieldsInfo(fieldsA);

        Field[] fieldsB = b.getDeclaredFields();
        getFieldsInfo(fieldsB);

        Constructor aConstrct = a.getConstructor(int.class, int.class);
        ClassA classA = (ClassA)aConstrct.newInstance(3, 7);

        Constructor bConstrct = b.getConstructor(String.class);
        ClassB classB = (ClassB)bConstrct.newInstance("qwerty");

        Field fieldX = classA.getClass().getDeclaredField("x");
        fieldX.setAccessible(true);
        fieldX.set(classA, 90);

        Field fieldY = classA.getClass().getDeclaredField("y");
        fieldY.setAccessible(true);
        fieldY.set(classA, 23);

        Field fieldText = classB.getClass().getDeclaredField("text");
        fieldText.setAccessible(true);
        fieldText.set(classB, "qazwsx");

        Method m1 = classA.getClass().getMethod("getMax");
        System.out.println(m1.invoke(classA));

        Method m2 = classA.getClass().getMethod("getMin");
        System.out.println(m2.invoke(classA));

        Method m3 = classB.getClass().getMethod("print");
        System.out.println(m3.invoke(classB));
    }

    private static void getFieldsInfo(Field[] fields) {
        for (Field field : fields) {
            Class fieldType = field.getType();
            System.out.println("Name: " + field);
            System.out.println("Type " + fieldType);
        }
    }
}
