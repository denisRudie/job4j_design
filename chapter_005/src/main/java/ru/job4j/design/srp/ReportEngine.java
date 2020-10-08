package ru.job4j.design.srp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public File generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append("<h1>Отчет по зарплатам сотрудников</h1>");
        text.append("<h2>Name; Salary;</h2>");
        List<Employee> list = store.findBy(filter);
        list.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (Employee employee : list) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary() * 0.87).append(";")
                    .append("</p>");
        }
        text.append("</html>");
        String html = text.toString();
        File report = new File("report.html");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            writer.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
}
