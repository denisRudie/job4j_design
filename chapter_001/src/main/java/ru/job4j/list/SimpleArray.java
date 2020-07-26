package ru.job4j.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private final int DEFAULT_CAPACITY = 10;
    private int size;
    private int modCount;

    public SimpleArray() {
        this.container = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == container.length) {
            int newCapacity = size * 2;
            container = Arrays.copyOf(container, newCapacity);
        }
        container[size] = model;
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int cursor = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return (T) container[cursor++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
