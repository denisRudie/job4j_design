package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.concurrent.boundedblockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

@ThreadSafe
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;

        int threadCounter = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < threadCounter; i++) {
            Thread t = new Worker(tasks);
            t.start();
            threads.add(t);
        }
    }

    public boolean isTerminated() {
        for (Thread thread : threads) {
            if (thread.getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private static class Worker extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;

        private Worker(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = tasks.poll();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}