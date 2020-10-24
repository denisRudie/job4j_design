package ru.job4j.concurrent.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    @GuardedBy("monitor")
    private final Object monitor = this;
    private final int total;
    private volatile int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            while (total > count) {
                count++;
            }
            notifyAll();
        }
    }

    public void increment() {
        synchronized (monitor) {
            count++;
        }
    }

    public void await() {
        synchronized (monitor) {
            try {
                if (count != total) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        return count;
    }
}
