package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        mem.set(mem.indexOf(findById(id)), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return mem.removeIf(mem -> mem.getId().equals(id));
    }

    @Override
    public T findById(String id) {
        Optional<T> o = mem.stream()
                .filter(mem -> mem.getId().equals(id))
                .findFirst();
        if (o.isPresent()) {
            return o.get();
        } else {
            throw new NoSuchElementException("Object not found");
        }
    }
}