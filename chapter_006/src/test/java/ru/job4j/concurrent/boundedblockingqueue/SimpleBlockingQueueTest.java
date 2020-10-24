package ru.job4j.concurrent.boundedblockingqueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void oneThreadOfferSecondThreadPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(() -> queue.offer(1));

        Thread consumer = new Thread(queue::poll);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assertEquals(0, queue.size());
    }

    @Test
    public void oneThreadOfferSecondThreadPollTwoTimes() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(() -> queue.offer(1));

        Thread consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
        });

        producer.start();
        consumer.start();

        producer.join();

        assertEquals(Thread.State.WAITING, consumer.getState());
    }
}