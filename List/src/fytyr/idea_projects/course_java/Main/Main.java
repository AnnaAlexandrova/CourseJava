package fytyr.idea_projects.course_java.Main;

import fytyr.idea_projects.course_java.SinglyLinkedList.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirstItem(12);
        list.addFirstItem(15);
        list.addFirstItem(5);
        list.addFirstItem(9);
        list.addFirstItem(2);
        list.addFirstItem(6);
        list.addFirstItem(null);
        list.addFirstItem(3);
        list.addFirstItem(7);

        System.out.println(list.getSize());
        System.out.println(list.getFirstElement());
        System.out.println(list.getElement(4));

        System.out.println(list.setElement(3, 0));
        System.out.println(list.removeFirst());

        System.out.println(list.removeNode(3));
        System.out.println(list.remove(null));

        System.out.println(list);
        list.addItem(6, 26);
        list.addItem(3, 90);
        System.out.println(list);
        list.reverse();
        System.out.println(list);

        SinglyLinkedList<Integer> copy = list.copy();
        System.out.println(copy);
        System.out.println(copy.getSize());
    }
}
