package fytyr.idea_projects.course_java.threads;

public class MyThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <=10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
