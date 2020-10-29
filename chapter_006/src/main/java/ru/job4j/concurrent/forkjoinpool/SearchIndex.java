package ru.job4j.concurrent.forkjoinpool;

import ru.job4j.concurrent.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex {

    private final User[] users;

    public SearchIndex(User[] users) {
        this.users = users;
    }

    public Optional<User> searchById(int id) {
        ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        User rsl = fjp.invoke(new Task(id, users));
        if (rsl == null) {
            return Optional.empty();
        } else {
            return Optional.of(rsl);
        }
    }

    private static class Task extends RecursiveTask<User> {

        private static final long serialVersionUID = -3189026370079821321L;
        private final int threshold = 10;
        private final int id;
        private final User[] users;

        private Task(int id, User[] users) {
            this.id = id;
            this.users = users;
        }

        @Override
        protected User compute() {
            if (users.length <= threshold) {
                Optional<User> rsl =
                        Arrays.stream(users).filter(user -> user.getId() == id).findFirst();
                return rsl.orElse(null);
            }

            int mid = users.length / 2;

            Task left = new Task(id, Arrays.copyOfRange(users, 0, mid));
            Task right = new Task(id, Arrays.copyOfRange(users, mid, users.length));

            left.fork();
            right.fork();

            User first = left.join();
            User second = right.join();

            if (first != null && first.getId() == id) {
                return first;
            } else if (second != null && second.getId() == id) {
                return second;
            }
            return null;
        }
    }
}

