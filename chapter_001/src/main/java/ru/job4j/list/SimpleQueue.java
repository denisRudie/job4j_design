package ru.job4j.list;


public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;

    /**
     * @return return first and remove it from collection
     */
    public T poll() {

            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
            }
            return out.pop();
    }

    /**
     * @param value add value to the end of collection
     */
    public void push(T value) {
        in.push(value);
        inCount++;
    }
}