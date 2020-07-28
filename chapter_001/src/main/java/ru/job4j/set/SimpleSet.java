package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray = new SimpleArray<>();

    void add(E e) {
        boolean dublicate = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                dublicate = true;
            }
        }
        if (!dublicate) {
            simpleArray.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}