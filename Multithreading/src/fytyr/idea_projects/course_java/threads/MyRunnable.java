package fytyr.idea_projects.course_java.threads;

import java.util.List;

public class MyRunnable implements Runnable {
    private final List<Integer> list;
    //  private final Object lock = new Object();

    public MyRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; ++i) {
            synchronized (list) {
                list.add(i);
            }
        }
    }
}
