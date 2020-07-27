package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int i = findIndexById(id);
        if (i != -1) {
            mem.set(i, model);
            return true;
        }
        return false;
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
        return -1;
    }

    @Override
    public T findById(String id) {
        int i = findIndexById(id);
        if (i != -1) {
            return mem.get(i);
        } else {
            return null;
        }
    }
}