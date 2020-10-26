package ru.job4j.concurrent.nonblocking;

public class OptimisticException extends RuntimeException {

    private static final long serialVersionUID = -493005023612470808L;

    public OptimisticException(String msg) {
        super(msg);
    }
}