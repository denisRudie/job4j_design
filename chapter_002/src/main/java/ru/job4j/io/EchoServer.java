package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    private static final String HELLO = "Hello";
    private static final String EXIT = "Exit";

    public static void serverStart() {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean working = true;

            while (working) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    StringBuilder msg = new StringBuilder();
                    boolean firstString = true;

                    while (!(str = in.readLine()).isEmpty()) {
                        if (firstString) {
                            String req = getRequest(str);
                            if (req.equals(HELLO)) {
                                msg.append("Hello, my dear friend!");
                            } else if (req.equals(EXIT)) {
                                working = false;
                            } else {
                                msg.append(req);
                            }
                            firstString = false;
                        }
                        System.out.println(str);
                    }

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (working) {
                        out.write(msg.toString().getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRequest(String request) {
        Pattern p = Pattern.compile("(/\\?msg=).+(\\sHTTP/1\\.1)");
        Matcher m = p.matcher(request);
        if (!m.find()) {
            return "Bad request";
        }
        return request.substring(m.end(1), m.start(2));
    }

    public static void main(String[] args) {
        serverStart();
    }
}