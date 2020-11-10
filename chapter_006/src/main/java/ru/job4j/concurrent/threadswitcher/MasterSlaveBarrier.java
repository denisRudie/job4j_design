package ru.job4j.concurrent.threadswitcher;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MasterSlaveBarrier {

    private boolean currMaster = true;

    public synchronized void tryMaster() {
        if (!currMaster) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        if (currMaster) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        currMaster = false;
        notify();
    }

    public synchronized void doneSlave() {
        currMaster = true;
        notify();
    }
}
