package ru.job4j.parking;

public interface Parking {

    boolean accept(Transport transport);

    void add(Transport transport);

    void remove(Transport transport);
}