package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadLimitSpeed {

    public static void downloadFile(String url, String targetFileSource, int downloadSpeed) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(targetFileSource)) {
            byte[] dataBuffer = new byte[downloadSpeed * 1024];
            int bytesRead;
            boolean complete = false;

            while (!complete) {
                long start = System.currentTimeMillis();
                if ((bytesRead = in.read(dataBuffer)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    long finish = System.currentTimeMillis();
                    long downloadTime = finish - start;
                    Thread.sleep(1000 - downloadTime);
                } else {
                    complete = true;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileDownloadLimitSpeed.downloadFile(
                args[3],
                "downloadedPom.xml",
                Integer.parseInt(args[4]));
    }
}
