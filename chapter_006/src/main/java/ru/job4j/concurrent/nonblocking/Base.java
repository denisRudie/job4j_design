package ru.job4j.concurrent.nonblocking;

public class Base {
    private final int id;
    private int version;

    public Base(int id) {
        this.id = id;
        this.version = 1;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}