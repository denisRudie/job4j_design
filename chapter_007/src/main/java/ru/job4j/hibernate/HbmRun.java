package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.CarBrand;
import ru.job4j.model.CarModel;

import java.util.List;

public class HbmRun {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    public void addData() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            CarBrand brand = new CarBrand();
            brand.setName("Toyota");

            for (int i = 1; i <= 5; i++) {
                CarModel model = new CarModel();
                model.setName("model" + i);
                brand.addModel(model);
                model.setBrand(brand);
            }
            session.save(brand);
            session.getTransaction().commit();
        }
    }

    public List<CarBrand> getData() {
        List<CarBrand> brands;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            brands = session.createQuery(
                    "select distinct cb from CarBrand cb join fetch cb.models")
                    .list();
            session.getTransaction().commit();
        }
        return brands;
    }

    public static void main(String[] args) {
        HbmRun hbmRun = new HbmRun();
        hbmRun.addData();
        List<CarBrand> brands = hbmRun.getData();

        for (CarBrand carBrand : brands) {
            for (CarModel model : carBrand.getModels()) {
                System.out.println(model.getName());
            }
        }
    }
}