package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] loadingSymbols = {'\\', '|', '/'};
        int cursor = 0;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r loading ... : " + loadingSymbols[cursor++]);
                if (cursor == loadingSymbols.length) {
                    cursor = 0;
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
