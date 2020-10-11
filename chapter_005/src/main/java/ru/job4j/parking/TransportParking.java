package ru.job4j.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    /**
     * Looking space for transport.
     *
     * @param transport for looking.
     * @return space id or -1 if space not found.
     */
    @Override
    public int accept(Transport transport) {
        int transportSize = transport.getSize();

        if (spaces < transportSize) {
            return -1;
        }
        int nearbySpacesCounter = 0;

        for (int i = 0; i < parkedTransport.size(); i++) {

            if (parkedTransport.get(i) == null) {
                nearbySpacesCounter++;
            } else {
                nearbySpacesCounter = 0;
            }

            if (nearbySpacesCounter == transportSize) {
                return i - (nearbySpacesCounter - 1);
            }
        }
        return -1;
    }

    @Override
    public int add(Transport transport) {
        int spaceId = accept(transport);

        if (spaceId != -1) {
            for (int i = spaceId; i < spaceId + transport.getSize(); i++) {
                parkedTransport.put(i, transport);
                spaces--;
            }
        }
        return spaceId;
    }

    @Override
    public void removeByTransport(Transport transport) {
        parkedTransport.values().stream()
                .filter(t -> t.equals(transport))
                .forEach(t -> t = null);
        spaces += transport.getSize();
    }


    @Override
    public Map<Integer, Transport> getTransportList() {
        return parkedTransport;
    }

    @Override
    public Optional<Transport> getTransportBySpaceId(int id) {
        return Optional.of(parkedTransport.get(id));
    }

    @Override
    public int getFreeSpaces() {
        return spaces;
    }
}
