package ru.job4j.concurrent.threadsafe;

import org.junit.Test;
import ru.job4j.concurrent.storage.User;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SingleLockListTest {

    @Test
    public void addInt() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(x -> x);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test
    public void addUser() throws InterruptedException {
        SingleLockList<User> list = new SingleLockList<>(u -> new User(u.getId(), u.getAmount()));
        User u1 = new User(1, 100);
        User u2 = new User(2, 200);
        Thread first = new Thread(() -> list.add(u1));
        Thread second = new Thread(() -> list.add(u2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<User> rsl = new HashSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(u1, u2)));
    }
}