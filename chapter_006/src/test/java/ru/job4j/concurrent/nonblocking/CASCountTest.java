package ru.job4j.concurrent.nonblocking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

public class CASCountTest {

    @Test
    public void whenTwoThreadsConcurrentlyIncrementValue() throws InterruptedException {
        CASCount casCount = new CASCount();
        AtomicInteger t1Counter = new AtomicInteger();
        AtomicInteger t2Counter = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                casCount.increment();
                t1Counter.getAndIncrement();
            }
        });

        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                casCount.increment();
                t2Counter.getAndIncrement();
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(1);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();

        assertTrue(t1Counter.get() > 0);
        assertTrue(t2Counter.get() > 0);
    }
}