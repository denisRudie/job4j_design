package ru.job4j.parking;

import java.util.Map;

public interface Parking {

    boolean accept(Transport transport);

    boolean add(Transport transport);

    void removeByTransport(Transport transport);

    void removeBySpaceId(int id);

    int getFreeSpaces();

    Map<Integer, Transport> getTransportList();
}