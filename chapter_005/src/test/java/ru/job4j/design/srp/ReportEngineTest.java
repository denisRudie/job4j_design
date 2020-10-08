package ru.job4j.design.srp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class ReportEngineTest {

    @Test
    public void generateHtmlReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 90);
        Employee worker2 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        ReportEngine engine = new ReportEngine(store);
        File report = engine.generate(employee -> true);
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(report))) {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder expect = new StringBuilder()
                .append("<html>")
                .append("<h1>Отчет по зарплатам сотрудников</h1>")
                .append("<h2>Name; Salary;</h2>")
                .append("<p>")
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary() * 0.87).append(";")
                .append("</p>")
                .append("<p>")
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary() * 0.87).append(";")
                .append("</p>")
                .append("</html>");
        assertEquals(s, expect.toString());
    }
}