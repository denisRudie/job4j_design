package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        mem.set(findIndexById(id), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return mem.removeIf(mem -> mem.getId().equals(id));
    }

    public int findIndexById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                return i;
            }
        }
        throw new NoSuchElementException("Object not found");
    }

    @Override
    public T findById(String id) {
        return mem.get(findIndexById(id));
    }
}