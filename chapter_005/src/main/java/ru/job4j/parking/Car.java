package ru.job4j.parking;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return size == car.size &&
                name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
