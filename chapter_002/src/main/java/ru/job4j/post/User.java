package ru.job4j.post;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private Set<String> email;

    public User(String name) {
        this.name = name;
        this.email = new HashSet<>();
    }

    public void addEmail(String email) {
        this.email.add(email);
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}
