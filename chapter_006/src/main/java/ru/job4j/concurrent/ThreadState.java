package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads terminated");
    }
}
