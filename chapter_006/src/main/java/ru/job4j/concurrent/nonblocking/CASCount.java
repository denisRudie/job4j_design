package ru.job4j.concurrent.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        Integer temp;
        do {
            temp = count.get();
        }
        while (!count.compareAndSet(temp, temp + 1));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();

        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                casCount.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                casCount.increment();
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(100);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();

        System.out.println(casCount.get());
    }
}
