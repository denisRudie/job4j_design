package ru.job4j.io;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell {
    private final List<String> elements = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();

    public void cd(String path) {
        String command = "";

//        looking for complex request
        Pattern p = Pattern.compile("/\\w+");
        Matcher m = p.matcher(path);
        while (m.find()) {
            elements.add(m.group().substring(1));
        }

//        looking for simple request
        Pattern p2 = Pattern.compile("^\\w+$");
        Matcher m2 = p2.matcher(path);
        if (m2.find()) {
            elements.add(m2.group());
        }

//        looking for command
        Pattern patCmd = Pattern.compile("(\\.\\.|/)$");
        Matcher matCmd = patCmd.matcher(path);
        if (matCmd.find()) {
            command = (matCmd.group());
        }

        if (command.equals("..")) {
            back();
        } else if (command.equals("/")) {
            root();
        }
    }

    private void back() {
        if (elements.size() > 0) {
            elements.remove(elements.size() - 1);
        }
    }

    private void root() {
        elements.clear();
    }

    public String pwd() {
        if (elements.size() == 0) {
            sb.append("/");
        } else {
            elements.forEach(s -> {
                sb.append("/");
                sb.append(s);
            });
        }
        return sb.toString();
    }
}