package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray = new SimpleArray<>();

    void add(E e) {
        if (!contains(e)) {
            simpleArray.add(e);
        }
    }

    public boolean contains(E e) {
        boolean dublicate = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), e)) {
                dublicate = true;
                break;
            }
        }
        return dublicate;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }
}