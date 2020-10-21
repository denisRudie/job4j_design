package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadLimitSpeed {

    public static void main(String[] args) throws Exception {
        String url = args[3];
        int downloadSpeed = Integer.parseInt(args[4]);

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[downloadSpeed * 1024];
            int bytesRead;

            while (true) {
                long start = System.currentTimeMillis();
                if ((bytesRead = in.read(dataBuffer)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    long finish = System.currentTimeMillis();
                    long downloadTime = finish - start;
                    Thread.sleep(1000 - downloadTime);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
