package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.arrayList.ArrayList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("ABC");
        list1.add("QWERTY");
        list1.add("plm");
        list1.add(1, "wsx");
        list1.add("cvb");
        list1.ensureCapacity(15);
        System.out.println(list1);

        ArrayList<String> list2 = new ArrayList<>(15);
        list2.addAll(list1);
        System.out.println(list2);
        list2.addAll(5, list1);
        System.out.println(list2);
        System.out.println(list2.size());

        System.out.println(list1.isEmpty());
        list1.clear();

        list1.addAll(list2);
        System.out.println(list1.set(3, "123"));
        System.out.println(list1.get(3));

        for (Iterator<String> i = list2.iterator(); i.hasNext(); ) {
            String text = i.next();
            System.out.print(text + "% ");
        }
        System.out.println();

        System.out.println(list1.contains("wsx"));
        list2.addAll(list1);
        System.out.println(list2.containsAll(list1));
        System.out.println(list2.remove(4));
        System.out.println(list2.remove("ABC"));
        System.out.println(list2 + " " + list2.size());

        System.out.println(list2.indexOf(null));
        System.out.println(list2.lastIndexOf("ABC"));

        Object[] array1 = list1.toArray(new Object[30]);
        for (Object s : array1) {
            System.out.print(s + " ");
        }
        System.out.println();

        Object[] array2 = list2.toArray();
        for (Object s : array2) {
            System.out.print(s + " ");
        }
        System.out.println();

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("wsx");
        list3.add("ABC");

        System.out.println(list2.removeAll(list3));
        System.out.println(list2 + " " + list2.size());
        list2.trimToSize();
        System.out.println(list2);

        ArrayList<String> list4 = new ArrayList<>();
        System.out.println(list4);
        list4.add("345");
        list4.add("ABC");
        System.out.println(list1.retainAll(list4));
        System.out.println(list1 + " " + list1.size());
    }
}
