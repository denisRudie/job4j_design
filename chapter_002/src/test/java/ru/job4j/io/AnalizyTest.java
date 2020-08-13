package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void whenHave2PeriodsOfUnavailable() throws IOException {
        File source = temp.newFile();
        File target = temp.newFile();
        Analizy analizy = new Analizy();

        try (PrintWriter pw = new PrintWriter(new FileWriter(source))) {
            pw.println("200 10:56:01\n" +
                    "500 10:57:01\n" +
                    "400 10:58:01\n" +
                    "200 10:59:01\n" +
                    "500 11:01:02\n" +
                    "200 11:02:02");
        }

        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))){
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
        }
    }
}