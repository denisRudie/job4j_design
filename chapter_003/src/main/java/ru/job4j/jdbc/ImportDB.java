package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private static final Logger LOG = LoggerFactory.getLogger(ImportDB.class);
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] temp = s.split(";");
                if (temp.length > 1
                        && temp[0].matches(
                        "^(([\\p{L}\\p{N}])+(\\s)*([\\p{L}\\p{N}]))+$")
                        && temp[1].matches(
                        "^[\\w.\\-]+@([A-Za-z0-9]+([\\w.\\-]*[\\p{L}\\p{N}]+)*\\.)+[\\p{L}]*$")) {
                    users.add(new User(temp[0], temp[1]));
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             cnt.prepareStatement("insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream(
                "chapter_003/src/main/resources/app.properties")) {
            cfg.load(in);
            ImportDB db = new ImportDB(cfg, "chapter_003/src/main/resources/dump.txt");
            db.save(db.load());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}