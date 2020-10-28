package ru.job4j.concurrent.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService es = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String patternSubj = "Notification {username} to email {email}";
        String patternBody = "Add a new event to {username}";

        Runnable task = () -> {
            String subject = patternSubj
                    .replaceFirst("\\{username}", user.getUsername())
                    .replaceFirst("\\{email}", user.getEmail());
            String body = patternBody
                    .replaceFirst("\\{username}", user.getUsername());
            send(subject, body, user.getEmail());
        };
        es.submit(task);
        es.isTerminated();
    }

    public void close() {
        es.shutdown();
        while (!es.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) {
        System.out.println("subject " + subject + "\nbody " + body + "\nemail " + email);
    }

    public static void main(String[] args) {
        User u = new User("Sam", "sam@gmail.com");
        EmailNotification emailNoti = new EmailNotification();
        emailNoti.emailTo(u);
        emailNoti.close();
    }
}
