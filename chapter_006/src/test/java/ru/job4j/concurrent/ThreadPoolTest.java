package ru.job4j.concurrent;

import org.junit.Test;
import ru.job4j.concurrent.boundedblockingqueue.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadPoolTest {

    /**
     * Generate same simple tasks in queue.
     * Thread pool deque and execute tasks from queue.
     *
     * @throws InterruptedException throws if sleeping thread will be interrupted.
     */
    @Test
    public void whenAddTasksThanGetResultOfThreadPoolWorking() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(1_000);
        for (int i = 0; i < 1_000; i++) {
            try {
                tasks.offer(counter::getAndIncrement);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        new ThreadPool(tasks);

        Thread.sleep(150);
        assertEquals(1_000, counter.get());
    }

    @Test
    public void whenShutdownThreadPoolThanCheckEachThreadState() throws InterruptedException {
        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(100);

        ThreadPool pool = new ThreadPool(queue);

        pool.shutdown();
        Thread.sleep(150);

        assertTrue(pool.isTerminated());
    }
}