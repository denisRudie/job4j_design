package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.CarBrand;
import ru.job4j.model.CarModel;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            CarBrand brand = new CarBrand();
            brand.setName("Toyota");

            for (int i = 1; i <= 5; i++) {
                CarModel model = new CarModel();
                model.setName("model" + i);
                int modelId = (Integer) session.save(model);
                brand.addModel(session.get(CarModel.class, modelId));
            }

            session.save(brand);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}