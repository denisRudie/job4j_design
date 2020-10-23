package ru.job4j.concurrent.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        User rsl = users.putIfAbsent(user.getId(), new User(user.getId(), user.getAmount()));
        return rsl == null;
    }

    public synchronized boolean update(User user) {
        boolean check;
        check = delete(user);
        check &= add(user);
        return check;
    }

    public synchronized boolean delete(User user) {
        User rsl = users.remove(user.getId());
        return rsl != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);

        if (from != null && to != null && from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            return true;
        }
        return false;
    }
}
