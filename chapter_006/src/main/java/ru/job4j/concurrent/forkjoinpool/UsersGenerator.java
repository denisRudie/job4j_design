package ru.job4j.concurrent.forkjoinpool;

import ru.job4j.concurrent.User;

import java.util.concurrent.ThreadLocalRandom;

public class UsersGenerator {

    public static User[] generate(int count) {
        User[] users = new User[count];
        for (int i = 0; i < count; i++) {
            int id = ThreadLocalRandom.current().nextInt();
            users[i] = new User(i, "user" + i);
        }
        return users;
    }

    public static void main(String[] args) {
        User[] users = UsersGenerator.generate(10);
        System.out.println(users[8].getName());
    }
}
