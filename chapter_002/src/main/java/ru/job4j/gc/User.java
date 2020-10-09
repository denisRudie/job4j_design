package ru.job4j.gc;

import java.util.Date;
import java.util.UUID;

public class User {
    private static int gcCounter;
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
        gcCounter++;
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            new User(UUID.randomUUID().toString(), new Date());
            gcCounter++;
        }

//        WeakReference<User> weak = new WeakReference<>(new User(UUID.randomUUID().toString(),
//                new Date()));
//        SoftReference<User> soft = new SoftReference<>(new User(UUID.randomUUID().toString(),
//                new Date()));
//        System.gc();
//        System.out.println(weak.get());
//        System.out.println(soft.get());
    }

    public static long getUsedMemory() {
        int kb = 1024;
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / kb;
    }
}
