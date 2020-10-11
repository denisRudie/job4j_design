package ru.job4j.parking;

public class Car implements Transport {

    private final String name;
    private final int size;

    public Car(String name) {
        this.name = name;
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
