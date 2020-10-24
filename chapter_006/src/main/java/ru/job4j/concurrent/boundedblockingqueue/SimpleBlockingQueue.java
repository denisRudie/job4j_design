package ru.job4j.concurrent.boundedblockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() {
        T value = queue.poll();
        if (value == null) {
            try {
                wait();
                poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return value;
    }

    public synchronized int size() {
        return queue.size();
    }
}