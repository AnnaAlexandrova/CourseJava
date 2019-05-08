package fytyr.idea_projects.course_java.proudcerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerManager {
    private final Queue<String> items = new LinkedList<>();
    private static final int MAX_CAPACITY = 20;
    private final Object lock = new Object();

    public ProducerConsumerManager(int producersCount, int consumersCount) {
        for (int i = 1; i < producersCount; i++) {
            Thread t = new Thread(() -> {
                try {
                    int j = 1;
                    while (true) {
                        Thread.sleep(1000);
                        String item = "Item " + j;
                        j++;

                        synchronized (lock) {
                            while (items.size() >= MAX_CAPACITY) {
                                lock.wait();
                            }

                            items.offer(item);
                            System.out.println(items.size());
                            lock.notifyAll();
                        }
                    }
                } catch (InterruptedException ignored) {

                }
            });
            t.start();
        }

        for (int i = 1; i < consumersCount; i++) {
            Thread t = new Thread(() -> {
                try {
                    int j = 1;
                    while (true) {
                        Thread.sleep(1000);

                        synchronized (lock) {
                            while (items.isEmpty()) {
                                lock.wait();
                            }

                            String item = items.remove();
                            System.out.println(item);
                            System.out.println(items.size());

                            lock.notifyAll();
                        }
                    }
                } catch (InterruptedException ignored) {

                }
            });
            t.start();
        }
    }
}
