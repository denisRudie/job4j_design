package ru.job4j.parking;

import java.util.HashMap;
import java.util.Map;

public class TransportParking implements Parking {

    private final String name;
    private int spaces;
    private final Map<Integer, Transport> parkedTransport;

    public TransportParking(String name, int spaces) {
        this.name = name;
        this.spaces = spaces;
        this.parkedTransport = new HashMap<>(spaces);
        for (int i = 0; i < spaces; i++) {
            parkedTransport.put(i, null);
        }
    }

    @Override
    public boolean accept(Transport transport) {
        return true;
    }

    @Override
    public boolean add(Transport transport) {
        return true;
    }

    @Override
    public void removeByTransport(Transport transport) {

    }

    @Override
    public void removeBySpaceId(int id) {

    }

    @Override
    public Map<Integer, Transport> getTransportList() {
        return null;
    }

    @Override
    public int getFreeSpaces() {
        return 0;
    }
}
