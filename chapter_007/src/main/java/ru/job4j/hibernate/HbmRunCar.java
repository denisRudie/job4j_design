package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Car;
import ru.job4j.model.Driver;
import ru.job4j.model.Engine;

public class HbmRunCar {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Engine engine = new Engine();
            engine.setName("engine1");
            session.save(engine);

            Driver driver1 = new Driver();
            driver1.setName("driver1");
            session.save(driver1);
            Driver driver2 = new Driver();
            driver2.setName("driver2");
            session.save(driver2);

            Car car = new Car();
            car.setEngine(engine);
            car.setModel("model1");
            car.addDriver(driver1);
            car.addDriver(driver2);

            session.save(car);

            session.getTransaction().commit();
        }
    }
}