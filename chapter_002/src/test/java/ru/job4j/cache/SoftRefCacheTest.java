package ru.job4j.cache;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SoftRefCacheTest {

    @Test
    public void whenAddFileIntoCacheThenTryToRequest() {
        File testFile = new File("./src/main/java/ru/job4j/cache/files/test99.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Testing...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SoftRefCache softRefCache = SoftRefCache.createCache(
                "./src/main/java/ru/job4j/cache/files");
        String txt = softRefCache.getText("test99.txt");
        assertThat(txt, is("Testing..."));
    }
}