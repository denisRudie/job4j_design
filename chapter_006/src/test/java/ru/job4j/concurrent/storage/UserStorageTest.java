package ru.job4j.concurrent.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAdd() {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        assertTrue(storage.add(u1));
    }

    @Test
    public void whenAddThenRemove() {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        storage.add(u1);
        assertTrue(storage.delete(u1));
    }

    @Test
    public void whenRemoveNotExistingUser() {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        assertFalse(storage.delete(u1));
    }

    @Test
    public void whenAddThenUpdate() {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        storage.add(u1);
        User u2 = new User(1, 200);
        assertTrue(storage.update(u2));
    }

    @Test
    public void whenTransferMoneyBetweenTwoUsers() {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        User u2 = new User(2, 200);
        storage.add(u1);
        storage.add(u2);

        assertTrue(storage.transfer(1, 2, 100));
        assertTrue(storage.transfer(2, 1, 300));
    }

    @Test
    public void whenConcurrentDeleteUserAndTransferMoneyToHim() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User u1 = new User(1, 100);
        User u2 = new User(2, 200);
        storage.add(u1);
        storage.add(u2);

        Thread thread1 = new Thread(() -> storage.transfer(1, 2, 50));
        Thread thread2 = new Thread(() -> storage.transfer(2, 1, 50));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertTrue(storage.transfer(1, 2, 100));
        assertTrue(storage.transfer(2, 1, 300));
    }
}