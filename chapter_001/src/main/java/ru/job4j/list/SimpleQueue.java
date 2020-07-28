package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * @return return first and remove it from collection
     */
    public T poll() {
        T value;

        try {
            while (true) {
                out.push(in.pop());
            }
        } catch (NoSuchElementException e) {
            value = out.pop();
            return value;
        }
    }

    /**
     * @param value add value to the end of collection
     */
    public void push(T value) {
        in.push(value);
    }
}