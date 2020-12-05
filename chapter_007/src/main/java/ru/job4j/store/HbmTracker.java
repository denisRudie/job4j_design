package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.tracker.Item;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl;
        Session session = sf.openSession();
        session.beginTransaction();
        Item existing = session.get(Item.class, Integer.parseInt(id));

        if (existing != null) {
            item.setId(existing.getId());
            session.merge(item);
            rsl = true;
        } else {
            rsl = false;
        }
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl;
        Session session = sf.openSession();
        session.beginTransaction();
        Item existing = session.get(Item.class, Integer.parseInt(id));
        if (existing != null) {
            session.delete(existing);
            rsl = true;
        } else {
            rsl = false;
        }
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where name =:name ");
        query.setParameter("name", key);
        List<Item> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static void main(String[] args) {
        HbmTracker hbmTracker = new HbmTracker();
//        hbmTracker.findAll().forEach(System.out::println);
        Item item = new Item();
        item.setName("Test123");
//        hbmTracker.add(item);
//        hbmTracker.replace("118", item);
//        hbmTracker.delete("118");
//        hbmTracker.findByName("Hibernate").forEach(System.out::println);
        System.out.println(hbmTracker.findById("115"));
    }
}
