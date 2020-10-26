package ru.job4j.concurrent.nonblocking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class CacheTest {

    @Test
    public void whenTwoThreadsTryToUpdateOnePosition() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Cache cache = new Cache();
        Base position = new Base(1);
        cache.add(position);

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    cache.update(position);
                } catch (OptimisticException e) {
                    ex.set(e);
                }
            }).start();
        }

        Thread.sleep(100);

        assertEquals(OptimisticException.class, ex.get().getClass());
    }
}