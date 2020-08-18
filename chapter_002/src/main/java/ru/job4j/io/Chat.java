package ru.job4j.io;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Chat {
    private static final String PAUSE = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String CLOSE = "закончить";

    private static int getRowNumber(int size) {
        return ThreadLocalRandom.current().nextInt(0, size);
    }

    private static void startChatting() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> strings;
            StringBuilder sb = new StringBuilder();

            try (BufferedReader fileReader = new BufferedReader(new FileReader("answersForChat.txt"))) {
                strings = fileReader.lines()
                        .collect(Collectors.toList());
            }

            String in;
            boolean running = true;
            boolean open = true;

            while (open) {
                in = reader.readLine();
                sb.append(getTime())
                        .append(" You: ")
                        .append(in).append(System.lineSeparator());
                if (in.equals(CLOSE)) {
                    open = false;
                } else if (running) {
                    if (in.equals(PAUSE)) {
                        running = false;
                    } else {
                        sb.append(getTime())
                                .append(" bot: ")
                                .append(strings.get(getRowNumber(strings.size())))
                                .append(System.lineSeparator());
                        System.out.println(strings.get(getRowNumber(strings.size())));
                    }
                } else if (in.equals(CONTINUE)) {
                    running = true;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt"))) {
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTime() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        LocalDateTime now = LocalDateTime.now();
        return sdf.format(now);
    }

    public static void main(String[] args) {
        startChatting();
    }
}
