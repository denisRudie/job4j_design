package ru.job4j.parking;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return size == truck.size &&
                name.equals(truck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
