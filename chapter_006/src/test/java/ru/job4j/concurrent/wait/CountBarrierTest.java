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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            cb.await();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread0.start();

        thread0.join();

        assertEquals(Thread.State.TIMED_WAITING, thread1.getState());
        assertEquals(Thread.State.TIMED_WAITING, thread2.getState());
    }

}