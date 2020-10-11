package ru.job4j.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Ignore
    @Test
    public void whenParkCarToParkingWithFreeSpaces() {
        TransportParking tp = new TransportParking("parking1", 1);
        Transport car = new Car("kia");
        tp.add(car);
        assertTrue(tp.getTransportList().containsValue(car));
        assertThat(tp.getFreeSpaces(), is(0));
    }

    @Ignore
    @Test
    public void whenParkTruckToParkingWithFreeSpaces() {
        TransportParking tp = new TransportParking("parking1", 2);
        Transport truck = new Truck("kamaz");
        tp.add(truck);
        assertTrue(tp.getTransportList().containsValue(truck));
        assertThat(tp.getFreeSpaces(), is(0));
    }

    @Ignore
    @Test
    public void whenParkCarToParkingWithoutFreeSpaces() {
        TransportParking tp = new TransportParking("parking1", 0);
        Transport car = new Car("kia");
        assertThat(tp.add(car), is(false));
    }

    @Ignore
    @Test
    public void whenParkTruckToParkingWithoutFreeSpaces() {
        TransportParking tp = new TransportParking("parking1", 1);
        Transport truck = new Truck("kamaz");
        assertThat(tp.add(truck), is(false));
    }

    @Ignore
    @Test
    public void whenTryToParkTruckAmong2Cars() {
        TransportParking tp = new TransportParking("parking1", 4);
        Transport car1 = new Car("car1");
        tp.add(car1);
        Transport car2 = new Car("car2");
        tp.add(car2);
        Transport car3 = new Car("car3");
        tp.add(car3);
        Transport car4 = new Car("car4");
        tp.add(car4);

        tp.removeBySpaceId(1);
        tp.removeBySpaceId(3);

        Transport truck = new Truck("kamaz");
        assertThat(tp.getFreeSpaces(), is(2));
        assertThat(tp.add(truck), is(false));
    }
}