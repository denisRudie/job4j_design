package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> rslList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String from = null;

            while (reader.ready()) {
                String s = reader.readLine();
                String[] temp = s.split("\\s");
                if (temp[0].equals("400") || temp[0].equals("500")) {
                    if (from == null) {
                        from = temp[1];
                    }
                } else {
                    if (from != null) {
                        rslList.add(from + ";" + temp[1]);
                        from = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            rslList.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}