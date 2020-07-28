package ru.job4j.list;

import java.util.LinkedList;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * @return return T object and delete it from collection
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * @param value add value to collection
     */
    public void push(T value) {
        linked.add(value);
    }
}