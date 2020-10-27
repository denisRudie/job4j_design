package ru.job4j.concurrent.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final ConcurrentHashMap<Integer, Base> store = new ConcurrentHashMap<>();

    public void add(Base model) {
        store.putIfAbsent(model.getId(), model);
    }

    public Base update(Base model) throws OptimisticException {
        int temp = model.getVersion();
        return store.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() != temp) {
                throw new OptimisticException("overwriting version which has been updated after " +
                        "you started updating process");
            }
            v.setVersion(temp + 1);
            return v;
        });
    }

    public void delete(Base model) {
        store.remove(model.getId());
    }
}