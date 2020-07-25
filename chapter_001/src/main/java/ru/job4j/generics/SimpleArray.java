package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int i = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        if (i < array.length) {
            array[i++] = model;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, i);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, i);
        System.arraycopy(array, index + 1, array, index, i - 1 - index);
        array[--i] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, i);
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < i;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return (T) array[cursor++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}