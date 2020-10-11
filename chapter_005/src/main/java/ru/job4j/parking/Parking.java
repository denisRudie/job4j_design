package ru.job4j.parking;

import java.util.Map;
import java.util.Optional;

public interface Parking {

    int accept(Transport transport);

    int add(Transport transport);

    void removeByTransport(Transport transport);

    int getFreeSpaces();

    Map<Integer, Transport> getTransportList();

    Optional<Transport> getTransportBySpaceId(int id);
}