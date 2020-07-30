package ru.job4j.list;


public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    /**
     * @return return first and remove it from collection
     */
    public T poll() {

            while (inCount > 0) {
                out.push(in.pop());
                outCount++;
                inCount--;
            }

            T value = out.pop();
            outCount--;

            while (outCount > 0) {
                in.push(out.pop());
                outCount--;
                inCount++;
            }
            return value;
    }

    /**
     * @param value add value to the end of collection
     */
    public void push(T value) {
        in.push(value);
        inCount++;
    }
}