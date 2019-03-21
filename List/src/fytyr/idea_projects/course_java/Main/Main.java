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
        list.addFirstItem(3);
        list.addFirstItem(7);

        System.out.println(list.getSize());
        System.out.println(list.getFirstElement());
        System.out.println(list.getElement(4));

        System.out.println(list.setElement(2, 0));
        System.out.println(list.removeFirst());

        System.out.println(list.removeNode(3));
        System.out.println(list.remove(15));

        list.print();
        list.addItem(1, 26);
        list.addItem(0, 90);
        list.print();
        list.reverse();
        list.print();

        SinglyLinkedList<Integer> copy = list.copy();
        copy.print();
        System.out.println(copy.getSize());
    }
}
