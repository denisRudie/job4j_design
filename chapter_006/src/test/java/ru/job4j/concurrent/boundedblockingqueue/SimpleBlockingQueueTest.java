package ru.job4j.concurrent.boundedblockingqueue;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void oneThreadOfferSecondThreadPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assertEquals(0, queue.size());
    }

    @Test
    public void oneThreadOfferSecondThreadPollTwoTimes() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                queue.poll();
                queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();

        assertEquals(Thread.State.WAITING, consumer.getState());
    }

    @Test
    public void whenQueueIsFullThanProducerSleep() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);

        Thread producer = new Thread(() -> IntStream.range(0, 4).forEach((i) -> {
            try {
                queue.offer(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }));
        producer.start();
        Thread.sleep(100);

        assertEquals(3, queue.size());
        assertEquals(Thread.State.WAITING, producer.getState());
    }

    @Test
    public void whenInterruptConsumerThanTakeAllBefore() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(100);
        CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();

        Thread producer = new Thread(() -> IntStream.range(0, 100).forEach((i) -> {
            try {
                queue.offer(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }));
        producer.start();

        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() || queue.size() != 0) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();

        consumer.interrupt();
        consumer.join();

        assertEquals(100, buffer.size());
        assertEquals(0, queue.size());
    }
}