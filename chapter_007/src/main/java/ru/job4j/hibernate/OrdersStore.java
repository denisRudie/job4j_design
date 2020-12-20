package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Order;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdersStore implements AutoCloseable {

    private final static OrdersStore INST = new OrdersStore();
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final   SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private OrdersStore() {

    }

    public static OrdersStore instOf() {
        return INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        T rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = command.apply(session);
            session.getTransaction().commit();
        }
        return rsl;
    }

    private void consume(final Consumer<Session> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            command.accept(session);
            session.getTransaction().commit();
        }
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    public int add(Order o) {
        return tx(session -> (Integer) session.save(o));
    }

    public Order findById(int id) {
        return tx(session -> session.get(Order.class, id));
    }

    public Collection<Order> findAll() {
        return tx(session -> session.createQuery("from Order ").list());
    }

    public void update(Order o) {
        consume(session -> session.update(o));
    }

    public static void main(String[] args) {
        OrdersStore ordersStore = OrdersStore.instOf();
//        ordersStore.add(Order.of("2", "3"));
//        System.out.println(ordersStore.findById(1));
        ordersStore.findAll().forEach(System.out::println);
    }
}
