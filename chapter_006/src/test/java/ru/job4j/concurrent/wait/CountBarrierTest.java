package ru.job4j.concurrent.wait;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountBarrierTest {

    @Test
    public void twoThreadsSleepWhenOneCountThenIncrement() throws InterruptedException {
        CountBarrier cb = new CountBarrier(100);

        Thread thread0 = new Thread(cb::count);

        Thread thread1 = new Thread(() -> {
            cb.await();
            cb.increment();
        });

        Thread thread2 = new Thread(() -> {
            cb.await();
            cb.increment();
        });

        thread1.start();
        thread2.start();
        thread0.start();

        thread1.join();
        thread2.join();
        thread0.join();

        assertEquals(102, cb.getCount());
    }

}