package ru.job4j.io.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Issue {
    private final String name;
    private final int priority;
    private boolean isClosed;
    private final Contact author;
    private final String[] tags;

    public Issue(String name, int priority, Contact author, String[] tags) {
        this.name = name;
        this.priority = priority;
        this.isClosed = false;
        this.author = author;
        this.tags = tags;
    }

    public void closeIssue() {
        isClosed = true;
    }

    public void openIssue() {
        isClosed = false;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", isClosed=" + isClosed +
                ", author=" + author +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }

    public static void main(String[] args) {
        Issue issue1 = new Issue(
                "Mike",
                2,
                new Contact(123123, "+8(000)123-12-01", "Mike"),
                new String[]{"problem", "printer", "HR"});

        Issue issue2 = new Issue(
                "Tom",
                5,
                new Contact(123123, "+8(000)123-12-01", "Mike"),
                new String[]{"problem", "printer", "HR"});

        Issue[] issues = {issue1, issue2};

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            File tempFile = Files.createTempFile(null, null).toFile();
            try (FileWriter writer = new FileWriter(tempFile)) {
                gson.toJson(issues, writer);
            }
            try (FileReader reader = new FileReader(tempFile)) {
                 Issue[] read = gson.fromJson(reader, Issue[].class);
                System.out.println(Arrays.toString(read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
