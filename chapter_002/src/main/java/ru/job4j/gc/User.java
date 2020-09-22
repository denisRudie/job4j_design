package ru.job4j.gc;

import java.util.Date;
import java.util.UUID;

public class User {
    private String name;
    private Date birthday;

    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("GC destroying object: %s%n", this.getName());
    }

    public static void main(String[] args) {
        long usedMemBefore = getUsedMemory();
        for (int i = 0; i < 1000; i++) {
            new User(UUID.randomUUID().toString(), new Date());
        }
        long usedMemAfter = getUsedMemory();
        System.out.printf("object size: %d%n", usedMemAfter - usedMemBefore);

    }

    public static long getUsedMemory() {
//        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
