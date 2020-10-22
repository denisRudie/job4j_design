package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.io.*;

@ThreadSafe
public class ParseFile {

    private volatile File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
        String output = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            output = reader.lines().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();

        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) != -1) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
