package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(10);
        tree.add(1);
        tree.add(20);
        tree.add(15);
        tree.add(25);

        System.out.println("size: " + tree.getSize());
        System.out.println(tree.contains(10));

        Consumer<Integer> consumer = System.out::println;

        tree.traverseInDepthRecursion(consumer);
        tree.traverseInDepth(consumer);

        System.out.println(tree.remove(5));
        System.out.println(tree.remove(20));
        tree.traverseInWidth(consumer);
    }
}
