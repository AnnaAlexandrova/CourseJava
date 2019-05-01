package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 9);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);

        graph.print();

        Consumer<Integer> consumer = System.out::print;
        graph.traverseInBreadth(consumer);
        System.out.println();
        graph.traverseInDepth(consumer);
    }
}
