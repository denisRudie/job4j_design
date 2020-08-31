package ru.job4j.io;

import java.util.Arrays;
import java.util.LinkedList;

public class Shell {
    private LinkedList<String> list = new LinkedList<>();

    public void cd(String path) {
        if (path.equals("/")) {
            list.clear();
            return;
        }

        Arrays.stream(path.split("/"))
                .filter(s -> !s.equals(""))
                .forEach(list::add);

        if (list.getLast().equals("..")) {
            list.pollLast();
            list.pollLast();
        }
    }

    public String pwd() {
        StringBuilder sb = new StringBuilder();

        if (list.size() == 0) {
            sb.append("/");
        } else {
            list.forEach(s -> {
                sb.append("/");
                sb.append(s);
            });
        }
        return sb.toString();
    }
}