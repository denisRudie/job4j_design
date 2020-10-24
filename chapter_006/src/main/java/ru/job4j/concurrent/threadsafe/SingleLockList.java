package ru.job4j.concurrent.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArray;

import java.util.Iterator;
import java.util.function.Function;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> list;
    private final Function<T, T> copyMethod;

    public SingleLockList(Function<T, T> copyMethod) {
        this.list = new SimpleArray<>();
        this.copyMethod = copyMethod;
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copyList(list).iterator();
    }

    private synchronized SimpleArray<T> copyList(SimpleArray<T> list) {
        SimpleArray<T> copy = new SimpleArray<>();

        for (T t : list) {
            T newT = copyMethod.apply(t);
            copy.add(newT);
        }
        return copy;
    }
}