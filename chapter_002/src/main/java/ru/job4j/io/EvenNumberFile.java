package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();

            while (in.available() > 0) {
                sb.append((char) in.read());
            }

            String[] strings = sb.toString().split(System.lineSeparator());

            Arrays.stream(strings)
                    .map(Integer::parseInt)
                    .filter(i -> i % 2 == 0)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
