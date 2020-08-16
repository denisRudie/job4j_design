package ru.job4j.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 3) {
            return false;
        }
        for (String arg : args) {
            Pattern p = Pattern.compile("^-[deo]=\\S+$");
            Matcher m = p.matcher(arg);
            if (!m.matches()) {
                return false;
            }
        }
        return true;
    }

    public String directory() {
        String[] d = args[0].split("=");
        return d[1];
    }

    public String exclude() {
        String[] d = args[1].split("=");
        return d[1];
    }

    public String output() {
        String[] d = args[2].split("=");
        return d[1];
    }
}