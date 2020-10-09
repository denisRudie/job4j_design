package ru.job4j.design.srp;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Report generator.
 * Generates reports in .html, .json, .xml formats.
 */
public class ReportEngine {

    /**
     * Data store.
     */
    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Getting filtered and applied to custom format data.
     * Format:
     * -exclude fields: hired, fired.
     * -salary with taxes (13%).
     * -sorted by decreasing salary.
     *
     * @param filter Filters data for report.
     * @return Filtered Employee list in required format.
     */
    private List<Employee> getData(Predicate<Employee> filter) {
        List<Employee> data = store.findBy(filter);
        data.forEach(e -> e.setSalary(e.getSalary() * 0.87));
        data.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return data;
    }

    /**
     * Generates html report.
     *
     * @param filter filters data for report.
     * @return Html report file.
     */
    public File generateHtml(Predicate<Employee> filter) {
        List<Employee> data = getData(filter);
        File report = new File("report.html");

        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append("<h1>Отчет по зарплатам сотрудников</h1>");
        text.append("<h2>Name; Salary;</h2>");
        for (Employee employee : data) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>");
        }
        text.append("</html>");
        String html = text.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            writer.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }

    /**
     * Generates json report.
     *
     * @param filter filters data for report.
     * @return Json report file.
     */
    public File generateJson(Predicate<Employee> filter) {
        List<Employee> data = getData(filter);
        File report = new File("report.json");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(report)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }

    /**
     * Generates xml report.
     *
     * @param filter filters data for report.
     * @return xml report file.
     */
    public File generateXml(Predicate<Employee> filter) {
        List<Employee> data = getData(filter);
        File report = new File("report.xml");
        XmlMapper xmlMapper = new XmlMapper();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            String textXml = xmlMapper.writeValueAsString(data);
            writer.write(textXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
}
