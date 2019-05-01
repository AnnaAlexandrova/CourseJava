package fytyr.idea_projects.course_java.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private int[][] matrix;
    private int size;

    public Graph(int size) {
        this.size = size;
        this.matrix = new int[size][size];
    }

    public void addEdge(int begin, int end) {
        if (begin < 0 && end >= size) {
            throw new IndexOutOfBoundsException("Ребро с переданными вершинами не входит в граф");
        }
        if (begin == end) {
            matrix[begin][end] = 0;
        } else {
            matrix[begin][end] = 1;
            matrix[end][begin] = 1;
        }
    }

    public void traverseInBreadth(Consumer<Integer> consumer) {
        if (size == 0) {
            return;
        }
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            queue.add(i);
            while (!queue.isEmpty()) {
                int currentVertex = queue.remove();
                if (visited[currentVertex]) {
                    continue;
                }
                visited[currentVertex] = true;
                consumer.accept(currentVertex);
                for (int j = 0; j < size; j++) {
                    if (matrix[currentVertex][j] == 0) {
                        continue;
                    }
                    queue.add(j);
                }
            }
        }
    }

    public void traverseInDepth(Consumer<Integer> consumer) {
        if (size == 0) {
            return;
        }
        boolean[] visited = new boolean[size];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            deque.addLast(i);
            while (!deque.isEmpty()) {
                int currentVertex = deque.removeLast();
                if (visited[currentVertex]) {
                    continue;
                }
                visited[currentVertex] = true;
                consumer.accept(currentVertex);
                for (int j = 0; j < size; j++) {
                    if (matrix[j][currentVertex] == 0) {
                        continue;
                    }
                    deque.addLast(j);
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
