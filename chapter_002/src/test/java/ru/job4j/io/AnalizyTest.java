package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void whenHave2PeriodsOfUnavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/log.txt", "unavailable.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable.csv"))){
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}