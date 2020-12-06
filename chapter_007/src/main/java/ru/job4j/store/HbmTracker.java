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
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            item.setId(Integer.parseInt(id));
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = new Item();
            item.setId(Integer.parseInt(id));
            session.delete(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
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
        System.out.println(hbmTracker.replace("150", item));
//        System.out.println(hbmTracker.delete("116"));
//        hbmTracker.add(item);
//        hbmTracker.replace("118", item);
//        hbmTracker.delete("118");
//        hbmTracker.findByName("Hibernate").forEach(System.out::println);
//        System.out.println(hbmTracker.findById("115"));
    }
}
