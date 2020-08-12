package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

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
                        out.println(from + ";" + temp[1]);
                        from = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}