package fytyr.idea_projects.course_java.main;


import fytyr.idea_projects.course_java.threads.MyRunnable;
import fytyr.idea_projects.course_java.threads.MyThread;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new MyThread());
        t.start();
        try {
            t.join();
            System.out.println("Исполнение продолжено");
        } catch (InterruptedException e) {
        }

        List<Integer> list = new ArrayList<>();

        Thread thread1 = new Thread(new MyRunnable(list));
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable(list));
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }
        System.out.println(list.size());
        System.out.println(list);
    }
}
