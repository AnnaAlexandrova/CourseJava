package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(7);
        tree.add(6);
        tree.add(11);
        tree.add(10);
        tree.add(4);
        tree.add(16);
        tree.add(15);
        tree.add(12);
        tree.add(13);

        System.out.println("size: " + tree.getSize());
        System.out.println(tree.contains(10));

        Consumer<Integer> consumer = System.out::println;
        tree.traverseInWidth(consumer);
        tree.traverseInDepthRecursion(consumer);
        tree.traverseInDepth(consumer);

        System.out.println(tree.remove(6));
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(11));
    }
}
