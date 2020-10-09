package ru.job4j.design.srp;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportEngineTest {
    private ReportEngine engine;
    private File report;
    private Employee worker1;
    private Employee worker2;

    @Before
    public void preparingTestData() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        worker1 = new Employee("Ivan", now, now, 90);
        worker2 = new Employee("Petr", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        engine = new ReportEngine(store);
    }

    @Test
    public void generateHtmlReport() {
        report = engine.generateHtml(employee -> true);
        List<String> read = new ArrayList<>();
        try {
            Document page = Jsoup.parse(report, "UTF-8");
            Elements elements = page.getElementsByTag("p");
            read = new ArrayList<>();
            elements.stream()
                    .map(Element::text)
                    .forEach(read::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(read.get(0),
                String.format("%s;%s;", worker2.getName(), worker2.getSalary()));
        assertEquals(read.get(1),
                String.format("%s;%s;", worker1.getName(), worker1.getSalary()));
    }

    @Test
    public void generateJsonReport() {
        Gson gson = new GsonBuilder().create();
        report = engine.generateJson(employee -> true);
        List<Employee> read = new ArrayList<>();
        Type employeeListType = new TypeToken<List<Employee>>() {
        }.getType();
        try (FileReader reader = new FileReader(report)) {
            read = gson.fromJson(reader, employeeListType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(worker2, read.get(0));
        assertEquals(worker1, read.get(1));
    }

    @Test
    public void generateXmlReport() throws IOException {
        report = engine.generateXml(employee -> true);
        XmlMapper xmlMapper = new XmlMapper();
        JavaType type = xmlMapper.getTypeFactory()
                .constructCollectionType(ArrayList.class, Employee.class);
        List<Employee> read = xmlMapper.readValue(report, type);

        assertEquals(worker2, read.get(0));
        assertEquals(worker1, read.get(1));
    }
}