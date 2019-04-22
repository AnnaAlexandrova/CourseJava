package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.hashTable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> table1 = new HashTable<>();
        HashTable<String> table2 = new HashTable<>(1);
        table1.add("qwerty");
        table1.add("ty");
        table1.add("w");
        table1.add("123");
        table1.add(null);
        table1.add("w");
        table1.add("2");
        table1.add("o");

        table2.add("w");
        table2.add("123");

        Object[] array1 = table1.toArray();
        for (Object e : array1) {
            System.out.print(e + " ");
        }
        System.out.println();

        String[] array2 = table2.toArray(new String[1]);
        for (Object e : array2) {
            System.out.print(e + " ");
        }
        System.out.println();

        System.out.println(table1);
        System.out.println(table2);
        System.out.println("t1 contains t2" + table1.containsAll(table2));

        System.out.println(table2.addAll(table1));
        System.out.println("t2 + t1 " + table2);
        System.out.println(table2.size());

        HashTable<String> table3 = new HashTable<>(10);
        table3.add("6");
        table3.add("m");
        table3.add("123");
        table3.add("w");
        System.out.println(table2.removeAll(table3));
        System.out.println(table2);

        System.out.println(table1.retainAll(table2));
        System.out.println(table1);
    }
}

