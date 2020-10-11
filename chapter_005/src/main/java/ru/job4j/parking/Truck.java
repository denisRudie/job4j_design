package ru.job4j.parking;

public class Truck implements Transport {

    private final String name;
    private final int size;

    public Truck(String name) {
        this.name = name;
        this.size = 2;
    }

    @Override
    public int getSize() {
        return size;
    }
}
